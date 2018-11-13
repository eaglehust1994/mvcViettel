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

import com.google.common.collect.Lists;
import com.viettel.ktts2.common.UEncrypt;
import com.viettel.qll.bo.TblDlDauVaoDayMayBO;
import com.viettel.qll.dao.TblDanhMucDAO;
import com.viettel.qll.dao.TblDlDauVaoDayMayDAO;
import com.viettel.qll.dto.ErrExcelDTO;
import com.viettel.qll.dto.ImportErrDTO;
import com.viettel.qll.dto.LsThaoTacDTO;
import com.viettel.qll.dto.TblDlDauVaoDayMayDTO;
import com.viettel.qll.dto.TblNhanVienDTO;
import com.viettel.qll.dao.TblNhanVienDAO;
import com.viettel.qll.dto.TblPhatFcDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;
import com.viettel.utils.ValidateUtils;

import freemarker.core.ParseException;

import org.apache.commons.lang3.StringUtils;
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


@Service("tblDlDauVaoDayMayBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TblDlDauVaoDayMayBusinessImpl extends BaseFWBusinessImpl<TblDlDauVaoDayMayDAO,TblDlDauVaoDayMayDTO, TblDlDauVaoDayMayBO> implements TblDlDauVaoDayMayBusiness {

    @Autowired
    private TblDlDauVaoDayMayDAO tblDlDauVaoDayMayDAO;
    static Logger LOGGER = LoggerFactory.getLogger(TblDlDauVaoDayMayDTO.class);
	List<ErrExcelDTO> lstErrExcelDto;
	@Value("${folder_upload2}")
	private String folder2Upload;
	@Autowired
	TblNhanVienDAO tblNhanVienDAO;
	@Autowired
	TblDanhMucDAO tblDanhmucDao;
	
	LsThaoTacDTO lsThaoTacDTO = new LsThaoTacDTO();

	@Autowired
	TblQltsThucXuatTheoKyBusinessImpl businessImpl=new TblQltsThucXuatTheoKyBusinessImpl();
    public TblDlDauVaoDayMayBusinessImpl() {
        tModel = new TblDlDauVaoDayMayBO();
        tDAO = tblDlDauVaoDayMayDAO;
    }

    @Override
    public TblDlDauVaoDayMayDAO gettDAO() {
        return tblDlDauVaoDayMayDAO;
    }
	@Override
	public DataListDTO doSearch(TblDlDauVaoDayMayDTO obj, HttpServletRequest request) throws Exception {
		String err = "";

		List<TblDlDauVaoDayMayDTO> ls = tblDlDauVaoDayMayDAO.doSearch(obj);
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		data.setTotal(obj.getTotalRecord());
		data.setSize(obj.getTotalRecord());
		data.setStart(1);
		return data;
	}

	@Override
	public String importFile(String fileInput, HttpServletRequest request) throws Exception {
		List<TblDlDauVaoDayMayDTO> workLst = Lists.newArrayList();
		List<ImportErrDTO> workFault = Lists.newArrayList();
		long tStart = System.currentTimeMillis();
		lstErrExcelDto= new ArrayList<>();;
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
				if (count > 7 && !ValidateUtils.isRowEmpty(row)) {
					boolean checkkyLuong = true;
					boolean checkmaNv = true;
					boolean checkhoVaTen = true;
					boolean checktenHuyen = true;
					boolean checkdonVi = true;
					boolean checkmaTinh = true;
					boolean checkghep = true;
					boolean checkghiChu = true;
					boolean checkdoiTuong = true;
					boolean checkddtbqlDayTbkt = true;
					boolean checkddtbqlDayTbAon = true;
					boolean checkddtbqlDaToaNha = true;
					boolean checkddtbqlGponAdslPstnEoc = true;
					boolean checkddtbqlTongQuyDoi = true;
					boolean checkddtbqlHuongLuongDuyTri = true;
					boolean checktbmtbkt02 = true;
					boolean checktbmtbkt3 = true;
					boolean checktbmtbkt4 = true;
					boolean checktbmtbkt5 = true;
					boolean checktbmtbkt6 = true;
					boolean checktbmtbkt7 = true;
					boolean checktbmtbt02 = true;
					boolean checktbmtbt3 = true;
					boolean checktbmtbt4 = true;
					boolean checktbmtbt5 = true;
					boolean checktbmtbt6 = true;
					boolean checktbmtbt7 = true;
					boolean checktbmtbdv02 = true;
					boolean checktbmtbdv3 = true;
					boolean checktbmtbdv4 = true;
					boolean checktbmtbdv5 = true;
					boolean checktbmtbdv6 = true;
					boolean checktbmtbdv7 = true;
					boolean checktbmtbcs02 = true;
					boolean checktbmtbcs3 = true;
					boolean checktbmtbcs4 = true;
					boolean checktbmtbcs5 = true;
					boolean checktbmtbcs6 = true;
					boolean checktbmtbwf02 = true;
					boolean checktbmtbwf3 = true;
					boolean checktbmtbwf4 = true;
					boolean checktbmtbwf5 = true;
					boolean checktbmtbwf6 = true;
					boolean checknhomTruong = true;

					String kyLuong = "";
					String maNv = "";
					String hoVaTen = "";
					String tenHuyen = "";
					String donVi = "";
					String maTinh = "";
					String ghep = "";
					String ghiChu = "";
					String doiTuong = "";
					String ddtbqlDayTbkt = "";
					String ddtbqlDayTbAon = "";
					String ddtbqlDaToaNha = "";
					String ddtbqlGponAdslPstnEoc = "";
					String ddtbqlTongQuyDoi = "";
					String ddtbqlHuongLuongDuyTri = "";
					String tbmtbkt02 = "";
					String tbmtbkt3 = "";
					String tbmtbkt4 = "";
					String tbmtbkt5 = "";
					String tbmtbkt6 = "";
					String tbmtbkt7 = "";
					String tbmtbt02 = "";
					String tbmtbt3 = "";
					String tbmtbt4 = "";
					String tbmtbt5 = "";
					String tbmtbt6 = "";
					String tbmtbt7 = "";
					String tbmtbdv02 = "";
					String tbmtbdv3 = "";
					String tbmtbdv4 = "";
					String tbmtbdv5 = "";
					String tbmtbdv6 = "";
					String tbmtbdv7 = "";
					String tbmtbcs02 = "";
					String tbmtbcs3 = "";
					String tbmtbcs4 = "";
					String tbmtbcs5 = "";
					String tbmtbcs6 = "";
					String tbmtbwf02 = "";
					String tbmtbwf3 = "";
					String tbmtbwf4 = "";
					String tbmtbwf5 = "";
					String tbmtbwf6 = "";
					String nhomTruong = "";

					for (Cell cell : row) {
						if (cell.getColumnIndex() == 1) {
							kyLuong = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 2) {
							maNv = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 3) {
							hoVaTen = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 4) {
							donVi = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 5) {
							maTinh = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 6) {
							tenHuyen = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 7) {
							ghep = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 8) {
							doiTuong = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 9) {
							ddtbqlDayTbkt = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 10) {
							ddtbqlDayTbAon = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 11) {
							ddtbqlDaToaNha = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 12) {
							ddtbqlGponAdslPstnEoc = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 13) {
							ddtbqlTongQuyDoi = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 14) {
							ddtbqlHuongLuongDuyTri = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 15) {
							tbmtbkt02 = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 16) {
							tbmtbkt3 = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 17) {
							tbmtbkt4 = dataFormatter.formatCellValue(cell).trim();
						}else if (cell.getColumnIndex() == 18) {
							tbmtbkt5 = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 19) {
							tbmtbkt6 = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 20) {
							tbmtbkt7 = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 21) {
							tbmtbt02 = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 22) {
							tbmtbt3 = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 23) {
							tbmtbt4 = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 24) {
							tbmtbt5 = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 25) {
							tbmtbt6 = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 26) {
							tbmtbt7 = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 27) {
							tbmtbdv02 = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 28) {
							tbmtbdv3 = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 29) {
							tbmtbdv4 = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 30) {
							tbmtbdv5 = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 31) {
							tbmtbdv6 = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 32) {
							tbmtbdv7 = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 33) {
							tbmtbcs02 = dataFormatter.formatCellValue(cell).trim();
						}else if (cell.getColumnIndex() == 34) {
							tbmtbcs3 = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 35) {
							tbmtbcs4 = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 36) {
							tbmtbcs5 = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 37) {
							tbmtbcs6 = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 38) {
							tbmtbwf02 = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 39) {
							tbmtbwf3 = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 40) {
							tbmtbwf4 = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 41) {
							tbmtbwf5 = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 42) {
							tbmtbwf6 = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 43) {
							nhomTruong = dataFormatter.formatCellValue(cell).trim();
						} else if (cell.getColumnIndex() == 44) {
							ghiChu = dataFormatter.formatCellValue(cell).trim();
						}
					}
					 checkkyLuong = checkDLdauvaoDM(1, kyLuong,"", orderErrorFormat);
					 checkmaNv = checkDLdauvaoDM(2, maNv,"",  orderErrorFormat);
					 checkhoVaTen = checkDLdauvaoDM(3, hoVaTen,"",  orderErrorFormat);
					 checktenHuyen = checkDLdauvaoDM(4, tenHuyen, maTinh, orderErrorFormat);
					 checkdonVi = checkDLdauvaoDM(5, donVi,maTinh,  orderErrorFormat);
					 checkmaTinh = checkDLdauvaoDM(6, maTinh,"",  orderErrorFormat);
					 checkghep = checkDLdauvaoDM(7, ghep,"",  orderErrorFormat);
					 checkghiChu = checkDLdauvaoDM(8, ghiChu, "", orderErrorFormat);
					 checkdoiTuong = checkDLdauvaoDM(9, doiTuong,"",  orderErrorFormat);
					 checkddtbqlDayTbkt = checkDLdauvaoDM(10, ddtbqlDayTbkt,"",  orderErrorFormat);
					 checkddtbqlDayTbAon = checkDLdauvaoDM(11, ddtbqlDayTbAon,"",  orderErrorFormat);
					 checkddtbqlDaToaNha = checkDLdauvaoDM(12, ddtbqlDaToaNha,"",  orderErrorFormat);
					 checkddtbqlGponAdslPstnEoc = checkDLdauvaoDM(13, ddtbqlGponAdslPstnEoc,"",  orderErrorFormat);
					 checkddtbqlTongQuyDoi = checkDLdauvaoDM(14, ddtbqlTongQuyDoi,"",  orderErrorFormat);
					 checkddtbqlHuongLuongDuyTri = checkDLdauvaoDM(15, ddtbqlHuongLuongDuyTri,"",  orderErrorFormat);
					 checktbmtbkt02 = checkDLdauvaoDM(16, tbmtbkt02, "",orderErrorFormat);
					 checktbmtbkt3 = checkDLdauvaoDM(17, tbmtbkt3, "",orderErrorFormat);
					 checktbmtbkt4 = checkDLdauvaoDM(18, tbmtbkt4, "",orderErrorFormat);
					 checktbmtbkt5 = checkDLdauvaoDM(19, tbmtbkt5, "",orderErrorFormat);
					 checktbmtbkt6 = checkDLdauvaoDM(20, tbmtbkt6, "",orderErrorFormat);
					 checktbmtbkt7 = checkDLdauvaoDM(21, tbmtbkt7, "",orderErrorFormat);
					 checktbmtbt02 = checkDLdauvaoDM(22, tbmtbt02, "",orderErrorFormat);
					 checktbmtbt3 = checkDLdauvaoDM(23, tbmtbt3, "",orderErrorFormat);
					 checktbmtbt4 = checkDLdauvaoDM(24, tbmtbt4, "",orderErrorFormat);
					 checktbmtbt5 = checkDLdauvaoDM(25, tbmtbt5, "",orderErrorFormat);
					 checktbmtbt6 = checkDLdauvaoDM(26, tbmtbt6, "",orderErrorFormat);
					 checktbmtbt7 = checkDLdauvaoDM(27, tbmtbt7, "",orderErrorFormat);
					 checktbmtbdv02 = checkDLdauvaoDM(28, tbmtbdv02, "",orderErrorFormat);
					 checktbmtbdv3 = checkDLdauvaoDM(29, tbmtbdv3, "",orderErrorFormat);
					 checktbmtbdv4 = checkDLdauvaoDM(30, tbmtbdv4, "",orderErrorFormat);
					 checktbmtbdv5 = checkDLdauvaoDM(31, tbmtbdv5, "",orderErrorFormat);
					 checktbmtbdv6 = checkDLdauvaoDM(32, tbmtbdv6, "",orderErrorFormat);
					 checktbmtbdv7 = checkDLdauvaoDM(33, tbmtbdv7, "",orderErrorFormat);
					 checktbmtbcs02 = checkDLdauvaoDM(34, tbmtbcs02, "",orderErrorFormat);
					 checktbmtbcs3 = checkDLdauvaoDM(35, tbmtbcs3, "",orderErrorFormat);
					 checktbmtbcs4 = checkDLdauvaoDM(36, tbmtbcs4, "",orderErrorFormat);
					 checktbmtbcs5 = checkDLdauvaoDM(37, tbmtbcs5, "",orderErrorFormat);
					 checktbmtbcs6 = checkDLdauvaoDM(38, tbmtbcs6, "",orderErrorFormat);
					 checktbmtbwf02 = checkDLdauvaoDM(39, tbmtbwf02, "",orderErrorFormat);
					 checktbmtbwf3 = checkDLdauvaoDM(40, tbmtbwf3, "",orderErrorFormat);
					 checktbmtbwf4 = checkDLdauvaoDM(41, tbmtbwf4, "",orderErrorFormat);
					 checktbmtbwf5 = checkDLdauvaoDM(42, tbmtbwf5, "",orderErrorFormat);
					 checktbmtbwf6 = checkDLdauvaoDM(43, tbmtbwf6, "",orderErrorFormat);
					 checknhomTruong = checkDLdauvaoDM(44, nhomTruong, "",orderErrorFormat);

					 if( checkkyLuong && checkmaNv && checkhoVaTen && checktenHuyen && checkdonVi && checkmaTinh && checkghep &&
							 checkghiChu && checkdoiTuong && checkddtbqlDayTbkt && checkddtbqlDayTbAon && checkddtbqlDaToaNha &&
							 checkddtbqlGponAdslPstnEoc && checkddtbqlTongQuyDoi && checkddtbqlHuongLuongDuyTri && checktbmtbkt02 &&
							 checktbmtbkt3 && checktbmtbkt4 && checktbmtbkt5 && checktbmtbkt6 && checktbmtbkt7 && checktbmtbt02 &&
							 checktbmtbt3 && checktbmtbt4 && checktbmtbt5 && checktbmtbt6 && checktbmtbt7 && checktbmtbdv02 &&
							 checktbmtbdv3 && checktbmtbdv4 && checktbmtbdv5 && checktbmtbdv6 && checktbmtbdv7 && checktbmtbcs02 &&
							 checktbmtbcs3 && checktbmtbcs4 && checktbmtbcs5 && checktbmtbcs6 && checktbmtbwf02 && checktbmtbwf3 &&
							 checktbmtbwf4 &&	 checktbmtbwf5 && checktbmtbwf6 && checknhomTruong ){
						long delele = deletePhatFcByMaNvAndThang(maNv, kyLuong);
						TblDlDauVaoDayMayDTO lst = new TblDlDauVaoDayMayDTO();
						lst.setMaNv(maNv.toUpperCase());
						lst.setHoVaTen(hoVaTen);
						lst.setMaTinh(maTinh);
						  lst.setKyLuong(kyLuong);
		                    lst.setDonVi(donVi);
		                    lst.setMaTinh(maTinh);
		                    lst.setTenHuyen(tenHuyen);
		                    lst.setGhep(ghep);
		                    lst.setDoiTuong(doiTuong);
		                    if (!ddtbqlDayTbkt.isEmpty()) {
		                        lst.setDdtbqlDayTbkt(Float.parseFloat(ddtbqlDayTbkt));
		                    } else {
		                        lst.setDdtbqlDayTbkt(0f);
		                    }
		                    if (!ddtbqlDayTbAon.isEmpty()) {
		                        lst.setDdtbqlDayTbAon(Float.parseFloat(ddtbqlDayTbAon));
		                    } else {
		                        lst.setDdtbqlDayTbAon(0f);
		                    }

		                    if (!ddtbqlDaToaNha.isEmpty()) {
		                        lst.setDdtbqlDaToaNha(Float.parseFloat(ddtbqlDaToaNha));
		                    } else {
		                        lst.setDdtbqlDaToaNha(0f);
		                    }

		                    if (!ddtbqlGponAdslPstnEoc.isEmpty()) {
		                        lst.setDdtbqlGponAdslPstnEoc(Float.parseFloat(ddtbqlGponAdslPstnEoc));
		                    } else {
		                        lst.setDdtbqlGponAdslPstnEoc(0f);
		                    }

		                    if (!ddtbqlTongQuyDoi.isEmpty()) {
		                        lst.setDdtbqlTongQuyDoi(Float.parseFloat(ddtbqlTongQuyDoi));
		                    } else {
		                        lst.setDdtbqlTongQuyDoi(0f);
		                    }

		                    if (!ddtbqlHuongLuongDuyTri.isEmpty()) {
		                        lst.setDdtbqlHuongLuongDuyTri(Float.parseFloat(ddtbqlHuongLuongDuyTri));
		                    } else {
		                        lst.setDdtbqlHuongLuongDuyTri(0f);
		                    }
		                    if (!tbmtbkt02.isEmpty()) {
		                        lst.setTbmtbkt02(Float.parseFloat(tbmtbkt02));
		                    } else {
		                        lst.setTbmtbkt02(0f);
		                    }

		                    if (!tbmtbkt3.isEmpty()) {
		                        lst.setTbmtbkt3(Float.parseFloat(tbmtbkt3));
		                    } else {
		                        lst.setTbmtbkt3(0f);
		                    }

		                    if (!tbmtbkt4.isEmpty()) {
		                        lst.setTbmtbkt4(Float.parseFloat(tbmtbkt4));
		                    } else {
		                        lst.setTbmtbkt4(0f);
		                    }

		                    if (!tbmtbkt5.isEmpty()) {
		                        lst.setTbmtbkt5(Float.parseFloat(tbmtbkt5));
		                    } else {
		                        lst.setTbmtbkt5(0f);
		                    }
		                    if (!tbmtbkt6.isEmpty()) {
		                        lst.setTbmtbkt6(Float.parseFloat(tbmtbkt6));
		                    } else {
		                        lst.setTbmtbkt6(0f);
		                    }

		                    if (!tbmtbkt7.isEmpty()) {
		                        lst.setTbmtbkt7(Float.parseFloat(tbmtbkt7));
		                    } else {
		                        lst.setTbmtbkt7(0f);
		                    }

		                    if (!tbmtbt02.isEmpty()) {
		                        lst.setTbmtbt02(Float.parseFloat(tbmtbt02));
		                    } else {
		                        lst.setTbmtbt02(0f);
		                    }

		                    if (!tbmtbt3.isEmpty()) {
		                        lst.setTbmtbt3(Float.parseFloat(tbmtbt3));
		                    } else {
		                        lst.setTbmtbt3(0f);
		                    }

		                    if (!tbmtbt4.isEmpty()) {
		                        lst.setTbmtbt4(Float.parseFloat(tbmtbt4));
		                    } else {
		                        lst.setTbmtbt4(0f);
		                    }

		                    if (!tbmtbt5.isEmpty()) {
		                        lst.setTbmtbt5(Float.parseFloat(tbmtbt5));
		                    } else {
		                        lst.setTbmtbt5(0f);
		                    }

		                    if (!tbmtbt6.isEmpty()) {
		                        lst.setTbmtbt6(Float.parseFloat(tbmtbt6));
		                    } else {
		                        lst.setTbmtbt6(0f);
		                    }

		                    if (!tbmtbt7.isEmpty()) {
		                        lst.setTbmtbt7(Float.parseFloat(tbmtbt7));
		                    } else {
		                        lst.setTbmtbt7(0f);
		                    }

		                    if (!tbmtbdv02.isEmpty()) {
		                        lst.setTbmtbdv02(Float.parseFloat(tbmtbdv02));
		                    } else {
		                        lst.setTbmtbdv02(0f);
		                    }

		                    if (!tbmtbdv3.isEmpty()) {
		                        lst.setTbmtbdv3(Float.parseFloat(tbmtbdv3));
		                    } else {
		                        lst.setTbmtbdv3(0f);
		                    }

		                    if (!tbmtbdv4.isEmpty()) {
		                        lst.setTbmtbdv4(Float.parseFloat(tbmtbdv4));
		                    } else {
		                        lst.setTbmtbdv4(0f);
		                    }

		                    if (!tbmtbdv5.isEmpty()) {
		                        lst.setTbmtbdv5(Float.parseFloat(tbmtbdv5));
		                    } else {
		                        lst.setTbmtbdv5(0f);
		                    }

		                    if (!tbmtbdv6.isEmpty()) {
		                        lst.setTbmtbdv6(Float.parseFloat(tbmtbdv6));
		                    } else {
		                        lst.setTbmtbdv6(0f);
		                    }

		                    if (!tbmtbdv7.isEmpty()) {
		                        lst.setTbmtbdv7(Float.parseFloat(tbmtbdv7));
		                    } else {
		                        lst.setTbmtbdv7(0f);
		                    }
		                    //------
		                    if (!tbmtbcs02.isEmpty()) {
		                        lst.setTbmtbcs02(Float.parseFloat(tbmtbcs02));
		                    } else {
		                        lst.setTbmtbcs02(0f);
		                    }

		                    if (!tbmtbcs3.isEmpty()) {
		                        lst.setTbmtbcs3(Float.parseFloat(tbmtbcs3));
		                    } else {
		                        lst.setTbmtbcs3(0f);
		                    }

		                    if (!tbmtbcs4.isEmpty()) {
		                        lst.setTbmtbcs4(Float.parseFloat(tbmtbcs4));
		                    } else {
		                        lst.setTbmtbcs4(0f);
		                    }

		                    if (!tbmtbcs5.isEmpty()) {
		                        lst.setTbmtbcs5(Float.parseFloat(tbmtbcs5));
		                    } else {
		                        lst.setTbmtbcs5(0f);
		                    }

		                    if (!tbmtbcs6.isEmpty()) {
		                        lst.setTbmtbcs6(Float.parseFloat(tbmtbcs6));
		                    } else {
		                        lst.setTbmtbcs6(0f);
		                    }

		                    //-----
		                    if (!tbmtbwf02.isEmpty()) {
		                        lst.setTbmtbwf02(Float.parseFloat(tbmtbwf02));
		                    } else {
		                        lst.setTbmtbwf02(0f);
		                    }

		                    if (!tbmtbwf3.isEmpty()) {
		                        lst.setTbmtbwf3(Float.parseFloat(tbmtbwf3));
		                    } else {
		                        lst.setTbmtbwf3(0f);
		                    }

		                    if (!tbmtbwf4.isEmpty()) {
		                        lst.setTbmtbwf4(Float.parseFloat(tbmtbwf4));
		                    } else {
		                        lst.setTbmtbwf4(0f);
		                    }

		                    if (!tbmtbwf5.isEmpty()) {
		                        lst.setTbmtbwf5(Float.parseFloat(tbmtbwf5));
		                    } else {
		                        lst.setTbmtbwf5(0f);
		                    }

		                    if (!tbmtbwf6.isEmpty()) {
		                        lst.setTbmtbwf6(Float.parseFloat(tbmtbwf6));
		                    } else {
		                        lst.setTbmtbwf6(0f);
		                    }
		                    if (!nhomTruong.isEmpty()) {
		                        lst.setNhomTruong(Float.parseFloat(nhomTruong));
		                    } else {
		                        lst.setNhomTruong(0f);
		                    }

		                    lst.setGhiChu(ghiChu);

						lst.setXoa(0l);
						lst.setHoatDong(1l);

						workLst.add(lst);
						// save(newObj);
					} else {
						ImportErrDTO errObj = new ImportErrDTO();
						errObj.setColumn1(kyLuong);
						errObj.setColumn2(maNv);
						errObj.setColumn3(hoVaTen);
						errObj.setColumn4(donVi);
						errObj.setColumn5(maTinh);
						errObj.setColumn6(tenHuyen);
						errObj.setColumn7(ghep);
						errObj.setColumn8(doiTuong);
						errObj.setColumn9(ddtbqlDayTbkt);
						errObj.setColumn10(ddtbqlDayTbAon);
						errObj.setColumn11(ddtbqlDaToaNha);
						errObj.setColumn12(ddtbqlGponAdslPstnEoc);
						errObj.setColumn13(ddtbqlTongQuyDoi);
						errObj.setColumn14(ddtbqlHuongLuongDuyTri);
						errObj.setColumn15(tbmtbkt02);
						errObj.setColumn16(tbmtbkt3);
						errObj.setColumn17(tbmtbkt4);
						errObj.setColumn18(tbmtbkt5);
						errObj.setColumn19(tbmtbkt6);
						errObj.setColumn20(tbmtbkt7);
						errObj.setColumn21(tbmtbt02);
						errObj.setColumn22(tbmtbt3);
						errObj.setColumn23(tbmtbt4);
						errObj.setColumn24(tbmtbt5);
						errObj.setColumn25(tbmtbt6);
						errObj.setColumn26(tbmtbt7);
						errObj.setColumn27(tbmtbdv02);
						errObj.setColumn28(tbmtbdv3);
						errObj.setColumn29(tbmtbdv4);
						errObj.setColumn30(tbmtbdv5);
						errObj.setColumn31(tbmtbdv6);
						errObj.setColumn32(tbmtbdv7);
						errObj.setColumn33(tbmtbcs02);
						errObj.setColumn34(tbmtbcs3);
						errObj.setColumn35(tbmtbcs4);
						errObj.setColumn36(tbmtbcs5);
						errObj.setColumn37(tbmtbcs6);
						errObj.setColumn38(tbmtbwf02);
						errObj.setColumn39(tbmtbwf3);
						errObj.setColumn40(tbmtbwf4);
						errObj.setColumn41(tbmtbwf5);
						errObj.setColumn42(tbmtbwf6);
						errObj.setColumn43(nhomTruong);
						errObj.setColumn44(ghiChu);
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
					lsThaoTacDTO.setChucNang("Import dữ liệu đầu vào dây máy");
					lsThaoTacDTO.setMoTa("Import " +workLst.size() +" bản ghi dữ liệu đầu vào dây máy" );
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
	public boolean checkDLdauvaoDM(int index, String value,String value2, ErrExcelDTO orderErrorFormat) {
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
					if(ketthuc>batdau)
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
						orderErrorFormat.setDetailError("Tháng năm không đúng định dạng");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
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
				}
				else if(tblDanhmucDao.checkExisttenhuyenBytenhuyen(value,value2)!=1l)
				{
					orderErrorFormat.setDetailError("Tên Quận/Huyện không nằm trong tỉnh ");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}
		} else if (index == 5) {
			if (!value.isEmpty()) {
				if (value.length() > 100) {
					orderErrorFormat.setDetailError("Đơn vi vượt quá độ dài cho phép");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
				else if(tblDanhmucDao.checkExistTinhBytenTinh(value,value2)!=1l)
				{
					orderErrorFormat.setDetailError("Đơn vị không trùng mã tỉnh ");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}
		} else if (index == 6) {
			if (!value.isEmpty()) {
				if (value.length() > 100) {
					orderErrorFormat.setDetailError("Mã tỉnh vượt quá độ dài cho phép");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}
			 else if (index == 7) {
					if (!value.isEmpty()) {
						if (value.length() > 200) {
							orderErrorFormat.setDetailError("Ghép vượt quá độ dài cho phép");
							lstErrExcelDto.add(orderErrorFormat);
							return false;
						}
					}
			 else if (index == 8) {
					if (!value.isEmpty()) {
						if (value.length() > 200) {
							orderErrorFormat.setDetailError("Ghi chú vượt quá độ dài cho phép");
							lstErrExcelDto.add(orderErrorFormat);
							return false;
						}
					}
				}
			 else if (index == 9) {
					if (!value.isEmpty()) {
						if (value.length() > 100) {
							orderErrorFormat.setDetailError("Đối tượng vượt quá độ dài cho phép");
							lstErrExcelDto.add(orderErrorFormat);
							return false;
						}
					}
				}
		} else if (index == 10) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					orderErrorFormat.setDetailError("Dây thuê bao kênh truyền không đúng định dạng");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					orderErrorFormat.setDetailError("Dây thuê bao kênh truyền phải là số thực không âm");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}
		} else if (index == 11) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					orderErrorFormat.setDetailError("Dây thuê bao AON không đúng định dạng");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					orderErrorFormat.setDetailError("Dây thuê bao AON phải là số thực không âm");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}
		} else if (index == 12) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					orderErrorFormat.setDetailError("Dây thuê bao thuộc các dự án tòa nhà không đúng định dạng");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					orderErrorFormat.setDetailError("Dây thuê bao thuộc các dự án tòa nhà phải là số thực không âm");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}
		} else if (index == 13) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					orderErrorFormat
							.setDetailError("Dây thuê bao GPON, ADSL, PSTN, EoC  không đúng định dạng");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					orderErrorFormat.setDetailError(
							"Dây thuê bao GPON, ADSL, PSTN, EoC không âm");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}
		} else if (index == 14) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					orderErrorFormat
							.setDetailError("Tổng dây thuê bao quy đổi không đúng định dạng");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					orderErrorFormat.setDetailError(
							"Tổng dây thuê bao quy đổi phải là số thực không âm");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}
		} else if (index == 15) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					orderErrorFormat.setDetailError("Có được hưởng lương duy trì hay không? không đúng định dạng");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					orderErrorFormat
							.setDetailError("Có được hưởng lương duy trì hay không? phải là số thực không âm");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}
		} else if (index == 16) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					orderErrorFormat.setDetailError("Triển khai dây thuê bao mới TB Kênh truyền 0-2 ngày không đúng định dạng");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					orderErrorFormat.setDetailError("Triển khai dây thuê bao mới TB Kênh truyền 0-2 ngày phải là số thực không âm");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}
		} else if (index == 17) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					orderErrorFormat.setDetailError("Triển khai dây thuê bao mới TB Kênh truyền 3 ngày không đúng định dạng");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					orderErrorFormat.setDetailError("Triển khai dây thuê bao mới TB Kênh truyền 3 ngày phải là số thực không âm");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}
		} else if (index == 18) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					orderErrorFormat.setDetailError("Triển khai dây thuê bao mới TB Kênh truyền 4 ngày không đúng định dạng");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					orderErrorFormat.setDetailError("Triển khai dây thuê bao mới TB Kênh truyền 4 ngày phải là số thực không âm");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}
		} else if (index == 19) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					orderErrorFormat.setDetailError("Triển khai dây thuê bao mới TB Kênh truyền 5 ngày không đúng định dạng");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					orderErrorFormat.setDetailError("Triển khai dây thuê bao mới TB Kênh truyền 5 ngày phải là số thực không âm");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}
		} else if (index == 20) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					orderErrorFormat.setDetailError("Triển khai dây thuê bao mới TB Kênh truyền 6 ngày không đúng định dạng");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					orderErrorFormat.setDetailError("Triển khai dây thuê bao mới TB Kênh truyền 6 ngày phải là số thực không âm");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}
		}
		else if (index == 21) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					orderErrorFormat.setDetailError("Triển khai dây thuê bao mới TB Kênh truyền >6 ngày không đúng định dạng");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					orderErrorFormat.setDetailError("Triển khai dây thuê bao mới TB Kênh truyền >6 ngày phải là số thực không âm");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}
		}
		else if (index == 22) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					orderErrorFormat.setDetailError("Triển khai dây thuê bao mới TB thường 0-2 ngày không đúng định dạng");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					orderErrorFormat.setDetailError("Triển khai dây thuê bao mới TB thường 0-2 ngày phải là số thực không âm");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}
		}
		else if (index == 23) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					orderErrorFormat.setDetailError("Triển khai dây thuê bao mới TB thường 3 ngày không đúng định dạng");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					orderErrorFormat.setDetailError("Triển khai dây thuê bao mới TB thường 3 ngày phải là số thực không âm");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}
		}
		else if (index == 24) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					orderErrorFormat.setDetailError("Triển khai dây thuê bao mới TB thường 4 ngày không đúng định dạng");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					orderErrorFormat.setDetailError("Triển khai dây thuê bao mới TB thường 4 ngày phải là số thực không âm");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}
		}
		else if (index == 25) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					orderErrorFormat.setDetailError("Triển khai dây thuê bao mới TB thường 5 ngày không đúng định dạng");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					orderErrorFormat.setDetailError("Triển khai dây thuê bao mới TB thường 5 ngày phải là số thực không âm");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}
		}
		else if (index == 26) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					orderErrorFormat.setDetailError("Triển khai dây thuê bao mới TB thường 6 ngày không đúng định dạng");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					orderErrorFormat.setDetailError("Triển khai dây thuê bao mới TB thường 6 ngày phải là số thực không âm");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}
		}
		else if (index == 27) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					orderErrorFormat.setDetailError("Triển khai dây thuê bao mới TB thường >6 ngày không đúng định dạng");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					orderErrorFormat.setDetailError("Triển khai dây thuê bao mới TB thường >6 ngày phải là số thực không âm");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}
		}
		else if (index == 28) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					orderErrorFormat.setDetailError("Triển khai dây thuê bao mới tất cả dịch vụ 0-2 ngày không đúng định dạng");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					orderErrorFormat.setDetailError("Triển khai dây thuê bao mới tất cả dịch vụ 0-2 ngày phải là số thực không âm");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}
		}
		else if (index == 29) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					orderErrorFormat.setDetailError("Triển khai dây thuê bao mới tất cả dịch vụ 3 ngày không đúng định dạng");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					orderErrorFormat.setDetailError("Triển khai dây thuê bao mới tất cả dịch vụ 3 ngày phải là số thực không âm");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}
		}
		else if (index == 30) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					orderErrorFormat.setDetailError("Triển khai dây thuê bao mới tất cả dịch vụ 4 ngày không đúng định dạng");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					orderErrorFormat.setDetailError("Triển khai dây thuê bao mới tất cả dịch vụ 4 ngày phải là số thực không âm");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}
		}
		else if (index == 31) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					orderErrorFormat.setDetailError("Triển khai dây thuê bao mới tất cả dịch vụ 5 ngày không đúng định dạng");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					orderErrorFormat.setDetailError("Triển khai dây thuê bao mới tất cả dịch vụ 5 ngày phải là số thực không âm");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}
		}
		else if (index == 32) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					orderErrorFormat.setDetailError("Triển khai dây thuê bao mới tất cả dịch vụ 6 ngày không đúng định dạng");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					orderErrorFormat.setDetailError("Triển khai dây thuê bao mới tất cả dịch vụ 6 ngày phải là số thực không âm");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}
		}
		else if (index == 33) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					orderErrorFormat.setDetailError("Triển khai dây thuê bao mới tất cả dịch vụ >6 ngày không đúng định dạng");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					orderErrorFormat.setDetailError("Triển khai dây thuê bao mới tất cả dịch vụ >6 ngày phải là số thực không âm");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}
		}
		else if (index == 34) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					orderErrorFormat.setDetailError(" Thêm dịch vụ trên đường dây có sẵn 0-2 ngày không đúng định dạng");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					orderErrorFormat.setDetailError(" Thêm dịch vụ trên đường dây có sẵn 0-2 ngày phải là số thực không âm");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}
		}
		else if (index == 35) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					orderErrorFormat.setDetailError(" Thêm dịch vụ trên đường dây có sẵn 3 ngày không đúng định dạng");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					orderErrorFormat.setDetailError(" Thêm dịch vụ trên đường dây có sẵn 3 ngày phải là số thực không âm");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}
		}
		else if (index == 36) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					orderErrorFormat.setDetailError(" Thêm dịch vụ trên đường dây có sẵn 4 ngày không đúng định dạng");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					orderErrorFormat.setDetailError(" Thêm dịch vụ trên đường dây có sẵn 4 ngày phải là số thực không âm");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}
		}
		else if (index == 37) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					orderErrorFormat.setDetailError(" Thêm dịch vụ trên đường dây có sẵn 5 ngày không đúng định dạng");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					orderErrorFormat.setDetailError(" Thêm dịch vụ trên đường dây có sẵn 5 ngày phải là số thực không âm");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}
		}
		else if (index == 38) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					orderErrorFormat.setDetailError(" Thêm dịch vụ trên đường dây có sẵn >5 ngày không đúng định dạng");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					orderErrorFormat.setDetailError(" Thêm dịch vụ trên đường dây có sẵn >5 ngày phải là số thực không âm");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}
		}
		else if (index == 39) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					orderErrorFormat.setDetailError("  Triển khai cài đặt thiết bị phát Wifi 0-2 ngày không đúng định dạng");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					orderErrorFormat.setDetailError("  Triển khai cài đặt thiết bị phát Wifi 0-2 ngày phải là số thực không âm");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}
		}
		else if (index == 40) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					orderErrorFormat.setDetailError("  Triển khai cài đặt thiết bị phát Wifi 3 ngày không đúng định dạng");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					orderErrorFormat.setDetailError("  Triển khai cài đặt thiết bị phát Wifi 3 ngày phải là số thực không âm");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}
		}
		else if (index == 41) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					orderErrorFormat.setDetailError("  Triển khai cài đặt thiết bị phát Wifi 4 ngày không đúng định dạng");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					orderErrorFormat.setDetailError("  Triển khai cài đặt thiết bị phát Wifi 4 ngày phải là số thực không âm");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}
		}
		else if (index == 42) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					orderErrorFormat.setDetailError("  Triển khai cài đặt thiết bị phát Wifi 5 ngày không đúng định dạng");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					orderErrorFormat.setDetailError("  Triển khai cài đặt thiết bị phát Wifi 5 ngày phải là số thực không âm");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}
		}
		else if (index == 43) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					orderErrorFormat.setDetailError("  Triển khai cài đặt thiết bị phát Wifi >5 ngày không đúng định dạng");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					orderErrorFormat.setDetailError("  Triển khai cài đặt thiết bị phát Wifi >5 ngày phải là số thực không âm");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}
		}
		else if (index == 44) {
			if (!value.isEmpty()) {
				if (!ValidateUtils.isFloat(value)) {
					orderErrorFormat.setDetailError("  Nhóm trưởng  không đúng định dạng");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
				if (Float.parseFloat(value) < 0) {
					orderErrorFormat.setDetailError("  Nhóm trưởng  phải là số thực không âm");
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
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
			InputStream file = new BufferedInputStream(new FileInputStream(filePath + "NVDayMay-dulieuduytrierror.xlsx"));
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
			int rownum = 8;
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
				
				for (int j = 0; j < obj.get(i).getLstErrorOrder().size(); j++) {
					err.append(obj.get(i).getLstErrorOrder().get(j).getDetailError() + "-");
					// System.out.println("err" + err);
				}
				System.out.println("err " + i + ":" + err);

				XSSFCell cell45 = row.createCell(45);
				cell45.setCellValue(err.toString());

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
				cell19.setCellStyle(style);
				cell20.setCellStyle(style);
				cell21.setCellStyle(style);
				cell22.setCellStyle(style);
				cell23.setCellStyle(style);
				cell24.setCellStyle(style);
				cell25.setCellStyle(style);
				cell26.setCellStyle(style);
				cell27.setCellStyle(style);
				cell28.setCellStyle(style);
				cell29.setCellStyle(style);
				cell30.setCellStyle(style);
				cell31.setCellStyle(style);
				cell32.setCellStyle(style);
				cell33.setCellStyle(style);
				cell34.setCellStyle(style);
				cell35.setCellStyle(style);
				cell36.setCellStyle(style);
				cell37.setCellStyle(style);
				cell38.setCellStyle(style);
				cell39.setCellStyle(style);
				cell40.setCellStyle(style);
				cell41.setCellStyle(style);
				cell42.setCellStyle(style);
				cell43.setCellStyle(style);
				cell44.setCellStyle(style);
				cell45.setCellStyle(style);
				
			}
			file.close();

			File out = new File(folder2Upload + File.separatorChar + "NVDayMay-dulieuduytrierror" + startExportTime + ".xlsx");

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
		return UEncrypt.encryptFileUploadPath("NVDayMay-dulieuduytrierror" + startExportTime + ".xlsx");
	}

	@Override
	public long deletePhatFcByMaNvAndThang(String maNv, String thang) {
		// TODO Auto-generated method stub
		return 0;
	}
	public String exportExcelGrid(TblDlDauVaoDayMayDTO lst) throws Exception {
		// TODO Auto-generated method stub
		List<TblDlDauVaoDayMayDTO> obj = tblDlDauVaoDayMayDAO.doSearch(lst);
		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream file = new BufferedInputStream(new FileInputStream(filePath + "exportduytridulieu.xlsx"));
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
			int rownum = 8;
			for (int i = 0; i < obj.size(); i++) {
				StringBuilder err = new StringBuilder();
				XSSFRow row = sheet.createRow(rownum);
				XSSFCell cell0 = row.createCell(0);
				cell0.setCellValue(i + 1);
				cell0.setCellStyle(style);
				rownum++;

				XSSFCell cell1 = row.createCell(1);
				cell1.setCellValue(obj.get(i).getKyLuong());

				XSSFCell cell2 = row.createCell(2);
				cell2.setCellValue(obj.get(i).getMaNv());

				XSSFCell cell3 = row.createCell(3);
				cell3.setCellValue(obj.get(i).getHoVaTen());

				XSSFCell cell4 = row.createCell(4);
				cell4.setCellValue(obj.get(i).getDonVi());

				XSSFCell cell5 = row.createCell(5);
				cell5.setCellValue(obj.get(i).getMaTinh());

				XSSFCell cell6 = row.createCell(6);
				cell6.setCellValue(obj.get(i).getTenHuyen());

				XSSFCell cell7 = row.createCell(7);
				cell7.setCellValue(obj.get(i).getGhep());

				XSSFCell cell8 = row.createCell(8);
				cell8.setCellValue(obj.get(i).getDoiTuong());

				XSSFCell cell9 = row.createCell(9);
				cell9.setCellValue(obj.get(i).getDdtbqlDayTbkt());

				XSSFCell cell10 = row.createCell(10);
				cell10.setCellValue(obj.get(i).getDdtbqlDayTbAon());

				XSSFCell cell11 = row.createCell(11);
				cell11.setCellValue(obj.get(i).getDdtbqlDaToaNha());

				XSSFCell cell12 = row.createCell(12);
				cell12.setCellValue(obj.get(i).getDdtbqlGponAdslPstnEoc());

				XSSFCell cell13 = row.createCell(13);
				cell13.setCellValue(obj.get(i).getDdtbqlTongQuyDoi());

				XSSFCell cell14 = row.createCell(14);
				cell14.setCellValue(obj.get(i).getDdtbqlHuongLuongDuyTri());

				XSSFCell cell15 = row.createCell(15);
				cell15.setCellValue(obj.get(i).getTbmtbkt02());

				XSSFCell cell16 = row.createCell(16);
				cell16.setCellValue(obj.get(i).getTbmtbkt3());

				XSSFCell cell17 = row.createCell(17);
				cell17.setCellValue(obj.get(i).getTbmtbkt4());
				
				XSSFCell cell18 = row.createCell(18);
				cell18.setCellValue(obj.get(i).getTbmtbkt5());
				
				XSSFCell cell19 = row.createCell(19);
				cell19.setCellValue(obj.get(i).getTbmtbkt6());
				
				XSSFCell cell20 = row.createCell(20);
				cell20.setCellValue(obj.get(i).getTbmtbkt7());
				
				XSSFCell cell21 = row.createCell(21);
				cell21.setCellValue(obj.get(i).getTbmtbt02());
				
				XSSFCell cell22 = row.createCell(22);
				cell22.setCellValue(obj.get(i).getTbmtbt3());
				
				XSSFCell cell23 = row.createCell(23);
				cell23.setCellValue(obj.get(i).getTbmtbt4());
				
				XSSFCell cell24 = row.createCell(24);
				cell24.setCellValue(obj.get(i).getTbmtbt5());
				
				XSSFCell cell25 = row.createCell(25);
				cell25.setCellValue(obj.get(i).getTbmtbt6());
				
				XSSFCell cell26 = row.createCell(26);
				cell26.setCellValue(obj.get(i).getTbmtbt7());
				
				XSSFCell cell27 = row.createCell(27);
				cell27.setCellValue(obj.get(i).getTbmtbdv02());
				
				XSSFCell cell28 = row.createCell(28);
				cell28.setCellValue(obj.get(i).getTbmtbdv3());
				
				XSSFCell cell29 = row.createCell(29);
				cell29.setCellValue(obj.get(i).getTbmtbdv4());
				
				XSSFCell cell30 = row.createCell(30);
				cell30.setCellValue(obj.get(i).getTbmtbdv5());
				
				XSSFCell cell31 = row.createCell(31);
				cell31.setCellValue(obj.get(i).getTbmtbdv6());
				
				XSSFCell cell32 = row.createCell(32);
				cell32.setCellValue(obj.get(i).getTbmtbdv7());
				
				XSSFCell cell33 = row.createCell(33);
				cell33.setCellValue(obj.get(i).getTbmtbcs02());
				
				XSSFCell cell34 = row.createCell(34);
				cell34.setCellValue(obj.get(i).getTbmtbcs3());
				
				XSSFCell cell35 = row.createCell(35);
				cell35.setCellValue(obj.get(i).getTbmtbcs4());
				
				XSSFCell cell36 = row.createCell(36);
				cell36.setCellValue(obj.get(i).getTbmtbcs5());
				
				XSSFCell cell37 = row.createCell(37);
				cell37.setCellValue(obj.get(i).getTbmtbcs6());
				
				XSSFCell cell38 = row.createCell(38);
				cell38.setCellValue(obj.get(i).getTbmtbwf02());
				
				XSSFCell cell39 = row.createCell(39);
				cell39.setCellValue(obj.get(i).getTbmtbwf3());
				
				XSSFCell cell40 = row.createCell(40);
				cell40.setCellValue(obj.get(i).getTbmtbwf4());
				
				XSSFCell cell41 = row.createCell(41);
				cell41.setCellValue(obj.get(i).getTbmtbwf5());
				
				XSSFCell cell42 = row.createCell(42);
				cell42.setCellValue(obj.get(i).getTbmtbwf6());
				
				XSSFCell cell43 = row.createCell(43);
				cell43.setCellValue(obj.get(i).getNhomTruong());
				
				XSSFCell cell44 = row.createCell(44);
				cell44.setCellValue(obj.get(i).getGhiChu());
				

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
				cell19.setCellStyle(style);
				cell20.setCellStyle(style);
				cell21.setCellStyle(style);
				cell22.setCellStyle(style);
				cell23.setCellStyle(style);
				cell24.setCellStyle(style);
				cell25.setCellStyle(style);
				cell26.setCellStyle(style);
				cell27.setCellStyle(style);
				cell28.setCellStyle(style);
				cell29.setCellStyle(style);
				cell30.setCellStyle(style);
				cell31.setCellStyle(style);
				cell32.setCellStyle(style);
				cell33.setCellStyle(style);
				cell34.setCellStyle(style);
				cell35.setCellStyle(style);
				cell36.setCellStyle(style);
				cell37.setCellStyle(style);
				cell38.setCellStyle(style);
				cell39.setCellStyle(style);
				cell40.setCellStyle(style);
				cell41.setCellStyle(style);
				cell42.setCellStyle(style);
				cell43.setCellStyle(style);
				cell44.setCellStyle(style);
				

			}
			file.close();

			File out = new File(folder2Upload + File.separatorChar + "exportduytridulieu" + startExportTime + ".xlsx");

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
		return UEncrypt.encryptFileUploadPath("exportduytridulieu" + startExportTime + ".xlsx");
	}
	@Override
	public String downloadImportTemplate() throws Exception {

		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		String filePath = classloader.getResource("../" + "doc-template").getPath();
		InputStream file = new BufferedInputStream(new FileInputStream(filePath + "exportduytridulieu.xlsx"));
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		
		
		file.close();
		File out = new File(folder2Upload + File.separatorChar + "exportduytridulieu.xlsx");
		
		FileOutputStream outFile = new FileOutputStream(out);
		workbook.write(outFile);
		workbook.close();
		outFile.close();
		
		String path = UEncrypt.encryptFileUploadPath("exportduytridulieu.xlsx");
		return path;
	}
}
	