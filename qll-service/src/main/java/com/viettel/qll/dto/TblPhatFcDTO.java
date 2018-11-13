package com.viettel.qll.dto;

import com.viettel.qll.bo.TblPhatFcBO;
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
@XmlRootElement(name = "TBL_PHAT_FCBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TblPhatFcDTO extends QllBaseDTO<TblPhatFcBO> {

	private java.lang.Long xoa;
	private java.lang.Float stpttag;
	private java.lang.Float sfcpt;
	private java.lang.Float gttvbdpd;
	private java.lang.Float slfcgtddbh;
	private java.lang.Float tslfclt;
	private java.lang.Float slfcptmvhmt;
	private java.lang.Float slfcdsdttcptmt;
	private java.lang.Float tbgptt;
	private java.lang.Float tsfccplt;
	private java.lang.Float tsddql;
	private java.lang.String maTinh;
	private java.lang.String tinh;
	private java.lang.String quanHuyen;
	private java.lang.String hoVaTen;
	private java.lang.String maNv;
	private java.lang.String thang;
	private java.lang.Long hoatDong;
	private java.lang.Long tblPhatFcId;
	private java.lang.Float slfcttxlsct;
	private java.lang.String exThang;
	private java.lang.String exNam;
	private String text;
	private int start;
	private int maxResult;

    @Override
    public TblPhatFcBO toModel() {
        TblPhatFcBO tblPhatFcBO = new TblPhatFcBO();
        tblPhatFcBO.setXoa(this.xoa);
        tblPhatFcBO.setStpttag(this.stpttag);
        tblPhatFcBO.setSfcpt(this.sfcpt);
        tblPhatFcBO.setGttvbdpd(this.gttvbdpd);
        tblPhatFcBO.setSlfcgtddbh(this.slfcgtddbh);
        tblPhatFcBO.setTslfclt(this.tslfclt);
        tblPhatFcBO.setSlfcptmvhmt(this.slfcptmvhmt);
        tblPhatFcBO.setSlfcdsdttcptmt(this.slfcdsdttcptmt);
        tblPhatFcBO.setTbgptt(this.tbgptt);
        tblPhatFcBO.setTsfccplt(this.tsfccplt);
        tblPhatFcBO.setTsddql(this.tsddql);
        tblPhatFcBO.setMaTinh(this.maTinh);
        tblPhatFcBO.setTinh(this.tinh);
        tblPhatFcBO.setQuanHuyen(this.quanHuyen);
        tblPhatFcBO.setHoVaTen(this.hoVaTen);
        tblPhatFcBO.setMaNv(this.maNv);
        tblPhatFcBO.setThang(this.thang);
        tblPhatFcBO.setHoatDong(this.hoatDong);
        tblPhatFcBO.setTblPhatFcId(this.tblPhatFcId);
        tblPhatFcBO.setSlfcttxlsct(this.slfcttxlsct);
        return tblPhatFcBO;
    }

	@JsonProperty("xoa")
    public java.lang.Long getXoa(){
		return xoa;
    }
	
    public void setXoa(java.lang.Long xoa){
		this.xoa = xoa;
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

	@JsonProperty("stpttag")
    public java.lang.Float getStpttag(){
		return stpttag;
    }
	
    public void setStpttag(java.lang.Float stpttag){
		this.stpttag = stpttag;
    }	
	
	@JsonProperty("sfcpt")
    public java.lang.Float getSfcpt(){
		return sfcpt;
    }
	
    public void setSfcpt(java.lang.Float sfcpt){
		this.sfcpt = sfcpt;
    }	
	
	@JsonProperty("gttvbdpd")
    public java.lang.Float getGttvbdpd(){
		return gttvbdpd;
    }
	
    public void setGttvbdpd(java.lang.Float gttvbdpd){
		this.gttvbdpd = gttvbdpd;
    }	
	
	@JsonProperty("slfcgtddbh")
    public java.lang.Float getSlfcgtddbh(){
		return slfcgtddbh;
    }
	
    public void setSlfcgtddbh(java.lang.Float slfcgtddbh){
		this.slfcgtddbh = slfcgtddbh;
    }	
	
	@JsonProperty("tslfclt")
    public java.lang.Float getTslfclt(){
		return tslfclt;
    }
	
    public void setTslfclt(java.lang.Float tslfclt){
		this.tslfclt = tslfclt;
    }	
	
	@JsonProperty("slfcptmvhmt")
    public java.lang.Float getSlfcptmvhmt(){
		return slfcptmvhmt;
    }
	
    public void setSlfcptmvhmt(java.lang.Float slfcptmvhmt){
		this.slfcptmvhmt = slfcptmvhmt;
    }	
	
	@JsonProperty("slfcdsdttcptmt")
    public java.lang.Float getSlfcdsdttcptmt(){
		return slfcdsdttcptmt;
    }
	
    public void setSlfcdsdttcptmt(java.lang.Float slfcdsdttcptmt){
		this.slfcdsdttcptmt = slfcdsdttcptmt;
    }	
	
	@JsonProperty("tbgptt")
    public java.lang.Float getTbgptt(){
		return tbgptt;
    }
	
    public void setTbgptt(java.lang.Float tbgptt){
		this.tbgptt = tbgptt;
    }	
	
	@JsonProperty("tsfccplt")
    public java.lang.Float getTsfccplt(){
		return tsfccplt;
    }
	
    public void setTsfccplt(java.lang.Float tsfccplt){
		this.tsfccplt = tsfccplt;
    }	
	
	@JsonProperty("tsddql")
    public java.lang.Float getTsddql(){
		return tsddql;
    }
	
    public void setTsddql(java.lang.Float tsddql){
		this.tsddql = tsddql;
    }	
	
	@JsonProperty("maTinh")
    public java.lang.String getMaTinh(){
		return maTinh;
    }
	
    public void setMaTinh(java.lang.String maTinh){
		this.maTinh = maTinh;
    }	
	
	@JsonProperty("tinh")
    public java.lang.String getTinh(){
		return tinh;
    }
	
    public void setTinh(java.lang.String tinh){
		this.tinh = tinh;
    }	
	
	@JsonProperty("quanHuyen")
    public java.lang.String getQuanHuyen(){
		return quanHuyen;
    }
	
    public void setQuanHuyen(java.lang.String quanHuyen){
		this.quanHuyen = quanHuyen;
    }	
	
	@JsonProperty("hoVaTen")
    public java.lang.String getHoVaTen(){
		return hoVaTen;
    }
	
    public void setHoVaTen(java.lang.String hoVaTen){
		this.hoVaTen = hoVaTen;
    }	
	
	@JsonProperty("maNv")
    public java.lang.String getMaNv(){
		return maNv;
    }
	
    public void setMaNv(java.lang.String maNv){
		this.maNv = maNv;
    }	
	
	@JsonProperty("thang")
    public java.lang.String getThang(){
		return thang;
    }
	
    public void setThang(java.lang.String thang){
		this.thang = thang;
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
        return tblPhatFcId;
    }
   
    @Override
    public String catchName() {
        return getTblPhatFcId().toString();
    }
	
	@JsonProperty("tblPhatFcId")
    public java.lang.Long getTblPhatFcId(){
		return tblPhatFcId;
    }
	
    public void setTblPhatFcId(java.lang.Long tblPhatFcId){
		this.tblPhatFcId = tblPhatFcId;
    }	
	
	@JsonProperty("slfcttxlsct")
    public java.lang.Float getSlfcttxlsct(){
		return slfcttxlsct;
    }
	
    public void setSlfcttxlsct(java.lang.Float slfcttxlsct){
		this.slfcttxlsct = slfcttxlsct;
    }	
	
	
}
