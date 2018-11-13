package com.viettel.wms.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.model.BaseFWModelImpl;
import com.viettel.wms.dto.SignVofficeDetailDTO;
@Entity
@Table(name = "WMS_OWNER_KTTS.SIGN_VOFFICE_DETAIL")


public class SignVofficeDetailBO extends BaseFWModelImpl {

	@Id
	@GeneratedValue(generator = "sequence")
	    @GenericGenerator(name = "sequence", strategy = "sequence",
	            parameters = {
	                @Parameter(name = "sequence", value = "WMS_OWNER_KTTS.SIGN_VOFFICE_DETAIL_SEQ")
	            }
	    )
	@Column(name = "SIGN_VOFFICE_DETAIL_ID", length = 22)
	private java.lang.Long signVofficeDetailId;
	@Column(name = "ODRER", length = 22)
	private java.lang.Long odrer;
	@Column(name = "ODRER_NAME", length = 100)
	private java.lang.String odrerName;
	@Column(name = "ROLE_ID", length = 22)
	private java.lang.Long roleId;
	@Column(name = "ROLE_NAME", length = 100)
	private java.lang.String roleName;
	@Column(name = "SIGN_VOFFICE_ID", length = 22)
	private java.lang.Long signVofficeId;
	@Column(name = "SYS_USER_ID", length = 22)
	private java.lang.Long sysUserId;
	
	 public SignVofficeDetailBO() {
	        setColId("signVofficeDetailId");
	        setColName("signVofficeDetailId");
	        setUniqueColumn(new String[]{"signVofficeDetailId"});
	}
	 
	 public java.lang.Long getSignVofficeDetailId() {
			return signVofficeDetailId;
		}
		public void setSignVofficeDetailId(java.lang.Long signVofficeDetailId) {
			this.signVofficeDetailId = signVofficeDetailId;
		}
		public java.lang.Long getOdrer() {
			return odrer;
		}
		public void setOdrer(java.lang.Long odrer) {
			this.odrer = odrer;
		}
		public java.lang.String getOdrerName() {
			return odrerName;
		}
		public void setOdrerName(java.lang.String odrerName) {
			this.odrerName = odrerName;
		}
		public java.lang.Long getRoleId() {
			return roleId;
		}
		public void setRoleId(java.lang.Long roleId) {
			this.roleId = roleId;
		}
		public java.lang.String getRoleName() {
			return roleName;
		}
		public void setRoleName(java.lang.String roleName) {
			this.roleName = roleName;
		}
		public java.lang.Long getSignVofficeId() {
			return signVofficeId;
		}
		public void setSignVofficeId(java.lang.Long signVofficeId) {
			this.signVofficeId = signVofficeId;
		}
		public java.lang.Long getSysUserId() {
			return sysUserId;
		}
		public void setSysUserId(java.lang.Long sysUserId) {
			this.sysUserId = sysUserId;
		}
		
	@Override
	public BaseFWDTOImpl toDTO() {
		SignVofficeDetailDTO signVofficeDetailDTO=new SignVofficeDetailDTO();
		signVofficeDetailDTO.setSignVofficeDetailId(this.signVofficeDetailId);
		signVofficeDetailDTO.setOdrer(this.odrer);
		signVofficeDetailDTO.setOdrerName(this.odrerName);
		signVofficeDetailDTO.setRoleId(this.roleId);
		signVofficeDetailDTO.setRoleName(this.roleName);
		signVofficeDetailDTO.setSignVofficeId(this.signVofficeId);
		signVofficeDetailDTO.setSysUserId(this.sysUserId);
		
		return signVofficeDetailDTO;
		
	}

}
