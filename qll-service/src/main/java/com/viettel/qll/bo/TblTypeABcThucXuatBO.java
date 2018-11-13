package com.viettel.qll.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.viettel.qll.dto.TblTypeABcThucXuatDTO;
import com.viettel.service.base.model.BaseFWModelImpl;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.erp.bo.TblTypeABcThucXuatBO")
@Table(name = "TBL_TYPE_A_BC_THUC_XUAT")
/**
 *
 * @author: hailh10
 */
public class TblTypeABcThucXuatBO extends BaseFWModelImpl {
     
	@Column(name = "KHO_NHAN", length = 1500)
	private java.lang.String khoNhan;
	@Column(name = "DON_VI_NHAN", length = 1500)
	private java.lang.String donViNhan;
	@Column(name = "NGUOI_NHAN", length = 1500)
	private java.lang.String nguoiNhan;
	@Column(name = "XOA", length = 22)
	private java.lang.Long xoa;
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "TBL_TYPE_A_BC_THUC_XUAT_SEQ") })
	@Column(name = "TBL_TYPE_A_BC_THUC_XUAT_ID", length = 22)
	private java.lang.Long tblTypeABcThucXuatId;
	@Column(name = "SO_PHIEU_XUAT", length = 1500)
	private java.lang.String soPhieuXuat;
	@Column(name = "SO_PHIEU_XUAT_BEN_LOGISTIC", length = 1500)
	private java.lang.String soPhieuXuatBenLogistic;
	@Column(name = "NGAY_XUAT", length = 7)
	private java.util.Date ngayXuat;
	@Column(name = "NOI_DUNG", length = 1500)
	private java.lang.String noiDung;
	@Column(name = "MA_KHO_XUAT", length = 1500)
	private java.lang.String maKhoXuat;
	@Column(name = "TEN_VAT_TU", length = 1500)
	private java.lang.String tenVatTu;
	@Column(name = "MA_VAT_TU", length = 1500)
	private java.lang.String maVatTu;
	@Column(name = "DON_VI_TINH", length = 1500)
	private java.lang.String donViTinh;
	@Column(name = "SO_LUONG_XUAT", length = 22)
	private java.lang.Float soLuongXuat;
	@Column(name = "DON_GIA", length = 22)
	private java.lang.Float donGia;
	@Column(name = "THANH_TIEN", length = 22)
	private java.lang.Float thanhTien;
	@Column(name = "MA_CONG_TRINH", length = 1500)
	private java.lang.String maCongTrinh;
	@Column(name = "SO_LUONG_YC", length = 1500)
	private java.lang.String soLuongYc;
	@Column(name = "LY_DO", length = 1500)
	private java.lang.String lyDo;
	@Column(name = "DIEN_GIAI", length = 1500)
	private java.lang.String dienGiai;
	@Column(name = "TEN_KHO_XUAT", length = 1500)
	private java.lang.String tenKhoXuat;
	@Column(name = "MA_KHO_NHAN", length = 1500)
	private java.lang.String maKhoNhan;

	
	public java.lang.String getKhoNhan(){
		return khoNhan;
	}
	
	public void setKhoNhan(java.lang.String khoNhan)
	{
		this.khoNhan = khoNhan;
	}
	
	public java.lang.String getDonViNhan(){
		return donViNhan;
	}
	
	public void setDonViNhan(java.lang.String donViNhan)
	{
		this.donViNhan = donViNhan;
	}
	
	public java.lang.String getNguoiNhan(){
		return nguoiNhan;
	}
	
	public void setNguoiNhan(java.lang.String nguoiNhan)
	{
		this.nguoiNhan = nguoiNhan;
	}
	
	public java.lang.Long getXoa(){
		return xoa;
	}
	
	public void setXoa(java.lang.Long xoa)
	{
		this.xoa = xoa;
	}
	
	public java.lang.Long getTblTypeABcThucXuatId(){
		return tblTypeABcThucXuatId;
	}
	
	public void setTblTypeABcThucXuatId(java.lang.Long tblTypeABcThucXuatId)
	{
		this.tblTypeABcThucXuatId = tblTypeABcThucXuatId;
	}
	
	public java.lang.String getSoPhieuXuat(){
		return soPhieuXuat;
	}
	
	public void setSoPhieuXuat(java.lang.String soPhieuXuat)
	{
		this.soPhieuXuat = soPhieuXuat;
	}
	
	public java.lang.String getSoPhieuXuatBenLogistic(){
		return soPhieuXuatBenLogistic;
	}
	
	public void setSoPhieuXuatBenLogistic(java.lang.String soPhieuXuatBenLogistic)
	{
		this.soPhieuXuatBenLogistic = soPhieuXuatBenLogistic;
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
	
	public java.lang.String getSoLuongYc(){
		return soLuongYc;
	}
	
	public void setSoLuongYc(java.lang.String soLuongYc)
	{
		this.soLuongYc = soLuongYc;
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
   
    @Override
    public TblTypeABcThucXuatDTO toDTO() {
        TblTypeABcThucXuatDTO tblTypeABcThucXuatDTO = new TblTypeABcThucXuatDTO(); 
        tblTypeABcThucXuatDTO.setKhoNhan(this.khoNhan);		
        tblTypeABcThucXuatDTO.setDonViNhan(this.donViNhan);		
        tblTypeABcThucXuatDTO.setNguoiNhan(this.nguoiNhan);		
        tblTypeABcThucXuatDTO.setXoa(this.xoa);		
        tblTypeABcThucXuatDTO.setTblTypeABcThucXuatId(this.tblTypeABcThucXuatId);		
        tblTypeABcThucXuatDTO.setSoPhieuXuat(this.soPhieuXuat);		
        tblTypeABcThucXuatDTO.setSoPhieuXuatBenLogistic(this.soPhieuXuatBenLogistic);		
        tblTypeABcThucXuatDTO.setNgayXuat(this.ngayXuat);		
        tblTypeABcThucXuatDTO.setNoiDung(this.noiDung);		
        tblTypeABcThucXuatDTO.setMaKhoXuat(this.maKhoXuat);		
        tblTypeABcThucXuatDTO.setTenVatTu(this.tenVatTu);		
        tblTypeABcThucXuatDTO.setMaVatTu(this.maVatTu);		
        tblTypeABcThucXuatDTO.setDonViTinh(this.donViTinh);		
        tblTypeABcThucXuatDTO.setSoLuongXuat(this.soLuongXuat);		
        tblTypeABcThucXuatDTO.setDonGia(this.donGia);		
        tblTypeABcThucXuatDTO.setThanhTien(this.thanhTien);		
        tblTypeABcThucXuatDTO.setMaCongTrinh(this.maCongTrinh);		
        tblTypeABcThucXuatDTO.setSoLuongYc(this.soLuongYc);		
        tblTypeABcThucXuatDTO.setLyDo(this.lyDo);		
        tblTypeABcThucXuatDTO.setDienGiai(this.dienGiai);		
        tblTypeABcThucXuatDTO.setTenKhoXuat(this.tenKhoXuat);		
        tblTypeABcThucXuatDTO.setMaKhoNhan(this.maKhoNhan);		
        return tblTypeABcThucXuatDTO;
    }
}
