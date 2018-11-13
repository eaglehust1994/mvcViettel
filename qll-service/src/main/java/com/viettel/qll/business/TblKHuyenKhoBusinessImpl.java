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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.viettel.ktts2.common.UEncrypt;
import com.viettel.qll.bo.TblKHuyenKhoBO;
import com.viettel.qll.dao.TblKHuyenKhoDAO;
import com.viettel.qll.dao.TblNhanVienDAO;
import com.viettel.qll.dto.ErrExcelDTO;
import com.viettel.qll.dto.ImportErrDTO;
import com.viettel.qll.dto.LsThaoTacDTO;
import com.viettel.qll.dto.QllBaseDTO;
import com.viettel.qll.dto.TblKHuyenKhoDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;
import com.viettel.utils.ValidateUtils;

@Service("tblKHuyenKhoBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TblKHuyenKhoBusinessImpl extends BaseFWBusinessImpl<TblKHuyenKhoDAO, TblKHuyenKhoDTO, TblKHuyenKhoBO>
		implements TblKHuyenKhoBusiness {
	static Logger log = LoggerFactory.getLogger(TblKHuyenKhoBusiness.class);
private String fomat="MM/yyyy";
	@Autowired
	private TblKHuyenKhoDAO tblKHuyenKhoDAO;

	LsThaoTacDTO lsThaoTacDTO = new LsThaoTacDTO();

	@Autowired
	TblQltsThucXuatTheoKyBusinessImpl businessImpl=new TblQltsThucXuatTheoKyBusinessImpl();
	
	
	@Autowired
	TblNhanVienDAO tblNhanVienDAO;
	
	@Value("${folder_upload2}")
	private String folder2Upload;

	List<ErrExcelDTO> lstErrorExcell;

	public TblKHuyenKhoBusinessImpl() {
		tModel = new TblKHuyenKhoBO();
		tDAO = tblKHuyenKhoDAO;
	}

	@Override
	public DataListDTO doSearchKHuyenKho(TblKHuyenKhoDTO obj, HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		String err = "";

		List<TblKHuyenKhoDTO> ls = tblKHuyenKhoDAO.doSearchKHuyenKho(obj);
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

	public String importkHuyenKho(String filePath,HttpServletRequest request) throws Exception {
		// public static void main(String[] args) throws IOException {
		long tStart = System.currentTimeMillis();
		TblKHuyenKhoDTO lst = new TblKHuyenKhoDTO();
		// KIDonViDTO lstFault = new KIDonViDTO();

		ImportErrDTO importErrDTO = new ImportErrDTO();
		// List<ErrExcelDTO> lstErrorExcell;

		ErrExcelDTO errExcelDTO = new ErrExcelDTO();
		// lstErrorOrder = new ArrayList<>();
		List<TblKHuyenKhoDTO> workLstDone = Lists.newArrayList();
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
				boolean checkTinh = true;
				boolean checkMaDonVi = true;
				boolean checkTenDonVi = true;
				boolean checkNhomDoiTuong = true;
				boolean checkDeXuatLoaiHuyen = true;
				boolean checkKKhoKhan = true;
				String thang = "";
				String tinh = "";
				String maDonVi = "";
				String tenDonVi = "";
				String nhomDoiTuong = "";
				String deXuatLoaiHuyen = "";
				String kKhoKhan = "";
				for (Cell cell : row) {
					if (cell.getColumnIndex() == 1) {
						thang = dataFormatter.formatCellValue(cell);

					} else if (cell.getColumnIndex() == 2) {
						tinh = dataFormatter.formatCellValue(cell);
					} else if (cell.getColumnIndex() == 3) {
						maDonVi = dataFormatter.formatCellValue(cell);
					} else if (cell.getColumnIndex() == 4) {
						tenDonVi = dataFormatter.formatCellValue(cell);
					} else if (cell.getColumnIndex() == 5) {
						nhomDoiTuong = dataFormatter.formatCellValue(cell);
					} else if (cell.getColumnIndex() == 6) {
						deXuatLoaiHuyen = dataFormatter.formatCellValue(cell);
					} else if (cell.getColumnIndex() == 7) {
						kKhoKhan = dataFormatter.formatCellValue(cell);
					}
				}
				checkThang = checkKKhoKhan(1, thang);
				checkTinh = checkKKhoKhan(2, tinh);
				checkMaDonVi = checkKKhoKhan(3, maDonVi);
				checkTenDonVi = checkKKhoKhan(4, tenDonVi);
				checkNhomDoiTuong = checkKKhoKhan(5, nhomDoiTuong);
				checkDeXuatLoaiHuyen = checkKKhoKhan(6, deXuatLoaiHuyen);
				checkKKhoKhan = checkKKhoKhan(7, kKhoKhan);
				if (checkThang && checkTinh && checkMaDonVi && checkTenDonVi && checkNhomDoiTuong
						&& checkDeXuatLoaiHuyen && checkKKhoKhan) {

					lst = new TblKHuyenKhoDTO();
					lst.setThang(thang);
					lst.setTinh(tinh);
					lst.setMaDonVi(maDonVi);
					lst.setTenDonVi(tenDonVi);
					lst.setNhomDoiTuong(Long.parseLong(nhomDoiTuong));
					lst.setDeXuatLoaiHuyen(deXuatLoaiHuyen);
					lst.setKKhoKhan(Float.parseFloat(kKhoKhan));
					lst.setXoa((long) 0);
					lst.setHoatDong((long) 1);
					tblKHuyenKhoDAO.deleteObj(lst);
					workLstDone.add(lst);
//					save(lst);
				} else {
					importErrDTO = new ImportErrDTO();
					importErrDTO.setColumn1(thang);
					importErrDTO.setColumn2(tinh);
					importErrDTO.setColumn3(maDonVi);
					importErrDTO.setColumn4(tenDonVi);
					importErrDTO.setColumn5(nhomDoiTuong);
					importErrDTO.setColumn6(deXuatLoaiHuyen);
					importErrDTO.setColumn7(kKhoKhan);
					importErrDTO.setLstErrorOrder(lstErrorExcell);
					workLstFault.add(importErrDTO);
				}
			}
		}

		System.out.println("size done: " + workLstDone.size());
		System.out.println("size: " + workLstFault.size());
		// workLstDone.toArray();
		if (sheet.getLastRowNum() < 7) {
			throw new IllegalArgumentException("File import không có dữ liệu");
		} else {
			try {
				if (workLstDone.size() > 0) {
					saveList1(workLstDone);

					long tEnd = System.currentTimeMillis();
					lsThaoTacDTO.setChucNang("Import dữ liệu kdc huyện khó");
					lsThaoTacDTO.setMoTa("Import "+workLstDone.size() +" bản ghi dữ liệu kdc huyện khó ");
					lsThaoTacDTO.setThoiGianThucHien(tEnd-tStart);
					businessImpl.insertLSTT(lsThaoTacDTO,request);
//					tblKHuyenKhoDAO.saveList1(workLstDone);
//					tblKHuyenKhoDAO.insertObj(workLstDone);
//					 saveList(workLstDone);
				}
			} catch (NullPointerException e) {
				e.printStackTrace();
			}

			if (workLstFault.size() > 0) {
				return exportKKhoKhanErr(workLstFault);
			}
		}
		// System.out.println(workLstDone.toString().toString());
		return "";
	}

	public boolean checkKKhoKhan(int index, String value) {
		String errDetail = "";
		String[] lstDeXuat = { "0", "Loại 1", "Loại 2", "Loại 3", "Loại 4", "Loại 5" };
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
			if (value.isEmpty()) {
				err.setDetailError("Tỉnh không được để trống");
				lstErrorExcell.add(err);
				return false;
			} else if (value.length() > 100) {
				err.setDetailError("Tỉnh chỉ được phép nhập 50 kí tự");
				lstErrorExcell.add(err);
				return false;
			}
		} else if (index == 3) {
			if (value.isEmpty()) {
				err.setDetailError("Mã đơn vị không được để trống");
				lstErrorExcell.add(err);
				return false;
			}
		} else if (index == 4) {
			if (value.isEmpty()) {
				err.setDetailError("Tên đơn vị không được để trống");
				lstErrorExcell.add(err);
				return false;
			}
		} else if (index == 5) {
			if (value.isEmpty()) {
				err.setDetailError("Nhóm đối tượng không được để trống");
				lstErrorExcell.add(err);
				return false;
			} else if (!StringUtils.isNumeric(value)) {
				err.setDetailError("Nhóm đối tượng không đúng định dạng");
				lstErrorExcell.add(err);
				return false;
			} else if (Integer.parseInt(value) > 1 || Integer.parseInt(value) < 0) {
				err.setDetailError("Nhóm đối tượng phải nhập 0 hoặc 1");
				lstErrorExcell.add(err);
				return false;
			}
		} else if (index == 6) {
			if (value.isEmpty()) {
				err.setDetailError("Đề xuất loại huyện không được để trống");
				lstErrorExcell.add(err);
				return false;
			} else if (contains(lstDeXuat, value)) {
				err.setDetailError("Đề xuất loại huyện không nằm trong danh sách");
				lstErrorExcell.add(err);
				return false;
			}
		} else if (index == 7) {
			if (value.isEmpty()) {
				err.setDetailError("K khó khăn không được để trống");
				lstErrorExcell.add(err);
				return false;
			} else if (!ValidateUtils.isFloat(value)) {
				err.setDetailError("K khó khăn không đúng định dạng");
				lstErrorExcell.add(err);
				return false;
			}
		}

		return true;
	}

	public String exportKKhoKhanErr(List<ImportErrDTO> obj) throws Exception {
		// TODO Auto-generated method stub
		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream fileIn = new BufferedInputStream(new FileInputStream(filePath + "kHuyenKhoErr.xlsx"));
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

				XSSFCell cell7 = row.createCell(7);
				cell7.setCellValue(obj.get(i).getColumn7());
				cell7.setCellStyle(style);

				for (int j = 0; j < obj.get(i).getLstErrorOrder().size(); j++) {
					err.append(obj.get(i).getLstErrorOrder().get(j).getDetailError() + "-");
					// System.out.println("err" + err);
				}
				// System.out.println("err " + i + ":" + err);

				XSSFCell cell8 = row.createCell(8);
				cell8.setCellValue(err.toString());
				cell8.setCellStyle(style);
			}
			File out = new File(folder2Upload + File.separatorChar + "kHuyenKhoErr" + startExportTime + ".xlsx");

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
		return UEncrypt.encryptFileUploadPath("kHuyenKhoErr" + startExportTime + ".xlsx");
	}

	public String exportKIDonVi(TblKHuyenKhoDTO lst) throws Exception {
		List<TblKHuyenKhoDTO> obj = tblKHuyenKhoDAO.doSearchKHuyenKho(lst);
		// TODO Auto-generated method stub
		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream fileIn = new BufferedInputStream(new FileInputStream(filePath + "ExportKHuyenKho.xlsx"));
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
				cell2.setCellValue(obj.get(i).getTinh());
				cell2.setCellStyle(style);

				XSSFCell cell3 = row.createCell(3);
				cell3.setCellValue(obj.get(i).getMaDonVi());
				cell3.setCellStyle(style);

				XSSFCell cell4 = row.createCell(4);
				cell4.setCellValue(obj.get(i).getTenDonVi());
				cell4.setCellStyle(style);

				XSSFCell cell5 = row.createCell(5);
				if ((long) obj.get(i).getNhomDoiTuong() == (long) 1) {
					cell5.setCellValue("Nhóm dây máy");
				} else {
					cell5.setCellValue("Nhóm nhà trạm");
				}
				cell5.setCellStyle(style);

				XSSFCell cell6 = row.createCell(6);
				cell6.setCellValue(obj.get(i).getDeXuatLoaiHuyen());
				cell6.setCellStyle(style);

				XSSFCell cell7 = row.createCell(7);
				cell7.setCellValue(obj.get(i).getKKhoKhan());
				cell7.setCellStyle(style);

			}
			File out = new File(folder2Upload + File.separatorChar + "ExportKHuyenKho" + startExportTime + ".xlsx");

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
		return UEncrypt.encryptFileUploadPath("ExportKHuyenKho" + startExportTime + ".xlsx");
	}
	
	@SuppressWarnings("unchecked")
	public String saveList1(List<TblKHuyenKhoDTO> lstCenterBO)
	  {
		@SuppressWarnings("rawtypes")
		List lstModel = new ArrayList<>();
	    if (lstCenterBO != null) {
	      for (int i = 0; i < lstCenterBO.size(); i++) {
	        lstModel.add(((QllBaseDTO<TblKHuyenKhoBO>)lstCenterBO.get(i)).toModel());
	      }
	    }
	    return tblKHuyenKhoDAO.saveList1(lstModel);
	  }
}
