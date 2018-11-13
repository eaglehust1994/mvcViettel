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
import com.viettel.qll.bo.TblLoiLapLaiBO;
import com.viettel.qll.dao.TblDanhMucDAO;
import com.viettel.qll.dao.TblLoiLapLaiDAO;
import com.viettel.qll.dao.TblNhanVienDAO;
import com.viettel.qll.dto.ErrExcelDTO;
import com.viettel.qll.dto.ImportErrDTO;
import com.viettel.qll.dto.LsThaoTacDTO;
import com.viettel.qll.dto.TblLoiLapLaiDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;
import com.viettel.utils.ValidateUtils;

@Service("tblLoiLapLaiBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TblLoiLapLaiBusinessImpl extends BaseFWBusinessImpl<TblLoiLapLaiDAO, TblLoiLapLaiDTO, TblLoiLapLaiBO>
		implements TblLoiLapLaiBusiness {

	@Autowired
	private TblLoiLapLaiDAO tblLoiLapLaiDAO;
	@Autowired
	private TblNhanVienDAO tblNhanVienDAO;
	@Autowired
	private TblDanhMucDAO tblDanhmucDAO;
	private String fomat = "MM/yyyy";
	List<ErrExcelDTO> lstErrExcelDto;
	@Value("${folder_upload2}")
	private String folder2Upload;

	LsThaoTacDTO lsThaoTacDTO = new LsThaoTacDTO();

	@Autowired
	TblQltsThucXuatTheoKyBusinessImpl businessImpl = new TblQltsThucXuatTheoKyBusinessImpl();

	static Logger LOGGER = LoggerFactory.getLogger(TblLoiLapLaiBusinessImpl.class);

	public TblLoiLapLaiBusinessImpl() {
		tModel = new TblLoiLapLaiBO();
		tDAO = tblLoiLapLaiDAO;
	}

	@Override
	public TblLoiLapLaiDAO gettDAO() {
		return tblLoiLapLaiDAO;
	}

	@Override
	public DataListDTO doSearch(TblLoiLapLaiDTO obj) {
		// TODO Auto-generated method stub
		List<TblLoiLapLaiDTO> ls = tblLoiLapLaiDAO.doSearch(obj);
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		data.setTotal(obj.getTotalRecord());
		data.setSize(obj.getTotalRecord());
		data.setStart(1);
		return data;
	}

	@Override
	public String importFile(String fileInput, HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		long tStart = System.currentTimeMillis();
		List<TblLoiLapLaiDTO> workLst = Lists.newArrayList();
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
				if (count >= 6 && !ValidateUtils.isRowEmpty(row)) {
					lstErrExcelDto = new ArrayList<>();
					boolean checkThang = true;
					boolean checkTinhTp = true;
					boolean checkQuanHuyen = true;
					boolean checkLoaiDichVu = true;
					boolean checkAccKh = true;
					boolean checkMaNv = true;
					boolean checkSoLanLap = true;
					boolean checkPhat = true;

					String thang = "";
					String tinhTp = "";
					String quanHuyen = "";
					String loaiDichVu = "";
					String accKh = "";
					String maNv = "";
					String soLanLap = "";
					String phat = "";

					for (Cell cell : row) {
						if (cell.getColumnIndex() == 1) {
							thang = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 2) {
							tinhTp = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 3) {
							quanHuyen = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 4) {
							loaiDichVu = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 5) {
							accKh = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 6) {
							maNv = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 7) {
							soLanLap = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 8) {
							phat = dataFormatter.formatCellValue(cell);
						}
					}

					checkThang = checkDataImport(1, thang, "", orderErrorFormat);
					checkTinhTp = checkDataImport(2, tinhTp, "", orderErrorFormat);
					checkQuanHuyen = checkDataImport(3, quanHuyen, tinhTp, orderErrorFormat);
					checkLoaiDichVu = checkDataImport(4, loaiDichVu, "", orderErrorFormat);
					checkAccKh = checkDataImport(5, accKh, "", orderErrorFormat);
					checkMaNv = checkDataImport(6, maNv, "", orderErrorFormat);
					checkSoLanLap = checkDataImport(7, soLanLap, "", orderErrorFormat);
					checkPhat = checkDataImport(8, phat, "", orderErrorFormat);

					if (checkThang && checkLoaiDichVu && checkTinhTp && checkQuanHuyen && checkMaNv && checkSoLanLap
							&& checkPhat && checkAccKh) {
						long delele = tblLoiLapLaiDAO.deletePhatLoiLapLaiByMaNvAndThangAndAccKh(maNv, thang, accKh);
						TblLoiLapLaiDTO lst = new TblLoiLapLaiDTO();
						// lst = new TblPhatCdt();
						lst.setThang(thang);
						lst.setTinh(tinhTp);
						lst.setQuanHuyen(quanHuyen);
						lst.setLoaiDv(loaiDichVu);
						lst.setMaNv(maNv.toUpperCase());
						lst.setAccountKh(accKh);
						if (!soLanLap.isEmpty()) {
							lst.setSoLanLap(Float.parseFloat(soLanLap));
						} else {
							lst.setSoLanLap(0f);
						}
						if (!phat.isEmpty()) {
							lst.setPhat(Float.parseFloat(phat));
						} else {
							lst.setPhat(0f);
						}

						lst.setXoa(0l);
						lst.setHoatDong(1l);

						workLst.add(lst);
						// save(newObj);
					} else {
						ImportErrDTO lstFault = new ImportErrDTO();
						lstFault.setColumn1(thang);
						lstFault.setColumn2(tinhTp);
						lstFault.setColumn3(quanHuyen);
						lstFault.setColumn4(loaiDichVu);
						lstFault.setColumn5(accKh);
						lstFault.setColumn6(maNv);
						lstFault.setColumn7(soLanLap);
						lstFault.setColumn8(phat);

						lstFault.setLstErrorOrder(lstErrExcelDto);
						workFault.add(lstFault);
					}
				} else if (sheet.getLastRowNum() < 6) {
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
					lsThaoTacDTO.setChucNang("Import dữ liệu phạt lỗi lặp lại");
					lsThaoTacDTO.setMoTa("Import "+workLst.size() +" bản ghi dữ liệu phạt lỗi lặp lại ");
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
	public boolean checkDataImport(int index, String value, String value2, ErrExcelDTO err) {
		// TODO Auto-generated method stub
		err = new ErrExcelDTO();
		if (index == 1) {
			if (value.isEmpty()) {
				err.setDetailError("Tháng không được để trống");
				lstErrExcelDto.add(err);
				return false;
			} else {
				String checkthang = "";
				checkthang = value.substring(value.lastIndexOf('/') + 1, value.length());
				if (!ValidateUtils.isDate(value, fomat)) {
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
			if (!value.isEmpty() && value.length() > 50) {
				err.setDetailError("Tỉnh/Tp vượt quá độ dài cho phép");
				lstErrExcelDto.add(err);
				return false;
			} else if (tblDanhmucDAO.checkExistTinhBytenTinh(value, "") != 1l) {
				err.setDetailError("Tỉnh/Tp không chính xác");
				lstErrExcelDto.add(err);
				return false;
			}
		} else if (index == 3) {
			if (!value.isEmpty() && value.length() > 50) {
				err.setDetailError("Quận huyện vượt quá độ dài cho phép");
				lstErrExcelDto.add(err);
				return false;
			} else if (tblDanhmucDAO.checkhuyenbytentinh(value, value2) != 1l) {
				err.setDetailError("Quận huyện không trùng với tỉnh");
				lstErrExcelDto.add(err);
				return false;
			}
		} else if (index == 4) {
			if (value.isEmpty()) {
				err.setDetailError("Loại dịch vụ không được để trống");
				lstErrExcelDto.add(err);
				return false;
			}
			if (!"Kênh truyền".equals(value) && !"CĐBR".equals(value)) {
				err.setDetailError("Loại dịch vụ phải là \"Kênh truyền\" hoặc \"CĐBR\"");
				lstErrExcelDto.add(err);
				return false;
			}
		} else if (index == 5) {
			if (value.isEmpty()) {
				err.setDetailError("Trường acc khách hàng không được để trống");
				lstErrExcelDto.add(err);
				return false;
			}
			if (value.length() > 50) {
				err.setDetailError("Trường acc khách hàng vượt quá độ dài cho phép");
				lstErrExcelDto.add(err);
				return false;
			}
		} else if (index == 6) {
			if (value.isEmpty()) {
				err.setDetailError("Trường mã nhân viên không được để trống");
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
		} else if (index == 7) {
			// if (value.isEmpty()) {
			// err.setDetailError("Trường phạt không được để trống");
			// lstErrExcelDto.add(err);
			// return false;
			// }
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					err.setDetailError("Số lần lặp không đúng định dạng");
					lstErrExcelDto.add(err);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					err.setDetailError("Số lần lặp phải là số thực không âm");
					lstErrExcelDto.add(err);
					return false;
				}
			}
		} else if (index == 8) {
			// if (value.isEmpty()) {
			// err.setDetailError("Trường phạt không được để trống");
			// lstErrExcelDto.add(err);
			// return false;
			// }
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					err.setDetailError("Phạt không đúng định dạng");
					lstErrExcelDto.add(err);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					err.setDetailError("Phạt phải là số thực không âm");
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

		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream file = new BufferedInputStream(
					new FileInputStream(filePath + "NVDayMay-PhatLoiLapLai-err.xlsx"));

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
				for (int j = 0; j < obj.get(i).getLstErrorOrder().size(); j++) {
					err.append(obj.get(i).getLstErrorOrder().get(j).getDetailError() + "-");
					// System.out.println("err" + err);
				}
				System.out.println("err " + i + ":" + err);

				XSSFCell cell9 = row.createCell(9);
				cell9.setCellValue(err.toString());

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

			}
			file.close();

			File out = new File(
					folder2Upload + File.separatorChar + "NVDayMay-PhatLoiLapLai-err" + startExportTime + ".xlsx");

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

		return UEncrypt.encryptFileUploadPath("NVDayMay-PhatLoiLapLai-err" + startExportTime + ".xlsx");
	}

	@Override
	public String exportExcelGrid(TblLoiLapLaiDTO lst) throws Exception {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		List<TblLoiLapLaiDTO> obj = tblLoiLapLaiDAO.doSearch(lst);
		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream file = new BufferedInputStream(new FileInputStream(filePath + "ExportPhatLoiLapLai.xlsx"));
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

				XSSFCell cell2 = row.createCell(2);
				cell2.setCellValue(obj.get(i).getTinh());

				XSSFCell cell3 = row.createCell(3);
				cell3.setCellValue(obj.get(i).getQuanHuyen());

				XSSFCell cell4 = row.createCell(4);
				cell4.setCellValue(obj.get(i).getLoaiDv());

				XSSFCell cell5 = row.createCell(5);
				cell5.setCellValue(obj.get(i).getAccountKh());

				XSSFCell cell6 = row.createCell(6);
				cell6.setCellValue(obj.get(i).getMaNv());

				XSSFCell cell7 = row.createCell(7);
				cell7.setCellValue(obj.get(i).getSoLanLap());

				XSSFCell cell8 = row.createCell(8);
				cell8.setCellValue(obj.get(i).getPhat());

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
			file.close();

			File out = new File(folder2Upload + File.separatorChar + "ExportPhatLoiLapLai" + startExportTime + ".xlsx");

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
		return UEncrypt.encryptFileUploadPath("ExportPhatLoiLapLai" + startExportTime + ".xlsx");
	}

	public String downloadImportTemplate() throws Exception {
		// TODO Auto-generated method stub

		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		String filePath = classloader.getResource("../" + "doc-template").getPath();
		InputStream file = new BufferedInputStream(new FileInputStream(filePath + "ExportPhatLoiLapLai.xlsx"));
		XSSFWorkbook workbook = new XSSFWorkbook(file);

		file.close();
		File out = new File(folder2Upload + File.separatorChar + "ExportPhatLoiLapLai.xlsx");

		FileOutputStream outFile = new FileOutputStream(out);
		workbook.write(outFile);
		workbook.close();
		outFile.close();

		String path = UEncrypt.encryptFileUploadPath("ExportPhatLoiLapLai.xlsx");
		return path;
	}

}
