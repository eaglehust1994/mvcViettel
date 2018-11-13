package com.viettel.qll.dto;

import com.viettel.qll.bo.TblDmThongTinLoiDayMayBO;
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
@XmlRootElement(name = "TBL_DM_THONG_TIN_LOI_DAY_MAYBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TblDmThongTinLoiDayMayDTO extends QllBaseDTO<TblDmThongTinLoiDayMayBO> {

	private java.lang.String maLoi;
	private java.lang.Long xoa;
	private java.lang.Float cuocPhatSinh;
	private java.lang.Float soNgayTon;
	private java.lang.String maNv;
	private java.lang.String thueBaoKh;
	private java.lang.String thang;
	private java.lang.Long hoatDong;
	private java.lang.Long thongTinLoiDayMayId;
	private java.lang.String exThang;
	private java.lang.String exNam;
	private Float tienPhat;
	private String text;
	private int start;
	private int maxResult;

    @Override
    public TblDmThongTinLoiDayMayBO toModel() {
        TblDmThongTinLoiDayMayBO tblDmThongTinLoiDayMayBO = new TblDmThongTinLoiDayMayBO();
        tblDmThongTinLoiDayMayBO.setMaLoi(this.maLoi);
        tblDmThongTinLoiDayMayBO.setXoa(this.xoa);
        tblDmThongTinLoiDayMayBO.setCuocPhatSinh(this.cuocPhatSinh);
        tblDmThongTinLoiDayMayBO.setSoNgayTon(this.soNgayTon);
        tblDmThongTinLoiDayMayBO.setMaNv(this.maNv);
        tblDmThongTinLoiDayMayBO.setThueBaoKh(this.thueBaoKh);
        tblDmThongTinLoiDayMayBO.setThang(this.thang);
        tblDmThongTinLoiDayMayBO.setHoatDong(this.hoatDong);
        tblDmThongTinLoiDayMayBO.setThongTinLoiDayMayId(this.thongTinLoiDayMayId);
        return tblDmThongTinLoiDayMayBO;
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

	@JsonProperty("maLoi")
    public java.lang.String getMaLoi(){
		return maLoi;
    }
	
    public void setMaLoi(java.lang.String maLoi){
		this.maLoi = maLoi;
    }	
	
	@JsonProperty("xoa")
    public java.lang.Long getXoa(){
		return xoa;
    }
	
    public void setXoa(java.lang.Long xoa){
		this.xoa = xoa;
    }	
	
	@JsonProperty("cuocPhatSinh")
    public java.lang.Float getCuocPhatSinh(){
		return cuocPhatSinh;
    }
	
    public void setCuocPhatSinh(java.lang.Float cuocPhatSinh){
		this.cuocPhatSinh = cuocPhatSinh;
    }	
	
	@JsonProperty("soNgayTon")
    public java.lang.Float getSoNgayTon(){
		return soNgayTon;
    }
	
    public void setSoNgayTon(java.lang.Float soNgayTon){
		this.soNgayTon = soNgayTon;
    }	
	
	@JsonProperty("maNv")
    public java.lang.String getMaNv(){
		return maNv;
    }
	
    public void setMaNv(java.lang.String maNv){
		this.maNv = maNv;
    }	
	
	@JsonProperty("thueBaoKh")
    public java.lang.String getThueBaoKh(){
		return thueBaoKh;
    }
	
    public void setThueBaoKh(java.lang.String thueBaoKh){
		this.thueBaoKh = thueBaoKh;
    }	
	
	@JsonProperty("thang")
    public java.lang.String getThang(){
		return thang;
    }
	
    public void setThang(java.lang.String thang){
		this.thang = thang;
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
        return thongTinLoiDayMayId;
    }
   
    @Override
    public String catchName() {
        return getThongTinLoiDayMayId().toString();
    }
	
	@JsonProperty("thongTinLoiDayMayId")
    public java.lang.Long getThongTinLoiDayMayId(){
		return thongTinLoiDayMayId;
    }
	
    public void setThongTinLoiDayMayId(java.lang.Long thongTinLoiDayMayId){
		this.thongTinLoiDayMayId = thongTinLoiDayMayId;
    }

	public Float getTienPhat() {
		return tienPhat;
	}

	public void setTienPhat(Float tienPhat) {
		this.tienPhat = tienPhat;
	}	
    
    
	
	
}
