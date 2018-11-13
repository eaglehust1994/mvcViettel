package com.viettel.qll.dto;

import com.viettel.qll.bo.TblDmMaLoiBO;
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
@XmlRootElement(name = "TBL_DM_MA_LOIBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TblDmMaLoiDTO extends QllBaseDTO<TblDmMaLoiBO> {

	private java.lang.Float tienPhat;
	private java.lang.String tenLoi;
	private java.lang.String maLoi;
	private java.lang.Long hoatDong;
	private java.lang.Long tblDmMaLoiId;
	private java.lang.Long xoa;
	private String text;
	private int start;
	private int maxResult;

    @Override
    public TblDmMaLoiBO toModel() {
        TblDmMaLoiBO tblDmMaLoiBO = new TblDmMaLoiBO();
        tblDmMaLoiBO.setTienPhat(this.tienPhat);
        tblDmMaLoiBO.setTenLoi(this.tenLoi);
        tblDmMaLoiBO.setMaLoi(this.maLoi);
        tblDmMaLoiBO.setHoatDong(this.hoatDong);
        tblDmMaLoiBO.setTblDmMaLoiId(this.tblDmMaLoiId);
        tblDmMaLoiBO.setXoa(this.xoa);
        return tblDmMaLoiBO;
    }

	@JsonProperty("tienPhat")
    public java.lang.Float getTienPhat(){
		return tienPhat;
    }
	
    public void setTienPhat(java.lang.Float tienPhat){
		this.tienPhat = tienPhat;
    }	
	
	@JsonProperty("tenLoi")
    public java.lang.String getTenLoi(){
		return tenLoi;
    }
	
    public void setTenLoi(java.lang.String tenLoi){
		this.tenLoi = tenLoi;
    }	
	
	@JsonProperty("maLoi")
    public java.lang.String getMaLoi(){
		return maLoi;
    }
	
    public void setMaLoi(java.lang.String maLoi){
		this.maLoi = maLoi;
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
        return tblDmMaLoiId;
    }
   
    @Override
    public String catchName() {
        return getTblDmMaLoiId().toString();
    }
	
	@JsonProperty("tblDmMaLoiId")
    public java.lang.Long getTblDmMaLoiId(){
		return tblDmMaLoiId;
    }
	
    public void setTblDmMaLoiId(java.lang.Long tblDmMaLoiId){
		this.tblDmMaLoiId = tblDmMaLoiId;
    }	
	
	@JsonProperty("xoa")
    public java.lang.Long getXoa(){
		return xoa;
    }
	
    public void setXoa(java.lang.Long xoa){
		this.xoa = xoa;
    }	
	
	
}
