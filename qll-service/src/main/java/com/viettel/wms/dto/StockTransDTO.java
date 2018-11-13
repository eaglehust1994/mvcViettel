/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.viettel.erp.utils.JsonDateDeserializer;
import com.viettel.erp.utils.JsonDateSerializerDate;
import com.viettel.erp.utils.JsonDateSerializer;
import com.viettel.wms.bo.StockTransBO;

/**
 *
 * @author PhongPV
 */
@XmlRootElement(name = "STOCK_TRANSBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class StockTransDTO extends wmsBaseDTO<StockTransBO> {

private java.lang.Long stockTransId;
private java.lang.Long orderId;
private java.lang.String orderCode;
private java.lang.String loginName;
private java.lang.String code;
private java.lang.String type;
private java.lang.Long stockId;
private java.lang.String status;
private java.lang.String signState;
private java.lang.Long fromStockTransId;
private java.lang.String description;
private java.lang.Long createdBy;
private java.lang.String createdByName;
private java.lang.String stockReceiveCode;
@JsonSerialize(using = JsonDateSerializerDate.class)
@JsonDeserialize(using = JsonDateDeserializer.class)
private java.util.Date createdDate;
private java.lang.Long createdDeptId;
private java.lang.String createdDeptName;
private java.lang.Long updatedBy;
@JsonSerialize(using = JsonDateSerializerDate.class)
@JsonDeserialize(using = JsonDateDeserializer.class)
private java.util.Date updatedDate;
@JsonSerialize(using = JsonDateSerializer.class)
@JsonDeserialize(using = JsonDateDeserializer.class)
private java.util.Date realIeTransDate;
private java.lang.String realIeUserId;
private java.lang.String realIeUserName;
private java.lang.Long shipperId;
private java.lang.String shipperName;
@JsonSerialize(using = JsonDateSerializerDate.class)
@JsonDeserialize(using = JsonDateDeserializer.class)
private java.util.Date cancelDate;
private java.lang.Long cancelBy;
private java.lang.String cancelByName;
private java.lang.String cancelReasonName;
private java.lang.String cancelDescription;
private java.lang.String vofficeTransactionCode;
private java.lang.String shipmentCode;
private java.lang.String contractCode;
private java.lang.String projectCode;
private java.lang.Long cusId;
private java.lang.String stockName;
private java.lang.String bussinessType;
private java.lang.Long goodsId;
private java.lang.String serial;
private java.lang.String in_roal;
private Long deptReceiveId;
private java.lang.Long stockReceiveId;
private java.lang.String deptReceiveName;
private java.lang.String orderCodeSearch;
private java.lang.String stockCode;
@JsonSerialize(using = JsonDateSerializerDate.class)
@JsonDeserialize(using = JsonDateDeserializer.class)
private java.util.Date startDate;

@JsonSerialize(using = JsonDateSerializerDate.class)
@JsonDeserialize(using = JsonDateDeserializer.class)
private java.util.Date endDate;
private java.lang.String bussinessTypeName;
private java.lang.String createrNoteSearch;

private List<String> listNoteStatus = new ArrayList<>();
private List<String> listSignCAState = new ArrayList<>();
private java.lang.String keySearch;

private List<String> listStatus = new ArrayList<>();
private List<String> listSignState = new ArrayList<>();
@JsonSerialize(using = JsonDateSerializerDate.class)
@JsonDeserialize(using = JsonDateDeserializer.class)
private java.util.Date createdDateFrom;
@JsonSerialize(using = JsonDateSerializerDate.class)
@JsonDeserialize(using = JsonDateDeserializer.class)
private java.util.Date createdDateTo;

private OrderDTO orderDTO;
private OrderGoodsDTO orderGoodsDTO;
private OrderGoodsDetailDTO orderGoodsDetailDTO;
private StockTransDetailDTO stockTransDetailDTO;
private StockTransDetailSerialDTO stockTransDetailSerialDTO;
private List<StockTransDetailDTO> listStockTransDetailDTO;
private List<StockTransDetailSerialDTO> listStockTransDetailSerialDTO;
private List<OrderGoodsDetailDTO> listOrderGoodsDetailDTO;
private List<OrderGoodsDTO> listOrderGoodsDTO;
private List<Long> listStockId;

private java.lang.Long shipmentId;
private java.lang.Long countSerial;
private java.lang.Long countSerialDetail;
public java.lang.Long getCountSerialDetail() {
	return countSerialDetail;
}

public void setCountSerialDetail(java.lang.Long countSerialDetail) {
	this.countSerialDetail = countSerialDetail;
}

private java.lang.Long  receiverId;
private java.lang.String  receiverName;
    public java.lang.Long getReceiverId() {
	return receiverId;
}

public void setReceiverId(java.lang.Long receiverId) {
	this.receiverId = receiverId;
}

public java.lang.String getReceiverName() {
	return receiverName;
}

public void setReceiverName(java.lang.String receiverName) {
	this.receiverName = receiverName;
}

	public java.lang.Long getCountSerial() {
	return countSerial;
}

public void setCountSerial(java.lang.Long countSerial) {
	this.countSerial = countSerial;
}

	@Override
    public StockTransBO toModel() {
        StockTransBO stockTransBO = new StockTransBO();
        stockTransBO.setStockTransId(this.stockTransId);
        stockTransBO.setOrderId(this.orderId);
        stockTransBO.setOrderCode(this.orderCode);
        stockTransBO.setCode(this.code);
        stockTransBO.setType(this.type);
        stockTransBO.setStockId(this.stockId);
        stockTransBO.setStatus(this.status);
        stockTransBO.setSignState(this.signState);
        stockTransBO.setFromStockTransId(this.fromStockTransId);
        stockTransBO.setDescription(this.description);
        stockTransBO.setCreatedBy(this.createdBy);
        stockTransBO.setCreatedByName(this.createdByName);
        stockTransBO.setCreatedDate(this.createdDate);
        stockTransBO.setCreatedDeptId(this.createdDeptId);
        stockTransBO.setCreatedDeptName(this.createdDeptName);
        stockTransBO.setUpdatedBy(this.updatedBy);
        stockTransBO.setUpdatedDate(this.updatedDate);
        stockTransBO.setRealIeTransDate(this.realIeTransDate);
        stockTransBO.setRealIeUserId(this.realIeUserId);
        stockTransBO.setRealIeUserName(this.realIeUserName);
        stockTransBO.setShipperId(this.shipperId);
        stockTransBO.setShipperName(this.shipperName);
        stockTransBO.setCancelDate(this.cancelDate);
        stockTransBO.setCancelByName(this.cancelByName);
        stockTransBO.setCancelBy(this.cancelBy);
        stockTransBO.setCancelReasonName(this.cancelReasonName);
        stockTransBO.setCancelDescription(this.cancelDescription);
        stockTransBO.setVofficeTransactionCode(this.vofficeTransactionCode);
        stockTransBO.setShipmentCode(this.shipmentCode);
        stockTransBO.setContractCode(this.contractCode);
        stockTransBO.setProjectCode(this.projectCode);
        stockTransBO.setCusId(this.cusId);
        stockTransBO.setBussinessTypeName(this.bussinessTypeName);
        stockTransBO.setInRoal(this.in_roal);
        stockTransBO.setDeptReceiveId(this.deptReceiveId);
        stockTransBO.setDeptReceiveName(this.deptReceiveName);
        stockTransBO.setStockReceiveId(this.stockReceiveId);
        stockTransBO.setStockReceiveCode(this.stockReceiveCode);
        return stockTransBO;
    }
    
    public Long getDeptReceiveId() {
    	return deptReceiveId;
    }



    public void setDeptReceiveId(Long deptReceiveId) {
    	this.deptReceiveId = deptReceiveId;
    }



    public java.lang.Long getStockReceiveId() {
    	return stockReceiveId;
    }



    public void setStockReceiveId(java.lang.Long stockReceiveId) {
    	this.stockReceiveId = stockReceiveId;
    }



    
    public java.lang.String getLoginName() {
		return loginName;
	}



	public void setLoginName(java.lang.String loginName) {
		this.loginName = loginName;
	}



	public java.lang.String getCreaterNoteSearch() {
		return createrNoteSearch;
	}



	public void setCreaterNoteSearch(java.lang.String createrNoteSearch) {
		this.createrNoteSearch = createrNoteSearch;
	}



	public List<OrderGoodsDTO> getListOrderGoodsDTO() {
		return listOrderGoodsDTO;
	}



	public void setListOrderGoodsDTO(List<OrderGoodsDTO> listOrderGoodsDTO) {
		this.listOrderGoodsDTO = listOrderGoodsDTO;
	}



	public java.lang.String getStockReceiveCode() {
		return stockReceiveCode;
	}


	public void setStockReceiveCode(java.lang.String stockReceiveCode) {
		this.stockReceiveCode = stockReceiveCode;
	}


	public java.lang.String getOrderCodeSearch() {
		return orderCodeSearch;
	}

	public void setOrderCodeSearch(java.lang.String orderCodeSearch) {
		this.orderCodeSearch = orderCodeSearch;
	}

	public java.lang.String getDeptReceiveName() {
		return deptReceiveName;
	}

	public void setDeptReceiveName(java.lang.String deptReceiveName) {
		this.deptReceiveName = deptReceiveName;
	}

	public java.lang.String getIn_roal() {
		return in_roal;
	}

	public void setIn_roal(java.lang.String in_roal) {
		this.in_roal = in_roal;
	}

	public java.lang.Long getCusId() {
		return cusId;
	}

	public void setCusId(java.lang.Long cusId) {
		this.cusId = cusId;
	}

	@Override
     public Long getFWModelId() {
        return stockTransId;
    }
   
    @Override
    public String catchName() {
        return getStockTransId().toString();
    }
    public java.lang.Long getStockTransId(){
    return stockTransId;
    }
    
    public void setStockTransId(java.lang.Long stockTransId)
    {
    this.stockTransId = stockTransId;
    }
    
    public java.lang.Long getOrderId(){
    return orderId;
    }
    public void setOrderId(java.lang.Long orderId)
    {
    this.orderId = orderId;
    }
    
    public java.lang.String getOrderCode(){
    	return orderCode;
    }
    public void setOrderCode(java.lang.String orderCode)
    {
    this.orderCode = StringUtils.isNotEmpty(orderCode)? orderCode.toUpperCase():null;
    }
    
    public java.lang.String getCode(){
    return code;
    }
    public void setCode(java.lang.String code)
    {
    	this.code = StringUtils.isNotEmpty(code)? code.toUpperCase():null;
    }
    
    public java.lang.String getType(){
    return type;
    }
    public void setType(java.lang.String type)
    {
    this.type = type;
    }
    
    public java.lang.Long getStockId(){
    return stockId;
    }
    public void setStockId(java.lang.Long stockId)
    {
    this.stockId = stockId;
    }
    
    public java.lang.String getStatus(){
    return status;
    }
    public void setStatus(java.lang.String status)
    {
    this.status = status;
    }
    
    public java.lang.String getSignState(){
    return signState;
    }
    public void setSignState(java.lang.String signState)
    {
    this.signState = signState;
    }
    
    public java.lang.Long getFromStockTransId(){
    return fromStockTransId;
    }
    public void setFromStockTransId(java.lang.Long fromStockTransId)
    {
    this.fromStockTransId = fromStockTransId;
    }
    
    public java.lang.String getDescription(){
    return description;
    }
    public void setDescription(java.lang.String description)
    {
    this.description = description;
    }
    
    public java.lang.Long getCreatedBy(){
    return createdBy;
    }
    public void setCreatedBy(java.lang.Long createdBy)
    {
    this.createdBy = createdBy;
    }
    
    public java.lang.String getCreatedByName(){
    return createdByName;
    }
    public void setCreatedByName(java.lang.String createdByName)
    {
    this.createdByName = createdByName;
    }
    
    
    public java.lang.Long getCreatedDeptId(){
    return createdDeptId;
    }
    public void setCreatedDeptId(java.lang.Long createdDeptId)
    {
    this.createdDeptId = createdDeptId;
    }
    
    public java.lang.String getCreatedDeptName(){
    return createdDeptName;
    }
    public void setCreatedDeptName(java.lang.String createdDeptName)
    {
    this.createdDeptName = createdDeptName;
    }
    
    public java.lang.Long getUpdatedBy(){
    return updatedBy;
    }
    public void setUpdatedBy(java.lang.Long updatedBy)
    {
    this.updatedBy = updatedBy;
    }
    
    public java.util.Date getUpdatedDate(){
    return updatedDate;
    }
    public void setUpdatedDate(java.util.Date updatedDate)
    {
    this.updatedDate = updatedDate;
    }
    
    public java.util.Date getRealIeTransDate(){
    return realIeTransDate;
    }
    public void setRealIeTransDate(java.util.Date realIeTransDate)
    {
    this.realIeTransDate = realIeTransDate;
    }
    
    public java.lang.String getRealIeUserId(){
    return realIeUserId;
    }
    public void setRealIeUserId(java.lang.String realIeUserId)
    {
    this.realIeUserId = realIeUserId;
    }
    
    public java.lang.String getRealIeUserName(){
    return realIeUserName;
    }
    public void setRealIeUserName(java.lang.String realIeUserName)
    {
    this.realIeUserName = realIeUserName;
    }
    
    public java.lang.Long getShipperId(){
    return shipperId;
    }
    public void setShipperId(java.lang.Long shipperId)
    {
    this.shipperId = shipperId;
    }
    
    public java.lang.String getShipperName(){
    return shipperName;
    }
    public void setShipperName(java.lang.String shipperName)
    {
    this.shipperName = shipperName;
    }
    
    public java.util.Date getCancelDate(){
    return cancelDate;
    }
    public void setCancelDate(java.util.Date cancelDate)
    {
    this.cancelDate = cancelDate;
    }
    
    public java.lang.Long getCancelBy(){
    return cancelBy;
    }
    public void setCancelBy(java.lang.Long cancelBy)
    {
    this.cancelBy = cancelBy;
    }
    
    public java.lang.String getCancelByName(){
    return cancelByName;
    }
    public void setCancelByName(java.lang.String cancelByName)
    {
    this.cancelByName = cancelByName;
    }
    
    public java.lang.String getCancelReasonName(){
    return cancelReasonName;
    }
    public void setCancelReasonName(java.lang.String cancelReasonName)
    {
    this.cancelReasonName = cancelReasonName;
    }
    
    public java.lang.String getCancelDescription(){
    return cancelDescription;
    }
    public void setCancelDescription(java.lang.String cancelDescription)
    {
    this.cancelDescription = cancelDescription;
    }
    
    public java.lang.String getVofficeTransactionCode(){
    return vofficeTransactionCode;
    }
    public void setVofficeTransactionCode(java.lang.String vofficeTransactionCode)
    {
    this.vofficeTransactionCode = vofficeTransactionCode;
    }
    
    public java.lang.String getShipmentCode(){
    return shipmentCode;
    }
    public void setShipmentCode(java.lang.String shipmentCode)
    {
    this.shipmentCode = StringUtils.isNotEmpty(shipmentCode) ? shipmentCode.toUpperCase() : null;
    }
    
    public java.lang.String getContractCode(){
    return contractCode;
    }
    public void setContractCode(java.lang.String contractCode)
    {
    this.contractCode = contractCode;
    }
    
    public java.lang.String getProjectCode(){
    return projectCode;
    }
    public void setProjectCode(java.lang.String projectCode)
    {
    this.projectCode = projectCode;
    }

	public java.lang.String getStockName() {
		return stockName;
	}

	public void setStockName(java.lang.String stockName) {
		this.stockName = stockName;
	}

	public StockTransDetailDTO getStockTransDetailDTO() {
		return stockTransDetailDTO;
	}

	public void setStockTransDetailDTO(StockTransDetailDTO stockTransDetailDTO) {
		this.stockTransDetailDTO = stockTransDetailDTO;
	}

	public StockTransDetailSerialDTO getStockTransDetailSerialDTO() {
		return stockTransDetailSerialDTO;
	}

	public void setStockTransDetailSerialDTO(StockTransDetailSerialDTO stockTransDetailSerialDTO) {
		this.stockTransDetailSerialDTO = stockTransDetailSerialDTO;
	}


	public List<StockTransDetailDTO> getListStockTransDetailDTO() {
		return listStockTransDetailDTO;
	}


	public void setListStockTransDetailDTO(List<StockTransDetailDTO> listStockTransDetailDTO) {
		this.listStockTransDetailDTO = listStockTransDetailDTO;
	}



	public List<StockTransDetailSerialDTO> getListStockTransDetailSerialDTO() {
		return listStockTransDetailSerialDTO;
	}

	public void setListStockTransDetailSerialDTO(List<StockTransDetailSerialDTO> listStockTransDetailSerialDTO) {
		this.listStockTransDetailSerialDTO = listStockTransDetailSerialDTO;
	}
    
	public java.lang.String getBussinessType() {
		return bussinessType;
	}

	public void setBussinessType(java.lang.String bussinessType) {
		this.bussinessType = bussinessType;
	}

	public OrderDTO getOrderDTO() {
		return orderDTO;
	}

	public void setOrderDTO(OrderDTO orderDTO) {
		this.orderDTO = orderDTO;
	}

	public OrderGoodsDTO getOrderGoodsDTO() {
		return orderGoodsDTO;
	}

	public void setOrderGoodsDTO(OrderGoodsDTO orderGoodsDTO) {
		this.orderGoodsDTO = orderGoodsDTO;
	}

	public OrderGoodsDetailDTO getOrderGoodsDetailDTO() {
		return orderGoodsDetailDTO;
	}

	public void setOrderGoodsDetailDTO(OrderGoodsDetailDTO orderGoodsDetailDTO) {
		this.orderGoodsDetailDTO = orderGoodsDetailDTO;
	}

	public java.lang.String getBussinessTypeName() {
		return bussinessTypeName;
	}

	public void setBussinessTypeName(java.lang.String bussinessTypeName) {
		this.bussinessTypeName = bussinessTypeName;
	}

	public java.util.Date getStartDate() {
		return startDate;
	}

	public void setStartDate(java.util.Date startDate) {
		this.startDate = startDate;
	}

	public java.util.Date getEndDate() {
		return endDate;
	}

	public void setEndDate(java.util.Date endDate) {
		this.endDate = endDate;
	}
	public java.lang.Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(java.lang.Long goodsId) {
		this.goodsId = goodsId;
	}

	public java.lang.String getSerial() {
		return serial;
	}

	public void setSerial(java.lang.String serial) {
		this.serial = serial;
	}

	public java.util.Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(java.util.Date createdDate) {
		this.createdDate = createdDate;
	}

	public java.lang.Long getShipmentId() {
		return shipmentId;
	}

	public void setShipmentId(java.lang.Long shipmentId) {
		this.shipmentId = shipmentId;
	}

	public List<String> getListNoteStatus() {
		return listNoteStatus;
	}

	public void setListNoteStatus(List<String> listNoteStatus) {
		this.listNoteStatus = listNoteStatus;
	}

	public List<String> getListSignCAState() {
		return listSignCAState;
	}

	public void setListSignCAState(List<String> listSignCAState) {
		this.listSignCAState = listSignCAState;
	}

	public java.lang.String getKeySearch() {
		return keySearch;
	}

	public void setKeySearch(java.lang.String keySearch) {
		this.keySearch = keySearch;
	}

	public java.lang.String getStockCode() {
		return stockCode;
	}

	public void setStockCode(java.lang.String stockCode) {
		if(stockCode!=null){
			this.stockCode = stockCode.toUpperCase();
		}else{
			this.stockCode = null;
		}
		
	}

	public List<String> getListStatus() {
		return listStatus;
	}

	public void setListStatus(List<String> listStatus) {
		this.listStatus = listStatus;
	}

	public List<String> getListSignState() {
		return listSignState;
	}

	public void setListSignState(List<String> listSignState) {
		this.listSignState = listSignState;
	}

	public java.util.Date getCreatedDateFrom() {
		return createdDateFrom;
	}

	public void setCreatedDateFrom(java.util.Date createdDateFrom) {
		this.createdDateFrom = createdDateFrom;
	}

	public java.util.Date getCreatedDateTo() {
		return createdDateTo;
	}

	public void setCreatedDateTo(java.util.Date createdDateTo) {
		this.createdDateTo = createdDateTo;
	}



	public List<OrderGoodsDetailDTO> getListOrderGoodsDetailDTO() {
		return listOrderGoodsDetailDTO;
	}



	public void setListOrderGoodsDetailDTO(List<OrderGoodsDetailDTO> listOrderGoodsDetailDTO) {
		this.listOrderGoodsDetailDTO = listOrderGoodsDetailDTO;
	}

	public List<Long> getListStockId() {
		return listStockId;
	}

	public void setListStockId(List<Long> listStockId) {
		this.listStockId = listStockId;
	}


	
}
