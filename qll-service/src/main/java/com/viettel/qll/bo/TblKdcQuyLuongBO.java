package com.viettel.qll.bo;

import com.viettel.qll.dto.TblKdcQuyLuongDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.qll.bo.TblKdcQuyLuongBO")
@Table(name = "TBL_KDC_QUY_LUONG")
/**
 *
 * @author: hailh10
 */
public class TblKdcQuyLuongBO extends BaseFWModelImpl {
     
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
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "TBL_KDC_QUY_LUONG_SEQ") })
	@Column(name = "TBL_KDC_QUY_LUONG_ID", length = 22)
	private java.lang.Long tblKdcQuyLuongId;

	
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
	
	public java.lang.Long getTblKdcQuyLuongId(){
		return tblKdcQuyLuongId;
	}
	
	public void setTblKdcQuyLuongId(java.lang.Long tblKdcQuyLuongId)
	{
		this.tblKdcQuyLuongId = tblKdcQuyLuongId;
	}
   
    @Override
    public TblKdcQuyLuongDTO toDTO() {
        TblKdcQuyLuongDTO tblKdcQuyLuongDTO = new TblKdcQuyLuongDTO(); 
        tblKdcQuyLuongDTO.setHoatDong(this.hoatDong);		
        tblKdcQuyLuongDTO.setKdc(this.kdc);		
        tblKdcQuyLuongDTO.setThang(this.thang);		
        tblKdcQuyLuongDTO.setXoa(this.xoa);		
        tblKdcQuyLuongDTO.setDonVi(this.donVi);		
        tblKdcQuyLuongDTO.setTblKdcQuyLuongId(this.tblKdcQuyLuongId);		
        return tblKdcQuyLuongDTO;
    }
}
