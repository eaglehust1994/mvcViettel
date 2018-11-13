package com.viettel.qll.dto;

import com.viettel.qll.bo.TblDmKiDonViBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.viettel.erp.utils.CustomJsonDateDeserializer;
import com.viettel.erp.utils.CustomJsonDateSerializer;
import com.viettel.ims.bo.PurchaseOrderBO;
import com.viettel.ims.dto.imsBaseDTO;
import com.viettel.erp.constant.ApplicationConstants;

/**
 *
 * @author hailh10
 */
@SuppressWarnings("serial")
@XmlRootElement(name = "TBL_DM_KI_DON_VIBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TblDmKiDonViDTO extends QllBaseDTO<TblDmKiDonViBO> {

	private java.lang.Long tblDmKiDonViId;
	private java.lang.String thang;
	private java.lang.String tinh;
	private java.lang.Float kiDonVi;
	private java.lang.Long hoatDong;
	private java.lang.Long xoa;
	private java.lang.Float heSo;
	private String text;
	private int start;
	private int maxResult;
	private String exThang;
	private String exNam;

    @Override
    public TblDmKiDonViBO toModel() {
        TblDmKiDonViBO tblDmKiDonViBO = new TblDmKiDonViBO();
        tblDmKiDonViBO.setTblDmKiDonViId(this.tblDmKiDonViId);
        tblDmKiDonViBO.setThang(this.thang);
        tblDmKiDonViBO.setTinh(this.tinh);
        tblDmKiDonViBO.setKiDonVi(this.kiDonVi);
        tblDmKiDonViBO.setHoatDong(this.hoatDong);
        tblDmKiDonViBO.setXoa(this.xoa);
        tblDmKiDonViBO.setHeSo(this.heSo);
        return tblDmKiDonViBO;
    }

    @Override
     public Long getFWModelId() {
        return tblDmKiDonViId;
    }
   
    @Override
    public String catchName() {
        return getTblDmKiDonViId().toString();
    }
	
	@JsonProperty("tblDmKiDonViId")
    public java.lang.Long getTblDmKiDonViId(){
		return tblDmKiDonViId;
    }
	
    public void setTblDmKiDonViId(java.lang.Long tblDmKiDonViId){
		this.tblDmKiDonViId = tblDmKiDonViId;
    }	
	
	@JsonProperty("thang")
    public java.lang.String getThang(){
		return thang;
    }
	
    public void setThang(java.lang.String thang){
		this.thang = thang;
    }	
	
	@JsonProperty("tinh")
    public java.lang.String getTinh(){
		return tinh;
    }
	
    public void setTinh(java.lang.String tinh){
		this.tinh = tinh;
    }	
	
	@JsonProperty("kiDonVi")
    public java.lang.Float getKiDonVi(){
		return kiDonVi;
    }
	
    public void setKiDonVi(java.lang.Float kiDonVi){
		this.kiDonVi = kiDonVi;
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
	
	@JsonProperty("heSo")
    public java.lang.Float getHeSo(){
		return heSo;
    }
	
    public void setHeSo(java.lang.Float heSo){
		this.heSo = heSo;
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
