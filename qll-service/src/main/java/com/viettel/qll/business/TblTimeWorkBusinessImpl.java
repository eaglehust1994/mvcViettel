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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import java.util.List;


import javax.servlet.http.HttpServletRequest;

import com.google.common.collect.Lists;
import com.viettel.ktts2.common.UEncrypt;
import com.viettel.qll.bo.TblTimeWorkBO;
import com.viettel.qll.dao.TblTimeWorkDAO;
import com.viettel.qll.dto.ErrExcelDTO;
import com.viettel.qll.dto.ImportErrDTO;

import com.viettel.qll.dto.TblTimeWorkDTO;
import com.viettel.qll.dto.VpsUserToken;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;


import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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


@Service("tblTimeWorkBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TblTimeWorkBusinessImpl extends BaseFWBusinessImpl<TblTimeWorkDAO,TblTimeWorkDTO, TblTimeWorkBO> implements TblTimeWorkBusiness {

    @Autowired
    private TblTimeWorkDAO tblTimeWorkDAO;
     
    protected final Logger log = Logger.getLogger(TblTimeWorkBusinessImpl.class);
    
    @Value("${folder_upload2}")
  	private String folder2Upload;
    List<ErrExcelDTO> lstErrExcelDto;
    DateFormat dt = new SimpleDateFormat("MM/dd/yyyy hh:mm");
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
    
    public TblTimeWorkBusinessImpl() {
        tModel = new TblTimeWorkBO();
        tDAO = tblTimeWorkDAO;
    }

    @Override
    public TblTimeWorkDAO gettDAO() {
        return tblTimeWorkDAO;
    }
	


    @Override
	public DataListDTO doSearch(TblTimeWorkDTO obj) throws Exception {
		List<TblTimeWorkDTO> ls = tblTimeWorkDAO.reportTimeLate(obj);
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		data.setTotal(obj.getTotalRecord());
		data.setSize(obj.getTotalRecord());
		data.setStart(1);
		return data;
	}
    
	public boolean checkDataFromFileExel(String data, int columnIndex, ErrExcelDTO orderErrorFormat){
		orderErrorFormat = new ErrExcelDTO();
		switch (columnIndex){
		case 0:
			if (StringUtils.isEmpty(data)) {
				orderErrorFormat.setDetailError("Gen-time không được để trống");
				lstErrExcelDto.add(orderErrorFormat);
				return false;
			}
			break;
		case 5:
			if (StringUtils.isEmpty(data)) {
				orderErrorFormat.setDetailError("Reader không được để trống");
				lstErrExcelDto.add(orderErrorFormat);
				return false;
			}
			break;	
			
		case 9:
			if (StringUtils.isEmpty(data)) {
				orderErrorFormat.setDetailError("Name không được để trống");
				lstErrExcelDto.add(orderErrorFormat);
				return false;
			}
			break;	
		default:
			break;
	
		}
		
		
		return true;
	}
    @SuppressWarnings("unused")
	public String importFile(String fileInput, HttpServletRequest request) throws Exception{
		
    		tblTimeWorkDAO.getTruncate();
			List<TblTimeWorkDTO> workLst = Lists.newArrayList();
			List<ImportErrDTO> workFault = Lists.newArrayList();
			
			VpsUserToken token = (VpsUserToken) request.getSession().getAttribute("vpsUserToken");
		try{
			String Ten = "";
//			File f = new File(fileInput);
			
			InputStream f = new FileInputStream(fileInput);
			HSSFWorkbook  workbook = new HSSFWorkbook(f);
			HSSFSheet  sheet = workbook.getSheetAt(0);

			DataFormatter dataFormatter = new DataFormatter();
			int count = 0;
			for(Row row : sheet){
				count++;
				ErrExcelDTO orderErrorFormat = new ErrExcelDTO();
				if(count>=2  && !isRowEmpty(row)){
					lstErrExcelDto = new ArrayList<>();
					
					boolean checkGenTime = true;
					boolean checkDerpartment = true;
					boolean checkName = true;
					
					
					String genTime ="";
					String name ="";
					String derpartment ="";
					
					
					for( Cell cell: row){
						if (cell.getColumnIndex() == 0) {
							genTime = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 5) {
							derpartment = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 9) {
							name = dataFormatter.formatCellValue(cell);
						} 
					}
					
					checkGenTime = checkDataFromFileExel(genTime.trim(), 0, orderErrorFormat);
					checkDerpartment = checkDataFromFileExel(derpartment.trim(), 5, orderErrorFormat);
					checkName = checkDataFromFileExel(name.trim(), 9, orderErrorFormat);
					
					
					if(checkGenTime&&checkDerpartment&&checkName){
						TblTimeWorkDTO newObj = new TblTimeWorkDTO();
						
						if (StringUtils.isNotEmpty(genTime)) {
							Date date = dt.parse(genTime);
							
							newObj.setGenTime(dt.parse(genTime));
						}
						newObj.setDerpartment(derpartment);
						newObj.setName(name);
						
						
						workLst.add(newObj);
			
//						
						
					} else {
						
						ImportErrDTO errObj = new ImportErrDTO();
						errObj.setColumn1(genTime);
						errObj.setColumn2(derpartment);
						errObj.setColumn3(name);
						
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
				
//					List<TblTimeWorkDTO> workLstFake = new ArrayList<TblTimeWorkDTO>(workLst);
			
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
			
		} catch (NullPointerException pointerException) {
			// pointerException.printStackTrace();
			log.error(pointerException.getMessage(), pointerException);
		} catch (Exception e) {
			// e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return "";
		
	}
    
    public String exportTimeLate(TblTimeWorkDTO obj, HttpServletRequest request) throws Exception{
    	List<TblTimeWorkDTO> listObj = tblTimeWorkDAO.reportTimeLate(obj);
    	String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
    	try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream file = new BufferedInputStream(new FileInputStream(filePath + "ExportTimeLate.xlsx"));

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
			int rownum = 1;
			

			for (int i = 0; i < listObj.size(); i++) {
				try {
					XSSFRow row = sheet.createRow(rownum);
					XSSFCell cell0 = row.createCell(0);
					cell0.setCellValue(i + 1);
					cell0.setCellStyle(style);
					rownum++;
					XSSFCell cell1 = row.createCell(1);
					cell1.setCellValue(listObj.get(i).getName());

					XSSFCell cell2 = row.createCell(2);
					cell2.setCellValue(listObj.get(i).getTimeIn());
						
					XSSFCell cell3 = row.createCell(3);
					cell3.setCellValue(listObj.get(i).getStatusIn());
					
					XSSFCell cell4 = row.createCell(4);
					cell4.setCellValue(listObj.get(i).getTimeOut());
					
					XSSFCell cell5 = row.createCell(5);
					cell5.setCellValue(listObj.get(i).getStatusOut());

					cell0.setCellStyle(style);
					cell1.setCellStyle(style);
					cell2.setCellStyle(style);
					cell3.setCellStyle(style);
					cell4.setCellStyle(style);
					cell5.setCellStyle(style);
					
				} catch (Exception e) {
					log.error(e.getMessage());
				}

			}

			file.close();

			File out = new File(folder2Upload + File.separatorChar + "ExportTimeLate" + startExportTime + ".xlsx");

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
		return UEncrypt.encryptFileUploadPath("ExportTimeLate" + startExportTime + ".xlsx");
    }
    public String exportExcelError(List<ImportErrDTO> obj) throws Exception {
		// TODO Auto-generated method stub
		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream file = new BufferedInputStream(new FileInputStream(filePath + "importTimeLateErr.xlsx"));
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
			int rownum = 1;
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

				XSSFCell cell10 = row.createCell(10);
				cell10.setCellValue(obj.get(i).getColumn10());

				XSSFCell cell11 = row.createCell(11);
				
				cell11.setCellValue(obj.get(i).getColumn11());

				XSSFCell cell12 = row.createCell(12);
			
				cell12.setCellValue(obj.get(i).getColumn12());

			
				
				
			
				
				
				

				for (int j = 0; j < obj.get(i).getLstErrorOrder().size(); j++) {
					err.append(obj.get(i).getLstErrorOrder().get(j).getDetailError() + "-");
					// System.out.println("err" + err);
				}
				System.out.println("err " + i + ":" + err);

				XSSFCell cell13 = row.createCell(13);
				cell13.setCellValue(err.toString());
				
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
				cell10.setCellStyle(style);
				cell11.setCellStyle(style);
				cell12.setCellStyle(style);
				
				
				
				
			
			}
			file.close();

			File out = new File(folder2Upload + File.separatorChar + "importTimeLateErr" + startExportTime + ".xlsx");

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
		return UEncrypt.encryptFileUploadPath("importTimeLateErr" + startExportTime + ".xlsx");
	}
}
