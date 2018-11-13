package com.viettel.qll.business;
 
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.google.common.collect.Lists;
import com.viettel.ktts2.common.UEncrypt;
import com.viettel.qll.bo.TblKhQlvcBO;
import com.viettel.qll.dao.TblDanhMucDAO;
import com.viettel.qll.dao.TblKhQlvcDAO;
import com.viettel.qll.dto.ErrExcelDTO;
import com.viettel.qll.dto.ImportErrDTO;
import com.viettel.qll.dto.KqHdTkDTO;
import com.viettel.qll.dto.TblDanhMucDTO;
import com.viettel.qll.dto.TblKhQlvcDTO;
import com.viettel.qll.dto.VpsUserToken;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;
import com.viettel.utils.ValidateUtils;

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


@Service("tblKhQlvcBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TblKhQlvcBusinessImpl extends BaseFWBusinessImpl<TblKhQlvcDAO,TblKhQlvcDTO, TblKhQlvcBO> implements TblKhQlvcBusiness {

	
	protected final Logger log = Logger.getLogger(TblKhQlvcBusinessImpl.class);
    @Autowired
    private TblKhQlvcDAO tblKhQlvcDAO;
     
   
    
    @Value("${folder_upload2}")
	private String folder2Upload;
    List<ErrExcelDTO> lstErrExcelDto;
    public TblKhQlvcBusinessImpl() {
        tModel = new TblKhQlvcBO();
        tDAO = tblKhQlvcDAO;
    }

    @Override
    public TblKhQlvcDAO gettDAO() {
        return tblKhQlvcDAO;
    }
	
	


	@Override
	public DataListDTO doSearch(TblKhQlvcDTO obj) throws Exception {
		List<TblKhQlvcDTO> ls = tblKhQlvcDAO.doSearch(obj);
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		data.setTotal(obj.getTotalRecord());
		data.setSize(obj.getTotalRecord());
		data.setStart(1);
		return data;
	}
	@Override
	public long deleteObj(TblKhQlvcDTO obj) {
		try {
			tblKhQlvcDAO.delete(obj.toModel());
			return 1l;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0l;
	}
	@Override
	public long deleteListObj(TblKhQlvcDTO obj) {
		List<TblKhQlvcDTO> lst = tblKhQlvcDAO.doSearch(obj);
		List<Long> lstId = new ArrayList<>();
		int size = lst.size();
		int count = 0;
		int groupBatch = 0;
		try {
			for (TblKhQlvcDTO obj1 : lst) {
				count++;
				groupBatch++;
				lstId.add(obj1.getKhQlvcId());
				if (groupBatch == 999) {
					groupBatch=0;
					tblKhQlvcDAO.deleteList(lstId);
					lstId.clear();
				}
				if (groupBatch < 999 && count == size) {
					
					tblKhQlvcDAO.deleteList(lstId);

				}
			}
			return 1l;
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return 0l;
	}
	
	@Override
	public long updateKhNv(TblKhQlvcDTO obj) throws Exception {
		try {
			
			Long ids = tblKhQlvcDAO.updateObject(obj.toModel());
			return ids;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 1l;
	}
	
	@Override
	public List<TblKhQlvcDTO> getForAutoCompleteMonth(TblKhQlvcDTO obj) {
		// TODO Auto-generated method stub
		return tblKhQlvcDAO.getForAutoCompleteMonth(obj);
	}
	
	@Override
	public List<TblKhQlvcDTO> getForAutoCompleteYear(TblKhQlvcDTO obj) {
		// TODO Auto-generated method stub
		return tblKhQlvcDAO.getForAutoCompleteYear(obj);
	}
	@Override
	public List<TblKhQlvcDTO> getForAutoCompleteMaNv(TblKhQlvcDTO obj) {
		// TODO Auto-generated method stub
		return tblKhQlvcDAO.getForAutoCompleteMaNv(obj);
	}
	
	@Override
	public List<TblKhQlvcDTO> getForAutoCompleteMaDv(TblKhQlvcDTO obj) {
		// TODO Auto-generated method stub
		return tblKhQlvcDAO.getForAutoCompleteMaDv(obj);
	}
	@SuppressWarnings("deprecation")
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
		case 3:
			if (StringUtils.isEmpty(data)) {
				orderErrorFormat.setDetailError("Mã đơn vị không được để trống");
				lstErrExcelDto.add(orderErrorFormat);
				return false;
			}
			break;
		case 5:
			if (StringUtils.isEmpty(data)) {
				orderErrorFormat.setDetailError("Mã nhân viên giao việc không được để trống");
				lstErrExcelDto.add(orderErrorFormat);
				return false;
			}
			break;	
			
		case 7:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isLong(data)) {
					if (!ValidateUtils.isLong(convertValue(data))) {
						orderErrorFormat.setDetailError("Tổng số nhiệm vụ không đúng định dạng");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				}
			}
			break;	
		
	
		case 8:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isLong(data)) {
					if (!ValidateUtils.isLong(convertValue(data))) {
						orderErrorFormat.setDetailError("Tổng số nhiệm vụ chậm không đúng định dạng");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				}
			}
			break;	
		}
		
		
		return true;
	}
	 
	
	@SuppressWarnings("unused")
	public String importFile(String fileInput, HttpServletRequest request) throws Exception{
		
			TblKhQlvcDTO objSearch = new TblKhQlvcDTO();
			List<TblKhQlvcDTO> listObj = tblKhQlvcDAO.doSearch(objSearch);
//			List<TblKhQlvcDTO> workLst = Lists.newArrayList();

			Set<TblKhQlvcDTO> workLst = new LinkedHashSet<>();
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
					lstErrExcelDto = new ArrayList<>();
					
					boolean checkDonVi = true;
					boolean checkNvGiaoViec = true;
					boolean checkTongNv = true;
					boolean checkTongCham = true;
					
					String thang ="";
					String nam ="";
					String maDonVi ="";
					String tenDonVi ="";
					String maNvGiaoViec="";
					String tenNvGiaoViec="";
					String tongNv="";
					String tongCham="";
					
					for( Cell cell: row){
						if (cell.getColumnIndex() == 1) {
							thang = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 2) {
							nam = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 3) {
							maDonVi = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 4) {
							tenDonVi = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 5) {
							maNvGiaoViec = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 6) {
							tenNvGiaoViec = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 7) {
							tongNv = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 8) {
							tongCham = dataFormatter.formatCellValue(cell);
						} 
					}
					
					checkDonVi = checkDataFromFileExel(maDonVi.trim(), 3, orderErrorFormat);
					checkNvGiaoViec = checkDataFromFileExel(maNvGiaoViec.trim(), 5, orderErrorFormat);
					checkTongNv = checkDataFromFileExel(tongNv.trim(), 7, orderErrorFormat);
					
					checkTongCham = checkDataFromFileExel(tongCham.trim(), 8, orderErrorFormat);
					
					if(checkDonVi&&checkNvGiaoViec&&checkTongNv&&checkTongCham){
						TblKhQlvcDTO newObj = new TblKhQlvcDTO();
						
						newObj.setThang(thang);
						newObj.setNam(nam);
						newObj.setMaDonVi(maDonVi);
						newObj.setTenDonVi(tenDonVi);
						newObj.setMaNvGiaoViec(maNvGiaoViec);
						newObj.setTenNvGiaoViec(tenNvGiaoViec);
						if(!StringUtils.isEmpty(tongNv)){
							tongNv = convertValue(tongNv);
							newObj.setTongNv(Long.parseLong(tongNv));
						}else{
							newObj.setTongNv( (long)0);
						}
				
						if(!StringUtils.isEmpty(tongCham)){
							tongCham = convertValue(tongCham);
							newObj.setTongCham(Long.parseLong(tongCham));
						}else{
							newObj.setTongCham((long) 0);
						}
						
//						workLst.add(newObj);
						boolean checkItem= false;
						if(listObj.size()!=0){
							for(int i =0;i<listObj.size();i++){		// check trùng database và file excel
								
								listObj.get(i).setKhQlvcId(null);
								
								if(newObj.equals(listObj.get(i))){
									checkItem= false;
									break;
								}else{
									checkItem = true;
								}	
							}
							
							if(checkItem==true){
								workLst.add(newObj);									
							}
							
						}else{
							workLst.add(newObj);
						}
						
//						
						
					} else {
						
						ImportErrDTO errObj = new ImportErrDTO();
						errObj.setColumn1(thang);
						errObj.setColumn2(nam);
						errObj.setColumn3(maDonVi);
						errObj.setColumn4(tenDonVi);
						errObj.setColumn5(maNvGiaoViec);
						errObj.setColumn6(tenNvGiaoViec);
						errObj.setColumn7(tongNv);
				
						errObj.setColumn8(tongCham);
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
				
					List<TblKhQlvcDTO> workLstFake = new ArrayList<TblKhQlvcDTO>(workLst);
					saveList(workLstFake);
				}

				if (workFault.size() > 0) {
					// exportExcelError(workFault);
					// throw new IllegalArgumentException("Có lỗi trong file
					// import");
					return exportExcelError(workFault);
				}
				// return (long) 1;
			}
			
		} catch (NullPointerException pointerException) {
			// pointerException.printStackTrace();
			log.error(pointerException.getMessage(), pointerException);
		} catch (Exception e) {
			// e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return "";
		
	}
	
	@SuppressWarnings("deprecation")
	public String exportExcelError(List<ImportErrDTO> obj) throws Exception{
		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try{
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream file = new BufferedInputStream(new FileInputStream(filePath + "KhTkNhiem_vuErr.xlsx"));
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

				XSSFCell cell9= row.createCell(9);
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
				
				
			}
			
			file.close();

			File out = new File(folder2Upload + File.separatorChar + "KhTkNhiem_vuErr" + startExportTime + ".xlsx");

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
		return UEncrypt.encryptFileUploadPath("importTkPkhErr" + startExportTime + ".xlsx");
	}
	//biểu đồ đơn vị theo thang,năm 
	public TblKhQlvcDTO getChartDept(TblKhQlvcDTO obj,HttpServletRequest request) throws Exception{
		
		TblKhQlvcDTO objReturn = new TblKhQlvcDTO();
		List<TblKhQlvcDTO> list = tblKhQlvcDAO.getChartDept(obj);
		
		
		
		for(Iterator<TblKhQlvcDTO> interator = list.iterator(); interator.hasNext();){
			TblKhQlvcDTO month = interator.next();
			float tc = month.getTongCham();
			float tnv = month.getTongNv();
			float roundnv = Math.round((tc/tnv)*10000);
			long tht = month.getTongNv() -  month.getTongCham() ;
			float tiLe = roundnv/100;
			
			
			objReturn.getListTongNvHoanThanh().add(tht);
			objReturn.getListTongCham().add(month.getTongCham());
			objReturn.getListTile().add(tiLe);
			objReturn.getListMaDonVi().add(month.getMaDonVi());
		
		}
		return objReturn;
	}
	//biểu đồ năm theo đơn vị 
