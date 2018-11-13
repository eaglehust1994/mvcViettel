package com.viettel.qll.business;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
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
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
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
import org.apache.poi.ss.util.CellRangeAddress;
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
import com.viettel.qll.bo.KqHdTkBO;
import com.viettel.qll.dao.KqHdTkDAO;
import com.viettel.qll.dto.ErrExcelDTO;
import com.viettel.qll.dto.ImportErrDTO;
import com.viettel.qll.dto.KqHdTkDTO;

import com.viettel.qll.dto.VpsUserToken;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;
import com.viettel.utils.ValidateUtils;

import com.viettel.wms.dto.CommonDTO;

@Service("kqHdTkBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class KqHdTkBusinessImpl extends BaseFWBusinessImpl<KqHdTkDAO, KqHdTkDTO, KqHdTkBO> implements KqHdTkBusiness {

	protected final Logger log = Logger.getLogger(KqHdTkBusinessImpl.class);

	@Value("${folder_upload2}")
	private String folder2Upload;
	@Autowired
	private KqHdTkDAO kqHdTkDAO;
	
	DecimalFormat dFormat = new DecimalFormat("#,##");
	DateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
	DecimalFormat dFormat1 = new DecimalFormat("#,###.00");
	DateFormat d1t = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	List<ErrExcelDTO> lstErrExcelDto;
	
	
	
	public CommonDTO getCharTwoMonth(CommonDTO obj, HttpServletRequest request)
			throws ParseException {
//		List<Long> listStockId = commonBusiness.getListDomainData(
//				Constants.OperationKey.VIEW, Constants.AdResourceKey.STOCK,
//				request);
//		obj.setListStockId(listStockId); 
		CommonDTO objReturn = new CommonDTO();
	
		List<CommonDTO> ls = kqHdTkDAO.getCharTwoMonth();
		System.out.println(ls);
		ls.size();
		if (ls.size() > 0 && ls.size() < 10) {
			for (Iterator<CommonDTO> interator = ls.iterator(); interator
					.hasNext();) {
				CommonDTO wi = interator.next();
				objReturn.getListTongDn().add(wi.getTongDn());
				objReturn.getListTongTd().add(wi.getTongTd());
				objReturn.getListThangGhiNhanQuyLuongTqt().add(wi.getThangGhiNhanQuyLuongTqt());
			}
		} else if (ls.size() >= 10) {
			Integer index1 = 0;
			Integer index2 = null;
			Integer index3 = null;
			Integer index4 = null;
			Integer index5 = ls.size() - 1;
			index3 = index5 / 2;
			index4 = index3 + (index5 - index3) / 2;
			index2 = index3 - index3 / 2;
			List<Integer> listIndex = Lists.newArrayList();
			listIndex.add(index1);
			listIndex.add(index2);
			listIndex.add(index3);
			listIndex.add(index4);
			listIndex.add(index5);

			for (Integer index : listIndex) {
				objReturn.getListExported().add(ls.get(index).getExported());
				objReturn.getListImported().add(ls.get(index).getImported());
				objReturn.getListDay().add(ls.get(index).getDay());
			}
		}
		return objReturn;

	}
	
	public KqHdTkBusinessImpl() {
		tModel = new KqHdTkBO();
		tDAO = kqHdTkDAO;
	}

	@Override
	public KqHdTkDAO gettDAO() {
		return kqHdTkDAO;
	}

	@Override
	@Transactional
	public Long updatePath(KqHdTkDTO obj) throws Exception {
		try {
			long ids = kqHdTkDAO.updatePath(obj);
			return ids;
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Có lỗi khi upload ảnh!!!");
		}

	}
	
	@Override
	public DataListDTO doSearch(KqHdTkDTO obj) throws Exception {
		List<KqHdTkDTO> ls = kqHdTkDAO.doSearch(obj);
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		data.setTotal(obj.getTotalRecord());
		data.setSize(obj.getTotalRecord());
		data.setStart(1);
		return data;
	}

	/**
	 * Xóa 1 bản ghi
	 */
	@Override
	public long deleteObj(KqHdTkDTO obj) {
		try {
			kqHdTkDAO.delete(obj.toModel());
			return 1l;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0l;
	}

	/**
	 * Xóa 1 list bản ghi
	 */
	@Override
	public long deleteListObj(KqHdTkDTO obj) {
		List<KqHdTkDTO> lst = kqHdTkDAO.doSearch(obj);
		List<Long> lstId = new ArrayList<>();
		int size = lst.size();
		int count = 0;
		int groupBatch = 0;
		try {
			for (KqHdTkDTO obj1 : lst) {
				count++;
				groupBatch++;
				lstId.add(obj1.getKqHdTkId());
				if (groupBatch == 999) {
					groupBatch=0;
					kqHdTkDAO.deleteList(lstId);
					lstId.clear();
				}
				if (groupBatch < 999 && count == size) {
					
					kqHdTkDAO.deleteList(lstId);

				}
			}
			return 1l;
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return 0l;
	}

	@Override
	public long insertHD(KqHdTkDTO obj) throws Exception {
		try {
			Long ids = kqHdTkDAO.saveObject(obj.toModel());
			return ids;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 1l;
	}

	@Override
	public long updateHDTd(KqHdTkDTO obj) throws Exception {
		try {
			obj.setNgayCapNhat(new Date());
			Long ids = kqHdTkDAO.updateObject(obj.toModel());
			return ids;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 1l;
	}
	
	@Override
	public long updateHdPopSubmit(KqHdTkDTO obj) throws Exception {
		try {
			obj.setNgayCapNhat(new Date());
			Long ids = kqHdTkDAO.updateObject(obj.toModel());
			return ids;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 1l;
	}
	/**
	 * Xuất báo cáo theo từng số hợp đồng
	 * 
	 * @param lst
	 * @param request
	 * @return
	 * @throws Exception
	 */

	public String exportTHTheoHD(KqHdTkDTO lst, HttpServletRequest request) throws Exception {

		List<KqHdTkDTO> obj1 = kqHdTkDAO.doSearch(lst);
		// obj1.stream().filter(x->x.getSoHd()==lst.getSoHd()).distinct().collect(Collectors.toList());
		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream fileIn = new BufferedInputStream(new FileInputStream(filePath + "ExportTHTheoHD.xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(fileIn);
			DataFormat format = workbook.createDataFormat();

			XSSFSheet sheet = workbook.getSheetAt(0);
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

			XSSFRow row1 = null;
			row1 = sheet.createRow(2);
			XSSFCell cellTop = row1.createCell(0);
			cellTop.setCellValue("PHÊ DUYỆT");
			cellTop.setCellStyle(style1);
			XSSFCell cellTop1 = row1.createCell(17);
			cellTop1.setCellValue(obj1.get(0).getTenTinh() + ", Ngày ……Tháng ……Năm ……");
			cellTop1.setCellStyle(style4);

			row1 = sheet.createRow(12);
			XSSFCell cellTitle1 = row1.createCell(0);
			cellTitle1.setCellValue("Căn cứ theo hợp đồng số : " + obj1.get(0).getSoHd() + " giữa Viettel "
					+ obj1.get(0).getTenTinh() + " với Tổng Công ty CP Công trình Viettel.");
			cellTitle1.setCellStyle(style3);

			row1 = sheet.createRow(13);
			XSSFCell cellTitle2 = row1.createCell(0);
			cellTitle2.setCellValue("Căn cứ theo kế hoạch thi công số : " + obj1.get(0).getSoKhTc() + " ngày "
					+ obj1.get(0).getNgayKyKh());
			cellTitle2.setCellStyle(style3);

			row1 = sheet.createRow(14);
			XSSFCell cellTitle3 = row1.createCell(0);
			cellTitle3.setCellValue("Trung tâm Kỹ thuật Viettel " + obj1.get(0).getTenTinh()
					+ " Kính đề nghị Ban Giám đốc, Phòng Tài Chính, Phòng Thanh khoản phê duyệt thanh toán.");
			cellTitle3.setCellStyle(style3);

			row1 = sheet.createRow(15);
			XSSFCell cellTitle4 = row1.createCell(0);
			cellTitle4.setCellValue(
					"Nội dung công việc : Thi công " + obj1.get(0).getNoiDung() + " tại " + obj1.get(0).getTenTinh());
			cellTitle4.setCellStyle(style3);

			row1 = sheet.createRow(16);
			XSSFCell cellTitle5 = row1.createCell(0);
			cellTitle5.setCellValue("Địa chỉ: " + obj1.get(0).getTenTinh());
			cellTitle5.setCellStyle(style3);

			int rownum1 = 21;

			for (int j = 0; j < obj1.size(); j++) {

				XSSFRow row = sheet.createRow(rownum1);
				XSSFCell cell0 = row.createCell(0);
				cell0.setCellValue(j + 1);
				rownum1++;

				XSSFCell cell2 = row.createCell(1);
				cell2.setCellValue(obj1.get(j).getMaTramTuyen());

				XSSFCell cell3 = row.createCell(2);
				cell3.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getGtQtCdtChuaVat() == null || obj1.get(j).getGtQtCdtChuaVat() == (float) 0) {
					obj1.get(j).setGtQtCdtChuaVat((float) 0);
					cell3.setCellValue(obj1.get(j).getGtQtCdtChuaVat());
				} else {
					cell3.setCellValue(obj1.get(j).getGtQtCdtChuaVat());
				}

				XSSFCell cell4 = row.createCell(3);
				cell4.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getGtQtCdtCoVat() == null || obj1.get(j).getGtQtCdtCoVat() == (float) 0) {
					obj1.get(j).setGtQtCdtCoVat((float) 0);
					cell4.setCellValue(obj1.get(j).getGtQtCdtCoVat());
				} else {
					cell4.setCellValue(obj1.get(j).getGtQtCdtCoVat());
				}

				XSSFCell cell5 = row.createCell(4);
				cell5.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getCpNhanCongDn() == null || obj1.get(j).getCpNhanCongDn() == (float) 0) {
					obj1.get(j).setCpNhanCongDn((float) 0);
					cell5.setCellValue(obj1.get(j).getCpNhanCongDn());
				} else {
					cell5.setCellValue(obj1.get(j).getCpNhanCongDn());
				}

				XSSFCell cell6 = row.createCell(5);
				cell6.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getCpVatLieuDn() == null || obj1.get(j).getCpVatLieuDn() == (float) 0) {
					obj1.get(j).setCpVatLieuDn((float) 0);
					cell6.setCellValue(obj1.get(j).getCpVatLieuDn());
				} else {
					cell6.setCellValue(obj1.get(j).getCpVatLieuDn());
				}

				XSSFCell cell7 = row.createCell(6);
				cell7.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getCpHshcDn() == null || obj1.get(j).getCpHshcDn() == (float) 0) {
					obj1.get(j).setCpHshcDn((float) 0);
					cell7.setCellValue(obj1.get(j).getCpHshcDn());
				} else {
					cell7.setCellValue(obj1.get(j).getCpHshcDn());
				}

				XSSFCell cell8 = row.createCell(7);
				cell8.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getCpVanChuyenDn() == null || obj1.get(j).getCpVanChuyenDn() == (float) 0) {
					obj1.get(j).setCpVanChuyenDn((float) 0);
					cell8.setCellValue(obj1.get(j).getCpVanChuyenDn());
				} else {
					cell8.setCellValue(obj1.get(j).getCpVanChuyenDn());
				}

				XSSFCell cell9 = row.createCell(8);
				cell9.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getChiPhiKhacDn() == null || obj1.get(j).getChiPhiKhacDn() == (float) 0) {
					obj1.get(j).setChiPhiKhacDn((float) 0);
					cell9.setCellValue(obj1.get(j).getChiPhiKhacDn());
				} else {
					cell9.setCellValue(obj1.get(j).getChiPhiKhacDn());
				}

				XSSFCell cell10 = row.createCell(9);
				cell10.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getChiPhiLuongDn() == null || obj1.get(j).getChiPhiLuongDn() == (float) 0) {
					obj1.get(j).setChiPhiLuongDn((float) 0);
					cell10.setCellValue(obj1.get(j).getChiPhiLuongDn());
				} else {
					cell10.setCellValue(obj1.get(j).getChiPhiLuongDn());
				}

				XSSFCell cell11 = row.createCell(10);
				cell11.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getVatDn() == null || obj1.get(j).getVatDn() == (float) 0) {
					obj1.get(j).setVatDn((float) 0);
					cell11.setCellValue(obj1.get(j).getVatDn());
				} else {
					cell11.setCellValue(obj1.get(j).getVatDn());
				}

				XSSFCell cell12 = row.createCell(11);
				cell12.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getTongDn() == null || obj1.get(j).getTongDn() == (float) 0) {
					obj1.get(j).setTongDn((float) 0);
					cell12.setCellValue(obj1.get(j).getTongDn());
				} else {
					cell12.setCellValue(obj1.get(j).getTongDn());
				}

				XSSFCell cell13 = row.createCell(12);
				cell13.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getCpNhanCongTd() == null || obj1.get(j).getCpNhanCongTd() == (float) 0) {
					obj1.get(j).setCpNhanCongTd((float) 0);
					cell13.setCellValue(obj1.get(j).getCpNhanCongTd());
				} else {
					cell13.setCellValue(obj1.get(j).getCpNhanCongTd());
				}

				XSSFCell cell14 = row.createCell(13);
				cell14.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getCpVatLieuTd() == null || obj1.get(j).getCpVatLieuTd() == (float) 0) {
					obj1.get(j).setCpVatLieuTd((float) 0);
					cell14.setCellValue(obj1.get(j).getCpVatLieuTd());
				} else {
					cell14.setCellValue(obj1.get(j).getCpVatLieuTd());
				}

				XSSFCell cell15 = row.createCell(14);
				cell15.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getCpHshcTd() == null || obj1.get(j).getCpHshcTd() == (float) 0) {
					obj1.get(j).setCpHshcTd((float) 0);
					cell15.setCellValue(obj1.get(j).getCpHshcTd());
				} else {
					cell15.setCellValue(obj1.get(j).getCpHshcTd());
				}

				XSSFCell cell16 = row.createCell(15);
				cell16.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getCpVanChuyenTd() == null || obj1.get(j).getCpVanChuyenTd() == (float) 0) {
					obj1.get(j).setCpVanChuyenTd((float) 0);
					cell16.setCellValue(obj1.get(j).getCpVanChuyenTd());
				} else {
					// cell16.setCellValue(obj1.get(j).getTtTienDen());
					cell16.setCellValue(obj1.get(j).getCpVanChuyenTd());
				}

				XSSFCell cell17 = row.createCell(16);
				cell17.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getCpKhacTd() == null || obj1.get(j).getCpKhacTd() == (float) 0) {
					obj1.get(j).setCpKhacTd((float) 0);
					cell17.setCellValue(obj1.get(j).getCpKhacTd());
				} else {
					cell17.setCellValue(obj1.get(j).getCpKhacTd());
				}

				XSSFCell cell18 = row.createCell(17);
				cell18.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getCpLuongTd() == null || obj1.get(j).getCpLuongTd() == (float) 0) {
					obj1.get(j).setCpLuongTd((float) 0);
					cell18.setCellValue(obj1.get(j).getCpLuongTd());
				} else {
					cell18.setCellValue(obj1.get(j).getCpLuongTd());
				}

				XSSFCell cell19 = row.createCell(18);
				cell19.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getVatTd() == null || obj1.get(j).getVatTd() == (float) 0) {
					obj1.get(j).setVatTd((float) 0);
					cell19.setCellValue(obj1.get(j).getVatTd());
				} else {
					cell19.setCellValue(obj1.get(j).getVatTd());
				}

				XSSFCell cell20 = row.createCell(19);
				cell20.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getTongTd() == null || obj1.get(j).getTongTd() == (float) 0) {
					obj1.get(j).setTongTd((float) 0);
					cell20.setCellValue(obj1.get(j).getTongTd());
				} else {
					cell20.setCellValue(obj1.get(j).getTongTd());
				}

				XSSFCell cell21 = row.createCell(20);
				cell21.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getTongTd() == 0l && obj1.get(j).getTongDn() == 0l) {
					cell21.setCellValue("0");
				} else {
					cell21.setCellValue(obj1.get(j).getTongTd() / obj1.get(j).getTongDn());
				}

				XSSFCell cell22 = row.createCell(21);
				cell22.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getTyLe() != null) {
					cell22.setCellValue(obj1.get(j).getTyLe());
				} else {
					cell22.setCellValue("0");
				}

				XSSFCell cell23 = row.createCell(22);
				if (obj1.get(j).getGhiChu() != null) {
					cell23.setCellValue(obj1.get(j).getGhiChu());
				} else {
					cell23.setCellValue("");
				}

				cell0.setCellStyle(style);
