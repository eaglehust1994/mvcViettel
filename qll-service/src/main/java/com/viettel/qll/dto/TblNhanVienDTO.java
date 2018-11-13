package com.viettel.qll.dto;

import com.viettel.qll.bo.TblNhanVienBO;
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
@XmlRootElement(name = "TBL_NHAN_VIENBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TblNhanVienDTO extends QllBaseDTO<TblNhanVienBO> {

	private java.lang.String chucDanh;
	private java.lang.Long dayMayNhaTram;
	private java.lang.String donVi;
	private java.lang.String hoVaTen;
	private java.lang.String maNv;
	private java.lang.Long xoa;
	private java.lang.Long tblNhanVienId;
	private java.lang.Long hoatDong;
	private String text;
	private int start;
	private int maxResult;
	private String name;

    @Override
    public TblNhanVienBO toModel() {
        TblNhanVienBO tblNhanVienBO = new TblNhanVienBO();
        tblNhanVienBO.setChucDanh(this.chucDanh);
        tblNhanVienBO.setDayMayNhaTram(this.dayMayNhaTram);
        tblNhanVienBO.setDonVi(this.donVi);
        tblNhanVienBO.setHoVaTen(this.hoVaTen);
        tblNhanVienBO.setMaNv(this.maNv);
        tblNhanVienBO.setXoa(this.xoa);
        tblNhanVienBO.setTblNhanVienId(this.tblNhanVienId);
        tblNhanVienBO.setHoatDong(this.hoatDong);
        return tblNhanVienBO;
    }

	@JsonProperty("chucDanh")
    public java.lang.String getChucDanh(){
		return chucDanh;
    }
	
    public void setChucDanh(java.lang.String chucDanh){
		this.chucDanh = chucDanh;
    }	
	
	@JsonProperty("dayMayNhaTram")
    public java.lang.Long getDayMayNhaTram(){
		return dayMayNhaTram;
    }
	
    public void setDayMayNhaTram(java.lang.Long dayMayNhaTram){
		this.dayMayNhaTram = dayMayNhaTram;
    }	
	
	@JsonProperty("donVi")
    public java.lang.String getDonVi(){
		return donVi;
    }
	
    public void setDonVi(java.lang.String donVi){
		this.donVi = donVi;
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
	
	@JsonProperty("xoa")
    public java.lang.Long getXoa(){
		return xoa;
    }
	
    public void setXoa(java.lang.Long xoa){
		this.xoa = xoa;
    }	
	
    @Override
     public Long getFWModelId() {
        return tblNhanVienId;
    }
   
    @Override
    public String catchName() {
        return getTblNhanVienId().toString();
    }
	
	@JsonProperty("tblNhanVienId")
    public java.lang.Long getTblNhanVienId(){
		return tblNhanVienId;
    }
	
    public void setTblNhanVienId(java.lang.Long tblNhanVienId){
		this.tblNhanVienId = tblNhanVienId;
    }	
	
	@JsonProperty("hoatDong")
    public java.lang.Long getHoatDong(){
		return hoatDong;
    }
	
    public void setHoatDong(java.lang.Long hoatDong){
		this.hoatDong = hoatDong;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
	
	
}
