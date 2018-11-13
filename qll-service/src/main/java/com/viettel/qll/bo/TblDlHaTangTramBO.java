package com.viettel.qll.bo;

import com.viettel.qll.dto.TblDlHaTangTramDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.qll.bo.TblDlHaTangTramBO")
@Table(name = "TBL_DL_HA_TANG_TRAM")
/**
 *
 * @author: hailh10
 */
public class TblDlHaTangTramBO extends BaseFWModelImpl {
     
	@Column(name = "NGAY_CONG_CHE_DO", length = 22)
	private java.lang.Float ngayCongCheDo;
	@Column(name = "THANG", length = 300)
	private java.lang.String thang;
	@Column(name = "SO_NGAY_QUAN_LY", length = 22)
	private java.lang.Float soNgayQuanLy;
	@Column(name = "SO_NGAY_TRONG_THANG", length = 22)
	private java.lang.Float soNgayTrongThang;
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "TBL_DL_HA_TANG_TRAM_SEQ") })
	@Column(name = "TBL_DL_HA_TANG_TRAM_ID", length = 22)
	private java.lang.Long tblDlHaTangTramId;
	@Column(name = "KHU_VUC", length = 100)
	private java.lang.String khuVuc;
	@Column(name = "MA_TINH", length = 100)
	private java.lang.String maTinh;
	@Column(name = "HUYEN", length = 100)
	private java.lang.String huyen;
	@Column(name = "MA_TRAM", length = 100)
	private java.lang.String maTram;
	@Column(name = "LOAI_DIA_HINH", length = 1000)
	private java.lang.String loaiDiaHinh;
	@Column(name = "PHAN_LOAI_TRAM", length = 100)
	private java.lang.String phanLoaiTram;
	@Column(name = "VUNG", length = 50)
	private java.lang.String vung;
	@Column(name = "MA_NV", length = 100)
	private java.lang.String maNv;
	@Column(name = "NGAY_CONG_TINH_LUONG", length = 22)
	private java.lang.Float ngayCongTinhLuong;

	
	public java.lang.Float getNgayCongCheDo(){
		return ngayCongCheDo;
	}
	
	public void setNgayCongCheDo(java.lang.Float ngayCongCheDo)
	{
		this.ngayCongCheDo = ngayCongCheDo;
	}
	
	public java.lang.String getThang(){
		return thang;
	}
	
	public void setThang(java.lang.String thang)
	{
		this.thang = thang;
	}
	
	public java.lang.Float getSoNgayQuanLy(){
		return soNgayQuanLy;
	}
	
	public void setSoNgayQuanLy(java.lang.Float soNgayQuanLy)
	{
		this.soNgayQuanLy = soNgayQuanLy;
	}
	
	public java.lang.Float getSoNgayTrongThang(){
		return soNgayTrongThang;
	}
	
	public void setSoNgayTrongThang(java.lang.Float soNgayTrongThang)
	{
		this.soNgayTrongThang = soNgayTrongThang;
	}
	
	public java.lang.Long getTblDlHaTangTramId(){
		return tblDlHaTangTramId;
	}
	
	public void setTblDlHaTangTramId(java.lang.Long tblDlHaTangTramId)
	{
		this.tblDlHaTangTramId = tblDlHaTangTramId;
	}
	
	public java.lang.String getKhuVuc(){
		return khuVuc;
	}
	
	public void setKhuVuc(java.lang.String khuVuc)
	{
		this.khuVuc = khuVuc;
	}
	
	public java.lang.String getMaTinh(){
		return maTinh;
	}
	
	public void setMaTinh(java.lang.String maTinh)
	{
		this.maTinh = maTinh;
	}
	
	public java.lang.String getHuyen(){
		return huyen;
	}
	
	public void setHuyen(java.lang.String huyen)
	{
		this.huyen = huyen;
	}
	
	public java.lang.String getMaTram(){
		return maTram;
	}
	
	public void setMaTram(java.lang.String maTram)
	{
		this.maTram = maTram;
	}
	
	public java.lang.String getLoaiDiaHinh(){
		return loaiDiaHinh;
	}
	
	public void setLoaiDiaHinh(java.lang.String loaiDiaHinh)
	{
		this.loaiDiaHinh = loaiDiaHinh;
	}
	
	public java.lang.String getPhanLoaiTram(){
		return phanLoaiTram;
	}
	
	public void setPhanLoaiTram(java.lang.String phanLoaiTram)
	{
		this.phanLoaiTram = phanLoaiTram;
	}
	
	public java.lang.String getVung(){
		return vung;
	}
	
	public void setVung(java.lang.String vung)
	{
		this.vung = vung;
	}
	
	public java.lang.String getMaNv(){
		return maNv;
	}
	
	public void setMaNv(java.lang.String maNv)
	{
		this.maNv = maNv;
	}
	
	public java.lang.Float getNgayCongTinhLuong(){
		return ngayCongTinhLuong;
	}
	
	public void setNgayCongTinhLuong(java.lang.Float ngayCongTinhLuong)
	{
		this.ngayCongTinhLuong = ngayCongTinhLuong;
	}
   
    @Override
    public TblDlHaTangTramDTO toDTO() {
        TblDlHaTangTramDTO tblDlHaTangTramDTO = new TblDlHaTangTramDTO(); 
        tblDlHaTangTramDTO.setNgayCongCheDo(this.ngayCongCheDo);		
        tblDlHaTangTramDTO.setThang(this.thang);		
        tblDlHaTangTramDTO.setSoNgayQuanLy(this.soNgayQuanLy);		
        tblDlHaTangTramDTO.setSoNgayTrongThang(this.soNgayTrongThang);		
        tblDlHaTangTramDTO.setTblDlHaTangTramId(this.tblDlHaTangTramId);		
        tblDlHaTangTramDTO.setKhuVuc(this.khuVuc);		
        tblDlHaTangTramDTO.setMaTinh(this.maTinh);		
        tblDlHaTangTramDTO.setHuyen(this.huyen);		
        tblDlHaTangTramDTO.setMaTram(this.maTram);		
        tblDlHaTangTramDTO.setLoaiDiaHinh(this.loaiDiaHinh);		
        tblDlHaTangTramDTO.setPhanLoaiTram(this.phanLoaiTram);		
        tblDlHaTangTramDTO.setVung(this.vung);		
        tblDlHaTangTramDTO.setMaNv(this.maNv);		
        tblDlHaTangTramDTO.setNgayCongTinhLuong(this.ngayCongTinhLuong);		
        return tblDlHaTangTramDTO;
    }
}
