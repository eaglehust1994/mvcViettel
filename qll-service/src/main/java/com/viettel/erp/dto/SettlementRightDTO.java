/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.google.common.collect.Lists;
import com.viettel.erp.bo.CatEmployeeBO;
import com.viettel.erp.bo.ConstrConstructionsBO;
import com.viettel.erp.bo.RoleCaBO;
import com.viettel.erp.bo.SettlementRightBO;
import com.viettel.erp.bo.VConstructionHcqtBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "SETTLEMENT_RIGHTBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SettlementRightDTO extends BaseFWDTOImpl<SettlementRightBO> {

	private java.lang.Long constructId;
	private java.lang.Long settlementRightId;
	private java.lang.Long employeeId;
	private java.lang.Long roleid;
	private java.lang.Long isCurrent;
	private java.lang.Long groupSide;
	private java.lang.String rolename;
	private java.lang.String fullName;
	private java.lang.String email;
	private java.lang.String userId;
	private java.lang.String name;
	private java.lang.Long type;
	private java.lang.String loginName;
	private java.lang.String contractCode;
	private java.lang.String keySearch;

	public java.lang.String getKeySearch() {
		return keySearch;
	}

	public void setKeySearch(java.lang.String keySearch) {
		this.keySearch = keySearch;
	}

	public java.lang.String getContractCode() {
		return contractCode;
	}

	public void setContractCode(java.lang.String contractCode) {
		this.contractCode = contractCode;
	}

	private java.lang.Long isMornitor;

	private List<RoleCaDTO> listRole = Lists.newArrayList();

	public java.lang.Long getType() {
		return type;
	}

	public void setType(java.lang.Long type) {
		this.type = type;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	@Override
	public SettlementRightBO toModel() {
		SettlementRightBO settlementRightBO = new SettlementRightBO();
		// settlementRightBO.setConstructId(this.constructId);
		settlementRightBO
				.setConstrconstructions(this.constructId == null ? null : new ConstrConstructionsBO(this.constructId));

		settlementRightBO.setSettlementRightId(this.settlementRightId);
		// settlementRightBO.setEmployeeId(this.employeeId);
		settlementRightBO
				.setCatemployee(this.employeeId == null ? null : new CatEmployeeBO(this.employeeId.toString()));
		settlementRightBO.setRoleca(this.roleid == null ? null : new RoleCaBO(this.roleid));
		// settlementRightBO.setRoleid(this.roleid);
		settlementRightBO.setIsCurrent(this.isCurrent);
		return settlementRightBO;
	}

	public java.lang.Long getConstructId() {
		return constructId;
	}

	public void setConstructId(java.lang.Long constructId) {
		this.constructId = constructId;
	}

	@Override
	public Long getFWModelId() {
		return settlementRightId;
	}

	@Override
	public String catchName() {
		return getSettlementRightId().toString();
	}

	public java.lang.Long getSettlementRightId() {
		return settlementRightId;
	}

	public void setSettlementRightId(java.lang.Long settlementRightId) {
		this.settlementRightId = settlementRightId;
	}

	public java.lang.Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(java.lang.Long employeeId) {
		this.employeeId = employeeId;
	}

	public java.lang.Long getRoleid() {
		return roleid;
	}

	public void setRoleid(java.lang.Long roleid) {
		this.roleid = roleid;
	}

	public java.lang.Long getIsCurrent() {
		return isCurrent;
	}

	public void setIsCurrent(java.lang.Long isCurrent) {
		this.isCurrent = isCurrent;
	}

	public java.lang.Long getGroupSide() {
		return groupSide;
	}

	public void setGroupSide(java.lang.Long groupSide) {
		this.groupSide = groupSide;
	}

	public java.lang.String getRolename() {
		return rolename;
	}

	public void setRolename(java.lang.String roleName) {
		this.rolename = roleName;
	}

	public java.lang.String getFullName() {
		return fullName;
	}

	public void setFullName(java.lang.String fullName) {
		this.fullName = fullName;
	}

	public java.lang.String getEmail() {
		return email;
	}

	public void setEmail(java.lang.String email) {
		this.email = email;
	}

	public java.lang.String getUserId() {
		return userId;
	}

	public void setUserId(java.lang.String userId) {
		this.userId = userId;
	}

	public List<RoleCaDTO> getListRole() {
		return listRole;
	}

	public void setListRole(List<RoleCaDTO> listRole) {
		this.listRole = listRole;
	}

	public java.lang.Long getIsMornitor() {
		return isMornitor;
	}

	public void setIsMornitor(java.lang.Long isMornitor) {
		this.isMornitor = isMornitor;
	}

	public java.lang.String getLoginName() {
		return loginName;
	}

	public void setLoginName(java.lang.String loginName) {
		this.loginName = loginName;
	}

}
