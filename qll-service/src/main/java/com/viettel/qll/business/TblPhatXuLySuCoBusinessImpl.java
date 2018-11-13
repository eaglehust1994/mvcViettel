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
import com.viettel.qll.bo.TblPhatXuLySuCoBO;
import com.viettel.qll.dao.TblNhanVienDAO;
import com.viettel.qll.dao.TblPhatPakhDAO;
import com.viettel.qll.dao.TblPhatXuLySuCoDAO;
import com.viettel.qll.dto.ErrExcelDTO;
import com.viettel.qll.dto.ImportErrDTO;
import com.viettel.qll.dto.LsThaoTacDTO;
import com.viettel.qll.dto.TblPhatXuLySuCoDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;
import com.viettel.utils.ValidateUtils;

@Service("tblPhatXuLySuCoBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TblPhatXuLySuCoBusinessImpl
		extends BaseFWBusinessImpl<TblPhatXuLySuCoDAO, TblPhatXuLySuCoDTO, TblPhatXuLySuCoBO>
		implements TblPhatXuLySuCoBusiness {

	@Autowired
	private TblPhatPakhDAO tblPhatPakhDAO;

	LsThaoTacDTO lsThaoTacDTO = new LsThaoTacDTO();

	@Autowired
	TblQltsThucXuatTheoKyBusinessImpl businessImpl = new TblQltsThucXuatTheoKyBusinessImpl();

	@Autowired
	private TblNhanVienDAO tblNhanVienDAO;
	List<ErrExcelDTO> lstErrExcelDto;
	@Value("${folder_upload2}")
	private String folder2Upload;
	static Logger LOGGER = LoggerFactory.getLogger(TblPhatXuLySuCoDTO.class);
	@Autowired
	private TblPhatXuLySuCoDAO tblPhatXuLySuCoDAO;

	public TblPhatXuLySuCoBusinessImpl() {
		tModel = new TblPhatXuLySuCoBO();
		tDAO = tblPhatXuLySuCoDAO;
	}

	@Override
	public TblPhatXuLySuCoDAO gettDAO() {
		return tblPhatXuLySuCoDAO;
	}

	@Override
	public DataListDTO doSearchPhatKPIXLSC(TblPhatXuLySuCoDTO obj) {
		List<TblPhatXuLySuCoDTO> ls = tblPhatXuLySuCoDAO.doSearchPhatKPIXLSC(obj);
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		data.setTotal(obj.getTotalRecord());
		data.setSize(obj.getTotalRecord());
		data.setStart(1);
		return data;
	}

	public String importFile(String fileInput, HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		long tStart = System.currentTimeMillis();
		List<TblPhatXuLySuCoDTO> workLst = Lists.newArrayList();
		List<ImportErrDTO> workFault = Lists.newArrayList();
		try {

			File f = new File(fileInput);

			XSSFWorkbook workbook = new XSSFWorkbook(f);
			XSSFSheet sheet = workbook.getSheetAt(0);

			DataFormatter dataFormatter = new DataFormatter();
			int count = 0;
			for (Row row : sheet) {
				count++;
				// Create object OrderGoodsExelDTO
				ErrExcelDTO orderErrorFormat = new ErrExcelDTO();
				if (count >= 8 && !ValidateUtils.isRowEmpty(row)) {
					lstErrExcelDto = new ArrayList<>();
					boolean checkThang = true;
					boolean checkMaNv = true;
					boolean checktinh = true;
					boolean checkhuyen = true;
					boolean checkACCOUNT = true;
					boolean checkTHUONG_TGXL_SL_TSC = true;
					boolean checkPHAT_TGXL_SL_3H = true;
					boolean checkPHAT_TGXL_SL_24H = true;
					boolean checkPHAT_TGXL_TL_3H = true;
					boolean checkPHAT_TGXL_TL_24H = true;
					boolean checkPHAT_TGXL_SLP = true;
					boolean checkTONG = true;
					String Thang = "";
					String MaNv = "";
					String tinh = "";
					String huyen = "";
					String ACCOUNT = "";
					String THUONG_TGXL_SL_TSC = "";
					String PHAT_TGXL_SL_3H = "";
					String PHAT_TGXL_SL_24H = "";
					String PHAT_TGXL_TL_3H = "";
					String PHAT_TGXL_TL_24H = "";
					String PHAT_TGXL_SLP = "";
					String TONG = "";

					for (Cell cell : row) {
						if (cell.getColumnIndex() == 1) {
							Thang = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 2) {
							tinh = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 3) {
							huyen = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 4) {
							ACCOUNT = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 5) {
							MaNv = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 6) {
							THUONG_TGXL_SL_TSC = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 7) {
							PHAT_TGXL_SL_3H = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 8) {
							PHAT_TGXL_SL_24H = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 9) {
							PHAT_TGXL_TL_3H = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 10) {
							PHAT_TGXL_TL_24H = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 11) {
							PHAT_TGXL_SLP = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 12) {
							TONG = dataFormatter.formatCellValue(cell);
						}
					}
					checkThang = checkDataImport(1, Thang, orderErrorFormat);
					checkMaNv = checkDataImport(2, MaNv, orderErrorFormat);
					checktinh = checkDataImport(3, tinh, orderErrorFormat);
					checkhuyen = checkDataImport(4, huyen, orderErrorFormat);
					checkACCOUNT = checkDataImport(5, ACCOUNT, orderErrorFormat);
					checkTHUONG_TGXL_SL_TSC = checkDataImport(6, THUONG_TGXL_SL_TSC, orderErrorFormat);
					checkPHAT_TGXL_SL_3H = checkDataImport(7, PHAT_TGXL_SL_3H, orderErrorFormat);
					checkPHAT_TGXL_SL_24H = checkDataImport(8, PHAT_TGXL_SL_24H, orderErrorFormat);
					checkPHAT_TGXL_TL_3H = checkDataImport(9, PHAT_TGXL_TL_3H, orderErrorFormat);
					checkPHAT_TGXL_TL_24H = checkDataImport(10, PHAT_TGXL_TL_24H, orderErrorFormat);
					checkPHAT_TGXL_SLP = checkDataImport(11, PHAT_TGXL_SLP, orderErrorFormat);
					checkTONG = checkDataImport(12, TONG, orderErrorFormat);
					if (checkThang && checkMaNv && checktinh && checkhuyen && checkACCOUNT && checkTHUONG_TGXL_SL_TSC
							&& checkPHAT_TGXL_SL_3H && checkPHAT_TGXL_SL_24H && checkPHAT_TGXL_TL_3H
							&& checkPHAT_TGXL_TL_24H && checkPHAT_TGXL_SLP && checkTONG) {
						long delele = tblPhatXuLySuCoDAO.deletePhatXLSCByMaNvAndThang(MaNv, Thang);
						TblPhatXuLySuCoDTO lst = new TblPhatXuLySuCoDTO();
						lst.setThang(Thang);
						// lst.setAccountKh(accKh);
						lst.setMaNv(MaNv.toUpperCase());
						lst.setTinh(tinh);
						lst.setHuyen(huyen);
						lst.setAccount(ACCOUNT);
						if (!THUONG_TGXL_SL_TSC.isEmpty()) {
							lst.setPhatTgxlTp(Float.parseFloat(THUONG_TGXL_SL_TSC));
						} else {
							lst.setPhatTgxlTp(0f);
						}
						if (!PHAT_TGXL_SL_3H.isEmpty()) {
							lst.setPhatTgxlSl3h(Float.parseFloat(PHAT_TGXL_SL_3H));
						} else {
							lst.setPhatTgxlSl3h(0f);
						}
						if (!PHAT_TGXL_SL_24H.isEmpty()) {
							lst.setPhatTgxlSl24h(Float.parseFloat(PHAT_TGXL_SL_24H));
						} else {
							lst.setPhatTgxlSl24h(0f);
						}
						if (!PHAT_TGXL_TL_3H.isEmpty()) {
							lst.setPhatTgxlTl3h(Float.parseFloat(PHAT_TGXL_TL_3H));
						} else {
							lst.setPhatTgxlTl3h(0f);
						}
						if (!PHAT_TGXL_TL_24H.isEmpty()) {
							lst.setPhatTgxlTl24h(Float.parseFloat(PHAT_TGXL_TL_24H));
						} else {
							lst.setPhatTgxlTl24h(0f);
						}
						if (!PHAT_TGXL_SLP.isEmpty()) {
							lst.setPhatTgxlSlp(Float.parseFloat(PHAT_TGXL_SLP));
						} else {
							lst.setPhatTgxlSlp(0f);
						}
						if (!TONG.isEmpty()) {
							lst.setTong(Float.parseFloat(TONG));
						} else {
							lst.setTong(0f);
						}
						lst.setXoa(0l);
						lst.setHoatDong(1l);

						workLst.add(lst);
						// save(newObj);
					} else {
						ImportErrDTO errObj = new ImportErrDTO();
						errObj.setColumn1(Thang);
						errObj.setColumn2(MaNv);
						errObj.setColumn3(tinh);
						errObj.setColumn4(huyen);
						errObj.setColumn5(ACCOUNT);
						errObj.setColumn6(THUONG_TGXL_SL_TSC);
						errObj.setColumn7(PHAT_TGXL_SL_3H);
						errObj.setColumn8(PHAT_TGXL_SL_24H);
						errObj.setColumn9(PHAT_TGXL_TL_3H);
						errObj.setColumn10(PHAT_TGXL_TL_24H);
						errObj.setColumn11(PHAT_TGXL_SLP);
						errObj.setColumn12(TONG);
						errObj.setLstErrorOrder(lstErrExcelDto);
						workFault.add(errObj);
					}
				} else if (sheet.getLastRowNum() < 8) {
					orderErrorFormat.setLineError(String.valueOf(count));
					orderErrorFormat.setColumnError(String.valueOf(1));
					orderErrorFormat.setDetailError("File excel không đúng");
					lstErrExcelDto.add(orderErrorFormat);
				}

			}

			workbook.close();

			System.out.println("size done: " + workLst.size());
			System.out.println("size fault: " + workFault.size());
			if (workLst.size() <= 0 && workFault.size() <= 0) {
				throw new IllegalArgumentException("File import không có dữ liệu");
			} else {
				if (workLst.size() > 0) {
					saveList(workLst);
					long tEnd = System.currentTimeMillis();
					lsThaoTacDTO.setChucNang("Import dữ liệu PXK type B");
					lsThaoTacDTO.setMoTa("Import "+workLst.size()+" bản ghi dữ liệu PXK type B ");
					lsThaoTacDTO.setThoiGianThucHien(tEnd-tStart);
					businessImpl.insertLSTT(lsThaoTacDTO,request);
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

	@Override
	public boolean checkDataImport(int index, String value, ErrExcelDTO err) {
		err = new ErrExcelDTO();
		if (index == 1) {
			if (value.isEmpty()) {
				err.setDetailError("Tháng không được để trống");
				lstErrExcelDto.add(err);
				return false;
			} else {
				String checkthang = "";
				checkthang = value.substring(value.lastIndexOf('/') + 1, value.length());
				if (!ValidateUtils.isDate(value, "MM/yyyy")) {
					err.setDetailError("Tháng không đúng định dạng mm/yyyy");
					lstErrExcelDto.add(err);
					return false;
				} else if (checkthang.length() != 4) {
					err.setDetailError("Tháng không đúng định dạng mm/yyyy");
					lstErrExcelDto.add(err);
					return false;
				} else if (value.lastIndexOf('/') != value.indexOf('/')) {
					err.setDetailError("Tháng không đúng định dạng mm/yyyy");
					lstErrExcelDto.add(err);
					return false;
				}
			}

		} else if (index == 2) {
			// ImportKiCaNhanBusiness importKiCaNhanBusiness = new
			// ImportKiCaNhanBusiness();
			if (value.isEmpty()) {
				err.setDetailError("Mã nhân viên không được để trống");
				lstErrExcelDto.add(err);
				return false;
			}
			if (value.length() > 50) {
				err.setDetailError("Mã nhân viên vượt quá độ dài cho phép");
				lstErrExcelDto.add(err);
				return false;
			}
			if (tblNhanVienDAO.checkExistMaNvByMaNv(value) == 0) {
				err.setDetailError("Mã nhân viên không tồn tại");
				lstErrExcelDto.add(err);
				return false;
			}
		} else if (index == 3) {
			// ImportKiCaNhanBusiness importKiCaNhanBusiness = new
			// ImportKiCaNhanBusiness();
			if (value.isEmpty()) {
				err.setDetailError("Tỉnh không được để trống");
				lstErrExcelDto.add(err);
				return false;
			}
			if (value.length() > 50) {
				err.setDetailError("Tỉnh vượt quá độ dài cho phép");
				lstErrExcelDto.add(err);
				return false;
			}

		} else if (index == 4) {
			// ImportKiCaNhanBusiness importKiCaNhanBusiness = new
			// ImportKiCaNhanBusiness();
			if (value.isEmpty()) {
				err.setDetailError("Huyện không được để trống");
				lstErrExcelDto.add(err);
				return false;
			}
			if (value.length() > 50) {
				err.setDetailError("Huyện vượt quá độ dài cho phép");
				lstErrExcelDto.add(err);
				return false;
			}

		} else if (index == 5) {
			// ImportKiCaNhanBusiness importKiCaNhanBusiness = new
			// ImportKiCaNhanBusiness();
			if (value.isEmpty()) {
				err.setDetailError("Account không được để trống");
				lstErrExcelDto.add(err);
				return false;
			}
			if (value.length() > 50) {
				err.setDetailError("Account vượt quá độ dài cho phép");
				lstErrExcelDto.add(err);
				return false;
			}

		} else if (index == 6) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					err.setDetailError("Trường Tổng sự cố không đúng định dạng");
					lstErrExcelDto.add(err);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					err.setDetailError("Trường Tổng sự cố phải là số thực không âm");
					lstErrExcelDto.add(err);
					return false;
				}
			}
		} else if (index == 7) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					err.setDetailError("Trường số lương 3h không đúng định dạng");
					lstErrExcelDto.add(err);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					err.setDetailError("Trường số lương 3h phải là số thực không âm");
					lstErrExcelDto.add(err);
					return false;
				}
			}
		} else if (index == 8) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					err.setDetailError("Trường số lương 24h không đúng định dạng");
					lstErrExcelDto.add(err);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					err.setDetailError("Trường số lương 24h phải là số thực không âm");
					lstErrExcelDto.add(err);
					return false;
				}
			}
		} else if (index == 9) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					err.setDetailError("Trường Tỷ lệ 3h không đúng định dạng");
					lstErrExcelDto.add(err);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					err.setDetailError("Trường Tỷ lệ 3h phải là số thực không âm");
					lstErrExcelDto.add(err);
					return false;
				}
			}
		} else if (index == 10) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					err.setDetailError("Trường Tỷ lệ 24h không đúng định dạng");
					lstErrExcelDto.add(err);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					err.setDetailError("Trường Tỷ lệ 24h phải là số thực không âm");
					lstErrExcelDto.add(err);
					return false;
				}
			}
		} else if (index == 11) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					err.setDetailError("Trường Số lượng phạt không đúng định dạng");
					lstErrExcelDto.add(err);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					err.setDetailError("Trường Số lượng phạt phải là số thực không âm");
					lstErrExcelDto.add(err);
					return false;
				}
			}
		} else if (index == 12) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					err.setDetailError("Trường Tổng tiền phạt không đúng định dạng");
					lstErrExcelDto.add(err);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					err.setDetailError("Trường Tổng tiền phạt phải là số thực không âm");
					lstErrExcelDto.add(err);
					return false;
				}
			}
		}

		return true;
	}

	@Override
	public String exportExcelError(List<ImportErrDTO> obj) throws Exception {
		// TODO Auto-generated method stub
		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream file = new BufferedInputStream(new FileInputStream(filePath + "NVDayMay-PhatXLSC-err.xlsx"));

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
			int rownum = 8;
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
			}
			file.close();

			File out = new File(
					folder2Upload + File.separatorChar + "NVDayMay-PhatXLSC-err" + startExportTime + ".xlsx");

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
		return UEncrypt.encryptFileUploadPath("NVDayMay-PhatXLSC-err" + startExportTime + ".xlsx");
	}

	@Override
	public String exportExcelGrid(TblPhatXuLySuCoDTO lst) throws Exception {
		// TODO Auto-generated method stub
		List<TblPhatXuLySuCoDTO> obj = tblPhatXuLySuCoDAO.doSearchPhatKPIXLSC(lst);
		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream file = new BufferedInputStream(new FileInputStream(filePath + "11-NVDayMay-phatXLSC.xlsx"));
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
			int rownum = 8;
			for (int i = 0; i < obj.size(); i++) {
				StringBuilder err = new StringBuilder();
				XSSFRow row = sheet.createRow(rownum);
				XSSFCell cell0 = row.createCell(0);
				cell0.setCellValue(i + 1);
				cell0.setCellStyle(style);
				rownum++;

				XSSFCell cell1 = row.createCell(1);
				cell1.setCellValue(obj.get(i).getThang());

				XSSFCell cell2 = row.createCell(2);
				cell2.setCellValue(obj.get(i).getTinh());

				XSSFCell cell3 = row.createCell(3);
				cell3.setCellValue(obj.get(i).getHuyen());

				XSSFCell cell4 = row.createCell(4);
				cell4.setCellValue(obj.get(i).getAccount());

				XSSFCell cell5 = row.createCell(5);
				cell5.setCellValue(obj.get(i).getMaNv());

				XSSFCell cell6 = row.createCell(6);
				cell6.setCellValue(obj.get(i).getPhatTgxlTp());

				XSSFCell cell7 = row.createCell(7);
				cell7.setCellValue(obj.get(i).getPhatTgxlSl3h());

				XSSFCell cell8 = row.createCell(8);
				cell8.setCellValue(obj.get(i).getPhatTgxlSl24h());

				XSSFCell cell9 = row.createCell(9);
				cell9.setCellValue(obj.get(i).getPhatTgxlTl3h());

				XSSFCell cell10 = row.createCell(10);
				cell10.setCellValue(obj.get(i).getPhatTgxlTl24h());

				XSSFCell cell11 = row.createCell(11);
				cell11.setCellValue(obj.get(i).getPhatTgxlSlp());

				XSSFCell cell12 = row.createCell(12);
				cell12.setCellValue(obj.get(i).getTong());
				cell0.setCellStyle(style);
				cell1.setCellStyle(style);
				cell2.setCellStyle(style);
				cell3.setCellStyle(style);
				cell0.setCellStyle(style);
				cell1.setCellStyle(style);
				cell2.setCellStyle(style);
				cell3.setCellStyle(style);
				cell0.setCellStyle(style);
				cell1.setCellStyle(style);
				cell2.setCellStyle(style);
				cell3.setCellStyle(style);
				cell0.setCellStyle(style);
				cell1.setCellStyle(style);
				cell2.setCellStyle(style);

			}
			file.close();

			File out = new File(
					folder2Upload + File.separatorChar + "11-NVDayMay-phatXLSC" + startExportTime + ".xlsx");

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
		return UEncrypt.encryptFileUploadPath("11-NVDayMay-phatXLSC" + startExportTime + ".xlsx");
	}

	public String downloadImportTemplate() throws Exception {
		// TODO Auto-generated method stub

		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		String filePath = classloader.getResource("../" + "doc-template").getPath();
		InputStream file = new BufferedInputStream(new FileInputStream(filePath + "11-NVDayMay-phatXLSC.xlsx"));
		XSSFWorkbook workbook = new XSSFWorkbook(file);

		file.close();
		File out = new File(folder2Upload + File.separatorChar + "11-NVDayMay-phatXLSC.xlsx");

		FileOutputStream outFile = new FileOutputStream(out);
		workbook.write(outFile);
		workbook.close();
		outFile.close();

		String path = UEncrypt.encryptFileUploadPath("11-NVDayMay-phatXLSC.xlsx");
		return path;
	}

}
