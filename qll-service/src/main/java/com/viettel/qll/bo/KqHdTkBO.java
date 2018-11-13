package com.viettel.qll.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.viettel.qll.dto.KqHdTkDTO;
import com.viettel.service.base.model.BaseFWModelImpl;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.qll.bo.KqHdTkBO")
@Table(name = "KQ_HD_TK")
/**
 *
 * @author: hailh10
 */
public class KqHdTkBO extends BaseFWModelImpl {
     
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_KQHDTK") })
	@Column(name = "KQ_HD_TK_ID", length = 22)
	private java.lang.Long kqHdTkId;
	@Column(name = "TINH", length = 1000)
	private java.lang.String tinh;
	@Column(name = "MA_TRAM_TUYEN", length = 1000)
	private java.lang.String maTramTuyen;
	@Column(name = "NGUON_CAP_UNG", length = 1000)
	private java.lang.String nguonCapUng;
	@Column(name = "SO_HD", length = 1000)
	private java.lang.String soHd;
	@Column(name = "SO_KH_TC", length = 1000)
	private java.lang.String soKhTc;
	@Column(name = "NGAY_KY_KH", length = 7)
	private java.util.Date ngayKyKh;
	@Column(name = "THUOC_DM_TO_TRINH", length = 1000)
	private java.lang.String thuocDmToTrinh;
	@Column(name = "LOAI_CT", length = 1000)
	private java.lang.String loaiCt;
	@Column(name = "NOI_DUNG", length = 4000)
	private java.lang.String noiDung;
	@Column(name = "NGAY_GUI_HSHC", length = 7)
	private java.util.Date ngayGuiHshc;
	@Column(name = "SO_BILL", length = 1000)
	private java.lang.String soBill;
	@Column(name = "GHI_CHU", length = 4000)
	private java.lang.String ghiChu;
	@Column(name = "GT_QT_CDT_CHUA_VAT", length = 22)
	private java.lang.Float gtQtCdtChuaVat;
	@Column(name = "GT_QT_CDT_CO_VAT", length = 22)
	private java.lang.Float gtQtCdtCoVat;
	@Column(name = "CP_NHAN_CONG_DN", length = 22)
	private java.lang.Float cpNhanCongDn;
	@Column(name = "CP_VAT_LIEU_DN", length = 22)
	private java.lang.Float cpVatLieuDn;
	@Column(name = "CP_HSHC_DN", length = 22)
	private java.lang.Float cpHshcDn;
	@Column(name = "CP_VAN_CHUYEN_DN", length = 22)
	private java.lang.Float cpVanChuyenDn;
	@Column(name = "CHI_PHI_KHAC_DN", length = 22)
	private java.lang.Float chiPhiKhacDn;
	@Column(name = "CHI_PHI_LUONG_DN", length = 22)
	private java.lang.Float chiPhiLuongDn;
	@Column(name = "VAT_DN", length = 22)
	private java.lang.Float vatDn;
	@Column(name = "TONG_DN", length = 22)
	private java.lang.Float tongDn;
	@Column(name = "NGUOI_CAP_NHAT", length = 300)
	private java.lang.String nguoiCapNhat;
	@Column(name = "NGAY_GUI_PTK_HSHC", length = 7)
	private java.util.Date ngayGuiPtkHshc;
	@Column(name = "TINH_TRANG_CHUNG_TU", length = 2000)
	private java.lang.String tinhTrangChungTu;
	@Column(name = "NGAY_TUTTTT", length = 7)
	private java.util.Date ngayTutttt;
	@Column(name = "GHI_CHU_HS_LOI", length = 4000)
	private java.lang.String ghiChuHsLoi;
	@Column(name = "NGAY_PTK_TD_XONG", length = 7)
	private java.util.Date ngayPtkTdXong;
	@Column(name = "CP_NHAN_CONG_TD", length = 22)
	private java.lang.Float cpNhanCongTd;
	@Column(name = "CP_VAT_LIEU_TD", length = 22)
	private java.lang.Float cpVatLieuTd;
	@Column(name = "CP_HSHC_TD", length = 22)
	private java.lang.Float cpHshcTd;
	@Column(name = "CP_VAN_CHUYEN_TD", length = 22)
	private java.lang.Float cpVanChuyenTd;
	@Column(name = "CP_KHAC_TD", length = 22)
	private java.lang.Float cpKhacTd;
	@Column(name = "VAT_TD", length = 22)
	private java.lang.Float vatTd;
	@Column(name = "TONG_TD", length = 22)
	private java.lang.Float tongTd;
	@Column(name = "GT_TD_PTK_CHUA_VAT", length = 22)
	private java.lang.Float gtTdPtkChuaVat;
	@Column(name = "GT_TD_PTK_CO_VAT", length = 22)
	private java.lang.Float gtTdPtkCoVat;
	@Column(name = "THANG_HSHC_QUY_LUONG", length = 20)
	private java.lang.String thangHshcQuyLuong;
	@Column(name = "THANG_GHI_NHAN_QUY_LUONG_TQT", length = 20)
	private java.lang.String thangGhiNhanQuyLuongTqt;
	@Column(name = "TAM_UNG_LUONG", length = 22)
	private java.lang.Float tamUngLuong;
	@Column(name = "LUONG_THUC_NHAN", length = 22)
	private java.lang.Float luongThucNhan;
	@Column(name = "TY_LE", length = 22)
	private java.lang.Float tyLe;
	@Column(name = "HS_TON_QUA_5N", length = 2000)
	private java.lang.String hsTonQua5n;
	@Column(name = "GT_QH_XL_CT", length = 4000)
	private java.lang.String gtQhXlCt;
	@Column(name = "NGUOI_PHE_DUYET_PTK", length = 100)
	private java.lang.String nguoiPheDuyetPtk;

