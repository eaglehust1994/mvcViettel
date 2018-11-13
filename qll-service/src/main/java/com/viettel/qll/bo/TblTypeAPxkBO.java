package com.viettel.qll.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.viettel.qll.dto.TblTypeAPxkDTO;
import com.viettel.service.base.model.BaseFWModelImpl;

@SuppressWarnings("serial")
@Entity(name="com.viettel.qll.bo.TblTypeAPxkBO")
@Table(name = "TBL_TYPE_A_PXK")
/**
 *
 * @author: hailh10
 */
public class TblTypeAPxkBO extends BaseFWModelImpl {
     
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "TBL_TYPE_A_PXK_SEQ") })
	@Column(name = "TBL_TYPE_A_PXK_ID", length = 22)
	private java.lang.Long tblTypeAPxkId;
	@Column(name = "MA_PHIEU_XUAT", length = 1500)
	private java.lang.String maPhieuXuat;
	@Column(name = "MA_LENH_XUAT", length = 1500)
	private java.lang.String maLenhXuat;
	@Column(name = "MA_YC_XUAT", length = 1500)
	private java.lang.String maYcXuat;
	@Column(name = "NGUOI_TAO_YC", length = 1500)
	private java.lang.String nguoiTaoYc;
	@Column(name = "NGUOI_PHE_DUYET", length = 1500)
	private java.lang.String nguoiPheDuyet;
	@Column(name = "NGAY_TAO", length = 7)
	private java.util.Date ngayTao;
	@Column(name = "NGAY_THUC_XUAT", length = 7)
	private java.util.Date ngayThucXuat;
	@Column(name = "KHO_XUAT", length = 1500)
	private java.lang.String khoXuat;
	@Column(name = "LY_DO_XUAT", length = 1500)
	private java.lang.String lyDoXuat;
	@Column(name = "KHO_NHAN", length = 1500)
	private java.lang.String khoNhan;
	@Column(name = "DON_VI_NHAN", length = 1500)
	private java.lang.String donViNhan;
	@Column(name = "CONG_TRINH_NHAN", length = 1500)
	private java.lang.String congTrinhNhan;
	@Column(name = "MA_TRAM_NHAN", length = 1500)
	private java.lang.String maTramNhan;
	@Column(name = "TINH_TRANG", length = 1500)
	private java.lang.String tinhTrang;
	@Column(name = "LY_DO_TU_CHOI", length = 1500)
	private java.lang.String lyDoTuChoi;
	@Column(name = "MA_HDXL", length = 1500)
	private java.lang.String maHdxl;
	@Column(name = "TINH_TRANG_KY_CA", length = 1500)
	private java.lang.String tinhTrangKyCa;
	@Column(name = "XOA", length = 22)
	private java.lang.Long xoa;

	@Column(name = "MA_CHI_NHANH", length =250)
	private java.lang.String maChiNhanh;
	
	@Column(name = "MA_TINH", length = 250)
	private java.lang.String maTinh;
	
	@Column(name = "MA_NV", length = 250)
	private java.lang.String maNv;
	
	
	@Column(name = "KT_KV_TTKT", length = 250)
	private java.lang.Long ktKvttkt;
	
	@Column(name = "PATH_IMG", length = 2000)
	private java.lang.String pathImg;
	
	@Column(name = "CHECK_NHAP_LIEU", length = 250)
	private java.lang.Long checkNhapLieu;
	
	
	@Column(name = "NGAY_GAN_DON_VI", length = 7)
	private java.util.Date ngayGanDonVi;
	
	@Column(name = "NGAY_GAN_NV", length = 7)
	private java.util.Date ngayGanNv;
	

	
	public java.lang.Long getTblTypeAPxkId(){
		return tblTypeAPxkId;
	}
	
	public void setTblTypeAPxkId(java.lang.Long tblTypeAPxkId)
	{
		this.tblTypeAPxkId = tblTypeAPxkId;
	}
	
	public java.lang.String getMaPhieuXuat(){
		return maPhieuXuat;
	}
	
	public void setMaPhieuXuat(java.lang.String maPhieuXuat)
	{
		this.maPhieuXuat = maPhieuXuat;
	}
	
	public java.lang.String getMaLenhXuat(){
		return maLenhXuat;
	}
	
	public void setMaLenhXuat(java.lang.String maLenhXuat)
	{
		this.maLenhXuat = maLenhXuat;
	}
	
	public java.lang.String getMaYcXuat(){
		return maYcXuat;
	}
	
	public void setMaYcXuat(java.lang.String maYcXuat)
	{
		this.maYcXuat = maYcXuat;
	}
	
	public java.lang.String getNguoiTaoYc(){
		return nguoiTaoYc;
	}
	
	public void setNguoiTaoYc(java.lang.String nguoiTaoYc)
	{
		this.nguoiTaoYc = nguoiTaoYc;
	}
	
	public java.lang.String getNguoiPheDuyet(){
		return nguoiPheDuyet;
	}
	
	public void setNguoiPheDuyet(java.lang.String nguoiPheDuyet)
	{
		this.nguoiPheDuyet = nguoiPheDuyet;
	}
	
	public java.util.Date getNgayTao(){
		return ngayTao;
	}
	
	public void setNgayTao(java.util.Date ngayTao)
	{
		this.ngayTao = ngayTao;
	}
	
	public java.util.Date getNgayThucXuat(){
		return ngayThucXuat;
	}
	
	public void setNgayThucXuat(java.util.Date ngayThucXuat)
	{
		this.ngayThucXuat = ngayThucXuat;
	}
	
	public java.lang.String getKhoXuat(){
		return khoXuat;
	}
	
	public void setKhoXuat(java.lang.String khoXuat)
	{
		this.khoXuat = khoXuat;
	}
	
	public java.lang.String getLyDoXuat(){
		return lyDoXuat;
	}
	
	public void setLyDoXuat(java.lang.String lyDoXuat)
	{
		this.lyDoXuat = lyDoXuat;
	}
	
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
	
	public java.lang.String getCongTrinhNhan(){
		return congTrinhNhan;
	}
	
	public void setCongTrinhNhan(java.lang.String congTrinhNhan)
	{
		this.congTrinhNhan = congTrinhNhan;
	}
	
	public java.lang.String getMaTramNhan(){
		return maTramNhan;
	}
	
	public void setMaTramNhan(java.lang.String maTramNhan)
	{
		this.maTramNhan = maTramNhan;
	}
	
	public java.lang.String getTinhTrang(){
		return tinhTrang;
	}
	
	public void setTinhTrang(java.lang.String tinhTrang)
	{
		this.tinhTrang = tinhTrang;
	}
	
	public java.lang.String getLyDoTuChoi(){
		return lyDoTuChoi;
	}
	
	public void setLyDoTuChoi(java.lang.String lyDoTuChoi)
	{
		this.lyDoTuChoi = lyDoTuChoi;
	}
	
	public java.lang.String getMaHdxl(){
		return maHdxl;
	}
	
	public void setMaHdxl(java.lang.String maHdxl)
	{
		this.maHdxl = maHdxl;
	}
	
	public java.lang.String getTinhTrangKyCa(){
		return tinhTrangKyCa;
	}
	
	public void setTinhTrangKyCa(java.lang.String tinhTrangKyCa)
	{
		this.tinhTrangKyCa = tinhTrangKyCa;
	}
	
	public java.lang.Long getXoa(){
		return xoa;
	}
	
	public void setXoa(java.lang.Long xoa)
	{
		this.xoa = xoa;
	}
	
    public java.lang.String getMaChiNhanh() {
		return maChiNhanh;
	}

	public void setMaChiNhanh(java.lang.String maChiNhanh) {
		this.maChiNhanh = maChiNhanh;
	}

	public java.lang.String getMaTinh() {
		return maTinh;
	}

	public void setMaTinh(java.lang.String maTinh) {
		this.maTinh = maTinh;
	}

	public java.lang.String getMaNv() {
		return maNv;
	}

	public void setMaNv(java.lang.String maNv) {
		this.maNv = maNv;
	}

	public java.lang.Long getKtKvttkt() {
		return ktKvttkt;
	}

	public void setKtKvttkt(java.lang.Long ktKvttkt) {
		this.ktKvttkt = ktKvttkt;
	}
	

	public java.lang.String getPathImg() {
		return pathImg;
	}

	public void setPathImg(java.lang.String pathImg) {
		this.pathImg = pathImg;
	}
	
	

	public java.lang.Long getCheckNhapLieu() {
		return checkNhapLieu;
	}

	public void setCheckNhapLieu(java.lang.Long checkNhapLieu) {
		this.checkNhapLieu = checkNhapLieu;
	}

	@Override
    public TblTypeAPxkDTO toDTO() {
        TblTypeAPxkDTO tblTypeAPxkDTO = new TblTypeAPxkDTO(); 
        tblTypeAPxkDTO.setTblTypeAPxkId(this.tblTypeAPxkId);		
        tblTypeAPxkDTO.setMaPhieuXuat(this.maPhieuXuat);		
        tblTypeAPxkDTO.setMaLenhXuat(this.maLenhXuat);		
        tblTypeAPxkDTO.setMaYcXuat(this.maYcXuat);		
        tblTypeAPxkDTO.setNguoiTaoYc(this.nguoiTaoYc);		
        tblTypeAPxkDTO.setNguoiPheDuyet(this.nguoiPheDuyet);		
        tblTypeAPxkDTO.setNgayTao(this.ngayTao);		
        tblTypeAPxkDTO.setNgayThucXuat(this.ngayThucXuat);		
        tblTypeAPxkDTO.setKhoXuat(this.khoXuat);		
        tblTypeAPxkDTO.setLyDoXuat(this.lyDoXuat);		
        tblTypeAPxkDTO.setKhoNhan(this.khoNhan);		
        tblTypeAPxkDTO.setDonViNhan(this.donViNhan);		
        tblTypeAPxkDTO.setCongTrinhNhan(this.congTrinhNhan);		
        tblTypeAPxkDTO.setMaTramNhan(this.maTramNhan);		
        tblTypeAPxkDTO.setTinhTrang(this.tinhTrang);		
        tblTypeAPxkDTO.setLyDoTuChoi(this.lyDoTuChoi);		
        tblTypeAPxkDTO.setMaHdxl(this.maHdxl);		
        tblTypeAPxkDTO.setTinhTrangKyCa(this.tinhTrangKyCa);		
        tblTypeAPxkDTO.setXoa(this.xoa);	
        tblTypeAPxkDTO.setMaNv(this.maNv);
        tblTypeAPxkDTO.setMaChiNhanh(this.maChiNhanh);
        tblTypeAPxkDTO.setMaTinh(this.maTinh);
        tblTypeAPxkDTO.setKtKvttkt(this.ktKvttkt);
        tblTypeAPxkDTO.setPathImg(this.pathImg);
        tblTypeAPxkDTO.setCheckNhapLieu(this.checkNhapLieu);
        tblTypeAPxkDTO.setNgayGanDonVi(this.ngayGanDonVi);
        tblTypeAPxkDTO.setNgayGanNv(this.ngayGanNv);
        return tblTypeAPxkDTO;
    }
}
