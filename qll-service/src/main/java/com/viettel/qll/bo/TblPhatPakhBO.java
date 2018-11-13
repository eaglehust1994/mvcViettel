package com.viettel.qll.bo;

import com.viettel.qll.dto.TblPhatPakhDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.qll.bo.TblPhatPakhBO")
@Table(name = "TBL_PHAT_PAKH")
/**
 *
 * @author: hailh10
 */
public class TblPhatPakhBO extends BaseFWModelImpl {
     
	@Column(name = "XOA", length = 22)
	private java.lang.Long xoa;
	@Column(name = "SU_CO_PHAN_ANH", length = 22)
	private java.lang.Float suCoPhanAnh;
	@Column(name = "SU_CO_CHO_PHEP", length = 22)
	private java.lang.Float suCoChoPhep;
	@Column(name = "MA_NV", length = 300)
	private java.lang.String maNv;
	@Column(name = "ACCOUNT_KH", length = 600)
	private java.lang.String accountKh;
	@Column(name = "HOAT_DONG", length = 22)
	private java.lang.Long hoatDong;
	@Column(name = "MA_KHIEU_NAI", length = 600)
	private java.lang.String maKhieuNai;
	@Column(name = "QUAN_HUYEN", length = 1500)
	private java.lang.String quanHuyen;
	@Column(name = "TINH", length = 300)
	private java.lang.String tinh;
	@Column(name = "THANG", length = 300)
	private java.lang.String thang;
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "TBL_PHAT_PAKH_SEQ") })
	@Column(name = "TBL_PHAT_PAKH_ID", length = 22)
	private java.lang.Long tblPhatPakhId;
	@Column(name = "PHAT", length = 22)
	private java.lang.Float phat;

	
	public java.lang.Long getXoa(){
		return xoa;
	}
	
	public void setXoa(java.lang.Long xoa)
	{
		this.xoa = xoa;
	}
	
	public java.lang.Float getSuCoPhanAnh(){
		return suCoPhanAnh;
	}
	
	public void setSuCoPhanAnh(java.lang.Float suCoPhanAnh)
	{
		this.suCoPhanAnh = suCoPhanAnh;
	}
	
	public java.lang.Float getSuCoChoPhep(){
		return suCoChoPhep;
	}
	
	public void setSuCoChoPhep(java.lang.Float suCoChoPhep)
	{
		this.suCoChoPhep = suCoChoPhep;
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
	
	public java.lang.Long getHoatDong(){
		return hoatDong;
	}
	
	public void setHoatDong(java.lang.Long hoatDong)
	{
		this.hoatDong = hoatDong;
	}
	
	public java.lang.String getMaKhieuNai(){
		return maKhieuNai;
	}
	
	public void setMaKhieuNai(java.lang.String maKhieuNai)
	{
		this.maKhieuNai = maKhieuNai;
	}
	
	public java.lang.String getQuanHuyen(){
		return quanHuyen;
	}
	
	public void setQuanHuyen(java.lang.String quanHuyen)
	{
		this.quanHuyen = quanHuyen;
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
	
	public java.lang.Long getTblPhatPakhId(){
		return tblPhatPakhId;
	}
	
	public void setTblPhatPakhId(java.lang.Long tblPhatPakhId)
	{
		this.tblPhatPakhId = tblPhatPakhId;
	}
	
	public java.lang.Float getPhat(){
		return phat;
	}
	
	public void setPhat(java.lang.Float phat)
	{
		this.phat = phat;
	}
   
    @Override
    public TblPhatPakhDTO toDTO() {
        TblPhatPakhDTO tblPhatPakhDTO = new TblPhatPakhDTO(); 
        tblPhatPakhDTO.setXoa(this.xoa);		
        tblPhatPakhDTO.setSuCoPhanAnh(this.suCoPhanAnh);		
        tblPhatPakhDTO.setSuCoChoPhep(this.suCoChoPhep);		
        tblPhatPakhDTO.setMaNv(this.maNv);		
        tblPhatPakhDTO.setAccountKh(this.accountKh);		
        tblPhatPakhDTO.setHoatDong(this.hoatDong);		
        tblPhatPakhDTO.setMaKhieuNai(this.maKhieuNai);		
        tblPhatPakhDTO.setQuanHuyen(this.quanHuyen);		
        tblPhatPakhDTO.setTinh(this.tinh);		
        tblPhatPakhDTO.setThang(this.thang);		
        tblPhatPakhDTO.setTblPhatPakhId(this.tblPhatPakhId);		
        tblPhatPakhDTO.setPhat(this.phat);		
        return tblPhatPakhDTO;
    }
}
