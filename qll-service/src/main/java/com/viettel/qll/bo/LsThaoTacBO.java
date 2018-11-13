package com.viettel.qll.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.viettel.qll.dto.LsThaoTacDTO;
import com.viettel.service.base.model.BaseFWModelImpl;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.qll.bo.LsThaoTacBO")
@Table(name = "LS_THAO_TAC")
/**
 *
 * @author: hailh10
 */
public class LsThaoTacBO extends BaseFWModelImpl {
     
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_LS_TT") })
	@Column(name = "ID_THAO_TAC", length = 22)
	private java.lang.Long idThaoTac;
	@Column(name = "CHUC_NANG", length = 2000)
	private java.lang.String chucNang;
	@Column(name = "USER_CODE", length = 2000)
	private java.lang.String userCode;
	@Column(name = "FULL_NAME", length = 2000)
	private java.lang.String fullName;
	@Column(name = "IP_ADD", length = 20)
	private java.lang.String ipAdd;
	@Column(name = "NGAY_THAO_TAC", length = 7)
	private java.util.Date ngayThaoTac;
	@Column(name = "MO_TA", length = 2000)
	private java.lang.String moTa;
	
	@Column(name = "THOI_GIAN_THUC_HIEN", length = 22)
	private java.lang.Long thoiGianThucHien;
	

	
	public java.lang.Long getIdThaoTac(){
		return idThaoTac;
	}
	
	public void setIdThaoTac(java.lang.Long idThaoTac)
	{
		this.idThaoTac = idThaoTac;
	}
	
	public java.lang.String getChucNang(){
		return chucNang;
	}
	
	public void setChucNang(java.lang.String chucNang)
	{
		this.chucNang = chucNang;
	}
	
	public java.lang.String getUserCode(){
		return userCode;
	}
	
	public void setUserCode(java.lang.String userCode)
	{
		this.userCode = userCode;
	}
	
	public java.lang.String getFullName(){
		return fullName;
	}
	
	public void setFullName(java.lang.String fullName)
	{
		this.fullName = fullName;
	}
	
	public java.lang.String getIpAdd(){
		return ipAdd;
	}
	
	public void setIpAdd(java.lang.String ipAdd)
	{
		this.ipAdd = ipAdd;
	}
	
	public java.util.Date getNgayThaoTac(){
		return ngayThaoTac;
	}
	
	public void setNgayThaoTac(java.util.Date ngayThaoTac)
	{
		this.ngayThaoTac = ngayThaoTac;
	}
	
	public java.lang.String getMoTa(){
		return moTa;
	}
	
	public void setMoTa(java.lang.String moTa)
	{
		this.moTa = moTa;
	}
	
    public java.lang.Long getThoiGianThucHien() {
		return thoiGianThucHien;
	}

	public void setThoiGianThucHien(java.lang.Long thoiGianThucHien) {
		this.thoiGianThucHien = thoiGianThucHien;
	}

	@Override
    public LsThaoTacDTO toDTO() {
        LsThaoTacDTO lsThaoTacDTO = new LsThaoTacDTO(); 
        lsThaoTacDTO.setIdThaoTac(this.idThaoTac);		
        lsThaoTacDTO.setChucNang(this.chucNang);		
        lsThaoTacDTO.setUserCode(this.userCode);		
        lsThaoTacDTO.setFullName(this.fullName);		
        lsThaoTacDTO.setIpAdd(this.ipAdd);		
        lsThaoTacDTO.setNgayThaoTac(this.ngayThaoTac);		
        lsThaoTacDTO.setMoTa(this.moTa);
        lsThaoTacDTO.setThoiGianThucHien(this.thoiGianThucHien);
        return lsThaoTacDTO;
    }
}
