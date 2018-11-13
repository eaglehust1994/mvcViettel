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
import com.viettel.qll.bo.TblPhatRoiMangBO;
import com.viettel.qll.dao.TblDanhMucDAO;
import com.viettel.qll.dao.TblNhanVienDAO;
import com.viettel.qll.dao.TblPhatRoiMangDAO;
import com.viettel.qll.dto.ErrExcelDTO;
import com.viettel.qll.dto.ImportErrDTO;
import com.viettel.qll.dto.LsThaoTacDTO;
import com.viettel.qll.dto.TblPhatRoiMangDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;
import com.viettel.utils.ValidateUtils;


@Service("tblPhatRoiMangBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TblPhatRoiMangBusinessImpl extends BaseFWBusinessImpl<TblPhatRoiMangDAO,TblPhatRoiMangDTO, TblPhatRoiMangBO> implements TblPhatRoiMangBusiness {

    @Autowired
    private TblPhatRoiMangDAO tblPhatRoiMangDAO;
    @Autowired
    private TblNhanVienDAO tblNhanVienDAO;
    
    LsThaoTacDTO lsThaoTacDTO = new LsThaoTacDTO();

	@Autowired
	TblQltsThucXuatTheoKyBusinessImpl businessImpl=new TblQltsThucXuatTheoKyBusinessImpl();
	
    @Autowired
    private TblDanhMucDAO tblDanhmucDAO;
	List<ErrExcelDTO> lstErrExcelDto;
	@Value("${folder_upload2}")
	private String folder2Upload;
	static Logger LOGGER = LoggerFactory.getLogger(TblPhatRoiMangBusinessImpl.class);
	
    public TblPhatRoiMangBusinessImpl() {
        tModel = new TblPhatRoiMangBO();
        tDAO = tblPhatRoiMangDAO;
    }

    @Override
    public TblPhatRoiMangDAO gettDAO() {
        return tblPhatRoiMangDAO;
    }

	@Override
	public DataListDTO doSearch(TblPhatRoiMangDTO obj) {
		// TODO Auto-generated method stub
		String err = "";

		List<TblPhatRoiMangDTO> ls = tblPhatRoiMangDAO.doSearch(obj);
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
		List<TblPhatRoiMangDTO> workLst = Lists.newArrayList();
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
				if (count >= 7 && !ValidateUtils.isRowEmpty(row)) {
					lstErrExcelDto = new ArrayList<>();
					boolean checkmatinh = true;
					boolean checkmattns = true;
					boolean checkphatsauthue = true;
					boolean checkphattruocthue = true;
					boolean checktenctv = true;
					boolean checkthang = true;
					boolean checkuserbh = true;
					boolean checktongthuebao=true;
					String matinh = "";
					String mattns = "";
					String phatsauthue = "";
					String phattruocthue = "";
					String tenctv = "";
					String thang = "";
					String tongthuebao = "";
					String userbh = "";

					for (Cell cell : row) {
						if (cell.getColumnIndex() == 1) {
							thang = dataFormatter.formatCellValue(cell).trim();
						}  else if (cell.getColumnIndex() == 2) {
							userbh = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 3) {
							matinh = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 4) {
							tongthuebao = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 5) {
							phattruocthue = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 6) {
							phatsauthue = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 7) {
							mattns = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 8) {
							tenctv = dataFormatter.formatCellValue(cell).trim();
						}
					}

					checkthang = checkPhatRoiMang(1, thang, orderErrorFormat);
					checkmatinh = checkPhatRoiMang(2, matinh, orderErrorFormat);
					checkmattns = checkPhatRoiMang(3, mattns, orderErrorFormat);
					checkphatsauthue = checkPhatRoiMang(4, phatsauthue, orderErrorFormat);
					checkphattruocthue = checkPhatRoiMang(5, phattruocthue, orderErrorFormat);
					checktenctv = checkPhatRoiMang(6, tenctv, orderErrorFormat);
					checktongthuebao = checkPhatRoiMang(7, tongthuebao, orderErrorFormat);
					checkuserbh = checkPhatRoiMang(8, userbh, orderErrorFormat);


					if (checkmatinh && checkmattns && checkphatsauthue && checkphattruocthue && checktenctv && checkthang && checktongthuebao && checkuserbh) {
						long delele = deletePhatFcByMaNvAndThang(userbh, thang);
						TblPhatRoiMangDTO lst = new TblPhatRoiMangDTO();
						lst.setThang(thang);
						lst.setMaTinh(matinh.toUpperCase());
						lst.setMaTtns(mattns);
						lst.setTenCtv(tenctv);
						lst.setUserBh(userbh);
						if (!phatsauthue.isEmpty()) {
							lst.setPhatSauThue(Float.parseFloat(phatsauthue));
						} else {
							lst.setPhatSauThue(0f);
						}

						if (!phattruocthue.isEmpty()) {
							lst.setPhatTruocThue(Float.parseFloat(phattruocthue));
						} else {
							lst.setPhatTruocThue(0f);
						}

						if (!tongthuebao.isEmpty()) {
							lst.setTongThueBao(Float.parseFloat(tongthuebao));
						} else {
							lst.setTongThueBao(0f);
						}

						lst.setXoa(0l);
						lst.setHoatDong(1l);

						workLst.add(lst);
						// save(newObj);
					} else {
						ImportErrDTO errObj = new ImportErrDTO();
						errObj.setColumn1(thang);
						errObj.setColumn2(userbh);
						errObj.setColumn3(matinh);
						errObj.setColumn4(tongthuebao);
						errObj.setColumn5(phattruocthue);
						errObj.setColumn6(phatsauthue);
						errObj.setColumn7(mattns);
						errObj.setLstErrorOrder(lstErrExcelDto);
						workFault.add(errObj);
					}
				} else if (sheet.getLastRowNum() < 7) {
					int tam = sheet.getLastRowNum();
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
					lsThaoTacDTO.setChucNang("Import dữ liệu phạt rời mạng");
					lsThaoTacDTO.setMoTa("Import "+workLst.size()+" bản ghi dữ liệu phạt rời mạng ");
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
	public boolean checkPhatRoiMang(int index, String value, ErrExcelDTO orderErrorFormat) {
		orderErrorFormat = new ErrExcelDTO();
		if (index == 1) {
			if (value.isEmpty()) {
				orderErrorFormat.setDetailError("Trường tháng không được để trống");
				lstErrExcelDto.add(orderErrorFormat);
				return false;
			} else {
				if (!ValidateUtils.isDate(value,"MM/yyyy")) {
					orderErrorFormat.setDetailError("Trường tháng không đúng định dạng");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				} else {
					int batdau, ketthuc;
					batdau = value.indexOf('/') + 1;
					ketthuc = batdau + value.substring(value.indexOf('/') + 1).indexOf('/');
					if(batdau<ketthuc)
					{
						String thang = value.substring(batdau, ketthuc);
						int chuyendoi = Integer.parseInt(thang);
						if (chuyendoi > 12 || chuyendoi < 1) {
							orderErrorFormat.setDetailError("Tháng không đúng định dạng");
							lstErrExcelDto.add(orderErrorFormat);
							return false;
						}
					}
					else
					{
						String namcv = value.substring(batdau);
						String thangcv = value.substring(0,batdau-1);
						if(namcv.length()<4)
						{
							orderErrorFormat.setDetailError("Năm không đúng định dạng");
							lstErrExcelDto.add(orderErrorFormat);
							return false;
						}
						int chuyendoi = Integer.parseInt(thangcv);
						if (chuyendoi > 12 || chuyendoi < 1) {
							orderErrorFormat.setDetailError("Tháng không đúng định dạng");
							lstErrExcelDto.add(orderErrorFormat);
							return false;
						}
						
					}
					
				}
			}

		} else if (index == 2) {

			// TblNhanVienDTO tblNhanVienDTO = new TblNhanVienDTO();
			if (value.isEmpty()) {
				orderErrorFormat.setDetailError("Trường mã Tỉnh không được để trống");
				lstErrExcelDto.add(orderErrorFormat);
				return false;
			} else {
				// tblNhanVienDTO.setMaNv(value);
				if (value.length() > 50) {
					orderErrorFormat.setDetailError("Mã Tỉnh vượt quá độ dài cho phép");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
				else if(tblDanhmucDAO.checkExistmatinhBymatinh(value)!=1l)
				{
					orderErrorFormat.setDetailError("Mã Tỉnh không chính xác");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
				// if (tblPhatFcDAO.checkExistMaNvByMaNv(value) == 0) {
			}
		} else if (index == 3) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					orderErrorFormat.setDetailError("MÃ TTNS không đúng định dạng");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					orderErrorFormat.setDetailError("MÃ TTNS phải là số thực không âm");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}
		} else if (index == 4) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					orderErrorFormat.setDetailError("Phạt sau thuế không đúng định dạng");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					orderErrorFormat.setDetailError("Phạt sau thuế phải là số thực không âm");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}
		} else if (index == 5) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					orderErrorFormat.setDetailError("Phạt trước thuế không đúng định dạng");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					orderErrorFormat.setDetailError("Phạt trước thuế phải là số thực không âm");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}
		}else if (index == 6) {

			// TblNhanVienDTO tblNhanVienDTO = new TblNhanVienDTO();
			if (value.isEmpty()) {
				orderErrorFormat.setDetailError("Trường Tên CTV không được để trống");
				lstErrExcelDto.add(orderErrorFormat);
				return false;
			} else {
				// tblNhanVienDTO.setMaNv(value);
				if (value.length() > 50) {
					orderErrorFormat.setDetailError("Tên CTV vượt quá độ dài cho phép");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
				// if (tblPhatFcDAO.checkExistMaNvByMaNv(value) == 0) {
			}
		}
		else if (index == 7) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					orderErrorFormat.setDetailError("Tổng thuê bao không đúng định dạng");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					orderErrorFormat.setDetailError("Tổng thuê bao phải là số thực không âm");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}
		
			else if (index == 8) {

				// TblNhanVienDTO tblNhanVienDTO = new TblNhanVienDTO();
				if (value.isEmpty()) {
					orderErrorFormat.setDetailError("Trường User bán hàng không được để trống");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				} else {
					// tblNhanVienDTO.setMaNv(value);
					if (value.length() > 50) {
						orderErrorFormat.setDetailError("User bán hàng vượt quá độ dài cho phép");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
					// if (tblPhatFcDAO.checkExistMaNvByMaNv(value) == 0) {
				}
			}
		}
		return true;
	}
	public long deletePhatFcByMaNvAndThang(String maNv, String thang) {
		return tblPhatRoiMangDAO.deletePhatroimangByMaNvAndThang(maNv, thang);
		// return 1l;
	}

	@Override
	public String exportExcelError(List<ImportErrDTO> obj) throws Exception {

		// TODO Auto-generated method stub
		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream file = new BufferedInputStream(new FileInputStream(filePath + "NVDayMayphatRoiMangerror.xlsx"));
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

			File out = new File(folder2Upload + File.separatorChar + "NVDayMayphatRoiMangerror" + startExportTime + ".xlsx");

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
		return UEncrypt.encryptFileUploadPath("NVDayMayphatRoiMangerror" + startExportTime + ".xlsx");
	}

	@Override
	public String exportExcelGrid(TblPhatRoiMangDTO lst) throws Exception {
		List<TblPhatRoiMangDTO> obj = tblPhatRoiMangDAO.doSearch(lst);
		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream file = new BufferedInputStream(new FileInputStream(filePath + "10-NVDayMay-phatRoiMang.xlsx"));
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
				cell1.setCellValue(obj.get(i).getThang());

				XSSFCell cell2 = row.createCell(2);
				cell2.setCellValue(obj.get(i).getUserBh());

				XSSFCell cell3 = row.createCell(3);
				cell3.setCellValue(obj.get(i).getMaTinh());

				XSSFCell cell4 = row.createCell(4);
				cell4.setCellValue(obj.get(i).getTongThueBao());

				XSSFCell cell5 = row.createCell(5);
				cell5.setCellValue(obj.get(i).getPhatTruocThue());

				XSSFCell cell6 = row.createCell(6);
				cell6.setCellValue(obj.get(i).getPhatSauThue());

				XSSFCell cell7 = row.createCell(7);
				cell7.setCellValue(obj.get(i).getMaTtns());

				XSSFCell cell8 = row.createCell(8);
				cell8.setCellValue(obj.get(i).getTenCtv());

				

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

			File out = new File(folder2Upload + File.separatorChar + "10-NVDayMay-phatRoiMang" + startExportTime + ".xlsx");

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
		return UEncrypt.encryptFileUploadPath("10-NVDayMay-phatRoiMang" + startExportTime + ".xlsx");
	}
	
	public String downloadImportTemplate() throws Exception {
		// TODO Auto-generated method stub
		
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		String filePath = classloader.getResource("../" + "doc-template").getPath();
		InputStream file = new BufferedInputStream(new FileInputStream(filePath + "10-NVDayMay-phatRoiMang.xlsx"));
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		
		
		file.close();
		File out = new File(folder2Upload + File.separatorChar + "10-NVDayMay-phatRoiMang.xlsx");
		
		FileOutputStream outFile = new FileOutputStream(out);
		workbook.write(outFile);
		workbook.close();
		outFile.close();
		
		String path = UEncrypt.encryptFileUploadPath("10-NVDayMay-phatRoiMang.xlsx");
		return path;
	}
}
