package com.viettel.qll.dto;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import com.viettel.qll.bo.TblPhatDutCapBO;

/**
 *
 * @author hailh10
 */
@SuppressWarnings("serial")
@XmlRootElement(name = "TBL_PHAT_DUT_CAPBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TblPhatDutCapDTO extends QllBaseDTO<TblPhatDutCapBO> {

	private java.lang.Long hoatDong;
	private java.lang.Long xoa;
	private java.lang.Float tienPhatDutCap;
	private java.lang.Float soLanDut;
	private java.lang.Long tblPhatDutCapId;
	private java.lang.String thang;
	private java.lang.String kv;
	private java.lang.String maTinh;
	private java.lang.String maNv;
	private java.lang.String nvNhanGiaoKhoan;
	private String text;
	private int start;
	private int maxResult;

    @Override
    public TblPhatDutCapBO toModel() {
        TblPhatDutCapBO tblPhatDutCapBO = new TblPhatDutCapBO();
        tblPhatDutCapBO.setHoatDong(this.hoatDong);
        tblPhatDutCapBO.setXoa(this.xoa);
        tblPhatDutCapBO.setTienPhatDutCap(this.tienPhatDutCap);
        tblPhatDutCapBO.setSoLanDut(this.soLanDut);
        tblPhatDutCapBO.setTblPhatDutCapId(this.tblPhatDutCapId);
        tblPhatDutCapBO.setThang(this.thang);
        tblPhatDutCapBO.setKv(this.kv);
        tblPhatDutCapBO.setMaTinh(this.maTinh);
        tblPhatDutCapBO.setMaNv(this.maNv);
        tblPhatDutCapBO.setNvNhanGiaoKhoan(this.nvNhanGiaoKhoan);
        return tblPhatDutCapBO;
    }

	@JsonProperty("hoatDong")
    public java.lang.Long getHoatDong(){
		return hoatDong;
    }
	
    public void setHoatDong(java.lang.Long hoatDong){
		this.hoatDong = hoatDong;
    }	
	
	@JsonProperty("xoa")
    public java.lang.Long getXoa(){
		return xoa;
    }
	
    public void setXoa(java.lang.Long xoa){
		this.xoa = xoa;
    }	
	
	@JsonProperty("tienPhatDutCap")
    public java.lang.Float getTienPhatDutCap(){
		return tienPhatDutCap;
    }
	
    public void setTienPhatDutCap(java.lang.Float tienPhatDutCap){
		this.tienPhatDutCap = tienPhatDutCap;
    }	
	
	@JsonProperty("soLanDut")
    public java.lang.Float getSoLanDut(){
		return soLanDut;
    }
	
    public void setSoLanDut(java.lang.Float soLanDut){
		this.soLanDut = soLanDut;
    }	
	
    @Override
     public Long getFWModelId() {
        return tblPhatDutCapId;
    }
   
    @Override
    public String catchName() {
        return getTblPhatDutCapId().toString();
    }
	
	@JsonProperty("tblPhatDutCapId")
    public java.lang.Long getTblPhatDutCapId(){
		return tblPhatDutCapId;
    }
	
    public void setTblPhatDutCapId(java.lang.Long tblPhatDutCapId){
		this.tblPhatDutCapId = tblPhatDutCapId;
    }	
	
	@JsonProperty("thang")
    public java.lang.String getThang(){
		return thang;
    }
	
    public void setThang(java.lang.String thang){
		this.thang = thang;
    }	
	
	@JsonProperty("kv")
    public java.lang.String getKv(){
		return kv;
    }
	
    public void setKv(java.lang.String kv){
		this.kv = kv;
    }	
	
	@JsonProperty("maTinh")
    public java.lang.String getMaTinh(){
		return maTinh;
    }
	
    public void setMaTinh(java.lang.String maTinh){
		this.maTinh = maTinh;
    }	
	
	@JsonProperty("maNv")
    public java.lang.String getMaNv(){
		return maNv;
    }
	
    public void setMaNv(java.lang.String maNv){
		this.maNv = maNv;
    }	
	
	@JsonProperty("nvNhanGiaoKhoan")
    public java.lang.String getNvNhanGiaoKhoan(){
		return nvNhanGiaoKhoan;
    }
	
    public void setNvNhanGiaoKhoan(java.lang.String nvNhanGiaoKhoan){
		this.nvNhanGiaoKhoan = nvNhanGiaoKhoan;
    }	
	
	
}
