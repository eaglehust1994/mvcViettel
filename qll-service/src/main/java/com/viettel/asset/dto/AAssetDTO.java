package com.viettel.asset.dto;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.viettel.erp.utils.JsonResetServiceClientSerializer;
import com.viettel.erp.utils.JsonDateSerializer;

public class AAssetDTO {
	private String orgValue;
	
	private String departmentValue;
	private Long documentType;
	private String sourceValue;	
	private String type;
	private Long typeEstimate;
	private String groupValue;
	private String typeValue;
	private Long monthDepreciation;
	private String value;
	private String name;
	@JsonSerialize(using = JsonResetServiceClientSerializer.class)
	private Date createDate;
	private String docBbbg;
	@JsonSerialize(using = JsonResetServiceClientSerializer.class)
	private Date dateused;
	private Long baseAmount;	
	private Long depreciationAmount;
	private Long depreciationTime;
	
	
	private String projectValue;
	private String constructionValue;
	
	
	public String getProjectValue() {
		return projectValue;
	}
	public void setProjectValue(String projectValue) {
		this.projectValue = projectValue;
	}
	public String getConstructionValue() {
		return constructionValue;
	}
	public void setConstructionValue(String constructionValue) {
		this.constructionValue = constructionValue;
	}
	public Long getDepreciationTime() {
		return depreciationTime;
	}
	public void setDepreciationTime(Long depreciationTime) {
		this.depreciationTime = depreciationTime;
	}
	public Long getDepreciationAmount() {
		return depreciationAmount;
	}
	public void setDepreciationAmount(Long depreciationAmount) {
		this.depreciationAmount = depreciationAmount;
	}
	public String getOrgValue() {
		return orgValue;
	}
	public void setOrgValue(String orgValue) {
		this.orgValue = orgValue;
	}
	public String getDepartmentValue() {
		return departmentValue;
	}
	public void setDepartmentValue(String departmentValue) {
		this.departmentValue = departmentValue;
	}
	public Long getDocumentType() {
		return documentType;
	}
	public void setDocumentType(Long documentType) {
		this.documentType = documentType;
	}
	public String getSourceValue() {
		return sourceValue;
	}
	public void setSourceValue(String sourceValue) {
		this.sourceValue = sourceValue;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getTypeEstimate() {
		return typeEstimate;
	}
	public void setTypeEstimate(Long typeEstimate) {
		this.typeEstimate = typeEstimate;
	}
	public String getGroupValue() {
		return groupValue;
	}
	public void setGroupValue(String groupValue) {
		this.groupValue = groupValue;
	}
	public String getTypeValue() {
		return typeValue;
	}
	public void setTypeValue(String typeValue) {
		this.typeValue = typeValue;
	}
	public Long getMonthDepreciation() {
		return monthDepreciation;
	}
	public void setMonthDepreciation(Long monthDepreciation) {
		this.monthDepreciation = monthDepreciation;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getDocBbbg() {
		return docBbbg;
	}
	public void setDocBbbg(String docBbbg) {
		this.docBbbg = docBbbg;
	}
	public Date getDateused() {
		return dateused;
	}
	public void setDateused(Date dateused) {
		this.dateused = dateused;
	}
	public Long getBaseAmount() {
		return baseAmount;
	}
	public void setBaseAmount(Long baseAmount) {
		this.baseAmount = baseAmount;
	}
}
