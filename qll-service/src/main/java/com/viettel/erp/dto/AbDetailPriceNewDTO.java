package com.viettel.erp.dto;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.viettel.erp.bo.AbDetailPriceBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;

@XmlRootElement(name = "AB_DETAIL_PRICEBO")
public class AbDetailPriceNewDTO{

	
	private java.lang.String numberic ;


	private java.lang.Long estimatesWorkItemId;
	private java.lang.String workItemCode2;
	private java.lang.String workItemName2;
	private java.lang.String unit2;
	private java.lang.Long type2;
	private java.lang.String costIngredientName2;
	private java.lang.Double unitPrice2;	
	private java.lang.Double normIndex2;
	private java.lang.Double coefficient2;
	private java.lang.Double totalMoney2;	

	
	private java.lang.String workItemCode4;
	private java.lang.String workItemName4;
	private java.lang.String unit4;
	private java.lang.Long type4;
	private String costIngredientName4;
	private java.lang.Double unitPrice4;
	private java.lang.Double normIndex4;
	private java.lang.Double coefficient4;
	private java.lang.Double totalMoney4;
	
	private java.lang.String deviant;


	
	//Ep kieu
	private java.lang.String unitPrice22;
	private java.lang.String unitPrice44;
	private java.lang.String normIndex22;
	private java.lang.String normIndex44;
	private java.lang.String coefficient22;
	private java.lang.String coefficient44;
	private java.lang.String totalMoney22;
	private java.lang.String totalMoney44;
	
	DecimalFormat df = new DecimalFormat("0.#");
	
	
	
	
	
	/**
	 * @return the estimatesWorkItemId
	 */
	public java.lang.Long getEstimatesWorkItemId() {
		return estimatesWorkItemId;
	}

	/**
	 * @param estimatesWorkItemId the estimatesWorkItemId to set
	 */
	public void setEstimatesWorkItemId(java.lang.Long estimatesWorkItemId) {
		this.estimatesWorkItemId = estimatesWorkItemId;
	}

	/**
	 * @return the workItemCode2
	 */
	public java.lang.String getWorkItemCode2() {
		return Strings.nullToEmpty(workItemCode2);
	}

	/**
	 * @param workItemCode2 the workItemCode2 to set
	 */
	public void setWorkItemCode2(java.lang.String workItemCode2) {
		this.workItemCode2 = workItemCode2;
	}

	/**
	 * @return the workItemName2
	 */
	public java.lang.String getWorkItemName2() {
		return Strings.nullToEmpty(workItemName2);
	}

	/**
	 * @param workItemName2 the workItemName2 to set
	 */
	public void setWorkItemName2(java.lang.String workItemName2) {
		this.workItemName2 = workItemName2;
	}

	/**
	 * @return the unit2
	 */
	public java.lang.String getUnit2() {
		return Strings.nullToEmpty(unit2);
	}

	/**
	 * @param unit2 the unit2 to set
	 */
	public void setUnit2(java.lang.String unit2) {
		this.unit2 = unit2;
	}

	/**
	 * @return the type2
	 */
	public java.lang.Long getType2() {
		return type2;
	}

	/**
	 * @param type2 the type2 to set
	 */
	public void setType2(java.lang.Long type2) {
		this.type2 = type2;
	}

	/**
	 * @return the costIngredientName2
	 */
	public java.lang.String getCostIngredientName2() {
		return Strings.nullToEmpty(costIngredientName2);
	}

	/**
	 * @param costIngredientName2 the costIngredientName2 to set
	 */
	public void setCostIngredientName2(java.lang.String costIngredientName2) {
		this.costIngredientName2 = costIngredientName2;
	}

	/**
	 * @return the unitPrice2
	 */
	public java.lang.Double getUnitPrice2() {
		return unitPrice2;
	}

	/**
	 * @param unitPrice2 the unitPrice2 to set
	 */
	public void setUnitPrice2(java.lang.Double unitPrice2) {
		this.unitPrice2 = unitPrice2;
	}

	/**
	 * @return the normIndex2
	 */
	public java.lang.Double getNormIndex2() {
		return normIndex2;
	}

	/**
	 * @param normIndex2 the normIndex2 to set
	 */
	public void setNormIndex2(java.lang.Double normIndex2) {
		this.normIndex2 = normIndex2;
	}

	/**
	 * @return the totalMoney2
	 */
	public java.lang.Double getTotalMoney2() {
		return totalMoney2;
	}

	/**
	 * @param totalMoney2 the totalMoney2 to set
	 */
	public void setTotalMoney2(java.lang.Double totalMoney2) {
		this.totalMoney2 = totalMoney2;
	}

	/**
	 * @return the workItemCode4
	 */
	public java.lang.String getWorkItemCode4() {
		return workItemCode4;
	}

	/**
	 * @param workItemCode4 the workItemCode4 to set
	 */
	public void setWorkItemCode4(java.lang.String workItemCode4) {
		this.workItemCode4 = workItemCode4;
	}

	/**
	 * @return the workItemName4
	 */
	public java.lang.String getWorkItemName4() {
		return workItemName4;
	}

	/**
	 * @param workItemName4 the workItemName4 to set
	 */
	public void setWorkItemName4(java.lang.String workItemName4) {
		this.workItemName4 = workItemName4;
	}

