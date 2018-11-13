package com.viettel.qll.dto;

import com.viettel.qll.bo.TblCauHinhTienBO;
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
@XmlRootElement(name = "TBL_CAU_HINH_TIENBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TblCauHinhTienDTO extends QllBaseDTO<TblCauHinhTienBO> {

	private java.lang.Long tblCauHinhTienId;
	private java.lang.Long hoatDong;
	private java.lang.Long xoa;
	private java.lang.String loaiCongViec;
	private java.lang.Float heSoNgoaiNha;
	private java.lang.String ngay;
	private java.lang.Float mucTien;
	private java.lang.Float heSoTrongNha;
	private java.lang.String haTang;
	private String text;
	private int start;
	private int maxResult;

    @Override
    public TblCauHinhTienBO toModel() {
        TblCauHinhTienBO tblCauHinhTienBO = new TblCauHinhTienBO();
        tblCauHinhTienBO.setTblCauHinhTienId(this.tblCauHinhTienId);
        tblCauHinhTienBO.setHoatDong(this.hoatDong);
        tblCauHinhTienBO.setXoa(this.xoa);
        tblCauHinhTienBO.setLoaiCongViec(this.loaiCongViec);
        tblCauHinhTienBO.setHeSoNgoaiNha(this.heSoNgoaiNha);
        tblCauHinhTienBO.setNgay(this.ngay);
        tblCauHinhTienBO.setMucTien(this.mucTien);
        tblCauHinhTienBO.setHeSoTrongNha(this.heSoTrongNha);
        tblCauHinhTienBO.setHaTang(this.haTang);
        return tblCauHinhTienBO;
    }

    @Override
     public Long getFWModelId() {
        return tblCauHinhTienId;
    }
   
    @Override
    public String catchName() {
        return getTblCauHinhTienId().toString();
    }
	
	@JsonProperty("tblCauHinhTienId")
    public java.lang.Long getTblCauHinhTienId(){
		return tblCauHinhTienId;
    }
	
    public void setTblCauHinhTienId(java.lang.Long tblCauHinhTienId){
		this.tblCauHinhTienId = tblCauHinhTienId;
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
	
	@JsonProperty("loaiCongViec")
    public java.lang.String getLoaiCongViec(){
		return loaiCongViec;
    }
	
    public void setLoaiCongViec(java.lang.String loaiCongViec){
		this.loaiCongViec = loaiCongViec;
    }	
	
	@JsonProperty("heSoNgoaiNha")
    public java.lang.Float getHeSoNgoaiNha(){
		return heSoNgoaiNha;
    }
	
    public void setHeSoNgoaiNha(java.lang.Float heSoNgoaiNha){
		this.heSoNgoaiNha = heSoNgoaiNha;
    }	
	
	@JsonProperty("ngay")
    public java.lang.String getNgay(){
		return ngay;
    }
	
    public void setNgay(java.lang.String ngay){
		this.ngay = ngay;
    }	
	
	@JsonProperty("mucTien")
    public java.lang.Float getMucTien(){
		return mucTien;
    }
	
    public void setMucTien(java.lang.Float mucTien){
		this.mucTien = mucTien;
    }	
	
	@JsonProperty("heSoTrongNha")
    public java.lang.Float getHeSoTrongNha(){
		return heSoTrongNha;
    }
	
    public void setHeSoTrongNha(java.lang.Float heSoTrongNha){
		this.heSoTrongNha = heSoTrongNha;
    }	
	
	@JsonProperty("haTang")
    public java.lang.String getHaTang(){
		return haTang;
    }
	
    public void setHaTang(java.lang.String haTang){
		this.haTang = haTang;
    }	
	
	
}
