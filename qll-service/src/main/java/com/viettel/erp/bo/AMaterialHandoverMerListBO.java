/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.viettel.erp.dto.AMaterialHandoverMerListDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity (name = "amaterialhandovermerlist")
@Table(name = "A_MATERIAL_HANDOVER_MER_LIST")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class AMaterialHandoverMerListBO extends BaseFWModelImpl {

private MerEntityBO merentity;
private AMaterialHandoverBO amaterialhandover;
private ConstrMerchandiseBO constrmerchandise;
private java.lang.Long aMateHandMerListId;
private java.lang.String expNoteCode;
//private java.lang.Long merEntityId;
private java.lang.String merName;
private java.lang.String serialNumber;
private java.lang.Long unitId;
private java.lang.Double handoverQuantity;
private java.lang.Double actualReceiveQuantity;
private java.lang.Long qualityStatus;
private java.lang.String comments;
private java.lang.Long amaterialHandoverId;
private java.lang.Long isDevice;
private java.lang.Long merId;

 public AMaterialHandoverMerListBO() {
        setColId("aMateHandMerListId");
        setColName("aMateHandMerListId");
        setUniqueColumn(new String[]{"aMateHandMerListId"});
}

@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "A_MATERIAL_HAN_MER_LIST_SEQ")
            }
    )
@Column(name = "A_MATE_HAND_MER_LIST_ID", length = 22)
public java.lang.Long getAMateHandMerListId(){
return aMateHandMerListId;
}
public void setAMateHandMerListId(java.lang.Long aMateHandMerListId)
{
this.aMateHandMerListId = aMateHandMerListId;
}
@Column(name = "EXP_NOTE_CODE", length = 600)
public java.lang.String getExpNoteCode(){
return expNoteCode;
}
public void setExpNoteCode(java.lang.String expNoteCode)
{
this.expNoteCode = expNoteCode;
}
/*@Column(name = "MER_ENTITY_ID", length = 22)
public java.lang.Long getMerEntityId(){
return merEntityId;
}
public void setMerEntityId(java.lang.Long merEntityId)
{
this.merEntityId = merEntityId;
}*/

@ManyToOne
@JoinColumn(name = "MER_ENTITY_ID")
public MerEntityBO getMerentity(){
	return merentity;
}
public void setMerentity(MerEntityBO merentity){
	this.merentity = merentity;
}

@OneToOne
@JoinColumn(name = "MER_ENTITY_ID",referencedColumnName = "MER_ENTITY_ID", insertable=false, updatable=false)
public ConstrMerchandiseBO getConstrmerchandise(){
	return constrmerchandise;
}
public void setConstrmerchandise(ConstrMerchandiseBO constrmerchandise){
	this.constrmerchandise = constrmerchandise;
}




@Column(name = "MER_NAME", length = 1000)
public java.lang.String getMerName(){
return merName;
}
public void setMerName(java.lang.String merName)
{
this.merName = merName;
}
@Column(name = "SERIAL_NUMBER", length = 400)
public java.lang.String getSerialNumber(){
return serialNumber;
}
public void setSerialNumber(java.lang.String serialNumber)
{
this.serialNumber = serialNumber;
}
@Column(name = "UNIT_ID", length = 22)
public java.lang.Long getUnitId(){
return unitId;
}
public void setUnitId(java.lang.Long unitId)
{
this.unitId = unitId;
}
@Column(name = "HANDOVER_QUANTITY", length = 22)
public java.lang.Double getHandoverQuantity(){
return handoverQuantity;
}
public void setHandoverQuantity(java.lang.Double handoverQuantity)
{
this.handoverQuantity = handoverQuantity;
}
@Column(name = "ACTUAL_RECEIVE_QUANTITY", length = 22)
public java.lang.Double getActualReceiveQuantity(){
return actualReceiveQuantity;
}
public void setActualReceiveQuantity(java.lang.Double actualReceiveQuantity)
{
this.actualReceiveQuantity = actualReceiveQuantity;
}
@Column(name = "QUALITY_STATUS", length = 22)
public java.lang.Long getQualityStatus(){
return qualityStatus;
}
public void setQualityStatus(java.lang.Long qualityStatus)
{
this.qualityStatus = qualityStatus;
}
@Column(name = "COMMENTS", length = 1000)
public java.lang.String getComments(){
return comments;
}
public void setComments(java.lang.String comments)
{
this.comments = comments;
}




