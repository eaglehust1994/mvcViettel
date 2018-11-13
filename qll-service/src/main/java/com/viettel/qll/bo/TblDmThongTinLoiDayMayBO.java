package com.viettel.qll.bo;

import com.viettel.qll.dto.TblDmThongTinLoiDayMayDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.qll.bo.TblDmThongTinLoiDayMayBO")
@Table(name = "TBL_DM_THONG_TIN_LOI_DAY_MAY")
/**
 *
 * @author: hailh10
 */
public class TblDmThongTinLoiDayMayBO extends BaseFWModelImpl {
     
	@Column(name = "MA_LOI", length = 600)
	private java.lang.String maLoi;
	@Column(name = "XOA", length = 22)
	private java.lang.Long xoa;
	@Column(name = "CUOC_PHAT_SINH", length = 22)
	private java.lang.Float cuocPhatSinh;
	@Column(name = "SO_NGAY_TON", length = 22)
	private java.lang.Float soNgayTon;
	@Column(name = "MA_NV", length = 300)
	private java.lang.String maNv;
	@Column(name = "THUE_BAO_KH", length = 600)
	private java.lang.String thueBaoKh;
	@Column(name = "THANG", length = 300)
	private java.lang.String thang;
	@Column(name = "HOAT_DONG", length = 22)
	private java.lang.Long hoatDong;
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "TBL_PHAT_KHAC_SEQ") })
	@Column(name = "THONG_TIN_LOI_DAY_MAY_ID", length = 22)
	private java.lang.Long thongTinLoiDayMayId;

	
	public java.lang.String getMaLoi(){
		return maLoi;
	}
	
	public void setMaLoi(java.lang.String maLoi)
	{
		this.maLoi = maLoi;
	}
	
	public java.lang.Long getXoa(){
		return xoa;
	}
	
	public void setXoa(java.lang.Long xoa)
	{
		this.xoa = xoa;
	}
	
	public java.lang.Float getCuocPhatSinh(){
		return cuocPhatSinh;
	}
	
	public void setCuocPhatSinh(java.lang.Float cuocPhatSinh)
	{
		this.cuocPhatSinh = cuocPhatSinh;
	}
	
	public java.lang.Float getSoNgayTon(){
		return soNgayTon;
	}
	
	public void setSoNgayTon(java.lang.Float soNgayTon)
	{
		this.soNgayTon = soNgayTon;
	}
	
	public java.lang.String getMaNv(){
		return maNv;
	}
	
	public void setMaNv(java.lang.String maNv)
	{
		this.maNv = maNv;
	}
	
	public java.lang.String getThueBaoKh(){
		return thueBaoKh;
	}
	
	public void setThueBaoKh(java.lang.String thueBaoKh)
	{
		this.thueBaoKh = thueBaoKh;
	}
	
	public java.lang.String getThang(){
		return thang;
	}
	
	public void setThang(java.lang.String thang)
	{
		this.thang = thang;
	}
	
	public java.lang.Long getHoatDong(){
		return hoatDong;
	}
	
	public void setHoatDong(java.lang.Long hoatDong)
	{
		this.hoatDong = hoatDong;
	}
	
	public java.lang.Long getThongTinLoiDayMayId(){
		return thongTinLoiDayMayId;
	}
	
	public void setThongTinLoiDayMayId(java.lang.Long thongTinLoiDayMayId)
	{
		this.thongTinLoiDayMayId = thongTinLoiDayMayId;
	}
   
    @Override
    public TblDmThongTinLoiDayMayDTO toDTO() {
        TblDmThongTinLoiDayMayDTO tblDmThongTinLoiDayMayDTO = new TblDmThongTinLoiDayMayDTO(); 
        tblDmThongTinLoiDayMayDTO.setMaLoi(this.maLoi);		
        tblDmThongTinLoiDayMayDTO.setXoa(this.xoa);		
        tblDmThongTinLoiDayMayDTO.setCuocPhatSinh(this.cuocPhatSinh);		
        tblDmThongTinLoiDayMayDTO.setSoNgayTon(this.soNgayTon);		
        tblDmThongTinLoiDayMayDTO.setMaNv(this.maNv);		
        tblDmThongTinLoiDayMayDTO.setThueBaoKh(this.thueBaoKh);		
        tblDmThongTinLoiDayMayDTO.setThang(this.thang);		
        tblDmThongTinLoiDayMayDTO.setHoatDong(this.hoatDong);		
        tblDmThongTinLoiDayMayDTO.setThongTinLoiDayMayId(this.thongTinLoiDayMayId);		
        return tblDmThongTinLoiDayMayDTO;
    }
}
