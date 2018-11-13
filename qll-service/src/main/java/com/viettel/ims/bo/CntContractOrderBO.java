package com.viettel.ims.bo;

import com.viettel.ims.dto.CntContractOrderDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.erp.bo.CntContractOrderBO")
@Table(name = "CNT_CONTRACT_ORDER")
/**
 *
 * @author: hailh10
 */
public class CntContractOrderBO extends BaseFWModelImpl {
     
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "CNT_CONTRACT_ORDER_SEQ") })
	@Column(name = "CNT_CONTRACT_ORDER_ID", length = 22)
	private java.lang.Long cntContractOrderId;
	@Column(name = "CNT_CONTRACT_ID", length = 22)
	private java.lang.Long cntContractId;
	@Column(name = "PURCHASE_ORDER_ID", length = 22)
	private java.lang.Long purchaseOrderId;

	
	public java.lang.Long getCntContractOrderId(){
		return cntContractOrderId;
	}
	
	public void setCntContractOrderId(java.lang.Long cntContractOrderId)
	{
		this.cntContractOrderId = cntContractOrderId;
	}
	
	public java.lang.Long getCntContractId(){
		return cntContractId;
	}
	
	public void setCntContractId(java.lang.Long cntContractId)
	{
		this.cntContractId = cntContractId;
	}
	
	public java.lang.Long getPurchaseOrderId(){
		return purchaseOrderId;
	}
	
	public void setPurchaseOrderId(java.lang.Long purchaseOrderId)
	{
		this.purchaseOrderId = purchaseOrderId;
	}
   
    @Override
    public CntContractOrderDTO toDTO() {
        CntContractOrderDTO cntContractOrderDTO = new CntContractOrderDTO(); 
        cntContractOrderDTO.setCntContractOrderId(this.cntContractOrderId);		
        cntContractOrderDTO.setCntContractId(this.cntContractId);		
        cntContractOrderDTO.setPurchaseOrderId(this.purchaseOrderId);		
        return cntContractOrderDTO;
    }
}
