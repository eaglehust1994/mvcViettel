package com.viettel.asset.dto;

public class BaseWsRequest {

	private AuthenticationInfo authenticationInfo;
	
	public AuthenticationInfo getAuthenticationInfo() {
		return authenticationInfo;
	}
	public void setAuthenticationInfo(AuthenticationInfo authenticationInfo) {
		this.authenticationInfo = authenticationInfo;
	}
}
