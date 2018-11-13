package com.viettel.qll.dto;

import com.viettel.qll.bo.TblPhatPakhBO;
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
@XmlRootElement(name = "TBL_PHAT_PAKHBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TblPhatPakhDTO extends QllBaseDTO<TblPhatPakhBO> {

	private java.lang.Long xoa;
	private java.lang.Float suCoPhanAnh;
	private java.lang.Float suCoChoPhep;
	private java.lang.String maNv;
	private java.lang.String accountKh;
	private java.lang.Long hoatDong;
	private java.lang.String maKhieuNai;
	private java.lang.String quanHuyen;
	private java.lang.String tinh;
	private java.lang.String thang;
	private java.lang.Long tblPhatPakhId;
	private java.lang.Float phat;
	private java.lang.String exThang;
	private java.lang.String exNam;
	private String text;
	private int start;
	private int maxResult;

    @Override
    public TblPhatPakhBO toModel() {
        TblPhatPakhBO tblPhatPakhBO = new TblPhatPakhBO();
        tblPhatPakhBO.setXoa(this.xoa);
        tblPhatPakhBO.setSuCoPhanAnh(this.suCoPhanAnh);
        tblPhatPakhBO.setSuCoChoPhep(this.suCoChoPhep);
        tblPhatPakhBO.setMaNv(this.maNv);
        tblPhatPakhBO.setAccountKh(this.accountKh);
        tblPhatPakhBO.setHoatDong(this.hoatDong);
        tblPhatPakhBO.setMaKhieuNai(this.maKhieuNai);
        tblPhatPakhBO.setQuanHuyen(this.quanHuyen);
        tblPhatPakhBO.setTinh(this.tinh);
        tblPhatPakhBO.setThang(this.thang);
        tblPhatPakhBO.setTblPhatPakhId(this.tblPhatPakhId);
        tblPhatPakhBO.setPhat(this.phat);
        return tblPhatPakhBO;
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


	@JsonProperty("xoa")
    public java.lang.Long getXoa(){
		return xoa;
    }
	
    public void setXoa(java.lang.Long xoa){
		this.xoa = xoa;
    }	
	
	@JsonProperty("suCoPhanAnh")
    public java.lang.Float getSuCoPhanAnh(){
		return suCoPhanAnh;
    }
	
    public void setSuCoPhanAnh(java.lang.Float suCoPhanAnh){
		this.suCoPhanAnh = suCoPhanAnh;
    }	
	
	@JsonProperty("suCoChoPhep")
    public java.lang.Float getSuCoChoPhep(){
		return suCoChoPhep;
    }
	
    public void setSuCoChoPhep(java.lang.Float suCoChoPhep){
		this.suCoChoPhep = suCoChoPhep;
    }	
	
	@JsonProperty("maNv")
    public java.lang.String getMaNv(){
		return maNv;
    }
	
    public void setMaNv(java.lang.String maNv){
		this.maNv = maNv;
    }	
	
	@JsonProperty("accountKh")
    public java.lang.String getAccountKh(){
		return accountKh;
    }
	
    public void setAccountKh(java.lang.String accountKh){
		this.accountKh = accountKh;
    }	
	
	@JsonProperty("hoatDong")
    public java.lang.Long getHoatDong(){
		return hoatDong;
    }
	
    public void setHoatDong(java.lang.Long hoatDong){
		this.hoatDong = hoatDong;
    }	
	
	@JsonProperty("maKhieuNai")
    public java.lang.String getMaKhieuNai(){
		return maKhieuNai;
    }
	
    public void setMaKhieuNai(java.lang.String maKhieuNai){
		this.maKhieuNai = maKhieuNai;
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
	
    @Override
     public Long getFWModelId() {
        return tblPhatPakhId;
    }
   
    @Override
    public String catchName() {
        return getTblPhatPakhId().toString();
    }
	
	@JsonProperty("tblPhatPakhId")
    public java.lang.Long getTblPhatPakhId(){
		return tblPhatPakhId;
    }
	
    public void setTblPhatPakhId(java.lang.Long tblPhatPakhId){
		this.tblPhatPakhId = tblPhatPakhId;
    }	
	
	@JsonProperty("phat")
    public java.lang.Float getPhat(){
		return phat;
    }
	
    public void setPhat(java.lang.Float phat){
		this.phat = phat;
    }	
	
	
}
