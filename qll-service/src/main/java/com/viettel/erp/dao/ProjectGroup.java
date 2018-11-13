/**
 * 
 */
package com.viettel.erp.dao;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Huy
 *
 */
public class ProjectGroup implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3994800349342180018L;
	private BigDecimal subTotal;
	private Long projectId;
	
	
	/**
	 * @return the subTotalDouble
	 */
	public Double getSubTotalDouble() {
		return subTotal == null ? 0d : subTotal.doubleValue();
	}
	
	public void setSubTotalDouble(Double subTotal) {
		this.subTotal = new BigDecimal(subTotal);
	}
	/**
	 * @return the subTotal
	 */
	public BigDecimal getSubTotal() {
		return subTotal;
	}
	/**
	 * @param subTotal the subTotal to set
	 */
	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}
	/**
	 * @return the projectId
	 */
	public Long getProjectId() {
		return projectId;
	}
	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	
}
