package com.viettel.qll.bo;

import com.viettel.qll.dto.TblDmKiCaNhanDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.qll.bo.TblDmKiCaNhanBO")
@Table(name = "TBL_DM_KI_CA_NHAN")
/**
 *
 * @author: hailh10
 */
public class TblDmKiCaNhanBO extends BaseFWModelImpl {
     
	@Column(name = "XOA", length = 22)
	private java.lang.Long xoa;
	@Column(name = "THANG", length = 300)
	private java.lang.String thang;
	@Column(name = "MA_NV", length = 300)
	private java.lang.String maNv;
	@Column(name = "HO_VA_TEN", length = 600)
	private java.lang.String hoVaTen;
	@Column(name = "HE_SO", length = 22)
	private java.lang.Float heSo;
	@Column(name = "KI_CA_NHAN", length = 600)
	private java.lang.String kiCaNhan;
	@Column(name = "HOAT_DONG", length = 22)
	private java.lang.Long hoatDong;
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "TBL_DM_KI_CA_NHAN_SEQ1") })
	@Column(name = "TBL_DM_KI_CA_NHAN_ID", length = 22)
	private java.lang.Long tblDmKiCaNhanId;
	@Column(name = "DON_VI", length = 300)
	private java.lang.String donVi;

	
	public java.lang.Long getXoa(){
		return xoa;
	}
	
	public void setXoa(java.lang.Long xoa)
	{
		this.xoa = xoa;
	}
	
	public java.lang.String getThang(){
		return thang;
	}
	
	public void setThang(java.lang.String thang)
	{
		this.thang = thang;
	}
	
	public java.lang.String getMaNv(){
		return maNv;
	}
	
	public void setMaNv(java.lang.String maNv)
	{
		this.maNv = maNv;
	}
	
	public java.lang.String getHoVaTen(){
		return hoVaTen;
	}
	
	public void setHoVaTen(java.lang.String hoVaTen)
	{
		this.hoVaTen = hoVaTen;
	}
	
	public java.lang.Float getHeSo(){
		return heSo;
	}
	
	public void setHeSo(java.lang.Float heSo)
	{
		this.heSo = heSo;
	}
	
	public java.lang.String getKiCaNhan(){
		return kiCaNhan;
	}
	
	public void setKiCaNhan(java.lang.String kiCaNhan)
	{
		this.kiCaNhan = kiCaNhan;
	}
	
	public java.lang.Long getHoatDong(){
		return hoatDong;
	}
	
	public void setHoatDong(java.lang.Long hoatDong)
	{
		this.hoatDong = hoatDong;
	}
	
	public java.lang.Long getTblDmKiCaNhanId(){
		return tblDmKiCaNhanId;
	}
	
	public void setTblDmKiCaNhanId(java.lang.Long tblDmKiCaNhanId)
	{
		this.tblDmKiCaNhanId = tblDmKiCaNhanId;
	}
	
	public java.lang.String getDonVi(){
		return donVi;
	}
	
	public void setDonVi(java.lang.String donVi)
	{
		this.donVi = donVi;
	}
   
    @Override
    public TblDmKiCaNhanDTO toDTO() {
        TblDmKiCaNhanDTO tblDmKiCaNhanDTO = new TblDmKiCaNhanDTO(); 
        tblDmKiCaNhanDTO.setXoa(this.xoa);		
        tblDmKiCaNhanDTO.setThang(this.thang);		
        tblDmKiCaNhanDTO.setMaNv(this.maNv);		
        tblDmKiCaNhanDTO.setHoVaTen(this.hoVaTen);		
        tblDmKiCaNhanDTO.setHeSo(this.heSo);		
        tblDmKiCaNhanDTO.setKiCaNhan(this.kiCaNhan);		
        tblDmKiCaNhanDTO.setHoatDong(this.hoatDong);		
        tblDmKiCaNhanDTO.setTblDmKiCaNhanId(this.tblDmKiCaNhanId);		
        tblDmKiCaNhanDTO.setDonVi(this.donVi);		
        return tblDmKiCaNhanDTO;
    }
}
