/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.viettel.erp.dto.ConstrAcceptLostEntityDTO;
import com.viettel.service.base.model.BaseFWModelImpl;

@Entity
@Table(name = "CONSTR_ACCEPT_LOST_ENTITY")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class ConstrAcceptLostEntityBO extends BaseFWModelImpl {
     
private java.lang.Long id;
private java.lang.Long acceptNoteId;
private java.lang.Long merEntityId;
private java.lang.Long count;
private java.lang.Long unitPrice;
private java.lang.Long preStatusId;
private java.lang.Long deliveryNoteId;
private java.lang.Long upgradeParentId;

 public ConstrAcceptLostEntityBO() {
        setColId("id");
        setColName("id");
        setUniqueColumn(new String[]{"id"});
}

@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "CONSTR_ACCEPT_LOST_ENTITY_SEQ")
            }
    )
@Column(name = "ID", length = 22)
public java.lang.Long getId(){
return id;
}
public void setId(java.lang.Long id)
{
this.id = id;
}
@Column(name = "ACCEPT_NOTE_ID", length = 22)
public java.lang.Long getAcceptNoteId(){
return acceptNoteId;
}
public void setAcceptNoteId(java.lang.Long acceptNoteId)
{
this.acceptNoteId = acceptNoteId;
}
@Column(name = "MER_ENTITY_ID", length = 22)
public java.lang.Long getMerEntityId(){
return merEntityId;
}
public void setMerEntityId(java.lang.Long merEntityId)
{
this.merEntityId = merEntityId;
}
@Column(name = "COUNT", length = 22)
public java.lang.Long getCount(){
return count;
}
public void setCount(java.lang.Long count)
{
this.count = count;
}
@Column(name = "UNIT_PRICE", length = 22)
public java.lang.Long getUnitPrice(){
return unitPrice;
}
public void setUnitPrice(java.lang.Long unitPrice)
{
this.unitPrice = unitPrice;
}
@Column(name = "PRE_STATUS_ID", length = 22)
public java.lang.Long getPreStatusId(){
return preStatusId;
}
public void setPreStatusId(java.lang.Long preStatusId)
{
this.preStatusId = preStatusId;
}
@Column(name = "DELIVERY_NOTE_ID", length = 22)
public java.lang.Long getDeliveryNoteId(){
return deliveryNoteId;
}
public void setDeliveryNoteId(java.lang.Long deliveryNoteId)
{
this.deliveryNoteId = deliveryNoteId;
}
@Column(name = "UPGRADE_PARENT_ID", length = 22)
public java.lang.Long getUpgradeParentId(){
return upgradeParentId;
}
public void setUpgradeParentId(java.lang.Long upgradeParentId)
{
this.upgradeParentId = upgradeParentId;
}
   

    @Override
    public ConstrAcceptLostEntityDTO toDTO() {
        ConstrAcceptLostEntityDTO constrAcceptLostEntityDTO = new ConstrAcceptLostEntityDTO();
        //set cac gia tri 
        constrAcceptLostEntityDTO.setId(this.id);
        constrAcceptLostEntityDTO.setAcceptNoteId(this.acceptNoteId);
        constrAcceptLostEntityDTO.setMerEntityId(this.merEntityId);
        constrAcceptLostEntityDTO.setCount(this.count);
        constrAcceptLostEntityDTO.setUnitPrice(this.unitPrice);
        constrAcceptLostEntityDTO.setPreStatusId(this.preStatusId);
        constrAcceptLostEntityDTO.setDeliveryNoteId(this.deliveryNoteId);
        constrAcceptLostEntityDTO.setUpgradeParentId(this.upgradeParentId);
        return constrAcceptLostEntityDTO;
    }
}