	@Column(name = "CP_LUONG_TD", length = 22)
	private java.lang.Float cpLuongTd;
	
	@Column(name = "TEN_TINH", length = 100)
	private java.lang.String tenTinh;
	
	@Column(name = "CHECK_TRINH_KY", length = 22)
	private java.lang.Float checkTrinhKy;
	
	@Column(name = "LY_DO_TC", length = 2000)
	private java.lang.String lyDoTc;
	
	@Column(name = "PATH_FILE", length = 2000)
	private java.lang.String pathFile;
	
	@Column(name = "NGAY_CAP_NHAT", length = 20)
	private java.util.Date ngayCapNhat;
	

	@Column(name = "NGUOI_TAO", length = 2000)
	private java.lang.String nguoiTao;
	
	@Column(name = "TRANG_THAI", length = 200)
	private java.lang.String trangThai;
	
	@Column(name = "NGAY_THI_CONG_XONG", length= 7)
	private java.util.Date ngayThiCong;
	
	public java.util.Date getNgayThiCong() {
		return ngayThiCong;
	}

	public void setNgayThiCong(java.util.Date ngayThiCong) {
		this.ngayThiCong = ngayThiCong;
	}

	public java.lang.Long getKqHdTkId(){
		return kqHdTkId;
	}
	
