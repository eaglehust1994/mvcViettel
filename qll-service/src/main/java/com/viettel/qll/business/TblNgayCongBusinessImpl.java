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
import com.viettel.qll.bo.TblNgayCongBO;
import com.viettel.qll.dao.TblNgayCongDAO;
import com.viettel.qll.dao.TblNhanVienDAO;
import com.viettel.qll.dto.ErrExcelDTO;
import com.viettel.qll.dto.ImportErrDTO;
import com.viettel.qll.dto.LsThaoTacDTO;
import com.viettel.qll.dto.TblNgayCongDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;
import com.viettel.utils.ValidateUtils;


@Service("tblNgayCongBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TblNgayCongBusinessImpl extends BaseFWBusinessImpl<TblNgayCongDAO,TblNgayCongDTO, TblNgayCongBO> implements TblNgayCongBusiness {
	static Logger LOGGER = LoggerFactory.getLogger(TblNgayCongBusinessImpl.class);
    @Autowired
    private TblNgayCongDAO tblNgayCongDAO;
    List<ErrExcelDTO> lstErrExcelDto;
    private String fomat="MM/yyyy";
    
    LsThaoTacDTO lsThaoTacDTO = new LsThaoTacDTO();

	@Autowired
	TblQltsThucXuatTheoKyBusinessImpl businessImpl=new TblQltsThucXuatTheoKyBusinessImpl();
	
    
	@Autowired
	private TblNhanVienDAO tblNhanVienDAO;
	@Value("${folder_upload2}")
	private String folder2Upload;
	
    public TblNgayCongBusinessImpl() {
        tModel = new TblNgayCongBO();
        tDAO = tblNgayCongDAO;
    }

    @Override
    public TblNgayCongDAO gettDAO() {
        return tblNgayCongDAO;
    }

	@Override
	public DataListDTO doSearch(TblNgayCongDTO obj, HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		String err = "";

		List<TblNgayCongDTO> ls = tblNgayCongDAO.doSearch(obj);
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		data.setTotal(obj.getTotalRecord());
		data.setSize(obj.getTotalRecord());
		data.setStart(1);
		return data;
	}
	@Override
	public String importFile(String fileInput,HttpServletRequest request) {
		long tStart = System.currentTimeMillis();
		List<TblNgayCongDTO> workLst = Lists.newArrayList();
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
					boolean checkThang = true;
					boolean checkMaNv = true;
					boolean checkHoVaTen = true;
					boolean checkqttl = true;
					boolean checkngaycongtl = true;
					boolean checkngaycongcd = true;
					String thang = "";
					String maNv = "";
					String hoVaTen = "";
					String qttl = "";
					String ngaycongtl = "";
					String ngaycongcd="";
					for (Cell cell : row) {
						if (cell.getColumnIndex() == 1) {
							thang = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 2) {
							maNv = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 3) {
							hoVaTen = dataFormatter.formatCellValue(cell).trim();
						}else if (cell.getColumnIndex() == 4) {
							qttl = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 5) {
							ngaycongtl = dataFormatter.formatCellValue(cell).trim();
						}
						else if (cell.getColumnIndex() == 6) {
							ngaycongcd = dataFormatter.formatCellValue(cell).trim();
						}
					}

					checkThang = checkPhatFC(1, thang, orderErrorFormat);
					checkMaNv = checkPhatFC(2, maNv, orderErrorFormat);
					checkHoVaTen = checkPhatFC(3, hoVaTen, orderErrorFormat);
					//checkqttl = checkPhatFC(4, qttl, orderErrorFormat);
					checkngaycongtl = checkPhatFC(5, ngaycongtl, orderErrorFormat);
					checkngaycongcd = checkPhatFC(6, ngaycongcd, orderErrorFormat);

					if (checkThang && checkMaNv && checkHoVaTen && checkngaycongcd && checkngaycongtl&&checkqttl) {
						long delele = deletePhatFcByMaNvAndThang(maNv, thang);
						TblNgayCongDTO lst = new TblNgayCongDTO();
						lst.setThang(thang);
						lst.setMaNv(maNv.toUpperCase());
						lst.setHoVaTen(hoVaTen);
						lst.setQuatrinhtinhluong(qttl);
						if (!ngaycongtl.isEmpty()) {
							lst.setNgayCongTinhLuong(Float.parseFloat(ngaycongtl));
						} else {
							lst.setNgayCongTinhLuong(0f);
						}

						if (!ngaycongcd.isEmpty()) {
							lst.setNgayCongCheDo(Float.parseFloat(ngaycongcd));
						} else {
							lst.setNgayCongCheDo(0f);
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
						errObj.setColumn4(qttl);
						errObj.setColumn5(ngaycongtl);
						errObj.setColumn6(ngaycongcd);
						errObj.setLstErrorOrder(lstErrExcelDto);
						workFault.add(errObj);
					}
				} else if (sheet.getLastRowNum() < 6) {
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
					lsThaoTacDTO.setChucNang("Import dữ liệu ngày công");
					lsThaoTacDTO.setMoTa("Import "+workLst.size()+" bản ghi dữ liệu ngày công ");
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
	public boolean checkPhatFC(int index, String value, ErrExcelDTO orderErrorFormat) {
		orderErrorFormat = new ErrExcelDTO();
		if (index == 1) {
			if (value.isEmpty()) {
				orderErrorFormat.setDetailError("Trường tháng không được để trống");
				lstErrExcelDto.add(orderErrorFormat);
				return false;
			} else {
				if (!ValidateUtils.isDate(value,fomat)) {
					orderErrorFormat.setDetailError("Trường tháng không đúng định dạng");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				} else {
					int batdau, ketthuc;
					String thang;
					batdau = value.indexOf('/') + 1;
					ketthuc = batdau + value.substring(value.indexOf('/') + 1).indexOf('/');
					if(batdau<ketthuc)
					{
						 thang = value.substring(batdau, ketthuc);
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
				orderErrorFormat.setDetailError("Trường mã nhân viên không được để trống");
				lstErrExcelDto.add(orderErrorFormat);
				return false;
			} else {
				// tblNhanVienDTO.setMaNv(value);
				if (value.length() > 50) {
					orderErrorFormat.setDetailError("Mã nhân viên vượt quá độ dài cho phép");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
				// if (tblPhatFcDAO.checkExistMaNvByMaNv(value) == 0) {
				if (tblNhanVienDAO.checkExistMaNvByMaNv(value) == 0) {
					orderErrorFormat.setDetailError("Mã nhân viên không tồn tại");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}
		} else if (index == 3) {
			if (!value.isEmpty()) {
				if (value.length() > 100) {
					orderErrorFormat.setDetailError("Họ và tên vượt quá độ dài cho phép");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}
		}  else if (index == 5) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					orderErrorFormat.setDetailError("Ngày công tính lương không đúng định dạng");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					orderErrorFormat.setDetailError("Ngày công tính lương phải là số thực không âm");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}
		}
		else if (index == 6) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					orderErrorFormat.setDetailError("Ngày công chế độ không đúng định dạng");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					orderErrorFormat.setDetailError("Ngày công chế độ phải là số thực không âm");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}
		}
		return true;
	}
	public String exportExcelGrid(TblNgayCongDTO lst) throws Exception {
		// TODO Auto-generated method stub
		List<TblNgayCongDTO> obj = tblNgayCongDAO.doSearch(lst);
		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream file = new BufferedInputStream(new FileInputStream(filePath + "ExportNgayCong.xlsx"));
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
				cell2.setCellValue(obj.get(i).getMaNv());

				XSSFCell cell3 = row.createCell(3);
				cell3.setCellValue(obj.get(i).getHoVaTen());

				XSSFCell cell4 = row.createCell(4);
				cell4.setCellValue(obj.get(i).getQuatrinhtinhluong());

				XSSFCell cell5 = row.createCell(5);
				cell5.setCellValue(obj.get(i).getNgayCongTinhLuong());

				XSSFCell cell6 = row.createCell(6);
				cell6.setCellValue(obj.get(i).getNgayCongCheDo());
				cell0.setCellStyle(style);
				cell1.setCellStyle(style);
				cell2.setCellStyle(style);
				cell3.setCellStyle(style);
				cell4.setCellStyle(style);
				cell5.setCellStyle(style);
				cell6.setCellStyle(style);

			}
			file.close();

			File out = new File(folder2Upload + File.separatorChar + "ExportNgayCong" + startExportTime + ".xlsx");

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
		return UEncrypt.encryptFileUploadPath("ExportNgayCong" + startExportTime + ".xlsx");
	}
	@Override
	public String exportExcelError(List<ImportErrDTO> obj) throws Exception {
		// TODO Auto-generated method stub
				String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
				try {
					ClassLoader classloader = Thread.currentThread().getContextClassLoader();
					String filePath = classloader.getResource("../" + "doc-template").getPath();
					InputStream file = new BufferedInputStream(new FileInputStream(filePath + "ExportNgayCong_error.xlsx"));
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

						for (int j = 0; j < obj.get(i).getLstErrorOrder().size(); j++) {
							err.append(obj.get(i).getLstErrorOrder().get(j).getDetailError() + "-");
							// System.out.println("err" + err);
						}
						System.out.println("err " + i + ":" + err);

						XSSFCell cell7 = row.createCell(7);
						cell7.setCellValue(err.toString());

						cell0.setCellStyle(style);
						cell1.setCellStyle(style);
						cell2.setCellStyle(style);
						cell3.setCellStyle(style);
						cell4.setCellStyle(style);
						cell5.setCellStyle(style);
						cell6.setCellStyle(style);
						cell7.setCellStyle(style);
				
					}
					file.close();

					File out = new File(folder2Upload + File.separatorChar + "ExportNgayCong_error.xlsx" + startExportTime + ".xlsx");

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
				return UEncrypt.encryptFileUploadPath("ExportNgayCong_error.xlsx" + startExportTime + ".xlsx");
	}

	@Override
	public long deletePhatFcByMaNvAndThang(String maNv, String thang) {
		return tblNgayCongDAO.deletePhatFcByMaNvAndThang(maNv, thang);
	}

	@Override
	public String downloadImportTemplate() throws Exception {
		
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		String filePath = classloader.getResource("../" + "doc-template").getPath();
		InputStream file = new BufferedInputStream(new FileInputStream(filePath + "ExportNgayCong.xlsx"));
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		
		
		file.close();
		File out = new File(folder2Upload + File.separatorChar + "ExportNgayCong.xlsx");
		
		FileOutputStream outFile = new FileOutputStream(out);
		workbook.write(outFile);
		workbook.close();
		outFile.close();
		
		String path = UEncrypt.encryptFileUploadPath("ExportNgayCong.xlsx");
		return path;
	}
	
}
