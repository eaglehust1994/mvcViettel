package com.viettel.qll.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.viettel.qll.dto.TblDanhMucDTO;
import com.viettel.service.base.model.BaseFWModelImpl;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.qll.bo.TblDanhMucBO")
@Table(name = "TBL_DANH_MUC")
/**
 *
 * @author: hailh10
 */
public class TblDanhMucBO extends BaseFWModelImpl {
     
	@Column(name = "GIA_TRI", length = 1500)
	private java.lang.String giaTri;
	@Column(name = "MA_DANH_MUC", length = 1500)
	private java.lang.String maDanhMuc;
	@Column(name = "TEN_DANH_MUC", length = 1500)
	private java.lang.String tenDanhMuc;
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "TBL_DANH_MUC_SEQ") })
	@Column(name = "TBL_DANH_MUC_ID", length = 22)
	private java.lang.Long tblDanhMucId;
	@Column(name = "HOAT_DONG", length = 22)
	private java.lang.Long hoatDong;
	@Column(name = "DANH_MUC_CHA", length = 22)
	private java.lang.Long danhMucCha; 
	
	@Column(name = "LOAI_DANH_MUC", length = 1500)
	private java.lang.String loaiDanhMuc;

	@Column(name = "MA_DANH_MUC_CHA", length = 1500)
	private java.lang.String maDanhMucCha;
	
		
	public java.lang.String getGiaTri(){
		return giaTri;
	}
	
	public void setGiaTri(java.lang.String giaTri)
	{
		this.giaTri = giaTri;
	}
	
	public java.lang.String getMaDanhMuc(){
		return maDanhMuc;
	}
	
	public void setMaDanhMuc(java.lang.String maDanhMuc)
	{
		this.maDanhMuc = maDanhMuc;
	}
	
	public java.lang.String getTenDanhMuc(){
		return tenDanhMuc;
	}
	
	public void setTenDanhMuc(java.lang.String tenDanhMuc)
	{
		this.tenDanhMuc = tenDanhMuc;
	}
	
	public java.lang.Long getTblDanhMucId(){
		return tblDanhMucId;
	}
	
	public void setTblDanhMucId(java.lang.Long tblDanhMucId)
	{
		this.tblDanhMucId = tblDanhMucId;
	}
	
	public java.lang.Long getHoatDong(){
		return hoatDong;
	}
	
	public void setHoatDong(java.lang.Long hoatDong)
	{
		this.hoatDong = hoatDong;
	}
   
    public java.lang.Long getDanhMucCha() {
		return danhMucCha;
	}

	public void setDanhMucCha(java.lang.Long danhMucCha) {
		this.danhMucCha = danhMucCha;
	}
	

	public java.lang.String getLoaiDanhMuc() {
		return loaiDanhMuc;
	}

	public void setLoaiDanhMuc(java.lang.String loaiDanhMuc) {
		this.loaiDanhMuc = loaiDanhMuc;
	}
	
	

	public java.lang.String getMaDanhMucCha() {
		return maDanhMucCha;
	}

	public void setMaDanhMucCha(java.lang.String maDanhMucCha) {
		this.maDanhMucCha = maDanhMucCha;
	}

	@Override
    public TblDanhMucDTO toDTO() {
        TblDanhMucDTO tblDanhMucDTO = new TblDanhMucDTO(); 
        tblDanhMucDTO.setGiaTri(this.giaTri);		
        tblDanhMucDTO.setMaDanhMuc(this.maDanhMuc);		
        tblDanhMucDTO.setTenDanhMuc(this.tenDanhMuc);		
        tblDanhMucDTO.setTblDanhMucId(this.tblDanhMucId);		
        tblDanhMucDTO.setHoatDong(this.hoatDong);	
        tblDanhMucDTO.setDanhMucCha(this.danhMucCha);
        tblDanhMucDTO.setLoaiDanhMuc(this.loaiDanhMuc);
        tblDanhMucDTO.setMaDanhMucCha(this.maDanhMucCha);
        return tblDanhMucDTO;
    }
}
