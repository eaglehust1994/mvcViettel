package com.viettel.qll.bo;

import com.viettel.qll.dto.TblPhiBanHangDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.qll.bo.TblPhiBanHangBO")
@Table(name = "TBL_PHI_BAN_HANG")
/**
 *
 * @author: hailh10
 */
public class TblPhiBanHangBO extends BaseFWModelImpl {
     
	@Column(name = "TEN_CTV", length = 1500)
	private java.lang.String tenCtv;
	@Column(name = "MA_NV", length = 300)
	private java.lang.String maNv;
	@Column(name = "PHI_SAU_THUE", length = 22)
	private java.lang.Float phiSauThue;
	@Column(name = "PHI_TRUOC_THUE", length = 22)
	private java.lang.Float phiTruocThue;
	@Column(name = "TONG_THUE_BAO", length = 22)
	private java.lang.Long tongThueBao;
	@Column(name = "THANG", length = 300)
	private java.lang.String thang;
	@Column(name = "USER_BAN_HANG", length = 600)
	private java.lang.String userBanHang;
	@Column(name = "XOA", length = 22)
	private java.lang.Long xoa;
	@Column(name = "HOAT_DONG", length = 22)
	private java.lang.Long hoatDong;
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "TBL_PHI_BAN_HANG_SEQ") })
	@Column(name = "TBL_PHI_BAN_HANG_ID", length = 22)
	private java.lang.Long tblPhiBanHangId;
	@Column(name = "MA_TINH", length = 300)
	private java.lang.String maTinh;

	
	public java.lang.String getTenCtv(){
		return tenCtv;
	}
	
	public void setTenCtv(java.lang.String tenCtv)
	{
		this.tenCtv = tenCtv;
	}
	
	public java.lang.String getMaNv(){
		return maNv;
	}
	
	public void setMaNv(java.lang.String maNv)
	{
		this.maNv = maNv;
	}
	
	public java.lang.Float getPhiSauThue(){
		return phiSauThue;
	}
	
	public void setPhiSauThue(java.lang.Float phiSauThue)
	{
		this.phiSauThue = phiSauThue;
	}
	
	public java.lang.Float getPhiTruocThue(){
		return phiTruocThue;
	}
	
	public void setPhiTruocThue(java.lang.Float phiTruocThue)
	{
		this.phiTruocThue = phiTruocThue;
	}
	
	public java.lang.Long getTongThueBao(){
		return tongThueBao;
	}
	
	public void setTongThueBao(java.lang.Long tongThueBao)
	{
		this.tongThueBao = tongThueBao;
	}
	
	public java.lang.String getThang(){
		return thang;
	}
	
	public void setThang(java.lang.String thang)
	{
		this.thang = thang;
	}
	
	public java.lang.String getUserBanHang(){
		return userBanHang;
	}
	
	public void setUserBanHang(java.lang.String userBanHang)
	{
		this.userBanHang = userBanHang;
	}
	
	public java.lang.Long getXoa(){
		return xoa;
	}
	
	public void setXoa(java.lang.Long xoa)
	{
		this.xoa = xoa;
	}
	
	public java.lang.Long getHoatDong(){
		return hoatDong;
	}
	
	public void setHoatDong(java.lang.Long hoatDong)
	{
		this.hoatDong = hoatDong;
	}
	
	public java.lang.Long getTblPhiBanHangId(){
		return tblPhiBanHangId;
	}
	
	public void setTblPhiBanHangId(java.lang.Long tblPhiBanHangId)
	{
		this.tblPhiBanHangId = tblPhiBanHangId;
	}
	
	public java.lang.String getMaTinh(){
		return maTinh;
	}
	
	public void setMaTinh(java.lang.String maTinh)
	{
		this.maTinh = maTinh;
	}
   
    @Override
    public TblPhiBanHangDTO toDTO() {
        TblPhiBanHangDTO tblPhiBanHangDTO = new TblPhiBanHangDTO(); 
        tblPhiBanHangDTO.setTenCtv(this.tenCtv);		
        tblPhiBanHangDTO.setMaNv(this.maNv);		
        tblPhiBanHangDTO.setPhiSauThue(this.phiSauThue);		
        tblPhiBanHangDTO.setPhiTruocThue(this.phiTruocThue);		
        tblPhiBanHangDTO.setTongThueBao(this.tongThueBao);		
        tblPhiBanHangDTO.setThang(this.thang);		
        tblPhiBanHangDTO.setUserBanHang(this.userBanHang);		
        tblPhiBanHangDTO.setXoa(this.xoa);		
        tblPhiBanHangDTO.setHoatDong(this.hoatDong);		
        tblPhiBanHangDTO.setTblPhiBanHangId(this.tblPhiBanHangId);		
        tblPhiBanHangDTO.setMaTinh(this.maTinh);		
        return tblPhiBanHangDTO;
    }
}
