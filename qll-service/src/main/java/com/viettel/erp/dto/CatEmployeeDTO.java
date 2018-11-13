/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.viettel.erp.bo.CatEmployeeBO;
import com.viettel.erp.utils.CustomJsonDateDeserializer;
import com.viettel.erp.utils.CustomJsonDateSerializer;
import com.viettel.ktts2.common.UEncrypt;
import com.viettel.service.base.dto.BaseFWDTOImpl;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "CAT_EMPLOYEEBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CatEmployeeDTO extends BaseFWDTOImpl<CatEmployeeBO> {

	private java.lang.String id;
	private java.lang.String code;
	private java.lang.String fullName;
	private java.lang.String partnerName;
	private java.lang.Long partnerId;
	private java.lang.String email;
	private java.lang.String phoneNumber;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date birthday;
	private java.lang.Long groupId;
	private java.lang.Long isActive;
	private java.lang.Long positionId;
	private java.lang.String imagePath;
	private java.lang.Long creatorId;
	private java.lang.Long creatorGroupId;
	private java.lang.Long role;
	private java.lang.Long enableEmailWarning;
	private java.lang.String positionName;
	private java.lang.Long organizationId;
	private java.lang.Long type;
	private java.lang.String constrtCode;
	private java.lang.Long userId;
	private java.lang.Long constructId;
	private java.lang.String vofficeAccount;
	private java.lang.String identifyNumber;
	private java.lang.Long partnerOrgId;
	private java.lang.String partnerOrgName;
	private java.lang.String userName;
	private java.lang.String groupName;
	
	private UserEmployeeDTO userEmployee;
	private java.lang.Long ueemployeeId;
	private java.lang.Long ueuserId;
	private UtilAttachedDocumentsDTO utilAttachedDocuments;
	private java.lang.String documentPath;
	private java.lang.Long roleid;
	private List<Long> listPartnerid;
	private List<Long> listGroupId;
	private java.lang.Long take;
	private java.lang.Long page;
	private java.lang.Long pageSize;
	private java.lang.Integer total;
	
	private java.lang.String keySearch;

	public java.lang.String getKeySearch() {
		return keySearch;
	}

	public void setKeySearch(java.lang.String keySearch) {
		this.keySearch = keySearch;
	}

	public java.lang.Long getRoleid() {
		return roleid;
	}

	public void setRoleid(java.lang.Long roleid) {
		this.roleid = roleid;
	}

	public java.lang.String getIdentifyNumber() {
		return identifyNumber;
	}

	public void setIdentifyNumber(java.lang.String identifyNumber) {
		this.identifyNumber = identifyNumber;
	}

	public java.lang.Long getPartnerOrgId() {
		return partnerOrgId;
	}
	private java.lang.Long isCurrent;
	public java.lang.Long getIsCurrent() {
		return isCurrent;
	}

	public void setIsCurrent(java.lang.Long isCurrent) {
		this.isCurrent = isCurrent;
	}

	public void setPartnerOrgId(java.lang.Long partnerOrgId) {
		this.partnerOrgId = partnerOrgId;
	}

	public java.lang.Long getUserId() {
		return userId;
	}

	public void setUserId(java.lang.Long userId) {
		this.userId = userId;
	}

	public java.lang.String getConstrtCode() {
		return constrtCode;
	}

	public void setConstrtCode(java.lang.String constrtCode) {
		this.constrtCode = constrtCode;
	}

	@Override
	public CatEmployeeBO toModel() {
		CatEmployeeBO catEmployeeBO = new CatEmployeeBO();
		catEmployeeBO.setId(this.id);
		catEmployeeBO.setCode(this.code);
		catEmployeeBO.setFullName(this.fullName);
		catEmployeeBO.setEmail(this.email);
		catEmployeeBO.setPhoneNumber(this.phoneNumber);
		catEmployeeBO.setBirthday(this.birthday);
		catEmployeeBO.setGroupId(this.groupId);
		catEmployeeBO.setIsActive(this.isActive);
		catEmployeeBO.setPositionId(this.positionId);
		catEmployeeBO.setImagePath(this.imagePath);
		catEmployeeBO.setCreatorId(this.creatorId);
		catEmployeeBO.setCreatorGroupId(this.creatorGroupId);
		catEmployeeBO.setRole(this.role);
		catEmployeeBO.setEnableEmailWarning(this.enableEmailWarning);
		catEmployeeBO.setPositionName(this.positionName);
		catEmployeeBO.setOrganizationId(this.organizationId);
		catEmployeeBO.setVofficeAccount(this.vofficeAccount);
		catEmployeeBO.setUserId(this.userId);
		catEmployeeBO.setType(this.type);
		catEmployeeBO.setIdentifyNumber(this.identifyNumber);
		catEmployeeBO.setPartnerOrgId(this.partnerOrgId);
		return catEmployeeBO;
	}

	@Override
	public Long getFWModelId() {
		return 0l;
	}

	@Override
	public String catchName() {
		return getId().toString();
	}

	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getCode() {
		return code;
	}

	public void setCode(java.lang.String code) {
		this.code = code;
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

	public java.lang.String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(java.lang.String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public java.util.Date getBirthday() {
		return birthday;
	}

	public void setBirthday(java.util.Date birthday) {
		this.birthday = birthday;
	}

	public java.lang.Long getGroupId() {
		return groupId;
	}

	public void setGroupId(java.lang.Long groupId) {
		this.groupId = groupId;
	}

	public java.lang.Long getIsActive() {
		return isActive;
	}

	public void setIsActive(java.lang.Long isActive) {
		this.isActive = isActive;
	}

	public java.lang.Long getPositionId() {
		return positionId;
	}

	public void setPositionId(java.lang.Long positionId) {
		this.positionId = positionId;
	}

	public java.lang.String getImagePath() {
		return imagePath;
	}

	public void setImagePath(java.lang.String imagePath) {
		this.imagePath = imagePath;
	}

	public java.lang.Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(java.lang.Long creatorId) {
		this.creatorId = creatorId;
	}

	public java.lang.Long getCreatorGroupId() {
		return creatorGroupId;
	}

	public void setCreatorGroupId(java.lang.Long creatorGroupId) {
		this.creatorGroupId = creatorGroupId;
	}

	public java.lang.Long getRole() {
		return role;
	}

	public void setRole(java.lang.Long role) {
		this.role = role;
	}

	public java.lang.Long getEnableEmailWarning() {
		return enableEmailWarning;
	}

	public void setEnableEmailWarning(java.lang.Long enableEmailWarning) {
		this.enableEmailWarning = enableEmailWarning;
	}

	public java.lang.String getPositionName() {
		return positionName;
	}

	public void setPositionName(java.lang.String positionName) {
		this.positionName = positionName;
	}

	public java.lang.Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(java.lang.Long organizationId) {
		this.organizationId = organizationId;
	}

	public java.lang.Long getType() {
		return type;
	}

	public void setType(java.lang.Long type) {
		this.type = type;
	}

	public java.lang.Long getConstructId() {
		return constructId;
	}

	public void setConstructId(java.lang.Long constructId) {
		this.constructId = constructId;
	}

	public java.lang.String getVofficeAccount() {
		return vofficeAccount;
	}

	public void setVofficeAccount(java.lang.String vofficeAccount) {
		this.vofficeAccount = vofficeAccount;
	}

	public java.lang.String getPartnerOrgName() {
		return partnerOrgName;
	}

	public void setPartnerOrgName(java.lang.String partnerOrgName) {
		this.partnerOrgName = partnerOrgName;
	}

	public java.lang.String getUserName() {
		return userName;
	}

	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}

	public UserEmployeeDTO getUserEmployee() {
		return userEmployee;
	}

	public void setUserEmployee(UserEmployeeDTO userEmployee) {
		this.userEmployee = userEmployee;
	}

	public java.lang.Long getUEemployeeId() {
		return ueemployeeId;
	}

	public void setUEemployeeId(java.lang.Long uEemployeeId) {
		ueemployeeId = uEemployeeId;
	}

	public java.lang.Long getUEuserId() {
		return ueuserId;
	}

	public void setUEuserId(java.lang.Long uEuserId) {
		ueuserId = uEuserId;
	}

	public UtilAttachedDocumentsDTO getUtilAttachedDocuments() {
		return utilAttachedDocuments;
	}

	public void setUtilAttachedDocuments(UtilAttachedDocumentsDTO utilAttachedDocuments) {
		this.utilAttachedDocuments = utilAttachedDocuments;
	}

	public java.lang.String getDocumentPath() throws Exception {
		if(documentPath!=null){
			documentPath = UEncrypt.encryptFileUploadPath(documentPath);		
		}	
		return documentPath;
	}

	public void setDocumentPath(java.lang.String documentPath) {
		this.documentPath = documentPath;
	}

	public java.lang.String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(java.lang.String partnerName) {
		this.partnerName = partnerName;
	}

	public java.lang.Long getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(java.lang.Long partnerId) {
		this.partnerId = partnerId;
	}

	public java.lang.String getGroupName() {
		return groupName;
	}

	public void setGroupName(java.lang.String groupName) {
		this.groupName = groupName;
	}

	public List<Long> getListPartnerid() {
		return listPartnerid;
	}

	public void setListPartnerid(List<Long> listPartnerid) {
		this.listPartnerid = listPartnerid;
	}

	public List<Long> getListGroupId() {
		return listGroupId;
	}

	public void setListGroupId(List<Long> listGroupId) {
		this.listGroupId = listGroupId;
	}
	
	public java.lang.Long getTake() {
		return take;
	}

	public void setTake(java.lang.Long take) {
		this.take = take;
	}

	public java.lang.Long getPage() {
		return page;
	}

	public void setPage(java.lang.Long page) {
		this.page = page;
	}

	public java.lang.Long getPageSize() {
		return pageSize;
	}

	public void setPageSize(java.lang.Long pageSize) {
		this.pageSize = pageSize;
	}

	public java.lang.Integer getTotal() {
		return total;
	}

	public void setTotal(java.lang.Integer total) {
		this.total = total;
	}

}
