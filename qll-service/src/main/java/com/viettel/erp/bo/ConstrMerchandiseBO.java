/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.viettel.erp.dto.ConstrMerchandiseDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity (name = "constrmerchandise")
@Table(name = "CONSTR_MERCHANDISE")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class ConstrMerchandiseBO extends BaseFWModelImpl {
	
private ConstrConstructionsBO constrconstructions;
private AMaterialHandoverMerListBO amaterialhandovermerlist;
private MerEntityBO merentity;	
private java.lang.Long id;
private java.lang.Long quantity;
private java.lang.Long isActive;
private java.lang.Long merEntityId;
private java.lang.Long workItemId;
private java.lang.Long constructId;
private java.lang.Long rootMerEntityId;
private java.lang.Long remainCount;
private java.lang.Long merId;
private java.lang.Long upgradeParentId;
private java.lang.Long nodeId;

 public ConstrMerchandiseBO() {
        setColId("id");
        setColName("id");
        setUniqueColumn(new String[]{"id"});
}

@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "CONSTR_MERCHANDISE_SEQ")
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
@Column(name = "QUANTITY", length = 22)
public java.lang.Long getQuantity(){
return quantity;
}
public void setQuantity(java.lang.Long quantity)
{
this.quantity = quantity;
}
@Column(name = "IS_ACTIVE", length = 22)
public java.lang.Long getIsActive(){
return isActive;
}
public void setIsActive(java.lang.Long isActive)
{
this.isActive = isActive;
}
@Column(name = "MER_ENTITY_ID", length = 22)
public java.lang.Long getMerEntityId(){
return merEntityId;
}
public void setMerEntityId(java.lang.Long merEntityId)
{
this.merEntityId = merEntityId;
}


/*@ManyToOne
@JoinColumn(name = "MER_ENTITY_ID")
public MerEntityBO getMerentity(){
	return merentity;
}
public void setMerentity(MerEntityBO merentity){
	this.merentity = merentity;
}
*/

/*@OneToOne(mappedBy = "constrmerchandise")
public AMaterialHandoverMerListBO getAmaterialhandovermerlist(){
	return amaterialhandovermerlist;
}
public void setAmaterialhandovermerlist(AMaterialHandoverMerListBO amaterialhandovermerlist){
	this.amaterialhandovermerlist = amaterialhandovermerlist;
}
*/


@Column(name = "WORK_ITEM_ID", length = 22)
public java.lang.Long getWorkItemId(){
return workItemId;
}
public void setWorkItemId(java.lang.Long workItemId)
{
this.workItemId = workItemId;
}
/*@Column(name = "CONSTRUCT_ID", length = 22)
public java.lang.Long getConstructId(){
return constructId;
}
public void setConstructId(java.lang.Long constructId)
{
this.constructId = constructId;
}*/




@Column(name = "ROOT_MER_ENTITY_ID", length = 22)
public java.lang.Long getRootMerEntityId(){
return rootMerEntityId;
}
public void setRootMerEntityId(java.lang.Long rootMerEntityId)
{
this.rootMerEntityId = rootMerEntityId;
}
@Column(name = "REMAIN_COUNT", length = 22)
public java.lang.Long getRemainCount(){
return remainCount;
}
public void setRemainCount(java.lang.Long remainCount)
{
this.remainCount = remainCount;
}
@Column(name = "MER_ID", length = 22)
public java.lang.Long getMerId(){
return merId;
}
public void setMerId(java.lang.Long merId)
{
this.merId = merId;
}
@Column(name = "UPGRADE_PARENT_ID", length = 22)
public java.lang.Long getUpgradeParentId(){
return upgradeParentId;
}
public void setUpgradeParentId(java.lang.Long upgradeParentId)
{
this.upgradeParentId = upgradeParentId;
}
@Column(name = "NODE_ID", length = 22)
public java.lang.Long getNodeId(){
return nodeId;
}
public void setNodeId(java.lang.Long nodeId)
{
this.nodeId = nodeId;
}
@ManyToOne
@JoinColumn(name = "CONSTRUCT_ID")
public ConstrConstructionsBO getconstrconstructions(){
	return constrconstructions;
}
public void setconstrconstructions(ConstrConstructionsBO constrconstructions){
	this.constrconstructions = constrconstructions;
}   

    @Override
    public ConstrMerchandiseDTO toDTO() {
        ConstrMerchandiseDTO constrMerchandiseDTO = new ConstrMerchandiseDTO();
        //set cac gia tri 
        constrMerchandiseDTO.setId(this.id);
        constrMerchandiseDTO.setQuantity(this.quantity);
        constrMerchandiseDTO.setIsActive(this.isActive);
        constrMerchandiseDTO.setMerEntityId(this.merEntityId);
        constrMerchandiseDTO.setWorkItemId(this.workItemId);
        constrMerchandiseDTO.setConstructId(this.constructId);
        constrMerchandiseDTO.setRootMerEntityId(this.rootMerEntityId);
        constrMerchandiseDTO.setRemainCount(this.remainCount);
        constrMerchandiseDTO.setMerId(this.merId);
        constrMerchandiseDTO.setUpgradeParentId(this.upgradeParentId);
        constrMerchandiseDTO.setNodeId(this.nodeId);
        return constrMerchandiseDTO;
    }
}
