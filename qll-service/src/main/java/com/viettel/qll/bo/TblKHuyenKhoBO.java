package com.viettel.qll.bo;

import com.viettel.qll.dto.TblKHuyenKhoDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.qll.bo.TblKHuyenKhoBO")
@Table(name = "TBL_K_HUYEN_KHO")
/**
 *
 * @author: hailh10
 */
public class TblKHuyenKhoBO extends BaseFWModelImpl {
     
	@Column(name = "XOA", length = 22)
	private java.lang.Long xoa;
	@Column(name = "TINH", length = 2400)
	private java.lang.String tinh;
	@Column(name = "THANG", length = 2400)
	private java.lang.String thang;
	@Column(name = "TEN_DON_VI", length = 2400)
	private java.lang.String tenDonVi;
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "TBL_K_HUYEN_KHO_SEQ") })
	@Column(name = "TBL_K_HUYEN_KHO_ID", length = 22)
	private java.lang.Long tblKHuyenKhoId;
	@Column(name = "MA_DON_VI", length = 2400)
	private java.lang.String maDonVi;
	@Column(name = "K_KHO_KHAN", length = 22)
	private java.lang.Float kKhoKhan;
	@Column(name = "HOAT_DONG", length = 22)
	private java.lang.Long hoatDong;
	@Column(name = "DE_XUAT_LOAI_HUYEN", length = 1200)
	private java.lang.String deXuatLoaiHuyen;
	@Column(name = "NHOM_DOI_TUONG", length = 22)
	private java.lang.Long nhomDoiTuong;

	
	public java.lang.Long getXoa(){
		return xoa;
	}
	
	public void setXoa(java.lang.Long xoa)
	{
		this.xoa = xoa;
	}
	
	public java.lang.String getTinh(){
		return tinh;
	}
	
	public void setTinh(java.lang.String tinh)
	{
		this.tinh = tinh;
	}
	
	public java.lang.String getThang(){
		return thang;
	}
	
	public void setThang(java.lang.String thang)
	{
		this.thang = thang;
	}
	
	public java.lang.String getTenDonVi(){
		return tenDonVi;
	}
	
	public void setTenDonVi(java.lang.String tenDonVi)
	{
		this.tenDonVi = tenDonVi;
	}
	
	public java.lang.Long getTblKHuyenKhoId(){
		return tblKHuyenKhoId;
	}
	
	public void setTblKHuyenKhoId(java.lang.Long tblKHuyenKhoId)
	{
		this.tblKHuyenKhoId = tblKHuyenKhoId;
	}
	
	public java.lang.String getMaDonVi(){
		return maDonVi;
	}
	
	public void setMaDonVi(java.lang.String maDonVi)
	{
		this.maDonVi = maDonVi;
	}
	
	public java.lang.Float getKKhoKhan(){
		return kKhoKhan;
	}
	
	public void setKKhoKhan(java.lang.Float kKhoKhan)
	{
		this.kKhoKhan = kKhoKhan;
	}
	
	public java.lang.Long getHoatDong(){
		return hoatDong;
	}
	
	public void setHoatDong(java.lang.Long hoatDong)
	{
		this.hoatDong = hoatDong;
	}
	
	public java.lang.String getDeXuatLoaiHuyen(){
		return deXuatLoaiHuyen;
	}
	
	public void setDeXuatLoaiHuyen(java.lang.String deXuatLoaiHuyen)
	{
		this.deXuatLoaiHuyen = deXuatLoaiHuyen;
	}
	
	public java.lang.Long getNhomDoiTuong(){
		return nhomDoiTuong;
	}
	
	public void setNhomDoiTuong(java.lang.Long nhomDoiTuong)
	{
		this.nhomDoiTuong = nhomDoiTuong;
	}
   
    @Override
    public TblKHuyenKhoDTO toDTO() {
        TblKHuyenKhoDTO tblKHuyenKhoDTO = new TblKHuyenKhoDTO(); 
        tblKHuyenKhoDTO.setXoa(this.xoa);		
        tblKHuyenKhoDTO.setTinh(this.tinh);		
        tblKHuyenKhoDTO.setThang(this.thang);		
        tblKHuyenKhoDTO.setTenDonVi(this.tenDonVi);		
        tblKHuyenKhoDTO.setTblKHuyenKhoId(this.tblKHuyenKhoId);		
        tblKHuyenKhoDTO.setMaDonVi(this.maDonVi);		
        tblKHuyenKhoDTO.setKKhoKhan(this.kKhoKhan);		
        tblKHuyenKhoDTO.setHoatDong(this.hoatDong);		
        tblKHuyenKhoDTO.setDeXuatLoaiHuyen(this.deXuatLoaiHuyen);		
        tblKHuyenKhoDTO.setNhomDoiTuong(this.nhomDoiTuong);		
        return tblKHuyenKhoDTO;
    }
}
