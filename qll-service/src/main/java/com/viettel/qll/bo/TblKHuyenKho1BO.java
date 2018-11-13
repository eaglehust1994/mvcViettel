package com.viettel.qll.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.viettel.qll.dto.TblKHuyenKho1DTO;
import com.viettel.service.base.model.BaseFWModelImpl;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.qll.bo.TblKHuyenKho1BO")
@Table(name = "TBL_K_HUYEN_KHO_1")
/**
 *
 * @author: hailh10
 */
public class TblKHuyenKho1BO extends BaseFWModelImpl {
     
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "TBL_K_HUYEN_KHO_1_SEQ") })
	@Column(name = "TBL_K_HUYEN_KHO_ID", length = 22)
	private java.lang.Long tblKHuyenKhoId;
	@Column(name = "MA_DON_VI", length = 300)
	private java.lang.String maDonVi;
	@Column(name = "KI_KHO_KHAN", length = 22)
	private java.lang.Float kiKhoKhan;
	@Column(name = "DE_XUAT_LOAI_HUYEN", length = 300)
	private java.lang.String deXuatLoaiHuyen;
	@Column(name = "NHOM_DOI_TUONG", length = 22)
	private java.lang.Long nhomDoiTuong;
	@Column(name = "HOAT_DONG", length = 22)
	private java.lang.Long hoatDong;
	@Column(name = "XOA", length = 22)
	private java.lang.Long xoa;
	@Column(name = "THANG", length = 100)
	private java.lang.String thang;
	@Column(name = "TINH", length = 100)
	private java.lang.String tinh;
	
	@Column(name = "TEN_DON_VI", length = 100)
	private java.lang.String tenDonVi;

	
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
	
	public java.lang.Float getKiKhoKhan(){
		return kiKhoKhan;
	}
	
	public void setKiKhoKhan(java.lang.Float kiKhoKhan)
	{
		this.kiKhoKhan = kiKhoKhan;
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
	
	
   
    public java.lang.String getTenDonVi() {
		return tenDonVi;
	}

	public void setTenDonVi(java.lang.String tenDonVi) {
		this.tenDonVi = tenDonVi;
	}

	@Override
    public TblKHuyenKho1DTO toDTO() {
        TblKHuyenKho1DTO tblKHuyenKho1DTO = new TblKHuyenKho1DTO(); 
        tblKHuyenKho1DTO.setTblKHuyenKhoId(this.tblKHuyenKhoId);		
        tblKHuyenKho1DTO.setMaDonVi(this.maDonVi);		
        tblKHuyenKho1DTO.setKiKhoKhan(this.kiKhoKhan);		
        tblKHuyenKho1DTO.setDeXuatLoaiHuyen(this.deXuatLoaiHuyen);		
        tblKHuyenKho1DTO.setNhomDoiTuong(this.nhomDoiTuong);		
        tblKHuyenKho1DTO.setHoatDong(this.hoatDong);		
        tblKHuyenKho1DTO.setXoa(this.xoa);		
        tblKHuyenKho1DTO.setThang(this.thang);		
        tblKHuyenKho1DTO.setTinh(this.tinh);	
        tblKHuyenKho1DTO.setTenDonVi(this.tenDonVi);
        return tblKHuyenKho1DTO;
    }
}
