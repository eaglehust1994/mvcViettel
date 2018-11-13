package com.viettel.qll.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.viettel.erp.utils.CustomJsonDateDeserializer;
import com.viettel.erp.utils.CustomJsonDateSerializer;
import com.viettel.qll.bo.QlCvPtkBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;

/**
 *
 * @author hailh10
 */
@SuppressWarnings("serial")
@XmlRootElement(name = "QL_CV_PTKBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class QlCvPtkDTO extends QllBaseDTO<QlCvPtkBO> {

	private java.lang.Long qlCvPtkId;
	private java.lang.String cnkv;
	private java.lang.String tinh;
	private java.lang.String maTramTuyen;
	private java.lang.String soHd;
	private java.lang.String thuocDmTt;
	private java.lang.String loaiCt;
	private java.lang.String noiDung;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayGuiHshc;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayGuiHshcFrom;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayGuiHshcTo;
	private java.lang.String soBill;
	private java.lang.String ghiChu;
	private java.lang.Float gtSlHtTinh;
	private java.lang.Float gtDnQtkCn;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayDnQtk;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayDnQtkFrom;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayDnQtkTo;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayNhanGuiPtkHshc;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayNhanGuiPtkHshcFrom;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayNhanGuiPtkHshcTo;
	private java.lang.String tinhTrangChuyenUngTu;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayTuongUngTtcut;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayTuongUngTtcutFrom;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayTuongUngTtcutTo;
	private java.lang.String ghiChuHsLoi;
	private java.lang.Float gtPtkTdXongQtk;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayPtkTdXongQtk;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayPtkTdXongQtkFrom;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayPtkTdXongQtkTo;
	private java.lang.Float gtDnQtCdt;
	private java.lang.Float gtChotQtCdt;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayGtChotQtCdt;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayGtChotQtCdtFrom;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayGtChotQtCdtTo;
	private java.lang.String ghiChuDn;
	private java.lang.String thangQtk;
	private java.lang.String thangQtkCdt;
	private java.lang.Float tyLe;
	private java.lang.String hsTonQua35n;
	private java.lang.String gtQhChuaXl;
	private java.lang.String luuTaiKho;
	private java.lang.String nguoiDuyetPtk;
	private java.lang.String nguoiDuyetCdt;
	private java.lang.String banThamDinh1;
	private java.lang.String banThamDinh2;
	private java.util.Date ngayCapNhat;
	private java.lang.String nguoiTao;
	private java.lang.String fileQtk;
	private java.lang.String fileQtDt;
	

	private java.lang.Float gtTonTamTinh;
	
	private java.lang.Long sldndv,sldnqlk,sltdqlk,slDaNhapDn,slDaChotCDT,slTon;
	private List<String> listTinhTrangChuyenUngTu;

    @Override
    public QlCvPtkBO toModel() {
        QlCvPtkBO qlCvPtkBO = new QlCvPtkBO();
        qlCvPtkBO.setQlCvPtkId(this.qlCvPtkId);
        qlCvPtkBO.setCnkv(this.cnkv);
        qlCvPtkBO.setTinh(this.tinh);
        qlCvPtkBO.setMaTramTuyen(this.maTramTuyen);
        qlCvPtkBO.setSoHd(this.soHd);
        qlCvPtkBO.setThuocDmTt(this.thuocDmTt);
        qlCvPtkBO.setLoaiCt(this.loaiCt);
        qlCvPtkBO.setNoiDung(this.noiDung);
        qlCvPtkBO.setNgayGuiHshc(this.ngayGuiHshc);
        qlCvPtkBO.setSoBill(this.soBill);
        qlCvPtkBO.setGhiChu(this.ghiChu);
        qlCvPtkBO.setGtSlHtTinh(this.gtSlHtTinh);
        qlCvPtkBO.setGtDnQtkCn(this.gtDnQtkCn);
        qlCvPtkBO.setNgayDnQtk(this.ngayDnQtk);
        qlCvPtkBO.setNgayNhanGuiPtkHshc(this.ngayNhanGuiPtkHshc);
        qlCvPtkBO.setTinhTrangChuyenUngTu(this.tinhTrangChuyenUngTu);
        qlCvPtkBO.setNgayTuongUngTtcut(this.ngayTuongUngTtcut);
        qlCvPtkBO.setGhiChuHsLoi(this.ghiChuHsLoi);
        qlCvPtkBO.setGtPtkTdXongQtk(this.gtPtkTdXongQtk);
        qlCvPtkBO.setNgayPtkTdXongQtk(this.ngayPtkTdXongQtk);
        qlCvPtkBO.setGtDnQtCdt(this.gtDnQtCdt);
        qlCvPtkBO.setGtChotQtCdt(this.gtChotQtCdt);
        qlCvPtkBO.setNgayGtChotQtCdt(this.ngayGtChotQtCdt);
        qlCvPtkBO.setGhiChuDn(this.ghiChuDn);
        qlCvPtkBO.setThangQtk(this.thangQtk);
        qlCvPtkBO.setThangQtkCdt(this.thangQtkCdt);
        qlCvPtkBO.setTyLe(this.tyLe);
        qlCvPtkBO.setHsTonQua35n(this.hsTonQua35n);
        qlCvPtkBO.setGtQhChuaXl(this.gtQhChuaXl);
        qlCvPtkBO.setLuuTaiKho(this.luuTaiKho);
        qlCvPtkBO.setNguoiDuyetPtk(this.nguoiDuyetPtk);
        qlCvPtkBO.setNguoiDuyetCdt(this.nguoiDuyetCdt);
        qlCvPtkBO.setBanThamDinh1(this.banThamDinh1);
        qlCvPtkBO.setBanThamDinh2(this.banThamDinh2);
        qlCvPtkBO.setNgayCapNhat(this.ngayCapNhat);
        qlCvPtkBO.setNguoiTao(nguoiTao);
        qlCvPtkBO.setFileQtk(this.fileQtk);
        qlCvPtkBO.setFileQtDt(this.fileQtDt);
        return qlCvPtkBO;
    }
    
    @JsonProperty("fileQtk")
    public java.lang.String getFileQtk() {
		return fileQtk;
	}

	public void setFileQtk(java.lang.String fileQtk) {
		this.fileQtk = fileQtk;
	}
	@JsonProperty("fileQtDt")
	public java.lang.String getFileQtDt() {
		return fileQtDt;
	}

	public void setFileQtDt(java.lang.String fileQtDt) {
		this.fileQtDt = fileQtDt;
	}
    @Override
     public Long getFWModelId() {
        return qlCvPtkId;
    }
   
    @Override
    public String catchName() {
        return getQlCvPtkId().toString();
    }
	
	@JsonProperty("qlCvPtkId")
    public java.lang.Long getQlCvPtkId(){
		return qlCvPtkId;
    }
	
    public void setQlCvPtkId(java.lang.Long qlCvPtkId){
		this.qlCvPtkId = qlCvPtkId;
    }	
	
	@JsonProperty("cnkv")
    public java.lang.String getCnkv(){
		return cnkv;
    }
	
    public void setCnkv(java.lang.String cnkv){
		this.cnkv = cnkv;
    }	
	
	@JsonProperty("tinh")
    public java.lang.String getTinh(){
		return tinh;
    }
	
    public void setTinh(java.lang.String tinh){
		this.tinh = tinh;
    }	
	
	@JsonProperty("maTramTuyen")
    public java.lang.String getMaTramTuyen(){
		return maTramTuyen;
    }
	
    public void setMaTramTuyen(java.lang.String maTramTuyen){
		this.maTramTuyen = maTramTuyen;
    }	
	
	@JsonProperty("soHd")
    public java.lang.String getSoHd(){
		return soHd;
    }
	
    public void setSoHd(java.lang.String soHd){
		this.soHd = soHd;
    }	
	
	@JsonProperty("thuocDmTt")
    public java.lang.String getThuocDmTt(){
		return thuocDmTt;
    }
	
    public void setThuocDmTt(java.lang.String thuocDmTt){
		this.thuocDmTt = thuocDmTt;
    }	
	
	@JsonProperty("loaiCt")
    public java.lang.String getLoaiCt(){
		return loaiCt;
    }
	
    public void setLoaiCt(java.lang.String loaiCt){
		this.loaiCt = loaiCt;
    }	
	
	@JsonProperty("noiDung")
    public java.lang.String getNoiDung(){
		return noiDung;
    }
	
    public void setNoiDung(java.lang.String noiDung){
		this.noiDung = noiDung;
    }	
	
	@JsonProperty("ngayGuiHshc")
    public java.util.Date getNgayGuiHshc(){
		return ngayGuiHshc;
    }
	
    public void setNgayGuiHshc(java.util.Date ngayGuiHshc){
		this.ngayGuiHshc = ngayGuiHshc;
    }	
    public java.util.Date getNgayGuiHshcFrom() {
		return ngayGuiHshcFrom;
	}

	public void setNgayGuiHshcFrom(java.util.Date ngayGuiHshcFrom) {
		this.ngayGuiHshcFrom = ngayGuiHshcFrom;
	}

	public java.util.Date getNgayGuiHshcTo() {
		return ngayGuiHshcTo;
	}

	public void setNgayGuiHshcTo(java.util.Date ngayGuiHshcTo) {
		this.ngayGuiHshcTo = ngayGuiHshcTo;
	}
	@JsonProperty("soBill")
    public java.lang.String getSoBill(){
		return soBill;
    }
	
    public void setSoBill(java.lang.String soBill){
		this.soBill = soBill;
    }	
	
	@JsonProperty("ghiChu")
    public java.lang.String getGhiChu(){
		return ghiChu;
    }
	
    public void setGhiChu(java.lang.String ghiChu){
		this.ghiChu = ghiChu;
    }	
	
	@JsonProperty("gtSlHtTinh")
    public java.lang.Float getGtSlHtTinh(){
		return gtSlHtTinh;
    }
	
    public void setGtSlHtTinh(java.lang.Float gtSlHtTinh){
		this.gtSlHtTinh = gtSlHtTinh;
    }	
	
	@JsonProperty("gtDnQtkCn")
    public java.lang.Float getGtDnQtkCn(){
		return gtDnQtkCn;
    }
	
    public void setGtDnQtkCn(java.lang.Float gtDnQtkCn){
		this.gtDnQtkCn = gtDnQtkCn;
    }	
	
	@JsonProperty("ngayDnQtk")
    public java.util.Date getNgayDnQtk(){
		return ngayDnQtk;
    }
	
    public void setNgayDnQtk(java.util.Date ngayDnQtk){
		this.ngayDnQtk = ngayDnQtk;
    }	
	
	public java.util.Date getNgayDnQtkFrom() {
    	return ngayDnQtkFrom;
    }
	
    public void setNgayDnQtkFrom(java.util.Date ngayDnQtkFrom) {
    	this.ngayDnQtkFrom = ngayDnQtkFrom;
    }
	
	public java.util.Date getNgayDnQtkTo() {
    	return ngayDnQtkTo;
    }
	
    public void setNgayDnQtkTo(java.util.Date ngayDnQtkTo) {
    	this.ngayDnQtkTo = ngayDnQtkTo;
    }
	
	@JsonProperty("ngayNhanGuiPtkHshc")
    public java.util.Date getNgayNhanGuiPtkHshc(){
		return ngayNhanGuiPtkHshc;
    }
	
    public void setNgayNhanGuiPtkHshc(java.util.Date ngayNhanGuiPtkHshc){
		this.ngayNhanGuiPtkHshc = ngayNhanGuiPtkHshc;
    }	
	
	public java.util.Date getNgayNhanGuiPtkHshcFrom() {
    	return ngayNhanGuiPtkHshcFrom;
    }
	
    public void setNgayNhanGuiPtkHshcFrom(java.util.Date ngayNhanGuiPtkHshcFrom) {
    	this.ngayNhanGuiPtkHshcFrom = ngayNhanGuiPtkHshcFrom;
    }
	
	public java.util.Date getNgayNhanGuiPtkHshcTo() {
    	return ngayNhanGuiPtkHshcTo;
    }
	
    public void setNgayNhanGuiPtkHshcTo(java.util.Date ngayNhanGuiPtkHshcTo) {
    	this.ngayNhanGuiPtkHshcTo = ngayNhanGuiPtkHshcTo;
    }
	
	@JsonProperty("tinhTrangChuyenUngTu")
    public java.lang.String getTinhTrangChuyenUngTu(){
		return tinhTrangChuyenUngTu;
    }
	
    public void setTinhTrangChuyenUngTu(java.lang.String tinhTrangChuyenUngTu){
		this.tinhTrangChuyenUngTu = tinhTrangChuyenUngTu;
    }	
	
	@JsonProperty("ngayTuongUngTtcut")
    public java.util.Date getNgayTuongUngTtcut(){
		return ngayTuongUngTtcut;
    }
	
    public void setNgayTuongUngTtcut(java.util.Date ngayTuongUngTtcut){
		this.ngayTuongUngTtcut = ngayTuongUngTtcut;
    }	
	
	public java.util.Date getNgayTuongUngTtcutFrom() {
    	return ngayTuongUngTtcutFrom;
    }
	
    public void setNgayTuongUngTtcutFrom(java.util.Date ngayTuongUngTtcutFrom) {
    	this.ngayTuongUngTtcutFrom = ngayTuongUngTtcutFrom;
    }
	
	public java.util.Date getNgayTuongUngTtcutTo() {
    	return ngayTuongUngTtcutTo;
    }
	
    public void setNgayTuongUngTtcutTo(java.util.Date ngayTuongUngTtcutTo) {
    	this.ngayTuongUngTtcutTo = ngayTuongUngTtcutTo;
    }
	
	@JsonProperty("ghiChuHsLoi")
    public java.lang.String getGhiChuHsLoi(){
		return ghiChuHsLoi;
    }
	
    public void setGhiChuHsLoi(java.lang.String ghiChuHsLoi){
		this.ghiChuHsLoi = ghiChuHsLoi;
    }	
	
	@JsonProperty("gtPtkTdXongQtk")
    public java.lang.Float getGtPtkTdXongQtk(){
		return gtPtkTdXongQtk;
    }
	
    public void setGtPtkTdXongQtk(java.lang.Float gtPtkTdXongQtk){
		this.gtPtkTdXongQtk = gtPtkTdXongQtk;
    }	
	
	@JsonProperty("ngayPtkTdXongQtk")
    public java.util.Date getNgayPtkTdXongQtk(){
		return ngayPtkTdXongQtk;
    }
	
    public void setNgayPtkTdXongQtk(java.util.Date ngayPtkTdXongQtk){
		this.ngayPtkTdXongQtk = ngayPtkTdXongQtk;
    }	
	
	public java.util.Date getNgayPtkTdXongQtkFrom() {
    	return ngayPtkTdXongQtkFrom;
    }
	
    public void setNgayPtkTdXongQtkFrom(java.util.Date ngayPtkTdXongQtkFrom) {
    	this.ngayPtkTdXongQtkFrom = ngayPtkTdXongQtkFrom;
    }
	
	public java.util.Date getNgayPtkTdXongQtkTo() {
    	return ngayPtkTdXongQtkTo;
    }
	
    public void setNgayPtkTdXongQtkTo(java.util.Date ngayPtkTdXongQtkTo) {
    	this.ngayPtkTdXongQtkTo = ngayPtkTdXongQtkTo;
    }
	
	@JsonProperty("gtDnQtCdt")
    public java.lang.Float getGtDnQtCdt(){
		return gtDnQtCdt;
    }
	
    public void setGtDnQtCdt(java.lang.Float gtDnQtCdt){
		this.gtDnQtCdt = gtDnQtCdt;
    }	
	
	@JsonProperty("gtChotQtCdt")
    public java.lang.Float getGtChotQtCdt(){
		return gtChotQtCdt;
    }
	
    public void setGtChotQtCdt(java.lang.Float gtChotQtCdt){
		this.gtChotQtCdt = gtChotQtCdt;
    }	
	
	@JsonProperty("ngayGtChotQtCdt")
    public java.util.Date getNgayGtChotQtCdt(){
		return ngayGtChotQtCdt;
    }
	
    public void setNgayGtChotQtCdt(java.util.Date ngayGtChotQtCdt){
		this.ngayGtChotQtCdt = ngayGtChotQtCdt;
    }	
	
	public java.util.Date getNgayGtChotQtCdtFrom() {
    	return ngayGtChotQtCdtFrom;
    }
	
    public void setNgayGtChotQtCdtFrom(java.util.Date ngayGtChotQtCdtFrom) {
    	this.ngayGtChotQtCdtFrom = ngayGtChotQtCdtFrom;
    }
	
	public java.util.Date getNgayGtChotQtCdtTo() {
    	return ngayGtChotQtCdtTo;
    }
	
    public void setNgayGtChotQtCdtTo(java.util.Date ngayGtChotQtCdtTo) {
    	this.ngayGtChotQtCdtTo = ngayGtChotQtCdtTo;
    }
	
	@JsonProperty("ghiChuDn")
    public java.lang.String getGhiChuDn(){
		return ghiChuDn;
    }
	
    public void setGhiChuDn(java.lang.String ghiChuDn){
		this.ghiChuDn = ghiChuDn;
    }	
	
	@JsonProperty("thangQtk")
    public java.lang.String getThangQtk(){
		return thangQtk;
    }
	
    public void setThangQtk(java.lang.String thangQtk){
		this.thangQtk = thangQtk;
    }	
	
	@JsonProperty("thangQtkCdt")
    public java.lang.String getThangQtkCdt(){
		return thangQtkCdt;
    }
	
    public void setThangQtkCdt(java.lang.String thangQtkCdt){
		this.thangQtkCdt = thangQtkCdt;
    }	
	
	@JsonProperty("tyLe")
    public java.lang.Float getTyLe(){
		return tyLe;
    }
	
    public void setTyLe(java.lang.Float tyLe){
		this.tyLe = tyLe;
    }	
	
	@JsonProperty("hsTonQua35n")
    public java.lang.String getHsTonQua35n(){
		return hsTonQua35n;
    }
	
    public void setHsTonQua35n(java.lang.String hsTonQua35n){
		this.hsTonQua35n = hsTonQua35n;
    }	
	
	@JsonProperty("gtQhChuaXl")
    public java.lang.String getGtQhChuaXl(){
		return gtQhChuaXl;
    }
	
    public void setGtQhChuaXl(java.lang.String gtQhChuaXl){
		this.gtQhChuaXl = gtQhChuaXl;
    }	
	
	@JsonProperty("luuTaiKho")
    public java.lang.String getLuuTaiKho(){
		return luuTaiKho;
    }
	
    public void setLuuTaiKho(java.lang.String luuTaiKho){
		this.luuTaiKho = luuTaiKho;
    }	
	
	@JsonProperty("nguoiDuyetPtk")
    public java.lang.String getNguoiDuyetPtk(){
		return nguoiDuyetPtk;
    }
	
    public void setNguoiDuyetPtk(java.lang.String nguoiDuyetPtk){
		this.nguoiDuyetPtk = nguoiDuyetPtk;
    }	
	
	@JsonProperty("nguoiDuyetCdt")
    public java.lang.String getNguoiDuyetCdt(){
		return nguoiDuyetCdt;
    }
	
    public void setNguoiDuyetCdt(java.lang.String nguoiDuyetCdt){
		this.nguoiDuyetCdt = nguoiDuyetCdt;
    }	
	
	@JsonProperty("banThamDinh1")
    public java.lang.String getBanThamDinh1(){
		return banThamDinh1;
    }
	
    public void setBanThamDinh1(java.lang.String banThamDinh1){
		this.banThamDinh1 = banThamDinh1;
    }	
	
	@JsonProperty("banThamDinh2")
    public java.lang.String getBanThamDinh2(){
		return banThamDinh2;
    }
	
    public void setBanThamDinh2(java.lang.String banThamDinh2){
		this.banThamDinh2 = banThamDinh2;
    }

	

	public java.util.Date getNgayCapNhat() {
		return ngayCapNhat;
	}

	public void setNgayCapNhat(java.util.Date ngayCapNhat) {
		this.ngayCapNhat = ngayCapNhat;
	}

	public java.lang.Float getGtTonTamTinh() {
		return gtTonTamTinh;
	}

	public void setGtTonTamTinh(java.lang.Float gtTonTamTinh) {
		this.gtTonTamTinh = gtTonTamTinh;
	}

	public java.lang.Long getSldndv() {
		return sldndv;
	}

	public void setSldndv(java.lang.Long sldndv) {
		this.sldndv = sldndv;
	}

	public java.lang.Long getSldnqlk() {
		return sldnqlk;
	}

	public void setSldnqlk(java.lang.Long sldnqlk) {
		this.sldnqlk = sldnqlk;
	}

	public java.lang.Long getSltdqlk() {
		return sltdqlk;
	}

	public void setSltdqlk(java.lang.Long sltdqlk) {
		this.sltdqlk = sltdqlk;
	}

	public java.lang.Long getSlDaNhapDn() {
		return slDaNhapDn;
	}

	public void setSlDaNhapDn(java.lang.Long slDaNhapDn) {
		this.slDaNhapDn = slDaNhapDn;
	}

	public java.lang.Long getSlDaChotCDT() {
		return slDaChotCDT;
	}

	public void setSlDaChotCDT(java.lang.Long slDaChotCDT) {
		this.slDaChotCDT = slDaChotCDT;
	}

	public java.lang.Long getSlTon() {
		return slTon;
	}

	public void setSlTon(java.lang.Long slTon) {
		this.slTon = slTon;
	}

	public List<String> getListTinhTrangChuyenUngTu() {
		return listTinhTrangChuyenUngTu;
	}

	public void setListTinhTrangChuyenUngTu(List<String> listTinhTrangChuyenUngTu) {
		this.listTinhTrangChuyenUngTu = listTinhTrangChuyenUngTu;
	}

	public java.lang.String getNguoiTao() {
		return nguoiTao;
	}

	public void setNguoiTao(java.lang.String nguoiTao) {
		this.nguoiTao = nguoiTao;
	}	
    
    
}
