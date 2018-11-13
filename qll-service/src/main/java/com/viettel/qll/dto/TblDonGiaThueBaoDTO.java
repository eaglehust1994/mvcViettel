package com.viettel.qll.dto;

import com.viettel.qll.bo.TblDonGiaThueBaoBO;
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
@XmlRootElement(name = "TBL_DON_GIA_THUE_BAOBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TblDonGiaThueBaoDTO extends QllBaseDTO<TblDonGiaThueBaoBO> {

	private java.lang.Long hoatDong;
	private java.lang.String tinh;
	private java.lang.Long xoa;
	private java.lang.String vungLuong;
	private java.lang.Float donGiaChuDauTu;
	private java.lang.Float donGiaMoi;
	private java.lang.Long tblDonGiaThueBaoId;
	private java.lang.String ghiChu;
	private String text;
	private int start;
	private int maxResult;

    @Override
    public TblDonGiaThueBaoBO toModel() {
        TblDonGiaThueBaoBO tblDonGiaThueBaoBO = new TblDonGiaThueBaoBO();
        tblDonGiaThueBaoBO.setHoatDong(this.hoatDong);
        tblDonGiaThueBaoBO.setTinh(this.tinh);
        tblDonGiaThueBaoBO.setXoa(this.xoa);
        tblDonGiaThueBaoBO.setVungLuong(this.vungLuong);
        tblDonGiaThueBaoBO.setDonGiaChuDauTu(this.donGiaChuDauTu);
        tblDonGiaThueBaoBO.setDonGiaMoi(this.donGiaMoi);
        tblDonGiaThueBaoBO.setTblDonGiaThueBaoId(this.tblDonGiaThueBaoId);
        tblDonGiaThueBaoBO.setGhiChu(this.ghiChu);
        return tblDonGiaThueBaoBO;
    }

	@JsonProperty("hoatDong")
    public java.lang.Long getHoatDong(){
		return hoatDong;
    }
	
    public void setHoatDong(java.lang.Long hoatDong){
		this.hoatDong = hoatDong;
    }	
	
	@JsonProperty("tinh")
    public java.lang.String getTinh(){
		return tinh;
    }
	
    public void setTinh(java.lang.String tinh){
		this.tinh = tinh;
    }	
	
	@JsonProperty("xoa")
    public java.lang.Long getXoa(){
		return xoa;
    }
	
    public void setXoa(java.lang.Long xoa){
		this.xoa = xoa;
    }	
	
	@JsonProperty("vungLuong")
    public java.lang.String getVungLuong(){
		return vungLuong;
    }
	
    public void setVungLuong(java.lang.String vungLuong){
		this.vungLuong = vungLuong;
    }	
	
	@JsonProperty("donGiaChuDauTu")
    public java.lang.Float getDonGiaChuDauTu(){
		return donGiaChuDauTu;
    }
	
    public void setDonGiaChuDauTu(java.lang.Float donGiaChuDauTu){
		this.donGiaChuDauTu = donGiaChuDauTu;
    }	
	
	@JsonProperty("donGiaMoi")
    public java.lang.Float getDonGiaMoi(){
		return donGiaMoi;
    }
	
    public void setDonGiaMoi(java.lang.Float donGiaMoi){
		this.donGiaMoi = donGiaMoi;
    }	
	
    @Override
     public Long getFWModelId() {
        return tblDonGiaThueBaoId;
    }
   
    @Override
    public String catchName() {
        return getTblDonGiaThueBaoId().toString();
    }
	
	@JsonProperty("tblDonGiaThueBaoId")
    public java.lang.Long getTblDonGiaThueBaoId(){
		return tblDonGiaThueBaoId;
    }
	
    public void setTblDonGiaThueBaoId(java.lang.Long tblDonGiaThueBaoId){
		this.tblDonGiaThueBaoId = tblDonGiaThueBaoId;
    }	
	
	@JsonProperty("ghiChu")
    public java.lang.String getGhiChu(){
		return ghiChu;
    }
	
    public void setGhiChu(java.lang.String ghiChu){
		this.ghiChu = ghiChu;
    }	
	
	
}
