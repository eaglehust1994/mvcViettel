package com.viettel.asset.dto.search;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.viettel.asset.dto.BaseSearchDto;
import com.viettel.erp.utils.CustomJsonDateSerializer;
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssetHandOverDto{
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private Date handoverDate;
	
	private String handoverCode;
	
	private Long type;//Loại biên bản bàn giao -1:qua xay lap, 2: khong qua xay lap
	private Long id;
	private String constrCode;
	private String stationCode;
	private String receiverName;
	private String deliveryGroupName;
	private String deliveryGroupTitleName;
	
	public String getDeliveryGroupTitleName() {
		return deliveryGroupTitleName;
	}
	public void setDeliveryGroupTitleName(String deliveryTitleName) {
		this.deliveryGroupTitleName = deliveryTitleName;
	}
	/*
	 * de view bien ban ban giao
	 */
	private String reason;
	private Long isAccepted;
	private String receiverPosition;
	private String deliveryName;
	private String deliveryPosition;
	private String foundation;
	private String deliveryNoteCode;
	private String receiverGroupName;//don vi nhan ban giao
	
	
	
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Long getIsAccepted() {
		return isAccepted;
	}
	public void setIsAccepted(Long isAccepted) {
		this.isAccepted = isAccepted;
	}
	public String getReceiverPosition() {
		return receiverPosition;
	}
	public void setReceiverPosition(String receiverPosition) {
		this.receiverPosition = receiverPosition;
	}
	public String getDeliveryName() {
		return deliveryName;
	}
	public void setDeliveryName(String deliveryName) {
		this.deliveryName = deliveryName;
	}
	public String getDeliveryPosition() {
		return deliveryPosition;
	}
	public void setDeliveryPosition(String deliveryPosition) {
		this.deliveryPosition = deliveryPosition;
	}
	public String getFoundation() {
		return foundation;
	}
	public void setFoundation(String foundation) {
		this.foundation = foundation;
	}
	public String getDeliveryNoteCode() {
		return deliveryNoteCode;
	}
	public void setDeliveryNoteCode(String deliveryNoteCode) {
		this.deliveryNoteCode = deliveryNoteCode;
	}
	public String getReceiverGroupName() {
		return receiverGroupName;
	}
	public void setReceiverGroupName(String receiverGroupName) {
		this.receiverGroupName = receiverGroupName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getConstrCode() {
		return constrCode;
	}
	public void setConstrCode(String constrCode) {
		this.constrCode = constrCode;
	}
	public String getStationCode() {
		return stationCode;
	}
	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiver_name) {
		this.receiverName = receiver_name;
	}
	public String getDeliveryGroupName() {
		return deliveryGroupName;
	}
	public void setDeliveryGroupName(String deliveryGroupName) {
		this.deliveryGroupName = deliveryGroupName;
	}
	public Date getHandoverDate() {
		return handoverDate;
	}
	public void setHandoverDate(Date handoverDate) {
		this.handoverDate = handoverDate;
	}
	public Long getType() {
		return type;
	}
	public void setType(Long type) {
		this.type = type;
	}
	public String getHandoverCode() {
		return handoverCode;
	}
	public void setHandoverCode(String handoverCode) {
		this.handoverCode = handoverCode;
	}
}
