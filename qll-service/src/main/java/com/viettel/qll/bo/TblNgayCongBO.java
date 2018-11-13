package com.viettel.qll.bo;

import com.viettel.qll.dto.TblNgayCongDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.qll.bo.TblNgayCongBO")
@Table(name = "TBL_NGAY_CONG")
/**
 *
 * @author: hailh10
 */
public class TblNgayCongBO extends BaseFWModelImpl {
     
	@Column(name = "NGAY_CONG_TINH_LUONG", length = 22)
	private java.lang.Float ngayCongTinhLuong;
	@Column(name = "QTTL", length = 600)
	private java.lang.String Quatrinhtinhluong;
	@Column(name = "HO_VA_TEN", length = 600)
	private java.lang.String hoVaTen;
	@Column(name = "MA_NV", length = 300)
	private java.lang.String maNv;
	@Column(name = "THANG", length = 300)
	private java.lang.String thang;
	@Column(name = "XOA", length = 22)
	private java.lang.Long xoa;
	@Column(name = "HOAT_DONG", length = 22)
	private java.lang.Long hoatDong;
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "TBL_NGAY_CONG_SEQ") })
	@Column(name = "TBL_NGAY_CONG_ID", length = 22)
	private java.lang.Long tblNgayCongId;
	@Column(name = "NGAY_CONG_CHE_DO", length = 22)
	private java.lang.Float ngayCongCheDo;

	
	public java.lang.Float getNgayCongTinhLuong(){
		return ngayCongTinhLuong;
	}
	
	public void setNgayCongTinhLuong(java.lang.Float ngayCongTinhLuong)
	{
		this.ngayCongTinhLuong = ngayCongTinhLuong;
	}
	
	public java.lang.String getHoVaTen(){
		return hoVaTen;
	}
	public java.lang.String getQuatrinhtinhluong(){
		return Quatrinhtinhluong;
	}
	
	public void setquatrinhtinhluong(java.lang.String Quatrinhtinhluong)
	{
		this.Quatrinhtinhluong = Quatrinhtinhluong;
	}
	public void setHoVaTen(java.lang.String hoVaTen)
	{
		this.hoVaTen = hoVaTen;
	}
	
	public java.lang.String getMaNv(){
		return maNv;
	}
	
	public void setMaNv(java.lang.String maNv)
	{
		this.maNv = maNv;
	}
	
	public java.lang.String getThang(){
		return thang;
	}
	
	public void setThang(java.lang.String thang)
	{
		this.thang = thang;
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
	
	public java.lang.Long getTblNgayCongId(){
		return tblNgayCongId;
	}
	
	public void setTblNgayCongId(java.lang.Long tblNgayCongId)
	{
		this.tblNgayCongId = tblNgayCongId;
	}
	
	public java.lang.Float getNgayCongCheDo(){
		return ngayCongCheDo;
	}
	
	public void setNgayCongCheDo(java.lang.Float ngayCongCheDo)
	{
		this.ngayCongCheDo = ngayCongCheDo;
	}
   
    @Override
    public TblNgayCongDTO toDTO() {
        TblNgayCongDTO tblNgayCongDTO = new TblNgayCongDTO(); 
        tblNgayCongDTO.setNgayCongTinhLuong(this.ngayCongTinhLuong);		
        tblNgayCongDTO.setHoVaTen(this.hoVaTen);		
        tblNgayCongDTO.setMaNv(this.maNv);		
        tblNgayCongDTO.setThang(this.thang);		
        tblNgayCongDTO.setXoa(this.xoa);		
        tblNgayCongDTO.setHoatDong(this.hoatDong);		
        tblNgayCongDTO.setTblNgayCongId(this.tblNgayCongId);		
        tblNgayCongDTO.setNgayCongCheDo(this.ngayCongCheDo);	
        tblNgayCongDTO.setQuatrinhtinhluong(this.Quatrinhtinhluong);
        return tblNgayCongDTO;
    }
}
