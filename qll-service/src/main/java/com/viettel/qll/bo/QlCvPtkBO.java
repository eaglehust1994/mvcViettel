package com.viettel.qll.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.viettel.qll.dto.QlCvPtkDTO;
import com.viettel.service.base.model.BaseFWModelImpl;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.qll.bo.QlCvPtkBO")
@Table(name = "QL_CV_PTK")
/**
 *
 * @author: hailh10
 */
public class QlCvPtkBO extends BaseFWModelImpl {
     
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_QLCV_PTK") })
	@Column(name = "QL_CV_PTK_ID", length = 22)
	private java.lang.Long qlCvPtkId;
	@Column(name = "CNKV", length = 2000)
	private java.lang.String cnkv;
	@Column(name = "TINH", length = 2000)
	private java.lang.String tinh;
	@Column(name = "MA_TRAM_TUYEN", length = 2000)
	private java.lang.String maTramTuyen;
	@Column(name = "SO_HD", length = 2000)
	private java.lang.String soHd;
	@Column(name = "THUOC_DM_TT", length = 2000)
	private java.lang.String thuocDmTt;
	@Column(name = "LOAI_CT", length = 2000)
	private java.lang.String loaiCt;
	@Column(name = "NOI_DUNG", length = 4000)
	private java.lang.String noiDung;
	@Column(name = "NGAY_GUI_HSHC", length = 20)
	private java.util.Date ngayGuiHshc;
	@Column(name = "SO_BILL", length = 2000)
	private java.lang.String soBill;
	@Column(name = "GHI_CHU", length = 4000)
	private java.lang.String ghiChu;
	@Column(name = "GT_SL_HT_TINH", length = 22)
	private java.lang.Float gtSlHtTinh;
	@Column(name = "GT_DN_QTK_CN", length = 22)
	private java.lang.Float gtDnQtkCn;
	@Column(name = "NGAY_DN_QTK", length = 7)
	private java.util.Date ngayDnQtk;
	@Column(name = "NGAY_NHAN_GUI_PTK_HSHC", length = 7)
	private java.util.Date ngayNhanGuiPtkHshc;
	@Column(name = "TINH_TRANG_CHUYEN_UNG_TU", length = 1000)
	private java.lang.String tinhTrangChuyenUngTu;
	@Column(name = "NGAY_TUONG_UNG_TTCUT", length = 7)
	private java.util.Date ngayTuongUngTtcut;
	@Column(name = "GHI_CHU_HS_LOI", length = 4000)
	private java.lang.String ghiChuHsLoi;
	@Column(name = "GT_PTK_TD_XONG_QTK", length = 22)
	private java.lang.Float gtPtkTdXongQtk;
	@Column(name = "NGAY_PTK_TD_XONG_QTK", length = 7)
	private java.util.Date ngayPtkTdXongQtk;
	@Column(name = "GT_DN_QT_CDT", length = 22)
	private java.lang.Float gtDnQtCdt;
	@Column(name = "GT_CHOT_QT_CDT", length = 22)
	private java.lang.Float gtChotQtCdt;
	@Column(name = "NGAY_GT_CHOT_QT_CDT", length = 7)
	private java.util.Date ngayGtChotQtCdt;
	@Column(name = "GHI_CHU_DN", length = 4000)
	private java.lang.String ghiChuDn;
	@Column(name = "THANG_QTK", length = 20)
	private java.lang.String thangQtk;
	@Column(name = "THANG_QTK_CDT", length = 20)
	private java.lang.String thangQtkCdt;
	@Column(name = "TY_LE", length = 22)
	private java.lang.Float tyLe;
	@Column(name = "HS_TON_QUA_35N", length = 4000)
	private java.lang.String hsTonQua35n;
	@Column(name = "GT_QH_CHUA_XL", length = 4000)
	private java.lang.String gtQhChuaXl;
	@Column(name = "LUU_TAI_KHO", length = 4000)
	private java.lang.String luuTaiKho;
	@Column(name = "NGUOI_DUYET_PTK", length = 500)
	private java.lang.String nguoiDuyetPtk;
	@Column(name = "NGUOI_DUYET_CDT", length = 500)
	private java.lang.String nguoiDuyetCdt;
	@Column(name = "BAN_THAM_DINH_1", length = 2000)
	private java.lang.String banThamDinh1;
	@Column(name = "BAN_THAM_DINH_2", length = 2000)
	private java.lang.String banThamDinh2;

