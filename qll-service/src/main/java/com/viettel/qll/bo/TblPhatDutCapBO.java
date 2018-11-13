package com.viettel.qll.bo;

import com.viettel.qll.dto.TblPhatDutCapDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.qll.bo.TblPhatDutCapBO")
@Table(name = "TBL_PHAT_DUT_CAP")
/**
 *
 * @author: hailh10
 */
public class TblPhatDutCapBO extends BaseFWModelImpl {
     
	@Column(name = "HOAT_DONG", length = 22)
	private java.lang.Long hoatDong;
	@Column(name = "XOA", length = 22)
	private java.lang.Long xoa;
	@Column(name = "TIEN_PHAT_DUT_CAP", length = 22)
	private java.lang.Float tienPhatDutCap;
	@Column(name = "SO_LAN_DUT", length = 22)
	private java.lang.Float soLanDut;
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "TBL_PHAT_DUT_CAP_SEQ") })
	@Column(name = "TBL_PHAT_DUT_CAP_ID", length = 22)
	private java.lang.Long tblPhatDutCapId;
	@Column(name = "THANG", length = 300)
	private java.lang.String thang;
	@Column(name = "KV", length = 1500)
	private java.lang.String kv;
	@Column(name = "MA_TINH", length = 300)
	private java.lang.String maTinh;
	@Column(name = "MA_NV", length = 300)
	private java.lang.String maNv;
	@Column(name = "NV_NHAN_GIAO_KHOAN", length = 1500)
	private java.lang.String nvNhanGiaoKhoan;

	
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
	
	public java.lang.Float getTienPhatDutCap(){
		return tienPhatDutCap;
	}
	
	public void setTienPhatDutCap(java.lang.Float tienPhatDutCap)
	{
		this.tienPhatDutCap = tienPhatDutCap;
	}
	
	public java.lang.Float getSoLanDut(){
		return soLanDut;
	}
	
	public void setSoLanDut(java.lang.Float soLanDut)
	{
		this.soLanDut = soLanDut;
	}
	
	public java.lang.Long getTblPhatDutCapId(){
		return tblPhatDutCapId;
	}
	
	public void setTblPhatDutCapId(java.lang.Long tblPhatDutCapId)
	{
		this.tblPhatDutCapId = tblPhatDutCapId;
	}
	
	public java.lang.String getThang(){
		return thang;
	}
	
	public void setThang(java.lang.String thang)
	{
		this.thang = thang;
	}
	
	public java.lang.String getKv(){
		return kv;
	}
	
	public void setKv(java.lang.String kv)
	{
		this.kv = kv;
	}
	
	public java.lang.String getMaTinh(){
		return maTinh;
	}
	
	public void setMaTinh(java.lang.String maTinh)
	{
		this.maTinh = maTinh;
	}
	
	public java.lang.String getMaNv(){
		return maNv;
	}
	
	public void setMaNv(java.lang.String maNv)
	{
		this.maNv = maNv;
	}
	
	public java.lang.String getNvNhanGiaoKhoan(){
		return nvNhanGiaoKhoan;
	}
	
	public void setNvNhanGiaoKhoan(java.lang.String nvNhanGiaoKhoan)
	{
		this.nvNhanGiaoKhoan = nvNhanGiaoKhoan;
	}
   
    @Override
    public TblPhatDutCapDTO toDTO() {
        TblPhatDutCapDTO tblPhatDutCapDTO = new TblPhatDutCapDTO(); 
        tblPhatDutCapDTO.setHoatDong(this.hoatDong);		
        tblPhatDutCapDTO.setXoa(this.xoa);		
        tblPhatDutCapDTO.setTienPhatDutCap(this.tienPhatDutCap);		
        tblPhatDutCapDTO.setSoLanDut(this.soLanDut);		
        tblPhatDutCapDTO.setTblPhatDutCapId(this.tblPhatDutCapId);		
        tblPhatDutCapDTO.setThang(this.thang);		
        tblPhatDutCapDTO.setKv(this.kv);		
        tblPhatDutCapDTO.setMaTinh(this.maTinh);		
        tblPhatDutCapDTO.setMaNv(this.maNv);		
        tblPhatDutCapDTO.setNvNhanGiaoKhoan(this.nvNhanGiaoKhoan);		
        return tblPhatDutCapDTO;
    }
}