//				cell1.setCellStyle(style);
				cell2.setCellStyle(styleNumber);
				cell3.setCellStyle(styleNumber);
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
				cell21.setCellStyle(styleNumber);
				cell22.setCellStyle(styleNumber);
				cell23.setCellStyle(styleNumber);
			}
			// tính tổng theo từng điều kiện sử dung lamba java 8(có thể dùng
			// câu sql sum thay thế)
			float sumGtQtCdtChuaVat = obj1.stream().map(e -> e.getGtQtCdtChuaVat()).reduce((float) 0, Float::sum);
			float sumGtQtCdtCoVat = obj1.stream().map(e -> e.getGtQtCdtCoVat()).reduce((float) 0, Float::sum);
			float sumCpNhanCongDn = obj1.stream().map(e -> e.getCpNhanCongDn()).reduce((float) 0, Float::sum);
			float sumCpVatLieuDn = obj1.stream().map(e -> e.getCpVatLieuDn()).reduce((float) 0, Float::sum);
			float sumCpHshcDn = obj1.stream().map(e -> e.getCpHshcDn()).reduce((float) 0, Float::sum);
			float sumCpVanChuyenDn = obj1.stream().map(e -> e.getCpVanChuyenDn()).reduce((float) 0, Float::sum);
			float sumChiPhiKhacDn = obj1.stream().map(e -> e.getChiPhiKhacDn()).reduce((float) 0, Float::sum);
			float sumChiPhiLuongDn = obj1.stream().map(e -> e.getChiPhiLuongDn()).reduce((float) 0, Float::sum);
			float sumVatDn = obj1.stream().map(e -> e.getVatDn()).reduce((float) 0, Float::sum);
			float sumTongDn = obj1.stream().map(e -> e.getTongDn()).reduce((float) 0, Float::sum);

			float sumCpNhanCongTd = obj1.stream().map(e -> e.getCpNhanCongTd()).reduce((float) 0, Float::sum);
			float sumCpVatLieuTd = obj1.stream().map(e -> e.getCpVatLieuTd()).reduce((float) 0, Float::sum);
			float sumCpHshcTd = obj1.stream().map(e -> e.getCpHshcTd()).reduce((float) 0, Float::sum);
			float sumCpVanChuyenTd = obj1.stream().map(e -> e.getCpVanChuyenTd()).reduce((float) 0, Float::sum);
			float sumCpKhacTd = obj1.stream().map(e -> e.getCpKhacTd()).reduce((float) 0, Float::sum);
			float sumCpLuongTd = obj1.stream().map(e -> e.getCpLuongTd()).reduce((float) 0, Float::sum);
			float sumVatTd = obj1.stream().map(e -> e.getVatTd()).reduce((float) 0, Float::sum);
			float sumTongTd = obj1.stream().map(e -> e.getTongTd()).reduce((float) 0, Float::sum);

			// CellRangeAddress(1,2,3,4) : Merg từ hàng thứ 1 đến hàng thứ 2, từ
			// cột thứ 3 --> cột thứ 4
			sheet.addMergedRegion(new CellRangeAddress(obj1.size() + 21, obj1.size() + 21, 0, 1));

			row1 = sheet.createRow(obj1.size() + 21);
			XSSFCell cellbottom0 = row1.createCell(0);
			cellbottom0.setCellValue("Tổng cộng ");
			cellbottom0.setCellStyle(style2);

			XSSFCell cellbottom1 = row1.createCell(1);
			cellbottom1.setCellValue("");
			cellbottom1.setCellStyle(style2);
