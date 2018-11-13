package com.viettel.asset.dto;

import java.util.Date;
import com.viettel.asset.bo.SysGroup;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@XmlRootElement(name = "sysGroup")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysGroupDto {
	
	
		private Long groupId;
		private String name;
		private String description;
		private Long parentId;
		private String path;
		private Long status;
		private String groupCode;
		private String fullName;
		private Long permissionAll;
		private String titleName;
		private String address;
//		private String maTinh;
		private Long theLevel;
		private Long theOrder;
		private Long provinceId;
		private Long catEmployeeId;
		private Long catEmployeeNetWId;
		private Long isOff;
		private Long orderExpTree;
		private Long lockStatus;
		private Date changeDate;
		private Long organizationId;
	
	public SysGroupDto() {
		
	}
	
	public SysGroupDto(SysGroup entity) {
		this.groupId = entity.getGroupId();
		this.name = entity.getName();
		this.description = entity.getDescription();
		this.parentId = entity.getParentId();
		this.path = entity.getPath();
		this.status = entity.getStatus();
		this.groupCode = entity.getGroupCode();
		this.fullName = entity.getFullName();
		this.permissionAll = entity.getPermissionAll();
		this.titleName = entity.getTitleName();
		this.address = entity.getAddress();
//		this.maTinh = entity.getMaTinh();
		this.theLevel = entity.getTheLevel();
		this.theOrder = entity.getTheOrder();
		this.provinceId = entity.getProvinceId();
		this.catEmployeeId = entity.getCatEmployeeId();
		this.catEmployeeNetWId = entity.getCatEmployeeNetWId();
		this.isOff = entity.getIsOff();
		this.orderExpTree = entity.getOrderExpTree();
		this.lockStatus = entity.getLockStatus();
		this.changeDate = entity.getChangeDate();
		this.organizationId = entity.getOrganizationId();
	}
	
	public SysGroup toEntity() {
		SysGroup entity = new SysGroup();
		entity.setGroupId(this.groupId);
		entity.setName(this.name);
		entity.setDescription(this.description);
		entity.setParentId(this.parentId);
		entity.setPath(this.path);
		entity.setStatus(this.status);
		entity.setGroupCode(this.groupCode);
		entity.setFullName(this.fullName);
		entity.setPermissionAll(this.permissionAll);
		entity.setTitleName(this.titleName);
		entity.setAddress(this.address);
//		entity.setMaTinh(this.maTinh);
		entity.setTheLevel(this.theLevel);
		entity.setTheOrder(this.theOrder);
		entity.setProvinceId(this.provinceId);
		entity.setCatEmployeeId(this.catEmployeeId);
		entity.setCatEmployeeNetWId(this.catEmployeeNetWId);
		entity.setIsOff(this.isOff);
		entity.setOrderExpTree(this.orderExpTree);
		entity.setLockStatus(this.lockStatus);
		entity.setChangeDate(this.changeDate);
		entity.setOrganizationId(this.organizationId);
		return entity;
	}
	
	public void updateEntity(SysGroup entity) {
			entity.setName(this.name);
			entity.setDescription(this.description);
			entity.setParentId(this.parentId);
			entity.setPath(this.path);
			entity.setStatus(this.status);
			entity.setGroupCode(this.groupCode);
			entity.setFullName(this.fullName);
			entity.setPermissionAll(this.permissionAll);
			entity.setTitleName(this.titleName);
			entity.setAddress(this.address);
//			entity.setMaTinh(this.maTinh);
			entity.setTheLevel(this.theLevel);
			entity.setTheOrder(this.theOrder);
			entity.setProvinceId(this.provinceId);
			entity.setCatEmployeeId(this.catEmployeeId);
			entity.setCatEmployeeNetWId(this.catEmployeeNetWId);
			entity.setIsOff(this.isOff);
			entity.setOrderExpTree(this.orderExpTree);
			entity.setLockStatus(this.lockStatus);
			entity.setChangeDate(this.changeDate);
			entity.setOrganizationId(this.organizationId);
	}
	
		public Long getGroupId() {
			return groupId;
		}
		public void setGroupId(Long groupId) {
			this.groupId = groupId;
		}		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}		
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}		
		public Long getParentId() {
			return parentId;
		}
		public void setParentId(Long parentId) {
			this.parentId = parentId;
		}		
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}		
		public Long getStatus() {
			return status;
		}
		public void setStatus(Long status) {
			this.status = status;
		}		
		public String getGroupCode() {
			return groupCode;
		}
		public void setGroupCode(String groupCode) {
			this.groupCode = groupCode;
		}		
		public String getFullName() {
			return fullName;
		}
		public void setFullName(String fullName) {
			this.fullName = fullName;
		}		
		public Long getPermissionAll() {
			return permissionAll;
		}
		public void setPermissionAll(Long permissionAll) {
			this.permissionAll = permissionAll;
		}		
		public String getTitleName() {
			return titleName;
		}
		public void setTitleName(String titleName) {
			this.titleName = titleName;
		}		
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}		
//		public String getMaTinh() {
//			return maTinh;
//		}
//		public void setMaTinh(String maTinh) {
//			this.maTinh = maTinh;
//		}		
		public Long getTheLevel() {
			return theLevel;
		}
		public void setTheLevel(Long theLevel) {
			this.theLevel = theLevel;
		}		
		public Long getTheOrder() {
			return theOrder;
		}
		public void setTheOrder(Long theOrder) {
			this.theOrder = theOrder;
		}		
		public Long getProvinceId() {
			return provinceId;
		}
		public void setProvinceId(Long provinceId) {
			this.provinceId = provinceId;
		}		
		public Long getCatEmployeeId() {
			return catEmployeeId;
		}
		public void setCatEmployeeId(Long catEmployeeId) {
			this.catEmployeeId = catEmployeeId;
		}		
		public Long getCatEmployeeNetWId() {
			return catEmployeeNetWId;
		}
		public void setCatEmployeeNetWId(Long catEmployeeNetWId) {
			this.catEmployeeNetWId = catEmployeeNetWId;
		}		
		public Long getIsOff() {
			return isOff;
		}
		public void setIsOff(Long isOff) {
			this.isOff = isOff;
		}		
		public Long getOrderExpTree() {
			return orderExpTree;
		}
		public void setOrderExpTree(Long orderExpTree) {
			this.orderExpTree = orderExpTree;
		}		
		public Long getLockStatus() {
			return lockStatus;
		}
		public void setLockStatus(Long lockStatus) {
			this.lockStatus = lockStatus;
		}		
		public Date getChangeDate() {
			return changeDate;
		}
		public void setChangeDate(Date changeDate) {
			this.changeDate = changeDate;
		}		
		public Long getOrganizationId() {
			return organizationId;
		}
		public void setOrganizationId(Long organizationId) {
			this.organizationId = organizationId;
		}		
	
}
