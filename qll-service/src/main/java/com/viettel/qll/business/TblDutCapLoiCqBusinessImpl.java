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

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
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
import com.viettel.qll.bo.TblDutCapLoiCqBO;
import com.viettel.qll.dao.TblDanhMucDAO;
import com.viettel.qll.dao.TblDutCapLoiCqDAO;
import com.viettel.qll.dao.TblNhanVienDAO;
import com.viettel.qll.dto.ErrExcelDTO;
import com.viettel.qll.dto.ImportErrDTO;
import com.viettel.qll.dto.LsThaoTacDTO;
import com.viettel.qll.dto.TblDutCapLoiCqDTO;
import com.viettel.qll.dto.TblNhanVienDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;
import com.viettel.utils.ValidateUtils;

import freemarker.core.ParseException;

@Service("tblDutCapLoiCqBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TblDutCapLoiCqBusinessImpl extends
		BaseFWBusinessImpl<TblDutCapLoiCqDAO, TblDutCapLoiCqDTO, TblDutCapLoiCqBO> implements TblDutCapLoiCqBusiness {

	protected final Logger log = Logger.getLogger(TblDutCapLoiCqBusiness.class);
	@Autowired
	private TblDutCapLoiCqDAO tblDutCapLoiCqDAO;
	
	LsThaoTacDTO lsThaoTacDTO = new LsThaoTacDTO();

	@Autowired
	TblQltsThucXuatTheoKyBusinessImpl businessImpl=new TblQltsThucXuatTheoKyBusinessImpl();
	
	
private String fomat = "MM/yyyy";
	@Autowired
	private TblNhanVienDAO nhanVienDAO;
@Autowired 
private TblDanhMucDAO tblDanhmucDAO;
	@Value("${folder_upload2}")
	private String folder2Upload;

	List<ErrExcelDTO> lstErrorExcell;

	public TblDutCapLoiCqBusinessImpl() {
		tModel = new TblDutCapLoiCqBO();
		tDAO = tblDutCapLoiCqDAO;
	}

	@Override
	public TblDutCapLoiCqDAO gettDAO() {
		return tblDutCapLoiCqDAO;
	}

	@Override
	public DataListDTO doSearchDutCap(TblDutCapLoiCqDTO obj) {
		List<TblDutCapLoiCqDTO> ls = tblDutCapLoiCqDAO.doSearchDutCap(obj);
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

	public String importDutCapCQ(String filePath,HttpServletRequest request) throws Exception {
		// public static void main(String[] args) throws IOException {
		long tStart = System.currentTimeMillis();
		TblDutCapLoiCqDTO tblDutCapLoiCq = new TblDutCapLoiCqDTO();
		/**
		 * sửa đây
		 */
		ImportErrDTO importErrDTO = new ImportErrDTO();
		// List<ErrExcelDTO> lstErrorExcell;

		ErrExcelDTO errExcelDTO = new ErrExcelDTO();
		// lstErrorOrder = new ArrayList<>();
		List<TblDutCapLoiCqDTO> workLstDone = Lists.newArrayList();
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
				boolean checkThang = true;
				boolean checkKV = true;
				boolean checkMaTinh = true;
				boolean checkNVGiao = true;
				boolean checkMaNV = true;
				boolean checkSolanDut = true;
				boolean checkTienPhat = true;

				String thang = "";
				String kv = "";
				String maTinh = "";
				String nvGiao = "";
				String maNV = "";
				String solanDut = "";
				String tienPhat = "";

				for (Cell cell : row) {
					switch (cell.getColumnIndex()) {
					case 1:
						thang = dataFormatter.formatCellValue(cell);
						break;
					case 2:
						kv = dataFormatter.formatCellValue(cell);
						// checkTenNV = testCheck(cell.getColumnIndex(),tenNV);
						break;
					case 3:
						maTinh = dataFormatter.formatCellValue(cell);
						// checkDonVi = testCheck(cell.getColumnIndex(),donVi);
						break;
					case 4:
						nvGiao = dataFormatter.formatCellValue(cell);
						// checkChucDanh =
						// testCheck(cell.getColumnIndex(),chucDanh);
						break;
					case 5:
						maNV = dataFormatter.formatCellValue(cell);
						break;
					case 6:
						solanDut = dataFormatter.formatCellValue(cell);
						break;
					case 7:
						tienPhat = dataFormatter.formatCellValue(cell);
						// checkDonVi = testCheck(cell.getColumnIndex(),donVi);
						break;

					default:
						break;
					}
				}
				checkThang = checkPhatKhac(0, thang, maTinh, thang);
				checkKV = checkPhatKhac(1, kv, maTinh, thang);
				checkMaTinh = checkPhatKhac(2, maTinh, maTinh, thang);
				checkNVGiao = checkPhatKhac(3, nvGiao, maTinh, thang);
				checkMaNV = checkPhatKhac(4, maNV, maTinh, thang);
				checkSolanDut = checkPhatKhac(5, solanDut, maTinh, thang);
				checkTienPhat = checkPhatKhac(6, tienPhat, maTinh, thang);

				DateFormat dt = new SimpleDateFormat("MM/yyyy");
				if (checkThang && checkKV && checkMaTinh && checkNVGiao && checkMaNV && checkSolanDut
						&& checkTienPhat) {
					tblDutCapLoiCq = new TblDutCapLoiCqDTO();
					if (thang.length() == 6) {
						thang = "0" + thang;
					}
					tblDutCapLoiCq.setThang(thang);
					tblDutCapLoiCq.setKhuVuc(kv);
					tblDutCapLoiCq.setMaTinh(maTinh);
					tblDutCapLoiCq.setNvNhanGk(nvGiao);
					tblDutCapLoiCq.setMaNv(maNV);
					if (solanDut == null || "".equals(solanDut)) {
						tblDutCapLoiCq.setSoLanDut((long) (0));
					} else {
						tblDutCapLoiCq.setSoLanDut(Long.parseLong(solanDut));
					}

					if (tienPhat == null || "".equals(tienPhat)) {
						tblDutCapLoiCq.setTienPhat(Float.parseFloat("0"));
					} else {
						// String tienPhat1 = conVertNumber(tienPhat);
						tblDutCapLoiCq.setTienPhat(Float.parseFloat(tienPhat));
					}

					tblDutCapLoiCqDAO.deleteObj(tblDutCapLoiCq);
					// tblDutCapLoiCq.setXoa((long)0);
					// phatKhac.setHoatDong(true);
					workLstDone.add(tblDutCapLoiCq);
				} else {
					importErrDTO = new ImportErrDTO();
					importErrDTO.setColumn1(thang);
					importErrDTO.setColumn2(kv);
					importErrDTO.setColumn3(maTinh);
					importErrDTO.setColumn4(nvGiao);
					importErrDTO.setColumn5(maNV);
					importErrDTO.setColumn6(solanDut);
					importErrDTO.setColumn7(tienPhat);
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
				lsThaoTacDTO.setChucNang("Import dữ liệu phạt đứt cáp do lỗi chủ quan");
				lsThaoTacDTO.setMoTa("Import "+workLstDone.size() +" bản ghi dữ liệu phạt đứt cáp do lỗi chủ quan ");
				lsThaoTacDTO.setThoiGianThucHien(tEnd-tStart);
				businessImpl.insertLSTT(lsThaoTacDTO,request);
			}
			if (workLstFault.size() > 0) {
				return exportLoiCQErr(workLstFault);
			}
		}
		System.out.println(workLstDone.size());
		System.out.println(workLstFault.size());
		return "";
	}

	/**
	 *
	 * @param index
	 * @param value
	 * @param maTinh
	 * @param thang
	 * @return
	 * @throws ParseException
	 */
	public boolean checkPhatKhac(int index, String value, String maTinh, String thang) throws ParseException {
		String errDetail = "";
		// DateFormat fmt = new SimpleDateFormat("MM/yyyy");
		ErrExcelDTO err = new ErrExcelDTO();

		switch (index) {
		case 0:
			if (value.isEmpty()) {
				err.setDetailError("Trường tháng không được để trống");
				lstErrorExcell.add(err);
				return false;
			} else {
				if (!ValidateUtils.isDate(value,"MM/yyyy")) {
					err.setDetailError("Trường tháng không đúng định dạng");
					lstErrorExcell.add(err);
					return false;
				} else {
					int batdau, ketthuc;
					batdau = value.indexOf('/') + 1;
					ketthuc = batdau + value.substring(value.indexOf('/') + 1).indexOf('/');
					if(ketthuc>batdau)
					{
						String thangcv = value.substring(batdau, ketthuc);
						int chuyendoi = Integer.parseInt(thangcv);
						if (chuyendoi > 12 || chuyendoi < 1) {
							err.setDetailError("Tháng không đúng định dạng");
							lstErrorExcell.add(err);
							return false;
						}
					}
					else
					{
						String namcv = value.substring(batdau);
						String thangcv = value.substring(0,batdau-1);
						if(namcv.length()<4)
						{
							err.setDetailError("Năm không đúng định dạng");
							lstErrorExcell.add(err);
							return false;
						}
						int chuyendoi = Integer.parseInt(thangcv);
						if (chuyendoi > 12 || chuyendoi < 1) {
							err.setDetailError("Tháng không đúng định dạng");
							lstErrorExcell.add(err);
							return false;
						}
						
					}
					
				}
			}
			break;
		case 1:
			/*
			 * kiểm tra tên trùng với mã nv ?????
			 */
			if (value.isEmpty() || "".equals(value)) {
				err.setDetailError("Khu vực đang để trống, ");
				lstErrorExcell.add(err);
				return false;
			}
			break;
		case 2:
			if (value.isEmpty() || "".equals(value)) {
				err.setDetailError("Mã tỉnh đang để trống, ");
				lstErrorExcell.add(err);
				return false;
			}else if(tblDanhmucDAO.checkExistmatinhBymatinh(value)!=1l)
			{
				err.setDetailError("Mã tỉnh không chính xác, ");
				lstErrorExcell.add(err);
				return false;
			}

			break;
		case 3:
			// existsMaNV
			// List<TblNhanVien> lstNV =
			// nVTramDAO.checkExistMaNvByMaNv(value.trim());

			if (value.isEmpty() || "".equals(value)) {
				err.setDetailError("Nhân viên nhận giao khoán đang để trống, ");
				lstErrorExcell.add(err);
				return false;
			}
			break;

		case 4:
			List<TblNhanVienDTO> countNv = nhanVienDAO.checkNV(value);

			if (value.isEmpty() || "".equals(value)) {
				err.setDetailError("Mã nhân viên đang để trống, ");
				lstErrorExcell.add(err);
				return false;
			} else if (countNv.size() == 0) {
				err.setDetailError("Mã nhân viên không tồn tại, ");
				lstErrorExcell.add(err);
				return false;
			}

			break;
		case 5:
			// if (value.isEmpty() || "".equals(value)) {
			// err.setDetailError("Số lần đứt đang để trống");
			// lstErrorOrder.add(err);
			// return false;
			// } else
			if (!value.isEmpty()) {
				if (!StringUtils.isNumeric(value) || Integer.parseInt(value) < 0) {
					err.setDetailError("Số lần đứt phải là số nguyên dương, ");
					lstErrorExcell.add(err);
					return false;
				}
			}

			break;

		case 6:
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					err.setDetailError("Tiền phạt không đúng định dạng.");
					lstErrorExcell.add(err);
					return false;
				}
			}

			break;

		default:
			break;
		}
		return true;
	}

	public String exportLoiCQErr(List<ImportErrDTO> obj) throws Exception {
		// TODO Auto-generated method stub
		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream fileIn = new BufferedInputStream(new FileInputStream(filePath + "DutCapErr.xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(fileIn);
			XSSFSheet sheet = workbook.getSheetAt(0);
			XSSFCellStyle style = workbook.createCellStyle();
			style.setBorderBottom(BorderStyle.THIN);
			style.setBorderTop(BorderStyle.THIN);
			style.setBorderRight(BorderStyle.THIN);
			style.setBorderLeft(BorderStyle.THIN);
			style.setWrapText(true);
			int rownum = 5;
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

				XSSFCell cell7 = row.createCell(7);
				cell7.setCellValue(obj.get(i).getColumn7());

				for (int j = 0; j < obj.get(i).getLstErrorOrder().size(); j++) {
					err.append(obj.get(i).getLstErrorOrder().get(j).getDetailError());
					// System.out.println("err" + err);
				}

				System.out.println("err " + i + ":" + err);
				XSSFCell cell8 = row.createCell(8);
				cell8.setCellValue(err.toString());

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
			File out = new File(folder2Upload + File.separatorChar + "DutCapErr" + startExportTime + ".xlsx");

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
		return UEncrypt.encryptFileUploadPath("DutCapErr" + startExportTime + ".xlsx");
	}
	
	public String exportExcelGrid(TblDutCapLoiCqDTO lst) throws Exception {
		
		List<TblDutCapLoiCqDTO> obj=tblDutCapLoiCqDAO.doSearchDutCap(lst);
		// TODO Auto-generated method stub
		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream fileIn = new BufferedInputStream(new FileInputStream(filePath + "ExportDutCap.xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(fileIn);
			XSSFSheet sheet = workbook.getSheetAt(0);
			XSSFCellStyle style = workbook.createCellStyle();
			style.setBorderBottom(BorderStyle.THIN);
			style.setBorderTop(BorderStyle.THIN);
			style.setBorderRight(BorderStyle.THIN);
			style.setBorderLeft(BorderStyle.THIN);
			style.setWrapText(true);
			int rownum = 5;
			for (int i = 0; i < obj.size(); i++) {
				StringBuilder err = new StringBuilder();
				XSSFRow row = sheet.createRow(rownum);

				XSSFCell cell0 = row.createCell(0);
				cell0.setCellValue(i + 1);
				rownum++;

				XSSFCell cell1 = row.createCell(1);
				cell1.setCellValue(obj.get(i).getThang());

				XSSFCell cell2 = row.createCell(2);
				cell2.setCellValue(obj.get(i).getKhuVuc());

				XSSFCell cell3 = row.createCell(3);
				cell3.setCellValue(obj.get(i).getMaTinh());

				XSSFCell cell4 = row.createCell(4);
				cell4.setCellValue(obj.get(i).getNvNhanGk());

				XSSFCell cell5 = row.createCell(5);
				cell5.setCellValue(obj.get(i).getMaNv());

				XSSFCell cell6 = row.createCell(6);
				cell6.setCellValue(obj.get(i).getSoLanDut());

				XSSFCell cell7 = row.createCell(7);
				cell7.setCellValue(obj.get(i).getTienPhat());

				cell0.setCellStyle(style);
				cell1.setCellStyle(style);
				cell2.setCellStyle(style);
				cell3.setCellStyle(style);
				cell4.setCellStyle(style);
				cell5.setCellStyle(style);
				cell6.setCellStyle(style);
				cell7.setCellStyle(style);
			}
			File out = new File(folder2Upload + File.separatorChar + "ExportDutCap" + startExportTime + ".xlsx");

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
		return UEncrypt.encryptFileUploadPath("ExportDutCap" + startExportTime + ".xlsx");
	}
	
	public String downloadImportTemplate() throws Exception {
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		String filePath = classloader.getResource("../" + "doc-template").getPath();
		InputStream file = new BufferedInputStream(new FileInputStream(filePath + "ExportDutCap.xlsx"));
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		
		
		file.close();
		File out = new File(folder2Upload + File.separatorChar + "ExportDutCap.xlsx");
		
		FileOutputStream outFile = new FileOutputStream(out);
		workbook.write(outFile);
		workbook.close();
		outFile.close();
		
		String path = UEncrypt.encryptFileUploadPath("ExportDutCap.xlsx");
		return path;
	}

}
