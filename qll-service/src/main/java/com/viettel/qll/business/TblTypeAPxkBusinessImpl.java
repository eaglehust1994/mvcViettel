package com.viettel.qll.business;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.viettel.ktts2.common.UEncrypt;
import com.viettel.qll.bo.TblTypeAPxkBO;
import com.viettel.qll.dao.TblNhanVienDAO;
import com.viettel.qll.dao.TblQltsThucXuatTheoKyDAO;
import com.viettel.qll.dao.TblTypeAPxkDAO;
import com.viettel.qll.dto.ErrExcelDTO;
import com.viettel.qll.dto.ImportErrDTO;
import com.viettel.qll.dto.LsThaoTacDTO;
import com.viettel.qll.dto.QllBaseDTO;
import com.viettel.qll.dto.TblQltsThucXuatTheoKyDTO;
import com.viettel.qll.dto.TblTypeAPxkDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;
import com.viettel.utils.ValidateUtils;

import freemarker.core.ParseException;

@Service("tblTypeAPxkBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TblTypeAPxkBusinessImpl extends BaseFWBusinessImpl<TblTypeAPxkDAO, TblTypeAPxkDTO, TblTypeAPxkBO>
		implements TblTypeAPxkBusiness {
	private String fomat = "dd/MM/yyyy";
	protected final Logger log = Logger.getLogger(TblTypeAPxkBusiness.class);

	@Autowired
	private TblTypeAPxkDAO tblTypeAPxkDAO;

	@Autowired
	TblNhanVienDAO tblNhanVienDAO;

	LsThaoTacDTO lsThaoTacDTO = new LsThaoTacDTO();
	@Autowired
	TblQltsThucXuatTheoKyBusinessImpl businessImpl = new TblQltsThucXuatTheoKyBusinessImpl();

	DecimalFormat dFormat = new DecimalFormat("#,###");
	DecimalFormat dFormat1 = new DecimalFormat("#,###.00");
	@Autowired
	TblQltsThucXuatTheoKyDAO qltsThucXuatTheoKyDAO;

	@Value("${folder_upload2}")
	private String folder2Upload;

	@Value("${folder_upload}")
	private String folderTemp;

	List<ErrExcelDTO> lstErrorExcell;

	DateFormat dt = new SimpleDateFormat("dd/MM/yyyy");

	public TblTypeAPxkBusinessImpl() {
		tModel = new TblTypeAPxkBO();
		tDAO = tblTypeAPxkDAO;
	}

	@Override
	@Transactional
	public Long updatePathImg(TblTypeAPxkDTO obj) throws Exception {
		try {
			long ids = tblTypeAPxkDAO.updatePath(obj);
			return ids;
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Có lỗi khi upload ảnh!!!");
		}

	}

	@Override
	public DataListDTO doSearchPXK(TblTypeAPxkDTO obj) {
		List<TblTypeAPxkDTO> ls = tblTypeAPxkDAO.doSearchPXK(obj);
		List<Long> listErr = new ArrayList<>();
		Date date = new Date();
		for (int i = 0; i < ls.size(); i++) {
			if (ls.get(i).getNgayGanDonVi() != null&&ls.get(i).getMaNv()!=null) {
				if (ls.get(i).getCheckNhapLieu()==null&&(date.getTime() - ls.get(i).getNgayGanDonVi().getTime()) > 3 * 24 * 3600 * 1000) {
					ls.get(i).setCheckGt(1);
				}else if(ls.get(i).getCheckNhapLieu()!=null){
					if (ls.get(i).getNgayGanNv() != null&&ls.get(i).getCheckNhapLieu()!=1) { 
						if ((date.getTime() - ls.get(i).getNgayGanNv().getTime()) > 3 * 24 * 3600 * 1000) {
							ls.get(i).setCheckGt(2);
						}
					}
				}
				
			}
		}

		DataListDTO data = new DataListDTO();
		data.setData(ls);
		data.setTotal(obj.getTotalRecord());
		data.setSize(obj.getTotalRecord());
		data.setStart(1);
		return data;
	}

	@Override
	public List<TblTypeAPxkDTO> getForAutoCompleteTramNhan(TblTypeAPxkDTO obj) {
		return tblTypeAPxkDAO.getForAutoCompleteTramNhan(obj);
	}

	@Override
	public List<TblTypeAPxkDTO> getForAutoCompleteHDXL(TblTypeAPxkDTO obj) {
		return tblTypeAPxkDAO.getForAutoCompleteHDXL(obj);
	}

	@Override
	public List<TblTypeAPxkDTO> checkMaHdxl(TblTypeAPxkDTO obj) {
		return tblTypeAPxkDAO.checkMaHdxl(obj);
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

	@Override
	public Long updateTypeA(TblTypeAPxkDTO obj, long index) throws Exception {
		try {

			obj.setKtKvttkt(index);
			obj.setNgayGanDonVi(new Date());
			long ids1 = tblTypeAPxkDAO.updateTypeA(obj);
			return ids1;
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Có lỗi khi gán chi nhánh 5 KV");
		}

	}

	@Override
	public Long updateTypeNV(TblTypeAPxkDTO obj) throws Exception {
		try {

			// obj.setKtKvttkt(index);
			obj.setNgayGanNv(new Date());
			long ids1 = tblTypeAPxkDAO.updateTypeNV(obj);
			return ids1;
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Có lỗi khi gán nhân viên");
		}

	}

	@Override
	public Long updateCheck(TblTypeAPxkDTO obj) throws Exception {
		try {
			long ids1 = tblTypeAPxkDAO.updateCheck(obj);
			return ids1;
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Có lỗi khi kiểm tra nhập liệu");
		}

	}

	public String importTypePXK(String filePath, HttpServletRequest request) throws Exception {
		// public static void main(String[] args) throws IOException {
		long tStart = System.currentTimeMillis();
		TblTypeAPxkDTO tblTypeAPxkDTO = new TblTypeAPxkDTO();
		// ExportNVTram exportHaTangTram = new ExportNVTram();
		/**
		 * sửa đây
		 */
		ImportErrDTO importErrDTO = new ImportErrDTO();
		// List<ErrExcelDTO> lstErrorExcell;

		ErrExcelDTO errExcelDTO = new ErrExcelDTO();
		// lstErrorOrder = new ArrayList<>();
		List<TblTypeAPxkDTO> workLstDone = Lists.newArrayList();
		/**
		 * sửa đây
		 */
		List<ImportErrDTO> workLstFault = Lists.newArrayList();
		// Đọc một file XSLx.
		File f = new File(filePath);

		// Đối tượng workbook cho file XSLx.
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
				ImportErrDTO err = new ImportErrDTO();
				boolean checkMaPX = true;
				boolean checkMaLX = true;
				boolean checkMaYCX = true;
				boolean checkNguoiTYC = true;
				boolean checkNguoiPD = true;
				boolean checkNgayTao = true;
				boolean checkNgayThucXuat = true;
				boolean checkKhoXuat = true;
				boolean checkLyDoXuat = true;
				boolean checkKhoNhan = true;
				boolean checkDonViNhan = true;
				boolean checkCongTrinhNhan = true;
				boolean checkMaTramNhan = true;
				boolean checkTinhTrang = true;
				boolean checkLyDoTuChoi = true;
				boolean checkMaHDXL = true;
				boolean checkTinhTrangCA = true;

				String maPX = "";
				String maLX = "";
				String maYCX = "";
				String nguoiTYC = "";
				String nguoiPD = "";
				String ngayTao = "";
				String ngayThucXuat = "";
				String khoXuat = "";
				String lyDoXuat = "";
				String khoNhan = "";
				String donViNhan = "";
				String congTrinhNhan = "";
				String maTramNhan = "";
				String tinhTrang = "";
				String lyDoTuChoi = "";
				String maHDXL = "";
				String tinhTrangCA = "";

				for (Cell cell : row) {
					switch (cell.getColumnIndex()) {
					case 1:
						maPX = dataFormatter.formatCellValue(cell);
						break;
					case 2:
						maLX = dataFormatter.formatCellValue(cell);
						// checkTenNV =
						// testCheck(cell.getColumnIndex(),tenNV);
						break;
					case 3:
						maYCX = dataFormatter.formatCellValue(cell);
						// checkDonVi =
						// testCheck(cell.getColumnIndex(),donVi);
						break;
					case 4:
						nguoiTYC = dataFormatter.formatCellValue(cell);
						// checkChucDanh =
						// testCheck(cell.getColumnIndex(),chucDanh);
						break;
					case 5:
						nguoiPD = dataFormatter.formatCellValue(cell);
						break;
					case 6:
						ngayTao = dataFormatter.formatCellValue(cell);
						// checkChucDanh =
						// testCheck(cell.getColumnIndex(),chucDanh);
						break;
					case 7:
						ngayThucXuat = dataFormatter.formatCellValue(cell);
						// checkChucDanh =
						// testCheck(cell.getColumnIndex(),chucDanh);
						break;
					case 8:
						khoXuat = dataFormatter.formatCellValue(cell);
						// checkChucDanh =
						// testCheck(cell.getColumnIndex(),chucDanh);
						break;
					case 9:
						lyDoXuat = dataFormatter.formatCellValue(cell);
						// checkChucDanh =
						// testCheck(cell.getColumnIndex(),chucDanh);
						break;
					case 10:
						khoNhan = dataFormatter.formatCellValue(cell);
						// checkChucDanh =
						// testCheck(cell.getColumnIndex(),chucDanh);
						break;
					case 11:
						donViNhan = dataFormatter.formatCellValue(cell);
						// checkChucDanh =
						// testCheck(cell.getColumnIndex(),chucDanh);
						break;
					case 12:
						congTrinhNhan = dataFormatter.formatCellValue(cell);
						// checkChucDanh =
						// testCheck(cell.getColumnIndex(),chucDanh);
						break;
					case 13:
						maTramNhan = dataFormatter.formatCellValue(cell);
						// checkChucDanh =
						// testCheck(cell.getColumnIndex(),chucDanh);
						break;
					case 14:
						tinhTrang = dataFormatter.formatCellValue(cell);
						// checkChucDanh =
						// testCheck(cell.getColumnIndex(),chucDanh);
						break;
					case 15:
						lyDoTuChoi = dataFormatter.formatCellValue(cell);
						// checkChucDanh =
						// testCheck(cell.getColumnIndex(),chucDanh);
						break;

					case 16:
						maHDXL = dataFormatter.formatCellValue(cell);
						// checkChucDanh =
						// testCheck(cell.getColumnIndex(),chucDanh);
						break;
					case 17:
						tinhTrangCA = dataFormatter.formatCellValue(cell);
						// checkChucDanh =
						// testCheck(cell.getColumnIndex(),chucDanh);
						break;
					default:
						break;
					}
				}
				// if (index == 4) {
				checkMaPX = checkTypePXK(0, maPX, null, null);
				checkMaLX = checkTypePXK(1, maLX, null, null);
				checkMaYCX = checkTypePXK(2, maYCX, null, null);
				checkNguoiTYC = checkTypePXK(3, nguoiTYC, null, null);
				checkNguoiPD = checkTypePXK(4, nguoiPD, null, null);
				checkNgayTao = checkTypePXK(5, ngayTao, null, null);
				checkNgayThucXuat = checkTypePXK(6, ngayThucXuat, null, null);
				checkKhoXuat = checkTypePXK(7, khoXuat, null, null);
				checkLyDoXuat = checkTypePXK(8, lyDoXuat, null, null);
				checkKhoNhan = checkTypePXK(9, khoNhan, null, null);
				checkDonViNhan = checkTypePXK(10, donViNhan, null, null);
				checkCongTrinhNhan = checkTypePXK(11, congTrinhNhan, null, null);
				checkMaTramNhan = checkTypePXK(12, maTramNhan, null, null);
				checkTinhTrang = checkTypePXK(13, tinhTrang, null, null);
				checkLyDoTuChoi = checkTypePXK(14, lyDoTuChoi, null, null);
				checkMaHDXL = checkTypePXK(15, maHDXL, null, null);
				checkTinhTrangCA = checkTypePXK(16, tinhTrangCA, null, null);

				// } else if (index == 5) {
				// System.out.println("fck you t.a");
				// }

				if (checkMaPX && checkMaLX && checkMaYCX && checkNguoiTYC && checkNguoiPD && checkNgayTao
						&& checkNgayThucXuat && checkKhoXuat && checkLyDoXuat && checkKhoNhan && checkDonViNhan
						&& checkCongTrinhNhan && checkMaTramNhan && checkTinhTrang && checkLyDoTuChoi
						&& checkTinhTrangCA && checkMaHDXL) {
					tblTypeAPxkDTO = new TblTypeAPxkDTO();

					// Date date=dt.parse(thang);
					// System.out.println("aaa"+dt.format(date));
					tblTypeAPxkDTO.setMaPhieuXuat(maPX);
					tblTypeAPxkDTO.setMaLenhXuat(maLX);
					tblTypeAPxkDTO.setMaYcXuat(maYCX);
					tblTypeAPxkDTO.setNguoiTaoYc(nguoiTYC);
					tblTypeAPxkDTO.setNguoiPheDuyet(nguoiPD);
					if (StringUtils.isNotEmpty(ngayTao)) {
						tblTypeAPxkDTO.setNgayTao(dt.parse(ngayTao));
					}
					if (StringUtils.isNotEmpty(ngayThucXuat)) {
						tblTypeAPxkDTO.setNgayThucXuat(dt.parse(ngayThucXuat));
					}
					tblTypeAPxkDTO.setKhoXuat(khoXuat);
					tblTypeAPxkDTO.setLyDoXuat(lyDoXuat);
					tblTypeAPxkDTO.setKhoNhan(khoNhan);
					tblTypeAPxkDTO.setDonViNhan(donViNhan);
					tblTypeAPxkDTO.setCongTrinhNhan(congTrinhNhan);
					tblTypeAPxkDTO.setMaTramNhan(maTramNhan);
					tblTypeAPxkDTO.setTinhTrang(tinhTrang);
					tblTypeAPxkDTO.setLyDoTuChoi(lyDoTuChoi);
					tblTypeAPxkDTO.setMaHdxl(maHDXL);
					tblTypeAPxkDTO.setTinhTrangKyCa(tinhTrangCA);
					tblTypeAPxkDTO.setXoa((long) 0);
					if (tblTypeAPxkDAO.checkDup(tblTypeAPxkDTO).size() > 0) {
						tblTypeAPxkDAO.deleteObj(tblTypeAPxkDTO);
					}
					workLstDone.add(tblTypeAPxkDTO);
				} else {
					importErrDTO = new ImportErrDTO();
					importErrDTO.setColumn1(maPX);
					importErrDTO.setColumn2(maLX);
					importErrDTO.setColumn3(maYCX);
					importErrDTO.setColumn4(nguoiTYC);
					importErrDTO.setColumn5(nguoiPD);
					importErrDTO.setColumn6(ngayTao);
					importErrDTO.setColumn7(ngayThucXuat);
					importErrDTO.setColumn8(khoXuat);
					importErrDTO.setColumn9(lyDoXuat);
					importErrDTO.setColumn10(khoNhan);
					importErrDTO.setColumn11(donViNhan);
					importErrDTO.setColumn12(congTrinhNhan);
					importErrDTO.setColumn13(maTramNhan);
					importErrDTO.setColumn14(tinhTrang);
					importErrDTO.setColumn15(lyDoTuChoi);
					importErrDTO.setColumn16(maHDXL);
					importErrDTO.setColumn17(tinhTrangCA);
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
				// saveList(workLstDone);
				try {
					saveList1(workLstDone);
					long tEnd = System.currentTimeMillis();
					lsThaoTacDTO.setChucNang("Import dữ liệu đầu vào PXK type A");
					lsThaoTacDTO.setMoTa("Import " + workLstDone.size() + " bản ghi dữ liệu đầu vào PXK type A ");
					lsThaoTacDTO.setThoiGianThucHien(tEnd - tStart);
					businessImpl.insertLSTT(lsThaoTacDTO, request);
				} catch (NullPointerException e) {
					// TODO: handle exception
					e.printStackTrace();
				}

			}
			if (workLstFault.size() > 0) {
				return exportTypePXKErr(workLstFault);
			}
		}
		System.out.println(workLstDone.size());
		System.out.println(workLstFault.size());
		return "";
	}

	public boolean checkTypePXK(int index, String value, String t1, String t2) throws ParseException {
		String errDetail = "";
		ErrExcelDTO err = new ErrExcelDTO();
		DateFormat fmt = new SimpleDateFormat("MM/yyyy");

		switch (index) {
		case 0:
			if (value.isEmpty() || "".equals(value)) {
				// err.setDetailError("Mã phiếu xuất đang để trống ");
				// lstErrorExcell.add(err);
				// return false;
			}
			break;
		case 1:
			if (value.isEmpty() || "".equals(value)) {
				// err.setDetailError("Mã lệnh xuất đang để trống ");
				// lstErrorExcell.add(err);
				// return false;
			}
			break;
		case 2:
			// if (value.isEmpty() || "".equals(value)) {
			// err.setDetailError("Mã yêu cầu xuất đang để trống ");
			// lstErrorExcell.add(err);
			// return false;
			// }
			break;
		case 3:
			// if (value.isEmpty() || "".equals(value)) {
			// err.setDetailError("Người tạo yêu cầu đang để trống ");
			// lstErrorExcell.add(err);
			// return false;
			// }
			break;
		case 4:
			// if (value.isEmpty() || "".equals(value)) {
			// err.setDetailError("Người phê duyệt đang để trống ");
			// lstErrorExcell.add(err);
			// return false;
			// }
			break;
		case 5:
			if (StringUtils.isNotEmpty(value)) {
				if (!ValidateUtils.isDate(value, fomat)) {
					err.setDetailError("Ngày tạo không đúng định dạng <ngày/tháng/năm> ");
					lstErrorExcell.add(err);
					return false;
				}
			}
			break;
		case 6:
			if (StringUtils.isNotEmpty(value)) {
				if (!ValidateUtils.isDate(value, fomat)) {
					err.setDetailError("Ngày thực xuất không đúng định dạng <ngày/tháng/năm> ");
					lstErrorExcell.add(err);
					return false;
				}

			}
			break;
		case 7:
			// if (value.isEmpty() || "".equals(value)) {
			// err.setDetailError("Kho xuất đang để trống ");
			// lstErrorExcell.add(err);
			// return false;
			// }
			// break;
		case 8:
			// if (value.isEmpty() || "".equals(value)) {
			// err.setDetailError("Lý do xuất đang để trống ");
			// lstErrorExcell.add(err);
			// return false;
			// }
			// boolean flagk = false;
			// List<TblNhanVienDTO> lstNV = tblNhanVienDAO.checkNV(value);
			// //
			// System.out.println("Business.NVTram.DuLieuHaTangTramBusiness.checkDLHaTangTram()"+lst);
			// if (value.isEmpty() || "".equals(value)) {
			// err.setDetailError("Mã nhân viên đang để trống, ");
			// lstErrorExcell.add(err);
			// return false;
			// } else if (0 == lstNV.size()) {
			// err.setDetailError("Mã nhân viên không tồn tại trong cơ sở dữ
			// liệu, ");
			// lstErrorExcell.add(err);
			// return false;
			// }
			// for (int i = 0; i < lstNV.size(); i++) {
			// if (lstNV.size() == 1) {
			// if (!"0".equals(lstNV.get(i).getDayMayNhaTram().toString())) {
			// err.setDetailError("Mã nhân viên không phải là nhân viên trạm,
			// ");
			// lstErrorExcell.add(err);
			// return false;
			// }
			// } else {
			// if (lstNV.get(0).getDayMayNhaTram().toString().equals("0")) {
			// for (int k = i++; k < lstNV.size(); k++) {
			// if (!lstNV.get(i).getDayMayNhaTram().toString()
			// .equals(lstNV.get(k).getDayMayNhaTram().toString())) {
			// flagk = true;
			// } else {
			// if (lstNV.get(i).getDayMayNhaTram().toString()
			// .equals(lstNV.get(k).getDayMayNhaTram().toString())) {
			// flagk = true;
			// }
			// }
			// }
			// } else {
			// for (int k = i++; k < lstNV.size(); k++) {
			// if (!lstNV.get(i).getDayMayNhaTram().toString()
			// .equals(lstNV.get(k).getDayMayNhaTram().toString())) {
			// flagk = true;
			// }
			// }
			// }
			// if (flagk == false) {
			// err.setDetailError("Mã nhân viên không phải là nhân viên trạm,
			// ");
			// lstErrorExcell.add(err);
			// return false;
			// }
			// }
			//
			// }

			break;
		case 9:
			// if (value.isEmpty() || "".equals(value)) {
			// err.setDetailError("Kho nhận đang để trống ");
			// lstErrorExcell.add(err);
			// return false;
			// }

			break;
		case 10:
			// if (value.isEmpty() || "".equals(value)) {
			// err.setDetailError("Đơn vị nhận đang để trống ");
			// lstErrorExcell.add(err);
			// return false;
			// }
			// break;
		case 11:
			// if (value.isEmpty() || "".equals(value)) {
			// err.setDetailError("Công trình nhận đang để trống ");
			// lstErrorExcell.add(err);
			// return false;
			// }

			break;
		case 12:
			// if (value.isEmpty() || "".equals(value)) {
			// err.setDetailError("Mã trạm nhận đang để trống. ");
			// lstErrorExcell.add(err);
			// return false;
			// }
		case 13:
			// if (value.isEmpty() || "".equals(value)) {
			// err.setDetailError("Tình trạng đang để trống ");
			// lstErrorExcell.add(err);
			// return false;
			// }
			break;
		case 14:
			// if (value.isEmpty() || "".equals(value)) {
			// err.setDetailError("Lý do từ chối nhận đang để trống ");
			// lstErrorExcell.add(err);
			// return false;
			// }

			break;
		case 15:
			// if (value.isEmpty() || "".equals(value)) {
			// err.setDetailError("Mã hợp đông lao động đang để trống. ");
			// lstErrorExcell.add(err);
			// return false;
			// }
			break;
		case 16:
			// if (value.isEmpty() || "".equals(value)) {
			// err.setDetailError("Tình trạng ký CA nhận đang để trống. ");
			// lstErrorExcell.add(err);
			// return false;
			// }
			break;
		default:
			break;
		}
		return true;
	}

	public String exportTypePXKErr(List<ImportErrDTO> obj) throws Exception {
		// TODO Auto-generated method stub
		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			// ClassLoader classloader =
			// Thread.currentThread().getContextClassLoader();
			InputStream fileIn = new BufferedInputStream(new FileInputStream(filePath + "TypePXKErr.xlsx"));

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

				XSSFCell cell14 = row.createCell(14);
				cell14.setCellValue(obj.get(i).getColumn14());

				XSSFCell cell15 = row.createCell(15);
				cell15.setCellValue(obj.get(i).getColumn15());

				XSSFCell cell16 = row.createCell(16);
				cell16.setCellValue(obj.get(i).getColumn16());

				XSSFCell cell17 = row.createCell(17);
				cell17.setCellValue(obj.get(i).getColumn17());

				for (int j = 0; j < obj.get(i).getLstErrorOrder().size(); j++) {
					err.append(obj.get(i).getLstErrorOrder().get(j).getDetailError() + "-");
					// System.out.println("err" + err);
				}
				System.out.println("err " + i + ":" + err);
				XSSFCell cell18 = row.createCell(18);
				cell18.setCellValue(err.toString());
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
				cell15.setCellStyle(style);
				cell16.setCellStyle(style);
				cell17.setCellStyle(style);
				cell18.setCellStyle(style);

			}
			fileIn.close();

			File out = new File(folder2Upload + File.separatorChar + "TypePXKErr" + startExportTime + ".xlsx");

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
		return UEncrypt.encryptFileUploadPath("TypePXKErr" + startExportTime + ".xlsx");
	}

	public String exportExcelGrid(TblTypeAPxkDTO lst, HttpServletRequest request) throws Exception {

		List<TblTypeAPxkDTO> obj = tblTypeAPxkDAO.doSearchPXK(lst);

		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream fileIn = new BufferedInputStream(new FileInputStream(filePath + "ExportTypePXK.xlsx"));
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
				cell1.setCellValue(obj.get(i).getMaPhieuXuat());

				XSSFCell cell2 = row.createCell(2);
				cell2.setCellValue(obj.get(i).getMaLenhXuat());

				XSSFCell cell3 = row.createCell(3);
				cell3.setCellValue(obj.get(i).getMaYcXuat());

				XSSFCell cell4 = row.createCell(4);
				cell4.setCellValue(obj.get(i).getNguoiTaoYc());

				XSSFCell cell5 = row.createCell(5);
				cell5.setCellValue(obj.get(i).getNguoiPheDuyet());

				XSSFCell cell6 = row.createCell(6);
				cell6.setCellValue(dt.format(obj.get(i).getNgayTao()));

				XSSFCell cell7 = row.createCell(7);
				cell7.setCellValue(dt.format(obj.get(i).getNgayThucXuat()));

				XSSFCell cell8 = row.createCell(8);
				cell8.setCellValue(obj.get(i).getKhoXuat());

				XSSFCell cell9 = row.createCell(9);
				cell9.setCellValue(obj.get(i).getLyDoXuat());

				XSSFCell cell10 = row.createCell(10);
				cell10.setCellValue(obj.get(i).getKhoNhan());

				XSSFCell cell11 = row.createCell(11);
				cell11.setCellValue(obj.get(i).getDonViNhan());

				XSSFCell cell12 = row.createCell(12);
				cell12.setCellValue(obj.get(i).getCongTrinhNhan());

				XSSFCell cell13 = row.createCell(13);
				cell13.setCellValue(obj.get(i).getMaTramNhan());

				XSSFCell cell14 = row.createCell(14);
				cell14.setCellValue(obj.get(i).getTinhTrang());

				XSSFCell cell15 = row.createCell(15);
				cell15.setCellValue(obj.get(i).getLyDoTuChoi());

				XSSFCell cell16 = row.createCell(16);
				cell16.setCellValue(obj.get(i).getMaHdxl());

				XSSFCell cell17 = row.createCell(17);
				cell17.setCellValue(obj.get(i).getTinhTrangKyCa());

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
				cell15.setCellStyle(style);
				cell16.setCellStyle(style);
				cell17.setCellStyle(style);
			}

			File out = new File(folder2Upload + File.separatorChar + "ExportTypePXK" + startExportTime + ".xlsx");

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
		lsThaoTacDTO.setChucNang("Export dữ liệu đầu vào PXK type A");
		lsThaoTacDTO.setMoTa("Export dữ liệu đầu vào PXK type A " + (new Date()));
		businessImpl.insertLSTT(lsThaoTacDTO, request);
		return UEncrypt.encryptFileUploadPath("ExportTypePXK" + startExportTime + ".xlsx");
	}

	@SuppressWarnings("unchecked")
	public String saveList1(List<TblTypeAPxkDTO> lstCenterBO) {
		@SuppressWarnings("rawtypes")
		List lstModel = new ArrayList<>();
		if (lstCenterBO != null) {
			for (int i = 0; i < lstCenterBO.size(); i++) {
				lstModel.add(((QllBaseDTO<TblTypeAPxkBO>) lstCenterBO.get(i)).toModel());
			}
		}
		return tblTypeAPxkDAO.saveList1(lstModel);
	}

	public String exportTHDoiSoatVatTu(TblTypeAPxkDTO lst, HttpServletRequest request) throws Exception {

		// List<TblTypeAPxkDTO> obj = tblTypeAPxkDAO.getAllMaHdxl(lst);
		List<TblTypeAPxkDTO> obj1 = tblTypeAPxkDAO.getAllMaHdxlDistinct(lst);
		List<TblTypeAPxkDTO> obj2 = new ArrayList<>();
		// for(TblTypeAPxkDTO aPxkDTO:obj1){
		// TblTypeAPxkDTO aPxkDTO2=new TblTypeAPxkDTO();
		// aPxkDTO2= tblTypeAPxkDAO.getSoPXKStt(aPxkDTO);
		// obj2.add(aPxkDTO2);
		// }
		// TblTypeAPxkDTO sumobj=tblTypeAPxkDAO.sumMaHdxl(lst);

		float gt1sl = 0;
		float gt1tt = 0;
		float gt2sl = 0;
		float gt2tt = 0;
		float gt3sl = 0;
		float gt3tt = 0;
		float gt4sl = 0;
		float gt4tt = 0;
		float gt11sl = 0;
		float gt11tt = 0;
		float gt21sl = 0;
		float gt22tt = 0;
		float gt31sl = 0;
		float gt32tt = 0;
		float gt41sl = 0;
		float gt42tt = 0;
		TblTypeAPxkDTO aPxkDTO = new TblTypeAPxkDTO();
		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream fileIn = new BufferedInputStream(new FileInputStream(filePath + "ThDoiSoatVatTuACap.xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(fileIn);
			XSSFSheet sheet = workbook.getSheetAt(0);
			XSSFCellStyle style = workbook.createCellStyle();
			XSSFCellStyle style1 = workbook.createCellStyle();
			XSSFCellStyle style2 = workbook.createCellStyle();
			XSSFCellStyle style3 = workbook.createCellStyle();
			XSSFCellStyle styleNumber = workbook.createCellStyle();
			XSSFFont hSSFFont = workbook.createFont();
			hSSFFont.setFontName("Times New Roman");
			try {
				XSSFFont hSSFFont1 = workbook.createFont();
				hSSFFont1.setFontName("Times New Roman");
				hSSFFont1.setFontHeightInPoints((short) 12);
				hSSFFont1.setBold(true);
				// style1.setVerticalAlignment(VerticalAlignment.CENTER);
				style1.setFont(hSSFFont1);
				style1.setWrapText(true);
				style1.setAlignment(CellStyle.ALIGN_CENTER);
			} catch (Exception e) {
				e.printStackTrace();
			}
			style.setFont(hSSFFont);
			style.setBorderBottom(BorderStyle.THIN);
			style.setBorderTop(BorderStyle.THIN);
			style.setBorderRight(BorderStyle.THIN);
			style.setBorderLeft(BorderStyle.THIN);
			style.setWrapText(true);
			style2.setBorderBottom(BorderStyle.THIN);
			style2.setBorderLeft(BorderStyle.THIN);
			style3.setBorderBottom(BorderStyle.THIN);
			styleNumber.setBorderBottom(BorderStyle.THIN);
			styleNumber.setBorderTop(BorderStyle.THIN);
			styleNumber.setBorderRight(BorderStyle.THIN);
			styleNumber.setBorderLeft(BorderStyle.THIN);
			styleNumber.setWrapText(true);
			styleNumber.setAlignment(CellStyle.ALIGN_RIGHT);

			XSSFRow row1 = null;
			row1 = sheet.createRow(4);
			XSSFCell cell1101 = row1.createCell(0);
			cell1101.setCellValue("Hợp đồng số: " + lst.getMaHdxl());
			cell1101.setCellStyle(style1);

			row1 = sheet.createRow(5);
			XSSFCell cell110 = row1.createCell(0);
			if (lst.getNgayThucXuatFrom() != null) {
				cell110.setCellValue("Từ ngày :" + dt.format(lst.getNgayThucXuatFrom()) + " đến ngày :"
						+ dt.format(lst.getNgayThucXuatTo()));
			} else {
				cell110.setCellValue("Từ ngày ...../..../...... đến ngày ...../..../......");
			}
			cell110.setCellStyle(style1);

			int rownum = 9;
			int rownum1 = 9;
			for (int i = 0; i < obj1.size(); i++) {// distinct pxk

				if (i == 0) {
					row1 = sheet.createRow(rownum);
					rownum = rownum1;

				} else {
					rownum = rownum1;
					row1 = sheet.createRow(rownum);
				}

				XSSFCell cell01 = row1.createCell(0);
				cell01.setCellValue("Nhóm " + (i + 1));
				XSSFCell cell111 = row1.createCell(1);
				cell111.setCellValue(obj1.get(i).getSoPhieuXuat());
				cell111.setCellStyle(style);
				cell01.setCellStyle(style);

				obj2 = tblTypeAPxkDAO.getAllMaHdxl(obj1.get(i));
				for (int j = 0; j < obj2.size(); j++) {// lấy tất cả pxk theo
														// maHDXL

					rownum1++;
					XSSFRow row = sheet.createRow(rownum1);
					XSSFCell cell0 = row.createCell(0);
					cell0.setCellValue(j + 1);

					XSSFCell cell1 = row.createCell(1);
					cell1.setCellValue("");

					XSSFCell cell2 = row.createCell(2);
					cell2.setCellValue(dt.format(obj2.get(j).getNgayXuat()));

					XSSFCell cell3 = row.createCell(3);
					cell3.setCellValue(obj2.get(j).getMaCongTrinh());

					XSSFCell cell4 = row.createCell(4);
					cell4.setCellValue(obj2.get(j).getMaTramNhan());

					XSSFCell cell5 = row.createCell(5);
					cell5.setCellValue(obj2.get(j).getTenVatTu());

					XSSFCell cell6 = row.createCell(6);
					cell6.setCellValue(obj2.get(j).getMaVatTu());

					XSSFCell cell7 = row.createCell(7);
					cell7.setCellValue(obj2.get(j).getDonViTinh());

					XSSFCell cell8 = row.createCell(8);
					if (obj2.get(j).getSoLuongXuat() == null || obj2.get(j).getSoLuongXuat() == (float) 0) {
						obj2.get(j).setSoLuongXuat((float) 0);
						cell8.setCellValue(obj2.get(j).getSoLuongXuat());
					} else {
						cell8.setCellValue(obj2.get(j).getSoLuongXuat());
						// cell8.setCellValue(dFormat1.format(obj2.get(j).getSoLuongXuat()));
					}
					gt1sl += obj2.get(j).getSoLuongXuat();

					XSSFCell cell9 = row.createCell(9);
					if (obj2.get(j).getDonGia() == null || obj2.get(j).getDonGia() == (float) 0) {
						obj2.get(j).setDonGia((float) 0);
						cell9.setCellValue(obj2.get(j).getDonGia());
					} else {
						cell9.setCellValue(obj2.get(j).getDonGia());
						// cell9.setCellValue(dFormat.format(obj2.get(j).getDonGia()));
					}

					XSSFCell cell10 = row.createCell(10);
					if (obj2.get(j).getThanhTien() == null || obj2.get(j).getThanhTien() == (float) 0) {
						obj2.get(j).setThanhTien((float) 0);
						cell10.setCellValue(obj2.get(j).getThanhTien());
					} else {
						cell10.setCellValue(obj2.get(j).getThanhTien());
						// cell10.setCellValue(dFormat.format(obj2.get(j).getThanhTien()));
					}
					gt1tt += obj2.get(j).getThanhTien();

					XSSFCell cell11 = row.createCell(11);
					if (obj2.get(j).getSlThucTeTC() == null || obj2.get(j).getSlThucTeTC() == (float) 0) {
						obj2.get(j).setSlThucTeTC((float) 0);
						cell11.setCellValue(obj2.get(j).getSlThucTeTC());
					} else {
						cell11.setCellValue(obj2.get(j).getSlThucTeTC());
						// cell11.setCellValue(dFormat1.format(obj2.get(j).getSlThucTeTC()));
					}
					gt2sl += obj2.get(j).getSlThucTeTC();

					XSSFCell cell12 = row.createCell(12);
					if (obj2.get(j).getTtThucTeTC() == null || obj2.get(j).getTtThucTeTC() == (float) 0) {
						obj2.get(j).setTtThucTeTC((float) 0);
						cell12.setCellValue(obj2.get(j).getTtThucTeTC());
					} else {
						cell12.setCellValue(obj2.get(j).getTtThucTeTC());
						// cell12.setCellValue(dFormat.format(obj2.get(j).getTtThucTeTC()));
					}
					gt2tt += obj2.get(j).getTtThucTeTC();

					XSSFCell cell13 = row.createCell(13);
					if (obj2.get(j).getSlThuHoi() == null || obj2.get(j).getSlThuHoi() == (float) 0) {
						obj2.get(j).setSlThuHoi((float) 0);
						cell13.setCellValue(obj2.get(j).getSlThuHoi());
					} else {
						cell13.setCellValue(obj2.get(j).getSlThuHoi());
						// cell13.setCellValue(dFormat1.format(obj2.get(j).getSlThuHoi()));
					}
					gt3sl += obj2.get(j).getSlThuHoi();

					XSSFCell cell14 = row.createCell(14);
					if (obj2.get(j).getTtThuHoi() == null || obj2.get(j).getTtThuHoi() == (float) 0) {
						obj2.get(j).setTtThuHoi((float) 0);
						cell14.setCellValue(obj2.get(j).getTtThuHoi());
					} else {
						cell14.setCellValue(obj2.get(j).getTtThuHoi());
						// cell14.setCellValue(dFormat.format(obj2.get(j).getTtThuHoi()));
					}
					gt3tt += obj2.get(j).getTtThuHoi();

					XSSFCell cell15 = row.createCell(15);
					if (obj2.get(j).getSlTienDen() == null || obj2.get(j).getSlTienDen() == (float) 0) {
						obj2.get(j).setSlTienDen((float) 0);
						cell15.setCellValue(obj2.get(j).getSlTienDen());
					} else {
						// cell15.setCellValue(dFormat1.format(obj2.get(j).getSlTienDen()));
						cell15.setCellValue(obj2.get(j).getSlTienDen());
					}
					gt4sl += obj2.get(j).getSlTienDen();

					XSSFCell cell16 = row.createCell(16);
					if (obj2.get(j).getTtTienDen() == null || obj2.get(j).getTtTienDen() == (float) 0) {
						obj2.get(j).setTtTienDen((float) 0);
						cell16.setCellValue(obj2.get(j).getTtTienDen());
					} else {
						cell16.setCellValue(obj2.get(j).getTtTienDen());
						// cell16.setCellValue(dFormat.format(obj2.get(j).getTtTienDen()));
					}
					gt4tt += obj2.get(j).getTtTienDen();

					XSSFCell cell17 = row.createCell(17);
					cell17.setCellValue("");

					cell0.setCellStyle(style);
					cell1.setCellStyle(style);
					cell2.setCellStyle(style);
					cell3.setCellStyle(style);
					cell4.setCellStyle(style);
					cell5.setCellStyle(style);
					cell6.setCellStyle(style);
					cell7.setCellStyle(style);
					cell8.setCellStyle(styleNumber);
					cell9.setCellStyle(styleNumber);
					cell10.setCellStyle(styleNumber);
					cell11.setCellStyle(styleNumber);
					cell12.setCellStyle(styleNumber);
					cell13.setCellStyle(styleNumber);
					cell14.setCellStyle(styleNumber);
					cell15.setCellStyle(styleNumber);
					cell16.setCellStyle(styleNumber);
					cell17.setCellStyle(styleNumber);
					if (j == (obj2.size() - 1)) {
						rownum1++;
					}
				}
				// thừa có thể bỏ :))))
				gt11sl = gt1sl;
				gt11tt = gt1tt;
				gt21sl = gt2sl;
				gt22tt = gt2tt;
				gt31sl = gt3sl;
				gt32tt = gt3tt;
				gt41sl = gt4sl;
				gt42tt = gt4tt;
				//
			}
			sheet.addMergedRegion(new CellRangeAddress(rownum1, rownum1, 1, 7));

			row1 = sheet.createRow(rownum1);
			XSSFCell cell171 = row1.createCell(1);
			cell171.setCellValue("Cộng");
			cell171.setCellStyle(style2);
			XSSFCell cell17120 = row1.createCell(2);
			cell17120.setCellStyle(style3);
			XSSFCell cell17121 = row1.createCell(3);
			cell17121.setCellStyle(style3);
			XSSFCell cell17122 = row1.createCell(4);
			cell17122.setCellStyle(style3);
			XSSFCell cell17123 = row1.createCell(5);
			cell17123.setCellStyle(style3);
			XSSFCell cell17124 = row1.createCell(6);
			cell17124.setCellStyle(style3);
			XSSFCell cell17125 = row1.createCell(7);
			cell17125.setCellStyle(style3);

			XSSFCell cell1710 = row1.createCell(8);
			cell1710.setCellValue(gt11sl);
			// cell1710.setCellValue("");
			cell1710.setCellStyle(style);

			XSSFCell cell1711 = row1.createCell(9);
			cell1711.setCellValue("");
			cell1711.setCellStyle(style);

			XSSFCell cell1712 = row1.createCell(10);
			cell1712.setCellValue(gt11tt);
			// cell1712.setCellValue("");
			cell1712.setCellStyle(style);

			XSSFCell cell1713 = row1.createCell(11);
			// cell1713.setCellValue("");
			cell1713.setCellValue(gt21sl);
			cell1713.setCellStyle(style);

			XSSFCell cell1714 = row1.createCell(12);
			// cell1714.setCellValue("");
			cell1714.setCellValue(gt22tt);
			cell1714.setCellStyle(style);

			XSSFCell cell1715 = row1.createCell(13);
			cell1715.setCellValue(gt31sl);
			// cell1715.setCellValue("");
			cell1715.setCellStyle(style);

			XSSFCell cell1716 = row1.createCell(14);
			cell1716.setCellValue(gt32tt);
			// cell1716.setCellValue("");
			cell1716.setCellStyle(style);

			XSSFCell cell1717 = row1.createCell(15);
			// cell1717.setCellValue(sumobj.getSlTienDen());
			cell1717.setCellValue(gt41sl);
			cell1717.setCellStyle(style);

			XSSFCell cell1718 = row1.createCell(16);
			// cell1718.setCellValue("");
			cell1718.setCellValue(gt42tt);
			cell1718.setCellStyle(style);

			XSSFCell cell1719 = row1.createCell(17);
			cell1719.setCellValue("");
			cell1719.setCellStyle(style);

			sheet.addMergedRegion(new CellRangeAddress(rownum1 + 1, rownum1 + 1, 1, 7));
			row1 = sheet.createRow(rownum1 + 1);
			XSSFCell cell181 = row1.createCell(1);
			cell181.setCellValue("Tổng cộng theo hợp đồng");
			// cell181.setCellStyle(style);

			sheet.addMergedRegion(new CellRangeAddress(rownum1 + 4, rownum1 + 4, 1, 8));
			row1 = sheet.createRow(rownum1 + 4);
			XSSFCell cell191 = row1.createCell(1);
			cell191.setCellValue("VIETTEL HƯNG YÊN");
			cell191.setCellStyle(style1);

			sheet.addMergedRegion(new CellRangeAddress(rownum1 + 4, rownum1 + 4, 9, 16));
			XSSFCell cell120 = row1.createCell(9);
			cell120.setCellValue("CÔNG TY CỔ PHẦN CÔNG TRÌNH VIETTEL");
			cell120.setCellStyle(style1);

			sheet.addMergedRegion(new CellRangeAddress(rownum1 + 5, rownum1 + 5, 1, 2));
			sheet.addMergedRegion(new CellRangeAddress(rownum1 + 5, rownum1 + 5, 3, 6));
			sheet.addMergedRegion(new CellRangeAddress(rownum1 + 5, rownum1 + 5, 7, 8));
			sheet.addMergedRegion(new CellRangeAddress(rownum1 + 5, rownum1 + 5, 9, 12));
			sheet.addMergedRegion(new CellRangeAddress(rownum1 + 5, rownum1 + 5, 13, 15));
			sheet.addMergedRegion(new CellRangeAddress(rownum1 + 5, rownum1 + 5, 16, 17));

			row1 = sheet.createRow(rownum1 + 5);
			XSSFCell cell121 = row1.createCell(1);
			cell121.setCellValue("PHÒNG KT HẠ TẦNG");
			cell121.setCellStyle(style1);

			XSSFCell cell131 = row1.createCell(3);
			cell131.setCellValue("PHÒNG KT HẠ TẦNG");
			cell131.setCellStyle(style1);

			XSSFCell cell141 = row1.createCell(7);
			cell141.setCellValue("GIÁM ĐỐC");
			cell141.setCellStyle(style1);

			XSSFCell cell151 = row1.createCell(9);
			cell151.setCellValue("ĐỘI THI CÔNG");
			cell151.setCellStyle(style1);

			XSSFCell cell161 = row1.createCell(13);
			cell161.setCellValue("KẾ TOÁN");
			cell161.setCellStyle(style1);

			XSSFCell cell162 = row1.createCell(16);
			cell162.setCellValue("TUQ. GIÁM ĐỐC");
			cell162.setCellStyle(style1);

			File out = new File(folder2Upload + File.separatorChar + "ThDoiSoatVatTuACap" + startExportTime + ".xlsx");

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
		lsThaoTacDTO.setChucNang("Export đối soát vật tư tổng hợp");
		lsThaoTacDTO.setMoTa("Export đối soát vật tư tổng hợp " + (new Date()));
		businessImpl.insertLSTT(lsThaoTacDTO, request);
		return UEncrypt.encryptFileUploadPath("ThDoiSoatVatTuACap" + startExportTime + ".xlsx");
	}

}
