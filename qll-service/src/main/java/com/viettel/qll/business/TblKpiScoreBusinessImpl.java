package com.viettel.qll.business;
 
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.google.common.collect.Lists;
import com.viettel.ktts2.common.UEncrypt;
import com.viettel.qll.bo.TblKpiScoreBO;
import com.viettel.qll.dao.SysUserDAO;
import com.viettel.qll.dao.TblKpiScoreDAO;
import com.viettel.qll.dto.ErrExcelDTO;
import com.viettel.qll.dto.ImportErrDTO;

import com.viettel.qll.dto.SysGroupDTO;
import com.viettel.qll.dto.SysUserDTO;
import com.viettel.qll.dto.TblKpiScoreDTO;

import com.viettel.qll.dto.VpsUserToken;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;

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
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("tblKpiScoreBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TblKpiScoreBusinessImpl extends BaseFWBusinessImpl<TblKpiScoreDAO,TblKpiScoreDTO, TblKpiScoreBO> implements TblKpiScoreBusiness {

    @Autowired
    private TblKpiScoreDAO tblKpiScoreDAO;
    protected final Logger log = Logger.getLogger(TblTimeWorkBusinessImpl.class);
    @Autowired
    SysGroupBusinessImpl sysGroupBusinessImpl;
    
    @Autowired
    private SysUserDAO sysUserDAO;
    @Value("${folder_upload2}")
  	private String folder2Upload;
    
    List<ErrExcelDTO> lstErrExcelDto;
    public TblKpiScoreBusinessImpl() {
        tModel = new TblKpiScoreBO();
        tDAO = tblKpiScoreDAO;
    }
    
    private final static Date DATE= new Date();
    DateFormat dt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    @Override
    public TblKpiScoreDAO gettDAO() {
        return tblKpiScoreDAO;
    }
//	xóa 1 ban ghi
    @Override
	public long deleteObj(TblKpiScoreDTO obj) {
		try {
			tblKpiScoreDAO.delete(obj.toModel());
			return 1l;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0l;
	}
    //update 1 bản ghi
    @Override
	public long updateObj(TblKpiScoreDTO obj) {
    	Date d = new Date();
		try {
			obj.setModifieddate(d);
			tblKpiScoreDAO.update(obj.toModel());
			return 1l;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0l;
	}
    
    public long insertObj(TblKpiScoreDTO obj) {
    	Date d = new Date();
		try {
			SysGroupDTO group = (SysGroupDTO) sysGroupBusinessImpl.getOneById(obj.getDepartmentid());
			if(Integer.parseInt(group.getGroupLevel()) >3){
				String[] pathId = group.getPath().split("/");
				Long parentGroupId = Long.parseLong(pathId[3]);
				obj.setDepartmentid(parentGroupId);
			}
			obj.setScorebonusConfirm(obj.getScorebonus());
			obj.setScorepenaltyConfirm(obj.getScorepenalty());
			obj.setCreateddate(d);
			obj.setModifieddate(d);
			obj.setUseridModified(null);
			obj.setUsernameModified(null);
			obj.setFullnameModified(null);
			obj.setDepartmentidModified(null);
			obj.setDepartmentnameModified(null);
			tblKpiScoreDAO.saveObject(obj.toModel());
			return 1l;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0l;
	}
    
    public DataListDTO getListAllDepartment(TblKpiScoreDTO obj){
//    	ConnectionDAO con = new ConnectionDAO();
//		System.out.println(con);
    	List<TblKpiScoreDTO> lstDepartment = tblKpiScoreDAO.getListAllDepartment(obj);
    	for(int i=0;i<lstDepartment.size();i++){
    		lstDepartment.get(i).setSumScore(lstDepartment.get(i).getScorebonus()+lstDepartment.get(i).getScorepenalty());
    		lstDepartment.get(i).setSumScoreConfirm(lstDepartment.get(i).getScorebonusConfirm()+lstDepartment.get(i).getScorepenaltyConfirm());
    	}
    	DataListDTO data = new DataListDTO();
		data.setData(lstDepartment);
		data.setTotal(obj.getTotalRecord());
		data.setSize(obj.getTotalRecord());
		data.setStart(1);
		return data;
    	
    	
    }
    
    public List<TblKpiScoreDTO> getInfoDetailByDepartment (TblKpiScoreDTO obj) {
    	
    	List<TblKpiScoreDTO> lstDetailByDepartment = tblKpiScoreDAO.getInfoDetail(obj);
  
    	
    	return lstDetailByDepartment;
    }
    
    public List<TblKpiScoreDTO> getInfoDetailByUseridcreated (TblKpiScoreDTO obj){
    	
    	List<TblKpiScoreDTO> lstDetailByUseridcreated = tblKpiScoreDAO.getInfoDetail(obj);
    	return lstDetailByUseridcreated;
    }
    
    public DataListDTO getInfoDetailByDoSearch (TblKpiScoreDTO obj){
    	System.out.println(obj.getCheckScore());
     	if("1".equals(obj.getCheckScore())){
    		obj.setScorebonus((float) 0);
    	}
    	if("0".equals(obj.getCheckScore())){
    		obj.setScorepenalty((float) 0);
    	}
    	List<TblKpiScoreDTO> lstInfo = tblKpiScoreDAO.getInfoDetail(obj);
    	for(int i=0;i<lstInfo.size();i++){
    		if(lstInfo.get(i).getScorebonus()!=0){
    			lstInfo.get(i).setViewScore(lstInfo.get(i).getScorebonus());
    		}
    		if(lstInfo.get(i).getScorepenalty()!=0){
    			lstInfo.get(i).setViewScore(lstInfo.get(i).getScorepenalty());
    		}
    		if(lstInfo.get(i).getScorebonusConfirm()!=0){
    			lstInfo.get(i).setViewScoreConfirm(lstInfo.get(i).getScorebonusConfirm());
    		}
    		if(lstInfo.get(i).getScorepenaltyConfirm()!=0){
    			lstInfo.get(i).setViewScoreConfirm(lstInfo.get(i).getScorepenaltyConfirm());
    		}
    	}
    	DataListDTO data = new DataListDTO();
		data.setData(lstInfo);
		data.setTotal(obj.getTotalRecord());
		data.setSize(obj.getTotalRecord());
		data.setStart(1);
		return data;
    	
    }
    
    public List<TblKpiScoreDTO> getNotifyByDepartment(TblKpiScoreDTO obj){
    	
    	List<TblKpiScoreDTO> lstNotifyByDepartment = tblKpiScoreDAO.getNotifyByDepartment(obj);
    	return lstNotifyByDepartment;
    }
    
    public List<TblKpiScoreDTO> getAutoDepartmentKPI(TblKpiScoreDTO obj){
    	return tblKpiScoreDAO.getAutoDepartmentKPI(obj);
    }
    public List<TblKpiScoreDTO> getAutoDepartmentCreatedKPI(TblKpiScoreDTO obj){
    	return tblKpiScoreDAO.getAutoDepartmentCreatedKPI(obj);
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
    		case 3:
    			if (StringUtils.isEmpty(data)) {
				orderErrorFormat.setDetailError("Điểm  chốt không được để trống");
				lstErrExcelDto.add(orderErrorFormat);
				return false;
			}
			break;
    		
    		case 9:
    			if (StringUtils.isEmpty(data)) {
				orderErrorFormat.setDetailError("Mã ID  không được để trống");
				lstErrExcelDto.add(orderErrorFormat);
				return false;
			}
			break;
    		default:
    			break;
    	}
    	return true;
    }
    
    public String decidedImportFile(String fileInput , HttpServletRequest request) throws Exception{
    	
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
				count ++;
				ErrExcelDTO orderErrorFormat = new ErrExcelDTO();
				if (count >=3 && !isRowEmpty(row)){
					lstErrExcelDto = new ArrayList<>();
					
					
						
					boolean checkViewScoreConfirm = true;
					boolean checkKPIid = true;
					
					String departmentName="";
					
					String viewScore="";
					String viewScoreConfirm="";
					String reason="";
					String createdDate="";
					String fullnameCreated="";
					String departmentnameCreated="";
					String reasonConfirm="";
					String kpiid ="";
					
					for(Cell cell : row){
						if(cell.getColumnIndex() == 1){
							departmentName =dataFormatter.formatCellValue(cell);
						}else if(cell.getColumnIndex() == 2){
							viewScore = dataFormatter.formatCellValue(cell);
						}else if(cell.getColumnIndex() == 3){
							viewScoreConfirm = dataFormatter.formatCellValue(cell);
						}else if(cell.getColumnIndex() == 4){
							reason = dataFormatter.formatCellValue(cell);
						}else if(cell.getColumnIndex() == 5){
							createdDate = dataFormatter.formatCellValue(cell);
						}else if(cell.getColumnIndex() == 6){
							fullnameCreated = dataFormatter.formatCellValue(cell);
						}else if(cell.getColumnIndex() == 7){
							departmentnameCreated = dataFormatter.formatCellValue(cell);
						}else if(cell.getColumnIndex() == 8){
							reasonConfirm = dataFormatter.formatCellValue(cell);
						}else if(cell.getColumnIndex() == 9){
							kpiid = dataFormatter.formatCellValue(cell);
						}
					}
				
					checkViewScoreConfirm = checkDataFromFileExel(viewScoreConfirm.trim(), 3, orderErrorFormat);
					checkKPIid = checkDataFromFileExel(kpiid.trim(), 9, orderErrorFormat);
					
					if(checkViewScoreConfirm&&checkKPIid){
						TblKpiScoreDTO oldObj = new TblKpiScoreDTO();
						
						oldObj.setKpiid(Long.parseLong(kpiid));
						viewScoreConfirm = convertValue(viewScoreConfirm);
						float score = Float.parseFloat(viewScoreConfirm);
						if(score <=0){
							oldObj.setScorepenaltyConfirm(score);
							oldObj.setScorebonusConfirm((float) 0);
						}
						if(score >= 0){
							oldObj.setScorebonusConfirm(score);
							oldObj.setScorepenaltyConfirm((float) 0);
						}
						
						if(StringUtils.isNotEmpty(reasonConfirm)){
							oldObj.setReasonConfirm(reasonConfirm);
						}else{
							oldObj.setReasonConfirm("Đồng ý với phòng/ban đề xuất");
						}
						oldObj.setModifieddate(DATE);
						oldObj.setUseridModified(token.getAuthorizedData().getUser().getSysUserId());
						oldObj.setUsernameModified(token.getAuthorizedData().getUser().getLoginName());
						oldObj.setFullnameModified(token.getAuthorizedData().getUser().getFullName());
						List<SysUserDTO> result = sysUserDAO.getSysUserByEmployee(oldObj.getUsernameModified());
						SysUserDTO user = result.size() >0 ? result.get(0) : null;
						SysGroupDTO createDept = (SysGroupDTO) sysGroupBusinessImpl.getOneById(user.getDepartmentId());
						if(user != null)
							if(Integer.parseInt(createDept.getGroupLevel())  <= 3){
								oldObj.setDepartmentnameModified(user.getSysGroupName());
							} else{ // neu nguoi tao thuoc don vi co group lever > 3 => lay don vi cha cua group do
								oldObj.setDepartmentnameModified(createDept.getGroupNameLevel3());
								Long parentGroupId = Long.parseLong(createDept.getPath().split("/")[3]);
								oldObj.setDepartmentidModified(parentGroupId);
							}
						
						tblKpiScoreDAO.updateDecided(oldObj);
					}else{
						ImportErrDTO errObj = new ImportErrDTO();
						errObj.setColumn1(departmentName);
						errObj.setColumn2(viewScore);
						errObj.setColumn3(viewScoreConfirm);
						errObj.setColumn4(reason);
						errObj.setColumn5(createdDate);
						errObj.setColumn6(fullnameCreated);
						errObj.setColumn7(departmentnameCreated);
						errObj.setColumn8(reasonConfirm);
						errObj.setColumn9(kpiid);
						errObj.setLstErrorOrder(lstErrExcelDto);
						workFault.add(errObj);
					}
					 
				}
				workbook.close();
				
				if ( workFault.size() < 0) {
					throw new IllegalArgumentException("File import không có dữ liệu");
				} else {
					if (workFault.size() > 0) {
						// exportExcelError(workFault);
						// throw new IllegalArgumentException("Có lỗi trong file
						// import");
						return exportExcelError(workFault);
					}
					// return (long) 1;
				}
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
    
    
    public String exportExcelError(List<ImportErrDTO> obj) throws Exception {
		// TODO Auto-generated method stub
		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream file = new BufferedInputStream(new FileInputStream(filePath + "Chi tiết điểm phối hợp các phòng ban - Error.xlsx"));
			// String filePath =
			// UEncrypt.decryptFileUploadPath(obj.getFilePathError());
			// InputStream file = new BufferedInputStream(new
			// FileInputStream(filePath));
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
			for (int i = 0; i < obj.size(); i++) {
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

				XSSFCell cell9 = row.createCell(9);
				cell9.setCellValue(obj.get(i).getColumn9());


				for (int j = 0; j < obj.get(i).getLstErrorOrder().size(); j++) {
					err.append(obj.get(i).getLstErrorOrder().get(j).getDetailError() + "-");
					// System.out.println("err" + err);
				}
				System.out.println("err " + i + ":" + err);

				XSSFCell cell10 = row.createCell(10);
				cell10.setCellValue(err.toString());
				
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

			File out = new File(folder2Upload + File.separatorChar + "Chi tiết điểm phối hợp các phòng ban - Error" + startExportTime + ".xlsx");

			FileOutputStream outFile = new FileOutputStream(out);
			System.out.println(out.getCanonicalPath());
			workbook.write(outFile);
			workbook.close();
			outFile.close();
		} catch (FileNotFoundException e) {
			log.error(e.getMessage(), e);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
		// String path = UEncrypt.encryptFileUploadPath("BaoCaoThucXuatTheoKy" +
		// startExportTime +".xlsx");
		return UEncrypt.encryptFileUploadPath("Chi tiết điểm phối hợp các phòng ban - Error" + startExportTime + ".xlsx");
	}
    
    public String decidedExportFile(TblKpiScoreDTO obj,HttpServletRequest request) throws Exception{
    	
    	if("1".equals(obj.getCheckScore())){
    		obj.setScorebonus((float) 0);
    	}
    	if("0".equals(obj.getCheckScore())){
    		obj.setScorepenalty((float) 0);
    	}
    	
    	List<TblKpiScoreDTO> listObj = tblKpiScoreDAO.getInfoDetail(obj);
    	String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
    	try{
    		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream file = new BufferedInputStream(new FileInputStream(filePath + "Chi tiết điểm phối hợp các phòng ban.xlsx"));
			
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

			for (int i = 0; i < listObj.size(); i++) {
				try {
					XSSFRow row = sheet.createRow(rownum);
					XSSFCell cell0 = row.createCell(0);
					cell0.setCellValue(i + 1);
					cell0.setCellStyle(style);
					rownum++;
					XSSFCell cell1 = row.createCell(1);
					cell1.setCellValue(listObj.get(i).getDepartmentname());

					XSSFCell cell2 = row.createCell(2);
					if(listObj.get(i).getScorebonus()!=0){
						cell2.setCellValue(listObj.get(i).getScorebonus());
					}else{
						cell2.setCellValue(listObj.get(i).getScorepenalty());
					}
					
						
					XSSFCell cell3 = row.createCell(3);
					if(listObj.get(i).getScorebonusConfirm()!=0){
						cell3.setCellValue(listObj.get(i).getScorebonusConfirm());
					}else{
						cell3.setCellValue(listObj.get(i).getScorepenaltyConfirm());
					}			
					XSSFCell cell4 = row.createCell(4);
					cell4.setCellValue(listObj.get(i).getReason());
					XSSFCell cell5 = row.createCell(5);
					cell5.setCellValue(listObj.get(i).getCreated_date());
					XSSFCell cell6 = row.createCell(6);
					cell6.setCellValue(listObj.get(i).getUsernamecreated());
					XSSFCell cell7 = row.createCell(7);
					cell7.setCellValue(listObj.get(i).getDepartmentnamecreated());
					XSSFCell cell9 = row.createCell(9);
					cell9.setCellValue(listObj.get(i).getKpiid());
					
					
					
					
					cell0.setCellStyle(style);
					cell1.setCellStyle(style);
					cell2.setCellStyle(styleNumber);
					cell3.setCellStyle(styleNumber);
				
					cell4.setCellStyle(style);
					cell5.setCellStyle(style);
					
					cell6.setCellStyle(style);
					cell7.setCellStyle(style);
					cell9.setCellStyle(style);
					
				} catch (Exception e) {
					log.error(e.getMessage());
				}

			}

			file.close();

			File out = new File(folder2Upload + File.separatorChar + "Chi tiết điểm phối hợp các phòng ban" + startExportTime + ".xlsx");

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
		return UEncrypt.encryptFileUploadPath("Chi tiết điểm phối hợp các phòng ban" + startExportTime + ".xlsx");
    	
    }
    public String generalExportFile(TblKpiScoreDTO obj,HttpServletRequest request) throws Exception{
    	List<TblKpiScoreDTO> listObj = tblKpiScoreDAO.getListAllDepartment(obj);
    	String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
    	try{
    		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream file = new BufferedInputStream(new FileInputStream(filePath + "Tổng hợp điểm phối hợp các Phòng Ban.xlsx"));
			
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

			for (int i = 0; i < listObj.size(); i++) {
				try {
					XSSFRow row = sheet.createRow(rownum);
					XSSFCell cell0 = row.createCell(0);
					cell0.setCellValue(i + 1);
					cell0.setCellStyle(style);
					rownum++;
					XSSFCell cell1 = row.createCell(1);
					cell1.setCellValue(listObj.get(i).getDepartmentname());

					XSSFCell cell2 = row.createCell(2);
					cell2.setCellValue(listObj.get(i).getScorebonus());
						
					XSSFCell cell3 = row.createCell(3);
					cell3.setCellValue(listObj.get(i).getScorebonusConfirm());
					
					XSSFCell cell4 = row.createCell(4);
					cell4.setCellValue(listObj.get(i).getScorepenalty());
					
					XSSFCell cell5 = row.createCell(5);
					cell5.setCellValue(listObj.get(i).getScorepenaltyConfirm());
					
					XSSFCell cell6 = row.createCell(6);
					cell6.setCellValue(listObj.get(i).getScorebonus()+listObj.get(i).getScorepenalty());
					
					XSSFCell cell7 = row.createCell(7);
					cell7.setCellValue(listObj.get(i).getScorebonusConfirm()+listObj.get(i).getScorepenaltyConfirm());

					cell0.setCellStyle(style);
					cell1.setCellStyle(style);
					cell2.setCellStyle(styleNumber);
					cell3.setCellStyle(styleNumber);
					cell4.setCellStyle(styleNumber);
					cell5.setCellStyle(styleNumber);
					cell6.setCellStyle(styleNumber);
					cell7.setCellStyle(styleNumber);
					
				} catch (Exception e) {
					log.error(e.getMessage());
				}

			}

			file.close();

			File out = new File(folder2Upload + File.separatorChar + "Tổng hợp điểm phối hợp các Phòng Ban" + startExportTime + ".xlsx");

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
		return UEncrypt.encryptFileUploadPath("Tổng hợp điểm phối hợp các Phòng Ban" + startExportTime + ".xlsx");
    	
    }
    
    
}
