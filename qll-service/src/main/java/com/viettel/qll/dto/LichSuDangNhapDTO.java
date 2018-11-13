package com.viettel.qll.dto;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

//import com.viettel.erp.utils.CustomJsonDateDeserializer;
//import com.viettel.erp.utils.CustomJsonDateSerializer;
import com.viettel.qll.bo.LichSuDangNhapBO;

/**
 *
 * @author hailh10
 */
@SuppressWarnings("serial")
@XmlRootElement(name = "LICH_SU_DANG_NHAPBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LichSuDangNhapDTO extends QllBaseDTO<LichSuDangNhapBO> {

	private java.lang.Long id;
	private java.lang.String userCode;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date gioDangNhap;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date gioDangNhapFrom;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date gioDangNhapTo;
	private java.lang.String ipDangNhap;
	private java.lang.String trangThai;

    @Override
    public LichSuDangNhapBO toModel() {
        LichSuDangNhapBO lichSuDangNhapBO = new LichSuDangNhapBO();
        lichSuDangNhapBO.setId(this.id);
        lichSuDangNhapBO.setUserCode(this.userCode);
        lichSuDangNhapBO.setGioDangNhap(this.gioDangNhap);
        lichSuDangNhapBO.setIpDangNhap(this.ipDangNhap);
        lichSuDangNhapBO.setTrangThai(this.trangThai);
        return lichSuDangNhapBO;
    }

    @Override
     public Long getFWModelId() {
        return id;
    }
   
    @Override
    public String catchName() {
        return getId().toString();
    }
	
	@JsonProperty("id")
    public java.lang.Long getId(){
		return id;
    }
	
    public void setId(java.lang.Long id){
		this.id = id;
    }	
	
	@JsonProperty("userCode")
    public java.lang.String getUserCode(){
		return userCode;
    }
	
    public void setUserCode(java.lang.String userCode){
		this.userCode = userCode;
    }	
	
	@JsonProperty("gioDangNhap")
    public java.util.Date getGioDangNhap(){
		return gioDangNhap;
    }
	
    public void setGioDangNhap(java.util.Date gioDangNhap){
		this.gioDangNhap = gioDangNhap;
    }	
	
	public java.util.Date getGioDangNhapFrom() {
    	return gioDangNhapFrom;
    }
	
    public void setGioDangNhapFrom(java.util.Date gioDangNhapFrom) {
    	this.gioDangNhapFrom = gioDangNhapFrom;
    }
	
	public java.util.Date getGioDangNhapTo() {
    	return gioDangNhapTo;
    }
	
    public void setGioDangNhapTo(java.util.Date gioDangNhapTo) {
    	this.gioDangNhapTo = gioDangNhapTo;
    }
	
	@JsonProperty("ipDangNhap")
    public java.lang.String getIpDangNhap(){
		return ipDangNhap;
    }
	
    public void setIpDangNhap(java.lang.String ipDangNhap){
		this.ipDangNhap = ipDangNhap;
    }	
	
	@JsonProperty("trangThai")
    public java.lang.String getTrangThai(){
		return trangThai;
    }
	
    public void setTrangThai(java.lang.String trangThai){
		this.trangThai = trangThai;
    }	

}
