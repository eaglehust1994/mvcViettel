package com.viettel.asset.filter.session;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.viettel.asset.dto.VSysUserDto;
import com.viettel.wms.dto.SysUserQLKDTO;

import viettel.passport.client.UserToken;


@Component
@Scope(value = "session",proxyMode=ScopedProxyMode.TARGET_CLASS)
//@Scope("session")
public class UserSession implements Serializable{
	 
		private static final long serialVersionUID = 7415008935248658355L;
	    
		
	    private Long groupId;
	    private String groupName;
	    private String groupCode;
	    private String vOfficeName;
	    private String staffCode;
	    private String loginName;
	    private String fullName;
	    private Long depId;
	    public Long getDepId() {
			return depId;
		}

		public void setDepId(Long depId) {
			this.depId = depId;
		}

		private Long userId;
	    private UserToken vsaUserToken;
	    
	    private VSysUserDto srvUserInfo;
	    private SysUserQLKDTO vpsUserInfo;
	    public SysUserQLKDTO getVpsUserInfo() {
			return vpsUserInfo;
		}

		public void setVpsUserInfo(SysUserQLKDTO vpsUserInfo) {
			this.vpsUserInfo = vpsUserInfo;
		}

		public VSysUserDto getSrvUserInfo() {
			return srvUserInfo;
		}

		public void setSrvUserInfo(VSysUserDto srvUserInfo) {
			this.srvUserInfo = srvUserInfo;
		}

		public UserToken getVsaUserToken() {
			return vsaUserToken;
		}

		public void setVsaUserToken(UserToken vsaUserToken) {
			this.vsaUserToken = vsaUserToken;
		}

		public Long getUserId() {
	        return userId;
	    }

	    public void setUserId(Long userId) {
	        this.userId = userId;
	    }

	    public Long getGroupId() {
	        return groupId;
	    }

	    public void setGroupId(Long groupId) {
	        this.groupId = groupId;
	    }

	    public String getGroupName() {
	        return groupName;
	    }

	    public void setGroupName(String groupName) {
	        this.groupName = groupName;
	    }

	    public String getGroupCode() {
	        return groupCode;
	    }

	    public void setGroupCode(String groupCode) {
	        this.groupCode = groupCode;
	    }

	    public String getvOfficeName() {
	        return vOfficeName;
	    }

	    public void setvOfficeName(String vOfficeName) {
	        this.vOfficeName = vOfficeName;
	    }

	    public String getStaffCode() {
	        return staffCode;
	    }

	    public void setStaffCode(String staffCode) {
	        this.staffCode = staffCode;
	    }

	    public String getLoginName() {
	        return loginName;
	    }

	    public void setLoginName(String loginName) {
	        this.loginName = loginName;
	    }

	    public String getFullName() {
	        return fullName;
	    }

	    public void setFullName(String fullName) {
	        this.fullName = fullName;
	    }
	    
	    
	    
	    

}
