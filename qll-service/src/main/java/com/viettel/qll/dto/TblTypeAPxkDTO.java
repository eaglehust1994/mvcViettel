package com.viettel.qll.dto;

import java.util.List;

import javax.persistence.Column;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.viettel.erp.utils.CustomJsonDateDeserializer;
import com.viettel.erp.utils.CustomJsonDateSerializer;
import com.viettel.qll.bo.TblTypeAPxkBO;

/**
 *
 * @author hailh10
 */
@SuppressWarnings("serial")
@XmlRootElement(name = "TBL_TYPE_A_PXKBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TblTypeAPxkDTO extends QllBaseDTO<TblTypeAPxkBO> {

	private java.lang.Long tblTypeAPxkId;
	private java.lang.String maPhieuXuat;
	private java.lang.String maLenhXuat;
	private java.lang.String maYcXuat;
	private java.lang.String nguoiTaoYc;
	private java.lang.String nguoiPheDuyet;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayTao;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayTaoFrom;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayTaoTo;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayThucXuat;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayThucXuatFrom;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayThucXuatTo;
	private java.lang.String khoXuat;
	private java.lang.String lyDoXuat;
	private java.lang.String khoNhan;
	private java.lang.String donViNhan;
	private java.lang.String congTrinhNhan;
	private java.lang.String maTramNhan;
	private java.lang.String tinhTrang;
	private java.lang.String lyDoTuChoi;
	private java.lang.String maHdxl;
	private java.lang.String tinhTrangKyCa;
	private java.lang.Long xoa;
	private String text;
	private int start;
	private int maxResult;
	private Long ktKvttkt;
	private java.lang.String pathImg;
	private java.lang.Long checkNhapLieu;
	
	private String exThang;
	private String exNam;
	
	private String soPhieuXuat;
	
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayXuat;
	private java.lang.String tenVatTu;
	private java.lang.String maVatTu;
	private java.lang.String donViTinh;
	private java.lang.Float soLuongXuat;
	private java.lang.Float donGia;
	private java.lang.Float thanhTien;
	private java.lang.String maCongTrinh;
	private java.lang.Float slThucTeTC;

	private java.lang.Float ttThucTeTC;

	private java.lang.Float slThuHoi;

	private java.lang.Float ttThuHoi;

	private java.lang.Float slTienDen;

	private java.lang.Float ttTienDen;
	
	
	private java.lang.String maChiNhanh;
	private java.lang.String maTinh;
	private java.lang.String maNv;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayGanDonVi;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayGanNv;
	
	private Long checkDv;
	private Long checkNl;
	private Long checkXnnl;
	private Long checkTaiAnh;
	private String donVi;
	private Long checkGanNV;

	private List<Long> gt1;
	private List<Long> gt2;
	private int checkGt;

    @Override
    public TblTypeAPxkBO toModel() {
        TblTypeAPxkBO tblTypeAPxkBO = new TblTypeAPxkBO();
        tblTypeAPxkBO.setTblTypeAPxkId(this.tblTypeAPxkId);
        tblTypeAPxkBO.setMaPhieuXuat(this.maPhieuXuat);
        tblTypeAPxkBO.setMaLenhXuat(this.maLenhXuat);
        tblTypeAPxkBO.setMaYcXuat(this.maYcXuat);
        tblTypeAPxkBO.setNguoiTaoYc(this.nguoiTaoYc);
        tblTypeAPxkBO.setNguoiPheDuyet(this.nguoiPheDuyet);
        tblTypeAPxkBO.setNgayTao(this.ngayTao);
        tblTypeAPxkBO.setNgayThucXuat(this.ngayThucXuat);
        tblTypeAPxkBO.setKhoXuat(this.khoXuat);
        tblTypeAPxkBO.setLyDoXuat(this.lyDoXuat);
        tblTypeAPxkBO.setKhoNhan(this.khoNhan);
        tblTypeAPxkBO.setDonViNhan(this.donViNhan);
        tblTypeAPxkBO.setCongTrinhNhan(this.congTrinhNhan);
        tblTypeAPxkBO.setMaTramNhan(this.maTramNhan);
        tblTypeAPxkBO.setTinhTrang(this.tinhTrang);
        tblTypeAPxkBO.setLyDoTuChoi(this.lyDoTuChoi);
        tblTypeAPxkBO.setMaHdxl(this.maHdxl);
        tblTypeAPxkBO.setTinhTrangKyCa(this.tinhTrangKyCa);
        tblTypeAPxkBO.setXoa(this.xoa);
        tblTypeAPxkBO.setMaNv(this.maNv);
        tblTypeAPxkBO.setMaChiNhanh(this.maChiNhanh);
        tblTypeAPxkBO.setMaTinh(this.maTinh);
        tblTypeAPxkBO.setKtKvttkt(this.ktKvttkt);
        tblTypeAPxkBO.setPathImg(this.pathImg);
        tblTypeAPxkBO.setCheckNhapLieu(this.checkNhapLieu);
        return tblTypeAPxkBO;
    }

    @Override
     public Long getFWModelId() {
        return tblTypeAPxkId;
    }
   
    @Override
    public String catchName() {
        return getTblTypeAPxkId().toString();
    }
	
	@JsonProperty("tblTypeAPxkId")
    public java.lang.Long getTblTypeAPxkId(){
		return tblTypeAPxkId;
    }
	
    public void setTblTypeAPxkId(java.lang.Long tblTypeAPxkId){
		this.tblTypeAPxkId = tblTypeAPxkId;
    }	
	
	@JsonProperty("maPhieuXuat")
    public java.lang.String getMaPhieuXuat(){
		return maPhieuXuat;
    }
	
    public void setMaPhieuXuat(java.lang.String maPhieuXuat){
		this.maPhieuXuat = maPhieuXuat;
    }	
	
	@JsonProperty("maLenhXuat")
    public java.lang.String getMaLenhXuat(){
		return maLenhXuat;
    }
	
    public void setMaLenhXuat(java.lang.String maLenhXuat){
		this.maLenhXuat = maLenhXuat;
    }	
	
	@JsonProperty("maYcXuat")
    public java.lang.String getMaYcXuat(){
		return maYcXuat;
    }
	
    public void setMaYcXuat(java.lang.String maYcXuat){
		this.maYcXuat = maYcXuat;
    }	
	
	@JsonProperty("nguoiTaoYc")
    public java.lang.String getNguoiTaoYc(){
		return nguoiTaoYc;
    }
	
    public void setNguoiTaoYc(java.lang.String nguoiTaoYc){
		this.nguoiTaoYc = nguoiTaoYc;
    }	
	
	@JsonProperty("nguoiPheDuyet")
    public java.lang.String getNguoiPheDuyet(){
		return nguoiPheDuyet;
    }
	
    public void setNguoiPheDuyet(java.lang.String nguoiPheDuyet){
		this.nguoiPheDuyet = nguoiPheDuyet;
    }	
	
	@JsonProperty("ngayTao")
    public java.util.Date getNgayTao(){
		return ngayTao;
    }
	
    public void setNgayTao(java.util.Date ngayTao){
		this.ngayTao = ngayTao;
    }	
	
	public java.util.Date getNgayTaoFrom() {
    	return ngayTaoFrom;
    }
	
    public void setNgayTaoFrom(java.util.Date ngayTaoFrom) {
    	this.ngayTaoFrom = ngayTaoFrom;
    }
	
	public java.util.Date getNgayTaoTo() {
    	return ngayTaoTo;
    }
	
    public void setNgayTaoTo(java.util.Date ngayTaoTo) {
    	this.ngayTaoTo = ngayTaoTo;
    }
	
	@JsonProperty("ngayThucXuat")
    public java.util.Date getNgayThucXuat(){
		return ngayThucXuat;
    }
	
    public void setNgayThucXuat(java.util.Date ngayThucXuat){
		this.ngayThucXuat = ngayThucXuat;
    }	
	
	public java.util.Date getNgayThucXuatFrom() {
    	return ngayThucXuatFrom;
    }
	
    public void setNgayThucXuatFrom(java.util.Date ngayThucXuatFrom) {
    	this.ngayThucXuatFrom = ngayThucXuatFrom;
    }
	
	public java.util.Date getNgayThucXuatTo() {
    	return ngayThucXuatTo;
    }
	
    public void setNgayThucXuatTo(java.util.Date ngayThucXuatTo) {
    	this.ngayThucXuatTo = ngayThucXuatTo;
    }
	
	@JsonProperty("khoXuat")
    public java.lang.String getKhoXuat(){
		return khoXuat;
    }
	
    public void setKhoXuat(java.lang.String khoXuat){
		this.khoXuat = khoXuat;
    }	
	
	@JsonProperty("lyDoXuat")
    public java.lang.String getLyDoXuat(){
		return lyDoXuat;
    }
	
    public void setLyDoXuat(java.lang.String lyDoXuat){
		this.lyDoXuat = lyDoXuat;
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
	
	@JsonProperty("congTrinhNhan")
    public java.lang.String getCongTrinhNhan(){
		return congTrinhNhan;
    }
	
    public void setCongTrinhNhan(java.lang.String congTrinhNhan){
		this.congTrinhNhan = congTrinhNhan;
    }	
	
	@JsonProperty("maTramNhan")
    public java.lang.String getMaTramNhan(){
		return maTramNhan;
    }
	
    public void setMaTramNhan(java.lang.String maTramNhan){
		this.maTramNhan = maTramNhan;
    }	
	
	@JsonProperty("tinhTrang")
    public java.lang.String getTinhTrang(){
		return tinhTrang;
    }
	
    public void setTinhTrang(java.lang.String tinhTrang){
		this.tinhTrang = tinhTrang;
    }	
	
	@JsonProperty("lyDoTuChoi")
    public java.lang.String getLyDoTuChoi(){
		return lyDoTuChoi;
    }
	
    public void setLyDoTuChoi(java.lang.String lyDoTuChoi){
		this.lyDoTuChoi = lyDoTuChoi;
    }	
	
	@JsonProperty("maHdxl")
    public java.lang.String getMaHdxl(){
		return maHdxl;
    }
	
    public void setMaHdxl(java.lang.String maHdxl){
		this.maHdxl = maHdxl;
    }	
	
	@JsonProperty("tinhTrangKyCa")
    public java.lang.String getTinhTrangKyCa(){
		return tinhTrangKyCa;
    }
	
    public void setTinhTrangKyCa(java.lang.String tinhTrangKyCa){
		this.tinhTrangKyCa = tinhTrangKyCa;
    }	
	
	@JsonProperty("xoa")
    public java.lang.Long getXoa(){
		return xoa;
    }
	
    public void setXoa(java.lang.Long xoa){
		this.xoa = xoa;
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
	@JsonProperty("maChiNhanh")
	public java.lang.String getMaChiNhanh() {
		return maChiNhanh;
	}

	public void setMaChiNhanh(java.lang.String maChiNhanh) {
		this.maChiNhanh = maChiNhanh;
	}
	@JsonProperty("maTinh")
	public java.lang.String getMaTinh() {
		return maTinh;
	}

	public void setMaTinh(java.lang.String maTinh) {
		this.maTinh = maTinh;
	}
	@JsonProperty("maNv")
	public java.lang.String getMaNv() {
		return maNv;
	}

	public void setMaNv(java.lang.String maNv) {
		this.maNv = maNv;
	}

	public String getExThang() {
		return exThang;
	}

	public void setExThang(String exThang) {
		this.exThang = exThang;
	}

	public String getExNam() {
		return exNam;
	}

	public void setExNam(String exNam) {
		this.exNam = exNam;
	}

	public String getSoPhieuXuat() {
		return soPhieuXuat;
	}

	public void setSoPhieuXuat(String soPhieuXuat) {
		this.soPhieuXuat = soPhieuXuat;
	}

	public java.util.Date getNgayXuat() {
		return ngayXuat;
	}

	public void setNgayXuat(java.util.Date ngayXuat) {
		this.ngayXuat = ngayXuat;
	}

	public java.lang.String getTenVatTu() {
		return tenVatTu;
	}

	public void setTenVatTu(java.lang.String tenVatTu) {
		this.tenVatTu = tenVatTu;
	}

	public java.lang.String getMaVatTu() {
		return maVatTu;
	}

	public void setMaVatTu(java.lang.String maVatTu) {
		this.maVatTu = maVatTu;
	}

	public java.lang.String getDonViTinh() {
		return donViTinh;
	}

	public void setDonViTinh(java.lang.String donViTinh) {
		this.donViTinh = donViTinh;
	}

	public java.lang.Float getSoLuongXuat() {
		return soLuongXuat;
	}

	public void setSoLuongXuat(java.lang.Float soLuongXuat) {
		this.soLuongXuat = soLuongXuat;
	}

	public java.lang.Float getDonGia() {
		return donGia;
	}

	public void setDonGia(java.lang.Float donGia) {
		this.donGia = donGia;
	}

	public java.lang.Float getThanhTien() {
		return thanhTien;
	}

	public void setThanhTien(java.lang.Float thanhTien) {
		this.thanhTien = thanhTien;
	}

	public java.lang.String getMaCongTrinh() {
		return maCongTrinh;
	}

	public void setMaCongTrinh(java.lang.String maCongTrinh) {
		this.maCongTrinh = maCongTrinh;
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
	@JsonProperty("ktKvttkt")
	public Long getKtKvttkt() {
		return ktKvttkt;
	}

	public void setKtKvttkt(Long ktKvttkt) {
		this.ktKvttkt = ktKvttkt;
	}
	@JsonProperty("pathImg")
	public java.lang.String getPathImg() {
		return pathImg;
	}

	public void setPathImg(java.lang.String pathImg) {
		this.pathImg = pathImg;
	}
	@JsonProperty("checkNhapLieu")
	public java.lang.Long getCheckNhapLieu() {
		return checkNhapLieu;
	}

	public void setCheckNhapLieu(java.lang.Long checkNhapLieu) {
		this.checkNhapLieu = checkNhapLieu;
	}

	public Long getCheckDv() {
		return checkDv;
	}

	public void setCheckDv(Long checkDv) {
		this.checkDv = checkDv;
	}

	public Long getCheckNl() {
		return checkNl;
	}

	public void setCheckNl(Long checkNl) {
		this.checkNl = checkNl;
	}

	public Long getCheckXnnl() {
		return checkXnnl;
	}

	public void setCheckXnnl(Long checkXnnl) {
		this.checkXnnl = checkXnnl;
	}

	public Long getCheckTaiAnh() {
		return checkTaiAnh;
	}

	public void setCheckTaiAnh(Long checkTaiAnh) {
		this.checkTaiAnh = checkTaiAnh;
	}

	public String getDonVi() {
		return donVi;
	}

	public void setDonVi(String donVi) {
		this.donVi = donVi;
	}

	public Long getCheckGanNV() {
		return checkGanNV;
	}

	public void setCheckGanNV(Long checkGanNV) {
		this.checkGanNV = checkGanNV;
	}
	@JsonProperty("ngayGanDonVi")
	public java.util.Date getNgayGanDonVi() {
		return ngayGanDonVi;
	}

	public void setNgayGanDonVi(java.util.Date ngayGanDonVi) {
		this.ngayGanDonVi = ngayGanDonVi;
	}
	@JsonProperty("ngayGanNv")
	public java.util.Date getNgayGanNv() {
		return ngayGanNv;
	}

	public void setNgayGanNv(java.util.Date ngayGanNv) {
		this.ngayGanNv = ngayGanNv;
	}

	public List<Long> getGt1() {
		return gt1;
	}

	public void setGt1(List<Long> gt1) {
		this.gt1 = gt1;
	}

	public List<Long> getGt2() {
		return gt2;
	}

	public void setGt2(List<Long> gt2) {
		this.gt2 = gt2;
	}

	public int getCheckGt() {
		return checkGt;
	}

	public void setCheckGt(int checkGt) {
		this.checkGt = checkGt;
	}

	
	
}
