package com.viettel.qll.bo;

import com.viettel.qll.dto.TblPhatKpiDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.qll.bo.TblPhatKpiBO")
@Table(name = "TBL_PHAT_KPI")
/**
 *
 * @author: hailh10
 */
public class TblPhatKpiBO extends BaseFWModelImpl {
     
	@Column(name = "SO_WO_QUA_HAN", length = 22)
	private java.lang.Float soWoQuaHan;
	@Column(name = "SO_BLOCK_WO_QUA_HAN", length = 22)
	private java.lang.Float soBlockWoQuaHan;
	@Column(name = "DIEM_KIEM_TRA_TRAM", length = 22)
	private java.lang.Float diemKiemTraTram;
	@Column(name = "XOA", length = 22)
	private java.lang.Long xoa;
	@Column(name = "HOAT_DONG", length = 22)
	private java.lang.Long hoatDong;
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "PHAT_KPI_SEQ") })
	@Column(name = "TBL_PHAT_KPI_ID", length = 22)
	private java.lang.Long tblPhatKpiId;
	@Column(name = "THANG", length = 300)
	private java.lang.String thang;
	@Column(name = "KV", length = 1500)
	private java.lang.String kv;
	@Column(name = "MA_TINH", length = 300)
	private java.lang.String maTinh;
	@Column(name = "MA_NV", length = 300)
	private java.lang.String maNv;
	@Column(name = "MA_TRAM", length = 300)
	private java.lang.String maTram;
	
	public java.lang.Float getSoWoQuaHan(){
		return soWoQuaHan;
	}
	
	public void setSoWoQuaHan(java.lang.Float soWoQuaHan)
	{
		this.soWoQuaHan = soWoQuaHan;
	}
	public java.lang.Float getSoBlockWoQuaHan(){
		return soBlockWoQuaHan;
	}
	
	public void setSoBlockWoQuaHan(java.lang.Float soBlockWoQuaHan)
	{
		this.soBlockWoQuaHan = soBlockWoQuaHan;
	}
	
	public java.lang.Float getDiemKiemTraTram(){
		return diemKiemTraTram;
	}
	
	public void setDiemKiemTraTram(java.lang.Float diemKiemTraTram)
	{
		this.diemKiemTraTram = diemKiemTraTram;
	}
	
	public java.lang.Long getXoa(){
		return xoa;
	}
	
	public void setXoa(java.lang.Long xoa)
	{
		this.xoa = xoa;
	}
	
	public java.lang.Long getHoatDong(){
		return hoatDong;
	}
	
	public void setHoatDong(java.lang.Long hoatDong)
	{
		this.hoatDong = hoatDong;
	}
	
	public java.lang.Long getTblPhatKpiId(){
		return tblPhatKpiId;
	}
	
	public void setTblPhatKpiId(java.lang.Long tblPhatKpiId)
	{
		this.tblPhatKpiId = tblPhatKpiId;
	}
	
	public java.lang.String getThang(){
		return thang;
	}
	
	public void setThang(java.lang.String thang)
	{
		this.thang = thang;
	}
	
	public java.lang.String getKv(){
		return kv;
	}
	
	public void setKv(java.lang.String kv)
	{
		this.kv = kv;
	}
	
	public java.lang.String getMaTinh(){
		return maTinh;
	}
	
	public void setMaTinh(java.lang.String maTinh)
	{
		this.maTinh = maTinh;
	}
	
	public java.lang.String getMaNv(){
		return maNv;
	}
	
	public void setMaNv(java.lang.String maNv)
	{
		this.maNv = maNv;
	}
	
	public java.lang.String getMaTram(){
		return maTram;
	}
	
	public void setMaTram(java.lang.String maTram)
	{
		this.maTram = maTram;
	}
   
    @Override
    public TblPhatKpiDTO toDTO() {
        TblPhatKpiDTO tblPhatKpiDTO = new TblPhatKpiDTO(); 
        tblPhatKpiDTO.setSoWoQuaHan(this.soWoQuaHan);		
        tblPhatKpiDTO.setSoBlockWoQuaHan(this.soBlockWoQuaHan);		
        tblPhatKpiDTO.setDiemKiemTraTram(this.diemKiemTraTram);		
        tblPhatKpiDTO.setXoa(this.xoa);		
        tblPhatKpiDTO.setHoatDong(this.hoatDong);		
        tblPhatKpiDTO.setTblPhatKpiId(this.tblPhatKpiId);		
        tblPhatKpiDTO.setThang(this.thang);		
        tblPhatKpiDTO.setKv(this.kv);		
        tblPhatKpiDTO.setMaTinh(this.maTinh);		
        tblPhatKpiDTO.setMaNv(this.maNv);		
        tblPhatKpiDTO.setMaTram(this.maTram);		
        return tblPhatKpiDTO;
    }

	
}
