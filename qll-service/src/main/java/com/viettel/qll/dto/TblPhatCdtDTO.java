package com.viettel.qll.dto;

import com.viettel.qll.bo.TblPhatCdtBO;
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
@XmlRootElement(name = "TBL_PHAT_CDTBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TblPhatCdtDTO extends QllBaseDTO<TblPhatCdtBO> {

	private java.lang.Float phatLdsvtXlsct;
	private java.lang.Float phatGttbrm;
	private java.lang.String hoVaTen;
	private java.lang.String maNv;
	private java.lang.String thang;
	private java.lang.Float phatTbcdcnktd;
	private java.lang.Float phatLyttd;
	private java.lang.Float phatTbrmktd;
	private java.lang.Long xoa;
	private java.lang.Long hoatDong;
	private java.lang.Float phatQcxlsc;
	private java.lang.Long tblPhatCdtId;
	private String text;
	private int start;
	private int maxResult;
	
	private String exThang;
	private String exNam;

    @Override
    public TblPhatCdtBO toModel() {
        TblPhatCdtBO tblPhatCdtBO = new TblPhatCdtBO();
        tblPhatCdtBO.setPhatLdsvtXlsct(this.phatLdsvtXlsct);
        tblPhatCdtBO.setPhatGttbrm(this.phatGttbrm);
        tblPhatCdtBO.setHoVaTen(this.hoVaTen);
        tblPhatCdtBO.setMaNv(this.maNv);
        tblPhatCdtBO.setThang(this.thang);
        tblPhatCdtBO.setPhatTbcdcnktd(this.phatTbcdcnktd);
        tblPhatCdtBO.setPhatLyttd(this.phatLyttd);
        tblPhatCdtBO.setPhatTbrmktd(this.phatTbrmktd);
        tblPhatCdtBO.setXoa(this.xoa);
        tblPhatCdtBO.setHoatDong(this.hoatDong);
        tblPhatCdtBO.setPhatQcxlsc(this.phatQcxlsc);
        tblPhatCdtBO.setTblPhatCdtId(this.tblPhatCdtId);
        return tblPhatCdtBO;
    }

	@JsonProperty("phatLdsvtXlsct")
    public java.lang.Float getPhatLdsvtXlsct(){
		return phatLdsvtXlsct;
    }
	
    public void setPhatLdsvtXlsct(java.lang.Float phatLdsvtXlsct){
		this.phatLdsvtXlsct = phatLdsvtXlsct;
    }	
	
	@JsonProperty("phatGttbrm")
    public java.lang.Float getPhatGttbrm(){
		return phatGttbrm;
    }
	
    public void setPhatGttbrm(java.lang.Float phatGttbrm){
		this.phatGttbrm = phatGttbrm;
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
	
	@JsonProperty("phatTbcdcnktd")
    public java.lang.Float getPhatTbcdcnktd(){
		return phatTbcdcnktd;
    }
	
    public void setPhatTbcdcnktd(java.lang.Float phatTbcdcnktd){
		this.phatTbcdcnktd = phatTbcdcnktd;
    }	
	
	@JsonProperty("phatLyttd")
    public java.lang.Float getPhatLyttd(){
		return phatLyttd;
    }
	
    public void setPhatLyttd(java.lang.Float phatLyttd){
		this.phatLyttd = phatLyttd;
    }	
	
	@JsonProperty("phatTbrmktd")
    public java.lang.Float getPhatTbrmktd(){
		return phatTbrmktd;
    }
	
    public void setPhatTbrmktd(java.lang.Float phatTbrmktd){
		this.phatTbrmktd = phatTbrmktd;
    }	
	
	@JsonProperty("xoa")
    public java.lang.Long getXoa(){
		return xoa;
    }
	
    public void setXoa(java.lang.Long xoa){
		this.xoa = xoa;
    }	
	
	@JsonProperty("hoatDong")
    public java.lang.Long getHoatDong(){
		return hoatDong;
    }
	
    public void setHoatDong(java.lang.Long hoatDong){
		this.hoatDong = hoatDong;
    }	
	
	@JsonProperty("phatQcxlsc")
    public java.lang.Float getPhatQcxlsc(){
		return phatQcxlsc;
    }
	
    public void setPhatQcxlsc(java.lang.Float phatQcxlsc){
		this.phatQcxlsc = phatQcxlsc;
    }	
	
    @Override
     public Long getFWModelId() {
        return tblPhatCdtId;
    }
   
    @Override
    public String catchName() {
        return getTblPhatCdtId().toString();
    }
	
	@JsonProperty("tblPhatCdtId")
    public java.lang.Long getTblPhatCdtId(){
		return tblPhatCdtId;
    }
	
    public void setTblPhatCdtId(java.lang.Long tblPhatCdtId){
		this.tblPhatCdtId = tblPhatCdtId;
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
