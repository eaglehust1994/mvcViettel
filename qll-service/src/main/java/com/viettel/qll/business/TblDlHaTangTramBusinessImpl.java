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
import com.viettel.qll.bo.TblDlHaTangTramBO;
import com.viettel.qll.dao.TblDanhMucDAO;
import com.viettel.qll.dao.TblDlHaTangTramDAO;
import com.viettel.qll.dao.TblNhanVienDAO;
import com.viettel.qll.dto.ErrExcelDTO;
import com.viettel.qll.dto.ImportErrDTO;
import com.viettel.qll.dto.LsThaoTacDTO;
import com.viettel.qll.dto.TblDlHaTangTramDTO;
import com.viettel.qll.dto.TblNhanVienDTO;
import com.viettel.qll.dto.TblPhatKhacDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;
import com.viettel.utils.ValidateUtils;

import freemarker.core.ParseException;

@Service("tblDlHaTangTramBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TblDlHaTangTramBusinessImpl
		extends BaseFWBusinessImpl<TblDlHaTangTramDAO, TblDlHaTangTramDTO, TblDlHaTangTramBO>
		implements TblDlHaTangTramBusiness {
	protected final Logger log = Logger.getLogger(TblDlHaTangTramBusiness.class);
	@Autowired
	private TblDlHaTangTramDAO tblDlHaTangTramDAO;
	@Autowired
	private TblDanhMucDAO tblDanhmucDAO;
	private String fomat= "MM/yyyy";
	
	LsThaoTacDTO lsThaoTacDTO = new LsThaoTacDTO();

	@Autowired
	TblQltsThucXuatTheoKyBusinessImpl businessImpl=new TblQltsThucXuatTheoKyBusinessImpl();
	@Autowired
	TblNhanVienDAO tblNhanVienDAO;
	@Value("${folder_upload2}")
	private String folder2Upload;

	List<ErrExcelDTO> lstErrorExcell;

	public TblDlHaTangTramBusinessImpl() {
		tModel = new TblDlHaTangTramBO();
		tDAO = tblDlHaTangTramDAO;
	}

	@Override
	public TblDlHaTangTramDAO gettDAO() {
		return tblDlHaTangTramDAO;
	}

	@Override
	public List<TblDlHaTangTramDTO> getAllDLHaTang() {
		// TODO Auto-generated method stub
		return tblDlHaTangTramDAO.getAllDLHaTang();
	}

	// @Override
	// public List<TblDlHaTangTramDTO> doSearchDLHaTang(TblDlHaTangTramDTO obj)
	// {
	// // TODO Auto-generated method stub
	// return tblDlHaTangTramDAO.doSearchDLHaTang(obj);
	// }

	@Override
	public DataListDTO doSearchDLHaTang(TblDlHaTangTramDTO obj) {
		List<TblDlHaTangTramDTO> ls = tblDlHaTangTramDAO.doSearchDLHaTang(obj);
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

	/**
	 *
	 * @param arr
	 * @param item
	 * @return
	 */
	public static boolean contains(String[] arr, String item) {
		for (String n : arr) {
			if (item.equals(n)) {
				return false;
			}
		}
		return true;
	}

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

		public String importDLHaTangtram(String filePath, HttpServletRequest request) throws Exception {
			// public static void main(String[] args) throws IOException {
			long tStart = System.currentTimeMillis();
			
			TblDlHaTangTramDTO dlHaTangTram = new TblDlHaTangTramDTO();
			// ExportNVTram exportHaTangTram = new ExportNVTram();
			/**
			 * sửa đây
			 */
			ImportErrDTO importErrDTO = new ImportErrDTO();
			// List<ErrExcelDTO> lstErrorExcell;

			ErrExcelDTO errExcelDTO = new ErrExcelDTO();
			// lstErrorOrder = new ArrayList<>();
			List<TblDlHaTangTramDTO> workLstDone = Lists.newArrayList();
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
					boolean checkMaNV = true;
					boolean checkMaTinh = true;
					boolean checkHuyen = true;
					boolean checkmaTram = true;
					boolean checkLoaiDiaHinh = true;
					boolean checkPhanLoaiTram = true;
					boolean checkVung = true;
					boolean checkSoNgayQL = true;
					boolean checkSoNgayTrongThang = true;
					boolean checkNgayNVTL = true;
					boolean checkNgayNCCD = true;

					String thang = "";
					String khuVuc = "";
					String maNV = "";
					String maTinh = "";
					String huyen = "";
					String maTram = "";
					String loaiDiaHinh = "";
					String phanLoaiTram = "";
					String vung = "";
					String soNgayQL = "";
					String soNgayTrongThang = "";
					String soNgayNCTL = "";
					String soNgayNCCD = "";
					for (Cell cell : row) {
						switch (cell.getColumnIndex()) {
						case 1:
							thang = dataFormatter.formatCellValue(cell);
							break;
						case 2:
							khuVuc = dataFormatter.formatCellValue(cell);
							// checkTenNV =
							// testCheck(cell.getColumnIndex(),tenNV);
							break;
						case 3:
							maTinh = dataFormatter.formatCellValue(cell);
							// checkDonVi =
							// testCheck(cell.getColumnIndex(),donVi);
							break;
						case 4:
							huyen = dataFormatter.formatCellValue(cell);
							// checkChucDanh =
							// testCheck(cell.getColumnIndex(),chucDanh);
							break;
						case 5:
							maTram = dataFormatter.formatCellValue(cell);
							break;
						case 6:
							loaiDiaHinh = dataFormatter.formatCellValue(cell);
							// checkChucDanh =
							// testCheck(cell.getColumnIndex(),chucDanh);
							break;
						case 7:
							phanLoaiTram = dataFormatter.formatCellValue(cell);
							// checkChucDanh =
							// testCheck(cell.getColumnIndex(),chucDanh);
							break;
						case 8:
							vung = dataFormatter.formatCellValue(cell);
							// checkChucDanh =
							// testCheck(cell.getColumnIndex(),chucDanh);
							break;
						case 9:
							maNV = dataFormatter.formatCellValue(cell);
							// checkChucDanh =
							// testCheck(cell.getColumnIndex(),chucDanh);
							break;
						case 10:
							soNgayNCTL = dataFormatter.formatCellValue(cell);
							// checkChucDanh =
							// testCheck(cell.getColumnIndex(),chucDanh);
							break;
						case 11:
							soNgayNCCD = dataFormatter.formatCellValue(cell);
							// checkChucDanh =
							// testCheck(cell.getColumnIndex(),chucDanh);
							break;
						case 12:
							soNgayQL = dataFormatter.formatCellValue(cell);
							// checkChucDanh =
							// testCheck(cell.getColumnIndex(),chucDanh);
							break;
						case 13:
							soNgayTrongThang = dataFormatter.formatCellValue(cell);
							// checkChucDanh =
							// testCheck(cell.getColumnIndex(),chucDanh);
							break;
						default:
							break;
						}
					}
					// if (index == 4) {
					checkThang = checkDLHaTangTram(0, thang, null, null);
					checkKV = checkDLHaTangTram(1, khuVuc, null, null);
					checkMaTinh = checkDLHaTangTram(2, maTinh, null, null);
					checkHuyen = checkDLHaTangTram(3, huyen, maTinh, null);
					checkmaTram = checkDLHaTangTram(4, maTram, null, null);
					checkLoaiDiaHinh = checkDLHaTangTram(5, loaiDiaHinh, null, null);
					checkPhanLoaiTram = checkDLHaTangTram(6, phanLoaiTram, null, null);
					checkVung = checkDLHaTangTram(7, vung, null, null);
					checkMaNV = checkDLHaTangTram(8, maNV, maTram, thang);
					checkNgayNVTL = checkDLHaTangTram(9, soNgayNCTL, null, null);
					checkNgayNCCD = checkDLHaTangTram(10, soNgayNCCD, null, null);
					checkSoNgayQL = checkDLHaTangTram(11, soNgayQL, null, null);
					checkSoNgayTrongThang = checkDLHaTangTram(12, soNgayTrongThang, null, null);

					// } else if (index == 5) {
					// System.out.println("fck you t.a");
					// }
					DateFormat dt = new SimpleDateFormat("MM/yyyy");
					if (checkThang && checkKV && checkMaTinh && checkHuyen && checkmaTram && checkLoaiDiaHinh
							&& checkPhanLoaiTram && checkVung && checkMaNV && checkNgayNVTL && checkNgayNCCD
							&& checkSoNgayQL && checkSoNgayTrongThang) {
						dlHaTangTram = new TblDlHaTangTramDTO();

						// Date date=dt.parse(thang);
						// System.out.println("aaa"+dt.format(date));
						if (thang.length() == 6) {
							thang = "0" + thang;
						}
						dlHaTangTram.setThang(thang);

						dlHaTangTram.setKhuVuc(khuVuc);
						dlHaTangTram.setMaTinh(maTinh);
						dlHaTangTram.setHuyen(huyen);
						dlHaTangTram.setMaTram(maTram);
						switch (loaiDiaHinh) {
						case "L1":
							dlHaTangTram.setLoaiDiaHinh(L1);
							break;
						case "L2":
							dlHaTangTram.setLoaiDiaHinh(L2);
							break;
						case "L3":
							dlHaTangTram.setLoaiDiaHinh(L3);
							break;
						default:
							break;
						}

						switch (phanLoaiTram) {
						case "T1":
							dlHaTangTram.setPhanLoaiTram(T1);
							break;
						case "T2":
							dlHaTangTram.setPhanLoaiTram(T2);
							break;
						case "T3":
							dlHaTangTram.setPhanLoaiTram(T3);
							break;
						case "T4":
							dlHaTangTram.setPhanLoaiTram(T4);
							break;
						default:

							break;
						}
						switch (vung) {
						case "V1":
							dlHaTangTram.setVung(V1);
							break;
						case "V2":
							dlHaTangTram.setVung(V2);
							break;
						case "V3":
							dlHaTangTram.setVung(V3);
							break;
						case "V4":
							dlHaTangTram.setVung(V4);
							break;
						default:

							break;
						}

						dlHaTangTram.setMaNv(maNV);
						if (soNgayNCTL == null || "".equals(soNgayNCTL)) {
							soNgayNCTL = "0";
						}
						dlHaTangTram.setNgayCongTinhLuong(Float.parseFloat(soNgayNCTL));
						if (soNgayNCCD == null || "".equals(soNgayNCCD)) {
							soNgayNCCD = "0";
						}
						dlHaTangTram.setNgayCongCheDo(Float.parseFloat(soNgayNCCD));
						dlHaTangTram.setSoNgayQuanLy(Float.parseFloat(soNgayQL));
						dlHaTangTram.setSoNgayTrongThang(Float.parseFloat(soNgayTrongThang));

						workLstDone.add(dlHaTangTram);
					} else {
						importErrDTO = new ImportErrDTO();
						importErrDTO.setColumn1(thang);
						importErrDTO.setColumn2(khuVuc);
						importErrDTO.setColumn3(maTinh);
						importErrDTO.setColumn4(huyen);
						importErrDTO.setColumn5(maTram);
						importErrDTO.setColumn6(loaiDiaHinh);
						importErrDTO.setColumn7(phanLoaiTram);
						importErrDTO.setColumn8(vung);
						importErrDTO.setColumn9(maNV);
						importErrDTO.setColumn10(soNgayNCTL);
						importErrDTO.setColumn11(soNgayNCCD);
						importErrDTO.setColumn12(soNgayQL);
						importErrDTO.setColumn13(soNgayTrongThang);
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
					lsThaoTacDTO.setChucNang("Import dữ liệu hạ tầng trạm");
					lsThaoTacDTO.setMoTa("Import " +workLstDone.size() +" dữ liệu hạ tầng trạm ");
					lsThaoTacDTO.setThoiGianThucHien(tEnd-tStart);
					businessImpl.insertLSTT(lsThaoTacDTO,request);
				}
				if (workLstFault.size() > 0) {
					return exporthatangtramErr(workLstFault);
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
		 * @param t1
		 * @param t2
		 * @return
		 * @throws ParseException
		 */
		public boolean checkDLHaTangTram(int index, String value, String t1, String t2) throws ParseException {
			String errDetail = "";
			ErrExcelDTO err = new ErrExcelDTO();
			DateFormat fmt = new SimpleDateFormat("MM/yyyy");

			String[] T = { "T1", "T2", "T3", "T4" };
			String[] L = { "L1", "L2", "L3" };
			String[] V = { "V1", "V2", "V3", "V4" };
			switch (index) {
			case 0:
				// if (value.length() == 6) {
				// value = "0" + value;
				// }
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
					err.setDetailError("Mã khu vực đang để trống, ");
					lstErrorExcell.add(err);
					return false;
				}
				break;
			case 2:
				/*
				 * kiểm tra tên trùng với mã nv ?????
				 */
				if (value.isEmpty() || "".equals(value)) {
					err.setDetailError("Mã tỉnh đang để trống, ");
					lstErrorExcell.add(err);
					return false;
				} else if (value.length() > 100) {
					err.setDetailError("Mã tỉnh chỉ được phép nhập 50 kí tự, ");
					lstErrorExcell.add(err);
					return false;
				} else if(tblDanhmucDAO.checkExistmatinhBymatinh(value)!=1l)
				{
					err.setDetailError("Mã tỉnh không chính xác , ");
					lstErrorExcell.add(err);
					return false;
				}
				break;
			case 3:
				if (value.isEmpty() || "".equals(value)) {
					err.setDetailError("Huyện đang để trống, ");
					lstErrorExcell.add(err);
					return false;
				}
				else if(tblDanhmucDAO.checkExisttenhuyenBytenhuyen(value, t1)!=1)
				{
					err.setDetailError("Huyện không trùng tỉnh ");
					lstErrorExcell.add(err);
					return false;
				}
				break;
			case 4:
				if (value.isEmpty() || "".equals(value)) {
					err.setDetailError("Mã trạm đang để trống, ");
					lstErrorExcell.add(err);
					return false;
				} else if (value.length() > 100) {
					err.setDetailError("Mã trạm chỉ được phép nhập 50 kí tự, ");
					lstErrorExcell.add(err);
					return false;
				}

				break;
			case 5:

				if (value.isEmpty() || "".equals(value)) {
					err.setDetailError("Loại địa hình đang để trống, ");
					lstErrorExcell.add(err);
					return false;
				} else if (contains(L, value)) {
					err.setDetailError("Địa hình nhập vào không đúng, ");
					lstErrorExcell.add(err);
					return false;
				}
				break;
			case 6:
				if (value.isEmpty() || "".equals(value)) {
					err.setDetailError("Phân loại trạm đang để trống, ");
					lstErrorExcell.add(err);
					return false;
				} else if (contains(T, value)) {
					err.setDetailError("Loại trạm nhập vào không đúng, ");
					lstErrorExcell.add(err);
					return false;
				}
				break;
			case 7:
				if (value.isEmpty() || "".equals(value)) {
					err.setDetailError("Vùng lương nhập đang để trống, ");
					lstErrorExcell.add(err);
					return false;
				} else if (contains(V, value)) {
					err.setDetailError("Vùng lương nhập vào không đúng, ");
					lstErrorExcell.add(err);
					return false;
				}
				break;
			case 8:
				boolean flagk = false;
				List<TblNhanVienDTO> lstNV = tblNhanVienDAO.checkNV(value);
				// System.out.println("Business.NVTram.DuLieuHaTangTramBusiness.checkDLHaTangTram()"+lst);
				if (value.isEmpty() || "".equals(value)) {
					err.setDetailError("Mã nhân viên đang để trống, ");
					lstErrorExcell.add(err);
					return false;
				} else if (0 == lstNV.size()) {
					err.setDetailError("Mã nhân viên không tồn tại trong cơ sở dữ liệu, ");
					lstErrorExcell.add(err);
					return false;
				}
				for (int i = 0; i < lstNV.size(); i++) {
					if (lstNV.size() == 1) {
						if (!"0".equals(lstNV.get(i).getDayMayNhaTram().toString())) {
							err.setDetailError("Mã nhân viên không phải là nhân viên trạm, ");
							lstErrorExcell.add(err);
							return false;
						}
					} else {
						if (lstNV.get(0).getDayMayNhaTram().toString().equals("0")) {
							for (int k = i++; k < lstNV.size(); k++) {
								if (!lstNV.get(i).getDayMayNhaTram().toString()
										.equals(lstNV.get(k).getDayMayNhaTram().toString())) {
									flagk = true;
								} else {
									if (lstNV.get(i).getDayMayNhaTram().toString()
											.equals(lstNV.get(k).getDayMayNhaTram().toString())) {
										flagk = true;
									}
								}
							}
						} else {
							for (int k = i++; k < lstNV.size(); k++) {
								if (!lstNV.get(i).getDayMayNhaTram().toString()
										.equals(lstNV.get(k).getDayMayNhaTram().toString())) {
									flagk = true;
								}
							}
						}
						if (flagk == false) {
							err.setDetailError("Mã nhân viên không phải là nhân viên trạm, ");
							lstErrorExcell.add(err);
							return false;
						}
					}
					// else if(lstNV.size() >1){
					// for(int k=i+1;k<lstNV.size();k++){
					// if(lstNV.get(i).getDayMayNhaTram().equals(lstNV.get(k).getDayMayNhaTram())){
					//
					// }
					// }
					// }

				}

				break;
			case 9:
				if (value.isEmpty() || "".equals(value)) {
					err.setDetailError("Số ngày công tính lương đang để trống, ");
					lstErrorExcell.add(err);
					return false;
				} else if (!value.isEmpty()) {
					if (!StringUtils.isNumeric(value)) {
						err.setDetailError("Số ngày công tính lương không hợp lệ,");
						lstErrorExcell.add(err);
						return false;
					} else if (Integer.parseInt(value) > 31 || Integer.parseInt(value) < 0) {
						err.setDetailError("Số ngày công tính lương phải từ 0 ->31 ngày, ");
						lstErrorExcell.add(err);
						return false;
					}
				}

				break;
			case 10:
				if (value.isEmpty() || "".equals(value)) {
					err.setDetailError("Số ngày công chế độ đang để trống, ");
					lstErrorExcell.add(err);
					return false;
				} else if (!value.isEmpty()) {
					if (!StringUtils.isNumeric(value)) {
						err.setDetailError("Số ngày công chế độ không hợp lệ, ");
						lstErrorExcell.add(err);
						return false;
					} else if (Integer.parseInt(value) > 31 || Integer.parseInt(value) <= 0) {
						err.setDetailError("Số ngày công chế độ phải là số từ 1->31 ngày, ");
						lstErrorExcell.add(err);
						return false;
					}
				}
				break;
			case 11:
				if (value.isEmpty() || "".equals(value)) {
					err.setDetailError("Số ngày quản lý đang để trống, ");
					lstErrorExcell.add(err);
					return false;
				} else if (!StringUtils.isNumeric(value)) {
					err.setDetailError("Số ngày quản lý không hợp lệ, ");
					lstErrorExcell.add(err);
					return false;
				} else if (Integer.parseInt(value) > 31 || Integer.parseInt(value) < 0) {
					err.setDetailError("Số ngày quản lý phải từ 0 ->31 ngày, ");
					lstErrorExcell.add(err);
					return false;
				}

				break;
			case 12:
				if (value.isEmpty() || "".equals(value)) {
					err.setDetailError("Số ngày trong tháng đang để trống. ");
					lstErrorExcell.add(err);
					return false;
				} else if (!StringUtils.isNumeric(value)) {
					err.setDetailError("Số ngày trong tháng không hợp lệ. ");
					lstErrorExcell.add(err);
					return false;
				} else if (Integer.parseInt(value) > 31 || Integer.parseInt(value) <= 0) {
					err.setDetailError("Số ngày trong tháng phải là số từ 1->31.");
					lstErrorExcell.add(err);
					return false;
				}
				break;
			default:
				break;
			}
			return true;
		}

		public String exporthatangtramErr(List<ImportErrDTO> obj) throws Exception {
			// TODO Auto-generated method stub
			String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			try {
				ClassLoader classloader = Thread.currentThread().getContextClassLoader();
				String filePath = classloader.getResource("../" + "doc-template").getPath();
				// ClassLoader classloader =
				// Thread.currentThread().getContextClassLoader();
				InputStream fileIn = new BufferedInputStream(new FileInputStream(filePath + "DLHaTangTramErr.xlsx"));

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

					XSSFCell cell13 = row.createCell(13);
					cell13.setCellValue(obj.get(i).getColumn13());

					for (int j = 0; j < obj.get(i).getLstErrorOrder().size(); j++) {
						err.append(obj.get(i).getLstErrorOrder().get(j).getDetailError());
						// System.out.println("err" + err);
					}
					System.out.println("err " + i + ":" + err);
					XSSFCell cell14 = row.createCell(14);
					cell14.setCellValue(err.toString());
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
					cell14.setCellStyle(style);

				}
				fileIn.close();

				File out = new File(folder2Upload + File.separatorChar + "DLHaTangTramErr" + startExportTime + ".xlsx");

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
			return UEncrypt.encryptFileUploadPath("DLHaTangTramErr" + startExportTime + ".xlsx");
		}
		
		public String exportExcelGrid(TblDlHaTangTramDTO lst) throws Exception {

			List<TblDlHaTangTramDTO> obj = tblDlHaTangTramDAO.doSearchDLHaTang(lst);

			String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			try {
				ClassLoader classloader = Thread.currentThread().getContextClassLoader();
				String filePath = classloader.getResource("../" + "doc-template").getPath();
				InputStream fileIn = new BufferedInputStream(new FileInputStream(filePath + "ExportDLHaTangTram.xlsx"));
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
					cell2.setCellValue(obj.get(i).getKhuVuc());

					XSSFCell cell3 = row.createCell(3);
					cell3.setCellValue(obj.get(i).getMaTinh());

					XSSFCell cell4 = row.createCell(4);
					cell4.setCellValue(obj.get(i).getHuyen());

					XSSFCell cell5 = row.createCell(5);
					cell5.setCellValue(obj.get(i).getMaTram());

					XSSFCell cell6 = row.createCell(6);
					cell6.setCellValue(obj.get(i).getLoaiDiaHinh());

					XSSFCell cell7 = row.createCell(7);
					cell7.setCellValue(obj.get(i).getPhanLoaiTram());

					XSSFCell cell8 = row.createCell(8);
					cell8.setCellValue(obj.get(i).getVung());

					XSSFCell cell9 = row.createCell(9);
					cell9.setCellValue(obj.get(i).getMaNv());

					XSSFCell cell10 = row.createCell(10);
					cell10.setCellValue(obj.get(i).getNgayCongTinhLuong());

					XSSFCell cell11 = row.createCell(11);
					cell11.setCellValue(obj.get(i).getNgayCongCheDo());

					XSSFCell cell12 = row.createCell(12);
					cell12.setCellValue(obj.get(i).getSoNgayQuanLy());
					
					XSSFCell cell13 = row.createCell(13);
					cell13.setCellValue(obj.get(i).getSoNgayTrongThang());

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
				File out = new File(folder2Upload + File.separatorChar + "ExportDLHaTangTram" + startExportTime + ".xlsx");

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
			return UEncrypt.encryptFileUploadPath("ExportDLHaTangTram" + startExportTime + ".xlsx");
		}

		@Override
		public String downloadImportTemplate() throws Exception {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream file = new BufferedInputStream(new FileInputStream(filePath + "ExportDLHaTangTram.xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			
			
			file.close();
			File out = new File(folder2Upload + File.separatorChar + "ExportDLHaTangTram.xlsx");
			
			FileOutputStream outFile = new FileOutputStream(out);
			workbook.write(outFile);
			workbook.close();
			outFile.close();
			
			String path = UEncrypt.encryptFileUploadPath("ExportDLHaTangTram.xlsx");
			return path;
		}

}
