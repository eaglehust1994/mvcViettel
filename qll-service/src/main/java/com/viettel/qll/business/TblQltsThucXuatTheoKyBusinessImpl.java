package com.viettel.qll.business;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import com.google.common.collect.Lists;
import com.graphbuilder.math.func.CeilFunction;
import com.viettel.ktts2.common.UEncrypt;
import com.viettel.qll.bo.LsThaoTacBO;
import com.viettel.qll.bo.TblQltsCongNoVatTuBO;
import com.viettel.qll.bo.TblQltsThucXuatTheoKyBO;
import com.viettel.qll.dao.LsThaoTacDAO;
import com.viettel.qll.dao.TblQltsThucXuatTheoKyDAO;
import com.viettel.qll.dao.TblTypeAPxkDAO;
import com.viettel.qll.dto.ErrExcelDTO;
import com.viettel.qll.dto.ImportErrDTO;
import com.viettel.qll.dto.LsThaoTacDTO;
import com.viettel.qll.dto.QllBaseDTO;
import com.viettel.qll.dto.TblDmKiDonViDTO;
import com.viettel.qll.dto.TblQltsCongNoVatTuDTO;
import com.viettel.qll.dto.TblQltsThucXuatTheoKyDTO;
import com.viettel.qll.dto.TblTypeAPxkDTO;
import com.viettel.qll.dto.VpsUserToken;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;
import com.viettel.utils.ValidateUtils;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

@Service("tblQltsThucXuatTheoKyBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TblQltsThucXuatTheoKyBusinessImpl
		extends BaseFWBusinessImpl<TblQltsThucXuatTheoKyDAO, TblQltsThucXuatTheoKyDTO, TblQltsThucXuatTheoKyBO>
		implements TblQltsThucXuatTheoKyBusiness {
	@Value("${folder_upload2}")
	private String folder2Upload;
	@Autowired
	private LsThaoTacDAO lsThaoTacDAO;
	private String fomat = "dd/MM/yyyy";
	DateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
	DateFormat d1t = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	DecimalFormat dFormat = new DecimalFormat("#,###");
	DecimalFormat dFormat1 = new DecimalFormat("#,###.00");
	static Logger LOGGER = LoggerFactory.getLogger(TblQltsThucXuatTheoKyBusinessImpl.class);
	@Autowired
	private TblQltsThucXuatTheoKyDAO tblQltsThucXuatTheoKyDAO;

	@Autowired
	private TblTypeAPxkBusinessImpl tblTypeAPxkBusinessImpl;

	LsThaoTacDTO lsThaoTacDTO = new LsThaoTacDTO();

//	TblQltsThucXuatTheoKyBusinessImpl businessImpl=new TblQltsThucXuatTheoKyBusinessImpl();

	List<ErrExcelDTO> lstErrExcelDto;

	public TblQltsThucXuatTheoKyBusinessImpl() {
		tModel = new TblQltsThucXuatTheoKyBO();
		tDAO = tblQltsThucXuatTheoKyDAO;
	}

	@Override
	public TblQltsThucXuatTheoKyDAO gettDAO() {
		return tblQltsThucXuatTheoKyDAO;
	}

	public DataListDTO getAllBctxtk() {
		List<TblQltsThucXuatTheoKyDTO> ls = tblQltsThucXuatTheoKyDAO.getAll();
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		return data;
	}

	@Override
	public DataListDTO doSearch(TblQltsThucXuatTheoKyDTO obj, HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		String err = "";

		List<TblQltsThucXuatTheoKyDTO> ls = tblQltsThucXuatTheoKyDAO.doSearch(obj);
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		data.setTotal(obj.getTotalRecord());
		data.setSize(obj.getTotalRecord());
		data.setStart(1);
		return data;
	}

	@Override
	public DataListDTO doSearchByPXK(TblQltsThucXuatTheoKyDTO obj, HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		String err = "";

		List<TblQltsThucXuatTheoKyDTO> ls = tblQltsThucXuatTheoKyDAO.doSearchByPXK(obj);
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

	public String importFile(String fileInput, HttpServletRequest request) throws Exception {
		long tStart = System.currentTimeMillis();
		List<TblQltsThucXuatTheoKyDTO> workLst = Lists.newArrayList();
		List<ImportErrDTO> workFault = Lists.newArrayList();
		try {
			String Ten = "";
			File f = new File(fileInput);

			XSSFWorkbook workbook = new XSSFWorkbook(f);
			XSSFSheet sheet = workbook.getSheetAt(0);

			DataFormatter dataFormatter = new DataFormatter();
			int count = 0;
			int a = 1;
			for (Row row : sheet) {
				count++;
				// Create object OrderGoodsExelDTO
				ErrExcelDTO orderErrorFormat = new ErrExcelDTO();
				if (count >= 11 && !isRowEmpty(row)) {
					lstErrExcelDto = new ArrayList<>();
					boolean checkSoPhieuXuat = true;
					boolean checkSPXLogistic = true;
					boolean checkNgayXuat = true;
					boolean checkNoiDung = true;
					boolean checkMaKhoXuat = true;
					boolean checkTenVatTu = true;
					boolean checkMaVatTu = true;
					boolean checkDonViTinh = true;
					boolean checkSoLuongXuat = true;
					boolean checkDonGia = true;
					boolean checkThanhTien = true;
					boolean checkMaCongTrinh = true;
					boolean checkNguoiNhanHang = true;
					boolean checkSoLuongYeuCau = true;
					boolean checkLyDo = true;
					boolean checkDienGiai = true;
					boolean checkTenKhoXuat = true;
					boolean checkKhoNhan = true;
					boolean checkDonViNhan = true;
					boolean checkMaKhoNhan = true;

					String soPhieuXuat = "";
					String sPXLogistic = "";
					String ngayXuat = "";
					String noiDung = "";
					String maKhoXuat = "";
					String tenVatTu = "";
					String maVatTu = "";
					String donViTinh = "";
					String soLuongXuat = "";
					String donGia = "";
					String thanhTien = "";
					String maCongTrinh = "";
					String nguoiNhanHang = "";
					String soLuongYeuCau = "";
					String lyDo = "";
					String dienGiai = "";
					String tenKhoXuat = "";
					String maKhoNhan = "";
					String khoNhan = "";
					String donViNhan = "";

					for (Cell cell : row) {

						// Check format file exel
						if (cell.getColumnIndex() == 1) {
							soPhieuXuat = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 2) {
							sPXLogistic = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 3) {
							ngayXuat = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 4) {
							noiDung = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 5) {
							maKhoXuat = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 6) {
							tenVatTu = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 7) {
							maVatTu = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 8) {
							donViTinh = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 9) {
							soLuongXuat = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 10) {
							donGia = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 11) {
							thanhTien = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 12) {
							maCongTrinh = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 13) {
							soLuongYeuCau = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 14) {
							lyDo = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 15) {
							dienGiai = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 16) {
							tenKhoXuat = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 17) {
							maKhoNhan = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 18) {
							khoNhan = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 19) {
							donViNhan = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 20) {
							nguoiNhanHang = dataFormatter.formatCellValue(cell);
						}

					}

					// if (count == 2) {
					// if (code == "" && serial == "" && amount == "" &&
					// goodsState == "") {
					// orderErrorFormat.setLineError(String.valueOf(count));
					// orderErrorFormat.setColumnError(String.valueOf(1));
					// orderErrorFormat.setDetailError("File excel trống");
					// lstErrExcelDto.add(orderErrorFormat);
					// }
					// }
					// if (code == "" && serial == "" && amount == "" &&
					// goodsState == "") {
					//
					// } else {
					// checkColumn1 = checkDataFromFileExel(code.trim(), count,
					// 1, orderErrorFormat);
					// // checkColumn3 = checkDataFromFileExelNK(serial, count,
					// // 3, orderErrorFormat);
					// GoodsDTO goodDto = new GoodsDTO();
					// goodDto = goodsBusinessImpl.getGoodByCode(code.trim());
					// if (goodDto != null) {
					// if (goodDto.getIsSerial().equals("1")) {
					// checkColumn3 = checkDataFromFileExelNK(serial.trim(),
					// count, 3, orderErrorFormat);
					// }
					// }
					// checkColumn4 = checkDataFromFileExelNK(amount.trim(),
					// count, 4, orderErrorFormat);
					//
					// checkColumn5 = checkDataFromFileExelNK(goodsState.trim(),
					// count, 5, orderErrorFormat);
					//
					// if (checkColumn1 == true && checkColumn3 == true &&
					// checkColumn4 == true
					// && checkColumn5 == true) {
					//
					// }
					//
					// }
					checkSoPhieuXuat = checkDataFromFileExel(soPhieuXuat.trim(), 1, orderErrorFormat);
					checkSPXLogistic = checkDataFromFileExel(sPXLogistic.trim(), 2, orderErrorFormat);
					checkNgayXuat = checkDataFromFileExel(ngayXuat.trim(), 3, orderErrorFormat);
					checkNoiDung = checkDataFromFileExel(noiDung.trim(), 4, orderErrorFormat);
					checkMaKhoXuat = checkDataFromFileExel(maKhoXuat.trim(), 5, orderErrorFormat);
					checkTenVatTu = checkDataFromFileExel(tenVatTu.trim(), 6, orderErrorFormat);
					checkMaVatTu = checkDataFromFileExel(maVatTu.trim(), 7, orderErrorFormat);
					checkDonViTinh = checkDataFromFileExel(donViTinh.trim(), 8, orderErrorFormat);
					checkSoLuongXuat = checkDataFromFileExel(soLuongXuat.trim(), 9, orderErrorFormat);
					checkDonGia = checkDataFromFileExel(donGia.trim(), 10, orderErrorFormat);
					checkThanhTien = checkDataFromFileExel(thanhTien.trim(), 11, orderErrorFormat);
					checkMaCongTrinh = checkDataFromFileExel(maCongTrinh.trim(), 12, orderErrorFormat);
					checkSoLuongYeuCau = checkDataFromFileExel(soLuongYeuCau.trim(), 13, orderErrorFormat);
					checkLyDo = checkDataFromFileExel(lyDo.trim(), 14, orderErrorFormat);
					checkDienGiai = checkDataFromFileExel(dienGiai.trim(), 15, orderErrorFormat);
					checkTenKhoXuat = checkDataFromFileExel(tenKhoXuat.trim(), 16, orderErrorFormat);
					checkMaKhoNhan = checkDataFromFileExel(maKhoNhan.trim(), 17, orderErrorFormat);
					checkKhoNhan = checkDataFromFileExel(khoNhan.trim(), 18, orderErrorFormat);
					checkDonViNhan = checkDataFromFileExel(donViNhan.trim(), 19, orderErrorFormat);
					checkNguoiNhanHang = checkDataFromFileExel(nguoiNhanHang.trim(), 20, orderErrorFormat);

					if (checkSoPhieuXuat && checkNgayXuat && checkNoiDung && checkMaKhoXuat && checkTenVatTu
							&& checkMaVatTu && checkDonViTinh && checkSoLuongXuat && checkDonGia && checkThanhTien
							&& checkMaCongTrinh && checkNguoiNhanHang && checkSoLuongYeuCau && checkLyDo
							&& checkDienGiai && checkTenKhoXuat && checkMaKhoNhan && checkSPXLogistic && checkDonViNhan
							&& checkKhoNhan && checkMaKhoNhan) {
						TblQltsThucXuatTheoKyDTO newObj = new TblQltsThucXuatTheoKyDTO();
						newObj.setSoPhieuXuat(soPhieuXuat);
						newObj.setSoPhieuXuatBenLogistic(sPXLogistic);
						if (StringUtils.isNotEmpty(ngayXuat)) {
							newObj.setNgayXuat(dt.parse(ngayXuat));
						}
						newObj.setNoiDung(noiDung);
						newObj.setMaKhoXuat(maKhoXuat);
						newObj.setTenVatTu(tenVatTu);
						newObj.setMaVatTu(maVatTu);
						newObj.setDonViTinh(donViTinh);
						if (!StringUtils.isEmpty(soLuongXuat)) {
							soLuongXuat = convertValue(soLuongXuat);
							newObj.setSoLuongXuat(Float.parseFloat(soLuongXuat));
						} else {
							newObj.setSoLuongXuat((float) 0);
						}

						if (!StringUtils.isEmpty(donGia)) {
							donGia = convertValue(donGia);
							newObj.setDonGia(Float.parseFloat(donGia));
						} else {
							newObj.setDonGia((float) 0);
						}
						thanhTien = convertValue(thanhTien);
						newObj.setThanhTien(Float.parseFloat(thanhTien));
						newObj.setMaCongTrinh(maCongTrinh);
						newObj.setNguoiNhanHang(nguoiNhanHang);
						if (!StringUtils.isEmpty(soLuongYeuCau)) {
							soLuongYeuCau = convertValue(soLuongYeuCau);
							newObj.setSoLuongYeuCau(Float.parseFloat(soLuongYeuCau));
						} else {
							newObj.setSoLuongYeuCau((float) 0);
						}
						newObj.setKhoNhan(khoNhan);
						newObj.setDonViNhan(donViNhan);
						newObj.setLyDo(lyDo);
						newObj.setDienGiai(dienGiai);
						newObj.setTenKhoXuat(tenKhoXuat);
						newObj.setMaKhoNhan(maKhoNhan);
						newObj.setHoatDong((long) 1);
						newObj.setXoa((long) 0);
						tblQltsThucXuatTheoKyDAO.deleteObj(newObj);
						workLst.add(newObj);
						// save(newObj);
					} else {
						ImportErrDTO errObj = new ImportErrDTO();
						errObj.setColumn1(soPhieuXuat);
						errObj.setColumn2(sPXLogistic);
						errObj.setColumn3(ngayXuat);
						errObj.setColumn4(noiDung);
						errObj.setColumn5(maKhoXuat);
						errObj.setColumn6(tenVatTu);
						errObj.setColumn7(maVatTu);
						errObj.setColumn8(donViTinh);
						errObj.setColumn9(soLuongXuat);
						errObj.setColumn10(donGia);
						errObj.setColumn11(thanhTien);
						errObj.setColumn12(maCongTrinh);
						errObj.setColumn13(soLuongYeuCau);
						errObj.setColumn14(lyDo);
						errObj.setColumn15(dienGiai);
						errObj.setColumn16(tenKhoXuat);
						errObj.setColumn17(maKhoNhan);
						errObj.setColumn18(khoNhan);
						errObj.setColumn19(donViNhan);
						errObj.setColumn20(nguoiNhanHang);
						errObj.setLstErrorOrder(lstErrExcelDto);
						workFault.add(errObj);
					}
				} else if (sheet.getLastRowNum() < 10) {
					orderErrorFormat.setLineError(String.valueOf(count));
					orderErrorFormat.setColumnError(String.valueOf(1));
					orderErrorFormat.setDetailError("File excel không đúng");
					lstErrExcelDto.add(orderErrorFormat);
				}

			}

			workbook.close();

			System.out.println("size done: " + workLst.size());
			System.out.println("size fault: " + workFault.size());
			if (workLst.size() < 0 && workFault.size() < 0) {
				throw new IllegalArgumentException("File import không có dữ liệu");
			} else {
				if (workLst.size() > 0) {
					saveList(workLst);
					long tEnd = System.currentTimeMillis();
					lsThaoTacDTO.setChucNang("Import dữ liệu đầu vào thực xuất PXK type A");
					lsThaoTacDTO.setMoTa("Import "+workLst.size()+" bản ghi dữ liệu đầu vào thực xuất PXK type A ");
					lsThaoTacDTO.setThoiGianThucHien(tEnd-tStart);
					insertLSTT(lsThaoTacDTO,request);
				}

				if (workFault.size() > 0) {
					// exportExcelError(workFault);
					// throw new IllegalArgumentException("Có lỗi trong file
					// import");
					return exportExcelError(workFault);
				}
				// return (long) 1;
			}
		} catch (NullPointerException pointerException) {
			// pointerException.printStackTrace();
			LOGGER.error(pointerException.getMessage(), pointerException);
		} catch (Exception e) {
			// e.printStackTrace();
			LOGGER.error(e.getMessage(), e);
		}
		return "";
	}

	public String convertValue(String value) {
		String val = value.replaceAll(",", "");
		return val;
	}

	public boolean checkDataFromFileExel(String data, int columnIndex, ErrExcelDTO orderErrorFormat) {
		orderErrorFormat = new ErrExcelDTO();
		if (columnIndex == 1) {
			if (StringUtils.isEmpty(data)) {
				// orderErrorFormat.setDetailError("Số phiếu xuất đang để
				// trống");
				// lstErrExcelDto.add(orderErrorFormat);
				// return false;
			} else if (data.length() > 2000) {
				orderErrorFormat.setDetailError("Số phiếu xuất vượt quá 2000 ký tự");
				lstErrExcelDto.add(orderErrorFormat);
				return false;
			}

		} else if (columnIndex == 2) {
			if (!StringUtils.isEmpty(data) && data.length() > 2000) {
				orderErrorFormat.setDetailError("Số phiếu xuất bên logistic  vượt quá 2000 ký tự");
				lstErrExcelDto.add(orderErrorFormat);
				return false;
			}
			// if (data.isEmpty()) {
			// orderErrorFormat.setDetailError("Số phiếu xuất bên logistic đang
			// để trống");
			// lstErrExcelDto.add(orderErrorFormat);
			// return false;
			// } else if (data.length() > 2000) {
			// orderErrorFormat.setDetailError("Số phiếu xuất bên logistic vượt
			// quá 2000 ký
			// tự");
			// lstErrExcelDto.add(orderErrorFormat);
			// return false;
			// }
		} else if (columnIndex == 3) {
			if (!StringUtils.isEmpty(data)) {
				if (!ValidateUtils.isDate(data, fomat)) {
					orderErrorFormat.setDetailError("Ngày xuất phải ở định dạng dd/MM/yyyy");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}

		} else if (columnIndex == 4)

		{
			if (!StringUtils.isEmpty(data)) {
				if (data.length() > 1000) {
					orderErrorFormat.setDetailError("Nội dung vượt quá 1000 ký tự");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			} else {
				// orderErrorFormat.setDetailError("Nội dung đang để trống");
				// lstErrExcelDto.add(orderErrorFormat);
				// return false;
			}
		} else if (columnIndex == 5) {
			if (StringUtils.isEmpty(data)) {
				// orderErrorFormat.setDetailError("Mã kho xuất đang để trống");
				// lstErrExcelDto.add(orderErrorFormat);
				// return false;
			} else if (data.length() > 1000) {
				orderErrorFormat.setDetailError("Mã kho xuất vượt quá 1000 ký tự");
				lstErrExcelDto.add(orderErrorFormat);
				return false;
			}

		} else if (columnIndex == 6) {
			if (!StringUtils.isEmpty(data)) {
				if (data.length() > 1000) {
					orderErrorFormat.setDetailError("Tên vật tư vượt quá 1000 ký tự");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}

		} else if (columnIndex == 7) {
			if (StringUtils.isEmpty(data)) {
				// orderErrorFormat.setDetailError("Mã vật tư đang để trống");
				// lstErrExcelDto.add(orderErrorFormat);
				// return false;
			} else if (data.length() > 1000) {
				orderErrorFormat.setDetailError("Mã vật tư vượt quá 1000 ký tự");
				lstErrExcelDto.add(orderErrorFormat);
				return false;
			}
		} else if (columnIndex == 8) {
			if (!StringUtils.isEmpty(data)) {
				if (data.length() > 1000) {
					orderErrorFormat.setDetailError("Đơn vị tính vượt quá 1000 ký tự");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}
		} else if (columnIndex == 9) {
			if (!StringUtils.isEmpty(data)) {
				if (!ValidateUtils.isFloat(data)) {
					if (!ValidateUtils.isFloat(convertValue(data))) {
						orderErrorFormat.setDetailError("Số lượng xuất không đúng định dạng");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}

				}
				if (data.contains(".")) {
					if (data.replace(".", "").length() > 30) {
						orderErrorFormat.setDetailError("Số lượng xuất vượt quá 30 ký tự");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				} else {
					if (data.length() > 30) {
						orderErrorFormat.setDetailError("Số lượng xuất vượt quá 30 ký tự");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				}
			} else {
				// orderErrorFormat.setDetailError("Số lượng xuất không được
				// để
				// trống");
				// lstErrExcelDto.add(orderErrorFormat);
				// return false;
			}

		} else if (columnIndex == 10) {
			if (!StringUtils.isEmpty(data)) {
				if (!ValidateUtils.isFloat(data)) {
					if (!ValidateUtils.isFloat(convertValue(data))) {
						orderErrorFormat.setDetailError("Đơn giá không đúng định dạng");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}

				}
				if (data.contains(".")) {
					if (data.replace(".", "").length() > 30) {
						orderErrorFormat.setDetailError("Đơn giá vượt quá 30 ký tự");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				} else {
					if (data.length() > 30) {
						orderErrorFormat.setDetailError("Đơn giá vượt quá 30 ký tự");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				}
			} else {
				// orderErrorFormat.setDetailError("Đơn giá không được để
				// trống");
				// lstErrExcelDto.add(orderErrorFormat);
				// return false;
			}

		} else if (columnIndex == 11) {
			if (!StringUtils.isEmpty(data)) {
				if (!ValidateUtils.isFloat(data)) {
					if (!ValidateUtils.isFloat(convertValue(data))) {
						orderErrorFormat.setDetailError("Thành tiền không đúng định dạng");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}

				}
				if (data.contains(".")) {
					if (data.replace(".", "").length() > 30) {
						orderErrorFormat.setDetailError("Thành tiền vượt quá 30 ký tự");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				} else {
					if (data.length() > 30) {
						orderErrorFormat.setDetailError("Thành tiền vượt quá 30 ký tự");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				}
			} else {
				// orderErrorFormat.setDetailError("Thành tiền đang để trống");
				// lstErrExcelDto.add(orderErrorFormat);
				// return false;
			}

		} else if (columnIndex == 12) {
			if (!StringUtils.isEmpty(data)) {
				if (data.length() > 1000) {
					orderErrorFormat.setDetailError("Mã công trình vượt quá 1000 ký tự");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			} else {
				// orderErrorFormat.setDetailError("Mã công trình đang để
				// trống");
				// lstErrExcelDto.add(orderErrorFormat);
				// return false;
			}

		} else if (columnIndex == 13) {
			if (!StringUtils.isEmpty(data)) {
				/*
				 * orderErrorFormat.
				 * setDetailError("Số lượng yêu cầu đang để trống");
				 * lstErrExcelDto.add(orderErrorFormat); return false;
				 */
				// if (!ValidateUtils.isFloat(data)) {
				// orderErrorFormat.setDetailError("Số lượng yêu cầu không đúng
				// định dạng");
				// lstErrExcelDto.add(orderErrorFormat);
				// return false;
				// }
				// if (data.contains(".")) {
				// if (data.replace(".", "").length() > 30) {
				// orderErrorFormat.setDetailError("Số lượng yêu cầu vượt quá 30
				// ký tự");
				// lstErrExcelDto.add(orderErrorFormat);
				// return false;
				// }
				// } else {
				// if (data.length() > 30) {
				// orderErrorFormat.setDetailError("Số lượng yêu cầu vượt quá 30
				// ký tự");
				// lstErrExcelDto.add(orderErrorFormat);
				// return false;
				// }
				// }
			} else if (!ValidateUtils.isFloat(data)) {
				if (!ValidateUtils.isFloat(convertValue(data))) {
					orderErrorFormat.setDetailError("Số lượng yêu cầu không đúng định dạng");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}

		} else if (columnIndex == 14) {
			// if (!data.isEmpty()) {
			// if (data.length() > 1000) {
			// orderErrorFormat.setDetailError("Lý do vượt quá 1000 ký tự");
			// lstErrExcelDto.add(orderErrorFormat);
			// return false;
			// }
			// }
		} else if (columnIndex == 15) {
			if (!StringUtils.isEmpty(data)) {
				if (data.length() > 1000) {
					orderErrorFormat.setDetailError("Diễn giải vượt quá 1000 ký tự");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			} else {
				// orderErrorFormat.setDetailError("Diễn giải Đang để trống");
				// lstErrExcelDto.add(orderErrorFormat);
				// return false;
			}
		} else if (columnIndex == 16) {
			// if (data.isEmpty()) {
			// orderErrorFormat.setDetailError("Tên kho xuất đang để trống");
			// lstErrExcelDto.add(orderErrorFormat);
			// return false;
			// }
			// if (data.length() > 1000) {
			// orderErrorFormat.setDetailError("Tên kho xuất vượt quá 1000 ký
			// tự");
			// lstErrExcelDto.add(orderErrorFormat);
			// return false;
			// }
		} else if (columnIndex == 17) {
			// if (data.isEmpty()) {
			// orderErrorFormat.setDetailError("Mã kho nhận đang để trống");
			// lstErrExcelDto.add(orderErrorFormat);
			// return false;
			// }
			// if (data.length() > 2000) {
			// orderErrorFormat.setDetailError("Mã kho nhận vượt quá 2000 ký
			// tự");
			// lstErrExcelDto.add(orderErrorFormat);
			// return false;
			// }
		} else if (columnIndex == 18) {
			// if (data.isEmpty()) {
			// orderErrorFormat.setDetailError("Kho nhận đang để trống");
			// lstErrExcelDto.add(orderErrorFormat);
			// return false;
			// }
			// if (data.length() > 2000) {
			// orderErrorFormat.setDetailError("Kho nhận vượt quá 2000 ký tự");
			// lstErrExcelDto.add(orderErrorFormat);
			// return false;
			// }
		} else if (columnIndex == 19) {
			// if (data.isEmpty()) {
			// orderErrorFormat.setDetailError("Đơn vị nhận đang để trống");
			// lstErrExcelDto.add(orderErrorFormat);
			// return false;
			// }
			// if (data.length() > 1000) {
			// orderErrorFormat.setDetailError("Đơn vị nhận vượt quá 1000 ký
			// tự");
			// lstErrExcelDto.add(orderErrorFormat);
			// return false;
			// }
		} else if (columnIndex == 20) {
			// if (!data.isEmpty()) {
			// if (data.length() > 2000) {
			// orderErrorFormat.setDetailError("Người nhận hàng vượt quá 2000 ký
			// tự");
			// lstErrExcelDto.add(orderErrorFormat);
			// return false;
			// }
			// }
		}

		return true;
	}

	public String exportExcelError(List<ImportErrDTO> obj) throws Exception {
		// TODO Auto-generated method stub
		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream file = new BufferedInputStream(new FileInputStream(filePath + "ThucXuatTheoKyErr.xlsx"));
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
			int rownum = 10;
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
			}
			file.close();

			File out = new File(folder2Upload + File.separatorChar + "ThucXuatTheoKyErr" + startExportTime + ".xlsx");

			FileOutputStream outFile = new FileOutputStream(out);
			System.out.println(out.getCanonicalPath());
			workbook.write(outFile);
			workbook.close();
			outFile.close();
		} catch (FileNotFoundException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
		// String path = UEncrypt.encryptFileUploadPath("BaoCaoThucXuatTheoKy" +
		// startExportTime +".xlsx");
		return UEncrypt.encryptFileUploadPath("ThucXuatTheoKyErr" + startExportTime + ".xlsx");
	}

	@Override
	@Transactional
	public Long updateChiNhanh(TblTypeAPxkDTO obj, HttpServletRequest request) throws Exception {
		try {
			TblQltsThucXuatTheoKyDTO dto = new TblQltsThucXuatTheoKyDTO();
			long ids = 0l;
			if (StringUtils.isNotEmpty(obj.getMaChiNhanh())) {
				tblTypeAPxkBusinessImpl.updateTypeA(obj, 0l);
				dto.setMaChiNhanh(obj.getMaChiNhanh());
				dto.setSoPhieuXuat(obj.getMaPhieuXuat());
				lsThaoTacDTO.setChucNang("Gán khu vực cho PXK");
				lsThaoTacDTO.setMoTa("Gán khu vực cho PXK :"+dto.getSoPhieuXuat());
				insertLSTT(lsThaoTacDTO,request);
				ids = tblQltsThucXuatTheoKyDAO.updateList(dto);
			} else if (StringUtils.isNotEmpty(obj.getMaTinh())) {
				tblTypeAPxkBusinessImpl.updateTypeA(obj, 1l);
				dto.setMaTinh(obj.getMaTinh());
				dto.setSoPhieuXuat(obj.getMaPhieuXuat());
				lsThaoTacDTO.setChucNang("Gán 62 TTKT cho PXK");
				lsThaoTacDTO.setMoTa("Gán 62 TTKT cho PXK "+dto.getSoPhieuXuat());
				insertLSTT(lsThaoTacDTO,request);
				ids = tblQltsThucXuatTheoKyDAO.updateList1(dto);
				
			}
			return ids;
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Có lỗi khi gán chi nhánh 5 KV");
		}

	}

	/**
	 * Gán phiếu xuất kho cho nhân viên chịu trách nhiệm 
	 */
	@Override
	@Transactional
	public Long updateNhanVien(TblTypeAPxkDTO obj, HttpServletRequest request) throws Exception {
		try {

			TblQltsThucXuatTheoKyDTO dto = new TblQltsThucXuatTheoKyDTO();
			long ids = 0l;
			if (StringUtils.isNotEmpty(obj.getMaNv())) {
				tblTypeAPxkBusinessImpl.updateTypeNV(obj);
				dto.setMaNv(obj.getMaNv());
				dto.setSoPhieuXuat(obj.getMaPhieuXuat());
				lsThaoTacDTO.setChucNang("Gán nhân viên cho PXK");
				////sửa ở đây
				lsThaoTacDTO.setMoTa("Gán nhân viên cho PXK :"+dto.getSoPhieuXuat());
				insertLSTT(lsThaoTacDTO,request);
				ids = tblQltsThucXuatTheoKyDAO.updateListNV(dto);
			}
			return ids;
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Có lỗi khi gán chi nhánh 5 KV");
		}

	}

	@Override
	@Transactional
	public Long updateSLByNV(TblQltsThucXuatTheoKyDTO obj, HttpServletRequest request) throws Exception {
		try {
			long ids = 0l;
			List<TblQltsThucXuatTheoKyDTO> lst = obj.getLstThucXuat();
			TblTypeAPxkDTO aPxkDTO = new TblTypeAPxkDTO();
			aPxkDTO.setTblTypeAPxkId(obj.getTblTypeAPxkId());
			aPxkDTO.setCheckNhapLieu(obj.getCheckNhapLieu());
			tblTypeAPxkBusinessImpl.updateCheck(aPxkDTO);
			for (TblQltsThucXuatTheoKyDTO kyDTO : lst) {
				kyDTO.setHoatDong((long) 1);
				ids = update(kyDTO);
			}
			lsThaoTacDTO.setChucNang("Cập nhật số lượng cho cho PXK");
			lsThaoTacDTO.setMoTa("Cập nhật số lượng cho "+lst.size()+" bản ghi của PXK ");
			insertLSTT(lsThaoTacDTO,request);
			return ids;
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Có lỗi khi gán chi nhánh 5 KV");
		}

	}

	// @Override
	// public List<TblQltsThucXuatTheoKyDTO>
	// selectAllNhanVienInTX(TblQltsThucXuatTheoKyDTO obj) {
	// // TODO Auto-generated method stub
	// return tblQltsThucXuatTheoKyDAO.selectAllNhanVienInTX(obj);
	// }
	//
	public String exportExcelTongHopTheoNV(TblQltsThucXuatTheoKyDTO obj2, HttpServletRequest request) throws Exception {
		List<TblQltsThucXuatTheoKyDTO> lst = tblQltsThucXuatTheoKyDAO.selectAllNhanVienInTX1(obj2);
		List<TblQltsThucXuatTheoKyDTO> obj = new ArrayList<>();
		String hoTen = "";
		int index = 0;
		String gt1 = "";
		String gt2 = "";
		String gt3 = "";
		String gt4 = "";

		TblQltsThucXuatTheoKyDTO sumobj = new TblQltsThucXuatTheoKyDTO();

		List<TblQltsThucXuatTheoKyDTO> lstSum = new ArrayList<>();
		float sumThanhTien = 0;
		float sumTtThucTeTC = 0;
		float sumTtThuHoi = 0;
		float sumTtTienDen = 0;
		float sunDoDang = 0;
		for (TblQltsThucXuatTheoKyDTO kyDTO : lst) {
			TblQltsThucXuatTheoKyDTO obj1 = new TblQltsThucXuatTheoKyDTO();
			obj1 = tblQltsThucXuatTheoKyDAO.sumValue(kyDTO.getMaNv(), kyDTO.getSoPhieuXuat());
			if (obj1.getThanhTien() == null) {
				obj1.setThanhTien((float) 0);
			}
			sumThanhTien += Math.floor(obj1.getThanhTien());
			if (obj1.getTtThucTeTC() == null) {
				obj1.setTtThucTeTC((float) 0);
			}
			sumTtThucTeTC += Math.floor(obj1.getTtThucTeTC());
			if (obj1.getTtThuHoi() == null) {
				obj1.setTtThuHoi((float) 0);
			}
			sumTtThuHoi += Math.floor(obj1.getTtThuHoi());
			if (obj1.getTtTienDen() == null) {
				obj1.setTtTienDen((float) 0);
			}
			sumTtTienDen += Math.floor(obj1.getTtTienDen());
			obj1.setHoVaTen(lst.get(index).getHoVaTen());
			if (StringUtils.isNotEmpty(lst.get(index).getMaChiNhanh())) {
				obj1.setDonViNhan(lst.get(index).getMaChiNhanh());
			} else if (StringUtils.isNotEmpty(lst.get(index).getMaTinh())) {
				obj1.setDonViNhan(lst.get(index).getMaTinh());
			}
			obj1.setMaKhoNhan(lst.get(index).getMaKhoNhan());

			obj1.setLyDo(lst.get(index).getLyDo());
			obj1.setSoPhieuXuat(lst.get(index).getSoPhieuXuat());
			obj1.setMaTramNhan(lst.get(index).getMaTramNhan());
			obj1.setMaCongTrinh(lst.get(index).getMaCongTrinh());
			obj1.setMaHdxl(lst.get(index).getMaHdxl());
			obj.add(obj1);
			index++;
		}

		// TODO Auto-generated method stub
		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream file = new BufferedInputStream(new FileInputStream(filePath + "ExportBCACapTheoNV.xlsx"));

			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);
			// set style
			XSSFCellStyle style = workbook.createCellStyle();
			XSSFCellStyle style1 = workbook.createCellStyle();
			XSSFCellStyle styleNumber = workbook.createCellStyle();
			XSSFFont hSSFFont = workbook.createFont();
			hSSFFont.setFontName("Times New Roman");
			style.setFont(hSSFFont);

			try {
				XSSFFont hSSFFont1 = workbook.createFont();
				hSSFFont1.setFontName("Times New Roman");
				hSSFFont1.setFontHeightInPoints((short) 12);
				hSSFFont1.setBold(true);
				style1.setFont(hSSFFont1);
				style1.setWrapText(true);
				style1.setAlignment(CellStyle.ALIGN_CENTER);
			} catch (Exception e) {
				e.printStackTrace();
			}

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

			XSSFRow row = null;

			row = sheet.createRow(6);
			XSSFCell cell110 = row.createCell(0);
			if (obj2.getNgayXuatFrom() != null) {
				cell110.setCellValue("Từ ngày : " + dt.format(obj2.getNgayXuatFrom()) + " đến ngày : "
						+ dt.format(obj2.getNgayXuatTo()));
			} else {
				cell110.setCellValue("Từ ngày :...../..../...... đến ngày :...../..../......");
			}
			cell110.setCellStyle(style1);

			int rownum = 10;

			for (int i = 0; i < obj.size(); i++) {
				// StringBuilder err = new StringBuilder();

				row = sheet.createRow(rownum);
				XSSFCell cell0 = row.createCell(0);
				cell0.setCellValue(i + 1);
				cell0.setCellStyle(style);
				rownum++;

				XSSFCell cell1 = row.createCell(1);
				cell1.setCellValue(obj.get(i).getHoVaTen());

				XSSFCell cell2 = row.createCell(2);
				cell2.setCellValue(obj.get(i).getDonViNhan());

				XSSFCell cell3 = row.createCell(3);
				cell3.setCellValue(obj.get(i).getSoPhieuXuat());

				XSSFCell cell4 = row.createCell(4);
				cell4.setCellValue(obj.get(i).getMaTramNhan());

				XSSFCell cell5 = row.createCell(5);
				cell5.setCellValue(obj.get(i).getMaCongTrinh());

				XSSFCell cell6 = row.createCell(6);
				cell6.setCellValue(obj.get(i).getMaHdxl());

				XSSFCell cell7 = row.createCell(7);
				if (obj.get(i).getThanhTien() == null || obj.get(i).getThanhTien() == (float) 0) {
					obj.get(i).setThanhTien((float) 0);
					cell7.setCellValue(obj.get(i).getThanhTien());
				} else if (obj.get(i).getThanhTien() != (float) 0) {
					cell7.setCellValue(Math.ceil(obj.get(i).getThanhTien()));
					// cell7.setCellValue(dFormat.format(obj.get(i).getThanhTien()));

				}
				gt1 = dFormat.format(Math.ceil(obj.get(i).getThanhTien()));
				gt1 = convertValue(gt1);
				XSSFCell cell8 = row.createCell(8);
				if (obj.get(i).getTtThucTeTC() == null || obj.get(i).getTtThucTeTC() == (float) 0) {
					obj.get(i).setTtThucTeTC((float) 0);
					cell8.setCellValue(obj.get(i).getTtThucTeTC());
				} else if (obj.get(i).getTtThucTeTC() != (float) 0) {
					cell8.setCellValue(Math.ceil(obj.get(i).getTtThucTeTC()));
					// cell8.setCellValue(dFormat.format(obj.get(i).getTtThucTeTC()));

				}
				gt2 = dFormat.format(Math.ceil(obj.get(i).getTtThucTeTC()));
				gt2 = convertValue(gt2);
				XSSFCell cell9 = row.createCell(9);
				if (obj.get(i).getTtThuHoi() == null || obj.get(i).getTtThuHoi() == (float) 0) {
					obj.get(i).setTtThuHoi((float) 0);
					cell9.setCellValue(obj.get(i).getTtThuHoi());
				} else if (obj.get(i).getTtThuHoi() != (float) 0) {
					cell9.setCellValue(Math.ceil(obj.get(i).getTtThuHoi()));
					// cell9.setCellValue(dFormat.format(obj.get(i).getTtThuHoi()));

				}
				gt3 = dFormat.format(Math.ceil(obj.get(i).getTtThuHoi()));
				gt3 = convertValue(gt3);
				XSSFCell cell10 = row.createCell(10);
				if (obj.get(i).getTtTienDen() == null || obj.get(i).getTtTienDen() == (float) 0) {
					obj.get(i).setTtTienDen((float) 0);
					cell10.setCellValue(obj.get(i).getTtTienDen());
				} else if (obj.get(i).getTtTienDen() != (float) 0) {
					// cell10.setCellValue(dFormat.format(obj.get(i).getTtTienDen()));
					cell10.setCellValue(Math.ceil(obj.get(i).getTtTienDen()));
				}

				gt4 = dFormat.format(Math.ceil(obj.get(i).getTtTienDen()));
				gt4 = convertValue(gt4);
				XSSFCell cell11 = row.createCell(11);
				float du = Float.parseFloat(gt1) - Float.parseFloat(gt2) - Float.parseFloat(gt3)
						- Float.parseFloat(gt4);
				if (du == (float) 0) {
					du = 0l;
					cell11.setCellValue(du);
				} else if (du != (float) 0) {
					cell11.setCellValue(du);
					// cell11.setCellValue(dFormat.format(du));
				}
				sunDoDang += Math.ceil(du);

				/// ghi chus
				XSSFCell cell12 = row.createCell(12);

				cell0.setCellStyle(style);
				cell1.setCellStyle(style);
				cell2.setCellStyle(style);
				cell3.setCellStyle(style);
				cell4.setCellStyle(style);
				cell5.setCellStyle(style);
				cell6.setCellStyle(style);
				cell7.setCellStyle(styleNumber);
				cell8.setCellStyle(styleNumber);
				cell9.setCellStyle(styleNumber);
				cell10.setCellStyle(styleNumber);
				cell11.setCellStyle(styleNumber);
				cell12.setCellStyle(style);

			}
			// sheet.addMergedRegion(new CellRangeAddress(obj.size(),
			// obj.size(), 1, 2));
			row = sheet.createRow(obj.size() + 10);
			XSSFCell cell11 = row.createCell(1);
			cell11.setCellValue("Tổng");
			cell11.setCellStyle(style);

			XSSFCell cell13 = row.createCell(2);
			cell13.setCellValue("");
			cell13.setCellStyle(style);
			XSSFCell cell131 = row.createCell(3);
			cell131.setCellValue("");
			cell131.setCellStyle(style);
			XSSFCell cell132 = row.createCell(4);
			cell132.setCellValue("");
			cell132.setCellStyle(style);
			XSSFCell cell133 = row.createCell(5);
			cell133.setCellValue("");
			cell133.setCellStyle(style);
			XSSFCell cell134 = row.createCell(6);
			cell134.setCellValue("");
			cell134.setCellStyle(style);

			XSSFCell cell135 = row.createCell(7);
			cell135.setCellValue(sumThanhTien);
			cell135.setCellStyle(style);

			XSSFCell cell14 = row.createCell(8);
			cell14.setCellValue(sumTtThucTeTC);
			cell14.setCellStyle(style);

			XSSFCell cell15 = row.createCell(9);
			cell15.setCellValue(sumTtThuHoi);
			cell15.setCellStyle(style);

			XSSFCell cell16 = row.createCell(10);
			cell16.setCellValue(sumTtTienDen);
			cell16.setCellStyle(style);

			XSSFCell cell17 = row.createCell(11);
			cell17.setCellValue(sunDoDang);
			cell17.setCellStyle(style);

			XSSFCell cell112 = row.createCell(12);
			cell112.setCellValue("");
			cell112.setCellStyle(style);

			file.close();

			File out = new File(folder2Upload + File.separatorChar + "ExportBCACapTheoNV" + startExportTime + ".xlsx");

			FileOutputStream outFile = new FileOutputStream(out);
			System.out.println(out.getCanonicalPath());
			workbook.write(outFile);
			workbook.close();
			outFile.close();
		} catch (FileNotFoundException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
		// String path = UEncrypt.encryptFileUploadPath("BaoCaoThucXuatTheoKy" +
		// startExportTime +".xlsx");
		
		lsThaoTacDTO.setChucNang("Export dữ liệu tổng hợp theo nhân viên type A");
		lsThaoTacDTO.setMoTa("Export "+obj.size()+" bản ghi dữ liệu tổng hợp theo nhân viên type A ");
		insertLSTT(lsThaoTacDTO,request);
		return UEncrypt.encryptFileUploadPath("ExportBCACapTheoNV" + startExportTime + ".xlsx");
	}

	public String exportExcelTongHopTheoDV(TblQltsThucXuatTheoKyDTO obj11, HttpServletRequest request) throws Exception {
		List<TblQltsThucXuatTheoKyDTO> lst = tblQltsThucXuatTheoKyDAO.selectAllDonViInTX1(obj11);
		List<TblQltsThucXuatTheoKyDTO> obj = new ArrayList<>();
		String hoTen = "";
		int index = 0;
		String gt1 = "";
		String gt2 = "";
		String gt3 = "";
		String gt4 = "";

		int sumThanhTien = 0;
		int sumTtThucTeTC = 0;
		int sumTtThuHoi = 0;
		int sumTtTienDen = 0;
		int sunDoDang = 0;
		for (TblQltsThucXuatTheoKyDTO kyDTO : lst) {
			TblQltsThucXuatTheoKyDTO obj1 = new TblQltsThucXuatTheoKyDTO();
			obj1 = tblQltsThucXuatTheoKyDAO.sumValueByDonVi(kyDTO.getMaChiNhanh(), kyDTO.getMaTinh(),
					kyDTO.getSoPhieuXuat());
			if (obj1.getThanhTien() == null) {
				obj1.setThanhTien((float) 0);
			}
			sumThanhTien += Math.floor(obj1.getThanhTien());
			if (obj1.getTtThucTeTC() == null) {
				obj1.setTtThucTeTC((float) 0);
			}
			sumTtThucTeTC += Math.floor(obj1.getTtThucTeTC());
			if (obj1.getTtThuHoi() == null) {
				obj1.setTtThuHoi((float) 0);
			}
			sumTtThuHoi += Math.floor(obj1.getTtThuHoi());
			if (obj1.getTtTienDen() == null) {
				obj1.setTtTienDen((float) 0);
			}
			sumTtTienDen += Math.floor(obj1.getTtTienDen());
			obj1.setMaKhoNhan(lst.get(index).getMaKhoNhan());
			obj1.setDonViNhan(lst.get(index).getDonViNhan());
			obj1.setLyDo(lst.get(index).getLyDo());
			obj1.setMaChiNhanh(lst.get(index).getMaChiNhanh());
			obj1.setMaTinh(lst.get(index).getMaTinh());
			obj1.setSoPhieuXuat(lst.get(index).getSoPhieuXuat());
			obj.add(obj1);
			index++;
		}

		// TODO Auto-generated method stub
		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream file = new BufferedInputStream(new FileInputStream(filePath + "ExportBCACapTheoDV.xlsx"));

			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);
			// set style
			XSSFCellStyle style = workbook.createCellStyle();
			XSSFCellStyle style1 = workbook.createCellStyle();
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

			try {
				XSSFFont hSSFFont1 = workbook.createFont();
				hSSFFont1.setFontName("Times New Roman");
				hSSFFont1.setFontHeightInPoints((short) 12);
				hSSFFont1.setBold(true);
				style1.setFont(hSSFFont1);
				style1.setWrapText(true);
				style1.setAlignment(CellStyle.ALIGN_CENTER);
			} catch (Exception e) {
				e.printStackTrace();
			}

			XSSFRow row = null;

			row = sheet.createRow(6);
			XSSFCell cell110 = row.createCell(0);
			if (obj11.getNgayXuatFrom() != null) {
				cell110.setCellValue("Từ ngày : " + dt.format(obj11.getNgayXuatFrom()) + " đến ngày : "
						+ dt.format(obj11.getNgayXuatTo()));
			} else {
				cell110.setCellValue("Từ ngày :...../..../...... đến ngày :...../..../......");
			}
			cell110.setCellStyle(style1);

			int rownum = 10;
			for (int i = 0; i < obj.size(); i++) {
				// StringBuilder err = new StringBuilder();

				row = sheet.createRow(rownum);
				XSSFCell cell0 = row.createCell(0);
				cell0.setCellValue(i + 1);
				cell0.setCellStyle(style);
				rownum++;

				XSSFCell cell1 = row.createCell(1);
				if (obj.get(i).getMaChiNhanh() != null) {
					cell1.setCellValue(obj.get(i).getMaChiNhanh());
				} else {
					cell1.setCellValue(obj.get(i).getMaTinh());
				}

				// XSSFCell cell2 = row.createCell(2);
				// cell2.setCellValue(obj.get(i).getSoPhieuXuat());
				//
				// XSSFCell cell3 = row.createCell(3);
				// cell3.setCellValue(obj.get(i).getMaKhoNhan());
				//
				// XSSFCell cell4 = row.createCell(4);
				// cell4.setCellValue(obj.get(i).getLyDo());

				XSSFCell cell2 = row.createCell(2);
				if (obj.get(i).getThanhTien() == null || obj.get(i).getThanhTien() == (float) 0) {
					obj.get(i).setThanhTien((float) 0);
					cell2.setCellValue(obj.get(i).getThanhTien());
				} else if (obj.get(i).getThanhTien() != (float) 0) {
					cell2.setCellValue(Math.ceil(obj.get(i).getThanhTien()));
					// cell5.setCellValue(dFormat.format(obj.get(i).getThanhTien()));
				}
				gt1 = dFormat.format(Math.ceil(obj.get(i).getThanhTien()));
				gt1 = convertValue(gt1);

				XSSFCell cell3 = row.createCell(3);
				if (obj.get(i).getTtThucTeTC() == null || obj.get(i).getTtThucTeTC() == (float) 0) {
					obj.get(i).setTtThucTeTC((float) 0);
					cell3.setCellValue(obj.get(i).getTtThucTeTC());
				} else if (obj.get(i).getTtThucTeTC() != (float) 0) {
					// cell6.setCellValue(dFormat.format(obj.get(i).getTtThucTeTC()));
					cell3.setCellValue(Math.ceil(obj.get(i).getTtThucTeTC()));
				}
				gt2 = dFormat.format(Math.ceil(obj.get(i).getTtThucTeTC()));
				gt2 = convertValue(gt2);

				XSSFCell cell4 = row.createCell(4);
				if (obj.get(i).getTtThuHoi() == null || obj.get(i).getTtThuHoi() == (float) 0) {
					obj.get(i).setTtThuHoi((float) 0);
					cell4.setCellValue(obj.get(i).getTtThuHoi());
				} else if (obj.get(i).getTtThuHoi() != (float) 0) {
					cell4.setCellValue(Math.ceil(obj.get(i).getTtThuHoi()));
					// cell7.setCellValue(dFormat.format(obj.get(i).getTtThuHoi()));
				}
				gt3 = dFormat.format(Math.ceil(obj.get(i).getTtThuHoi()));
				gt3 = convertValue(gt3);

				XSSFCell cell5 = row.createCell(5);
				if (obj.get(i).getTtTienDen() == null || obj.get(i).getTtTienDen() == (float) 0) {
					obj.get(i).setTtTienDen((float) 0);
					cell5.setCellValue(obj.get(i).getTtTienDen());
				} else if (obj.get(i).getTtTienDen() != (float) 0) {
					cell5.setCellValue(Math.ceil(obj.get(i).getTtTienDen()));
					// cell8.setCellValue(dFormat.format(obj.get(i).getTtTienDen()));
				}
				gt4 = dFormat.format(Math.ceil(obj.get(i).getTtTienDen()));
				gt4 = convertValue(gt4);

				XSSFCell cell6 = row.createCell(6);
				float du = Float.parseFloat(gt1) - Float.parseFloat(gt2) - Float.parseFloat(gt3)
						- Float.parseFloat(gt4);
				if (du == (float) 0) {
					du = 0l;
					cell6.setCellValue(du);
				} else if (du != (float) 0) {
					// cell9.setCellValue(dFormat.format(du));
					cell6.setCellValue(Math.ceil(du));
				}

				sunDoDang += Math.ceil(du);

				/// ghi chus
				XSSFCell cell7 = row.createCell(7);

				cell0.setCellStyle(style);
				cell1.setCellStyle(styleNumber);
				cell2.setCellStyle(styleNumber);
				cell3.setCellStyle(styleNumber);
				cell4.setCellStyle(styleNumber);
				cell5.setCellStyle(styleNumber);
				cell6.setCellStyle(styleNumber);
				cell7.setCellStyle(style);

			}
			row = sheet.createRow(obj.size() + 10);
			XSSFCell cell11 = row.createCell(1);
			cell11.setCellValue("Tổng");
			cell11.setCellStyle(style);

			XSSFCell cell135 = row.createCell(2);
			cell135.setCellValue(sumThanhTien);
			cell135.setCellStyle(styleNumber);

			XSSFCell cell14 = row.createCell(3);
			cell14.setCellValue(sumTtThucTeTC);
			cell14.setCellStyle(styleNumber);

			XSSFCell cell15 = row.createCell(4);
			cell15.setCellValue(sumTtThuHoi);
			cell15.setCellStyle(styleNumber);

			XSSFCell cell16 = row.createCell(5);
			cell16.setCellValue(sumTtTienDen);
			cell16.setCellStyle(styleNumber);

			XSSFCell cell17 = row.createCell(6);
			cell17.setCellValue(sunDoDang);
			cell17.setCellStyle(styleNumber);

			XSSFCell cell112 = row.createCell(7);
			cell112.setCellValue("");
			cell112.setCellStyle(style);

			file.close();

			File out = new File(folder2Upload + File.separatorChar + "ExportBCACapTheoDV" + startExportTime + ".xlsx");

			FileOutputStream outFile = new FileOutputStream(out);
			System.out.println(out.getCanonicalPath());
			workbook.write(outFile);
			workbook.close();
			outFile.close();
		} catch (FileNotFoundException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
		// String path = UEncrypt.encryptFileUploadPath("BaoCaoThucXuatTheoKy" +
		// startExportTime +".xlsx");
		
		lsThaoTacDTO.setChucNang("Export dữ liệu tổng hợp theo đơn vị type A");
		lsThaoTacDTO.setMoTa("Export "+ obj.size()+" bản ghi dữ liệu tổng hợp theo đơn vị type A ");
		insertLSTT(lsThaoTacDTO,request);
		return UEncrypt.encryptFileUploadPath("ExportBCACapTheoDV" + startExportTime + ".xlsx");
	}
	
	public String insertLSTT(LsThaoTacDTO obj, HttpServletRequest request) throws ParseException {
		VpsUserToken token = (VpsUserToken) request.getSession().getAttribute("vpsUserToken");
		String ip = (String) request.getSession().getAttribute("VTS-IPWAN");
		try {
			String date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
			obj.setNgayThaoTac(d1t.parse(date));
			obj.setIpAdd(ip);
			obj.setFullName(token.getFullName());
			obj.setUserCode(token.getUserName());
			List<LsThaoTacDTO> lst=new ArrayList<>();
			lst.add(obj);
			return saveList1(lst);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Xảy ra lỗi khi thêm mới lịch sử đăng nhập!!!");
		}catch (NullPointerException ex) {
				throw new IllegalArgumentException("không có session!!!");
			}
	}
	@SuppressWarnings("unchecked")
	public String saveList1(List<LsThaoTacDTO> lstCenterBO) {
		@SuppressWarnings("rawtypes")
		List lstModel = new ArrayList<>();
		if (lstCenterBO != null) {
			for (int i = 0; i < lstCenterBO.size(); i++) {
				lstModel.add(((QllBaseDTO<LsThaoTacBO>) lstCenterBO.get(i)).toModel());
			}
		}
		return tblQltsThucXuatTheoKyDAO.saveList1(lstModel);
	}

}
