package com.viettel.qll.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.viettel.qll.dto.TblKdcLuongCoDongDTO;
import com.viettel.service.base.model.BaseFWModelImpl;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.qll.bo.TblKdcLuongCoDongBO")
@Table(name = "TBL_KDC_LUONG_CO_DONG")
/**
 *
 * @author: hailh10
 */
public class TblKdcLuongCoDongBO extends BaseFWModelImpl {
     
	@Column(name = "HOAT_DONG", length = 22)
	private java.lang.Long hoatDong;
	@Column(name = "KDC", length = 22)
	private java.lang.Float kdc;
	@Column(name = "THANG", length = 2400)
	private java.lang.String thang;
	@Column(name = "XOA", length = 22)
	private java.lang.Long xoa;
	@Column(name = "DON_VI", length = 2400)
	private java.lang.String donVi;
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "TBL_KDC_LUONG_CO_DONG_SEQ") })
	@Column(name = "TBL_KDC_LUONG_CO_DONG_ID", length = 22)
	private java.lang.Long tblKdcLuongCoDongId;

	
	public java.lang.Long getHoatDong(){
		return hoatDong;
	}
	
	public void setHoatDong(java.lang.Long hoatDong)
	{
		this.hoatDong = hoatDong;
	}
	
	public java.lang.Float getKdc(){
		return kdc;
	}
	
	public void setKdc(java.lang.Float kdc)
	{
		this.kdc = kdc;
	}
	
	public java.lang.String getThang(){
		return thang;
	}
	
	public void setThang(java.lang.String thang)
	{
		this.thang = thang;
	}
	
	public java.lang.Long getXoa(){
		return xoa;
	}
	
	public void setXoa(java.lang.Long xoa)
	{
		this.xoa = xoa;
	}
	
	public java.lang.String getDonVi(){
		return donVi;
	}
	
	public void setDonVi(java.lang.String donVi)
	{
		this.donVi = donVi;
	}
	
	public java.lang.Long getTblKdcLuongCoDongId(){
		return tblKdcLuongCoDongId;
	}
	
	public void setTblKdcLuongCoDongId(java.lang.Long tblKdcLuongCoDongId)
	{
		this.tblKdcLuongCoDongId = tblKdcLuongCoDongId;
	}
   
    @Override
    public TblKdcLuongCoDongDTO toDTO() {
        TblKdcLuongCoDongDTO tblKdcLuongCoDongDTO = new TblKdcLuongCoDongDTO(); 
        tblKdcLuongCoDongDTO.setHoatDong(this.hoatDong);		
        tblKdcLuongCoDongDTO.setKdc(this.kdc);		
        tblKdcLuongCoDongDTO.setThang(this.thang);		
        tblKdcLuongCoDongDTO.setXoa(this.xoa);		
        tblKdcLuongCoDongDTO.setDonVi(this.donVi);		
        tblKdcLuongCoDongDTO.setTblKdcLuongCoDongId(this.tblKdcLuongCoDongId);		
        return tblKdcLuongCoDongDTO;
    }
}
