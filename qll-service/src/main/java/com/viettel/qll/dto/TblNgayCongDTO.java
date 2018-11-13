package com.viettel.qll.dto;

import com.viettel.qll.bo.TblNgayCongBO;
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
@XmlRootElement(name = "TBL_NGAY_CONGBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TblNgayCongDTO extends QllBaseDTO<TblNgayCongBO> {

	private java.lang.Float ngayCongTinhLuong;
	private java.lang.String hoVaTen;
	private java.lang.String maNv;
	private java.lang.String thang;
	private java.lang.String Quatrinhtinhluong;
	private java.lang.Long xoa;
	private java.lang.Long hoatDong;
	private java.lang.Long tblNgayCongId;
	private java.lang.Float ngayCongCheDo;
	private String text;
	private int start;
	private int maxResult;
	private String exThang;
	private String exNam;
    @Override
    public TblNgayCongBO toModel() {
        TblNgayCongBO tblNgayCongBO = new TblNgayCongBO();
        tblNgayCongBO.setNgayCongTinhLuong(this.ngayCongTinhLuong);
        tblNgayCongBO.setHoVaTen(this.hoVaTen);
        tblNgayCongBO.setMaNv(this.maNv);
        tblNgayCongBO.setThang(this.thang);
        tblNgayCongBO.setXoa(this.xoa);
        tblNgayCongBO.setHoatDong(this.hoatDong);
        tblNgayCongBO.setTblNgayCongId(this.tblNgayCongId);
        tblNgayCongBO.setNgayCongCheDo(this.ngayCongCheDo);
        tblNgayCongBO.setquatrinhtinhluong(this.Quatrinhtinhluong);
        return tblNgayCongBO;
    }

	@JsonProperty("ngayCongTinhLuong")
    public java.lang.Float getNgayCongTinhLuong(){
		return ngayCongTinhLuong;
    }
	
    public void setNgayCongTinhLuong(java.lang.Float ngayCongTinhLuong){
		this.ngayCongTinhLuong = ngayCongTinhLuong;
    }	
	@JsonProperty("Quatrinhtinhluong")
    public java.lang.String getQuatrinhtinhluong(){
		return Quatrinhtinhluong;
    }
	
    public void setQuatrinhtinhluong(java.lang.String Quatrinhtinhluong){
		this.Quatrinhtinhluong = Quatrinhtinhluong;
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
        return tblNgayCongId;
    }
   
    @Override
    public String catchName() {
        return getTblNgayCongId().toString();
    }
	
	@JsonProperty("tblNgayCongId")
    public java.lang.Long getTblNgayCongId(){
		return tblNgayCongId;
    }
	
    public void setTblNgayCongId(java.lang.Long tblNgayCongId){
		this.tblNgayCongId = tblNgayCongId;
    }	
	
	@JsonProperty("ngayCongCheDo")
    public java.lang.Float getNgayCongCheDo(){
		return ngayCongCheDo;
    }
	
    public void setNgayCongCheDo(java.lang.Float ngayCongCheDo){
		this.ngayCongCheDo = ngayCongCheDo;
    }	
	public void setExThang(String exThang) {
		this.exThang = exThang;
	}
	public String getExThang() {
		return exThang;
	}
	public String getExNam() {
		return exNam;
	}

	public void setExNam(String exNam) {
		this.exNam = exNam;
	}	
	
}