/*@Column(name = "A_MATERIAL_HANDOVER_ID", length = 22)
public java.lang.Long getAMaterialHandoverId(){
return aMaterialHandoverId;
}
public void setAMaterialHandoverId(java.lang.Long aMaterialHandoverId)
{
this.aMaterialHandoverId = aMaterialHandoverId;
}*/

/*public java.lang.Long getAmaterialHandoverId() {
	return amaterialHandoverId;
}

public void setAmaterialHandoverId(java.lang.Long amaterialHandoverId) {
	this.amaterialHandoverId = amaterialHandoverId;
}

public java.lang.Long getMerEntityId() {
	return merEntityId;
}

public void setMerEntityId(java.lang.Long merEntityId) {
	this.merEntityId = merEntityId;
}*/


@Column(name = "IS_DEVICE", length = 10)
public java.lang.Long getIsDevice() {
	return isDevice;
}

public void setIsDevice(java.lang.Long isDevice) {
	this.isDevice = isDevice;
}

@Column(name = "MER_ID", length = 38)
public java.lang.Long getMerId() {
	return merId;
}

public void setMerId(java.lang.Long merId) {
	this.merId = merId;
}

@ManyToOne
@JoinColumn(name = "A_MATERIAL_HANDOVER_ID")
public AMaterialHandoverBO getamaterialhandover(){
	return amaterialhandover;
}
public void setAmaterialhandover(AMaterialHandoverBO amaterialhandover){
	this.amaterialhandover = amaterialhandover;
}



   

    @Override
    public AMaterialHandoverMerListDTO toDTO() {
        AMaterialHandoverMerListDTO aMaterialHandoverMerListDTO = new AMaterialHandoverMerListDTO();
        //set cac gia tri 
        aMaterialHandoverMerListDTO.setAMateHandMerListId(this.aMateHandMerListId);
        aMaterialHandoverMerListDTO.setExpNoteCode(this.expNoteCode == null ? "" : this.expNoteCode);
        aMaterialHandoverMerListDTO.setMerEntityId(this.merentity == null ? null :this.merentity.getMerEntityId());
        aMaterialHandoverMerListDTO.setMerName(this.merName == null ? "" : this.merName);
        aMaterialHandoverMerListDTO.setSerialNumber(this.serialNumber == null ? "" : this.serialNumber);
        aMaterialHandoverMerListDTO.setUnitId(this.unitId == null ? 0 : this.unitId);
        aMaterialHandoverMerListDTO.setHandoverQuantity(this.handoverQuantity == null ? 0 : this.handoverQuantity);
        aMaterialHandoverMerListDTO.setActualReceiveQuantity(this.actualReceiveQuantity== null ? 0 : this.actualReceiveQuantity);
        aMaterialHandoverMerListDTO.setQualityStatus(this.qualityStatus == null ? 1 : this.qualityStatus);
        aMaterialHandoverMerListDTO.setComments(this.comments == null ? "" : this.comments);
       // aMaterialHandoverMerListDTO.setAmaterialHandoverId(this.amaterialHandoverId);
        
        aMaterialHandoverMerListDTO.setAmaterialHandoverId(this.amaterialhandover == null ? null :this.amaterialhandover.getAmaterialHandoverId());
        
        aMaterialHandoverMerListDTO.setContentname(this.merentity.getCatmerchandise().getName());
        aMaterialHandoverMerListDTO.setQuantity(this.constrmerchandise == null ? 0 : this.constrmerchandise.getQuantity());
        aMaterialHandoverMerListDTO.setExecuteQuantity(this.constrmerchandise == null ? 0D : this.constrmerchandise.getQuantity());
//        aMaterialHandoverMerListDTO.setUnitName(this.merentity.getCatmerchandise().getCatunit().getName());
        
        aMaterialHandoverMerListDTO.setIsDevice(this.isDevice);
        aMaterialHandoverMerListDTO.setMerId(this.merId);
        
        return aMaterialHandoverMerListDTO;
    }
}
