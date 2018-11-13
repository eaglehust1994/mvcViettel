package com.viettel.qll.bo;

import com.viettel.qll.dto.TblPhatXuLySuCoDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.qll.bo.TblPhatXuLySuCoBO")
@Table(name = "TBL_PHAT_XU_LY_SU_CO")
/**
 *
 * @author: hailh10
 */
public class TblPhatXuLySuCoBO extends BaseFWModelImpl {
     
	@Column(name = "MA_NV", length = 300)
	private java.lang.String maNv;
	@Column(name = "HOAT_DONG", length = 22)
	private java.lang.Long hoatDong;
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "TBL_PHAT_XU_LY_SU_CO_SEQ") })
	@Column(name = "TBL_PHAT_XU_LY_SU_CO_ID", length = 22)
	private java.lang.Long tblPhatXuLySuCoId;
	@Column(name = "XOA", length = 22)
	private java.lang.Long xoa;
	@Column(name = "TONG", length = 22)
	private java.lang.Float tong;
	@Column(name = "PHAT_TGXL_B_TPB", length = 22)
	private java.lang.Float phatTgxlBTpb;
	@Column(name = "PHAT_TGXL_TP", length = 22)
	private java.lang.Float phatTgxlTp;
	@Column(name = "PHAT_TGXL_SLP", length = 22)
	private java.lang.Float phatTgxlSlp;
	@Column(name = "PHAT_TGXL_TL_24H", length = 22)
	private java.lang.Float phatTgxlTl24h;
	@Column(name = "PHAT_TGXL_TL_3H", length = 22)
	private java.lang.Float phatTgxlTl3h;
	@Column(name = "PHAT_TGXL_SL_24H", length = 22)
	private java.lang.Float phatTgxlSl24h;
	@Column(name = "PHAT_TGXL_SL_3H", length = 22)
	private java.lang.Float phatTgxlSl3h;
	@Column(name = "THUONG_TGXL_T_TIEN", length = 22)
	private java.lang.Float thuongTgxlTTien;
	@Column(name = "THUONG_TGXL_T_TL", length = 22)
	private java.lang.Float thuongTgxlTTl;
	@Column(name = "THUONG_TGXL_TL_24H", length = 22)
	private java.lang.Float thuongTgxlTl24h;
	@Column(name = "THUONG_TGXL_TL_3H", length = 22)
	private java.lang.Float thuongTgxlTl3h;
	@Column(name = "THUONG_TGXL_SL_24H", length = 22)
	private java.lang.Float thuongTgxlSl24h;
	@Column(name = "THUONG_TGXL_SL_3H", length = 22)
	private java.lang.Float thuongTgxlSl3h;
	@Column(name = "THUONG_TGXL_SL_TSC", length = 22)
	private java.lang.Float thuongTgxlSlTsc;
	@Column(name = "HUYEN", length = 600)
	private java.lang.String huyen;
	@Column(name = "TINH", length = 300)
	private java.lang.String tinh;
	@Column(name = "THANG", length = 300)
	private java.lang.String thang;
	@Column(name = "ACCOUNT", length = 300)
	private java.lang.String account;
	@Column(name = "PHAT_TGXL_B_SB", length = 22)
	private java.lang.Float phatTgxlBSb;

	
	public java.lang.String getMaNv(){
		return maNv;
	}
	
	public void setMaNv(java.lang.String maNv)
	{
		this.maNv = maNv;
	}
	
	public java.lang.Long getHoatDong(){
		return hoatDong;
	}
	
	public void setHoatDong(java.lang.Long hoatDong)
	{
		this.hoatDong = hoatDong;
	}
	
	public java.lang.Long getTblPhatXuLySuCoId(){
		return tblPhatXuLySuCoId;
	}
	
	public void setTblPhatXuLySuCoId(java.lang.Long tblPhatXuLySuCoId)
	{
		this.tblPhatXuLySuCoId = tblPhatXuLySuCoId;
	}
	
	public java.lang.Long getXoa(){
		return xoa;
	}
	
	public void setXoa(java.lang.Long xoa)
	{
		this.xoa = xoa;
	}
	
	public java.lang.Float getTong(){
		return tong;
	}
	
	public void setTong(java.lang.Float tong)
	{
		this.tong = tong;
	}
	
	public java.lang.Float getPhatTgxlBTpb(){
		return phatTgxlBTpb;
	}
	
	public void setPhatTgxlBTpb(java.lang.Float phatTgxlBTpb)
	{
		this.phatTgxlBTpb = phatTgxlBTpb;
	}
	
	public java.lang.Float getPhatTgxlTp(){
		return phatTgxlTp;
	}
	
	public void setPhatTgxlTp(java.lang.Float phatTgxlTp)
	{
		this.phatTgxlTp = phatTgxlTp;
	}
	
	public java.lang.Float getPhatTgxlSlp(){
		return phatTgxlSlp;
	}
	
	public void setPhatTgxlSlp(java.lang.Float phatTgxlSlp)
	{
		this.phatTgxlSlp = phatTgxlSlp;
	}
	
	public java.lang.Float getPhatTgxlTl24h(){
		return phatTgxlTl24h;
	}
	
	public void setPhatTgxlTl24h(java.lang.Float phatTgxlTl24h)
	{
		this.phatTgxlTl24h = phatTgxlTl24h;
	}
	
	public java.lang.Float getPhatTgxlTl3h(){
		return phatTgxlTl3h;
	}
	
	public void setPhatTgxlTl3h(java.lang.Float phatTgxlTl3h)
	{
		this.phatTgxlTl3h = phatTgxlTl3h;
	}
	
	public java.lang.Float getPhatTgxlSl24h(){
		return phatTgxlSl24h;
	}
	
	public void setPhatTgxlSl24h(java.lang.Float phatTgxlSl24h)
	{
		this.phatTgxlSl24h = phatTgxlSl24h;
	}
	
	public java.lang.Float getPhatTgxlSl3h(){
		return phatTgxlSl3h;
	}
	
	public void setPhatTgxlSl3h(java.lang.Float phatTgxlSl3h)
	{
		this.phatTgxlSl3h = phatTgxlSl3h;
	}
	
	public java.lang.Float getThuongTgxlTTien(){
		return thuongTgxlTTien;
	}
	
	public void setThuongTgxlTTien(java.lang.Float thuongTgxlTTien)
	{
		this.thuongTgxlTTien = thuongTgxlTTien;
	}
	
	public java.lang.Float getThuongTgxlTTl(){
		return thuongTgxlTTl;
	}
	
	public void setThuongTgxlTTl(java.lang.Float thuongTgxlTTl)
	{
		this.thuongTgxlTTl = thuongTgxlTTl;
	}
	
	public java.lang.Float getThuongTgxlTl24h(){
		return thuongTgxlTl24h;
	}
	
	public void setThuongTgxlTl24h(java.lang.Float thuongTgxlTl24h)
	{
		this.thuongTgxlTl24h = thuongTgxlTl24h;
	}
	
	public java.lang.Float getThuongTgxlTl3h(){
		return thuongTgxlTl3h;
	}
	
	public void setThuongTgxlTl3h(java.lang.Float thuongTgxlTl3h)
	{
		this.thuongTgxlTl3h = thuongTgxlTl3h;
	}
	
	public java.lang.Float getThuongTgxlSl24h(){
		return thuongTgxlSl24h;
	}
	
	public void setThuongTgxlSl24h(java.lang.Float thuongTgxlSl24h)
	{
		this.thuongTgxlSl24h = thuongTgxlSl24h;
	}
	
	public java.lang.Float getThuongTgxlSl3h(){
		return thuongTgxlSl3h;
	}
	
	public void setThuongTgxlSl3h(java.lang.Float thuongTgxlSl3h)
	{
		this.thuongTgxlSl3h = thuongTgxlSl3h;
	}
	
	public java.lang.Float getThuongTgxlSlTsc(){
		return thuongTgxlSlTsc;
	}
	
	public void setThuongTgxlSlTsc(java.lang.Float thuongTgxlSlTsc)
	{
		this.thuongTgxlSlTsc = thuongTgxlSlTsc;
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
	
	public java.lang.String getAccount(){
		return account;
	}
	
	public void setAccount(java.lang.String account)
	{
		this.account = account;
	}
	
	public java.lang.Float getPhatTgxlBSb(){
		return phatTgxlBSb;
	}
	
	public void setPhatTgxlBSb(java.lang.Float phatTgxlBSb)
	{
		this.phatTgxlBSb = phatTgxlBSb;
	}
   
    @Override
    public TblPhatXuLySuCoDTO toDTO() {
        TblPhatXuLySuCoDTO tblPhatXuLySuCoDTO = new TblPhatXuLySuCoDTO(); 
        tblPhatXuLySuCoDTO.setMaNv(this.maNv);		
        tblPhatXuLySuCoDTO.setHoatDong(this.hoatDong);		
        tblPhatXuLySuCoDTO.setTblPhatXuLySuCoId(this.tblPhatXuLySuCoId);		
        tblPhatXuLySuCoDTO.setXoa(this.xoa);		
        tblPhatXuLySuCoDTO.setTong(this.tong);		
        tblPhatXuLySuCoDTO.setPhatTgxlBTpb(this.phatTgxlBTpb);		
        tblPhatXuLySuCoDTO.setPhatTgxlTp(this.phatTgxlTp);		
        tblPhatXuLySuCoDTO.setPhatTgxlSlp(this.phatTgxlSlp);		
        tblPhatXuLySuCoDTO.setPhatTgxlTl24h(this.phatTgxlTl24h);		
        tblPhatXuLySuCoDTO.setPhatTgxlTl3h(this.phatTgxlTl3h);		
        tblPhatXuLySuCoDTO.setPhatTgxlSl24h(this.phatTgxlSl24h);		
        tblPhatXuLySuCoDTO.setPhatTgxlSl3h(this.phatTgxlSl3h);		
        tblPhatXuLySuCoDTO.setThuongTgxlTTien(this.thuongTgxlTTien);		
        tblPhatXuLySuCoDTO.setThuongTgxlTTl(this.thuongTgxlTTl);		
        tblPhatXuLySuCoDTO.setThuongTgxlTl24h(this.thuongTgxlTl24h);		
        tblPhatXuLySuCoDTO.setThuongTgxlTl3h(this.thuongTgxlTl3h);		
        tblPhatXuLySuCoDTO.setThuongTgxlSl24h(this.thuongTgxlSl24h);		
        tblPhatXuLySuCoDTO.setThuongTgxlSl3h(this.thuongTgxlSl3h);		
        tblPhatXuLySuCoDTO.setThuongTgxlSlTsc(this.thuongTgxlSlTsc);		
        tblPhatXuLySuCoDTO.setHuyen(this.huyen);		
        tblPhatXuLySuCoDTO.setTinh(this.tinh);		
        tblPhatXuLySuCoDTO.setThang(this.thang);		
        tblPhatXuLySuCoDTO.setAccount(this.account);		
        tblPhatXuLySuCoDTO.setPhatTgxlBSb(this.phatTgxlBSb);		
        return tblPhatXuLySuCoDTO;
    }
}
