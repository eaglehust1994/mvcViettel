package com.viettel.qll.bo;

import com.viettel.qll.dto.TblDonGiaThueBaoDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.qll.bo.TblDonGiaThueBaoBO")
@Table(name = "TBL_DON_GIA_THUE_BAO")
/**
 *
 * @author: hailh10
 */
public class TblDonGiaThueBaoBO extends BaseFWModelImpl {
     
	@Column(name = "HOAT_DONG", length = 22)
	private java.lang.Long hoatDong;
	@Column(name = "TINH", length = 300)
	private java.lang.String tinh;
	@Column(name = "XOA", length = 22)
	private java.lang.Long xoa;
	@Column(name = "VUNG_LUONG", length = 600)
	private java.lang.String vungLuong;
	@Column(name = "DON_GIA_CHU_DAU_TU", length = 22)
	private java.lang.Float donGiaChuDauTu;
	@Column(name = "DON_GIA_MOI", length = 22)
	private java.lang.Float donGiaMoi;
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "TBL_DON_GIA_THUE_BAO_SEQ") })
	@Column(name = "TBL_DON_GIA_THUE_BAO_ID", length = 22)
	private java.lang.Long tblDonGiaThueBaoId;
	@Column(name = "GHI_CHU", length = 4000)
	private java.lang.String ghiChu;

	
	public java.lang.Long getHoatDong(){
		return hoatDong;
	}
	
	public void setHoatDong(java.lang.Long hoatDong)
	{
		this.hoatDong = hoatDong;
	}
	
	public java.lang.String getTinh(){
		return tinh;
	}
	
	public void setTinh(java.lang.String tinh)
	{
		this.tinh = tinh;
	}
	
	public java.lang.Long getXoa(){
		return xoa;
	}
	
	public void setXoa(java.lang.Long xoa)
	{
		this.xoa = xoa;
	}
	
	public java.lang.String getVungLuong(){
		return vungLuong;
	}
	
	public void setVungLuong(java.lang.String vungLuong)
	{
		this.vungLuong = vungLuong;
	}
	
	public java.lang.Float getDonGiaChuDauTu(){
		return donGiaChuDauTu;
	}
	
	public void setDonGiaChuDauTu(java.lang.Float donGiaChuDauTu)
	{
		this.donGiaChuDauTu = donGiaChuDauTu;
	}
	
	public java.lang.Float getDonGiaMoi(){
		return donGiaMoi;
	}
	
	public void setDonGiaMoi(java.lang.Float donGiaMoi)
	{
		this.donGiaMoi = donGiaMoi;
	}
	
	public java.lang.Long getTblDonGiaThueBaoId(){
		return tblDonGiaThueBaoId;
	}
	
	public void setTblDonGiaThueBaoId(java.lang.Long tblDonGiaThueBaoId)
	{
		this.tblDonGiaThueBaoId = tblDonGiaThueBaoId;
	}
	
	public java.lang.String getGhiChu(){
		return ghiChu;
	}
	
	public void setGhiChu(java.lang.String ghiChu)
	{
		this.ghiChu = ghiChu;
	}
   
    @Override
    public TblDonGiaThueBaoDTO toDTO() {
        TblDonGiaThueBaoDTO tblDonGiaThueBaoDTO = new TblDonGiaThueBaoDTO(); 
        tblDonGiaThueBaoDTO.setHoatDong(this.hoatDong);		
        tblDonGiaThueBaoDTO.setTinh(this.tinh);		
        tblDonGiaThueBaoDTO.setXoa(this.xoa);		
        tblDonGiaThueBaoDTO.setVungLuong(this.vungLuong);		
        tblDonGiaThueBaoDTO.setDonGiaChuDauTu(this.donGiaChuDauTu);		
        tblDonGiaThueBaoDTO.setDonGiaMoi(this.donGiaMoi);		
        tblDonGiaThueBaoDTO.setTblDonGiaThueBaoId(this.tblDonGiaThueBaoId);		
        tblDonGiaThueBaoDTO.setGhiChu(this.ghiChu);		
        return tblDonGiaThueBaoDTO;
    }
}
