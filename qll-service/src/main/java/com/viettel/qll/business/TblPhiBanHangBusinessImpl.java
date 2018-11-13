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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.viettel.ktts2.common.UEncrypt;
import com.viettel.qll.bo.TblPhiBanHangBO;
import com.viettel.qll.dao.TblDmKiCaNhanDAO;
import com.viettel.qll.dao.TblNhanVienDAO;
import com.viettel.qll.dao.TblPhiBanHangDAO;
import com.viettel.qll.dto.ErrExcelDTO;
import com.viettel.qll.dto.ImportErrDTO;
import com.viettel.qll.dto.LsThaoTacDTO;
import com.viettel.qll.dto.TblPhiBanHangDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;
import com.viettel.utils.ValidateUtils;

@Service("tblPhiBanHangBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TblPhiBanHangBusinessImpl extends BaseFWBusinessImpl<TblPhiBanHangDAO, TblPhiBanHangDTO, TblPhiBanHangBO>
		implements TblPhiBanHangBusiness {
	protected final Logger log = Logger.getLogger(TblPhiBanHangBusiness.class);

	@Autowired
	private TblPhiBanHangDAO tblPhiBanHangDAO;
	private String fomat="dd/MM/yyyy";
	@Autowired
	private TblDmKiCaNhanDAO kiCaNhanDAO;
	
	LsThaoTacDTO lsThaoTacDTO = new LsThaoTacDTO();

	@Autowired
	TblQltsThucXuatTheoKyBusinessImpl businessImpl=new TblQltsThucXuatTheoKyBusinessImpl();
	

	@Autowired
	TblNhanVienDAO tblNhanVienDAO;
	@Value("${folder_upload2}")
	private String folder2Upload;

	List<ErrExcelDTO> lstErrorExcell;

	public TblPhiBanHangBusinessImpl() {
		tModel = new TblPhiBanHangBO();
		tDAO = tblPhiBanHangDAO;
	}

	@Override
	public TblPhiBanHangDAO gettDAO() {
		return tblPhiBanHangDAO;
	}

	@Override
	public DataListDTO doSearchPhiBanHang(TblPhiBanHangDTO obj, HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		String err = "";

		List<TblPhiBanHangDTO> ls = tblPhiBanHangDAO.doSearchPhiBanHang(obj);
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		data.setTotal(obj.getTotalRecord());
		data.setSize(obj.getTotalRecord());
		data.setStart(1);
		return data;
	}

	public static boolean contains(String[] arr, String item) {
		for (String n : arr) {
			if (item.equals(n)) {
				return false;
			}
		}
		return true;
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

	public String importPhiBanHang(String filePath,HttpServletRequest request
			) throws Exception {
		long tStart = System.currentTimeMillis();
		TblPhiBanHangDTO lst = new TblPhiBanHangDTO();
		// KIDonViDTO lstFault = new KIDonViDTO();

		ImportErrDTO importErrDTO = new ImportErrDTO();
		// List<ErrExcelDTO> lstErrorExcell;

		ErrExcelDTO errExcelDTO = new ErrExcelDTO();
		// lstErrorOrder = new ArrayList<>();
		List<TblPhiBanHangDTO> workLstDone = Lists.newArrayList();
		/**
		 * sửa đây
		 */
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

			if (count >= 7 && !isRowEmpty(row)) {
				lstErrorExcell = new ArrayList();
				ImportErrDTO err = new ImportErrDTO();
				boolean checkThang = true;
				boolean checkUserBanHang = true;
				boolean checkMaTinh = true;
				boolean checkTongThueBao = true;
				boolean checkPhiBanHangTruocThue = true;
				boolean checkPhiBanHangSauThue = true;
				boolean checkMaNv = true;
				boolean checkTenCtv = true;

				String thang = "";
				String userBanHang = "";
				String maTinh = "";
				String tongThueBao = "";
				String phiBanHangTruocThue = "";
				String phiBanHangSauThue = "";
				String maNv = "";
				String tenCtv = "";
				for (Cell cell : row) {
					if (cell.getColumnIndex() == 1) {
						thang = dataFormatter.formatCellValue(cell);
					} else if (cell.getColumnIndex() == 2) {
						userBanHang = dataFormatter.formatCellValue(cell);
					} else if (cell.getColumnIndex() == 3) {
						maTinh = dataFormatter.formatCellValue(cell);
					} else if (cell.getColumnIndex() == 4) {
						tongThueBao = dataFormatter.formatCellValue(cell);
					} else if (cell.getColumnIndex() == 5) {
						phiBanHangTruocThue = dataFormatter.formatCellValue(cell);
					} else if (cell.getColumnIndex() == 6) {
						phiBanHangSauThue = dataFormatter.formatCellValue(cell);
					} else if (cell.getColumnIndex() == 7) {
						maNv = dataFormatter.formatCellValue(cell);
					} else if (cell.getColumnIndex() == 8) {
						tenCtv = dataFormatter.formatCellValue(cell);
					}
				}

				checkThang = checkPhiBanHang(1, thang);
				checkUserBanHang = checkPhiBanHang(2, userBanHang);
				checkMaTinh = checkPhiBanHang(3, maTinh);
				checkTongThueBao = checkPhiBanHang(4, tongThueBao);
				checkPhiBanHangTruocThue = checkPhiBanHang(5, phiBanHangTruocThue);
				checkPhiBanHangSauThue = checkPhiBanHang(6, phiBanHangSauThue);
				checkMaNv = checkPhiBanHang(7, maNv);
				checkTenCtv = checkPhiBanHang(8, tenCtv);

				// if(checkMaNv && checkThang){
				// if(countPhiBanHangByMaNvAndThang(maNv, thang)!=0){
				// checkThang = false;
				// checkMaNv = false;
				// err.setDetailError("Trường mã nhân viên + tháng đã tồn tại
				// trong hệ thống");
				// lstErrorOrder.add(err);
				// }
				// }
				if (checkThang && checkUserBanHang && checkMaTinh && checkTongThueBao && checkPhiBanHangTruocThue
						&& checkPhiBanHangSauThue && checkMaNv && checkTenCtv) {

					lst = new TblPhiBanHangDTO();
					lst.setThang(thang);
					lst.setUserBanHang(userBanHang);
					lst.setMaTinh(maTinh);
					if (!tongThueBao.isEmpty()) {
						lst.setTongThueBao(Long.parseLong(tongThueBao));
					} else {
						lst.setTongThueBao((long) 0);
					}

					if (!phiBanHangTruocThue.isEmpty()) {
						lst.setPhiTruocThue(Float.parseFloat(phiBanHangTruocThue));
					} else {
						lst.setPhiTruocThue(Float.parseFloat("0"));
					}
					if (!phiBanHangSauThue.isEmpty()) {
						lst.setPhiSauThue(Float.parseFloat(phiBanHangSauThue));
					} else {
						lst.setPhiSauThue(Float.parseFloat("0"));
					}

					lst.setMaNv(maNv.toUpperCase());
					lst.setTenCtv(tenCtv);
					lst.setXoa((long) 0);
					lst.setHoatDong((long) 1);
					workLstDone.add(lst);
				} else {
					importErrDTO = new ImportErrDTO();
					importErrDTO.setColumn1(thang);
					importErrDTO.setColumn2(userBanHang);
					importErrDTO.setColumn3(maTinh);
					importErrDTO.setColumn4(tongThueBao);
					importErrDTO.setColumn5(phiBanHangTruocThue);
					importErrDTO.setColumn6(phiBanHangSauThue);
					importErrDTO.setColumn7(maNv);
					importErrDTO.setColumn8(tenCtv);
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
				lsThaoTacDTO.setChucNang("Import dữ liệu PXK type B");
				lsThaoTacDTO.setMoTa("Import "+workLstDone.size()+" bản ghi dữ liệu PXK type B ");
				lsThaoTacDTO.setThoiGianThucHien(tEnd-tStart);
				businessImpl.insertLSTT(lsThaoTacDTO,request);
			}
			if (workLstFault.size() > 0) {
				return exportPhiBanHangErr(workLstFault);
			}
		}
		System.out.println(workLstDone.toString().toString());
		return "";
	}

	// check ngay cong
	public boolean checkPhiBanHang(int index, String value) {
		String errDetail = "";
		ErrExcelDTO err = new ErrExcelDTO();
		if (index == 1) {
			if (value.isEmpty()) {
				err.setDetailError("Tháng không được để trống");
				lstErrorExcell.add(err);
				return false;
			} else {
				if (!ValidateUtils.isDate(value,fomat)) {
					err.setDetailError("Tháng không đúng định dạng mm/yyyy");
					lstErrorExcell.add(err);
					return false;
				}
			}

		} else if (index == 2) {
			if (!value.isEmpty()) {
				if (value.length() > 50) {
					err.setDetailError("Trường user bán hàng vượt quá độ dài cho phép");
					lstErrorExcell.add(err);
					return false;
				}
			}
		} else if (index == 3) {
			if (!value.isEmpty()) {
				if (value.length() > 50) {
					err.setDetailError("Trường mã tỉnh vượt quá độ dài cho phép");
					lstErrorExcell.add(err);
					return false;
				}
			}
		} else if (index == 4) {
			if (!value.isEmpty()) {
				if (!isIntNumber(value)) {
					err.setDetailError("Trường tổng thuê bao không đúng định dạng");
					lstErrorExcell.add(err);
					return false;
				}
			}
		} else if (index == 5) {
			// if (value.isEmpty()) {
			// err.setDetailError("Trường phí bán hàng trước thuế không được để
			// trống");
			// lstErrorOrder.add(err);
			// return false;
			// } else
			if (!value.isEmpty() && !ValidateUtils.isFloat(value)) {
				err.setDetailError("Trường phí bán hàng trước thuế không đúng định dạng");
				lstErrorExcell.add(err);
				return false;
			}
			if (!value.isEmpty() && Double.parseDouble(value) < 0) {
				err.setDetailError("Trường phí bán hàng trước thuế phải là số thực không âm");
				lstErrorExcell.add(err);
				return false;
			}
		} else if (index == 6) {
			// if (value.isEmpty()) {
			// err.setDetailError("Trường phí bán hàng sau thuế không được để
			// trống");
			// lstErrorOrder.add(err);
			// return false;
			// } else
			if (!value.isEmpty() && !ValidateUtils.isFloat(value)) {
				err.setDetailError("Trường phí bán hàng sau thuế không đúng định dạng");
				lstErrorExcell.add(err);
				return false;
			}
			if (!value.isEmpty() && Double.parseDouble(value) < 0) {
				err.setDetailError("Trường phí bán hàng sau thuế phải là số thực không âm");
				lstErrorExcell.add(err);
				return false;
			}
		} else if (index == 7) {
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
			if (kiCaNhanDAO.checkExistMaNvByMaNv(value) == 0) {
				err.setDetailError("Mã nhân viên không tồn tại");
				lstErrorExcell.add(err);
				return false;
			}
		} else if (index == 8) {
			if (!value.isEmpty()) {
				if (value.length() > 50) {
					err.setDetailError("Trường CTV vượt quá độ dài cho phép");
					lstErrorExcell.add(err);
					return false;
				}
			}
		}
		return true;
	}

	public boolean isIntNumber(String str) {
		try {
			int d = Integer.parseInt(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public String exportPhiBanHangErr(List<ImportErrDTO> obj) throws Exception {
		// TODO Auto-generated method stub
		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream fileIn = new BufferedInputStream(new FileInputStream(filePath + "phiBanHangErr.xlsx"));
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
			int rownum = 7;
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

				XSSFCell cell7 = row.createCell(7);
				cell7.setCellValue(obj.get(i).getColumn7());
				cell7.setCellStyle(style);

				XSSFCell cell8 = row.createCell(8);
				cell8.setCellValue(obj.get(i).getColumn8());
				cell8.setCellStyle(style);

				for (int j = 0; j < obj.get(i).getLstErrorOrder().size(); j++) {
					err.append(obj.get(i).getLstErrorOrder().get(j).getDetailError() + "-");
					// System.out.println("err" + err);
				}
				System.out.println("err " + i + ":" + err);

				XSSFCell cell9 = row.createCell(9);
				cell9.setCellValue(err.toString());
				cell9.setCellStyle(style);
			}
			File out = new File(folder2Upload + File.separatorChar + "phiBanHangErr" + startExportTime + ".xlsx");

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
		return UEncrypt.encryptFileUploadPath("phiBanHangErr" + startExportTime + ".xlsx");
	}

	public String exportKIDonVi(TblPhiBanHangDTO lst) throws Exception {
		List<TblPhiBanHangDTO> obj = tblPhiBanHangDAO.doSearchPhiBanHang(lst);
		// TODO Auto-generated method stub
		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream fileIn = new BufferedInputStream(new FileInputStream(filePath + "ExportPhiBanHang.xlsx"));
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
			int rownum = 7;
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
				cell2.setCellValue(obj.get(i).getUserBanHang());
				cell2.setCellStyle(style);

				XSSFCell cell3 = row.createCell(3);
				cell3.setCellValue(obj.get(i).getMaTinh());
				cell3.setCellStyle(style);

				XSSFCell cell4 = row.createCell(4);
				cell4.setCellValue(obj.get(i).getTongThueBao());
				cell4.setCellStyle(style);

				XSSFCell cell5 = row.createCell(5);
				cell5.setCellValue(obj.get(i).getPhiTruocThue());
				cell5.setCellStyle(style);

				XSSFCell cell6 = row.createCell(6);
				cell6.setCellValue(obj.get(i).getPhiSauThue());
				cell6.setCellStyle(style);

				XSSFCell cell7 = row.createCell(7);
				cell7.setCellValue(obj.get(i).getMaNv());
				cell7.setCellStyle(style);

				XSSFCell cell8 = row.createCell(8);
				cell8.setCellValue(obj.get(i).getTenCtv());
				cell8.setCellStyle(style);

			}
			File out = new File(folder2Upload + File.separatorChar + "ExportPhiBanHang" + startExportTime + ".xlsx");

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
		return UEncrypt.encryptFileUploadPath("ExportPhiBanHang" + startExportTime + ".xlsx");
	}

}
