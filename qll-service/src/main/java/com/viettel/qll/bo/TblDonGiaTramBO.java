package com.viettel.qll.bo;

import com.viettel.qll.dto.TblDonGiaTramDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.qll.bo.TblDonGiaTramBO")
@Table(name = "TBL_DON_GIA_TRAM")
/**
 *
 * @author: hailh10
 */
public class TblDonGiaTramBO extends BaseFWModelImpl {
     
	@Column(name = "PL", length = 22)
	private java.lang.Long pl;
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "TBL_DON_GIA_TRAM_SEQ") })
	@Column(name = "TBL_DON_GIA_TRAM_ID", length = 22)
	private java.lang.Long tblDonGiaTramId;
	@Column(name = "DIA_HINH", length = 1000)
	private java.lang.String diaHinh;
	@Column(name = "DON_GIA", length = 22)
	private java.lang.Float donGia;
	@Column(name = "GHI_CHU", length = 4000)
	private java.lang.String ghiChu;
	@Column(name = "LOAI_TRAM", length = 1500)
	private java.lang.String loaiTram;
	@Column(name = "VUNG_LUONG", length = 100)
	private java.lang.String vungLuong;

	
	public java.lang.Long getPl(){
		return pl;
	}
	
	public void setPl(java.lang.Long pl)
	{
		this.pl = pl;
	}
	
	public java.lang.Long getTblDonGiaTramId(){
		return tblDonGiaTramId;
	}
	
	public void setTblDonGiaTramId(java.lang.Long tblDonGiaTramId)
	{
		this.tblDonGiaTramId = tblDonGiaTramId;
	}
	
	public java.lang.String getDiaHinh(){
		return diaHinh;
	}
	
	public void setDiaHinh(java.lang.String diaHinh)
	{
		this.diaHinh = diaHinh;
	}
	
	public java.lang.Float getDonGia(){
		return donGia;
	}
	
	public void setDonGia(java.lang.Float donGia)
	{
		this.donGia = donGia;
	}
	
	public java.lang.String getGhiChu(){
		return ghiChu;
	}
	
	public void setGhiChu(java.lang.String ghiChu)
	{
		this.ghiChu = ghiChu;
	}
	
	public java.lang.String getLoaiTram(){
		return loaiTram;
	}
	
	public void setLoaiTram(java.lang.String loaiTram)
	{
		this.loaiTram = loaiTram;
	}
	
	public java.lang.String getVungLuong(){
		return vungLuong;
	}
	
	public void setVungLuong(java.lang.String vungLuong)
	{
		this.vungLuong = vungLuong;
	}
   
    @Override
    public TblDonGiaTramDTO toDTO() {
        TblDonGiaTramDTO tblDonGiaTramDTO = new TblDonGiaTramDTO(); 
        tblDonGiaTramDTO.setPl(this.pl);		
        tblDonGiaTramDTO.setTblDonGiaTramId(this.tblDonGiaTramId);		
        tblDonGiaTramDTO.setDiaHinh(this.diaHinh);		
        tblDonGiaTramDTO.setDonGia(this.donGia);		
        tblDonGiaTramDTO.setGhiChu(this.ghiChu);		
        tblDonGiaTramDTO.setLoaiTram(this.loaiTram);		
        tblDonGiaTramDTO.setVungLuong(this.vungLuong);		
        return tblDonGiaTramDTO;
    }
}
