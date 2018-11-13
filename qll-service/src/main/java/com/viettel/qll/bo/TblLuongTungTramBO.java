package com.viettel.qll.bo;

import com.viettel.qll.dto.TblLuongTungTramDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.qll.bo.TblLuongTungTramBO")
@Table(name = "TBL_LUONG_TUNG_TRAM")
/**
 *
 * @author: hailh10
 */
public class TblLuongTungTramBO extends BaseFWModelImpl {
     
	@Column(name = "LOAI_TRAM", length = 1500)
	private java.lang.String loaiTram;
	@Column(name = "DON_GIA", length = 22)
	private java.lang.Float donGia;
	@Column(name = "DIA_HINH", length = 1500)
	private java.lang.String diaHinh;
	@Column(name = "VUNG_LUONG", length = 1500)
	private java.lang.String vungLuong;
	@Column(name = "TRAM", length = 300)
	private java.lang.String tram;
	@Column(name = "HO_TEN", length = 600)
	private java.lang.String hoTen;
	@Column(name = "MA_NV", length = 300)
	private java.lang.String maNv;
	@Column(name = "HUYEN", length = 1500)
	private java.lang.String huyen;
	@Column(name = "TINH", length = 300)
	private java.lang.String tinh;
	@Column(name = "THANG", length = 300)
	private java.lang.String thang;
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "TBL_LUONG_TUNG_TRAM_SEQ") })
	@Column(name = "TBL_LUONG_TUNG_TRAM_ID", length = 22)
	private java.lang.Long tblLuongTungTramId;
	@Column(name = "HOAT_DONG", length = 22)
	private java.lang.Long hoatDong;
	@Column(name = "XOA", length = 22)
	private java.lang.Long xoa;
	@Column(name = "LUONG", length = 22)
	private java.lang.Float luong;
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
	@Column(name = "LUONG_DUY_TRI_TUNG_TRAM", length = 22)
	private java.lang.Float luongDuyTriTungTram;
	@Column(name = "NGAY_CONG_CHE_DO", length = 22)
	private java.lang.Float ngayCongCheDo;
	@Column(name = "NGAY_CONG_TINH_LUONG", length = 22)
	private java.lang.Float ngayCongTinhLuong;
	@Column(name = "HE_SO_DIEU_CHINH", length = 22)
	private java.lang.Float heSoDieuChinh;
	@Column(name = "KPI_TRAM", length = 22)
	private java.lang.Float kpiTram;
	@Column(name = "KI_DON_VI", length = 22)
	private java.lang.Float kiDonVi;

	
	public java.lang.String getLoaiTram(){
		return loaiTram;
	}
	
	public void setLoaiTram(java.lang.String loaiTram)
	{
		this.loaiTram = loaiTram;
	}
	
	public java.lang.Float getDonGia(){
		return donGia;
	}
	
	public void setDonGia(java.lang.Float donGia)
	{
		this.donGia = donGia;
	}
	
	public java.lang.String getDiaHinh(){
		return diaHinh;
	}
	
	public void setDiaHinh(java.lang.String diaHinh)
	{
		this.diaHinh = diaHinh;
	}
	
	public java.lang.String getVungLuong(){
		return vungLuong;
	}
	
	public void setVungLuong(java.lang.String vungLuong)
	{
		this.vungLuong = vungLuong;
	}
	
	public java.lang.String getTram(){
		return tram;
	}
	
	public void setTram(java.lang.String tram)
	{
		this.tram = tram;
	}
	
	public java.lang.String getHoTen(){
		return hoTen;
	}
	
	public void setHoTen(java.lang.String hoTen)
	{
		this.hoTen = hoTen;
	}
	
	public java.lang.String getMaNv(){
		return maNv;
	}
	
	public void setMaNv(java.lang.String maNv)
	{
		this.maNv = maNv;
	}
	
	public java.lang.String getHuyen(){
		return huyen;
	}
	
	public void setHuyen(java.lang.String huyen)
	{
		this.huyen = huyen;
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
	
	public java.lang.Long getTblLuongTungTramId(){
		return tblLuongTungTramId;
	}
	
	public void setTblLuongTungTramId(java.lang.Long tblLuongTungTramId)
	{
		this.tblLuongTungTramId = tblLuongTungTramId;
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
	
	public java.lang.Float getKpiTram(){
		return kpiTram;
	}
	
	public void setKpiTram(java.lang.Float kpiTram)
	{
		this.kpiTram = kpiTram;
	}
	
	public java.lang.Float getKiDonVi(){
		return kiDonVi;
	}
	
	public void setKiDonVi(java.lang.Float kiDonVi)
	{
		this.kiDonVi = kiDonVi;
	}
   
    @Override
    public TblLuongTungTramDTO toDTO() {
        TblLuongTungTramDTO tblLuongTungTramDTO = new TblLuongTungTramDTO(); 
        tblLuongTungTramDTO.setLoaiTram(this.loaiTram);		
        tblLuongTungTramDTO.setDonGia(this.donGia);		
        tblLuongTungTramDTO.setDiaHinh(this.diaHinh);		
        tblLuongTungTramDTO.setVungLuong(this.vungLuong);		
        tblLuongTungTramDTO.setTram(this.tram);		
        tblLuongTungTramDTO.setHoTen(this.hoTen);		
        tblLuongTungTramDTO.setMaNv(this.maNv);		
        tblLuongTungTramDTO.setHuyen(this.huyen);		
        tblLuongTungTramDTO.setTinh(this.tinh);		
        tblLuongTungTramDTO.setThang(this.thang);		
        tblLuongTungTramDTO.setTblLuongTungTramId(this.tblLuongTungTramId);		
        tblLuongTungTramDTO.setHoatDong(this.hoatDong);		
        tblLuongTungTramDTO.setXoa(this.xoa);		
        tblLuongTungTramDTO.setLuong(this.luong);		
        tblLuongTungTramDTO.setPhatLoi6(this.phatLoi6);		
        tblLuongTungTramDTO.setPhatLoi5(this.phatLoi5);		
        tblLuongTungTramDTO.setPhatLoi4(this.phatLoi4);		
        tblLuongTungTramDTO.setPhatLoi3(this.phatLoi3);		
        tblLuongTungTramDTO.setPhatLoi2(this.phatLoi2);		
        tblLuongTungTramDTO.setPhatLoi1(this.phatLoi1);		
        tblLuongTungTramDTO.setLuongDuyTriTungTram(this.luongDuyTriTungTram);		
        tblLuongTungTramDTO.setNgayCongCheDo(this.ngayCongCheDo);		
        tblLuongTungTramDTO.setNgayCongTinhLuong(this.ngayCongTinhLuong);		
        tblLuongTungTramDTO.setHeSoDieuChinh(this.heSoDieuChinh);		
        tblLuongTungTramDTO.setKpiTram(this.kpiTram);		
        tblLuongTungTramDTO.setKiDonVi(this.kiDonVi);		
        return tblLuongTungTramDTO;
    }
}
