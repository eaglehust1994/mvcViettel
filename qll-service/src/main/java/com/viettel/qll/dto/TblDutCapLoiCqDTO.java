package com.viettel.qll.dto;

import com.viettel.qll.bo.TblDutCapLoiCqBO;
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
@XmlRootElement(name = "TBL_DUT_CAP_LOI_CQBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TblDutCapLoiCqDTO extends QllBaseDTO<TblDutCapLoiCqBO> {

	private java.lang.String khuVuc;
	private java.lang.String maTinh;
	private java.lang.String nvNhanGk;
	private java.lang.Long tblDutCapLoiCqId;
	private java.lang.String maNv;
	private java.lang.Long soLanDut;
	private java.lang.Float tienPhat;
	private java.lang.String thang;
	private String text;
	private int start;
	private int maxResult;
	
	private String exThang;
	private String exNam;

    @Override
    public TblDutCapLoiCqBO toModel() {
        TblDutCapLoiCqBO tblDutCapLoiCqBO = new TblDutCapLoiCqBO();
        tblDutCapLoiCqBO.setKhuVuc(this.khuVuc);
        tblDutCapLoiCqBO.setMaTinh(this.maTinh);
        tblDutCapLoiCqBO.setNvNhanGk(this.nvNhanGk);
        tblDutCapLoiCqBO.setTblDutCapLoiCqId(this.tblDutCapLoiCqId);
        tblDutCapLoiCqBO.setMaNv(this.maNv);
        tblDutCapLoiCqBO.setSoLanDut(this.soLanDut);
        tblDutCapLoiCqBO.setTienPhat(this.tienPhat);
        tblDutCapLoiCqBO.setThang(this.thang);
        return tblDutCapLoiCqBO;
    }

	@JsonProperty("khuVuc")
    public java.lang.String getKhuVuc(){
		return khuVuc;
    }
	
    public void setKhuVuc(java.lang.String khuVuc){
		this.khuVuc = khuVuc;
    }	
	
	@JsonProperty("maTinh")
    public java.lang.String getMaTinh(){
		return maTinh;
    }
	
    public void setMaTinh(java.lang.String maTinh){
		this.maTinh = maTinh;
    }	
	
	@JsonProperty("nvNhanGk")
    public java.lang.String getNvNhanGk(){
		return nvNhanGk;
    }
	
    public void setNvNhanGk(java.lang.String nvNhanGk){
		this.nvNhanGk = nvNhanGk;
    }	
	
    @Override
     public Long getFWModelId() {
        return tblDutCapLoiCqId;
    }
   
    @Override
    public String catchName() {
        return getTblDutCapLoiCqId().toString();
    }
	
	@JsonProperty("tblDutCapLoiCqId")
    public java.lang.Long getTblDutCapLoiCqId(){
		return tblDutCapLoiCqId;
    }
	
    public void setTblDutCapLoiCqId(java.lang.Long tblDutCapLoiCqId){
		this.tblDutCapLoiCqId = tblDutCapLoiCqId;
    }	
	
	@JsonProperty("maNv")
    public java.lang.String getMaNv(){
		return maNv;
    }
	
    public void setMaNv(java.lang.String maNv){
		this.maNv = maNv;
    }	
	
	@JsonProperty("soLanDut")
    public java.lang.Long getSoLanDut(){
		return soLanDut;
    }
	
    public void setSoLanDut(java.lang.Long soLanDut){
		this.soLanDut = soLanDut;
    }	
	
	@JsonProperty("tienPhat")
    public java.lang.Float getTienPhat(){
		return tienPhat;
    }
	
    public void setTienPhat(java.lang.Float tienPhat){
		this.tienPhat = tienPhat;
    }	
	
	@JsonProperty("thang")
    public java.lang.String getThang(){
		return thang;
    }
	
    public void setThang(java.lang.String thang){
		this.thang = thang;
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