	public void setKqHdTkId(java.lang.Long kqHdTkId)
	{
		this.kqHdTkId = kqHdTkId;
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
	
	public java.lang.String getNguonCapUng(){
		return nguonCapUng;
	}
	
	public void setNguonCapUng(java.lang.String nguonCapUng)
	{
		this.nguonCapUng = nguonCapUng;
	}
	
	public java.lang.String getSoHd(){
		return soHd;
	}
	
	public void setSoHd(java.lang.String soHd)
	{
		this.soHd = soHd;
	}
	
	public java.lang.String getSoKhTc(){
		return soKhTc;
	}
	
	public void setSoKhTc(java.lang.String soKhTc)
	{
		this.soKhTc = soKhTc;
	}
	
	public java.util.Date getNgayKyKh(){
		return ngayKyKh;
	}
	
	public void setNgayKyKh(java.util.Date ngayKyKh)
	{
		this.ngayKyKh = ngayKyKh;
	}
	
	public java.lang.String getThuocDmToTrinh(){
		return thuocDmToTrinh;
	}
	
	public void setThuocDmToTrinh(java.lang.String thuocDmToTrinh)
	{
		this.thuocDmToTrinh = thuocDmToTrinh;
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
	
	public java.lang.Float getGtQtCdtChuaVat(){
		return gtQtCdtChuaVat;
	}
	
	public void setGtQtCdtChuaVat(java.lang.Float gtQtCdtChuaVat)
	{
		this.gtQtCdtChuaVat = gtQtCdtChuaVat;
	}
	
	public java.lang.Float getGtQtCdtCoVat(){
		return gtQtCdtCoVat;
	}
	
	public void setGtQtCdtCoVat(java.lang.Float gtQtCdtCoVat)
	{
		this.gtQtCdtCoVat = gtQtCdtCoVat;
	}
	
	public java.lang.Float getCpNhanCongDn(){
		return cpNhanCongDn;
	}
	
	public void setCpNhanCongDn(java.lang.Float cpNhanCongDn)
	{
		this.cpNhanCongDn = cpNhanCongDn;
	}
	
	public java.lang.Float getCpVatLieuDn(){
		return cpVatLieuDn;
	}
	
	public void setCpVatLieuDn(java.lang.Float cpVatLieuDn)
	{
		this.cpVatLieuDn = cpVatLieuDn;
	}
	
	public java.lang.Float getCpHshcDn(){
		return cpHshcDn;
	}
	
	public void setCpHshcDn(java.lang.Float cpHshcDn)
	{
		this.cpHshcDn = cpHshcDn;
	}
	
	public java.lang.Float getCpVanChuyenDn(){
		return cpVanChuyenDn;
	}
	
	public void setCpVanChuyenDn(java.lang.Float cpVanChuyenDn)
	{
		this.cpVanChuyenDn = cpVanChuyenDn;
	}
	
	public java.lang.Float getChiPhiKhacDn(){
		return chiPhiKhacDn;
	}
	
	public void setChiPhiKhacDn(java.lang.Float chiPhiKhacDn)
	{
		this.chiPhiKhacDn = chiPhiKhacDn;
	}
	
	public java.lang.Float getChiPhiLuongDn(){
		return chiPhiLuongDn;
	}
	
	public void setChiPhiLuongDn(java.lang.Float chiPhiLuongDn)
	{
		this.chiPhiLuongDn = chiPhiLuongDn;
	}
	
	public java.lang.Float getVatDn(){
		return vatDn;
	}
	
	public void setVatDn(java.lang.Float vatDn)
	{
		this.vatDn = vatDn;
	}
	
	public java.lang.Float getTongDn(){
		return tongDn;
	}
	
	public void setTongDn(java.lang.Float tongDn)
	{
		this.tongDn = tongDn;
	}
	
	public java.lang.String getNguoiCapNhat(){
		return nguoiCapNhat;
	}
	
	public void setNguoiCapNhat(java.lang.String nguoiCapNhat)
	{
		this.nguoiCapNhat = nguoiCapNhat;
	}
	
	public java.util.Date getNgayGuiPtkHshc(){
		return ngayGuiPtkHshc;
	}
	
	public void setNgayGuiPtkHshc(java.util.Date ngayGuiPtkHshc)
	{
		this.ngayGuiPtkHshc = ngayGuiPtkHshc;
	}
	
	public java.lang.String getTinhTrangChungTu(){
		return tinhTrangChungTu;
	}
	
	public void setTinhTrangChungTu(java.lang.String tinhTrangChungTu)
	{
		this.tinhTrangChungTu = tinhTrangChungTu;
	}
	
	public java.util.Date getNgayTutttt(){
		return ngayTutttt;
	}
	
	public void setNgayTutttt(java.util.Date ngayTutttt)
	{
		this.ngayTutttt = ngayTutttt;
	}
	
	public java.lang.String getGhiChuHsLoi(){
		return ghiChuHsLoi;
	}
	
	public void setGhiChuHsLoi(java.lang.String ghiChuHsLoi)
	{
		this.ghiChuHsLoi = ghiChuHsLoi;
	}
	
	public java.util.Date getNgayPtkTdXong(){
		return ngayPtkTdXong;
	}
	
	public void setNgayPtkTdXong(java.util.Date ngayPtkTdXong)
	{
		this.ngayPtkTdXong = ngayPtkTdXong;
	}
	
	public java.lang.Float getCpNhanCongTd(){
		return cpNhanCongTd;
	}
	
	public void setCpNhanCongTd(java.lang.Float cpNhanCongTd)
	{
		this.cpNhanCongTd = cpNhanCongTd;
	}
	
	public java.lang.Float getCpVatLieuTd(){
		return cpVatLieuTd;
	}
	
	public void setCpVatLieuTd(java.lang.Float cpVatLieuTd)
	{
		this.cpVatLieuTd = cpVatLieuTd;
	}
	
	public java.lang.Float getCpHshcTd(){
		return cpHshcTd;
	}
	
	public void setCpHshcTd(java.lang.Float cpHshcTd)
	{
		this.cpHshcTd = cpHshcTd;
	}
	
	public java.lang.Float getCpVanChuyenTd(){
		return cpVanChuyenTd;
	}
	
	public void setCpVanChuyenTd(java.lang.Float cpVanChuyenTd)
	{
		this.cpVanChuyenTd = cpVanChuyenTd;
	}
	
	public java.lang.Float getCpKhacTd(){
		return cpKhacTd;
	}
	
	public void setCpKhacTd(java.lang.Float cpKhacTd)
	{
		this.cpKhacTd = cpKhacTd;
	}
	
	public java.lang.Float getVatTd(){
		return vatTd;
	}
	
	public void setVatTd(java.lang.Float vatTd)
	{
		this.vatTd = vatTd;
	}
	
	public java.lang.Float getTongTd(){
		return tongTd;
	}
	
	public void setTongTd(java.lang.Float tongTd)
	{
		this.tongTd = tongTd;
	}
	
	public java.lang.Float getGtTdPtkChuaVat(){
		return gtTdPtkChuaVat;
	}
	
	public void setGtTdPtkChuaVat(java.lang.Float gtTdPtkChuaVat)
	{
		this.gtTdPtkChuaVat = gtTdPtkChuaVat;
	}
	
	public java.lang.Float getGtTdPtkCoVat(){
		return gtTdPtkCoVat;
	}
	
	public void setGtTdPtkCoVat(java.lang.Float gtTdPtkCoVat)
	{
		this.gtTdPtkCoVat = gtTdPtkCoVat;
	}
	
	public java.lang.String getThangHshcQuyLuong(){
		return thangHshcQuyLuong;
	}
	
	public void setThangHshcQuyLuong(java.lang.String thangHshcQuyLuong)
	{
		this.thangHshcQuyLuong = thangHshcQuyLuong;
	}
	
	public java.lang.String getThangGhiNhanQuyLuongTqt(){
		return thangGhiNhanQuyLuongTqt;
	}
	
	public void setThangGhiNhanQuyLuongTqt(java.lang.String thangGhiNhanQuyLuongTqt)
	{
		this.thangGhiNhanQuyLuongTqt = thangGhiNhanQuyLuongTqt;
	}
	
	public java.lang.Float getTamUngLuong(){
		return tamUngLuong;
	}
	
	public void setTamUngLuong(java.lang.Float tamUngLuong)
	{
		this.tamUngLuong = tamUngLuong;
	}
	
	public java.lang.Float getLuongThucNhan(){
		return luongThucNhan;
	}
	
	public void setLuongThucNhan(java.lang.Float luongThucNhan)
	{
		this.luongThucNhan = luongThucNhan;
	}
	
	public java.lang.Float getTyLe(){
		return tyLe;
	}
	
	public void setTyLe(java.lang.Float tyLe)
	{
		this.tyLe = tyLe;
	}
	
	public java.lang.String getHsTonQua5n(){
		return hsTonQua5n;
	}
	
	public void setHsTonQua5n(java.lang.String hsTonQua5n)
	{
		this.hsTonQua5n = hsTonQua5n;
	}
	
	public java.lang.String getGtQhXlCt(){
		return gtQhXlCt;
	}
	
	public void setGtQhXlCt(java.lang.String gtQhXlCt)
	{
		this.gtQhXlCt = gtQhXlCt;
	}
	
	public java.lang.String getNguoiPheDuyetPtk(){
		return nguoiPheDuyetPtk;
	}
	
	public void setNguoiPheDuyetPtk(java.lang.String nguoiPheDuyetPtk)
	{
		this.nguoiPheDuyetPtk = nguoiPheDuyetPtk;
	}
   
    public java.lang.Float getCpLuongTd() {
		return cpLuongTd;
	}

	public void setCpLuongTd(java.lang.Float cpLuongTd) {
		this.cpLuongTd = cpLuongTd;
	}
	
	

	public java.lang.String getTenTinh() {
		return tenTinh;
	}

	public void setTenTinh(java.lang.String tenTinh) {
		this.tenTinh = tenTinh;
	}
	

	public java.lang.Float getCheckTrinhKy() {
		return checkTrinhKy;
	}

	public void setCheckTrinhKy(java.lang.Float checkTrinhKy) {
		this.checkTrinhKy = checkTrinhKy;
	}

	public java.lang.String getLyDoTc() {
		return lyDoTc;
	}

	public void setLyDoTc(java.lang.String lyDoTc) {
		this.lyDoTc = lyDoTc;
	}
	
	public java.lang.String getPathFile() {
		return pathFile;
	}

	public void setPathFile(java.lang.String pathFile) {
		this.pathFile = pathFile;
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

	
	
	public java.lang.String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(java.lang.String trangThai) {
		this.trangThai = trangThai;
	}

	@Override
    public KqHdTkDTO toDTO() {
        KqHdTkDTO kqHdTkDTO = new KqHdTkDTO(); 
        kqHdTkDTO.setKqHdTkId(this.kqHdTkId);		
        kqHdTkDTO.setTinh(this.tinh);		
        kqHdTkDTO.setMaTramTuyen(this.maTramTuyen);		
        kqHdTkDTO.setNguonCapUng(this.nguonCapUng);		
        kqHdTkDTO.setSoHd(this.soHd);		
        kqHdTkDTO.setSoKhTc(this.soKhTc);		
        kqHdTkDTO.setNgayKyKh(this.ngayKyKh);		
        kqHdTkDTO.setThuocDmToTrinh(this.thuocDmToTrinh);		
        kqHdTkDTO.setLoaiCt(this.loaiCt);		
        kqHdTkDTO.setNoiDung(this.noiDung);		
        kqHdTkDTO.setNgayGuiHshc(this.ngayGuiHshc);		
        kqHdTkDTO.setSoBill(this.soBill);		
        kqHdTkDTO.setGhiChu(this.ghiChu);		
        kqHdTkDTO.setGtQtCdtChuaVat(this.gtQtCdtChuaVat);		
        kqHdTkDTO.setGtQtCdtCoVat(this.gtQtCdtCoVat);		
        kqHdTkDTO.setCpNhanCongDn(this.cpNhanCongDn);		
        kqHdTkDTO.setCpVatLieuDn(this.cpVatLieuDn);		
        kqHdTkDTO.setCpHshcDn(this.cpHshcDn);		
        kqHdTkDTO.setCpVanChuyenDn(this.cpVanChuyenDn);		
        kqHdTkDTO.setChiPhiKhacDn(this.chiPhiKhacDn);		
        kqHdTkDTO.setChiPhiLuongDn(this.chiPhiLuongDn);		
        kqHdTkDTO.setVatDn(this.vatDn);		
        kqHdTkDTO.setTongDn(this.tongDn);		
        kqHdTkDTO.setNguoiCapNhat(this.nguoiCapNhat);		
        kqHdTkDTO.setNgayGuiPtkHshc(this.ngayGuiPtkHshc);		
        kqHdTkDTO.setTinhTrangChungTu(this.tinhTrangChungTu);		
        kqHdTkDTO.setNgayTutttt(this.ngayTutttt);		
        kqHdTkDTO.setGhiChuHsLoi(this.ghiChuHsLoi);		
        kqHdTkDTO.setNgayPtkTdXong(this.ngayPtkTdXong);		
        kqHdTkDTO.setCpNhanCongTd(this.cpNhanCongTd);		
        kqHdTkDTO.setCpVatLieuTd(this.cpVatLieuTd);		
        kqHdTkDTO.setCpHshcTd(this.cpHshcTd);		
        kqHdTkDTO.setCpVanChuyenTd(this.cpVanChuyenTd);		
        kqHdTkDTO.setCpKhacTd(this.cpKhacTd);		
        kqHdTkDTO.setVatTd(this.vatTd);		
        kqHdTkDTO.setTongTd(this.tongTd);		
        kqHdTkDTO.setGtTdPtkChuaVat(this.gtTdPtkChuaVat);		
        kqHdTkDTO.setGtTdPtkCoVat(this.gtTdPtkCoVat);		
        kqHdTkDTO.setThangHshcQuyLuong(this.thangHshcQuyLuong);		
        kqHdTkDTO.setThangGhiNhanQuyLuongTqt(this.thangGhiNhanQuyLuongTqt);		
        kqHdTkDTO.setTamUngLuong(this.tamUngLuong);		
        kqHdTkDTO.setLuongThucNhan(this.luongThucNhan);		
        kqHdTkDTO.setTyLe(this.tyLe);		
        kqHdTkDTO.setHsTonQua5n(this.hsTonQua5n);		
        kqHdTkDTO.setGtQhXlCt(this.gtQhXlCt);		
        kqHdTkDTO.setNguoiPheDuyetPtk(this.nguoiPheDuyetPtk);
        kqHdTkDTO.setCpLuongTd(this.cpLuongTd);
        kqHdTkDTO.setTenTinh(this.tenTinh);
        kqHdTkDTO.setCheckTrinhKy(this.checkTrinhKy);
        kqHdTkDTO.setLyDoTc(this.lyDoTc);
        kqHdTkDTO.setPathFile(this.pathFile);
        kqHdTkDTO.setNgayCapNhat(this.ngayCapNhat);
        kqHdTkDTO.setNguoiTao(this.nguoiTao);
        kqHdTkDTO.setTrangThai(this.trangThai);
        kqHdTkDTO.setNgayThiCong(this.ngayThiCong);
        return kqHdTkDTO;
    }
}
