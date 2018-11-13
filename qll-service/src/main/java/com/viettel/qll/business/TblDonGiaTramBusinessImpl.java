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
import com.viettel.qll.bo.TblDonGiaTramBO;
import com.viettel.qll.dao.TblDonGiaTramDAO;
import com.viettel.qll.dto.ErrExcelDTO;
import com.viettel.qll.dto.ImportErrDTO;
import com.viettel.qll.dto.LsThaoTacDTO;
import com.viettel.qll.dto.TblDonGiaTramDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;
import com.viettel.utils.ValidateUtils;

import freemarker.core.ParseException;

@Service("tblDonGiaTramBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TblDonGiaTramBusinessImpl extends BaseFWBusinessImpl<TblDonGiaTramDAO, TblDonGiaTramDTO, TblDonGiaTramBO>
		implements TblDonGiaTramBusiness {
	protected final Logger log = Logger.getLogger(TblDonGiaTramBusiness.class);

	private final String L1 = "KV thành thị, thủ phủ";
	private final String L2 = "Đồng bằng, trung du";
	private final String L3 = "Địa hình khó khăn (Miền núi, sông nước)";
	private final String T1 = "Loại 1";
	private final String T2 = "Loại 2";
	private final String T3 = "Loại 3";
	private final String T4 = "Loại 4";
	private final String V1 = "Vùng 1";
	private final String V2 = "Vùng 2";
	private final String V3 = "Vùng 3";
	private final String V4 = "Vùng 4";
	
	LsThaoTacDTO lsThaoTacDTO = new LsThaoTacDTO();

	@Autowired
	TblQltsThucXuatTheoKyBusinessImpl businessImpl=new TblQltsThucXuatTheoKyBusinessImpl();
	

	@Autowired
	private TblDonGiaTramDAO tblDonGiaTramDAO;

	@Value("${folder_upload2}")
	private String folder2Upload;

	List<ErrExcelDTO> lstErrorExcell;

	public TblDonGiaTramBusinessImpl() {
		tModel = new TblDonGiaTramBO();
		tDAO = tblDonGiaTramDAO;
	}

	@Override
	public TblDonGiaTramDAO gettDAO() {
		return tblDonGiaTramDAO;
	}

	@Override
	public DataListDTO doSearchDonGiaTram(TblDonGiaTramDTO obj) {
		if (!StringUtils.isEmpty(obj.getLoaiTram())) {
			switch (obj.getLoaiTram()) {
			case "1":
				obj.setLoaiTram(T1);
				break;
			case "2":
				obj.setLoaiTram(T2);
				break;
			case "3":
				obj.setLoaiTram(T3);
				break;
			case "4":
				obj.setLoaiTram(T4);
				break;
			default:

				break;

			}
		}

		if (!StringUtils.isEmpty(obj.getVungLuong())) {
			switch (obj.getVungLuong()) {
			case "1":
				obj.setVungLuong(V1);
				break;
			case "2":
				obj.setVungLuong(V2);
				break;
			case "3":
				obj.setVungLuong(V3);
				break;
			case "4":
				obj.setVungLuong(V4);
				break;
			default:
				break;
			}
		}
		if (!StringUtils.isEmpty(obj.getDiaHinh())) {
			switch (obj.getDiaHinh()) {
			case "1":
				obj.setDiaHinh(L1);
				break;
			case "2":
				obj.setDiaHinh(L2);
				break;
			case "3":
				obj.setDiaHinh(L3);
				break;
			default:
				break;
			}
		}

		List<TblDonGiaTramDTO> ls = tblDonGiaTramDAO.doSearchDonGiaTram(obj);
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

	public String importDonGia(String filePath,HttpServletRequest request) throws Exception {
		// public static void main(String[] args) throws IOException {
		long tStart = System.currentTimeMillis();
		TblDonGiaTramDTO donGiaTram = new TblDonGiaTramDTO();
		/**
		 * sửa đây
		 */
		ImportErrDTO importErrDTO = new ImportErrDTO();
		// List<ErrExcelDTO> lstErrorExcell;

		ErrExcelDTO errExcelDTO = new ErrExcelDTO();
		// lstErrorOrder = new ArrayList<>();
		List<TblDonGiaTramDTO> workLstDone = Lists.newArrayList();
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
			if (count >= 6 && !isRowEmpty(row)) {
				System.out.println(count);
				lstErrorExcell = new ArrayList();
				boolean checkLoaiTram = true;
				boolean checkVungLuong = true;
				boolean checkDiaHinh = true;
				boolean checkDonGia = true;
				boolean checkphanLoai = true;
				String loaiTram = "";
				String vungLuong = "";
				String diaHinh = "";
				String donGia = "";
				String ghiChu = "";
				String phanLoai = "";

				for (Cell cell : row) {
					switch (cell.getColumnIndex()) {
					case 1:
						loaiTram = dataFormatter.formatCellValue(cell);
						break;
					case 2:
						vungLuong = dataFormatter.formatCellValue(cell);
						// checkTenNV = testCheck(cell.getColumnIndex(),tenNV);
						break;
					case 3:
						diaHinh = dataFormatter.formatCellValue(cell);
						// checkDonVi = testCheck(cell.getColumnIndex(),donVi);
						break;
					case 4:
						donGia = dataFormatter.formatCellValue(cell);
						// checkChucDanh =
						// testCheck(cell.getColumnIndex(),chucDanh);
						break;
					case 5:
						ghiChu = dataFormatter.formatCellValue(cell);
						break;
					case 6:
						phanLoai = dataFormatter.formatCellValue(cell);
						break;

					default:
						break;
					}
				}
				checkLoaiTram = checkDonGiaTram(0, loaiTram, null, null);
				checkVungLuong = checkDonGiaTram(1, vungLuong, null, null);
				checkDiaHinh = checkDonGiaTram(2, diaHinh, loaiTram, vungLuong);
				checkDonGia = checkDonGiaTram(3, donGia, null, null);
				checkphanLoai = checkDonGiaTram(4, phanLoai, null, null);

				// DateFormat dt = new SimpleDateFormat("mm/yyyy");
				if (checkLoaiTram && checkVungLuong && checkDiaHinh && checkDonGia && checkphanLoai) {
					donGiaTram = new TblDonGiaTramDTO();
					switch (loaiTram) {
					case "T1":
						donGiaTram.setLoaiTram(T1);
						break;
					case "T2":
						donGiaTram.setLoaiTram(T2);
						break;
					case "T3":
						donGiaTram.setLoaiTram(T3);
						break;
					case "T4":
						donGiaTram.setLoaiTram(T4);
						break;
					default:

						break;
					}
					switch (vungLuong) {
					case "V1":
						donGiaTram.setVungLuong(V1);
						break;
					case "V2":
						donGiaTram.setVungLuong(V2);
						break;
					case "V3":
						donGiaTram.setVungLuong(V3);
						break;
					case "V4":
						donGiaTram.setVungLuong(V4);
						break;
					default:
						break;
					}
					switch (diaHinh) {
					case "L1":
						donGiaTram.setDiaHinh(L1);
						break;
					case "L2":
						donGiaTram.setDiaHinh(L2);
						break;
					case "L3":
						donGiaTram.setDiaHinh(L3);
						break;
					default:
						break;
					}

					// String vl1 = donGia.replace(",", "");
					// String vl2 = vl1.replace("$", "");
					donGiaTram.setDonGia(Float.parseFloat(donGia));
					donGiaTram.setGhiChu(ghiChu);
					donGiaTram.setPl(Long.parseLong(phanLoai));
					// delete
					tblDonGiaTramDAO.deleteObj(donGiaTram);
					workLstDone.add(donGiaTram);
				} else {
					importErrDTO = new ImportErrDTO();
					importErrDTO.setColumn1(loaiTram);
					importErrDTO.setColumn2(vungLuong);
					importErrDTO.setColumn3(diaHinh);
					importErrDTO.setColumn4(donGia);
					importErrDTO.setColumn5(ghiChu);
					importErrDTO.setColumn6(phanLoai);
					importErrDTO.setLstErrorOrder(lstErrorExcell);
					workLstFault.add(importErrDTO);
				}
				// lst.setName(maNV);
				// lst.setNumber(Integer.parseInt(donVi));
			}
		}

		workLstDone.toArray();
		workLstFault.toArray();

		if (workLstDone.size() <= 0 && workLstFault.size() <= 0) {
			throw new IllegalArgumentException("File import không có dữ liệu");
		} else {
			if (workLstDone.size() > 0) {
				saveList(workLstDone);
				long tEnd = System.currentTimeMillis();
				lsThaoTacDTO.setChucNang("Import dữ liệu đơn giá trạm");
				lsThaoTacDTO.setMoTa("Import "+workLstDone.size() +" bản ghi dữ liệu đơn giá trạm ");
				lsThaoTacDTO.setThoiGianThucHien(tEnd-tStart);
				businessImpl.insertLSTT(lsThaoTacDTO,request);
			}
			if (workLstFault.size() > 0) {
				return exportDonGiaTramErr(workLstFault);
			}
		}
		System.out.println(workLstDone.size());
		System.out.println(workLstFault.size());
		return "";
	}

	public static boolean contains(String[] arr, String item) {
		for (String n : arr) {
			if (item.equals(n)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * đợi xử lý
	 *
	 * @param index
	 * @param value
	 * @return
	 */
	public boolean checkDonGiaTram(int index, String value, String t1, String t2) throws ParseException {
		String errDetail = "";
		ErrExcelDTO err = new ErrExcelDTO();
		String[] T = { "T1", "T2", "T3", "T4" };
		String[] L = { "L1", "L2", "L3" };
		String[] V = { "V1", "V2", "V3", "V4" };

		switch (index) {
		case 0:
			if (value.isEmpty() || "".equals(value)) {
				err.setDetailError("Loại trạm đang để trống, ");
				lstErrorExcell.add(err);
				return false;
			} else if (contains(T, value)) {
				err.setDetailError("Loại trạm nhập vào không đúng, ");
				lstErrorExcell.add(err);
				return false;
			}
			break;
		case 1:
			/*
			 * kiểm tra tên trùng với mã nv ?????
			 */
			//
			if (value.isEmpty() || "".equals(value)) {
				err.setDetailError("Vùng lương đang để trống, ");
				lstErrorExcell.add(err);
				return false;
			} else if (contains(V, value)) {
				err.setDetailError("Vùng lương nhập vào không đúng, ");
				lstErrorExcell.add(err);
				return false;
			}
			break;
		case 2:
			if (value.isEmpty() || "".equals(value)) {
				err.setDetailError("Địa hình đang để trống, ");
				lstErrorExcell.add(err);
				return false;
			} else if (contains(L, value)) {
				err.setDetailError("Địa hình nhập vào không đúng, ");
				lstErrorExcell.add(err);
				return false;
			}

			break;
		case 3:
			if (StringUtils.isEmpty(value)) {
				err.setDetailError("Đơn giá đang để trống, ");
				lstErrorExcell.add(err);
				return false;
			}
			if (!ValidateUtils.isFloat(value)) {
				err.setDetailError("Đơn giá không đúng định dạng");
				lstErrorExcell.add(err);
				return false;
			} else if (Float.parseFloat(value) < 0) {
				err.setDetailError("Đơn giá phải là số thực không âm.");
				lstErrorExcell.add(err);
				return false;
			}
			break;
		case 4:
			if (StringUtils.isEmpty(value)) {
				err.setDetailError("Phân loại trạm đang để trống, ");
				lstErrorExcell.add(err);
				return false;
			} else if (!StringUtils.isNumeric(value)) {
				err.setDetailError("Phân loại trạm không đúng định dạng");
				lstErrorExcell.add(err);
				return false;
			} else if (Integer.parseInt(value) > Integer.parseInt("1")
					|| Integer.parseInt(value) < Integer.parseInt("0")) {
				err.setDetailError("Phân loại trạm phải là 0 hoặc 1");
				lstErrorExcell.add(err);
				return false;
			}
			break;

		default:
			break;
		}
		return true;
	}

	public String exportDonGiaTramErr(List<ImportErrDTO> obj) throws Exception {
		// TODO Auto-generated method stub
		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			// ClassLoader classloader =
			// Thread.currentThread().getContextClassLoader();
			InputStream fileIn = new BufferedInputStream(new FileInputStream(filePath + "DonGiaTramErr.xlsx"));
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
				XSSFCell cell0 = row.createCell(0);
				cell0.setCellValue(i + 1);
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

				for (int j = 0; j < obj.get(i).getLstErrorOrder().size(); j++) {
					err.append(obj.get(i).getLstErrorOrder().get(j).getDetailError());
					// System.out.println("err" + err);
				}
				System.out.println("err " + i + ":" + err);
				XSSFCell cell7 = row.createCell(7);
				cell7.setCellValue(err.toString());
				cell0.setCellStyle(style);
				cell1.setCellStyle(style);
				cell2.setCellStyle(style);
				cell3.setCellStyle(style);
				cell4.setCellStyle(style);
				cell5.setCellStyle(style);
				cell6.setCellStyle(style);
				cell7.setCellStyle(style);

			}
			fileIn.close();
			File out = new File(folder2Upload + File.separatorChar + "DonGiaTramErr" + startExportTime + ".xlsx");

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
		return UEncrypt.encryptFileUploadPath("DonGiaTramErr" + startExportTime + ".xlsx");

	}

	public String exportDonGiaTram(TblDonGiaTramDTO lst) throws Exception {
		List<TblDonGiaTramDTO> obj = tblDonGiaTramDAO.doSearchDonGiaTram(lst);
		// TODO Auto-generated method stub
		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream fileIn = new BufferedInputStream(new FileInputStream(filePath + "ExportDonGiaTram.xlsx"));
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
				cell1.setCellValue(obj.get(i).getLoaiTram());
				cell1.setCellStyle(style);

				XSSFCell cell2 = row.createCell(2);
				cell2.setCellValue(obj.get(i).getVungLuong());
				cell2.setCellStyle(style);

				XSSFCell cell3 = row.createCell(3);
				cell3.setCellValue(obj.get(i).getLoaiTram());
				cell3.setCellStyle(style);

				XSSFCell cell4 = row.createCell(4);
				cell4.setCellValue(obj.get(i).getDonGia());
				cell4.setCellStyle(style);

				XSSFCell cell5 = row.createCell(5);
				cell5.setCellValue(obj.get(i).getGhiChu());
				cell5.setCellStyle(style);

				XSSFCell cell6 = row.createCell(6);
				cell6.setCellValue(obj.get(i).getPl());
				cell6.setCellStyle(style);

			}
			File out = new File(folder2Upload + File.separatorChar + "ExportDonGiaTram" + startExportTime + ".xlsx");

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
		return UEncrypt.encryptFileUploadPath("ExportDonGiaTram" + startExportTime + ".xlsx");
	}
	public String downloadImportTemplate() throws Exception {
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		String filePath = classloader.getResource("../" + "doc-template").getPath();
		InputStream file = new BufferedInputStream(new FileInputStream(filePath + "ExportDonGiaTram.xlsx"));
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		
		
		file.close();
		File out = new File(folder2Upload + File.separatorChar + "ExportDonGiaTram.xlsx");
		
		FileOutputStream outFile = new FileOutputStream(out);
		workbook.write(outFile);
		workbook.close();
		outFile.close();
		
		String path = UEncrypt.encryptFileUploadPath("ExportDonGiaTram.xlsx");
		return path;
	}
}
