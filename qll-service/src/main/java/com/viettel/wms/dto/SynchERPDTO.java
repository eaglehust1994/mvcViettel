/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.dto;

import com.viettel.erp.utils.JsonDateDeserializer;
import com.viettel.erp.utils.JsonDateSerializerDate;
//import com.viettel.wms.bo.AppParamBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
 * @author PhongPV
 */
@XmlRootElement(name = "APP_PARAMBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SynchERPDTO  {
	
	
	/*input*/
	@JsonSerialize(using = JsonDateSerializerDate.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private java.util.Date fromDateTime;
	@JsonSerialize(using = JsonDateSerializerDate.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private java.util.Date toDateTime;
	private java.lang.String stringtype;
	private java.lang.String stringtransCode;
	
	/*output*/
	private java.lang.Long id;
	private java.lang.String code;
	private java.lang.String stockCode;
	private java.lang.String realImportExporeName;
	private java.util.Date realImportExportDate;
	private java.lang.String contractCode;
	
	private java.lang.String shipmentCode;
	private java.lang.String projectCode;
	private java.lang.String constrCode;
	private java.lang.String partnerCode;
	private java.lang.Long bussTypeId;
	
	private java.lang.Long deptReceiveId;
	private java.lang.String deptReceiveCode;
	private java.lang.String deptReceiveName;
	private java.lang.Double totalMoney;
	private java.lang.Long type;
	
	/*Detail*/

	private Long goodsType;
	private String goodsTypeName;
	private Long goodsId;
	private String goodsCode;
	private String goodsName;
	private Long goodsIsSerial;
	private Long goodsState;
	private String goodsStateName;
	private String goodsUnitName;
	private Long goodsUnitId;
	private Double amountOrder;
	private Double amountReal;
	private Double totalPrice;
	
	/**
	 * @return the fromDateTime
	 */
	public java.util.Date getFromDateTime() {
		return fromDateTime;
	}
	/**
	 * @param fromDateTime the fromDateTime to set
	 */
	public void setFromDateTime(java.util.Date fromDateTime) {
		this.fromDateTime = fromDateTime;
	}
	/**
	 * @return the toDateTime
	 */
	public java.util.Date getToDateTime() {
		return toDateTime;
	}
	/**
	 * @param toDateTime the toDateTime to set
	 */
	public void setToDateTime(java.util.Date toDateTime) {
		this.toDateTime = toDateTime;
	}
	/**
	 * @return the stringtype
	 */
	public java.lang.String getStringtype() {
		return stringtype;
	}
	/**
	 * @param stringtype the stringtype to set
	 */
	public void setStringtype(java.lang.String stringtype) {
		this.stringtype = stringtype;
	}
	/**
	 * @return the stringtransCode
	 */
	public java.lang.String getStringtransCode() {
		return stringtransCode;
	}
	/**
	 * @param stringtransCode the stringtransCode to set
	 */
	public void setStringtransCode(java.lang.String stringtransCode) {
		this.stringtransCode = stringtransCode;
	}
	/**
	 * @return the id
	 */
	public java.lang.Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(java.lang.Long id) {
		this.id = id;
	}
	/**
	 * @return the code
	 */
	public java.lang.String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(java.lang.String code) {
		this.code = code;
	}
	/**
	 * @return the stockCode
	 */
	public java.lang.String getStockCode() {
		return stockCode;
	}
	/**
	 * @param stockCode the stockCode to set
	 */
	public void setStockCode(java.lang.String stockCode) {
		this.stockCode = stockCode;
	}
	/**
	 * @return the realImportExporeName
	 */
	public java.lang.String getRealImportExporeName() {
		return realImportExporeName;
	}
	/**
	 * @param realImportExporeName the realImportExporeName to set
	 */
	public void setRealImportExporeName(java.lang.String realImportExporeName) {
		this.realImportExporeName = realImportExporeName;
	}
	/**
	 * @return the realImportExportDate
	 */
	public java.util.Date getRealImportExportDate() {
		return realImportExportDate;
	}
	/**
	 * @param realImportExportDate the realImportExportDate to set
	 */
	public void setRealImportExportDate(java.util.Date realImportExportDate) {
		this.realImportExportDate = realImportExportDate;
	}
	/**
	 * @return the contractCode
	 */
	public java.lang.String getContractCode() {
		return contractCode;
	}
	/**
	 * @param contractCode the contractCode to set
	 */
	public void setContractCode(java.lang.String contractCode) {
		this.contractCode = contractCode;
	}
	/**
	 * @return the shipmentCode
	 */
	public java.lang.String getShipmentCode() {
		return shipmentCode;
	}
	/**
	 * @param shipmentCode the shipmentCode to set
	 */
	public void setShipmentCode(java.lang.String shipmentCode) {
		this.shipmentCode = shipmentCode;
	}
	/**
	 * @return the projectCode
	 */
	public java.lang.String getProjectCode() {
		return projectCode;
	}
	/**
	 * @param projectCode the projectCode to set
	 */
	public void setProjectCode(java.lang.String projectCode) {
		this.projectCode = projectCode;
	}
	/**
	 * @return the constrCode
	 */
	public java.lang.String getConstrCode() {
		return constrCode;
	}
	/**
	 * @param constrCode the constrCode to set
	 */
	public void setConstrCode(java.lang.String constrCode) {
		this.constrCode = constrCode;
	}
	/**
	 * @return the partnerCode
	 */
	public java.lang.String getPartnerCode() {
		return partnerCode;
	}
	/**
	 * @param partnerCode the partnerCode to set
	 */
	public void setPartnerCode(java.lang.String partnerCode) {
		this.partnerCode = partnerCode;
	}
	/**
	 * @return the bussTypeId
	 */
	public java.lang.Long getBussTypeId() {
		return bussTypeId;
	}
	/**
	 * @param bussTypeId the bussTypeId to set
	 */
	public void setBussTypeId(java.lang.Long bussTypeId) {
		this.bussTypeId = bussTypeId;
	}
	/**
	 * @return the deptReceiveId
	 */
	public java.lang.Long getDeptReceiveId() {
		return deptReceiveId;
	}
	/**
	 * @param deptReceiveId the deptReceiveId to set
	 */
	public void setDeptReceiveId(java.lang.Long deptReceiveId) {
		this.deptReceiveId = deptReceiveId;
	}
	/**
	 * @return the deptReceiveCode
	 */
	public java.lang.String getDeptReceiveCode() {
		return deptReceiveCode;
	}
	/**
	 * @param deptReceiveCode the deptReceiveCode to set
	 */
	public void setDeptReceiveCode(java.lang.String deptReceiveCode) {
		this.deptReceiveCode = deptReceiveCode;
	}
	/**
	 * @return the deptReceiveName
	 */
	public java.lang.String getDeptReceiveName() {
		return deptReceiveName;
	}
	/**
	 * @param deptReceiveName the deptReceiveName to set
	 */
	public void setDeptReceiveName(java.lang.String deptReceiveName) {
		this.deptReceiveName = deptReceiveName;
	}
	/**
	 * @return the totalMoney
	 */
	public java.lang.Double getTotalMoney() {
		return totalMoney;
	}
	/**
	 * @param totalMoney the totalMoney to set
	 */
	public void setTotalMoney(java.lang.Double totalMoney) {
		this.totalMoney = totalMoney;
	}
	/**
	 * @return the type
	 */
	public java.lang.Long getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(java.lang.Long type) {
		this.type = type;
	}
	/**
	 * @return the goodsType
	 */
	public Long getGoodsType() {
		return goodsType;
	}
	/**
	 * @param goodsType the goodsType to set
	 */
	public void setGoodsType(Long goodsType) {
		this.goodsType = goodsType;
	}
	/**
	 * @return the goodsTypeName
	 */
	public String getGoodsTypeName() {
		return goodsTypeName;
	}
	/**
	 * @param goodsTypeName the goodsTypeName to set
	 */
	public void setGoodsTypeName(String goodsTypeName) {
		this.goodsTypeName = goodsTypeName;
	}
	/**
	 * @return the goodsId
	 */
	public Long getGoodsId() {
		return goodsId;
	}
	/**
	 * @param goodsId the goodsId to set
	 */
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	/**
	 * @return the goodsCode
	 */
	public String getGoodsCode() {
		return goodsCode;
	}
	/**
	 * @param goodsCode the goodsCode to set
	 */
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	/**
	 * @return the goodsName
	 */
	public String getGoodsName() {
		return goodsName;
	}
	/**
	 * @param goodsName the goodsName to set
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	/**
	 * @return the goodsIsSerial
	 */
	public Long getGoodsIsSerial() {
		return goodsIsSerial;
	}
	/**
	 * @param goodsIsSerial the goodsIsSerial to set
	 */
	public void setGoodsIsSerial(Long goodsIsSerial) {
		this.goodsIsSerial = goodsIsSerial;
	}
	/**
	 * @return the goodsState
	 */
	public Long getGoodsState() {
		return goodsState;
	}
	/**
	 * @param goodsState the goodsState to set
	 */
	public void setGoodsState(Long goodsState) {
		this.goodsState = goodsState;
	}
	/**
	 * @return the goodsStateName
	 */
	public String getGoodsStateName() {
		return goodsStateName;
	}
	/**
	 * @param goodsStateName the goodsStateName to set
	 */
	public void setGoodsStateName(String goodsStateName) {
		this.goodsStateName = goodsStateName;
	}
	/**
	 * @return the goodsUnitName
	 */
	public String getGoodsUnitName() {
		return goodsUnitName;
	}
	/**
	 * @param goodsUnitName the goodsUnitName to set
	 */
	public void setGoodsUnitName(String goodsUnitName) {
		this.goodsUnitName = goodsUnitName;
	}
	/**
	 * @return the goodsUnitId
	 */
	public Long getGoodsUnitId() {
		return goodsUnitId;
	}
	/**
	 * @param goodsUnitId the goodsUnitId to set
	 */
	public void setGoodsUnitId(Long goodsUnitId) {
		this.goodsUnitId = goodsUnitId;
	}
	/**
	 * @return the amountOrder
	 */
	public Double getAmountOrder() {
		return amountOrder;
	}
	/**
	 * @param amountOrder the amountOrder to set
	 */
	public void setAmountOrder(Double amountOrder) {
		this.amountOrder = amountOrder;
	}
	/**
	 * @return the amountReal
	 */
	public Double getAmountReal() {
		return amountReal;
	}
	/**
	 * @param amountReal the amountReal to set
	 */
	public void setAmountReal(Double amountReal) {
		this.amountReal = amountReal;
	}
	/**
	 * @return the totalPrice
	 */
	public Double getTotalPrice() {
		return totalPrice;
	}
	/**
	 * @param totalPrice the totalPrice to set
	 */
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
}
