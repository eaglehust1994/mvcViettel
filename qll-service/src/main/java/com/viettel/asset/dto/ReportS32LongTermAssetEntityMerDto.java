package com.viettel.asset.dto;

import java.io.Serializable;

public class ReportS32LongTermAssetEntityMerDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String num;
	private String merName;
	private String merUnitName;
	private Long quantity;
	private Long originalPrice;
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getMerName() {
		return merName==null?"":merName;
	}
	public void setMerName(String merName) {
		this.merName = merName;
	}
	public String getMerUnitName() {
		return merUnitName==null?"":merUnitName;
	}
	public void setMerUnitName(String merUnitName) {
		this.merUnitName = merUnitName;
	}
	public Long getQuantity() {
		return quantity==null?0l:quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public Long getOriginalPrice() {
		return originalPrice==null?0l:originalPrice;
	}
	public void setOriginalPrice(Long originalPrice) {
		this.originalPrice = originalPrice;
	}
	

}
