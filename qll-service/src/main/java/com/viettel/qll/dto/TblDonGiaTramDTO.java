package com.viettel.qll.dto;

import com.viettel.qll.bo.TblDonGiaTramBO;
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
@XmlRootElement(name = "TBL_DON_GIA_TRAMBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TblDonGiaTramDTO extends QllBaseDTO<TblDonGiaTramBO> {

	private java.lang.Long pl;
	private java.lang.Long tblDonGiaTramId;
	private java.lang.String diaHinh;
	private java.lang.Float donGia;
	private java.lang.String ghiChu;
	private java.lang.String loaiTram;
	private java.lang.String vungLuong;
	private String text;
	private int start;
	private int maxResult;

    @Override
    public TblDonGiaTramBO toModel() {
        TblDonGiaTramBO tblDonGiaTramBO = new TblDonGiaTramBO();
        tblDonGiaTramBO.setPl(this.pl);
        tblDonGiaTramBO.setTblDonGiaTramId(this.tblDonGiaTramId);
        tblDonGiaTramBO.setDiaHinh(this.diaHinh);
        tblDonGiaTramBO.setDonGia(this.donGia);
        tblDonGiaTramBO.setGhiChu(this.ghiChu);
        tblDonGiaTramBO.setLoaiTram(this.loaiTram);
        tblDonGiaTramBO.setVungLuong(this.vungLuong);
        return tblDonGiaTramBO;
    }

	@JsonProperty("pl")
    public java.lang.Long getPl(){
		return pl;
    }
	
    public void setPl(java.lang.Long pl){
		this.pl = pl;
    }	
	
    @Override
     public Long getFWModelId() {
        return tblDonGiaTramId;
    }
   
    @Override
    public String catchName() {
        return getTblDonGiaTramId().toString();
    }
	
	@JsonProperty("tblDonGiaTramId")
    public java.lang.Long getTblDonGiaTramId(){
		return tblDonGiaTramId;
    }
	
    public void setTblDonGiaTramId(java.lang.Long tblDonGiaTramId){
		this.tblDonGiaTramId = tblDonGiaTramId;
    }	
	
	@JsonProperty("diaHinh")
    public java.lang.String getDiaHinh(){
		return diaHinh;
    }
	
    public void setDiaHinh(java.lang.String diaHinh){
		this.diaHinh = diaHinh;
    }	
	
	@JsonProperty("donGia")
    public java.lang.Float getDonGia(){
		return donGia;
    }
	
    public void setDonGia(java.lang.Float donGia){
		this.donGia = donGia;
    }	
	
	@JsonProperty("ghiChu")
    public java.lang.String getGhiChu(){
		return ghiChu;
    }
	
    public void setGhiChu(java.lang.String ghiChu){
		this.ghiChu = ghiChu;
    }	
	
	@JsonProperty("loaiTram")
    public java.lang.String getLoaiTram(){
		return loaiTram;
    }
	
    public void setLoaiTram(java.lang.String loaiTram){
		this.loaiTram = loaiTram;
    }	
	
	@JsonProperty("vungLuong")
    public java.lang.String getVungLuong(){
		return vungLuong;
    }
	
    public void setVungLuong(java.lang.String vungLuong){
		this.vungLuong = vungLuong;
    }	
	
	
}
