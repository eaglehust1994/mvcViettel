package com.viettel.qll.bo;

import com.viettel.qll.dto.TblKhBckqcvDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.erp.bo.TblKhBckqcvBO")
@Table(name = "TBL_KH_BCKQCV")
/**
 *
 * @author: hailh10
 */
public class TblKhBckqcvBO extends BaseFWModelImpl {
     
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "TBL_KH_BCKQCV_SEQ") })
	@Column(name = "KH_BCKQCV_ID", length = 22)
	private java.lang.Long khBckqcvId;
	@Column(name = "NGAY", length = 1500)
	private java.lang.String ngay;
	@Column(name = "THANG", length = 1500)
	private java.lang.String thang;
	@Column(name = "NAM", length = 1500)
	private java.lang.String nam;
	@Column(name = "THOI_GIAN", length = 7)
	private java.util.Date thoiGian;
	@Column(name = "MA_DON_VI", length = 1500)
	private java.lang.String maDonVi;
	@Column(name = "TEN_DON_VI", length = 1500)
	private java.lang.String tenDonVi;
	@Column(name = "SAN_LUONG", length = 22)
	private java.lang.Long sanLuong;
	@Column(name = "DOANH_THU", length = 22)
	private java.lang.Long doanhThu;
	@Column(name = "HCQT", length = 22)
	private java.lang.Long hcqt;
	@Column(name = "LOAI", length = 6)
	private java.lang.String loai;
	@Column(name = "KHOI", length = 150)
	private java.lang.String khoi;
	@Column(name = "THUOC", length = 6)
	private java.lang.String thuoc;
	
	
	public java.lang.String getThuoc() {
		return thuoc;
	}

	public void setThuoc(java.lang.String thuoc) {
		this.thuoc = thuoc;
	}

	public java.lang.String getKhoi() {
		return khoi;
	}

	public void setKhoi(java.lang.String khoi) {
		this.khoi = khoi;
	}

	public java.lang.Long getKhBckqcvId(){
		return khBckqcvId;
	}
	
	public void setKhBckqcvId(java.lang.Long khBckqcvId)
	{
		this.khBckqcvId = khBckqcvId;
	}
	
	public java.lang.String getNgay(){
		return ngay;
	}
	
	public void setNgay(java.lang.String ngay)
	{
		this.ngay = ngay;
	}
	
	public java.lang.String getThang(){
		return thang;
	}
	
	public void setThang(java.lang.String thang)
	{
		this.thang = thang;
	}
	
	public java.lang.String getNam(){
		return nam;
	}
	
	public void setNam(java.lang.String nam)
	{
		this.nam = nam;
	}
	
	public java.util.Date getThoiGian(){
		return thoiGian;
	}
	
	public void setThoiGian(java.util.Date thoiGian)
	{
		this.thoiGian = thoiGian;
	}
	
	public java.lang.String getMaDonVi(){
		return maDonVi;
	}
	
	public void setMaDonVi(java.lang.String maDonVi)
	{
		this.maDonVi = maDonVi;
	}
	
	public java.lang.String getTenDonVi(){
		return tenDonVi;
	}
	
	public void setTenDonVi(java.lang.String tenDonVi)
	{
		this.tenDonVi = tenDonVi;
	}
	
	public java.lang.Long getSanLuong(){
		return sanLuong;
	}
	
	public void setSanLuong(java.lang.Long sanLuong)
	{
		this.sanLuong = sanLuong;
	}
	
	public java.lang.Long getDoanhThu(){
		return doanhThu;
	}
	
	public void setDoanhThu(java.lang.Long doanhThu)
	{
		this.doanhThu = doanhThu;
	}
	
	public java.lang.Long getHcqt(){
		return hcqt;
	}
	
	public void setHcqt(java.lang.Long hcqt)
	{
		this.hcqt = hcqt;
	}
	
	public java.lang.String getLoai(){
		return loai;
	}
	
	public void setLoai(java.lang.String loai)
	{
		this.loai = loai;
	}
   
    @Override
    public TblKhBckqcvDTO toDTO() {
        TblKhBckqcvDTO tblKhBckqcvDTO = new TblKhBckqcvDTO(); 
        tblKhBckqcvDTO.setKhBckqcvId(this.khBckqcvId);		
        tblKhBckqcvDTO.setNgay(this.ngay);		
        tblKhBckqcvDTO.setThang(this.thang);		
        tblKhBckqcvDTO.setNam(this.nam);		
        tblKhBckqcvDTO.setThoiGian(this.thoiGian);		
        tblKhBckqcvDTO.setMaDonVi(this.maDonVi);		
        tblKhBckqcvDTO.setTenDonVi(this.tenDonVi);		
        tblKhBckqcvDTO.setSanLuong(this.sanLuong);		
        tblKhBckqcvDTO.setDoanhThu(this.doanhThu);		
        tblKhBckqcvDTO.setHcqt(this.hcqt);		
        tblKhBckqcvDTO.setLoai(this.loai);
        tblKhBckqcvDTO.setKhoi(this.khoi);
        tblKhBckqcvDTO.setThuoc(this.thuoc);
        return tblKhBckqcvDTO;
    }
}
