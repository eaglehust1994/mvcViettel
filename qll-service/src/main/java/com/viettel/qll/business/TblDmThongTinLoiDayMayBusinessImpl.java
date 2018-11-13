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
import com.viettel.qll.bo.TblDmThongTinLoiDayMayBO;
import com.viettel.qll.dao.TblDmThongTinLoiDayMayDAO;
import com.viettel.qll.dao.TblNhanVienDAO;
import com.viettel.qll.dto.ErrExcelDTO;
import com.viettel.qll.dto.ImportErrDTO;
import com.viettel.qll.dto.LsThaoTacDTO;
import com.viettel.qll.dto.TblDmThongTinLoiDayMayDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;
import com.viettel.utils.ValidateUtils;


@Service("tblDmThongTinLoiDayMayBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TblDmThongTinLoiDayMayBusinessImpl extends BaseFWBusinessImpl<TblDmThongTinLoiDayMayDAO,TblDmThongTinLoiDayMayDTO, TblDmThongTinLoiDayMayBO> implements TblDmThongTinLoiDayMayBusiness {

	private String fomat="MM/yyyy";
    @Autowired
    private TblDmThongTinLoiDayMayDAO tblDmThongTinLoiDayMayDAO;
    @Autowired
    private TblNhanVienDAO tblNhanVienDAO;
	List<ErrExcelDTO> lstErrExcelDto;
	@Value("${folder_upload2}")
	private String folder2Upload;
	
	LsThaoTacDTO lsThaoTacDTO = new LsThaoTacDTO();

	@Autowired
	TblQltsThucXuatTheoKyBusinessImpl businessImpl=new TblQltsThucXuatTheoKyBusinessImpl();
	
	
	static Logger LOGGER = LoggerFactory.getLogger(TblPhatCdtBusinessImpl.class);
	
    public TblDmThongTinLoiDayMayBusinessImpl() {
        tModel = new TblDmThongTinLoiDayMayBO();
        tDAO = tblDmThongTinLoiDayMayDAO;
    }

    @Override
    public TblDmThongTinLoiDayMayDAO gettDAO() {
        return tblDmThongTinLoiDayMayDAO;
    }

	@Override
	public DataListDTO doSearchTblDmThongTinLoiDayMayDTO(TblDmThongTinLoiDayMayDTO obj) {
		// TODO Auto-generated method stub
		List<TblDmThongTinLoiDayMayDTO> ls = tblDmThongTinLoiDayMayDAO.doSearch(obj);
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		data.setTotal(obj.getTotalRecord());
		data.setSize(obj.getTotalRecord());
		data.setStart(1);
		return data;
	}

	@Override
	public String importFile(String fileInput,HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		long tStart = System.currentTimeMillis();
		List<TblDmThongTinLoiDayMayDTO> workLst = Lists.newArrayList();
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
	                boolean checkMaLoi = true;
	                boolean checkThueBaoKh = true;
	                boolean checkMaNv = true;
	                boolean checkSoNgayTon = true;
	                boolean checkCuocPhatSinh = true;

	                String thang = "";
	                String maLoi = "";
	                String thueBaoKh = "";
	                String maNv = "";
	                String soNgayTon = "";
	                String cuocPhatSinh = "";

	                for (Cell cell : row) {
	                	if (cell.getColumnIndex() == 1) {
	                        thang = dataFormatter.formatCellValue(cell).trim();
	                    } else if (cell.getColumnIndex() == 2) {
	                        maLoi = dataFormatter.formatCellValue(cell).trim();
	                    } else if (cell.getColumnIndex() == 3) {
	                        thueBaoKh = dataFormatter.formatCellValue(cell).trim();
	                    } else if (cell.getColumnIndex() == 4) {
	                        maNv = dataFormatter.formatCellValue(cell).trim();
	                    } else if (cell.getColumnIndex() == 5) {
	                        soNgayTon = dataFormatter.formatCellValue(cell).trim();
	                    } else if (cell.getColumnIndex() == 6) {
	                        cuocPhatSinh = dataFormatter.formatCellValue(cell).trim();
	                    }
	                }

	                checkThang = checkTblDmThongTinLoiDayMayDTO(1, thang, orderErrorFormat);
	                checkMaLoi = checkTblDmThongTinLoiDayMayDTO(2, maLoi, orderErrorFormat);
	                checkThueBaoKh = checkTblDmThongTinLoiDayMayDTO(3, thueBaoKh, orderErrorFormat);
	                checkMaNv = checkTblDmThongTinLoiDayMayDTO(4, maNv, orderErrorFormat);
	                checkSoNgayTon = checkTblDmThongTinLoiDayMayDTO(5, soNgayTon, orderErrorFormat);
	                checkCuocPhatSinh = checkTblDmThongTinLoiDayMayDTO(6, cuocPhatSinh, orderErrorFormat);

	                if (checkThang && checkMaLoi && checkThueBaoKh && checkMaNv && checkSoNgayTon && checkCuocPhatSinh) {
	                	long delele = tblDmThongTinLoiDayMayDAO.deleteTblDmThongTinLoiDayMayByMaNvAndThangAndThueBaoKh(maNv, thang, thueBaoKh);
	                	TblDmThongTinLoiDayMayDTO lst = new TblDmThongTinLoiDayMayDTO();
	                	//lst = new TblPhatCdt();
	                	lst.setThang(thang);
	                    lst.setMaLoi(maLoi);
	                    lst.setThueBaoKh(thueBaoKh);
	                    lst.setMaNv(maNv.toUpperCase());
	                    //String cuocPhatSinhCuoi = cuocPhatSinh.replace(",", "");
	                    if (!soNgayTon.isEmpty()) {
	                        lst.setSoNgayTon(Float.parseFloat(soNgayTon));
	                    } else {
	                        lst.setSoNgayTon(0f);
	                    }
	                    if (!cuocPhatSinh.isEmpty()) {
	                        lst.setCuocPhatSinh(Float.parseFloat(cuocPhatSinh));
	                    } else {
	                        lst.setCuocPhatSinh(0f);
	                    }

	                    lst.setXoa(0l);
	                    lst.setHoatDong(1l);

						workLst.add(lst);
						//save(newObj); 
					} else {
						ImportErrDTO errObj = new ImportErrDTO();
						errObj.setColumn1(thang);
						errObj.setColumn2(maLoi);
						errObj.setColumn3(thueBaoKh);
						errObj.setColumn4(maNv);
						errObj.setColumn5(soNgayTon);
						errObj.setColumn6(cuocPhatSinh);

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
			if(workLst.size() > 0){
				saveList(workLst);
				long tEnd = System.currentTimeMillis();
				lsThaoTacDTO.setChucNang("Import dữ liệu đầu vào thông tin dây máy");
				lsThaoTacDTO.setMoTa("Import "+workLst.size() +" bản ghi dữ liệu đầu vào thông tin dây máy ");
				lsThaoTacDTO.setThoiGianThucHien(tEnd-tStart);
				businessImpl.insertLSTT(lsThaoTacDTO,request);
			}
				
			if (workFault.size() > 0) {
				//exportExcelError(workFault);
				//throw new IllegalArgumentException("Có lỗi trong file import");
				return exportExcelError(workFault);
			}
			//return (long) 1;
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
	public boolean checkTblDmThongTinLoiDayMayDTO(int index, String value, ErrExcelDTO err) {
		err = new ErrExcelDTO();
        if (index == 1) {
            if (value.isEmpty()) {
                err.setDetailError("Tháng không được để trống");
                lstErrExcelDto.add(err);
                return false;
            } else {
            	//kiem tra nam
                String checkthang="";
                checkthang=value.substring(value.lastIndexOf('/')+1, value.length());
                if (!ValidateUtils.isDate(value,fomat)) {
                    err.setDetailError("Tháng không đúng định dạng mm/yyyy");
                    lstErrExcelDto.add(err);
                    return false;
                }
                else if(checkthang.length()!=4)
                {
                	 err.setDetailError("Tháng không đúng định dạng mm/yyyy");
                     lstErrExcelDto.add(err);
                     return false;
                }
                else if(value.lastIndexOf('/')!=value.indexOf('/'))
                {
                	err.setDetailError("Tháng không đúng định dạng mm/yyyy");
                    lstErrExcelDto.add(err);
                    return false;
                }
                
            }
            

        } else if (index == 2) {
            if (value.isEmpty()) {
                err.setDetailError("Trường mã lỗi không được để trống");
                lstErrExcelDto.add(err);
                return false;
            }
            if (tblDmThongTinLoiDayMayDAO.checkExistMaLoiByMaLoi(value) == 0) {
                err.setDetailError("Trường mã lỗi không tồn tại trong danh mục lỗi");
                lstErrExcelDto.add(err);
                return false;
            }
        } else if (index == 3) {
            if (value.isEmpty()) {
                err.setDetailError("Trường thuê bao khách hàng không được để trống");
                lstErrExcelDto.add(err);
                return false;
            }
            if (value.length() > 100) {
                err.setDetailError("Trường thuê bao khách hàng vượt quá độ dài cho phép");
                lstErrExcelDto.add(err);
                return false;
            }
        } else if (index == 4) {
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
        }else if (index == 5) {
            if (!value.isEmpty()) {
                if (!ValidateUtils.isFloat(value)) {
                    err.setDetailError("Số ngày tồn không đúng định dạng");
                    lstErrExcelDto.add(err);
                    return false;
                }
                if (Float.parseFloat(value) < 0) {
                    err.setDetailError("Số ngày tồn phải là số thực không âm");
                    lstErrExcelDto.add(err);
                    return false;
                }
            }
        } else if (index == 6) {
            if (!value.isEmpty() && !ValidateUtils.isFloat(value)) {
                err.setDetailError("Trường cước phát sinh không đúng định dạng");
                lstErrExcelDto.add(err);
                return false;
            }
            if (!value.isEmpty() && Float.parseFloat(value) < 0) {
                err.setDetailError("Trường cước phát sinh phải là số thực không âm");
                lstErrExcelDto.add(err);
                return false;
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
			InputStream file = new BufferedInputStream(new FileInputStream(filePath + "NVDayMay-PhatKhac-err.xlsx"));

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

			File out = new File(
					folder2Upload + File.separatorChar + "NVDayMay-PhatKhac-err" + startExportTime + ".xlsx");

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
		return UEncrypt.encryptFileUploadPath("NVDayMay-PhatKhac-err" + startExportTime + ".xlsx");
	}
	public String downloadImportTemplate() throws Exception {
		// TODO Auto-generated method stub
		
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		String filePath = classloader.getResource("../" + "doc-template").getPath();
		InputStream file = new BufferedInputStream(new FileInputStream(filePath + "ExportThongTinLoiDayMay.xlsx"));
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		
		
		file.close();
		File out = new File(folder2Upload + File.separatorChar + "ExportThongTinLoiDayMay.xlsx");
		
		FileOutputStream outFile = new FileOutputStream(out);
		workbook.write(outFile);
		workbook.close();
		outFile.close();
		
		String path = UEncrypt.encryptFileUploadPath("ExportThongTinLoiDayMay.xlsx");
		return path;
	}
	@Override
	public String exportExcelGrid(TblDmThongTinLoiDayMayDTO lst) throws Exception {
		// TODO Auto-generated method stub
		List<TblDmThongTinLoiDayMayDTO> obj = tblDmThongTinLoiDayMayDAO.doSearch(lst);
		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream file = new BufferedInputStream(new FileInputStream(filePath + "ExportThongTinLoiDayMay.xlsx"));
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
				cell2.setCellValue(obj.get(i).getMaLoi());

				XSSFCell cell3 = row.createCell(3);
				cell3.setCellValue(obj.get(i).getThueBaoKh());

				XSSFCell cell4 = row.createCell(4);
				cell4.setCellValue(obj.get(i).getMaNv());

				XSSFCell cell5 = row.createCell(5);
				cell5.setCellValue(obj.get(i).getSoNgayTon());

				XSSFCell cell6 = row.createCell(6);
				cell6.setCellValue(obj.get(i).getCuocPhatSinh());

				cell0.setCellStyle(style);
				cell1.setCellStyle(style);
				cell2.setCellStyle(style);
				cell3.setCellStyle(style);
				cell4.setCellStyle(style);
				cell5.setCellStyle(style);
				cell6.setCellStyle(style);

			}
			file.close();

			File out = new File(folder2Upload + File.separatorChar + "ExportThongTinLoiDayMay" + startExportTime + ".xlsx");

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
		return UEncrypt.encryptFileUploadPath("ExportThongTinLoiDayMay" + startExportTime + ".xlsx");
	}
	
	
}
