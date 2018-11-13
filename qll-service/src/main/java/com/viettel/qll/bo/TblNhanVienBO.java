package com.viettel.qll.bo;

import com.viettel.qll.dto.TblNhanVienDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.qll.bo.TblNhanVienBO")
@Table(name = "TBL_NHAN_VIEN")
/**
 *
 * @author: hailh10
 */
public class TblNhanVienBO extends BaseFWModelImpl {
     
	@Column(name = "CHUC_DANH", length = 600)
	private java.lang.String chucDanh;
	@Column(name = "DAY_MAY_NHA_TRAM", length = 22)
	private java.lang.Long dayMayNhaTram;
	@Column(name = "DON_VI", length = 300)
	private java.lang.String donVi;
	@Column(name = "HO_VA_TEN", length = 600)
	private java.lang.String hoVaTen;
	@Column(name = "MA_NV", length = 300)
	private java.lang.String maNv;
	@Column(name = "XOA", length = 22)
	private java.lang.Long xoa;
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "TBL_NHAN_VIEN_SEQ") })
	@Column(name = "TBL_NHAN_VIEN_ID", length = 22)
	private java.lang.Long tblNhanVienId;
	@Column(name = "HOAT_DONG", length = 22)
	private java.lang.Long hoatDong;

	
	public java.lang.String getChucDanh(){
		return chucDanh;
	}
	
	public void setChucDanh(java.lang.String chucDanh)
	{
		this.chucDanh = chucDanh;
	}
	
	public java.lang.Long getDayMayNhaTram(){
		return dayMayNhaTram;
	}
	
	public void setDayMayNhaTram(java.lang.Long dayMayNhaTram)
	{
		this.dayMayNhaTram = dayMayNhaTram;
	}
	
	public java.lang.String getDonVi(){
		return donVi;
	}
	
	public void setDonVi(java.lang.String donVi)
	{
		this.donVi = donVi;
	}
	
	public java.lang.String getHoVaTen(){
		return hoVaTen;
	}
	
	public void setHoVaTen(java.lang.String hoVaTen)
	{
		this.hoVaTen = hoVaTen;
	}
	
	public java.lang.String getMaNv(){
		return maNv;
	}
	
	public void setMaNv(java.lang.String maNv)
	{
		this.maNv = maNv;
	}
	
	public java.lang.Long getXoa(){
		return xoa;
	}
	
	public void setXoa(java.lang.Long xoa)
	{
		this.xoa = xoa;
	}
	
	public java.lang.Long getTblNhanVienId(){
		return tblNhanVienId;
	}
	
	public void setTblNhanVienId(java.lang.Long tblNhanVienId)
	{
		this.tblNhanVienId = tblNhanVienId;
	}
	
	public java.lang.Long getHoatDong(){
		return hoatDong;
	}
	
	public void setHoatDong(java.lang.Long hoatDong)
	{
		this.hoatDong = hoatDong;
	}
   
    @Override
    public TblNhanVienDTO toDTO() {
        TblNhanVienDTO tblNhanVienDTO = new TblNhanVienDTO(); 
        tblNhanVienDTO.setChucDanh(this.chucDanh);		
        tblNhanVienDTO.setDayMayNhaTram(this.dayMayNhaTram);		
        tblNhanVienDTO.setDonVi(this.donVi);		
        tblNhanVienDTO.setHoVaTen(this.hoVaTen);		
        tblNhanVienDTO.setMaNv(this.maNv);		
        tblNhanVienDTO.setXoa(this.xoa);		
        tblNhanVienDTO.setTblNhanVienId(this.tblNhanVienId);		
        tblNhanVienDTO.setHoatDong(this.hoatDong);		
        return tblNhanVienDTO;
    }
}
