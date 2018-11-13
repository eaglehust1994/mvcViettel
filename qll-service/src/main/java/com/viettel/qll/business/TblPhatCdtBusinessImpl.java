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
import com.viettel.qll.bo.TblPhatCdtBO;
import com.viettel.qll.dao.TblNhanVienDAO;
import com.viettel.qll.dao.TblPhatCdtDAO;
import com.viettel.qll.dao.TblPhatFcDAO;
import com.viettel.qll.dto.ErrExcelDTO;
import com.viettel.qll.dto.ImportErrDTO;
import com.viettel.qll.dto.LsThaoTacDTO;
import com.viettel.qll.dto.TblPhatCdtDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;
import com.viettel.utils.ValidateUtils;

@Service("tblPhatCdtBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TblPhatCdtBusinessImpl extends BaseFWBusinessImpl<TblPhatCdtDAO, TblPhatCdtDTO, TblPhatCdtBO>
		implements TblPhatCdtBusiness {

	@Autowired
	private TblPhatCdtDAO tblPhatCdtDAO;
	@Autowired
	private TblPhatFcDAO tblPhatFcDAO;
	private String fomat="MM/yyyy";
	@Autowired
	private TblNhanVienDAO tblNhanVienDAO;
	List<ErrExcelDTO> lstErrExcelDto;
	@Value("${folder_upload2}")
	private String folder2Upload;
	static Logger LOGGER = LoggerFactory.getLogger(TblPhatCdtBusinessImpl.class);

	LsThaoTacDTO lsThaoTacDTO = new LsThaoTacDTO();

	@Autowired
	TblQltsThucXuatTheoKyBusinessImpl businessImpl=new TblQltsThucXuatTheoKyBusinessImpl();
	
	
	public TblPhatCdtBusinessImpl() {
		tModel = new TblPhatCdtBO();
		tDAO = tblPhatCdtDAO;
	}

	@Override
	public TblPhatCdtDAO gettDAO() {
		return tblPhatCdtDAO;
	}

	@Override
	public DataListDTO doSearchPhatCDT(TblPhatCdtDTO obj) {
		List<TblPhatCdtDTO> ls = tblPhatCdtDAO.doSearchPhatCDT(obj);
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		data.setTotal(obj.getTotalRecord());
		data.setSize(obj.getTotalRecord());
		data.setStart(1);
		return data;
	}

	@Override
	public String importFile(String fileInput,HttpServletRequest request) throws Exception {
		long tStart = System.currentTimeMillis();
		List<TblPhatCdtDTO> workLst = Lists.newArrayList();
		List<ImportErrDTO> workFault = Lists.newArrayList();
		try {

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
				if (count >= 6 && !ValidateUtils.isRowEmpty(row)) {
					lstErrExcelDto = new ArrayList<>();
					boolean checkThang = true;
					boolean checkMaNv = true;
					boolean checkHoVaTen = true;
					boolean checkPhatQcxlsc = true;
					boolean checkPhatLdsvtXlsct = true;
					boolean checkPhatGttbrm = true;
					boolean checkPhatTb = true;
					boolean checkPhatTbrmktd = true;
					boolean checkPhatLyttd = true;

					String thang = "";
					String maNv = "";
					String hoVaTen = "";
					String phatQcxlsc = "";
					String phatLdsvtXlsct = "";
					String phatGttbrm = "";
					String phatTb = "";
					String phatTbrmktd = "";
					String phatLyttd = "";

					for (Cell cell : row) {
						if (cell.getColumnIndex() == 1) {
							thang = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 2) {
							maNv = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 3) {
							hoVaTen = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 4) {
							phatQcxlsc = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 5) {
							phatLdsvtXlsct = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 6) {
							phatGttbrm = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 7) {
							phatTb = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 8) {
							phatTbrmktd = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 9) {
							phatLyttd = dataFormatter.formatCellValue(cell);
						}
					}

					checkThang = checkPhatCdt(1, thang, orderErrorFormat);
					checkMaNv = checkPhatCdt(2, maNv, orderErrorFormat);
					checkHoVaTen = checkPhatCdt(3, hoVaTen, orderErrorFormat);
					checkPhatQcxlsc = checkPhatCdt(4, phatQcxlsc, orderErrorFormat);
					checkPhatLdsvtXlsct = checkPhatCdt(5, phatLdsvtXlsct, orderErrorFormat);
					checkPhatGttbrm = checkPhatCdt(6, phatGttbrm, orderErrorFormat);
					checkPhatTb = checkPhatCdt(7, phatTb, orderErrorFormat);
					checkPhatTbrmktd = checkPhatCdt(8, phatTbrmktd, orderErrorFormat);
					checkPhatLyttd = checkPhatCdt(9, phatLyttd, orderErrorFormat);

					if (checkThang && checkMaNv && checkHoVaTen && checkPhatQcxlsc && checkPhatLdsvtXlsct
							&& checkPhatGttbrm && checkPhatTb && checkPhatTbrmktd && checkPhatLyttd) {
						long delele = tblPhatCdtDAO.deletePhatCdtByMaNvAndThang(maNv, thang);
						TblPhatCdtDTO lst = new TblPhatCdtDTO();
						// lst = new TblPhatCdt();
						lst.setThang(thang);
						lst.setMaNv(maNv.toUpperCase());
						lst.setHoVaTen(hoVaTen);
						if (!phatQcxlsc.isEmpty()) {
							lst.setPhatQcxlsc(Float.parseFloat(phatQcxlsc));
						} else {
							lst.setPhatQcxlsc(0f);
						}
						if (!phatLdsvtXlsct.isEmpty()) {
							lst.setPhatLdsvtXlsct(Float.parseFloat(phatLdsvtXlsct));
						} else {
							lst.setPhatLdsvtXlsct(0f);
						}
						if (!phatGttbrm.isEmpty()) {
							lst.setPhatGttbrm(Float.parseFloat(phatGttbrm));
						} else {
							lst.setPhatGttbrm(0f);
						}
						if (!phatTb.isEmpty()) {
							lst.setPhatTbcdcnktd(Float.parseFloat(phatTb));
						} else {
							lst.setPhatTbcdcnktd(0f);
						}
						if (!phatTbrmktd.isEmpty()) {
							lst.setPhatTbrmktd(Float.parseFloat(phatTbrmktd));
						} else {
							lst.setPhatTbrmktd(0f);
						}
						if (!phatLyttd.isEmpty()) {
							lst.setPhatLyttd(Float.parseFloat(phatLyttd));
						} else {
							lst.setPhatLyttd(0f);
						}
						lst.setXoa(0l);
						lst.setHoatDong(1l);

						workLst.add(lst);
						// save(newObj);
					} else {
						ImportErrDTO errObj = new ImportErrDTO();
						errObj.setColumn1(thang);
						errObj.setColumn2(maNv);
						errObj.setColumn3(hoVaTen);
						errObj.setColumn4(phatQcxlsc);
						errObj.setColumn5(phatLdsvtXlsct);
						errObj.setColumn6(phatGttbrm);
						errObj.setColumn7(phatTb);
						errObj.setColumn8(phatTbrmktd);
						errObj.setColumn9(phatLyttd);
						errObj.setLstErrorOrder(lstErrExcelDto);
						workFault.add(errObj);
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
					lsThaoTacDTO.setChucNang("Import dữ liệu phạt cdt");
					lsThaoTacDTO.setMoTa("Import "+workLst.size()+"bản ghi dữ liệu phạt cdt "+(new Date()));
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
	public boolean checkPhatCdt(int index, String value, ErrExcelDTO err) {
		err = new ErrExcelDTO();
		// String errDetail = "";
		// ErrorImportDTO err = new ErrorImportDTO();
		if (index == 1) {
			if (value.isEmpty()) {
				err.setDetailError("Tháng không được để trống");
				lstErrExcelDto.add(err);
				return false;
			} else {
				if (!ValidateUtils.isDate(value,fomat)) {
					err.setDetailError("Tháng không đúng định dạng mm/yyyy");
					lstErrExcelDto.add(err);
					return false;
				}
				else
				{
					int batdau,ketthuc;
					batdau=value.indexOf('/')+1;
					ketthuc=batdau+value.substring(value.indexOf('/')+1).indexOf('/');
					if(batdau<ketthuc)
					{
						String thang = value.substring(batdau,ketthuc);
						int chuyendoi=Integer.parseInt(thang);
						if(chuyendoi>12||chuyendoi<1)
						{
							err.setDetailError("Tháng không đúng định dạng");
							lstErrExcelDto.add(err);
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
							lstErrExcelDto.add(err);
							return false;
						}
						int chuyendoi = Integer.parseInt(thangcv);
						if (chuyendoi > 12 || chuyendoi < 1) {
							err.setDetailError("Tháng không đúng định dạng");
							lstErrExcelDto.add(err);
							return false;
						}
						
					}
					
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
			if (value.isEmpty()) {
				err.setDetailError("Trường họ và tên không được để trống");
				lstErrExcelDto.add(err);
				return false;
			}
			if (value.length() > 100) {
				err.setDetailError("Họ và tên vượt quá độ dài cho phép");
				lstErrExcelDto.add(err);
				return false;
			}
		} else if (index == 4) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					err.setDetailError("Phạt Quy cách xử lý sự cố không đúng định dạng");
					lstErrExcelDto.add(err);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					err.setDetailError("Phạt Quy cách xử lý sự cố phải là số thực không âm");
					lstErrExcelDto.add(err);
					return false;
				}
			}
		} else if (index == 5) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					err.setDetailError("Phạt lỗi đóng sai vật tư, sử lý sự cố thuê bao không đúng định dạng");
					lstErrExcelDto.add(err);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					err.setDetailError("Phạt lỗi đóng sai vật tư, sử lý sự cố thuê bao phải là số thực không âm");
					lstErrExcelDto.add(err);
					return false;
				}
			}
		} else if (index == 6) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					err.setDetailError("Phạt giảm trừ thuê bao rời mạng không đúng định dạng");
					lstErrExcelDto.add(err);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					err.setDetailError("Phạt giảm trừ thuê bao rời mạng phải là số thực không âm");
					lstErrExcelDto.add(err);
					return false;
				}
			}
		} else if (index == 7) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					err.setDetailError("Phạt thiết bị chuyển đổi công nghệ không thu được không đúng định dạng");
					lstErrExcelDto.add(err);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					err.setDetailError("Phạt thiết bị chuyển đổi công nghệ không thu được phải là số thực không âm");
					lstErrExcelDto.add(err);
					return false;
				}
			}
		} else if (index == 8) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					err.setDetailError("Phạt thiết bị rời mạng không thu được không đúng định dạng");
					lstErrExcelDto.add(err);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					err.setDetailError("Phạt thiết bị rời mạng không thu được phải là số thực không âm");
					lstErrExcelDto.add(err);
					return false;
				}
			}
		} else if (index == 9) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					err.setDetailError("Phạt lỗi ý thức thái độ không đúng định dạng");
					lstErrExcelDto.add(err);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					err.setDetailError("Phạt lỗi ý thức thái độ phải là số thực không âm");
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
			InputStream file = new BufferedInputStream(new FileInputStream(filePath + "NVDayMay-PhatCdt-err.xlsx"));

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

				XSSFCell cell9 = row.createCell(9);
				cell9.setCellValue(obj.get(i).getColumn9());

				for (int j = 0; j < obj.get(i).getLstErrorOrder().size(); j++) {
					err.append(obj.get(i).getLstErrorOrder().get(j).getDetailError() + "-");
					// System.out.println("err" + err);
				}
				System.out.println("err " + i + ":" + err);

				XSSFCell cell10 = row.createCell(10);
				cell10.setCellValue(err.toString());

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

			}
			file.close();

			File out = new File(
					folder2Upload + File.separatorChar + "NVDayMay-PhatCdt-err" + startExportTime + ".xlsx");

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
		return UEncrypt.encryptFileUploadPath("NVDayMay-PhatCdt-err" + startExportTime + ".xlsx");
	}

	@Override
	public String exportExcelGrid(TblPhatCdtDTO lst) throws Exception {

		// TODO Auto-generated method stub
		List<TblPhatCdtDTO> obj = tblPhatCdtDAO.doSearchPhatCDT(lst);
		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream file = new BufferedInputStream(new FileInputStream(filePath + "ExportPhatCdt.xlsx"));
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
				cell2.setCellValue(obj.get(i).getMaNv());

				XSSFCell cell3 = row.createCell(3);
				cell3.setCellValue(obj.get(i).getHoVaTen());

				XSSFCell cell4 = row.createCell(4);
				cell4.setCellValue(obj.get(i).getPhatQcxlsc());

				XSSFCell cell5 = row.createCell(5);
				cell5.setCellValue(obj.get(i).getPhatLdsvtXlsct());

				XSSFCell cell6 = row.createCell(6);
				cell6.setCellValue(obj.get(i).getPhatGttbrm());

				XSSFCell cell7 = row.createCell(7);
				cell7.setCellValue(obj.get(i).getPhatTbcdcnktd());

				XSSFCell cell8 = row.createCell(8);
				cell8.setCellValue(obj.get(i).getPhatTbrmktd());

				XSSFCell cell9 = row.createCell(9);
				cell9.setCellValue(obj.get(i).getPhatLyttd());

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

			File out = new File(folder2Upload + File.separatorChar + "ExportPhatCdt" + startExportTime + ".xlsx");

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
		return UEncrypt.encryptFileUploadPath("ExportPhatCdt" + startExportTime + ".xlsx");
	}
	public String downloadImportTemplate() throws Exception {
		// TODO Auto-generated method stub
		
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		String filePath = classloader.getResource("../" + "doc-template").getPath();
		InputStream file = new BufferedInputStream(new FileInputStream(filePath + "ExportPhatCdt.xlsx"));
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		
		
		file.close();
		File out = new File(folder2Upload + File.separatorChar + "ExportPhatCdt.xlsx");
		
		FileOutputStream outFile = new FileOutputStream(out);
		workbook.write(outFile);
		workbook.close();
		outFile.close();
		
		String path = UEncrypt.encryptFileUploadPath("ExportPhatCdt.xlsx");
		return path;
	}
}