	@Column(name = "NGUOI_TAO", length = 200)
	private java.lang.String nguoiTao;
	
	@Column(name = "NGAY_CAP_NHAT", length = 20)
	private java.util.Date ngayCapNhat;
	
	@Column(name = "FILE_QTK" , length =2000)
	private java.lang.String fileQtk;
	@Column(name = "FILE_QT_DOI_TAC" , length =2000)
	private java.lang.String fileQtDt;
	public java.lang.String getFileQtk() {
		return fileQtk;
	}

	public void setFileQtk(java.lang.String fileQtk) {
		this.fileQtk = fileQtk;
	}

	public java.lang.String getFileQtDt() {
		return fileQtDt;
	}

	public void setFileQtDt(java.lang.String fileQtDt) {
		this.fileQtDt = fileQtDt;
	}

	public java.lang.Long getQlCvPtkId(){
		return qlCvPtkId;
	}
	
	public void setQlCvPtkId(java.lang.Long qlCvPtkId)
	{
		this.qlCvPtkId = qlCvPtkId;
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
	
	public java.lang.String getMaTramTuyen(){
		return maTramTuyen;
	}
	
	public void setMaTramTuyen(java.lang.String maTramTuyen)
	{
		this.maTramTuyen = maTramTuyen;
	}
	
	public java.lang.String getSoHd(){
		return soHd;
	}
	
	public void setSoHd(java.lang.String soHd)
	{
		this.soHd = soHd;
	}
	
	public java.lang.String getThuocDmTt(){
		return thuocDmTt;
	}
	
	public void setThuocDmTt(java.lang.String thuocDmTt)
	{
		this.thuocDmTt = thuocDmTt;
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
	
	public java.lang.String getGhiChu(){
		return ghiChu;
	}
	
	public void setGhiChu(java.lang.String ghiChu)
	{
		this.ghiChu = ghiChu;
	}
	
	public java.lang.Float getGtSlHtTinh(){
		return gtSlHtTinh;
	}
	
	public void setGtSlHtTinh(java.lang.Float gtSlHtTinh)
	{
		this.gtSlHtTinh = gtSlHtTinh;
	}
	
	public java.lang.Float getGtDnQtkCn(){
		return gtDnQtkCn;
	}
	
	public void setGtDnQtkCn(java.lang.Float gtDnQtkCn)
	{
		this.gtDnQtkCn = gtDnQtkCn;
	}
	
	public java.util.Date getNgayDnQtk(){
		return ngayDnQtk;
	}
	
	public void setNgayDnQtk(java.util.Date ngayDnQtk)
	{
		this.ngayDnQtk = ngayDnQtk;
	}
	
	public java.util.Date getNgayNhanGuiPtkHshc(){
		return ngayNhanGuiPtkHshc;
	}
	
	public void setNgayNhanGuiPtkHshc(java.util.Date ngayNhanGuiPtkHshc)
	{
		this.ngayNhanGuiPtkHshc = ngayNhanGuiPtkHshc;
	}
	
	public java.lang.String getTinhTrangChuyenUngTu(){
		return tinhTrangChuyenUngTu;
	}
	
	public void setTinhTrangChuyenUngTu(java.lang.String tinhTrangChuyenUngTu)
	{
		this.tinhTrangChuyenUngTu = tinhTrangChuyenUngTu;
	}
	
	public java.util.Date getNgayTuongUngTtcut(){
		return ngayTuongUngTtcut;
	}
	
	public void setNgayTuongUngTtcut(java.util.Date ngayTuongUngTtcut)
	{
		this.ngayTuongUngTtcut = ngayTuongUngTtcut;
	}
	
	public java.lang.String getGhiChuHsLoi(){
		return ghiChuHsLoi;
	}
	
	public void setGhiChuHsLoi(java.lang.String ghiChuHsLoi)
	{
		this.ghiChuHsLoi = ghiChuHsLoi;
	}
	
	public java.lang.Float getGtPtkTdXongQtk(){
		return gtPtkTdXongQtk;
	}
	
	public void setGtPtkTdXongQtk(java.lang.Float gtPtkTdXongQtk)
	{
		this.gtPtkTdXongQtk = gtPtkTdXongQtk;
	}
	
	public java.util.Date getNgayPtkTdXongQtk(){
		return ngayPtkTdXongQtk;
	}
	
	public void setNgayPtkTdXongQtk(java.util.Date ngayPtkTdXongQtk)
	{
		this.ngayPtkTdXongQtk = ngayPtkTdXongQtk;
	}
	
	public java.lang.Float getGtDnQtCdt(){
		return gtDnQtCdt;
	}
	
	public void setGtDnQtCdt(java.lang.Float gtDnQtCdt)
	{
		this.gtDnQtCdt = gtDnQtCdt;
	}
	
	public java.lang.Float getGtChotQtCdt(){
		return gtChotQtCdt;
	}
	
	public void setGtChotQtCdt(java.lang.Float gtChotQtCdt)
	{
		this.gtChotQtCdt = gtChotQtCdt;
	}
	
	public java.util.Date getNgayGtChotQtCdt(){
		return ngayGtChotQtCdt;
	}
	
	public void setNgayGtChotQtCdt(java.util.Date ngayGtChotQtCdt)
	{
		this.ngayGtChotQtCdt = ngayGtChotQtCdt;
	}
	
	public java.lang.String getGhiChuDn(){
		return ghiChuDn;
	}
	
	public void setGhiChuDn(java.lang.String ghiChuDn)
	{
		this.ghiChuDn = ghiChuDn;
	}
	
	public java.lang.String getThangQtk(){
		return thangQtk;
	}
	
	public void setThangQtk(java.lang.String thangQtk)
	{
		this.thangQtk = thangQtk;
	}
	
	public java.lang.String getThangQtkCdt(){
		return thangQtkCdt;
	}
	
	public void setThangQtkCdt(java.lang.String thangQtkCdt)
	{
		this.thangQtkCdt = thangQtkCdt;
	}
	
	public java.lang.Float getTyLe(){
		return tyLe;
	}
	
	public void setTyLe(java.lang.Float tyLe)
	{
		this.tyLe = tyLe;
	}
	
	public java.lang.String getHsTonQua35n(){
		return hsTonQua35n;
	}
	
	public void setHsTonQua35n(java.lang.String hsTonQua35n)
	{
		this.hsTonQua35n = hsTonQua35n;
	}
	
	public java.lang.String getGtQhChuaXl(){
		return gtQhChuaXl;
	}
	
	public void setGtQhChuaXl(java.lang.String gtQhChuaXl)
	{
		this.gtQhChuaXl = gtQhChuaXl;
	}
	
	public java.lang.String getLuuTaiKho(){
		return luuTaiKho;
	}
	
	public void setLuuTaiKho(java.lang.String luuTaiKho)
	{
		this.luuTaiKho = luuTaiKho;
	}
	
	public java.lang.String getNguoiDuyetPtk(){
		return nguoiDuyetPtk;
	}
	
	public void setNguoiDuyetPtk(java.lang.String nguoiDuyetPtk)
	{
		this.nguoiDuyetPtk = nguoiDuyetPtk;
	}
	
	public java.lang.String getNguoiDuyetCdt(){
		return nguoiDuyetCdt;
	}
	
	public void setNguoiDuyetCdt(java.lang.String nguoiDuyetCdt)
	{
		this.nguoiDuyetCdt = nguoiDuyetCdt;
	}
	
	public java.lang.String getBanThamDinh1(){
		return banThamDinh1;
	}
	
	public void setBanThamDinh1(java.lang.String banThamDinh1)
	{
		this.banThamDinh1 = banThamDinh1;
	}
	
	public java.lang.String getBanThamDinh2(){
		return banThamDinh2;
	}
	
	public void setBanThamDinh2(java.lang.String banThamDinh2)
	{
		this.banThamDinh2 = banThamDinh2;
	}
   
    public java.util.Date getNgayCapNhat() {
		return ngayCapNhat;
	}

	public void setNgayCapNhat(java.util.Date ngayCapNhat) {
		this.ngayCapNhat = ngayCapNhat;
	}

	public java.lang.String getNguoiTao() {
		return nguoiTao;
	}

	public void setNguoiTao(java.lang.String nguoiTao) {
		this.nguoiTao = nguoiTao;
	}

	@Override
    public QlCvPtkDTO toDTO() {
        QlCvPtkDTO qlCvPtkDTO = new QlCvPtkDTO(); 
        qlCvPtkDTO.setQlCvPtkId(this.qlCvPtkId);		
        qlCvPtkDTO.setCnkv(this.cnkv);		
        qlCvPtkDTO.setTinh(this.tinh);		
        qlCvPtkDTO.setMaTramTuyen(this.maTramTuyen);		
        qlCvPtkDTO.setSoHd(this.soHd);		
        qlCvPtkDTO.setThuocDmTt(this.thuocDmTt);		
        qlCvPtkDTO.setLoaiCt(this.loaiCt);		
        qlCvPtkDTO.setNoiDung(this.noiDung);		
        qlCvPtkDTO.setNgayGuiHshc(this.ngayGuiHshc);		
        qlCvPtkDTO.setSoBill(this.soBill);		
        qlCvPtkDTO.setGhiChu(this.ghiChu);		
        qlCvPtkDTO.setGtSlHtTinh(this.gtSlHtTinh);		
        qlCvPtkDTO.setGtDnQtkCn(this.gtDnQtkCn);		
        qlCvPtkDTO.setNgayDnQtk(this.ngayDnQtk);		
        qlCvPtkDTO.setNgayNhanGuiPtkHshc(this.ngayNhanGuiPtkHshc);		
        qlCvPtkDTO.setTinhTrangChuyenUngTu(this.tinhTrangChuyenUngTu);		
        qlCvPtkDTO.setNgayTuongUngTtcut(this.ngayTuongUngTtcut);		
        qlCvPtkDTO.setGhiChuHsLoi(this.ghiChuHsLoi);		
        qlCvPtkDTO.setGtPtkTdXongQtk(this.gtPtkTdXongQtk);		
        qlCvPtkDTO.setNgayPtkTdXongQtk(this.ngayPtkTdXongQtk);		
        qlCvPtkDTO.setGtDnQtCdt(this.gtDnQtCdt);		
        qlCvPtkDTO.setGtChotQtCdt(this.gtChotQtCdt);		
        qlCvPtkDTO.setNgayGtChotQtCdt(this.ngayGtChotQtCdt);		
        qlCvPtkDTO.setGhiChuDn(this.ghiChuDn);		
        qlCvPtkDTO.setThangQtk(this.thangQtk);		
        qlCvPtkDTO.setThangQtkCdt(this.thangQtkCdt);		
        qlCvPtkDTO.setTyLe(this.tyLe);		
        qlCvPtkDTO.setHsTonQua35n(this.hsTonQua35n);		
        qlCvPtkDTO.setGtQhChuaXl(this.gtQhChuaXl);		
        qlCvPtkDTO.setLuuTaiKho(this.luuTaiKho);		
        qlCvPtkDTO.setNguoiDuyetPtk(this.nguoiDuyetPtk);		
        qlCvPtkDTO.setNguoiDuyetCdt(this.nguoiDuyetCdt);		
        qlCvPtkDTO.setBanThamDinh1(this.banThamDinh1);		
        qlCvPtkDTO.setBanThamDinh2(this.banThamDinh2);		
        qlCvPtkDTO.setNgayCapNhat(this.ngayCapNhat);
        qlCvPtkDTO.setNguoiTao(nguoiTao);
        return qlCvPtkDTO;
    }
}
