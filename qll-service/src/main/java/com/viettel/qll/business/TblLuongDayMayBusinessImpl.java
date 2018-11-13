package com.viettel.qll.business;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.BorderStyle;
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

import com.viettel.ktts2.common.UEncrypt;
import com.viettel.qll.bo.TblLuongDayMayBO;
import com.viettel.qll.dao.TblLuongDayMayDAO;
import com.viettel.qll.dto.ErrExcelDTO;
import com.viettel.qll.dto.LsThaoTacDTO;
import com.viettel.qll.dto.TblCauHinhTienDTO;
import com.viettel.qll.dto.TblDlDauVaoDayMayDTO;
import com.viettel.qll.dto.TblDmKiDonViDTO;
import com.viettel.qll.dto.TblDmThongTinLoiDayMayDTO;
import com.viettel.qll.dto.TblDonGiaThueBaoDTO;
import com.viettel.qll.dto.TblLoiDongAoDTO;
import com.viettel.qll.dto.TblLoiLapLaiDTO;
import com.viettel.qll.dto.TblLuongDayMayDTO;
import com.viettel.qll.dto.TblNgayCongDTO;
import com.viettel.qll.dto.TblPhatCdtDTO;
import com.viettel.qll.dto.TblPhatFcDTO;
import com.viettel.qll.dto.TblPhatPakhDTO;
import com.viettel.qll.dto.TblPhatRoiMangDTO;
import com.viettel.qll.dto.TblPhatXuLySuCoDTO;
import com.viettel.qll.dto.TblPhiBanHangDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;

