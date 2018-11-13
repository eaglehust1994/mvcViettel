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
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
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

import com.google.common.collect.Lists;
import com.viettel.ktts2.common.UEncrypt;
import com.viettel.qll.bo.TblQuaTrinhTlBO;
import com.viettel.qll.dao.TblQuaTrinhTlDAO;
import com.viettel.qll.dto.ErrExcelDTO;
import com.viettel.qll.dto.ImportErrDTO;
import com.viettel.qll.dto.LsThaoTacDTO;
import com.viettel.qll.dto.TblQuaTrinhTlDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;

@Service("tblQuaTrinhTlBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TblQuaTrinhTlBusinessImpl extends BaseFWBusinessImpl<TblQuaTrinhTlDAO, TblQuaTrinhTlDTO, TblQuaTrinhTlBO>
		implements TblQuaTrinhTlBusiness {
	protected final Logger log = Logger.getLogger(TblQuaTrinhTlBusiness.class);

	@Autowired
	private TblQuaTrinhTlDAO tblQuaTrinhTlDAO;

	LsThaoTacDTO lsThaoTacDTO = new LsThaoTacDTO();

	@Autowired
	TblQltsThucXuatTheoKyBusinessImpl businessImpl=new TblQltsThucXuatTheoKyBusinessImpl();
	
	
	@Value("${folder_upload2}")
	private String folder2Upload;

	List<ErrExcelDTO> lstErrorExcell;

	public TblQuaTrinhTlBusinessImpl() {
		tModel = new TblQuaTrinhTlBO();
		tDAO = tblQuaTrinhTlDAO;
	}

	@Override
	public TblQuaTrinhTlDAO gettDAO() {
		return tblQuaTrinhTlDAO;
	}

	@Override
	public DataListDTO doSearchQTTL(TblQuaTrinhTlDTO obj) {
		List<TblQuaTrinhTlDTO> ls = tblQuaTrinhTlDAO.doSearchQTTL(obj);
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		data.setTotal(obj.getTotalRecord());
		data.setSize(obj.getTotalRecord());
		data.setStart(1);
		return data;
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

	public String importQTTL(String filePath,HttpServletRequest request) throws Exception {
		TblQuaTrinhTlDTO quaTrinhTlDTO = new TblQuaTrinhTlDTO();
		long tStart = System.currentTimeMillis();
		ImportErrDTO importErrDTO = new ImportErrDTO();
		ErrExcelDTO errExcelDTO = new ErrExcelDTO();
		List<TblQuaTrinhTlDTO> workLstDone = Lists.newArrayList();
		List<ImportErrDTO> workLstFault = Lists.newArrayList();
		File file = new File(filePath);// đọc file từ filepath excel

		XSSFWorkbook workbook = new XSSFWorkbook(file);// khởi tạo 1 workBook cho
													// file khởi tạo ở trên

		XSSFSheet sheet = workbook.getSheetAt(0);// lấy sheet(trang excel) đầu
													// tiên của 1 workbook
		DataFormatter dataFormatter = new DataFormatter();// .....

		int count = 0;// biến đếm hàng trong sheet
		// đọc từng row trong sheet
		for (Row row : sheet) {
			count++;// cứ bắt đầu 1 vòng for tăng biến đếm lên 1
			// count>=6 bỏ qua row 0 đến row chứa tiêu đề(row =5) ,isRowEmpty:
			// check row trống sẽ bỏ qua
			if (count >= 6 && !isRowEmpty(row)) {
				lstErrorExcell = new ArrayList();
				boolean checkDonVi = true;
				boolean checkMaTram = true;
				boolean checkMaQttl = true;

				String donVi = "";
				String maTram = "";
				String maQttl = "";

				// đọc từng cột trong row(hàng)
				for (Cell cell : row) {
					// gán giá trị cho từng cột trong row bỏ qua từ 0(0 là
					// STT--> bỏ qua)
					switch (cell.getColumnIndex()) {
					case 1:
						donVi = dataFormatter.formatCellValue(cell);
						break;
					case 2:
						maTram = dataFormatter.formatCellValue(cell);
						break;
					case 3:
						maQttl = dataFormatter.formatCellValue(cell);
						break;

					default:
						break;
					}
				}
				// kiểm tra tưng cột với các ràng buộc
				 checkDonVi=checkQTTL(1, donVi, errExcelDTO);
				 checkMaTram=checkQTTL(2, maTram, errExcelDTO);
				 checkMaQttl=checkQTTL(3, maQttl, errExcelDTO);
				 
				if (checkDonVi && checkMaTram && checkMaQttl) {
					quaTrinhTlDTO = new TblQuaTrinhTlDTO();
					quaTrinhTlDTO.setDonVi(donVi);
					quaTrinhTlDTO.setMaQttl(maQttl);
					quaTrinhTlDTO.setMaTram(maTram);
					// xóa các bản ghi trùng đã có trong db
					tblQuaTrinhTlDAO.deleteObj(quaTrinhTlDTO);
					workLstDone.add(quaTrinhTlDTO);
				} else {
					importErrDTO = new ImportErrDTO();
					importErrDTO.setColumn1(donVi);
					importErrDTO.setColumn2(maTram);
					importErrDTO.setColumn3(maQttl);
					importErrDTO.setLstErrorOrder(lstErrorExcell);
					workLstFault.add(importErrDTO);
				}

			}
		}

		workLstDone.toArray();
		workLstFault.toArray();
		if (workLstDone.size() > 0) {
			saveList(workLstDone);
			long tEnd = System.currentTimeMillis();
			lsThaoTacDTO.setChucNang("Import dữ liệu PXK type B");
			lsThaoTacDTO.setMoTa("Import "+workLstDone.size()+" bản ghi dữ liệu PXK type B ");
			lsThaoTacDTO.setThoiGianThucHien(tEnd-tStart);
			businessImpl.insertLSTT(lsThaoTacDTO,request);
		}
		if (workLstFault.size() > 0) {
			 return exportQTTLErr(workLstFault);
		}
		System.out.println(workLstDone.size());
		System.out.println(workLstFault.size());
		return "";

	}

	public boolean checkQTTL(int index, String value, ErrExcelDTO errExcelDTO) {
		ErrExcelDTO err = new ErrExcelDTO();
		switch (index) {
		case 1:
			if (StringUtils.isEmpty(value)) {
				err.setDetailError("Đơn vị đang để trống");
				lstErrorExcell.add(err);
				return false;
			}
			break;
		case 2:
			if (StringUtils.isEmpty(value)) {
				err.setDetailError("Mã trạm đang để trống");
				lstErrorExcell.add(err);
				return false;
			}
			break;
		case 3:
			if (StringUtils.isEmpty(value)) {
				err.setDetailError("Mã quá trình tính lương đang để trống");
				lstErrorExcell.add(err);
				return false;
			}
			break;

		default:
			break;
		}
		return true;
	}
	
	public String exportQTTLErr(List<ImportErrDTO> obj) throws Exception {
		// TODO Auto-generated method stub
		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream fileIn = new BufferedInputStream(new FileInputStream(filePath + "QTTLErr.xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(fileIn);
			XSSFSheet sheet = workbook.getSheetAt(0);
			XSSFCellStyle style = workbook.createCellStyle();

			XSSFFont hSSFFont = workbook.createFont();
			hSSFFont.setFontName("Times New Roman");
			style.setFont(hSSFFont);

			style.setBorderBottom(BorderStyle.THIN);
			style.setBorderTop(BorderStyle.THIN);
			style.setBorderRight(BorderStyle.THIN);
			style.setBorderLeft(BorderStyle.THIN);
			style.setWrapText(true);
			int rownum = 6;
			for (int i = 0; i < obj.size(); i++) {
				StringBuilder err = new StringBuilder();
				XSSFRow row = sheet.createRow(rownum);
				rownum++;

				XSSFCell cell0 = row.createCell(0);
				cell0.setCellValue(i + 1);
				cell0.setCellStyle(style);

				XSSFCell cell1 = row.createCell(1);
				cell1.setCellValue(obj.get(i).getColumn1());

				XSSFCell cell2 = row.createCell(2);
				cell2.setCellValue(obj.get(i).getColumn2());

				XSSFCell cell3 = row.createCell(3);
				cell3.setCellValue(obj.get(i).getColumn3());

				for (int j = 0; j < obj.get(i).getLstErrorOrder().size(); j++) {
					err.append(obj.get(i).getLstErrorOrder().get(j).getDetailError());
					// System.out.println("err" + err);
				}
				System.out.println("err " + i + ":" + err);
				XSSFCell cell4 = row.createCell(4);
				cell4.setCellValue(err.toString());
				cell0.setCellStyle(style);
				cell1.setCellStyle(style);
				cell2.setCellStyle(style);
				cell3.setCellStyle(style);

			}
			fileIn.close();

			File out = new File(folder2Upload + File.separatorChar + "QTTLErr" + startExportTime + ".xlsx");

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
		return UEncrypt.encryptFileUploadPath("QTTLErr" + startExportTime + ".xlsx");
	}
	
	public String exportExcelGrid(TblQuaTrinhTlDTO lst) throws Exception {

		List<TblQuaTrinhTlDTO> obj = tblQuaTrinhTlDAO.doSearchQTTL(lst);

		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream fileIn = new BufferedInputStream(new FileInputStream(filePath + "ExportQTTL.xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(fileIn);
			XSSFSheet sheet = workbook.getSheetAt(0);
			XSSFCellStyle style = workbook.createCellStyle();
			style.setBorderBottom(BorderStyle.THIN);
			style.setBorderTop(BorderStyle.THIN);
			style.setBorderRight(BorderStyle.THIN);
			style.setBorderLeft(BorderStyle.THIN);
			style.setWrapText(true);
			int rownum = 6;
			for (int i = 0; i < obj.size(); i++) {
				StringBuilder err = new StringBuilder();
				XSSFRow row = sheet.createRow(rownum);

				XSSFCell cell0 = row.createCell(0);
				cell0.setCellValue(i + 1);
				rownum++;

				XSSFCell cell1 = row.createCell(1);
				cell1.setCellValue(obj.get(i).getDonVi());

				XSSFCell cell2 = row.createCell(2);
				cell2.setCellValue(obj.get(i).getMaTram());

				XSSFCell cell3 = row.createCell(3);
				cell3.setCellValue(obj.get(i).getMaQttl());


				cell0.setCellStyle(style);
				cell1.setCellStyle(style);
				cell2.setCellStyle(style);
				cell3.setCellStyle(style);
			}
			File out = new File(folder2Upload + File.separatorChar + "ExportQTTL" + startExportTime + ".xlsx");

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
		return UEncrypt.encryptFileUploadPath("ExportQTTL" + startExportTime + ".xlsx");
	}
	public String downloadImportTemplate() throws Exception {
		// TODO Auto-generated method stub
		
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		String filePath = classloader.getResource("../" + "doc-template").getPath();
		InputStream file = new BufferedInputStream(new FileInputStream(filePath + "ExportQTTL.xlsx"));
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		
		
		file.close();
		File out = new File(folder2Upload + File.separatorChar + "ExportQTTL.xlsx");
		
		FileOutputStream outFile = new FileOutputStream(out);
		workbook.write(outFile);
		workbook.close();
		outFile.close();
		
		String path = UEncrypt.encryptFileUploadPath("ExportQTTL.xlsx");
		return path;
	}
}