	/**
	 * @return the unit4
	 */
	public java.lang.String getUnit4() {
		return unit4;
	}

	/**
	 * @param unit4 the unit4 to set
	 */
	public void setUnit4(java.lang.String unit4) {
		this.unit4 = unit4;
	}

	/**
	 * @return the type4
	 */
	public java.lang.Long getType4() {
		return type4;
	}

	/**
	 * @param type4 the type4 to set
	 */
	public void setType4(java.lang.Long type4) {
		this.type4 = type4;
	}

	/**
	 * @return the costIngredientName4
	 */
	public String getCostIngredientName4() {
		return costIngredientName4;
	}

	/**
	 * @param costIngredientName4 the costIngredientName4 to set
	 */
	public void setCostIngredientName4(String costIngredientName4) {
		this.costIngredientName4 = costIngredientName4;
	}

	/**
	 * @return the unitPrice4
	 */
	public java.lang.Double getUnitPrice4() {
		return unitPrice4;
	}

	/**
	 * @param unitPrice4 the unitPrice4 to set
	 */
	public void setUnitPrice4(java.lang.Double unitPrice4) {
		this.unitPrice4 = unitPrice4;
	}

	/**
	 * @return the normIndex4
	 */
	public java.lang.Double getNormIndex4() {
		return normIndex4;
	}

	/**
	 * @param normIndex4 the normIndex4 to set
	 */
	public void setNormIndex4(java.lang.Double normIndex4) {
		this.normIndex4 = normIndex4;
	}

	/**
	 * @return the totalMoney4
	 */
	public java.lang.Double getTotalMoney4() {
		return totalMoney4;
	}

	/**
	 * @param totalMoney4 the totalMoney4 to set
	 */
	public void setTotalMoney4(java.lang.Double totalMoney4) {
		this.totalMoney4 = totalMoney4;
	}

	/**
	 * @return the unitPrice22
	 */
	public java.lang.String getUnitPrice22() {
		return Strings.nullToEmpty(unitPrice22);
	}

	/**
	 * @param unitPrice22 the unitPrice22 to set
	 */
	public void setUnitPrice22(java.lang.String unitPrice22) {
		this.unitPrice22 = unitPrice22;
	}

	/**
	 * @return the unitPrice44
	 */
	public java.lang.String getUnitPrice44() {
		return  Strings.nullToEmpty(unitPrice44);
	}

	/**
	 * @param unitPrice44 the unitPrice44 to set
	 */
	public void setUnitPrice44(java.lang.String unitPrice44) {
		this.unitPrice44 = unitPrice44;
	}

	/**
	 * @return the normIndex22
	 */
	public java.lang.String getNormIndex22() {
		return  Strings.nullToEmpty(normIndex22);
	}

	/**
	 * @param normIndex22 the normIndex22 to set
	 */
	public void setNormIndex22(java.lang.String normIndex22) {
		this.normIndex22 = normIndex22;
	}

	/**
	 * @return the normIndex44
	 */
	public java.lang.String getNormIndex44() {
		return  Strings.nullToEmpty(normIndex44);
	}

	/**
	 * @param normIndex44 the normIndex44 to set
	 */
	public void setNormIndex44(java.lang.String normIndex44) {
		this.normIndex44 = normIndex44;
	}

	/**
	 * @return the totalMoney22
	 */
	public java.lang.String getTotalMoney22() {
		return  Strings.nullToEmpty(totalMoney22);
	}

	/**
	 * @param totalMoney22 the totalMoney22 to set
	 */
	public void setTotalMoney22(java.lang.String totalMoney22) {
		this.totalMoney22 = totalMoney22;
	}

	/**
	 * @return the totalMoney44
	 */
	public java.lang.String getTotalMoney44() {
		return  Strings.nullToEmpty(totalMoney44);
	}

	/**
	 * @param totalMoney44 the totalMoney44 to set
	 */
	public void setTotalMoney44(java.lang.String totalMoney44) {
		this.totalMoney44 = totalMoney44;
	}

	public java.lang.String getNumberic() {
		return  Strings.nullToEmpty(numberic);
	}

	public void setNumberic(java.lang.String numberic) {
		this.numberic = numberic;
	}

	public java.lang.String getDeviant() {
		

		return Strings.nullToEmpty(deviant);
	}

	public void setDeviant(java.lang.String deviant) {
		this.deviant = deviant;
	}

	public java.lang.Double getCoefficient2() {
		return coefficient2;
	}

	public void setCoefficient2(java.lang.Double coefficient2) {
		this.coefficient2 = coefficient2;
	}

	public java.lang.Double getCoefficient4() {
		return coefficient4;
	}

	public void setCoefficient4(java.lang.Double coefficient4) {
		this.coefficient4 = coefficient4;
	}

	public java.lang.String getCoefficient22() {
		return Strings.nullToEmpty(coefficient22);
	}

	public void setCoefficient22(java.lang.String coefficient22) {
		this.coefficient22 = coefficient22;
	}

	public java.lang.String getCoefficient44() {
		return Strings.nullToEmpty(coefficient44);
	}

	public void setCoefficient44(java.lang.String coefficient44) {
		this.coefficient44 = coefficient44;
	}




	

	
}
