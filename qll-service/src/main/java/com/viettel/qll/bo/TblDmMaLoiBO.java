package com.viettel.qll.bo;

import com.viettel.qll.dto.TblDmMaLoiDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.qll.bo.TblDmMaLoiBO")
@Table(name = "TBL_DM_MA_LOI")
/**
 *
 * @author: hailh10
 */
public class TblDmMaLoiBO extends BaseFWModelImpl {
     
	@Column(name = "TIEN_PHAT", length = 22)
	private java.lang.Float tienPhat;
	@Column(name = "TEN_LOI", length = 1500)
	private java.lang.String tenLoi;
	@Column(name = "MA_LOI", length = 600)
	private java.lang.String maLoi;
	@Column(name = "HOAT_DONG", length = 22)
	private java.lang.Long hoatDong;
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "TBL_DM_MA_LOI_SEQ") })
	@Column(name = "TBL_DM_MA_LOI_ID", length = 22)
	private java.lang.Long tblDmMaLoiId;
	@Column(name = "XOA", length = 22)
	private java.lang.Long xoa;

	
	public java.lang.Float getTienPhat(){
		return tienPhat;
	}
	
	public void setTienPhat(java.lang.Float tienPhat)
	{
		this.tienPhat = tienPhat;
	}
	
	public java.lang.String getTenLoi(){
		return tenLoi;
	}
	
	public void setTenLoi(java.lang.String tenLoi)
	{
		this.tenLoi = tenLoi;
	}
	
	public java.lang.String getMaLoi(){
		return maLoi;
	}
	
	public void setMaLoi(java.lang.String maLoi)
	{
		this.maLoi = maLoi;
	}
	
	public java.lang.Long getHoatDong(){
		return hoatDong;
	}
	
	public void setHoatDong(java.lang.Long hoatDong)
	{
		this.hoatDong = hoatDong;
	}
	
	public java.lang.Long getTblDmMaLoiId(){
		return tblDmMaLoiId;
	}
	
	public void setTblDmMaLoiId(java.lang.Long tblDmMaLoiId)
	{
		this.tblDmMaLoiId = tblDmMaLoiId;
	}
	
	public java.lang.Long getXoa(){
		return xoa;
	}
	
	public void setXoa(java.lang.Long xoa)
	{
		this.xoa = xoa;
	}
   
    @Override
    public TblDmMaLoiDTO toDTO() {
        TblDmMaLoiDTO tblDmMaLoiDTO = new TblDmMaLoiDTO(); 
        tblDmMaLoiDTO.setTienPhat(this.tienPhat);		
        tblDmMaLoiDTO.setTenLoi(this.tenLoi);		
        tblDmMaLoiDTO.setMaLoi(this.maLoi);		
        tblDmMaLoiDTO.setHoatDong(this.hoatDong);		
        tblDmMaLoiDTO.setTblDmMaLoiId(this.tblDmMaLoiId);		
        tblDmMaLoiDTO.setXoa(this.xoa);		
        return tblDmMaLoiDTO;
    }
}
