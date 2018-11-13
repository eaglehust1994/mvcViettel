package com.viettel.qll.dto;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import com.viettel.qll.bo.TblQuaTrinhTlBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;

/**
 *
 * @author hailh10
 */
@SuppressWarnings("serial")
@XmlRootElement(name = "TBL_QUA_TRINH_TLBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TblQuaTrinhTlDTO extends QllBaseDTO<TblQuaTrinhTlBO> {

	private java.lang.Long tblQuaTrinhTlId;
	private java.lang.String donVi;
	private java.lang.String maTram;
	private java.lang.String maQttl;

    @Override
    public TblQuaTrinhTlBO toModel() {
        TblQuaTrinhTlBO tblQuaTrinhTlBO = new TblQuaTrinhTlBO();
        tblQuaTrinhTlBO.setTblQuaTrinhTlId(this.tblQuaTrinhTlId);
        tblQuaTrinhTlBO.setDonVi(this.donVi);
        tblQuaTrinhTlBO.setMaTram(this.maTram);
        tblQuaTrinhTlBO.setMaQttl(this.maQttl);
        return tblQuaTrinhTlBO;
    }

    @Override
     public Long getFWModelId() {
        return tblQuaTrinhTlId;
    }
   
    @Override
    public String catchName() {
        return getTblQuaTrinhTlId().toString();
    }
	
	@JsonProperty("tblQuaTrinhTlId")
    public java.lang.Long getTblQuaTrinhTlId(){
		return tblQuaTrinhTlId;
    }
	
    public void setTblQuaTrinhTlId(java.lang.Long tblQuaTrinhTlId){
		this.tblQuaTrinhTlId = tblQuaTrinhTlId;
    }	
	
	@JsonProperty("donVi")
    public java.lang.String getDonVi(){
		return donVi;
    }
	
    public void setDonVi(java.lang.String donVi){
		this.donVi = donVi;
    }	
	
	@JsonProperty("maTram")
    public java.lang.String getMaTram(){
		return maTram;
    }
	
    public void setMaTram(java.lang.String maTram){
		this.maTram = maTram;
    }	
	
	@JsonProperty("maQttl")
    public java.lang.String getMaQttl(){
		return maQttl;
    }
	
    public void setMaQttl(java.lang.String maQttl){
		this.maQttl = maQttl;
    }	
	

}
