package com.viettel.qll.bo;

import com.viettel.qll.dto.TblPhatRoiMangDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.qll.bo.TblPhatRoiMangBO")
@Table(name = "TBL_PHAT_ROI_MANG")
/**
 *
 * @author: hailh10
 */
public class TblPhatRoiMangBO extends BaseFWModelImpl {
     
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "TBL_PHAT_ROI_MANG_SEQ") })
	@Column(name = "TBL_PHAT_ROI_MANG_ID", length = 22)
	private java.lang.Long tblPhatRoiMangId;
	@Column(name = "HOAT_DONG", length = 22)
	private java.lang.Long hoatDong;
	@Column(name = "XOA", length = 22)
	private java.lang.Long xoa;
	@Column(name = "THANG", length = 300)
	private java.lang.String thang;
	@Column(name = "USER_BH", length = 600)
	private java.lang.String userBh;
	@Column(name = "TONG_THUE_BAO", length = 22)
	private java.lang.Float tongThueBao;
	@Column(name = "PHAT_TRUOC_THUE", length = 22)
	private java.lang.Float phatTruocThue;
	@Column(name = "PHAT_SAU_THUE", length = 22)
	private java.lang.Float phatSauThue;
	@Column(name = "MA_TTNS", length = 300)
	private java.lang.String maTtns;
	@Column(name = "TEN_CTV", length = 600)
	private java.lang.String tenCtv;
	@Column(name = "MA_TINH", length = 300)
	private java.lang.String maTinh;

	
	public java.lang.Long getTblPhatRoiMangId(){
		return tblPhatRoiMangId;
	}
	
	public void setTblPhatRoiMangId(java.lang.Long tblPhatRoiMangId)
	{
		this.tblPhatRoiMangId = tblPhatRoiMangId;
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
	
	public java.lang.String getUserBh(){
		return userBh;
	}
	
	public void setUserBh(java.lang.String userBh)
	{
		this.userBh = userBh;
	}
	
	public java.lang.Float getTongThueBao(){
		return tongThueBao;
	}
	
	public void setTongThueBao(java.lang.Float tongThueBao)
	{
		this.tongThueBao = tongThueBao;
	}
	
	public java.lang.Float getPhatTruocThue(){
		return phatTruocThue;
	}
	
	public void setPhatTruocThue(java.lang.Float phatTruocThue)
	{
		this.phatTruocThue = phatTruocThue;
	}
	
	public java.lang.Float getPhatSauThue(){
		return phatSauThue;
	}
	
	public void setPhatSauThue(java.lang.Float phatSauThue)
	{
		this.phatSauThue = phatSauThue;
	}
	
	public java.lang.String getMaTtns(){
		return maTtns;
	}
	
	public void setMaTtns(java.lang.String maTtns)
	{
		this.maTtns = maTtns;
	}
	
	public java.lang.String getTenCtv(){
		return tenCtv;
	}
	
	public void setTenCtv(java.lang.String tenCtv)
	{
		this.tenCtv = tenCtv;
	}
	
	public java.lang.String getMaTinh(){
		return maTinh;
	}
	
	public void setMaTinh(java.lang.String maTinh)
	{
		this.maTinh = maTinh;
	}
   
    @Override
    public TblPhatRoiMangDTO toDTO() {
        TblPhatRoiMangDTO tblPhatRoiMangDTO = new TblPhatRoiMangDTO(); 
        tblPhatRoiMangDTO.setTblPhatRoiMangId(this.tblPhatRoiMangId);		
        tblPhatRoiMangDTO.setHoatDong(this.hoatDong);		
        tblPhatRoiMangDTO.setXoa(this.xoa);		
        tblPhatRoiMangDTO.setThang(this.thang);		
        tblPhatRoiMangDTO.setUserBh(this.userBh);		
        tblPhatRoiMangDTO.setTongThueBao(this.tongThueBao);		
        tblPhatRoiMangDTO.setPhatTruocThue(this.phatTruocThue);		
        tblPhatRoiMangDTO.setPhatSauThue(this.phatSauThue);		
        tblPhatRoiMangDTO.setMaTtns(this.maTtns);		
        tblPhatRoiMangDTO.setTenCtv(this.tenCtv);		
        tblPhatRoiMangDTO.setMaTinh(this.maTinh);		
        return tblPhatRoiMangDTO;
    }
}