@Service("tblLuongDayMayBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TblLuongDayMayBusinessImpl extends
		BaseFWBusinessImpl<TblLuongDayMayDAO, TblLuongDayMayDTO, TblLuongDayMayBO> implements TblLuongDayMayBusiness {

	protected final Logger log = Logger.getLogger(TblLuongDayMayBusinessImpl.class);
	@Autowired
	private TblLuongDayMayDAO tblLuongDayMayDAO;

	@Value("${folder_upload2}")
	private String folder2Upload;

	@Value("${folder_upload}")
	private String folderTemp;

	List<ErrExcelDTO> lstErrorExcell;

	LsThaoTacDTO lsThaoTacDTO = new LsThaoTacDTO();
	@Autowired
	TblQltsThucXuatTheoKyBusinessImpl businessImpl = new TblQltsThucXuatTheoKyBusinessImpl();

	public TblLuongDayMayBusinessImpl() {
		tModel = new TblLuongDayMayBO();
		tDAO = tblLuongDayMayDAO;
	}

	@Override
	public TblLuongDayMayDAO gettDAO() {
		return tblLuongDayMayDAO;
	}
	
	
	@Override
	public DataListDTO doSearch(TblLuongDayMayDTO obj) {
		List<TblLuongDayMayDTO> ls = tblLuongDayMayDAO.doSearch(obj);
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		data.setTotal(obj.getTotalRecord());
		data.setSize(obj.getTotalRecord());
		data.setStart(1);
		return data;
	}

	@SuppressWarnings("unused")
	public String tinhLuongNvDayMay(TblLuongDayMayDTO obj, HttpServletRequest request) throws Exception {
		long tStart = System.currentTimeMillis();
		List<TblDlDauVaoDayMayDTO> lstNvDayMayBegin = tblLuongDayMayDAO.getListNvDayMayByThang(obj.getThang());
		// List<TblDanhMucDTO> lstDanhMuc = new ArrayList<>();
		final float tyLe = 1l;
		int hsdc = 1;
		String thang, tinh, huyen, maNv = null, hoTen;
		float kiDonVi = 0, heSoDieuChinh, soDayThueBao, donGia, ngayCongTinhLuong, ngayCongCheDo;
		float luong, phiBanHang;
		float tkm0_2, tkm3, tkm4, tkmHon5;
		float tkmCoSan0_2, tkmCoSan3, tkmCoSan4, tkmCoSanHon5;
		float phatXlsc, phatPakh, phatQuaHanRutPort = 0, phatDongHeThong = 0, phatRutPortVatLy = 0,
				phatQuaHanCamPort = 0, phatCamPortNhungKhongDongViec = 0;
		float phatCamPortHeThongNhungChuaCamPortVatLy = 0, phatTbTamNgungNhungKhongRutPort = 0, phatSuCoLapLaiCdbr = 0,
				phatSuCoLapLaiKenhTruyen = 0;
		float phatSdDauFc, phatLoiDongAo = 0;
		float phatRoiMang = 0;
		float phatQcxlsc = 0, phatLdsvtXlsct = 0, phatGttbrm = 0, phatTbcdcnktd = 0, phatTbrmktd = 0, phatLyttd = 0;
		float luongCuoiCung;

		ArrayList<Float> zzz = new ArrayList<Float>();
		List<ErrExcelDTO> lstErrorOrder = new ArrayList<>();
		TblDlDauVaoDayMayDTO lstDTO = new TblDlDauVaoDayMayDTO();

		List<TblLuongDayMayDTO> lstDone = new ArrayList<>();

		final List<TblDlDauVaoDayMayDTO> lstNvDayMay = lstNvDayMayBegin;

		TblLuongDayMayDTO luongNvDone = new TblLuongDayMayDTO();

		ErrExcelDTO err = new ErrExcelDTO();
		// tung nhan vien 1
		// step 1: get list nv

		int countLuong = 0;

		if (lstNvDayMayBegin.size() > 0) {
			for (TblDlDauVaoDayMayDTO lst : lstNvDayMay) {
				try {
					thang = obj.getThang();
					tinh = lst.getDonVi();
					huyen = lst.getTenHuyen();
					maNv = lst.getMaNv();
					hoTen = lst.getHoVaTen();
					if (!tinh.isEmpty() && !obj.getThang().isEmpty()) {
						List<TblDmKiDonViDTO> lstKiDv = tblLuongDayMayDAO.getKiDonViByTinhAndThang(tinh,
								obj.getThang());
						if (lstKiDv.size() > 0) {
							kiDonVi = lstKiDv.get(0).getKiDonVi() / 100;
						} else {
							kiDonVi = 0;
						}
					}
					heSoDieuChinh = (tyLe * kiDonVi);
					// if (hsdc == 1) {
					// heSoDieuChinh = (tyLe * kiDonVi);
					// } else {
					// heSoDieuChinh = 1;
					// }
					// get so day thue bao
					soDayThueBao = lst.getDdtbqlTongQuyDoi();

					// get don gia
					List<TblDonGiaThueBaoDTO> lstDonGia = tblLuongDayMayDAO.getDonGiaByTinh(tinh);
					
					if(lstDonGia.size()>0){
						donGia=lstDonGia.get(0).getDonGiaMoi();
					}else{
						donGia=0;
					}
					// get ngay cong tinh luong va ngay cong che do
					List<TblNgayCongDTO> lstNgayCong = tblLuongDayMayDAO.getNgayCongByMaNvAndThang(maNv, thang);
					ngayCongTinhLuong = lstNgayCong.get(0).getNgayCongTinhLuong();
					ngayCongCheDo = lstNgayCong.get(0).getNgayCongCheDo();

					// tinh luong co ban
					luong = (float) (donGia * soDayThueBao * (0.8 + 0.2 * kiDonVi) * heSoDieuChinh
							* (ngayCongTinhLuong / ngayCongCheDo));

					List<TblPhiBanHangDTO> lstPhiBh = tblLuongDayMayDAO.getPhiBanHangByMaNvAndThang(maNv, thang);
					if (lstPhiBh.size() > 0) {
						phiBanHang = lstPhiBh.get(0).getPhiTruocThue();
					} else {
						phiBanHang = 0;
					}

					List<TblCauHinhTienDTO> lstluongdm=tblLuongDayMayDAO.getTienByLoaiCvVaNgay("TKM", "0_2");
					if(lstluongdm.size()>0){
						tkm0_2 = lstluongdm.get(0).getMucTien()* lst.getTbmtbdv02();
					}else{
						tkm0_2=0;
					}
					List<TblCauHinhTienDTO> lstluongdm1=tblLuongDayMayDAO.getTienByLoaiCvVaNgay("TKM", "3");
					if(lstluongdm1.size()>0){
						tkm3 = lstluongdm1.get(0).getMucTien() * lst.getTbmtbdv3();
					}else{
						tkm3=0;
					}
					List<TblCauHinhTienDTO> lstluongdm2=tblLuongDayMayDAO.getTienByLoaiCvVaNgay("TKM", "4");
					if(lstluongdm2.size()>0){
						tkm4 = lstluongdm2.get(0).getMucTien() * lst.getTbmtbdv4();
					}else{
						tkm4=0;
					}
					List<TblCauHinhTienDTO> lstluongdm3=tblLuongDayMayDAO.getTienByLoaiCvVaNgay("TKM", "5");
					if(lstluongdm3.size()>0){
						tkmHon5 = lstluongdm3.get(0).getMucTien()* (lst.getTbmtbdv5() + lst.getTbmtbdv6() + lst.getTbmtbdv7());
					}else{
						tkmHon5=0;
					}
					

					// get du lieu trien khai moi co san (loai cv la TKS)
					List<TblCauHinhTienDTO> lstluongdm4=tblLuongDayMayDAO.getTienByLoaiCvVaNgay("TKS", "0_2");
					if(lstluongdm4.size()>0){
						tkmCoSan0_2 = lstluongdm4.get(0).getMucTien()* (lst.getTbmtbcs02() + lst.getTbmtbwf02());
					}else{
						tkmCoSan0_2=0;
					}
					List<TblCauHinhTienDTO> lstluongdm5=tblLuongDayMayDAO.getTienByLoaiCvVaNgay("TKS", "3");
					if(lstluongdm4.size()>0){
						tkmCoSan3 = lstluongdm5.get(0).getMucTien()
								* (lst.getTbmtbcs3() + lst.getTbmtbwf3());
					}else{
						tkmCoSan3=0;
					}
					List<TblCauHinhTienDTO> lstluongdm6=tblLuongDayMayDAO.getTienByLoaiCvVaNgay("TKS", "4");
					if(lstluongdm6.size()>0){
						tkmCoSan4 = lstluongdm6.get(0).getMucTien()
								* (lst.getTbmtbcs4() + lst.getTbmtbwf4());
					}else{
						tkmCoSan4=0;
					}
					List<TblCauHinhTienDTO> lstluongdm7=tblLuongDayMayDAO.getTienByLoaiCvVaNgay("TKS", "5");
					if(lstluongdm7.size()>0){
						tkmCoSanHon5 = lstluongdm7.get(0).getMucTien()
								* (lst.getTbmtbcs5() + lst.getTbmtbwf5() + lst.getTbmtbcs6() + lst.getTbmtbwf6());
					}else{
						tkmCoSanHon5=0;
					}
					

					// get phat xlsc
					List<TblPhatXuLySuCoDTO> lstXlsc = tblLuongDayMayDAO.getPhatXlscByMaNvAndThang(maNv, thang);
					if (lstXlsc.size() > 0) {
						phatXlsc = lstXlsc.get(0).getTong();
					} else {
						phatXlsc = 0;
					}

					// get phat pakh
					List<TblPhatPakhDTO> lstPakh = tblLuongDayMayDAO.getPhatPakhByMaNvAndThang(maNv, thang);
					if (lstPakh.size() > 0) {
						phatPakh = lstPakh.get(0).getPhat();
					} else {
						phatPakh = 0;
					}

					List<TblDmThongTinLoiDayMayDTO> lstPhatKhac = tblLuongDayMayDAO
							.getPhatKhacByMaNvAndThangAndMaLoi(maNv, thang, "001");
					if (lstPhatKhac.size() > 0) {
						for (int i = 0; i < lstPhatKhac.size(); i++) {
							if (lstPhatKhac.get(i).getCuocPhatSinh() != null) {
								phatQuaHanRutPort = phatQuaHanRutPort + lstPhatKhac.get(i).getTienPhat()
										+ lstPhatKhac.get(i).getCuocPhatSinh();
							} else {
								phatQuaHanRutPort = phatQuaHanRutPort + lstPhatKhac.get(i).getTienPhat();
							}
						}
					} else {
						phatQuaHanRutPort = 0;
					}

					// get phat dong viec he thong(ma loi 002)
					List<TblDmThongTinLoiDayMayDTO> lstPhatKhac1 = tblLuongDayMayDAO
							.getPhatKhacByMaNvAndThangAndMaLoi(maNv, thang, "002");
					if (lstPhatKhac1.size() > 0) {
						for (int i = 0; i < lstPhatKhac1.size(); i++) {
							if (lstPhatKhac1.get(i).getCuocPhatSinh() != null) {
								phatDongHeThong = phatDongHeThong + lstPhatKhac1.get(i).getTienPhat()
										+ lstPhatKhac1.get(i).getCuocPhatSinh();
							} else {
								phatDongHeThong = phatDongHeThong + lstPhatKhac1.get(i).getTienPhat();
							}
						}
					} else {
						phatDongHeThong = 0;
					}
					// get phat rut port vat ly(ma loi 003)
					List<TblDmThongTinLoiDayMayDTO> lstPhatKhac2 = tblLuongDayMayDAO
							.getPhatKhacByMaNvAndThangAndMaLoi(maNv, thang, "003");
					if (lstPhatKhac2.size() > 0) {
						for (int i = 0; i < lstPhatKhac2.size(); i++) {
							if (lstPhatKhac2.get(i).getCuocPhatSinh() != null) {
								phatRutPortVatLy = phatRutPortVatLy + lstPhatKhac2.get(i).getTienPhat()
										+ lstPhatKhac2.get(i).getCuocPhatSinh();
							} else {
								phatRutPortVatLy = phatRutPortVatLy + lstPhatKhac2.get(i).getTienPhat();
							}
						}
					} else {
						phatRutPortVatLy = 0;
					}

					// get phat qua han cam port(ma loi 004)
					List<TblDmThongTinLoiDayMayDTO> lstPhatKhac3 = tblLuongDayMayDAO
							.getPhatKhacByMaNvAndThangAndMaLoi(maNv, thang, "004");
					if (lstPhatKhac3.size() > 0) {
						for (int i = 0; i < lstPhatKhac3.size(); i++) {
							if (lstPhatKhac3.get(i).getCuocPhatSinh() != null) {
								phatQuaHanCamPort = phatQuaHanCamPort + lstPhatKhac3.get(i).getTienPhat()
										+ lstPhatKhac3.get(i).getCuocPhatSinh();
							} else {
								phatQuaHanCamPort = phatQuaHanCamPort + lstPhatKhac3.get(i).getTienPhat();
							}

						}
					} else {
						phatQuaHanCamPort = 0;
					}

					// get phat da cam port nhung khong dong viec(ma loi 005)
					List<TblDmThongTinLoiDayMayDTO> lstPhatKhac4 = tblLuongDayMayDAO
							.getPhatKhacByMaNvAndThangAndMaLoi(maNv, thang, "005");
					if (lstPhatKhac4.size() > 0) {
						for (int i = 0; i < lstPhatKhac4.size(); i++) {
							if (lstPhatKhac4.get(i).getCuocPhatSinh() != null) {
								phatCamPortNhungKhongDongViec = phatCamPortNhungKhongDongViec
										+ lstPhatKhac4.get(i).getTienPhat() + lstPhatKhac4.get(i).getCuocPhatSinh();
							} else {
								phatCamPortNhungKhongDongViec = phatCamPortNhungKhongDongViec
										+ lstPhatKhac4.get(i).getTienPhat();
							}
						}
					} else {
						phatCamPortNhungKhongDongViec = 0;
					}

					// get phat dong viec cam port nhung chua cam port vat ly(ma
					// loi
					// 006)
					List<TblDmThongTinLoiDayMayDTO> lstPhatKhac5 = tblLuongDayMayDAO
							.getPhatKhacByMaNvAndThangAndMaLoi(maNv, thang, "006");
					if (lstPhatKhac5.size() > 0) {
						for (int i = 0; i < lstPhatKhac5.size(); i++) {
							if (lstPhatKhac5.get(i).getCuocPhatSinh() != null) {
								phatCamPortHeThongNhungChuaCamPortVatLy = phatCamPortHeThongNhungChuaCamPortVatLy
										+ lstPhatKhac5.get(i).getTienPhat() + lstPhatKhac5.get(i).getCuocPhatSinh();
							} else {
								phatCamPortHeThongNhungChuaCamPortVatLy = phatCamPortHeThongNhungChuaCamPortVatLy
										+ lstPhatKhac5.get(i).getTienPhat();
							}
						}
					} else {
						phatCamPortHeThongNhungChuaCamPortVatLy = 0;
					}
					// get phat thue bao tam ngung(ma loi 007)
					List<TblDmThongTinLoiDayMayDTO> lstPhatKhac6 = tblLuongDayMayDAO
							.getPhatKhacByMaNvAndThangAndMaLoi(maNv, thang, "007");
					if (lstPhatKhac6.size() > 0) {
						for (int i = 0; i < lstPhatKhac6.size(); i++) {
							if (lstPhatKhac6.get(i).getCuocPhatSinh() != null) {
								phatTbTamNgungNhungKhongRutPort = phatTbTamNgungNhungKhongRutPort
										+ lstPhatKhac6.get(i).getTienPhat() + lstPhatKhac6.get(i).getCuocPhatSinh();
							} else {
								phatTbTamNgungNhungKhongRutPort = phatTbTamNgungNhungKhongRutPort
										+ lstPhatKhac.get(i).getTienPhat();
							}
						}
					} else {
						phatTbTamNgungNhungKhongRutPort = 0;
					}

					// get phat loi lap lai CĐBR
					List<TblLoiLapLaiDTO> lstPhatLoiLapLai = tblLuongDayMayDAO
							.getPhatLoiLapLaiByMaNvAndThangAndLdv(maNv, thang, "CĐBR");
					if (lstPhatLoiLapLai.size() > 0) {
						for (int i = 0; i < lstPhatLoiLapLai.size(); i++) {
							phatSuCoLapLaiCdbr = phatSuCoLapLaiCdbr + lstPhatLoiLapLai.get(i).getPhat();
						}
					} else {
						phatSuCoLapLaiCdbr = 0;
					}

					// get phat loi lap lai Kênh truyền
					List<TblLoiLapLaiDTO> lstPhatLoiLapLai1 = tblLuongDayMayDAO
							.getPhatLoiLapLaiByMaNvAndThangAndLdv(maNv, thang, "Kênh truyền");
					if (lstPhatLoiLapLai1.size() > 0) {
						for (int i = 0; i < lstPhatLoiLapLai.size(); i++) {
							phatSuCoLapLaiKenhTruyen = phatSuCoLapLaiKenhTruyen + lstPhatLoiLapLai1.get(i).getPhat();
						}
					} else {
						phatSuCoLapLaiKenhTruyen = 0;
					}

					// get phat FC
					List<TblPhatFcDTO> lstPhatFc = tblLuongDayMayDAO.getPhatFcByMaNvAndThang(maNv, thang);
					if (lstPhatFc.size() > 0) {
						phatSdDauFc = lstPhatFc.get(0).getStpttag();
					} else {
						phatSdDauFc = 0;
					}

					// get phat dong ao
					List<TblLoiDongAoDTO> lstPhatLoiDongAo = tblLuongDayMayDAO.getPhatDongAoByMaNvAndThang(maNv, thang);
					if (lstPhatLoiDongAo.size() > 0) {
						for (int i = 0; i < lstPhatLoiDongAo.size(); i++) {
							phatLoiDongAo = phatLoiDongAo + lstPhatLoiDongAo.get(i).getThanhTien();
						}
					} else {
						phatLoiDongAo = 0;
					}

					// get phat roi mang
					List<TblPhatRoiMangDTO> lstPhatRoiMang = tblLuongDayMayDAO.getPhatRoiMangByMaNvAndThang(maNv,
							thang);
					if (lstPhatRoiMang.size() > 0) {
						phatRoiMang = lstPhatRoiMang.get(0).getPhatSauThue();
					} else {
						phatRoiMang = 0;
					}

					// get phat chu dau tu - cdt
					List<TblPhatCdtDTO> lstPhatCdt = tblLuongDayMayDAO.getPhatCdtByMaNvAndThang(maNv, thang);
					if (lstPhatCdt.size() > 0) {
						// phatQcxlsc =
						// 0,phatLdsvtXlsct=0,phatGttbrm=0,phatTbcdcnktd=0,phatTbrmktd=0,phatLyttd=0;
						phatQcxlsc = lstPhatCdt.get(0).getPhatQcxlsc();
						phatLdsvtXlsct = lstPhatCdt.get(0).getPhatLdsvtXlsct();
						phatGttbrm = lstPhatCdt.get(0).getPhatGttbrm();
						phatTbcdcnktd = lstPhatCdt.get(0).getPhatTbcdcnktd();
						phatTbrmktd = lstPhatCdt.get(0).getPhatTbrmktd();
						phatLyttd = lstPhatCdt.get(0).getPhatLyttd();
					} else {
						phatQcxlsc = 0;
						phatLdsvtXlsct = 0;
						phatGttbrm = 0;
						phatTbcdcnktd = 0;
						phatTbrmktd = 0;
						phatLyttd = 0;
					}

					// tinh luong cuoi cung
					luongCuoiCung = luong + phiBanHang + tkm0_2 + tkm3 + tkm4 + tkmHon5 + tkmCoSan0_2 + tkmCoSan3
							+ tkmCoSan4 + tkmCoSanHon5 - phatXlsc - phatPakh - phatQuaHanRutPort - phatDongHeThong
							- phatRutPortVatLy - phatQuaHanCamPort - phatCamPortNhungKhongDongViec
							- phatCamPortHeThongNhungChuaCamPortVatLy - phatTbTamNgungNhungKhongRutPort
							- phatSuCoLapLaiCdbr - phatSuCoLapLaiKenhTruyen - phatSdDauFc - phatLoiDongAo - phatRoiMang
							- phatQcxlsc - phatLdsvtXlsct - phatGttbrm - phatTbcdcnktd - phatTbrmktd - phatLyttd;

					DecimalFormat dFormat = new DecimalFormat("#.00");
					System.out.println("$" + dFormat.format(luongCuoiCung));
					System.out.println("luong :" + luongCuoiCung);
					zzz.add(luongCuoiCung);

					luongNvDone = new TblLuongDayMayDTO();
					luongNvDone.setThang(thang);
					luongNvDone.setTinh(tinh);
					luongNvDone.setHuyen(huyen);
					luongNvDone.setMaNv(maNv);
					luongNvDone.setHoTen(hoTen);
					luongNvDone.setKiDonVi(kiDonVi * 100);
					luongNvDone.setHeSoDieuChinh(heSoDieuChinh);
					luongNvDone.setSoDayTbQuiDoi(soDayThueBao);
					luongNvDone.setDonGia(donGia);
					luongNvDone.setNgayCongTinhLuong(ngayCongTinhLuong);
					luongNvDone.setNgayCongCheDo(ngayCongCheDo);
					luongNvDone.setLuongDuyTri(luong);
					luongNvDone.setPhiBanHang(phiBanHang);
					luongNvDone.setTkm0(tkm0_2);
					luongNvDone.setTkm3(tkm3);
					luongNvDone.setTkm4(tkm4);
					luongNvDone.setTkm5(tkmHon5);
					luongNvDone.setTdv0(tkmCoSan0_2);
					luongNvDone.setTdv3(tkmCoSan3);
					luongNvDone.setTdv4(tkmCoSan4);
					luongNvDone.setTdv5(tkmCoSanHon5);
					luongNvDone.setPhatXlsc(phatXlsc);
					luongNvDone.setPhatPakh(phatPakh);
					luongNvDone.setLoi1(phatQuaHanRutPort);
					luongNvDone.setLoi2(phatDongHeThong);
					luongNvDone.setLoi3(phatRutPortVatLy);
					luongNvDone.setLoi4(phatQuaHanCamPort);
					luongNvDone.setLoi5(phatCamPortNhungKhongDongViec);
					luongNvDone.setLoi6(phatCamPortHeThongNhungChuaCamPortVatLy);
					luongNvDone.setLoi7(phatTbTamNgungNhungKhongRutPort);
					luongNvDone.setLoi8(phatSuCoLapLaiCdbr);
					luongNvDone.setLoi9(phatSuCoLapLaiKenhTruyen);
					luongNvDone.setLoi10(phatSdDauFc);
					luongNvDone.setLoi11(phatLoiDongAo);
					luongNvDone.setLoi12(phatRoiMang);
					luongNvDone.setLuong(luongCuoiCung);
					luongNvDone.setHoatDong((long) 1);
					luongNvDone.setXoa((long) 0);
					luongNvDone.setPhatQcxlsc(phatQcxlsc);
					luongNvDone.setPhatLdsvtXlsct(phatLdsvtXlsct);
					luongNvDone.setPhatGttbrm(phatGttbrm);
					luongNvDone.setPhatTbcdcnktd(phatTbcdcnktd);
					luongNvDone.setPhatTbrmktd(phatTbrmktd);
					luongNvDone.setPhatLyttd(phatLyttd);
					lstDone.add(luongNvDone);

				} catch (Exception e) {
					err = new ErrExcelDTO();
					err.setLineError(maNv);
					err.setDetailError(e.getClass().toString());
					lstErrorOrder.add(err);
				}

			}
			if (lstDone.size() > 0) {
				tblLuongDayMayDAO.deleteObj(obj.getThang());
				saveList(lstDone);

			}
			try {
				return exportLuongNvDayMayAndFault(lstDone, lstErrorOrder, request, tStart,obj.getThang());

			} catch (IllegalArgumentException ie) {
				throw new IllegalArgumentException("Lỗi rồi!!!");
			}
		} else {
			throw new IllegalArgumentException("Dữ liệu đầu vào dây máy " + obj.getThang() + " không có dữ liệu");
		}
	}

	public String exportLuongNvDayMayAndFault(List<TblLuongDayMayDTO> obj, List<ErrExcelDTO> fault,
			HttpServletRequest request, long tStart,String thang) throws Exception {
		// TODO Auto-generated method stub
		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			// ClassLoader classloader =
			// Thread.currentThread().getContextClassLoader();
			InputStream fileIn = new BufferedInputStream(new FileInputStream(filePath + "LuongNVDayMay.xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(fileIn);
			XSSFSheet sheet = workbook.getSheetAt(0);
			workbook.setSheetName(workbook.getSheetIndex(sheet), "Bang_Luong");

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
			int rownum = 9;
			for (int i = 0; i < obj.size(); i++) {
				StringBuilder err = new StringBuilder();
				XSSFRow row = sheet.createRow(rownum);
				XSSFCell cell0 = row.createCell(0);
				cell0.setCellValue(i + 1);
				rownum++;

				XSSFCell cell1 = row.createCell(1);
				cell1.setCellValue(obj.get(i).getThang());

				XSSFCell cell2 = row.createCell(2);
				cell2.setCellValue(obj.get(i).getTinh());

				XSSFCell cell3 = row.createCell(3);
				cell3.setCellValue(obj.get(i).getHuyen());

				XSSFCell cell4 = row.createCell(4);
				cell4.setCellValue(obj.get(i).getMaNv());

				XSSFCell cell5 = row.createCell(5);
				cell5.setCellValue(obj.get(i).getHoTen());

				XSSFCell cell6 = row.createCell(6);
				cell6.setCellValue(obj.get(i).getKiDonVi());

				XSSFCell cell7 = row.createCell(7);
				cell7.setCellValue(obj.get(i).getHeSoDieuChinh());

				XSSFCell cell8 = row.createCell(8);
				cell8.setCellValue(obj.get(i).getSoDayTbQuiDoi());

				XSSFCell cell9 = row.createCell(9);
				// cell9.setCellValue(dFormat.format(obj.get(i).getDonGia()));
				cell9.setCellValue(obj.get(i).getDonGia());

				XSSFCell cell10 = row.createCell(10);
				cell10.setCellValue(obj.get(i).getNgayCongTinhLuong());

				XSSFCell cell11 = row.createCell(11);
				cell11.setCellValue(obj.get(i).getNgayCongCheDo());

				XSSFCell cell12 = row.createCell(12);
				// cell12.setCellValue(dFormat.format(obj.get(i).getLuongDuyTri()));
				cell12.setCellValue(obj.get(i).getLuongDuyTri());

				XSSFCell cell13 = row.createCell(13);
				// cell13.setCellValue(dFormat.format(obj.get(i).getPhiBanHang()));
				cell13.setCellValue(obj.get(i).getPhiBanHang());

				XSSFCell cell14 = row.createCell(14);
				// cell14.setCellValue(dFormat.format(obj.get(i).getTkm0()));
				cell14.setCellValue(obj.get(i).getTkm0());

				XSSFCell cell15 = row.createCell(15);
				// cell15.setCellValue(dFormat.format(obj.get(i).getTkm3()));
				cell15.setCellValue(obj.get(i).getTkm3());

				XSSFCell cell16 = row.createCell(16);
				// cell16.setCellValue(dFormat.format(obj.get(i).getTkm4()));
				cell16.setCellValue(obj.get(i).getTkm4());

				XSSFCell cell17 = row.createCell(17);
				// cell17.setCellValue(dFormat.format(obj.get(i).getTkm5()));
				cell17.setCellValue(obj.get(i).getTkm5());

				XSSFCell cell18 = row.createCell(18);
				// cell18.setCellValue(dFormat.format(obj.get(i).getTdv0()));
				cell18.setCellValue(obj.get(i).getTdv0());

				XSSFCell cell19 = row.createCell(19);
				// cell19.setCellValue(dFormat.format(obj.get(i).getTdv3()));
				cell19.setCellValue(obj.get(i).getTdv3());

				XSSFCell cell20 = row.createCell(20);
				// cell20.setCellValue(dFormat.format(obj.get(i).getTdv4()));
				cell20.setCellValue(obj.get(i).getTdv4());

				XSSFCell cell21 = row.createCell(21);
				// cell21.setCellValue(dFormat.format(obj.get(i).getTdv5()));
				cell21.setCellValue(obj.get(i).getTdv5());

				XSSFCell cell22 = row.createCell(22);
				// cell22.setCellValue(dFormat.format(obj.get(i).getPhatXlsc()));
				cell22.setCellValue(obj.get(i).getPhatXlsc());

				XSSFCell cell23 = row.createCell(23);
				// cell23.setCellValue(dFormat.format(obj.get(i).getPhatPakh()));
				cell23.setCellValue(obj.get(i).getPhatPakh());

				XSSFCell cell24 = row.createCell(24);
				// cell24.setCellValue(dFormat.format(obj.get(i).getLoi1()));
				cell24.setCellValue(obj.get(i).getLoi1());

				XSSFCell cell25 = row.createCell(25);
				// cell25.setCellValue(dFormat.format(obj.get(i).getLoi2()));
				cell25.setCellValue(obj.get(i).getLoi2());

				XSSFCell cell26 = row.createCell(26);
				// cell26.setCellValue(dFormat.format(obj.get(i).getLoi3()));
				cell26.setCellValue(obj.get(i).getLoi3());

				XSSFCell cell27 = row.createCell(27);
				// cell27.setCellValue(dFormat.format(obj.get(i).getLoi4()));
				cell27.setCellValue(obj.get(i).getLoi4());

				XSSFCell cell28 = row.createCell(28);
				// cell28.setCellValue(dFormat.format(obj.get(i).getLoi5()));
				cell28.setCellValue(obj.get(i).getLoi5());

				XSSFCell cell29 = row.createCell(29);
				// cell29.setCellValue(dFormat.format(obj.get(i).getLoi7()));
				cell29.setCellValue(obj.get(i).getLoi7());

				XSSFCell cell30 = row.createCell(30);
				// cell30.setCellValue(dFormat.format(obj.get(i).getLoi6()));
				cell30.setCellValue(obj.get(i).getLoi6());

				XSSFCell cell31 = row.createCell(31);
				// cell31.setCellValue(dFormat.format(obj.get(i).getLoi8()));
				cell31.setCellValue(obj.get(i).getLoi8());

				XSSFCell cell32 = row.createCell(32);
				// cell32.setCellValue(dFormat.format(obj.get(i).getLoi9()));
				cell32.setCellValue(obj.get(i).getLoi9());

				XSSFCell cell33 = row.createCell(33);
				// cell33.setCellValue(dFormat.format(obj.get(i).getLoi10()));
				cell33.setCellValue(obj.get(i).getLoi10());

				XSSFCell cell34 = row.createCell(34);
				// cell34.setCellValue(dFormat.format(obj.get(i).getLoi11()));
				cell34.setCellValue(obj.get(i).getLoi11());

				XSSFCell cell35 = row.createCell(35);
				// cell35.setCellValue(dFormat.format(obj.get(i).getLoi12()));
				cell35.setCellValue(obj.get(i).getLoi12());

				// luongNvDone.set(phatQcxlsc);
				// luongNvDone.set(phatLdsvtXlsct);
				// luongNvDone.set(phatGttbrm);
				// luongNvDone.set(phatTbcdcnktd);
				// luongNvDone.set(phatTbrmktd);
				// luongNvDone.set(phatLyttd);
				XSSFCell cell36 = row.createCell(36);
				// cell36.setCellValue(dFormat.format(obj.get(i).getPhatQcxlsc()));
				cell36.setCellValue(obj.get(i).getPhatQcxlsc());

				XSSFCell cell37 = row.createCell(37);
				// cell37.setCellValue(dFormat.format(obj.get(i).getPhatLdsvtXlsct()));
				cell37.setCellValue(obj.get(i).getPhatLdsvtXlsct());

				XSSFCell cell38 = row.createCell(38);
				// cell38.setCellValue(dFormat.format(obj.get(i).getPhatGttbrm()));
				cell38.setCellValue(obj.get(i).getPhatGttbrm());

				XSSFCell cell39 = row.createCell(39);
				// cell39.setCellValue(dFormat.format(obj.get(i).getPhatTbcdcnktd()));
				cell39.setCellValue(obj.get(i).getPhatTbcdcnktd());

				XSSFCell cell40 = row.createCell(40);
				// cell40.setCellValue(dFormat.format(obj.get(i).getPhatTbrmktd()));
				cell40.setCellValue(obj.get(i).getPhatTbrmktd());

				XSSFCell cell41 = row.createCell(41);
				// cell41.setCellValue(dFormat.format(obj.get(i).getPhatLyttd()));
				cell41.setCellValue(obj.get(i).getPhatLyttd());

				XSSFCell cell42 = row.createCell(42);
				// cell42.setCellValue(dFormat.format(obj.get(i).getLuong()));
				cell42.setCellValue(Math.round(obj.get(i).getLuong()));

				// XSSFCell cell36 = row.createCell(36);
				// cell36.setCellValue(obj.get(i).getMaNv());
				// set style for each cell
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
			}

			fileIn.close();

			File out = new File(folder2Upload + File.separatorChar + "LuongNVDayMay" + startExportTime + ".xlsx");

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
		long tEnd = System.currentTimeMillis();
		lsThaoTacDTO.setChucNang("Tính lương nhân viên dây máy");
		lsThaoTacDTO.setMoTa("Tính xong lương "+obj.size() +"bản ghi của nhân viên dây máy " + thang);
		lsThaoTacDTO.setThoiGianThucHien(tEnd - tStart);
		businessImpl.insertLSTT(lsThaoTacDTO, request);
		return UEncrypt.encryptFileUploadPath("LuongNVDayMay" + startExportTime + ".xlsx");
	}

}
