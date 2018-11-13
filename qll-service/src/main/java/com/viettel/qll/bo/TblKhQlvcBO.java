package com.viettel.qll.bo;

import com.viettel.qll.dto.TblKhQlvcDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.erp.bo.TblKhQlvcBO")
@Table(name = "TBL_KH_QLVC")
/**
 *
 * @author: hailh10
 */
public class TblKhQlvcBO extends BaseFWModelImpl {
     
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "TBL_KH_QLVC_SEQ") })
	@Column(name = "KH_QLVC_ID", length = 22)
	private java.lang.Long khQlvcId;
	@Column(name = "THANG", length = 1500)
	private java.lang.String thang;
	@Column(name = "NAM", length = 1500)
	private java.lang.String nam;
	@Column(name = "MA_DON_VI", length = 1500)
	private java.lang.String maDonVi;
	@Column(name = "TEN_DON_VI", length = 1500)
	private java.lang.String tenDonVi;
	@Column(name = "MA_NV_GIAO_VIEC", length = 1500)
	private java.lang.String maNvGiaoViec;
	@Column(name = "TEN_NV_GIAO_VIEC", length = 1500)
	private java.lang.String tenNvGiaoViec;
	@Column(name = "TONG_NV", length = 22)
	private java.lang.Long tongNv;
	
	@Column(name = "TONG_CHAM", length = 22)
	private java.lang.Long tongCham;

	
	public java.lang.Long getKhQlvcId(){
		return khQlvcId;
	}
	
	public void setKhQlvcId(java.lang.Long khQlvcId)
	{
		this.khQlvcId = khQlvcId;
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
	
	public java.lang.String getMaNvGiaoViec(){
		return maNvGiaoViec;
	}
	
	public void setMaNvGiaoViec(java.lang.String maNvGiaoViec)
	{
		this.maNvGiaoViec = maNvGiaoViec;
	}
	
	public java.lang.String getTenNvGiaoViec(){
		return tenNvGiaoViec;
	}
	
	public void setTenNvGiaoViec(java.lang.String tenNvGiaoViec)
	{
		this.tenNvGiaoViec = tenNvGiaoViec;
	}
	
	public java.lang.Long getTongNv(){
		return tongNv;
	}
	
	public void setTongNv(java.lang.Long tongNv)
	{
		this.tongNv = tongNv;
	}
	

	
	public java.lang.Long getTongCham(){
		return tongCham;
	}
	
	public void setTongCham(java.lang.Long tongCham)
	{
		this.tongCham = tongCham;
	}
   
    @Override
    public TblKhQlvcDTO toDTO() {
        TblKhQlvcDTO tblKhQlvcDTO = new TblKhQlvcDTO(); 
        tblKhQlvcDTO.setKhQlvcId(this.khQlvcId);		
        tblKhQlvcDTO.setThang(this.thang);		
        tblKhQlvcDTO.setNam(this.nam);		
        tblKhQlvcDTO.setMaDonVi(this.maDonVi);		
        tblKhQlvcDTO.setTenDonVi(this.tenDonVi);		
        tblKhQlvcDTO.setMaNvGiaoViec(this.maNvGiaoViec);		
        tblKhQlvcDTO.setTenNvGiaoViec(this.tenNvGiaoViec);		
        tblKhQlvcDTO.setTongNv(this.tongNv);		
     		
        tblKhQlvcDTO.setTongCham(this.tongCham);		
        return tblKhQlvcDTO;
    }
}
