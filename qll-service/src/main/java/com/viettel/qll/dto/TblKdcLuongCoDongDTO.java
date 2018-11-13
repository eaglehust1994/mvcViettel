package com.viettel.qll.dto;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import com.viettel.qll.bo.TblKdcLuongCoDongBO;

/**
 *
 * @author hailh10
 */
@SuppressWarnings("serial")
@XmlRootElement(name = "TBL_KDC_LUONG_CO_DONGBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TblKdcLuongCoDongDTO extends QllBaseDTO<TblKdcLuongCoDongBO> {

	private java.lang.Long hoatDong;
	private java.lang.Float kdc;
	private java.lang.String thang;
	private java.lang.Long xoa;
	private java.lang.String donVi;
	private java.lang.Long tblKdcLuongCoDongId;
	private String exThang;
	private String exNam;
	
    @Override
    public TblKdcLuongCoDongBO toModel() {
        TblKdcLuongCoDongBO tblKdcLuongCoDongBO = new TblKdcLuongCoDongBO();
        tblKdcLuongCoDongBO.setHoatDong(this.hoatDong);
        tblKdcLuongCoDongBO.setKdc(this.kdc);
        tblKdcLuongCoDongBO.setThang(this.thang);
        tblKdcLuongCoDongBO.setXoa(this.xoa);
        tblKdcLuongCoDongBO.setDonVi(this.donVi);
        tblKdcLuongCoDongBO.setTblKdcLuongCoDongId(this.tblKdcLuongCoDongId);
        return tblKdcLuongCoDongBO;
    }

	@JsonProperty("hoatDong")
    public java.lang.Long getHoatDong(){
		return hoatDong;
    }
	
    public void setHoatDong(java.lang.Long hoatDong){
		this.hoatDong = hoatDong;
    }	
	
	@JsonProperty("kdc")
    public java.lang.Float getKdc(){
		return kdc;
    }
	
    public void setKdc(java.lang.Float kdc){
		this.kdc = kdc;
    }	
	
	@JsonProperty("thang")
    public java.lang.String getThang(){
		return thang;
    }
	
    public void setThang(java.lang.String thang){
		this.thang = thang;
    }	
	
	@JsonProperty("xoa")
    public java.lang.Long getXoa(){
		return xoa;
    }
	
    public void setXoa(java.lang.Long xoa){
		this.xoa = xoa;
    }	
	
	@JsonProperty("donVi")
    public java.lang.String getDonVi(){
		return donVi;
    }
	
    public void setDonVi(java.lang.String donVi){
		this.donVi = donVi;
    }	
	
    @Override
     public Long getFWModelId() {
        return tblKdcLuongCoDongId;
    }
   
    @Override
    public String catchName() {
        return getTblKdcLuongCoDongId().toString();
    }
	
	@JsonProperty("tblKdcLuongCoDongId")
    public java.lang.Long getTblKdcLuongCoDongId(){
		return tblKdcLuongCoDongId;
    }
	
    public void setTblKdcLuongCoDongId(java.lang.Long tblKdcLuongCoDongId){
		this.tblKdcLuongCoDongId = tblKdcLuongCoDongId;
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
	
    
	
}
