/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.google.common.collect.Lists;
import com.viettel.erp.dto.CatEmployeeDTO;
import com.viettel.service.base.model.BaseFWModelImpl;

@Entity(name = "catemployee")
@Table(name = "CAT_EMPLOYEE")
@DynamicInsert
@DynamicUpdate
@SQLDelete(sql = "UPDATE CAT_EMPLOYEE c SET c.IS_ACTIVE = 1 WHERE c.ID = ? ")
//@Where(clause = "IS_ACTIVE = '1'")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL, region = "erp-cache")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */

public class CatEmployeeBO extends BaseFWModelImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5134187485106486953L;
	private List<SettlementRightBO> settlementright = Lists.newArrayList();
	private String id;
	private String code;
	private String fullName;
	private String email;
	private String phoneNumber;
	private java.util.Date birthday;
	private Long groupId;
	private Long isActive;
	private Long positionId;
	private String imagePath;
	private Long creatorId;
	private Long creatorGroupId;
	private Long role;
	private Long enableEmailWarning;
	private String positionName;
	private Long organizationId;
	private String vofficeAccount;
	private Long userId;
	private Long type;
	private String identifyNumber;
	private Long partnerOrgId;

	public CatEmployeeBO() {
		setColId("id");
		setColName("id");
		setUniqueColumn(new String[] { "id" });
	}

	public CatEmployeeBO(String id) {
		this.id = id;
	}

	@Id
	@GenericGenerator(name = "STRING_SEQUENCE_GENERATOR", strategy = "com.viettel.erp.utils.StringSequenceGenerator", parameters = {
			@Parameter(name = "sequence", value = "CAT_EMPLOYEE_SEQ") })
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STRING_SEQUENCE_GENERATOR")
	@Column(name = "ID", length = 44)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "CODE", length = 100)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "FULL_NAME", length = 200)
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Column(name = "EMAIL", length = 200)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "PHONE_NUMBER", length = 200)
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name = "BIRTHDAY", length = 7)
	public java.util.Date getBirthday() {
		return birthday;
	}

	public void setBirthday(java.util.Date birthday) {
		this.birthday = birthday;
	}

	@Column(name = "GROUP_ID", length = 22)
	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	@Column(name = "IS_ACTIVE", length = 22)
	public Long getIsActive() {
		return isActive;
	}

	public void setIsActive(Long isActive) {
		this.isActive = isActive;
	}

	@Column(name = "POSITION_ID", length = 22)
	public Long getPositionId() {
		return positionId;
	}

	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}

	@Column(name = "IMAGE_PATH", length = 1000)
	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	@Column(name = "CREATOR_ID", length = 22)
	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	@Column(name = "CREATOR_GROUP_ID", length = 22)
	public Long getCreatorGroupId() {
		return creatorGroupId;
	}

	public void setCreatorGroupId(Long creatorGroupId) {
		this.creatorGroupId = creatorGroupId;
	}

	@Column(name = "ROLE", length = 22)
	public Long getRole() {
		return role;
	}

	public void setRole(Long role) {
		this.role = role;
	}

	@Column(name = "ENABLE_EMAIL_WARNING", length = 22)
	public Long getEnableEmailWarning() {
		return enableEmailWarning;
	}

	public void setEnableEmailWarning(Long enableEmailWarning) {
		this.enableEmailWarning = enableEmailWarning;
	}

	@Column(name = "POSITION_NAME", length = 200)
	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	@Column(name = "ORGANIZATION_ID", length = 22)
	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	@OneToMany(mappedBy = "catemployee")
	public List<SettlementRightBO> getSettlementright() {
		return settlementright;
	}

	public void setSettlementright(List<SettlementRightBO> settlementright) {
		this.settlementright = settlementright;
	}

	@Override
	public CatEmployeeDTO toDTO() {
		CatEmployeeDTO catEmployeeDTO = new CatEmployeeDTO();
		// set cac gia tri
		catEmployeeDTO.setId(this.id);
		catEmployeeDTO.setCode(this.code);
		catEmployeeDTO.setFullName(this.fullName);
		catEmployeeDTO.setEmail(this.email);
		catEmployeeDTO.setPhoneNumber(this.phoneNumber);
		catEmployeeDTO.setBirthday(this.birthday);
		catEmployeeDTO.setGroupId(this.groupId);
		catEmployeeDTO.setIsActive(this.isActive);
		catEmployeeDTO.setPositionId(this.positionId);
		catEmployeeDTO.setImagePath(this.imagePath);
		catEmployeeDTO.setCreatorId(this.creatorId);
		catEmployeeDTO.setCreatorGroupId(this.creatorGroupId);
		catEmployeeDTO.setRole(this.role);
		catEmployeeDTO.setEnableEmailWarning(this.enableEmailWarning);
		catEmployeeDTO.setPositionName(this.positionName);
		catEmployeeDTO.setOrganizationId(this.organizationId);
		catEmployeeDTO.setVofficeAccount(this.vofficeAccount);
		catEmployeeDTO.setUserId(this.userId);
		catEmployeeDTO.setType(this.type);
		catEmployeeDTO.setIdentifyNumber(this.identifyNumber);
		catEmployeeDTO.setPartnerOrgId(this.partnerOrgId);
		return catEmployeeDTO;
	}

	@Column(name = "VOFFICE_ACCOUNT", length = 200)
	public String getVofficeAccount() {
		return vofficeAccount;
	}

	public void setVofficeAccount(String vofficeAccount) {
		this.vofficeAccount = vofficeAccount;
	}

	@Column(name = "USER_ID", length = 38)
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "TYPE", length = 1)
	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	@Column(name = "IDENTIFY_NUMBER", length = 200)
	public String getIdentifyNumber() {
		return identifyNumber;
	}

	public void setIdentifyNumber(String identifyNumber) {
		this.identifyNumber = identifyNumber;
	}

	@Column(name = "PARTNER_ID", length = 38)
	public Long getPartnerOrgId() {
		return partnerOrgId;
	}

	public void setPartnerOrgId(Long partnerOrgId) {
		this.partnerOrgId = partnerOrgId;
	}

}
