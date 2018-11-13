package com.viettel.asset.business;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Workbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.viettel.asset.bo.CatAssetCode;
import com.viettel.asset.bo.SysGroup;
import com.viettel.asset.dao.CatAssetCodeDao;
import com.viettel.asset.dao.LongTermAssetDao;
import com.viettel.asset.dao.LongTermAssetEntityDao;
import com.viettel.asset.dao.LongTermAssetHistoryDao;
import com.viettel.asset.dao.MerHandOverInfoDao;
import com.viettel.asset.dao.SysGroupDao;
import com.viettel.asset.dto.AssetReoportS23Dto;
import com.viettel.asset.dto.AssetReportS21DetailDto;
import com.viettel.asset.dto.AssetReportS21SearchDto;
import com.viettel.asset.dto.AutocompleteSearchDto;
import com.viettel.asset.dto.LongTermAssetAutoCompleteDto;
import com.viettel.asset.dto.LongTermAssetHistoryReportS23Dto;
import com.viettel.asset.dto.ReportIncreaseDecreaseDto;
import com.viettel.asset.dto.ReportS32LongTermAssetEntityMerDto;
import com.viettel.asset.dto.SysGroupDto;
import com.viettel.ktts2.common.BusinessException;
import com.viettel.ktts2.common.ResponseMessage;
import com.viettel.ktts2.common.UDate;
import com.viettel.ktts2.common.UString;

