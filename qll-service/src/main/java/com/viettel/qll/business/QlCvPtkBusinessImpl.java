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
import java.util.List;
import java.util.Locale;
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
//import org.eclipse.jdt.internal.compiler.apt.dispatch.RoundDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.viettel.ktts2.common.UEncrypt;
import com.viettel.qll.bo.QlCvPtkBO;
import com.viettel.qll.dao.QlCvPtkDAO;
import com.viettel.qll.dto.ErrExcelDTO;
import com.viettel.qll.dto.ImportErrDTO;

import com.viettel.qll.dto.QlCvPtkDTO;
import com.viettel.qll.dto.VpsUserToken;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;
import com.viettel.utils.ValidateUtils;

@Service("qlCvPtkBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class QlCvPtkBusinessImpl extends BaseFWBusinessImpl<QlCvPtkDAO, QlCvPtkDTO, QlCvPtkBO>
		implements QlCvPtkBusiness {
	protected final Logger log = Logger.getLogger(QlCvPtkBusinessImpl.class);
	@Autowired
	private QlCvPtkDAO qlCvPtkDAO;
	List<ErrExcelDTO> lstErrExcelDto;
	@Value("${folder_upload2}")
	private String folder2Upload;

	public QlCvPtkBusinessImpl() {
		tModel = new QlCvPtkBO();
		tDAO = qlCvPtkDAO;
	}

	DateFormat dt = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public QlCvPtkDAO gettDAO() {
		return qlCvPtkDAO;
	}

	public List<QlCvPtkDTO> getForAutoCompleteSHD(QlCvPtkDTO obj) {
		return qlCvPtkDAO.getForAutoCompleteSHD(obj);
	}

	/**
	 * Tìm kiếm
	 */
	@Override
	public DataListDTO doSearch(QlCvPtkDTO obj) throws Exception {
		List<QlCvPtkDTO> ls = qlCvPtkDAO.doSearch(obj);
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		data.setTotal(obj.getTotalRecord());
		data.setSize(obj.getTotalRecord());
		data.setStart(1);
		return data;
	}

	/**
	 * thêm mới file scan
	 */
	@Override
	@Transactional
	public Long updatePathFileScan(QlCvPtkDTO obj) throws Exception {
		try {
			long ids = qlCvPtkDAO.updatePathFileScan(obj);
			return ids;
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Có lỗi khi upload ảnh!!!");
		}

	}

	/**
	 * thêm mới file excell
	 */
	@Override
	@Transactional
	public Long updatePathFileExcell(QlCvPtkDTO obj) throws Exception {
		try {
			long ids = qlCvPtkDAO.updatePathFileExcell(obj);
			return ids;
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Có lỗi khi upload excell!!!");
		}

	}

	/**
	 * thêm mới file Qtk
	 */
	@Override
	@Transactional
	public Long updatePathFileQtk(QlCvPtkDTO obj) throws Exception {
		try {
			long ids = qlCvPtkDAO.updatePathFileQtk(obj);
			return ids;
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Có lỗi khi upload file!!!");
		}

	}
	
	/**
	 * thêm mới file scan
	 */
	@Override
	@Transactional
	public Long updatePathFileQtDt(QlCvPtkDTO obj) throws Exception {
		try {
			long ids = qlCvPtkDAO.updatePathFileQtDt(obj);
			return ids;
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Có lỗi khi upload file!!!");
		}

	}
	
	/**
	 * Xóa 1 bản ghi
	 */
	@Override
	public long deleteObj(QlCvPtkDTO obj) {
		try {
			qlCvPtkDAO.delete(obj.toModel());
			return 1l;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0l;
	}
	/**
	 * xóa 1 list theo điều kiện tìm kiếm 
	 * @param obj
	 * @return
	 */
	@Override
	public long deleteListObj(QlCvPtkDTO obj) {
		List<QlCvPtkDTO> lst = qlCvPtkDAO.doSearch(obj);
		List<Long> lstId = new ArrayList<>();
		int size = lst.size();
		int count = 0;
		int groupBatch = 0;
		try {
			for (QlCvPtkDTO obj1 : lst) {
				count++;
				groupBatch++;
				lstId.add(obj1.getQlCvPtkId());
				if (groupBatch == 999) {
					groupBatch=0;
					qlCvPtkDAO.deleteList(lstId);
					lstId.clear();
				}
				if (groupBatch < 999 && count == size) {
					qlCvPtkDAO.deleteList(lstId);

				}
			}
			return 1l;
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return 0l;
	}

	/**
	 * Thêm mới công việc quản lý ptk
	 * 
	 * @param obj
	 * @return
	 */
	public long saveAddCv(QlCvPtkDTO obj) {
		try {
			long ids = qlCvPtkDAO.saveObject(obj.toModel());
			return ids;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0l;
	}

	/**
	 * Xuất báo cáo tổng hợp công việc Ptk
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String importFile(String fileInput, HttpServletRequest request) throws Exception{
		
		QlCvPtkDTO objSearch= new QlCvPtkDTO();
		List<QlCvPtkDTO> lstObj=qlCvPtkDAO.doSearch(objSearch);
		List<QlCvPtkDTO> workLst = Lists.newArrayList();
		List<ImportErrDTO> workFault = Lists.newArrayList();
		VpsUserToken token = (VpsUserToken) request.getSession().getAttribute("vpsUserToken");
		try {
			String Ten = "";
			File f = new File(fileInput);

			XSSFWorkbook workbook = new XSSFWorkbook(f);
			XSSFSheet sheet = workbook.getSheetAt(0);

			DataFormatter dataFormatter = new DataFormatter();
			int count = 0;
			for(Row row : sheet){
				count++;
				ErrExcelDTO orderErrorFormat = new ErrExcelDTO();
				if (count >= 3 && !isRowEmpty(row)) {
					lstErrExcelDto = new ArrayList<>();
//					boolean checkCNKV = true;			// Chi nhánh khu vực
//					boolean checkTinh = true;			// tỉnh
//					boolean checkMaTramTuyen = true;	//	mã trạm tuyến 
//					boolean checkSoHd = true;			// số hợp đồng	
//					boolean checkThuocDMTT = true;		// thuộc định mức||tờ trình
//					boolean checkLoaiCt = true;			// loại công trình
//					boolean checkNoiDung = true;		// Nội dung			
					boolean checkNgayGuiHSHC = true;	// Ngày gửi HSHC lên công ty
//					boolean checkSoBill = true;			// Số bill
//					boolean checkGhiChu = true;			// ghi chú
					boolean checkGiaTriSLHTTT = true;	// giá trị theo sản lượng hạ tâng tạm tính
					boolean checkGiaTriDNQTKCN = true;	// giá trị dề nghị QTK của chi nhánh
					boolean checkNgayTriDNQTK = true; 	// ngày trị dề nghi QTK
					boolean checkNgayPTKgHSHC = true;   // ngày nhận gửi PTK gửi HSHC
//					boolean checkTinhtrangCCt = true;   // tình trạng chuyển chứng từ
					//ko check 16						// ngày tương ứng theo tình trạng chứng từ
//					boolean checkGhiChuHs =true;        //	ghi chú hồ sơ
					boolean checkGtPTKTdQTK =true;      //	giá trị PTK thẩm định xong QTK 
					boolean checkNgayTDKTdQTK =true;    //	ngày PTK thẩm định xong QTK
					boolean checkGtDnQTvsCDT = true;	// giá trị đề nghị QT với CĐT
					boolean checkGtQTvsCDT = true;      // giá trị chốt QT vs CĐT
					boolean checkNgayGtQTvsCDT = true;  // ngày giá trị chốt QT vs CĐT
//					boolean checkGhichuDn =true;         // ghi chú dn
//					boolean checkThangQTK=true;	        // tháng QTK
//					boolean checkThangQTvsCDT=true;		// tháng QT với CDT
					//ko check 26						// tỉ lệ % 	GtQTvsCDT/GtDnQTvsCDT
					//ko check 27						// NgayGtQTvsCDT - NgayPTKgHSHC
//					boolean checkGiaitrinhQhXlCt =true; // Giải trình về quá hạn chưa xử lý chứng từ
//					boolean checkLuuKho = true ;     // Đang lưu tại kho 
					//ko check 30						// NGười duyệt PTK
//					boolean	checkNguoiCDT = true;       // Người duyệt CDT
				
					
					String CNKV = "";
					String tinh = "";
					String maTramTuyen = "";
					String soHd = "";
					String thuocDmTt = "";
					String loaiCt = "";
					String noiDung = "";
					String ngayGuiHshc = "";
					String soBill = "";
					String ghiChu = "";
					String gtSlHtTt = "";
					String gtDnQtKcn = "";
					String ntDnQtk = "";
					String nPtkHshc = "";
					String tinhTrangCCt= "";
					String ngayTtCt = "";
					String ghiChuHs = "";
					String gtPtkTdQtk = "";
					String ngayPtkTdQtk = "";
					String gtDnQtCdt = "";
					String gtQtCdt = "";
					String ngayGtQtCdt = "";
					String ghiChuDn = "";
					String thangQtk = "";
					String thangQtCdt = "";
					String tiLe = "";
					String soSanh = "";
					String giaitrinhQhXlCt = "";
					String luuKho = "";
					String nguoiPtk = "";
					String nguoiCdt = "";
					
				
					for(Cell cell : row){
						if (cell.getColumnIndex() == 1) {
							CNKV = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 2) {
							tinh = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 3) {
							maTramTuyen = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 4) {
							soHd = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 5) {
							thuocDmTt = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 6) {
							loaiCt = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 7) {
							noiDung = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 8) {
							ngayGuiHshc = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 9) {
							soBill = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 10) {
							ghiChu = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 11) {
							gtSlHtTt = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 12) {
							gtDnQtKcn = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 13) {
							ntDnQtk = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 14) {
							nPtkHshc = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 15) {
							tinhTrangCCt = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 16) {
							ngayTtCt = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 17) {
							ghiChuHs = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 18) {
							gtPtkTdQtk = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 19) {
							ngayPtkTdQtk = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 20) {
							gtDnQtCdt = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 21) {
							gtQtCdt = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 22) {
							ngayGtQtCdt = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 23) {
							ghiChuDn = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 24) {
							thangQtk = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 25) {
							thangQtCdt = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 26) {
							tiLe = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 27) {
							soSanh = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 28) {
							giaitrinhQhXlCt = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 29) {
							luuKho = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 30) {
							nguoiPtk = dataFormatter.formatCellValue(cell);
						} else if (cell.getColumnIndex() == 31) {
							nguoiCdt = dataFormatter.formatCellValue(cell);
						} 
					}
//					checkCNKV = checkDataFromFileExel(CNKV.trim(), 1, orderErrorFormat);
//					checkTinh = checkDataFromFileExel(tinh.trim(), 2, orderErrorFormat);
//					checkMaTramTuyen = checkDataFromFileExel(maTramTuyen.trim(), 3, orderErrorFormat);
//					checkSoHd = checkDataFromFileExel(soHd.trim(), 4, orderErrorFormat);
//					checkThuocDMTT = checkDataFromFileExel(thuocDmTt.trim(), 5, orderErrorFormat);
//					checkLoaiCt = checkDataFromFileExel(loaiCt.trim(), 6, orderErrorFormat);
//					checkNoiDung = checkDataFromFileExel(noiDung.trim(), 7, orderErrorFormat);
					checkNgayGuiHSHC = checkDataFromFileExel(ngayGuiHshc.trim(), 8, orderErrorFormat);
//					checkSoBill = checkDataFromFileExel(soBill.trim(), 9, orderErrorFormat);
//					checkGhiChu = checkDataFromFileExel(ghiChu.trim(), 10, orderErrorFormat);
					checkGiaTriSLHTTT = checkDataFromFileExel(gtSlHtTt.trim(), 11, orderErrorFormat);
					checkGiaTriDNQTKCN = checkDataFromFileExel(gtDnQtKcn.trim(), 12, orderErrorFormat);
					checkNgayTriDNQTK = checkDataFromFileExel(ntDnQtk.trim(), 13, orderErrorFormat);
					checkNgayPTKgHSHC = checkDataFromFileExel(nPtkHshc.trim(), 14, orderErrorFormat);
//					checkTinhtrangCCt = checkDataFromFileExel(tinhTrangCCt.trim(), 15, orderErrorFormat);
//					checkGhiChuHs = checkDataFromFileExel(ghiChuHs.trim(), 17, orderErrorFormat);
					checkGtPTKTdQTK = checkDataFromFileExel(gtPtkTdQtk.trim(), 18, orderErrorFormat);
					checkNgayTDKTdQTK = checkDataFromFileExel(ngayPtkTdQtk.trim(), 19, orderErrorFormat);
					checkGtDnQTvsCDT = checkDataFromFileExel(gtDnQtCdt.trim(), 20, orderErrorFormat);
					checkGtQTvsCDT = checkDataFromFileExel(gtQtCdt.trim(), 21, orderErrorFormat);
					checkNgayGtQTvsCDT = checkDataFromFileExel(ngayGtQtCdt.trim(), 22, orderErrorFormat);
//					checkGhichuDn = checkDataFromFileExel(ghiChuDn.trim(), 23, orderErrorFormat);
//					checkThangQTK = checkDataFromFileExel(thangQtk.trim(), 24, orderErrorFormat);
//					checkThangQTvsCDT = checkDataFromFileExel(thangQtCdt.trim(), 25, orderErrorFormat);
//					checkGiaitrinhQhXlCt = checkDataFromFileExel(giaitrinhQhXlCt.trim(), 28, orderErrorFormat);
//					checkLuuKho = checkDataFromFileExel(luuKho.trim(), 29, orderErrorFormat);
//					checkNguoiCDT = checkDataFromFileExel(nguoiCdt.trim(), 31, orderErrorFormat);
				
					if(
					checkNgayGuiHSHC&&checkGiaTriSLHTTT&&checkGiaTriDNQTKCN&&checkNgayTriDNQTK
					&&checkNgayPTKgHSHC&&checkGtPTKTdQTK&&checkNgayTDKTdQTK&&checkGtDnQTvsCDT
					&&checkGtQTvsCDT&&checkNgayGtQTvsCDT){
						QlCvPtkDTO newObj = new QlCvPtkDTO();
						newObj.setCnkv(CNKV);
						newObj.setTinh(tinh);
						newObj.setMaTramTuyen(maTramTuyen);
						newObj.setSoHd(soHd);
						newObj.setThuocDmTt(thuocDmTt);
						newObj.setLoaiCt(loaiCt);
						newObj.setNoiDung(noiDung);
						if(StringUtils.isNotEmpty(ngayGuiHshc)){
							newObj.setNgayGuiHshc(dt.parse(ngayGuiHshc));
						}
						newObj.setSoBill(soBill);
						newObj.setGhiChu(ghiChu);
						if(!StringUtils.isEmpty(gtSlHtTt)){
							gtSlHtTt = convertValue(gtSlHtTt);
							newObj.setGtSlHtTinh(Float.parseFloat(gtSlHtTt));
						}else{
							newObj.setGtSlHtTinh((float) 0);
						}
						if(!StringUtils.isEmpty(gtDnQtKcn)){
							gtDnQtKcn = convertValue(gtDnQtKcn);
							newObj.setGtDnQtkCn(Float.parseFloat(gtDnQtKcn));
						}else{
							newObj.setGtDnQtkCn((float) 0);
						}
						if(StringUtils.isNotEmpty(ntDnQtk)){
							newObj.setNgayDnQtk(dt.parse(ntDnQtk));
						}
						if(StringUtils.isNotEmpty(nPtkHshc)){
							newObj.setNgayNhanGuiPtkHshc(dt.parse(nPtkHshc));
						}
						newObj.setTinhTrangChuyenUngTu(tinhTrangCCt);
						newObj.setNgayTuongUngTtcut(new Date());
						newObj.setGhiChuHsLoi(ghiChuHs);
						
						if(!StringUtils.isEmpty(gtPtkTdQtk)){
							gtPtkTdQtk = convertValue(gtPtkTdQtk);
							newObj.setGtPtkTdXongQtk(Float.parseFloat(gtPtkTdQtk));
						}else{
							newObj.setGtPtkTdXongQtk((float) 0);
						}
						
						if(StringUtils.isNotEmpty(ngayPtkTdQtk)){
							newObj.setNgayPtkTdXongQtk(dt.parse(ngayPtkTdQtk));
						}
						
						if(!StringUtils.isEmpty(gtDnQtCdt)){
							gtDnQtCdt = convertValue(gtDnQtCdt);
							newObj.setGtDnQtCdt(Float.parseFloat(gtDnQtCdt));
						}else{
							newObj.setGtDnQtCdt((float) 0);
						}
						if(!StringUtils.isEmpty(gtQtCdt)){
							gtQtCdt = convertValue(gtQtCdt);
							newObj.setGtChotQtCdt(Float.parseFloat(gtQtCdt));
						}else{
							newObj.setGtChotQtCdt((float) 0);
						}
						if(StringUtils.isNotEmpty(ngayGtQtCdt)){
							newObj.setNgayGtChotQtCdt(dt.parse(ngayGtQtCdt));
						}
						newObj.setGhiChuDn(ghiChuDn);
						newObj.setThangQtk(thangQtk);
						newObj.setThangQtkCdt(thangQtCdt);
						
						if(!StringUtils.isEmpty(tiLe)){
							newObj.setTyLe(Float.parseFloat(convertValue(tiLe)));
						}else if(!StringUtils.isEmpty(gtQtCdt)&&!StringUtils.isEmpty(gtDnQtCdt)){
							float giaTriQtCdt = Float.parseFloat(convertValue(gtQtCdt));
							float giaTriDnQtCdt = Float.parseFloat(convertValue(gtDnQtCdt));
							float tle =Math.round((giaTriQtCdt/giaTriDnQtCdt)*10000);
							float xxx = tle/100;
							newObj.setTyLe(xxx);
						}else{
							newObj.setTyLe((float) 0);
						}
												
						if(StringUtils.isNotEmpty(ngayGtQtCdt) && StringUtils.isNotEmpty(nPtkHshc)){							
							 long date = Math.abs(dt.parse(nPtkHshc).getTime() - dt.parse(ngayGtQtCdt).getTime());
							 if( date >= Math.abs(35 * 24 * 3600 * 1000)){
								 newObj.setHsTonQua35n("Hồ sơ đã quá hạn");
							 }else{
								 newObj.setHsTonQua35n("Hồ sơ còn hạn");
							 }
						}
						newObj.setGtQhChuaXl(giaitrinhQhXlCt);
						newObj.setLuuTaiKho(luuKho);
						newObj.setNguoiDuyetPtk(token.getFullName());
						newObj.setNguoiDuyetCdt(nguoiCdt);
						workLst.add(newObj);
					
					}else{
						ImportErrDTO errObj = new ImportErrDTO();
						errObj.setColumn1(CNKV);
						errObj.setColumn2(tinh);
						errObj.setColumn3(maTramTuyen);
						errObj.setColumn4(soHd);
						errObj.setColumn5(thuocDmTt);
						errObj.setColumn6(loaiCt);
						errObj.setColumn7(noiDung);
						errObj.setColumn8(ngayGuiHshc);
						errObj.setColumn9(soBill);
						errObj.setColumn10(ghiChu);
						errObj.setColumn11(gtSlHtTt);
						errObj.setColumn12(gtDnQtKcn);
						errObj.setColumn13(ntDnQtk);
						errObj.setColumn14(nPtkHshc);
						errObj.setColumn15(tinhTrangCCt);
						errObj.setColumn16(ngayTtCt);
						errObj.setColumn17(ghiChuHs);
						errObj.setColumn18(gtPtkTdQtk);
						errObj.setColumn19(ngayPtkTdQtk);
						errObj.setColumn20(gtDnQtCdt);
						errObj.setColumn21(gtQtCdt);
						errObj.setColumn22(ngayGtQtCdt);
						errObj.setColumn23(ghiChuDn);
						errObj.setColumn24(thangQtk);
						errObj.setColumn25(thangQtCdt);
						errObj.setColumn26(tiLe);
						errObj.setColumn27(soSanh);
						errObj.setColumn28(giaitrinhQhXlCt);
						errObj.setColumn29(luuKho);
						errObj.setColumn30(nguoiPtk);
						errObj.setColumn31(nguoiCdt);
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
					saveList(workLst);
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
		String[] L = { "BTS", "Ngầm", "Công văn lẻ", "Chi nhánh tỉnh" };
		switch (columnIndex) {
		
		case 8:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isDate(data, "dd/MM/yyyy")) {
						if (ValidateUtils.isDate(data, "MM/dd/yyyy")) {
							 DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH).toFormat();
					}else{
						orderErrorFormat.setDetailError("Ngày gửi HSHC không hợp lệ(ex:13/06/2018)");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				}
			}
			break;
	
		case 11:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isFloat(data)) {
					if (!ValidateUtils.isFloat(convertValue(data))) {
						orderErrorFormat.setDetailError("Giá trị sản lượng theo hạ tầng tạm tính không đúng định dạng");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				}
			}
			break;
		case 12:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isFloat(data)) {
					if (!ValidateUtils.isFloat(convertValue(data))) {
						orderErrorFormat.setDetailError("Giá trị đề nghị QTK  không đúng định dạng");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				}
			}
			break;
		case 13:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isDate(data, "dd/MM/yyyy")) {
					if (ValidateUtils.isDate(data, "MM/dd/yyyy")) {
						 DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH).toFormat();
					}else{
					orderErrorFormat.setDetailError("Ngày trị đề nghị QTK không hợp lệ(ex:13/06/2018)");
					lstErrExcelDto.add(orderErrorFormat);
					return false;}
				}
			}
			break;
		case 14:
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isDate(data, "dd/MM/yyyy")) {
					if (ValidateUtils.isDate(data, "MM/dd/yyyy")) {
						 DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH).toFormat();
					}else{
					orderErrorFormat.setDetailError("Ngày nhận gửi PTK gửi HSHC không hợp lệ(ex:13/06/2018)");
					lstErrExcelDto.add(orderErrorFormat);
					return false;}
				}
			}
			break;
		
		case 18 :
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isFloat(data)) {
					if (!ValidateUtils.isFloat(convertValue(data))) {
						orderErrorFormat.setDetailError("Giá trị PTK thẩm định xong QTK  không đúng định dạng");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				}
			}
			break;
		case 19 :
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isDate(data, "dd/MM/yyyy")) {
					if (ValidateUtils.isDate(data, "MM/dd/yyyy")) {
						 DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH).toFormat();
					}else{
					orderErrorFormat.setDetailError("Ngày PTK thẩn định xong QTK không hợp lệ(ex:13/06/2018)");
					lstErrExcelDto.add(orderErrorFormat);
					return false;}
				}
			}
			break;
		case 20 :
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isFloat(data)) {
					if (!ValidateUtils.isFloat(convertValue(data))) {
						orderErrorFormat.setDetailError("Giá trị đề nghị QT với CĐT  không đúng định dạng");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				}
			}
			break;
		case 21 : 
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isFloat(data)) {
					if (!ValidateUtils.isFloat(convertValue(data))) {
						orderErrorFormat.setDetailError("Giá trị chôt QT với CĐT  không đúng định dạng");
						lstErrExcelDto.add(orderErrorFormat);
						return false;
					}
				}
			}
			break;
		case 22 :
			if (StringUtils.isNotEmpty(data)) {
				if (!ValidateUtils.isDate(data, "dd/MM/yyyy")) {
					if (ValidateUtils.isDate(data, "MM/dd/yyyy")) {
						 DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH).toFormat();
					}else{
					orderErrorFormat.setDetailError("Ngày giá trị chôt QT với CĐT không hợp lệ(ex:13/06/2018)");
					lstErrExcelDto.add(orderErrorFormat);
					return false;}
				}
			}
			break;
	
			
		default:
			break;
		}

		return true;
	}
	public String exportExcelError(List<ImportErrDTO> obj) throws Exception {
		// TODO Auto-generated method stub
		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream file = new BufferedInputStream(new FileInputStream(filePath + "ImportCvPtkErr.xlsx"));
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
				cell11.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				cell11.setCellValue(obj.get(i).getColumn11());

				XSSFCell cell12 = row.createCell(12);
				cell12.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
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
				cell18.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				cell18.setCellValue(obj.get(i).getColumn18());
				
				XSSFCell cell19 = row.createCell(19);
				cell19.setCellValue(obj.get(i).getColumn19());
				
				XSSFCell cell20 = row.createCell(20);
				cell20.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				cell20.setCellValue(obj.get(i).getColumn20());
				
				XSSFCell cell21 = row.createCell(21);
				cell21.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
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
				cell26.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
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
				

				for (int j = 0; j < obj.get(i).getLstErrorOrder().size(); j++) {
					err.append(obj.get(i).getLstErrorOrder().get(j).getDetailError() + "-");
					// System.out.println("err" + err);
				}
				System.out.println("err " + i + ":" + err);

				XSSFCell cell32 = row.createCell(32);
				cell32.setCellValue(err.toString());
				
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
				cell11.setCellStyle(styleNumber);
				cell12.setCellStyle(styleNumber);
				cell13.setCellStyle(style);
				cell14.setCellStyle(style);
				cell15.setCellStyle(style);
				cell16.setCellStyle(style);
				cell17.setCellStyle(style);
				cell18.setCellStyle(styleNumber);
				cell19.setCellStyle(style);
				cell20.setCellStyle(styleNumber);
				cell21.setCellStyle(styleNumber);
				cell22.setCellStyle(style);
				cell23.setCellStyle(style);
				cell24.setCellStyle(style);
				cell25.setCellStyle(style);
				cell26.setCellStyle(styleNumber);
				cell27.setCellStyle(style);
				cell28.setCellStyle(style);
				cell29.setCellStyle(style);
				cell30.setCellStyle(style);
				cell31.setCellStyle(style);
			
			}
			file.close();

			File out = new File(folder2Upload + File.separatorChar + "ImportCvPtkErr" + startExportTime + ".xlsx");

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
		return UEncrypt.encryptFileUploadPath("ImportCvPtkErr" + startExportTime + ".xlsx");
	}
	
	public String exportRowAll( QlCvPtkDTO lst, HttpServletRequest request) throws Exception {

		List<QlCvPtkDTO> obj1 = qlCvPtkDAO.doSearch(lst);
		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream fileIn = new BufferedInputStream(
					new FileInputStream(filePath + "ImportCvPtk.xlsx"));
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
				cell1.setCellValue(obj1.get(j).getCnkv());

				XSSFCell cell2 = row.createCell(2);
				cell2.setCellValue(obj1.get(j).getTinh());

				XSSFCell cell3 = row.createCell(3);
				cell3.setCellValue(obj1.get(j).getMaTramTuyen());

				XSSFCell cell4 = row.createCell(4);
				cell4.setCellValue(obj1.get(j).getSoHd());

				XSSFCell cell5 = row.createCell(5);
				cell5.setCellValue(obj1.get(j).getThuocDmTt());
				
				XSSFCell cell6 = row.createCell(6);
				cell6.setCellValue(obj1.get(j).getLoaiCt());
				

				XSSFCell cell7 = row.createCell(7);
				cell7.setCellValue(obj1.get(j).getNoiDung());

				XSSFCell cell8= row.createCell(8);
				if (obj1.get(j).getNgayGuiHshc() != null) {
					cell8.setCellValue(dt.format(obj1.get(j).getNgayGuiHshc()));
				} else {
					cell8.setCellValue("");
				}

				XSSFCell cell9 = row.createCell(9);
				cell9.setCellValue(obj1.get(j).getSoBill());

				XSSFCell cell10 = row.createCell(10);
				cell10.setCellValue(obj1.get(j).getGhiChu());
				

				XSSFCell cell11 = row.createCell(11);
				cell11.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getGtSlHtTinh() == null || obj1.get(j).getGtSlHtTinh() == (float) 0) {
					obj1.get(j).setGtSlHtTinh((float) 0);
					cell11.setCellValue(obj1.get(j).getGtSlHtTinh());
				} else {
					cell11.setCellValue(obj1.get(j).getGtSlHtTinh());
				}

				XSSFCell cell12 = row.createCell(12);
				cell12.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getGtDnQtkCn() == null || obj1.get(j).getGtDnQtkCn() == (float) 0) {
					obj1.get(j).setGtDnQtkCn((float) 0);
					cell12.setCellValue(obj1.get(j).getGtDnQtkCn());
				} else {
					cell12.setCellValue(obj1.get(j).getGtDnQtkCn());
				}

				XSSFCell cell13= row.createCell(13);
				if (obj1.get(j).getNgayDnQtk() != null) {
					cell13.setCellValue(dt.format(obj1.get(j).getNgayDnQtk()));
				} else {
					cell13.setCellValue("");
				}

				XSSFCell cell14= row.createCell(14);
				if (obj1.get(j).getNgayNhanGuiPtkHshc() != null) {
					cell14.setCellValue(dt.format(obj1.get(j).getNgayNhanGuiPtkHshc()));
				} else {
					cell14.setCellValue("");
				}

				XSSFCell cell15 = row.createCell(15);
				cell15.setCellValue(obj1.get(j).getTinhTrangChuyenUngTu());
				
				XSSFCell cell16 = row.createCell(16);
				cell16.setCellValue(obj1.get(j).getGhiChuHsLoi());
				
				XSSFCell cell17= row.createCell(17);
				if (obj1.get(j).getNgayTuongUngTtcut() != null) {
					cell17.setCellValue(dt.format(obj1.get(j).getNgayTuongUngTtcut()));
				} else {
					cell17.setCellValue("");
				}
				
				XSSFCell cell18 = row.createCell(18);
				cell18.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getGtPtkTdXongQtk() == null || obj1.get(j).getGtPtkTdXongQtk() == (float) 0) {
					obj1.get(j).setGtPtkTdXongQtk((float) 0);
					cell18.setCellValue(obj1.get(j).getGtPtkTdXongQtk());
				} else {
					cell18.setCellValue(obj1.get(j).getGtPtkTdXongQtk());
				}

				XSSFCell cell19= row.createCell(19);
				if (obj1.get(j).getNgayPtkTdXongQtk() != null) {
					cell19.setCellValue(dt.format(obj1.get(j).getNgayPtkTdXongQtk()));
				} else {
					cell19.setCellValue("");
				}

				XSSFCell cell20 = row.createCell(20);
				cell20.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getGtDnQtCdt() == null || obj1.get(j).getGtDnQtCdt() == (float) 0) {
					obj1.get(j).setGtDnQtCdt((float) 0);
					cell20.setCellValue(obj1.get(j).getGtDnQtCdt());
				} else {
					cell20.setCellValue(obj1.get(j).getGtDnQtCdt());
				}

				XSSFCell cell21 = row.createCell(21);
				cell21.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getGtChotQtCdt() == null || obj1.get(j).getGtChotQtCdt() == (float) 0) {
					obj1.get(j).setGtChotQtCdt((float) 0);
					cell21.setCellValue(obj1.get(j).getGtChotQtCdt());
				} else {
					cell21.setCellValue(obj1.get(j).getGtChotQtCdt());
				}

				XSSFCell cell22= row.createCell(22);
				if (obj1.get(j).getNgayGtChotQtCdt() != null) {
					cell22.setCellValue(dt.format(obj1.get(j).getNgayGtChotQtCdt()));
				} else {
					cell22.setCellValue("");
				}
				
				XSSFCell cell23 = row.createCell(23);
				cell23.setCellValue(obj1.get(j).getGhiChuDn());

				XSSFCell cell24 = row.createCell(24);
				cell24.setCellValue(obj1.get(j).getThangQtk());
				
				XSSFCell cell25 = row.createCell(25);
				cell25.setCellValue(obj1.get(j).getThangQtkCdt());

				XSSFCell cell26= row.createCell(26);
				cell26.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (obj1.get(j).getTyLe() == null || obj1.get(j).getTyLe() == (float) 0) {
					obj1.get(j).setTyLe((float) 0);
					cell26.setCellValue(obj1.get(j).getTyLe());
				} else {
					cell26.setCellValue(obj1.get(j).getTyLe());
				}

				XSSFCell cell27 = row.createCell(27);
				cell27.setCellValue(obj1.get(j).getHsTonQua35n());
				
				XSSFCell cell28 = row.createCell(28);
				cell28.setCellValue(obj1.get(j).getGtQhChuaXl());
				
				XSSFCell cell29 = row.createCell(29);
				cell29.setCellValue(obj1.get(j).getLuuTaiKho());
				
				XSSFCell cell30 = row.createCell(30);
				cell30.setCellValue(obj1.get(j).getNguoiDuyetCdt());
				
				XSSFCell cell31 = row.createCell(31);
				cell31.setCellValue(obj1.get(j).getNguoiDuyetPtk());
				
			
				

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
				cell11.setCellStyle(styleNumber);
				cell12.setCellStyle(styleNumber);
				cell13.setCellStyle(style);
				cell14.setCellStyle(style);
				cell15.setCellStyle(style);
				cell16.setCellStyle(style);
				cell17.setCellStyle(style);
				cell18.setCellStyle(styleNumber);
				cell19.setCellStyle(style);
				cell20.setCellStyle(styleNumber);
				cell21.setCellStyle(styleNumber);
				cell22.setCellStyle(style);
				cell23.setCellStyle(style);
				cell24.setCellStyle(style);
				cell25.setCellStyle(style);
				cell26.setCellStyle(styleNumber);
				cell27.setCellStyle(style);
				cell28.setCellStyle(style);
				cell29.setCellStyle(style);
				cell30.setCellStyle(style);
				cell31.setCellStyle(style);
				
				
			}

			File out = new File(
					folder2Upload + File.separatorChar + "BaoCaoTongHopCongViecPTK" + startExportTime + ".xlsx");

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
		return UEncrypt.encryptFileUploadPath("BaoCaoTongHopCongViecPTK" + startExportTime + ".xlsx");
	}
	
	public String xuatBCTHCV(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		List<QlCvPtkDTO> lstShd = qlCvPtkDAO.getDistSoHd();
		List<QlCvPtkDTO> lstObj = new ArrayList<>();
		// for(QlCvPtkDTO dto:lstShd){
		//
		// }
		for (int i = 0; i < lstShd.size(); i++) {
			QlCvPtkDTO obj = new QlCvPtkDTO();
//			QlCvPtkDTO obj1 = new QlCvPtkDTO();
			List<QlCvPtkDTO> lst = new ArrayList<>();
			obj.setSoHd(lstShd.get(i).getSoHd());
			lst = qlCvPtkDAO.doSearch(obj);
			// Tính toán sử dụng lamba java 8(có thể dùng sum,count trong sql
			// thay thế)
			long sldndv = lst.stream().filter(x -> x.getGtSlHtTinh() != null).collect(Collectors.counting());// số
																												// lượng
																												// đã
																												// nhận
																												// về
			float gtttdv = lst.stream().filter(e -> e.getGtSlHtTinh() != null).map(e -> e.getGtSlHtTinh())
					.reduce((float) 0, Float::sum);// giá trị tạm tính đã nhận
													// về
			long sldnqlk = lst.stream().filter(x -> x.getGtDnQtkCn() != null).collect(Collectors.counting());// số
																												// lượng
																												// đã
																												// nhận
																												// QTK
			float gtttqlk = lst.stream().filter(e -> e.getGtDnQtkCn() != null).map(e -> e.getGtDnQtkCn())
					.reduce((float) 0, Float::sum);// giá trị tạm tính đã nhận
													// QTK
			long sltdqlk = lst.stream().filter(x -> x.getGtPtkTdXongQtk() != null).collect(Collectors.counting());// số
																													// lượng
																													// PTK
																													// thẩm
																													// định
																													// xong
			float gttdttqlk = lst.stream().filter(e -> e.getGtPtkTdXongQtk() != null).map(e -> e.getGtPtkTdXongQtk())
					.reduce((float) 0, Float::sum);// giá trị PTK thẩm định xong
			long slDaNhapDn = lst.stream().filter(x -> x.getGtDnQtCdt() != null).collect(Collectors.counting());// số
																												// lượng
																												// đề
																												// nghị
																												// qt
																												// cới
																												// cđt
			float gtDaNhapDn = lst.stream().filter(e -> e.getGtDnQtCdt() != null).map(e -> e.getGtDnQtCdt())
					.reduce((float) 0, Float::sum);// giá trị đề nghị qt cới cđt
			long slDaChotCDT = lst.stream().filter(x -> x.getGtChotQtCdt() != null).collect(Collectors.counting());// số
																													// lượng
																													// đã
																													// chốt
																													// cới
																													// cđt
			float gtDaChotCDT = lst.stream().filter(e -> e.getGtChotQtCdt() != null).map(e -> e.getGtChotQtCdt())
					.reduce((float) 0, Float::sum);// giá trị đã chốt qt cới cđt
			// nếu dùng sql thì có thể dùng case when
			long slTon = sldndv - slDaChotCDT;
			float gtTonTamTinh = gtttdv - gtDaChotCDT;
			//
			obj.setLoaiCt(lst.get(0).getLoaiCt());
			obj.setSldndv(sldndv);
			obj.setGtSlHtTinh(gtttdv);
			obj.setSldnqlk(sldnqlk);
			obj.setGtDnQtkCn(gtttqlk);
			obj.setSltdqlk(sltdqlk);
			obj.setGtPtkTdXongQtk(gttdttqlk);
			obj.setSlDaNhapDn(slDaNhapDn);
			obj.setGtDnQtCdt(gtDaNhapDn);
			obj.setSlDaChotCDT(slDaChotCDT);
			obj.setGtChotQtCdt(gtDaChotCDT);
			obj.setSlTon(slTon);
			obj.setGtTonTamTinh(gtTonTamTinh);
			lstObj.add(obj);
		}

		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream file = new BufferedInputStream(new FileInputStream(filePath + "baoCaoTongHopCVPTK.xlsx"));

			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);
			DataFormat format = workbook.createDataFormat();
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
			styleNumber.setDataFormat(format.getFormat("#,###"));
			styleNumber.setBorderBottom(BorderStyle.THIN);
			styleNumber.setBorderTop(BorderStyle.THIN);
			styleNumber.setBorderRight(BorderStyle.THIN);
			styleNumber.setBorderLeft(BorderStyle.THIN);
			styleNumber.setWrapText(true);
			styleNumber.setAlignment(CellStyle.ALIGN_RIGHT);

			XSSFRow row = null;

			int rownum = 4;
			// định dạng công thưc tính cho excel
			String strFormulaC = "SUBTOTAL(9," + "C" + 5 + ":C" + (lstObj.size() + 4) + ")";
			String strFormulaD = "SUBTOTAL(9," + "D" + 5 + ":D" + (lstObj.size() + 4) + ")";
			String strFormulaE = "SUBTOTAL(9," + "E" + 5 + ":E" + (lstObj.size() + 4) + ")";
			String strFormulaF = "SUBTOTAL(9," + "F" + 5 + ":F" + (lstObj.size() + 4) + ")";
			String strFormulaG = "SUBTOTAL(9," + "G" + 5 + ":G" + (lstObj.size() + 4) + ")";
			String strFormulaH = "SUBTOTAL(9," + "H" + 5 + ":H" + (lstObj.size() + 4) + ")";
			String strFormulaI = "SUBTOTAL(9," + "I" + 5 + ":I" + (lstObj.size() + 4) + ")";
			String strFormulaJ = "SUBTOTAL(9," + "J" + 5 + ":J" + (lstObj.size() + 4) + ")";
			String strFormulaK = "SUBTOTAL(9," + "K" + 5 + ":K" + (lstObj.size() + 4) + ")";
			String strFormulaL = "SUBTOTAL(9," + "L" + 5 + ":L" + (lstObj.size() + 4) + ")";
			String strFormulaM = "SUBTOTAL(9," + "M" + 5 + ":M" + (lstObj.size() + 4) + ")";
			String strFormulaN = "SUBTOTAL(9," + "N" + 5 + ":N" + (lstObj.size() + 4) + ")";
			String strFormulaO = "SUBTOTAL(9," + "O" + 5 + ":O" + (lstObj.size() + 4) + ")";
			String strFormulaP = "SUBTOTAL(9," + "P" + 5 + ":P" + (lstObj.size() + 4) + ")";
			for (int i = 0; i < lstObj.size(); i++) {
				row = sheet.createRow(rownum);
				XSSFCell cell0 = row.createCell(0);
				cell0.setCellValue(i + 1);
				cell0.setCellStyle(style);
				rownum++;

				XSSFCell cell1 = row.createCell(1);
				cell1.setCellValue(lstObj.get(i).getSoHd());

				XSSFCell cell2 = row.createCell(2);
				cell2.setCellType(HSSFCell.CELL_TYPE_NUMERIC);// set kiểu cho ô
				cell2.setCellValue("");

				XSSFCell cell3 = row.createCell(3);
				cell3.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				cell3.setCellValue("");

				XSSFCell cell4 = row.createCell(4);
				cell4.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (lstObj.get(i).getSldndv() != null && lstObj.get(i).getSldndv() != 0l) {
					cell4.setCellValue(lstObj.get(i).getSldndv());
				} else {
					cell4.setCellValue("0");
				}

				XSSFCell cell5 = row.createCell(5);
				cell5.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (lstObj.get(i).getGtSlHtTinh() != null && lstObj.get(i).getGtSlHtTinh() != (float) 0) {
					cell5.setCellValue(lstObj.get(i).getGtSlHtTinh());
				} else {
					cell5.setCellValue("0");
				}

				XSSFCell cell6 = row.createCell(6);
				cell6.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (lstObj.get(i).getSldnqlk() != null && lstObj.get(i).getSldnqlk() != 0l) {
					cell6.setCellValue(lstObj.get(i).getSldnqlk());
				} else {
					cell6.setCellValue("0");
				}

				XSSFCell cell7 = row.createCell(7);
				cell7.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (lstObj.get(i).getGtDnQtkCn() != null && lstObj.get(i).getGtDnQtkCn() != (float) 0) {
					cell7.setCellValue(lstObj.get(i).getGtDnQtkCn());
				} else {
					cell7.setCellValue("0");
				}

				XSSFCell cell8 = row.createCell(8);
				cell8.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (lstObj.get(i).getSltdqlk() != null && lstObj.get(i).getSltdqlk() != 0l) {
					cell8.setCellValue(lstObj.get(i).getSltdqlk());
				} else {
					cell8.setCellValue("0");
				}

				XSSFCell cell9 = row.createCell(9);
				cell9.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (lstObj.get(i).getGtPtkTdXongQtk() != null && lstObj.get(i).getGtPtkTdXongQtk() != (float) 0) {
					cell9.setCellValue(lstObj.get(i).getGtPtkTdXongQtk());
				} else {
					cell9.setCellValue("0");
				}

				XSSFCell cell10 = row.createCell(10);
				cell10.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (lstObj.get(i).getSlDaNhapDn() != null && lstObj.get(i).getSlDaNhapDn() != (float) 0) {
					cell10.setCellValue(lstObj.get(i).getSlDaNhapDn());
				} else {
					cell10.setCellValue("0");
				}

				XSSFCell cell11 = row.createCell(11);
				cell11.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (lstObj.get(i).getGtDnQtCdt() != null && lstObj.get(i).getGtDnQtCdt() != 0l) {
					cell11.setCellValue(lstObj.get(i).getGtDnQtCdt());
				} else {
					cell11.setCellValue("0");
				}

				XSSFCell cell12 = row.createCell(12);
				cell12.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (lstObj.get(i).getSlDaChotCDT() != null && lstObj.get(i).getSlDaChotCDT() != (float) 0) {
					cell12.setCellValue(lstObj.get(i).getSlDaChotCDT());
				} else {
					cell12.setCellValue("0");
				}

				XSSFCell cell13 = row.createCell(13);
				cell13.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (lstObj.get(i).getGtChotQtCdt() != null && lstObj.get(i).getGtChotQtCdt() != 0l) {
					cell13.setCellValue(lstObj.get(i).getGtChotQtCdt());
				} else {
					cell13.setCellValue("0");
				}

				XSSFCell cell14 = row.createCell(14);
				cell14.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (lstObj.get(i).getSlTon() != null && lstObj.get(i).getSlTon() != 0l) {
					cell14.setCellValue(lstObj.get(i).getSlTon());
				} else {
					cell14.setCellValue("0");
				}
				XSSFCell cell15 = row.createCell(15);
				cell15.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (lstObj.get(i).getGtTonTamTinh() != null && lstObj.get(i).getGtTonTamTinh() != (float) 0) {
					cell15.setCellValue(lstObj.get(i).getGtTonTamTinh());
				} else {
					cell15.setCellValue("0");
				}

				XSSFCell cell16 = row.createCell(16);
				if (lstObj.get(i).getLoaiCt() != null) {
					switch (lstObj.get(i).getLoaiCt()) {
					case "1":
						lstObj.get(i).setLoaiCt("BTS");
						break;
					case "2":
						lstObj.get(i).setLoaiCt("Ngầm");
						break;
					case "3":
						lstObj.get(i).setLoaiCt("Công văn lẻ");
						break;
					case "4":
						lstObj.get(i).setLoaiCt("Chi nhánh tỉnh");
						break;
					default:
						lstObj.get(i).setLoaiCt("");
						break;
					}
				} else {
					lstObj.get(i).setLoaiCt("");
				}

				cell16.setCellValue(lstObj.get(i).getLoaiCt());

				cell0.setCellStyle(style);
				cell1.setCellStyle(style);
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

			}
			row = sheet.createRow(3);
			XSSFCell cell00 = row.createCell(0);
			cell00.setCellValue("Tổng");

			XSSFCell cell01 = row.createCell(1);
			cell01.setCellValue("");

			XSSFCell cell02 = row.createCell(2);
			cell02.setCellFormula(strFormulaC);

			XSSFCell cell03 = row.createCell(3);
			cell03.setCellFormula(strFormulaD);

			XSSFCell cell04 = row.createCell(4);
			cell04.setCellFormula(strFormulaE);

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
			cell016.setCellValue("");

			cell00.setCellStyle(style);
			cell01.setCellStyle(style);
			cell02.setCellStyle(styleNumber);
			cell03.setCellStyle(styleNumber);
			cell04.setCellStyle(styleNumber);
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
			cell016.setCellStyle(style);
			file.close();

			File out = new File(folder2Upload + File.separatorChar + "baoCaoTongHopCVPTK" + startExportTime + ".xlsx");

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
		return UEncrypt.encryptFileUploadPath("baoCaoTongHopCVPTK" + startExportTime + ".xlsx");
	}

	public String xuatBCTheoDauViec(QlCvPtkDTO obj, HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		List<QlCvPtkDTO> lstObj = qlCvPtkDAO.getTinhTrang(obj);
		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream file = new BufferedInputStream(new FileInputStream(filePath + "baoCaoTheoDauMucCongViec.xlsx"));

			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);
			DataFormat format = workbook.createDataFormat();
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
			styleNumber.setDataFormat(format.getFormat("#,###"));
			styleNumber.setBorderBottom(BorderStyle.THIN);
			styleNumber.setBorderTop(BorderStyle.THIN);
			styleNumber.setBorderRight(BorderStyle.THIN);
			styleNumber.setBorderLeft(BorderStyle.THIN);
			styleNumber.setWrapText(true);
			styleNumber.setAlignment(CellStyle.ALIGN_RIGHT);

			XSSFRow row = null;

			int rownum = 3;
			String strFormulaE = "SUBTOTAL(9," + "E" + 4 + ":E" + (lstObj.size() + 3) + ")";
			String strFormulaF = "SUBTOTAL(9," + "F" + 4 + ":F" + (lstObj.size() + 3) + ")";
			String strFormulaG = "SUBTOTAL(9," + "G" + 4 + ":G" + (lstObj.size() + 3) + ")";
			String strFormulaH = "SUBTOTAL(9," + "H" + 4 + ":H" + (lstObj.size() + 3) + ")";
			for (int i = 0; i < lstObj.size(); i++) {
				row = sheet.createRow(rownum);
				XSSFCell cell0 = row.createCell(0);
				cell0.setCellValue(i + 1);
				cell0.setCellStyle(style);
				rownum++;

				XSSFCell cell1 = row.createCell(1);
				cell1.setCellValue(lstObj.get(i).getTinhTrangChuyenUngTu());

				XSSFCell cell2 = row.createCell(2);
				cell2.setCellValue(lstObj.get(i).getMaTramTuyen());

				XSSFCell cell3 = row.createCell(3);
				cell3.setCellValue(lstObj.get(i).getSoHd());

				XSSFCell cell4 = row.createCell(4);
				cell4.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (lstObj.get(i).getGtSlHtTinh() != null && lstObj.get(i).getGtSlHtTinh() != 0l) {
					cell4.setCellValue(lstObj.get(i).getGtSlHtTinh());
				} else {
					cell4.setCellValue("0");
				}

				XSSFCell cell5 = row.createCell(5);
				cell5.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (lstObj.get(i).getGtPtkTdXongQtk() != null && lstObj.get(i).getGtPtkTdXongQtk() != (float) 0) {
					cell5.setCellValue(lstObj.get(i).getGtPtkTdXongQtk());
				} else {
					cell5.setCellValue("0");
				}

				XSSFCell cell6 = row.createCell(6);
				cell6.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (lstObj.get(i).getGtDnQtCdt() != null && lstObj.get(i).getGtDnQtCdt() != 0l) {
					cell6.setCellValue(lstObj.get(i).getGtDnQtCdt());
				} else {
					cell6.setCellValue("0");
				}

				XSSFCell cell7 = row.createCell(7);
				cell7.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (lstObj.get(i).getGtChotQtCdt() != null && lstObj.get(i).getGtChotQtCdt() != (float) 0) {
					cell7.setCellValue(lstObj.get(i).getGtChotQtCdt());
				} else {
					cell7.setCellValue("0");
				}

				XSSFCell cell8 = row.createCell(8);
				cell8.setCellValue(lstObj.get(i).getCnkv());

				XSSFCell cell9 = row.createCell(9);
				cell9.setCellValue(lstObj.get(i).getGhiChuHsLoi());

				XSSFCell cell10 = row.createCell(10);
				if (lstObj.get(i).getNgayNhanGuiPtkHshc() != null) {
					cell10.setCellValue(dt.format(lstObj.get(i).getNgayNhanGuiPtkHshc()));
				} else {
					cell10.setCellValue("");
				}

				XSSFCell cell11 = row.createCell(11);
				if (lstObj.get(i).getNgayTuongUngTtcut() != null) {
					cell11.setCellValue(dt.format(lstObj.get(i).getNgayTuongUngTtcut()));
				} else {
					cell11.setCellValue("");
				}

				XSSFCell cell12 = row.createCell(12);
				cell12.setCellValue(lstObj.get(i).getNguoiDuyetPtk());

				cell0.setCellStyle(style);
				cell1.setCellStyle(style);
				cell2.setCellStyle(style);
				cell3.setCellStyle(styleNumber);
				cell4.setCellStyle(styleNumber);
				cell5.setCellStyle(styleNumber);
				cell6.setCellStyle(styleNumber);
				cell7.setCellStyle(style);
				cell8.setCellStyle(style);
				cell9.setCellStyle(style);
				cell10.setCellStyle(style);
				cell11.setCellStyle(style);
				cell12.setCellStyle(style);

			}
			row = sheet.createRow(2);
			XSSFCell cell00 = row.createCell(0);
			cell00.setCellValue("Tổng");

			XSSFCell cell01 = row.createCell(1);
			cell01.setCellValue("");

			XSSFCell cell02 = row.createCell(2);
			cell02.setCellValue("");

			XSSFCell cell03 = row.createCell(3);
			cell03.setCellValue("");

			XSSFCell cell04 = row.createCell(4);
			cell04.setCellFormula(strFormulaE);

			XSSFCell cell05 = row.createCell(5);
			cell05.setCellFormula(strFormulaF);

			XSSFCell cell06 = row.createCell(6);
			cell06.setCellFormula(strFormulaG);

			XSSFCell cell07 = row.createCell(7);
			cell07.setCellFormula(strFormulaH);

			XSSFCell cell08 = row.createCell(8);
			cell08.setCellValue("");

			XSSFCell cell09 = row.createCell(9);
			cell09.setCellValue("");

			XSSFCell cell010 = row.createCell(10);
			cell010.setCellValue("");

			XSSFCell cell011 = row.createCell(11);
			cell011.setCellValue("");

			XSSFCell cell012 = row.createCell(12);
			cell012.setCellValue("");

			cell00.setCellStyle(style);
			cell01.setCellStyle(style);
			cell02.setCellStyle(style);
			cell03.setCellStyle(style);
			cell04.setCellStyle(styleNumber);
			cell05.setCellStyle(styleNumber);
			cell06.setCellStyle(styleNumber);
			cell07.setCellStyle(styleNumber);
			cell08.setCellStyle(style);
			cell09.setCellStyle(style);
			cell010.setCellStyle(style);
			cell011.setCellStyle(style);
			cell012.setCellStyle(style);
			file.close();

			File out = new File(
					folder2Upload + File.separatorChar + "baoCaoTheoDauMucCongViec" + startExportTime + ".xlsx");

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
		return UEncrypt.encryptFileUploadPath("baoCaoTheoDauMucCongViec" + startExportTime + ".xlsx");
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
}
