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

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.viettel.ktts2.common.UEncrypt;
import com.viettel.qll.bo.TblNhanVienBO;
import com.viettel.qll.dao.TblNhanVienDAO;
import com.viettel.qll.dto.ErrExcelDTO;
import com.viettel.qll.dto.ImportErrDTO;
import com.viettel.qll.dto.LsThaoTacDTO;
import com.viettel.qll.dto.TblNhanVienDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;

import freemarker.core.ParseException;

@Service("tblNhanVienBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TblNhanVienBusinessImpl extends BaseFWBusinessImpl<TblNhanVienDAO, TblNhanVienDTO, TblNhanVienBO>
		implements TblNhanVienBusiness {

	@Autowired
	private TblNhanVienDAO tblNhanVienDAO;
	
	LsThaoTacDTO lsThaoTacDTO = new LsThaoTacDTO();

	@Autowired
	TblQltsThucXuatTheoKyBusinessImpl businessImpl=new TblQltsThucXuatTheoKyBusinessImpl();
	
	
	@Value("${folder_upload2}")
	private String folder2Upload;
	List<ErrExcelDTO> lstErrorExcell;
	static Logger log = LoggerFactory.getLogger(TblNhanVienBusinessImpl.class);

	public TblNhanVienBusinessImpl() {
		tModel = new TblNhanVienBO();
		tDAO = tblNhanVienDAO;
	}

	@Override
	public TblNhanVienDAO gettDAO() {
		return tblNhanVienDAO;
	}


	public DataListDTO getAllNhanVien() {
		List<TblNhanVienDTO> ls = tblNhanVienDAO.getAll();
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		return data;
	}
	
	public List<TblNhanVienDTO> getForAutoCompleteNV(TblNhanVienDTO obj ) {
		return tblNhanVienDAO.getForAutoCompleteNV(obj);
	}
	
	

	@Override
	public DataListDTO doSearchNhanVien(TblNhanVienDTO obj) {
		List<TblNhanVienDTO> ls = tblNhanVienDAO.doSearchNhanVien(obj);
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

	public String importNhanVien(String filePath,HttpServletRequest request) throws Exception {
		// public static void main(String[] args) throws IOException {
		long tStart = System.currentTimeMillis();
		
		TblNhanVienDTO tblNhanVien = new TblNhanVienDTO();
		/**
		 * sửa đây
		 */
		ImportErrDTO importErrDTO = new ImportErrDTO();
		// List<ErrExcelDTO> lstErrorExcell;

		ErrExcelDTO errExcelDTO = new ErrExcelDTO();
		// lstErrorOrder = new ArrayList<>();
		List<TblNhanVienDTO> workLstDone = Lists.newArrayList();
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
				ImportErrDTO err = new ImportErrDTO();
				boolean checkMaNV = true;
				boolean checkHoTen = true;
				boolean checkDonVi = true;
				boolean checkDMNT = true;
				boolean checkChucDanh = true;

				String maNv = "";
				String hoTen = "";
				String donVi = "";
				String DMNT = "";
				String chucDanh = "";

				for (Cell cell : row) {
					switch (cell.getColumnIndex()) {
					case 1:
						maNv = dataFormatter.formatCellValue(cell);
						break;
					case 2:
						hoTen = dataFormatter.formatCellValue(cell);
						// checkTenNV = testCheck(cell.getColumnIndex(),tenNV);
						break;
					case 3:
						donVi = dataFormatter.formatCellValue(cell);
						// checkDonVi = testCheck(cell.getColumnIndex(),donVi);
						break;
					case 4:
						DMNT = dataFormatter.formatCellValue(cell);
						// checkDonVi = testCheck(cell.getColumnIndex(),donVi);
						break;
					case 5:
						chucDanh = dataFormatter.formatCellValue(cell);
						// checkChucDanh =
						// testCheck(cell.getColumnIndex(),chucDanh);
						break;

					default:
						break;
					}
				}
				checkMaNV = checkDonGiaTram(0, maNv, null, null);
				checkHoTen = checkDonGiaTram(1, hoTen, null, null);
				checkDonVi = checkDonGiaTram(2, donVi, null, null);
				checkDMNT = checkDonGiaTram(3, DMNT, null, null);
				checkChucDanh = checkDonGiaTram(4, chucDanh, null, null);

				// DateFormat dt = new SimpleDateFormat("mm/yyyy");
				if (checkMaNV && checkHoTen && checkDMNT && checkChucDanh&&checkDonVi) {
					tblNhanVien = new TblNhanVienDTO();
					tblNhanVien.setMaNv(maNv);
					tblNhanVien.setHoVaTen(hoTen);
					tblNhanVien.setDonVi(donVi);
					if (DMNT.equals("1")) {
						tblNhanVien.setDayMayNhaTram((long) 1);
					} else {
						tblNhanVien.setDayMayNhaTram((long) 0);
					}
					if (tblNhanVien.getDayMayNhaTram().toString().equals("0")) {
						tblNhanVien.setChucDanh("Nhân viên nhà trạm");
					} else {
						tblNhanVien.setChucDanh("Nhân viên dây máy");
					}
					tblNhanVienDAO.deleteNhanVien(tblNhanVien);
					tblNhanVien.setXoa((long) 1);
					tblNhanVien.setHoatDong((long) 0);
					workLstDone.add(tblNhanVien);
				} else {
					importErrDTO = new ImportErrDTO();
					importErrDTO.setColumn1(maNv);
					importErrDTO.setColumn2(hoTen);
					importErrDTO.setColumn3(donVi);
					importErrDTO.setColumn4(DMNT);
					importErrDTO.setColumn5(chucDanh);
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
				lsThaoTacDTO.setChucNang("Import dữ liệu nhân viên");
				lsThaoTacDTO.setMoTa("Import "+workLstDone.size()+"bản ghi dữ liệu nhân viên ");
				lsThaoTacDTO.setThoiGianThucHien(tEnd-tStart);
				businessImpl.insertLSTT(lsThaoTacDTO,request);
			}
			if (workLstFault.size() > 0) {
				return exportNhanVienErr(workLstFault);
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
		DateFormat fmt = new SimpleDateFormat("MM/yyyy");
		String[] T = { "T1", "T2", "T3", "T4" };
		String[] L = { "L1", "L2", "L3" };
		String[] V = { "V1", "V2", "V3", "V4" };

		String[] DMNT = { "0", "1" };
		switch (index) {
		case 0:
			if (value.isEmpty() || "".equals(value)) {
				err.setDetailError("Mã nhân viên đang để trống, ");
				lstErrorExcell.add(err);
				return false;
			}
			// else if(lstNhanVien.size()>0){
			// err.setDetailError("Mã nhân viên đã tồn tại, ");
			// lstErrorOrder.add(err);
			// return false;
			// }
			else if (value.length() > 50) {
				err.setDetailError("Mã nhân viên không được quá 50 kí tự, ");
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
				err.setDetailError("Họ tên đang để trống, ");
				lstErrorExcell.add(err);
				return false;
			} else if (value.length() > 100) {
				err.setDetailError("Họ tên chỉ nhập 100 kí tự, ");
				lstErrorExcell.add(err);
				return false;
			}
			break;
		case 2:
			/*
			 * kiểm tra tên trùng với mã nv ?????
			 */
			//
			if (value.isEmpty() || "".equals(value)) {
				err.setDetailError("Đơn vị đang để trống, ");
				lstErrorExcell.add(err);
				return false;
			} else if (value.length() > 50) {
				err.setDetailError("Họ tên chỉ nhập 50 kí tự, ");
				lstErrorExcell.add(err);
				return false;
			}
			break;
		case 3:
			//
			if (value.isEmpty() || "".equals(value)) {
				err.setDetailError("Dây máy nhà trạm đang để trống, ");
				lstErrorExcell.add(err);
				return false;
			} else if (contains(DMNT, value)) {
				err.setDetailError("Dây máy nhà trạm phải nhập 0 hoặc 1 ");
				lstErrorExcell.add(err);
				return false;
			}

			break;
		case 4:
			if (value.isEmpty() || "".equals(value)) {
				err.setDetailError("Chức danh đang để trống, ");
				lstErrorExcell.add(err);
				return false;
			}

			break;
		default:
			break;
		}
		return true;
	}

	public String exportNhanVienErr(List<ImportErrDTO> obj) throws Exception {
		// TODO Auto-generated method stub
		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream fileIn = new BufferedInputStream(new FileInputStream(filePath + "nhanVienErr.xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(fileIn);
			XSSFSheet sheet = workbook.getSheetAt(0);
			XSSFCellStyle style = workbook.createCellStyle();
			style.setBorderBottom(BorderStyle.THIN);
			style.setBorderTop(BorderStyle.THIN);
			style.setBorderRight(BorderStyle.THIN);
			style.setBorderLeft(BorderStyle.THIN);
			style.setWrapText(true);
			int rownum =6;
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

				for (int j = 0; j < obj.get(i).getLstErrorOrder().size(); j++) {
					err.append(obj.get(i).getLstErrorOrder().get(j).getDetailError());
					// System.out.println("err" + err);
				}

				System.out.println("err " + i + ":" + err);
				XSSFCell cell6 = row.createCell(6);
				cell6.setCellValue(err.toString());

				cell0.setCellStyle(style);
				cell1.setCellStyle(style);
				cell2.setCellStyle(style);
				cell3.setCellStyle(style);
				cell4.setCellStyle(style);
				cell5.setCellStyle(style);
				cell6.setCellStyle(style);
			}
			File out = new File(folder2Upload + File.separatorChar + "nhanVienErr" + startExportTime + ".xlsx");

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
		return UEncrypt.encryptFileUploadPath("nhanVienErr" + startExportTime + ".xlsx");
	}

	public String exportExcelGrid(TblNhanVienDTO lst) throws Exception {

		List<TblNhanVienDTO> obj = tblNhanVienDAO.doSearchNhanVien(lst);
		// TODO Auto-generated method stub
		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream fileIn = new BufferedInputStream(new FileInputStream(filePath + "ExportNhanVien.xlsx"));
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
				cell1.setCellValue(obj.get(i).getMaNv());

				XSSFCell cell2 = row.createCell(2);
				cell2.setCellValue(obj.get(i).getHoVaTen());

				XSSFCell cell3 = row.createCell(3);
				cell3.setCellValue(obj.get(i).getDonVi());

				XSSFCell cell4 = row.createCell(4);
				cell4.setCellValue(obj.get(i).getDayMayNhaTram());

				XSSFCell cell5 = row.createCell(5);
				cell5.setCellValue(obj.get(i).getChucDanh());

				cell0.setCellStyle(style);
				cell1.setCellStyle(style);
				cell2.setCellStyle(style);
				cell3.setCellStyle(style);
				cell4.setCellStyle(style);
				cell5.setCellStyle(style);
			}
			File out = new File(folder2Upload + File.separatorChar + "ExportNhanVien" + startExportTime + ".xlsx");

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
		return UEncrypt.encryptFileUploadPath("ExportNhanVien" + startExportTime + ".xlsx");
	}

}
