package com.viettel.asset.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.viettel.asset.bo.CatAssetCode;

@XmlRootElement(name = "catAssetCode")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CatAssetCodeDto {

	private Long catAssetCodeId;
	private String caacCode;
	private String caacName;
	private String caacPath;
	private Long caacParentId;
	private Long caacLevel;
	private Long caacIndex;
	private Long isActive;
	private String caacFullCode;
	private Long creatorId;
	private Date createdDate;
	private Long lastUpdaterId;
	private Date lastModifiedDate;
	private Long isFixedAsset;
	private Long depreciationTime;
	private Boolean isUsing;
	private String text;
	private Long useDuration;

	public Long getUseDuration() {
		return useDuration;
	}

	public void setUseDuration(Long useDuration) {
		this.useDuration = useDuration;
	}

	public String getText() {
		return text = this.caacCode + " - " + this.caacName;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Boolean getIsUsing() {
		return isUsing;
	}

	public void setIsUsing(Boolean isUsing) {
		this.isUsing = isUsing;
	}

	public CatAssetCodeDto() {

	}

	public CatAssetCodeDto(CatAssetCode entity) {
		this.catAssetCodeId = entity.getCatAssetCodeId();
		this.caacCode = entity.getCaacCode();
		this.caacName = entity.getCaacName();
		this.caacPath = entity.getCaacPath();
		this.caacParentId = entity.getCaacParentId();
		this.caacLevel = entity.getCaacLevel();
		this.caacIndex = entity.getCaacIndex();
		this.isActive = entity.getIsActive();
		this.caacFullCode = entity.getCaacFullCode();
		this.creatorId = entity.getCreatorId();
		this.createdDate = entity.getCreatedDate();
		this.lastUpdaterId = entity.getLastUpdaterId();
		this.lastModifiedDate = entity.getLastModifiedDate();
		this.isFixedAsset = entity.getIsFixedAsset();
		this.depreciationTime = entity.getDepreciationTime();
		this.useDuration=entity.getUseDuration();
		this.text=entity.getCaacFullCode()+" - "+entity.getCaacName();
	}

	public CatAssetCode toEntity() {
		CatAssetCode entity = new CatAssetCode();
		entity.setCatAssetCodeId(this.catAssetCodeId);
		entity.setCaacCode(this.caacCode);
		entity.setCaacName(this.caacName);
		entity.setCaacPath(this.caacPath);
		entity.setCaacParentId(this.caacParentId);
		entity.setCaacLevel(this.caacLevel);
		entity.setCaacIndex(this.caacIndex);
		entity.setIsActive(this.isActive);
		entity.setCaacFullCode(this.caacFullCode);
		entity.setCreatorId(this.creatorId);
		entity.setCreatedDate(this.createdDate);
		entity.setLastUpdaterId(this.lastUpdaterId);
		entity.setLastModifiedDate(this.lastModifiedDate);
		entity.setIsFixedAsset(this.isFixedAsset);
		entity.setDepreciationTime(this.depreciationTime);
		entity.setUseDuration(this.useDuration);
		return entity;
	}

	public Long getCatAssetCodeId() {
		return catAssetCodeId;
	}

	public void setCatAssetCodeId(Long catAssetCodeId) {
		this.catAssetCodeId = catAssetCodeId;
	}

	public String getCaacCode() {
		return caacCode;
	}

	public void setCaacCode(String caacCode) {
		this.caacCode = caacCode;
	}

	public String getCaacName() {
		return caacName;
	}

	public void setCaacName(String caacName) {
		this.caacName = caacName;
	}

	public String getCaacPath() {
		return caacPath;
	}

	public void setCaacPath(String caacPath) {
		this.caacPath = caacPath;
	}

	public Long getCaacParentId() {
		return caacParentId;
	}

	public void setCaacParentId(Long caacParentId) {
		this.caacParentId = caacParentId;
	}

	public Long getCaacLevel() {
		return caacLevel;
	}

	public void setCaacLevel(Long caacLevel) {
		this.caacLevel = caacLevel;
	}

	public Long getCaacIndex() {
		return caacIndex;
	}

	public void setCaacIndex(Long caacIndex) {
		this.caacIndex = caacIndex;
	}

	public Long getIsActive() {
		return isActive;
	}

	public void setIsActive(Long isActive) {
		this.isActive = isActive;
	}

	public String getCaacFullCode() {
		return caacFullCode;
	}

	public void setCaacFullCode(String caacFullCode) {
		this.caacFullCode = caacFullCode;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Long getLastUpdaterId() {
		return lastUpdaterId;
	}

	public void setLastUpdaterId(Long lastUpdaterId) {
		this.lastUpdaterId = lastUpdaterId;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public Long getIsFixedAsset() {
		return isFixedAsset;
	}

	public void setIsFixedAsset(Long isFixedAsset) {
		this.isFixedAsset = isFixedAsset;
	}

	public Long getDepreciationTime() {
		return depreciationTime;
	}

	public void setDepreciationTime(Long depreciationTime) {
		this.depreciationTime = depreciationTime;
	}

}
