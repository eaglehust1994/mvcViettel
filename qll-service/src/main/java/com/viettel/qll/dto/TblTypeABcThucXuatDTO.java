package com.viettel.qll.dto;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.viettel.erp.constant.ApplicationConstants;
import com.viettel.erp.utils.CustomJsonDateDeserializer;
import com.viettel.erp.utils.CustomJsonDateSerializer;
import com.viettel.qll.bo.TblTypeABcThucXuatBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;

/**
 *
 * @author hailh10
 */
@SuppressWarnings("serial")
@XmlRootElement(name = "TBL_TYPE_A_BC_THUC_XUATBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TblTypeABcThucXuatDTO extends BaseFWDTOImpl<TblTypeABcThucXuatBO> {

	private java.lang.String khoNhan;
	private java.lang.String donViNhan;
	private java.lang.String nguoiNhan;
	private java.lang.Long xoa;
	private java.lang.Long tblTypeABcThucXuatId;
	private java.lang.String soPhieuXuat;
	private java.lang.String soPhieuXuatBenLogistic;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayXuat;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayXuatFrom;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayXuatTo;
	private java.lang.String noiDung;
	private java.lang.String maKhoXuat;
	private java.lang.String tenVatTu;
	private java.lang.String maVatTu;
	private java.lang.String donViTinh;
	private java.lang.Float soLuongXuat;
	private java.lang.Float donGia;
	private java.lang.Float thanhTien;
	private java.lang.String maCongTrinh;
	private java.lang.String soLuongYc;
	private java.lang.String lyDo;
	private java.lang.String dienGiai;
	private java.lang.String tenKhoXuat;
	private java.lang.String maKhoNhan;
	private String text;
	private int start;
	private int maxResult;

    @Override
    public TblTypeABcThucXuatBO toModel() {
        TblTypeABcThucXuatBO tblTypeABcThucXuatBO = new TblTypeABcThucXuatBO();
        tblTypeABcThucXuatBO.setKhoNhan(this.khoNhan);
        tblTypeABcThucXuatBO.setDonViNhan(this.donViNhan);
        tblTypeABcThucXuatBO.setNguoiNhan(this.nguoiNhan);
        tblTypeABcThucXuatBO.setXoa(this.xoa);
        tblTypeABcThucXuatBO.setTblTypeABcThucXuatId(this.tblTypeABcThucXuatId);
        tblTypeABcThucXuatBO.setSoPhieuXuat(this.soPhieuXuat);
        tblTypeABcThucXuatBO.setSoPhieuXuatBenLogistic(this.soPhieuXuatBenLogistic);
		if (this.soPhieuXuatBenLogistic == null || "".equalsIgnoreCase(this.soPhieuXuatBenLogistic) || "N".equalsIgnoreCase(this.soPhieuXuatBenLogistic)) {
			tblTypeABcThucXuatBO.setSoPhieuXuatBenLogistic("N");
		} else {
			tblTypeABcThucXuatBO.setSoPhieuXuatBenLogistic("Y");
		}
        tblTypeABcThucXuatBO.setNgayXuat(this.ngayXuat);
        tblTypeABcThucXuatBO.setNoiDung(this.noiDung);
        tblTypeABcThucXuatBO.setMaKhoXuat(this.maKhoXuat);
        tblTypeABcThucXuatBO.setTenVatTu(this.tenVatTu);
        tblTypeABcThucXuatBO.setMaVatTu(this.maVatTu);
        tblTypeABcThucXuatBO.setDonViTinh(this.donViTinh);
        tblTypeABcThucXuatBO.setSoLuongXuat(this.soLuongXuat);
        tblTypeABcThucXuatBO.setDonGia(this.donGia);
        tblTypeABcThucXuatBO.setThanhTien(this.thanhTien);
        tblTypeABcThucXuatBO.setMaCongTrinh(this.maCongTrinh);
        tblTypeABcThucXuatBO.setSoLuongYc(this.soLuongYc);
        tblTypeABcThucXuatBO.setLyDo(this.lyDo);
        tblTypeABcThucXuatBO.setDienGiai(this.dienGiai);
        tblTypeABcThucXuatBO.setTenKhoXuat(this.tenKhoXuat);
        tblTypeABcThucXuatBO.setMaKhoNhan(this.maKhoNhan);
        return tblTypeABcThucXuatBO;
    }

	@JsonProperty("khoNhan")
    public java.lang.String getKhoNhan(){
		return khoNhan;
    }
	
    public void setKhoNhan(java.lang.String khoNhan){
		this.khoNhan = khoNhan;
    }	
	
	@JsonProperty("donViNhan")
    public java.lang.String getDonViNhan(){
		return donViNhan;
    }
	
    public void setDonViNhan(java.lang.String donViNhan){
		this.donViNhan = donViNhan;
    }	
	
	@JsonProperty("nguoiNhan")
    public java.lang.String getNguoiNhan(){
		return nguoiNhan;
    }
	
    public void setNguoiNhan(java.lang.String nguoiNhan){
		this.nguoiNhan = nguoiNhan;
    }	
	
	@JsonProperty("xoa")
    public java.lang.Long getXoa(){
		return xoa;
    }
	
    public void setXoa(java.lang.Long xoa){
		this.xoa = xoa;
    }	
	
    @Override
     public Long getFWModelId() {
        return tblTypeABcThucXuatId;
    }
   
    @Override
    public String catchName() {
        return getTblTypeABcThucXuatId().toString();
    }
	
	@JsonProperty("tblTypeABcThucXuatId")
    public java.lang.Long getTblTypeABcThucXuatId(){
		return tblTypeABcThucXuatId;
    }
	
    public void setTblTypeABcThucXuatId(java.lang.Long tblTypeABcThucXuatId){
		this.tblTypeABcThucXuatId = tblTypeABcThucXuatId;
    }	
	
	@JsonProperty("soPhieuXuat")
    public java.lang.String getSoPhieuXuat(){
		return soPhieuXuat;
    }
	
    public void setSoPhieuXuat(java.lang.String soPhieuXuat){
		this.soPhieuXuat = soPhieuXuat;
    }	
	
	@JsonProperty("soPhieuXuatBenLogistic")
    public java.lang.String getSoPhieuXuatBenLogistic(){
		return soPhieuXuatBenLogistic;
    }
	
    public void setSoPhieuXuatBenLogistic(java.lang.String soPhieuXuatBenLogistic){
		this.soPhieuXuatBenLogistic = soPhieuXuatBenLogistic;
    }	
	
	@JsonProperty("ngayXuat")
    public java.util.Date getNgayXuat(){
		return ngayXuat;
    }
	
    public void setNgayXuat(java.util.Date ngayXuat){
		this.ngayXuat = ngayXuat;
    }	
	
	public java.util.Date getNgayXuatFrom() {
    	return ngayXuatFrom;
    }
	
    public void setNgayXuatFrom(java.util.Date ngayXuatFrom) {
    	this.ngayXuatFrom = ngayXuatFrom;
    }
	
	public java.util.Date getNgayXuatTo() {
    	return ngayXuatTo;
    }
	
    public void setNgayXuatTo(java.util.Date ngayXuatTo) {
    	this.ngayXuatTo = ngayXuatTo;
    }
	
	@JsonProperty("noiDung")
    public java.lang.String getNoiDung(){
		return noiDung;
    }
	
    public void setNoiDung(java.lang.String noiDung){
		this.noiDung = noiDung;
    }	
	
	@JsonProperty("maKhoXuat")
    public java.lang.String getMaKhoXuat(){
		return maKhoXuat;
    }
	
    public void setMaKhoXuat(java.lang.String maKhoXuat){
		this.maKhoXuat = maKhoXuat;
    }	
	
	@JsonProperty("tenVatTu")
    public java.lang.String getTenVatTu(){
		return tenVatTu;
    }
	
    public void setTenVatTu(java.lang.String tenVatTu){
		this.tenVatTu = tenVatTu;
    }	
	
	@JsonProperty("maVatTu")
    public java.lang.String getMaVatTu(){
		return maVatTu;
    }
	
    public void setMaVatTu(java.lang.String maVatTu){
		this.maVatTu = maVatTu;
    }	
	
	@JsonProperty("donViTinh")
    public java.lang.String getDonViTinh(){
		return donViTinh;
    }
	
    public void setDonViTinh(java.lang.String donViTinh){
		this.donViTinh = donViTinh;
    }	
	
	@JsonProperty("soLuongXuat")
    public java.lang.Float getSoLuongXuat(){
		return soLuongXuat;
    }
	
    public void setSoLuongXuat(java.lang.Float soLuongXuat){
		this.soLuongXuat = soLuongXuat;
    }	
	
	@JsonProperty("donGia")
    public java.lang.Float getDonGia(){
		return donGia;
    }
	
    public void setDonGia(java.lang.Float donGia){
		this.donGia = donGia;
    }	
	
	@JsonProperty("thanhTien")
    public java.lang.Float getThanhTien(){
		return thanhTien;
    }
	
    public void setThanhTien(java.lang.Float thanhTien){
		this.thanhTien = thanhTien;
    }	
	
	@JsonProperty("maCongTrinh")
    public java.lang.String getMaCongTrinh(){
		return maCongTrinh;
    }
	
    public void setMaCongTrinh(java.lang.String maCongTrinh){
		this.maCongTrinh = maCongTrinh;
    }	
	
	@JsonProperty("soLuongYc")
    public java.lang.String getSoLuongYc(){
		return soLuongYc;
    }
	
    public void setSoLuongYc(java.lang.String soLuongYc){
		this.soLuongYc = soLuongYc;
    }	
	
	@JsonProperty("lyDo")
    public java.lang.String getLyDo(){
		return lyDo;
    }
	
    public void setLyDo(java.lang.String lyDo){
		this.lyDo = lyDo;
    }	
	
	@JsonProperty("dienGiai")
    public java.lang.String getDienGiai(){
		return dienGiai;
    }
	
    public void setDienGiai(java.lang.String dienGiai){
		this.dienGiai = dienGiai;
    }	
	
	@JsonProperty("tenKhoXuat")
    public java.lang.String getTenKhoXuat(){
		return tenKhoXuat;
    }
	
    public void setTenKhoXuat(java.lang.String tenKhoXuat){
		this.tenKhoXuat = tenKhoXuat;
    }	
	
	@JsonProperty("maKhoNhan")
    public java.lang.String getMaKhoNhan(){
		return maKhoNhan;
    }
	
    public void setMaKhoNhan(java.lang.String maKhoNhan){
		this.maKhoNhan = maKhoNhan;
    }	
	
	@JsonProperty("start")
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	@JsonProperty("maxResult")
	public int getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}
	
}