//	public TblKhQlvcDTO getChartDeptYear(TblKhQlvcDTO obj,HttpServletRequest request) throws Exception{
//		
//		TblKhQlvcDTO objReturn = new TblKhQlvcDTO();
//		List<TblKhQlvcDTO> list = tblKhQlvcDAO.getChartDeptYear(obj);
//		for(Iterator<TblKhQlvcDTO> interator = list.iterator(); interator.hasNext();){
//			TblKhQlvcDTO deptYear = interator.next();
//			objReturn.getListTongNv().add(deptYear.getTongNv());
//			objReturn.getListTongHoanThanh().add(deptYear.getTongHoanThanh());
//			objReturn.getListTongCham().add(deptYear.getTongCham());
//			objReturn.getListNam().add(deptYear.getNam());
//			
//		}
//		return objReturn;
//	}
	//biểu đồ tháng 
		public TblKhQlvcDTO getChartMonth(TblKhQlvcDTO obj,HttpServletRequest request) throws Exception{
			
			TblKhQlvcDTO objReturn = new TblKhQlvcDTO();
			List<TblKhQlvcDTO> list = tblKhQlvcDAO.getChartMonth(obj);
			for(Iterator<TblKhQlvcDTO> interator = list.iterator(); interator.hasNext();){
				TblKhQlvcDTO deptMonth = interator.next();
				float tc = deptMonth.getTongCham();
				float tnv = deptMonth.getTongNv();
				long tht = deptMonth.getTongNv() -  deptMonth.getTongCham() ;
				float roundnv = Math.round((tc/tnv)*10000);
				float tiLe = roundnv/100;
				objReturn.getListTongNvHoanThanh().add(tht);
				objReturn.getListTile().add(tiLe);
				objReturn.getListTongCham().add(deptMonth.getTongCham());
				objReturn.getListThang().add(deptMonth.getThang());
				
			}
			return objReturn;
		}
	//biểu đồ năm theo nhân viên	
