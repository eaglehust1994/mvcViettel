package com.viettel.qll.dto;

import com.viettel.qll.bo.TblPhiBanHangBO;
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
@XmlRootElement(name = "TBL_PHI_BAN_HANGBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TblPhiBanHangDTO extends QllBaseDTO<TblPhiBanHangBO> {

	private java.lang.String tenCtv;
	private java.lang.String maNv;
	private java.lang.Float phiSauThue;
	private java.lang.Float phiTruocThue;
	private java.lang.Long tongThueBao;
	private java.lang.String thang;
	private java.lang.String userBanHang;
	private java.lang.Long xoa;
	private java.lang.Long hoatDong;
	private java.lang.Long tblPhiBanHangId;
	private java.lang.String maTinh;

	private String exThang;
	private String exNam;
    @Override
    public TblPhiBanHangBO toModel() {
        TblPhiBanHangBO tblPhiBanHangBO = new TblPhiBanHangBO();
        tblPhiBanHangBO.setTenCtv(this.tenCtv);
        tblPhiBanHangBO.setMaNv(this.maNv);
        tblPhiBanHangBO.setPhiSauThue(this.phiSauThue);
        tblPhiBanHangBO.setPhiTruocThue(this.phiTruocThue);
        tblPhiBanHangBO.setTongThueBao(this.tongThueBao);
        tblPhiBanHangBO.setThang(this.thang);
        tblPhiBanHangBO.setUserBanHang(this.userBanHang);
        tblPhiBanHangBO.setXoa(this.xoa);
        tblPhiBanHangBO.setHoatDong(this.hoatDong);
        tblPhiBanHangBO.setTblPhiBanHangId(this.tblPhiBanHangId);
        tblPhiBanHangBO.setMaTinh(this.maTinh);
        return tblPhiBanHangBO;
    }

	@JsonProperty("tenCtv")
    public java.lang.String getTenCtv(){
		return tenCtv;
    }
	
    public void setTenCtv(java.lang.String tenCtv){
		this.tenCtv = tenCtv;
    }	
	
	@JsonProperty("maNv")
    public java.lang.String getMaNv(){
		return maNv;
    }
	
    public void setMaNv(java.lang.String maNv){
		this.maNv = maNv;
    }	
	
	@JsonProperty("phiSauThue")
    public java.lang.Float getPhiSauThue(){
		return phiSauThue;
    }
	
    public void setPhiSauThue(java.lang.Float phiSauThue){
		this.phiSauThue = phiSauThue;
    }	
	
	@JsonProperty("phiTruocThue")
    public java.lang.Float getPhiTruocThue(){
		return phiTruocThue;
    }
	
    public void setPhiTruocThue(java.lang.Float phiTruocThue){
		this.phiTruocThue = phiTruocThue;
    }	
	
	@JsonProperty("tongThueBao")
    public java.lang.Long getTongThueBao(){
		return tongThueBao;
    }
	
    public void setTongThueBao(java.lang.Long tongThueBao){
		this.tongThueBao = tongThueBao;
    }	
	
	@JsonProperty("thang")
    public java.lang.String getThang(){
		return thang;
    }
	
    public void setThang(java.lang.String thang){
		this.thang = thang;
    }	
	
	@JsonProperty("userBanHang")
    public java.lang.String getUserBanHang(){
		return userBanHang;
    }
	
    public void setUserBanHang(java.lang.String userBanHang){
		this.userBanHang = userBanHang;
    }	
	
	@JsonProperty("xoa")
    public java.lang.Long getXoa(){
		return xoa;
    }
	
    public void setXoa(java.lang.Long xoa){
		this.xoa = xoa;
    }	
	
	@JsonProperty("hoatDong")
    public java.lang.Long getHoatDong(){
		return hoatDong;
    }
	
    public void setHoatDong(java.lang.Long hoatDong){
		this.hoatDong = hoatDong;
    }	
	
    @Override
     public Long getFWModelId() {
        return tblPhiBanHangId;
    }
   
    @Override
    public String catchName() {
        return getTblPhiBanHangId().toString();
    }
	
	@JsonProperty("tblPhiBanHangId")
    public java.lang.Long getTblPhiBanHangId(){
		return tblPhiBanHangId;
    }
	
    public void setTblPhiBanHangId(java.lang.Long tblPhiBanHangId){
		this.tblPhiBanHangId = tblPhiBanHangId;
    }	
	
	@JsonProperty("maTinh")
    public java.lang.String getMaTinh(){
		return maTinh;
    }
	
    public void setMaTinh(java.lang.String maTinh){
		this.maTinh = maTinh;
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
