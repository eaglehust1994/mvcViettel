package com.viettel.qll.dto;

import com.viettel.qll.bo.TblLoiLapLaiBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.viettel.erp.utils.CustomJsonDateDeserializer;
import com.viettel.erp.utils.CustomJsonDateSerializer;
import com.viettel.erp.constant.ApplicationConstants;

/**
 *
 * @author hailh10
 */
@SuppressWarnings("serial")
@XmlRootElement(name = "TBL_LOI_LAP_LAIBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TblLoiLapLaiDTO extends QllBaseDTO<TblLoiLapLaiBO> {

	private java.lang.Long hoatDong;
	private java.lang.Long xoa;
	private java.lang.Float phat;
	private java.lang.Float soLanLap;
	private java.lang.String maNv;
	private java.lang.Long tblLoiLapLaiId;
	private java.lang.String loaiDv;
	private java.lang.String quanHuyen;
	private java.lang.String tinh;
	private java.lang.String thang;
	private java.lang.String accountKh;
	private java.lang.String exThang;
	private java.lang.String exNam;
	private String text;
	private int start;
	private int maxResult;

    @Override
    public TblLoiLapLaiBO toModel() {
        TblLoiLapLaiBO tblLoiLapLaiBO = new TblLoiLapLaiBO();
        tblLoiLapLaiBO.setHoatDong(this.hoatDong);
        tblLoiLapLaiBO.setXoa(this.xoa);
        tblLoiLapLaiBO.setPhat(this.phat);
        tblLoiLapLaiBO.setSoLanLap(this.soLanLap);
        tblLoiLapLaiBO.setMaNv(this.maNv);
        tblLoiLapLaiBO.setTblLoiLapLaiId(this.tblLoiLapLaiId);
        tblLoiLapLaiBO.setLoaiDv(this.loaiDv);
        tblLoiLapLaiBO.setQuanHuyen(this.quanHuyen);
        tblLoiLapLaiBO.setTinh(this.tinh);
        tblLoiLapLaiBO.setThang(this.thang);
        tblLoiLapLaiBO.setAccountKh(this.accountKh);
        return tblLoiLapLaiBO;
    }

    
	public java.lang.String getExThang() {
		return exThang;
	}


	public void setExThang(java.lang.String exThang) {
		this.exThang = exThang;
	}


	public java.lang.String getExNam() {
		return exNam;
	}


	public void setExNam(java.lang.String exNam) {
		this.exNam = exNam;
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
	
	@JsonProperty("phat")
    public java.lang.Float getPhat(){
		return phat;
    }
	
    public void setPhat(java.lang.Float phat){
		this.phat = phat;
    }	
	
	@JsonProperty("soLanLap")
    public java.lang.Float getSoLanLap(){
		return soLanLap;
    }
	
    public void setSoLanLap(java.lang.Float soLanLap){
		this.soLanLap = soLanLap;
    }	
	
	@JsonProperty("maNv")
    public java.lang.String getMaNv(){
		return maNv;
    }
	
    public void setMaNv(java.lang.String maNv){
		this.maNv = maNv;
    }	
	
    @Override
     public Long getFWModelId() {
        return tblLoiLapLaiId;
    }
   
    @Override
    public String catchName() {
        return getTblLoiLapLaiId().toString();
    }
	
	@JsonProperty("tblLoiLapLaiId")
    public java.lang.Long getTblLoiLapLaiId(){
		return tblLoiLapLaiId;
    }
	
    public void setTblLoiLapLaiId(java.lang.Long tblLoiLapLaiId){
		this.tblLoiLapLaiId = tblLoiLapLaiId;
    }	
	
	@JsonProperty("loaiDv")
    public java.lang.String getLoaiDv(){
		return loaiDv;
    }
	
    public void setLoaiDv(java.lang.String loaiDv){
		this.loaiDv = loaiDv;
    }	
	
	@JsonProperty("quanHuyen")
    public java.lang.String getQuanHuyen(){
		return quanHuyen;
    }
	
    public void setQuanHuyen(java.lang.String quanHuyen){
		this.quanHuyen = quanHuyen;
    }	
	
	@JsonProperty("tinh")
    public java.lang.String getTinh(){
		return tinh;
    }
	
    public void setTinh(java.lang.String tinh){
		this.tinh = tinh;
    }	
	
	@JsonProperty("thang")
    public java.lang.String getThang(){
		return thang;
    }
	
    public void setThang(java.lang.String thang){
		this.thang = thang;
    }	
	
	@JsonProperty("accountKh")
    public java.lang.String getAccountKh(){
		return accountKh;
    }
	
    public void setAccountKh(java.lang.String accountKh){
		this.accountKh = accountKh;
    }	
	
	
}
