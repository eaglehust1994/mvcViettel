/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.dto;

import com.google.common.collect.Lists;
import com.viettel.erp.utils.CustomJsonDateDeserializer;
import com.viettel.erp.utils.CustomJsonDateSerializer;
import com.viettel.erp.utils.JsonDateDeserializer;
import com.viettel.erp.utils.JsonDateSerializerDate;
import com.viettel.wms.bo.OrderBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.basic.BasicListUI.ListSelectionHandler;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
 * @author PhongPV
 */
@XmlRootElement(name = "ORDERBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDTO extends wmsBaseDTO<OrderBO>{
	private java.lang.String createdByName;
	private java.lang.String cancelReasonName;
	private java.lang.Long orderId;
	private java.lang.String code;
	private java.lang.String type;
	private java.lang.String bussinessType;
	private java.lang.Long stockId;
	private java.lang.Long deptReceiveId;
	private java.lang.String deptReceiveName;
	private java.lang.String status;
	private java.lang.String description;
	private java.lang.String purchaseOrderNo;
	@JsonSerialize(using = JsonDateSerializerDate.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private java.util.Date purchaseOrderDate;
	private java.lang.String cerNo;
	@JsonSerialize(using = JsonDateSerializerDate.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private java.util.Date cerDate;
	@JsonSerialize(using = JsonDateSerializerDate.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private java.util.Date createdDate;
	private java.lang.Long createdBy;
	private java.lang.Long createdDeptedId;
	private java.lang.String createdDeptedName;
	private java.lang.Long updatedBy;
	private java.util.Date updatedDate;
	private java.lang.String shipperId;
	private java.lang.String shipperName;
	@JsonSerialize(using = JsonDateSerializerDate.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private java.util.Date shipDate;
	private java.lang.Long shipmentId;
	private java.lang.String shipmentCode;
	private java.lang.String contractCode;
	private java.lang.String maintainOrderCode;
	private java.lang.String maintainReportCode;
	private java.lang.String consRetrieveOrderCode;
	private java.lang.String deptRetrieveOrderCode;
	private java.lang.String loanRetrieveOrderCode;
	private java.lang.String constrCode;
	private java.lang.Long partnerId;
	private java.lang.Long ieStockId;
	private java.lang.String projectCode;
	private java.lang.String signState;
	@JsonSerialize(using = JsonDateSerializerDate.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private java.util.Date cancelDate;
	private java.lang.Long cancelBy;
	private java.lang.String cancelByName;
	private java.lang.String cancelDescription;
	private java.lang.String vofficeTransactionCode;
	private java.lang.Long reasonId;
	
	private java.lang.String name;

	private java.lang.String stockName;
	private java.lang.String stockTransCode;
	private java.lang.String goodsCode;
	private java.lang.String goodsName;
	private java.lang.String goodsUnitName;
	private java.lang.Double amount;
	private java.lang.String goodsStateName;
	private java.lang.String goodsState;
	private java.lang.String isSerial;
	private java.lang.String serial;
	private java.lang.String orderGoodsDetailContractCode;	
	private java.lang.String partNumber;
	private java.lang.String manufacturerName;
	private java.lang.Long manufacturerId;
	private java.lang.String producingCountryName;
	private java.lang.Long producingCountryId;
	private java.lang.Long stockTransId;
	private java.lang.Long goodsUnitId;
	private java.lang.String constructionName,reasonToExport,partnerName,projectName;
	private java.lang.String stockCode;
	private java.lang.String ieStockCode;

	@JsonSerialize(using = JsonDateSerializerDate.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private java.util.Date createdDateFrom;
	@JsonSerialize(using = JsonDateSerializerDate.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private java.util.Date createdDateTo;
	private java.lang.String keySearch;	
	private List<String> listStatus = new ArrayList<>();
	private List<String> listBussinessType = new ArrayList<>();
	private List<String> listSignState = new ArrayList<>();
	private List<Long> listStockId = new ArrayList<>();
	private java.lang.String otherKey ;
	private java.lang.String createrSearch;
	
	private OrderGoodsDTO orderGoodsDTO;
	private OrderGoodsDetailDTO orderGoodsDetailDTO;
	private List<OrderGoodsDTO> listOrderGoodsDTO;
	private List<OrderGoodsDetailDTO> listOrderGoodsDetailDTO;
	private String cancelReasonApply;
	private String ieStockName;
	
	private java.lang.Long recieverId;
	private java.lang.String recieverName;
	@JsonSerialize(using = JsonDateSerializerDate.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private java.util.Date expectedRecievedDate;
	private java.lang.Long ieStockTransId;
	private java.lang.String loginName;
	private List<OrderGoodsExelDTO> lstErrorOrderGoods;
	
	private List<SignVofficeDTO> listSignVoffice= Lists.newArrayList();
	
	private java.lang.Long countSerial;
	
	
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

	public java.lang.String getIeStockCode() {
		return ieStockCode;
	}

	public void setIeStockCode(java.lang.String ieStockCode) {
		this.ieStockCode = ieStockCode;
	}

	public java.lang.String getLoginName() {
		return loginName;
	}

	public void setLoginName(java.lang.String loginName) {
		this.loginName = loginName;
	}

	public java.lang.String getRecieverName() {
		return recieverName;
	}

	public void setRecieverName(java.lang.String recieverName) {
		this.recieverName = recieverName;
	}

	public String getIeStockName() {
		return ieStockName;
	}

	
	public java.lang.String getProjectName() {
		return projectName;
	}

	public void setProjectName(java.lang.String projectName) {
		this.projectName = projectName;
	}

	public void setIeStockName(String ieStockName) {
		this.ieStockName = ieStockName;
	}

	
	public List<SignVofficeDTO> getListSignVoffice() {
		return listSignVoffice;
	}

	public void setListSignVoffice(List<SignVofficeDTO> listSignVoffice) {
		this.listSignVoffice = listSignVoffice;
	}

	public String getCancelReasonApply() {
		return cancelReasonApply;
	}

	public void setCancelReasonApply(String cancelReasonApply) {
		this.cancelReasonApply = cancelReasonApply;
	}

	
	public java.lang.String getCreaterSearch() {
		return createrSearch;
	}

	public void setCreaterSearch(java.lang.String createrSearch) {
		this.createrSearch = createrSearch;
	}

	public java.lang.String getOtherKey() {
		return otherKey;
	}

	public void setOtherKey(java.lang.String otherKey) {
		this.otherKey = otherKey;
	}

	public List<String> getListBussinessType() {
		return listBussinessType;
	}

	public void setListBussinessType(List<String> listBussinessType) {
		this.listBussinessType = listBussinessType;
	}

	public List<String> getListSignState() {
		return listSignState;
	}

	public void setListSignState(List<String> listSignState) {
		this.listSignState = listSignState;
	}

	public List<String> getListStatus() {
		return listStatus;
	}

	public void setListStatus(List<String> listStatus) {
		this.listStatus = listStatus;
	}

	

	public java.lang.String getCreatedByName() {
		return createdByName;
	}

	public void setCreatedByName(java.lang.String createdByName) {
		this.createdByName = createdByName;
	}

	public java.lang.String getCancelReasonName() {
		return cancelReasonName;
	}

	public void setCancelReasonName(java.lang.String cancelReasonName) {
		this.cancelReasonName = cancelReasonName;
	}

	public java.lang.Long getOrderId() {
		return orderId;
	}

	public void setOrderId(java.lang.Long orderId) {
		this.orderId = orderId;
	}

	public java.lang.String getCode() {
		return code;
	}

	public void setCode(java.lang.String code) {
		this.code = code.toUpperCase();
	}

	public java.lang.String getType() {
		return type;
	}

	public void setType(java.lang.String type) {
		this.type = type;
	}

	public java.lang.String getBussinessType() {
		return bussinessType;
	}

	public void setBussinessType(java.lang.String bussinessType) {
		this.bussinessType = bussinessType;
	}

	public java.lang.Long getStockId() {
		return stockId;
	}

	public void setStockId(java.lang.Long stockId) {
		this.stockId = stockId;
	}

	public java.lang.Long getDeptReceiveId() {
		return deptReceiveId;
	}

	public void setDeptReceiveId(java.lang.Long deptReceiveId) {
		this.deptReceiveId = deptReceiveId;
	}

	public java.lang.String getDeptReceiveName() {
		return deptReceiveName;
	}

	public void setDeptReceiveName(java.lang.String deptReceiveName) {
		this.deptReceiveName = deptReceiveName;
	}

	public java.lang.String getStatus() {
		return status;
	}

	public void setStatus(java.lang.String status) {
		this.status = status;
	}

	public java.lang.String getDescription() {
		return description;
	}

	public void setDescription(java.lang.String description) {
		this.description = description;
	}

	public java.lang.String getPurchaseOrderNo() {
		return purchaseOrderNo;
	}

	public void setPurchaseOrderNo(java.lang.String purchaseOrderNo) {
		this.purchaseOrderNo = purchaseOrderNo;
	}

	public java.util.Date getPurchaseOrderDate() {
		return purchaseOrderDate;
	}

	public void setPurchaseOrderDate(java.util.Date purchaseOrderDate) {
		this.purchaseOrderDate = purchaseOrderDate;
	}

	public java.lang.String getCerNo() {
		return cerNo;
	}

	public void setCerNo(java.lang.String cerNo) {
		this.cerNo = cerNo;
	}

	public java.util.Date getCerDate() {
		return cerDate;
	}

	public void setCerDate(java.util.Date cerDate) {
		this.cerDate = cerDate;
	}

	public java.util.Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(java.util.Date createdDate) {
		this.createdDate = createdDate;
	}

	public java.lang.Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(java.lang.Long createdBy) {
		this.createdBy = createdBy;
	}

	public java.lang.Long getCreatedDeptedId() {
		return createdDeptedId;
	}

	public void setCreatedDeptedId(java.lang.Long createdDeptedId) {
		this.createdDeptedId = createdDeptedId;
	}

	public java.lang.String getCreatedDeptedName() {
		return createdDeptedName;
	}

	public void setCreatedDeptedName(java.lang.String createdDeptedName) {
		this.createdDeptedName = createdDeptedName;
	}

	public java.lang.Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(java.lang.Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public java.util.Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(java.util.Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public java.lang.String getShipperId() {
		return shipperId;
	}

	public void setShipperId(java.lang.String shipperId) {
		this.shipperId = shipperId;
	}

	public java.lang.String getShipperName() {
		return shipperName;
	}

	public void setShipperName(java.lang.String shipperName) {
		this.shipperName = shipperName;
	}

	public java.util.Date getShipDate() {
		return shipDate;
	}

	public void setShipDate(java.util.Date shipDate) {
		this.shipDate = shipDate;
	}

	public java.lang.Long getShipmentId() {
		return shipmentId;
	}

	public void setShipmentId(java.lang.Long shipmentId) {
		this.shipmentId = shipmentId;
	}

	public java.lang.String getShipmentCode() {
		return shipmentCode;
	}

	public void setShipmentCode(java.lang.String shipmentCode) {
		this.shipmentCode = shipmentCode;
	}

	public java.lang.String getContractCode() {
		return contractCode;
	}

	public void setContractCode(java.lang.String contractCode) {
		this.contractCode = contractCode;
	}

	public java.lang.String getMaintainOrderCode() {
		return maintainOrderCode;
	}

	public void setMaintainOrderCode(java.lang.String maintainOrderCode) {
		this.maintainOrderCode = maintainOrderCode;
	}

	public java.lang.String getMaintainReportCode() {
		return maintainReportCode;
	}

	public void setMaintainReportCode(java.lang.String maintainReportCode) {
		this.maintainReportCode = maintainReportCode;
	}

	public java.lang.String getConsRetrieveOrderCode() {
		return consRetrieveOrderCode;
	}

	public void setConsRetrieveOrderCode(java.lang.String consRetrieveOrderCode) {
		this.consRetrieveOrderCode = consRetrieveOrderCode;
	}

	public java.lang.String getDeptRetrieveOrderCode() {
		return deptRetrieveOrderCode;
	}

	public void setDeptRetrieveOrderCode(java.lang.String deptRetrieveOrderCode) {
		this.deptRetrieveOrderCode = deptRetrieveOrderCode;
	}

	public java.lang.String getLoanRetrieveOrderCode() {
		return loanRetrieveOrderCode;
	}

	public void setLoanRetrieveOrderCode(java.lang.String loanRetrieveOrderCode) {
		this.loanRetrieveOrderCode = loanRetrieveOrderCode;
	}

	public java.lang.String getConstrCode() {
		return constrCode;
	}

	public void setConstrCode(java.lang.String constrCode) {
		this.constrCode = constrCode;
	}

	public java.lang.Long getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(java.lang.Long partnerId) {
		this.partnerId = partnerId;
	}

	public java.lang.Long getIeStockId() {
		return ieStockId;
	}

	public void setIeStockId(java.lang.Long ieStockId) {
		this.ieStockId = ieStockId;
	}

	public java.lang.String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(java.lang.String projectCode) {
		this.projectCode = projectCode;
	}

	public java.lang.String getSignState() {
		return signState;
	}

	public void setSignState(java.lang.String signState) {
		this.signState = signState;
	}

	public java.util.Date getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(java.util.Date cancelDate) {
		this.cancelDate = cancelDate;
	}

	public java.lang.Long getCancelBy() {
		return cancelBy;
	}

	public void setCancelBy(java.lang.Long cancelBy) {
		this.cancelBy = cancelBy;
	}

	public java.lang.String getCancelByName() {
		return cancelByName;
	}

	public void setCancelByName(java.lang.String cancelByName) {
		this.cancelByName = cancelByName;
	}

	public java.lang.String getCancelDescription() {
		return cancelDescription;
	}

	public void setCancelDescription(java.lang.String cancelDescription) {
		this.cancelDescription = cancelDescription;
	}

	public java.lang.String getVofficeTransactionCode() {
		return vofficeTransactionCode;
	}

	public void setVofficeTransactionCode(java.lang.String vofficeTransactionCode) {
		this.vofficeTransactionCode = vofficeTransactionCode;
	}

	public java.lang.Long getReasonId() {
		return reasonId;
	}

	public void setReasonId(java.lang.Long reasonId) {
		this.reasonId = reasonId;
	}

	@Override
	public Long getFWModelId() {
		return orderId;
	}

	@Override
	public String catchName() {
		return getOrderId().toString();
	}

	public java.lang.String getStockName() {
		return stockName;
	}

	public void setStockName(java.lang.String stockName) {
		this.stockName = stockName;
	}

	public java.lang.String getStockTransCode() {
		return stockTransCode;
	}

	public void setStockTransCode(java.lang.String stockTransCode) {
		this.stockTransCode = stockTransCode;
	}

	public java.lang.String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(java.lang.String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public java.lang.String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(java.lang.String goodsName) {
		this.goodsName = goodsName;
	}

	public java.lang.String getGoodsUnitName() {
		return goodsUnitName;
	}

	public void setGoodsUnitName(java.lang.String goodsUnitName) {
		this.goodsUnitName = goodsUnitName;
	}

	public java.lang.Double getAmount() {
		return amount;
	}

	public void setAmount(java.lang.Double amount) {
		this.amount = amount;
	}

	public java.lang.String getGoodsStateName() {
		return goodsStateName;
	}

	public void setGoodsStateName(java.lang.String goodsStateName) {
		this.goodsStateName = goodsStateName;
	}

	public java.lang.String getSerial() {
		return serial;
	}

	public void setSerial(java.lang.String serial) {
		this.serial =  org.apache.commons.lang3.StringUtils.isNotEmpty(serial) ? serial.toUpperCase():null;
	}

	public java.lang.String getOrderGoodsDetailContractCode() {
		return orderGoodsDetailContractCode;
	}

	public void setOrderGoodsDetailContractCode(java.lang.String orderGoodsDetailContractCode) {
		this.orderGoodsDetailContractCode = orderGoodsDetailContractCode;
	}

	public java.lang.String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(java.lang.String partNumber) {
		this.partNumber = partNumber;
	}

	public java.lang.String getManufacturerName() {
		return manufacturerName;
	}

	public void setManufacturerName(java.lang.String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	public java.lang.String getProducingCountryName() {
		return producingCountryName;
	}

	public void setProducingCountryName(java.lang.String producingCountryName) {
		this.producingCountryName = producingCountryName;
	}

	public java.lang.Long getStockTransId() {
		return stockTransId;
	}

	public void setStockTransId(java.lang.Long stockTransId) {
		this.stockTransId = stockTransId;
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

	public java.lang.String getKeySearch() {
		return keySearch;
	}

	public void setKeySearch(java.lang.String keySearch) {
		this.keySearch = keySearch;
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

	public List<OrderGoodsDTO> getListOrderGoodsDTO() {
		return listOrderGoodsDTO;
	}

	public void setListOrderGoodsDTO(List<OrderGoodsDTO> listOrderGoodsDTO) {
		this.listOrderGoodsDTO = listOrderGoodsDTO;
	}

	public List<OrderGoodsDetailDTO> getListOrderGoodsDetailDTO() {
		return listOrderGoodsDetailDTO;
	}

	public void setListOrderGoodsDetailDTO(List<OrderGoodsDetailDTO> listOrderGoodsDetailDTO) {
		this.listOrderGoodsDetailDTO = listOrderGoodsDetailDTO;
	}
	
	public List<OrderGoodsExelDTO> getLstErrorOrderGoods() {
		return lstErrorOrderGoods;
	}

	public void setLstErrorOrderGoods(List<OrderGoodsExelDTO> lstErrorOrderGoods) {
		this.lstErrorOrderGoods = lstErrorOrderGoods;
	}

	public java.lang.String getConstructionName() {
		return constructionName;
	}

	public void setConstructionName(java.lang.String constructionName) {
		this.constructionName = constructionName;
	}

	public java.lang.String getReasonToExport() {
		return reasonToExport;
	}

	public void setReasonToExport(java.lang.String reasonToExport) {
		this.reasonToExport = reasonToExport;
	}

	public java.lang.String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(java.lang.String partnerName) {
		this.partnerName = partnerName;
	}

	public java.lang.Long getRecieverId() {
		return recieverId;
	}

	public void setRecieverId(java.lang.Long recieverId) {
		this.recieverId = recieverId;
	}

	public java.util.Date getExpectedRecievedDate() {
		return expectedRecievedDate;
	}

	public void setExpectedRecievedDate(java.util.Date expectedRecievedDate) {
		this.expectedRecievedDate = expectedRecievedDate;
	}

	public java.lang.Long getIeStockTransId() {
		return ieStockTransId;
	}

	public void setIeStockTransId(java.lang.Long ieStockTransId) {
		this.ieStockTransId = ieStockTransId;
	}
	

	public java.lang.Long getCountSerial() {
		return countSerial;
	}

	public void setCountSerial(java.lang.Long countSerial) {
		this.countSerial = countSerial;
	}

	public List<Long> getListStockId() {
		return listStockId;
	}

	public void setListStockId(List<Long> listStockId) {
		this.listStockId = listStockId;
	}

	public java.lang.Long getManufacturerId() {
		return manufacturerId;
	}

	public void setManufacturerId(java.lang.Long manufacturerId) {
		this.manufacturerId = manufacturerId;
	}

	public java.lang.Long getProducingCountryId() {
		return producingCountryId;
	}

	public void setProducingCountryId(java.lang.Long producingCountryId) {
		this.producingCountryId = producingCountryId;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getGoodsState() {
		return goodsState;
	}

	public void setGoodsState(java.lang.String goodsState) {
		this.goodsState = goodsState;
	}

	public java.lang.String getIsSerial() {
		return isSerial;
	}

	public void setIsSerial(java.lang.String isSerial) {
		this.isSerial = isSerial;
	}
	
	@Override
	public OrderBO toModel() {
		OrderBO orderBO = new OrderBO();
		orderBO.setCreatedByName(this.createdByName);
		orderBO.setCancelReasonName(this.cancelReasonName);
		orderBO.setOrderId(this.orderId);
		orderBO.setCode(this.code);
		orderBO.setType(this.type);
		orderBO.setBussinessType(this.bussinessType);
		orderBO.setStockId(this.stockId);
		orderBO.setDeptReceiveId(this.deptReceiveId);
		orderBO.setDeptReceiveName(this.deptReceiveName);
		orderBO.setStatus(this.status);
		orderBO.setDescription(this.description);
		orderBO.setPurchaseOrderNo(this.purchaseOrderNo);
		orderBO.setPurchaseOrderDate(this.purchaseOrderDate);
		orderBO.setCerNo(this.cerNo);
		orderBO.setCerDate(this.cerDate);
		orderBO.setCreatedDate(this.createdDate);
		orderBO.setCreatedBy(this.createdBy);
		orderBO.setCreatedDeptedId(this.createdDeptedId);
		orderBO.setCreatedDeptedName(this.createdDeptedName);
		orderBO.setUpdatedBy(this.updatedBy);
		orderBO.setUpdatedDate(this.updatedDate);
		orderBO.setShipperId(this.shipperId);
		orderBO.setShipperName(this.shipperName);
		orderBO.setShipDate(this.shipDate);
		orderBO.setShipmentId(this.shipmentId);
		orderBO.setShipmentCode(this.shipmentCode);
		orderBO.setContractCode(this.contractCode);
		orderBO.setMaintainOrderCode(this.maintainOrderCode);
		orderBO.setMaintainReportCode(this.maintainReportCode);
		orderBO.setConsRetrieveOrderCode(this.consRetrieveOrderCode);
		orderBO.setDeptRetrieveOrderCode(this.deptRetrieveOrderCode);
		orderBO.setLoanRetrieveOrderCode(this.loanRetrieveOrderCode);
		orderBO.setConstrCode(this.constrCode);
		orderBO.setPartnerId(this.partnerId);
		orderBO.setIeStockId(this.ieStockId);
		orderBO.setProjectCode(this.projectCode);
		orderBO.setSignState(this.signState);
		orderBO.setCancelDate(this.cancelDate);
		orderBO.setCancelBy(this.cancelBy);
		orderBO.setCancelByName(this.cancelByName);
		orderBO.setCancelDescription(this.cancelDescription);
		orderBO.setVofficeTransactionCode(this.vofficeTransactionCode);
		orderBO.setReasonId(this.reasonId);
		orderBO.setExpectedRecievedDate(this.expectedRecievedDate);
		orderBO.setRecieverId(this.recieverId);
		orderBO.setIeStockTransId(this.ieStockTransId);

		return orderBO;
	}

	public java.lang.Long getGoodsUnitId() {
		return goodsUnitId;
	}

	public void setGoodsUnitId(java.lang.Long goodsUnitId) {
		this.goodsUnitId = goodsUnitId;
	}
	
}
