package com.viettel.qll.bo;

import com.viettel.qll.dto.TblDutCapLoiCqDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.qll.bo.TblDutCapLoiCqBO")
@Table(name = "TBL_DUT_CAP_LOI_CQ")
/**
 *
 * @author: hailh10
 */
public class TblDutCapLoiCqBO extends BaseFWModelImpl {
     
	@Column(name = "KHU_VUC", length = 100)
	private java.lang.String khuVuc;
	@Column(name = "MA_TINH", length = 100)
	private java.lang.String maTinh;
	@Column(name = "NV_NHAN_GK", length = 100)
	private java.lang.String nvNhanGk;
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "TBL_DUT_CAP_LOI_CQ_SEQ") })
	@Column(name = "TBL_DUT_CAP_LOI_CQ_ID", length = 22)
	private java.lang.Long tblDutCapLoiCqId;
	@Column(name = "MA_NV", length = 100)
	private java.lang.String maNv;
	@Column(name = "SO_LAN_DUT", length = 22)
	private java.lang.Long soLanDut;
	@Column(name = "TIEN_PHAT", length = 22)
	private java.lang.Float tienPhat;
	@Column(name = "THANG", length = 100)
	private java.lang.String thang;

	
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
	
	public java.lang.String getNvNhanGk(){
		return nvNhanGk;
	}
	
	public void setNvNhanGk(java.lang.String nvNhanGk)
	{
		this.nvNhanGk = nvNhanGk;
	}
	
	public java.lang.Long getTblDutCapLoiCqId(){
		return tblDutCapLoiCqId;
	}
	
	public void setTblDutCapLoiCqId(java.lang.Long tblDutCapLoiCqId)
	{
		this.tblDutCapLoiCqId = tblDutCapLoiCqId;
	}
	
	public java.lang.String getMaNv(){
		return maNv;
	}
	
	public void setMaNv(java.lang.String maNv)
	{
		this.maNv = maNv;
	}
	
	public java.lang.Long getSoLanDut(){
		return soLanDut;
	}
	
	public void setSoLanDut(java.lang.Long soLanDut)
	{
		this.soLanDut = soLanDut;
	}
	
	public java.lang.Float getTienPhat(){
		return tienPhat;
	}
	
	public void setTienPhat(java.lang.Float tienPhat)
	{
		this.tienPhat = tienPhat;
	}
	
	public java.lang.String getThang(){
		return thang;
	}
	
	public void setThang(java.lang.String thang)
	{
		this.thang = thang;
	}
   
    @Override
    public TblDutCapLoiCqDTO toDTO() {
        TblDutCapLoiCqDTO tblDutCapLoiCqDTO = new TblDutCapLoiCqDTO(); 
        tblDutCapLoiCqDTO.setKhuVuc(this.khuVuc);		
        tblDutCapLoiCqDTO.setMaTinh(this.maTinh);		
        tblDutCapLoiCqDTO.setNvNhanGk(this.nvNhanGk);		
        tblDutCapLoiCqDTO.setTblDutCapLoiCqId(this.tblDutCapLoiCqId);		
        tblDutCapLoiCqDTO.setMaNv(this.maNv);		
        tblDutCapLoiCqDTO.setSoLanDut(this.soLanDut);		
        tblDutCapLoiCqDTO.setTienPhat(this.tienPhat);		
        tblDutCapLoiCqDTO.setThang(this.thang);		
        return tblDutCapLoiCqDTO;
    }
}
