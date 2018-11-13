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
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.sun.xml.bind.Util;
import com.viettel.ktts2.common.UEncrypt;
import com.viettel.qll.bo.TblDmKiCaNhanBO;
import com.viettel.qll.dao.TblDmKiCaNhanDAO;
import com.viettel.qll.dao.TblNhanVienDAO;
import com.viettel.qll.dto.ErrExcelDTO;
import com.viettel.qll.dto.ImportErrDTO;
import com.viettel.qll.dto.LsThaoTacDTO;
import com.viettel.qll.dto.TblDmKiCaNhanDTO;
import com.viettel.qll.dto.TblDmKiDonViDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;
import com.viettel.utils.ValidateUtils;

import net.sf.ehcache.hibernate.HibernateUtil;

@Service("tblDmKiCaNhanBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TblDmKiCaNhanBusinessImpl extends BaseFWBusinessImpl<TblDmKiCaNhanDAO, TblDmKiCaNhanDTO, TblDmKiCaNhanBO>
		implements TblDmKiCaNhanBusiness {

	protected final Logger log = Logger.getLogger(TblDmKiCaNhanBusiness.class);

	@Autowired
	private TblDmKiCaNhanDAO tblDmKiCaNhanDAO;
	List<ErrExcelDTO> lstErrorExcell;

	LsThaoTacDTO lsThaoTacDTO = new LsThaoTacDTO();

	@Autowired
	TblQltsThucXuatTheoKyBusinessImpl businessImpl = new TblQltsThucXuatTheoKyBusinessImpl();
	
	private String fomat = "MM/yyyy";
	@Autowired
	TblNhanVienDAO tblNhanVienDAO;
	@Value("${folder_upload2}")
	private String folder2Upload;

	public TblDmKiCaNhanBusinessImpl() {
		tModel = new TblDmKiCaNhanBO();
		tDAO = tblDmKiCaNhanDAO;
	}

	@Override
	public TblDmKiCaNhanDAO gettDAO() {
		return tblDmKiCaNhanDAO;
	}

	@Override
	public DataListDTO doSearchKiCaNhan(TblDmKiCaNhanDTO obj, HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		String err = "";

		List<TblDmKiCaNhanDTO> ls = tblDmKiCaNhanDAO.doSearchKiCaNhan(obj);
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

	// import ki ca nhan
	public String importKiCaNhan(String filePath, HttpServletRequest request) throws Exception {
		long tStart = System.currentTimeMillis();
		TblDmKiCaNhanDTO lst = new TblDmKiCaNhanDTO();
		ImportErrDTO importErrDTO = new ImportErrDTO();
		// List<ErrExcelDTO> lstErrorExcell;

		ErrExcelDTO errExcelDTO = new ErrExcelDTO();
		// lstErrorOrder = new ArrayList<>();
		List<TblDmKiCaNhanDTO> workLstDone = Lists.newArrayList();
		/**
		 * sửa đây
		 */
		ArrayList<String> lstMaNv = new ArrayList<String>();
		List<ImportErrDTO> workLstFault = Lists.newArrayList();
		// Đọc một file XSL.
		File f = new File(filePath);

		// Đối tượng workbook cho file XSL.
		XSSFWorkbook workbook = new XSSFWorkbook(f);

		// Lấy ra sheet đầu tiên từ workbook
		XSSFSheet sheet = workbook.getSheetAt(0);
		DataFormatter dataFormatter = new DataFormatter();

		int count = 0;
		for (Row row : sheet) {
			count++;

			if (count >= 6 && !isRowEmpty(row)) {
				lstErrorExcell = new ArrayList();
				ImportErrDTO err = new ImportErrDTO();
				ErrExcelDTO err1 = new ErrExcelDTO();
				boolean checkThang = true;
				boolean checkMaNV = true;
				boolean checkHoVaTen = true;
				boolean checkKICaNhan = true;
				boolean checkHeSo = true;
				boolean checkDonVi = true;
				String thang = "";
				String maNV = "";
				String hoVaTen = "";
				String heSo = "";
				String donVi = "";
				String kiCaNhan = "";
				for (Cell cell : row) {
					if (cell.getColumnIndex() == 1) {
						thang = dataFormatter.formatCellValue(cell);
					} else if (cell.getColumnIndex() == 2) {
						maNV = dataFormatter.formatCellValue(cell);
					} else if (cell.getColumnIndex() == 3) {
						hoVaTen = dataFormatter.formatCellValue(cell);
					} else if (cell.getColumnIndex() == 4) {
						kiCaNhan = dataFormatter.formatCellValue(cell);
					} else if (cell.getColumnIndex() == 5) {
						heSo = dataFormatter.formatCellValue(cell);
					} else if (cell.getColumnIndex() == 6) {
						donVi = dataFormatter.formatCellValue(cell);
					}
				}

				checkThang = checkKICaNhan(1, thang);
				checkMaNV = checkKICaNhan(2, maNV);
				checkHoVaTen = checkKICaNhan(3, hoVaTen);
				checkKICaNhan = checkKICaNhan(4, kiCaNhan);
				checkHeSo = checkKICaNhan(5, heSo);
				checkDonVi = checkKICaNhan(6, donVi);
				boolean checkDupMaNv = false;
				if (checkMaNV) {
					for (int i = 0; i < lstMaNv.size(); i++) {
						if (maNV.equals(lstMaNv.get(i))) {
							err1.setDetailError("Mã nhân viên đã tồn tại trong file excel");
							lstErrorExcell.add(err1);
							checkMaNV = false;
							checkDupMaNv = true;
						}
					}
					if (!checkDupMaNv) {
						lstMaNv.add(maNV);
					}
				}
				if (checkThang && checkMaNV && checkHoVaTen && checkKICaNhan && checkHeSo && checkDonVi) {

					lst = new TblDmKiCaNhanDTO();
					lst.setThang(thang);
					lst.setMaNv(maNV.toUpperCase());
					lst.setHoVaTen(hoVaTen);
					lst.setKiCaNhan(kiCaNhan);
					lst.setHeSo(Float.parseFloat(heSo));
					lst.setDonVi(donVi);
					lst.setXoa((long) 0);
					lst.setHoatDong((long) 1);
					tblDmKiCaNhanDAO.deleteObj(lst);
					workLstDone.add(lst);

				} else {
					importErrDTO = new ImportErrDTO();
					importErrDTO.setColumn1(thang);
					importErrDTO.setColumn2(maNV);
					importErrDTO.setColumn3(hoVaTen);
					importErrDTO.setColumn4(kiCaNhan);
					importErrDTO.setColumn5(heSo);
					importErrDTO.setColumn6(donVi);
					importErrDTO.setLstErrorOrder(lstErrorExcell);
					workLstFault.add(importErrDTO);
				}
			}
		}

		System.out.println("size done: " + workLstDone.size());
		System.out.println("size: " + workLstFault.size());
		workLstDone.toArray();
		if (workLstDone.size() <= 0 && workLstFault.size() <= 0) {
			throw new IllegalArgumentException("File import không có dữ liệu");
		} else {
			if (workLstDone.size() > 0) {
				saveList(workLstDone);
				long tEnd = System.currentTimeMillis();
				lsThaoTacDTO.setChucNang("Import dữ liệu ki cá nhân");
				lsThaoTacDTO.setMoTa("Import "+workLstDone.size() +" bản ghi dữ liệu ki cá nhân ");
				lsThaoTacDTO.setThoiGianThucHien(tEnd-tStart);
				businessImpl.insertLSTT(lsThaoTacDTO,request);
			}
			if (workLstFault.size() > 0) {
				return exportKICaNhanErr(workLstFault);
			}
		}
		System.out.println(workLstDone.toString().toString());
		return "";
	}

	// check ki ca nhan
	public boolean checkKICaNhan(int index, String value) {
		String errDetail = "";
		ErrExcelDTO err = new ErrExcelDTO();
		if (index == 1) {
			if (value.isEmpty()) {
				err.setDetailError("Trường tháng không được để trống");
				lstErrorExcell.add(err);
				return false;
			} else {
				if (!ValidateUtils.isDate(value, fomat)) {
					err.setDetailError("Trường tháng không đúng định dạng MM/yyyy");
					lstErrorExcell.add(err);
					return false;
				}
			}

		} else if (index == 2) {
			if (value.isEmpty()) {
				err.setDetailError("Trường mã nhân viên không được để trống");
				lstErrorExcell.add(err);
				return false;
			}
			if (value.length() > 50) {
				err.setDetailError("Mã nhân viên vượt quá độ dài cho phép");
				lstErrorExcell.add(err);
				return false;
			}
			try {
				if (tblDmKiCaNhanDAO.checkExistMaNvByMaNv(value) == 0) {
					err.setDetailError("Mã nhân viên không tồn tại trong cơ sở dữ liệu");
					lstErrorExcell.add(err);
					return false;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (index == 3) {
			if (value.isEmpty()) {
				err.setDetailError("Trường họ và tên không được để trống");
				lstErrorExcell.add(err);
				return false;
			}
		} else if (index == 4) {
			if (value.isEmpty()) {
				err.setDetailError("Trường ki cá nhân không được để trống");
				lstErrorExcell.add(err);
				return false;
			}
		} else if (index == 5) {
			if (value.isEmpty()) {
				err.setDetailError("Trường hệ số không được để trống");
				lstErrorExcell.add(err);
				return false;
			} else if (!ValidateUtils.isFloat(value)) {
				err.setDetailError("Trường hệ số không đúng định dạng");
				lstErrorExcell.add(err);
				return false;
			}
		} else if (index == 6) {
			if (value.isEmpty()) {
				err.setDetailError("Trường đơn vị không được để trống");
				lstErrorExcell.add(err);
				return false;
			}
		}
		return true;
	}

	public String exportKICaNhanErr(List<ImportErrDTO> obj) throws Exception {
		// TODO Auto-generated method stub
		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream fileIn = new BufferedInputStream(new FileInputStream(filePath + "KiCaNhanErr.xlsx"));
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
			// System.out.println("tem:::"+folderTemplateBuild);
			int rownum = 6;
			for (int i = 0; i < obj.size(); i++) {
				StringBuilder err = new StringBuilder();
				XSSFRow row = sheet.createRow(rownum);
				XSSFCell cell0 = row.createCell(0);
				cell0.setCellValue(i + 1);
				cell0.setCellStyle(style);
				rownum++;

				XSSFCell cell1 = row.createCell(1);
				cell1.setCellValue(obj.get(i).getColumn1());
				cell1.setCellStyle(style);

				XSSFCell cell2 = row.createCell(2);
				cell2.setCellValue(obj.get(i).getColumn2());
				cell2.setCellStyle(style);

				XSSFCell cell3 = row.createCell(3);
				cell3.setCellValue(obj.get(i).getColumn3());
				cell3.setCellStyle(style);

				XSSFCell cell4 = row.createCell(4);
				cell4.setCellValue(obj.get(i).getColumn4());
				cell4.setCellStyle(style);

				XSSFCell cell5 = row.createCell(5);
				cell5.setCellValue(obj.get(i).getColumn5());
				cell5.setCellStyle(style);

				XSSFCell cell6 = row.createCell(6);
				cell6.setCellValue(obj.get(i).getColumn6());
				cell6.setCellStyle(style);

				for (int j = 0; j < obj.get(i).getLstErrorOrder().size(); j++) {
					err.append(obj.get(i).getLstErrorOrder().get(j).getDetailError() + "-");
					// System.out.println("err" + err);
				}
				System.out.println("err " + i + ":" + err);

				XSSFCell cell7 = row.createCell(7);
				cell7.setCellValue(err.toString());
				cell7.setCellStyle(style);
			}
			File out = new File(folder2Upload + File.separatorChar + "KiCaNhanErr" + startExportTime + ".xlsx");

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
		return UEncrypt.encryptFileUploadPath("KiCaNhanErr" + startExportTime + ".xlsx");
	}

	public String exportKICaNhan(TblDmKiCaNhanDTO lst) throws Exception {
		List<TblDmKiCaNhanDTO> obj = tblDmKiCaNhanDAO.doSearchKiCaNhan(lst);
		// TODO Auto-generated method stub
		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream fileIn = new BufferedInputStream(new FileInputStream(filePath + "ExportKiCaNhan.xlsx"));
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
			// System.out.println("tem:::"+folderTemplateBuild);
			int rownum = 6;
			for (int i = 0; i < obj.size(); i++) {
				StringBuilder err = new StringBuilder();
				XSSFRow row = sheet.createRow(rownum);
				XSSFCell cell0 = row.createCell(0);
				cell0.setCellValue(i + 1);
				cell0.setCellStyle(style);
				rownum++;

				XSSFCell cell1 = row.createCell(1);
				cell1.setCellValue(obj.get(i).getThang());
				cell1.setCellStyle(style);

				XSSFCell cell2 = row.createCell(2);
				cell2.setCellValue(obj.get(i).getMaNv());
				cell2.setCellStyle(style);

				XSSFCell cell3 = row.createCell(3);
				cell3.setCellValue(obj.get(i).getHoVaTen());
				cell3.setCellStyle(style);

				XSSFCell cell4 = row.createCell(4);
				cell4.setCellValue(obj.get(i).getKiCaNhan());
				cell4.setCellStyle(style);

				XSSFCell cell5 = row.createCell(5);
				cell5.setCellValue(obj.get(i).getHeSo());
				cell5.setCellStyle(style);

				XSSFCell cell6 = row.createCell(6);
				cell6.setCellValue(obj.get(i).getDonVi());
				cell6.setCellStyle(style);

			}
			File out = new File(folder2Upload + File.separatorChar + "ExportKiCaNhan" + startExportTime + ".xlsx");

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
		return UEncrypt.encryptFileUploadPath("ExportKiCaNhan" + startExportTime + ".xlsx");
	}

}
