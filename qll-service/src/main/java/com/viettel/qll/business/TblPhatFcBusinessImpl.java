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
import com.viettel.qll.bo.TblPhatFcBO;
import com.viettel.qll.dao.TblDanhMucDAO;
import com.viettel.qll.dao.TblNhanVienDAO;
import com.viettel.qll.dao.TblPhatFcDAO;
import com.viettel.qll.dto.ErrExcelDTO;
import com.viettel.qll.dto.ImportErrDTO;
import com.viettel.qll.dto.LsThaoTacDTO;
import com.viettel.qll.dto.TblPhatFcDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;
import com.viettel.utils.ValidateUtils;

@Service("tblPhatFcBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TblPhatFcBusinessImpl extends BaseFWBusinessImpl<TblPhatFcDAO, TblPhatFcDTO, TblPhatFcBO>
		implements TblPhatFcBusiness {
	List<ErrExcelDTO> lstErrExcelDto;
	TblNhanVienBusinessImpl tblNhanVienBusinessImpl;
	@Autowired
	private TblPhatFcDAO tblPhatFcDAO;
	
	LsThaoTacDTO lsThaoTacDTO = new LsThaoTacDTO();

	@Autowired
	TblQltsThucXuatTheoKyBusinessImpl businessImpl=new TblQltsThucXuatTheoKyBusinessImpl();
	
	@Autowired
	private TblDanhMucDAO tblDanhmucDao;
	private String fomat="MM/yyyy";
	@Autowired
	private TblNhanVienDAO tblNhanVienDAO;
	@Value("${folder_upload2}")
	private String folder2Upload;
	static Logger LOGGER = LoggerFactory.getLogger(TblPhatFcBusinessImpl.class);

	public TblPhatFcBusinessImpl() {
		tModel = new TblPhatFcBO();
		tDAO = tblPhatFcDAO;
	}

	@Override
	public TblPhatFcDAO gettDAO() {
		return tblPhatFcDAO;
	}

	@Override
	public DataListDTO doSearch(TblPhatFcDTO obj, HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		String err = "";

		List<TblPhatFcDTO> ls = tblPhatFcDAO.doSearch(obj);
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		data.setTotal(obj.getTotalRecord());
		data.setSize(obj.getTotalRecord());
		data.setStart(1);
		return data;
	}
	public long checkExistMaNvByMaNv(String maNv) {
		TblNhanVienDAO dao = new TblNhanVienDAO();
		return dao.checkExistMaNvByMaNv(maNv);
	}
	public long deletePhatFcByMaNvAndThang(String maNv, String thang) {
		return tblPhatFcDAO.deletePhatFcByMaNvAndThang(maNv, thang);
		// return 1l;
	}

	@Override
	public String importFile(String fileInput,HttpServletRequest request) throws Exception {
		long tStart = System.currentTimeMillis();
		List<TblPhatFcDTO> workLst = Lists.newArrayList();
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
					boolean checkThang = true;
					boolean checkMaNv = true;
					boolean checkHoVaTen = true;
					boolean checkQuanHuyen = true;
					boolean checkTinh = true;
					boolean checkMaTinh = true;
					boolean checkTsddql = true;
					boolean checkTsfccplt = true;
					boolean checkTbgptt = true;
					boolean checkSlfcdsdttcptmt = true;
					boolean checkSlfcptmvhmt = true;
					boolean checkSlfcttxlsct = true;
					boolean checkTslfclt = true;
					boolean checkSlfcgtddbh = true;
					boolean checkGttvbdpd = true;
					boolean checkSfcpt = true;
					boolean checkSoTienPhatTinhTheoAcc = true;

					String thang = "";
					String maNv = "";
					String hoVaTen = "";
					String quanHuyen = "";
					String tinh = "";
					String maTinh = "";
					String tsddql = "";
					String tsfccplt = "";
					String tbgptt = "";
					String slfcdsdttcptmt = "";
					String slfcptmvhmt = "";
					String slfcttxlsct = "";
					String tslfclt = "";
					String slfcgtddbh = "";
					String gttvbdpd = "";
					String sfcpt = "";
					String soTienPhatTinhTheoAcc = "";

					for (Cell cell : row) {
						if (cell.getColumnIndex() == 1) {
							thang = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 2) {
							maNv = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 3) {
							hoVaTen = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 4) {
							quanHuyen = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 5) {
							tinh = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 6) {
							maTinh = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 7) {
							tsddql = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 8) {
							tsfccplt = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 9) {
							tbgptt = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 10) {
							slfcdsdttcptmt = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 11) {
							slfcptmvhmt = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 12) {
							slfcttxlsct = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 13) {
							tslfclt = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 14) {
							slfcgtddbh = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 15) {
							gttvbdpd = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 16) {
							sfcpt = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 17) {
							soTienPhatTinhTheoAcc = dataFormatter.formatCellValue(cell).trim();
						}
					}

					checkThang = checkPhatFC(1, thang,"", orderErrorFormat);
					checkMaNv = checkPhatFC(2, maNv,"", orderErrorFormat);

					checkHoVaTen = checkPhatFC(3, hoVaTen,"", orderErrorFormat);
					checkQuanHuyen = checkPhatFC(4, quanHuyen,maTinh, orderErrorFormat);
					checkTinh = checkPhatFC(5, tinh,maTinh, orderErrorFormat);
					checkMaTinh = checkPhatFC(6, maTinh,"", orderErrorFormat);
					checkTsddql = checkPhatFC(7, tsddql,"", orderErrorFormat);
					checkTsfccplt = checkPhatFC(8, tsfccplt,"", orderErrorFormat);
					checkTbgptt = checkPhatFC(9, tbgptt,"", orderErrorFormat);
					checkSlfcdsdttcptmt = checkPhatFC(10, slfcdsdttcptmt,"", orderErrorFormat);
					checkSlfcptmvhmt = checkPhatFC(11, slfcptmvhmt,"", orderErrorFormat);
					checkSlfcttxlsct = checkPhatFC(12, slfcttxlsct,"", orderErrorFormat);
					checkTslfclt = checkPhatFC(13, tslfclt,"", orderErrorFormat);
					checkSlfcgtddbh = checkPhatFC(14, slfcgtddbh,"", orderErrorFormat);
					checkGttvbdpd = checkPhatFC(15, gttvbdpd,"", orderErrorFormat);
					checkSfcpt = checkPhatFC(16, sfcpt,"", orderErrorFormat);
					checkSoTienPhatTinhTheoAcc = checkPhatFC(17, soTienPhatTinhTheoAcc,"", orderErrorFormat);

					if (checkThang && checkMaNv && checkHoVaTen && checkQuanHuyen && checkTinh && checkMaTinh
							&& checkTsddql && checkTsfccplt && checkTbgptt && checkSlfcdsdttcptmt && checkSlfcptmvhmt
							&& checkSlfcttxlsct && checkTslfclt && checkSlfcgtddbh && checkGttvbdpd && checkSfcpt
							&& checkSoTienPhatTinhTheoAcc) {
						long delele = deletePhatFcByMaNvAndThang(maNv, thang);
						TblPhatFcDTO lst = new TblPhatFcDTO();
						lst.setThang(thang);
						lst.setMaNv(maNv.toUpperCase());
						lst.setHoVaTen(hoVaTen);
						lst.setQuanHuyen(quanHuyen);
						lst.setTinh(tinh);
						lst.setMaTinh(maTinh);
						if (!tsddql.isEmpty()) {
							lst.setTsddql(Float.parseFloat(tsddql));
						} else {
							lst.setTsddql(0f);
						}

						if (!tsfccplt.isEmpty()) {
							lst.setTsfccplt(Float.parseFloat(tsfccplt));
						} else {
							lst.setTsfccplt(0f);
						}

						if (!tbgptt.isEmpty()) {
							lst.setTbgptt(Float.parseFloat(tbgptt));
						} else {
							lst.setTbgptt(0f);
						}

						if (!slfcdsdttcptmt.isEmpty()) {
							lst.setSlfcdsdttcptmt(Float.parseFloat(slfcdsdttcptmt));
						} else {
							lst.setSlfcdsdttcptmt(0f);
						}

						if (!slfcptmvhmt.isEmpty()) {
							lst.setSlfcptmvhmt(Float.parseFloat(slfcptmvhmt));
						} else {
							lst.setSlfcptmvhmt(0f);
						}

						if (!slfcttxlsct.isEmpty()) {
							lst.setSlfcttxlsct(Float.parseFloat(slfcttxlsct));
						} else {
							lst.setSlfcttxlsct(0f);
						}

						if (!tslfclt.isEmpty()) {
							lst.setTslfclt(Float.parseFloat(tslfclt));
						} else {
							lst.setTslfclt(0f);
						}

						if (!slfcgtddbh.isEmpty()) {
							lst.setSlfcgtddbh(Float.parseFloat(slfcgtddbh));
						} else {
							lst.setSlfcgtddbh(0f);
						}

						if (!gttvbdpd.isEmpty()) {
							lst.setGttvbdpd(Float.parseFloat(gttvbdpd));
						} else {
							lst.setGttvbdpd(0f);
						}

						if (!sfcpt.isEmpty()) {
							lst.setSfcpt(Float.parseFloat(sfcpt));
						} else {
							lst.setSfcpt(0f);
						}

						// String stpttagCuoi =
						// soTienPhatTinhTheoAcc.replace(",", "");
						if (!soTienPhatTinhTheoAcc.isEmpty()) {
							lst.setStpttag(Float.parseFloat(soTienPhatTinhTheoAcc));
						} else {
							lst.setStpttag(0f);
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
						errObj.setColumn4(quanHuyen);
						errObj.setColumn5(tinh);
						errObj.setColumn6(maTinh);
						errObj.setColumn7(tsddql);
						errObj.setColumn8(tsfccplt);
						errObj.setColumn9(tbgptt);
						errObj.setColumn10(slfcdsdttcptmt);
						errObj.setColumn11(slfcptmvhmt);
						errObj.setColumn12(slfcttxlsct);
						errObj.setColumn13(tslfclt);
						errObj.setColumn14(slfcgtddbh);
						errObj.setColumn15(gttvbdpd);
						errObj.setColumn16(sfcpt);
						errObj.setColumn17(soTienPhatTinhTheoAcc);
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
					lsThaoTacDTO.setChucNang("Import dữ liệu phạt FC");
					lsThaoTacDTO.setMoTa("Import "+workLst.size()+"bản ghi dữ liệu phạt FC ");
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

	public boolean checkPhatFC(int index, String value,String value2, ErrExcelDTO orderErrorFormat) {
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
		} else if (index == 4) {
			if (!value.isEmpty()) {
				if (value.length() > 100) {
					orderErrorFormat.setDetailError("Quận/Huyện vượt quá độ dài cho phép");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				} else if(tblDanhmucDao.checkExisttenhuyenBytenhuyen(value,value2)!=1l)
				{
					orderErrorFormat.setDetailError("Tên Quận/Huyện không chính xác");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}
		} else if (index == 5) {
			if (!value.isEmpty()) {
				if (value.length() > 100) {
					orderErrorFormat.setDetailError("Tỉnh vượt quá độ dài cho phép");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
				else if(tblDanhmucDao.checkExistTinhBytenTinh(value,value2) !=1l)
				{
					orderErrorFormat.setDetailError("Tên tỉnh không chính xác");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}
		} else if (index == 6) {
			if (!value.isEmpty()) {				if (value.length() > 50) {
					orderErrorFormat.setDetailError("Mã tỉnh vượt quá độ dài cho phép");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			else if(tblDanhmucDao.checkExistmatinhBymatinh(value)==0l)
			{
				orderErrorFormat.setDetailError("Mã tỉnh Không đúng");
				lstErrExcelDto.add(orderErrorFormat);
				return false;
			}
			}
		} else if (index == 7) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					orderErrorFormat.setDetailError("Tổng số đường dây quản lý không đúng định dạng");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					orderErrorFormat.setDetailError("Tổng số đường dây quản lý phải là số thực không âm");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}
		} else if (index == 8) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					orderErrorFormat.setDetailError("Tổng Số FC cho phép lỗi tháng n-1 không đúng định dạng");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					orderErrorFormat.setDetailError("Tổng Số FC cho phép lỗi tháng n-1 phải là số thực không âm");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}
		} else if (index == 9) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					orderErrorFormat.setDetailError("Thuê bao Gline phát triển tháng n-1 không đúng định dạng");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					orderErrorFormat.setDetailError("Thuê bao Gline phát triển tháng n-1 phải là số thực không âm");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}
		} else if (index == 10) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					orderErrorFormat
							.setDetailError("Số lượng FC đã sử dụng thực tế cho phát triển mới  không đúng định dạng");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					orderErrorFormat.setDetailError(
							"Số lượng FC đã sử dụng thực tế cho phát triển mới  phải là số thực không âm");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}
		} else if (index == 11) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					orderErrorFormat
							.setDetailError("Số lượng FC phát triển mới vượt hạn mức tháng n-1 không đúng định dạng");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					orderErrorFormat.setDetailError(
							"Số lượng FC phát triển mới vượt hạn mức tháng n-1 phải là số thực không âm");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}
		} else if (index == 12) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					orderErrorFormat.setDetailError("Số lượng FC thực tế xử lý sự cố tháng n-1 không đúng định dạng");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					orderErrorFormat
							.setDetailError("Số lượng FC thực tế xử lý sự cố tháng n-1 phải là số thực không âm");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}
		} else if (index == 13) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					orderErrorFormat.setDetailError("Tổng số lượng FC lỗi tháng n-1 không đúng định dạng");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					orderErrorFormat.setDetailError("Tổng số lượng FC lỗi tháng n-1 phải là số thực không âm");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}
		} else if (index == 14) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					orderErrorFormat.setDetailError("Số lượng FC giảm trừ do được bảo hành không đúng định dạng");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					orderErrorFormat.setDetailError("Số lượng FC giảm trừ do được bảo hành phải là số thực không âm");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}
		} else if (index == 15) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					orderErrorFormat.setDetailError("Giảm trừ từ văn bản được phê duyệt không đúng định dạng");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					orderErrorFormat.setDetailError("Giảm trừ từ văn bản được phê duyệt phải là số thực không âm");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}
		} else if (index == 16) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					orderErrorFormat.setDetailError("Số FC phạt tháng n-1 không đúng định dạng");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					orderErrorFormat.setDetailError("Số FC phạt tháng n-1 phải là số thực không âm");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}
		} else if (index == 17) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					orderErrorFormat.setDetailError("Số tiền phạt tính theo Accout gline không đúng định dạng");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					orderErrorFormat.setDetailError("Số tiền phạt tính theo Accout gline phải là số thực không âm");
					lstErrExcelDto.add(orderErrorFormat);
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
			InputStream file = new BufferedInputStream(new FileInputStream(filePath + "NVDayMay-PhatFC-err.xlsx"));
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

				for (int j = 0; j < obj.get(i).getLstErrorOrder().size(); j++) {
					err.append(obj.get(i).getLstErrorOrder().get(j).getDetailError() + "-");
					// System.out.println("err" + err);
				}
				System.out.println("err " + i + ":" + err);

				XSSFCell cell18 = row.createCell(18);
				cell18.setCellValue(err.toString());

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
			}
			file.close();

			File out = new File(folder2Upload + File.separatorChar + "NVDayMay-PhatFC-err" + startExportTime + ".xlsx");

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
		return UEncrypt.encryptFileUploadPath("NVDayMay-PhatFC-err" + startExportTime + ".xlsx");
	}

	// export excel
	public String exportExcelGrid(TblPhatFcDTO lst) throws Exception {
		// TODO Auto-generated method stub
		List<TblPhatFcDTO> obj = tblPhatFcDAO.doSearch(lst);
		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream file = new BufferedInputStream(new FileInputStream(filePath + "ExportPhatFc.xlsx"));
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
				cell4.setCellValue(obj.get(i).getQuanHuyen());

				XSSFCell cell5 = row.createCell(5);
				cell5.setCellValue(obj.get(i).getTinh());

				XSSFCell cell6 = row.createCell(6);
				cell6.setCellValue(obj.get(i).getMaTinh());

				XSSFCell cell7 = row.createCell(7);
				cell7.setCellValue(obj.get(i).getTsddql());

				XSSFCell cell8 = row.createCell(8);
				cell8.setCellValue(obj.get(i).getTsfccplt());

				XSSFCell cell9 = row.createCell(9);
				cell9.setCellValue(obj.get(i).getTbgptt());

				XSSFCell cell10 = row.createCell(10);
				cell10.setCellValue(obj.get(i).getSlfcdsdttcptmt());

				XSSFCell cell11 = row.createCell(11);
				cell11.setCellValue(obj.get(i).getSlfcptmvhmt());

				XSSFCell cell12 = row.createCell(12);
				cell12.setCellValue(obj.get(i).getSlfcttxlsct());

				XSSFCell cell13 = row.createCell(13);
				cell13.setCellValue(obj.get(i).getTslfclt());

				XSSFCell cell14 = row.createCell(14);
				cell14.setCellValue(obj.get(i).getSlfcgtddbh());

				XSSFCell cell15 = row.createCell(15);
				cell15.setCellValue(obj.get(i).getGttvbdpd());

				XSSFCell cell16 = row.createCell(16);
				cell16.setCellValue(obj.get(i).getSfcpt());

				XSSFCell cell17 = row.createCell(17);
				cell17.setCellValue(obj.get(i).getStpttag());

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

			}
			file.close();

			File out = new File(folder2Upload + File.separatorChar + "ExportPhatFc" + startExportTime + ".xlsx");

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
		return UEncrypt.encryptFileUploadPath("ExportPhatFc" + startExportTime + ".xlsx");
	}

	@Override
	public String downloadImportTemplate() throws Exception {
		// TODO Auto-generated method stub
		
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		String filePath = classloader.getResource("../" + "doc-template").getPath();
		InputStream file = new BufferedInputStream(new FileInputStream(filePath + "5-NVDayMay-phatFC.xlsx"));
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		
		
		file.close();
		File out = new File(folder2Upload + File.separatorChar + "NVDayMay-phatFC.xlsx");
		
		FileOutputStream outFile = new FileOutputStream(out);
		workbook.write(outFile);
		workbook.close();
		outFile.close();
		
		String path = UEncrypt.encryptFileUploadPath("NVDayMay-phatFC.xlsx");
		return path;
	}

}
