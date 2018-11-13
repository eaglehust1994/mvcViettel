package com.viettel.qll.bo;

import com.viettel.qll.dto.TblQltsThucXuatTheoKyDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.qll.bo.TblQltsThucXuatTheoKyBO")
@Table(name = "TBL_QLTS_THUC_XUAT_THEO_KY")
/**
 *
 * @author: hailh10
 */
public class TblQltsThucXuatTheoKyBO extends BaseFWModelImpl {
     
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "TBL_QLTS_THUC_XUAT_THEO_KY_SEQ") })
	@Column(name = "TBL_QLTS_THUC_XUAT_THEO_KY_ID", length = 22)
	private java.lang.Long tblQltsThucXuatTheoKyId;
	@Column(name = "SO_PHIEU_XUAT", length = 4000)
	private java.lang.String soPhieuXuat;
	@Column(name = "SO_PHIEU_XUAT_BEN_LOGISTIC", length = 2000)
	private java.lang.String soPhieuXuatBenLogistic;
	
	@Column(name = "NGAY_XUAT", length = 7)
	private java.util.Date ngayXuat;
	@Column(name = "NOI_DUNG", length = 4000)
	private java.lang.String noiDung;
	@Column(name = "MA_KHO_XUAT", length = 4000)
	private java.lang.String maKhoXuat;
	@Column(name = "TEN_VAT_TU", length = 4000)
	private java.lang.String tenVatTu;
	@Column(name = "MA_VAT_TU", length = 4000)
	private java.lang.String maVatTu;
	@Column(name = "DON_VI_TINH", length = 4000)
	private java.lang.String donViTinh;
	@Column(name = "SO_LUONG_XUAT", length = 22)
	private java.lang.Float soLuongXuat;
	@Column(name = "DON_GIA", length = 22)
	private java.lang.Float donGia;
	@Column(name = "THANH_TIEN", length = 22)
	private java.lang.Float thanhTien;
	@Column(name = "MA_CONG_TRINH", length = 4000)
	private java.lang.String maCongTrinh;
	@Column(name = "NGUOI_NHAN_HANG", length = 4000)
	private java.lang.String nguoiNhanHang;
	@Column(name = "SO_LUONG_YEU_CAU", length = 22)
	private java.lang.Float soLuongYeuCau;
	@Column(name = "LY_DO", length = 4000)
	private java.lang.String lyDo;
	@Column(name = "DIEN_GIAI", length = 4000)
	private java.lang.String dienGiai;
	@Column(name = "TEN_KHO_XUAT", length = 4000)
	private java.lang.String tenKhoXuat;
	@Column(name = "MA_KHO_NHAN", length = 4000)
	private java.lang.String maKhoNhan;
	
	@Column(name = "KHO_NHAN", length = 4000)
	private java.lang.String khoNhan;
	
	@Column(name = "XOA", length = 22)
	private java.lang.Long xoa;
	@Column(name = "HOAT_DONG", length = 22)
	private java.lang.Long hoatDong;
	@Column(name = "DON_VI_NHAN", length = 4000)
	private java.lang.String donViNhan;
	
	@Column(name = "MA_TINH", length = 1000)
	private java.lang.String maTinh;
	
	@Column(name = "MA_CHI_NHANH", length = 1000)
	private java.lang.String maChiNhanh;
	
	@Column(name = "MA_NV", length = 1000)
	private java.lang.String maNv;
	
	@Column(name = "SL_THUC_TE_TC", length = 22)
	private java.lang.Float slThucTeTC;
	
	@Column(name = "TT_THUC_TE_TC", length = 22)
	private java.lang.Float ttThucTeTC;
	
	@Column(name = "SL_THU_HOI", length = 22)
	private java.lang.Float slThuHoi;
	
	@Column(name = "TT_THU_HOI", length = 22)
	private java.lang.Float ttThuHoi;
	
	@Column(name = "SL_TIEN_DEN", length = 22)
	private java.lang.Float slTienDen;
	
	@Column(name = "TT_TIEN_DEN", length = 22)
	private java.lang.Float ttTienDen;
	
	
	public java.lang.Long getTblQltsThucXuatTheoKyId(){
		return tblQltsThucXuatTheoKyId;
	}
	
	public void setTblQltsThucXuatTheoKyId(java.lang.Long tblQltsThucXuatTheoKyId)
	{
		this.tblQltsThucXuatTheoKyId = tblQltsThucXuatTheoKyId;
	}
	
	public java.lang.String getSoPhieuXuat(){
		return soPhieuXuat;
	}
	
	public void setSoPhieuXuat(java.lang.String soPhieuXuat)
	{
		this.soPhieuXuat = soPhieuXuat;
	}
	
	public java.util.Date getNgayXuat(){
		return ngayXuat;
	}
	
	public void setNgayXuat(java.util.Date ngayXuat)
	{
		this.ngayXuat = ngayXuat;
	}
	
	public java.lang.String getNoiDung(){
		return noiDung;
	}
	
	public void setNoiDung(java.lang.String noiDung)
	{
		this.noiDung = noiDung;
	}
	
	public java.lang.String getMaKhoXuat(){
		return maKhoXuat;
	}
	
	public void setMaKhoXuat(java.lang.String maKhoXuat)
	{
		this.maKhoXuat = maKhoXuat;
	}
	
	public java.lang.String getTenVatTu(){
		return tenVatTu;
	}
	
	public void setTenVatTu(java.lang.String tenVatTu)
	{
		this.tenVatTu = tenVatTu;
	}
	
	public java.lang.String getMaVatTu(){
		return maVatTu;
	}
	
	public void setMaVatTu(java.lang.String maVatTu)
	{
		this.maVatTu = maVatTu;
	}
	
	public java.lang.String getDonViTinh(){
		return donViTinh;
	}
	
	public void setDonViTinh(java.lang.String donViTinh)
	{
		this.donViTinh = donViTinh;
	}
	
	public java.lang.Float getSoLuongXuat(){
		return soLuongXuat;
	}
	
	public void setSoLuongXuat(java.lang.Float soLuongXuat)
	{
		this.soLuongXuat = soLuongXuat;
	}
	
	public java.lang.Float getDonGia(){
		return donGia;
	}
	
	public void setDonGia(java.lang.Float donGia)
	{
		this.donGia = donGia;
	}
	
	public java.lang.Float getThanhTien(){
		return thanhTien;
	}
	
	public void setThanhTien(java.lang.Float thanhTien)
	{
		this.thanhTien = thanhTien;
	}
	
	public java.lang.String getMaCongTrinh(){
		return maCongTrinh;
	}
	
	public void setMaCongTrinh(java.lang.String maCongTrinh)
	{
		this.maCongTrinh = maCongTrinh;
	}
	
	public java.lang.String getNguoiNhanHang(){
		return nguoiNhanHang;
	}
	
	public void setNguoiNhanHang(java.lang.String nguoiNhanHang)
	{
		this.nguoiNhanHang = nguoiNhanHang;
	}
	
	public java.lang.Float getSoLuongYeuCau(){
		return soLuongYeuCau;
	}
	
	public void setSoLuongYeuCau(java.lang.Float soLuongYeuCau)
	{
		this.soLuongYeuCau = soLuongYeuCau;
	}
	
	public java.lang.String getLyDo(){
		return lyDo;
	}
	
	public void setLyDo(java.lang.String lyDo)
	{
		this.lyDo = lyDo;
	}
	
	public java.lang.String getDienGiai(){
		return dienGiai;
	}
	
	public void setDienGiai(java.lang.String dienGiai)
	{
		this.dienGiai = dienGiai;
	}
	
	public java.lang.String getTenKhoXuat(){
		return tenKhoXuat;
	}
	
	public void setTenKhoXuat(java.lang.String tenKhoXuat)
	{
		this.tenKhoXuat = tenKhoXuat;
	}
	
	public java.lang.String getMaKhoNhan(){
		return maKhoNhan;
	}
	
	public void setMaKhoNhan(java.lang.String maKhoNhan)
	{
		this.maKhoNhan = maKhoNhan;
	}
	
	public java.lang.Long getXoa(){
		return xoa;
	}
	
	public void setXoa(java.lang.Long xoa)
	{
		this.xoa = xoa;
	}
	
	public java.lang.Long getHoatDong(){
		return hoatDong;
	}
	
	public void setHoatDong(java.lang.Long hoatDong)
	{
		this.hoatDong = hoatDong;
	}
   
    public java.lang.String getSoPhieuXuatBenLogistic() {
		return soPhieuXuatBenLogistic;
	}

	public void setSoPhieuXuatBenLogistic(java.lang.String soPhieuXuatBenLogistic) {
		this.soPhieuXuatBenLogistic = soPhieuXuatBenLogistic;
	}
	
	public java.lang.String getKhoNhan() {
		return khoNhan;
	}

	public void setKhoNhan(java.lang.String khoNhan) {
		this.khoNhan = khoNhan;
	}
	
	public java.lang.String getDonViNhan() {
		return donViNhan;
	}

	public void setDonViNhan(java.lang.String donViNhan) {
		this.donViNhan = donViNhan;
	}
	
	

	public java.lang.String getMaTinh() {
		return maTinh;
	}

	public void setMaTinh(java.lang.String maTinh) {
		this.maTinh = maTinh;
	}

	public java.lang.String getMaChiNhanh() {
		return maChiNhanh;
	}

	public void setMaChiNhanh(java.lang.String maChiNhanh) {
		this.maChiNhanh = maChiNhanh;
	}

	public java.lang.String getMaNv() {
		return maNv;
	}

	public void setMaNv(java.lang.String maNv) {
		this.maNv = maNv;
	}
	

	public java.lang.Float getSlThucTeTC() {
		return slThucTeTC;
	}

	public void setSlThucTeTC(java.lang.Float slThucTeTC) {
		this.slThucTeTC = slThucTeTC;
	}

	public java.lang.Float getTtThucTeTC() {
		return ttThucTeTC;
	}

	public void setTtThucTeTC(java.lang.Float ttThucTeTC) {
		this.ttThucTeTC = ttThucTeTC;
	}

	public java.lang.Float getSlThuHoi() {
		return slThuHoi;
	}

	public void setSlThuHoi(java.lang.Float slThuHoi) {
		this.slThuHoi = slThuHoi;
	}

	public java.lang.Float getTtThuHoi() {
		return ttThuHoi;
	}

	public void setTtThuHoi(java.lang.Float ttThuHoi) {
		this.ttThuHoi = ttThuHoi;
	}

	public java.lang.Float getSlTienDen() {
		return slTienDen;
	}

	public void setSlTienDen(java.lang.Float slTienDen) {
		this.slTienDen = slTienDen;
	}

	public java.lang.Float getTtTienDen() {
		return ttTienDen;
	}

	public void setTtTienDen(java.lang.Float ttTienDen) {
		this.ttTienDen = ttTienDen;
	}

	@Override
    public TblQltsThucXuatTheoKyDTO toDTO() {
        TblQltsThucXuatTheoKyDTO tblQltsThucXuatTheoKyDTO = new TblQltsThucXuatTheoKyDTO(); 
        tblQltsThucXuatTheoKyDTO.setTblQltsThucXuatTheoKyId(this.tblQltsThucXuatTheoKyId);		
        tblQltsThucXuatTheoKyDTO.setSoPhieuXuat(this.soPhieuXuat);		
        tblQltsThucXuatTheoKyDTO.setNgayXuat(this.ngayXuat);		
        tblQltsThucXuatTheoKyDTO.setNoiDung(this.noiDung);		
        tblQltsThucXuatTheoKyDTO.setMaKhoXuat(this.maKhoXuat);		
        tblQltsThucXuatTheoKyDTO.setTenVatTu(this.tenVatTu);		
        tblQltsThucXuatTheoKyDTO.setMaVatTu(this.maVatTu);		
        tblQltsThucXuatTheoKyDTO.setDonViTinh(this.donViTinh);		
        tblQltsThucXuatTheoKyDTO.setSoLuongXuat(this.soLuongXuat);		
        tblQltsThucXuatTheoKyDTO.setDonGia(this.donGia);		
        tblQltsThucXuatTheoKyDTO.setThanhTien(this.thanhTien);		
        tblQltsThucXuatTheoKyDTO.setMaCongTrinh(this.maCongTrinh);		
        tblQltsThucXuatTheoKyDTO.setNguoiNhanHang(this.nguoiNhanHang);		
        tblQltsThucXuatTheoKyDTO.setSoLuongYeuCau(this.soLuongYeuCau);		
        tblQltsThucXuatTheoKyDTO.setLyDo(this.lyDo);		
        tblQltsThucXuatTheoKyDTO.setDienGiai(this.dienGiai);		
        tblQltsThucXuatTheoKyDTO.setTenKhoXuat(this.tenKhoXuat);		
        tblQltsThucXuatTheoKyDTO.setMaKhoNhan(this.maKhoNhan);		
        tblQltsThucXuatTheoKyDTO.setXoa(this.xoa);		
        tblQltsThucXuatTheoKyDTO.setHoatDong(this.hoatDong);		
        tblQltsThucXuatTheoKyDTO.setSoPhieuXuatBenLogistic(this.soPhieuXuatBenLogistic);
        tblQltsThucXuatTheoKyDTO.setKhoNhan(this.khoNhan);
        tblQltsThucXuatTheoKyDTO.setDonViNhan(this.donViNhan);
        tblQltsThucXuatTheoKyDTO.setMaChiNhanh(this.maChiNhanh);
        tblQltsThucXuatTheoKyDTO.setMaTinh(this.maTinh);
        tblQltsThucXuatTheoKyDTO.setMaNv(this.maNv);
        tblQltsThucXuatTheoKyDTO.setSlThucTeTC(this.slThucTeTC);
        tblQltsThucXuatTheoKyDTO.setTtThucTeTC(this.ttThucTeTC);
        tblQltsThucXuatTheoKyDTO.setSlThuHoi(this.slThuHoi);
        tblQltsThucXuatTheoKyDTO.setTtThuHoi(this.ttThuHoi);
        tblQltsThucXuatTheoKyDTO.setSlTienDen(this.slTienDen);
        tblQltsThucXuatTheoKyDTO.setTtTienDen(this.ttTienDen);
        return tblQltsThucXuatTheoKyDTO;
    }
}
