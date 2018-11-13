package com.viettel.qll.bo;

import com.viettel.qll.dto.TblPhatKhacDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.qll.bo.TblPhatKhacBO")
@Table(name = "TBL_PHAT_KHAC")
/**
 *
 * @author: hailh10
 */
public class TblPhatKhacBO extends BaseFWModelImpl {
     
	@Column(name = "HOAT_DONG", length = 22)
	private java.lang.Long hoatDong;
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "TBL_PHAT_KHAC_SEQ") })
	@Column(name = "TBL_PHAT_KHAC_ID", length = 22)
	private java.lang.Long tblPhatKhacId;
	@Column(name = "UCTT_CHAM_DAN", length = 22)
	private java.lang.Float ucttChamDan;
	@Column(name = "GIAN_LAN_XANG_DAU", length = 22)
	private java.lang.Float gianLanXangDau;
	@Column(name = "LOI_RA_VA_NHA_TRAM", length = 22)
	private java.lang.Float loiRaVaNhaTram;
	@Column(name = "VI_PHAM_DONG_AO", length = 22)
	private java.lang.Float viPhamDongAo;
	@Column(name = "GIAN_DOAN_THONG_TIN", length = 22)
	private java.lang.Float gianDoanThongTin;
	@Column(name = "PHAT_TRUC_TIEP", length = 22)
	private java.lang.Float phatTrucTiep;
	@Column(name = "MA_TRAM", length = 300)
	private java.lang.String maTram;
	@Column(name = "MA_NV", length = 300)
	private java.lang.String maNv;
	@Column(name = "NHAN_VIEN", length = 1500)
	private java.lang.String nhanVien;
	@Column(name = "MA_TINH", length = 300)
	private java.lang.String maTinh;
	@Column(name = "KV", length = 1500)
	private java.lang.String kv;
	@Column(name = "THANG", length = 300)
	private java.lang.String thang;
	@Column(name = "XOA", length = 22)
	private java.lang.Long xoa;

	
	public java.lang.Long getHoatDong(){
		return hoatDong;
	}
	
	public void setHoatDong(java.lang.Long hoatDong)
	{
		this.hoatDong = hoatDong;
	}
	
	public java.lang.Long getTblPhatKhacId(){
		return tblPhatKhacId;
	}
	
	public void setTblPhatKhacId(java.lang.Long tblPhatKhacId)
	{
		this.tblPhatKhacId = tblPhatKhacId;
	}
	
	public java.lang.Float getUcttChamDan(){
		return ucttChamDan;
	}
	
	public void setUcttChamDan(java.lang.Float ucttChamDan)
	{
		this.ucttChamDan = ucttChamDan;
	}
	
	public java.lang.Float getGianLanXangDau(){
		return gianLanXangDau;
	}
	
	public void setGianLanXangDau(java.lang.Float gianLanXangDau)
	{
		this.gianLanXangDau = gianLanXangDau;
	}
	
	public java.lang.Float getLoiRaVaNhaTram(){
		return loiRaVaNhaTram;
	}
	
	public void setLoiRaVaNhaTram(java.lang.Float loiRaVaNhaTram)
	{
		this.loiRaVaNhaTram = loiRaVaNhaTram;
	}
	
	public java.lang.Float getViPhamDongAo(){
		return viPhamDongAo;
	}
	
	public void setViPhamDongAo(java.lang.Float viPhamDongAo)
	{
		this.viPhamDongAo = viPhamDongAo;
	}
	
	public java.lang.Float getGianDoanThongTin(){
		return gianDoanThongTin;
	}
	
	public void setGianDoanThongTin(java.lang.Float gianDoanThongTin)
	{
		this.gianDoanThongTin = gianDoanThongTin;
	}
	
	public java.lang.Float getPhatTrucTiep(){
		return phatTrucTiep;
	}
	
	public void setPhatTrucTiep(java.lang.Float phatTrucTiep)
	{
		this.phatTrucTiep = phatTrucTiep;
	}
	
	public java.lang.String getMaTram(){
		return maTram;
	}
	
	public void setMaTram(java.lang.String maTram)
	{
		this.maTram = maTram;
	}
	
	public java.lang.String getMaNv(){
		return maNv;
	}
	
	public void setMaNv(java.lang.String maNv)
	{
		this.maNv = maNv;
	}
	
	public java.lang.String getNhanVien(){
		return nhanVien;
	}
	
	public void setNhanVien(java.lang.String nhanVien)
	{
		this.nhanVien = nhanVien;
	}
	
	public java.lang.String getMaTinh(){
		return maTinh;
	}
	
	public void setMaTinh(java.lang.String maTinh)
	{
		this.maTinh = maTinh;
	}
	
	public java.lang.String getKv(){
		return kv;
	}
	
	public void setKv(java.lang.String kv)
	{
		this.kv = kv;
	}
	
	public java.lang.String getThang(){
		return thang;
	}
	
	public void setThang(java.lang.String thang)
	{
		this.thang = thang;
	}
	
	public java.lang.Long getXoa(){
		return xoa;
	}
	
	public void setXoa(java.lang.Long xoa)
	{
		this.xoa = xoa;
	}
   
    @Override
    public TblPhatKhacDTO toDTO() {
        TblPhatKhacDTO tblPhatKhacDTO = new TblPhatKhacDTO(); 
        tblPhatKhacDTO.setHoatDong(this.hoatDong);		
        tblPhatKhacDTO.setTblPhatKhacId(this.tblPhatKhacId);		
        tblPhatKhacDTO.setUcttChamDan(this.ucttChamDan);		
        tblPhatKhacDTO.setGianLanXangDau(this.gianLanXangDau);		
        tblPhatKhacDTO.setLoiRaVaNhaTram(this.loiRaVaNhaTram);		
        tblPhatKhacDTO.setViPhamDongAo(this.viPhamDongAo);		
        tblPhatKhacDTO.setGianDoanThongTin(this.gianDoanThongTin);		
        tblPhatKhacDTO.setPhatTrucTiep(this.phatTrucTiep);		
        tblPhatKhacDTO.setMaTram(this.maTram);		
        tblPhatKhacDTO.setMaNv(this.maNv);		
        tblPhatKhacDTO.setNhanVien(this.nhanVien);		
        tblPhatKhacDTO.setMaTinh(this.maTinh);		
        tblPhatKhacDTO.setKv(this.kv);		
        tblPhatKhacDTO.setThang(this.thang);		
        tblPhatKhacDTO.setXoa(this.xoa);		
        return tblPhatKhacDTO;
    }
}
