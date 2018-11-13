package com.viettel.ims.dto;

import com.viettel.ims.bo.CntContractOrderBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.viettel.utils.CustomJsonDateDeserializer;
import com.viettel.utils.CustomJsonDateSerializer;

/**
 *
 * @author hailh10
 */
@SuppressWarnings("serial")
@XmlRootElement(name = "CNT_CONTRACT_ORDERBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CntContractOrderDTO extends imsBaseDTO<CntContractOrderBO> {

	private java.lang.Long cntContractOrderId;
	private java.lang.Long cntContractId;
	private java.lang.String cntContractName;
	private java.lang.Long purchaseOrderId;
	private java.lang.String purchaseOrderName;


    @Override
    public CntContractOrderBO toModel() {
        CntContractOrderBO cntContractOrderBO = new CntContractOrderBO();
        cntContractOrderBO.setCntContractOrderId(this.cntContractOrderId);
        cntContractOrderBO.setCntContractId(this.cntContractId);
        cntContractOrderBO.setPurchaseOrderId(this.purchaseOrderId);
        return cntContractOrderBO;
    }

    @Override
     public Long getFWModelId() {
        return cntContractOrderId;
    }
   
    @Override
    public String catchName() {
        return getCntContractOrderId().toString();
    }
	
	@JsonProperty("cntContractOrderId")
    public java.lang.Long getCntContractOrderId(){
		return cntContractOrderId;
    }
	
    public void setCntContractOrderId(java.lang.Long cntContractOrderId){
		this.cntContractOrderId = cntContractOrderId;
    }	
	
	@JsonProperty("cntContractId")
    public java.lang.Long getCntContractId(){
		return cntContractId;
    }
	
    public void setCntContractId(java.lang.Long cntContractId){
		this.cntContractId = cntContractId;
    }	
	
	@JsonProperty("cntContractName")
    public java.lang.String getCntContractName(){
		return cntContractName;
    }
	
    public void setCntContractName(java.lang.String cntContractName){
		this.cntContractName = cntContractName;
    }	
	
	@JsonProperty("purchaseOrderId")
    public java.lang.Long getPurchaseOrderId(){
		return purchaseOrderId;
    }
	
    public void setPurchaseOrderId(java.lang.Long purchaseOrderId){
		this.purchaseOrderId = purchaseOrderId;
    }	
	
	@JsonProperty("purchaseOrderName")
    public java.lang.String getPurchaseOrderName(){
		return purchaseOrderName;
    }
	
    public void setPurchaseOrderName(java.lang.String purchaseOrderName){
		this.purchaseOrderName = purchaseOrderName;
    }	

	

}
