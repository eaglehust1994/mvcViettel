package com.viettel.qll.bo;

import com.viettel.qll.dto.TblPhatCdtDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.qll.bo.TblPhatCdtBO")
@Table(name = "TBL_PHAT_CDT")
/**
 *
 * @author: hailh10
 */
public class TblPhatCdtBO extends BaseFWModelImpl {
     
	@Column(name = "PHAT_LDSVT_XLSCT", length = 22)
	private java.lang.Float phatLdsvtXlsct;
	@Column(name = "PHAT_GTTBRM", length = 22)
	private java.lang.Float phatGttbrm;
	@Column(name = "HO_VA_TEN", length = 600)
	private java.lang.String hoVaTen;
	@Column(name = "MA_NV", length = 300)
	private java.lang.String maNv;
	@Column(name = "THANG", length = 300)
	private java.lang.String thang;
	@Column(name = "PHAT_TBCDCNKTD", length = 22)
	private java.lang.Float phatTbcdcnktd;
	@Column(name = "PHAT_LYTTD", length = 22)
	private java.lang.Float phatLyttd;
	@Column(name = "PHAT_TBRMKTD", length = 22)
	private java.lang.Float phatTbrmktd;
	@Column(name = "XOA", length = 22)
	private java.lang.Long xoa;
	@Column(name = "HOAT_DONG", length = 22)
	private java.lang.Long hoatDong;
	@Column(name = "PHAT_QCXLSC", length = 22)
	private java.lang.Float phatQcxlsc;
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "TBL_PHAT_CDT_SEQ") })
	@Column(name = "TBL_PHAT_CDT_ID", length = 22)
	private java.lang.Long tblPhatCdtId;

	
	public java.lang.Float getPhatLdsvtXlsct(){
		return phatLdsvtXlsct;
	}
	
	public void setPhatLdsvtXlsct(java.lang.Float phatLdsvtXlsct)
	{
		this.phatLdsvtXlsct = phatLdsvtXlsct;
	}
	
	public java.lang.Float getPhatGttbrm(){
		return phatGttbrm;
	}
	
	public void setPhatGttbrm(java.lang.Float phatGttbrm)
	{
		this.phatGttbrm = phatGttbrm;
	}
	
	public java.lang.String getHoVaTen(){
		return hoVaTen;
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
	
	public java.lang.Float getPhatTbcdcnktd(){
		return phatTbcdcnktd;
	}
	
	public void setPhatTbcdcnktd(java.lang.Float phatTbcdcnktd)
	{
		this.phatTbcdcnktd = phatTbcdcnktd;
	}
	
	public java.lang.Float getPhatLyttd(){
		return phatLyttd;
	}
	
	public void setPhatLyttd(java.lang.Float phatLyttd)
	{
		this.phatLyttd = phatLyttd;
	}
	
	public java.lang.Float getPhatTbrmktd(){
		return phatTbrmktd;
	}
	
	public void setPhatTbrmktd(java.lang.Float phatTbrmktd)
	{
		this.phatTbrmktd = phatTbrmktd;
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
	
	public java.lang.Float getPhatQcxlsc(){
		return phatQcxlsc;
	}
	
	public void setPhatQcxlsc(java.lang.Float phatQcxlsc)
	{
		this.phatQcxlsc = phatQcxlsc;
	}
	
	public java.lang.Long getTblPhatCdtId(){
		return tblPhatCdtId;
	}
	
	public void setTblPhatCdtId(java.lang.Long tblPhatCdtId)
	{
		this.tblPhatCdtId = tblPhatCdtId;
	}
   
    @Override
    public TblPhatCdtDTO toDTO() {
        TblPhatCdtDTO tblPhatCdtDTO = new TblPhatCdtDTO(); 
        tblPhatCdtDTO.setPhatLdsvtXlsct(this.phatLdsvtXlsct);		
        tblPhatCdtDTO.setPhatGttbrm(this.phatGttbrm);		
        tblPhatCdtDTO.setHoVaTen(this.hoVaTen);		
        tblPhatCdtDTO.setMaNv(this.maNv);		
        tblPhatCdtDTO.setThang(this.thang);		
        tblPhatCdtDTO.setPhatTbcdcnktd(this.phatTbcdcnktd);		
        tblPhatCdtDTO.setPhatLyttd(this.phatLyttd);		
        tblPhatCdtDTO.setPhatTbrmktd(this.phatTbrmktd);		
        tblPhatCdtDTO.setXoa(this.xoa);		
        tblPhatCdtDTO.setHoatDong(this.hoatDong);		
        tblPhatCdtDTO.setPhatQcxlsc(this.phatQcxlsc);		
        tblPhatCdtDTO.setTblPhatCdtId(this.tblPhatCdtId);		
        return tblPhatCdtDTO;
    }
}
