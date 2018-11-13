package com.viettel.qll.business;
 
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import com.google.common.collect.Lists;
import com.viettel.ktts2.common.UEncrypt;
import com.viettel.qll.bo.TblKhBckqcvBO;
import com.viettel.qll.dao.TblKhBckqcvDAO;
import com.viettel.qll.dto.ErrExcelDTO;
import com.viettel.qll.dto.ImportErrDTO;
import com.viettel.qll.dto.TblKhBckqcvDTO;
import com.viettel.qll.dto.TblKhQlvcDTO;
import com.viettel.qll.dto.VpsUserToken;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;
import com.viettel.utils.ValidateUtils;

import net.sf.jasperreports.crosstabs.fill.JRPercentageCalculatorFactory.FloatPercentageCalculator;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("tblKhBckqcvBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TblKhBckqcvBusinessImpl extends BaseFWBusinessImpl<TblKhBckqcvDAO,TblKhBckqcvDTO, TblKhBckqcvBO> implements TblKhBckqcvBusiness {

	protected final Logger log = Logger.getLogger(TblKhQlvcBusinessImpl.class);
    @Autowired
    private TblKhBckqcvDAO tblKhBckqcvDAO;
    @Value("${folder_upload2}")
   	private String folder2Upload;
    List<ErrExcelDTO> lstErrExcelDto;
    DateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
    public TblKhBckqcvBusinessImpl() {
        tModel = new TblKhBckqcvBO();
        tDAO = tblKhBckqcvDAO;
    }

    @Override
    public TblKhBckqcvDAO gettDAO() {
        return tblKhBckqcvDAO;
    }

	@Override
	public DataListDTO doSearch(TblKhBckqcvDTO obj) throws Exception {
		List<TblKhBckqcvDTO> ls = tblKhBckqcvDAO.doSearch(obj);
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		data.setTotal(obj.getTotalRecord());
		data.setSize(obj.getTotalRecord());
		data.setStart(1);
		return data;
	}
	
	@Override
	public long deleteObj(TblKhBckqcvDTO obj) {
		try {
			tblKhBckqcvDAO.delete(obj.toModel());
			return 1l;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0l;
	}
	@Override
	public long deleteListObj(TblKhBckqcvDTO obj) {
		List<TblKhBckqcvDTO> lst = tblKhBckqcvDAO.doSearch(obj);
		List<Long> lstId = new ArrayList<>();
		int size = lst.size();
		int count = 0;
		int groupBatch = 0;
		try {
			for (TblKhBckqcvDTO obj1 : lst) {
				count++;
				groupBatch++;
				lstId.add(obj1.getKhBckqcvId());
				if (groupBatch == 999) {
					groupBatch=0;
					tblKhBckqcvDAO.deleteList(lstId);
					lstId.clear();
				}
				if (groupBatch < 999 && count == size) {
					
					tblKhBckqcvDAO.deleteList(lstId);

				}
			}
			return 1l;
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return 0l;
	}
	
	@Override
	public long updateKhKd(TblKhBckqcvDTO obj) throws Exception {
		try {
			
			Long ids = tblKhBckqcvDAO.updateObject(obj.toModel());
			return ids;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 1l;
	}
	
	@Override
	public List<TblKhBckqcvDTO> getForAutoCompleteMonth(TblKhBckqcvDTO obj) {
		// TODO Auto-generated method stub
		return tblKhBckqcvDAO.getForAutoCompleteMonth(obj);
	}
	@Override
	public List<TblKhBckqcvDTO> getForAutoCompleteMaDv(TblKhBckqcvDTO obj) {
		// TODO Auto-generated method stub
		return tblKhBckqcvDAO.getForAutoCompleteMaDv(obj);
	}
	// biểu đồ ngày theo đơn vị và khối đơn vị
	
	public TblKhBckqcvDTO getChartDeptDay(TblKhBckqcvDTO obj,HttpServletRequest request) throws Exception{
		TblKhBckqcvDTO objReturn = new TblKhBckqcvDTO();
		List<TblKhBckqcvDTO> list = tblKhBckqcvDAO.getForDeptDay(obj);
		for(Iterator<TblKhBckqcvDTO> interator = list.iterator(); interator.hasNext();){
			TblKhBckqcvDTO day = interator.next();
			objReturn.getListSanLuong().add(day.getSanLuong());
			objReturn.getListDoanhThu().add(day.getDoanhThu());
			objReturn.getListHcqt().add(day.getHcqt());
			objReturn.getListThoiGian().add(day.getThoiGian());
		
		}
		return objReturn;
	}
	
	//biểu đồ tháng theo đơn vị và khối đơn vị
	 public TblKhBckqcvDTO getChartDeptMonth(TblKhBckqcvDTO obj,HttpServletRequest request) throws Exception{
		 TblKhBckqcvDTO objReturn = new TblKhBckqcvDTO();
		 List<TblKhBckqcvDTO> list = tblKhBckqcvDAO.getForDeptMonth(obj);
		 
		 for(Iterator<TblKhBckqcvDTO> interator = list.iterator(); interator.hasNext();){
				TblKhBckqcvDTO month = interator.next();
				float sl = month.getSanLuong();
				float slKh = month.getSanLuongKh();
				float roundSl = Math.round((sl/slKh)*10000);
				float tileSl = roundSl/100;
				
				float dt = month.getDoanhThu();
				float dtKh = month.getDoanhThuKh();
				float roundDt = Math.round((dt/dtKh)*10000);
				float tileDt = roundDt/100;
				
				float hcqt = month.getHcqt();
				float hcqtKh = month.getHcqtKh();
				float roundHcqt = Math.round((hcqt/hcqtKh)*10000);
				float tileHcqt = roundHcqt/100;
				
				objReturn.getListSanLuong().add(month.getSanLuong());
				objReturn.getListDoanhThu().add(month.getDoanhThu());
				objReturn.getListHcqt().add(month.getHcqt());
				objReturn.getListSanLuongKh().add(month.getSanLuongKh());
				objReturn.getListDoanhThuKh().add(month.getDoanhThuKh());
				objReturn.getListHcqtKh().add(month.getHcqtKh());
				objReturn.getListTileSl().add(tileSl);
				objReturn.getListTileDt().add(tileDt);
				objReturn.getListTileHcqt().add(tileHcqt);
				objReturn.getListThang().add(month.getThang());
			}

		 return objReturn;
	 }
	 
	 // biểu đồ các đơn vị theo tháng
	 public TblKhBckqcvDTO getChartMonth(TblKhBckqcvDTO obj,HttpServletRequest request) throws Exception{
		 TblKhBckqcvDTO objReturn = new TblKhBckqcvDTO();
		 List<TblKhBckqcvDTO> list = tblKhBckqcvDAO.getForMonth(obj);
		 
		 for(Iterator<TblKhBckqcvDTO> interator = list.iterator(); interator.hasNext();){
				TblKhBckqcvDTO dept = interator.next();
				float sl = dept.getSanLuong();
				float slKh = dept.getSanLuongKh();
				float roundSl = Math.round((sl/slKh)*10000);
				float tileSl = roundSl/100;
				
				float dt = dept.getDoanhThu();
				float dtKh = dept.getDoanhThuKh();
				float roundDt = Math.round((dt/dtKh)*10000);
				float tileDt = roundDt/100;
				
				float hcqt = dept.getHcqt();
				float hcqtKh = dept.getHcqtKh();
				float roundHcqt = Math.round((hcqt/hcqtKh)*10000);
				float tileHcqt = roundHcqt/100;
				objReturn.getListSanLuong().add(dept.getSanLuong());
				objReturn.getListDoanhThu().add(dept.getDoanhThu());
				objReturn.getListHcqt().add(dept.getHcqt());
				objReturn.getListSanLuongKh().add(dept.getSanLuongKh());
				objReturn.getListDoanhThuKh().add(dept.getDoanhThuKh());
				objReturn.getListHcqtKh().add(dept	.getHcqtKh());
				objReturn.getListTileSl().add(tileSl);
				objReturn.getListTileDt().add(tileDt);
				objReturn.getListTileHcqt().add(tileHcqt);
				objReturn.getListDonVi().add(dept.getMaDonVi());
				
			}
		
		 return objReturn;
	 }
	public static boolean isRowEmpty(Row row) {
		for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
			Cell cell = row.getCell(c);
			if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK) {
				return false;
			}
		}
		return true;
	}
	public String convertValue(String value) {
		String val = value.replaceAll(",", "");
		return val;
	}
	public boolean checkDataFromFileExel(String data, int columnIndex, ErrExcelDTO orderErrorFormat){
		orderErrorFormat = new ErrExcelDTO();
		switch (columnIndex){
		case 1:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isDate(data, "dd/MM/yyyy")) {
					if(ValidateUtils.isDate(data, "MM/dd/yyyy")){
						  DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH).toFormat();
					}else{
					orderErrorFormat.setDetailError("Thời gian không hợp lệ(ex:13/06/2018)");
					lstErrExcelDto.add(orderErrorFormat);
					return false;}
				}
			}
			break;
		case 3:
			if (StringUtils.isEmpty(data)) {
				orderErrorFormat.setDetailError("Mã đơn vị không được để trống");
				lstErrExcelDto.add(orderErrorFormat);
				return false;
			}
			break;	
		case 4:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isLong(data)) {
					if (!ValidateUtils.isLong(convertValue(data))) {
						orderErrorFormat.setDetailError("Sản lượng không đúng định dạng");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				}
			}
			break;	
			
		case 5:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isLong(data)) {
					if (!ValidateUtils.isLong(convertValue(data))) {
						orderErrorFormat.setDetailError("Doanh thu không đúng định dạng");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				}
			}
			break;	
		
		case 6:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isLong(data)) {
					if (!ValidateUtils.isLong(convertValue(data))) {
						orderErrorFormat.setDetailError("Hoàn công quyết toán không đúng định dạng");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				}
			}
			break;
			
		case 7:
			if (StringUtils.isEmpty(data)) {
				orderErrorFormat.setDetailError("Loại không được để trống");
				lstErrExcelDto.add(orderErrorFormat);
				return false;
			}
			break;	
		}
		
		
		return true;
	}
	public String importFile(String fileInput, HttpServletRequest request) throws Exception{
		
//		TblKhBckqcvDTO objSearch = new TblKhBckqcvDTO();
//		List<TblKhBckqcvDTO> listObj = tblKhBckqcvDAO.doSearch(objSearch);
		List<TblKhBckqcvDTO> workLst = Lists.newArrayList();
//		Set<TblKhQlvcDTO> workLst = new LinkedHashSet<>();
		List<ImportErrDTO> workFault = Lists.newArrayList();
		
		VpsUserToken token = (VpsUserToken) request.getSession().getAttribute("vpsUserToken");
		
		try{
			String Ten = "";
			File f = new File(fileInput);

			XSSFWorkbook workbook = new XSSFWorkbook(f);
			XSSFSheet sheet = workbook.getSheetAt(0);

			DataFormatter dataFormatter = new DataFormatter();
			int count = 0;
			for(Row row : sheet){
				count++;
				ErrExcelDTO orderErrorFormat = new ErrExcelDTO();
				if(count>=2  && !isRowEmpty(row)){
					
					boolean checkSanLuong = true;
					boolean checkDoanhThu = true;
					boolean checkHcqt = true;
					boolean checkThoiGian = true;
					boolean checkMaDonVi = true;
					boolean checkLoai = true;
					
				
					String thoiGian = "";
					String maDonVi ="";
					String tenDonVi ="";
					String sanLuong ="";
					String doanhThu ="";
					String hcqt ="";
					String loai ="";
					String khoi ="";
			
					
					for( Cell cell: row){
						if (cell.getColumnIndex() == 1) {
							thoiGian = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 2) {
							maDonVi = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 3) {
							tenDonVi = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 4) {
							sanLuong = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 5) {
							doanhThu = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 6) {
							hcqt = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 7) {
							loai = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 8) {
							khoi = dataFormatter.formatCellValue(cell);
						} 
					}
					
					checkThoiGian =checkDataFromFileExel(thoiGian.trim(), 1, orderErrorFormat);
					checkMaDonVi = checkDataFromFileExel(maDonVi.trim(), 2 ,orderErrorFormat);
					checkSanLuong =checkDataFromFileExel(sanLuong.trim(), 4, orderErrorFormat);
					checkDoanhThu =checkDataFromFileExel(doanhThu.trim(), 5, orderErrorFormat);
					checkHcqt =	checkDataFromFileExel(hcqt.trim(), 6, orderErrorFormat);
					checkLoai = checkDataFromFileExel(loai.trim(), 7, orderErrorFormat);
					
					if(checkThoiGian&&checkSanLuong&&checkDoanhThu&&checkHcqt&&checkMaDonVi&&checkLoai){
						TblKhBckqcvDTO newObj = new TblKhBckqcvDTO();
						
						if(StringUtils.isNotEmpty(thoiGian)){
							
							String[] array=thoiGian.split("/");
							newObj.setNgay(array[0]);
							newObj.setThang(array[1]);
							newObj.setNam(array[2]);
							newObj.setThoiGian(dt.parse(thoiGian));
						}
					
						newObj.setMaDonVi(maDonVi);
						newObj.setTenDonVi(tenDonVi);
						
						if (!StringUtils.isEmpty(sanLuong)) {
							sanLuong = convertValue(sanLuong);
							newObj.setSanLuong(Long.parseLong(sanLuong));
						} else {
							newObj.setSanLuong((long) 0);
						}
						
						if (!StringUtils.isEmpty(doanhThu)) {
							doanhThu = convertValue(doanhThu);
							newObj.setDoanhThu(Long.parseLong(doanhThu));
						} else {
							newObj.setDoanhThu((long) 0);
						}
						
						if (!StringUtils.isEmpty(hcqt)) {
							hcqt = convertValue(hcqt);
							newObj.setHcqt(Long.parseLong(hcqt));
						} else {
							newObj.setHcqt((long) 0);
						}
						
						newObj.setLoai(loai);
						newObj.setKhoi(khoi);
						newObj.setThuoc("1");
						workLst.add(newObj);
					}else{
						ImportErrDTO errObj = new ImportErrDTO();
						errObj.setColumn1(thoiGian);
						errObj.setColumn2(maDonVi);
						errObj.setColumn3(tenDonVi);
						errObj.setColumn4(sanLuong);
						errObj.setColumn5(doanhThu);
						errObj.setColumn6(hcqt);
						errObj.setColumn7(loai);
						errObj.setColumn8(khoi);
						
						errObj.setLstErrorOrder(lstErrExcelDto);
						workFault.add(errObj);
					}
				}
			}
			workbook.close();

			System.out.println("size done: " + workLst.size());
			System.out.println("size fault: " + workFault.size());
			if (workLst.size() < 0 && workFault.size() < 0) {
				throw new IllegalArgumentException("File import không có dữ liệu");
			} else {
				if (workLst.size() > 0) {
					TblKhBckqcvDTO sumObj1 = new TblKhBckqcvDTO();
					TblKhBckqcvDTO sumObj2 = new TblKhBckqcvDTO();
					TblKhBckqcvDTO sumObj3 = new TblKhBckqcvDTO();
					TblKhBckqcvDTO sumObj4 = new TblKhBckqcvDTO();
					TblKhBckqcvDTO sumObj5 = new TblKhBckqcvDTO();
					TblKhBckqcvDTO sumObj11 = new TblKhBckqcvDTO();
					TblKhBckqcvDTO sumObj12 = new TblKhBckqcvDTO();
					TblKhBckqcvDTO sumObj13 = new TblKhBckqcvDTO();
					TblKhBckqcvDTO sumObj14 = new TblKhBckqcvDTO();
					TblKhBckqcvDTO sumObj15 = new TblKhBckqcvDTO();
					//trong nuoc
					Long sumSLKhTN = (long) 0;
					Long sumSLTtTN = (long) 0;
					Long sumDTKhTN = (long) 0;
					Long sumDTTtTN = (long) 0;
					Long sumHcqtKhTN = (long) 0;
					Long sumHcqtTtTN = (long) 0;
					//nuoc ngoai
					Long sumSLKhNN = (long) 0;
					Long sumSLTtNN = (long) 0;
					Long sumDTKhNN = (long) 0;
					Long sumDTTtNN = (long) 0;
					Long sumHcqtKhNN = (long) 0;
					Long sumHcqtTtNN = (long) 0;
					//vhkt
					Long sumSLKhKT = (long) 0;
					Long sumSLTtKT = (long) 0;
					Long sumDTKhKT = (long) 0;
					Long sumDTTtKT = (long) 0;
					Long sumHcqtKhKT = (long) 0;
					Long sumHcqtTtKT = (long) 0;
					//xay lap
					Long sumSLKhXL = (long) 0;
					Long sumSLTtXL = (long) 0;
					Long sumDTKhXL = (long) 0;
					Long sumDTTtXL = (long) 0;
					Long sumHcqtKhXL = (long) 0;
					Long sumHcqtTtXL = (long) 0;
					// cnkv+hni
					Long sumSLKhKV = (long) 0;
					Long sumSLTtKV = (long) 0;
					Long sumDTKhKV = (long) 0;
					Long sumDTTtKV = (long) 0;
					Long sumHcqtKhKV = (long) 0;
					Long sumHcqtTtKV = (long) 0;
				
					for(TblKhBckqcvDTO items : workLst){
						if("KH".equals(items.getLoai())){
							if("HTTN".equals(items.getKhoi())||"HTTN/5CNKV+HNI".equals(items.getKhoi())){
								sumObj1.setMaDonVi("HTTN");
								sumObj1.setTenDonVi("Hạ tầng trong nước");
								if(items.getSanLuong()!=0){
									sumSLKhTN = sumSLKhTN + items.getSanLuong();
									sumObj1.setSanLuong(sumSLKhTN);
								}
								if(items.getDoanhThu()!=0){
									sumDTKhTN = sumDTKhTN + items.getDoanhThu();
									sumObj1.setDoanhThu(sumDTKhTN);
								}
								if(items.getHcqt()!=0){
									sumHcqtKhTN = sumHcqtKhTN + items.getHcqt();
									sumObj1.setHcqt(sumHcqtKhTN);
								}
								sumObj1.setLoai(items.getLoai());
								sumObj1.setThoiGian(items.getThoiGian());
								sumObj1.setNgay(items.getNgay());
								sumObj1.setThang(items.getThang());
								sumObj1.setNam(items.getNam());
								sumObj1.setKhoi("HTTN");
								sumObj1.setThuoc("2");
							}
							if("HTNN".equals(items.getKhoi())){
								sumObj2.setMaDonVi("HTNN");
								sumObj2.setTenDonVi("Hạ Tầng nước ngoài");
								if(items.getSanLuong()!=0){
									sumSLKhNN = sumSLKhNN + items.getSanLuong();
									sumObj2.setSanLuong(sumSLKhNN);
								}
								if(items.getDoanhThu()!=0){
									sumDTKhNN = sumDTKhNN + items.getDoanhThu();
									sumObj2.setDoanhThu(sumDTKhNN);
								}
								if(items.getHcqt()!=0){
									sumHcqtKhNN = sumHcqtKhNN + items.getHcqt();
									sumObj2.setHcqt(sumHcqtKhNN);
								}
								sumObj2.setLoai(items.getLoai());
								sumObj2.setThoiGian(items.getThoiGian());
								sumObj2.setNgay(items.getNgay());
								sumObj2.setThang(items.getThang());
								sumObj2.setNam(items.getNam());
								sumObj2.setKhoi("HTNN");
								sumObj2.setThuoc("2");
							}
							if("VHKT".equals(items.getKhoi())){
								sumObj3.setMaDonVi("VHKT");
								sumObj3.setTenDonVi("VHKT");
								if(items.getSanLuong()!=0){
									sumSLKhKT = sumSLKhKT + items.getSanLuong();
									sumObj3.setSanLuong(sumSLKhKT);
								}
								if(items.getDoanhThu()!=0){
									sumDTKhKT = sumDTKhKT + items.getDoanhThu();
									sumObj3.setDoanhThu(sumDTKhKT);
								}
								if(items.getHcqt()!=0){
									sumHcqtKhKT = sumHcqtKhKT + items.getHcqt();
									sumObj3.setHcqt(sumHcqtKhKT);
								}
								sumObj3.setLoai(items.getLoai());
								sumObj3.setThoiGian(items.getThoiGian());
								sumObj3.setNgay(items.getNgay());
								sumObj3.setThang(items.getThang());
								sumObj3.setNam(items.getNam());
								sumObj3.setKhoi("VHKT");
								sumObj3.setThuoc("2");
							}
							if("HTTN/5CNKV+HNI".equals(items.getKhoi())||"HTTN".equals(items.getKhoi())||"HTNN".equals(items.getKhoi())){
								sumObj4.setMaDonVi("XL");
								sumObj4.setTenDonVi("Xây lắp");
								if(items.getSanLuong()!=0){
									sumSLKhXL = sumSLKhXL + items.getSanLuong();
									sumObj4.setSanLuong(sumSLKhXL);
								}
								if(items.getDoanhThu()!=0){
									sumDTKhXL = sumDTKhXL + items.getDoanhThu();
									sumObj4.setDoanhThu(sumDTKhXL);
								}
								if(items.getHcqt()!=0){
									sumHcqtKhXL = sumHcqtKhXL + items.getHcqt();
									sumObj4.setHcqt(sumHcqtKhXL);
								}
								sumObj4.setLoai(items.getLoai());
								sumObj4.setThoiGian(items.getThoiGian());
								sumObj4.setNgay(items.getNgay());
								sumObj4.setThang(items.getThang());
								sumObj4.setNam(items.getNam());
								sumObj4.setKhoi("XL");
								sumObj4.setThuoc("2");
							}
							if("HTTN/5CNKV+HNI".equals(items.getKhoi())){
								sumObj5.setMaDonVi("CNKV-HNI");
								sumObj5.setTenDonVi("CNKV-HNI");
								if(items.getSanLuong()!=0){
									sumSLKhKV = sumSLKhKV + items.getSanLuong();
									sumObj5.setSanLuong(sumSLKhKV);
								}
								if(items.getDoanhThu()!=0){
									sumDTKhKV = sumDTKhKV + items.getDoanhThu();
									sumObj5.setDoanhThu(sumDTKhKV);
								}
								if(items.getHcqt()!=0){
									sumHcqtKhKV = sumHcqtKhKV + items.getHcqt();
									sumObj5.setHcqt(sumHcqtKhKV);
								}
								sumObj5.setLoai(items.getLoai());
								sumObj5.setThoiGian(items.getThoiGian());
								sumObj5.setNgay(items.getNgay());
								sumObj5.setThang(items.getThang());
								sumObj5.setNam(items.getNam());
								sumObj5.setKhoi("5CNKV+HNI");
								sumObj5.setThuoc("2");
							}
						}
						
						if("TT".equals(items.getLoai())){
							if("HTTN".equals(items.getKhoi())||"HTTN/5CNKV+HNI".equals(items.getKhoi())){
								sumObj11.setMaDonVi("HTTN");
								sumObj11.setTenDonVi("Hạ tầng trong nước");
								if(items.getSanLuong()!=0){
									sumSLTtTN = sumSLTtTN + items.getSanLuong();
									sumObj11.setSanLuong(sumSLTtTN);
								}
								if(items.getDoanhThu()!=0){
									sumDTTtTN = sumDTTtTN + items.getDoanhThu();
									sumObj11.setDoanhThu(sumDTTtTN);
								}
								if(items.getHcqt()!=0){
									sumHcqtTtTN = sumHcqtTtTN + items.getHcqt();
									sumObj11.setHcqt(sumHcqtTtTN);
								}
								sumObj11.setLoai(items.getLoai());
								sumObj11.setThoiGian(items.getThoiGian());
								sumObj11.setNgay(items.getNgay());
								sumObj11.setThang(items.getThang());
								sumObj11.setNam(items.getNam());
								sumObj11.setKhoi("HTTN");
								sumObj11.setThuoc("2");
							}
							if("HTNN".equals(items.getKhoi())){
								sumObj12.setMaDonVi("HTNN");
								sumObj12.setTenDonVi("Hạ Tầng nước ngoài");
								if(items.getSanLuong()!=0){
									sumSLTtNN = sumSLTtNN + items.getSanLuong();
									sumObj12.setSanLuong(sumSLTtNN);
								}
								if(items.getDoanhThu()!=0){
									sumDTTtNN = sumDTTtNN + items.getDoanhThu();
									sumObj12.setDoanhThu(sumDTTtNN);
								}
								if(items.getHcqt()!=0){
									sumHcqtTtNN = sumHcqtTtNN + items.getHcqt();
									sumObj12.setHcqt(sumHcqtTtNN);
								}
								sumObj12.setLoai(items.getLoai());
								sumObj12.setThoiGian(items.getThoiGian());
								sumObj12.setNgay(items.getNgay());
								sumObj12.setThang(items.getThang());
								sumObj12.setNam(items.getNam());
								sumObj12.setKhoi(items.getKhoi());
								sumObj12.setThuoc("2");
							}
							if("VHKT".equals(items.getKhoi())){
								sumObj13.setMaDonVi("VHKT");
								sumObj13.setTenDonVi("VHKT");
								if(items.getSanLuong()!=0){
									sumSLTtKT = sumSLTtKT + items.getSanLuong();
									sumObj13.setSanLuong(sumSLTtKT);
								}
								if(items.getDoanhThu()!=0){
									sumDTTtKT = sumDTTtKT + items.getDoanhThu();
									sumObj13.setDoanhThu(sumDTTtKT);
								}
								if(items.getHcqt()!=0){
									sumHcqtTtKT = sumHcqtTtKT + items.getHcqt();
									sumObj13.setHcqt(sumHcqtTtKT);
								}
								sumObj13.setLoai(items.getLoai());
								sumObj13.setThoiGian(items.getThoiGian());
								sumObj13.setNgay(items.getNgay());
								sumObj13.setThang(items.getThang());
								sumObj13.setNam(items.getNam());
								sumObj13.setKhoi(items.getKhoi());
								sumObj13.setThuoc("2");
							}
							if("HTTN/5CNKV+HNI".equals(items.getKhoi())||"HTTN".equals(items.getKhoi())||"HTNN".equals(items.getKhoi())){
								sumObj14.setMaDonVi("XL");
								sumObj14.setTenDonVi("Xây lắp");
								if(items.getSanLuong()!=0){
									sumSLTtXL = sumSLTtXL + items.getSanLuong();
									sumObj14.setSanLuong(sumSLTtXL);
								}
								if(items.getDoanhThu()!=0){
									sumDTTtXL = sumDTTtXL + items.getDoanhThu();
									sumObj14.setDoanhThu(sumDTTtXL);
								}
								if(items.getHcqt()!=0){
									sumHcqtTtXL = sumHcqtTtXL + items.getHcqt();
									sumObj14.setHcqt(sumHcqtTtXL);
								}
								sumObj14.setLoai(items.getLoai());
								sumObj14.setThoiGian(items.getThoiGian());
								sumObj14.setNgay(items.getNgay());
								sumObj14.setThang(items.getThang());
								sumObj14.setNam(items.getNam());
								sumObj14.setKhoi("XL");
								sumObj14.setThuoc("2");
							}
							if("HTTN/5CNKV+HNI".equals(items.getKhoi())){
								sumObj15.setMaDonVi("CNKV-HNI");
								sumObj15.setTenDonVi("CNKV-HNI");
								if(items.getSanLuong()!=0){
									sumSLTtKV = sumSLTtKV + items.getSanLuong();
									sumObj15.setSanLuong(sumSLTtKV);
								}
								if(items.getDoanhThu()!=0){
									sumDTTtKV = sumDTTtKV + items.getDoanhThu();
									sumObj15.setDoanhThu(sumDTTtKV);
								}
								if(items.getHcqt()!=0){
									sumHcqtTtKV = sumHcqtTtKV + items.getHcqt();
									sumObj15.setHcqt(sumHcqtTtKV);
								}
								sumObj15.setLoai(items.getLoai());
								sumObj15.setThoiGian(items.getThoiGian());
								sumObj15.setNgay(items.getNgay());
								sumObj15.setThang(items.getThang());
								sumObj15.setNam(items.getNam());
								sumObj15.setKhoi("5CNKV+HNI");
								sumObj15.setThuoc("2");
							}
						}
						
					}
					workLst.add(sumObj1);
					workLst.add(sumObj2);
					workLst.add(sumObj3);
					workLst.add(sumObj4);
					workLst.add(sumObj5);
					workLst.add(sumObj11);
					workLst.add(sumObj12);
					workLst.add(sumObj13);
					workLst.add(sumObj14);
					workLst.add(sumObj15);
//					List<TblKhQlvcDTO> workLstFake = new ArrayList<TblKhQlvcDTO>(workLst);
					saveList(workLst);
				}

				if (workFault.size() > 0) {
					// exportExcelError(workFault);
					// throw new IllegalArgumentException("Có lỗi trong file
					// import");
					return exportExcelError(workFault);
				}
				// return (long) 1;
			}
			 
			
		}catch (NullPointerException pointerException) {
			// pointerException.printStackTrace();
			log.error(pointerException.getMessage(), pointerException);
		} catch (Exception e) {
			// e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return "";
		
	}
	
	public String exportExcelError(List<ImportErrDTO> obj) throws Exception{
		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try{
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream file = new BufferedInputStream(new FileInputStream(filePath + "KhBcKqCvErr.xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);
			DataFormat format = workbook.createDataFormat();
			// set style
			XSSFCellStyle style = workbook.createCellStyle();
			XSSFCellStyle styleNumber = workbook.createCellStyle();
			XSSFFont hSSFFont = workbook.createFont();
			hSSFFont.setFontName("Times New Roman");
			style.setFont(hSSFFont);
			style.setBorderBottom(BorderStyle.THIN);
			style.setBorderTop(BorderStyle.THIN);
			style.setBorderRight(BorderStyle.THIN);
			style.setBorderLeft(BorderStyle.THIN);
			style.setWrapText(true);
			styleNumber.setDataFormat(format.getFormat("#,##0"));
			styleNumber.setBorderBottom(BorderStyle.THIN);
			styleNumber.setBorderTop(BorderStyle.THIN);
			styleNumber.setBorderRight(BorderStyle.THIN);
			styleNumber.setBorderLeft(BorderStyle.THIN);
			styleNumber.setWrapText(true);
			styleNumber.setAlignment(CellStyle.ALIGN_RIGHT);
			int rownum = 2;
			for(int i = 0; i < obj.size(); i++){
				StringBuilder err = new StringBuilder();
				XSSFRow row = sheet.createRow(rownum);
				XSSFCell cell0 = row.createCell(0);
				cell0.setCellValue(i + 1);
				cell0.setCellStyle(style);
				rownum++;
				
				XSSFCell cell1 = row.createCell(1);
				cell1.setCellValue(obj.get(i).getColumn1());
				
				XSSFCell cell2 = row.createCell(2);
				cell2.setCellValue(obj.get(i).getColumn2());

				XSSFCell cell3 = row.createCell(3);
				cell3.setCellValue(obj.get(i).getColumn3());

				XSSFCell cell4 = row.createCell(4);
				cell4.setCellValue(obj.get(i).getColumn4());

				XSSFCell cell5 = row.createCell(5);
				cell5.setCellValue(obj.get(i).getColumn5());

				XSSFCell cell6 = row.createCell(6);
				cell6.setCellValue(obj.get(i).getColumn6());

				XSSFCell cell7 = row.createCell(7);
				cell7.setCellValue(obj.get(i).getColumn7());
				
				XSSFCell cell8 = row.createCell(8);
				cell8.setCellValue(obj.get(i).getColumn8());
				
			

				for (int j = 0; j < obj.get(i).getLstErrorOrder().size(); j++) {
					err.append(obj.get(i).getLstErrorOrder().get(j).getDetailError() + "-");
					// System.out.println("err" + err);
				}
				System.out.println("err " + i + ":" + err);

				XSSFCell cell9 = row.createCell(9);
				cell9.setCellValue(err.toString());
				
				cell0.setCellStyle(style);
				cell1.setCellStyle(style);
				cell2.setCellStyle(style);
				cell3.setCellStyle(style);
				cell4.setCellStyle(style);
				cell5.setCellStyle(style);
				cell6.setCellStyle(style);
				cell7.setCellStyle(style);
				cell8.setCellStyle(style);
				cell9.setCellStyle(style);
				
			}
			file.close();

			File out = new File(folder2Upload + File.separatorChar + "KhBcKqCvErr" + startExportTime + ".xlsx");

			FileOutputStream outFile = new FileOutputStream(out);
			System.out.println(out.getCanonicalPath());
			workbook.write(outFile);
			workbook.close();
			outFile.close();
		}catch (FileNotFoundException e) {
			log.error(e.getMessage(), e);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
		return UEncrypt.encryptFileUploadPath("KhBcKqCvErr" + startExportTime + ".xlsx");
	}
	
}
