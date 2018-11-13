package com.viettel.Common.CommonDTO;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.google.common.collect.Lists;
import com.viettel.utils.JsonDateDeserializer;
import com.viettel.utils.JsonDateSerializerDate;
import com.viettel.voffice.ws_autosign.service.FileAttachTranfer;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CommonDTO {
private String typeKpi;
private String keySearch;
private String reportName;
private List<String> reportNames;
public List<String> getReportNames() {
	return reportNames;
}
public void setReportNames(List<String> reportNames) {
	this.reportNames = reportNames;
}

private String reportType;
private String keySearch2;
private String loginName;
private Long stockId;
private Long shipmentId;
public Long getShipmentId() {
	return shipmentId;
}
public void setShipmentId(Long shipmentId) {
	this.shipmentId = shipmentId;
}
private String stringMoney;
private Double totalPrice;
public Double getTotalPrice() {
	return totalPrice;
}
public void setTotalPrice(Double totalPrice) {
	this.totalPrice = totalPrice;
}

private Long goodsState;
private String name;
private Long reponseStatus;
private List<String> listGoodsType;
private List<String> listGoodsTypeReponse;
private String goodsName;
private String goodsNameSearch;
@JsonSerialize(using = JsonDateSerializerDate.class)
@JsonDeserialize(using = JsonDateDeserializer.class)
private java.util.Date startDate;

@JsonSerialize(using = JsonDateSerializerDate.class)
@JsonDeserialize(using = JsonDateDeserializer.class)
private java.util.Date endDate;
private Long stockTransId;
private String user;
public String getUser() {
	return user;
}
public void setUser(String user) {
	this.user = user;
}

private Long orderId;
private String orderCode;
private List<Long> listStockTransId;
private List<Long> listStockId = Lists.newArrayList();


public List<Long> getListStockId() {
	return listStockId;
}
public void setListStockId(List<Long> listStockId) {
	this.listStockId = listStockId;
}
public List<Long> getListStockTransId() {
	return listStockTransId;
}
public void setListStockTransId(List<Long> listStockTransId) {
	this.listStockTransId = listStockTransId;
}
public String getKeySearch2() {
	return keySearch2;
}
public void setKeySearch2(String keySearch2) {
	this.keySearch2 = keySearch2;
}
public String getLoginName() {
	return loginName;
}
public void setLoginName(String loginName) {
	this.loginName = loginName;
}
public String getGoodsNameSearch() {
	return goodsNameSearch;
}
public void setGoodsNameSearch(String goodsNameSearch) {
	this.goodsNameSearch = goodsNameSearch;
}
public String getKeySearch() {
	return keySearch;
}
public void setKeySearch(String keySearch) {
	if(org.apache.commons.lang3.StringUtils.isNotEmpty(keySearch)){
	this.keySearch = keySearch;
	} else {
		this.keySearch=null;
	}
}
public String getTypeKpi() {
	return typeKpi;
}
public void setTypeKpi(String typeKpi) {
	this.typeKpi = typeKpi;
}
public String getGoodsName() {
	return goodsName;
}
public void setGoodsName(String goodsName) {
	this.goodsName = goodsName;
}
public List<String> getListGoodsType() {
	return listGoodsType;
}
public void setListGoodsType(List<String> listGoodsType) {
	this.listGoodsType = listGoodsType;
}
public Long getGoodsState() {
	return goodsState;
}
public void setGoodsState(Long goodsState) {
	this.goodsState = goodsState;
}
public Long getStockId() {
	return stockId;
}
public void setStockId(Long stockId) {
	this.stockId = stockId;
}
public String getReportType() {
	return reportType;
}
public void setReportType(String reportType) {
	this.reportType = reportType;
}
public String getReportName() {
	return reportName;
}
public void setReportName(String reportName) {
	this.reportName = reportName;
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
public Long getReponseStatus() {
	return reponseStatus;
}
public void setReponseStatus(Long reponseStatus) {
	this.reponseStatus = reponseStatus;
}

public List<String> getListGoodsTypeReponse() {
	return listGoodsTypeReponse;
}
public void setListGoodsTypeReponse(List<String> listGoodsTypeReponse) {
	this.listGoodsTypeReponse = listGoodsTypeReponse;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Long getStockTransId() {
	return stockTransId;
}
public void setStockTransId(Long stockTransId) {
	this.stockTransId = stockTransId;
}

	/*Gen CODE*/
	private String value;
	private String orgValue;
	private String stockValue;
	private Long orgId;
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getOrgValue() {
		return orgValue;
	}
	public void setOrgValue(String orgValue) {
		this.orgValue = orgValue;
	}
	public String getStockValue() {
		return stockValue;
	}
	public void setStockValue(String stockValue) {
		this.stockValue = stockValue;
	}
	//Dash board
	/*Param*/
	private Long userId;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getDomainTypeCode() {
		return domainTypeCode;
	}
	public void setDomainTypeCode(String domainTypeCode) {
		this.domainTypeCode = domainTypeCode;
	}

	/*CharFour*/
	private String roleCode;
	private String domainTypeCode;
	
	
	private Long ImNotSign;
	private Long ImNotReal;
	private Long ExNotSign;
	private Long ExNotReal;
	private Long ExInRoad;
	
	private Double ImNotSignRate;
	private Double ImNotRealRate;
	private Double ExNotSignRate;
	private Double ExNotRealRate;
	private Double ExInRoadRate;
	
	public Long getImNotSign() {
		return ImNotSign;
	}
	public void setImNotSign(Long imNotSign) {
		ImNotSign = imNotSign;
	}
	public Long getImNotReal() {
		return ImNotReal;
	}
	public void setImNotReal(Long imNotReal) {
		ImNotReal = imNotReal;
	}
	public Long getExNotSign() {
		return ExNotSign;
	}
	public void setExNotSign(Long exNotSign) {
		ExNotSign = exNotSign;
	}
	public Long getExNotReal() {
		return ExNotReal;
	}
	public void setExNotReal(Long exNotReal) {
		ExNotReal = exNotReal;
	}
	public Long getExInRoad() {
		return ExInRoad;
	}
	public void setExInRoad(Long exInRoad) {
		ExInRoad = exInRoad;
	}
	
	
	public Double getImNotSignRate() {
		return ImNotSignRate;
	}
	public void setImNotSignRate(Double imNotSignRate) {
		ImNotSignRate = imNotSignRate;
	}
	public Double getImNotRealRate() {
		return ImNotRealRate;
	}
	public void setImNotRealRate(Double imNotRealRate) {
		ImNotRealRate = imNotRealRate;
	}
	public Double getExNotSignRate() {
		return ExNotSignRate;
	}
	public void setExNotSignRate(Double exNotSignRate) {
		ExNotSignRate = exNotSignRate;
	}
	public Double getExNotRealRate() {
		return ExNotRealRate;
	}
	public void setExNotRealRate(Double exNotRealRate) {
		ExNotRealRate = exNotRealRate;
	}
	public Double getExInRoadRate() {
		return ExInRoadRate;
	}
	public void setExInRoadRate(Double exInRoadRate) {
		ExInRoadRate = exInRoadRate;
	}
	
	
	/*CharONE*/
	
	private Long imStock;
	private Long outStock;
	private String stockCode;
	
	public Long getImStock() {
		return imStock;
	}
	public void setImStock(Long imStock) {
		this.imStock = imStock;
	}
	public Long getOutStock() {
		return outStock;
	}
	public void setOutStock(Long outStock) {
		this.outStock = outStock;
	}
	public String getStockCode() {
		return stockCode;
	}
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	
	private List<Long> listImStock = Lists.newArrayList();
	private List<Long> listOutStock = Lists.newArrayList();
	public List<Long> getListOutStock() {
		return listOutStock;
	}
	public void setListOutStock(List<Long> listOutStock) {
		this.listOutStock = listOutStock;
	}

	private List<String> listStockCode=Lists.newArrayList();
	
	public List<Long> getListImStock() {
		return listImStock;
	}
	public void setListImStock(List<Long> listImStock) {
		this.listImStock = listImStock;
	}
	public List<String> getListStockCode() {
		return listStockCode;
	}
	public void setListStockCode(List<String> listStockCode) {
		this.listStockCode = listStockCode;
	}
	
	/*KIP Char*/
	
	private Long kipAmount;
	private List<Long> listKPIAmount= Lists.newArrayList();
	public List<Long> getListKPIAmount() {
		return listKPIAmount;
	}
	public void setListKPIAmount(List<Long> listKPIAmount) {
		this.listKPIAmount = listKPIAmount;
	}
	public Long getKipAmount() {
		return kipAmount;
	}
	public void setKipAmount(Long kipAmount) {
		this.kipAmount = kipAmount;
	}
	
	/*Char Two*/
	private Long imported;
	private Long exported;
	@JsonSerialize(using = JsonDateSerializerDate.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private Date day;
	
	private List<Long> listImported = Lists.newArrayList();
	private List<Long> listExported = Lists.newArrayList();
	private List<Date> listDay= Lists.newArrayList();
	
	public Long getImported() {
		return imported;
	}
	public void setImported(Long imported) {
		this.imported = imported;
	}
	public Long getExported() {
		return exported;
	}
	public void setExported(Long exported) {
		this.exported = exported;
	}
	public Date getDay() {
		return day;
	}
	public void setDay(Date day) {
		this.day = day;
	}
	public List<Long> getListImported() {
		return listImported;
	}
	public void setListImported(List<Long> listImported) {
		this.listImported = listImported;
	}
	public List<Long> getListExported() {
		return listExported;
	}
	public void setListExported(List<Long> listExported) {
		this.listExported = listExported;
	}
	public List<Date> getListDay() {
		return listDay;
	}
	public void setListDay(List<Date> listDay) {
		this.listDay = listDay;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	
	//voffice
	private List<FileAttachTranfer> lstFileAttach= Lists.newArrayList();
	private List<String> listEmail=Lists.newArrayList();
	private Long objectId;
	private Long createdBy;
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	private String objectCode;
	private String type;
	private List<Long> listId;


	
	public List<FileAttachTranfer> getLstFileAttach() {
		return lstFileAttach;
	}
	public void setLstFileAttach(List<FileAttachTranfer> lstFileAttach) {
		this.lstFileAttach = lstFileAttach;
	}
	public List<String> getListEmail() {
		return listEmail;
	}
	public void setListEmail(List<String> listEmail) {
		this.listEmail = listEmail;
	}

	
	public Long getObjectId() {
		return objectId;
	}
	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}
	public String getObjectCode() {
		return objectCode;
	}
	public void setObjectCode(String objectCode) {
		this.objectCode = objectCode;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<Long> getListId() {
		return listId;
	}
	public void setListId(List<Long> listId) {
		this.listId = listId;
	}

	
	
	private String reportGroup;
	public String getReportGroup() {
		return reportGroup;
	}
	public void setReportGroup(String reportGroup) {
		this.reportGroup = reportGroup;
	}
	public String getStringMoney() {
		return stringMoney;
	}
	public void setStringMoney(String stringMoney) {
		this.stringMoney = stringMoney;
	}
}
