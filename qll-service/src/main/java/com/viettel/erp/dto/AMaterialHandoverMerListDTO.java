/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import java.text.DecimalFormat;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.google.common.base.Strings;
import com.viettel.erp.bo.AMaterialHandoverBO;
import com.viettel.erp.bo.AMaterialHandoverMerListBO;
import com.viettel.erp.bo.MerEntityBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "A_MATERIAL_HANDOVER_MER_LISTBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AMaterialHandoverMerListDTO extends BaseFWDTOImpl<AMaterialHandoverMerListBO> {

	private java.lang.Long aMateHandMerListId;

	private java.lang.String expNoteCode;
	private java.lang.Long merEntityId;
	private java.lang.String merCode;
	private java.lang.String merName;
	private java.lang.String serialNumber;
	private java.lang.Long unitId;
	private java.lang.Double handoverQuantity;
	private java.lang.Double actualReceiveQuantity;
	private java.lang.Long qualityStatus;
	private java.lang.String comments="";
	private java.lang.Long amaterialHandoverId;
	private java.lang.String code;
	private java.lang.Long isDevice;
	private java.lang.Long merId;
	private java.lang.Long constructionId;
	private java.lang.String contentname;
	private java.lang.String unitName;
	private java.lang.Long quantity;
	private java.lang.Integer stt;
	private java.lang.String qualityStatusText;
	
	private java.lang.String workListUp ;
	private java.lang.String workListDown ;
	private java.lang.Long merChandiseId;
	private java.lang.Integer rownum ;
	
	DecimalFormat df = new DecimalFormat("###.#");

	private java.lang.Double executeQuantity;
	private java.lang.Double sldabangiao;
	
	public java.lang.Long getaMateHandMerListId() {
		return aMateHandMerListId;
	}

	public void setaMateHandMerListId(java.lang.Long aMateHandMerListId) {
		this.aMateHandMerListId = aMateHandMerListId;
	}

	public java.lang.Long getIsDevice() {
		return isDevice;
	}

	public void setIsDevice(java.lang.Long isDevice) {
		this.isDevice = isDevice;
	}

	public java.lang.Long getMerId() {
		return merId;
	}

	public void setMerId(java.lang.Long merId) {
		this.merId = merId;
	}

	public java.lang.String getCode() {
		return Strings.nullToEmpty(code);
	}

	public void setCode(java.lang.String code) {
		this.code = code;
	}
	
	

	@Override
	public AMaterialHandoverMerListBO toModel() {
		AMaterialHandoverMerListBO aMaterialHandoverMerListBO = new AMaterialHandoverMerListBO();
		aMaterialHandoverMerListBO.setAMateHandMerListId(this.aMateHandMerListId);
		aMaterialHandoverMerListBO.setExpNoteCode(this.expNoteCode);
		// aMaterialHandoverMerListBO.setMerEntityId(this.merEntityId);
		aMaterialHandoverMerListBO.setMerentity(this.merEntityId == null ? null : new MerEntityBO(this.merEntityId));
		aMaterialHandoverMerListBO.setMerName(this.merName);
		aMaterialHandoverMerListBO.setSerialNumber(this.serialNumber == null ? " " : this.serialNumber);
		aMaterialHandoverMerListBO.setUnitId(this.unitId == null ? 0 : this.unitId);
		aMaterialHandoverMerListBO.setHandoverQuantity(this.handoverQuantity == null ? 0 : this.handoverQuantity);
		aMaterialHandoverMerListBO.setActualReceiveQuantity(this.actualReceiveQuantity);
		aMaterialHandoverMerListBO.setQualityStatus(this.qualityStatus == null ? 1 : this.qualityStatus);
		aMaterialHandoverMerListBO.setComments(this.comments == null ? " " : this.comments);
		aMaterialHandoverMerListBO.setMerId(this.merId);
		aMaterialHandoverMerListBO.setIsDevice(this.isDevice);
		// aMaterialHandoverMerListBO.setAMaterialHandoverId(this.aMaterialHandoverId);
		aMaterialHandoverMerListBO.setAmaterialhandover(
				this.amaterialHandoverId == null ? null : new AMaterialHandoverBO(this.amaterialHandoverId));
		return aMaterialHandoverMerListBO;
	}

	@Override
	public Long getFWModelId() {
		return aMateHandMerListId;
	}

	@Override
	public String catchName() {
		return getAMateHandMerListId().toString();
	}

	public java.lang.Long getAMateHandMerListId() {
		return aMateHandMerListId;
	}

	public void setAMateHandMerListId(java.lang.Long aMateHandMerListId) {
		this.aMateHandMerListId = aMateHandMerListId;
	}

	public java.lang.String getExpNoteCode() {
		return Strings.nullToEmpty(expNoteCode);
	}

	public void setExpNoteCode(java.lang.String expNoteCode) {
		this.expNoteCode = expNoteCode;
	}

	public java.lang.Long getMerEntityId() {
		return merEntityId;
	}

	public void setMerEntityId(java.lang.Long merEntityId) {
		this.merEntityId = merEntityId;
	}

	public java.lang.String getMerName() {
		return Strings.nullToEmpty(merName);
	}

	public void setMerName(java.lang.String merName) {
		this.merName = merName;
	}

	public java.lang.String getSerialNumber() {
		return Strings.nullToEmpty(serialNumber);
	}

	public void setSerialNumber(java.lang.String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public java.lang.Long getUnitId() {
		return unitId;
	}

	public void setUnitId(java.lang.Long unitId) {
		this.unitId = unitId;
	}

	public java.lang.Double getHandoverQuantity() {
		return handoverQuantity;
	}

	public void setHandoverQuantity(java.lang.Double handoverQuantity) {
		this.handoverQuantity = handoverQuantity;
	}

	public java.lang.Double getActualReceiveQuantity() {
		 if (actualReceiveQuantity==null){
			 actualReceiveQuantity = 0.0;
		 } 
		 return actualReceiveQuantity;
	}

	public void setActualReceiveQuantity(java.lang.Double actualReceiveQuantity) {
		this.actualReceiveQuantity = actualReceiveQuantity;
	}

	public java.lang.Long getQualityStatus() {
		return qualityStatus;
	}

	public void setQualityStatus(java.lang.Long qualityStatus) {
		this.qualityStatus = qualityStatus;
	}

	public java.lang.String getComments() {
		return Strings.nullToEmpty(comments);
	}

	public void setComments(java.lang.String comments) {
		this.comments = comments;
	}

	public java.lang.Long getAmaterialHandoverId() {
		return amaterialHandoverId;
	}

	public void setAmaterialHandoverId(java.lang.Long amaterialHandoverId) {
		this.amaterialHandoverId = amaterialHandoverId;
	}

	public java.lang.String getContentname() {
		return Strings.nullToEmpty(contentname);
	}

	public void setContentname(java.lang.String contentname) {
		this.contentname = contentname;
	}

	public java.lang.String getUnitName() {
		return Strings.nullToEmpty(unitName);
	}

	public void setUnitName(java.lang.String unitname) {
		this.unitName = unitname;
	}

	public java.lang.Long getQuantity() {
		return quantity;
	}

	public void setQuantity(java.lang.Long quantity) {
		this.quantity = quantity;
	}

	public java.lang.Long getConstructionId() {
		return constructionId;
	}

	public void setConstructionId(java.lang.Long constructionId) {
		this.constructionId = constructionId;
	}

	/**
	 * @return the executeQuantity
	 */
	public java.lang.Double getExecuteQuantity() {		
		
		return executeQuantity;
	}

	/**
	 * @param executeQuantity the executeQuantity to set
	 */
	public void setExecuteQuantity(java.lang.Double executeQuantity) {
		this.executeQuantity = executeQuantity;
	}

	public java.lang.Integer getStt() {
		return stt;
	}

	public void setStt(java.lang.Integer stt) {
		this.stt = stt;
	}

	public java.lang.String getWorkListUp() {
		if(actualReceiveQuantity!=null && handoverQuantity !=null){
		if(actualReceiveQuantity >= handoverQuantity){
			workListUp = String.valueOf(df.format(actualReceiveQuantity - handoverQuantity));
		}else{
		    workListUp = "";
		    }
		}
		return workListUp;
	}

	public void setWorkListUp(java.lang.String workListUp) {
		this.workListUp = workListUp;
	}

	public java.lang.String getWorkListDown() {
		if(handoverQuantity!=null && actualReceiveQuantity !=null){
		if(handoverQuantity >= actualReceiveQuantity){
			workListDown = String.valueOf(df.format(handoverQuantity - actualReceiveQuantity));
		}else{
		   workListDown = "";
		}
		}

		return workListDown;
	}

	public java.lang.String getQualityStatusText() {
		return qualityStatusText;
	}

	public void setQualityStatusText(java.lang.String qualityStatusText) {
		this.qualityStatusText = qualityStatusText;
	}

	public void setWorkListDown(java.lang.String workListDown) {
		this.workListDown = workListDown;
	}

	public java.lang.Integer getRownum() {
		return rownum;
	}

	public void setRownum(java.lang.Integer rownum) {
		this.rownum = rownum;
	}

	public java.lang.String getMerCode() {
		return merCode;
	}

	public void setMerCode(java.lang.String merCode) {
		this.merCode = merCode;
	}

	public java.lang.Long getMerChandiseId() {
		return merChandiseId;
	}

	public void setMerChandiseId(java.lang.Long merChandiseId) {
		this.merChandiseId = merChandiseId;
	}

	public java.lang.Double getSldabangiao() {
		return sldabangiao;
	}

	public void setSldabangiao(java.lang.Double sldabangiao) {
		this.sldabangiao = sldabangiao;
	}
	
	

}
