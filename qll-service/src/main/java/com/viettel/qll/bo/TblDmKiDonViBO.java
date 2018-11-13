package com.viettel.qll.bo;

import com.viettel.qll.dto.TblDmKiDonViDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.qll.bo.TblDmKiDonViBO")
@Table(name = "TBL_DM_KI_DON_VI")
/**
 *
 * @author: hailh10
 */
public class TblDmKiDonViBO extends BaseFWModelImpl {
     
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "TBL_DM_KI_DON_VI_SEQ") })
	@Column(name = "TBL_DM_KI_DON_VI_ID", length = 22)
	private java.lang.Long tblDmKiDonViId;
	@Column(name = "THANG", length = 300)
	private java.lang.String thang;
	@Column(name = "TINH", length = 300)
	private java.lang.String tinh;
	@Column(name = "KI_DON_VI", length = 22)
	private java.lang.Float kiDonVi;
	@Column(name = "HOAT_DONG", length = 22)
	private java.lang.Long hoatDong;
	@Column(name = "XOA", length = 22)
	private java.lang.Long xoa;
	@Column(name = "HE_SO", length = 22)
	private java.lang.Float heSo;

	
	public java.lang.Long getTblDmKiDonViId(){
		return tblDmKiDonViId;
	}
	
	public void setTblDmKiDonViId(java.lang.Long tblDmKiDonViId)
	{
		this.tblDmKiDonViId = tblDmKiDonViId;
	}
	
	public java.lang.String getThang(){
		return thang;
	}
	
	public void setThang(java.lang.String thang)
	{
		this.thang = thang;
	}
	
	public java.lang.String getTinh(){
		return tinh;
	}
	
	public void setTinh(java.lang.String tinh)
	{
		this.tinh = tinh;
	}
	
	public java.lang.Float getKiDonVi(){
		return kiDonVi;
	}
	
	public void setKiDonVi(java.lang.Float kiDonVi)
	{
		this.kiDonVi = kiDonVi;
	}
	
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
	
	public java.lang.Float getHeSo(){
		return heSo;
	}
	
	public void setHeSo(java.lang.Float heSo)
	{
		this.heSo = heSo;
	}
   
    @Override
    public TblDmKiDonViDTO toDTO() {
        TblDmKiDonViDTO tblDmKiDonViDTO = new TblDmKiDonViDTO(); 
        tblDmKiDonViDTO.setTblDmKiDonViId(this.tblDmKiDonViId);		
        tblDmKiDonViDTO.setThang(this.thang);		
        tblDmKiDonViDTO.setTinh(this.tinh);		
        tblDmKiDonViDTO.setKiDonVi(this.kiDonVi);		
        tblDmKiDonViDTO.setHoatDong(this.hoatDong);		
        tblDmKiDonViDTO.setXoa(this.xoa);		
        tblDmKiDonViDTO.setHeSo(this.heSo);		
        return tblDmKiDonViDTO;
    }
}
