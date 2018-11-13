package com.viettel.qll.dto;

import com.viettel.qll.bo.TblDmKiCaNhanBO;
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
@XmlRootElement(name = "TBL_DM_KI_CA_NHANBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TblDmKiCaNhanDTO extends QllBaseDTO<TblDmKiCaNhanBO> {

	private java.lang.Long xoa;
	private java.lang.String thang;
	private java.lang.String maNv;
	private java.lang.String hoVaTen;
	private java.lang.Float heSo;
	private java.lang.String kiCaNhan;
	private java.lang.Long hoatDong;
	private java.lang.Long tblDmKiCaNhanId;
	private java.lang.String donVi;
	private String text;
	private int start;
	private int maxResult;
	private String exThang;
	private String exNam;
	
	
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

	@Override
    public TblDmKiCaNhanBO toModel() {
        TblDmKiCaNhanBO tblDmKiCaNhanBO = new TblDmKiCaNhanBO();
        tblDmKiCaNhanBO.setXoa(this.xoa);
        tblDmKiCaNhanBO.setThang(this.thang);
        tblDmKiCaNhanBO.setMaNv(this.maNv);
        tblDmKiCaNhanBO.setHoVaTen(this.hoVaTen);
        tblDmKiCaNhanBO.setHeSo(this.heSo);
        tblDmKiCaNhanBO.setKiCaNhan(this.kiCaNhan);
        tblDmKiCaNhanBO.setHoatDong(this.hoatDong);
        tblDmKiCaNhanBO.setTblDmKiCaNhanId(this.tblDmKiCaNhanId);
        tblDmKiCaNhanBO.setDonVi(this.donVi);
        return tblDmKiCaNhanBO;
    }

	@JsonProperty("xoa")
    public java.lang.Long getXoa(){
		return xoa;
    }
	
    public void setXoa(java.lang.Long xoa){
		this.xoa = xoa;
    }	
	
	@JsonProperty("thang")
    public java.lang.String getThang(){
		return thang;
    }
	
    public void setThang(java.lang.String thang){
		this.thang = thang;
    }	
	
	@JsonProperty("maNv")
    public java.lang.String getMaNv(){
		return maNv;
    }
	
    public void setMaNv(java.lang.String maNv){
		this.maNv = maNv;
    }	
	
	@JsonProperty("hoVaTen")
    public java.lang.String getHoVaTen(){
		return hoVaTen;
    }
	
    public void setHoVaTen(java.lang.String hoVaTen){
		this.hoVaTen = hoVaTen;
    }	
	
	@JsonProperty("heSo")
    public java.lang.Float getHeSo(){
		return heSo;
    }
	
    public void setHeSo(java.lang.Float heSo){
		this.heSo = heSo;
    }	
	
	@JsonProperty("kiCaNhan")
    public java.lang.String getKiCaNhan(){
		return kiCaNhan;
    }
	
    public void setKiCaNhan(java.lang.String kiCaNhan){
		this.kiCaNhan = kiCaNhan;
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
        return tblDmKiCaNhanId;
    }
   
    @Override
    public String catchName() {
        return getTblDmKiCaNhanId().toString();
    }
	
	@JsonProperty("tblDmKiCaNhanId")
    public java.lang.Long getTblDmKiCaNhanId(){
		return tblDmKiCaNhanId;
    }
	
    public void setTblDmKiCaNhanId(java.lang.Long tblDmKiCaNhanId){
		this.tblDmKiCaNhanId = tblDmKiCaNhanId;
    }	
	
	@JsonProperty("donVi")
    public java.lang.String getDonVi(){
		return donVi;
    }
	
    public void setDonVi(java.lang.String donVi){
		this.donVi = donVi;
    }	
	
	
}
