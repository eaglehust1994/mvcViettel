package com.viettel.qll.bo;

import com.viettel.qll.dto.TblLuongNvTramDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.qll.bo.TblLuongNvTramBO")
@Table(name = "TBL_LUONG_NV_TRAM")
/**
 *
 * @author: hailh10
 */
public class TblLuongNvTramBO extends BaseFWModelImpl {
     
	@Column(name = "MA_NV", length = 300)
	private java.lang.String maNv;
	@Column(name = "HO_TEN", length = 600)
	private java.lang.String hoTen;
	@Column(name = "TINH", length = 300)
	private java.lang.String tinh;
	@Column(name = "THANG", length = 300)
	private java.lang.String thang;
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "TBL_LUONG_NV_TRAM_SEQ") })
	@Column(name = "TBL_LUONG_NV_TRAM_ID", length = 22)
	private java.lang.Long tblLuongNvTramId;
	@Column(name = "HOAT_DONG", length = 22)
	private java.lang.Long hoatDong;
	@Column(name = "XOA", length = 22)
	private java.lang.Long xoa;
	@Column(name = "LUONG", length = 22)
	private java.lang.Float luong;
	@Column(name = "PHAT_LOI_7", length = 22)
	private java.lang.Float phatLoi7;
	@Column(name = "PHAT_LOI_6", length = 22)
	private java.lang.Float phatLoi6;
	@Column(name = "PHAT_LOI_5", length = 22)
	private java.lang.Float phatLoi5;
	@Column(name = "PHAT_LOI_4", length = 22)
	private java.lang.Float phatLoi4;
	@Column(name = "PHAT_LOI_3", length = 22)
	private java.lang.Float phatLoi3;
	@Column(name = "PHAT_LOI_2", length = 22)
	private java.lang.Float phatLoi2;
	@Column(name = "PHAT_LOI_1", length = 22)
	private java.lang.Float phatLoi1;
	@Column(name = "PHI_BAN_HANG", length = 22)
	private java.lang.Float phiBanHang;
	@Column(name = "LUONG_DUY_TRI_TUNG_TRAM", length = 22)
	private java.lang.Float luongDuyTriTungTram;
	@Column(name = "NGAY_CONG_CHE_DO", length = 22)
	private java.lang.Float ngayCongCheDo;
	@Column(name = "NGAY_CONG_TINH_LUONG", length = 22)
	private java.lang.Float ngayCongTinhLuong;
	@Column(name = "HE_SO_DIEU_CHINH", length = 22)
	private java.lang.Float heSoDieuChinh;
	@Column(name = "KPI_DON_VI", length = 22)
	private java.lang.Float kpiDonVi;
	@Column(name = "SO_TRAM_QUAN_LY", length = 1500)
	private java.lang.String soTramQuanLy;
	@Column(name = "HUYEN", length = 1500)
	private java.lang.String huyen;

	
	public java.lang.String getMaNv(){
		return maNv;
	}
	
	public void setMaNv(java.lang.String maNv)
	{
		this.maNv = maNv;
	}
	
	public java.lang.String getHoTen(){
		return hoTen;
	}
	
	public void setHoTen(java.lang.String hoTen)
	{
		this.hoTen = hoTen;
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
	
	public java.lang.Long getTblLuongNvTramId(){
		return tblLuongNvTramId;
	}
	
	public void setTblLuongNvTramId(java.lang.Long tblLuongNvTramId)
	{
		this.tblLuongNvTramId = tblLuongNvTramId;
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
	
	public java.lang.Float getLuong(){
		return luong;
	}
	
	public void setLuong(java.lang.Float luong)
	{
		this.luong = luong;
	}
	
	public java.lang.Float getPhatLoi7(){
		return phatLoi7;
	}
	
	public void setPhatLoi7(java.lang.Float phatLoi7)
	{
		this.phatLoi7 = phatLoi7;
	}
	
	public java.lang.Float getPhatLoi6(){
		return phatLoi6;
	}
	
	public void setPhatLoi6(java.lang.Float phatLoi6)
	{
		this.phatLoi6 = phatLoi6;
	}
	
	public java.lang.Float getPhatLoi5(){
		return phatLoi5;
	}
	
	public void setPhatLoi5(java.lang.Float phatLoi5)
	{
		this.phatLoi5 = phatLoi5;
	}
	
	public java.lang.Float getPhatLoi4(){
		return phatLoi4;
	}
	
	public void setPhatLoi4(java.lang.Float phatLoi4)
	{
		this.phatLoi4 = phatLoi4;
	}
	
	public java.lang.Float getPhatLoi3(){
		return phatLoi3;
	}
	
	public void setPhatLoi3(java.lang.Float phatLoi3)
	{
		this.phatLoi3 = phatLoi3;
	}
	
	public java.lang.Float getPhatLoi2(){
		return phatLoi2;
	}
	
	public void setPhatLoi2(java.lang.Float phatLoi2)
	{
		this.phatLoi2 = phatLoi2;
	}
	
	public java.lang.Float getPhatLoi1(){
		return phatLoi1;
	}
	
	public void setPhatLoi1(java.lang.Float phatLoi1)
	{
		this.phatLoi1 = phatLoi1;
	}
	
	public java.lang.Float getPhiBanHang(){
		return phiBanHang;
	}
	
	public void setPhiBanHang(java.lang.Float phiBanHang)
	{
		this.phiBanHang = phiBanHang;
	}
	
	public java.lang.Float getLuongDuyTriTungTram(){
		return luongDuyTriTungTram;
	}
	
	public void setLuongDuyTriTungTram(java.lang.Float luongDuyTriTungTram)
	{
		this.luongDuyTriTungTram = luongDuyTriTungTram;
	}
	
	public java.lang.Float getNgayCongCheDo(){
		return ngayCongCheDo;
	}
	
	public void setNgayCongCheDo(java.lang.Float ngayCongCheDo)
	{
		this.ngayCongCheDo = ngayCongCheDo;
	}
	
	public java.lang.Float getNgayCongTinhLuong(){
		return ngayCongTinhLuong;
	}
	
	public void setNgayCongTinhLuong(java.lang.Float ngayCongTinhLuong)
	{
		this.ngayCongTinhLuong = ngayCongTinhLuong;
	}
	
	public java.lang.Float getHeSoDieuChinh(){
		return heSoDieuChinh;
	}
	
	public void setHeSoDieuChinh(java.lang.Float heSoDieuChinh)
	{
		this.heSoDieuChinh = heSoDieuChinh;
	}
	
	public java.lang.Float getKpiDonVi(){
		return kpiDonVi;
	}
	
	public void setKpiDonVi(java.lang.Float kpiDonVi)
	{
		this.kpiDonVi = kpiDonVi;
	}
	
	public java.lang.String getSoTramQuanLy(){
		return soTramQuanLy;
	}
	
	public void setSoTramQuanLy(java.lang.String soTramQuanLy)
	{
		this.soTramQuanLy = soTramQuanLy;
	}
	
	public java.lang.String getHuyen(){
		return huyen;
	}
	
	public void setHuyen(java.lang.String huyen)
	{
		this.huyen = huyen;
	}
   
    @Override
    public TblLuongNvTramDTO toDTO() {
        TblLuongNvTramDTO tblLuongNvTramDTO = new TblLuongNvTramDTO(); 
        tblLuongNvTramDTO.setMaNv(this.maNv);		
        tblLuongNvTramDTO.setHoTen(this.hoTen);		
        tblLuongNvTramDTO.setTinh(this.tinh);		
        tblLuongNvTramDTO.setThang(this.thang);		
        tblLuongNvTramDTO.setTblLuongNvTramId(this.tblLuongNvTramId);		
        tblLuongNvTramDTO.setHoatDong(this.hoatDong);		
        tblLuongNvTramDTO.setXoa(this.xoa);		
        tblLuongNvTramDTO.setLuong(this.luong);		
        tblLuongNvTramDTO.setPhatLoi7(this.phatLoi7);		
        tblLuongNvTramDTO.setPhatLoi6(this.phatLoi6);		
        tblLuongNvTramDTO.setPhatLoi5(this.phatLoi5);		
        tblLuongNvTramDTO.setPhatLoi4(this.phatLoi4);		
        tblLuongNvTramDTO.setPhatLoi3(this.phatLoi3);		
        tblLuongNvTramDTO.setPhatLoi2(this.phatLoi2);		
        tblLuongNvTramDTO.setPhatLoi1(this.phatLoi1);		
        tblLuongNvTramDTO.setPhiBanHang(this.phiBanHang);		
        tblLuongNvTramDTO.setLuongDuyTriTungTram(this.luongDuyTriTungTram);		
        tblLuongNvTramDTO.setNgayCongCheDo(this.ngayCongCheDo);		
        tblLuongNvTramDTO.setNgayCongTinhLuong(this.ngayCongTinhLuong);		
        tblLuongNvTramDTO.setHeSoDieuChinh(this.heSoDieuChinh);		
        tblLuongNvTramDTO.setKpiDonVi(this.kpiDonVi);		
        tblLuongNvTramDTO.setSoTramQuanLy(this.soTramQuanLy);		
        tblLuongNvTramDTO.setHuyen(this.huyen);		
        return tblLuongNvTramDTO;
    }
}
