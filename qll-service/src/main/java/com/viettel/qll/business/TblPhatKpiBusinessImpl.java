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
import com.viettel.qll.bo.TblPhatKpiBO;
import com.viettel.qll.dao.TblDanhMucDAO;
import com.viettel.qll.dao.TblNhanVienDAO;
import com.viettel.qll.dao.TblPhatKpiDAO;
import com.viettel.qll.dto.ErrExcelDTO;
import com.viettel.qll.dto.ImportErrDTO;
import com.viettel.qll.dto.LsThaoTacDTO;
import com.viettel.qll.dto.TblPhatKpiDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;
import com.viettel.utils.ValidateUtils;

import freemarker.core.ParseException;

@Service("tblPhatKpiBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TblPhatKpiBusinessImpl extends BaseFWBusinessImpl<TblPhatKpiDAO, TblPhatKpiDTO, TblPhatKpiBO>
		implements TblPhatKpiBusiness {

	protected final Logger log = Logger.getLogger(TblPhatKpiBusiness.class);

	LsThaoTacDTO lsThaoTacDTO = new LsThaoTacDTO();

	@Autowired
	TblQltsThucXuatTheoKyBusinessImpl businessImpl=new TblQltsThucXuatTheoKyBusinessImpl();
	
	
	@Autowired
	private TblPhatKpiDAO tblPhatKpiDAO;
private String fomat ="MM/yyyy";
	@Autowired
	TblNhanVienDAO nhanVienDAO = new TblNhanVienDAO();
	@Autowired
	private TblDanhMucDAO tblDanhmucDAO;
	@Value("${folder_upload2}")
	private String folder2Upload;

	List<ErrExcelDTO> lstErrorExcell;

	public TblPhatKpiBusinessImpl() {
		tModel = new TblPhatKpiBO();
		tDAO = tblPhatKpiDAO;
	}

	@Override
	public TblPhatKpiDAO gettDAO() {
		return tblPhatKpiDAO;
	}

	@Override
	public DataListDTO doSearchPhatKPI(TblPhatKpiDTO obj) {
		List<TblPhatKpiDTO> ls = tblPhatKpiDAO.doSearchPhatKPI(obj);
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

	public String importPhatKpi(String filePath,HttpServletRequest request) throws Exception {
		// public static void main(String[] args) throws IOException {
		long tStart = System.currentTimeMillis();
		TblPhatKpiDTO phatKpi = new TblPhatKpiDTO();
		/**
		 * sửa đây
		 */
		ImportErrDTO importErrDTO = new ImportErrDTO();
		ErrExcelDTO errExcelDTO = new ErrExcelDTO();
		// lstErrorOrder = new ArrayList<>();
		List<TblPhatKpiDTO> workLstDone = Lists.newArrayList();
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
				boolean checkMaTinh = true;
				boolean checkMaNV = true;
				boolean checkMaTram = true;
				boolean checkSoWOQH = true;
				boolean checkSoBlockQH = true;
				boolean checkDiemKTTram = true;

				String thang = "";
				String kv = "";
				String maTinh = "";
				String maNV = "";
				String maTram = "";
				String soWOQH = "";
				String soBlockQH = "";
				String diemKTTran = "";

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
						maNV = dataFormatter.formatCellValue(cell);
						// checkChucDanh =
						// testCheck(cell.getColumnIndex(),chucDanh);
						break;
					case 5:
						maTram = dataFormatter.formatCellValue(cell);
						break;
					case 6:
						soWOQH = dataFormatter.formatCellValue(cell);
						// checkDonVi = testCheck(cell.getColumnIndex(),donVi);
						break;
					case 7:
						soBlockQH = dataFormatter.formatCellValue(cell);
						// checkChucDanh =
						// testCheck(cell.getColumnIndex(),chucDanh);
						break;
					case 8:
						diemKTTran = dataFormatter.formatCellValue(cell);
						break;

					default:
						break;
					}
				}
				checkThang = checkDLPhatKPI(0, thang, null, null);
				checkKV = checkDLPhatKPI(1, kv, null, null);
				checkMaTinh = checkDLPhatKPI(2, maTinh, null, null);
				checkMaNV = checkDLPhatKPI(3, maNV, null, null);
				checkMaTram = checkDLPhatKPI(4, maTram, maNV, thang);
				checkSoWOQH = checkDLPhatKPI(5, soWOQH, null, null);
				checkSoBlockQH = checkDLPhatKPI(6, soBlockQH, null, null);
				checkDiemKTTram = checkDLPhatKPI(7, diemKTTran, null, null);

				DateFormat dt = new SimpleDateFormat("mm/yyyy");
				if (checkThang && checkKV && checkMaTinh && checkMaNV && checkMaTram && checkSoWOQH && checkSoBlockQH
						&& checkDiemKTTram) {
					phatKpi = new TblPhatKpiDTO();
					if (thang.length() == 6) {
						thang = "0" + thang;
					}
					phatKpi.setThang(thang);
					phatKpi.setKv(kv);
					phatKpi.setMaTinh(maTinh);
					phatKpi.setMaNv(maNV);
					phatKpi.setMaTram(maTram);
					if (soWOQH == null || "".equals(soWOQH)) {
						phatKpi.setSoWoQuaHan(Float.parseFloat("0"));
					} else {
						phatKpi.setSoWoQuaHan(Float.parseFloat(soWOQH));
					}
					if (soBlockQH == null || "".equals(soBlockQH)) {
						phatKpi.setSoBlockWoQuaHan(Float.parseFloat("0"));
					} else {
						phatKpi.setSoBlockWoQuaHan(Float.parseFloat(soBlockQH));
					}
					if (diemKTTran == null || "".equals(diemKTTran)) {
						phatKpi.setDiemKiemTraTram(Float.parseFloat("0"));

					} else {
						phatKpi.setDiemKiemTraTram(Float.parseFloat(diemKTTran));
					}
					phatKpi.setHoatDong((long) 0);
					phatKpi.setXoa((long) 1);
					
					tblPhatKpiDAO.deleteObj(phatKpi);
					workLstDone.add(phatKpi);
				} else {
					importErrDTO = new ImportErrDTO();
					importErrDTO.setColumn1(thang);
					importErrDTO.setColumn2(kv);
					importErrDTO.setColumn3(maTinh);
					importErrDTO.setColumn4(maNV);
					importErrDTO.setColumn5(maTram);
					importErrDTO.setColumn6(soWOQH);
					importErrDTO.setColumn7(soBlockQH);
					importErrDTO.setColumn8(diemKTTran);
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
				lsThaoTacDTO.setChucNang("Import dữ liệu phạt KPI");
				lsThaoTacDTO.setMoTa("Import "+workLstDone.size()+" bản ghi dữ liệu phạt KPI ");
				lsThaoTacDTO.setThoiGianThucHien(tEnd-tStart);
				businessImpl.insertLSTT(lsThaoTacDTO,request);
			}
			if (workLstFault.size() > 0) {
				return exportPhatKPIErr(workLstFault);
			}
		}
		System.out.println(workLstDone.size());
		System.out.println(workLstFault.size());
		return "";
	}

	public boolean checkDLPhatKPI(int index, String value, String t1, String t2) throws ParseException {
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
			/*
			 * kiểm tra tên trùng với mã nv ?????
			 */
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
			long countNv = nhanVienDAO.checkExistMaNvByMaNv(value);
			if (value.isEmpty() || "".equals(value)) {
				err.setDetailError("Mã nhân viên đang để trống, ");
				lstErrorExcell.add(err);
				return false;
			}else if (countNv == (long) 0) {
				err.setDetailError("Mã nhân viên không tồn tại, ");
				lstErrorExcell.add(err);
				return false;
			}

			break;
		case 4:
			// long lst=nVTramDAO.lstphatKPIByKey(value, t1, t2);
			if (value.isEmpty() || "".equals(value)) {
				err.setDetailError("Mã trạm đang để trống.");
				lstErrorExcell.add(err);
				return false;
			} else if (value.length() > 50) {
				err.setDetailError("Mã trạm chỉ được phép nhập 50 kí tự.");
				lstErrorExcell.add(err);
				return false;
			}
			// else if(lst>0){
			// err.setDetailError("Key (Mã trạm,Mã NV,Tháng) đã tồn tại");
			// lstErrorOrder.add(err);
			// return false;
			// }
			break;
		case 5:
			/*
			 * kiểm tra tên trùng với mã nv ?????
			 */
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					err.setDetailError("Số WO quá hạn phải là số thực không âm, ");
					lstErrorExcell.add(err);
					return false;
				}
				// else if (Double.parseDouble(value) < 0) {
				// err.setDetailError("Số WO quá hạn phải là số thực không âm,
				// ");
				// lstErrorExcell.add(err);
				// return false;
				// }
			}

			break;
		case 6:
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					err.setDetailError("Số block WO quá hạn phải là số thực không âm, ");
					lstErrorExcell.add(err);
					return false;
				}
			}

			break;
		case 7:
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					err.setDetailError("Điểm kiểm tra trạm phải là số thực không âm. ");
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

	public String exportPhatKPIErr(List<ImportErrDTO> obj) throws Exception {
		// TODO Auto-generated method stub
		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {

			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream fileIn = new BufferedInputStream(new FileInputStream(filePath + "PhatKPIErr.xlsx"));
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

				for (int j = 0; j < obj.get(i).getLstErrorOrder().size(); j++) {
					err.append(obj.get(i).getLstErrorOrder().get(j).getDetailError());
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
			fileIn.close();

			File out = new File(folder2Upload + File.separatorChar + "PhatKPIErr" + startExportTime + ".xlsx");

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
		return UEncrypt.encryptFileUploadPath("PhatKPIErr" + startExportTime + ".xlsx");

	}
	
	public String exportExcelGrid(TblPhatKpiDTO lst) throws Exception {

		List<TblPhatKpiDTO> obj = tblPhatKpiDAO.doSearchPhatKPI(lst);

		// TODO Auto-generated method stub
		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream fileIn = new BufferedInputStream(new FileInputStream(filePath + "ExportPhatKPI.xlsx"));
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
				cell4.setCellValue(obj.get(i).getMaNv());

				XSSFCell cell5 = row.createCell(5);
				cell5.setCellValue(obj.get(i).getMaTram());

				XSSFCell cell6 = row.createCell(6);
				cell6.setCellValue(obj.get(i).getSoWoQuaHan());

				XSSFCell cell7 = row.createCell(7);
				cell7.setCellValue(obj.get(i).getSoBlockWoQuaHan());

				XSSFCell cell8 = row.createCell(8);
				cell8.setCellValue(obj.get(i).getDiemKiemTraTram());


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
			File out = new File(folder2Upload + File.separatorChar + "ExportPhatKPI" + startExportTime + ".xlsx");

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
		return UEncrypt.encryptFileUploadPath("ExportPhatKPI" + startExportTime + ".xlsx");
	}
	public String downloadImportTemplate() throws Exception {
		// TODO Auto-generated method stub
		
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		String filePath = classloader.getResource("../" + "doc-template").getPath();
		InputStream file = new BufferedInputStream(new FileInputStream(filePath + "ExportPhatKPI.xlsx"));
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		
		
		file.close();
		File out = new File(folder2Upload + File.separatorChar + "ExportPhatKPI.xlsx");
		
		FileOutputStream outFile = new FileOutputStream(out);
		workbook.write(outFile);
		workbook.close();
		outFile.close();
		
		String path = UEncrypt.encryptFileUploadPath("ExportPhatKPI.xlsx");
		return path;
	}
}
