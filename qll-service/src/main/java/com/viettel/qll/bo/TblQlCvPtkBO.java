package com.viettel.qll.bo;

import com.viettel.qll.dto.TblQlCvPtkDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.erp.bo.TblQlCvPtkBO")
@Table(name = "TBL_QL_CV_PTK")
/**
 *
 * @author: hailh10
 */
public class TblQlCvPtkBO extends BaseFWModelImpl {
     
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "TBL_QL_CV_PTK_SEQ") })
	@Column(name = "TBL_QL_CV_PTK_ID", length = 22)
	private java.lang.Long tblQlCvPtkId;
	@Column(name = "CNKV", length = 2000)
	private java.lang.String cnkv;
	@Column(name = "TINH", length = 2000)
	private java.lang.String tinh;
	@Column(name = "MTT_MA_VI_TRI", length = 2000)
	private java.lang.String mttMaViTri;
	@Column(name = "MTT_MA_2G", length = 2000)
	private java.lang.String mttMa2g;
	@Column(name = "MTT_MA_3G", length = 2000)
	private java.lang.String mttMa3g;
	@Column(name = "MTT_MA_XUAT_KHO", length = 2000)
	private java.lang.String mttMaXuatKho;
	@Column(name = "SO_HD_CDT", length = 2000)
	private java.lang.String soHdCdt;
	@Column(name = "LOAI_CT", length = 200)
	private java.lang.String loaiCt;
	@Column(name = "NOI_DUNG", length = 4000)
	private java.lang.String noiDung;
	@Column(name = "NGAY_GUI_HSHC", length = 7)
	private java.util.Date ngayGuiHshc;
	@Column(name = "SO_BILL", length = 2000)
	private java.lang.String soBill;
	@Column(name = "NGAY_NHAN_HSHC", length = 7)
	private java.util.Date ngayNhanHshc;
	@Column(name = "GHI_CHU", length = 4000)
	private java.lang.String ghiChu;
	@Column(name = "GT_SL_HT_TT_TONG", length = 22)
	private java.lang.Float gtSlHtTtTong;
	@Column(name = "GT_SL_HT_TT_XD", length = 22)
	private java.lang.Float gtSlHtTtXd;
	@Column(name = "GT_SL_HT_TT_DIEN", length = 22)
	private java.lang.Float gtSlHtTtDien;
	@Column(name = "GT_SL_HT_TT_LAP_DUNG", length = 22)
	private java.lang.Float gtSlHtTtLapDung;
	@Column(name = "GT_SL_HT_TT_LAP_BTS", length = 22)
	private java.lang.Float gtSlHtTtLapBts;
	@Column(name = "GT_SL_HT_TT_KHAC", length = 22)
	private java.lang.Float gtSlHtTtKhac;
	@Column(name = "GT_DN_QTK_CN_NGAY", length = 7)
	private java.util.Date gtDnQtkCnNgay;
	@Column(name = "GT_DN_QTK_CN_TONG", length = 22)
	private java.lang.Float gtDnQtkCnTong;
	@Column(name = "GT_DN_QTK_CN_XD", length = 22)
	private java.lang.Float gtDnQtkCnXd;
	@Column(name = "GT_DN_QTK_CN_DIEN", length = 22)
	private java.lang.Float gtDnQtkCnDien;
	@Column(name = "GT_DN_QTK_CN_LAP_DUNG", length = 22)
	private java.lang.Float gtDnQtkCnLapDung;
	@Column(name = "GT_DN_QTK_CN_LAP_BTS", length = 22)
	private java.lang.Float gtDnQtkCnLapBts;
	@Column(name = "GT_DN_QTK_CN_KHAC", length = 22)
	private java.lang.Float gtDnQtkCnKhac;
	@Column(name = "GT_DN_QTK_CN_NGUOI_LAP", length = 500)
	private java.lang.String gtDnQtkCnNguoiLap;
	@Column(name = "GT_QTK_PTK_NGAY", length = 7)
	private java.util.Date gtQtkPtkNgay;
	@Column(name = "GT_QTK_PTK_TONG", length = 22)
	private java.lang.Float gtQtkPtkTong;
	@Column(name = "GT_QTK_PTK_XD", length = 22)
	private java.lang.Float gtQtkPtkXd;
	@Column(name = "GT_QTK_PTK_DIEN", length = 22)
	private java.lang.Float gtQtkPtkDien;
	@Column(name = "GT_QTK_PTK_LAP_DUNG", length = 22)
	private java.lang.Float gtQtkPtkLapDung;
	@Column(name = "GT_QTK_PTK_LAP_BTS", length = 22)
	private java.lang.Float gtQtkPtkLapBts;
	@Column(name = "GT_QTK_PTK_KHAC", length = 22)
	private java.lang.Float gtQtkPtkKhac;
	@Column(name = "GT_QTK_PTK_NGUOI_CHOT", length = 500)
	private java.lang.String gtQtkPtkNguoiChot;
	@Column(name = "GT_QTK_PTK_DMTT", length = 1000)
	private java.lang.String gtQtkPtkDmtt;
	@Column(name = "GT_QTK_PTK_THANG_QTK", length = 50)
	private java.lang.String gtQtkPtkThangQtk;
	@Column(name = "TINH_TRANG_CCT1", length = 200)
	private java.lang.String tinhTrangCct1;
	@Column(name = "GHI_CHU_HS_LOI", length = 4000)
	private java.lang.String ghiChuHsLoi;
	@Column(name = "QT_DT_NGAY", length = 7)
	private java.util.Date qtDtNgay;
	@Column(name = "QT_DT_GT_DN_DT", length = 22)
	private java.lang.Float qtDtGtDnDt;
	@Column(name = "QT_DT_SO_HD", length = 2000)
	private java.lang.String qtDtSoHd;
	@Column(name = "QT_DT_NGAY_TD_DT", length = 7)
	private java.util.Date qtDtNgayTdDt;
	@Column(name = "QT_DT_TONG", length = 22)
	private java.lang.Float qtDtTong;
	@Column(name = "QT_DT_XD", length = 22)
	private java.lang.Float qtDtXd;
	@Column(name = "QT_DT_DIEN", length = 22)
	private java.lang.Float qtDtDien;
	@Column(name = "QT_DT_LAP_DUNG", length = 22)
	private java.lang.Float qtDtLapDung;
	@Column(name = "QT_DT_LAP_BTS", length = 22)
	private java.lang.Float qtDtLapBts;
	@Column(name = "QT_DT_KHAC", length = 22)
	private java.lang.Float qtDtKhac;
	@Column(name = "QT_DT_TT_DT", length = 4000)
	private java.lang.String qtDtTtDt;
	@Column(name = "QT_DT_NGUOI_TD", length = 500)
	private java.lang.String qtDtNguoiTd;
	@Column(name = "DN_QT_CDT_NGAY", length = 7)
	private java.util.Date dnQtCdtNgay;
	@Column(name = "DN_QT_CDT_GT", length = 22)
	private java.lang.Float dnQtCdtGt;
	@Column(name = "DN_QT_CDT_NGUOI_LAP", length = 500)
	private java.lang.String dnQtCdtNguoiLap;
	@Column(name = "DN_QT_CDT_NGUOI_NHAN_BG", length = 500)
	private java.lang.String dnQtCdtNguoiNhanBg;
	@Column(name = "DN_QT_CDT_NOI_DUNG_PS_CTK", length = 2000)
	private java.lang.String dnQtCdtNoiDungPsCtk;
	@Column(name = "DN_QT_CDT_KTK", length = 2000)
	private java.lang.String dnQtCdtKtk;
	@Column(name = "TD_QT_CDT_NGAY_CHOT", length = 7)
	private java.util.Date tdQtCdtNgayChot;
	@Column(name = "TD_QT_CDT_GT", length = 22)
	private java.lang.Float tdQtCdtGt;
	@Column(name = "TD_QT_CDT_NGUOI_CHOT_MVT", length = 500)
	private java.lang.String tdQtCdtNguoiChotMvt;
	@Column(name = "TD_QT_CDT_NGUOI_TD_CDT_MVT", length = 500)
	private java.lang.String tdQtCdtNguoiTdCdtMvt;
	@Column(name = "TD_QT_CDT_NGAY_BAN_TD", length = 7)
	private java.util.Date tdQtCdtNgayBanTd;
	@Column(name = "TD_QT_CDT_NGAY_CTC", length = 7)
	private java.util.Date tdQtCdtNgayCtc;
	@Column(name = "TD_QT_CDT_NOI_DUNG", length = 2000)
	private java.lang.String tdQtCdtNoiDung;
	@Column(name = "TINH_TRANG_CCT2", length = 300)
	private java.lang.String tinhTrangCct2;
	@Column(name = "GHI_CHU_2", length = 4000)
	private java.lang.String ghiChu2;
	@Column(name = "LUU_TAI_KHO", length = 2000)
	private java.lang.String luuTaiKho;
	@Column(name = "TRANG_THAI", length = 50)
	private java.lang.String trangThai;

	@Column(name = "PATH_FILE", length = 2000)
	private java.lang.String pathFile;

	public java.lang.Long getTblQlCvPtkId(){
		return tblQlCvPtkId;
	}
	
	public java.lang.String getPathFile() {
		return pathFile;
	}

	public void setPathFile(java.lang.String pathFile) {
		this.pathFile = pathFile;
	}

	public void setTblQlCvPtkId(java.lang.Long tblQlCvPtkId)
	{
		this.tblQlCvPtkId = tblQlCvPtkId;
	}
	
	public java.lang.String getCnkv(){
		return cnkv;
	}
	
	public void setCnkv(java.lang.String cnkv)
	{
		this.cnkv = cnkv;
	}
	
	public java.lang.String getTinh(){
		return tinh;
	}
	
	public void setTinh(java.lang.String tinh)
	{
		this.tinh = tinh;
	}
	
	public java.lang.String getMttMaViTri(){
		return mttMaViTri;
	}
	
	public void setMttMaViTri(java.lang.String mttMaViTri)
	{
		this.mttMaViTri = mttMaViTri;
	}
	
	public java.lang.String getMttMa2g(){
		return mttMa2g;
	}
	
	public void setMttMa2g(java.lang.String mttMa2g)
	{
		this.mttMa2g = mttMa2g;
	}
	
	public java.lang.String getMttMa3g(){
		return mttMa3g;
	}
	
	public void setMttMa3g(java.lang.String mttMa3g)
	{
		this.mttMa3g = mttMa3g;
	}
	
	public java.lang.String getMttMaXuatKho(){
		return mttMaXuatKho;
	}
	
	public void setMttMaXuatKho(java.lang.String mttMaXuatKho)
	{
		this.mttMaXuatKho = mttMaXuatKho;
	}
	
	public java.lang.String getSoHdCdt(){
		return soHdCdt;
	}
	
	public void setSoHdCdt(java.lang.String soHdCdt)
	{
		this.soHdCdt = soHdCdt;
	}
	
	public java.lang.String getLoaiCt(){
		return loaiCt;
	}
	
	public void setLoaiCt(java.lang.String loaiCt)
	{
		this.loaiCt = loaiCt;
	}
	
	public java.lang.String getNoiDung(){
		return noiDung;
	}
	
	public void setNoiDung(java.lang.String noiDung)
	{
		this.noiDung = noiDung;
	}
	
	public java.util.Date getNgayGuiHshc(){
		return ngayGuiHshc;
	}
	
	public void setNgayGuiHshc(java.util.Date ngayGuiHshc)
	{
		this.ngayGuiHshc = ngayGuiHshc;
	}
	
	public java.lang.String getSoBill(){
		return soBill;
	}
	
	public void setSoBill(java.lang.String soBill)
	{
		this.soBill = soBill;
	}
	
	public java.util.Date getNgayNhanHshc(){
		return ngayNhanHshc;
	}
	
	public void setNgayNhanHshc(java.util.Date ngayNhanHshc)
	{
		this.ngayNhanHshc = ngayNhanHshc;
	}
	
	public java.lang.String getGhiChu(){
		return ghiChu;
	}
	
	public void setGhiChu(java.lang.String ghiChu)
	{
		this.ghiChu = ghiChu;
	}
	
	public java.lang.Float getGtSlHtTtTong(){
		return gtSlHtTtTong;
	}
	
	public void setGtSlHtTtTong(java.lang.Float gtSlHtTtTong)
	{
		this.gtSlHtTtTong = gtSlHtTtTong;
	}
	
	public java.lang.Float getGtSlHtTtXd(){
		return gtSlHtTtXd;
	}
	
	public void setGtSlHtTtXd(java.lang.Float gtSlHtTtXd)
	{
		this.gtSlHtTtXd = gtSlHtTtXd;
	}
	
	public java.lang.Float getGtSlHtTtDien(){
		return gtSlHtTtDien;
	}
	
	public void setGtSlHtTtDien(java.lang.Float gtSlHtTtDien)
	{
		this.gtSlHtTtDien = gtSlHtTtDien;
	}
	
	public java.lang.Float getGtSlHtTtLapDung(){
		return gtSlHtTtLapDung;
	}
	
	public void setGtSlHtTtLapDung(java.lang.Float gtSlHtTtLapDung)
	{
		this.gtSlHtTtLapDung = gtSlHtTtLapDung;
	}
	
	public java.lang.Float getGtSlHtTtLapBts(){
		return gtSlHtTtLapBts;
	}
	
	public void setGtSlHtTtLapBts(java.lang.Float gtSlHtTtLapBts)
	{
		this.gtSlHtTtLapBts = gtSlHtTtLapBts;
	}
	
	public java.lang.Float getGtSlHtTtKhac(){
		return gtSlHtTtKhac;
	}
	
	public void setGtSlHtTtKhac(java.lang.Float gtSlHtTtKhac)
	{
		this.gtSlHtTtKhac = gtSlHtTtKhac;
	}
	
	public java.util.Date getGtDnQtkCnNgay(){
		return gtDnQtkCnNgay;
	}
	
	public void setGtDnQtkCnNgay(java.util.Date gtDnQtkCnNgay)
	{
		this.gtDnQtkCnNgay = gtDnQtkCnNgay;
	}
	
	public java.lang.Float getGtDnQtkCnTong(){
		return gtDnQtkCnTong;
	}
	
	public void setGtDnQtkCnTong(java.lang.Float gtDnQtkCnTong)
	{
		this.gtDnQtkCnTong = gtDnQtkCnTong;
	}
	
	public java.lang.Float getGtDnQtkCnXd(){
		return gtDnQtkCnXd;
	}
	
	public void setGtDnQtkCnXd(java.lang.Float gtDnQtkCnXd)
	{
		this.gtDnQtkCnXd = gtDnQtkCnXd;
	}
	
	public java.lang.Float getGtDnQtkCnDien(){
		return gtDnQtkCnDien;
	}
	
	public void setGtDnQtkCnDien(java.lang.Float gtDnQtkCnDien)
	{
		this.gtDnQtkCnDien = gtDnQtkCnDien;
	}
	
	public java.lang.Float getGtDnQtkCnLapDung(){
		return gtDnQtkCnLapDung;
	}
	
	public void setGtDnQtkCnLapDung(java.lang.Float gtDnQtkCnLapDung)
	{
		this.gtDnQtkCnLapDung = gtDnQtkCnLapDung;
	}
	
	public java.lang.Float getGtDnQtkCnLapBts(){
		return gtDnQtkCnLapBts;
	}
	
	public void setGtDnQtkCnLapBts(java.lang.Float gtDnQtkCnLapBts)
	{
		this.gtDnQtkCnLapBts = gtDnQtkCnLapBts;
	}
	
	public java.lang.Float getGtDnQtkCnKhac(){
		return gtDnQtkCnKhac;
	}
	
	public void setGtDnQtkCnKhac(java.lang.Float gtDnQtkCnKhac)
	{
		this.gtDnQtkCnKhac = gtDnQtkCnKhac;
	}
	
	public java.lang.String getGtDnQtkCnNguoiLap(){
		return gtDnQtkCnNguoiLap;
	}
	
	public void setGtDnQtkCnNguoiLap(java.lang.String gtDnQtkCnNguoiLap)
	{
		this.gtDnQtkCnNguoiLap = gtDnQtkCnNguoiLap;
	}
	
	public java.util.Date getGtQtkPtkNgay(){
		return gtQtkPtkNgay;
	}
	
	public void setGtQtkPtkNgay(java.util.Date gtQtkPtkNgay)
	{
		this.gtQtkPtkNgay = gtQtkPtkNgay;
	}
	
	public java.lang.Float getGtQtkPtkTong(){
		return gtQtkPtkTong;
	}
	
	public void setGtQtkPtkTong(java.lang.Float gtQtkPtkTong)
	{
		this.gtQtkPtkTong = gtQtkPtkTong;
	}
	
	public java.lang.Float getGtQtkPtkXd(){
		return gtQtkPtkXd;
	}
	
	public void setGtQtkPtkXd(java.lang.Float gtQtkPtkXd)
	{
		this.gtQtkPtkXd = gtQtkPtkXd;
	}
	
	public java.lang.Float getGtQtkPtkDien(){
		return gtQtkPtkDien;
	}
	
	public void setGtQtkPtkDien(java.lang.Float gtQtkPtkDien)
	{
		this.gtQtkPtkDien = gtQtkPtkDien;
	}
	
	public java.lang.Float getGtQtkPtkLapDung(){
		return gtQtkPtkLapDung;
	}
	
	public void setGtQtkPtkLapDung(java.lang.Float gtQtkPtkLapDung)
	{
		this.gtQtkPtkLapDung = gtQtkPtkLapDung;
	}
	
	public java.lang.Float getGtQtkPtkLapBts(){
		return gtQtkPtkLapBts;
	}
	
	public void setGtQtkPtkLapBts(java.lang.Float gtQtkPtkLapBts)
	{
		this.gtQtkPtkLapBts = gtQtkPtkLapBts;
	}
	
	public java.lang.Float getGtQtkPtkKhac(){
		return gtQtkPtkKhac;
	}
	
	public void setGtQtkPtkKhac(java.lang.Float gtQtkPtkKhac)
	{
		this.gtQtkPtkKhac = gtQtkPtkKhac;
	}
	
	public java.lang.String getGtQtkPtkNguoiChot(){
		return gtQtkPtkNguoiChot;
	}
	
	public void setGtQtkPtkNguoiChot(java.lang.String gtQtkPtkNguoiChot)
	{
		this.gtQtkPtkNguoiChot = gtQtkPtkNguoiChot;
	}
	
	public java.lang.String getGtQtkPtkDmtt(){
		return gtQtkPtkDmtt;
	}
	
	public void setGtQtkPtkDmtt(java.lang.String gtQtkPtkDmtt)
	{
		this.gtQtkPtkDmtt = gtQtkPtkDmtt;
	}
	
	public java.lang.String getGtQtkPtkThangQtk(){
		return gtQtkPtkThangQtk;
	}
	
	public void setGtQtkPtkThangQtk(java.lang.String gtQtkPtkThangQtk)
	{
		this.gtQtkPtkThangQtk = gtQtkPtkThangQtk;
	}
	
	public java.lang.String getTinhTrangCct1(){
		return tinhTrangCct1;
	}
	
	public void setTinhTrangCct1(java.lang.String tinhTrangCct1)
	{
		this.tinhTrangCct1 = tinhTrangCct1;
	}
	
	public java.lang.String getGhiChuHsLoi(){
		return ghiChuHsLoi;
	}
	
	public void setGhiChuHsLoi(java.lang.String ghiChuHsLoi)
	{
		this.ghiChuHsLoi = ghiChuHsLoi;
	}
	
	public java.util.Date getQtDtNgay(){
		return qtDtNgay;
	}
	
	public void setQtDtNgay(java.util.Date qtDtNgay)
	{
		this.qtDtNgay = qtDtNgay;
	}
	
	public java.lang.Float getQtDtGtDnDt(){
		return qtDtGtDnDt;
	}
	
	public void setQtDtGtDnDt(java.lang.Float qtDtGtDnDt)
	{
		this.qtDtGtDnDt = qtDtGtDnDt;
	}
	
	public java.lang.String getQtDtSoHd(){
		return qtDtSoHd;
	}
	
	public void setQtDtSoHd(java.lang.String qtDtSoHd)
	{
		this.qtDtSoHd = qtDtSoHd;
	}
	
	public java.util.Date getQtDtNgayTdDt(){
		return qtDtNgayTdDt;
	}
	
	public void setQtDtNgayTdDt(java.util.Date qtDtNgayTdDt)
	{
		this.qtDtNgayTdDt = qtDtNgayTdDt;
	}
	
	public java.lang.Float getQtDtTong(){
		return qtDtTong;
	}
	
	public void setQtDtTong(java.lang.Float qtDtTong)
	{
		this.qtDtTong = qtDtTong;
	}
	
	public java.lang.Float getQtDtXd(){
		return qtDtXd;
	}
	
	public void setQtDtXd(java.lang.Float qtDtXd)
	{
		this.qtDtXd = qtDtXd;
	}
	
	public java.lang.Float getQtDtDien(){
		return qtDtDien;
	}
	
	public void setQtDtDien(java.lang.Float qtDtDien)
	{
		this.qtDtDien = qtDtDien;
	}
	
	public java.lang.Float getQtDtLapDung(){
		return qtDtLapDung;
	}
	
	public void setQtDtLapDung(java.lang.Float qtDtLapDung)
	{
		this.qtDtLapDung = qtDtLapDung;
	}
	
	public java.lang.Float getQtDtLapBts(){
		return qtDtLapBts;
	}
	
	public void setQtDtLapBts(java.lang.Float qtDtLapBts)
	{
		this.qtDtLapBts = qtDtLapBts;
	}
	
	public java.lang.Float getQtDtKhac(){
		return qtDtKhac;
	}
	
	public void setQtDtKhac(java.lang.Float qtDtKhac)
	{
		this.qtDtKhac = qtDtKhac;
	}
	
	public java.lang.String getQtDtTtDt(){
		return qtDtTtDt;
	}
	
	public void setQtDtTtDt(java.lang.String qtDtTtDt)
	{
		this.qtDtTtDt = qtDtTtDt;
	}
	
	public java.lang.String getQtDtNguoiTd(){
		return qtDtNguoiTd;
	}
	
	public void setQtDtNguoiTd(java.lang.String qtDtNguoiTd)
	{
		this.qtDtNguoiTd = qtDtNguoiTd;
	}
	
	public java.util.Date getDnQtCdtNgay(){
		return dnQtCdtNgay;
	}
	
	public void setDnQtCdtNgay(java.util.Date dnQtCdtNgay)
	{
		this.dnQtCdtNgay = dnQtCdtNgay;
	}
	
	public java.lang.Float getDnQtCdtGt(){
		return dnQtCdtGt;
	}
	
	public void setDnQtCdtGt(java.lang.Float dnQtCdtGt)
	{
		this.dnQtCdtGt = dnQtCdtGt;
	}
	
	public java.lang.String getDnQtCdtNguoiLap(){
		return dnQtCdtNguoiLap;
	}
	
	public void setDnQtCdtNguoiLap(java.lang.String dnQtCdtNguoiLap)
	{
		this.dnQtCdtNguoiLap = dnQtCdtNguoiLap;
	}
	
	public java.lang.String getDnQtCdtNguoiNhanBg(){
		return dnQtCdtNguoiNhanBg;
	}
	
	public void setDnQtCdtNguoiNhanBg(java.lang.String dnQtCdtNguoiNhanBg)
	{
		this.dnQtCdtNguoiNhanBg = dnQtCdtNguoiNhanBg;
	}
	
	public java.lang.String getDnQtCdtNoiDungPsCtk(){
		return dnQtCdtNoiDungPsCtk;
	}
	
	public void setDnQtCdtNoiDungPsCtk(java.lang.String dnQtCdtNoiDungPsCtk)
	{
		this.dnQtCdtNoiDungPsCtk = dnQtCdtNoiDungPsCtk;
	}
	
	public java.lang.String getDnQtCdtKtk(){
		return dnQtCdtKtk;
	}
	
	public void setDnQtCdtKtk(java.lang.String dnQtCdtKtk)
	{
		this.dnQtCdtKtk = dnQtCdtKtk;
	}
	
	public java.util.Date getTdQtCdtNgayChot(){
		return tdQtCdtNgayChot;
	}
	
	public void setTdQtCdtNgayChot(java.util.Date tdQtCdtNgayChot)
	{
		this.tdQtCdtNgayChot = tdQtCdtNgayChot;
	}
	
	public java.lang.Float getTdQtCdtGt(){
		return tdQtCdtGt;
	}
	
	public void setTdQtCdtGt(java.lang.Float tdQtCdtGt)
	{
		this.tdQtCdtGt = tdQtCdtGt;
	}
	
	public java.lang.String getTdQtCdtNguoiChotMvt(){
		return tdQtCdtNguoiChotMvt;
	}
	
	public void setTdQtCdtNguoiChotMvt(java.lang.String tdQtCdtNguoiChotMvt)
	{
		this.tdQtCdtNguoiChotMvt = tdQtCdtNguoiChotMvt;
	}
	
	public java.lang.String getTdQtCdtNguoiTdCdtMvt(){
		return tdQtCdtNguoiTdCdtMvt;
	}
	
	public void setTdQtCdtNguoiTdCdtMvt(java.lang.String tdQtCdtNguoiTdCdtMvt)
	{
		this.tdQtCdtNguoiTdCdtMvt = tdQtCdtNguoiTdCdtMvt;
	}
	
	public java.util.Date getTdQtCdtNgayBanTd(){
		return tdQtCdtNgayBanTd;
	}
	
	public void setTdQtCdtNgayBanTd(java.util.Date tdQtCdtNgayBanTd)
	{
		this.tdQtCdtNgayBanTd = tdQtCdtNgayBanTd;
	}
	
	public java.util.Date getTdQtCdtNgayCtc(){
		return tdQtCdtNgayCtc;
	}
	
	public void setTdQtCdtNgayCtc(java.util.Date tdQtCdtNgayCtc)
	{
		this.tdQtCdtNgayCtc = tdQtCdtNgayCtc;
	}
	
	public java.lang.String getTdQtCdtNoiDung(){
		return tdQtCdtNoiDung;
	}
	
	public void setTdQtCdtNoiDung(java.lang.String tdQtCdtNoiDung)
	{
		this.tdQtCdtNoiDung = tdQtCdtNoiDung;
	}
	
	public java.lang.String getTinhTrangCct2(){
		return tinhTrangCct2;
	}
	
	public void setTinhTrangCct2(java.lang.String tinhTrangCct2)
	{
		this.tinhTrangCct2 = tinhTrangCct2;
	}
	
	public java.lang.String getGhiChu2(){
		return ghiChu2;
	}
	
	public void setGhiChu2(java.lang.String ghiChu2)
	{
		this.ghiChu2 = ghiChu2;
	}
	
	public java.lang.String getLuuTaiKho(){
		return luuTaiKho;
	}
	
	public void setLuuTaiKho(java.lang.String luuTaiKho)
	{
		this.luuTaiKho = luuTaiKho;
	}
   
    @Override
    public TblQlCvPtkDTO toDTO() {
        TblQlCvPtkDTO tblQlCvPtkDTO = new TblQlCvPtkDTO(); 
        tblQlCvPtkDTO.setTblQlCvPtkId(this.tblQlCvPtkId);		
        tblQlCvPtkDTO.setCnkv(this.cnkv);		
        tblQlCvPtkDTO.setTinh(this.tinh);		
        tblQlCvPtkDTO.setMttMaViTri(this.mttMaViTri);		
        tblQlCvPtkDTO.setMttMa2g(this.mttMa2g);		
        tblQlCvPtkDTO.setMttMa3g(this.mttMa3g);		
        tblQlCvPtkDTO.setMttMaXuatKho(this.mttMaXuatKho);		
        tblQlCvPtkDTO.setSoHdCdt(this.soHdCdt);		
        tblQlCvPtkDTO.setLoaiCt(this.loaiCt);		
        tblQlCvPtkDTO.setNoiDung(this.noiDung);		
        tblQlCvPtkDTO.setNgayGuiHshc(this.ngayGuiHshc);		
        tblQlCvPtkDTO.setSoBill(this.soBill);		
        tblQlCvPtkDTO.setNgayNhanHshc(this.ngayNhanHshc);		
        tblQlCvPtkDTO.setGhiChu(this.ghiChu);		
        tblQlCvPtkDTO.setGtSlHtTtTong(this.gtSlHtTtTong);		
        tblQlCvPtkDTO.setGtSlHtTtXd(this.gtSlHtTtXd);		
        tblQlCvPtkDTO.setGtSlHtTtDien(this.gtSlHtTtDien);		
        tblQlCvPtkDTO.setGtSlHtTtLapDung(this.gtSlHtTtLapDung);		
        tblQlCvPtkDTO.setGtSlHtTtLapBts(this.gtSlHtTtLapBts);		
        tblQlCvPtkDTO.setGtSlHtTtKhac(this.gtSlHtTtKhac);		
        tblQlCvPtkDTO.setGtDnQtkCnNgay(this.gtDnQtkCnNgay);		
        tblQlCvPtkDTO.setGtDnQtkCnTong(this.gtDnQtkCnTong);		
        tblQlCvPtkDTO.setGtDnQtkCnXd(this.gtDnQtkCnXd);		
        tblQlCvPtkDTO.setGtDnQtkCnDien(this.gtDnQtkCnDien);		
        tblQlCvPtkDTO.setGtDnQtkCnLapDung(this.gtDnQtkCnLapDung);		
        tblQlCvPtkDTO.setGtDnQtkCnLapBts(this.gtDnQtkCnLapBts);		
        tblQlCvPtkDTO.setGtDnQtkCnKhac(this.gtDnQtkCnKhac);		
        tblQlCvPtkDTO.setGtDnQtkCnNguoiLap(this.gtDnQtkCnNguoiLap);		
        tblQlCvPtkDTO.setGtQtkPtkNgay(this.gtQtkPtkNgay);		
        tblQlCvPtkDTO.setGtQtkPtkTong(this.gtQtkPtkTong);		
        tblQlCvPtkDTO.setGtQtkPtkXd(this.gtQtkPtkXd);		
        tblQlCvPtkDTO.setGtQtkPtkDien(this.gtQtkPtkDien);		
        tblQlCvPtkDTO.setGtQtkPtkLapDung(this.gtQtkPtkLapDung);		
        tblQlCvPtkDTO.setGtQtkPtkLapBts(this.gtQtkPtkLapBts);		
        tblQlCvPtkDTO.setGtQtkPtkKhac(this.gtQtkPtkKhac);		
        tblQlCvPtkDTO.setGtQtkPtkNguoiChot(this.gtQtkPtkNguoiChot);		
        tblQlCvPtkDTO.setGtQtkPtkDmtt(this.gtQtkPtkDmtt);		
        tblQlCvPtkDTO.setGtQtkPtkThangQtk(this.gtQtkPtkThangQtk);		
        tblQlCvPtkDTO.setTinhTrangCct1(this.tinhTrangCct1);		
        tblQlCvPtkDTO.setGhiChuHsLoi(this.ghiChuHsLoi);		
        tblQlCvPtkDTO.setQtDtNgay(this.qtDtNgay);		
        tblQlCvPtkDTO.setQtDtGtDnDt(this.qtDtGtDnDt);		
        tblQlCvPtkDTO.setQtDtSoHd(this.qtDtSoHd);		
        tblQlCvPtkDTO.setQtDtNgayTdDt(this.qtDtNgayTdDt);		
        tblQlCvPtkDTO.setQtDtTong(this.qtDtTong);		
        tblQlCvPtkDTO.setQtDtXd(this.qtDtXd);		
        tblQlCvPtkDTO.setQtDtDien(this.qtDtDien);		
        tblQlCvPtkDTO.setQtDtLapDung(this.qtDtLapDung);		
        tblQlCvPtkDTO.setQtDtLapBts(this.qtDtLapBts);		
        tblQlCvPtkDTO.setQtDtKhac(this.qtDtKhac);		
        tblQlCvPtkDTO.setQtDtTtDt(this.qtDtTtDt);		
        tblQlCvPtkDTO.setQtDtNguoiTd(this.qtDtNguoiTd);		
        tblQlCvPtkDTO.setDnQtCdtNgay(this.dnQtCdtNgay);		
        tblQlCvPtkDTO.setDnQtCdtGt(this.dnQtCdtGt);		
        tblQlCvPtkDTO.setDnQtCdtNguoiLap(this.dnQtCdtNguoiLap);		
        tblQlCvPtkDTO.setDnQtCdtNguoiNhanBg(this.dnQtCdtNguoiNhanBg);		
        tblQlCvPtkDTO.setDnQtCdtNoiDungPsCtk(this.dnQtCdtNoiDungPsCtk);		
        tblQlCvPtkDTO.setDnQtCdtKtk(this.dnQtCdtKtk);		
        tblQlCvPtkDTO.setTdQtCdtNgayChot(this.tdQtCdtNgayChot);		
        tblQlCvPtkDTO.setTdQtCdtGt(this.tdQtCdtGt);		
        tblQlCvPtkDTO.setTdQtCdtNguoiChotMvt(this.tdQtCdtNguoiChotMvt);		
        tblQlCvPtkDTO.setTdQtCdtNguoiTdCdtMvt(this.tdQtCdtNguoiTdCdtMvt);		
        tblQlCvPtkDTO.setTdQtCdtNgayBanTd(this.tdQtCdtNgayBanTd);		
        tblQlCvPtkDTO.setTdQtCdtNgayCtc(this.tdQtCdtNgayCtc);		
        tblQlCvPtkDTO.setTdQtCdtNoiDung(this.tdQtCdtNoiDung);		
        tblQlCvPtkDTO.setTinhTrangCct2(this.tinhTrangCct2);		
        tblQlCvPtkDTO.setGhiChu2(this.ghiChu2);		
        tblQlCvPtkDTO.setLuuTaiKho(this.luuTaiKho);
        tblQlCvPtkDTO.setTrangThai(this.trangThai);
        tblQlCvPtkDTO.setPathFile(this.pathFile);
        return tblQlCvPtkDTO;
    }

	public java.lang.String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(java.lang.String trangThai) {
		this.trangThai = trangThai;
	}
}
