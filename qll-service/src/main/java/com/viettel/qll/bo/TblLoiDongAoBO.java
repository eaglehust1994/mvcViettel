package com.viettel.qll.bo;

import com.viettel.qll.dto.TblLoiDongAoDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.qll.bo.TblLoiDongAoBO")
@Table(name = "TBL_LOI_DONG_AO")
/**
 *
 * @author: hailh10
 */
public class TblLoiDongAoBO extends BaseFWModelImpl {
     
	@Column(name = "TINH", length = 300)
	private java.lang.String tinh;
	@Column(name = "XOA", length = 22)
	private java.lang.Long xoa;
	@Column(name = "THANH_TIEN", length = 22)
	private java.lang.Float thanhTien;
	@Column(name = "DON_GIA", length = 22)
	private java.lang.Float donGia;
	@Column(name = "TONG_LOI", length = 22)
	private java.lang.Float tongLoi;
	@Column(name = "LOI_TICH_HEN_AO", length = 22)
	private java.lang.Float loiTichHenAo;
	@Column(name = "LOI_DONG_AO", length = 22)
	private java.lang.Float loiDongAo;
	@Column(name = "LOI_THAI_DO", length = 22)
	private java.lang.Float loiThaiDo;
	@Column(name = "LOI_NGHIEP_VU", length = 22)
	private java.lang.Float loiNghiepVu;
	@Column(name = "MA_NV", length = 300)
	private java.lang.String maNv;
	@Column(name = "ACCOUNT_KH", length = 600)
	private java.lang.String accountKh;
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "PHAT_LOI_DONG_AO_SEQ") })
	@Column(name = "TBL_LOI_DONG_AO_ID", length = 22)
	private java.lang.Long tblLoiDongAoId;
	@Column(name = "THANG", length = 300)
	private java.lang.String thang;
	@Column(name = "HOAT_DONG", length = 22)
	private java.lang.Long hoatDong;
	@Column(name = "QUAN_HUYEN", length = 1500)
	private java.lang.String quanHuyen;
	@Column(name = "LOAI_DV", length = 1500)
	private java.lang.String loaiDv;

	
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
	
	public java.lang.Float getThanhTien(){
		return thanhTien;
	}
	
	public void setThanhTien(java.lang.Float thanhTien)
	{
		this.thanhTien = thanhTien;
	}
	
	public java.lang.Float getDonGia(){
		return donGia;
	}
	
	public void setDonGia(java.lang.Float donGia)
	{
		this.donGia = donGia;
	}
	
	public java.lang.Float getTongLoi(){
		return tongLoi;
	}
	
	public void setTongLoi(java.lang.Float tongLoi)
	{
		this.tongLoi = tongLoi;
	}
	
	public java.lang.Float getLoiTichHenAo(){
		return loiTichHenAo;
	}
	
	public void setLoiTichHenAo(java.lang.Float loiTichHenAo)
	{
		this.loiTichHenAo = loiTichHenAo;
	}
	
	public java.lang.Float getLoiDongAo(){
		return loiDongAo;
	}
	
	public void setLoiDongAo(java.lang.Float loiDongAo)
	{
		this.loiDongAo = loiDongAo;
	}
	
	public java.lang.Float getLoiThaiDo(){
		return loiThaiDo;
	}
	
	public void setLoiThaiDo(java.lang.Float loiThaiDo)
	{
		this.loiThaiDo = loiThaiDo;
	}
	
	public java.lang.Float getLoiNghiepVu(){
		return loiNghiepVu;
	}
	
	public void setLoiNghiepVu(java.lang.Float loiNghiepVu)
	{
		this.loiNghiepVu = loiNghiepVu;
	}
	
	public java.lang.String getMaNv(){
		return maNv;
	}
	
	public void setMaNv(java.lang.String maNv)
	{
		this.maNv = maNv;
	}
	
	public java.lang.String getAccountKh(){
		return accountKh;
	}
	
	public void setAccountKh(java.lang.String accountKh)
	{
		this.accountKh = accountKh;
	}
	
	public java.lang.Long getTblLoiDongAoId(){
		return tblLoiDongAoId;
	}
	
	public void setTblLoiDongAoId(java.lang.Long tblLoiDongAoId)
	{
		this.tblLoiDongAoId = tblLoiDongAoId;
	}
	
	public java.lang.String getThang(){
		return thang;
	}
	
	public void setThang(java.lang.String thang)
	{
		this.thang = thang;
	}
	
	public java.lang.Long getHoatDong(){
		return hoatDong;
	}
	
	public void setHoatDong(java.lang.Long hoatDong)
	{
		this.hoatDong = hoatDong;
	}
	
	public java.lang.String getQuanHuyen(){
		return quanHuyen;
	}
	
	public void setQuanHuyen(java.lang.String quanHuyen)
	{
		this.quanHuyen = quanHuyen;
	}
	
	public java.lang.String getLoaiDv(){
		return loaiDv;
	}
	
	public void setLoaiDv(java.lang.String loaiDv)
	{
		this.loaiDv = loaiDv;
	}
   
    @Override
    public TblLoiDongAoDTO toDTO() {
        TblLoiDongAoDTO tblLoiDongAoDTO = new TblLoiDongAoDTO(); 
        tblLoiDongAoDTO.setTinh(this.tinh);		
        tblLoiDongAoDTO.setXoa(this.xoa);		
        tblLoiDongAoDTO.setThanhTien(this.thanhTien);		
        tblLoiDongAoDTO.setDonGia(this.donGia);		
        tblLoiDongAoDTO.setTongLoi(this.tongLoi);		
        tblLoiDongAoDTO.setLoiTichHenAo(this.loiTichHenAo);		
        tblLoiDongAoDTO.setLoiDongAo(this.loiDongAo);		
        tblLoiDongAoDTO.setLoiThaiDo(this.loiThaiDo);		
        tblLoiDongAoDTO.setLoiNghiepVu(this.loiNghiepVu);		
        tblLoiDongAoDTO.setMaNv(this.maNv);		
        tblLoiDongAoDTO.setAccountKh(this.accountKh);		
        tblLoiDongAoDTO.setTblLoiDongAoId(this.tblLoiDongAoId);		
        tblLoiDongAoDTO.setThang(this.thang);		
        tblLoiDongAoDTO.setHoatDong(this.hoatDong);		
        tblLoiDongAoDTO.setQuanHuyen(this.quanHuyen);		
        tblLoiDongAoDTO.setLoaiDv(this.loaiDv);		
        return tblLoiDongAoDTO;
    }
}
