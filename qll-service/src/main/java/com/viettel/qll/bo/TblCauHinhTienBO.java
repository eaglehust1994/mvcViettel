package com.viettel.qll.bo;

import com.viettel.qll.dto.TblCauHinhTienDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.qll.bo.TblCauHinhTienBO")
@Table(name = "TBL_CAU_HINH_TIEN")
/**
 *
 * @author: hailh10
 */
public class TblCauHinhTienBO extends BaseFWModelImpl {
     
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "TBL_CAU_HINH_TIEN_SEQ") })
	@Column(name = "TBL_CAU_HINH_TIEN_ID", length = 22)
	private java.lang.Long tblCauHinhTienId;
	@Column(name = "HOAT_DONG", length = 22)
	private java.lang.Long hoatDong;
	@Column(name = "XOA", length = 22)
	private java.lang.Long xoa;
	@Column(name = "LOAI_CONG_VIEC", length = 600)
	private java.lang.String loaiCongViec;
	@Column(name = "HE_SO_NGOAI_NHA", length = 22)
	private java.lang.Float heSoNgoaiNha;
	@Column(name = "NGAY", length = 600)
	private java.lang.String ngay;
	@Column(name = "MUC_TIEN", length = 22)
	private java.lang.Float mucTien;
	@Column(name = "HE_SO_TRONG_NHA", length = 22)
	private java.lang.Float heSoTrongNha;
	@Column(name = "HA_TANG", length = 600)
	private java.lang.String haTang;

	
	public java.lang.Long getTblCauHinhTienId(){
		return tblCauHinhTienId;
	}
	
	public void setTblCauHinhTienId(java.lang.Long tblCauHinhTienId)
	{
		this.tblCauHinhTienId = tblCauHinhTienId;
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
	
	public java.lang.String getLoaiCongViec(){
		return loaiCongViec;
	}
	
	public void setLoaiCongViec(java.lang.String loaiCongViec)
	{
		this.loaiCongViec = loaiCongViec;
	}
	
	public java.lang.Float getHeSoNgoaiNha(){
		return heSoNgoaiNha;
	}
	
	public void setHeSoNgoaiNha(java.lang.Float heSoNgoaiNha)
	{
		this.heSoNgoaiNha = heSoNgoaiNha;
	}
	
	public java.lang.String getNgay(){
		return ngay;
	}
	
	public void setNgay(java.lang.String ngay)
	{
		this.ngay = ngay;
	}
	
	public java.lang.Float getMucTien(){
		return mucTien;
	}
	
	public void setMucTien(java.lang.Float mucTien)
	{
		this.mucTien = mucTien;
	}
	
	public java.lang.Float getHeSoTrongNha(){
		return heSoTrongNha;
	}
	
	public void setHeSoTrongNha(java.lang.Float heSoTrongNha)
	{
		this.heSoTrongNha = heSoTrongNha;
	}
	
	public java.lang.String getHaTang(){
		return haTang;
	}
	
	public void setHaTang(java.lang.String haTang)
	{
		this.haTang = haTang;
	}
   
    @Override
    public TblCauHinhTienDTO toDTO() {
        TblCauHinhTienDTO tblCauHinhTienDTO = new TblCauHinhTienDTO(); 
        tblCauHinhTienDTO.setTblCauHinhTienId(this.tblCauHinhTienId);		
        tblCauHinhTienDTO.setHoatDong(this.hoatDong);		
        tblCauHinhTienDTO.setXoa(this.xoa);		
        tblCauHinhTienDTO.setLoaiCongViec(this.loaiCongViec);		
        tblCauHinhTienDTO.setHeSoNgoaiNha(this.heSoNgoaiNha);		
        tblCauHinhTienDTO.setNgay(this.ngay);		
        tblCauHinhTienDTO.setMucTien(this.mucTien);		
        tblCauHinhTienDTO.setHeSoTrongNha(this.heSoTrongNha);		
        tblCauHinhTienDTO.setHaTang(this.haTang);		
        return tblCauHinhTienDTO;
    }
}
