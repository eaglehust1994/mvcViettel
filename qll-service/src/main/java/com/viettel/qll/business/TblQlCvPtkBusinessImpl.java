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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
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
import com.viettel.qll.bo.TblQlCvPtkBO;
import com.viettel.qll.dao.TblGanNhiemVuDAO;
import com.viettel.qll.dao.TblQlCvPtkDAO;
import com.viettel.qll.dto.ErrExcelDTO;
import com.viettel.qll.dto.ImportErrDTO;
import com.viettel.qll.dto.KqHdTkDTO;
import com.viettel.qll.dto.TblGanNhiemVuDTO;
import com.viettel.qll.dto.TblKhQlvcDTO;
import com.viettel.qll.dto.TblQlCvPtkDTO;
import com.viettel.qll.dto.VpsUserToken;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;
import com.viettel.utils.ValidateUtils;

@Service("tblQlCvPtkBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TblQlCvPtkBusinessImpl extends BaseFWBusinessImpl<TblQlCvPtkDAO, TblQlCvPtkDTO, TblQlCvPtkBO>
		implements TblQlCvPtkBusiness {
	private final static String MISSION1 = "Đã nhận tại PTK";
	private final static String MISSION2 = "Đã nhận QTK";
	private final static String MISSION3 = "Đã thẩm định QTK";
	private final static String MISSION4 = "Đã lập đề nghị";
	private final static String MISSION5 = "Đã chốt với CDT";
	private final static String STATUS_SUCCESS = "Hoàn thành";
	private final static String STATUS_FAIL = "Quá hạn";
	
	@Autowired
	private TblQlCvPtkDAO tblQlCvPtkDAO;
	@Autowired
	private TblGanNhiemVuDAO tblGanNhiemVuDAO;
	List<ErrExcelDTO> lstErrExcelDto;
	@Value("${folder_upload2}")
	private String folder2Upload;
	protected final Logger log = Logger.getLogger(TblQlCvPtkBusinessImpl.class);

	public TblQlCvPtkBusinessImpl() {
		tModel = new TblQlCvPtkBO();
		tDAO = tblQlCvPtkDAO;
	}

	DateFormat dt = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public TblQlCvPtkDAO gettDAO() {
		return tblQlCvPtkDAO;
	}

	// Xoa 1 ban ghi

	public long deleteObj(TblQlCvPtkDTO obj) {
		try {
			tblQlCvPtkDAO.delete(obj.toModel());
			return 1l;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0l;
	}

	@Override
	public long deleteListObj(TblQlCvPtkDTO obj) {
		List<TblQlCvPtkDTO> lst = tblQlCvPtkDAO.doSearch(obj);
		List<Long> lstId = new ArrayList<>();
		int size = lst.size();
		int count = 0;
		int groupBatch = 0;
		try {
			for (TblQlCvPtkDTO obj1 : lst) {
				count++;
				groupBatch++;
				lstId.add(obj1.getTblQlCvPtkId());
				if (groupBatch == 999) {
					groupBatch = 0;
					tblQlCvPtkDAO.deleteList(lstId);
					lstId.clear();
				}
				if (groupBatch < 999 && count == size) {

					tblQlCvPtkDAO.deleteList(lstId);

				}
			}
			return 1l;
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return 0l;
	}

	@Override

	public DataListDTO listTasks(TblQlCvPtkDTO obj) throws Exception {
		List<TblQlCvPtkDTO> ls = tblQlCvPtkDAO.listTasks(obj);
		for(int i=0;i<ls.size();i++){
			TblGanNhiemVuDTO objSearch = new TblGanNhiemVuDTO();
			objSearch.setIdQlCvPtk(ls.get(i).getTblQlCvPtkId());
			
			List<TblGanNhiemVuDTO> listNhanVien = tblGanNhiemVuDAO.doSearch(objSearch);
			if(listNhanVien.size()>0){
				for(int j =0;j<listNhanVien.size();j++){
					
					if(listNhanVien.get(j).getTenNhiemVu().equals("Đã nhận tại PTK")){
						ls.get(i).setNhanVien1(listNhanVien.get(j).getFullname());
					}else if(ls.get(i).getNhanVien1()==null){
						ls.get(i).setNhanVien1("");
					}
					
					if(listNhanVien.get(j).getTenNhiemVu().equals("Đã nhận QTK")){
						ls.get(i).setNhanVien2(listNhanVien.get(j).getFullname());
					}else if(ls.get(i).getNhanVien2()==null){
						ls.get(i).setNhanVien2("");
					}
					
					if(listNhanVien.get(j).getTenNhiemVu().equals("Đã thẩm định QTK")){
						ls.get(i).setNhanVien3(listNhanVien.get(j).getFullname());
					}else if(ls.get(i).getNhanVien3()==null){
						ls.get(i).setNhanVien3("");
					}
					
					if(listNhanVien.get(j).getTenNhiemVu().equals("Đã lập đề nghị")){
						ls.get(i).setNhanVien4(listNhanVien.get(j).getFullname());
					}else if(ls.get(i).getNhanVien4()==null){
						ls.get(i).setNhanVien4("");
					}
					
					if(listNhanVien.get(j).getTenNhiemVu().equals("Đã chốt với CDT")){
						ls.get(i).setNhanVien5(listNhanVien.get(j).getFullname());
					}else if(ls.get(i).getNhanVien5()==null){
						ls.get(i).setNhanVien5("");
					}
				}
			}else{
				ls.get(i).setNhanVien1("");
				ls.get(i).setNhanVien2("");
				ls.get(i).setNhanVien3("");
				ls.get(i).setNhanVien4("");
				ls.get(i).setNhanVien5("");
			}
		}
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		data.setTotal(obj.getTotalRecord());
		data.setSize(obj.getTotalRecord());
		data.setStart(1);
		return data;
	}
	@Override

	public DataListDTO doSearch(TblQlCvPtkDTO obj) throws Exception {
		List<TblQlCvPtkDTO> ls = tblQlCvPtkDAO.doSearch(obj);
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		data.setTotal(obj.getTotalRecord());
		data.setSize(obj.getTotalRecord());
		data.setStart(1);
		return data;
	}
	@Override
	public long saveAddCv(TblQlCvPtkDTO obj) {
		try {
			long ids = tblQlCvPtkDAO.saveObject(obj.toModel());
			return ids;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0l;
	}

	@Override
	public long updateStatus(TblQlCvPtkDTO obj) throws Exception {
		try {

			Long ids = tblQlCvPtkDAO.updateStatus(obj);
			return ids;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 1l;
	}

	@Override
	@Transactional
	public long updatePath(TblQlCvPtkDTO obj) throws Exception {
		try {
			long ids = tblQlCvPtkDAO.updatePath(obj);
			return ids;
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Có lỗi khi upload ảnh!!!");
		}

	}
	@Override
	@Transactional
	public long updateCV1(TblQlCvPtkDTO obj) throws Exception {
		try {
			long ids = tblQlCvPtkDAO.updateCV1(obj);
			return ids;
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Có lỗi khi upload ảnh!!!");
		}

	}
	@Override
	@Transactional
	public long updateCV2(TblQlCvPtkDTO obj) throws Exception {
		try {
			long ids = tblQlCvPtkDAO.updateCV2(obj);
			return ids;
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Có lỗi khi upload ảnh!!!");
		}

	}
	@Override
	@Transactional
	public long updateCV3(TblQlCvPtkDTO obj) throws Exception {
		try {
			long ids = tblQlCvPtkDAO.updateCV3(obj);
			return ids;
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Có lỗi khi upload ảnh!!!");
		}

	}
	@Override
	@Transactional
	public long updateCV4(TblQlCvPtkDTO obj) throws Exception {
		try {
			long ids = tblQlCvPtkDAO.updateCV4(obj);
			return ids;
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Có lỗi khi upload ảnh!!!");
		}

	}
	@Override
	@Transactional
	public long updateCV5(TblQlCvPtkDTO obj) throws Exception {
		try {
			long ids = tblQlCvPtkDAO.updateCV5(obj);
			return ids;
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Có lỗi khi upload ảnh!!!");
		}

	}
	@Override
	public long updateCvPtk(TblQlCvPtkDTO obj) throws Exception {
		try {
		
			Long ids = tblQlCvPtkDAO.updateObject(obj.toModel());
			return ids;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 1l;
	}
	@Override
	public List<TblQlCvPtkDTO> getForAutoCompleteSHD(TblQlCvPtkDTO obj) {
		// TODO Auto-generated method stub
		return tblQlCvPtkDAO.getForAutoCompleteSHD(obj);
	}
	public String importUpdateCV(String fileInputCV, HttpServletRequest request) throws Exception {

		TblGanNhiemVuDTO objSearch = new TblGanNhiemVuDTO();
		List<TblGanNhiemVuDTO> lstMiss = tblGanNhiemVuDAO.doSearch(objSearch);
		List<ImportErrDTO> workFault = Lists.newArrayList();
		VpsUserToken token = (VpsUserToken) request.getSession().getAttribute("vpsUserToken");
		Date date = new Date();
		try {
			String Ten = "";
			File f = new File(fileInputCV);

			XSSFWorkbook workbook = new XSSFWorkbook(f);
			XSSFSheet sheet = workbook.getSheetAt(0);

			DataFormatter dataFormatter = new DataFormatter();
			int count = 0;

			for (Row row : sheet) {
				count++;
				ErrExcelDTO orderErrorFormat = new ErrExcelDTO();
				if (count >= 4 && !isRowEmpty(row)) {
					lstErrExcelDto = new ArrayList<>();

					boolean checkNgayGuiHshc = true;
					boolean checkNgayNhanHshc = true;
					// giá trị theo sản lượng hạ tầng tạm tính
					boolean checkGtSlHtTtTong = true;
					boolean checkGtSlHtTtXd = true;
					boolean checkGtSlHtTtDien = true;
					boolean checkGtSlHtTtLapDung = true;
					boolean checkGtSlHtTtLapBts = true;
					boolean checkGtSlHtTtKhac = true;
					// giá trị đề nghị Qtk của cn
					boolean checkGtDnQtkCnNgay = true;
					boolean checkGtDnQtkCnTong = true;
					boolean checkGtDnQtkCnXd = true;
					boolean checkGtDnQtkCnDien = true;
					boolean checkGtDnQtkCnLapDung = true;
					boolean checkGtDnQtkCnLapBts = true;
					boolean checkGtDnQtkCnKhac = true;
					// giá trị QTK chốt PTk
					boolean checkGtQtkPtkNgay = true;
					boolean checkGtQtkPtkTong = true;
					boolean checkGtQtkPtkXd = true;
					boolean checkGtQtkPtkDien = true;
					boolean checkGtQtkPtkLapDung = true;
					boolean checkGtQtkPtkLapBts = true;
					boolean checkGtQtkPtkKhac = true;
					// quyết toán đối tác
					boolean checkQtDtNgay = true;
					boolean checkQtDtGtDnDt = true;
					boolean checkQtDtNgayTdDt = true;
					boolean checkQtDtTong = true;
					boolean checkQtDtXd = true;
					boolean checkQtDtDien = true;
					boolean checkQtDtLapDung = true;
					boolean checkQtDtLapBts = true;
					boolean checkQtDtKhac = true;
					// đề nghị quyết toán cdt
					boolean checkDnQtCdtNgay = true;
					boolean checkDnQtCdtGt = true;
					// thẩm đinh quyết toán cdt
					boolean checkTdQtCdtNgayChot = true;
					boolean checkTdQtCdtGt = true;
					boolean checkTdQtCdtNgayBanTd = true;
					boolean checkTdQtCdtNgayCtc = true;

					String cnkv = "";
					String tinh = "";
					String mttMaViTri = "";
					String mttMa2g = "";
					String mttMa3g = "";
					String mttMaXuatKho = "";
					String soHdCdt = "";
					String loaiCt = "";
					String noiDung = "";
					String ngayGuiHshc = "";
					String soBill = "";
					String ngayNhanHshc = "";
					String ghiChu = "";
					// giá trị theo sản lượng hạ tầng tạm tính
					String gtSlHtTtTong = "";
					String gtSlHtTtXd = "";
					String gtSlHtTtDien = "";
					String gtSlHtTtLapDung = "";
					String gtSlHtTtLapBts = "";
					String gtSlHtTtKhac = "";
					// giá trị đề nghị Qtk của cn
					String gtDnQtkCnNgay = "";
					String gtDnQtkCnTong = "";
					String gtDnQtkCnXd = "";
					String gtDnQtkCnDien = "";
					String gtDnQtkCnLapDung = "";
					String gtDnQtkCnLapBts = "";
					String gtDnQtkCnKhac = "";
					String gtDnQtkCnNguoiLap = "";
					// giá trị QTK chốt PTk
					String gtQtkPtkNgay = "";
					String gtQtkPtkTong = "";
					String gtQtkPtkXd = "";
					String gtQtkPtkDien = "";
					String gtQtkPtkLapDung = "";
					String gtQtkPtkLapBts = "";
					String gtQtkPtkKhac = "";
					String gtQtkPtkNguoiChot = "";
					String gtQtkPtkDmtt = "";
					String gtQtkPtkThangQtk = "";
					String tinhTrangCct1 = "";
					String ghiChuHsLoi = "";
					// quyết toán đối tác
					String qtDtNgay = "";
					String qtDtGtDnDt = "";
					String qtDtSoHd = "";
					String qtDtNgayTdDt = "";
					String qtDtTong = "";
					String qtDtXd = "";
					String qtDtDien = "";
					String qtDtLapDung = "";
					String qtDtLapBts = "";
					String qtDtKhac = "";
					String qtDtTtDt = "";
					String qtDtNguoiTd = "";
					// đề nghị quyết toán cdt
					String dnQtCdtNgay = "";
					String dnQtCdtGt = "";
					String dnQtCdtNguoiLap = "";
					String dnQtCdtNguoiNhanBg = "";
					String dnQtCdtNoiDungPsCtk = "";
					String dnQtCdtKtk = "";
					// thẩm đinh quyết toán cdt
					String tdQtCdtNgayChot = "";
					String tdQtCdtGt = "";
					String tdQtCdtNguoiChotMvt = "";
					String tdQtCdtNguoiTdCdtMvt = "";
					String tdQtCdtNgayBanTd = "";
					String tdQtCdtNgayCtc = "";
					String tdQtCdtNoiDung = "";
					String tinhTrangCct2 = "";
					String ghiChu2 = "";
					String luuTaiKho = "";
					String trangThai = "";
					String tblQlCvPtkId = "";

					for (Cell cell : row) {
						if (cell.getColumnIndex() == 1) {
							cnkv = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 2) {
							tinh = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 3) {
							mttMaViTri = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 4) {
							mttMa2g = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 5) {
							mttMa3g = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 6) {
							mttMaXuatKho = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 7) {
							soHdCdt = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 8) {
							loaiCt = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 9) {
							noiDung = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 10) {
							ngayGuiHshc = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 11) {
							soBill = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 12) {
							ngayNhanHshc = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 13) {
							ghiChu = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 14) {
							gtSlHtTtTong = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 15) {
							gtSlHtTtXd = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 16) {
							gtSlHtTtDien = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 17) {
							gtSlHtTtLapDung = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 18) {
							gtSlHtTtLapBts = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 19) {
							gtSlHtTtKhac = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 20) {
							gtDnQtkCnNgay = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 21) {
							gtDnQtkCnTong = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 22) {
							gtDnQtkCnXd = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 23) {
							gtDnQtkCnDien = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 24) {
							gtDnQtkCnLapDung = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 25) {
							gtDnQtkCnLapBts = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 26) {
							gtDnQtkCnKhac = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 27) {
							gtDnQtkCnNguoiLap = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 28) {
							gtQtkPtkNgay = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 29) {
							gtQtkPtkTong = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 30) {
							gtQtkPtkXd = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 31) {
							gtQtkPtkDien = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 32) {
							gtQtkPtkLapDung = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 33) {
							gtQtkPtkLapBts = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 34) {
							gtQtkPtkKhac = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 35) {
							gtQtkPtkNguoiChot = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 36) {
							gtQtkPtkDmtt = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 37) {
							gtQtkPtkThangQtk = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 38) {
							tinhTrangCct1 = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 39) {
							ghiChuHsLoi = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 40) {
							qtDtNgay = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 41) {
							qtDtGtDnDt = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 42) {
							qtDtSoHd = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 43) {
							qtDtNgayTdDt = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 44) {
							qtDtTong = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 45) {
							qtDtXd = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 46) {
							qtDtDien = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 47) {
							qtDtLapDung = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 48) {
							qtDtLapBts = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 49) {
							qtDtKhac = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 50) {
							qtDtTtDt = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 51) {
							qtDtNguoiTd = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 52) {
							dnQtCdtNgay = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 53) {
							dnQtCdtGt = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 54) {
							dnQtCdtNguoiLap = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 55) {
							dnQtCdtNguoiNhanBg = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 56) {
							dnQtCdtNoiDungPsCtk = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 57) {
							dnQtCdtKtk = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 58) {
							tdQtCdtNgayChot = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 59) {
							tdQtCdtGt = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 60) {
							tdQtCdtNguoiChotMvt = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 61) {
							tdQtCdtNguoiTdCdtMvt = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 62) {
							tdQtCdtNgayBanTd = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 63) {
							tdQtCdtNgayCtc = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 64) {
							tdQtCdtNoiDung = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 65) {
							tinhTrangCct2 = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 66) {
							ghiChu2 = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 67) {
							luuTaiKho = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 68) {
							trangThai = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 69) {
							tblQlCvPtkId = dataFormatter.formatCellValue(cell);
						}

					}

					checkNgayGuiHshc = checkDataFromFileExel(ngayGuiHshc.trim(), 10, orderErrorFormat);
					checkNgayNhanHshc = checkDataFromFileExel(ngayNhanHshc.trim(), 12, orderErrorFormat);

					checkGtSlHtTtTong = checkDataFromFileExel(gtSlHtTtTong.trim(), 14, orderErrorFormat);
					checkGtSlHtTtXd = checkDataFromFileExel(gtSlHtTtXd.trim(), 15, orderErrorFormat);
					checkGtSlHtTtDien = checkDataFromFileExel(gtSlHtTtDien.trim(), 16, orderErrorFormat);
					checkGtSlHtTtLapDung = checkDataFromFileExel(gtSlHtTtLapDung.trim(), 17, orderErrorFormat);
					checkGtSlHtTtLapBts = checkDataFromFileExel(gtSlHtTtLapBts.trim(), 18, orderErrorFormat);
					checkGtSlHtTtKhac = checkDataFromFileExel(gtSlHtTtKhac.trim(), 19, orderErrorFormat);

					checkGtDnQtkCnNgay = checkDataFromFileExel(gtDnQtkCnNgay.trim(), 20, orderErrorFormat);
					checkGtDnQtkCnTong = checkDataFromFileExel(gtDnQtkCnTong.trim(), 21, orderErrorFormat);
					checkGtDnQtkCnXd = checkDataFromFileExel(gtDnQtkCnXd.trim(), 22, orderErrorFormat);
					checkGtDnQtkCnDien = checkDataFromFileExel(gtDnQtkCnDien.trim(), 23, orderErrorFormat);
					checkGtDnQtkCnLapDung = checkDataFromFileExel(gtDnQtkCnLapDung.trim(), 24, orderErrorFormat);
					checkGtDnQtkCnLapBts = checkDataFromFileExel(gtDnQtkCnLapBts.trim(), 25, orderErrorFormat);
					checkGtDnQtkCnKhac = checkDataFromFileExel(gtDnQtkCnKhac.trim(), 26, orderErrorFormat);
					// giá trị QTK chốt PTk
					checkGtQtkPtkNgay = checkDataFromFileExel(gtQtkPtkNgay.trim(), 28, orderErrorFormat);
					checkGtQtkPtkTong = checkDataFromFileExel(gtQtkPtkTong.trim(), 29, orderErrorFormat);
					checkGtQtkPtkXd = checkDataFromFileExel(gtQtkPtkXd.trim(), 30, orderErrorFormat);
					checkGtQtkPtkDien = checkDataFromFileExel(gtQtkPtkDien.trim(), 31, orderErrorFormat);
					checkGtQtkPtkLapDung = checkDataFromFileExel(gtQtkPtkLapDung.trim(), 32, orderErrorFormat);
					checkGtQtkPtkLapBts = checkDataFromFileExel(gtQtkPtkLapBts.trim(), 33, orderErrorFormat);
					checkGtQtkPtkKhac = checkDataFromFileExel(gtQtkPtkKhac.trim(), 34, orderErrorFormat);
					// quyết toán đối tác
					checkQtDtNgay = checkDataFromFileExel(qtDtNgay.trim(), 40, orderErrorFormat);
					checkQtDtGtDnDt = checkDataFromFileExel(qtDtGtDnDt.trim(), 41, orderErrorFormat);
					checkQtDtNgayTdDt = checkDataFromFileExel(qtDtNgayTdDt.trim(), 43, orderErrorFormat);
					checkQtDtTong = checkDataFromFileExel(qtDtTong.trim(), 44, orderErrorFormat);
					checkQtDtXd = checkDataFromFileExel(qtDtXd.trim(), 45, orderErrorFormat);
					checkQtDtDien = checkDataFromFileExel(qtDtDien.trim(), 46, orderErrorFormat);
					checkQtDtLapDung = checkDataFromFileExel(qtDtLapDung.trim(), 47, orderErrorFormat);
					checkQtDtLapBts = checkDataFromFileExel(qtDtLapBts.trim(), 48, orderErrorFormat);
					checkQtDtKhac = checkDataFromFileExel(qtDtKhac.trim(), 49, orderErrorFormat);
					// đề nghị quyết toán cdt
					checkDnQtCdtNgay = checkDataFromFileExel(dnQtCdtNgay.trim(), 52, orderErrorFormat);
					checkDnQtCdtGt = checkDataFromFileExel(dnQtCdtGt.trim(), 53, orderErrorFormat);
					// thẩm đinh quyết toán cdt
 					checkTdQtCdtNgayChot = checkDataFromFileExel(tdQtCdtNgayChot.trim(), 58, orderErrorFormat);
					checkTdQtCdtGt = checkDataFromFileExel(tdQtCdtGt.trim(), 59, orderErrorFormat);
					checkTdQtCdtNgayBanTd = checkDataFromFileExel(tdQtCdtNgayBanTd.trim(), 62, orderErrorFormat);
					checkTdQtCdtNgayCtc = checkDataFromFileExel(tdQtCdtNgayCtc.trim(), 63, orderErrorFormat);

					if (checkNgayGuiHshc && checkNgayNhanHshc && checkGtSlHtTtTong && checkGtSlHtTtXd
							&& checkGtSlHtTtDien && checkGtSlHtTtLapDung && checkGtSlHtTtLapBts && checkGtSlHtTtKhac
							&& checkGtDnQtkCnNgay && checkGtDnQtkCnTong && checkGtDnQtkCnXd && checkGtDnQtkCnDien
							&& checkGtDnQtkCnLapDung && checkGtDnQtkCnLapBts && checkGtDnQtkCnKhac && checkGtQtkPtkNgay
							&& checkGtQtkPtkTong && checkGtQtkPtkXd && checkGtQtkPtkDien && checkGtQtkPtkLapDung
							&& checkGtQtkPtkLapBts && checkGtQtkPtkKhac && checkQtDtNgay && checkQtDtGtDnDt
							&& checkQtDtNgayTdDt && checkQtDtTong && checkQtDtXd && checkQtDtDien && checkQtDtLapDung
							&& checkQtDtLapBts && checkQtDtKhac && checkDnQtCdtNgay && checkDnQtCdtGt
							&& checkTdQtCdtNgayChot && checkTdQtCdtGt && checkTdQtCdtNgayBanTd && checkTdQtCdtNgayCtc) {

						TblQlCvPtkDTO updateObj = new TblQlCvPtkDTO();
						tblQlCvPtkId = convertValue(tblQlCvPtkId);
						updateObj.setTblQlCvPtkId(Long.parseLong(tblQlCvPtkId));
						updateObj.setCnkv(cnkv);
						updateObj.setTinh(tinh);
						updateObj.setMttMaViTri(mttMaViTri);
						updateObj.setMttMa2g(mttMa2g);
						updateObj.setMttMa3g(mttMa3g);
						updateObj.setMttMaXuatKho(mttMaXuatKho);
						updateObj.setSoHdCdt(soHdCdt);
						updateObj.setLoaiCt(loaiCt);
						updateObj.setNoiDung(noiDung);
						if (StringUtils.isNotEmpty(ngayGuiHshc)) {
							updateObj.setNgayGuiHshc(dt.parse(ngayGuiHshc));
						}
						updateObj.setSoBill(soBill);
						if (StringUtils.isNotEmpty(ngayNhanHshc)) {
							updateObj.setNgayNhanHshc(dt.parse(ngayNhanHshc));
						}
						updateObj.setGhiChu(ghiChu);
						// giá trị theo sản lượng hạ tầng tạm tính

						if (!StringUtils.isEmpty(gtSlHtTtTong)) {
							gtSlHtTtTong = convertValue(gtSlHtTtTong);
							updateObj.setGtSlHtTtTong(Float.parseFloat(gtSlHtTtTong));
						} else {
							updateObj.setGtSlHtTtTong((float) 0);
						}

						if (!StringUtils.isEmpty(gtSlHtTtXd)) {
							gtSlHtTtXd = convertValue(gtSlHtTtXd);
							updateObj.setGtSlHtTtXd(Float.parseFloat(gtSlHtTtXd));
						} else {
							updateObj.setGtSlHtTtXd((float) 0);
						}

						if (!StringUtils.isEmpty(gtSlHtTtDien)) {
							gtSlHtTtDien = convertValue(gtSlHtTtDien);
							updateObj.setGtSlHtTtDien(Float.parseFloat(gtSlHtTtDien));
						} else {
							updateObj.setGtSlHtTtDien((float) 0);
						}

						if (!StringUtils.isEmpty(gtSlHtTtLapDung)) {
							gtSlHtTtLapDung = convertValue(gtSlHtTtLapDung);
							updateObj.setGtSlHtTtLapDung(Float.parseFloat(gtSlHtTtLapDung));
						} else {
							updateObj.setGtSlHtTtLapDung((float) 0);
						}

						if (!StringUtils.isEmpty(gtSlHtTtLapBts)) {
							gtSlHtTtLapBts = convertValue(gtSlHtTtLapBts);
							updateObj.setGtSlHtTtLapBts(Float.parseFloat(gtSlHtTtLapBts));
						} else {
							updateObj.setGtSlHtTtLapBts((float) 0);
						}

						if (!StringUtils.isEmpty(gtSlHtTtKhac)) {
							gtSlHtTtKhac = convertValue(gtSlHtTtKhac);
							updateObj.setGtSlHtTtKhac(Float.parseFloat(gtSlHtTtKhac));
						} else {
							updateObj.setGtSlHtTtKhac((float) 0);
						}

						//// giá trị đề nghị Qtk của cn

						if (StringUtils.isNotEmpty(gtDnQtkCnNgay)) {
							updateObj.setGtDnQtkCnNgay(dt.parse(gtDnQtkCnNgay));
						}

						if (!StringUtils.isEmpty(gtDnQtkCnTong)) {
							gtDnQtkCnTong = convertValue(gtDnQtkCnTong);
							updateObj.setGtDnQtkCnTong(Float.parseFloat(gtDnQtkCnTong));
						} else {
							updateObj.setGtDnQtkCnTong((float) 0);
						}

						if (!StringUtils.isEmpty(gtDnQtkCnXd)) {
							gtDnQtkCnXd = convertValue(gtDnQtkCnXd);
							updateObj.setGtDnQtkCnXd(Float.parseFloat(gtDnQtkCnXd));
						} else {
							updateObj.setGtDnQtkCnXd((float) 0);
						}

						if (!StringUtils.isEmpty(gtDnQtkCnDien)) {
							gtDnQtkCnDien = convertValue(gtDnQtkCnDien);
							updateObj.setGtDnQtkCnDien(Float.parseFloat(gtDnQtkCnDien));
						} else {
							updateObj.setGtDnQtkCnDien((float) 0);
						}

						if (!StringUtils.isEmpty(gtDnQtkCnLapDung)) {
							gtDnQtkCnLapDung = convertValue(gtDnQtkCnLapDung);
							updateObj.setGtDnQtkCnLapDung(Float.parseFloat(gtDnQtkCnLapDung));
						} else {
							updateObj.setGtDnQtkCnLapDung((float) 0);
						}

						if (!StringUtils.isEmpty(gtDnQtkCnLapBts)) {
							gtDnQtkCnLapBts = convertValue(gtDnQtkCnLapBts);
							updateObj.setGtDnQtkCnLapBts(Float.parseFloat(gtDnQtkCnLapBts));
						} else {
							updateObj.setGtDnQtkCnLapBts((float) 0);
						}

						if (!StringUtils.isEmpty(gtDnQtkCnKhac)) {
							gtDnQtkCnKhac = convertValue(gtDnQtkCnKhac);
							updateObj.setGtDnQtkCnKhac(Float.parseFloat(gtDnQtkCnKhac));
						} else {
							updateObj.setGtDnQtkCnKhac((float) 0);
						}
						updateObj.setGtDnQtkCnNguoiLap(gtDnQtkCnNguoiLap);
						// giá trị QTK chốt PTk

						if (StringUtils.isNotEmpty(gtQtkPtkNgay)) {
							updateObj.setGtQtkPtkNgay(dt.parse(gtQtkPtkNgay));
						}

						if (!StringUtils.isEmpty(gtQtkPtkTong)) {
							gtQtkPtkTong = convertValue(gtQtkPtkTong);
							updateObj.setGtQtkPtkTong(Float.parseFloat(gtQtkPtkTong));
						} else {
							updateObj.setGtQtkPtkTong((float) 0);
						}

						if (!StringUtils.isEmpty(gtQtkPtkXd)) {
							gtQtkPtkXd = convertValue(gtQtkPtkXd);
							updateObj.setGtQtkPtkXd(Float.parseFloat(gtQtkPtkXd));
						} else {
							updateObj.setGtQtkPtkXd((float) 0);
						}

						if (!StringUtils.isEmpty(gtQtkPtkDien)) {
							gtQtkPtkDien = convertValue(gtQtkPtkDien);
							updateObj.setGtQtkPtkDien(Float.parseFloat(gtQtkPtkDien));
						} else {
							updateObj.setGtQtkPtkDien((float) 0);
						}

						if (!StringUtils.isEmpty(gtQtkPtkLapDung)) {
							gtQtkPtkLapDung = convertValue(gtQtkPtkLapDung);
							updateObj.setGtQtkPtkLapDung(Float.parseFloat(gtQtkPtkLapDung));
						} else {
							updateObj.setGtQtkPtkLapDung((float) 0);
						}

						if (!StringUtils.isEmpty(gtQtkPtkLapBts)) {
							gtQtkPtkLapBts = convertValue(gtQtkPtkLapBts);
							updateObj.setGtQtkPtkLapBts(Float.parseFloat(gtQtkPtkLapBts));
						} else {
							updateObj.setGtQtkPtkLapBts((float) 0);
						}

						if (!StringUtils.isEmpty(gtQtkPtkKhac)) {
							gtQtkPtkKhac = convertValue(gtQtkPtkKhac);
							updateObj.setGtQtkPtkKhac(Float.parseFloat(gtQtkPtkKhac));
						} else {
							updateObj.setGtQtkPtkKhac((float) 0);
						}

						updateObj.setGtQtkPtkNguoiChot(gtQtkPtkNguoiChot);
						updateObj.setGtQtkPtkDmtt(gtQtkPtkDmtt);
						updateObj.setGtQtkPtkThangQtk(gtQtkPtkThangQtk);
						updateObj.setTinhTrangCct1(tinhTrangCct1);
						updateObj.setGhiChuHsLoi(ghiChuHsLoi);

						// quyết toán đối tác

						if (StringUtils.isNotEmpty(qtDtNgay)) {
							updateObj.setQtDtNgay(dt.parse(qtDtNgay));
						}

						if (!StringUtils.isEmpty(qtDtGtDnDt)) {
							qtDtGtDnDt = convertValue(qtDtGtDnDt);
							updateObj.setQtDtGtDnDt(Float.parseFloat(qtDtGtDnDt));
						} else {
							updateObj.setQtDtGtDnDt((float) 0);
						}

						updateObj.setQtDtSoHd(qtDtSoHd);

						if (StringUtils.isNotEmpty(qtDtNgayTdDt)) {
							updateObj.setQtDtNgayTdDt(dt.parse(qtDtNgayTdDt));
						}

						if (!StringUtils.isEmpty(qtDtTong)) {
							qtDtTong = convertValue(qtDtTong);
							updateObj.setQtDtTong(Float.parseFloat(qtDtTong));
						} else {
							updateObj.setQtDtTong((float) 0);
						}

						if (!StringUtils.isEmpty(qtDtXd)) {
							qtDtXd = convertValue(qtDtXd);
							updateObj.setQtDtXd(Float.parseFloat(qtDtXd));
						} else {
							updateObj.setQtDtXd((float) 0);
						}

						if (!StringUtils.isEmpty(qtDtDien)) {
							qtDtDien = convertValue(qtDtDien);
							updateObj.setQtDtDien(Float.parseFloat(qtDtDien));
						} else {
							updateObj.setQtDtDien((float) 0);
						}

						if (!StringUtils.isEmpty(qtDtLapDung)) {
							qtDtLapDung = convertValue(qtDtLapDung);
							updateObj.setQtDtLapDung(Float.parseFloat(qtDtLapDung));
						} else {
							updateObj.setQtDtLapDung((float) 0);
						}

						if (!StringUtils.isEmpty(qtDtLapBts)) {
							qtDtLapBts = convertValue(qtDtLapBts);
							updateObj.setQtDtLapBts(Float.parseFloat(qtDtLapBts));
						} else {
							updateObj.setQtDtLapBts((float) 0);
						}

						if (!StringUtils.isEmpty(qtDtKhac)) {
							qtDtKhac = convertValue(qtDtKhac);
							updateObj.setQtDtKhac(Float.parseFloat(qtDtKhac));
						} else {
							updateObj.setQtDtKhac((float) 0);
						}

						updateObj.setQtDtTtDt(qtDtTtDt);
						updateObj.setQtDtNguoiTd(qtDtNguoiTd);

						// đề nghị quyết toán cdt

						if (StringUtils.isNotEmpty(dnQtCdtNgay)) {
							updateObj.setDnQtCdtNgay(dt.parse(dnQtCdtNgay));
						}

						if (!StringUtils.isEmpty(dnQtCdtGt)) {
							dnQtCdtGt = convertValue(dnQtCdtGt);
							updateObj.setDnQtCdtGt(Float.parseFloat(dnQtCdtGt));
						} else {
							updateObj.setDnQtCdtGt((float) 0);
						}

						updateObj.setDnQtCdtNguoiLap(dnQtCdtNguoiLap);
						updateObj.setDnQtCdtNguoiNhanBg(dnQtCdtNguoiNhanBg);
						updateObj.setDnQtCdtNoiDungPsCtk(dnQtCdtNoiDungPsCtk);
						updateObj.setDnQtCdtKtk(dnQtCdtKtk);

						// thẩm đinh quyết toán cdt

						if (StringUtils.isNotEmpty(tdQtCdtNgayChot)) {
							updateObj.setTdQtCdtNgayChot(dt.parse(tdQtCdtNgayChot));
						}

						if (!StringUtils.isEmpty(tdQtCdtGt)) {
							tdQtCdtGt = convertValue(tdQtCdtGt);
							updateObj.setTdQtCdtGt(Float.parseFloat(tdQtCdtGt));
						} else {
							updateObj.setTdQtCdtGt((float) 0);
						}

						updateObj.setTdQtCdtNguoiChotMvt(tdQtCdtNguoiChotMvt);
						updateObj.setTdQtCdtNguoiTdCdtMvt(tdQtCdtNguoiTdCdtMvt);

						if (StringUtils.isNotEmpty(tdQtCdtNgayBanTd)) {
							updateObj.setTdQtCdtNgayBanTd(dt.parse(tdQtCdtNgayBanTd));
						}

						if (StringUtils.isNotEmpty(tdQtCdtNgayCtc)) {
							updateObj.setTdQtCdtNgayCtc(dt.parse(tdQtCdtNgayCtc));
						}

						updateObj.setTdQtCdtNoiDung(tdQtCdtNoiDung);
						updateObj.setTinhTrangCct2(tinhTrangCct2);
						updateObj.setGhiChu2(ghiChu2);
						updateObj.setLuuTaiKho(luuTaiKho);

						if (updateObj.getGtSlHtTtTong() != 0 && updateObj.getGtDnQtkCnTong() != 0
								&& updateObj.getGtQtkPtkTong() != 0 && updateObj.getDnQtCdtGt() != 0
								&& updateObj.getTdQtCdtGt() != 0) {

							updateObj.setTrangThai("Hoàn thành");
						} else {
							updateObj.setTrangThai("Đã gán nhân viên");
						}

						for (TblGanNhiemVuDTO missObj : lstMiss) {
							if (missObj.getIdQlCvPtk() == Long.parseLong(tblQlCvPtkId)) {
								if (updateObj.getGtSlHtTtTong() != 0) {
									if(date.getTime()>missObj.getNgayHoanThanh().getTime()){
										tblGanNhiemVuDAO.updateMission(MISSION1,STATUS_FAIL, Long.parseLong(tblQlCvPtkId));
									}else{
										tblGanNhiemVuDAO.updateMission(MISSION1,STATUS_SUCCESS, Long.parseLong(tblQlCvPtkId));
									}
									
								}
								if (updateObj.getGtDnQtkCnTong() != 0) {
									if(date.getTime()>missObj.getNgayHoanThanh().getTime()){
										tblGanNhiemVuDAO.updateMission(MISSION2,STATUS_FAIL, Long.parseLong(tblQlCvPtkId));
									}else{
										tblGanNhiemVuDAO.updateMission(MISSION2,STATUS_SUCCESS, Long.parseLong(tblQlCvPtkId));
									}
									
								}
								if (updateObj.getGtQtkPtkTong() != 0) {
									if(date.getTime()>missObj.getNgayHoanThanh().getTime()){
										tblGanNhiemVuDAO.updateMission(MISSION3,STATUS_FAIL, Long.parseLong(tblQlCvPtkId));
									}else{
										tblGanNhiemVuDAO.updateMission(MISSION3,STATUS_SUCCESS,Long.parseLong(tblQlCvPtkId));
									}
									
								}
								if (updateObj.getDnQtCdtGt() != 0) {
									if(date.getTime()>missObj.getNgayHoanThanh().getTime()){
										tblGanNhiemVuDAO.updateMission(MISSION4,STATUS_FAIL, Long.parseLong(tblQlCvPtkId));
									}else{
										tblGanNhiemVuDAO.updateMission(MISSION4,STATUS_SUCCESS,Long.parseLong(tblQlCvPtkId));
									}
									
									
								}
								if (updateObj.getTdQtCdtGt() != 0) {
									if(date.getTime()>missObj.getNgayHoanThanh().getTime()){
										tblGanNhiemVuDAO.updateMission(MISSION5,STATUS_FAIL, Long.parseLong(tblQlCvPtkId));
									}else{
										tblGanNhiemVuDAO.updateMission(MISSION5,STATUS_SUCCESS,Long.parseLong(tblQlCvPtkId));
									}
									
									
								}
							}
						}

						tblQlCvPtkDAO.update(updateObj.toModel());
					} else {
						ImportErrDTO errObj = new ImportErrDTO();
						errObj.setColumn1(cnkv);
						errObj.setColumn2(tinh);
						errObj.setColumn3(mttMaViTri);
						errObj.setColumn4(mttMa2g);
						errObj.setColumn5(mttMa3g);
						errObj.setColumn6(mttMaXuatKho);
						errObj.setColumn7(soHdCdt);
						errObj.setColumn8(loaiCt);
						errObj.setColumn9(noiDung);
						errObj.setColumn10(ngayGuiHshc);
						errObj.setColumn11(soBill);
						errObj.setColumn12(ngayNhanHshc);
						errObj.setColumn13(ghiChu);
						errObj.setColumn14(gtSlHtTtTong);
						errObj.setColumn15(gtSlHtTtXd);
						errObj.setColumn16(gtSlHtTtDien);
						errObj.setColumn17(gtSlHtTtLapDung);
						errObj.setColumn18(gtSlHtTtLapBts);
						errObj.setColumn19(gtSlHtTtKhac);
						errObj.setColumn20(gtDnQtkCnNgay);
						errObj.setColumn21(gtDnQtkCnTong);
						errObj.setColumn22(gtDnQtkCnXd);
						errObj.setColumn23(gtDnQtkCnDien);
						errObj.setColumn24(gtDnQtkCnLapDung);
						errObj.setColumn25(gtDnQtkCnLapBts);
						errObj.setColumn26(gtDnQtkCnKhac);
						errObj.setColumn27(gtDnQtkCnNguoiLap);
						errObj.setColumn28(gtQtkPtkNgay);
						errObj.setColumn29(gtQtkPtkTong);
						errObj.setColumn30(gtQtkPtkXd);
						errObj.setColumn31(gtQtkPtkDien);
						errObj.setColumn32(gtQtkPtkLapDung);
						errObj.setColumn33(gtQtkPtkLapBts);
						errObj.setColumn34(gtQtkPtkKhac);
						errObj.setColumn35(gtQtkPtkNguoiChot);
						errObj.setColumn36(gtQtkPtkDmtt);
						errObj.setColumn37(gtQtkPtkThangQtk);
						errObj.setColumn38(tinhTrangCct1);
						errObj.setColumn39(ghiChuHsLoi);
						errObj.setColumn40(qtDtNgay);
						errObj.setColumn41(qtDtGtDnDt);
						errObj.setColumn42(qtDtSoHd);
						errObj.setColumn43(qtDtNgayTdDt);
						errObj.setColumn44(qtDtTong);
						errObj.setColumn45(qtDtXd);
						errObj.setColumn46(qtDtDien);
						errObj.setColumn47(qtDtLapDung);
						errObj.setColumn48(qtDtLapBts);
						errObj.setColumn49(qtDtKhac);
						errObj.setColumn50(qtDtTtDt);
						errObj.setColumn51(qtDtNguoiTd);
						errObj.setColumn52(dnQtCdtNgay);
						errObj.setColumn53(dnQtCdtGt);
						errObj.setColumn54(dnQtCdtNguoiLap);
						errObj.setColumn55(dnQtCdtNguoiNhanBg);
						errObj.setColumn56(dnQtCdtNoiDungPsCtk);
						errObj.setColumn57(dnQtCdtKtk);
						errObj.setColumn58(tdQtCdtNgayChot);
						errObj.setColumn59(tdQtCdtGt);
						errObj.setColumn60(tdQtCdtNguoiChotMvt);
						errObj.setColumn61(tdQtCdtNguoiTdCdtMvt);
						errObj.setColumn62(tdQtCdtNgayBanTd);
						errObj.setColumn63(tdQtCdtNgayCtc);
						errObj.setColumn64(tdQtCdtNoiDung);
						errObj.setColumn65(tinhTrangCct2);
						errObj.setColumn66(ghiChu2);
						errObj.setColumn67(luuTaiKho);
						errObj.setColumn68(trangThai);
						errObj.setLstErrorOrder(lstErrExcelDto);
						workFault.add(errObj);
					}

				}

			}
			workbook.close();

			if (workFault.size() < 0) {
				throw new IllegalArgumentException("File import không có dữ liệu");
			} else {
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
			log.error(pointerException.getMessage(), pointerException);
		} catch (Exception e) {
			// e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return "";
	}

	public String importFile(String fileInput, HttpServletRequest request) throws Exception {
		TblQlCvPtkDTO objSearch = new TblQlCvPtkDTO();
		List<TblQlCvPtkDTO> lstObj = tblQlCvPtkDAO.doSearch(objSearch);
		Set<TblQlCvPtkDTO> workList = new LinkedHashSet<>();
		List<ImportErrDTO> workFault = Lists.newArrayList();
		VpsUserToken token = (VpsUserToken) request.getSession().getAttribute("vpsUserToken");

		try {

			File f = new File(fileInput);

			XSSFWorkbook workbook = new XSSFWorkbook(f);
			XSSFSheet sheet = workbook.getSheetAt(0);

			DataFormatter dataFormatter = new DataFormatter();
			int count = 0;
			for (Row row : sheet) {
				count++;
				ErrExcelDTO orderErrorFormat = new ErrExcelDTO();
				if (count >= 4 && !isRowEmpty(row)) {
					lstErrExcelDto = new ArrayList<>();
					boolean checkNgayGuiHshc = true;
					boolean checkNgayNhanHshc = true;
					// giá trị theo sản lượng hạ tầng tạm tính
					boolean checkGtSlHtTtTong = true;
					boolean checkGtSlHtTtXd = true;
					boolean checkGtSlHtTtDien = true;
					boolean checkGtSlHtTtLapDung = true;
					boolean checkGtSlHtTtLapBts = true;
					boolean checkGtSlHtTtKhac = true;
					// giá trị đề nghị Qtk của cn
					boolean checkGtDnQtkCnNgay = true;
					boolean checkGtDnQtkCnTong = true;
					boolean checkGtDnQtkCnXd = true;
					boolean checkGtDnQtkCnDien = true;
					boolean checkGtDnQtkCnLapDung = true;
					boolean checkGtDnQtkCnLapBts = true;
					boolean checkGtDnQtkCnKhac = true;
					// giá trị QTK chốt PTk
					boolean checkGtQtkPtkNgay = true;
					boolean checkGtQtkPtkTong = true;
					boolean checkGtQtkPtkXd = true;
					boolean checkGtQtkPtkDien = true;
					boolean checkGtQtkPtkLapDung = true;
					boolean checkGtQtkPtkLapBts = true;
					boolean checkGtQtkPtkKhac = true;
					// quyết toán đối tác
					boolean checkQtDtNgay = true;
					boolean checkQtDtGtDnDt = true;
					boolean checkQtDtNgayTdDt = true;
					boolean checkQtDtTong = true;
					boolean checkQtDtXd = true;
					boolean checkQtDtDien = true;
					boolean checkQtDtLapDung = true;
					boolean checkQtDtLapBts = true;
					boolean checkQtDtKhac = true;
					// đề nghị quyết toán cdt
					boolean checkDnQtCdtNgay = true;
					boolean checkDnQtCdtGt = true;
					// thẩm đinh quyết toán cdt
					boolean checkTdQtCdtNgayChot = true;
					boolean checkTdQtCdtGt = true;
					boolean checkTdQtCdtNgayBanTd = true;
					boolean checkTdQtCdtNgayCtc = true;

					String cnkv = "";
					String tinh = "";
					String mttMaViTri = "";
					String mttMa2g = "";
					String mttMa3g = "";
					String mttMaXuatKho = "";
					String soHdCdt = "";
					String loaiCt = "";
					String noiDung = "";
					String ngayGuiHshc = "";
					String soBill = "";
					String ngayNhanHshc = "";
					String ghiChu = "";
					// giá trị theo sản lượng hạ tầng tạm tính
					String gtSlHtTtTong = "";
					String gtSlHtTtXd = "";
					String gtSlHtTtDien = "";
					String gtSlHtTtLapDung = "";
					String gtSlHtTtLapBts = "";
					String gtSlHtTtKhac = "";
					// giá trị đề nghị Qtk của cn
					String gtDnQtkCnNgay = "";
					String gtDnQtkCnTong = "";
					String gtDnQtkCnXd = "";
					String gtDnQtkCnDien = "";
					String gtDnQtkCnLapDung = "";
					String gtDnQtkCnLapBts = "";
					String gtDnQtkCnKhac = "";
					String gtDnQtkCnNguoiLap = "";
					// giá trị QTK chốt PTk
					String gtQtkPtkNgay = "";
					String gtQtkPtkTong = "";
					String gtQtkPtkXd = "";
					String gtQtkPtkDien = "";
					String gtQtkPtkLapDung = "";
					String gtQtkPtkLapBts = "";
					String gtQtkPtkKhac = "";
					String gtQtkPtkNguoiChot = "";
					String gtQtkPtkDmtt = "";
					String gtQtkPtkThangQtk = "";
					String tinhTrangCct1 = "";
					String ghiChuHsLoi = "";
					// quyết toán đối tác
					String qtDtNgay = "";
					String qtDtGtDnDt = "";
					String qtDtSoHd = "";
					String qtDtNgayTdDt = "";
					String qtDtTong = "";
					String qtDtXd = "";
					String qtDtDien = "";
					String qtDtLapDung = "";
					String qtDtLapBts = "";
					String qtDtKhac = "";
					String qtDtTtDt = "";
					String qtDtNguoiTd = "";
					// đề nghị quyết toán cdt
					String dnQtCdtNgay = "";
					String dnQtCdtGt = "";
					String dnQtCdtNguoiLap = "";
					String dnQtCdtNguoiNhanBg = "";
					String dnQtCdtNoiDungPsCtk = "";
					String dnQtCdtKtk = "";
					// thẩm đinh quyết toán cdt
					String tdQtCdtNgayChot = "";
					String tdQtCdtGt = "";
					String tdQtCdtNguoiChotMvt = "";
					String tdQtCdtNguoiTdCdtMvt = "";
					String tdQtCdtNgayBanTd = "";
					String tdQtCdtNgayCtc = "";
					String tdQtCdtNoiDung = "";
					String tinhTrangCct2 = "";
					String ghiChu2 = "";
					String luuTaiKho = "";
					String trangThai = "";

					// format value column

					for (Cell cell : row) {
						if (cell.getColumnIndex() == 1) {
							cnkv = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 2) {
							tinh = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 3) {
							mttMaViTri = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 4) {
							mttMa2g = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 5) {
							mttMa3g = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 6) {
							mttMaXuatKho = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 7) {
							soHdCdt = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 8) {
							loaiCt = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 9) {
							noiDung = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 10) {
							ngayGuiHshc = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 11) {
							soBill = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 12) {
							ngayNhanHshc = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 13) {
							ghiChu = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 14) {
							gtSlHtTtTong = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 15) {
							gtSlHtTtXd = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 16) {
							gtSlHtTtDien = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 17) {
							gtSlHtTtLapDung = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 18) {
							gtSlHtTtLapBts = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 19) {
							gtSlHtTtKhac = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 20) {
							gtDnQtkCnNgay = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 21) {
							gtDnQtkCnTong = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 22) {
							gtDnQtkCnXd = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 23) {
							gtDnQtkCnDien = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 24) {
							gtDnQtkCnLapDung = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 25) {
							gtDnQtkCnLapBts = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 26) {
							gtDnQtkCnKhac = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 27) {
							gtDnQtkCnNguoiLap = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 28) {
							gtQtkPtkNgay = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 29) {
							gtQtkPtkTong = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 30) {
							gtQtkPtkXd = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 31) {
							gtQtkPtkDien = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 32) {
							gtQtkPtkLapDung = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 33) {
							gtQtkPtkLapBts = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 34) {
							gtQtkPtkKhac = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 35) {
							gtQtkPtkNguoiChot = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 36) {
							gtQtkPtkDmtt = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 37) {
							gtQtkPtkThangQtk = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 38) {
							tinhTrangCct1 = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 39) {
							ghiChuHsLoi = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 40) {
							qtDtNgay = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 41) {
							qtDtGtDnDt = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 42) {
							qtDtSoHd = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 43) {
							qtDtNgayTdDt = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 44) {
							qtDtTong = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 45) {
							qtDtXd = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 46) {
							qtDtDien = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 47) {
							qtDtLapDung = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 48) {
							qtDtLapBts = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 49) {
							qtDtKhac = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 50) {
							qtDtTtDt = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 51) {
							qtDtNguoiTd = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 52) {
							dnQtCdtNgay = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 53) {
							dnQtCdtGt = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 54) {
							dnQtCdtNguoiLap = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 55) {
							dnQtCdtNguoiNhanBg = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 56) {
							dnQtCdtNoiDungPsCtk = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 57) {
							dnQtCdtKtk = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 58) {
							tdQtCdtNgayChot = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 59) {
							tdQtCdtGt = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 60) {
							tdQtCdtNguoiChotMvt = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 61) {
							tdQtCdtNguoiTdCdtMvt = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 62) {
							tdQtCdtNgayBanTd = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 63) {
							tdQtCdtNgayCtc = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 64) {
							tdQtCdtNoiDung = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 65) {
							tinhTrangCct2 = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 66) {
							ghiChu2 = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 67) {
							luuTaiKho = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 68) {
							trangThai = dataFormatter.formatCellValue(cell);
						}

					}

					checkNgayGuiHshc = checkDataFromFileExel(ngayGuiHshc.trim(), 10, orderErrorFormat);
					checkNgayNhanHshc = checkDataFromFileExel(ngayNhanHshc.trim(), 12, orderErrorFormat);

					checkGtSlHtTtTong = checkDataFromFileExel(gtSlHtTtTong.trim(), 14, orderErrorFormat);
					checkGtSlHtTtXd = checkDataFromFileExel(gtSlHtTtXd.trim(), 15, orderErrorFormat);
					checkGtSlHtTtDien = checkDataFromFileExel(gtSlHtTtDien.trim(), 16, orderErrorFormat);
					checkGtSlHtTtLapDung = checkDataFromFileExel(gtSlHtTtLapDung.trim(), 17, orderErrorFormat);
					checkGtSlHtTtLapBts = checkDataFromFileExel(gtSlHtTtLapBts.trim(), 18, orderErrorFormat);
					checkGtSlHtTtKhac = checkDataFromFileExel(gtSlHtTtKhac.trim(), 19, orderErrorFormat);

					checkGtDnQtkCnNgay = checkDataFromFileExel(gtDnQtkCnNgay.trim(), 20, orderErrorFormat);
					checkGtDnQtkCnTong = checkDataFromFileExel(gtDnQtkCnTong.trim(), 21, orderErrorFormat);
					checkGtDnQtkCnXd = checkDataFromFileExel(gtDnQtkCnXd.trim(), 22, orderErrorFormat);
					checkGtDnQtkCnDien = checkDataFromFileExel(gtDnQtkCnDien.trim(), 23, orderErrorFormat);
					checkGtDnQtkCnLapDung = checkDataFromFileExel(gtDnQtkCnLapDung.trim(), 24, orderErrorFormat);
					checkGtDnQtkCnLapBts = checkDataFromFileExel(gtDnQtkCnLapBts.trim(), 25, orderErrorFormat);
					checkGtDnQtkCnKhac = checkDataFromFileExel(gtDnQtkCnKhac.trim(), 26, orderErrorFormat);
					// giá trị QTK chốt PTk
					checkGtQtkPtkNgay = checkDataFromFileExel(gtQtkPtkNgay.trim(), 28, orderErrorFormat);
					checkGtQtkPtkTong = checkDataFromFileExel(gtQtkPtkTong.trim(), 29, orderErrorFormat);
					checkGtQtkPtkXd = checkDataFromFileExel(gtQtkPtkXd.trim(), 30, orderErrorFormat);
					checkGtQtkPtkDien = checkDataFromFileExel(gtQtkPtkDien.trim(), 31, orderErrorFormat);
					checkGtQtkPtkLapDung = checkDataFromFileExel(gtQtkPtkLapDung.trim(), 32, orderErrorFormat);
					checkGtQtkPtkLapBts = checkDataFromFileExel(gtQtkPtkLapBts.trim(), 33, orderErrorFormat);
					checkGtQtkPtkKhac = checkDataFromFileExel(gtQtkPtkKhac.trim(), 34, orderErrorFormat);
					// quyết toán đối tác
					checkQtDtNgay = checkDataFromFileExel(qtDtNgay.trim(), 40, orderErrorFormat);
					checkQtDtGtDnDt = checkDataFromFileExel(qtDtGtDnDt.trim(), 41, orderErrorFormat);
					checkQtDtNgayTdDt = checkDataFromFileExel(qtDtNgayTdDt.trim(), 43, orderErrorFormat);
					checkQtDtTong = checkDataFromFileExel(qtDtTong.trim(), 44, orderErrorFormat);
					checkQtDtXd = checkDataFromFileExel(qtDtXd.trim(), 45, orderErrorFormat);
					checkQtDtDien = checkDataFromFileExel(qtDtDien.trim(), 46, orderErrorFormat);
					checkQtDtLapDung = checkDataFromFileExel(qtDtLapDung.trim(), 47, orderErrorFormat);
					checkQtDtLapBts = checkDataFromFileExel(qtDtLapBts.trim(), 48, orderErrorFormat);
					checkQtDtKhac = checkDataFromFileExel(qtDtKhac.trim(), 49, orderErrorFormat);
					// đề nghị quyết toán cdt
					checkDnQtCdtNgay = checkDataFromFileExel(dnQtCdtNgay.trim(), 52, orderErrorFormat);
					checkDnQtCdtGt = checkDataFromFileExel(dnQtCdtGt.trim(), 53, orderErrorFormat);
					// thẩm đinh quyết toán cdt
					checkTdQtCdtNgayChot = checkDataFromFileExel(tdQtCdtNgayChot.trim(), 58, orderErrorFormat);
					checkTdQtCdtGt = checkDataFromFileExel(tdQtCdtGt.trim(), 59, orderErrorFormat);
					checkTdQtCdtNgayBanTd = checkDataFromFileExel(tdQtCdtNgayBanTd.trim(), 62, orderErrorFormat);
					checkTdQtCdtNgayCtc = checkDataFromFileExel(tdQtCdtNgayCtc.trim(), 63, orderErrorFormat);

					if (checkNgayGuiHshc && checkNgayNhanHshc && checkGtSlHtTtTong && checkGtSlHtTtXd
							&& checkGtSlHtTtDien && checkGtSlHtTtLapDung && checkGtSlHtTtLapBts && checkGtSlHtTtKhac
							&& checkGtDnQtkCnNgay && checkGtDnQtkCnTong && checkGtDnQtkCnXd && checkGtDnQtkCnDien
							&& checkGtDnQtkCnLapDung && checkGtDnQtkCnLapBts && checkGtDnQtkCnKhac && checkGtQtkPtkNgay
							&& checkGtQtkPtkTong && checkGtQtkPtkXd && checkGtQtkPtkDien && checkGtQtkPtkLapDung
							&& checkGtQtkPtkLapBts && checkGtQtkPtkKhac && checkQtDtNgay && checkQtDtGtDnDt
							&& checkQtDtNgayTdDt && checkQtDtTong && checkQtDtXd && checkQtDtDien && checkQtDtLapDung
							&& checkQtDtLapBts && checkQtDtKhac && checkDnQtCdtNgay && checkDnQtCdtGt
							&& checkTdQtCdtNgayChot && checkTdQtCdtGt && checkTdQtCdtNgayBanTd && checkTdQtCdtNgayCtc) {

						TblQlCvPtkDTO newObj = new TblQlCvPtkDTO();
						newObj.setCnkv(cnkv);
						newObj.setTinh(tinh);
						newObj.setMttMaViTri(mttMaViTri);
						newObj.setMttMa2g(mttMa2g);
						newObj.setMttMa3g(mttMa3g);
						newObj.setMttMaXuatKho(mttMaXuatKho);
						newObj.setSoHdCdt(soHdCdt);
						newObj.setLoaiCt(loaiCt);
						newObj.setNoiDung(noiDung);

						if (StringUtils.isNotEmpty(ngayGuiHshc)) {
							newObj.setNgayGuiHshc(dt.parse(ngayGuiHshc));
						}

						newObj.setSoBill(soBill);

						if (StringUtils.isNotEmpty(ngayNhanHshc)) {
							newObj.setNgayNhanHshc(dt.parse(ngayNhanHshc));
						}

						newObj.setGhiChu(ghiChu);

						// giá trị theo sản lượng hạ tầng tạm tính

						if (!StringUtils.isEmpty(gtSlHtTtTong)) {
							gtSlHtTtTong = convertValue(gtSlHtTtTong);
							newObj.setGtSlHtTtTong(Float.parseFloat(gtSlHtTtTong));
						} else {
							newObj.setGtSlHtTtTong((float) 0);
						}

						if (!StringUtils.isEmpty(gtSlHtTtXd)) {
							gtSlHtTtXd = convertValue(gtSlHtTtXd);
							newObj.setGtSlHtTtXd(Float.parseFloat(gtSlHtTtXd));
						} else {
							newObj.setGtSlHtTtXd((float) 0);
						}

						if (!StringUtils.isEmpty(gtSlHtTtDien)) {
							gtSlHtTtDien = convertValue(gtSlHtTtDien);
							newObj.setGtSlHtTtDien(Float.parseFloat(gtSlHtTtDien));
						} else {
							newObj.setGtSlHtTtDien((float) 0);
						}

						if (!StringUtils.isEmpty(gtSlHtTtLapDung)) {
							gtSlHtTtLapDung = convertValue(gtSlHtTtLapDung);
							newObj.setGtSlHtTtLapDung(Float.parseFloat(gtSlHtTtLapDung));
						} else {
							newObj.setGtSlHtTtLapDung((float) 0);
						}

						if (!StringUtils.isEmpty(gtSlHtTtLapBts)) {
							gtSlHtTtLapBts = convertValue(gtSlHtTtLapBts);
							newObj.setGtSlHtTtLapBts(Float.parseFloat(gtSlHtTtLapBts));
						} else {
							newObj.setGtSlHtTtLapBts((float) 0);
						}

						if (!StringUtils.isEmpty(gtSlHtTtKhac)) {
							gtSlHtTtKhac = convertValue(gtSlHtTtKhac);
							newObj.setGtSlHtTtKhac(Float.parseFloat(gtSlHtTtKhac));
						} else {
							newObj.setGtSlHtTtKhac((float) 0);
						}

						//// giá trị đề nghị Qtk của cn

						if (StringUtils.isNotEmpty(gtDnQtkCnNgay)) {
							newObj.setGtDnQtkCnNgay(dt.parse(gtDnQtkCnNgay));
						}

						if (!StringUtils.isEmpty(gtDnQtkCnTong)) {
							gtDnQtkCnTong = convertValue(gtDnQtkCnTong);
							newObj.setGtDnQtkCnTong(Float.parseFloat(gtDnQtkCnTong));
						} else {
							newObj.setGtDnQtkCnTong((float) 0);
						}

						if (!StringUtils.isEmpty(gtDnQtkCnXd)) {
							gtDnQtkCnXd = convertValue(gtDnQtkCnXd);
							newObj.setGtDnQtkCnXd(Float.parseFloat(gtDnQtkCnXd));
						} else {
							newObj.setGtDnQtkCnXd((float) 0);
						}

						if (!StringUtils.isEmpty(gtDnQtkCnDien)) {
							gtDnQtkCnDien = convertValue(gtDnQtkCnDien);
							newObj.setGtDnQtkCnDien(Float.parseFloat(gtDnQtkCnDien));
						} else {
							newObj.setGtDnQtkCnDien((float) 0);
						}

						if (!StringUtils.isEmpty(gtDnQtkCnLapDung)) {
							gtDnQtkCnLapDung = convertValue(gtDnQtkCnLapDung);
							newObj.setGtDnQtkCnLapDung(Float.parseFloat(gtDnQtkCnLapDung));
						} else {
							newObj.setGtDnQtkCnLapDung((float) 0);
						}

						if (!StringUtils.isEmpty(gtDnQtkCnLapBts)) {
							gtDnQtkCnLapBts = convertValue(gtDnQtkCnLapBts);
							newObj.setGtDnQtkCnLapBts(Float.parseFloat(gtDnQtkCnLapBts));
						} else {
							newObj.setGtDnQtkCnLapBts((float) 0);
						}

						if (!StringUtils.isEmpty(gtDnQtkCnKhac)) {
							gtDnQtkCnKhac = convertValue(gtDnQtkCnKhac);
							newObj.setGtDnQtkCnKhac(Float.parseFloat(gtDnQtkCnKhac));
						} else {
							newObj.setGtDnQtkCnKhac((float) 0);
						}
						newObj.setGtDnQtkCnNguoiLap(gtDnQtkCnNguoiLap);
						// giá trị QTK chốt PTk

						if (StringUtils.isNotEmpty(gtQtkPtkNgay)) {
							newObj.setGtQtkPtkNgay(dt.parse(gtQtkPtkNgay));
						}

						if (!StringUtils.isEmpty(gtQtkPtkTong)) {
							gtQtkPtkTong = convertValue(gtQtkPtkTong);
							newObj.setGtQtkPtkTong(Float.parseFloat(gtQtkPtkTong));
						} else {
							newObj.setGtQtkPtkTong((float) 0);
						}

						if (!StringUtils.isEmpty(gtQtkPtkXd)) {
							gtQtkPtkXd = convertValue(gtQtkPtkXd);
							newObj.setGtQtkPtkXd(Float.parseFloat(gtQtkPtkXd));
						} else {
							newObj.setGtQtkPtkXd((float) 0);
						}

						if (!StringUtils.isEmpty(gtQtkPtkDien)) {
							gtQtkPtkDien = convertValue(gtQtkPtkDien);
							newObj.setGtQtkPtkDien(Float.parseFloat(gtQtkPtkDien));
						} else {
							newObj.setGtQtkPtkDien((float) 0);
						}

						if (!StringUtils.isEmpty(gtQtkPtkLapDung)) {
							gtQtkPtkLapDung = convertValue(gtQtkPtkLapDung);
							newObj.setGtQtkPtkLapDung(Float.parseFloat(gtQtkPtkLapDung));
						} else {
							newObj.setGtQtkPtkLapDung((float) 0);
						}

						if (!StringUtils.isEmpty(gtQtkPtkLapBts)) {
							gtQtkPtkLapBts = convertValue(gtQtkPtkLapBts);
							newObj.setGtQtkPtkLapBts(Float.parseFloat(gtQtkPtkLapBts));
						} else {
							newObj.setGtQtkPtkLapBts((float) 0);
						}

						if (!StringUtils.isEmpty(gtQtkPtkKhac)) {
							gtQtkPtkKhac = convertValue(gtQtkPtkKhac);
							newObj.setGtQtkPtkKhac(Float.parseFloat(gtQtkPtkKhac));
						} else {
							newObj.setGtQtkPtkKhac((float) 0);
						}

						newObj.setGtQtkPtkNguoiChot(gtQtkPtkNguoiChot);
						newObj.setGtQtkPtkDmtt(gtQtkPtkDmtt);
						newObj.setGtQtkPtkThangQtk(gtQtkPtkThangQtk);
						newObj.setTinhTrangCct1(tinhTrangCct1);
						newObj.setGhiChuHsLoi(ghiChuHsLoi);

						// quyết toán đối tác

						if (StringUtils.isNotEmpty(qtDtNgay)) {
							newObj.setQtDtNgay(dt.parse(qtDtNgay));
						}

						if (!StringUtils.isEmpty(qtDtGtDnDt)) {
							qtDtGtDnDt = convertValue(qtDtGtDnDt);
							newObj.setQtDtGtDnDt(Float.parseFloat(qtDtGtDnDt));
						} else {
							newObj.setQtDtGtDnDt((float) 0);
						}

						newObj.setQtDtSoHd(qtDtSoHd);

						if (StringUtils.isNotEmpty(qtDtNgayTdDt)) {
							newObj.setQtDtNgayTdDt(dt.parse(qtDtNgayTdDt));
						}

						if (!StringUtils.isEmpty(qtDtTong)) {
							qtDtTong = convertValue(qtDtTong);
							newObj.setQtDtTong(Float.parseFloat(qtDtTong));
						} else {
							newObj.setQtDtTong((float) 0);
						}

						if (!StringUtils.isEmpty(qtDtXd)) {
							qtDtXd = convertValue(qtDtXd);
							newObj.setQtDtXd(Float.parseFloat(qtDtXd));
						} else {
							newObj.setQtDtXd((float) 0);
						}

						if (!StringUtils.isEmpty(qtDtDien)) {
							qtDtDien = convertValue(qtDtDien);
							newObj.setQtDtDien(Float.parseFloat(qtDtDien));
						} else {
							newObj.setQtDtDien((float) 0);
						}

						if (!StringUtils.isEmpty(qtDtLapDung)) {
							qtDtLapDung = convertValue(qtDtLapDung);
							newObj.setQtDtLapDung(Float.parseFloat(qtDtLapDung));
						} else {
							newObj.setQtDtLapDung((float) 0);
						}

						if (!StringUtils.isEmpty(qtDtLapBts)) {
							qtDtLapBts = convertValue(qtDtLapBts);
							newObj.setQtDtLapBts(Float.parseFloat(qtDtLapBts));
						} else {
							newObj.setQtDtLapBts((float) 0);
						}

						if (!StringUtils.isEmpty(qtDtKhac)) {
							qtDtKhac = convertValue(qtDtKhac);
							newObj.setQtDtKhac(Float.parseFloat(qtDtKhac));
						} else {
							newObj.setQtDtKhac((float) 0);
						}

						newObj.setQtDtTtDt(qtDtTtDt);
						newObj.setQtDtNguoiTd(qtDtNguoiTd);

						// đề nghị quyết toán cdt

						if (StringUtils.isNotEmpty(dnQtCdtNgay)) {
							newObj.setDnQtCdtNgay(dt.parse(dnQtCdtNgay));
						}

						if (!StringUtils.isEmpty(dnQtCdtGt)) {
							dnQtCdtGt = convertValue(dnQtCdtGt);
							newObj.setDnQtCdtGt(Float.parseFloat(dnQtCdtGt));
						} else {
							newObj.setDnQtCdtGt((float) 0);
						}

						newObj.setDnQtCdtNguoiLap(dnQtCdtNguoiLap);
						newObj.setDnQtCdtNguoiNhanBg(dnQtCdtNguoiNhanBg);
						newObj.setDnQtCdtNoiDungPsCtk(dnQtCdtNoiDungPsCtk);
						newObj.setDnQtCdtKtk(dnQtCdtKtk);

						// thẩm đinh quyết toán cdt

						if (StringUtils.isNotEmpty(tdQtCdtNgayChot)) {
							newObj.setTdQtCdtNgayChot(dt.parse(tdQtCdtNgayChot));
						}

						if (!StringUtils.isEmpty(tdQtCdtGt)) {
							tdQtCdtGt = convertValue(tdQtCdtGt);
							newObj.setTdQtCdtGt(Float.parseFloat(tdQtCdtGt));
						} else {
							newObj.setTdQtCdtGt((float) 0);
						}

						newObj.setTdQtCdtNguoiChotMvt(tdQtCdtNguoiChotMvt);
						newObj.setTdQtCdtNguoiTdCdtMvt(tdQtCdtNguoiTdCdtMvt);

						if (StringUtils.isNotEmpty(tdQtCdtNgayBanTd)) {
							newObj.setTdQtCdtNgayBanTd(dt.parse(tdQtCdtNgayBanTd));
						}

						if (StringUtils.isNotEmpty(tdQtCdtNgayCtc)) {
							newObj.setTdQtCdtNgayCtc(dt.parse(tdQtCdtNgayCtc));
						}

						newObj.setTdQtCdtNoiDung(tdQtCdtNoiDung);
						newObj.setTinhTrangCct2(tinhTrangCct2);
						newObj.setGhiChu2(ghiChu2);
						newObj.setLuuTaiKho(luuTaiKho);

						newObj.setTrangThai("Mới tạo");

					
						
						
					

						boolean checkItem= false;
						if(lstObj.size()!=0){
							for(int i =0;i<lstObj.size();i++){		// check trùng database và file excel
								
								lstObj.get(i).setTblQlCvPtkId(null);
								
								if(newObj.equals(lstObj.get(i))){
									checkItem= false;
									break;
								}else{
									checkItem = true;
								}	
							}
							
							if(checkItem==true){
								workList.add(newObj);									
							}
							
						}else{
							workList.add(newObj);
						}

					} else {
						ImportErrDTO errObj = new ImportErrDTO();
						errObj.setColumn1(cnkv);
						errObj.setColumn2(tinh);
						errObj.setColumn3(mttMaViTri);
						errObj.setColumn4(mttMa2g);
						errObj.setColumn5(mttMa3g);
						errObj.setColumn6(mttMaXuatKho);
						errObj.setColumn7(soHdCdt);
						errObj.setColumn8(loaiCt);
						errObj.setColumn9(noiDung);
						errObj.setColumn10(ngayGuiHshc);
						errObj.setColumn11(soBill);
						errObj.setColumn12(ngayNhanHshc);
						errObj.setColumn13(ghiChu);
						errObj.setColumn14(gtSlHtTtTong);
						errObj.setColumn15(gtSlHtTtXd);
						errObj.setColumn16(gtSlHtTtDien);
						errObj.setColumn17(gtSlHtTtLapDung);
						errObj.setColumn18(gtSlHtTtLapBts);
						errObj.setColumn19(gtSlHtTtKhac);
						errObj.setColumn20(gtDnQtkCnNgay);
						errObj.setColumn21(gtDnQtkCnTong);
						errObj.setColumn22(gtDnQtkCnXd);
						errObj.setColumn23(gtDnQtkCnDien);
						errObj.setColumn24(gtDnQtkCnLapDung);
						errObj.setColumn25(gtDnQtkCnLapBts);
						errObj.setColumn26(gtDnQtkCnKhac);
						errObj.setColumn27(gtDnQtkCnNguoiLap);
						errObj.setColumn28(gtQtkPtkNgay);
						errObj.setColumn29(gtQtkPtkTong);
						errObj.setColumn30(gtQtkPtkXd);
						errObj.setColumn31(gtQtkPtkDien);
						errObj.setColumn32(gtQtkPtkLapDung);
						errObj.setColumn33(gtQtkPtkLapBts);
						errObj.setColumn34(gtQtkPtkKhac);
						errObj.setColumn35(gtQtkPtkNguoiChot);
						errObj.setColumn36(gtQtkPtkDmtt);
						errObj.setColumn37(gtQtkPtkThangQtk);
						errObj.setColumn38(tinhTrangCct1);
						errObj.setColumn39(ghiChuHsLoi);
						errObj.setColumn40(qtDtNgay);
						errObj.setColumn41(qtDtGtDnDt);
						errObj.setColumn42(qtDtSoHd);
						errObj.setColumn43(qtDtNgayTdDt);
						errObj.setColumn44(qtDtTong);
						errObj.setColumn45(qtDtXd);
						errObj.setColumn46(qtDtDien);
						errObj.setColumn47(qtDtLapDung);
						errObj.setColumn48(qtDtLapBts);
						errObj.setColumn49(qtDtKhac);
						errObj.setColumn50(qtDtTtDt);
						errObj.setColumn51(qtDtNguoiTd);
						errObj.setColumn52(dnQtCdtNgay);
						errObj.setColumn53(dnQtCdtGt);
						errObj.setColumn54(dnQtCdtNguoiLap);
						errObj.setColumn55(dnQtCdtNguoiNhanBg);
						errObj.setColumn56(dnQtCdtNoiDungPsCtk);
						errObj.setColumn57(dnQtCdtKtk);
						errObj.setColumn58(tdQtCdtNgayChot);
						errObj.setColumn59(tdQtCdtGt);
						errObj.setColumn60(tdQtCdtNguoiChotMvt);
						errObj.setColumn61(tdQtCdtNguoiTdCdtMvt);
						errObj.setColumn62(tdQtCdtNgayBanTd);
						errObj.setColumn63(tdQtCdtNgayCtc);
						errObj.setColumn64(tdQtCdtNoiDung);
						errObj.setColumn65(tinhTrangCct2);
						errObj.setColumn66(ghiChu2);
						errObj.setColumn67(luuTaiKho);
						errObj.setColumn68(trangThai);
						errObj.setLstErrorOrder(lstErrExcelDto);
						workFault.add(errObj);
					}
				}

			}

			workbook.close();
			System.out.println("size done: " + workList.size());
			System.out.println("size fault: " + workFault.size());
			if (workList.size() < 0 && workFault.size() < 0) {
				throw new IllegalArgumentException("File import không có dữ liệu");
			} else {
				if (workList.size() > 0) {
					List<TblQlCvPtkDTO> workLstFake = new ArrayList<TblQlCvPtkDTO>(workList);
					saveList(workLstFake);
				}

				if (workFault.size() > 0) {
					// exportExcelError(workFault);
					// throw new IllegalArgumentException("Có lỗi trong file
					// import");
					return exportExcelError(workFault);
				}
				// return (long) 1;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return "";

	}

	public boolean checkDataFromFileExel(String data, int columnIndex, ErrExcelDTO orderErrorFormat) {

		orderErrorFormat = new ErrExcelDTO();
		switch (columnIndex) {
		case 10:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isDate(data, "dd/MM/yyyy")) {
					if (ValidateUtils.isDate(data, "MM/dd/yyyy")) {
						DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH).toFormat();
					} else {
						orderErrorFormat.setDetailError("Ngày gửi HSHC không hợp lệ(ex:13/06/2018)");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				}
			}
			break;
		case 12:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isDate(data, "dd/MM/yyyy")) {
					if (ValidateUtils.isDate(data, "MM/dd/yyyy")) {
						DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH).toFormat();
					} else {
						orderErrorFormat.setDetailError("Ngày nhận HSHC không hợp lệ(ex:13/06/2018)");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				}
			}
			break;
		case 14:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isFloat(data)) {
					if (!ValidateUtils.isFloat(convertValue(data))) {
						orderErrorFormat
								.setDetailError("Giá trị sản lượng theo hạ tầng tạm tính( Tổng ) không đúng định dạng");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				}
			}
			break;
		case 15:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isFloat(data)) {
					if (!ValidateUtils.isFloat(convertValue(data))) {
						orderErrorFormat.setDetailError(
								"Giá trị sản lượng theo hạ tầng tạm tính( Xây dựng ) không đúng định dạng");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				}
			}
			break;
		case 16:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isFloat(data)) {
					if (!ValidateUtils.isFloat(convertValue(data))) {
						orderErrorFormat
								.setDetailError("Giá trị sản lượng theo hạ tầng tạm tính( Điện ) không đúng định dạng");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				}
			}
			break;
		case 17:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isFloat(data)) {
					if (!ValidateUtils.isFloat(convertValue(data))) {
						orderErrorFormat.setDetailError(
								"Giá trị sản lượng theo hạ tầng tạm tính( Lắp dựng) không đúng định dạng");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				}
			}
			break;
		case 18:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isFloat(data)) {
					if (!ValidateUtils.isFloat(convertValue(data))) {
						orderErrorFormat.setDetailError(
								"Giá trị sản lượng theo hạ tầng tạm tính( Lắp BTS ) không đúng định dạng");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				}
			}
			break;
		case 19:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isFloat(data)) {
					if (!ValidateUtils.isFloat(convertValue(data))) {
						orderErrorFormat
								.setDetailError("Giá trị sản lượng theo hạ tầng tạm tính( Khác ) không đúng định dạng");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				}
			}
			break;
		case 20:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isDate(data, "dd/MM/yyyy")) {
					if (ValidateUtils.isDate(data, "MM/dd/yyyy")) {
						DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH).toFormat();
					} else {
						orderErrorFormat.setDetailError(
								"Ngày/tháng/năm (Giá trị đề nghị QTK của Cn) không hợp lệ(ex:13/06/2018)");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				}
			}
			break;
		case 21:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isFloat(data)) {
					if (!ValidateUtils.isFloat(convertValue(data))) {
						orderErrorFormat.setDetailError("Giá trị đề nghị QTK của Cn( Tổng ) không đúng định dạng");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				}
			}
			break;
		case 22:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isFloat(data)) {
					if (!ValidateUtils.isFloat(convertValue(data))) {
						orderErrorFormat.setDetailError("Giá trị đề nghị QTK của Cn( Xây dựng) không đúng định dạng");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				}
			}
			break;
		case 23:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isFloat(data)) {
					if (!ValidateUtils.isFloat(convertValue(data))) {
						orderErrorFormat.setDetailError("Giá trị đề nghị QTK của Cn( Điện ) không đúng định dạng");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				}
			}
			break;
		case 24:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isFloat(data)) {
					if (!ValidateUtils.isFloat(convertValue(data))) {
						orderErrorFormat.setDetailError("Giá trị đề nghị QTK của Cn( Lắp dựng ) không đúng định dạng");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				}
			}
			break;
		case 25:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isFloat(data)) {
					if (!ValidateUtils.isFloat(convertValue(data))) {
						orderErrorFormat.setDetailError("Giá trị đề nghị QTK của Cn( Lắp BTS ) không đúng định dạng");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				}
			}
			break;
		case 26:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isFloat(data)) {
					if (!ValidateUtils.isFloat(convertValue(data))) {
						orderErrorFormat.setDetailError("Giá trị đề nghị QTK của Cn( Khác ) không đúng định dạng");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				}
			}
			break;
		case 28:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isDate(data, "dd/MM/yyyy")) {
					if (ValidateUtils.isDate(data, "MM/dd/yyyy")) {
						DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH).toFormat();
					} else {
						orderErrorFormat
								.setDetailError("Ngày/tháng/năm (Giá trị  QTK chốt Ptk ) không hợp lệ(ex:13/06/2018)");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				}
			}
			break;
		case 29:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isFloat(data)) {
					if (!ValidateUtils.isFloat(convertValue(data))) {
						orderErrorFormat.setDetailError("Giá trị  QTK chốt Ptk( Tổng ) không đúng định dạng");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				}
			}
			break;
		case 30:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isFloat(data)) {
					if (!ValidateUtils.isFloat(convertValue(data))) {
						orderErrorFormat.setDetailError("Giá trị  QTK chốt Ptk( Xây dựng ) không đúng định dạng");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				}
			}
			break;
		case 31:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isFloat(data)) {
					if (!ValidateUtils.isFloat(convertValue(data))) {
						orderErrorFormat.setDetailError("Giá trị  QTK chốt Ptk( Điện ) không đúng định dạng");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				}
			}
			break;
		case 32:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isFloat(data)) {
					if (!ValidateUtils.isFloat(convertValue(data))) {
						orderErrorFormat.setDetailError("Giá trị  QTK chốt Ptk( Lắp dựng ) không đúng định dạng");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				}
			}
			break;
		case 33:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isFloat(data)) {
					if (!ValidateUtils.isFloat(convertValue(data))) {
						orderErrorFormat.setDetailError("Giá trị  QTK chốt Ptk( Lắp BTS ) không đúng định dạng");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				}
			}
			break;
		case 34:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isFloat(data)) {
					if (!ValidateUtils.isFloat(convertValue(data))) {
						orderErrorFormat.setDetailError("Giá trị  QTK chốt Ptk( Khác ) không đúng định dạng");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				}
			}
			break;
		case 40:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isDate(data, "dd/MM/yyyy")) {
					if (ValidateUtils.isDate(data, "MM/dd/yyyy")) {
						DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH).toFormat();
					} else {
						orderErrorFormat.setDetailError("Ngày/tháng/năm Đối tác gửi không hợp lệ(ex:13/06/2018)");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				}
			}
			break;
		case 41:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isFloat(data)) {
					if (!ValidateUtils.isFloat(convertValue(data))) {
						orderErrorFormat.setDetailError("Giá trị đề nghị của đối tác không đúng định dạng");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				}
			}
			break;
		case 43:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isDate(data, "dd/MM/yyyy")) {
					if (ValidateUtils.isDate(data, "MM/dd/yyyy")) {
						DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH).toFormat();
					} else {
						orderErrorFormat.setDetailError("Ngày/tháng/năm thẩm đối tác không hợp lệ(ex:13/06/2018)");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				}
			}
			break;
		case 44:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isFloat(data)) {
					if (!ValidateUtils.isFloat(convertValue(data))) {
						orderErrorFormat.setDetailError("Quyết toán đối tác( Tổng ) không đúng định dạng");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				}
			}
			break;
		case 45:

			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isFloat(data)) {
					if (!ValidateUtils.isFloat(convertValue(data))) {
						orderErrorFormat.setDetailError("Quyết toán đối tác( Xây dựng ) không đúng định dạng");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				}
			}
			break;
		case 46:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isFloat(data)) {
					if (!ValidateUtils.isFloat(convertValue(data))) {
						orderErrorFormat.setDetailError("Quyết toán đối tác( Điện ) không đúng định dạng");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				}
			}
			break;
		case 47:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isFloat(data)) {
					if (!ValidateUtils.isFloat(convertValue(data))) {
						orderErrorFormat.setDetailError("Quyết toán đối tác( Lắp dựng ) không đúng định dạng");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				}
			}
			break;
		case 48:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isFloat(data)) {
					if (!ValidateUtils.isFloat(convertValue(data))) {
						orderErrorFormat.setDetailError("Quyết toán đối tác( Lắp BTS ) không đúng định dạng");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				}
			}
			break;
		case 49:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isFloat(data)) {
					if (!ValidateUtils.isFloat(convertValue(data))) {
						orderErrorFormat.setDetailError("Quyết toán đối tác( Khác) không đúng định dạng");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				}
			}
			break;
		case 52:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isDate(data, "dd/MM/yyyy")) {
					if (ValidateUtils.isDate(data, "MM/dd/yyyy")) {
						DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH).toFormat();
					} else {
						orderErrorFormat
								.setDetailError("Ngày/tháng/năm (Đề nghị quyết toán CDT) không hợp lệ(ex:13/06/2018)");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				}
			}
			break;
		case 53:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isFloat(data)) {
					if (!ValidateUtils.isFloat(convertValue(data))) {
						orderErrorFormat.setDetailError("Giá trị ( Đề nghị quyết toán CDT) không đúng định dạng");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				}
			}
			break;
		case 58:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isDate(data, "dd/MM/yyyy")) {
					if (ValidateUtils.isDate(data, "MM/dd/yyyy")) {
						DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH).toFormat();
					} else {
						orderErrorFormat.setDetailError(
								"Ngày/tháng/năm chốt (Thẩm định Quyết toán CĐT) không hợp lệ(ex:13/06/2018)");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				}
			}
			break;
		case 59:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isFloat(data)) {
					if (!ValidateUtils.isFloat(convertValue(data))) {
						orderErrorFormat.setDetailError("Giá trị ( Thẩm định Quyết toán CĐT) không đúng định dạng");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				}
			}
			break;
		case 62:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isDate(data, "dd/MM/yyyy")) {
					if (ValidateUtils.isDate(data, "MM/dd/yyyy")) {
						DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH).toFormat();
					} else {
						orderErrorFormat.setDetailError(
								"Ngày/tháng/năm bản thẩm về (Thẩm định Quyết toán CĐT) không hợp lệ(ex:13/06/2018)");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				}
			}
			break;
		case 63:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isDate(data, "dd/MM/yyyy")) {
					if (ValidateUtils.isDate(data, "MM/dd/yyyy")) {
						DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH).toFormat();
					} else {
						orderErrorFormat.setDetailError(
								"Ngày chuyển tài chính (Thẩm định Quyết toán CĐT) không hợp lệ(ex:13/06/2018)");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				}
			}
			break;

		default:
			break;
		}
		return true;
	}

	@SuppressWarnings("deprecation")
	public String exportExcelError(List<ImportErrDTO> obj) throws Exception {
		// TODO Auto-generated method stub
		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream file = new BufferedInputStream(new FileInputStream(filePath + "importQlCvPtkErr.xlsx"));
			// String filePath =
			// UEncrypt.decryptFileUploadPath(obj.getFilePathError());
			// InputStream file = new BufferedInputStream(new
			// FileInputStream(filePath));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);
			DataFormat format = workbook.createDataFormat();
			// set style
			XSSFCellStyle style = workbook.createCellStyle();
			XSSFCellStyle styleNumber = workbook.createCellStyle();
			XSSFFont hSSFFont = workbook.createFont();
			hSSFFont.setFontName("Times New Roman");
			style.setFont(hSSFFont);
			style.setBorderBottom(BorderStyle.THIN);
			style.setBorderTop(BorderStyle.THIN);
			style.setBorderRight(BorderStyle.THIN);
			style.setBorderLeft(BorderStyle.THIN);
			style.setWrapText(true);
			styleNumber.setDataFormat(format.getFormat("#,##0"));
			styleNumber.setBorderBottom(BorderStyle.THIN);
			styleNumber.setBorderTop(BorderStyle.THIN);
			styleNumber.setBorderRight(BorderStyle.THIN);
			styleNumber.setBorderLeft(BorderStyle.THIN);
			styleNumber.setWrapText(true);
			styleNumber.setAlignment(CellStyle.ALIGN_RIGHT);
			int rownum = 3;
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

				XSSFCell cell18 = row.createCell(18);

				cell18.setCellValue(obj.get(i).getColumn18());

				XSSFCell cell19 = row.createCell(19);

				cell19.setCellValue(obj.get(i).getColumn19());

				XSSFCell cell20 = row.createCell(20);
				cell20.setCellValue(obj.get(i).getColumn20());

				XSSFCell cell21 = row.createCell(21);

				cell21.setCellValue(obj.get(i).getColumn21());

				XSSFCell cell22 = row.createCell(22);

				cell22.setCellValue(obj.get(i).getColumn22());

				XSSFCell cell23 = row.createCell(23);

				cell23.setCellValue(obj.get(i).getColumn23());

				XSSFCell cell24 = row.createCell(24);

				cell24.setCellValue(obj.get(i).getColumn24());

				XSSFCell cell25 = row.createCell(25);
				cell25.setCellValue(obj.get(i).getColumn25());

				XSSFCell cell26 = row.createCell(26);

				cell26.setCellValue(obj.get(i).getColumn26());

				XSSFCell cell27 = row.createCell(27);
				cell27.setCellValue(obj.get(i).getColumn27());

				XSSFCell cell28 = row.createCell(28);
				cell28.setCellValue(obj.get(i).getColumn28());

				XSSFCell cell29 = row.createCell(29);

				cell29.setCellValue(obj.get(i).getColumn29());

				XSSFCell cell30 = row.createCell(30);

				cell30.setCellValue(obj.get(i).getColumn30());

				XSSFCell cell31 = row.createCell(31);

				cell31.setCellValue(obj.get(i).getColumn31());

				XSSFCell cell32 = row.createCell(32);

				cell32.setCellValue(obj.get(i).getColumn32());

				XSSFCell cell33 = row.createCell(33);

				cell33.setCellValue(obj.get(i).getColumn33());

				XSSFCell cell34 = row.createCell(34);

				cell34.setCellValue(obj.get(i).getColumn34());

				XSSFCell cell35 = row.createCell(35);
				cell35.setCellValue(obj.get(i).getColumn35());

				XSSFCell cell36 = row.createCell(36);
				cell36.setCellValue(obj.get(i).getColumn36());

				XSSFCell cell37 = row.createCell(37);
				cell37.setCellValue(obj.get(i).getColumn37());

				XSSFCell cell38 = row.createCell(38);
				cell38.setCellValue(obj.get(i).getColumn38());

				XSSFCell cell39 = row.createCell(39);
				cell39.setCellValue(obj.get(i).getColumn39());

				XSSFCell cell40 = row.createCell(40);
				cell40.setCellValue(obj.get(i).getColumn40());

				XSSFCell cell41 = row.createCell(41);

				cell41.setCellValue(obj.get(i).getColumn41());

				XSSFCell cell42 = row.createCell(42);
				cell42.setCellValue(obj.get(i).getColumn42());

				XSSFCell cell43 = row.createCell(43);
				cell43.setCellValue(obj.get(i).getColumn43());

				XSSFCell cell44 = row.createCell(44);

				cell44.setCellValue(obj.get(i).getColumn44());

				XSSFCell cell45 = row.createCell(45);

				cell45.setCellValue(obj.get(i).getColumn45());

				XSSFCell cell46 = row.createCell(46);

				cell46.setCellValue(obj.get(i).getColumn46());

				XSSFCell cell47 = row.createCell(47);

				cell47.setCellValue(obj.get(i).getColumn47());

				XSSFCell cell48 = row.createCell(48);

				cell48.setCellValue(obj.get(i).getColumn48());

				XSSFCell cell49 = row.createCell(49);

				cell49.setCellValue(obj.get(i).getColumn49());

				XSSFCell cell50 = row.createCell(50);
				cell50.setCellValue(obj.get(i).getColumn50());

				XSSFCell cell51 = row.createCell(51);
				cell51.setCellValue(obj.get(i).getColumn51());

				XSSFCell cell52 = row.createCell(52);
				cell52.setCellValue(obj.get(i).getColumn52());

				XSSFCell cell53 = row.createCell(53);

				cell53.setCellValue(obj.get(i).getColumn53());

				XSSFCell cell54 = row.createCell(54);
				cell54.setCellValue(obj.get(i).getColumn54());

				XSSFCell cell55 = row.createCell(55);
				cell55.setCellValue(obj.get(i).getColumn55());

				XSSFCell cell56 = row.createCell(56);
				cell56.setCellValue(obj.get(i).getColumn56());

				XSSFCell cell57 = row.createCell(57);
				cell57.setCellValue(obj.get(i).getColumn57());

				XSSFCell cell58 = row.createCell(58);
				cell58.setCellValue(obj.get(i).getColumn58());

				XSSFCell cell59 = row.createCell(59);

				cell59.setCellValue(obj.get(i).getColumn59());

				XSSFCell cell60 = row.createCell(60);
				cell60.setCellValue(obj.get(i).getColumn60());

				XSSFCell cell61 = row.createCell(61);
				cell61.setCellValue(obj.get(i).getColumn61());

				XSSFCell cell62 = row.createCell(62);
				cell62.setCellValue(obj.get(i).getColumn62());

				XSSFCell cell63 = row.createCell(63);
				cell63.setCellValue(obj.get(i).getColumn63());

				XSSFCell cell64 = row.createCell(64);
				cell64.setCellValue(obj.get(i).getColumn64());

				XSSFCell cell65 = row.createCell(65);
				cell65.setCellValue(obj.get(i).getColumn65());

				XSSFCell cell66 = row.createCell(66);
				cell66.setCellValue(obj.get(i).getColumn66());

				XSSFCell cell67 = row.createCell(67);
				cell67.setCellValue(obj.get(i).getColumn67());

				for (int j = 0; j < obj.get(i).getLstErrorOrder().size(); j++) {
					err.append(obj.get(i).getLstErrorOrder().get(j).getDetailError() + "-");
					// System.out.println("err" + err);
				}
				System.out.println("err " + i + ":" + err);

				XSSFCell cell68 = row.createCell(68);
				cell68.setCellValue(err.toString());

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
				cell14.setCellStyle(styleNumber);
				cell15.setCellStyle(styleNumber);
				cell16.setCellStyle(styleNumber);
				cell17.setCellStyle(styleNumber);
				cell18.setCellStyle(styleNumber);
				cell19.setCellStyle(styleNumber);
				cell20.setCellStyle(style);
				cell21.setCellStyle(styleNumber);
				cell22.setCellStyle(styleNumber);
				cell23.setCellStyle(styleNumber);
				cell24.setCellStyle(styleNumber);
				cell25.setCellStyle(styleNumber);
				cell26.setCellStyle(styleNumber);
				cell27.setCellStyle(style);
				cell28.setCellStyle(style);
				cell29.setCellStyle(styleNumber);
				cell30.setCellStyle(styleNumber);
				cell31.setCellStyle(styleNumber);
				cell32.setCellStyle(styleNumber);
				cell33.setCellStyle(styleNumber);
				cell34.setCellStyle(styleNumber);
				cell35.setCellStyle(style);
				cell36.setCellStyle(style);
				cell37.setCellStyle(style);
				cell38.setCellStyle(style);
				cell39.setCellStyle(style);
				cell40.setCellStyle(styleNumber);
				cell41.setCellStyle(style);
				cell42.setCellStyle(style);
				cell43.setCellStyle(style);
				cell44.setCellStyle(styleNumber);
				cell45.setCellStyle(styleNumber);
				cell46.setCellStyle(styleNumber);
				cell47.setCellStyle(styleNumber);
				cell48.setCellStyle(styleNumber);
				cell49.setCellStyle(styleNumber);
				cell50.setCellStyle(style);
				cell51.setCellStyle(style);
				cell52.setCellStyle(style);
				cell53.setCellStyle(styleNumber);
				cell54.setCellStyle(style);
				cell55.setCellStyle(style);
				cell56.setCellStyle(style);
				cell57.setCellStyle(style);
				cell58.setCellStyle(style);
				cell59.setCellStyle(styleNumber);
				cell60.setCellStyle(style);
				cell61.setCellStyle(style);
				cell62.setCellStyle(style);
				cell63.setCellStyle(style);
				cell64.setCellStyle(style);
				cell65.setCellStyle(style);
				cell66.setCellStyle(style);
				cell67.setCellStyle(style);

			}
			file.close();

			File out = new File(folder2Upload + File.separatorChar + "importQlCvPtkErr" + startExportTime + ".xlsx");

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
		// String path = UEncrypt.encryptFileUploadPath("BaoCaoThucXuatTheoKy" +
		// startExportTime +".xlsx");
		return UEncrypt.encryptFileUploadPath("importQlCvPtkErr" + startExportTime + ".xlsx");
	}

	public String exportAllCV(TblQlCvPtkDTO lst, HttpServletRequest request) throws Exception {

		List<TblQlCvPtkDTO> obj1 = tblQlCvPtkDAO.doSearch(lst);
		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream fileIn = new BufferedInputStream(new FileInputStream(filePath + "DSCvPtk.xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(fileIn);
			XSSFSheet sheet = workbook.getSheetAt(0);
			DataFormat format = workbook.createDataFormat();
			XSSFCellStyle style = workbook.createCellStyle();
			XSSFCellStyle style1 = workbook.createCellStyle();
			XSSFCellStyle style2 = workbook.createCellStyle();
			XSSFCellStyle style3 = workbook.createCellStyle();
			XSSFCellStyle style4 = workbook.createCellStyle();
			XSSFCellStyle styleNumber = workbook.createCellStyle();
			XSSFFont hSSFFont = workbook.createFont();
			hSSFFont.setFontName("Times New Roman");
			XSSFFont hSSFFont2 = workbook.createFont();
			hSSFFont2.setFontName("Times New Roman");
			hSSFFont2.setItalic(true);
			try {
				XSSFFont hSSFFont1 = workbook.createFont();
				hSSFFont1.setFontName("Times New Roman");
				hSSFFont1.setFontHeightInPoints((short) 12);
				hSSFFont1.setBold(true);
				// style1.setVerticalAlignment(VerticalAlignment.CENTER);
				style1.setFont(hSSFFont1);
				style1.setWrapText(true);
				style1.setAlignment(CellStyle.ALIGN_CENTER);
				style3.setFont(hSSFFont1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			style4.setFont(hSSFFont2);
			style4.setAlignment(CellStyle.ALIGN_CENTER);
			style.setFont(hSSFFont);
			style.setBorderBottom(BorderStyle.THIN);
			style.setBorderTop(BorderStyle.THIN);
			style.setBorderRight(BorderStyle.THIN);
			style.setBorderLeft(BorderStyle.THIN);
			style.setWrapText(true);
			style2.setBorderBottom(BorderStyle.THIN);
			style2.setBorderLeft(BorderStyle.THIN);

			styleNumber.setBorderBottom(BorderStyle.THIN);
			styleNumber.setBorderTop(BorderStyle.THIN);
			styleNumber.setBorderRight(BorderStyle.THIN);
			styleNumber.setBorderLeft(BorderStyle.THIN);
			styleNumber.setDataFormat(format.getFormat("#,##0"));
			styleNumber.setWrapText(true);
			styleNumber.setAlignment(CellStyle.ALIGN_RIGHT);
			int rownum1 = 3;
			for (int j = 0; j < obj1.size(); j++) {
				XSSFRow row = sheet.createRow(rownum1);
				XSSFCell cell0 = row.createCell(0);
				cell0.setCellValue(j + 1);
				rownum1++;

				XSSFCell cell1 = row.createCell(1);
				cell1.setCellValue(obj1.get(j).getCnkv());

				XSSFCell cell2 = row.createCell(2);
				cell2.setCellValue(obj1.get(j).getTinh());

				XSSFCell cell3 = row.createCell(3);
				cell3.setCellValue(obj1.get(j).getMttMaViTri());

				XSSFCell cell4 = row.createCell(4);
				cell4.setCellValue(obj1.get(j).getMttMa2g());

				XSSFCell cell5 = row.createCell(5);
				cell5.setCellValue(obj1.get(j).getMttMa3g());

				XSSFCell cell6 = row.createCell(6);
				cell6.setCellValue(obj1.get(j).getMttMaXuatKho());

				XSSFCell cell7 = row.createCell(7);
				cell7.setCellValue(obj1.get(j).getSoHdCdt());

				XSSFCell cell8 = row.createCell(8);
				cell8.setCellValue(obj1.get(j).getLoaiCt());

				XSSFCell cell9 = row.createCell(9);
				cell9.setCellValue(obj1.get(j).getNoiDung());

				XSSFCell cell10 = row.createCell(10);
				if (obj1.get(j).getNgayGuiHshc() != null) {
					cell10.setCellValue(dt.format(obj1.get(j).getNgayGuiHshc()));
				} else {
					cell10.setCellValue("");
				}

				XSSFCell cell11 = row.createCell(11);
				cell11.setCellValue(obj1.get(j).getSoBill());

				XSSFCell cell12 = row.createCell(12);
				if (obj1.get(j).getNgayNhanHshc() != null) {
					cell12.setCellValue(dt.format(obj1.get(j).getNgayNhanHshc()));
				} else {
					cell12.setCellValue("");
				}

				XSSFCell cell13 = row.createCell(13);
				cell13.setCellValue(obj1.get(j).getGhiChu());

				XSSFCell cell14 = row.createCell(14);
				cell14.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getGtSlHtTtTong() == null || obj1.get(j).getGtSlHtTtTong() == (float) 0) {
					obj1.get(j).setGtSlHtTtTong((float) 0);
					cell14.setCellValue(obj1.get(j).getGtSlHtTtTong());
				} else {
					cell14.setCellValue(obj1.get(j).getGtSlHtTtTong());
				}

				XSSFCell cell15 = row.createCell(15);
				cell15.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getGtSlHtTtXd() == null || obj1.get(j).getGtSlHtTtXd() == (float) 0) {
					obj1.get(j).setGtSlHtTtXd((float) 0);
					cell15.setCellValue(obj1.get(j).getGtSlHtTtXd());
				} else {
					cell15.setCellValue(obj1.get(j).getGtSlHtTtXd());
				}

				XSSFCell cell16 = row.createCell(16);
				cell16.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getGtSlHtTtDien() == null || obj1.get(j).getGtSlHtTtDien() == (float) 0) {
					obj1.get(j).setGtSlHtTtDien((float) 0);
					cell16.setCellValue(obj1.get(j).getGtSlHtTtDien());
				} else {
					cell16.setCellValue(obj1.get(j).getGtSlHtTtDien());
				}

				XSSFCell cell17 = row.createCell(17);
				cell17.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getGtSlHtTtLapDung() == null || obj1.get(j).getGtSlHtTtLapDung() == (float) 0) {
					obj1.get(j).setGtSlHtTtLapDung((float) 0);
					cell17.setCellValue(obj1.get(j).getGtSlHtTtLapDung());
				} else {
					cell17.setCellValue(obj1.get(j).getGtSlHtTtLapDung());
				}

				XSSFCell cell18 = row.createCell(18);
				cell18.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getGtSlHtTtLapBts() == null || obj1.get(j).getGtSlHtTtLapBts() == (float) 0) {
					obj1.get(j).setGtSlHtTtLapBts((float) 0);
					cell18.setCellValue(obj1.get(j).getGtSlHtTtLapBts());
				} else {
					cell18.setCellValue(obj1.get(j).getGtSlHtTtLapBts());
				}

				XSSFCell cell19 = row.createCell(19);
				cell19.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getGtSlHtTtKhac() == null || obj1.get(j).getGtSlHtTtKhac() == (float) 0) {
					obj1.get(j).setGtSlHtTtKhac((float) 0);
					cell19.setCellValue(obj1.get(j).getGtSlHtTtKhac());
				} else {
					cell19.setCellValue(obj1.get(j).getGtSlHtTtKhac());
				}

				XSSFCell cell20 = row.createCell(20);
				if (obj1.get(j).getGtDnQtkCnNgay() != null) {
					cell20.setCellValue(dt.format(obj1.get(j).getGtDnQtkCnNgay()));
				} else {
					cell20.setCellValue("");
				}

				XSSFCell cell21 = row.createCell(21);
				cell21.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getGtDnQtkCnTong() == null || obj1.get(j).getGtDnQtkCnTong() == (float) 0) {
					obj1.get(j).setGtDnQtkCnTong((float) 0);
					cell21.setCellValue(obj1.get(j).getGtDnQtkCnTong());
				} else {
					cell21.setCellValue(obj1.get(j).getGtDnQtkCnTong());
				}

				XSSFCell cell22 = row.createCell(22);
				cell22.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getGtDnQtkCnXd() == null || obj1.get(j).getGtDnQtkCnXd() == (float) 0) {
					obj1.get(j).setGtDnQtkCnXd((float) 0);
					cell22.setCellValue(obj1.get(j).getGtDnQtkCnXd());
				} else {
					cell22.setCellValue(obj1.get(j).getGtDnQtkCnXd());
				}

				XSSFCell cell23 = row.createCell(23);
				cell23.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getGtDnQtkCnDien() == null || obj1.get(j).getGtDnQtkCnDien() == (float) 0) {
					obj1.get(j).setGtDnQtkCnDien((float) 0);
					cell23.setCellValue(obj1.get(j).getGtDnQtkCnDien());
				} else {
					cell23.setCellValue(obj1.get(j).getGtDnQtkCnDien());
				}

				XSSFCell cell24 = row.createCell(24);
				cell24.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getGtDnQtkCnLapDung() == null || obj1.get(j).getGtDnQtkCnLapDung() == (float) 0) {
					obj1.get(j).setGtDnQtkCnLapDung((float) 0);
					cell24.setCellValue(obj1.get(j).getGtDnQtkCnLapDung());
				} else {
					cell24.setCellValue(obj1.get(j).getGtDnQtkCnLapDung());
				}

				XSSFCell cell25 = row.createCell(25);
				cell25.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getGtDnQtkCnLapBts() == null || obj1.get(j).getGtDnQtkCnLapBts() == (float) 0) {
					obj1.get(j).setGtDnQtkCnLapBts((float) 0);
					cell25.setCellValue(obj1.get(j).getGtDnQtkCnLapBts());
				} else {
					cell25.setCellValue(obj1.get(j).getGtDnQtkCnLapBts());
				}

				XSSFCell cell26 = row.createCell(26);
				cell26.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getGtDnQtkCnKhac() == null || obj1.get(j).getGtDnQtkCnKhac() == (float) 0) {
					obj1.get(j).setGtDnQtkCnKhac((float) 0);
					cell26.setCellValue(obj1.get(j).getGtDnQtkCnKhac());
				} else {
					cell26.setCellValue(obj1.get(j).getGtDnQtkCnKhac());
				}

				XSSFCell cell27 = row.createCell(27);
				cell27.setCellValue(obj1.get(j).getGtDnQtkCnNguoiLap());

				XSSFCell cell28 = row.createCell(28);
				if (obj1.get(j).getGtQtkPtkNgay() != null) {
					cell28.setCellValue(dt.format(obj1.get(j).getGtQtkPtkNgay()));
				} else {
					cell28.setCellValue("");
				}

				XSSFCell cell29 = row.createCell(29);
				cell29.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getGtQtkPtkTong() == null || obj1.get(j).getGtQtkPtkTong() == (float) 0) {
					obj1.get(j).setGtQtkPtkTong((float) 0);
					cell29.setCellValue(obj1.get(j).getGtQtkPtkTong());
				} else {
					cell29.setCellValue(obj1.get(j).getGtQtkPtkTong());
				}

				XSSFCell cell30 = row.createCell(30);
				cell30.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getGtQtkPtkXd() == null || obj1.get(j).getGtQtkPtkXd() == (float) 0) {
					obj1.get(j).setGtQtkPtkXd((float) 0);
					cell30.setCellValue(obj1.get(j).getGtQtkPtkXd());
				} else {
					cell30.setCellValue(obj1.get(j).getGtQtkPtkXd());
				}

				XSSFCell cell31 = row.createCell(31);
				cell31.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getGtQtkPtkDien() == null || obj1.get(j).getGtQtkPtkDien() == (float) 0) {
					obj1.get(j).setGtQtkPtkDien((float) 0);
					cell31.setCellValue(obj1.get(j).getGtQtkPtkDien());
				} else {
					cell31.setCellValue(obj1.get(j).getGtQtkPtkDien());
				}

				XSSFCell cell32 = row.createCell(32);
				cell32.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getGtQtkPtkLapDung() == null || obj1.get(j).getGtQtkPtkLapDung() == (float) 0) {
					obj1.get(j).setGtQtkPtkLapDung((float) 0);
					cell32.setCellValue(obj1.get(j).getGtQtkPtkLapDung());
				} else {
					cell32.setCellValue(obj1.get(j).getGtQtkPtkLapDung());
				}

				XSSFCell cell33 = row.createCell(33);
				cell33.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getGtQtkPtkLapBts() == null || obj1.get(j).getGtQtkPtkLapBts() == (float) 0) {
					obj1.get(j).setGtQtkPtkLapBts((float) 0);
					cell33.setCellValue(obj1.get(j).getGtQtkPtkLapBts());
				} else {
					cell33.setCellValue(obj1.get(j).getGtQtkPtkLapBts());
				}

				XSSFCell cell34 = row.createCell(34);
				cell34.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getGtQtkPtkKhac() == null || obj1.get(j).getGtQtkPtkKhac() == (float) 0) {
					obj1.get(j).setGtQtkPtkKhac((float) 0);
					cell34.setCellValue(obj1.get(j).getGtQtkPtkKhac());
				} else {
					cell34.setCellValue(obj1.get(j).getGtQtkPtkKhac());
				}

				XSSFCell cell35 = row.createCell(35);
				cell35.setCellValue(obj1.get(j).getGtQtkPtkNguoiChot());

				XSSFCell cell36 = row.createCell(36);
				cell36.setCellValue(obj1.get(j).getGtQtkPtkDmtt());

				XSSFCell cell37 = row.createCell(37);
				cell37.setCellValue(obj1.get(j).getGtQtkPtkThangQtk());

				XSSFCell cell38 = row.createCell(38);
				cell38.setCellValue(obj1.get(j).getTinhTrangCct1());

				XSSFCell cell39 = row.createCell(39);
				cell39.setCellValue(obj1.get(j).getGhiChuHsLoi());

				XSSFCell cell40 = row.createCell(40);
				if (obj1.get(j).getQtDtNgay() != null) {
					cell40.setCellValue(dt.format(obj1.get(j).getQtDtNgay()));
				} else {
					cell40.setCellValue("");
				}

				XSSFCell cell41 = row.createCell(41);
				cell41.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getQtDtGtDnDt() == null || obj1.get(j).getQtDtGtDnDt() == (float) 0) {
					obj1.get(j).setQtDtGtDnDt((float) 0);
					cell41.setCellValue(obj1.get(j).getQtDtGtDnDt());
				} else {
					cell41.setCellValue(obj1.get(j).getQtDtGtDnDt());
				}

				XSSFCell cell42 = row.createCell(42);
				cell42.setCellValue(obj1.get(j).getQtDtSoHd());

				XSSFCell cell43 = row.createCell(43);
				if (obj1.get(j).getQtDtNgayTdDt() != null) {
					cell43.setCellValue(dt.format(obj1.get(j).getQtDtNgayTdDt()));
				} else {
					cell43.setCellValue("");
				}

				XSSFCell cell44 = row.createCell(44);
				cell44.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getQtDtTong() == null || obj1.get(j).getQtDtTong() == (float) 0) {
					obj1.get(j).setQtDtTong((float) 0);
					cell44.setCellValue(obj1.get(j).getQtDtTong());
				} else {
					cell44.setCellValue(obj1.get(j).getQtDtTong());
				}

				XSSFCell cell45 = row.createCell(45);
				cell45.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getQtDtXd() == null || obj1.get(j).getQtDtXd() == (float) 0) {
					obj1.get(j).setQtDtXd((float) 0);
					cell45.setCellValue(obj1.get(j).getQtDtXd());
				} else {
					cell45.setCellValue(obj1.get(j).getQtDtXd());
				}

				XSSFCell cell46 = row.createCell(46);
				cell46.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getQtDtDien() == null || obj1.get(j).getQtDtDien() == (float) 0) {
					obj1.get(j).setQtDtDien((float) 0);
					cell46.setCellValue(obj1.get(j).getQtDtDien());
				} else {
					cell46.setCellValue(obj1.get(j).getQtDtDien());
				}

				XSSFCell cell47 = row.createCell(47);
				cell47.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getQtDtLapDung() == null || obj1.get(j).getQtDtLapDung() == (float) 0) {
					obj1.get(j).setQtDtLapDung((float) 0);
					cell47.setCellValue(obj1.get(j).getQtDtLapDung());
				} else {
					cell47.setCellValue(obj1.get(j).getQtDtLapDung());
				}

				XSSFCell cell48 = row.createCell(48);
				cell48.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getQtDtLapBts() == null || obj1.get(j).getQtDtLapBts() == (float) 0) {
					obj1.get(j).setQtDtLapBts((float) 0);
					cell48.setCellValue(obj1.get(j).getQtDtLapBts());
				} else {
					cell48.setCellValue(obj1.get(j).getQtDtLapBts());
				}

				XSSFCell cell49 = row.createCell(49);
				cell49.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getQtDtKhac() == null || obj1.get(j).getQtDtKhac() == (float) 0) {
					obj1.get(j).setQtDtKhac((float) 0);
					cell49.setCellValue(obj1.get(j).getQtDtKhac());
				} else {
					cell49.setCellValue(obj1.get(j).getQtDtKhac());
				}

				XSSFCell cell50 = row.createCell(50);
				cell50.setCellValue(obj1.get(j).getQtDtTtDt());

				XSSFCell cell51 = row.createCell(51);
				cell51.setCellValue(obj1.get(j).getQtDtNguoiTd());

				XSSFCell cell52 = row.createCell(52);
				if (obj1.get(j).getDnQtCdtNgay() != null) {
					cell52.setCellValue(dt.format(obj1.get(j).getDnQtCdtNgay()));
				} else {
					cell52.setCellValue("");
				}

				XSSFCell cell53 = row.createCell(53);
				cell53.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getDnQtCdtGt() == null || obj1.get(j).getDnQtCdtGt() == (float) 0) {
					obj1.get(j).setDnQtCdtGt((float) 0);
					cell53.setCellValue(obj1.get(j).getDnQtCdtGt());
				} else {
					cell53.setCellValue(obj1.get(j).getDnQtCdtGt());
				}

				XSSFCell cell54 = row.createCell(54);
				cell54.setCellValue(obj1.get(j).getDnQtCdtNguoiLap());

				XSSFCell cell55 = row.createCell(55);
				cell55.setCellValue(obj1.get(j).getDnQtCdtNguoiNhanBg());

				XSSFCell cell56 = row.createCell(56);
				cell56.setCellValue(obj1.get(j).getDnQtCdtNoiDungPsCtk());

				XSSFCell cell57 = row.createCell(57);
				cell57.setCellValue(obj1.get(j).getDnQtCdtKtk());

				XSSFCell cell58 = row.createCell(58);
				if (obj1.get(j).getTdQtCdtNgayChot() != null) {
					cell58.setCellValue(dt.format(obj1.get(j).getTdQtCdtNgayChot()));
				} else {
					cell58.setCellValue("");
				}

				XSSFCell cell59 = row.createCell(59);
				cell59.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getTdQtCdtGt() == null || obj1.get(j).getTdQtCdtGt() == (float) 0) {
					obj1.get(j).setTdQtCdtGt((float) 0);
					cell59.setCellValue(obj1.get(j).getTdQtCdtGt());
				} else {
					cell59.setCellValue(obj1.get(j).getTdQtCdtGt());
				}

				XSSFCell cell60 = row.createCell(60);
				cell60.setCellValue(obj1.get(j).getTdQtCdtNguoiChotMvt());

				XSSFCell cell61 = row.createCell(61);
				cell61.setCellValue(obj1.get(j).getTdQtCdtNguoiTdCdtMvt());

				XSSFCell cell62 = row.createCell(62);
				if (obj1.get(j).getTdQtCdtNgayBanTd() != null) {
					cell62.setCellValue(dt.format(obj1.get(j).getTdQtCdtNgayBanTd()));
				} else {
					cell62.setCellValue("");
				}

				XSSFCell cell63 = row.createCell(63);
				if (obj1.get(j).getTdQtCdtNgayCtc() != null) {
					cell63.setCellValue(dt.format(obj1.get(j).getTdQtCdtNgayCtc()));
				} else {
					cell63.setCellValue("");
				}

				XSSFCell cell64 = row.createCell(64);
				cell64.setCellValue(obj1.get(j).getTdQtCdtNoiDung());

				XSSFCell cell65 = row.createCell(65);
				cell65.setCellValue(obj1.get(j).getTinhTrangCct2());

				XSSFCell cell66 = row.createCell(66);
				cell66.setCellValue(obj1.get(j).getGhiChu2());

				XSSFCell cell67 = row.createCell(67);
				cell67.setCellValue(obj1.get(j).getLuuTaiKho());

				XSSFCell cell68 = row.createCell(68);
				cell68.setCellValue(obj1.get(j).getTrangThai());

				XSSFCell cell69 = row.createCell(69);
				cell69.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getTblQlCvPtkId() == null || obj1.get(j).getTblQlCvPtkId() == (long) 0) {
					obj1.get(j).setTblQlCvPtkId((long) 0);
					cell69.setCellValue(obj1.get(j).getTblQlCvPtkId());
				} else {
					cell69.setCellValue(obj1.get(j).getTblQlCvPtkId());
				}

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
				cell14.setCellStyle(styleNumber);
				cell15.setCellStyle(styleNumber);
				cell16.setCellStyle(styleNumber);
				cell17.setCellStyle(styleNumber);
				cell18.setCellStyle(styleNumber);
				cell19.setCellStyle(styleNumber);
				cell20.setCellStyle(style);
				cell21.setCellStyle(styleNumber);
				cell22.setCellStyle(styleNumber);
				cell23.setCellStyle(styleNumber);
				cell24.setCellStyle(styleNumber);
				cell25.setCellStyle(styleNumber);
				cell26.setCellStyle(styleNumber);
				cell27.setCellStyle(style);
				cell28.setCellStyle(style);
				cell29.setCellStyle(styleNumber);
				cell30.setCellStyle(styleNumber);
				cell31.setCellStyle(styleNumber);
				cell32.setCellStyle(styleNumber);
				cell33.setCellStyle(styleNumber);
				cell34.setCellStyle(styleNumber);
				cell35.setCellStyle(style);
				cell36.setCellStyle(style);
				cell37.setCellStyle(style);
				cell38.setCellStyle(style);
				cell39.setCellStyle(style);
				cell40.setCellStyle(style);
				cell41.setCellStyle(styleNumber);
				cell42.setCellStyle(style);
				cell43.setCellStyle(style);
				cell44.setCellStyle(styleNumber);
				cell45.setCellStyle(styleNumber);
				cell46.setCellStyle(styleNumber);
				cell47.setCellStyle(styleNumber);
				cell48.setCellStyle(styleNumber);
				cell49.setCellStyle(styleNumber);
				cell50.setCellStyle(style);
				cell51.setCellStyle(style);
				cell52.setCellStyle(style);
				cell53.setCellStyle(styleNumber);
				cell54.setCellStyle(style);
				cell55.setCellStyle(style);
				cell56.setCellStyle(style);
				cell57.setCellStyle(style);
				cell58.setCellStyle(style);
				cell59.setCellStyle(styleNumber);
				cell60.setCellStyle(style);
				cell61.setCellStyle(style);
				cell62.setCellStyle(style);
				cell63.setCellStyle(style);
				cell64.setCellStyle(style);
				cell65.setCellStyle(style);
				cell66.setCellStyle(style);
				cell67.setCellStyle(style);
				cell68.setCellStyle(style);
				cell69.setCellStyle(styleNumber);

			}

			File out = new File(folder2Upload + File.separatorChar + "DSCvPtk" + startExportTime + ".xlsx");

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
		return UEncrypt.encryptFileUploadPath("DSCvPtk" + startExportTime + ".xlsx");
	}
	
	@SuppressWarnings("deprecation")
	public String reportForQTK(TblQlCvPtkDTO obj, HttpServletRequest request)throws Exception{
		
	
		List<TblQlCvPtkDTO> listCnkv = tblQlCvPtkDAO.reportForQTK(obj);

		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
	
		
		
		try{
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream fileIn = new BufferedInputStream(new FileInputStream(filePath + "exportBaoCaoQTK.xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(fileIn);
			XSSFSheet sheet = workbook.getSheetAt(0);
			DataFormat format = workbook.createDataFormat();
			XSSFCellStyle style = workbook.createCellStyle();
			XSSFCellStyle style1 = workbook.createCellStyle();
			XSSFCellStyle style2 = workbook.createCellStyle();
			XSSFCellStyle style3 = workbook.createCellStyle();
			XSSFCellStyle style4 = workbook.createCellStyle();
			XSSFCellStyle styleNumber = workbook.createCellStyle();
			XSSFFont hSSFFont = workbook.createFont();
			hSSFFont.setFontName("Times New Roman");
			XSSFFont hSSFFont2 = workbook.createFont();
			hSSFFont2.setFontName("Times New Roman");
			hSSFFont2.setItalic(true);
			try {
				XSSFFont hSSFFont1 = workbook.createFont();
				hSSFFont1.setFontName("Times New Roman");
				hSSFFont1.setFontHeightInPoints((short) 12);
				hSSFFont1.setBold(true);
				// style1.setVerticalAlignment(VerticalAlignment.CENTER);
				style1.setFont(hSSFFont1);
				style1.setWrapText(true);
				style1.setAlignment(CellStyle.ALIGN_CENTER);
				style3.setFont(hSSFFont1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			style4.setFont(hSSFFont2);
			style4.setAlignment(CellStyle.ALIGN_CENTER);
			style.setFont(hSSFFont);
			style.setBorderBottom(BorderStyle.THIN);
			style.setBorderTop(BorderStyle.THIN);
			style.setBorderRight(BorderStyle.THIN);
			style.setBorderLeft(BorderStyle.THIN);
			style.setWrapText(true);
			style2.setBorderBottom(BorderStyle.THIN);
			style2.setBorderLeft(BorderStyle.THIN);

			styleNumber.setBorderBottom(BorderStyle.THIN);
			styleNumber.setBorderTop(BorderStyle.THIN);
			styleNumber.setBorderRight(BorderStyle.THIN);
			styleNumber.setBorderLeft(BorderStyle.THIN);
			styleNumber.setDataFormat(format.getFormat("#,##0"));
			styleNumber.setWrapText(true);
			styleNumber.setAlignment(CellStyle.ALIGN_RIGHT);

			XSSFRow row = null;

			int rownum = 3;
			for(int i = 0; i < listCnkv.size(); i++){
				row = sheet.createRow(rownum);
				XSSFCell cell0 = row.createCell(0);
				cell0.setCellValue(i + 1);
				cell0.setCellStyle(style);
				rownum++;
				
				XSSFCell cell1 = row.createCell(1);
				cell1.setCellValue(listCnkv.get(i).getCnkv());
				
				XSSFCell cell2 = row.createCell(2);
				cell2.setCellValue(listCnkv.get(i).getMttMaViTri());
				
				XSSFCell cell3 = row.createCell(3);
				cell3.setCellValue(listCnkv.get(i).getSoHdCdt());
				
				
				XSSFCell cell4 = row.createCell(4);
				cell4.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if(listCnkv.get(i).getGtSlHtTtTong()==null){
					cell4.setCellValue("");
				}else{
					cell4.setCellValue(listCnkv.get(i).getGtSlHtTtTong());
				}
				
				
				XSSFCell cell5 = row.createCell(5);
				cell5.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if(listCnkv.get(i).getGtQtkPtkTong()==null){
					cell5.setCellValue("");
				}else{
					cell5.setCellValue(listCnkv.get(i).getGtQtkPtkTong());
				}
				
				
				XSSFCell cell6 = row.createCell(6);
				cell6.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if(listCnkv.get(i).getDnQtCdtGt()==null){
					cell6.setCellValue("");
				}else{
					cell6.setCellValue(listCnkv.get(i).getDnQtCdtGt());
				}
				
				
				XSSFCell cell7 = row.createCell(7);
				cell7.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if(listCnkv.get(i).getTdQtCdtGt()==null){
					cell7.setCellValue("");
				}else{
					cell7.setCellValue(listCnkv.get(i).getTdQtCdtGt());
				}
				
				
				XSSFCell cell8 = row.createCell(8);
				cell8.setCellValue(listCnkv.get(i).getGhiChuHsLoi());
				
				XSSFCell cell9 = row.createCell(9);
				cell9.setCellValue(listCnkv.get(i).getGtDnQtkCnNguoiLap());
				
				XSSFCell cell10 = row.createCell(10);
				cell10.setCellValue(listCnkv.get(i).getGtQtkPtkNguoiChot());
				
				
				
				
				cell1.setCellStyle(style);
				cell2.setCellStyle(style);
				cell3.setCellStyle(style);
				cell4.setCellStyle(styleNumber);
				cell5.setCellStyle(styleNumber);
				cell6.setCellStyle(styleNumber);
				cell7.setCellStyle(styleNumber);
				cell8.setCellStyle(style);
				cell9.setCellStyle(style);
				cell10.setCellStyle(style);
			
				
				
			}
			
			fileIn.close();

			File out = new File(folder2Upload + File.separatorChar + "exportBaoCaoQTK" + startExportTime + ".xlsx");

			FileOutputStream outFile = new FileOutputStream(out);
			System.out.println(out.getCanonicalPath());
			workbook.write(outFile);
			workbook.close();
			outFile.close();
			
		}catch (FileNotFoundException e) {
			log.error(e.getMessage(), e);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
		return UEncrypt.encryptFileUploadPath("exportBaoCaoQTK" + startExportTime + ".xlsx");
	}
	
	@SuppressWarnings("deprecation")
	public String reportTotal( TblQlCvPtkDTO obj,HttpServletRequest request)throws Exception{
		
//		List<TblQlCvPtkDTO> listShd = tblQlCvPtkDAO.reportTotal();
		List<TblQlCvPtkDTO> newList = Lists.newArrayList(); 
		List<TblQlCvPtkDTO> lstShdCdt = tblQlCvPtkDAO.getForSoHdCdt(obj);
		
		for(int i =0; i<lstShdCdt.size();i++){
			TblQlCvPtkDTO objSearch = new TblQlCvPtkDTO();
			TblQlCvPtkDTO newObj = new TblQlCvPtkDTO();
			objSearch.setSoHdCdt(lstShdCdt.get(i).getSoHdCdt());
			objSearch.setNgayNhanHshc(obj.getNgayNhanHshc());
			objSearch.setNgayNhanHshcFrom(obj.getNgayNhanHshcFrom());
			objSearch.setNgayNhanHshcTo(obj.getNgayNhanHshcTo());
			
			List<TblQlCvPtkDTO> lstObj = tblQlCvPtkDAO.BCTongHop(objSearch);
			
			long soLuongHD = lstShdCdt.get(i).getSoLuongHD();
			
			long soLuongGtHt = lstObj.stream().filter(x->x.getGtSlHtTtTong()!=0).collect(Collectors.counting());
			float sumGtHt = lstObj.stream().map(x->x.getGtSlHtTtTong()).reduce((float) 0, Float::sum);
			
			long soLuongDnQtk = lstObj.stream().filter(x->x.getGtDnQtkCnTong()!=0).collect(Collectors.counting());
			float gtHtWithDnQtk = lstObj.stream().filter(x->x.getGtDnQtkCnTong()!=0).map(x->x.getGtSlHtTtTong()).reduce((float) 0, Float::sum);
			float sumDnQtk = lstObj.stream().map(x->x.getGtDnQtkCnTong()).reduce((float) 0, Float::sum);
			
			long soLuongTdQtk = lstObj.stream().filter(x->x.getGtQtkPtkTong()!=0).collect(Collectors.counting());
			float gtHtWithTdQtk = lstObj.stream().filter(x->x.getGtQtkPtkTong()!=0).map(x->x.getGtSlHtTtTong()).reduce((float) 0, Float::sum);
			float sumTdQtk = lstObj.stream().map(x->x.getGtQtkPtkTong()).reduce((float) 0, Float::sum);
			
			long soLuongDnCdt = lstObj.stream().filter(x->x.getDnQtCdtGt()!=0).collect(Collectors.counting());
			float gtHtWithDnCdt = lstObj.stream().filter(x->x.getDnQtCdtGt()!=0).map(x->x.getGtSlHtTtTong()).reduce((float) 0, Float::sum);
			float sumDnCdt = lstObj.stream().map(x->x.getDnQtCdtGt()).reduce((float) 0, Float::sum);
			
			long soLuongTdCdt = lstObj.stream().filter(x->x.getTdQtCdtGt()!=0).collect(Collectors.counting());
			float gtHtWithTdCdt = lstObj.stream().filter(x->x.getTdQtCdtGt()!=0).map(x->x.getGtSlHtTtTong()).reduce((float) 0, Float::sum);
			float sumTdCdt = lstObj.stream().map(x->x.getTdQtCdtGt()).reduce((float) 0, Float::sum);
			
			long soLuongQtDt = lstObj.stream().filter(x->x.getQtDtTong()!=0).collect(Collectors.counting());
			float gtHtWithQtDt = lstObj.stream().filter(x->x.getQtDtTong()!=0).map(x->x.getGtSlHtTtTong()).reduce((float) 0, Float::sum);
			float sumQtDt = lstObj.stream().map(x->x.getQtDtTong()).reduce((float) 0, Float::sum);
			String maTram ="";
			if(objSearch.getSoHdCdt().equals(null) || objSearch.getSoHdCdt().equals("Chưa thấy số hợp đồng")){
				List<String> lstMaTram = lstObj.stream().map(x->x.getMttMaViTri()).collect(Collectors.toList());
				
				for( String s : lstMaTram){
					maTram += s + ",";
				}
			}
		
			
			if(objSearch.getSoHdCdt().equals(null) || objSearch.getSoHdCdt().equals("Chưa thấy số hợp đồng")){
				newObj.setSoHdCdt("Chưa thấy số hợp đồng");
				newObj.setMttMaViTri(maTram);
			}else{
				newObj.setSoHdCdt(objSearch.getSoHdCdt());
			}
			
			newObj.setSoLuongHD(soLuongHD);
			
			newObj.setSoLuongGtHt(soLuongGtHt);
			newObj.setSumGtHt(sumGtHt);
			
			newObj.setSoLuongDnQtk(soLuongDnQtk);
			newObj.setGtHtWithDnQtk(gtHtWithDnQtk);
			newObj.setSumDnQtk(sumDnQtk);
			
			newObj.setSoLuongTdQtk(soLuongTdQtk);
			newObj.setGtHtWithTdQtk(gtHtWithTdQtk);
			newObj.setSumTdQtk(sumTdQtk);
			
			newObj.setSoLuongDnCdt(soLuongDnCdt);
			newObj.setGtHtWithDnCdt(gtHtWithDnCdt);
			newObj.setSumDnCdt(sumDnCdt);
			
			newObj.setSoLuongTdCdt(soLuongTdCdt);
			newObj.setGtHtWithTdCdt(gtHtWithTdCdt);
			newObj.setSumTdCdt(sumTdCdt);
			
			newObj.setSoLuongQtDt(soLuongQtDt);
			newObj.setGtHtWithQtDt(gtHtWithQtDt);
			newObj.setSumQtDt(sumQtDt);
			
			newList.add(newObj);
			
		}
		
		
		
		
		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
//		
		
		
		try{
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream fileIn = new BufferedInputStream(new FileInputStream(filePath + "exportBaoCaoTong.xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(fileIn);
			XSSFSheet sheet = workbook.getSheetAt(0);
			DataFormat format = workbook.createDataFormat();
			XSSFCellStyle style = workbook.createCellStyle();
			XSSFCellStyle style1 = workbook.createCellStyle();
			XSSFCellStyle style2 = workbook.createCellStyle();
			XSSFCellStyle style3 = workbook.createCellStyle();
			XSSFCellStyle style4 = workbook.createCellStyle();
			XSSFCellStyle styleNumber = workbook.createCellStyle();
			XSSFFont hSSFFont = workbook.createFont();
			hSSFFont.setFontName("Times New Roman");
			XSSFFont hSSFFont2 = workbook.createFont();
			hSSFFont2.setFontName("Times New Roman");
			hSSFFont2.setItalic(true);
			try {
				XSSFFont hSSFFont1 = workbook.createFont();
				hSSFFont1.setFontName("Times New Roman");
				hSSFFont1.setFontHeightInPoints((short) 12);
				hSSFFont1.setBold(true);
				// style1.setVerticalAlignment(VerticalAlignment.CENTER);
				style1.setFont(hSSFFont1);
				style1.setWrapText(true);
				style1.setAlignment(CellStyle.ALIGN_CENTER);
				style3.setFont(hSSFFont1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			style4.setFont(hSSFFont2);
			style4.setAlignment(CellStyle.ALIGN_CENTER);
			style.setFont(hSSFFont);
			style.setBorderBottom(BorderStyle.THIN);
			style.setBorderTop(BorderStyle.THIN);
			style.setBorderRight(BorderStyle.THIN);
			style.setBorderLeft(BorderStyle.THIN);
			style.setWrapText(true);
			style2.setBorderBottom(BorderStyle.THIN);
			style2.setBorderLeft(BorderStyle.THIN);

			styleNumber.setBorderBottom(BorderStyle.THIN);
			styleNumber.setBorderTop(BorderStyle.THIN);
			styleNumber.setBorderRight(BorderStyle.THIN);
			styleNumber.setBorderLeft(BorderStyle.THIN);
			styleNumber.setDataFormat(format.getFormat("#,##0"));
			styleNumber.setWrapText(true);
			styleNumber.setAlignment(CellStyle.ALIGN_RIGHT);

			XSSFRow row = null;

			int rownum = 3;
			for(int i = 0; i < newList.size(); i++){
				row = sheet.createRow(rownum);
				XSSFCell cell0 = row.createCell(0);
				cell0.setCellValue(i + 1);
				cell0.setCellStyle(style);
				rownum++;
				
				XSSFCell cell1 = row.createCell(1);
				if(newList.get(i).getSoHdCdt()==null){
					cell1.setCellValue("Không có số HD");
				}else{
					cell1.setCellValue(newList.get(i).getSoHdCdt());
				}
				
				XSSFCell cell2 = row.createCell(2);
				cell2.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				cell2.setCellValue(newList.get(i).getSoLuongHD());
				
				
				
				XSSFCell cell3 = row.createCell(3);
				cell3.setCellValue("");
				
				XSSFCell cell4 = row.createCell(4);
				cell4.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				cell4.setCellValue(newList.get(i).getSoLuongGtHt());
				
				XSSFCell cell5 = row.createCell(5);
				cell5.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				cell5.setCellValue(newList.get(i).getSumGtHt());
				
				XSSFCell cell6 = row.createCell(6);
				cell6.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				cell6.setCellValue(newList.get(i).getSoLuongDnQtk());
				
				XSSFCell cell7 = row.createCell(7);
				cell7.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				cell7.setCellValue(newList.get(i).getGtHtWithDnQtk());
				
				XSSFCell cell8 = row.createCell(8);
				cell8.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				cell8.setCellValue(newList.get(i).getSumDnQtk());
				
				XSSFCell cell9 = row.createCell(9);
				cell9.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				cell9.setCellValue(newList.get(i).getSoLuongTdQtk());
				
				XSSFCell cell10 = row.createCell(10);
				cell10.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				cell10.setCellValue(newList.get(i).getGtHtWithTdQtk());
				
				XSSFCell cell11 = row.createCell(11);
				cell11.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				cell11.setCellValue(newList.get(i).getSumTdQtk());
				
				XSSFCell cell12 = row.createCell(12);
				cell12.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				cell12.setCellValue(newList.get(i).getSoLuongDnCdt());
				
				XSSFCell cell13 = row.createCell(13);
				cell13.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				cell13.setCellValue(newList.get(i).getGtHtWithDnCdt());
				
				XSSFCell cell14 = row.createCell(14);
				cell14.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				cell14.setCellValue(newList.get(i).getSumDnCdt());
				
				XSSFCell cell15 = row.createCell(15);
				cell15.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				cell15.setCellValue(newList.get(i).getSoLuongTdCdt());
				
				XSSFCell cell16 = row.createCell(16);
				cell16.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				cell16.setCellValue(newList.get(i).getGtHtWithTdCdt());
				
				XSSFCell cell17 = row.createCell(17);
				cell17.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				cell17.setCellValue(newList.get(i).getSumTdCdt());
				
				XSSFCell cell18 = row.createCell(18);
				cell18.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				cell18.setCellValue(newList.get(i).getSoLuongQtDt());
				
				XSSFCell cell19 = row.createCell(19);
				cell19.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				cell19.setCellValue(newList.get(i).getGtHtWithQtDt());
				
				XSSFCell cell20 = row.createCell(20);
				cell20.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				cell20.setCellValue(newList.get(i).getSumQtDt());
				
				XSSFCell cell21 = row.createCell(21);
				cell21.setCellValue("");
				
				XSSFCell cell22 = row.createCell(22);
				cell22.setCellValue("");
				
				XSSFCell cell23 = row.createCell(23);
				if(newList.get(i).getMttMaViTri()!=null){
					
					cell23.setCellValue(newList.get(i).getMttMaViTri());
				}else{
					
					cell23.setCellValue("");
				}
				
				
				cell1.setCellStyle(style);
				cell2.setCellStyle(styleNumber);
				cell3.setCellStyle(style);
				cell4.setCellStyle(styleNumber);
				cell5.setCellStyle(styleNumber);
				cell6.setCellStyle(styleNumber);
				cell7.setCellStyle(styleNumber);
				cell8.setCellStyle(styleNumber);
				cell9.setCellStyle(styleNumber);
				cell10.setCellStyle(styleNumber);
				cell11.setCellStyle(styleNumber);
				cell12.setCellStyle(styleNumber);
				cell13.setCellStyle(styleNumber);
				cell14.setCellStyle(styleNumber);
				cell15.setCellStyle(styleNumber);
				cell16.setCellStyle(styleNumber);
				cell17.setCellStyle(styleNumber);
				cell18.setCellStyle(styleNumber);
				cell19.setCellStyle(styleNumber);
				cell20.setCellStyle(styleNumber);
				cell21.setCellStyle(style);
				cell22.setCellStyle(style);
				cell23.setCellStyle(style);
				
			}
			
			fileIn.close();

			File out = new File(folder2Upload + File.separatorChar + "exportBaoCaoTong" + startExportTime + ".xlsx");

			FileOutputStream outFile = new FileOutputStream(out);
			System.out.println(out.getCanonicalPath());
			workbook.write(outFile);
			workbook.close();
			outFile.close();
			
		}catch (FileNotFoundException e) {
			log.error(e.getMessage(), e);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
		return UEncrypt.encryptFileUploadPath("exportBaoCaoTong" + startExportTime + ".xlsx");
	}
	public TblQlCvPtkDTO getChart(TblQlCvPtkDTO obj,HttpServletRequest request) throws Exception{
		TblQlCvPtkDTO newObj = new TblQlCvPtkDTO();
		TblQlCvPtkDTO objSearch = new TblQlCvPtkDTO();
		List<TblQlCvPtkDTO> lstCnkv = tblQlCvPtkDAO.getForCNKV(obj);
		for(int i =0; i <lstCnkv.size();i++){
			objSearch.getListCnkv().add(lstCnkv.get(i).getCnkv());			
		}
		objSearch.setNgayNhanHshc(obj.getNgayNhanHshc());
		objSearch.setNgayNhanHshcFrom(obj.getNgayNhanHshcFrom());
		objSearch.setNgayNhanHshcTo(obj.getNgayNhanHshcTo());
		objSearch.setSoHdCdt(obj.getSoHdCdt());
		List<TblQlCvPtkDTO> lstObj = tblQlCvPtkDAO.reportChart(objSearch);
		
		for(Iterator<TblQlCvPtkDTO> interator = lstObj.iterator(); interator.hasNext();){
			TblQlCvPtkDTO reportChart = interator.next();
			newObj.getListCnkv().add(reportChart.getCnkv());
			newObj.getListGtSlHtTtTong().add(reportChart.getSumGtHt());
			newObj.getListDnQtCdtGt().add(reportChart.getSumDnCdt());
			newObj.getListTdQtCdtGt().add(reportChart.getSumTdCdt());
			newObj.getListGtSlHtTtTongDn().add(reportChart.getGtHtWithDnCdt());
			newObj.getListGtSlHtTtTongTd().add(reportChart.getGtHtWithTdCdt());
			newObj.getListSoLuongMaTramGtSl().add(reportChart.getSoLuongGtHt());
			newObj.getListSoLuongMaTramDnQtCdt().add(reportChart.getSoLuongDnCdt());
			newObj.getListSoLuongMaTramTdQtCdt().add(reportChart.getSoLuongTdCdt());
		}
		
		return newObj;
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

	public String convertValue(String value) {
		String val = value.replaceAll(",", "");
		return val;
	}
}
