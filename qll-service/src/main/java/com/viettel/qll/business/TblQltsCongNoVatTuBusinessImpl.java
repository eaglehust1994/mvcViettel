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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.viettel.ktts2.common.UEncrypt;
import com.viettel.qll.bo.TblQltsCongNoVatTuBO;
import com.viettel.qll.dao.TblNhanVienDAO;
import com.viettel.qll.dao.TblQltsCongNoVatTuDAO;
import com.viettel.qll.dto.ErrExcelDTO;
import com.viettel.qll.dto.ImportErrDTO;
import com.viettel.qll.dto.LsThaoTacDTO;
import com.viettel.qll.dto.QllBaseDTO;
import com.viettel.qll.dto.TblQltsCongNoVatTuDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;
import com.viettel.utils.ValidateUtils;

@Service("tblQltsCongNoVatTuBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TblQltsCongNoVatTuBusinessImpl
		extends BaseFWBusinessImpl<TblQltsCongNoVatTuDAO, TblQltsCongNoVatTuDTO, TblQltsCongNoVatTuBO>
		implements TblQltsCongNoVatTuBusiness {
	protected final Logger log = Logger.getLogger(TblQltsCongNoVatTuBusiness.class);
	@Value("${folder_upload2}")
	private String folder2Upload;
	private String fomat = "dd/MM/yyyy";
	List<ErrExcelDTO> lstErrExcelDTO;

	DecimalFormat dFormat = new DecimalFormat("#,###.00");
	DateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
	
	LsThaoTacDTO lsThaoTacDTO = new LsThaoTacDTO();

	@Autowired
	TblQltsThucXuatTheoKyBusinessImpl businessImpl=new TblQltsThucXuatTheoKyBusinessImpl();

	@Autowired
	private TblQltsCongNoVatTuDAO tblQltsCongNoVatTuDAO;

	@Autowired
	private TblNhanVienDAO nhanVienDAO;

	public TblQltsCongNoVatTuBusinessImpl() {
		tModel = new TblQltsCongNoVatTuBO();
		tDAO = tblQltsCongNoVatTuDAO;
	}

	@Override
	public TblQltsCongNoVatTuDAO gettDAO() {
		return tblQltsCongNoVatTuDAO;
	}

	@Override
	public DataListDTO getAllCongNo(TblQltsCongNoVatTuDTO obj) {
		List<TblQltsCongNoVatTuDTO> ls = tblQltsCongNoVatTuDAO.getAllQlCongNo(obj);
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
	
	@Override
	@Transactional
	public Long updateSlCongNo(TblQltsCongNoVatTuDTO obj,HttpServletRequest request) throws Exception {
		try{
			long ids =update(obj.getLstCongNo().get(0));
				lsThaoTacDTO.setChucNang("Cập nhật số lượng vật tư đối soát");
				lsThaoTacDTO.setMoTa("Cập nhật số lượng vật tư đối soát "+(new Date()));
				businessImpl.insertLSTT(lsThaoTacDTO,request);
			return ids;
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Có lỗi khi cập nhật số lượng công nợ!!");
		}

	}

	public String importCongNoVatTu(String filePath,HttpServletRequest request) throws Exception {
		// public static void main(String[] args) throws IOException {
		long tStart = System.currentTimeMillis();
		/**
		 * sửa đây
		 */

		// lstErrorOrder = new ArrayList<>();
		List<TblQltsCongNoVatTuDTO> workLstDone = Lists.newArrayList();
		List<ImportErrDTO> lstFails = Lists.newArrayList();

		String Ten = "";
		/**
		 * sửa đây
		 */
		// List<DonGiaTramErr> workLstFault = Lists.newArrayList();
		// Đọc một file XSL.
		try {
			File f = new File(filePath);

			// Đối tượng workbook cho file XSL.
			XSSFWorkbook workbook = new XSSFWorkbook(f);

			// Lấy ra sheet đầu tiên từ workbook
			XSSFSheet sheet = workbook.getSheetAt(0);
			DataFormatter dataFormatter = new DataFormatter();
			DateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
			int count = 0;
			for (Row row : sheet) {
				count++;
				ErrExcelDTO errExcelDTO = new ErrExcelDTO();
				if (count >= 12 && !isRowEmpty(row)) {
					lstErrExcelDTO = new ArrayList<>();
					System.out.println(count);

					boolean checkDoiTuongNhanNo = true;
					boolean checkmaNV = true;
					boolean checkDonVi = true;
					boolean checkSoPxk = true;
					boolean checkNgayChungTu = true;
					boolean checkTenVatTu = true;
					boolean checkMaVatTu = true;
					boolean checkMaTran = true;
					boolean checkHangMuc = true;
					boolean checkDVT = true;
					boolean checkGia = true;
					boolean checkKlxkSoLuong = true;
					boolean checkKlxkThanhTien = true;
					boolean checkSntSoLuong = true;
					boolean checkSntThanhTien = true;
					boolean checkShhtdmSoLuong = true;
					boolean checkShhtdmThanhTien = true;
					boolean checkSdtthSoLuong = true;
					boolean checkSdtthThanhTien = true;
					boolean checkSmmpbtcctSoLuong = true;
					boolean checkSmmpbtcctThanhTien = true;

					String doiTuongNhanNo = "";
					String maNv = "";
					String donVi = "";
					String soPxk = "";
					String ngayChungTu = "";
					String tenVatTu = "";
					String maVatTu = "";
					String maTram = "";
					String hangMuc = "";
					String dvt = "";
					String gia = "";
					String klxkSoLuong = "";
					String klxkThanhTien = "";
					String sntSoLuong = "";
					String sntThanhTien = "";
					String shhtdmSoLuong = "";
					String shhtdmThanhTien = "";
					String sdtthSoLuong = "";
					String sdtthThanhTien = "";
					String smmpbtcctSoLuong = "";
					String smmpbtcctThanhTien = "";
					String ghiChu = "";

					for (Cell cell : row) {
						switch (cell.getColumnIndex()) {
						case 1:

							doiTuongNhanNo = dataFormatter.formatCellValue(cell);
							break;

						// case 2:
						// maNv = dataFormatter.formatCellValue(cell);
						// // checkTenNV =
						// // testCheck(cell.getColumnIndex(),tenNV);
						// break;
						// case 3:
						// donVi = dataFormatter.formatCellValue(cell);
						// // checkDonVi =
						// // testCheck(cell.getColumnIndex(),donVi);
						// break;
						case 2:
							soPxk = dataFormatter.formatCellValue(cell);
							// checkChucDanh =
							// testCheck(cell.getColumnIndex(),chucDanh);
							break;
						case 3:
							ngayChungTu = dataFormatter.formatCellValue(cell);
							break;
						case 4:
							tenVatTu = dataFormatter.formatCellValue(cell);
							break;
						case 5:
							maVatTu = dataFormatter.formatCellValue(cell);
							// checkTenNV =
							// testCheck(cell.getColumnIndex(),tenNV);
							break;
						case 6:
							maTram = dataFormatter.formatCellValue(cell);
							// checkDonVi =
							// testCheck(cell.getColumnIndex(),donVi);
							break;
						case 7:
							hangMuc = dataFormatter.formatCellValue(cell);
							// checkChucDanh =
							// testCheck(cell.getColumnIndex(),chucDanh);
							break;
						case 8:
							dvt = dataFormatter.formatCellValue(cell);
							break;
						case 9:
							gia = dataFormatter.formatCellValue(cell);
							break;
						case 10:
							klxkSoLuong = dataFormatter.formatCellValue(cell);
							// checkTenNV =
							// testCheck(cell.getColumnIndex(),tenNV);
							break;
						case 11:
							klxkThanhTien = dataFormatter.formatCellValue(cell);
							// checkDonVi =
							// testCheck(cell.getColumnIndex(),donVi);
							break;
						case 12:
							sntSoLuong = dataFormatter.formatCellValue(cell);
							// checkChucDanh =
							// testCheck(cell.getColumnIndex(),chucDanh);
							break;
						case 13:
							sntThanhTien = dataFormatter.formatCellValue(cell);
							break;
						case 14:
							shhtdmSoLuong = dataFormatter.formatCellValue(cell);
							// checkDonVi =
							// testCheck(cell.getColumnIndex(),donVi);
							break;
						case 15:
							shhtdmThanhTien = dataFormatter.formatCellValue(cell);
							break;
						case 16:
							sdtthSoLuong = dataFormatter.formatCellValue(cell);
							break;
						case 17:
							sdtthThanhTien = dataFormatter.formatCellValue(cell);
							// checkTenNV =
							// testCheck(cell.getColumnIndex(),tenNV);
							break;
						case 18:
							smmpbtcctSoLuong = dataFormatter.formatCellValue(cell);
							// checkDonVi =
							// testCheck(cell.getColumnIndex(),donVi);
							break;
						case 19:
							smmpbtcctThanhTien = dataFormatter.formatCellValue(cell);
							// checkChucDanh =
							// testCheck(cell.getColumnIndex(),chucDanh);
							break;
						case 20:
							ghiChu = dataFormatter.formatCellValue(cell);
							break;

						default:
							break;
						}
					}
					// checkDataFromFileExel

					checkDoiTuongNhanNo = checkDataFromFileExel(doiTuongNhanNo, count, 1, errExcelDTO, null);
					// checkmaNV = checkDataFromFileExel(maNv, count, 2,
					// errExcelDTO, null);
					// checkDonVi = checkDataFromFileExel(donVi, count, 3,
					// errExcelDTO, null);
					checkSoPxk = checkDataFromFileExel(soPxk, count, 2, errExcelDTO, null);
					checkNgayChungTu = checkDataFromFileExel(ngayChungTu, count, 3, errExcelDTO, null);
					checkTenVatTu = checkDataFromFileExel(tenVatTu, count, 4, errExcelDTO, null);
					checkMaVatTu = checkDataFromFileExel(maVatTu, count, 5, errExcelDTO, null);
					checkMaTran = checkDataFromFileExel(maTram, count, 6, errExcelDTO, null);
					checkHangMuc = checkDataFromFileExel(hangMuc, count, 7, errExcelDTO, null);
					checkDVT = checkDataFromFileExel(dvt, count, 8, errExcelDTO, null);
					checkGia = checkDataFromFileExel(gia, count, 9, errExcelDTO, null);
					checkKlxkSoLuong = checkDataFromFileExel(klxkSoLuong, count, 10, errExcelDTO, null);
					checkKlxkThanhTien = checkDataFromFileExel(klxkThanhTien, count, 11, errExcelDTO, null);
					checkSntSoLuong = checkDataFromFileExel(sntSoLuong, count, 12, errExcelDTO, klxkSoLuong);
					checkSntThanhTien = checkDataFromFileExel(sntThanhTien, count, 13, errExcelDTO, null);
					checkShhtdmSoLuong = checkDataFromFileExel(shhtdmSoLuong, count, 14, errExcelDTO, klxkSoLuong);
					checkShhtdmThanhTien = checkDataFromFileExel(shhtdmThanhTien, count, 15, errExcelDTO, null);
					checkSdtthSoLuong = checkDataFromFileExel(sdtthSoLuong, count, 16, errExcelDTO, klxkSoLuong);
					checkSdtthThanhTien = checkDataFromFileExel(sdtthThanhTien, count, 17, errExcelDTO, null);
					checkSmmpbtcctSoLuong = checkDataFromFileExel(smmpbtcctSoLuong, count, 18, errExcelDTO,
							klxkSoLuong);
					checkSmmpbtcctThanhTien = checkDataFromFileExel(smmpbtcctThanhTien, count, 19, errExcelDTO, null);

					if (checkDoiTuongNhanNo && checkSoPxk && checkDonVi && checkmaNV && checkNgayChungTu
							&& checkTenVatTu && checkMaVatTu && checkMaTran && checkHangMuc && checkDVT && checkGia
							&& checkKlxkSoLuong && checkKlxkThanhTien && checkSntSoLuong && checkSntThanhTien
							&& checkShhtdmSoLuong && checkShhtdmSoLuong && checkShhtdmThanhTien && checkSdtthSoLuong
							&& checkSdtthThanhTien && checkSdtthThanhTien && checkSmmpbtcctSoLuong
							&& checkSmmpbtcctThanhTien) {
						TblQltsCongNoVatTuDTO congNoVatTuDTO = new TblQltsCongNoVatTuDTO();

						congNoVatTuDTO.setDoiTuongNhanNo(doiTuongNhanNo);
						// congNoVatTuDTO.setMaNv(maNv);
						char index = 0;
						if(StringUtils.isNotEmpty(soPxk)){
							 index = soPxk.charAt(0);
						}
						
						if ('M' == index) {
							congNoVatTuDTO.setDonVi("Chi nhánh khu vực 1");
						} else if ('H' == index) {
							congNoVatTuDTO.setDonVi("Chi nhánh khu vực 2");
						} else if ('B' == index) {
							congNoVatTuDTO.setDonVi("Chi nhánh khu vực 3");
						} else if ('T' == index) {
							congNoVatTuDTO.setDonVi("Chi nhánh khu vực 4");
						} else if ('N' == index) {
							congNoVatTuDTO.setDonVi("Chi nhánh khu vực 5");
						}

						congNoVatTuDTO.setSoPxk(soPxk);
						congNoVatTuDTO.setNgayChungTu(dt.parse(ngayChungTu));
						congNoVatTuDTO.setTenVatTu(tenVatTu);
						congNoVatTuDTO.setMaVatTu(maVatTu);
						congNoVatTuDTO.setMaTram(maTram);
						congNoVatTuDTO.setHangMuc(hangMuc);
						congNoVatTuDTO.setDvt(dvt);
						gia=convertValue(gia);
						congNoVatTuDTO.setGia(Float.parseFloat(gia));
						if (StringUtils.isEmpty(klxkSoLuong)) {
							congNoVatTuDTO.setKlxkSoLuong(Float.parseFloat("0"));
						} else {
							klxkSoLuong=convertValue(klxkSoLuong);
							congNoVatTuDTO.setKlxkSoLuong(Float.parseFloat(klxkSoLuong));
						}
						if (StringUtils.isEmpty(klxkThanhTien)) {
							congNoVatTuDTO.setKlxkThanhTien(Float.parseFloat("0"));
						} else {
							klxkThanhTien=convertValue(klxkThanhTien);
							congNoVatTuDTO.setKlxkThanhTien(Float.parseFloat(klxkThanhTien));
						}
						if (StringUtils.isEmpty(sntSoLuong)) {
							congNoVatTuDTO.setSntSoLuong(Float.parseFloat("0"));
						} else {
							sntSoLuong=convertValue(sntSoLuong);
							congNoVatTuDTO.setSntSoLuong(Float.parseFloat(sntSoLuong));
						}
						if (StringUtils.isEmpty(sntThanhTien)) {
							congNoVatTuDTO.setSntThanhTien(Float.parseFloat("0"));
						} else {
							sntThanhTien=convertValue(sntThanhTien);
							congNoVatTuDTO.setSntThanhTien(Float.parseFloat(sntThanhTien));
						}
						if (StringUtils.isEmpty(shhtdmSoLuong)) {
							congNoVatTuDTO.setShhtdmSoLuong(Float.parseFloat("0"));
						} else {
							shhtdmSoLuong=convertValue(shhtdmSoLuong);
							congNoVatTuDTO.setShhtdmSoLuong(Float.parseFloat(shhtdmSoLuong));
						}
						if (StringUtils.isEmpty(shhtdmThanhTien)) {
							congNoVatTuDTO.setShhtdmThanhTien(Float.parseFloat("0"));
						} else {
							shhtdmThanhTien=convertValue(shhtdmThanhTien);
							congNoVatTuDTO.setShhtdmThanhTien(Float.parseFloat(shhtdmThanhTien));
						}
						if (StringUtils.isEmpty(shhtdmSoLuong)) {
							congNoVatTuDTO.setShhtdmSoLuong(Float.parseFloat("0"));
						} else {
							shhtdmSoLuong=convertValue(shhtdmSoLuong);
							congNoVatTuDTO.setShhtdmSoLuong(Float.parseFloat(shhtdmSoLuong));
						}
						if (StringUtils.isEmpty(shhtdmThanhTien)) {
							congNoVatTuDTO.setShhtdmThanhTien(Float.parseFloat("0"));
						} else {
							shhtdmThanhTien=convertValue(shhtdmThanhTien);
							congNoVatTuDTO.setShhtdmThanhTien(Float.parseFloat(shhtdmThanhTien));
						}
						if (StringUtils.isEmpty(sdtthSoLuong)) {
							congNoVatTuDTO.setSdtthSoLuong(Float.parseFloat("0"));
						} else {
							sdtthSoLuong=convertValue(sdtthSoLuong);
							congNoVatTuDTO.setSdtthSoLuong(Float.parseFloat(sdtthSoLuong));
						}
						if (StringUtils.isEmpty(sdtthThanhTien)) {
							congNoVatTuDTO.setSdtthThanhTien(Float.parseFloat("0"));
						} else {
							sdtthThanhTien=convertValue(sdtthThanhTien);
							congNoVatTuDTO.setSdtthThanhTien(Float.parseFloat(sdtthThanhTien));
						}
						if (StringUtils.isEmpty(smmpbtcctSoLuong)) {
							congNoVatTuDTO.setSmmpbtcctSoLuong(Float.parseFloat("0"));
						} else {
							smmpbtcctSoLuong=convertValue(smmpbtcctSoLuong);
							congNoVatTuDTO.setSmmpbtcctSoLuong(Float.parseFloat(smmpbtcctSoLuong));
						}
						if (StringUtils.isEmpty(smmpbtcctThanhTien)) {
							congNoVatTuDTO.setSmmpbtcctThanhTien(Float.parseFloat("0"));
						} else {
							smmpbtcctThanhTien=convertValue(smmpbtcctThanhTien);
							congNoVatTuDTO.setSmmpbtcctThanhTien(Float.parseFloat(smmpbtcctThanhTien));
						}
						
						

						congNoVatTuDTO.setGhiChu(ghiChu);
						congNoVatTuDTO.setXoa(false);
						congNoVatTuDTO.setHoatDong(true);
//						congNoVatTuDTO.setType(Float.parseFloat("1"));
						// if(sntSoLuong.equals("0")&&shhtdmSoLuong.equals("0")&&sdtthSoLuong.equals("0")&&smmpbtcctSoLuong.equals("0")){
						// congNoVatTuDTO.setCheckDD(1l);
						// }else{
						// congNoVatTuDTO.setCheckDD(0l);
						// }
						tblQltsCongNoVatTuDAO.deleteObj(congNoVatTuDTO);
						workLstDone.add(congNoVatTuDTO);
						// save(congNoVatTuDTO);
					} else {
						ImportErrDTO importErrDTO = new ImportErrDTO();
						importErrDTO.setColumn1(doiTuongNhanNo);
						// importErrDTO.setColumn2(maNv);
						// importErrDTO.setColumn3(donVi);
						importErrDTO.setColumn2(soPxk);
						importErrDTO.setColumn3(ngayChungTu);
						importErrDTO.setColumn4(tenVatTu);
						importErrDTO.setColumn5(maVatTu);
						importErrDTO.setColumn6(maTram);
						importErrDTO.setColumn7(hangMuc);
						importErrDTO.setColumn8(dvt);
						importErrDTO.setColumn9(gia);
						importErrDTO.setColumn10(klxkSoLuong);
						importErrDTO.setColumn11(klxkThanhTien);
						importErrDTO.setColumn12(sntSoLuong);
						importErrDTO.setColumn13(sntThanhTien);
						importErrDTO.setColumn14(shhtdmSoLuong);
						importErrDTO.setColumn15(shhtdmThanhTien);
						importErrDTO.setColumn16(sdtthSoLuong);
						importErrDTO.setColumn17(sdtthThanhTien);
						importErrDTO.setColumn18(smmpbtcctSoLuong);
						importErrDTO.setColumn19(smmpbtcctThanhTien);
						importErrDTO.setColumn20(ghiChu);
						// lstErrExcelDTO
						importErrDTO.setLstErrorOrder(lstErrExcelDTO);
						lstFails.add(importErrDTO);
					}

				}
			}
			workbook.close();
			if (sheet.getLastRowNum() < 12) {
				throw new IllegalArgumentException("File import không có dữ liệu");
			} else {
				if (workLstDone.size() > 0) {
					saveList1(workLstDone);
					long tEnd = System.currentTimeMillis();
					lsThaoTacDTO.setChucNang("Import dữ liệu thi công nghiệm thu đối soát type B");
					lsThaoTacDTO.setMoTa("Import "+workLstDone.size()+" bản ghi dữ liệu thi công nghiệm thu đối soát type B ");
					lsThaoTacDTO.setThoiGianThucHien(tEnd-tStart);
					businessImpl.insertLSTT(lsThaoTacDTO,request);
				}
				if (lstFails.size() > 0) {
					return exportExcelError(lstFails,request);
				}
			}

			System.out.println(workLstDone.size());

		} catch (NullPointerException pointerException) {
			// pointerException.printStackTrace();
			log.error(pointerException.getMessage(), pointerException);
		} catch (Exception e) {
			// e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		// String filePathError = UEncrypt.encryptFileUploadPath(filePath);
		// TblQltsCongNoVatTuDTO objError=new TblQltsCongNoVatTuDTO();
		// objError.setLstCongNoErr(lstErrExcelDTO);
		// objError.setFilePathError(filePathError);

		// System.out.println(workLstFault.size());
		return null;
	}

	public String importCongNoVatTu1(String filePath,HttpServletRequest request) throws Exception {
		// public static void main(String[] args) throws IOException {

		long tStart = System.currentTimeMillis();
		/**
		 * sửa đây
		 */

		// lstErrorOrder = new ArrayList<>();
		List<TblQltsCongNoVatTuDTO> workLstDone = Lists.newArrayList();
		List<ImportErrDTO> lstFails = Lists.newArrayList();

		String Ten = "";
		/**
		 * sửa đây
		 */
		// List<DonGiaTramErr> workLstFault = Lists.newArrayList();
		// Đọc một file XSL.
		try {
			File f = new File(filePath);

			// Đối tượng workbook cho file XSL.
			XSSFWorkbook workbook = new XSSFWorkbook(f);

			// Lấy ra sheet đầu tiên từ workbook
			XSSFSheet sheet = workbook.getSheetAt(0);
			DataFormatter dataFormatter = new DataFormatter();
			DateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
			int count = 0;
			for (Row row : sheet) {
				count++;
				ErrExcelDTO errExcelDTO = new ErrExcelDTO();
				if (count >= 12 && !isRowEmpty(row)) {
					lstErrExcelDTO = new ArrayList<>();
					System.out.println(count);

					boolean checkDoiTuongNhanNo = true;
					boolean checkmaNV = true;
					boolean checkDonVi = true;
					boolean checkSoPxk = true;
					boolean checkNgayChungTu = true;
					boolean checkTenVatTu = true;
					boolean checkMaVatTu = true;
					boolean checkMaTran = true;
					boolean checkHangMuc = true;
					boolean checkDVT = true;
					boolean checkGia = true;
					boolean checkKlxkSoLuong = true;
					boolean checkKlxkThanhTien = true;
					// boolean checkSntSoLuong = true;
					// boolean checkSntThanhTien = true;
					// boolean checkShhtdmSoLuong = true;
					// boolean checkShhtdmThanhTien = true;
					// boolean checkSdtthSoLuong = true;
					// boolean checkSdtthThanhTien = true;
					// boolean checkSmmpbtcctSoLuong = true;
					// boolean checkSmmpbtcctThanhTien = true;

					String doiTuongNhanNo = "";
					String maNv = "";
					String donVi = "";
					String soPxk = "";
					String ngayChungTu = "";
					String tenVatTu = "";
					String maVatTu = "";
					String maTram = "";
					String hangMuc = "";
					String dvt = "";
					String gia = "";
					String klxkSoLuong = "";
					String klxkThanhTien = "";
					// String sntSoLuong = "";
					// String sntThanhTien = "";
					// String shhtdmSoLuong = "";
					// String shhtdmThanhTien = "";
					// String sdtthSoLuong = "";
					// String sdtthThanhTien = "";
					// String smmpbtcctSoLuong = "";
					// String smmpbtcctThanhTien = "";
					String ghiChu = "";

					for (Cell cell : row) {
						switch (cell.getColumnIndex()) {
						case 1:

							doiTuongNhanNo = dataFormatter.formatCellValue(cell);
							break;

						// case 2:
						// maNv = dataFormatter.formatCellValue(cell);
						// // checkTenNV =
						// // testCheck(cell.getColumnIndex(),tenNV);
						// break;
						// case 3:
						// donVi = dataFormatter.formatCellValue(cell);
						// // checkDonVi =
						// // testCheck(cell.getColumnIndex(),donVi);
						// break;
						case 2:
							soPxk = dataFormatter.formatCellValue(cell);
							// checkChucDanh =
							// testCheck(cell.getColumnIndex(),chucDanh);
							break;
						case 3:
							ngayChungTu = dataFormatter.formatCellValue(cell);
							break;
						case 4:
							tenVatTu = dataFormatter.formatCellValue(cell);
							break;
						case 5:
							maVatTu = dataFormatter.formatCellValue(cell);
							// checkTenNV =
							// testCheck(cell.getColumnIndex(),tenNV);
							break;
						case 6:
							maTram = dataFormatter.formatCellValue(cell);
							// checkDonVi =
							// testCheck(cell.getColumnIndex(),donVi);
							break;
						case 7:
							hangMuc = dataFormatter.formatCellValue(cell);
							// checkChucDanh =
							// testCheck(cell.getColumnIndex(),chucDanh);
							break;
						case 8:
							dvt = dataFormatter.formatCellValue(cell);
							break;
						case 9:
							gia = dataFormatter.formatCellValue(cell);
							break;
						case 10:
							klxkSoLuong = dataFormatter.formatCellValue(cell);
							// checkTenNV =
							// testCheck(cell.getColumnIndex(),tenNV);
							break;
						case 11:
							klxkThanhTien = dataFormatter.formatCellValue(cell);
							// checkDonVi =
							// testCheck(cell.getColumnIndex(),donVi);
							break;
						// case 12:
						// sntSoLuong = dataFormatter.formatCellValue(cell);
						// // checkChucDanh =
						// // testCheck(cell.getColumnIndex(),chucDanh);
						// break;
						// case 13:
						// sntThanhTien = dataFormatter.formatCellValue(cell);
						// break;
						// case 14:
						// shhtdmSoLuong = dataFormatter.formatCellValue(cell);
						// // checkDonVi =
						// // testCheck(cell.getColumnIndex(),donVi);
						// break;
						// case 15:
						// shhtdmThanhTien =
						// dataFormatter.formatCellValue(cell);
						// break;
						// case 16:
						// sdtthSoLuong = dataFormatter.formatCellValue(cell);
						// break;
						// case 17:
						// sdtthThanhTien = dataFormatter.formatCellValue(cell);
						// // checkTenNV =
						// // testCheck(cell.getColumnIndex(),tenNV);
						// break;
						// case 18:
						// smmpbtcctSoLuong =
						// dataFormatter.formatCellValue(cell);
						// // checkDonVi =
						// // testCheck(cell.getColumnIndex(),donVi);
						// break;
						// case 19:
						// smmpbtcctThanhTien =
						// dataFormatter.formatCellValue(cell);
						// // checkChucDanh =
						// // testCheck(cell.getColumnIndex(),chucDanh);
						// break;
						case 12:
							ghiChu = dataFormatter.formatCellValue(cell);
							break;

						default:
							break;
						}
					}

					checkDoiTuongNhanNo = checkDataFromFileExel(doiTuongNhanNo, count, 1, errExcelDTO, null);
					// checkmaNV = checkDataFromFileExel(maNv, count, 2,
					// errExcelDTO, null);
					// checkDonVi = checkDataFromFileExel(donVi, count, 3,
					// errExcelDTO, null);
					checkSoPxk = checkDataFromFileExel(soPxk, count, 2, errExcelDTO, null);
					checkNgayChungTu = checkDataFromFileExel(ngayChungTu, count, 3, errExcelDTO, null);
					checkTenVatTu = checkDataFromFileExel(tenVatTu, count, 4, errExcelDTO, null);
					checkMaVatTu = checkDataFromFileExel(maVatTu, count, 5, errExcelDTO, null);
					checkMaTran = checkDataFromFileExel(maTram, count, 6, errExcelDTO, null);
					checkHangMuc = checkDataFromFileExel(hangMuc, count, 7, errExcelDTO, null);
					checkDVT = checkDataFromFileExel(dvt, count, 8, errExcelDTO, null);
					checkGia = checkDataFromFileExel(gia, count, 9, errExcelDTO, null);
					checkKlxkSoLuong = checkDataFromFileExel(klxkSoLuong, count, 10, errExcelDTO, null);
					checkKlxkThanhTien = checkDataFromFileExel(klxkThanhTien, count, 11, errExcelDTO, null);
					// checkSntSoLuong = checkDataFromFileExel(sntSoLuong,
					// count, 12, errExcelDTO, klxkSoLuong);
					// checkSntThanhTien =
					// checkDataFromFileExel(sntThanhTien, count, 13,
					// errExcelDTO, null);
					// checkShhtdmSoLuong =
					// checkDataFromFileExel(shhtdmSoLuong, count, 14,
					// errExcelDTO, klxkSoLuong);
					// checkShhtdmThanhTien =
					// checkDataFromFileExel(shhtdmThanhTien, count, 15,
					// errExcelDTO, null);
					// checkSdtthSoLuong =
					// checkDataFromFileExel(sdtthSoLuong, count, 16,
					// errExcelDTO, klxkSoLuong);
					// checkSdtthThanhTien =
					// checkDataFromFileExel(sdtthThanhTien, count, 17,
					// errExcelDTO, null);
					// checkSmmpbtcctSoLuong =
					// checkDataFromFileExel(smmpbtcctSoLuong, count, 18,
					// errExcelDTO,
					// klxkSoLuong);
					// checkSmmpbtcctThanhTien =
					// checkDataFromFileExel(smmpbtcctThanhTien, count, 19,
					// errExcelDTO,
					// null);

					if (checkDoiTuongNhanNo && checkSoPxk && checkDonVi && checkmaNV && checkNgayChungTu
							&& checkTenVatTu && checkMaVatTu && checkMaTran && checkHangMuc && checkDVT && checkGia
							&& checkKlxkSoLuong && checkKlxkThanhTien
					// && checkSntSoLuong && checkSntThanhTien
					// && checkShhtdmSoLuong && checkShhtdmSoLuong &&
					// checkShhtdmThanhTien && checkSdtthSoLuong
					// && checkSdtthThanhTien && checkSdtthThanhTien &&
					// checkSmmpbtcctSoLuong
					// && checkSmmpbtcctThanhTien
					) {
						TblQltsCongNoVatTuDTO congNoVatTuDTO = new TblQltsCongNoVatTuDTO();

						congNoVatTuDTO.setDoiTuongNhanNo(doiTuongNhanNo);
						// congNoVatTuDTO.setMaNv(maNv);
						char index = soPxk.charAt(0);
						if ('M' == index) {
							congNoVatTuDTO.setDonVi("Chi nhánh khu vực 1");
						} else if ('H' == index) {
							congNoVatTuDTO.setDonVi("Chi nhánh khu vực 2");
						} else if ('B' == index) {
							congNoVatTuDTO.setDonVi("Chi nhánh khu vực 3");
						} else if ('T' == index) {
							congNoVatTuDTO.setDonVi("Chi nhánh khu vực 4");
						} else if ('N' == index) {
							congNoVatTuDTO.setDonVi("Chi nhánh khu vực 5");
						}

						congNoVatTuDTO.setSoPxk(soPxk);
						if(StringUtils.isNotEmpty(ngayChungTu)){
							congNoVatTuDTO.setNgayChungTu(dt.parse(ngayChungTu));
						}
					
						congNoVatTuDTO.setTenVatTu(tenVatTu);
						congNoVatTuDTO.setMaVatTu(maVatTu);
						congNoVatTuDTO.setMaTram(maTram);
						congNoVatTuDTO.setHangMuc(hangMuc);
						congNoVatTuDTO.setDvt(dvt);
						gia=convertValue(gia);
						congNoVatTuDTO.setGia(Float.parseFloat(gia));
						if (StringUtils.isEmpty(klxkSoLuong)) {
							
							congNoVatTuDTO.setKlxkSoLuong(Float.parseFloat("0"));
						} else {
							klxkSoLuong=convertValue(klxkSoLuong);
							congNoVatTuDTO.setKlxkSoLuong(Float.parseFloat(klxkSoLuong));
						}
						if (StringUtils.isEmpty(klxkThanhTien)) {
							congNoVatTuDTO.setKlxkThanhTien(Float.parseFloat("0"));
						} else {
							klxkThanhTien=convertValue(klxkThanhTien);
							congNoVatTuDTO.setKlxkThanhTien(Float.parseFloat(klxkThanhTien));
						}
						// if (StringUtils.isEmpty(sntSoLuong)) {
						// congNoVatTuDTO.setSntSoLuong(Float.parseFloat("0"));
						// } else {
						// congNoVatTuDTO.setSntSoLuong(Float.parseFloat(sntSoLuong));
						// }
						// if (StringUtils.isEmpty(sntThanhTien)) {
						// congNoVatTuDTO.setSntThanhTien(Float.parseFloat("0"));
						// } else {
						// congNoVatTuDTO.setSntThanhTien(Float.parseFloat(sntThanhTien));
						// }
						// if (StringUtils.isEmpty(shhtdmSoLuong)) {
						// congNoVatTuDTO.setShhtdmSoLuong(Float.parseFloat("0"));
						// } else {
						// congNoVatTuDTO.setShhtdmSoLuong(Float.parseFloat(shhtdmSoLuong));
						// }
						// if (StringUtils.isEmpty(shhtdmThanhTien)) {
						// congNoVatTuDTO.setShhtdmThanhTien(Float.parseFloat("0"));
						// } else {
						// congNoVatTuDTO.setShhtdmThanhTien(Float.parseFloat(shhtdmThanhTien));
						// }
						// if (StringUtils.isEmpty(shhtdmSoLuong)) {
						// congNoVatTuDTO.setShhtdmSoLuong(Float.parseFloat("0"));
						// } else {
						// congNoVatTuDTO.setShhtdmSoLuong(Float.parseFloat(shhtdmSoLuong));
						// }
						// if (StringUtils.isEmpty(shhtdmThanhTien)) {
						// congNoVatTuDTO.setShhtdmThanhTien(Float.parseFloat("0"));
						// } else {
						// congNoVatTuDTO.setShhtdmThanhTien(Float.parseFloat(shhtdmThanhTien));
						// }
						// if (StringUtils.isEmpty(sdtthSoLuong)) {
						// congNoVatTuDTO.setSdtthSoLuong(Float.parseFloat("0"));
						// } else {
						// congNoVatTuDTO.setSdtthSoLuong(Float.parseFloat(sdtthSoLuong));
						// }
						// if (StringUtils.isEmpty(sdtthThanhTien)) {
						// congNoVatTuDTO.setSdtthThanhTien(Float.parseFloat("0"));
						// } else {
						// congNoVatTuDTO.setSdtthThanhTien(Float.parseFloat(sdtthThanhTien));
						// }
						// if (StringUtils.isEmpty(smmpbtcctSoLuong)) {
						// congNoVatTuDTO.setSmmpbtcctSoLuong(Float.parseFloat("0"));
						// } else {
						// congNoVatTuDTO.setSmmpbtcctSoLuong(Float.parseFloat(smmpbtcctSoLuong));
						// }
						// if (StringUtils.isEmpty(smmpbtcctThanhTien)) {
						// congNoVatTuDTO.setSmmpbtcctThanhTien(Float.parseFloat("0"));
						// } else {
						// congNoVatTuDTO.setSmmpbtcctThanhTien(Float.parseFloat(smmpbtcctThanhTien));
						// }

						congNoVatTuDTO.setGhiChu(ghiChu);
						congNoVatTuDTO.setXoa(false);
						congNoVatTuDTO.setHoatDong(true);
//						congNoVatTuDTO.setType(Float.parseFloat("0"));
						tblQltsCongNoVatTuDAO.deleteObj(congNoVatTuDTO);
						workLstDone.add(congNoVatTuDTO);
						// save(congNoVatTuDTO);
					} else {
						ImportErrDTO importErrDTO = new ImportErrDTO();
						importErrDTO.setColumn1(doiTuongNhanNo);
						// importErrDTO.setColumn2(maNv);
						// importErrDTO.setColumn3(donVi);
						importErrDTO.setColumn2(soPxk);
						importErrDTO.setColumn3(ngayChungTu);
						importErrDTO.setColumn4(tenVatTu);
						importErrDTO.setColumn5(maVatTu);
						importErrDTO.setColumn6(maTram);
						importErrDTO.setColumn7(hangMuc);
						importErrDTO.setColumn8(dvt);
						importErrDTO.setColumn9(gia);
						importErrDTO.setColumn10(klxkSoLuong);
						importErrDTO.setColumn11(klxkThanhTien);
						// importErrDTO.setColumn12(sntSoLuong);
						// importErrDTO.setColumn13(sntThanhTien);
						// importErrDTO.setColumn14(shhtdmSoLuong);
						// importErrDTO.setColumn15(shhtdmThanhTien);
						// importErrDTO.setColumn16(sdtthSoLuong);
						// importErrDTO.setColumn17(sdtthThanhTien);
						// importErrDTO.setColumn18(smmpbtcctSoLuong);
						// importErrDTO.setColumn19(smmpbtcctThanhTien);
						importErrDTO.setColumn12(ghiChu);
						// lstErrExcelDTO
						importErrDTO.setLstErrorOrder(lstErrExcelDTO);
						lstFails.add(importErrDTO);
					}

				}
			}
			workbook.close();
			if (workLstDone.size() > 0) {
				saveList1(workLstDone);
				long tEnd = System.currentTimeMillis();
				lsThaoTacDTO.setChucNang("Import dữ liệu PXK type B");
				lsThaoTacDTO.setMoTa("Import "+workLstDone.size()+" bản ghi dữ liệu PXK type B ");
				lsThaoTacDTO.setThoiGianThucHien(tEnd-tStart);
				businessImpl.insertLSTT(lsThaoTacDTO,request);
			}
			if (lstFails.size() > 0) {
				return exportExcelError1(lstFails);
			}
			System.out.println(workLstDone.size());

		} catch (NullPointerException pointerException) {
			// pointerException.printStackTrace();
			log.error(pointerException.getMessage(), pointerException);
		} catch (Exception e) {
			// e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		// String filePathError = UEncrypt.encryptFileUploadPath(filePath);
		// TblQltsCongNoVatTuDTO objError=new TblQltsCongNoVatTuDTO();
		// objError.setLstCongNoErr(lstErrExcelDTO);
		// objError.setFilePathError(filePathError);

		// System.out.println(workLstFault.size());
		return null;
	}

	public String convertValue(String value){
		String val=value.replaceAll(",", "");
		return val;
	}
	public boolean checkDataFromFileExel(String data, int rowIndex, int columnIndex, ErrExcelDTO errExcelDTO,
			String data2) {
		errExcelDTO = new ErrExcelDTO();
		switch (columnIndex) {
		case 1:
			// if (StringUtils.isEmpty(data)) {
			// errExcelDTO.setLineError(String.valueOf(rowIndex));
			// errExcelDTO.setColumnError(String.valueOf(columnIndex));
			// errExcelDTO.setDetailError("Đối tượng nhận nợ đang để trống");
			// lstErrExcelDTO.add(errExcelDTO);
			// return false;
			// }
			break;
		// case 3:
		// if (StringUtils.isEmpty(data)) {
		// errExcelDTO.setLineError(String.valueOf(rowIndex));
		// errExcelDTO.setColumnError(String.valueOf(columnIndex));
		// errExcelDTO.setDetailError("Đơn vị đang để trống");
		// lstErrExcelDTO.add(errExcelDTO);
		// return false;
		// }
		// break;
		// case 2:
		// if (StringUtils.isEmpty(data)) {
		// errExcelDTO.setLineError(String.valueOf(rowIndex));
		// errExcelDTO.setColumnError(String.valueOf(columnIndex));
		// errExcelDTO.setDetailError("Mã nhân viên đang để trống");
		// lstErrExcelDTO.add(errExcelDTO);
		// return false;
		// } else if (nhanVienDAO.checkExistMaNvByMaNv(data) == (long)0) {
		// errExcelDTO.setLineError(String.valueOf(rowIndex));
		// errExcelDTO.setColumnError(String.valueOf(columnIndex));
		// errExcelDTO.setDetailError("Mã nhân viên không tồn tại");
		// lstErrExcelDTO.add(errExcelDTO);
		// return false;
		// }
		// break;
		case 2:
			if (StringUtils.isEmpty(data)) {
//				errExcelDTO.setLineError(String.valueOf(rowIndex));
//				errExcelDTO.setColumnError(String.valueOf(columnIndex));
//				errExcelDTO.setDetailError("Số PXK đang để trống");
//				lstErrExcelDTO.add(errExcelDTO);
//				return false;
			}else if(data.length()>2000){
				errExcelDTO.setLineError(String.valueOf(rowIndex));
				errExcelDTO.setColumnError(String.valueOf(columnIndex));
				errExcelDTO.setDetailError("Số PXK > 2000 kí tự");
				lstErrExcelDTO.add(errExcelDTO);
				return false;
			}
			break;
		case 3:
			if (StringUtils.isEmpty(data)) {
//				errExcelDTO.setLineError(String.valueOf(rowIndex));
//				errExcelDTO.setColumnError(String.valueOf(columnIndex));
//				errExcelDTO.setDetailError("Ngày chứng từ đang để trống");
//				lstErrExcelDTO.add(errExcelDTO);
//				return false;
			} else if (!ValidateUtils.isDate(data, fomat)) {
				errExcelDTO.setLineError(String.valueOf(rowIndex));
				errExcelDTO.setColumnError(String.valueOf(columnIndex));
				errExcelDTO.setDetailError("Ngày chứng từ không hợp lệ");
				lstErrExcelDTO.add(errExcelDTO);
				return false;
			}
			break;
		case 4:
			if (StringUtils.isEmpty(data)) {
//				errExcelDTO.setLineError(String.valueOf(rowIndex));
//				errExcelDTO.setColumnError(String.valueOf(columnIndex));
//				errExcelDTO.setDetailError("Tên vật tư đang để trống");
//				lstErrExcelDTO.add(errExcelDTO);
//				return false;
			}
			break;
		case 5:
			if (StringUtils.isEmpty(data)) {
//				errExcelDTO.setLineError(String.valueOf(rowIndex));
//				errExcelDTO.setColumnError(String.valueOf(columnIndex));
//				errExcelDTO.setDetailError("Mã vật tư đang để trống");
//				lstErrExcelDTO.add(errExcelDTO);
//				return false;
			}
			break;
		case 6:
			if (StringUtils.isEmpty(data)) {
//				errExcelDTO.setLineError(String.valueOf(rowIndex));
//				errExcelDTO.setColumnError(String.valueOf(columnIndex));
//				errExcelDTO.setDetailError("Mã trạm đang để trống");
//				lstErrExcelDTO.add(errExcelDTO);
//				return false;
			}
			break;
		case 7:
			if (StringUtils.isEmpty(data)) {
//				errExcelDTO.setLineError(String.valueOf(rowIndex));
//				errExcelDTO.setColumnError(String.valueOf(columnIndex));
//				errExcelDTO.setDetailError("Hạng mục đang để trống");
//				lstErrExcelDTO.add(errExcelDTO);
//				return false;
			}
			break;
		case 8:
			if (StringUtils.isEmpty(data)) {
//				errExcelDTO.setLineError(String.valueOf(rowIndex));
//				errExcelDTO.setColumnError(String.valueOf(columnIndex));
//				errExcelDTO.setDetailError("Đơn vị tính đang để trống");
//				lstErrExcelDTO.add(errExcelDTO);
//				return false;
			}
			break;
		case 9:
			if (StringUtils.isEmpty(data)) {
//				errExcelDTO.setLineError(String.valueOf(rowIndex));
//				errExcelDTO.setColumnError(String.valueOf(columnIndex));
//				errExcelDTO.setDetailError("Giá đang để trống");
//				lstErrExcelDTO.add(errExcelDTO);
//				return false;
			} else if (!ValidateUtils.isFloat(data)) {
				if (!ValidateUtils.isFloat(convertValue(data))) {
					errExcelDTO.setLineError(String.valueOf(rowIndex));
					errExcelDTO.setColumnError(String.valueOf(columnIndex));
					errExcelDTO.setDetailError("Giá không đúng định dạng");
					lstErrExcelDTO.add(errExcelDTO);
					return false;
				}
			}
			break;
		case 10:
			if (StringUtils.isNotEmpty(data)) {
					if (!ValidateUtils.isFloat(convertValue(data))) {
						errExcelDTO.setLineError(String.valueOf(rowIndex));
						errExcelDTO.setColumnError(String.valueOf(columnIndex));
						errExcelDTO.setDetailError("Số lượng (khối lượng suất kho) không đúng định dạng");
						lstErrExcelDTO.add(errExcelDTO);
						return false;
				}
			}
			break;
		case 11:
			if (StringUtils.isNotEmpty(data)) {
					if (!ValidateUtils.isFloat(convertValue(data))) {
						errExcelDTO.setLineError(String.valueOf(rowIndex));
						errExcelDTO.setColumnError(String.valueOf(columnIndex));
						errExcelDTO.setDetailError("Thành tiền (khối lượng suất kho) không đúng định dạng");
						lstErrExcelDTO.add(errExcelDTO);
						return false;
					}
			}

			break;
		case 12:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isFloat(convertValue(data))) {
					errExcelDTO.setLineError(String.valueOf(rowIndex));
					errExcelDTO.setColumnError(String.valueOf(columnIndex));
					errExcelDTO.setDetailError("Số lượng (số nghiệm thu) không đúng định dạng");
					lstErrExcelDTO.add(errExcelDTO);
					return false;
				}
				if (Float.parseFloat(data) > Float.parseFloat(data2)) {
					errExcelDTO.setLineError(String.valueOf(rowIndex));
					errExcelDTO.setColumnError(String.valueOf(columnIndex));
					errExcelDTO.setDetailError("Số lượng (số nghiệm thu) phải nhỏ hơn số lượng xuất kho");
					lstErrExcelDTO.add(errExcelDTO);
					return false;
				}
			}

			break;
		case 13:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isFloat(convertValue(data))) {
					errExcelDTO.setLineError(String.valueOf(rowIndex));
					errExcelDTO.setColumnError(String.valueOf(columnIndex));
					errExcelDTO.setDetailError("Thành tiền (số nghiệm thu) không đúng định dạng");
					lstErrExcelDTO.add(errExcelDTO);
					return false;
				}
			}
			break;
		case 14:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isFloat(convertValue(data))) {
					errExcelDTO.setLineError(String.valueOf(rowIndex));
					errExcelDTO.setColumnError(String.valueOf(columnIndex));
					errExcelDTO.setDetailError("Số lượng (số hao hụt theo định mức) không đúng định dạng");
					lstErrExcelDTO.add(errExcelDTO);
					return false;
				}
				if (Float.parseFloat(data) > Float.parseFloat(data2)) {
					errExcelDTO.setLineError(String.valueOf(rowIndex));
					errExcelDTO.setColumnError(String.valueOf(columnIndex));
					errExcelDTO.setDetailError("Số lượng (số hao hụt theo định mức) phải nhỏ hơn số lượng xuất kho");
					lstErrExcelDTO.add(errExcelDTO);
					return false;
				}
			}
			break;
		case 15:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isFloat(convertValue(data))) {
					errExcelDTO.setLineError(String.valueOf(rowIndex));
					errExcelDTO.setColumnError(String.valueOf(columnIndex));
					errExcelDTO.setDetailError("Thành tiền (số hao hụt theo định mức) không đúng định dạng");
					lstErrExcelDTO.add(errExcelDTO);
					return false;
				}
			}

			break;
		case 16:

			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isFloat(convertValue(data))) {
					errExcelDTO.setLineError(String.valueOf(rowIndex));
					errExcelDTO.setColumnError(String.valueOf(columnIndex));
					errExcelDTO.setDetailError("Số lượng (số đã thu hồi) không đúng định dạng");
					lstErrExcelDTO.add(errExcelDTO);
					return false;
				}
				if (Float.parseFloat(data) > Float.parseFloat(data2)) {
					errExcelDTO.setLineError(String.valueOf(rowIndex));
					errExcelDTO.setColumnError(String.valueOf(columnIndex));
					errExcelDTO.setDetailError("Số lượng (số đã thu hồi) phải nhỏ hơn số lượng xuất kho");
					lstErrExcelDTO.add(errExcelDTO);
					return false;
				}
			}
			break;
		case 17:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isFloat(convertValue(data))) {
					errExcelDTO.setLineError(String.valueOf(rowIndex));
					errExcelDTO.setColumnError(String.valueOf(columnIndex));
					errExcelDTO.setDetailError("Thành tiền (số đã thu hồi) không đúng định dạng");
					lstErrExcelDTO.add(errExcelDTO);
					return false;
				}
			}

			break;
		case 18:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isFloat(convertValue(data))) {
					errExcelDTO.setLineError(String.valueOf(rowIndex));
					errExcelDTO.setColumnError(String.valueOf(columnIndex));
					errExcelDTO.setDetailError("Số lượng (số mất mát phải bồi thường) không đúng định dạng");
					lstErrExcelDTO.add(errExcelDTO);
					return false;
				}
				if (Float.parseFloat(data) > Float.parseFloat(data2)) {
					errExcelDTO.setLineError(String.valueOf(rowIndex));
					errExcelDTO.setColumnError(String.valueOf(columnIndex));
					errExcelDTO.setDetailError("Số lượng (số mất mát phải bồi thường) phải nhỏ hơn số lượng xuất kho");
					lstErrExcelDTO.add(errExcelDTO);
					return false;
				}

			}

			break;
		case 19:

			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isFloat(convertValue(data))) {
					errExcelDTO.setLineError(String.valueOf(rowIndex));
					errExcelDTO.setColumnError(String.valueOf(columnIndex));
					errExcelDTO.setDetailError("Thành tiền (số mất mát phải bồi thường) không đúng định dạng");
					lstErrExcelDTO.add(errExcelDTO);
					return false;
				}
			}

			break;

		default:
			break;
		}

		return true;
	}

	public String exportExcelGrid(TblQltsCongNoVatTuDTO lst,HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//		lst.setType(Float.parseFloat("1"));
		List<TblQltsCongNoVatTuDTO> obj = tblQltsCongNoVatTuDAO.getAllQlCongNo(lst);
		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream file = new BufferedInputStream(new FileInputStream(filePath + "ExportChiTietCongNo.xlsx"));
			// String filePath =
			// UEncrypt.decryptFileUploadPath(obj.getFilePathError());
			// InputStream file = new BufferedInputStream(new
			// FileInputStream(filePath));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);
			// set style
			XSSFCellStyle style = workbook.createCellStyle();
			XSSFCellStyle styleNumber = workbook.createCellStyle();
			XSSFFont hSSFFont = workbook.createFont();
			hSSFFont.setFontName("Times New Roman");
			style.setFont(hSSFFont);
			style.setBorderBottom(BorderStyle.THIN);
			style.setBorderTop(BorderStyle.THIN);
			style.setBorderRight(BorderStyle.THIN);
			style.setBorderLeft(BorderStyle.THIN);
			style.setWrapText(true);
			styleNumber.setBorderBottom(BorderStyle.THIN);
			styleNumber.setBorderTop(BorderStyle.THIN);
			styleNumber.setBorderRight(BorderStyle.THIN);
			styleNumber.setBorderLeft(BorderStyle.THIN);
			styleNumber.setWrapText(true);
			styleNumber.setAlignment(CellStyle.ALIGN_RIGHT);
			
			int rownum = 12;
			for (int i = 0; i < obj.size(); i++) {
				StringBuilder err = new StringBuilder();
				XSSFRow row = sheet.createRow(rownum);
				XSSFCell cell0 = row.createCell(0);
				cell0.setCellValue(i + 1);
				cell0.setCellStyle(style);
				rownum++;

				XSSFCell cell1 = row.createCell(1);
				cell1.setCellValue(obj.get(i).getDoiTuongNhanNo());

				// XSSFCell cell2 = row.createCell(2);
				// cell2.setCellValue(obj.get(i).getMaNv());

				XSSFCell cell2 = row.createCell(2);
				cell2.setCellValue(obj.get(i).getDonVi());
				// char index=obj.get(i).getSoPxk().charAt(0);
				// if('M'==index){
				// cell3.setCellValue("Chi nhánh khu vực 1");
				// }else if('H'==index){
				// cell3.setCellValue("Chi nhánh khu vực 2");
				// }else if('B'==index){
				// cell3.setCellValue("Chi nhánh khu vực 3");
				// }else if('T'==index){
				// cell3.setCellValue("Chi nhánh khu vực 4");
				// }else if('N'==index){
				// cell3.setCellValue("Chi nhánh khu vực 5");
				// }

				XSSFCell cell3 = row.createCell(3);
				cell3.setCellValue(obj.get(i).getSoPxk());

				XSSFCell cell4 = row.createCell(4);

				cell4.setCellValue(sdf.format(obj.get(i).getNgayChungTu()));

				XSSFCell cell5 = row.createCell(5);
				cell5.setCellValue(obj.get(i).getTenVatTu());

				XSSFCell cell6 = row.createCell(6);
				cell6.setCellValue(obj.get(i).getMaVatTu());

				XSSFCell cell7 = row.createCell(7);
				cell7.setCellValue(obj.get(i).getMaTram());

				XSSFCell cell8 = row.createCell(8);
				cell8.setCellValue(obj.get(i).getHangMuc());

				XSSFCell cell9 = row.createCell(9);
				cell9.setCellValue(obj.get(i).getDvt());

				XSSFCell cell10 = row.createCell(10);
				if(obj.get(i).getGia()==null){
					obj.get(i).setGia((float)0);
				}
				cell10.setCellValue(obj.get(i).getGia());

				XSSFCell cell11 = row.createCell(11);
				if(obj.get(i).getKlxkSoLuong()==null){
					obj.get(i).setKlxkSoLuong((float)0);
				}
				cell11.setCellValue(obj.get(i).getKlxkSoLuong());

				XSSFCell cell12 = row.createCell(12);
				if(obj.get(i).getKlxkThanhTien()==null){
					obj.get(i).setKlxkThanhTien((float)0);
				}
				cell12.setCellValue(obj.get(i).getKlxkThanhTien());

				XSSFCell cell13 = row.createCell(13);
				if(obj.get(i).getSntSoLuong()==null){
					obj.get(i).setSntSoLuong((float)0);
				}
				cell13.setCellValue(obj.get(i).getSntSoLuong());

				XSSFCell cell14 = row.createCell(14);
				if(obj.get(i).getSntThanhTien()==null){
					obj.get(i).setSntThanhTien((float)0);
				}
				cell14.setCellValue(obj.get(i).getSntThanhTien());

				XSSFCell cell15 = row.createCell(15);
				if(obj.get(i).getShhtdmSoLuong()==null){
					obj.get(i).setShhtdmSoLuong((float)0);
				}
				cell15.setCellValue(obj.get(i).getShhtdmSoLuong());

				XSSFCell cell16 = row.createCell(16);
				if(obj.get(i).getShhtdmThanhTien()==null){
					obj.get(i).setShhtdmThanhTien((float)0);
				}
				cell16.setCellValue(obj.get(i).getShhtdmThanhTien());

				XSSFCell cell17 = row.createCell(17);
				if(obj.get(i).getSdtthSoLuong()==null){
					obj.get(i).setSdtthSoLuong((float)0);
				}
				cell17.setCellValue(obj.get(i).getSdtthSoLuong());

				XSSFCell cell18 = row.createCell(18);
				if(obj.get(i).getSdtthThanhTien()==null){
					obj.get(i).setSdtthThanhTien((float)0);
				}
				cell18.setCellValue(obj.get(i).getSdtthThanhTien());

				XSSFCell cell19 = row.createCell(19);
				if(obj.get(i).getSmmpbtcctSoLuong()==null){
					obj.get(i).setSmmpbtcctSoLuong((float)0);
				}
				cell19.setCellValue(obj.get(i).getSmmpbtcctSoLuong());

				XSSFCell cell20 = row.createCell(20);
				if(obj.get(i).getSmmpbtcctThanhTien()==null){
					obj.get(i).setSmmpbtcctThanhTien((float)0);
				}
				cell20.setCellValue(obj.get(i).getSmmpbtcctThanhTien());

				XSSFCell cell21 = row.createCell(21);
				cell21.setCellValue(obj.get(i).getGhiChu());

				// XSSFCell cell1 = row.createCell(1);
				// cell1.setCellValue(obj.get(i).getDoiTuongNhanNo());
				//
				// XSSFCell cell2 = row.createCell(2);
				// cell2.setCellValue(obj.get(i).getMaNv());
				//
				// XSSFCell cell3 = row.createCell(3);
				// cell3.setCellValue(obj.get(i).getDonVi());
				// // char index=obj.get(i).getSoPxk().charAt(0);
				// // if('M'==index){
				// // cell3.setCellValue("Chi nhánh khu vực 1");
				// // }else if('H'==index){
				// // cell3.setCellValue("Chi nhánh khu vực 2");
				// // }else if('B'==index){
				// // cell3.setCellValue("Chi nhánh khu vực 3");
				// // }else if('T'==index){
				// // cell3.setCellValue("Chi nhánh khu vực 4");
				// // }else if('N'==index){
				// // cell3.setCellValue("Chi nhánh khu vực 5");
				// // }
				//
				// XSSFCell cell4 = row.createCell(4);
				// cell4.setCellValue(obj.get(i).getSoPxk());
				//
				// XSSFCell cell5 = row.createCell(5);
				//
				// cell5.setCellValue(sdf.format(obj.get(i).getNgayChungTu()));
				//
				// XSSFCell cell6 = row.createCell(6);
				// cell6.setCellValue(obj.get(i).getTenVatTu());
				//
				// XSSFCell cell7 = row.createCell(7);
				// cell7.setCellValue(obj.get(i).getMaVatTu());
				//
				// XSSFCell cell8 = row.createCell(8);
				// cell8.setCellValue(obj.get(i).getMaTram());
				//
				// XSSFCell cell9 = row.createCell(9);
				// cell9.setCellValue(obj.get(i).getHangMuc());
				//
				// XSSFCell cell10 = row.createCell(10);
				// cell10.setCellValue(obj.get(i).getDvt());
				//
				// XSSFCell cell11 = row.createCell(11);
				// cell11.setCellValue(obj.get(i).getGia());
				//
				// XSSFCell cell12 = row.createCell(12);
				// cell12.setCellValue(obj.get(i).getKlxkSoLuong());
				//
				// XSSFCell cell13 = row.createCell(13);
				// cell13.setCellValue(obj.get(i).getKlxkThanhTien());
				//
				// XSSFCell cell14 = row.createCell(14);
				// cell14.setCellValue(obj.get(i).getSntSoLuong());
				//
				// XSSFCell cell15 = row.createCell(15);
				// cell15.setCellValue(obj.get(i).getSntThanhTien());
				//
				// XSSFCell cell16 = row.createCell(16);
				// cell16.setCellValue(obj.get(i).getShhtdmSoLuong());
				//
				// XSSFCell cell17 = row.createCell(17);
				// cell17.setCellValue(obj.get(i).getShhtdmThanhTien());
				//
				// XSSFCell cell18 = row.createCell(18);
				// cell18.setCellValue(obj.get(i).getSdtthSoLuong());
				//
				// XSSFCell cell19 = row.createCell(19);
				// cell19.setCellValue(obj.get(i).getSdtthThanhTien());
				//
				// XSSFCell cell20 = row.createCell(20);
				// cell20.setCellValue(obj.get(i).getSmmpbtcctSoLuong());
				//
				// XSSFCell cell21 = row.createCell(21);
				// cell21.setCellValue(obj.get(i).getSmmpbtcctThanhTien());
				//
				// XSSFCell cell22 = row.createCell(22);
				// cell22.setCellValue(obj.get(i).getGhiChu());

				// for (int j = 0; j < obj.get(i).getLstErrorOrder().size();
				// j++) {
				// err.append(obj.get(i).getLstErrorOrder().get(j).getDetailError()
				// + "-");
				// // System.out.println("err" + err);
				// }
				// System.out.println("err " + i + ":" + err);
				//
				// XSSFCell cell23 = row.createCell(23);
				// cell23.setCellValue(err.toString());

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
				cell10.setCellStyle(styleNumber);
				cell11.setCellStyle(styleNumber);
				cell12.setCellStyle(styleNumber);
				cell13.setCellStyle(styleNumber);
				cell14.setCellStyle(styleNumber);
				cell15.setCellStyle(styleNumber);
				cell16.setCellStyle(styleNumber);
				cell17.setCellStyle(styleNumber);
				cell18.setCellStyle(styleNumber);
				cell19.setCellStyle(styleNumber);
				cell20.setCellStyle(styleNumber);
				cell21.setCellStyle(style);
				// cell22.setCellStyle(style);

			}
			file.close();

			File out = new File(folder2Upload + File.separatorChar + "ExportChiTietCongNo" + startExportTime + ".xlsx");

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
		// String path = UEncrypt.encryptFileUploadPath("BaoCaoThucXuatTheoKy" +
		// startExportTime +".xlsx");
		
		lsThaoTacDTO.setChucNang("Export chi tiết PXK type B");
		lsThaoTacDTO.setMoTa("Export chi tiết PXK type B "+(new Date()));
		businessImpl.insertLSTT(lsThaoTacDTO,request);
		
		return UEncrypt.encryptFileUploadPath("ExportChiTietCongNo" + startExportTime + ".xlsx");
	}

	public String exportExcelGrid1(TblQltsCongNoVatTuDTO lst,HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//		lst.setType(Float.parseFloat("0"));
		List<TblQltsCongNoVatTuDTO> obj = tblQltsCongNoVatTuDAO.getAllQlCongNo(lst);
		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream file = new BufferedInputStream(new FileInputStream(filePath + "ExportDauVaoPXK.xlsx"));
			// String filePath =
			// UEncrypt.decryptFileUploadPath(obj.getFilePathError());
			// InputStream file = new BufferedInputStream(new
			// FileInputStream(filePath));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);
			// set style
			XSSFCellStyle style = workbook.createCellStyle();
			XSSFCellStyle styleNumber = workbook.createCellStyle();
			XSSFFont hSSFFont = workbook.createFont();
			hSSFFont.setFontName("Times New Roman");
			style.setFont(hSSFFont);
			style.setBorderBottom(BorderStyle.THIN);
			style.setBorderTop(BorderStyle.THIN);
			style.setBorderRight(BorderStyle.THIN);
			style.setBorderLeft(BorderStyle.THIN);
			style.setWrapText(true);
			styleNumber.setBorderBottom(BorderStyle.THIN);
			styleNumber.setBorderTop(BorderStyle.THIN);
			styleNumber.setBorderRight(BorderStyle.THIN);
			styleNumber.setBorderLeft(BorderStyle.THIN);
			styleNumber.setWrapText(true);
			styleNumber.setAlignment(CellStyle.ALIGN_RIGHT);
			int rownum = 12;
			for (int i = 0; i < obj.size(); i++) {
				StringBuilder err = new StringBuilder();
				XSSFRow row = sheet.createRow(rownum);
				XSSFCell cell0 = row.createCell(0);
				cell0.setCellValue(i + 1);
				cell0.setCellStyle(style);
				rownum++;

				XSSFCell cell1 = row.createCell(1);
				cell1.setCellValue(obj.get(i).getDoiTuongNhanNo());

				XSSFCell cell2 = row.createCell(2);
				cell2.setCellValue(obj.get(i).getMaNv());

				XSSFCell cell3 = row.createCell(3);
				cell3.setCellValue(obj.get(i).getDonVi());
				// char index=obj.get(i).getSoPxk().charAt(0);
				// if('M'==index){
				// cell3.setCellValue("Chi nhánh khu vực 1");
				// }else if('H'==index){
				// cell3.setCellValue("Chi nhánh khu vực 2");
				// }else if('B'==index){
				// cell3.setCellValue("Chi nhánh khu vực 3");
				// }else if('T'==index){
				// cell3.setCellValue("Chi nhánh khu vực 4");
				// }else if('N'==index){
				// cell3.setCellValue("Chi nhánh khu vực 5");
				// }

				XSSFCell cell4 = row.createCell(4);
				cell4.setCellValue(obj.get(i).getSoPxk());

				XSSFCell cell5 = row.createCell(5);

				cell5.setCellValue(sdf.format(obj.get(i).getNgayChungTu()));

				XSSFCell cell6 = row.createCell(6);
				cell6.setCellValue(obj.get(i).getTenVatTu());

				XSSFCell cell7 = row.createCell(7);
				cell7.setCellValue(obj.get(i).getMaVatTu());

				XSSFCell cell8 = row.createCell(8);
				cell8.setCellValue(obj.get(i).getMaTram());

				XSSFCell cell9 = row.createCell(9);
				cell9.setCellValue(obj.get(i).getHangMuc());

				XSSFCell cell10 = row.createCell(10);
				cell10.setCellValue(obj.get(i).getDvt());

				XSSFCell cell11 = row.createCell(11);
				if(obj.get(i).getGia()==null){
					obj.get(i).setGia((float)0);
				}
				cell11.setCellValue(obj.get(i).getGia());

				XSSFCell cell12 = row.createCell(12);
				if(obj.get(i).getKlxkSoLuong()==null){
					obj.get(i).setKlxkSoLuong((float)0);
				}
				cell12.setCellValue(obj.get(i).getKlxkSoLuong());

				XSSFCell cell13 = row.createCell(13);
				if(obj.get(i).getKlxkThanhTien()==null){
					obj.get(i).setKlxkThanhTien((float)0);
				}
				cell13.setCellValue(obj.get(i).getKlxkThanhTien());

				// XSSFCell cell14 = row.createCell(14);
				// cell14.setCellValue(obj.get(i).getSntSoLuong());
				//
				// XSSFCell cell15 = row.createCell(15);
				// cell15.setCellValue(obj.get(i).getSntThanhTien());
				//
				// XSSFCell cell16 = row.createCell(16);
				// cell16.setCellValue(obj.get(i).getShhtdmSoLuong());
				//
				// XSSFCell cell17 = row.createCell(17);
				// cell17.setCellValue(obj.get(i).getShhtdmThanhTien());
				//
				// XSSFCell cell18 = row.createCell(18);
				// cell18.setCellValue(obj.get(i).getSdtthSoLuong());
				//
				// XSSFCell cell19 = row.createCell(19);
				// cell19.setCellValue(obj.get(i).getSdtthThanhTien());
				//
				// XSSFCell cell20 = row.createCell(20);
				// cell20.setCellValue(obj.get(i).getSmmpbtcctSoLuong());
				//
				// XSSFCell cell21 = row.createCell(21);
				// cell21.setCellValue(obj.get(i).getSmmpbtcctThanhTien());

				XSSFCell cell14 = row.createCell(14);
				cell14.setCellValue(obj.get(i).getGhiChu());

				// for (int j = 0; j < obj.get(i).getLstErrorOrder().size();
				// j++) {
				// err.append(obj.get(i).getLstErrorOrder().get(j).getDetailError()
				// + "-");
				// // System.out.println("err" + err);
				// }
				// System.out.println("err " + i + ":" + err);
				//
				// XSSFCell cell23 = row.createCell(23);
				// cell23.setCellValue(err.toString());

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
				cell12.setCellStyle(styleNumber);
				cell13.setCellStyle(styleNumber);
				cell14.setCellStyle(style);
				// cell15.setCellStyle(style);
				// cell16.setCellStyle(style);
				// cell17.setCellStyle(style);
				// cell18.setCellStyle(style);
				// cell19.setCellStyle(style);
				// cell20.setCellStyle(style);
				// cell21.setCellStyle(style);
				// cell22.setCellStyle(style);

			}
			file.close();

			File out = new File(folder2Upload + File.separatorChar + "ExportDauVaoPXK" + startExportTime + ".xlsx");

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
		// String path = UEncrypt.encryptFileUploadPath("BaoCaoThucXuatTheoKy" +
		// startExportTime +".xlsx");
		lsThaoTacDTO.setChucNang("Export chi tiết đầu vào PXK type B");
		lsThaoTacDTO.setMoTa("Export chi tiết đầu vào PXK type B "+(new Date()));
		businessImpl.insertLSTT(lsThaoTacDTO,request);
		return UEncrypt.encryptFileUploadPath("ExportDauVaoPXK" + startExportTime + ".xlsx");
	}

	@SuppressWarnings("deprecation")
	public String exportExcelGrid2(TblQltsCongNoVatTuDTO lst,HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		// lst.setType(Float.parseFloat("0"));
		List<TblQltsCongNoVatTuDTO> obj1 = tblQltsCongNoVatTuDAO.selectDonVi(lst);
		List<TblQltsCongNoVatTuDTO> obj = new ArrayList();
		for (int j = 0; j < obj1.size(); j++) {
			TblQltsCongNoVatTuDTO dto = new TblQltsCongNoVatTuDTO();
			dto = tblQltsCongNoVatTuDAO.sumTenByDonVi(obj1.get(j));
			dto.setDonVi(obj1.get(j).getDonVi());
			obj.add(dto);
		}

		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream file = new BufferedInputStream(
					new FileInputStream(filePath + "ExportBaoCaoTongHopDoiSoatTheoDV.xlsx"));
			// String filePath =
			// UEncrypt.decryptFileUploadPath(obj.getFilePathError());
			// InputStream file = new BufferedInputStream(new
			// FileInputStream(filePath));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);
			// set style
			XSSFCellStyle style = workbook.createCellStyle();
			XSSFCellStyle style1 = workbook.createCellStyle();
			XSSFCellStyle style2 = workbook.createCellStyle();
			XSSFCellStyle styleNumber = workbook.createCellStyle();
			XSSFFont hSSFFont = workbook.createFont();
			hSSFFont.setFontName("Times New Roman");

			try {
				XSSFFont hSSFFont1 = workbook.createFont();
				XSSFFont hSSFFont2 = workbook.createFont();
				hSSFFont1.setFontName("Times New Roman");
				hSSFFont1.setFontHeightInPoints((short) 15);
				hSSFFont2.setFontName("Times New Roman");
				hSSFFont2.setFontHeightInPoints((short) 12);
				hSSFFont1.setBold(true);
				// style1.setVerticalAlignment(VerticalAlignment.CENTER);
				style1.setFont(hSSFFont1);
				style2.setFont(hSSFFont2);
				style1.setWrapText(true);
				style1.setAlignment(CellStyle.ALIGN_CENTER);
				style2.setWrapText(true);
				style2.setAlignment(CellStyle.ALIGN_CENTER);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			styleNumber.setBorderBottom(BorderStyle.THIN);
			styleNumber.setBorderTop(BorderStyle.THIN);
			styleNumber.setBorderRight(BorderStyle.THIN);
			styleNumber.setBorderLeft(BorderStyle.THIN);
			styleNumber.setWrapText(true);
			styleNumber.setAlignment(CellStyle.ALIGN_RIGHT);

			style.setFont(hSSFFont);
			style.setBorderBottom(BorderStyle.THIN);
			style.setBorderTop(BorderStyle.THIN);
			style.setBorderRight(BorderStyle.THIN);
			style.setBorderLeft(BorderStyle.THIN);
			style.setWrapText(true);
			XSSFRow row = null;

			// sheet.addMergedRegion(new CellRangeAddress(6, 6, 1,
			// 8));
			row = sheet.createRow(6);
			XSSFCell cell110 = row.createCell(0);
			if (lst.getNgayChungTuFrom() != null) {
				cell110.setCellValue("Từ ngày :" + dt.format(lst.getNgayChungTuFrom()) + " đến ngày :"
						+ dt.format(lst.getNgayChungTuTo()));
			} else {
				cell110.setCellValue("Từ ngày ...../..../...... đến ngày ...../..../......");
			}
			cell110.setCellStyle(style2);

			int rownum = 10;
			for (int i = 0; i < obj.size(); i++) {
				StringBuilder err = new StringBuilder();
				row = sheet.createRow(rownum);
				XSSFCell cell0 = row.createCell(0);
				cell0.setCellValue(i + 1);
				cell0.setCellStyle(style);
				rownum++;

				XSSFCell cell1 = row.createCell(1);
				cell1.setCellValue(obj.get(i).getDonVi());

				XSSFCell cell2 = row.createCell(2);
				if (obj.get(i).getKlxkThanhTien() == null||obj.get(i).getKlxkThanhTien() == (float) 0) {
					obj.get(i).setKlxkThanhTien(Float.parseFloat("0"));
					cell2.setCellValue(obj.get(i).getKlxkThanhTien());
				}else
				if (obj.get(i).getKlxkThanhTien() >= (float) 0) {
					cell2.setCellValue(dFormat.format(obj.get(i).getKlxkThanhTien()));
				}

				XSSFCell cell3 = row.createCell(3);
				if (obj.get(i).getSntThanhTien() == null || obj.get(i).getSntThanhTien() == (float) 0) {
					obj.get(i).setSntThanhTien(Float.parseFloat("0"));
					cell3.setCellValue(obj.get(i).getSntThanhTien());
				} else if (obj.get(i).getSntThanhTien() > (float) 0) {
					cell3.setCellValue(dFormat.format(obj.get(i).getSntThanhTien()));
				}

//				XSSFCell cell4 = row.createCell(4);
//				if (obj.get(i).getShhtdmThanhTien() == null || obj.get(i).getShhtdmThanhTien() == (float) 0) {
//					obj.get(i).setShhtdmThanhTien(Float.parseFloat("0"));
//					cell4.setCellValue(obj.get(i).getShhtdmThanhTien());
//				} else if (obj.get(i).getShhtdmThanhTien() > (float) 0) {
//					cell4.setCellValue(dFormat.format(obj.get(i).getShhtdmThanhTien()));
//				}

				XSSFCell cell4 = row.createCell(4);
				if (obj.get(i).getSdtthThanhTien() == null || obj.get(i).getSdtthThanhTien() == (float) 0) {
					obj.get(i).setSdtthThanhTien(Float.parseFloat("0"));
					cell4.setCellValue(obj.get(i).getSdtthThanhTien());
				} else if (obj.get(i).getSdtthThanhTien() > (float) 0) {
					cell4.setCellValue(dFormat.format(obj.get(i).getSdtthThanhTien()));
				}

				XSSFCell cell5 = row.createCell(5);
				if (obj.get(i).getSmmpbtcctThanhTien() == null || obj.get(i).getSmmpbtcctThanhTien() == (float) 0) {
					obj.get(i).setSmmpbtcctThanhTien(Float.parseFloat("0"));
					cell5.setCellValue(obj.get(i).getSmmpbtcctThanhTien());
				} else if (obj.get(i).getSmmpbtcctThanhTien() > (float) 0) {
					cell5.setCellValue(dFormat.format(obj.get(i).getSmmpbtcctThanhTien()));
				}

				float sLdd = obj.get(i).getKlxkThanhTien() - obj.get(i).getSntThanhTien()
						 - obj.get(i).getSdtthThanhTien()- obj.get(i).getSmmpbtcctThanhTien();
				if (sLdd == (float) 0) {
					sLdd = (float) 0;
				}

				XSSFCell cell6 = row.createCell(6);
				cell6.setCellValue(dFormat.format(sLdd));

				cell1.setCellStyle(style);
				cell2.setCellStyle(styleNumber);
				cell3.setCellStyle(styleNumber);
				cell4.setCellStyle(styleNumber);
				cell5.setCellStyle(styleNumber);
				cell6.setCellStyle(styleNumber);
//				cell7.setCellStyle(styleNumber);

			}
			sheet.addMergedRegion(new CellRangeAddress(obj.size() + 13, obj.size() + 13, 5, 7));
			row = sheet.createRow(obj.size() + 13);
			XSSFCell cell17 = row.createCell(5);
			cell17.setCellStyle(style1);
			cell17.setCellValue("PHÒNG QUẢN LÝ TÀI SẢN");

			file.close();

			File out = new File(folder2Upload + File.separatorChar + "ExportBaoCaoTongHopDoiSoatTheoDV"
					+ startExportTime + ".xlsx");

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
		// String path = UEncrypt.encryptFileUploadPath("BaoCaoThucXuatTheoKy" +
		// startExportTime +".xlsx");
		lsThaoTacDTO.setChucNang("Export báo cáo tổng hợp nghiệm thu đối soát type B");
		lsThaoTacDTO.setMoTa("Export báo cáo tổng hợp nghiệm thu đối soát type B "+(new Date()));
		businessImpl.insertLSTT(lsThaoTacDTO,request);
		return UEncrypt.encryptFileUploadPath("ExportBaoCaoTongHopDoiSoatTheoDV" + startExportTime + ".xlsx");
	}

	public String exportExcelError(List<ImportErrDTO> obj,HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream file = new BufferedInputStream(new FileInputStream(filePath + "Chi_tiet_cong_no_vat_tu.xlsx"));
			// String filePath =
			// UEncrypt.decryptFileUploadPath(obj.getFilePathError());
			// InputStream file = new BufferedInputStream(new
			// FileInputStream(filePath));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);
			// set style
			XSSFCellStyle style = workbook.createCellStyle();
			XSSFFont hSSFFont = workbook.createFont();
			hSSFFont.setFontName("Times New Roman");
			style.setFont(hSSFFont);
			style.setBorderBottom(BorderStyle.THIN);
			style.setBorderTop(BorderStyle.THIN);
			style.setBorderRight(BorderStyle.THIN);
			style.setBorderLeft(BorderStyle.THIN);
			style.setWrapText(true);
			int rownum = 12;
			for (int i = 0; i < obj.size(); i++) {
				StringBuilder err = new StringBuilder();
				XSSFRow row = sheet.createRow(rownum);
				XSSFCell cell0 = row.createCell(0);
				cell0.setCellValue(i + 1);
				cell0.setCellStyle(style);
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

				XSSFCell cell18 = row.createCell(18);
				cell18.setCellValue(obj.get(i).getColumn18());

				XSSFCell cell19 = row.createCell(19);
				cell19.setCellValue(obj.get(i).getColumn19());

				XSSFCell cell20 = row.createCell(20);
				cell20.setCellValue(obj.get(i).getColumn20());

				// XSSFCell cell21 = row.createCell(21);
				// cell21.setCellValue(obj.get(i).getColumn21());
				//
				// XSSFCell cell22 = row.createCell(22);
				// cell22.setCellValue(obj.get(i).getColumn22());

				for (int j = 0; j < obj.get(i).getLstErrorOrder().size(); j++) {
					err.append(obj.get(i).getLstErrorOrder().get(j).getDetailError() + "-");
					// System.out.println("err" + err);
				}
				System.out.println("err " + i + ":" + err);

				XSSFCell cell21 = row.createCell(21);
				cell21.setCellValue(err.toString());

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
				cell19.setCellStyle(style);
				cell20.setCellStyle(style);
				cell21.setCellStyle(style);
				// cell22.setCellStyle(style);
				// cell23.setCellStyle(style);

			}
			file.close();

			File out = new File(
					folder2Upload + File.separatorChar + "Chi_tiet_cong_no_vat_tu" + startExportTime + ".xlsx");

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
		// String path = UEncrypt.encryptFileUploadPath("BaoCaoThucXuatTheoKy" +
		// startExportTime +".xlsx");
		lsThaoTacDTO.setChucNang("Import dữ liệu thi công nghiệm thu đối soát type B");
		lsThaoTacDTO.setMoTa("Import dữ liệu thi công nghiệm thu đối soát type B "+(new Date()));
		businessImpl.insertLSTT(lsThaoTacDTO,request);
		return UEncrypt.encryptFileUploadPath("Chi_tiet_cong_no_vat_tu" + startExportTime + ".xlsx");
	}

	public String exportExcelError1(List<ImportErrDTO> obj) throws Exception {
		// TODO Auto-generated method stub
		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream file = new BufferedInputStream(new FileInputStream(filePath + "dauVaoPXKErr.xlsx"));
			// String filePath =
			// UEncrypt.decryptFileUploadPath(obj.getFilePathError());
			// InputStream file = new BufferedInputStream(new
			// FileInputStream(filePath));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);
			// set style
			XSSFCellStyle style = workbook.createCellStyle();
			XSSFFont hSSFFont = workbook.createFont();
			hSSFFont.setFontName("Times New Roman");
			style.setFont(hSSFFont);
			style.setBorderBottom(BorderStyle.THIN);
			style.setBorderTop(BorderStyle.THIN);
			style.setBorderRight(BorderStyle.THIN);
			style.setBorderLeft(BorderStyle.THIN);
			style.setWrapText(true);
			int rownum = 12;
			for (int i = 0; i < obj.size(); i++) {
				StringBuilder err = new StringBuilder();
				XSSFRow row = sheet.createRow(rownum);
				XSSFCell cell0 = row.createCell(0);
				cell0.setCellValue(i + 1);
				cell0.setCellStyle(style);
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

				// XSSFCell cell13 = row.createCell(13);
				// cell13.setCellValue(obj.get(i).getColumn13());
				//
				// XSSFCell cell14 = row.createCell(14);
				// cell14.setCellValue(obj.get(i).getColumn14());
				//
				// XSSFCell cell15 = row.createCell(15);
				// cell15.setCellValue(obj.get(i).getColumn15());
				//
				// XSSFCell cell16 = row.createCell(16);
				// cell16.setCellValue(obj.get(i).getColumn16());
				//
				// XSSFCell cell17 = row.createCell(17);
				// cell17.setCellValue(obj.get(i).getColumn17());
				//
				// XSSFCell cell18 = row.createCell(18);
				// cell18.setCellValue(obj.get(i).getColumn18());
				//
				// XSSFCell cell19 = row.createCell(19);
				// cell19.setCellValue(obj.get(i).getColumn19());
				//
				// XSSFCell cell20 = row.createCell(20);
				// cell20.setCellValue(obj.get(i).getColumn20());

				// XSSFCell cell21 = row.createCell(21);
				// cell21.setCellValue(obj.get(i).getColumn21());
				//
				// XSSFCell cell22 = row.createCell(22);
				// cell22.setCellValue(obj.get(i).getColumn22());

				for (int j = 0; j < obj.get(i).getLstErrorOrder().size(); j++) {
					err.append(obj.get(i).getLstErrorOrder().get(j).getDetailError() + "-");
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
				// cell14.setCellStyle(style);
				// cell15.setCellStyle(style);
				// cell16.setCellStyle(style);
				// cell17.setCellStyle(style);
				// cell18.setCellStyle(style);
				// cell19.setCellStyle(style);
				// cell20.setCellStyle(style);
				// cell21.setCellStyle(style);
				// cell22.setCellStyle(style);
				// cell23.setCellStyle(style);

			}
			file.close();

			File out = new File(folder2Upload + File.separatorChar + "dauVaoPXKErr" + startExportTime + ".xlsx");

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
		// String path = UEncrypt.encryptFileUploadPath("BaoCaoThucXuatTheoKy" +
		// startExportTime +".xlsx");
		
		return UEncrypt.encryptFileUploadPath("dauVaoPXKErr" + startExportTime + ".xlsx");
	}

	@Override
	public String prepareDataTongHop(TblQltsCongNoVatTuDTO obj,HttpServletRequest request) throws Exception {
//		obj.setType(Float.parseFloat("1"));
		List<TblQltsCongNoVatTuDTO> ls = tblQltsCongNoVatTuDAO.getAllQlCongNo(obj);
		List<TblQltsCongNoVatTuDTO> lsExport = Lists.newArrayList();
		TblQltsCongNoVatTuDTO obj1 = new TblQltsCongNoVatTuDTO();
		obj1.setNgayChungTuFrom(obj.getNgayChungTuFrom());
		obj1.setNgayChungTuTo(obj.getNgayChungTuTo());
		// List<String> lstKey = new ArrayList<>();
		// boolean checkDup = false;
		try {
			if (ls.size() > 0) {
				TblQltsCongNoVatTuDTO tblQltsCongNoVatTuDTO = new TblQltsCongNoVatTuDTO();
				boolean firstData = true;

				for (int i = 0; i < ls.size(); i++) {
					if (firstData) {
						tblQltsCongNoVatTuDTO = new TblQltsCongNoVatTuDTO();
						tblQltsCongNoVatTuDTO.setDoiTuongNhanNo(ls.get(i).getDoiTuongNhanNo());
						tblQltsCongNoVatTuDTO.setDonVi(ls.get(i).getDonVi());
						tblQltsCongNoVatTuDTO.setMaNv(ls.get(i).getMaNv());
						tblQltsCongNoVatTuDTO.setSoPxk(ls.get(i).getSoPxk());
						tblQltsCongNoVatTuDTO.setMaTram(ls.get(i).getMaTram());
						tblQltsCongNoVatTuDTO.setHangMuc(ls.get(i).getHangMuc());
						tblQltsCongNoVatTuDTO.setKlxkThanhTien(ls.get(i).getKlxkThanhTien());
						tblQltsCongNoVatTuDTO.setSntThanhTien(ls.get(i).getSntThanhTien());
						tblQltsCongNoVatTuDTO.setShhtdmThanhTien(ls.get(i).getShhtdmThanhTien());
						tblQltsCongNoVatTuDTO.setSdtthThanhTien(ls.get(i).getSdtthThanhTien());
						tblQltsCongNoVatTuDTO.setSmmpbtcctThanhTien(ls.get(i).getSmmpbtcctThanhTien());
						tblQltsCongNoVatTuDTO.setGhiChu(ls.get(i).getGhiChu());
						tblQltsCongNoVatTuDTO.setSldd(ls.get(i).getKlxkThanhTien() - ls.get(i).getSntThanhTien()
								- ls.get(i).getShhtdmThanhTien() - ls.get(i).getSdtthThanhTien()
								- ls.get(i).getSmmpbtcctThanhTien());
						lsExport.add(tblQltsCongNoVatTuDTO);
						firstData = false;
						continue;
					}
					boolean checkDup = false;
					String check = ls.get(i).getDoiTuongNhanNo() + "_" + ls.get(i).getSoPxk() + "_"
							+ ls.get(i).getMaTram();
					for (int j = 0; j < lsExport.size(); j++) {
						String checkKey = lsExport.get(j).getDoiTuongNhanNo() + "_" + lsExport.get(j).getSoPxk() + "_"
								+ lsExport.get(j).getMaTram();
						if (check.equals(checkKey)) {
							lsExport.get(j).setKlxkThanhTien(
									ls.get(i).getKlxkThanhTien() + lsExport.get(j).getKlxkThanhTien());
							lsExport.get(j)
									.setSntThanhTien(ls.get(i).getSntThanhTien() + lsExport.get(j).getSntThanhTien());
							lsExport.get(j).setShhtdmThanhTien(
									ls.get(i).getShhtdmThanhTien() + lsExport.get(j).getShhtdmThanhTien());
							lsExport.get(j).setSdtthThanhTien(
									ls.get(i).getSdtthThanhTien() + lsExport.get(j).getSdtthThanhTien());
							lsExport.get(j).setSmmpbtcctThanhTien(
									ls.get(i).getSmmpbtcctThanhTien() + lsExport.get(j).getSmmpbtcctThanhTien());
							lsExport.get(j)
									.setSldd(lsExport.get(j).getKlxkThanhTien() - lsExport.get(j).getSntThanhTien()
											- lsExport.get(j).getShhtdmThanhTien() - lsExport.get(j).getSdtthThanhTien()
											- lsExport.get(j).getSmmpbtcctThanhTien());
							checkDup = true;
							break;
							// lsExport.get(j).set
						}
					}
					if (!checkDup) {
						tblQltsCongNoVatTuDTO = new TblQltsCongNoVatTuDTO();
						tblQltsCongNoVatTuDTO.setDoiTuongNhanNo(ls.get(i).getDoiTuongNhanNo());
						tblQltsCongNoVatTuDTO.setMaNv(ls.get(i).getMaNv());
						tblQltsCongNoVatTuDTO.setDonVi(ls.get(i).getDonVi());
						tblQltsCongNoVatTuDTO.setSoPxk(ls.get(i).getSoPxk());
						tblQltsCongNoVatTuDTO.setMaTram(ls.get(i).getMaTram());
						tblQltsCongNoVatTuDTO.setHangMuc(ls.get(i).getHangMuc());
						tblQltsCongNoVatTuDTO.setKlxkThanhTien(ls.get(i).getKlxkThanhTien());
						tblQltsCongNoVatTuDTO.setSntThanhTien(ls.get(i).getSntThanhTien());
						tblQltsCongNoVatTuDTO.setShhtdmThanhTien(ls.get(i).getShhtdmThanhTien());
						tblQltsCongNoVatTuDTO.setSdtthThanhTien(ls.get(i).getSdtthThanhTien());
						tblQltsCongNoVatTuDTO.setSmmpbtcctThanhTien(ls.get(i).getSmmpbtcctThanhTien());
						tblQltsCongNoVatTuDTO.setGhiChu(ls.get(i).getGhiChu());
						tblQltsCongNoVatTuDTO.setSldd(ls.get(i).getKlxkThanhTien() - ls.get(i).getSntThanhTien()
								- ls.get(i).getShhtdmThanhTien() - ls.get(i).getSdtthThanhTien()
								- ls.get(i).getSmmpbtcctThanhTien());
						lsExport.add(tblQltsCongNoVatTuDTO);
					}
					// if(check)
					// lstKey.add(ls.get(i).getDoiTuongNhanNo()+"_"+ls.get(i).getSoPxk()+"_"+ls.get(i).getMaTram());

				}
				return exportExcelTongHop(lsExport, obj1,request);

			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		// TblQltsCongNoVatTuDTO tblQltsCongNoVatTuDTO = new
		// TblQltsCongNoVatTuDTO();
		return null;
	}

	public String exportExcelTongHop(List<TblQltsCongNoVatTuDTO> obj, TblQltsCongNoVatTuDTO obj1,HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream file = new BufferedInputStream(new FileInputStream(filePath + "ExportExcelTongHopCnvt.xlsx"));

			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);
			// set style
			XSSFCellStyle style = workbook.createCellStyle();
			XSSFCellStyle styleNumber = workbook.createCellStyle();
			XSSFFont hSSFFont = workbook.createFont();
			hSSFFont.setFontName("Times New Roman");
			style.setFont(hSSFFont);
			style.setBorderBottom(BorderStyle.THIN);
			style.setBorderTop(BorderStyle.THIN);
			style.setBorderRight(BorderStyle.THIN);
			style.setBorderLeft(BorderStyle.THIN);
			style.setWrapText(true);
			
			styleNumber.setBorderBottom(BorderStyle.THIN);
			styleNumber.setBorderTop(BorderStyle.THIN);
			styleNumber.setBorderRight(BorderStyle.THIN);
			styleNumber.setBorderLeft(BorderStyle.THIN);
			styleNumber.setWrapText(true);
			styleNumber.setAlignment(CellStyle.ALIGN_RIGHT);

			int rownum = 10;
			for (int i = 0; i < obj.size(); i++) {
				// StringBuilder err = new StringBuilder();

				XSSFRow row = sheet.createRow(rownum);
				XSSFCell cell0 = row.createCell(0);
				cell0.setCellValue(i + 1);
				cell0.setCellStyle(style);
				rownum++;

				XSSFCell cell1 = row.createCell(1);
				cell1.setCellValue(obj.get(i).getDoiTuongNhanNo());

				XSSFCell cell2 = row.createCell(2);
				cell2.setCellValue(obj.get(i).getMaNv());

				XSSFCell cell3 = row.createCell(3);
				cell3.setCellValue(obj.get(i).getDonVi());

				XSSFCell cell4 = row.createCell(4);
				cell4.setCellValue(obj.get(i).getSoPxk());

				XSSFCell cell5 = row.createCell(5);
				cell5.setCellValue(obj.get(i).getMaTram());

				XSSFCell cell6 = row.createCell(6);
				cell6.setCellValue(obj.get(i).getHangMuc());

				XSSFCell cell7 = row.createCell(7);
				if (obj.get(i).getKlxkThanhTien() == null || obj.get(i).getKlxkThanhTien() == (float) 0) {
					obj.get(i).setKlxkThanhTien((float) 0);
					cell7.setCellValue(obj.get(i).getKlxkThanhTien());
				} else if (obj.get(i).getKlxkThanhTien() > (float) 0) {
					cell7.setCellValue(dFormat.format(obj.get(i).getKlxkThanhTien()));
				}

				XSSFCell cell8 = row.createCell(8);
				if (obj.get(i).getSntThanhTien() == null || obj.get(i).getSntThanhTien() == (float) 0) {
					obj.get(i).setSntThanhTien((float) 0);
					cell8.setCellValue(obj.get(i).getSntThanhTien());
				} else if (obj.get(i).getSntThanhTien() > (float) 0) {
					cell8.setCellValue(dFormat.format(obj.get(i).getSntThanhTien()));
				}

//				XSSFCell cell9 = row.createCell(9);
//				if (obj.get(i).getShhtdmThanhTien() == null || obj.get(i).getShhtdmThanhTien() == (float) 0) {
//					obj.get(i).setShhtdmThanhTien((float) 0);
//					cell9.setCellValue(obj.get(i).getShhtdmThanhTien());
//				} else if (obj.get(i).getShhtdmThanhTien() > (float) 0) {
//					cell9.setCellValue(dFormat.format(obj.get(i).getShhtdmThanhTien()));
//				}

				XSSFCell cell9= row.createCell(9);
				if (obj.get(i).getSdtthThanhTien() == null || obj.get(i).getSdtthThanhTien() == (float) 0) {
					obj.get(i).setSdtthThanhTien((float) 0);
					cell9.setCellValue(obj.get(i).getSdtthThanhTien());
				} else if (obj.get(i).getSdtthThanhTien() > (float) 0) {
					cell9.setCellValue(dFormat.format(obj.get(i).getSdtthThanhTien()));
				}

				XSSFCell cell10 = row.createCell(10);
				if (obj.get(i).getSmmpbtcctThanhTien() == null || obj.get(i).getSmmpbtcctThanhTien() == (float) 0) {
					obj.get(i).setSmmpbtcctThanhTien((float) 0);
					cell10.setCellValue(obj.get(i).getSmmpbtcctThanhTien());
				} else if (obj.get(i).getSmmpbtcctThanhTien() > (float) 0) {
					cell10.setCellValue(dFormat.format(obj.get(i).getSmmpbtcctThanhTien()));
				}

				XSSFCell cell11 = row.createCell(11);
				if (obj.get(i).getSldd() == null || obj.get(i).getSldd() == (float) 0) {
					obj.get(i).setSldd((float) 0);
					cell11.setCellValue(obj.get(i).getSldd());
				} else if (obj.get(i).getSldd() > (float) 0) {
					cell11.setCellValue(dFormat.format(obj.get(i).getSldd()));
				}

				XSSFCell cell12 = row.createCell(12);
				cell12.setCellValue(obj.get(i).getGhiChu());

				cell0.setCellStyle(style);
				cell1.setCellStyle(style);
				cell2.setCellStyle(style);
				cell3.setCellStyle(style);
				cell4.setCellStyle(style);
				cell5.setCellStyle(style);
				cell6.setCellStyle(styleNumber);
				cell7.setCellStyle(styleNumber);
				cell8.setCellStyle(styleNumber);
				cell9.setCellStyle(styleNumber);
				cell10.setCellStyle(styleNumber);
				cell11.setCellStyle(styleNumber);
				cell12.setCellStyle(style);
//				cell13.setCellStyle(style);

			}
			file.close();

			File out = new File(
					folder2Upload + File.separatorChar + "Export_Excel_TongHop_Cnvt" + startExportTime + ".xlsx");

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
		// String path = UEncrypt.encryptFileUploadPath("BaoCaoThucXuatTheoKy" +
		// startExportTime +".xlsx");
		lsThaoTacDTO.setChucNang("Export báo cáo tổng hợp theo đơn vị type B");
		lsThaoTacDTO.setMoTa("Export báo cáo tổng hợp theo đơn vị type B "+(new Date()));
		businessImpl.insertLSTT(lsThaoTacDTO,request);
		return UEncrypt.encryptFileUploadPath("Export_Excel_TongHop_Cnvt" + startExportTime + ".xlsx");
	}

	@SuppressWarnings("unchecked")
	public String saveList1(List<TblQltsCongNoVatTuDTO> lstCenterBO) {
		@SuppressWarnings("rawtypes")
		List lstModel = new ArrayList<>();
		if (lstCenterBO != null) {
			for (int i = 0; i < lstCenterBO.size(); i++) {
				lstModel.add(((QllBaseDTO<TblQltsCongNoVatTuBO>) lstCenterBO.get(i)).toModel());
			}
		}
		return tblQltsCongNoVatTuDAO.saveList1(lstModel);
	}

}
