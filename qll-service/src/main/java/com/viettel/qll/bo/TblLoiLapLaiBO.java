package com.viettel.qll.bo;

import com.viettel.qll.dto.TblLoiLapLaiDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.qll.bo.TblLoiLapLaiBO")
@Table(name = "TBL_LOI_LAP_LAI")
/**
 *
 * @author: hailh10
 */
public class TblLoiLapLaiBO extends BaseFWModelImpl {
     
	@Column(name = "HOAT_DONG", length = 22)
	private java.lang.Long hoatDong;
	@Column(name = "XOA", length = 22)
	private java.lang.Long xoa;
	@Column(name = "PHAT", length = 22)
	private java.lang.Float phat;
	@Column(name = "SO_LAN_LAP", length = 22)
	private java.lang.Float soLanLap;
	@Column(name = "MA_NV", length = 300)
	private java.lang.String maNv;
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_TBL_LOI_LAP_LAI") })
	@Column(name = "TBL_LOI_LAP_LAI_ID", length = 22)
	private java.lang.Long tblLoiLapLaiId;
	@Column(name = "LOAI_DV", length = 1500)
	private java.lang.String loaiDv;
	@Column(name = "QUAN_HUYEN", length = 1500)
	private java.lang.String quanHuyen;
	@Column(name = "TINH", length = 300)
	private java.lang.String tinh;
	@Column(name = "THANG", length = 300)
	private java.lang.String thang;
	@Column(name = "ACCOUNT_KH", length = 600)
	private java.lang.String accountKh;

	
	public java.lang.Long getHoatDong(){
		return hoatDong;
	}
	
	public void setHoatDong(java.lang.Long hoatDong)
	{
		this.hoatDong = hoatDong;
	}
	
	public java.lang.Long getXoa(){
		return xoa;
	}
	
	public void setXoa(java.lang.Long xoa)
	{
		this.xoa = xoa;
	}
	
	public java.lang.Float getPhat(){
		return phat;
	}
	
	public void setPhat(java.lang.Float phat)
	{
		this.phat = phat;
	}
	
	public java.lang.Float getSoLanLap(){
		return soLanLap;
	}
	
	public void setSoLanLap(java.lang.Float soLanLap)
	{
		this.soLanLap = soLanLap;
	}
	
	public java.lang.String getMaNv(){
		return maNv;
	}
	
	public void setMaNv(java.lang.String maNv)
	{
		this.maNv = maNv;
	}
	
	public java.lang.Long getTblLoiLapLaiId(){
		return tblLoiLapLaiId;
	}
	
	public void setTblLoiLapLaiId(java.lang.Long tblLoiLapLaiId)
	{
		this.tblLoiLapLaiId = tblLoiLapLaiId;
	}
	
	public java.lang.String getLoaiDv(){
		return loaiDv;
	}
	
	public void setLoaiDv(java.lang.String loaiDv)
	{
		this.loaiDv = loaiDv;
	}
	
	public java.lang.String getQuanHuyen(){
		return quanHuyen;
	}
	
	public void setQuanHuyen(java.lang.String quanHuyen)
	{
		this.quanHuyen = quanHuyen;
	}
	
	public java.lang.String getTinh(){
		return tinh;
	}
	
	public void setTinh(java.lang.String tinh)
	{
		this.tinh = tinh;
	}
	
	public java.lang.String getThang(){
		return thang;
	}
	
	public void setThang(java.lang.String thang)
	{
		this.thang = thang;
	}
	
	public java.lang.String getAccountKh(){
		return accountKh;
	}
	
	public void setAccountKh(java.lang.String accountKh)
	{
		this.accountKh = accountKh;
	}
   
    @Override
    public TblLoiLapLaiDTO toDTO() {
        TblLoiLapLaiDTO tblLoiLapLaiDTO = new TblLoiLapLaiDTO(); 
        tblLoiLapLaiDTO.setHoatDong(this.hoatDong);		
        tblLoiLapLaiDTO.setXoa(this.xoa);		
        tblLoiLapLaiDTO.setPhat(this.phat);		
        tblLoiLapLaiDTO.setSoLanLap(this.soLanLap);		
        tblLoiLapLaiDTO.setMaNv(this.maNv);		
        tblLoiLapLaiDTO.setTblLoiLapLaiId(this.tblLoiLapLaiId);		
        tblLoiLapLaiDTO.setLoaiDv(this.loaiDv);		
        tblLoiLapLaiDTO.setQuanHuyen(this.quanHuyen);		
        tblLoiLapLaiDTO.setTinh(this.tinh);		
        tblLoiLapLaiDTO.setThang(this.thang);		
        tblLoiLapLaiDTO.setAccountKh(this.accountKh);		
        return tblLoiLapLaiDTO;
    }
}
