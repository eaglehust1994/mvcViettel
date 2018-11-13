package com.viettel.qll.bo;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.viettel.qll.dto.TblQuaTrinhTlDTO;
import com.viettel.service.base.model.BaseFWModelImpl;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.qll.dto.TblQuaTrinhTlBO")
@Table(name = "TBL_QUA_TRINH_TL")
/**
 *
 * @author: hailh10
 */
public class TblQuaTrinhTlBO extends BaseFWModelImpl {
     
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "TBL_QUA_TRINH_TL_SEQ") })
	@Column(name = "TBL_QUA_TRINH_TL_ID", length = 22)
	private java.lang.Long tblQuaTrinhTlId;
	@Column(name = "DON_VI", length = 100)
	private java.lang.String donVi;
	@Column(name = "MA_TRAM", length = 100)
	private java.lang.String maTram;
	@Column(name = "MA_QTTL", length = 100)
	private java.lang.String maQttl;

	
	public java.lang.Long getTblQuaTrinhTlId(){
		return tblQuaTrinhTlId;
	}
	
	public void setTblQuaTrinhTlId(java.lang.Long tblQuaTrinhTlId)
	{
		this.tblQuaTrinhTlId = tblQuaTrinhTlId;
	}
	
	public java.lang.String getDonVi(){
		return donVi;
	}
	
	public void setDonVi(java.lang.String donVi)
	{
		this.donVi = donVi;
	}
	
	public java.lang.String getMaTram(){
		return maTram;
	}
	
	public void setMaTram(java.lang.String maTram)
	{
		this.maTram = maTram;
	}
	
	public java.lang.String getMaQttl(){
		return maQttl;
	}
	
	public void setMaQttl(java.lang.String maQttl)
	{
		this.maQttl = maQttl;
	}
   
    @Override
    public TblQuaTrinhTlDTO toDTO() {
        TblQuaTrinhTlDTO tblQuaTrinhTlDTO = new TblQuaTrinhTlDTO(); 
        tblQuaTrinhTlDTO.setTblQuaTrinhTlId(this.tblQuaTrinhTlId);		
        tblQuaTrinhTlDTO.setDonVi(this.donVi);		
        tblQuaTrinhTlDTO.setMaTram(this.maTram);		
        tblQuaTrinhTlDTO.setMaQttl(this.maQttl);		
        return tblQuaTrinhTlDTO;
    }
}