//
//			XSSFCell cellbottom21 = row1.createCell(2);
//			cellbottom21.setCellValue("");
//			cellbottom21.setCellStyle(style2);

			XSSFCell cellbottom2 = row1.createCell(2);
			cellbottom2.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cellbottom2.setCellValue(sumGtQtCdtChuaVat);
			cellbottom2.setCellStyle(styleNumber);

			XSSFCell cellbottom3 = row1.createCell(3);
			cellbottom3.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cellbottom3.setCellValue(sumGtQtCdtCoVat);
			cellbottom3.setCellStyle(styleNumber);

			XSSFCell cellbottom4 = row1.createCell(4);
			cellbottom4.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cellbottom4.setCellValue(sumCpNhanCongDn);
			cellbottom4.setCellStyle(styleNumber);

			XSSFCell cellbottom5 = row1.createCell(5);
			cellbottom5.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cellbottom5.setCellValue(sumCpVatLieuDn);
			cellbottom5.setCellStyle(styleNumber);

			XSSFCell cellbottom6 = row1.createCell(6);
			cellbottom6.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cellbottom6.setCellValue(sumCpHshcDn);
			cellbottom6.setCellStyle(styleNumber);

			XSSFCell cellbottom7 = row1.createCell(7);
			cellbottom7.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cellbottom7.setCellValue(sumCpVanChuyenDn);
			cellbottom7.setCellStyle(styleNumber);

			XSSFCell cellbottom8 = row1.createCell(8);
			cellbottom8.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cellbottom8.setCellValue(sumChiPhiKhacDn);
			cellbottom8.setCellStyle(styleNumber);

			XSSFCell cellbottom91 = row1.createCell(9);
			cellbottom91.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cellbottom91.setCellValue(sumChiPhiLuongDn);
			cellbottom91.setCellStyle(styleNumber);

			XSSFCell cellbottom9 = row1.createCell(10);
			cellbottom9.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cellbottom9.setCellValue(sumVatDn);
			cellbottom9.setCellStyle(styleNumber);

			XSSFCell cellbottom10 = row1.createCell(11);
			cellbottom10.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cellbottom10.setCellValue(sumTongDn);
			cellbottom10.setCellStyle(styleNumber);

			XSSFCell cellbottom11 = row1.createCell(12);
			cellbottom11.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cellbottom11.setCellValue(sumCpNhanCongTd);
			cellbottom11.setCellStyle(styleNumber);

			XSSFCell cellbottom12 = row1.createCell(13);
			cellbottom12.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cellbottom12.setCellValue(sumCpVatLieuTd);
			cellbottom12.setCellStyle(styleNumber);

			XSSFCell cellbottom13 = row1.createCell(14);
			cellbottom13.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cellbottom13.setCellValue(sumCpHshcTd);
			cellbottom13.setCellStyle(styleNumber);

			XSSFCell cellbottom14 = row1.createCell(15);
			cellbottom14.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cellbottom14.setCellValue(sumCpVanChuyenTd);
			cellbottom14.setCellStyle(styleNumber);

			XSSFCell cellbottom15 = row1.createCell(16);
			cellbottom15.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cellbottom15.setCellValue(sumCpKhacTd);
			cellbottom15.setCellStyle(styleNumber);

			XSSFCell cellbottom16 = row1.createCell(17);
			cellbottom16.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cellbottom16.setCellValue(sumCpLuongTd);
			cellbottom16.setCellStyle(styleNumber);

			XSSFCell cellbottom17 = row1.createCell(18);
			cellbottom17.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cellbottom17.setCellValue(sumVatTd);
			cellbottom17.setCellStyle(styleNumber);

			XSSFCell cellbottom18 = row1.createCell(19);
			cellbottom18.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cellbottom18.setCellValue(sumTongTd);
			cellbottom18.setCellStyle(styleNumber);

			XSSFCell cellbottom19 = row1.createCell(20);
			cellbottom19.setCellValue("");
			cellbottom19.setCellStyle(styleNumber);

			XSSFCell cellbottom20 = row1.createCell(21);
			cellbottom20.setCellValue("");
			cellbottom20.setCellStyle(style);


			sheet.addMergedRegion(new CellRangeAddress(obj1.size() + 26, obj1.size() + 26, 2, 8));
			row1 = sheet.createRow(obj1.size() + 26);
			XSSFCell cellCK1 = row1.createCell(2);
			cellCK1.setCellValue("NHÂN VIÊN THẨM ĐỊNH " );
			cellCK1.setCellStyle(style1);

			sheet.addMergedRegion(new CellRangeAddress(obj1.size() + 26, obj1.size() + 26, 9, 14));
			// row1 = sheet.createRow(obj1.size() + 26);
			XSSFCell cellCK2 = row1.createCell(9);
			cellCK2.setCellValue("PHÒNG THANH KHOẢN");
			cellCK2.setCellStyle(style1);

			sheet.addMergedRegion(new CellRangeAddress(obj1.size() + 26, obj1.size() + 26, 15, 23));
			// row1 = sheet.createRow(obj1.size() + 26);
			XSSFCell cellCK3 = row1.createCell(15);
			cellCK3.setCellValue("PHÒNG TÀI CHÍNH");
			cellCK3.setCellStyle(style1);

			

			File out = new File(folder2Upload + File.separatorChar + "ExportTHTheoHD" + startExportTime + ".xlsx");

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
		return UEncrypt.encryptFileUploadPath("ExportTHTheoHD" + startExportTime + ".xlsx");
	}

	public String exportAllHD(KqHdTkDTO lst, HttpServletRequest request) throws Exception {

		List<KqHdTkDTO> obj1 = kqHdTkDAO.doSearch(lst);
		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream fileIn = new BufferedInputStream(
					new FileInputStream(filePath + "BaoCaoTongHopHopDongPTK.xlsx"));
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

			XSSFRow row1 = null;

			int rownum1 = 2;

			for (int j = 0; j < obj1.size(); j++) {

				XSSFRow row = sheet.createRow(rownum1);
				XSSFCell cell0 = row.createCell(0);
				cell0.setCellValue(j + 1);
				rownum1++;

				XSSFCell cell1 = row.createCell(1);
				cell1.setCellValue(obj1.get(j).getTinh());

				XSSFCell cell2 = row.createCell(2);
				cell2.setCellValue(obj1.get(j).getMaTramTuyen());

				XSSFCell cell3 = row.createCell(3);
				cell3.setCellValue(obj1.get(j).getNguonCapUng());

				XSSFCell cell4 = row.createCell(4);
				cell4.setCellValue(obj1.get(j).getSoHd());

				XSSFCell cell5 = row.createCell(5);
				cell5.setCellValue(obj1.get(j).getSoKhTc());

				XSSFCell cell6 = row.createCell(6);
				if (obj1.get(j).getNgayKyKh() != null) {
					cell6.setCellValue(dt.format(obj1.get(j).getNgayKyKh()));
				} else {
					cell6.setCellValue("");
				}

				XSSFCell cell7 = row.createCell(7);
				cell7.setCellValue(obj1.get(j).getThuocDmToTrinh());

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
				cell12.setCellValue(obj1.get(j).getGhiChu());
				XSSFCell cell13 = row.createCell(13);
				if (obj1.get(j).getNgayThiCong() != null) {
					cell13.setCellValue(dt.format(obj1.get(j).getNgayThiCong()));
				} else {
					cell13.setCellValue("");
				}

				XSSFCell cell14 = row.createCell(14);
				cell14.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getGtQtCdtChuaVat() == null || obj1.get(j).getGtQtCdtChuaVat() == (float) 0) {
					obj1.get(j).setGtQtCdtChuaVat((float) 0);
					cell14.setCellValue(obj1.get(j).getGtQtCdtChuaVat());
				} else {
					cell14.setCellValue(obj1.get(j).getGtQtCdtChuaVat());
				}

				XSSFCell cell15 = row.createCell(15);
				cell15.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getGtQtCdtCoVat() == null || obj1.get(j).getGtQtCdtCoVat() == (float) 0) {
					obj1.get(j).setGtQtCdtCoVat((float) 0);
					cell15.setCellValue(obj1.get(j).getGtQtCdtCoVat());
				} else {
					cell15.setCellValue(obj1.get(j).getGtQtCdtCoVat());
				}

				XSSFCell cell16 = row.createCell(16);
				cell16.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getCpNhanCongDn() == null || obj1.get(j).getCpNhanCongDn() == (float) 0) {
					obj1.get(j).setCpNhanCongDn((float) 0);
					cell16.setCellValue(obj1.get(j).getCpNhanCongDn());
				} else {
					cell16.setCellValue(obj1.get(j).getCpNhanCongDn());
				}

				XSSFCell cell17 = row.createCell(17);
				cell17.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getCpVatLieuDn() == null || obj1.get(j).getCpVatLieuDn() == (float) 0) {
					obj1.get(j).setCpVatLieuDn((float) 0);
					cell17.setCellValue(obj1.get(j).getCpVatLieuDn());
				} else {
					cell17.setCellValue(obj1.get(j).getCpVatLieuDn());
				}

				XSSFCell cell18 = row.createCell(18);
				cell18.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getCpHshcDn() == null || obj1.get(j).getCpHshcDn() == (float) 0) {
					obj1.get(j).setCpHshcDn((float) 0);
					cell18.setCellValue(obj1.get(j).getCpHshcDn());
				} else {
					cell18.setCellValue(obj1.get(j).getCpHshcDn());
				}

				XSSFCell cell19 = row.createCell(19);
				cell19.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getCpVanChuyenDn() == null || obj1.get(j).getCpVanChuyenDn() == (float) 0) {
					obj1.get(j).setCpVanChuyenDn((float) 0);
					cell19.setCellValue(obj1.get(j).getCpVanChuyenDn());
				} else {
					cell19.setCellValue(obj1.get(j).getCpVanChuyenDn());
				}

				XSSFCell cell20 = row.createCell(20);
				cell20.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getChiPhiKhacDn() == null || obj1.get(j).getChiPhiKhacDn() == (float) 0) {
					obj1.get(j).setChiPhiKhacDn((float) 0);
					cell20.setCellValue(obj1.get(j).getChiPhiKhacDn());
				} else {
					cell20.setCellValue(obj1.get(j).getChiPhiKhacDn());
				}

				XSSFCell cell21 = row.createCell(21);
				cell21.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getChiPhiLuongDn() == null || obj1.get(j).getChiPhiLuongDn() == (float) 0) {
					obj1.get(j).setChiPhiLuongDn((float) 0);
					cell21.setCellValue(obj1.get(j).getChiPhiLuongDn());
				} else {
					cell21.setCellValue(obj1.get(j).getChiPhiLuongDn());
				}

				XSSFCell cell22 = row.createCell(22);
				cell22.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getVatDn() == null || obj1.get(j).getVatDn() == (float) 0) {
					obj1.get(j).setVatDn((float) 0);
					cell22.setCellValue(obj1.get(j).getVatDn());
				} else {
					cell22.setCellValue(obj1.get(j).getVatDn());
				}

				XSSFCell cell23 = row.createCell(23);
				cell23.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getTongDn() == null || obj1.get(j).getTongDn() == (float) 0) {
					obj1.get(j).setTongDn((float) 0);
					cell23.setCellValue(obj1.get(j).getTongDn());
				} else {
					cell23.setCellValue(obj1.get(j).getTongDn());
				}

				XSSFCell cell24 = row.createCell(24);
				cell24.setCellValue(obj1.get(j).getNguoiCapNhat());

				XSSFCell cell25 = row.createCell(25);
				if (obj1.get(j).getNgayGuiPtkHshc() != null) {
					cell25.setCellValue(dt.format(obj1.get(j).getNgayGuiPtkHshc()));
				} else {
					cell25.setCellValue("");
				}

				XSSFCell cell26 = row.createCell(26);
				cell26.setCellValue(obj1.get(j).getTinhTrangChungTu());

				XSSFCell cell27 = row.createCell(27);
				if (obj1.get(j).getNgayTutttt() != null) {
					cell27.setCellValue(dt.format(obj1.get(j).getNgayTutttt()));
				} else {
					cell27.setCellValue("");
				}

				XSSFCell cell28 = row.createCell(28);
				cell28.setCellValue(obj1.get(j).getGhiChuHsLoi());

				XSSFCell cell29 = row.createCell(29);
				if (obj1.get(j).getNgayPtkTdXong() != null) {
					cell29.setCellValue(dt.format(obj1.get(j).getNgayPtkTdXong()));
				} else {
					cell29.setCellValue("");
				}

				XSSFCell cell30 = row.createCell(30);
				cell30.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getCpNhanCongTd() == null || obj1.get(j).getCpNhanCongTd() == (float) 0) {
					obj1.get(j).setCpNhanCongTd((float) 0);
					cell30.setCellValue(obj1.get(j).getCpNhanCongTd());
				} else {
					cell30.setCellValue(obj1.get(j).getCpNhanCongTd());
				}

				XSSFCell cell31 = row.createCell(31);
				cell31.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getCpVatLieuTd() == null || obj1.get(j).getCpVatLieuTd() == (float) 0) {
					obj1.get(j).setCpVatLieuTd((float) 0);
					cell31.setCellValue(obj1.get(j).getCpVatLieuTd());
				} else {
					cell31.setCellValue(obj1.get(j).getCpVatLieuTd());
				}

				XSSFCell cell32 = row.createCell(32);
				cell32.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getCpHshcTd() == null || obj1.get(j).getCpHshcTd() == (float) 0) {
					obj1.get(j).setCpHshcTd((float) 0);
					cell32.setCellValue(obj1.get(j).getCpHshcTd());
				} else {
					cell32.setCellValue(obj1.get(j).getCpHshcTd());
				}

				XSSFCell cell33 = row.createCell(33);
				cell33.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getCpVanChuyenTd() == null || obj1.get(j).getCpVanChuyenTd() == (float) 0) {
					obj1.get(j).setCpVanChuyenTd((float) 0);
					cell33.setCellValue(obj1.get(j).getCpVanChuyenTd());
				} else {
					// cell16.setCellValue(obj1.get(j).getTtTienDen());
					cell33.setCellValue(obj1.get(j).getCpVanChuyenTd());
				}

				XSSFCell cell34 = row.createCell(34);
				cell34.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getCpKhacTd() == null || obj1.get(j).getCpKhacTd() == (float) 0) {
					obj1.get(j).setCpKhacTd((float) 0);
					cell34.setCellValue(obj1.get(j).getCpKhacTd());
				} else {
					cell34.setCellValue(obj1.get(j).getCpKhacTd());
				}

				XSSFCell cell35 = row.createCell(35);
				cell34.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getCpLuongTd() == null || obj1.get(j).getCpLuongTd() == (float) 0) {
					obj1.get(j).setCpLuongTd((float) 0);
					cell35.setCellValue(obj1.get(j).getCpLuongTd());
				} else {
					cell35.setCellValue(obj1.get(j).getCpLuongTd());
				}

				XSSFCell cell36 = row.createCell(36);
				cell36.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getVatTd() == null || obj1.get(j).getVatTd() == (float) 0) {
					obj1.get(j).setVatTd((float) 0);
					cell36.setCellValue(obj1.get(j).getVatTd());
				} else {
					cell36.setCellValue(obj1.get(j).getVatTd());
				}

				XSSFCell cell37 = row.createCell(37);
				cell37.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getTongTd() == null || obj1.get(j).getTongTd() == (float) 0) {
					obj1.get(j).setTongTd((float) 0);
					cell37.setCellValue(obj1.get(j).getTongTd());
				} else {
					cell37.setCellValue(obj1.get(j).getTongTd());
				}

				XSSFCell cell38 = row.createCell(38);
				cell38.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getGtTdPtkChuaVat() == null || obj1.get(j).getGtTdPtkChuaVat() == (float) 0) {
					obj1.get(j).setGtTdPtkChuaVat((float) 0);
					cell38.setCellValue(obj1.get(j).getGtTdPtkChuaVat());
				} else {
					cell38.setCellValue(obj1.get(j).getGtTdPtkChuaVat());
				}

				XSSFCell cell39 = row.createCell(39);
				cell39.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getGtTdPtkCoVat() == null || obj1.get(j).getGtTdPtkCoVat() == (float) 0) {
					obj1.get(j).setGtTdPtkCoVat((float) 0);
					cell39.setCellValue(obj1.get(j).getGtTdPtkCoVat());
				} else {
					cell39.setCellValue(obj1.get(j).getGtTdPtkCoVat());
				}

				XSSFCell cell40 = row.createCell(40);
				cell40.setCellValue(obj1.get(j).getThangHshcQuyLuong());

				XSSFCell cell41 = row.createCell(41);
				cell41.setCellValue(obj1.get(j).getThangGhiNhanQuyLuongTqt());

				XSSFCell cell42 = row.createCell(42);
				cell42.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getTamUngLuong() == null || obj1.get(j).getTamUngLuong() == (float) 0) {
					obj1.get(j).setTamUngLuong((float) 0);
					cell42.setCellValue(obj1.get(j).getTamUngLuong());
				} else {
					cell42.setCellValue(obj1.get(j).getTamUngLuong());
				}

				XSSFCell cell43 = row.createCell(43);
				cell43.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getLuongThucNhan() == null || obj1.get(j).getLuongThucNhan() == (float) 0) {
					obj1.get(j).setLuongThucNhan((float) 0);
					cell43.setCellValue(obj1.get(j).getLuongThucNhan());
				} else {
					cell43.setCellValue(obj1.get(j).getLuongThucNhan());
				}

				XSSFCell cell44 = row.createCell(44);
				cell44.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getTyLe() == null || obj1.get(j).getTyLe() == (float) 0) {
					obj1.get(j).setTyLe((float) 0);
					cell44.setCellValue(obj1.get(j).getTyLe());
				} else {
					cell44.setCellValue(obj1.get(j).getTyLe());
				}

				XSSFCell cell45 = row.createCell(45);
				cell45.setCellValue(obj1.get(j).getHsTonQua5n());

				XSSFCell cell46 = row.createCell(46);
				cell46.setCellValue(obj1.get(j).getGtQhXlCt());

				XSSFCell cell47 = row.createCell(47);
				cell47.setCellValue(obj1.get(j).getNguoiPheDuyetPtk());
				
				XSSFCell cell48 = row.createCell(48);
				cell48.setCellValue(obj1.get(j).getLyDoTc());
				
				XSSFCell cell49 = row.createCell(49);
				cell49.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getKqHdTkId() == null || obj1.get(j).getKqHdTkId() == (long) 0) {
					obj1.get(j).setKqHdTkId((long) 0);
					cell49.setCellValue(obj1.get(j).getKqHdTkId());
				} else {
					cell49.setCellValue(obj1.get(j).getKqHdTkId());
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
				cell12.setCellStyle(styleNumber);
				cell13.setCellStyle(styleNumber);
				cell14.setCellStyle(styleNumber);
				cell15.setCellStyle(styleNumber);
				cell16.setCellStyle(styleNumber);
				cell17.setCellStyle(styleNumber);
				cell18.setCellStyle(styleNumber);
				cell19.setCellStyle(styleNumber);
				cell20.setCellStyle(styleNumber);
				cell21.setCellStyle(styleNumber);
				cell22.setCellStyle(styleNumber);
				cell23.setCellStyle(style);
				cell24.setCellStyle(style);
				cell25.setCellStyle(style);
				cell26.setCellStyle(style);
				cell27.setCellStyle(style);
				cell28.setCellStyle(style);
				cell29.setCellStyle(styleNumber);
				cell30.setCellStyle(styleNumber);
				cell31.setCellStyle(styleNumber);
				cell32.setCellStyle(styleNumber);
				cell33.setCellStyle(styleNumber);
				cell34.setCellStyle(styleNumber);
				cell35.setCellStyle(styleNumber);
				cell36.setCellStyle(styleNumber);
				cell37.setCellStyle(styleNumber);
				cell38.setCellStyle(styleNumber);
				cell39.setCellStyle(style);
				cell40.setCellStyle(style);
				cell41.setCellStyle(styleNumber);
				cell42.setCellStyle(styleNumber);
				cell43.setCellStyle(style);
				cell44.setCellStyle(style);
				cell45.setCellStyle(style);
				cell46.setCellStyle(style);
				cell47.setCellStyle(style);
				cell48.setCellStyle(style);
				cell49.setCellStyle(style);
			}

			File out = new File(
					folder2Upload + File.separatorChar + "BaoCaoTongHopHopDongPTK" + startExportTime + ".xlsx");

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
		return UEncrypt.encryptFileUploadPath("BaoCaoTongHopHopDongPTK" + startExportTime + ".xlsx");
	}

	public String xuatLuongTheoSoHdtc(KqHdTkDTO obj, HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		List<KqHdTkDTO> lst = kqHdTkDAO.tongLuongThucNhan(obj);
		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream file = new BufferedInputStream(new FileInputStream(filePath + "xuatBaoCaoLuongHDTC.xlsx"));

			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);
			// set style
			XSSFCellStyle style = workbook.createCellStyle();
			XSSFCellStyle style1 = workbook.createCellStyle();
			XSSFCellStyle style2 = workbook.createCellStyle();
			XSSFCellStyle styleNumber = workbook.createCellStyle();
			XSSFFont hSSFFont = workbook.createFont();
			hSSFFont.setFontName("Times New Roman");
			style.setFont(hSSFFont);
			DataFormat format = workbook.createDataFormat();

			try {
				XSSFFont hSSFFont1 = workbook.createFont();
				hSSFFont1.setFontName("Times New Roman");
				hSSFFont1.setFontHeightInPoints((short) 12);
				hSSFFont1.setBold(true);
				style1.setFont(hSSFFont1);
				style1.setWrapText(true);
				style1.setAlignment(CellStyle.ALIGN_CENTER);
			} catch (Exception e) {
				e.printStackTrace();
			}
			style2.setAlignment(CellStyle.ALIGN_CENTER);
			style.setBorderBottom(BorderStyle.THIN);
			style.setBorderTop(BorderStyle.THIN);
			style.setBorderRight(BorderStyle.THIN);
			style.setBorderLeft(BorderStyle.THIN);
			style.setWrapText(true);

			styleNumber.setBorderBottom(BorderStyle.THIN);
			styleNumber.setBorderTop(BorderStyle.THIN);
			styleNumber.setDataFormat(format.getFormat("#,##0"));
			styleNumber.setBorderRight(BorderStyle.THIN);
			styleNumber.setBorderLeft(BorderStyle.THIN);
			styleNumber.setWrapText(true);
			styleNumber.setAlignment(CellStyle.ALIGN_RIGHT);

			XSSFRow row = null;
			row = sheet.createRow(0);
			XSSFCell cell011 = row.createCell(0);
			cell011.setCellValue("PHỤ LỤC CHI TIẾT QUỸ LƯƠNG THÁNG " + obj.getThangGhiNhanQuyLuongTqt());
			cell011.setCellStyle(style2);
			int rownum = 4;

			for (int i = 0; i < lst.size(); i++) {
				try {
					// StringBuilder err = new StringBuilder();
					// String strFormula = "H" + (i + 5) + "/G" + (i + 5);
					row = sheet.createRow(rownum);
					XSSFCell cell0 = row.createCell(0);
					cell0.setCellValue(i + 1);
					cell0.setCellStyle(style);
					rownum++;

					XSSFCell cell1 = row.createCell(1);
					cell1.setCellValue(lst.get(i).getTinh());

					XSSFCell cell2 = row.createCell(2);
					cell2.setCellValue(lst.get(i).getSoKhTc());

					XSSFCell cell3 = row.createCell(3);
					if (lst.get(i).getNgayKyKh() != null) {
						cell3.setCellValue(dt.format(lst.get(i).getNgayKyKh()));
					} else {
						cell3.setCellValue("");
					}

					XSSFCell cell4 = row.createCell(4);
					cell4.setCellValue(lst.get(i).getMaTramTuyen());

					XSSFCell cell5 = row.createCell(5);
					if (lst.get(i).getLoaiCt() != null) {
						switch (lst.get(i).getLoaiCt()) {
						case "1":
							cell5.setCellValue("Nhà trạm");
							break;
						case "2":
							cell5.setCellValue("Truyền dẫn");
							break;
						case "3":
							cell5.setCellValue("Cơ điện");
							break;
						case "4":
							cell5.setCellValue("GPON");
							break;

						default:

							break;
						}
					} else {
						cell5.setCellValue("");
					}

					XSSFCell cell6 = row.createCell(6);
					cell6.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					if (lst.get(i).getTamUngLuong() != null) {
						cell6.setCellValue(lst.get(i).getTamUngLuong());
					} else {
						lst.get(i).setTamUngLuong((float) 0);
						cell6.setCellValue("0");
					}

					XSSFCell cell7 = row.createCell(7);
					cell7.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					if (lst.get(i).getCpLuongTd() != null) {
						cell7.setCellValue(lst.get(i).getCpLuongTd());
					} else {
						lst.get(i).setCpLuongTd((float) 0);
						cell7.setCellValue("0");
					}
					if (lst.get(i).getCpLuongTd() == null) {
						lst.get(i).setCpLuongTd((float) 0);
					}
					if (lst.get(i).getTamUngLuong() == null) {
						lst.get(i).setTamUngLuong((float) 0);
					}
					
					XSSFCell cell8 = row.createCell(8);
					cell8.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					if(lst.get(i).getCpLuongTd()!=null&&lst.get(i).getTamUngLuong()!=null){
						float luongThang = lst.get(i).getCpLuongTd() - lst.get(i).getTamUngLuong();
						cell8.setCellValue(luongThang);
						
					}else{
						cell8.setCellValue("0");
					}
					
					
					
					

					XSSFCell cell9 = row.createCell(9);
					cell9.setCellValue("0");

					XSSFCell cell10 = row.createCell(10);
					cell10.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					if(lst.get(i).getCpLuongTd()!=null&&lst.get(i).getTamUngLuong()!=null){
						float luongThang = lst.get(i).getCpLuongTd() - lst.get(i).getTamUngLuong();
						cell10.setCellValue(luongThang);
						
					}else{
						cell10.setCellValue("0");
					}

					XSSFCell cell11 = row.createCell(11);
					cell11.setCellValue(lst.get(i).getGhiChu());

					cell0.setCellStyle(style);
					cell1.setCellStyle(style);
					cell2.setCellStyle(style);
					cell3.setCellStyle(style);
					cell4.setCellStyle(style);
					cell5.setCellStyle(style);
					cell6.setCellStyle(styleNumber);
					cell7.setCellStyle(styleNumber);
					cell8.setCellStyle(styleNumber);
					cell9.setCellStyle(style);
					cell10.setCellStyle(styleNumber);
					cell11.setCellStyle(style);
				} catch (Exception e) {
					log.error(e.getMessage());
				}

			}

			file.close();

			File out = new File(folder2Upload + File.separatorChar + "xuatBaoCaoLuongHDTC" + startExportTime + ".xlsx");

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
		return UEncrypt.encryptFileUploadPath("xuatBaoCaoLuongHDTC" + startExportTime + ".xlsx");
	}

	public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
		Set<Object> seen = ConcurrentHashMap.newKeySet();
		return t -> seen.add(keyExtractor.apply(t));
	}

	public String xuatBCTH(KqHdTkDTO obj, HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub

		List<KqHdTkDTO> lstTinh = kqHdTkDAO.getMaTinh(obj);
		List<KqHdTkDTO> lstObj = new ArrayList<>();
		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		
		for (int i = 0; i < lstTinh.size(); i++) {
			KqHdTkDTO obj1 = new KqHdTkDTO();
			List<KqHdTkDTO> obj2 = new ArrayList<>();
			obj.setTinh(lstTinh.get(i).getTinh());
			obj1 = kqHdTkDAO.xuatBCTH(obj);
			obj2 = kqHdTkDAO.lstTrangThai(obj);
			// đếm tình trạng chứng từ theo từng điều kiện sử dụng lamba java 8
			long ttct1 = obj2.stream().filter(x ->x.getTinhTrangChungTu()!=null&& x.getTinhTrangChungTu().equals("Lỗi")).collect(Collectors.counting());
					
			long ttct2 = obj2.stream().filter(x ->x.getTinhTrangChungTu()!=null&& x.getTinhTrangChungTu().equals("Đang tại Phòng TK")).collect(Collectors.counting());
					
			long ttct3 = obj2.stream().filter(x -> x.getTinhTrangChungTu()!=null&&x.getTinhTrangChungTu().equals("Tạm tính")).collect(Collectors.counting());
					
			long ttct4 = obj2.stream().filter(x -> x.getTinhTrangChungTu()!=null&&x.getTinhTrangChungTu().equals("Đang ở Tài chính")).collect(Collectors.counting());
					
			long ttct5 = obj2.stream().filter(x -> x.getTinhTrangChungTu()!=null&&x.getTinhTrangChungTu().equals("Đang ở PGĐ")).collect(Collectors.counting());
					
			long ttct6 = obj2.stream().filter(x ->x.getTinhTrangChungTu()!=null&& x.getTinhTrangChungTu().equals("Đang ở Văn thư")).collect(Collectors.counting());
					
			long ttct7 = obj2.stream().filter(x ->x.getTinhTrangChungTu()!=null&& x.getTinhTrangChungTu().equals("Xong")).collect(Collectors.counting());
					
//			long hsQua5N = obj2.stream().filter(x->x.getTinhTrangChungTu()!=null&& x.getHsTonQua5n().equals("Quá hạn")).collect(Collectors.counting());
			
			long soHdChuaTd = obj2.stream().filter(x ->x.getNgayGuiPtkHshc()==null).collect(Collectors.counting());
			obj1.setTtct1(ttct1);
			obj1.setTtct2(ttct2);
			obj1.setTtct3(ttct3);
			obj1.setTtct4(ttct4);
			obj1.setTtct5(ttct5);
			obj1.setTtct6(ttct6);
			obj1.setTtct7(ttct7);
//			obj1.setHsQua5N(hsQua5N);
			obj1.setSoHdChuaTd(soHdChuaTd);
			obj1.setTinh(lstTinh.get(i).getTinh());
			lstObj.add(obj1);
			
		}
		
		
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream file = new BufferedInputStream(new FileInputStream(filePath + "THBCTheoNgay.xlsx"));

			XSSFWorkbook workbook = new XSSFWorkbook(file);
//			XSSFWorkbook workbook1 = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);
			workbook.setSheetName(workbook.getSheetIndex(sheet), "Báo cáo TH");
			XSSFSheet sheet1 = workbook.getSheetAt(1);
			DataFormat format = workbook.createDataFormat();
			workbook.setSheetName(workbook.getSheetIndex(sheet1), "Số hợp đồng đã đề nghị chưa thẩm định");
			// set style
			XSSFCellStyle style = workbook.createCellStyle();
			XSSFCellStyle style1 = workbook.createCellStyle();
			XSSFCellStyle styleNumber = workbook.createCellStyle();
			XSSFFont hSSFFont = workbook.createFont();
			hSSFFont.setFontName("Times New Roman");
			style.setFont(hSSFFont);

			try {
				XSSFFont hSSFFont1 = workbook.createFont();
				hSSFFont1.setFontName("Times New Roman");
				hSSFFont1.setFontHeightInPoints((short) 12);
				hSSFFont1.setBold(true);
				style1.setFont(hSSFFont1);
				style1.setWrapText(true);
				style1.setAlignment(CellStyle.ALIGN_CENTER);
			} catch (Exception e) {
				e.printStackTrace();
			}

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

			XSSFRow row = null;
			int rownum1 = 2;
			int rownum = 5;
			String strFormulaC = "SUBTOTAL(9," + "C" + 6 + ":C" + (lstObj.size() + 5) + ")";
			String strFormulaD = "SUBTOTAL(9," + "D" + 6 + ":D" + (lstObj.size() + 5) + ")";
			String strFormulaF = "SUBTOTAL(9," + "F" + 6 + ":F" + (lstObj.size() + 5) + ")";
			String strFormulaG = "SUBTOTAL(9," + "G" + 6 + ":G" + (lstObj.size() + 5) + ")";
			String strFormulaH = "SUBTOTAL(9," + "H" + 6 + ":H" + (lstObj.size() + 5) + ")";
			String strFormulaI = "SUBTOTAL(9," + "I" + 6 + ":I" + (lstObj.size() + 5) + ")";
			String strFormulaJ = "SUBTOTAL(9," + "J" + 6 + ":J" + (lstObj.size() + 5) + ")";
			String strFormulaK = "SUBTOTAL(9," + "K" + 6 + ":K" + (lstObj.size() + 5) + ")";
			String strFormulaL = "SUBTOTAL(9," + "L" + 6 + ":L" + (lstObj.size() + 5) + ")";
			String strFormulaM = "SUBTOTAL(9," + "M" + 6 + ":M" + (lstObj.size() + 5) + ")";
			String strFormulaN = "SUBTOTAL(9," + "N" + 6 + ":N" + (lstObj.size() + 5) + ")";
			String strFormulaO = "SUBTOTAL(9," + "O" + 6 + ":O" + (lstObj.size() + 5) + ")";
			String strFormulaP = "SUBTOTAL(9," + "P" + 6 + ":P" + (lstObj.size() + 5) + ")";
			String strFormulaQ = "SUBTOTAL(9," + "Q" + 6 + ":Q" + (lstObj.size() + 5) + ")";
			String strFormulaR = "SUBTOTAL(9," + "R" + 6 + ":R" + (lstObj.size() + 5) + ")";
			String strFormulaS = "SUBTOTAL(9," + "S" + 6 + ":S" + (lstObj.size() + 5) + ")";
			String strFormulaT = "SUBTOTAL(9," + "T" + 6 + ":T" + (lstObj.size() + 5) + ")";
			String strFormulaU = "SUBTOTAL(9," + "U" + 6 + ":U" + (lstObj.size() + 5) + ")";
			String strFormulaV = "SUBTOTAL(9," + "V" + 6 + ":V" + (lstObj.size() + 5) + ")";

			String strFormulaW = "SUBTOTAL(9," + "W" + 6 + ":W" + (lstObj.size() + 5) + ")";
			String strFormulaX = "SUBTOTAL(9," + "X" + 6 + ":X" + (lstObj.size() + 5) + ")";
			String strFormulaY = "SUBTOTAL(9," + "Y" + 6 + ":Y" + (lstObj.size() + 5) + ")";
			String strFormulaZ = "SUBTOTAL(9," + "Z" + 6 + ":Z" + (lstObj.size() + 5) + ")";
			String strFormulaAA = "SUBTOTAL(9," + "AA" + 6 + ":AA" + (lstObj.size() + 5) + ")";
			String strFormulaAB = "SUBTOTAL(9," + "AB" + 6 + ":AB" + (lstObj.size() + 5) + ")";
			String strFormulaAC = "SUBTOTAL(9," + "AC" + 6 + ":AC" + (lstObj.size() + 5) + ")";
			String strFormulaAD = "SUBTOTAL(9," + "AD" + 6 + ":AD" + (lstObj.size() + 5) + ")";
			String strFormulaAE = "SUBTOTAL(9," + "AE" + 6 + ":AE" + (lstObj.size() + 5) + ")";
			String strFormulaAF = "SUBTOTAL(9," + "AF" + 6 + ":AF" + (lstObj.size() + 5) + ")";
			String strFormulaAG = "SUBTOTAL(9," + "AG" + 6 + ":AG" + (lstObj.size() + 5) + ")";
			String strFormulaAH = "SUBTOTAL(9," + "AH" + 6 + ":AH" + (lstObj.size() + 5) + ")";
			String strFormulaAI = "SUBTOTAL(9," + "AI" + 6 + ":AI" + (lstObj.size() + 5) + ")";
			String strFormulaAJ = "SUBTOTAL(9," + "AJ" + 6 + ":AJ" + (lstObj.size() + 5) + ")";
			for (int i = 0; i < lstObj.size(); i++) {
				// StringBuilder err = new StringBuilder();

				// String strFormula = "H" + (i + 5) + "/G" + (i + 5);
				row = sheet.createRow(rownum);
				XSSFCell cell0 = row.createCell(0);
				cell0.setCellValue(i + 1);
				cell0.setCellStyle(style);
				rownum++;

				XSSFCell cell1 = row.createCell(1);
				cell1.setCellValue(lstObj.get(i).getTinh());

				XSSFCell cell2 = row.createCell(2);
				cell2.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj.getNgayGuiPtkHshc() != null) {
					cell2.setCellValue(lstObj.get(i).getSoHs1());
				} else {
					cell2.setCellValue("0");
				}

				XSSFCell cell3 = row.createCell(3);
				cell3.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj.getThangGhiNhanQuyLuongTqt() != null) {
					cell3.setCellValue(lstObj.get(i).getSoHs1());
				} else {
					cell3.setCellValue("0");
				}

				XSSFCell cell4 = row.createCell(4);
				cell4.setCellValue("");

				XSSFCell cell5 = row.createCell(5);
				cell5.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (lstObj.get(i).getGtQtCdtChuaVat() != null && lstObj.get(i).getGtQtCdtChuaVat() != 0l) {
					cell5.setCellValue(lstObj.get(i).getGtQtCdtChuaVat());
				} else {
					cell5.setCellValue("0");
				}

				XSSFCell cell6 = row.createCell(6);
				cell6.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (lstObj.get(i).getGtQtCdtCoVat() != null && lstObj.get(i).getGtQtCdtCoVat() != 0l) {
					cell6.setCellValue(lstObj.get(i).getGtQtCdtCoVat());
				} else {
					cell6.setCellValue("0");
				}
				XSSFCell cell7 = row.createCell(7);
				cell7.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (lstObj.get(i).getGtTdPtkChuaVat() != null && lstObj.get(i).getGtTdPtkChuaVat() != 0l) {
					cell7.setCellValue(lstObj.get(i).getGtTdPtkChuaVat());
				} else {
					cell7.setCellValue("0");
				}
				XSSFCell cell8 = row.createCell(8);
				cell8.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (lstObj.get(i).getGtTdPtkCoVat() != null && lstObj.get(i).getGtTdPtkCoVat() != 0l) {
					cell8.setCellValue(lstObj.get(i).getGtTdPtkCoVat());
				} else {
					cell8.setCellValue("0");
				}
				XSSFCell cell9 = row.createCell(9);
				cell9.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (lstObj.get(i).getTamUngLuong() != null && lstObj.get(i).getTamUngLuong() != 0l) {
					cell9.setCellValue(lstObj.get(i).getTamUngLuong());
				} else {
					cell9.setCellValue("0");
				}

				XSSFCell cell10 = row.createCell(10);
				cell10.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (lstObj.get(i).getLuongThucNhan() != null && lstObj.get(i).getLuongThucNhan() != 0l) {
					cell10.setCellValue(lstObj.get(i).getLuongThucNhan());
				} else {
					cell10.setCellValue("0");
				}

				XSSFCell cell11 = row.createCell(11);
				cell11.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (lstObj.get(i).getCpNhanCongDn() != null && lstObj.get(i).getCpNhanCongDn() != 0l) {
					cell11.setCellValue(lstObj.get(i).getCpNhanCongDn());
				} else {
					cell11.setCellValue("0");
				}

				XSSFCell cell12 = row.createCell(12);
				cell12.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (lstObj.get(i).getCpVatLieuDn() != null && lstObj.get(i).getCpVatLieuDn() != 0l) {
					cell12.setCellValue(lstObj.get(i).getCpVatLieuDn());
				} else {
					cell12.setCellValue("0");
				}

				XSSFCell cell13 = row.createCell(13);
				cell13.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (lstObj.get(i).getCpHshcDn() != null && lstObj.get(i).getCpHshcDn() != 0l) {
					cell13.setCellValue(lstObj.get(i).getCpHshcDn());
				} else {
					cell13.setCellValue("0");
				}

				XSSFCell cell14 = row.createCell(14);
				cell14.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (lstObj.get(i).getCpVanChuyenDn() != null && lstObj.get(i).getCpVanChuyenDn() != 0l) {
					cell14.setCellValue(lstObj.get(i).getCpVanChuyenDn());
				} else {
					cell14.setCellValue("0");
				}

				XSSFCell cell15 = row.createCell(15);
				cell15.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (lstObj.get(i).getChiPhiKhacDn() != null && lstObj.get(i).getChiPhiKhacDn() != 0l) {
					cell15.setCellValue(lstObj.get(i).getChiPhiKhacDn());
				} else {
					cell15.setCellValue("0");
				}

				XSSFCell cell16 = row.createCell(16);
				cell16.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (lstObj.get(i).getChiPhiLuongDn() != null && lstObj.get(i).getChiPhiLuongDn() != 0l) {
					cell16.setCellValue(lstObj.get(i).getChiPhiLuongDn());
				} else {
					cell16.setCellValue("0");
				}

				XSSFCell cell17 = row.createCell(17);
				cell17.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (lstObj.get(i).getVatDn() != null && lstObj.get(i).getVatDn() != 0l) {
					cell17.setCellValue(lstObj.get(i).getVatDn());
				} else {
					cell17.setCellValue("0");
				}

				XSSFCell cell18 = row.createCell(18);
				cell18.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (lstObj.get(i).getTongDn() != null && lstObj.get(i).getTongDn() != 0l) {
					cell18.setCellValue(lstObj.get(i).getTongDn());
				} else {
					cell18.setCellValue("0");
				}

				XSSFCell cell19 = row.createCell(19);
				cell19.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (lstObj.get(i).getCpNhanCongTd() != null && lstObj.get(i).getCpNhanCongTd() != 0l) {
					cell19.setCellValue(lstObj.get(i).getCpNhanCongTd());
				} else {
					cell19.setCellValue("0");
				}

				XSSFCell cell20 = row.createCell(20);
				cell20.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (lstObj.get(i).getCpVatLieuTd() != null && lstObj.get(i).getCpVatLieuTd() != 0l) {
					cell20.setCellValue(lstObj.get(i).getCpVatLieuTd());
				} else {
					cell20.setCellValue("0");
				}

				XSSFCell cell21 = row.createCell(21);
				cell21.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (lstObj.get(i).getCpHshcTd() != null && lstObj.get(i).getCpHshcTd() != 0l) {
					cell21.setCellValue(lstObj.get(i).getCpHshcTd());
				} else {
					cell21.setCellValue("0");
				}

				XSSFCell cell22 = row.createCell(22);
				cell22.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (lstObj.get(i).getCpVanChuyenTd() != null && lstObj.get(i).getCpVanChuyenTd() != 0l) {
					cell22.setCellValue(lstObj.get(i).getCpVanChuyenTd());
				} else {
					cell22.setCellValue("0");
				}

				XSSFCell cell23 = row.createCell(23);
				cell23.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (lstObj.get(i).getCpKhacTd() != null && lstObj.get(i).getCpKhacTd() != 0l) {
					cell23.setCellValue(lstObj.get(i).getCpKhacTd());
				} else {
					cell23.setCellValue("0");
				}

				XSSFCell cell24 = row.createCell(24);
				cell24.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (lstObj.get(i).getCpLuongTd() != null && lstObj.get(i).getCpLuongTd() != 0l) {
					cell24.setCellValue(lstObj.get(i).getCpLuongTd());
				} else {
					cell24.setCellValue("0");
				}

				XSSFCell cell25 = row.createCell(25);
				cell25.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (lstObj.get(i).getVatTd() != null && lstObj.get(i).getVatTd() != 0l) {
					cell25.setCellValue(lstObj.get(i).getVatTd());
				} else {
					cell25.setCellValue("0");
				}

				XSSFCell cell26 = row.createCell(26);
				cell26.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (lstObj.get(i).getTongTd() != null && lstObj.get(i).getTongTd() != 0l) {
					cell26.setCellValue(lstObj.get(i).getTongTd());
				} else {
					cell26.setCellValue("0");
				}

				XSSFCell cell27 = row.createCell(27);
				cell27.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (lstObj.get(i).getTtct1() != 0l) {
					cell27.setCellValue(lstObj.get(i).getTtct1());
				} else {
					cell27.setCellValue("0");
				}

				XSSFCell cell28 = row.createCell(28);
				cell28.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (lstObj.get(i).getTtct2() != 0l) {
					cell28.setCellValue(lstObj.get(i).getTtct2());
				} else {
					cell28.setCellValue("0");
				}

				XSSFCell cell29 = row.createCell(29);
				cell29.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (lstObj.get(i).getTtct3() != 0l) {
					cell29.setCellValue(lstObj.get(i).getTtct3());
				} else {
					cell29.setCellValue("0");
				}

				XSSFCell cell30 = row.createCell(30);
				cell30.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (lstObj.get(i).getTtct4() != 0l) {
					cell30.setCellValue(lstObj.get(i).getTtct4());
				} else {
					cell30.setCellValue("0");
				}

				XSSFCell cell31 = row.createCell(31);
				cell31.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (lstObj.get(i).getTtct5() != 0l) {
					cell31.setCellValue(lstObj.get(i).getTtct5());
				} else {
					cell31.setCellValue("0");
				}

				XSSFCell cell32 = row.createCell(32);
				cell32.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (lstObj.get(i).getTtct6() != 0l) {
					cell32.setCellValue(lstObj.get(i).getTtct6());
				} else {
					cell32.setCellValue("0");
				}

				XSSFCell cell33 = row.createCell(33);
				cell33.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (lstObj.get(i).getTtct7() != 0l) {
					cell33.setCellValue(lstObj.get(i).getTtct7());
				} else {
					cell33.setCellValue("0");
				}

				XSSFCell cell34 = row.createCell(34);
				cell34.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (lstObj.get(i).getHsQua5N() != 0l) {
					cell34.setCellValue(lstObj.get(i).getHsQua5N());
				} else {
					cell34.setCellValue("0");
				}

				XSSFCell cell35 = row.createCell(35);
				cell35.setCellValue("");

				cell0.setCellStyle(style);
				cell1.setCellStyle(style);
				cell2.setCellStyle(styleNumber);
				cell3.setCellStyle(styleNumber);
				cell4.setCellStyle(style);
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
				cell21.setCellStyle(styleNumber);
				cell22.setCellStyle(styleNumber);
				cell23.setCellStyle(styleNumber);
				cell24.setCellStyle(styleNumber);
				cell25.setCellStyle(styleNumber);
				cell26.setCellStyle(styleNumber);
				cell27.setCellStyle(styleNumber);
				cell28.setCellStyle(styleNumber);
				cell29.setCellStyle(styleNumber);
				cell30.setCellStyle(styleNumber);
				cell31.setCellStyle(styleNumber);
				cell32.setCellStyle(styleNumber);
				cell33.setCellStyle(styleNumber);
				cell34.setCellStyle(styleNumber);
				cell35.setCellStyle(style);

			}
			

			//xuất số hợp đồng đã  đề nghị chưa thẩm định của các tỉnh
				List<KqHdTkDTO> lstShdTon=new ArrayList<>();
					List<KqHdTkDTO> lstTinhDis=kqHdTkDAO.getAllMaTinh();
					for (int i = 0; i < lstTinh.size(); i++) {
						KqHdTkDTO objs=new KqHdTkDTO();
						List<KqHdTkDTO> lstTonHd=kqHdTkDAO.doSearch(lstTinh.get(i));
						String xx=lstTinh.get(i).getTinh();
						List<KqHdTkDTO> sohdTon=lstTinhDis.stream().filter(x->x.getTinh().equals(xx)&&x.getNgayGuiPtkHshc() == null).collect(Collectors.toList());
						objs.setSoHdChuaTd(Long.valueOf(sohdTon.size()));
						objs.setTinh(lstTinh.get(i).getTinh());
						lstShdTon.add(objs);
				}
				
			
			for (int j = 0; j < lstShdTon.size(); j++) {
				XSSFRow row1 = sheet1.createRow(rownum1);
				XSSFCell cell0s1 = row1.createCell(0);
				cell0s1.setCellValue(j + 1);
				cell0s1.setCellStyle(style);
				rownum1++;

				XSSFCell cell1s1 = row1.createCell(1);
				cell1s1.setCellValue(lstShdTon.get(j).getTinh());

				XSSFCell cell1s2 = row1.createCell(2);
				cell1s2.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (lstShdTon.get(j).getSoHdChuaTd() != (float)0) {
					cell1s2.setCellValue(lstShdTon.get(j).getSoHdChuaTd());
				} else {
					cell1s2.setCellValue("0");
				}
				cell0s1.setCellStyle(style);
				cell1s2.setCellStyle(styleNumber);
				cell1s1.setCellStyle(style);

			}
			
			
			row = sheet.createRow(4);
			XSSFCell cell00 = row.createCell(0);
			cell00.setCellValue("II");

			XSSFCell cell01 = row.createCell(1);
			cell01.setCellValue("");

			XSSFCell cell02 = row.createCell(2);
			cell02.setCellFormula(strFormulaC);

			XSSFCell cell03 = row.createCell(3);
			cell03.setCellFormula(strFormulaD);

			XSSFCell cell04 = row.createCell(4);
			cell04.setCellValue("");

			XSSFCell cell05 = row.createCell(5);
			cell05.setCellFormula(strFormulaF);

			XSSFCell cell06 = row.createCell(6);
			cell06.setCellFormula(strFormulaG);

			XSSFCell cell07 = row.createCell(7);
			cell07.setCellFormula(strFormulaH);

			XSSFCell cell08 = row.createCell(8);
			cell08.setCellFormula(strFormulaI);

			XSSFCell cell09 = row.createCell(9);
			cell09.setCellFormula(strFormulaJ);

			XSSFCell cell010 = row.createCell(10);
			cell010.setCellFormula(strFormulaK);

			XSSFCell cell011 = row.createCell(11);
			cell011.setCellFormula(strFormulaL);

			XSSFCell cell012 = row.createCell(12);
			cell012.setCellFormula(strFormulaM);

			XSSFCell cell013 = row.createCell(13);
			cell013.setCellFormula(strFormulaN);

			XSSFCell cell014 = row.createCell(14);
			cell014.setCellFormula(strFormulaO);

			XSSFCell cell015 = row.createCell(15);
			cell015.setCellFormula(strFormulaP);

			XSSFCell cell016 = row.createCell(16);
			cell016.setCellFormula(strFormulaQ);

			XSSFCell cell017 = row.createCell(17);
			cell017.setCellFormula(strFormulaR);

			XSSFCell cell018 = row.createCell(18);
			cell018.setCellFormula(strFormulaS);

			XSSFCell cell019 = row.createCell(19);
			cell019.setCellFormula(strFormulaT);

			XSSFCell cell020 = row.createCell(20);
			cell020.setCellFormula(strFormulaU);

			XSSFCell cell021 = row.createCell(21);
			cell021.setCellFormula(strFormulaV);

			XSSFCell cell022 = row.createCell(22);
			cell022.setCellFormula(strFormulaW);

			XSSFCell cell023 = row.createCell(23);
			cell023.setCellFormula(strFormulaX);

			XSSFCell cell024 = row.createCell(24);
			cell024.setCellFormula(strFormulaY);

			XSSFCell cell025 = row.createCell(25);
			cell025.setCellFormula(strFormulaZ);

			XSSFCell cell026 = row.createCell(26);
			cell026.setCellFormula(strFormulaAA);

			XSSFCell cell027 = row.createCell(27);
			cell027.setCellFormula(strFormulaAB);

			XSSFCell cell028 = row.createCell(28);
			cell028.setCellFormula(strFormulaAC);

			XSSFCell cell029 = row.createCell(29);
			cell029.setCellFormula(strFormulaAD);

			XSSFCell cell030 = row.createCell(30);
			cell030.setCellFormula(strFormulaAE);

			XSSFCell cell031 = row.createCell(31);
			cell031.setCellFormula(strFormulaAF);

			XSSFCell cell032 = row.createCell(32);
			cell032.setCellFormula(strFormulaAG);

			XSSFCell cell033 = row.createCell(33);
			cell033.setCellFormula(strFormulaAH);

			XSSFCell cell034 = row.createCell(34);
			cell034.setCellFormula(strFormulaAI);
			
			XSSFCell cell035= row.createCell(35);
			cell035.setCellValue("");

			cell00.setCellStyle(style);
			cell01.setCellStyle(style);
			cell02.setCellStyle(styleNumber);
			cell03.setCellStyle(styleNumber);
			cell04.setCellStyle(style);
			cell05.setCellStyle(styleNumber);
			cell06.setCellStyle(styleNumber);
			cell07.setCellStyle(styleNumber);
			cell08.setCellStyle(styleNumber);
			cell09.setCellStyle(styleNumber);
			cell010.setCellStyle(styleNumber);
			cell011.setCellStyle(styleNumber);
			cell012.setCellStyle(styleNumber);
			cell013.setCellStyle(styleNumber);
			cell014.setCellStyle(styleNumber);
			cell015.setCellStyle(styleNumber);
			cell016.setCellStyle(styleNumber);
			cell017.setCellStyle(styleNumber);
			cell018.setCellStyle(styleNumber);
			cell019.setCellStyle(styleNumber);
			cell020.setCellStyle(styleNumber);
			cell021.setCellStyle(styleNumber);
			cell022.setCellStyle(styleNumber);
			cell023.setCellStyle(styleNumber);
			cell024.setCellStyle(styleNumber);
			cell025.setCellStyle(styleNumber);
			cell026.setCellStyle(styleNumber);
			cell027.setCellStyle(styleNumber);
			cell028.setCellStyle(styleNumber);
			cell029.setCellStyle(styleNumber);
			cell030.setCellStyle(styleNumber);
			cell031.setCellStyle(styleNumber);
			cell032.setCellStyle(styleNumber);
			cell033.setCellStyle(styleNumber);
			cell034.setCellStyle(styleNumber);
			cell035.setCellStyle(style);

			
			
			
			file.close();

			File out = new File(folder2Upload + File.separatorChar + "THBCTheoNgay" + startExportTime + ".xlsx");

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
		return UEncrypt.encryptFileUploadPath("THBCTheoNgay" + startExportTime + ".xlsx");
	}

	@Override
	public List<KqHdTkDTO> getForAutoCompleteHD(KqHdTkDTO obj) {
		// TODO Auto-generated method stub
		return kqHdTkDAO.getForAutoCompleteHD(obj);
	}

	@Override
	public List<KqHdTkDTO> getForAutoCompleteKHTC(KqHdTkDTO obj) {
		// TODO Auto-generated method stub
		return kqHdTkDAO.getForAutoCompleteKHTC(obj);
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
	
	public String importFileTD (String fileInputTD , HttpServletRequest request) throws Exception{

		
		List<ImportErrDTO> workFault = Lists.newArrayList();
		VpsUserToken token = (VpsUserToken) request.getSession().getAttribute("vpsUserToken");
		try{
			
			String Ten = "";
			File f = new File(fileInputTD);
			
			
			XSSFWorkbook workbook = new XSSFWorkbook(f);
			XSSFSheet sheet = workbook.getSheetAt(0);
			
			
			DataFormatter dataFormatter = new DataFormatter();
			int count = 0;
			for  (Row row : sheet){
				count ++;
				ErrExcelDTO orderErrorFormat = new ErrExcelDTO();
				if (count >=3 && !isRowEmpty(row)){
					
					lstErrExcelDto = new ArrayList<>();
					
					
					
					
					boolean checkTinh = true;
					boolean checkMaTramTuyen = true;
					boolean checkNguonCapUng = true;
					boolean checkSoHd = true;
					boolean checkSoKhTc = true;
					boolean checkNgayKyKh = true;
					boolean checkLoaiCt = true;
					boolean checkNgayGuiHSHC = true;
					boolean checkNgayThiCong = true;
					boolean checkGtQtCdtKoVat = true;
					boolean checkGtQtCdtCoVat = true;
					boolean checkCpNhanCong = true;
					boolean checkCpVatLieu = true;
					boolean checkCpHSHC = true;
					boolean checkCpVanChuyen = true;
					boolean checkCpKhac = true;
					boolean checkCpLuong = true;
					boolean checkVat = true;
					boolean checkTong = true;
				
					// khởi tạo các biến kiểm tra giá trị td
					boolean checkNgayGuiPtkHshc = true;
					
					boolean checkNgayTutttt = true ;
					boolean checkNgayPtkTdXong = true;
					boolean checkCpNhanCongTd = true;
					boolean checkCpVatLieuTd = true;
					boolean checkCpHshcTd = true;
					boolean checkCpVanChuyenTd = true ;
					boolean checkCpKhacTd = true ;
					boolean checkCpLuongTd = true;
					boolean checkVatTd = true ;
					boolean checkTongTd = true;
					boolean checkGtTdPtkChuaVat = true;
					boolean checkGtTdPtkCoVat = true ;
					boolean checkTamUngLuong = true ;
					boolean checkLuongThucNhan = true;
					boolean checkTyLe = true ;
					
					
					
					
					
					String tinh = "";
					String maTramTuyen = "";
					String nguonCapUng = "";
					String soHd = "";
					String soKhTc = "";
					String ngayKyKh = "";
					String thuocDmTt = "";
					String loaiCt = "";
					String noiDung = "";
					String ngayGuiHshc = "";
					String soBill = "";
					String ghiChu = "";
					String ngayThiCong = "";
					String gtQtCdtKoVat = "";
					String gtQtCdtCoVat = "";
					String cpNhanCong = "";
					String cpVatLieu = "";
					String cpHshc = "";
					String cpVanChuyen = "";
					String cpKhac = "";
					String cpLuong = "";
					String vat = "";
					String tong = "";
					String nguoiCapNhat = "";
					// Khởi tạo các biến  cần gán  td
					
					String ngayGuiPtkHshc = "";
					String tinhTrangChungTu ="";
					String ngayTutttt = "";
					String ghiChuHsLoi ="";
					String ngayPtkTdXong = "";
					String cpNhanCongTd = "";
					String cpVatLieuTd = "";
					String cpHshcTd = "";
					String cpVanChuyenTd = "";
					String cpKhacTd = "";
					String cpLuongTd = "";
					String vatTd = "";
					String tongTd = "";
					String gtTdPtkChuaVat = "";
					String gtTdPtkCoVat = "";
					String thangHshcQuyLuong = "";
					String thangGhiNhanQuyLuongTqt ="";
					String tamUngLuong = "";
					String luongThucNhan = "";
					String tyLe = "";
					String hsTonQua5n ="";
					String gtQhXlCt = "";
					String nguoiPheDuyetPtk ="";
					String lyDoTc = "";
					String kqHdTkId = "";
					
					for(Cell cell : row){
						// check format file excel
						if (cell.getColumnIndex() == 1) {
							tinh = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 2) {
							maTramTuyen = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 3) {
							nguonCapUng = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 4) {
							soHd = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 5) {
							soKhTc = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 6) {
							ngayKyKh = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 7) {
							thuocDmTt = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 8) {
							loaiCt = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 9) {
							noiDung = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 10) {
							ngayGuiHshc = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 11) {
							soBill = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 12) {
							ghiChu = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 13) {
							ngayThiCong = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 14) {
							gtQtCdtKoVat = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 15) {
							gtQtCdtCoVat = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 16) {
							cpNhanCong = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 17) {
							cpVatLieu = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 18) {
							cpHshc = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 19) {
							cpVanChuyen = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 20) {
							cpKhac = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 21) {
							cpLuong = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 22) {
							vat = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 23) {
							tong = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 24) {
							nguoiCapNhat = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 25) {
							ngayGuiPtkHshc = dataFormatter.formatCellValue(cell);
						}else if(cell.getColumnIndex() == 26){
							tinhTrangChungTu = dataFormatter.formatCellValue(cell);
						}else if(cell.getColumnIndex() == 27){
							ngayTutttt = dataFormatter.formatCellValue(cell);
						}else if(cell.getColumnIndex() == 28){
							ghiChuHsLoi = dataFormatter.formatCellValue(cell);
						}else if(cell.getColumnIndex() == 29){
							ngayPtkTdXong = dataFormatter.formatCellValue(cell);
						}else if(cell.getColumnIndex() == 30){
							cpNhanCongTd = dataFormatter.formatCellValue(cell);
						}else if(cell.getColumnIndex() == 31){
							cpVatLieuTd = dataFormatter.formatCellValue(cell);
						}else if(cell.getColumnIndex() == 32){
							cpHshcTd = dataFormatter.formatCellValue(cell);
						}else if(cell.getColumnIndex() == 33){
							cpVanChuyenTd = dataFormatter.formatCellValue(cell);
						}else if(cell.getColumnIndex() == 34){
							cpKhacTd = dataFormatter.formatCellValue(cell);
						}else if(cell.getColumnIndex() == 35){
							cpLuongTd = dataFormatter.formatCellValue(cell);
						}else if(cell.getColumnIndex() == 36){
							vatTd = dataFormatter.formatCellValue(cell);
						}else if(cell.getColumnIndex() == 37){
							tongTd = dataFormatter.formatCellValue(cell);
						}else if(cell.getColumnIndex() == 38){
							gtTdPtkChuaVat = dataFormatter.formatCellValue(cell);
						}else if(cell.getColumnIndex() == 39){
							gtTdPtkCoVat = dataFormatter.formatCellValue(cell);
						}else if(cell.getColumnIndex() == 40){
							thangHshcQuyLuong = dataFormatter.formatCellValue(cell);
						}else if(cell.getColumnIndex() == 41){
							thangGhiNhanQuyLuongTqt = dataFormatter.formatCellValue(cell);
						}else if(cell.getColumnIndex() == 42){
							tamUngLuong = dataFormatter.formatCellValue(cell);
						}else if(cell.getColumnIndex() == 43){
							luongThucNhan = dataFormatter.formatCellValue(cell);
						}else if(cell.getColumnIndex() == 44){
							tyLe = dataFormatter.formatCellValue(cell);
						}else if(cell.getColumnIndex() == 45){
							hsTonQua5n = dataFormatter.formatCellValue(cell);
						}else if(cell.getColumnIndex() == 46){
							gtQhXlCt = dataFormatter.formatCellValue(cell);
						}else if(cell.getColumnIndex() == 47){
							nguoiPheDuyetPtk =  dataFormatter.formatCellValue(cell);
						}else if(cell.getColumnIndex() == 48){
							lyDoTc = dataFormatter.formatCellValue(cell);
						}else if(cell.getColumnIndex() == 49){
							kqHdTkId = dataFormatter.formatCellValue(cell);
						}
					}
					checkTinh = checkDataFromFileExel(tinh.trim(), 1, orderErrorFormat);
					checkMaTramTuyen = checkDataFromFileExel(maTramTuyen.trim(), 2, orderErrorFormat);
					checkNguonCapUng = checkDataFromFileExel(nguonCapUng.trim(), 3, orderErrorFormat);
					checkSoHd = checkDataFromFileExel(soHd.trim(), 4, orderErrorFormat);
					checkSoKhTc = checkDataFromFileExel(soKhTc.trim(), 5, orderErrorFormat);
					checkNgayKyKh = checkDataFromFileExel(ngayKyKh.trim(), 6, orderErrorFormat);
					checkLoaiCt = checkDataFromFileExel(loaiCt.trim(), 8, orderErrorFormat);
					checkNgayGuiHSHC = checkDataFromFileExel(ngayGuiHshc.trim(), 10, orderErrorFormat);
					checkNgayThiCong = checkDataFromFileExel(ngayThiCong.trim(),13, orderErrorFormat);
					checkGtQtCdtKoVat = checkDataFromFileExel(gtQtCdtKoVat.trim(), 14, orderErrorFormat);
					checkGtQtCdtCoVat = checkDataFromFileExel(gtQtCdtCoVat.trim(), 15, orderErrorFormat);
					checkCpNhanCong = checkDataFromFileExel(cpNhanCong.trim(), 16, orderErrorFormat);
					checkCpVatLieu = checkDataFromFileExel(cpVatLieu.trim(), 17, orderErrorFormat);
					checkCpHSHC = checkDataFromFileExel(cpHshc.trim(), 18, orderErrorFormat);
					checkCpVanChuyen = checkDataFromFileExel(cpVanChuyen.trim(), 19, orderErrorFormat);
					checkCpKhac = checkDataFromFileExel(cpKhac.trim(), 20, orderErrorFormat);
					checkCpLuong = checkDataFromFileExel(cpLuong.trim(), 21, orderErrorFormat);
					checkVat = checkDataFromFileExel(vat.trim(), 22, orderErrorFormat);
				
					checkTong = checkDataFromFileExel(tong.trim(), 23, orderErrorFormat);

					//td
					checkNgayGuiPtkHshc = checkDataFromFileExel(ngayGuiPtkHshc.trim(), 25, orderErrorFormat);
					checkNgayTutttt = checkDataFromFileExel(ngayTutttt.trim(), 27, orderErrorFormat);
					checkNgayPtkTdXong = checkDataFromFileExel(ngayPtkTdXong.trim(), 29, orderErrorFormat); 
					checkCpNhanCongTd = checkDataFromFileExel(cpNhanCongTd.trim(), 30, orderErrorFormat);
					checkCpVatLieuTd = checkDataFromFileExel(cpVatLieuTd.trim(), 31, orderErrorFormat);
					checkCpHshcTd = checkDataFromFileExel(cpHshcTd.trim(), 32, orderErrorFormat);
					checkCpVanChuyenTd = checkDataFromFileExel(cpVanChuyenTd.trim(), 33, orderErrorFormat);
					checkCpKhacTd = checkDataFromFileExel(cpKhacTd.trim(), 34, orderErrorFormat);
					checkCpLuongTd = checkDataFromFileExel(cpLuongTd.trim(), 35, orderErrorFormat);
					checkVatTd =  checkDataFromFileExel(vatTd.trim(), 36, orderErrorFormat);
					checkTongTd = checkDataFromFileExel(tongTd.trim(), 37, orderErrorFormat);
					checkGtTdPtkChuaVat = checkDataFromFileExel(gtTdPtkChuaVat.trim(), 38, orderErrorFormat);
					checkGtTdPtkCoVat = checkDataFromFileExel(gtTdPtkCoVat.trim(), 39, orderErrorFormat);
					checkTamUngLuong = checkDataFromFileExel(tamUngLuong.trim(), 42, orderErrorFormat);
					checkLuongThucNhan = checkDataFromFileExel(luongThucNhan.trim(), 43, orderErrorFormat);
					checkTyLe = checkDataFromFileExel(tyLe.trim(), 44, orderErrorFormat);
					
					
					if (checkTinh && checkMaTramTuyen && checkNguonCapUng && checkSoHd && checkSoKhTc && checkNgayKyKh
						&& checkLoaiCt && checkNgayGuiHSHC && checkNgayThiCong && checkGtQtCdtKoVat && checkGtQtCdtCoVat
						&& checkCpNhanCong && checkCpVatLieu && checkCpHSHC && checkCpVanChuyen && checkCpKhac
						&& checkCpLuong && checkVat && checkTong&&checkNgayGuiPtkHshc
						&&checkNgayTutttt&&checkNgayPtkTdXong&&checkCpNhanCongTd
						&&checkCpVatLieuTd&&checkCpHshcTd&&checkCpVanChuyenTd&&checkCpKhacTd&&checkCpLuongTd
						&&checkVatTd&&checkTongTd&&checkGtTdPtkChuaVat&&checkGtTdPtkCoVat&&checkTamUngLuong
						&&checkLuongThucNhan&&checkTyLe){
						
						KqHdTkDTO oldObj = new KqHdTkDTO();
						
						oldObj.setKqHdTkId(Long.parseLong(kqHdTkId));
						
						oldObj.setTinh(tinh);
						oldObj.setMaTramTuyen(maTramTuyen);
						oldObj.setNguonCapUng(nguonCapUng);
						oldObj.setSoHd(soHd);
						oldObj.setSoKhTc(soKhTc);
						if (StringUtils.isNotEmpty(ngayKyKh)) {
							oldObj.setNgayKyKh(dt.parse(ngayKyKh));
						}
						oldObj.setThuocDmToTrinh(thuocDmTt);
						oldObj.setLoaiCt(loaiCt);
						oldObj.setNoiDung(noiDung);
						if (StringUtils.isNotEmpty(ngayGuiHshc)) {
							oldObj.setNgayGuiHshc(dt.parse(ngayGuiHshc));
						}
						oldObj.setSoBill(soBill);
						oldObj.setGhiChu(ghiChu);
						if (StringUtils.isNotEmpty(ngayThiCong)) {
							oldObj.setNgayThiCong(dt.parse(ngayThiCong));
						}
						if (!StringUtils.isEmpty(gtQtCdtKoVat)) {
							gtQtCdtKoVat = convertValue(gtQtCdtKoVat);
							oldObj.setGtQtCdtChuaVat(Float.parseFloat(gtQtCdtKoVat));
						} else {
							oldObj.setGtQtCdtChuaVat((float) 0);
						}

						if (!StringUtils.isEmpty(gtQtCdtCoVat)) {
							gtQtCdtCoVat = convertValue(gtQtCdtCoVat);
							oldObj.setGtQtCdtCoVat(Float.parseFloat(gtQtCdtCoVat));
						} else {
							oldObj.setGtQtCdtCoVat((float) 0);
						}

						if (!StringUtils.isEmpty(cpNhanCong)) {
							cpNhanCong = convertValue(cpNhanCong);
							oldObj.setCpNhanCongDn(Float.parseFloat(cpNhanCong));
						} else {
							oldObj.setCpNhanCongDn((float) 0);
						}

						if (!StringUtils.isEmpty(cpVatLieu)) {
							cpVatLieu = convertValue(cpVatLieu);
							oldObj.setCpVatLieuDn(Float.parseFloat(cpVatLieu));
						} else {
							oldObj.setCpVatLieuDn((float) 0);
						}

						if (!StringUtils.isEmpty(cpHshc)) {
							cpHshc = convertValue(cpHshc);
							oldObj.setCpHshcDn(Float.parseFloat(cpHshc));
						} else {
							oldObj.setCpHshcDn((float) 0);
						}

						if (!StringUtils.isEmpty(cpVanChuyen)) {
							cpVanChuyen = convertValue(cpVanChuyen);
							oldObj.setCpVanChuyenDn(Float.parseFloat(cpVanChuyen));
						} else {
							oldObj.setCpVanChuyenDn((float) 0);
						}

						if (!StringUtils.isEmpty(cpKhac)) {
							cpKhac = convertValue(cpKhac);
							oldObj.setChiPhiKhacDn(Float.parseFloat(cpKhac));
						} else {
							oldObj.setChiPhiKhacDn((float) 0);
						}
						if (!StringUtils.isEmpty(cpLuong)) {
							cpLuong = convertValue(cpLuong);
							oldObj.setChiPhiLuongDn(Float.parseFloat(cpLuong));
						} else {
							oldObj.setChiPhiLuongDn((float) 0);
						}

						if (!StringUtils.isEmpty(vat)) {
							vat = convertValue(vat);
							oldObj.setVatDn(Float.parseFloat(vat));
						} else {
							oldObj.setVatDn((float) 0);
						}
						
						if (!StringUtils.isEmpty(tong)) {
							tong = convertValue(tong);
							oldObj.setTongDn(Float.parseFloat(tong));
						} else {
							oldObj.setTongDn((float) 0);
						}
						oldObj.setNguoiTao(nguoiCapNhat);
						
						
						//td
						if (StringUtils.isNotEmpty(ngayGuiPtkHshc)) {
							oldObj.setNgayGuiPtkHshc(dt.parse(ngayGuiPtkHshc));
						}
						
						oldObj.setTinhTrangChungTu(tinhTrangChungTu);
						
						if(StringUtils.isNotEmpty(ngayTutttt)){
							oldObj.setNgayTutttt(dt.parse(ngayTutttt));
						}
						
						oldObj.setGhiChuHsLoi(ghiChuHsLoi);
						
						if(StringUtils.isNotEmpty(ngayPtkTdXong)){
							oldObj.setNgayPtkTdXong(dt.parse(ngayPtkTdXong));
						}else{
							Date date = new Date();
							oldObj.setNgayPtkTdXong(date);
						}
						if (!StringUtils.isEmpty(cpNhanCongTd)) {
							cpNhanCongTd = convertValue(cpNhanCongTd);
							oldObj.setCpNhanCongTd(Float.parseFloat(cpNhanCongTd));
						} else {
							oldObj.setCpNhanCongTd((float) 0);
						}
						if (!StringUtils.isEmpty(cpVatLieuTd)) {
							cpVatLieuTd = convertValue(cpVatLieuTd);
							oldObj.setCpVatLieuTd(Float.parseFloat(cpVatLieuTd));
						} else {
							oldObj.setCpVatLieuTd((float) 0);
						}
						if (!StringUtils.isEmpty(cpHshcTd)) {
							cpHshcTd = convertValue(cpHshcTd);
							oldObj.setCpHshcTd(Float.parseFloat(cpHshcTd));
						} else {
							oldObj.setCpHshcTd((float) 0);
						}
						if (!StringUtils.isEmpty(cpVanChuyenTd)) {
							cpVanChuyenTd = convertValue(cpVanChuyenTd);
							oldObj.setCpVanChuyenTd(Float.parseFloat(cpVanChuyenTd));
						} else {
							oldObj.setCpVanChuyenTd((float) 0);
						}
						if (!StringUtils.isEmpty(cpKhacTd)) {
							cpKhacTd = convertValue(cpKhacTd);
							oldObj.setCpKhacTd(Float.parseFloat(cpKhacTd));
						} else {
							oldObj.setCpKhacTd((float) 0);
						}
						if (!StringUtils.isEmpty(cpLuongTd)) {
							cpLuongTd = convertValue(cpLuongTd);
							oldObj.setCpLuongTd(Float.parseFloat(cpLuongTd));
						} else {
							oldObj.setCpLuongTd((float) 0);
						}
						if (!StringUtils.isEmpty(vatTd)) {
							vatTd = convertValue(vatTd);
							oldObj.setVatTd(Float.parseFloat(vatTd));
						} else {
							oldObj.setVatTd((float) 0);
						}
						if ( "0".equals(tongTd)) {
							float cpNhanCongTd1 = Float.parseFloat(convertValue(cpNhanCongTd));
							float cpVatLieuTd1 = Float.parseFloat(convertValue(cpVatLieuTd));
							float cpHshcTd1 = Float.parseFloat(convertValue(cpHshcTd));
							float cpVanChuyenTd1 = Float.parseFloat(convertValue(cpVanChuyenTd));
							float cpKhacTd1 = Float.parseFloat(convertValue(cpKhacTd));
							float cpLuongTd1 = Float.parseFloat(convertValue(cpLuongTd));
							float vatTd1 = Float.parseFloat(convertValue(vatTd));
							float sum = cpNhanCongTd1 + cpVatLieuTd1 + cpHshcTd1 +cpVanChuyenTd1+cpKhacTd1+cpLuongTd1+vatTd1;
							oldObj.setTongTd(sum);
							
						} else {
							tongTd = convertValue(tongTd);
							oldObj.setTongTd(Float.parseFloat(tongTd));
						}
						if ( "0".equals(gtTdPtkChuaVat)) {
							float cpNhanCongTd1 = Float.parseFloat(convertValue(cpNhanCongTd));
							float cpVatLieuTd1 = Float.parseFloat(convertValue(cpVatLieuTd));
							float cpHshcTd1 = Float.parseFloat(convertValue(cpHshcTd));
							float cpVanChuyenTd1 = Float.parseFloat(convertValue(cpVanChuyenTd));
							float cpKhacTd1 = Float.parseFloat(convertValue(cpKhacTd));
							float cpLuongTd1 = Float.parseFloat(convertValue(cpLuongTd));
							
							float sum = cpNhanCongTd1 + cpVatLieuTd1 + cpHshcTd1 +cpVanChuyenTd1+cpKhacTd1+cpLuongTd1;
							oldObj.setGtTdPtkChuaVat(sum);
							
						} else {
							gtTdPtkChuaVat = convertValue(gtTdPtkChuaVat);
							oldObj.setGtTdPtkChuaVat(Float.parseFloat(gtTdPtkChuaVat));
						}
						if ( "0".equals(gtTdPtkCoVat)) {
							float cpNhanCongTd1 = Float.parseFloat(convertValue(cpNhanCongTd));
							float cpVatLieuTd1 = Float.parseFloat(convertValue(cpVatLieuTd));
							float cpHshcTd1 = Float.parseFloat(convertValue(cpHshcTd));
							float cpVanChuyenTd1 = Float.parseFloat(convertValue(cpVanChuyenTd));
							float cpKhacTd1 = Float.parseFloat(convertValue(cpKhacTd));
							float cpLuongTd1 = Float.parseFloat(convertValue(cpLuongTd));
							float vatTd1 = Float.parseFloat(convertValue(vatTd));
							float sum = cpNhanCongTd1 + cpVatLieuTd1 + cpHshcTd1 +cpVanChuyenTd1+cpKhacTd1+cpLuongTd1+vatTd1;
							oldObj.setGtTdPtkCoVat(sum);
							
						} else {
							gtTdPtkCoVat = convertValue(gtTdPtkCoVat);
							oldObj.setGtTdPtkCoVat(Float.parseFloat(gtTdPtkCoVat));
						}
						
						oldObj.setThangHshcQuyLuong(thangHshcQuyLuong);
						oldObj.setThangGhiNhanQuyLuongTqt(thangGhiNhanQuyLuongTqt);
						
						if (!StringUtils.isEmpty(tamUngLuong)) {
							tamUngLuong = convertValue(tamUngLuong);
							oldObj.setTamUngLuong(Float.parseFloat(tamUngLuong));
						} else {
							oldObj.setTamUngLuong((float) 0);
						}
						if ( "0".equals(luongThucNhan)) {
							float cpLuongTd1 = Float.parseFloat(convertValue(cpLuongTd));
							float tamUngLuong1 = Float.parseFloat(convertValue(tamUngLuong));
							float hieu = cpLuongTd1 - tamUngLuong1;
							oldObj.setLuongThucNhan(hieu);
							
						} else {
							luongThucNhan = convertValue(luongThucNhan);
							oldObj.setLuongThucNhan(Float.parseFloat(luongThucNhan));
						}
						if ( "0".equals(tyLe)) {
							float cpNhanCongTd1 = Float.parseFloat(convertValue(cpNhanCongTd));
							float cpVatLieuTd1 = Float.parseFloat(convertValue(cpVatLieuTd));
							float cpHshcTd1 = Float.parseFloat(convertValue(cpHshcTd));
							float cpVanChuyenTd1 = Float.parseFloat(convertValue(cpVanChuyenTd));
							float cpKhacTd1 = Float.parseFloat(convertValue(cpKhacTd));
							float cpLuongTd1 = Float.parseFloat(convertValue(cpLuongTd));
							float gtQtCdtKoVat1 = Float.parseFloat(convertValue(gtQtCdtKoVat));
							float sum = cpNhanCongTd1 + cpVatLieuTd1 + cpHshcTd1 +cpVanChuyenTd1+cpKhacTd1+cpLuongTd1;
							float tle =Math.round((gtQtCdtKoVat1/sum)*10000);
							float xxx = tle/100;
							oldObj.setTyLe(xxx);
							
						} else {
							tyLe = convertValue(tyLe);
							oldObj.setTyLe(Float.parseFloat(tyLe));
						}
						if(StringUtils.isNotEmpty(hsTonQua5n)){
							oldObj.setHsTonQua5n(hsTonQua5n);
						}else{
							Date date1 = dt.parse(ngayGuiPtkHshc);
							if(StringUtils.isNotEmpty(ngayPtkTdXong)){
								Date date2 = dt.parse(ngayPtkTdXong);
								long x = (date2.getTime()-date1.getTime())/(5*24*60*60*1000);
								if(x <1 ){
									oldObj.setHsTonQua5n("Chưa quá hạn");
								}else{
									oldObj.setHsTonQua5n("Quá hạn");
								}
							}else{
								Date date2 = new Date();
								long x = (date2.getTime()-date1.getTime())/(5*24*60*60*1000);
								if(x <1 ){
									oldObj.setHsTonQua5n("Chưa quá hạn");
								}else{
									oldObj.setHsTonQua5n("Quá hạn");
								}
							}
						}
						
						oldObj.setGtQhXlCt(gtQhXlCt);
						oldObj.setNguoiPheDuyetPtk(token.getFullName());
						oldObj.setLyDoTc(lyDoTc);
						
						oldObj.setTrangThai("Đã thẩm định");
						
						kqHdTkDAO.update(oldObj.toModel());
						
					}else {
						ImportErrDTO errObj = new ImportErrDTO();
						errObj.setColumn1(tinh);
						errObj.setColumn2(maTramTuyen);
						errObj.setColumn3(nguonCapUng);
						errObj.setColumn4(soHd);
						errObj.setColumn5(soKhTc);
						errObj.setColumn6(ngayKyKh);
						errObj.setColumn7(thuocDmTt);
						errObj.setColumn8(loaiCt);
						errObj.setColumn9(noiDung);
						errObj.setColumn10(ngayGuiHshc);
						errObj.setColumn11(soBill);
						errObj.setColumn12(ghiChu);
						errObj.setColumn13(ngayThiCong);
						errObj.setColumn14(gtQtCdtKoVat);
						errObj.setColumn15(gtQtCdtCoVat);
						errObj.setColumn16(cpNhanCong);
						errObj.setColumn17(cpVatLieu);
						errObj.setColumn18(cpHshc);
						errObj.setColumn19(cpVanChuyen);
						errObj.setColumn20(cpKhac);
						errObj.setColumn21(cpLuong);
						errObj.setColumn22(vat);
						errObj.setColumn23(tong);
						errObj.setColumn24(nguoiCapNhat);
						errObj.setColumn25(ngayGuiPtkHshc);
						errObj.setColumn26(tinhTrangChungTu);
						errObj.setColumn27(ngayTutttt);
						errObj.setColumn28(ghiChuHsLoi);
						errObj.setColumn29(ngayPtkTdXong);
						errObj.setColumn30(cpNhanCongTd);
						errObj.setColumn31(cpVatLieuTd);
						errObj.setColumn32(cpHshcTd);
						errObj.setColumn33(cpVanChuyenTd);
						errObj.setColumn34(cpKhacTd);
						errObj.setColumn35(cpLuongTd);
						errObj.setColumn36(vatTd);
						errObj.setColumn37(tongTd);
						errObj.setColumn38(gtTdPtkChuaVat);
						errObj.setColumn39(gtTdPtkCoVat);
						errObj.setColumn40(thangHshcQuyLuong);
						errObj.setColumn41(thangGhiNhanQuyLuongTqt);
						errObj.setColumn42(tamUngLuong);
						errObj.setColumn43(luongThucNhan);
						errObj.setColumn44(tyLe);
						errObj.setColumn45(hsTonQua5n);
						errObj.setColumn46(gtQhXlCt);
						errObj.setColumn48(lyDoTc);
						errObj.setLstErrorOrder(lstErrExcelDto);
						workFault.add(errObj);
					}
					
				}
			}
			workbook.close();
			
			if ( workFault.size() < 0) {
				throw new IllegalArgumentException("File import không có dữ liệu");
			} else {
				if (workFault.size() > 0) {
					// exportExcelError(workFault);
					// throw new IllegalArgumentException("Có lỗi trong file
					// import");
					return exportExcelErrorTD(workFault);
				}
				// return (long) 1;
			}
			
		}catch (NullPointerException pointerException) {
			// pointerException.printStackTrace();
			log.error(pointerException.getMessage(), pointerException);
		} catch (Exception e) {
			// e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return "";
	}
	
	public String importFile(String fileInput, HttpServletRequest request) throws Exception {
		// long tStart = System.currentTimeMillis();
		KqHdTkDTO objSearch=new KqHdTkDTO();
		List<KqHdTkDTO> lstObj=kqHdTkDAO.doSearch(objSearch);
//		List<KqHdTkDTO> workLst = Lists.newArrayList();
		Set<KqHdTkDTO> workLst = new LinkedHashSet<>();
		List<ImportErrDTO> workFault = Lists.newArrayList();
		VpsUserToken token = (VpsUserToken) request.getSession().getAttribute("vpsUserToken");
		try {
			String Ten = "";
			File f = new File(fileInput);

			XSSFWorkbook workbook = new XSSFWorkbook(f);
			XSSFSheet sheet = workbook.getSheetAt(0);

			DataFormatter dataFormatter = new DataFormatter();
			int count = 0;
			for (Row row : sheet) {
				count++;
				// Create object OrderGoodsExelDTO
				ErrExcelDTO orderErrorFormat = new ErrExcelDTO();
				if (count >= 4 && !isRowEmpty(row)) {
					lstErrExcelDto = new ArrayList<>();
					boolean checkTinh = true;
					boolean checkMaTramTuyen = true;
					boolean checkNguonCapUng = true;
					boolean checkSoHd = true;
					boolean checkSoKhTc = true;
					boolean checkNgayKyKh = true;
					// ko check c8
					boolean checkLoaiCt = true;
					// ko check c10
					boolean checkNgayGuiHSHC = true;
					// ko check c12
					// ko check c13
					boolean checkNgayThiCong = true;
					boolean checkGtQtCdtKoVat = true;
					boolean checkGtQtCdtCoVat = true;
					boolean checkCpNhanCong = true;
					boolean checkCpVatLieu = true;
					boolean checkCpHSHC = true;
					boolean checkCpVanChuyen = true;
					boolean checkCpKhac = true;
					boolean checkCpLuong = true;
					boolean checkVat = true;
					boolean checkTong = true;
					boolean checkLuongTamNhan=true;
					

					String tinh = "";
					String maTramTuyen = "";
					String nguonCapUng = "";
					String soHd = "";
					String soKhTc = "";
					String ngayKyKh = "";
					String thuocDmTt = "";
					String loaiCt = "";
					String noiDung = "";
					String ngayGuiHshc = "";
					String soBill = "";
					String ghiChu = "";
					String ngayThiCong = "";
					String gtQtCdtKoVat = "";
					String gtQtCdtCoVat = "";
					String cpNhanCong = "";
					String cpVatLieu = "";
					String cpHshc = "";
					String cpVanChuyen = "";
					String cpKhac = "";
					String cpLuong = "";
					String vat = "";
					String luongTamNhan="";
					String tong = "";
					
		
					
							

					for (Cell cell : row) {

						// Check format file exel
						if (cell.getColumnIndex() == 1) {
							tinh = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 2) {
							maTramTuyen = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 3) {
							nguonCapUng = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 4) {
							soHd = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 5) {
							soKhTc = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 6) {
							ngayKyKh = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 7) {
							thuocDmTt = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 8) {
							loaiCt = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 9) {
							noiDung = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 10) {
							ngayGuiHshc = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 11) {
							soBill = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 12) {
							ghiChu = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 13) {
							ngayThiCong = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 14) {
							gtQtCdtKoVat = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 15) {
							gtQtCdtCoVat = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 16) {
							cpNhanCong = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 17) {
							cpVatLieu = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 18) {
							cpHshc = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 19) {
							cpVanChuyen = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 20) {
							cpKhac = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 21) {
							cpLuong = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 22) {
							vat = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 24) {
							tong = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 23) {
							luongTamNhan=dataFormatter.formatCellValue(cell);
						}
					}

					checkTinh = checkDataFromFileExel(tinh.trim(), 1, orderErrorFormat);
					checkMaTramTuyen = checkDataFromFileExel(maTramTuyen.trim(), 2, orderErrorFormat);
					checkNguonCapUng = checkDataFromFileExel(nguonCapUng.trim(), 3, orderErrorFormat);
					checkSoHd = checkDataFromFileExel(soHd.trim(), 4, orderErrorFormat);
					checkSoKhTc = checkDataFromFileExel(soKhTc.trim(), 5, orderErrorFormat);
					checkNgayKyKh = checkDataFromFileExel(ngayKyKh.trim(), 6, orderErrorFormat);
					checkLoaiCt = checkDataFromFileExel(loaiCt.trim(), 8, orderErrorFormat);
					checkNgayGuiHSHC = checkDataFromFileExel(ngayGuiHshc.trim(), 10, orderErrorFormat);
					checkNgayThiCong = checkDataFromFileExel(ngayThiCong.trim(),13, orderErrorFormat);
					checkGtQtCdtKoVat = checkDataFromFileExel(gtQtCdtKoVat.trim(), 14, orderErrorFormat);
					checkGtQtCdtCoVat = checkDataFromFileExel(gtQtCdtCoVat.trim(), 15, orderErrorFormat);
					checkCpNhanCong = checkDataFromFileExel(cpNhanCong.trim(), 16, orderErrorFormat);
					checkCpVatLieu = checkDataFromFileExel(cpVatLieu.trim(), 17, orderErrorFormat);
					checkCpHSHC = checkDataFromFileExel(cpHshc.trim(), 18, orderErrorFormat);
					checkCpVanChuyen = checkDataFromFileExel(cpVanChuyen.trim(), 19, orderErrorFormat);
					checkCpKhac = checkDataFromFileExel(cpKhac.trim(), 20, orderErrorFormat);
					checkCpLuong = checkDataFromFileExel(cpLuong.trim(), 21, orderErrorFormat);
					checkVat = checkDataFromFileExel(vat.trim(), 22, orderErrorFormat);
					checkLuongTamNhan=checkDataFromFileExel(luongTamNhan.trim(), 23, orderErrorFormat);
					checkTong = checkDataFromFileExel(tong.trim(), 24, orderErrorFormat);

					if (checkTinh && checkMaTramTuyen && checkNguonCapUng && checkSoHd && checkSoKhTc && checkNgayKyKh
							&& checkLoaiCt && checkNgayGuiHSHC && checkNgayThiCong && checkGtQtCdtKoVat && checkGtQtCdtCoVat
							&& checkCpNhanCong && checkCpVatLieu && checkCpHSHC && checkCpVanChuyen && checkCpKhac
							&& checkCpLuong && checkVat && checkTong&&checkLuongTamNhan) {
						KqHdTkDTO newObj = new KqHdTkDTO();
						newObj.setTinh(tinh);
						newObj.setMaTramTuyen(maTramTuyen);
						newObj.setNguonCapUng(nguonCapUng);
						newObj.setSoHd(soHd);
						newObj.setSoKhTc(soKhTc);
						if (StringUtils.isNotEmpty(ngayKyKh)) {
							newObj.setNgayKyKh(dt.parse(ngayKyKh));
						}
						newObj.setThuocDmToTrinh(thuocDmTt);
						newObj.setLoaiCt(loaiCt);
						newObj.setNoiDung(noiDung);
						if (StringUtils.isNotEmpty(ngayGuiHshc)) {
							newObj.setNgayGuiHshc(dt.parse(ngayGuiHshc));
						}
						newObj.setSoBill(soBill);
						newObj.setGhiChu(ghiChu);
						if (StringUtils.isNotEmpty(ngayThiCong)) {
							newObj.setNgayThiCong(dt.parse(ngayThiCong));
						}
						if (!StringUtils.isEmpty(gtQtCdtKoVat)) {
							gtQtCdtKoVat = convertValue(gtQtCdtKoVat);
							newObj.setGtQtCdtChuaVat(Float.parseFloat(gtQtCdtKoVat));
						} else {
							newObj.setGtQtCdtChuaVat((float) 0);
						}

						if (!StringUtils.isEmpty(gtQtCdtCoVat)) {
							gtQtCdtCoVat = convertValue(gtQtCdtCoVat);
							newObj.setGtQtCdtCoVat(Float.parseFloat(gtQtCdtCoVat));
						} else {
							newObj.setGtQtCdtCoVat((float) 0);
						}

						if (!StringUtils.isEmpty(cpNhanCong)) {
							cpNhanCong = convertValue(cpNhanCong);
							newObj.setCpNhanCongDn(Float.parseFloat(cpNhanCong));
						} else {
							newObj.setCpNhanCongDn((float) 0);
						}

						if (!StringUtils.isEmpty(cpVatLieu)) {
							cpVatLieu = convertValue(cpVatLieu);
							newObj.setCpVatLieuDn(Float.parseFloat(cpVatLieu));
						} else {
							newObj.setCpVatLieuDn((float) 0);
						}

						if (!StringUtils.isEmpty(cpHshc)) {
							cpHshc = convertValue(cpHshc);
							newObj.setCpHshcDn(Float.parseFloat(cpHshc));
						} else {
							newObj.setCpHshcDn((float) 0);
						}

						if (!StringUtils.isEmpty(cpVanChuyen)) {
							cpVanChuyen = convertValue(cpVanChuyen);
							newObj.setCpVanChuyenDn(Float.parseFloat(cpVanChuyen));
						} else {
							newObj.setCpVanChuyenDn((float) 0);
						}

						if (!StringUtils.isEmpty(cpKhac)) {
							cpKhac = convertValue(cpKhac);
							newObj.setChiPhiKhacDn(Float.parseFloat(cpKhac));
						} else {
							newObj.setChiPhiKhacDn((float) 0);
						}
						if (!StringUtils.isEmpty(cpLuong)) {
							cpLuong = convertValue(cpLuong);
							newObj.setChiPhiLuongDn(Float.parseFloat(cpLuong));
						} else {
							newObj.setChiPhiLuongDn((float) 0);
						}

						if (!StringUtils.isEmpty(vat)) {
							vat = convertValue(vat);
							newObj.setVatDn(Float.parseFloat(vat));
						} else {
							newObj.setVatDn((float) 0);
						}
						if (!StringUtils.isEmpty(luongTamNhan)) {
							luongTamNhan = convertValue(luongTamNhan);
							newObj.setTamUngLuong(Float.parseFloat(luongTamNhan));
						} else {
							newObj.setTamUngLuong((float) 0);
						}
						

						if (!StringUtils.isEmpty(tong)) {
							tong = convertValue(tong);
							newObj.setTongDn(Float.parseFloat(tong));
						} else {
							newObj.setTongDn((float) 0);
						}
						newObj.setNguoiTao(token.getFullName());
						newObj.setTrangThai("Mới tạo");
						
//						workLst.add(newObj);
			
						
						
						for( KqHdTkDTO ItemObj : lstObj){		// check trùng database và file excel
							if(!newObj.equals(ItemObj)){
								if(workLst.size()==0){
									workLst.add(newObj);
								}else{
									for( KqHdTkDTO itemObj: workLst){
										if(!newObj.equals(itemObj)){
											workLst.add(newObj);
											break;
										}
									}
								}
							}	
						}
						

					} else {
						ImportErrDTO errObj = new ImportErrDTO();
						errObj.setColumn1(tinh);
						errObj.setColumn2(maTramTuyen);
						errObj.setColumn3(nguonCapUng);
						errObj.setColumn4(soHd);
						errObj.setColumn5(soKhTc);
						errObj.setColumn6(ngayKyKh);
						errObj.setColumn7(thuocDmTt);
						errObj.setColumn8(loaiCt);
						errObj.setColumn9(noiDung);
						errObj.setColumn10(ngayGuiHshc);
						errObj.setColumn11(soBill);
						errObj.setColumn12(ghiChu);
						errObj.setColumn13(ngayThiCong);
						errObj.setColumn14(gtQtCdtKoVat);
						errObj.setColumn15(gtQtCdtCoVat);
						errObj.setColumn16(cpNhanCong);
						errObj.setColumn17(cpVatLieu);
						errObj.setColumn18(cpHshc);
						errObj.setColumn19(cpVanChuyen);
						errObj.setColumn20(cpKhac);
						errObj.setColumn21(cpLuong);
						errObj.setColumn22(vat);
						errObj.setColumn24(tong);
						errObj.setColumn23(luongTamNhan);
						errObj.setLstErrorOrder(lstErrExcelDto);
						workFault.add(errObj);
					}
				}

			}

			workbook.close();

			System.out.println("size done: " + workLst.size());
			System.out.println("size fault: " + workFault.size());
			if (workLst.size() < 0 && workFault.size() < 0) {
				throw new IllegalArgumentException("File import không có dữ liệu");
			} else {
				if (workLst.size() > 0) {
					List<KqHdTkDTO> workLstFake = new ArrayList<KqHdTkDTO>(workLst);
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
			
			
			
		}
		 catch (NullPointerException pointerException) {
			// pointerException.printStackTrace();
			log.error(pointerException.getMessage(), pointerException);
		} catch (Exception e) {
			// e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return "";
	}

	public boolean checkDataFromFileExel(String data, int columnIndex, ErrExcelDTO orderErrorFormat) {
		orderErrorFormat = new ErrExcelDTO();
		String[] L = { "Nhà trạm", "Truyền dẫn", "Cơ điện", "GPON" };
		switch (columnIndex) {
		case 1:
			if (StringUtils.isEmpty(data)) {
				orderErrorFormat.setDetailError("Tỉnh không được để trống");
				lstErrExcelDto.add(orderErrorFormat);
				return false;
			}
			break;
		case 2:
			if (StringUtils.isEmpty(data)) {
				orderErrorFormat.setDetailError("Mã trạm/Tuyến không được để trống");
				lstErrExcelDto.add(orderErrorFormat);
				return false;
			}
			break;
		
		case 4:
			if (StringUtils.isEmpty(data)) {
				orderErrorFormat.setDetailError("Số hợp đồng không được để trống");
				lstErrExcelDto.add(orderErrorFormat);
				return false;
			}
			break;
		case 5:
			if (StringUtils.isEmpty(data)) {
				orderErrorFormat.setDetailError("Số kế hoạch thi công không được để trống");
				lstErrExcelDto.add(orderErrorFormat);
				return false;
			}
			break;
		case 6:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isDate(data, "dd/MM/yyyy")) {
					if(ValidateUtils.isDate(data, "MM/dd/yyyy")){
						  DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH).toFormat();
					}else{
					orderErrorFormat.setDetailError("Ngày ký kế hoạch không hợp lệ(ex:13/06/2018)");
					lstErrExcelDto.add(orderErrorFormat);
					return false;}
				}
			}
			break;
		case 8:
			if (StringUtils.isNotEmpty(data)) {
				if (!contains(L, data.trim())) {
					orderErrorFormat.setDetailError("Không có loại công trình :" + data);
					lstErrExcelDto.add(orderErrorFormat);
					return false;
				}
			}

			break;
		case 10:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isDate(data, "dd/MM/yyyy")) {
					if(ValidateUtils.isDate(data, "MM/dd/yyyy")){
						  DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH).toFormat();
					}else{
					orderErrorFormat.setDetailError("Ngày gửi HSHC lên công ty không hợp lệ(ex:13/06/2018)");
					lstErrExcelDto.add(orderErrorFormat);
					return false;}
				}
			}
			break;
		case 13:
			if (StringUtils.isEmpty(data)) {
				orderErrorFormat.setDetailError("ngày thi công xong không được để trống");
				lstErrExcelDto.add(orderErrorFormat);
				return false;
			}else{
				if (!ValidateUtils.isDate(data, "dd/MM/yyyy")) {
					if(ValidateUtils.isDate(data, "MM/dd/yyyy")){
						  DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH).toFormat();
					}else{
					orderErrorFormat.setDetailError("Ngày thi công xong không hợp lệ(ex:13/06/2018)");
					lstErrExcelDto.add(orderErrorFormat);
					return false;}
				}
			}
			break;
		case 14:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isFloat(data)) {
					if (!ValidateUtils.isFloat(convertValue(data))) {
						orderErrorFormat.setDetailError("Giá trị Qt Cdt chưa VAT không đúng định dạng");
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
						orderErrorFormat.setDetailError("Giá trị Qt Cdt có VAT không đúng định dạng");
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
						orderErrorFormat.setDetailError("Chi phí nhân công không đúng định dạng");
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
						orderErrorFormat.setDetailError("Chi phí vật liệu không đúng định dạng");
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
						orderErrorFormat.setDetailError("Chi phí HSHC không đúng định dạng");
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
						orderErrorFormat.setDetailError("Chi phí vận chuyển không đúng định dạng");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				}
			}
			break;
		case 20:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isFloat(data)) {
					if (!ValidateUtils.isFloat(convertValue(data))) {
						orderErrorFormat.setDetailError("Chi phí khác không đúng định dạng");
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
						orderErrorFormat.setDetailError("Chi phí lương không đúng định dạng");
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
						orderErrorFormat.setDetailError("VAT không đúng định dạng");
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
						orderErrorFormat.setDetailError("Lương tạm nhận không đúng định dạng");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				}
			}
		case 24:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isFloat(data)) {
					if (!ValidateUtils.isFloat(convertValue(data))) {
						orderErrorFormat.setDetailError("Tổng không đúng định dạng");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				}
			}
			break;
		case 25:
			if (StringUtils.isEmpty(data)) {
				orderErrorFormat.setDetailError("Ngày Gửi PTk,Hshc không được để trống");
				lstErrExcelDto.add(orderErrorFormat);
				return false;
			}else{
				if (!ValidateUtils.isDate(data, "dd/MM/yyyy")) {
					if(ValidateUtils.isDate(data, "MM/dd/yyyy")){
						  DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH).toFormat();
					}else{
					orderErrorFormat.setDetailError("Ngày Gửi PTk,Hshc không hợp lệ(ex:13/06/2018)");
					lstErrExcelDto.add(orderErrorFormat);
					return false;}
				}
			}
			break;
		case 27:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isDate(data, "dd/MM/yyyy")) {
					if(ValidateUtils.isDate(data, "MM/dd/yyyy")){
						  DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH).toFormat();
					}else{
					orderErrorFormat.setDetailError("Ngày tương ứng theo tình trạng chứng từ không hợp lệ(ex:13/06/2018)");
					lstErrExcelDto.add(orderErrorFormat);
					return false;}
				}
			}
			break;
		case 29:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isDate(data, "dd/MM/yyyy")) {
					if(ValidateUtils.isDate(data, "MM/dd/yyyy")){
						  DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH).toFormat();
					}else{
					orderErrorFormat.setDetailError("Ngày PTK thẩm định xong không hợp lệ(ex:13/06/2018)");
					lstErrExcelDto.add(orderErrorFormat);
					return false;}
				}
			}
			break;
		case 30:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isFloat(data)) {
					if (!ValidateUtils.isFloat(convertValue(data))) {
						orderErrorFormat.setDetailError("Chi phí nhân công đã thẩm định không đúng định dạng");
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
						orderErrorFormat.setDetailError("Chi phí vật liệu đã thẩm định không đúng định dạng");
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
						orderErrorFormat.setDetailError("Chi phí HSHC đã thẩm định không đúng định dạng");
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
						orderErrorFormat.setDetailError("Chi phí vận chuyển đã thẩm định không đúng định dạng");
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
						orderErrorFormat.setDetailError("Chi phí khác đã thẩm định không đúng định dạng");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				}
			}
			break;
		case 35:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isFloat(data)) {
					if (!ValidateUtils.isFloat(convertValue(data))) {
						orderErrorFormat.setDetailError("Chi phí Lương đã thẩm định không đúng định dạng");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				}
			}
			break;
		case 36:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isFloat(data)) {
					if (!ValidateUtils.isFloat(convertValue(data))) {
						orderErrorFormat.setDetailError("VAT đã thẩm định không đúng định dạng");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				}
			}
			break;
		case 37:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isFloat(data)) {
					if (!ValidateUtils.isFloat(convertValue(data))) {
						orderErrorFormat.setDetailError("Tổng đã thẩm định không đúng định dạng");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				}
			}
			break;
		case 38:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isFloat(data)) {
					if (!ValidateUtils.isFloat(convertValue(data))) {
						orderErrorFormat.setDetailError("giá trị đã thẩm định chưa vat không đúng định dạng");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				}
			}
			break;
		case 39:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isFloat(data)) {
					if (!ValidateUtils.isFloat(convertValue(data))) {
						orderErrorFormat.setDetailError("giá trị đã thẩm định có vat không đúng định dạng");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				}
			}
			break;
		case 42:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isFloat(data)) {
					if (!ValidateUtils.isFloat(convertValue(data))) {
						orderErrorFormat.setDetailError("Tạm ứng lương không đúng định dạng");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				}
			}
			break;
		case 43:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isFloat(data)) {
					if (!ValidateUtils.isFloat(convertValue(data))) {
						orderErrorFormat.setDetailError("Lương thực nhận không đúng định dạng");
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
						orderErrorFormat.setDetailError("Ty lệ không đúng định dạng");
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
	public String exportExcelErrorTD(List<ImportErrDTO> obj) throws Exception {
		// TODO Auto-generated method stub
		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream file = new BufferedInputStream(new FileInputStream(filePath + "import_Hd_Dn_TDErr.xlsx"));
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
				
				XSSFCell cell48 = row.createCell(48);
				cell48.setCellValue(obj.get(i).getColumn48());
				
				
			
				
				
				

				for (int j = 0; j < obj.get(i).getLstErrorOrder().size(); j++) {
					err.append(obj.get(i).getLstErrorOrder().get(j).getDetailError() + "-");
					// System.out.println("err" + err);
				}
				System.out.println("err " + i + ":" + err);

				XSSFCell cell49 = row.createCell(49);
				cell49.setCellValue(err.toString());
				
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
				cell46.setCellStyle(style);
				cell48.setCellStyle(style);
				
				
			
			}
			file.close();

			File out = new File(folder2Upload + File.separatorChar + "import_Hd_Dn_TDErr" + startExportTime + ".xlsx");

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
		return UEncrypt.encryptFileUploadPath("import_Hd_Dn_TDErr" + startExportTime + ".xlsx");
	}
	public String exportExcelError(List<ImportErrDTO> obj) throws Exception {
		// TODO Auto-generated method stub
		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream file = new BufferedInputStream(new FileInputStream(filePath + "importHdDnErr.xlsx"));
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
			int rownum = 2;
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
				
			
				
				
				

				for (int j = 0; j < obj.get(i).getLstErrorOrder().size(); j++) {
					err.append(obj.get(i).getLstErrorOrder().get(j).getDetailError() + "-");
					// System.out.println("err" + err);
				}
				System.out.println("err " + i + ":" + err);

				XSSFCell cell24 = row.createCell(24);
				cell24.setCellValue(err.toString());
				
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
				
				
			
			}
			file.close();

			File out = new File(folder2Upload + File.separatorChar + "importHdDnErr" + startExportTime + ".xlsx");

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
		return UEncrypt.encryptFileUploadPath("importHdDnErr" + startExportTime + ".xlsx");
	}

	public static boolean contains(String[] arr, String item) {
		for (String n : arr) {
			if (item.equals(n)) {
				return false;
			}
		}
		return true;
	}

	public String convertValue(String value) {
		String val = value.replaceAll(",", "");
		return val;
	}
//	public Date convertDate(Date value){
//		Date val = value.
//		return val;
//	}
}
