package com.viettel.qll.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.viettel.qll.dto.LichSuDangNhapDTO;
import com.viettel.service.base.model.BaseFWModelImpl;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.qll.bo.LichSuDangNhapBO")
@Table(name = "LICH_SU_DANG_NHAP")
/**
 *
 * @author: hailh10
 */
public class LichSuDangNhapBO extends BaseFWModelImpl {
     
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_LS_DN") })
	@Column(name = "ID", length = 22)
	private java.lang.Long id;
	@Column(name = "USER_CODE", length = 2000)
	private java.lang.String userCode;
	@Column(name = "GIO_DANG_NHAP", length = 7)
	private java.util.Date gioDangNhap;
	@Column(name = "IP_DANG_NHAP", length = 20)
	private java.lang.String ipDangNhap;
	@Column(name = "TRANG_THAI", length = 2000)
	private java.lang.String trangThai;

	
	public java.lang.Long getId(){
		return id;
	}
	
	public void setId(java.lang.Long id)
	{
		this.id = id;
	}
	
	public java.lang.String getUserCode(){
		return userCode;
	}
	
	public void setUserCode(java.lang.String userCode)
	{
		this.userCode = userCode;
	}
	
	public java.util.Date getGioDangNhap(){
		return gioDangNhap;
	}
	
	public void setGioDangNhap(java.util.Date gioDangNhap)
	{
		this.gioDangNhap = gioDangNhap;
	}
	
	public java.lang.String getIpDangNhap(){
		return ipDangNhap;
	}
	
	public void setIpDangNhap(java.lang.String ipDangNhap)
	{
		this.ipDangNhap = ipDangNhap;
	}
	
	public java.lang.String getTrangThai(){
		return trangThai;
	}
	
	public void setTrangThai(java.lang.String trangThai)
	{
		this.trangThai = trangThai;
	}
   
    @Override
    public LichSuDangNhapDTO toDTO() {
        LichSuDangNhapDTO lichSuDangNhapDTO = new LichSuDangNhapDTO(); 
        lichSuDangNhapDTO.setId(this.id);		
        lichSuDangNhapDTO.setUserCode(this.userCode);		
        lichSuDangNhapDTO.setGioDangNhap(this.gioDangNhap);		
        lichSuDangNhapDTO.setIpDangNhap(this.ipDangNhap);		
        lichSuDangNhapDTO.setTrangThai(this.trangThai);		
        return lichSuDangNhapDTO;
    }
}
