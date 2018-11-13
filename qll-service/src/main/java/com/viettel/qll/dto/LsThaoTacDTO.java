package com.viettel.qll.dto;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.viettel.qll.bo.LsThaoTacBO;

/**
 *
 * @author hailh10
 */
@SuppressWarnings("serial")
@XmlRootElement(name = "LS_THAO_TACBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LsThaoTacDTO extends QllBaseDTO<LsThaoTacBO> {

	private java.lang.Long idThaoTac;
	private java.lang.String chucNang;
	private java.lang.String userCode;
	private java.lang.String fullName;
	private java.lang.String ipAdd;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayThaoTac;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayThaoTacFrom;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayThaoTacTo;
	private java.lang.String moTa;
	
	private java.lang.Long thoiGianThucHien;

    @Override
    public LsThaoTacBO toModel() {
        LsThaoTacBO lsThaoTacBO = new LsThaoTacBO();
        lsThaoTacBO.setIdThaoTac(this.idThaoTac);
        lsThaoTacBO.setChucNang(this.chucNang);
        lsThaoTacBO.setUserCode(this.userCode);
        lsThaoTacBO.setFullName(this.fullName);
        lsThaoTacBO.setIpAdd(this.ipAdd);
        lsThaoTacBO.setNgayThaoTac(this.ngayThaoTac);
        lsThaoTacBO.setMoTa(this.moTa);
        lsThaoTacBO.setThoiGianThucHien(this.thoiGianThucHien);
        return lsThaoTacBO;
    }

    @Override
     public Long getFWModelId() {
        return idThaoTac;
    }
   
    @Override
    public String catchName() {
        return getIdThaoTac().toString();
    }
	
	@JsonProperty("idThaoTac")
    public java.lang.Long getIdThaoTac(){
		return idThaoTac;
    }
	
    public void setIdThaoTac(java.lang.Long idThaoTac){
		this.idThaoTac = idThaoTac;
    }	
	
	@JsonProperty("chucNang")
    public java.lang.String getChucNang(){
		return chucNang;
    }
	
    public void setChucNang(java.lang.String chucNang){
		this.chucNang = chucNang;
    }	
	
	@JsonProperty("userCode")
    public java.lang.String getUserCode(){
		return userCode;
    }
	
    public void setUserCode(java.lang.String userCode){
		this.userCode = userCode;
    }	
	
	@JsonProperty("fullName")
    public java.lang.String getFullName(){
		return fullName;
    }
	
    public void setFullName(java.lang.String fullName){
		this.fullName = fullName;
    }	
	
	@JsonProperty("ipAdd")
    public java.lang.String getIpAdd(){
		return ipAdd;
    }
	
    public void setIpAdd(java.lang.String ipAdd){
		this.ipAdd = ipAdd;
    }	
	
	@JsonProperty("ngayThaoTac")
    public java.util.Date getNgayThaoTac(){
		return ngayThaoTac;
    }
	
    public void setNgayThaoTac(java.util.Date ngayThaoTac){
		this.ngayThaoTac = ngayThaoTac;
    }	
	
	public java.util.Date getNgayThaoTacFrom() {
    	return ngayThaoTacFrom;
    }
	
    public void setNgayThaoTacFrom(java.util.Date ngayThaoTacFrom) {
    	this.ngayThaoTacFrom = ngayThaoTacFrom;
    }
	
	public java.util.Date getNgayThaoTacTo() {
    	return ngayThaoTacTo;
    }
	
    public void setNgayThaoTacTo(java.util.Date ngayThaoTacTo) {
    	this.ngayThaoTacTo = ngayThaoTacTo;
    }
	
	@JsonProperty("moTa")
    public java.lang.String getMoTa(){
		return moTa;
    }
	
    public void setMoTa(java.lang.String moTa){
		this.moTa = moTa;
    }

    @JsonProperty("thoiGianThucHien")
	public java.lang.Long getThoiGianThucHien() {
		return thoiGianThucHien;
	}

	public void setThoiGianThucHien(java.lang.Long thoiGianThucHien) {
		this.thoiGianThucHien = thoiGianThucHien;
	}	
    
    
}
