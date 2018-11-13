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
import com.viettel.qll.bo.TblPhatKhacBO;
import com.viettel.qll.dao.TblDanhMucDAO;
import com.viettel.qll.dao.TblNhanVienDAO;
import com.viettel.qll.dao.TblPhatKhacDAO;
import com.viettel.qll.dto.ErrExcelDTO;
import com.viettel.qll.dto.ImportErrDTO;
import com.viettel.qll.dto.LsThaoTacDTO;
import com.viettel.qll.dto.TblPhatKhacDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;
import com.viettel.utils.ValidateUtils;

import freemarker.core.ParseException;

@Service("tblPhatKhacBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TblPhatKhacBusinessImpl extends BaseFWBusinessImpl<TblPhatKhacDAO, TblPhatKhacDTO, TblPhatKhacBO>
		implements TblPhatKhacBusiness {
	protected final Logger log = Logger.getLogger(TblPhatKhacBusinessImpl.class);
	@Autowired
	private TblPhatKhacDAO tblPhatKhacDAO;
	private String fomat="MM/yyyy";
	@Autowired
	TblNhanVienDAO nhanVienDAO = new TblNhanVienDAO();
	@Autowired
	TblDanhMucDAO tblDanhmucDAO;
	@Value("${folder_upload2}")
	private String folder2Upload;

	LsThaoTacDTO lsThaoTacDTO = new LsThaoTacDTO();

	@Autowired
	TblQltsThucXuatTheoKyBusinessImpl businessImpl=new TblQltsThucXuatTheoKyBusinessImpl();
	
	
	List<ErrExcelDTO> lstErrorExcell;

	public TblPhatKhacBusinessImpl() {
		tModel = new TblPhatKhacBO();
		tDAO = tblPhatKhacDAO;
	}

	@Override
	public TblPhatKhacDAO gettDAO() {
		return tblPhatKhacDAO;
	}

	@Override
	public DataListDTO doSearchPhatCDT(TblPhatKhacDTO obj) {
		List<TblPhatKhacDTO> ls = tblPhatKhacDAO.doSearchPhatCDT(obj);
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

	public String importPhatKhac(String filePath,HttpServletRequest request) throws Exception {
		// public static void main(String[] args) throws IOException {
		long tStart = System.currentTimeMillis();
		TblPhatKhacDTO phatKhac = new TblPhatKhacDTO();
		// exportNVTram exportNVTram = new ExportNVTram();
		/**
		 * sửa đây
		 */
		ImportErrDTO importErrDTO = new ImportErrDTO();
		// List<ErrExcelDTO> lstErrorExcell;

		ErrExcelDTO errExcelDTO = new ErrExcelDTO();
		// lstErrorOrder = new ArrayList<>();
		List<TblPhatKhacDTO> workLstDone = Lists.newArrayList();
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
			if (count > 6 && !isRowEmpty(row)) {
				System.out.println(count);
				lstErrorExcell = new ArrayList();
				boolean checkThang = true;
				boolean checkKV = true;
				boolean checkMaTinh = true;
				boolean checkNVGiao = true;
				boolean checkMaNV = true;
				boolean checkMatram = true;
				// boolean checkDutCapDoLoiCQ = true;
				boolean checkPhatTT = true;
				boolean checkGianDoanTT = true;
				boolean checkVPDongAo = true;
				boolean checkLoiRaVaoNT = true;
				boolean checkGianLanXD = true;
				boolean checkUCTTChamDan = true;

				String thang = "";
				String kv = "";
				String maTinh = "";
				String nvGiao = "";
				String maNV = "";
				String maTram = "";
				// String dutCapDoLoiCQ = "";
				String phatTT = "";
				String gianDoanTT = "";
				String vpDongAo = "";
				String loiRaVaoNT = "";
				String gianLanXD = "";
				String uCTTChamDan = "";

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
						maTram = dataFormatter.formatCellValue(cell);
						break;
					// case 6:
					// dutCapDoLoiCQ = dataFormatter.formatCellValue(cell);
					// //checkDonVi = testCheck(cell.getColumnIndex(),donVi);
					// break;
					case 7:
						phatTT = dataFormatter.formatCellValue(cell);
						// checkChucDanh =
						// testCheck(cell.getColumnIndex(),chucDanh);
						break;
					case 8:
						gianDoanTT = dataFormatter.formatCellValue(cell);
						break;
					case 9:
						vpDongAo = dataFormatter.formatCellValue(cell);
						break;
					case 10:
						loiRaVaoNT = dataFormatter.formatCellValue(cell);
						break;
					case 11:
						gianLanXD = dataFormatter.formatCellValue(cell);
						break;
					case 12:
						uCTTChamDan = dataFormatter.formatCellValue(cell);
						break;

					default:
						break;
					}
				}
				checkThang = checkPhatKhac(0, thang, null, null, errExcelDTO);
				checkKV = checkPhatKhac(1, kv, null, null, errExcelDTO);
				checkMaTinh = checkPhatKhac(2, maTinh, null, null, errExcelDTO);
				checkNVGiao = checkPhatKhac(3, nvGiao, null, null, errExcelDTO);
				checkMaNV = checkPhatKhac(4, maNV, null, null, errExcelDTO);
				checkMatram = checkPhatKhac(5, maTram, maNV, thang, errExcelDTO);
				// checkDutCapDoLoiCQ = checkPhatKhac(6,
				// dutCapDoLoiCQ,null,null);
				checkPhatTT = checkPhatKhac(6, phatTT, null, null, errExcelDTO);
				checkGianDoanTT = checkPhatKhac(7, gianDoanTT, null, null, errExcelDTO);
				checkVPDongAo = checkPhatKhac(8, vpDongAo, null, null, errExcelDTO);
				checkLoiRaVaoNT = checkPhatKhac(9, loiRaVaoNT, null, null, errExcelDTO);
				checkGianLanXD = checkPhatKhac(10, gianLanXD, null, null, errExcelDTO);
				checkUCTTChamDan = checkPhatKhac(11, uCTTChamDan, null, null, errExcelDTO);

				DateFormat dt = new SimpleDateFormat("mm/yyyy");
				if (checkThang && checkKV && checkMaTinh && checkNVGiao && checkMaNV && checkMatram && checkPhatTT
						&& checkGianDoanTT && checkVPDongAo && checkLoiRaVaoNT && checkGianLanXD && checkUCTTChamDan) {
					phatKhac = new TblPhatKhacDTO();
					if (thang.length() == 6) {
						thang = "0" + thang;
					}
					phatKhac.setThang(thang);
					phatKhac.setKv(kv);
					phatKhac.setMaTinh(maTinh);
					phatKhac.setNhanVien(nvGiao);
					phatKhac.setMaNv(maNV);
					phatKhac.setMaTram(maTram);
					// String dutCapDoLoiCQ1=conVertNumber(dutCapDoLoiCQ);
					// phatKhac.setDutCapDoLoiChuQuan(Double.parseDouble(dutCapDoLoiCQ1));
					if (phatTT == null || "".equals(phatTT)) {
						phatKhac.setPhatTrucTiep(Float.parseFloat("0"));
					} else {
						// String phatTT1 = conVertNumber(phatTT);
						phatKhac.setPhatTrucTiep(Float.parseFloat(phatTT));
					}

					if (gianDoanTT == null || "".equals(gianDoanTT)) {
						phatKhac.setGianDoanThongTin(Float.parseFloat("0"));
					} else {
						// String gianDoanTT1 = conVertNumber(gianDoanTT);
						phatKhac.setGianDoanThongTin(Float.parseFloat(gianDoanTT));
					}

					if (vpDongAo == null || "".equals(vpDongAo)) {
						phatKhac.setViPhamDongAo(Float.parseFloat("0"));
					} else {
						// String vpDongAo1 = conVertNumber(vpDongAo);
						phatKhac.setViPhamDongAo(Float.parseFloat(vpDongAo));
					}

					if (loiRaVaoNT == null || "".equals(loiRaVaoNT)) {
						phatKhac.setLoiRaVaNhaTram(Float.parseFloat("0"));
					} else {
						// String loiRaVaoNT1 = conVertNumber(loiRaVaoNT);
						phatKhac.setLoiRaVaNhaTram(Float.parseFloat(loiRaVaoNT));
					}

					if (gianLanXD == null || "".equals(gianLanXD)) {
						phatKhac.setGianLanXangDau(Float.parseFloat("0"));
					} else {
						// String gianLanXD1 = conVertNumber(gianLanXD);
						phatKhac.setGianLanXangDau(Float.parseFloat(gianLanXD));
					}

					if (uCTTChamDan == null || "".equals(uCTTChamDan)) {
						phatKhac.setUcttChamDan(Float.parseFloat("0"));
					} else {
						// String uCTTChamDan1 = conVertNumber(uCTTChamDan);
						phatKhac.setUcttChamDan(Float.parseFloat(uCTTChamDan));
					}

					phatKhac.setXoa((long) 0);
					phatKhac.setHoatDong((long) 1);
					tblPhatKhacDAO.deleteObj(phatKhac.getMaTram(), phatKhac.getMaNv(), phatKhac.getThang());

					workLstDone.add(phatKhac);
				} else {
					importErrDTO = new ImportErrDTO();
					importErrDTO.setColumn1(thang);
					importErrDTO.setColumn2(kv);
					importErrDTO.setColumn3(maTinh);
					importErrDTO.setColumn4(nvGiao);
					importErrDTO.setColumn5(maNV);
					importErrDTO.setColumn6(maTram);
					importErrDTO.setColumn7(phatTT);
					importErrDTO.setColumn8(gianDoanTT);
					importErrDTO.setColumn9(vpDongAo);
					importErrDTO.setColumn10(loiRaVaoNT);
					importErrDTO.setColumn11(gianLanXD);
					importErrDTO.setColumn12(uCTTChamDan);
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
			lsThaoTacDTO.setChucNang("Import dữ liệu phạt khác ");
			lsThaoTacDTO.setMoTa("Import "+workLstDone.size()+" bản ghi dữ liệu phạt khác ");
			lsThaoTacDTO.setThoiGianThucHien(tEnd-tStart);
			businessImpl.insertLSTT(lsThaoTacDTO,request);
		}
		if (workLstFault.size() > 0) {
			return exportPhatKhacErr(workLstFault);
		}
		System.out.println(workLstDone.size());
		System.out.println(workLstFault.size());
		return "";
	}

	/**
	 * đợi xử lý
	 *
	 * @param index
	 * @param value
	 * @return
	 */
	public boolean checkPhatKhac(int index, String value, String t1, String t2, ErrExcelDTO errExcelDTO)
			throws ParseException {
		String errDetail = "";
		ErrExcelDTO err = new ErrExcelDTO();
		DateFormat fmt = new SimpleDateFormat("MM/yyyy");

		switch (index) {
		case 0:
			if (value.isEmpty() || "".equals(value)) {
				err.setDetailError("Tháng đang để trống, ");
				lstErrorExcell.add(err);
				return false;
			} else if (!ValidateUtils.isDate(value,fomat)) {
				err.setDetailError("Trường tháng không đúng định dạng, ");
				lstErrorExcell.add(err);
				return false;
			}
			break;
		case 1:
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
			} else if(tblDanhmucDAO.checkExistmatinhBymatinh(value)!=1l)
			{
				err.setDetailError("Mã tỉnh không chính xác, ");
				lstErrorExcell.add(err);
				return false;
			}

			break;
		case 3:

			if (value.isEmpty() || "".equals(value)) {
				err.setDetailError("Nhân viên nhận giao khoán đang để trống, ");
				lstErrorExcell.add(err);
				return false;
			}
			break;

		case 4:
			long countNv = nhanVienDAO.checkExistMaNvByMaNv(value);
			if (value.isEmpty() || "".equals(value)) {
				err.setDetailError("Mã nhân viên đang để trống, ");
				lstErrorExcell.add(err);
				return false;
			} else if (countNv == (long) 0) {
				err.setDetailError("Mã nhân viên không tồn tại, ");
				lstErrorExcell.add(err);
				return false;
			}

			break;
		case 5:

			if (value.isEmpty() || "".equals(value)) {
				err.setDetailError("Mã trạm đang để trống, ");
				lstErrorExcell.add(err);
				return false;
			}

			break;

		case 6:
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					err.setDetailError("Phạt trực tiếp đối với mỗi trạm không đạt chuẩn không đúng định dạng, ");
					lstErrorExcell.add(err);
					return false;
				}
			}

			break;
		case 7:
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					err.setDetailError("Phạt lỗi gián đoạn thông tin không đúng định dạng, ");
					lstErrorExcell.add(err);
					return false;
				} else if (Double.parseDouble(value) < 0) {
					err.setDetailError("Phạt lỗi gián đoạn thông tin phải là số thực không âm, ");
					lstErrorExcell.add(err);
					return false;
				}
			}

			break;
		case 8:
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					err.setDetailError("Vi phạm đóng ảo WO không đúng định dạng, ");
					lstErrorExcell.add(err);
					return false;
				} else if (Double.parseDouble(value) < 0) {
					err.setDetailError("Vi phạm đóng ảo WO  phải là số thực không âm, ");
					lstErrorExcell.add(err);
					return false;
				}
			}

			break;
		case 9:
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					err.setDetailError("Lỗi ra vào nhà trạm không đăng ký trên Vsmart không đúng định dạng, ");
					lstErrorExcell.add(err);
					return false;
				} else if (Double.parseDouble(value) < 0) {
					err.setDetailError("Lỗi ra vào nhà trạm không đăng ký trên Vsmart phải là số thực không âm, ");
					lstErrorExcell.add(err);
					return false;
				}
			}

			break;
		case 10:
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					err.setDetailError("Gian lận xăng dầu không đúng định dạng, ");
					lstErrorExcell.add(err);
					return false;
				} else if (Double.parseDouble(value) < 0) {
					err.setDetailError("Gian lận xăng dầu phải là số thực không âm, ");
					lstErrorExcell.add(err);
					return false;
				}
			}

			break;
		case 11:
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					err.setDetailError("ƯCTT chậm dẫn đến down trạm không đúng định dạng.");
					lstErrorExcell.add(err);
					return false;
				} else if (Double.parseDouble(value) < 0) {
					err.setDetailError("ƯCTT chậm dẫn đến down trạm phải là số thực không âm.");
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

	public String conVertNumber(String a) {
		String v1 = a.replace(",", "");
		String v12 = v1.replace("$", "");

		return v12;
	}

	public String exportPhatKhacErr(List<ImportErrDTO> obj) throws Exception {
		// TODO Auto-generated method stub
		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			// ClassLoader classloader =
			// Thread.currentThread().getContextClassLoader();
			InputStream fileIn = new BufferedInputStream(new FileInputStream(filePath + "PhatKhacErr.xlsx"));
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
				cell11.setCellValue(obj.get(i).getColumn12());

				for (int j = 0; j < obj.get(i).getLstErrorOrder().size(); j++) {
					err.append(obj.get(i).getLstErrorOrder().get(j).getDetailError());
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
				cell13.setCellStyle(style);

			}
			fileIn.close();

			File out = new File(folder2Upload + File.separatorChar + "PhatKhacErr" + startExportTime + ".xlsx");

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
		return UEncrypt.encryptFileUploadPath("PhatKhacErr" + startExportTime + ".xlsx");
	}

	public String exportExcelGrid(TblPhatKhacDTO lst) throws Exception {

		List<TblPhatKhacDTO> obj = tblPhatKhacDAO.doSearchPhatCDT(lst);

		// TODO Auto-generated method stub
		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream fileIn = new BufferedInputStream(new FileInputStream(filePath + "ExportPhatKhac.xlsx"));
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
				cell1.setCellValue(obj.get(i).getThang());

				XSSFCell cell2 = row.createCell(2);
				cell2.setCellValue(obj.get(i).getKv());

				XSSFCell cell3 = row.createCell(3);
				cell3.setCellValue(obj.get(i).getMaTinh());

				XSSFCell cell4 = row.createCell(4);
				cell4.setCellValue(obj.get(i).getNhanVien());

				XSSFCell cell5 = row.createCell(5);
				cell5.setCellValue(obj.get(i).getMaNv());

				XSSFCell cell6 = row.createCell(6);
				cell6.setCellValue(obj.get(i).getMaTram());

				XSSFCell cell7 = row.createCell(7);
				cell7.setCellValue(obj.get(i).getPhatTrucTiep());

				XSSFCell cell8 = row.createCell(8);
				cell8.setCellValue(obj.get(i).getGianDoanThongTin());

				XSSFCell cell9 = row.createCell(9);
				cell9.setCellValue(obj.get(i).getViPhamDongAo());

				XSSFCell cell10 = row.createCell(10);
				cell10.setCellValue(obj.get(i).getLoiRaVaNhaTram());

				XSSFCell cell11 = row.createCell(11);
				cell11.setCellValue(obj.get(i).getGianLanXangDau());

				XSSFCell cell12 = row.createCell(12);
				cell12.setCellValue(obj.get(i).getUcttChamDan());

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
			File out = new File(folder2Upload + File.separatorChar + "ExportPhatKhac" + startExportTime + ".xlsx");

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
		return UEncrypt.encryptFileUploadPath("ExportPhatKhac" + startExportTime + ".xlsx");
	}
	public String downloadImportTemplate() throws Exception {
		// TODO Auto-generated method stub
		
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		String filePath = classloader.getResource("../" + "doc-template").getPath();
		InputStream file = new BufferedInputStream(new FileInputStream(filePath + "ExportPhatKhac.xlsx"));
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		
		
		file.close();
		File out = new File(folder2Upload + File.separatorChar + "ExportPhatKhac.xlsx");
		
		FileOutputStream outFile = new FileOutputStream(out);
		workbook.write(outFile);
		workbook.close();
		outFile.close();
		
		String path = UEncrypt.encryptFileUploadPath("ExportPhatKhac.xlsx");
		return path;
	}

}