import fr.opensagres.xdocreport.core.io.internal.ByteArrayOutputStream;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;
import net.sf.jxls.transformer.XLSTransformer;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class AssetReportBusiness {
	private static final Long ASSET_TYPE_INCREASE=2l;//Loại ghi tăng
	private static final Long ASSET_TYPE_DECREASE=2l;//loại ghi giảm

    @Autowired
    BusinessLogBusiness businessLogBusiness;
    @Autowired
    SysGroupBusiness sysGroupBusiness;
    @Autowired
    SysGroupDao sysGroupDao;
    @Autowired
    CatAssetCodeDao catAssetCodeDao;
    @Autowired
    MerHandOverInfoDao merHandOverInfoDao;

    @Autowired
    LongTermAssetHistoryDao longTermAssetHistoryDao;
    @Autowired
    LongTermAssetDao longTermAssetDao;
    @Autowired
    LongTermAssetEntityDao longTermAssetEntityDao;

    public List<SysGroupDto> filterSysGroup(AutocompleteSearchDto autocompleteSearchDto) {
        List<SysGroup> lstFilterStartWidth = sysGroupDao.filterStartWidth(autocompleteSearchDto);
        if (lstFilterStartWidth.size() < autocompleteSearchDto.getPageSize()) {
            List<Long> ids = lstFilterStartWidth.stream().map(x -> x.getGroupId()).collect(Collectors.toList());
            List<SysGroup> lstFilterAll = sysGroupDao.filterAll(autocompleteSearchDto, ids);
            lstFilterStartWidth.addAll(lstFilterAll);
        }
        return lstFilterStartWidth.stream().map(x -> new SysGroupDto(x)).collect(Collectors.toList());
    }

    //anh commit code thieu, build loi doan nay
    //filter longterm asset theo ma	
    public List<LongTermAssetAutoCompleteDto> filterLongTermAsset(AutocompleteSearchDto autocompleteSearchDto) {
        return longTermAssetDao.filterLongTermAsset(autocompleteSearchDto);
    }

    public String exportAssetS21( AssetReportS21SearchDto dto, String type) throws Exception {

        //Get Data
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        String filePath = classloader.getResource("../" + "doc-template").getPath();

        //Set report year
        if (dto.getFromDate() == null || dto.getToDate() == null) {
            throw new BusinessException("error.reportS21.Date.null");
        }
        Calendar fromCal = Calendar.getInstance();
        fromCal.setTime(dto.getFromDate());
        Calendar toCal = Calendar.getInstance();
        toCal.setTime(UDate.toEndOfDate(dto.getToDate()));
        

        if (fromCal.after(toCal) || fromCal.get(Calendar.YEAR) != toCal.get(Calendar.YEAR)) {
            throw new BusinessException("error.reportS21.Date.notInAyear");
        }
        dto.setReportYear(String.valueOf(fromCal.get(Calendar.YEAR)));
        dto.setToDate(toCal.getTime());

        //Thông tin đơn vị
        SysGroup sysGroup = sysGroupDao.find(dto.getGroupId());
        if (sysGroup == null) {
            throw new BusinessException("error.reportS21.sysGroup.groupId.notInDb");
        }

        dto.setGroupName(sysGroup.getName());
        dto.setGroupAddress(sysGroup.getAddress() == null ? "" : sysGroup.getAddress());

        //Thông tin loại tài sản
        CatAssetCode catAssetCode = catAssetCodeDao.find(dto.getCatAssetCodeId());
        if (catAssetCode == null) {
            throw new BusinessException("error.reportS21.catAssetCode.catAssetCodeId.notInDb");
        }
        dto.setCaacName(catAssetCode.getCaacName() == null ? "" : catAssetCode.getCaacName());
        dto.setCaacFullCode(UString.trim(catAssetCode.getCaacFullCode()));
        List<AssetReportS21DetailDto> data = longTermAssetHistoryDao.getDataReportS21(dto);
        String path;
        if ("S21".equals(type)) {
            path = filePath + "/So_TSCD_(mau_S21).docx";
            long total = 0l;
            for (AssetReportS21DetailDto detail : data) {
                total += detail.getOriginalPrice();
            }
            dto.setOrginalPriceTotal(String.valueOf(total));
        } else if ("S22".equals(type)) {
            path = filePath + "/So_TSCD_va_CCDC_tai_noi_su_dung_(mau_S22).docx";
        } else {
            throw new BusinessException("error.report.TypeReport.invalid");
        }
        try (InputStream in = new FileInputStream(path)) {
            IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

            IContext context = report.createContext();
            FieldsMetadata metadata = report.createFieldsMetadata();
            metadata.load("cvnts", AssetReportS21DetailDto.class, true);

            context.put("item", dto);
            context.put("cvnts", data);
            try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                /*Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);		
				report.convert(context, options, out);*/
                report.process(context, out);
                out.close();

                return Base64.getEncoder().encodeToString(out.toByteArray());

            }

        }
    }

    public ResponseMessage exportAssetS23(  AssetReportS21SearchDto dto, String type) throws Exception {
        //Get Data
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        String filePath = classloader.getResource("../" + "doc-template").getPath();

        //Set report year
        if (dto.getFromDate() == null || dto.getToDate() == null) {
            throw new BusinessException("error.reportS21.Date.null");
        }
        Calendar fromCal = Calendar.getInstance();
        fromCal.setTime(dto.getFromDate());
        Calendar toCal = Calendar.getInstance();
//        toCal.setTime(dto.getToDate());
        toCal.setTime(UDate.toEndOfDate(dto.getToDate()));

        if (fromCal.after(toCal) || fromCal.get(Calendar.YEAR) != toCal.get(Calendar.YEAR)) {
            throw new BusinessException("error.reportS21.Date.notInAyear");
        }
        dto.setReportYear(String.valueOf(fromCal.get(Calendar.YEAR)));
        dto.setToDate(toCal.getTime());

        //Thông tin đơn vị
        SysGroup sysGroup = sysGroupDao.find(dto.getGroupId());
        if (sysGroup == null) {
            throw new BusinessException("error.reportS21.sysGroup.groupId.notInDb");
        }

        dto.setGroupName(sysGroup.getName());
        dto.setGroupAddress(sysGroup.getAddress() == null ? "" : sysGroup.getAddress());

        //Thông tin  tài sản
        //Lấy list lịch sử tài sản
        List<AssetReoportS23Dto> data = longTermAssetHistoryDao.getDataReportS23(dto);
        LongTermAssetHistoryReportS23Dto reportDto;
        AssetReoportS23Dto decrease;
        if (!data.isEmpty()) {
            if (!ASSET_TYPE_INCREASE.equals(data.get(0).getLtahType())) {//Không phải  là loại tăng -> thực hiện lấy bản ghi tăng từ db    			
                reportDto = longTermAssetHistoryDao.getIncreaseReportS23InPreviousTime(dto);
            } else {
                //Nếu là bản ghi tăng thì thực hiện lấy thông tin từ dto;
                reportDto = longTermAssetHistoryDao.getIncreaseReportS23ByHistoryId(data.get(0).getLongTermAssetHistoryId());

            }
            if (ASSET_TYPE_DECREASE.equals(data.get(data.size() - 1).getLtahType())) { // nếu cuối cùng là loại ghi giảm    			
                decrease = data.get(data.size() - 1);
                reportDto.setDecreaseVoucherCode(decrease.getVoucherCode());
                reportDto.setDecreaseVoucherDate(decrease.getVoucherDate());
                reportDto.setDecreaseReasonChange(decrease.getReasonChange());
            }
        } else {
        	//Không có bản ghi lịch sử -> xử lý chứ không thrown exception
        	reportDto = longTermAssetHistoryDao.getIncreaseReportS23InPreviousTime(dto);
        }
        List<ReportS32LongTermAssetEntityMerDto> lsReportS32LongTermAssetEntityMerDtos = longTermAssetEntityDao.getReportS32LongTermAssetEntity(dto.getLongTermAssetId());

        try (InputStream in = new FileInputStream(filePath + "/The_TSCD_(mau_23).docx")) {
            IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

            IContext context = report.createContext();
            FieldsMetadata metadata = report.createFieldsMetadata();
            metadata.load("cvnts", AssetReoportS23Dto.class, true);
            metadata.load("mer", ReportS32LongTermAssetEntityMerDto.class, true);

            context.put("item", reportDto);
            context.put("cvnts", data);
            context.put("mer", lsReportS32LongTermAssetEntityMerDtos);
            try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                report.process(context, out);
                out.close();
                ResponseMessage rs=new ResponseMessage(Base64.getEncoder().encodeToString(out.toByteArray()));
                return rs;
            }

        }
    }

    public String exportAssetBaseDonVi( AssetReportS21SearchDto dto, String type) throws Exception {
        //Get Data
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        String filePath = classloader.getResource("../" + "doc-template").getPath();
        //Set report year
        if (dto.getFromDate() == null || dto.getToDate() == null) {
            throw new BusinessException("error.reportS21.Date.null");
        }
        Calendar fromCal = Calendar.getInstance();
        fromCal.setTime(dto.getFromDate());
        fromCal.set(Calendar.DATE, 1);

        Calendar toCal = Calendar.getInstance();
        toCal.setTime(dto.getFromDate());
        fromCal.set(Calendar.DATE, 1);
        toCal.add(Calendar.MONTH, 1);
        toCal.set(Calendar.DAY_OF_MONTH, 1);

        //Thông tin đơn vị
        SysGroup sysGroup = sysGroupDao.find(dto.getGroupId());
        if (sysGroup == null) {
            throw new BusinessException("error.reportS21.sysGroup.groupId.notInDb");
        }

        dto.setGroupName(sysGroup.getName());
        dto.setFromDate(fromCal.getTime());
        dto.setToDate(toCal.getTime());
        dto.setGroupAddress(sysGroup.getAddress() == null ? "" : sysGroup.getAddress());

        List<ReportIncreaseDecreaseDto> lstReportIncreaseDecreaseDto = longTermAssetHistoryDao.getDataReportTscdOrganizeBase(dto);

//        try (InputStream in = new FileInputStream(filePath + "/ASSET_REPORT_TANG_GIAM_THEO_DON_VI.xlsx")) {
        	try (InputStream in = new FileInputStream(filePath + "/ASSET_BIEN_DONG_TAI_SAN_TRONG_LY_THEO_DON_VI.xlsx")) {
        	
            Map beans = new HashMap();
            beans.put("groupName", sysGroup.getName());
            beans.put("fromDate", UDate.toSimpleFormat(dto.getFromDate()));
            beans.put("toDate", UDate.toSimpleFormat(UDate.toEndOfPreviousDate(dto.getToDate())));
            beans.put("obj", lstReportIncreaseDecreaseDto);
            XLSTransformer transformer = new XLSTransformer();
            Workbook resultWorkbook = transformer.transformXLS(in, beans);

            //resultWorkbook.write(stream);
            try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
                resultWorkbook.write(bos);
                return Base64.getEncoder().encodeToString(bos.toByteArray());

            }

        }
    }

    public String exportAssetAllDonVi( AssetReportS21SearchDto dto, String type) throws Exception {
        //Get Data
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        String filePath = classloader.getResource("../" + "doc-template").getPath();
        //Set report year
        if (dto.getFromDate() == null || dto.getToDate() == null) {
            throw new BusinessException("error.reportS21.Date.null");
        }
        Calendar fromCal = Calendar.getInstance();
        fromCal.setTime(dto.getFromDate());
        fromCal.set(Calendar.DATE, 1);

        Calendar toCal = Calendar.getInstance();
        toCal.setTime(dto.getFromDate());
        fromCal.set(Calendar.DATE, 1);
        toCal.add(Calendar.MONTH, 1);
        toCal.set(Calendar.DAY_OF_MONTH, 1);

        dto.setFromDate(fromCal.getTime());
        dto.setToDate(toCal.getTime());

        dto.setLotaType(1l);
        List<ReportIncreaseDecreaseDto> lstTSCD = longTermAssetHistoryDao.exportAssetAllDonVi(dto);
        dto.setLotaType(2l);
        List<ReportIncreaseDecreaseDto> lstCDC = longTermAssetHistoryDao.exportAssetAllDonVi(dto);

        try (InputStream in = new FileInputStream(filePath + "/ASSET_BIEN_DONG_TAI_SAN_TRONG_LY_ALL_DON_VI.xlsx")) {
            Map beans = new HashMap();
            beans.put("fromDate", UDate.toSimpleFormat(dto.getFromDate()));
            beans.put("toDate", UDate.toSimpleFormat(UDate.toEndOfPreviousDate(dto.getToDate())));
            beans.put("objTSCD", lstTSCD);
            beans.put("objCDC", lstCDC);
            XLSTransformer transformer = new XLSTransformer();
            Workbook resultWorkbook = transformer.transformXLS(in, beans);

            //resultWorkbook.write(stream);
            try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
                /*Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);		
				report.convert(context, options, out);*/
                resultWorkbook.write(bos);
                return Base64.getEncoder().encodeToString(bos.toByteArray());

            }

        }
    }

    public List<Long> notifyMerNotCreateAsset() throws Exception {
        //firstIndex: là loại hình thành qua xây lắp
        //secondIndex: là loại hình thành không qua xây lắp
        List<Long> result = new ArrayList<Long>();
        // get tong so ban ghi hinh thanh qua xay lap
        Long countHinhThanhQuaXayLap = longTermAssetDao.countNoteCreatedByBuilding();
        Long countHinhThanhKhongQuaXayLap = longTermAssetDao.countNoteNotCreatedByBulding();
        result.add(countHinhThanhQuaXayLap);
        result.add(countHinhThanhKhongQuaXayLap);
        //get tong so ban ghi hinh thanh khong qua xay lap
        return result;
    }

}
