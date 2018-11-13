package com.viettel.qll.dto;

import com.viettel.qll.bo.TblPhatRoiMangBO;
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
@XmlRootElement(name = "TBL_PHAT_ROI_MANGBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TblPhatRoiMangDTO extends QllBaseDTO<TblPhatRoiMangBO> {

	private java.lang.Long tblPhatRoiMangId;
	private java.lang.Long hoatDong;
	private java.lang.Long xoa;
	private java.lang.String thang;
	private java.lang.String userBh;
	private java.lang.Float tongThueBao;
	private java.lang.Float phatTruocThue;
	private java.lang.Float phatSauThue;
	private java.lang.String maTtns;
	private java.lang.String tenCtv;
	private java.lang.String maTinh;
	private java.lang.String exThang;
	private java.lang.String exNam;
	private String text;
	private int start;
	private int maxResult;

    @Override
    public TblPhatRoiMangBO toModel() {
        TblPhatRoiMangBO tblPhatRoiMangBO = new TblPhatRoiMangBO();
        tblPhatRoiMangBO.setTblPhatRoiMangId(this.tblPhatRoiMangId);
        tblPhatRoiMangBO.setHoatDong(this.hoatDong);
        tblPhatRoiMangBO.setXoa(this.xoa);
        tblPhatRoiMangBO.setThang(this.thang);
        tblPhatRoiMangBO.setUserBh(this.userBh);
        tblPhatRoiMangBO.setTongThueBao(this.tongThueBao);
        tblPhatRoiMangBO.setPhatTruocThue(this.phatTruocThue);
        tblPhatRoiMangBO.setPhatSauThue(this.phatSauThue);
        tblPhatRoiMangBO.setMaTtns(this.maTtns);
        tblPhatRoiMangBO.setTenCtv(this.tenCtv);
        tblPhatRoiMangBO.setMaTinh(this.maTinh);
        return tblPhatRoiMangBO;
    }

    
    public java.lang.String getExThang() {
		return exThang;
	}


	public void setExThang(java.lang.String exThang) {
		this.exThang = exThang;
	}


	public java.lang.String getExNam() {
		return exNam;
	}


	public void setExNam(java.lang.String exNam) {
		this.exNam = exNam;
	}


	@Override
     public Long getFWModelId() {
        return tblPhatRoiMangId;
    }
   
    @Override
    public String catchName() {
        return getTblPhatRoiMangId().toString();
    }
	
	@JsonProperty("tblPhatRoiMangId")
    public java.lang.Long getTblPhatRoiMangId(){
		return tblPhatRoiMangId;
    }
	
    public void setTblPhatRoiMangId(java.lang.Long tblPhatRoiMangId){
		this.tblPhatRoiMangId = tblPhatRoiMangId;
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
	
	@JsonProperty("thang")
    public java.lang.String getThang(){
		return thang;
    }
	
    public void setThang(java.lang.String thang){
		this.thang = thang;
    }	
	
	@JsonProperty("userBh")
    public java.lang.String getUserBh(){
		return userBh;
    }
	
    public void setUserBh(java.lang.String userBh){
		this.userBh = userBh;
    }	
	
	@JsonProperty("tongThueBao")
    public java.lang.Float getTongThueBao(){
		return tongThueBao;
    }
	
    public void setTongThueBao(java.lang.Float tongThueBao){
		this.tongThueBao = tongThueBao;
    }	
	
	@JsonProperty("phatTruocThue")
    public java.lang.Float getPhatTruocThue(){
		return phatTruocThue;
    }
	
    public void setPhatTruocThue(java.lang.Float phatTruocThue){
		this.phatTruocThue = phatTruocThue;
    }	
	
	@JsonProperty("phatSauThue")
    public java.lang.Float getPhatSauThue(){
		return phatSauThue;
    }
	
    public void setPhatSauThue(java.lang.Float phatSauThue){
		this.phatSauThue = phatSauThue;
    }	
	
	@JsonProperty("maTtns")
    public java.lang.String getMaTtns(){
		return maTtns;
    }
	
    public void setMaTtns(java.lang.String maTtns){
		this.maTtns = maTtns;
    }	
	
	@JsonProperty("tenCtv")
    public java.lang.String getTenCtv(){
		return tenCtv;
    }
	
    public void setTenCtv(java.lang.String tenCtv){
		this.tenCtv = tenCtv;
    }	
	
	@JsonProperty("maTinh")
    public java.lang.String getMaTinh(){
		return maTinh;
    }
	
    public void setMaTinh(java.lang.String maTinh){
		this.maTinh = maTinh;
    }	
	
	
}
