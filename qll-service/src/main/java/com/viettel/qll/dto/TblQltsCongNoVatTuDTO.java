package com.viettel.qll.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.viettel.erp.utils.CustomJsonDateDeserializer;
import com.viettel.erp.utils.CustomJsonDateSerializer;
import com.viettel.erp.utils.JsonDateDeserializer;
import com.viettel.erp.utils.JsonDateSerializerDate;
import com.viettel.qll.bo.TblQltsCongNoVatTuBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;

/**
 *
 * @author hailh10
 */
@SuppressWarnings("serial")
@XmlRootElement(name = "TBL_QLTS_CONG_NO_VAT_TUBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TblQltsCongNoVatTuDTO extends QllBaseDTO<TblQltsCongNoVatTuBO> {

	
	private java.lang.Long tblQltsCongNoVatTuId;
	private java.lang.String doiTuongNhanNo;
	private java.lang.String maNv;
	private java.lang.String donVi;
	private java.lang.String soPxk;
	@JsonSerialize(using = JsonDateSerializerDate.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private java.util.Date ngayChungTu;
	@JsonSerialize(using = JsonDateSerializerDate.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private java.util.Date ngayChungTuFrom;
	@JsonSerialize(using = JsonDateSerializerDate.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private java.util.Date ngayChungTuTo;
	private java.lang.String tenVatTu;
	private java.lang.String maVatTu;
	private java.lang.String maTram;
	private java.lang.String hangMuc;
	private String dvt;
	private java.lang.Float gia;
	private java.lang.Float klxkSoLuong;
	private java.lang.Float klxkThanhTien;
	private java.lang.Float sntSoLuong;
	private java.lang.Float sntThanhTien;
	private java.lang.Float shhtdmSoLuong;
	private java.lang.Float shhtdmThanhTien;
	private java.lang.Float sdtthSoLuong;
	private java.lang.Float sdtthThanhTien;
	private java.lang.Float smmpbtcctSoLuong;
	private java.lang.Float smmpbtcctThanhTien;
	private java.lang.String ghiChu;
	private Boolean xoa;
	private Boolean hoatDong;
	private String text;
	private int start;
	private int maxResult;
	private List<ErrExcelDTO> lstCongNoErr;
	private java.lang.String filePathError;
	private java.lang.Float type;
	private java.lang.Long checkDD;
	private java.lang.Float Sldd;
	private List<TblQltsCongNoVatTuDTO> lstCongNo;
	private java.lang.Long checkNl;
	

    @Override
    public TblQltsCongNoVatTuBO toModel() {
        TblQltsCongNoVatTuBO tblQltsCongNoVatTuBO = new TblQltsCongNoVatTuBO();
        tblQltsCongNoVatTuBO.setTblQltsCongNoVatTuId(this.tblQltsCongNoVatTuId);
        tblQltsCongNoVatTuBO.setDoiTuongNhanNo(this.doiTuongNhanNo);
        tblQltsCongNoVatTuBO.setMaNv(this.maNv);
        tblQltsCongNoVatTuBO.setDonVi(this.donVi);
        tblQltsCongNoVatTuBO.setSoPxk(this.soPxk);
        tblQltsCongNoVatTuBO.setNgayChungTu(this.ngayChungTu);
        tblQltsCongNoVatTuBO.setTenVatTu(this.tenVatTu);
        tblQltsCongNoVatTuBO.setMaVatTu(this.maVatTu);
        tblQltsCongNoVatTuBO.setMaTram(this.maTram);
        tblQltsCongNoVatTuBO.setHangMuc(this.hangMuc);
        tblQltsCongNoVatTuBO.setDvt(this.dvt);
        tblQltsCongNoVatTuBO.setGia(this.gia);
        tblQltsCongNoVatTuBO.setKlxkSoLuong(this.klxkSoLuong);
        tblQltsCongNoVatTuBO.setKlxkThanhTien(this.klxkThanhTien);
        tblQltsCongNoVatTuBO.setSntSoLuong(this.sntSoLuong);
        tblQltsCongNoVatTuBO.setSntThanhTien(this.sntThanhTien);
        tblQltsCongNoVatTuBO.setShhtdmSoLuong(this.shhtdmSoLuong);
        tblQltsCongNoVatTuBO.setShhtdmThanhTien(this.shhtdmThanhTien);
        tblQltsCongNoVatTuBO.setSdtthSoLuong(this.sdtthSoLuong);
        tblQltsCongNoVatTuBO.setSdtthThanhTien(this.sdtthThanhTien);
        tblQltsCongNoVatTuBO.setSmmpbtcctSoLuong(this.smmpbtcctSoLuong);
        tblQltsCongNoVatTuBO.setSmmpbtcctThanhTien(this.smmpbtcctThanhTien);
        tblQltsCongNoVatTuBO.setGhiChu(this.ghiChu);
        tblQltsCongNoVatTuBO.setXoa(this.xoa);
        tblQltsCongNoVatTuBO.setHoatDong(this.hoatDong);
        tblQltsCongNoVatTuBO.setType(this.type);
        tblQltsCongNoVatTuBO.setCheckDD(this.checkDD);
        tblQltsCongNoVatTuBO.setCheckNl(this.checkNl);
        return tblQltsCongNoVatTuBO;
    }

    @Override
     public Long getFWModelId() {
        return tblQltsCongNoVatTuId;
    }
   
    @Override
    public String catchName() {
        return getTblQltsCongNoVatTuId().toString();
    }
	
	@JsonProperty("tblQltsCongNoVatTuId")
    public java.lang.Long getTblQltsCongNoVatTuId(){
		return tblQltsCongNoVatTuId;
    }
	
    public void setTblQltsCongNoVatTuId(java.lang.Long tblQltsCongNoVatTuId){
		this.tblQltsCongNoVatTuId = tblQltsCongNoVatTuId;
    }	
	
	@JsonProperty("doiTuongNhanNo")
    public java.lang.String getDoiTuongNhanNo(){
		return doiTuongNhanNo;
    }
	
    public void setDoiTuongNhanNo(java.lang.String doiTuongNhanNo){
		this.doiTuongNhanNo = doiTuongNhanNo;
    }	
	
	@JsonProperty("maNv")
    public java.lang.String getMaNv(){
		return maNv;
    }
	
    public void setMaNv(java.lang.String maNv){
		this.maNv = maNv;
    }	
	
	@JsonProperty("donVi")
    public java.lang.String getDonVi(){
		return donVi;
    }
	
    public void setDonVi(java.lang.String donVi){
		this.donVi = donVi;
    }	
	
	@JsonProperty("soPxk")
    public java.lang.String getSoPxk(){
		return soPxk;
    }
	
    public void setSoPxk(java.lang.String soPxk){
		this.soPxk = soPxk;
    }	
	
	@JsonProperty("ngayChungTu")
    public java.util.Date getNgayChungTu(){
		return ngayChungTu;
    }
	
    public void setNgayChungTu(java.util.Date ngayChungTu){
		this.ngayChungTu = ngayChungTu;
    }	
	
	public java.util.Date getNgayChungTuFrom() {
    	return ngayChungTuFrom;
    }
	
    public void setNgayChungTuFrom(java.util.Date ngayChungTuFrom) {
    	this.ngayChungTuFrom = ngayChungTuFrom;
    }
	
	public java.util.Date getNgayChungTuTo() {
    	return ngayChungTuTo;
    }
	
    public void setNgayChungTuTo(java.util.Date ngayChungTuTo) {
    	this.ngayChungTuTo = ngayChungTuTo;
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
	
	@JsonProperty("maTram")
    public java.lang.String getMaTram(){
		return maTram;
    }
	
    public void setMaTram(java.lang.String maTram){
		this.maTram = maTram;
    }	
	
	@JsonProperty("hangMuc")
    public java.lang.String getHangMuc(){
		return hangMuc;
    }
	
    public void setHangMuc(java.lang.String hangMuc){
		this.hangMuc = hangMuc;
    }	
	
	@JsonProperty("dvt")
    public java.lang.String getDvt(){
		return dvt;
    }
	
    public void setDvt(java.lang.String dvt){
		this.dvt = dvt;
    }	
	
	@JsonProperty("gia")
    public java.lang.Float getGia(){
		return gia;
    }
	
    public void setGia(java.lang.Float gia){
		this.gia = gia;
    }	
	
	@JsonProperty("klxkSoLuong")
    public java.lang.Float getKlxkSoLuong(){
		return klxkSoLuong;
    }
	
    public void setKlxkSoLuong(java.lang.Float klxkSoLuong){
		this.klxkSoLuong = klxkSoLuong;
    }	
	
	@JsonProperty("klxkThanhTien")
    public java.lang.Float getKlxkThanhTien(){
		return klxkThanhTien;
    }
	
    public void setKlxkThanhTien(java.lang.Float klxkThanhTien){
		this.klxkThanhTien = klxkThanhTien;
    }	
	
	@JsonProperty("sntSoLuong")
    public java.lang.Float getSntSoLuong(){
		return sntSoLuong;
    }
	
    public void setSntSoLuong(java.lang.Float sntSoLuong){
		this.sntSoLuong = sntSoLuong;
    }	
	
	@JsonProperty("sntThanhTien")
    public java.lang.Float getSntThanhTien(){
		return sntThanhTien;
    }
	
    public void setSntThanhTien(java.lang.Float sntThanhTien){
		this.sntThanhTien = sntThanhTien;
    }	
	
	@JsonProperty("shhtdmSoLuong")
    public java.lang.Float getShhtdmSoLuong(){
		return shhtdmSoLuong;
    }
	
    public void setShhtdmSoLuong(java.lang.Float shhtdmSoLuong){
		this.shhtdmSoLuong = shhtdmSoLuong;
    }	
	
	@JsonProperty("shhtdmThanhTien")
    public java.lang.Float getShhtdmThanhTien(){
		return shhtdmThanhTien;
    }
	
    public void setShhtdmThanhTien(java.lang.Float shhtdmThanhTien){
		this.shhtdmThanhTien = shhtdmThanhTien;
    }	
	
	@JsonProperty("sdtthSoLuong")
    public java.lang.Float getSdtthSoLuong(){
		return sdtthSoLuong;
    }
	
    public void setSdtthSoLuong(java.lang.Float sdtthSoLuong){
		this.sdtthSoLuong = sdtthSoLuong;
    }	
	
	@JsonProperty("sdtthThanhTien")
    public java.lang.Float getSdtthThanhTien(){
		return sdtthThanhTien;
    }
	
    public void setSdtthThanhTien(java.lang.Float sdtthThanhTien){
		this.sdtthThanhTien = sdtthThanhTien;
    }	
	
	@JsonProperty("smmpbtcctSoLuong")
    public java.lang.Float getSmmpbtcctSoLuong(){
		return smmpbtcctSoLuong;
    }
	
    public void setSmmpbtcctSoLuong(java.lang.Float smmpbtcctSoLuong){
		this.smmpbtcctSoLuong = smmpbtcctSoLuong;
    }	
	
	@JsonProperty("smmpbtcctThanhTien")
    public java.lang.Float getSmmpbtcctThanhTien(){
		return smmpbtcctThanhTien;
    }
	
    public void setSmmpbtcctThanhTien(java.lang.Float smmpbtcctThanhTien){
		this.smmpbtcctThanhTien = smmpbtcctThanhTien;
    }	
	
	@JsonProperty("ghiChu")
    public java.lang.String getGhiChu(){
		return ghiChu;
    }
	
    public void setGhiChu(java.lang.String ghiChu){
		this.ghiChu = ghiChu;
    }	
	
	@JsonProperty("xoa")
    public java.lang.Boolean getXoa(){
		return xoa;
    }
	
    public void setXoa(java.lang.Boolean xoa){
		this.xoa = xoa;
    }	
	
	@JsonProperty("hoatDong")
    public java.lang.Boolean getHoatDong(){
		return hoatDong;
    }
	
    public void setHoatDong(java.lang.Boolean hoatDong){
		this.hoatDong = hoatDong;
    }

	public List<ErrExcelDTO> getLstCongNoErr() {
		return lstCongNoErr;
	}

	public void setLstCongNoErr(List<ErrExcelDTO> lstCongNoErr) {
		this.lstCongNoErr = lstCongNoErr;
	}

	public java.lang.String getFilePathError() {
		return filePathError;
	}

	public void setFilePathError(java.lang.String filePathError) {
		this.filePathError = filePathError;
	}
	@JsonProperty("type")
	public java.lang.Float getType() {
		return type;
	}

	public void setType(java.lang.Float type) {
		this.type = type;
	}
	@JsonProperty("checkDD")
	public java.lang.Long getCheckDD() {
		return checkDD;
	}

	public void setCheckDD(java.lang.Long checkDD) {
		this.checkDD = checkDD;
	}

	public java.lang.Float getSldd() {
		return Sldd;
	}

	public void setSldd(java.lang.Float sldd) {
		Sldd = sldd;
	}

	public List<TblQltsCongNoVatTuDTO> getLstCongNo() {
		return lstCongNo;
	}

	public void setLstCongNo(List<TblQltsCongNoVatTuDTO> lstCongNo) {
		this.lstCongNo = lstCongNo;
	}
	@JsonProperty("checkNl")
	public java.lang.Long getCheckNl() {
		return checkNl;
	}

	public void setCheckNl(java.lang.Long checkNl) {
		this.checkNl = checkNl;
	}	
	
	
	
	
}
