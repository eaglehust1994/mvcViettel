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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import com.viettel.qll.bo.TblDonGiaThueBaoBO;
import com.viettel.qll.dao.TblDanhMucDAO;
import com.viettel.qll.dao.TblDonGiaThueBaoDAO;
import com.viettel.qll.dto.ErrExcelDTO;
import com.viettel.qll.dto.ImportErrDTO;
import com.viettel.qll.dto.LsThaoTacDTO;
import com.viettel.qll.dto.TblDonGiaThueBaoDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;
import com.viettel.utils.ValidateUtils;

@Service("tblDonGiaThueBaoBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TblDonGiaThueBaoBusinessImpl
		extends BaseFWBusinessImpl<TblDonGiaThueBaoDAO, TblDonGiaThueBaoDTO, TblDonGiaThueBaoBO>
		implements TblDonGiaThueBaoBusiness {
	List<ErrExcelDTO> lstErrExcelDto;
	static Logger LOGGER = LoggerFactory.getLogger(TblPhatFcBusinessImpl.class);
	@Autowired
	private TblDonGiaThueBaoDAO tblDonGiaThueBaoDAO;
	@Autowired
	private TblDanhMucDAO tblDanhmucDao;

	LsThaoTacDTO lsThaoTacDTO = new LsThaoTacDTO();

	@Autowired
	TblQltsThucXuatTheoKyBusinessImpl businessImpl = new TblQltsThucXuatTheoKyBusinessImpl();

	public TblDonGiaThueBaoBusinessImpl() {
		tModel = new TblDonGiaThueBaoBO();
		tDAO = tblDonGiaThueBaoDAO;
	}

	@Override
	public TblDonGiaThueBaoDAO gettDAO() {
		return tblDonGiaThueBaoDAO;
	}

	@Override
	public DataListDTO doSearch(TblDonGiaThueBaoDTO obj, HttpServletRequest request) throws Exception {
		List<TblDonGiaThueBaoDTO> ls = tblDonGiaThueBaoDAO.doSearch(obj);
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		data.setTotal(obj.getTotalRecord());
		data.setSize(obj.getTotalRecord());
		data.setStart(1);
		return data;
	}

	@Value("${folder_upload2}")
	private String folder2Upload;

	public String exportExcelError(List<ImportErrDTO> obj) throws Exception {

		// TODO Auto-generated method stub
		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream file = new BufferedInputStream(
					new FileInputStream(filePath + "DayMay-donGiaDayThueBao-error.xlsx"));
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
			int rownum = 7;
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

				for (int j = 0; j < obj.get(i).getLstErrorOrder().size(); j++) {
					err.append(obj.get(i).getLstErrorOrder().get(j).getDetailError() + "-");
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
			file.close();

			File out = new File(
					folder2Upload + File.separatorChar + "DayMay-donGiaDayThueBao-error" + startExportTime + ".xlsx");

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
		return UEncrypt.encryptFileUploadPath("DayMay-donGiaDayThueBao-error" + startExportTime + ".xlsx");
	}

	public boolean checkPhatFC(int index, String value, ErrExcelDTO orderErrorFormat) {
		orderErrorFormat = new ErrExcelDTO();
		if (index == 1) {
			if (!value.isEmpty()) {
				if (value.length() > 100) {
					orderErrorFormat.setDetailError("Tỉnh vượt quá độ dài cho phép");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				} else if (tblDanhmucDao.checkExistTinhBytenTinh(value, "") != 1l) {
					orderErrorFormat.setDetailError("Sai tên tỉnh");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}

			} else {
				orderErrorFormat.setDetailError("Tỉnh không được trống");
				lstErrExcelDto.add(orderErrorFormat);
				return false;
			}
		} else if (index == 2) {
			if (!value.isEmpty()) {
				Pattern pattern = Pattern.compile("\\d*");
				Matcher matcher = pattern.matcher(value);
				if (matcher.matches()) {
					if (Integer.parseInt(value) < 1 || Integer.parseInt(value) > 4) {
						orderErrorFormat.setDetailError("Vùng lương sai giá trị");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				} else {
					orderErrorFormat.setDetailError("Vùng lương sai giá trị");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}

			} else {
				orderErrorFormat.setDetailError("Vùng lương không được trống");
				lstErrExcelDto.add(orderErrorFormat);
				return false;
			}
		} else if (index == 3) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					orderErrorFormat.setDetailError("Đơn giá đầu tư không đúng định dạng");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					orderErrorFormat.setDetailError("Đơn giá đầu tư  phải là số thực không âm");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}
		} else if (index == 4) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					orderErrorFormat.setDetailError("Đơn giá mới không đúng định dạng");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					orderErrorFormat.setDetailError("Đơn giá mới phải là số thực không âm");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}

		} else if (index == 5) {
			if (!value.isEmpty()) {
				if (value.length() > 2000) {
					orderErrorFormat.setDetailError("Ghi chú vượt quá độ dài cho phép");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}
		}
		return true;
	}

	public String exportExcelGrid(TblDonGiaThueBaoDTO lst) throws Exception {
		// TODO Auto-generated method stub
		List<TblDonGiaThueBaoDTO> obj = tblDonGiaThueBaoDAO.doSearch(lst);
		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream file = new BufferedInputStream(new FileInputStream(filePath + "ExportDongiathuebao.xlsx"));
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
			int rownum = 7;
			for (int i = 0; i < obj.size(); i++) {
				StringBuilder err = new StringBuilder();
				XSSFRow row = sheet.createRow(rownum);
				XSSFCell cell0 = row.createCell(0);
				cell0.setCellValue(i + 1);
				cell0.setCellStyle(style);
				rownum++;

				XSSFCell cell1 = row.createCell(1);
				cell1.setCellValue(obj.get(i).getTinh());

				XSSFCell cell2 = row.createCell(2);
				cell2.setCellValue(obj.get(i).getVungLuong());

				XSSFCell cell3 = row.createCell(3);
				cell3.setCellValue(obj.get(i).getDonGiaChuDauTu());

				XSSFCell cell4 = row.createCell(4);
				cell4.setCellValue(obj.get(i).getDonGiaMoi());

				XSSFCell cell5 = row.createCell(5);
				cell5.setCellValue(obj.get(i).getGhiChu());

				cell0.setCellStyle(style);
				cell1.setCellStyle(style);
				cell2.setCellStyle(style);
				cell3.setCellStyle(style);
				cell4.setCellStyle(style);
				cell5.setCellStyle(style);

			}
			file.close();

			File out = new File(folder2Upload + File.separatorChar + "ExportDongiathuebao" + startExportTime + ".xlsx");

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
		return UEncrypt.encryptFileUploadPath("ExportDongiathuebao" + startExportTime + ".xlsx");
	}

	public String importFile(String fileInput, HttpServletRequest request) throws Exception {
		long tStart = System.currentTimeMillis();
		List<TblDonGiaThueBaoDTO> workLst = Lists.newArrayList();
		List<ImportErrDTO> workFault = Lists.newArrayList();
		try {

			File f = new File(fileInput);

			XSSFWorkbook workbook = new XSSFWorkbook(f);
			XSSFSheet sheet = workbook.getSheetAt(0);

			DataFormatter dataFormatter = new DataFormatter();
			int count = 0;
			int a = 1;
			for (Row row : sheet) {
				// System.out.println("last row: "+ sheet.getLastRowNum());
				count++;
				// Create object OrderGoodsExelDTO
				ErrExcelDTO orderErrorFormat = new ErrExcelDTO();
				if (count >= 6 && !ValidateUtils.isRowEmpty(row)) {
					lstErrExcelDto = new ArrayList<>();
					boolean checkTinh = true;
					boolean checkvungluong = true;
					boolean checkDongiaDT = true;
					boolean checkDongiamoi = true;
					boolean checkGhichu = true;

					String tinh = "";
					String vungluong = "";
					String DongiaDT = "";
					String Dongiamoi = "";
					String Ghichu = "";

					for (Cell cell : row) {
						if (cell.getColumnIndex() == 1) {
							tinh = dataFormatter.formatCellValue(cell).trim();
							checkTinh = checkPhatFC(1, tinh, orderErrorFormat);
						} else if (cell.getColumnIndex() == 2) {
							vungluong = dataFormatter.formatCellValue(cell).trim();
							checkvungluong = checkPhatFC(2, vungluong, orderErrorFormat);
						} else if (cell.getColumnIndex() == 3) {
							DongiaDT = dataFormatter.formatCellValue(cell).trim();
							checkDongiaDT = checkPhatFC(3, DongiaDT, orderErrorFormat);
						} else if (cell.getColumnIndex() == 4) {
							Dongiamoi = dataFormatter.formatCellValue(cell).trim();
							checkDongiamoi = checkPhatFC(4, Dongiamoi, orderErrorFormat);
						} else if (cell.getColumnIndex() == 5) {
							Ghichu = dataFormatter.formatCellValue(cell).trim();
							checkGhichu = checkPhatFC(5, Ghichu, orderErrorFormat);
						}
					}
					if (checkDongiaDT && checkDongiamoi && checkGhichu && checkTinh && checkvungluong) {
						TblDonGiaThueBaoDTO lst = new TblDonGiaThueBaoDTO();
						lst.setTinh(tinh);
						lst.setVungLuong(vungluong);
						if (!DongiaDT.isEmpty()) {
							lst.setDonGiaChuDauTu(Float.parseFloat(DongiaDT));
						} else {
							lst.setDonGiaChuDauTu(0f);
						}

						if (!Dongiamoi.isEmpty()) {
							lst.setDonGiaMoi(Float.parseFloat(Dongiamoi));
						} else {
							lst.setDonGiaMoi(0f);
						}

						lst.setGhiChu(Ghichu);

						lst.setXoa(0l);
						lst.setHoatDong(1l);

						workLst.add(lst);
						// save(newObj);
					} else {
						ImportErrDTO errObj = new ImportErrDTO();
						errObj.setColumn1(tinh);
						errObj.setColumn2(vungluong);
						errObj.setColumn3(DongiaDT);
						errObj.setColumn4(Dongiamoi);
						errObj.setColumn5(Ghichu);
						errObj.setLstErrorOrder(lstErrExcelDto);
						workFault.add(errObj);
					}
				}

			}
			// else if (sheet.getLastRowNum() < 6) {
			// lstErrExcelDto = new ArrayList<>();
			// orderErrorFormat.setLineError(String.valueOf(count));
			// orderErrorFormat.setColumnError(String.valueOf(1));
			// orderErrorFormat.setDetailError("File excel không đúng");
			// lstErrExcelDto.add(orderErrorFormat);
			// }

			workbook.close();

			System.out.println("size done: " + workLst.size());
			System.out.println("size fault: " + workFault.size());
			if (sheet.getLastRowNum() < 6) {
				throw new IllegalArgumentException("File import không có dữ liệu");
			} else {
				if (workLst.size() > 0) {
					saveList(workLst);
					long tEnd = System.currentTimeMillis();
					lsThaoTacDTO.setChucNang("Import dữ liệu đơn giá thuê bao");
					lsThaoTacDTO.setMoTa("Import "+workLst.size() +" bản ghi dữ liệu đơn giá thuê bao ");
					lsThaoTacDTO.setThoiGianThucHien(tEnd - tStart);
					businessImpl.insertLSTT(lsThaoTacDTO, request);
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
	public String downloadImportTemplate() throws Exception {
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		String filePath = classloader.getResource("../" + "doc-template").getPath();
		InputStream file = new BufferedInputStream(new FileInputStream(filePath + "ExportDongiathuebao.xlsx"));
		XSSFWorkbook workbook = new XSSFWorkbook(file);

		file.close();
		File out = new File(folder2Upload + File.separatorChar + "ExportDongiathuebao.xlsx");

		FileOutputStream outFile = new FileOutputStream(out);
		workbook.write(outFile);
		workbook.close();
		outFile.close();

		String path = UEncrypt.encryptFileUploadPath("ExportDongiathuebao.xlsx");
		return path;
	}

}
