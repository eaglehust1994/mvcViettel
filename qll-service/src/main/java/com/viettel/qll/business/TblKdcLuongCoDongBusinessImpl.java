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
import com.viettel.qll.bo.TblKdcLuongCoDongBO;
import com.viettel.qll.dao.TblKdcLuongCoDongDAO;
import com.viettel.qll.dao.TblNhanVienDAO;
import com.viettel.qll.dto.ErrExcelDTO;
import com.viettel.qll.dto.ImportErrDTO;
import com.viettel.qll.dto.LsThaoTacDTO;
import com.viettel.qll.dto.QllBaseDTO;
import com.viettel.qll.dto.TblKdcLuongCoDongDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;
import com.viettel.utils.ValidateUtils;

@Service("tblKdcLuongCoDongBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TblKdcLuongCoDongBusinessImpl
		extends BaseFWBusinessImpl<TblKdcLuongCoDongDAO, TblKdcLuongCoDongDTO, TblKdcLuongCoDongBO>
		implements TblKdcLuongCoDongBusiness {
	protected final Logger log = Logger.getLogger(TblKdcLuongCoDongBusiness.class);

	LsThaoTacDTO lsThaoTacDTO = new LsThaoTacDTO();

	@Autowired
	TblQltsThucXuatTheoKyBusinessImpl businessImpl=new TblQltsThucXuatTheoKyBusinessImpl();
	
	@Autowired
	private TblKdcLuongCoDongDAO tblKdcLuongCoDongDAO;
private String fomat="MM/yyyy";
	@Autowired
	TblNhanVienDAO tblNhanVienDAO;

	@Value("${folder_upload2}")
	private String folder2Upload;

	List<ErrExcelDTO> lstErrorExcell;

	public TblKdcLuongCoDongBusinessImpl() {
		tModel = new TblKdcLuongCoDongBO();
		tDAO = tblKdcLuongCoDongDAO;
	}

	@Override
	public DataListDTO doSearchKDCLuongCD(TblKdcLuongCoDongDTO obj, HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		String err = "";
		List<TblKdcLuongCoDongDTO> ls = tblKdcLuongCoDongDAO.doSearchKDCLuongCD(obj);
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

	public String importKDCLuongCD(String filePath,HttpServletRequest request
			) throws Exception {
		long tStart = System.currentTimeMillis();
		// public static void main(String[] args) throws IOException {
		TblKdcLuongCoDongDTO lst = new TblKdcLuongCoDongDTO();
		// KIDonViDTO lstFault = new KIDonViDTO();

		ImportErrDTO importErrDTO = new ImportErrDTO();
		// List<ErrExcelDTO> lstErrorExcell;

		ErrExcelDTO errExcelDTO = new ErrExcelDTO();
		// lstErrorOrder = new ArrayList<>();
		List<TblKdcLuongCoDongDTO> workLstDone = Lists.newArrayList();
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
				lstErrorExcell = new ArrayList();
				ImportErrDTO err = new ImportErrDTO();
				boolean checkThang = true;
				boolean checkDonVi = true;
				boolean checkKDC = true;
				String thang = "";
				String kdc = "";
				String donVi = "";
				for (Cell cell : row) {
					if (cell.getColumnIndex() == 1) {
						thang = dataFormatter.formatCellValue(cell);

					} else if (cell.getColumnIndex() == 2) {
						donVi = dataFormatter.formatCellValue(cell);
					} else if (cell.getColumnIndex() == 3) {
						kdc = dataFormatter.formatCellValue(cell);
					}
				}
				checkThang = checkKDCLuongCD(1, thang);
				checkDonVi = checkKDCLuongCD(2, donVi);
				checkKDC = checkKDCLuongCD(3, kdc);
				if (checkThang && checkDonVi && checkKDC) {

					lst = new TblKdcLuongCoDongDTO();
					lst.setThang(thang);
					lst.setDonVi(donVi);
					lst.setKdc(Float.parseFloat(kdc));
					lst.setXoa((long) 0);
					lst.setHoatDong((long) 1);
					tblKdcLuongCoDongDAO.deleteObj(lst);
					// if (tblDmKiDonViDAO.countKiDonViByTinh(tinh,
					// thang).size() > 0) {
					// tblDmKiDonViDAO.deleteObj(lst);
					// }
					workLstDone.add(lst);
				} else {
					importErrDTO = new ImportErrDTO();
					importErrDTO.setColumn1(thang);
					importErrDTO.setColumn2(donVi);
					importErrDTO.setColumn3(kdc);
					importErrDTO.setLstErrorOrder(lstErrorExcell);
					workLstFault.add(importErrDTO);
				}
			}
		}

		System.out.println("size done: " + workLstDone.size());
		System.out.println("size: " + workLstFault.size());
		// workLstDone.toArray();
		if (workLstDone.size() <= 0 && workLstFault.size() <= 0) {
			throw new IllegalArgumentException("File import không có dữ liệu");
		} else {
			if (workLstDone.size() > 0) {
				saveList1(workLstDone);
				long tEnd = System.currentTimeMillis();
				lsThaoTacDTO.setChucNang("Import dữ liệu kdc lương cơ động");
				lsThaoTacDTO.setMoTa("Import "+workLstDone.size() +" bản ghi dữ liệu kdc lương cơ động ");
				lsThaoTacDTO.setThoiGianThucHien(tEnd-tStart);
				businessImpl.insertLSTT(lsThaoTacDTO,request);
			}
			if (workLstFault.size() > 0) {
				return exportKDCLuongCDErr(workLstFault);
			}
		}
		System.out.println(workLstDone.toString().toString());
		return "";
	}

	public boolean checkKDCLuongCD(int index, String value) {
		String errDetail = "";
		ErrExcelDTO err = new ErrExcelDTO();
		if (index == 1) {
			if (value.isEmpty()) {
				err.setDetailError("Tháng không được để trống");
				lstErrorExcell.add(err);
				return false;
			} else {
				if (!ValidateUtils.isDate(value,fomat)) {
					err.setDetailError("Tháng không đúng định dạng mm/yyyy");
					lstErrorExcell.add(err);
					return false;
				}
			}

		} else if (index == 2) {
			if (value.isEmpty()) {
				err.setDetailError("Đơn vị không được để trống");
				lstErrorExcell.add(err);
				return false;
			} else if (value.length() > 50) {
				err.setDetailError("Tỉnh chỉ được phép nhập 100 kí tự");
				lstErrorExcell.add(err);
				return false;
			}
			// else {
			// long a = countKiDonViByTinh(value);
			// // System.out.println("aaaaaaa:" +a);
			// if(a!=0){
			// err.setDetailError("Trường tỉnh đã tồn tại trong cơ sở dữ liệu");
			// lstErrorOrder.add(err);
			// return false;
			// }
			// }
		} else if (index == 3) {
			if (value.isEmpty()) {
				err.setDetailError("K điều chỉnh không được để trống");
				lstErrorExcell.add(err);
				return false;
			} else if (!ValidateUtils.isFloat(value)) {
				err.setDetailError("K điều chỉnh không đúng định dạng");
				lstErrorExcell.add(err);
				return false;
			} else if (Double.parseDouble(value) < 0) {
				err.setDetailError("K điều chỉnh phải là số thực không âm");
				lstErrorExcell.add(err);
				return false;
			}
		}

		return true;
	}

	public String exportKDCLuongCDErr(List<ImportErrDTO> obj) throws Exception {
		// TODO Auto-generated method stub
		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream fileIn = new BufferedInputStream(new FileInputStream(filePath + "KDCLuongCDErr.xlsx"));
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
			// System.out.println("tem:::"+folderTemplateBuild);
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
				cell1.setCellStyle(style);

				XSSFCell cell2 = row.createCell(2);
				cell2.setCellValue(obj.get(i).getColumn2());
				cell2.setCellStyle(style);

				XSSFCell cell3 = row.createCell(3);
				cell3.setCellValue(obj.get(i).getColumn3());
				cell3.setCellStyle(style);

				for (int j = 0; j < obj.get(i).getLstErrorOrder().size(); j++) {
					err.append(obj.get(i).getLstErrorOrder().get(j).getDetailError() + "-");
					// System.out.println("err" + err);
				}
				System.out.println("err " + i + ":" + err);

				XSSFCell cell4 = row.createCell(4);
				cell4.setCellValue(err.toString());
				cell4.setCellStyle(style);
			}
			File out = new File(folder2Upload + File.separatorChar + "KDCLuongCDErr" + startExportTime + ".xlsx");

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
		return UEncrypt.encryptFileUploadPath("KDCLuongCDErr" + startExportTime + ".xlsx");
	}

	public String exportKDCLuongCD(TblKdcLuongCoDongDTO lst) throws Exception {
		List<TblKdcLuongCoDongDTO> obj = tblKdcLuongCoDongDAO.doSearchKDCLuongCD(lst);
		// TODO Auto-generated method stub
		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream fileIn = new BufferedInputStream(new FileInputStream(filePath + "ExportKDCLuongCD.xlsx"));
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
			// System.out.println("tem:::"+folderTemplateBuild);
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
				cell1.setCellStyle(style);

				XSSFCell cell2 = row.createCell(2);
				cell2.setCellValue(obj.get(i).getDonVi());
				cell2.setCellStyle(style);

				XSSFCell cell3 = row.createCell(3);
				cell3.setCellValue(obj.get(i).getKdc());
				cell3.setCellStyle(style);


			}
			File out = new File(folder2Upload + File.separatorChar + "ExportKDCLuongCD" + startExportTime + ".xlsx");

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
		return UEncrypt.encryptFileUploadPath("ExportKDCLuongCD" + startExportTime + ".xlsx");
	}
	
	@SuppressWarnings("unchecked")
	public String saveList1(List<TblKdcLuongCoDongDTO> lstCenterBO)
	  {
		@SuppressWarnings("rawtypes")
		List lstModel = new ArrayList<>();
	    if (lstCenterBO != null) {
	      for (int i = 0; i < lstCenterBO.size(); i++) {
	        lstModel.add(((QllBaseDTO<TblKdcLuongCoDongBO>)lstCenterBO.get(i)).toModel());
	      }
	    }
	    return tblKdcLuongCoDongDAO.saveList1(lstModel);
	  }
}