//		public TblKhQlvcDTO getChartEmpYear(TblKhQlvcDTO obj,HttpServletRequest request) throws Exception{
//			
//			TblKhQlvcDTO objReturn = new TblKhQlvcDTO();
//			List<TblKhQlvcDTO> list = tblKhQlvcDAO.getChartEmpYear(obj);
//			for(Iterator<TblKhQlvcDTO> interator = list.iterator(); interator.hasNext();){
//				TblKhQlvcDTO empYear = interator.next();
//				objReturn.getListTongNv().add(empYear.getTongNv());
//				objReturn.getListTongHoanThanh().add(empYear.getTongHoanThanh());
//				objReturn.getListTongCham().add(empYear.getTongCham());
//				objReturn.getListNam().add(empYear.getNam());
//				
//			}
//			return objReturn;
//		}
	//biểu đồ tháng theo nhân viên
//	public TblKhQlvcDTO getChartEmpMonth(TblKhQlvcDTO obj,HttpServletRequest request) throws Exception{
//				
//				TblKhQlvcDTO objReturn = new TblKhQlvcDTO();
//				List<TblKhQlvcDTO> list = tblKhQlvcDAO.getChartDept(obj);
//				for(Iterator<TblKhQlvcDTO> interator = list.iterator(); interator.hasNext();){
//					TblKhQlvcDTO empMonth = interator.next();
//					float tc = empMonth.getTongCham();
//					float tnv = empMonth.getTongNv();
//					float roundnv = Math.round((tc/tnv)*10000);
//					float tiLe = roundnv/100;
//					objReturn.getListTongNv().add(empMonth.getTongNv());
//					objReturn.getListTile().add(tiLe);
//					objReturn.getListTongCham().add(empMonth.getTongCham());
//					objReturn.getListThang().add(empMonth.getThang());
//					
//				}
//				return objReturn;
//			}
}
