package com.viettel.qll.dto;

import com.viettel.qll.bo.TblPhatKpiBO;
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
@XmlRootElement(name = "TBL_PHAT_KPIBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TblPhatKpiDTO extends QllBaseDTO<TblPhatKpiBO> {

	private java.lang.Float soWoQuaHan;
	private java.lang.Float soBlockWoQuaHan;
	private java.lang.Float diemKiemTraTram;
	private java.lang.Long xoa;
	private java.lang.Long hoatDong;
	private java.lang.Long tblPhatKpiId;
	private java.lang.String thang;
	private java.lang.String kv;
	private java.lang.String maTinh;
	private java.lang.String maNv;
	private java.lang.String maTram;
	private java.lang.String donVi;
	private String text;
	private int start;
	private int maxResult;
	
	private String exThang;
	private String exNam;

    @Override
    public TblPhatKpiBO toModel() {
        TblPhatKpiBO tblPhatKpiBO = new TblPhatKpiBO();
        tblPhatKpiBO.setSoWoQuaHan(this.soWoQuaHan);
        tblPhatKpiBO.setSoBlockWoQuaHan(this.soBlockWoQuaHan);
        tblPhatKpiBO.setDiemKiemTraTram(this.diemKiemTraTram);
        tblPhatKpiBO.setXoa(this.xoa);
        tblPhatKpiBO.setHoatDong(this.hoatDong);
        tblPhatKpiBO.setTblPhatKpiId(this.tblPhatKpiId);
        tblPhatKpiBO.setThang(this.thang);
        tblPhatKpiBO.setKv(this.kv);
        tblPhatKpiBO.setMaTinh(this.maTinh);
        tblPhatKpiBO.setMaNv(this.maNv);
        tblPhatKpiBO.setMaTram(this.maTram);
        return tblPhatKpiBO;
    }

	@JsonProperty("soWoQuaHan")
    public java.lang.Float getSoWoQuaHan(){
		return soWoQuaHan;
    }
	
    public void setSoWoQuaHan(java.lang.Float soWoQuaHan){
		this.soWoQuaHan = soWoQuaHan;
    }	
	@JsonProperty("donVi")
    public java.lang.String getdonVi(){
		return donVi;
    }
	
    public void setdonVi(java.lang.String donVi){
		this.donVi = donVi;
    }	
	@JsonProperty("soBlockWoQuaHan")
    public java.lang.Float getSoBlockWoQuaHan(){
		return soBlockWoQuaHan;
    }
	
    public void setSoBlockWoQuaHan(java.lang.Float soBlockWoQuaHan){
		this.soBlockWoQuaHan = soBlockWoQuaHan;
    }	
	
	@JsonProperty("diemKiemTraTram")
    public java.lang.Float getDiemKiemTraTram(){
		return diemKiemTraTram;
    }
	
    public void setDiemKiemTraTram(java.lang.Float diemKiemTraTram){
		this.diemKiemTraTram = diemKiemTraTram;
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
	
    @Override
     public Long getFWModelId() {
        return tblPhatKpiId;
    }
   
    @Override
    public String catchName() {
        return getTblPhatKpiId().toString();
    }
	
	@JsonProperty("tblPhatKpiId")
    public java.lang.Long getTblPhatKpiId(){
		return tblPhatKpiId;
    }
	
    public void setTblPhatKpiId(java.lang.Long tblPhatKpiId){
		this.tblPhatKpiId = tblPhatKpiId;
    }	
	
	@JsonProperty("thang")
    public java.lang.String getThang(){
		return thang;
    }
	
    public void setThang(java.lang.String thang){
		this.thang = thang;
    }	
	
	@JsonProperty("kv")
    public java.lang.String getKv(){
		return kv;
    }
	
    public void setKv(java.lang.String kv){
		this.kv = kv;
    }	
	
	@JsonProperty("maTinh")
    public java.lang.String getMaTinh(){
		return maTinh;
    }
	
    public void setMaTinh(java.lang.String maTinh){
		this.maTinh = maTinh;
    }	
	
	@JsonProperty("maNv")
    public java.lang.String getMaNv(){
		return maNv;
    }
	
    public void setMaNv(java.lang.String maNv){
		this.maNv = maNv;
    }	
	
	@JsonProperty("maTram")
    public java.lang.String getMaTram(){
		return maTram;
    }
	
    public void setMaTram(java.lang.String maTram){
		this.maTram = maTram;
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
