package com.viettel.qll.dto;

import com.viettel.qll.bo.TblPhatXuLySuCoBO;
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
@XmlRootElement(name = "TBL_PHAT_XU_LY_SU_COBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TblPhatXuLySuCoDTO extends QllBaseDTO<TblPhatXuLySuCoBO> {

	private java.lang.String maNv;
	private java.lang.Long hoatDong;
	private java.lang.Long tblPhatXuLySuCoId;
	private java.lang.Long xoa;
	private java.lang.Float tong;
	private java.lang.Float phatTgxlBTpb;
	private java.lang.Float phatTgxlTp;
	private java.lang.Float phatTgxlSlp;
	private java.lang.Float phatTgxlTl24h;
	private java.lang.Float phatTgxlTl3h;
	private java.lang.Float phatTgxlSl24h;
	private java.lang.Float phatTgxlSl3h;
	private java.lang.Float thuongTgxlTTien;
	private java.lang.Float thuongTgxlTTl;
	private java.lang.Float thuongTgxlTl24h;
	private java.lang.Float thuongTgxlTl3h;
	private java.lang.Float thuongTgxlSl24h;
	private java.lang.Float thuongTgxlSl3h;
	private java.lang.Float thuongTgxlSlTsc;
	private java.lang.String huyen;
	private java.lang.String tinh;
	private java.lang.String thang;
	private java.lang.String account;
	private java.lang.Float phatTgxlBSb;
	private String text;
	private int start;
	private int maxResult;

	private String exThang;
	private String exNam;

    @Override
    public TblPhatXuLySuCoBO toModel() {
        TblPhatXuLySuCoBO tblPhatXuLySuCoBO = new TblPhatXuLySuCoBO();
        tblPhatXuLySuCoBO.setMaNv(this.maNv);
        tblPhatXuLySuCoBO.setHoatDong(this.hoatDong);
        tblPhatXuLySuCoBO.setTblPhatXuLySuCoId(this.tblPhatXuLySuCoId);
        tblPhatXuLySuCoBO.setXoa(this.xoa);
        tblPhatXuLySuCoBO.setTong(this.tong);
        tblPhatXuLySuCoBO.setPhatTgxlBTpb(this.phatTgxlBTpb);
        tblPhatXuLySuCoBO.setPhatTgxlTp(this.phatTgxlTp);
        tblPhatXuLySuCoBO.setPhatTgxlSlp(this.phatTgxlSlp);
        tblPhatXuLySuCoBO.setPhatTgxlTl24h(this.phatTgxlTl24h);
        tblPhatXuLySuCoBO.setPhatTgxlTl3h(this.phatTgxlTl3h);
        tblPhatXuLySuCoBO.setPhatTgxlSl24h(this.phatTgxlSl24h);
        tblPhatXuLySuCoBO.setPhatTgxlSl3h(this.phatTgxlSl3h);
        tblPhatXuLySuCoBO.setThuongTgxlTTien(this.thuongTgxlTTien);
        tblPhatXuLySuCoBO.setThuongTgxlTTl(this.thuongTgxlTTl);
        tblPhatXuLySuCoBO.setThuongTgxlTl24h(this.thuongTgxlTl24h);
        tblPhatXuLySuCoBO.setThuongTgxlTl3h(this.thuongTgxlTl3h);
        tblPhatXuLySuCoBO.setThuongTgxlSl24h(this.thuongTgxlSl24h);
        tblPhatXuLySuCoBO.setThuongTgxlSl3h(this.thuongTgxlSl3h);
        tblPhatXuLySuCoBO.setThuongTgxlSlTsc(this.thuongTgxlSlTsc);
        tblPhatXuLySuCoBO.setHuyen(this.huyen);
        tblPhatXuLySuCoBO.setTinh(this.tinh);
        tblPhatXuLySuCoBO.setThang(this.thang);
        tblPhatXuLySuCoBO.setAccount(this.account);
        tblPhatXuLySuCoBO.setPhatTgxlBSb(this.phatTgxlBSb);
        return tblPhatXuLySuCoBO;
    }

	@JsonProperty("maNv")
    public java.lang.String getMaNv(){
		return maNv;
    }
	
    public void setMaNv(java.lang.String maNv){
		this.maNv = maNv;
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
        return tblPhatXuLySuCoId;
    }
   
    @Override
    public String catchName() {
        return getTblPhatXuLySuCoId().toString();
    }
	
	@JsonProperty("tblPhatXuLySuCoId")
    public java.lang.Long getTblPhatXuLySuCoId(){
		return tblPhatXuLySuCoId;
    }
	
    public void setTblPhatXuLySuCoId(java.lang.Long tblPhatXuLySuCoId){
		this.tblPhatXuLySuCoId = tblPhatXuLySuCoId;
    }	
	
	@JsonProperty("xoa")
    public java.lang.Long getXoa(){
		return xoa;
    }
	
    public void setXoa(java.lang.Long xoa){
		this.xoa = xoa;
    }	
	
	@JsonProperty("tong")
    public java.lang.Float getTong(){
		return tong;
    }
	
    public void setTong(java.lang.Float tong){
		this.tong = tong;
    }	
	
	@JsonProperty("phatTgxlBTpb")
    public java.lang.Float getPhatTgxlBTpb(){
		return phatTgxlBTpb;
    }
	
    public void setPhatTgxlBTpb(java.lang.Float phatTgxlBTpb){
		this.phatTgxlBTpb = phatTgxlBTpb;
    }	
	
	@JsonProperty("phatTgxlTp")
    public java.lang.Float getPhatTgxlTp(){
		return phatTgxlTp;
    }
	
    public void setPhatTgxlTp(java.lang.Float phatTgxlTp){
		this.phatTgxlTp = phatTgxlTp;
    }	
	
	@JsonProperty("phatTgxlSlp")
    public java.lang.Float getPhatTgxlSlp(){
		return phatTgxlSlp;
    }
	
    public void setPhatTgxlSlp(java.lang.Float phatTgxlSlp){
		this.phatTgxlSlp = phatTgxlSlp;
    }	
	
	@JsonProperty("phatTgxlTl24h")
    public java.lang.Float getPhatTgxlTl24h(){
		return phatTgxlTl24h;
    }
	
    public void setPhatTgxlTl24h(java.lang.Float phatTgxlTl24h){
		this.phatTgxlTl24h = phatTgxlTl24h;
    }	
	
	@JsonProperty("phatTgxlTl3h")
    public java.lang.Float getPhatTgxlTl3h(){
		return phatTgxlTl3h;
    }
	
    public void setPhatTgxlTl3h(java.lang.Float phatTgxlTl3h){
		this.phatTgxlTl3h = phatTgxlTl3h;
    }	
	
	@JsonProperty("phatTgxlSl24h")
    public java.lang.Float getPhatTgxlSl24h(){
		return phatTgxlSl24h;
    }
	
    public void setPhatTgxlSl24h(java.lang.Float phatTgxlSl24h){
		this.phatTgxlSl24h = phatTgxlSl24h;
    }	
	
	@JsonProperty("phatTgxlSl3h")
    public java.lang.Float getPhatTgxlSl3h(){
		return phatTgxlSl3h;
    }
	
    public void setPhatTgxlSl3h(java.lang.Float phatTgxlSl3h){
		this.phatTgxlSl3h = phatTgxlSl3h;
    }	
	
	@JsonProperty("thuongTgxlTTien")
    public java.lang.Float getThuongTgxlTTien(){
		return thuongTgxlTTien;
    }
	
    public void setThuongTgxlTTien(java.lang.Float thuongTgxlTTien){
		this.thuongTgxlTTien = thuongTgxlTTien;
    }	
	
	@JsonProperty("thuongTgxlTTl")
    public java.lang.Float getThuongTgxlTTl(){
		return thuongTgxlTTl;
    }
	
    public void setThuongTgxlTTl(java.lang.Float thuongTgxlTTl){
		this.thuongTgxlTTl = thuongTgxlTTl;
    }	
	
	@JsonProperty("thuongTgxlTl24h")
    public java.lang.Float getThuongTgxlTl24h(){
		return thuongTgxlTl24h;
    }
	
    public void setThuongTgxlTl24h(java.lang.Float thuongTgxlTl24h){
		this.thuongTgxlTl24h = thuongTgxlTl24h;
    }	
	
	@JsonProperty("thuongTgxlTl3h")
    public java.lang.Float getThuongTgxlTl3h(){
		return thuongTgxlTl3h;
    }
	
    public void setThuongTgxlTl3h(java.lang.Float thuongTgxlTl3h){
		this.thuongTgxlTl3h = thuongTgxlTl3h;
    }	
	
	@JsonProperty("thuongTgxlSl24h")
    public java.lang.Float getThuongTgxlSl24h(){
		return thuongTgxlSl24h;
    }
	
    public void setThuongTgxlSl24h(java.lang.Float thuongTgxlSl24h){
		this.thuongTgxlSl24h = thuongTgxlSl24h;
    }	
	
	@JsonProperty("thuongTgxlSl3h")
    public java.lang.Float getThuongTgxlSl3h(){
		return thuongTgxlSl3h;
    }
	
    public void setThuongTgxlSl3h(java.lang.Float thuongTgxlSl3h){
		this.thuongTgxlSl3h = thuongTgxlSl3h;
    }	
	
	@JsonProperty("thuongTgxlSlTsc")
    public java.lang.Float getThuongTgxlSlTsc(){
		return thuongTgxlSlTsc;
    }
	
    public void setThuongTgxlSlTsc(java.lang.Float thuongTgxlSlTsc){
		this.thuongTgxlSlTsc = thuongTgxlSlTsc;
    }	
	
	@JsonProperty("huyen")
    public java.lang.String getHuyen(){
		return huyen;
    }
	
    public void setHuyen(java.lang.String huyen){
		this.huyen = huyen;
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
	
	@JsonProperty("account")
    public java.lang.String getAccount(){
		return account;
    }
	
    public void setAccount(java.lang.String account){
		this.account = account;
    }	
	
	@JsonProperty("phatTgxlBSb")
    public java.lang.Float getPhatTgxlBSb(){
		return phatTgxlBSb;
    }
	
    public void setPhatTgxlBSb(java.lang.Float phatTgxlBSb){
		this.phatTgxlBSb = phatTgxlBSb;
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
