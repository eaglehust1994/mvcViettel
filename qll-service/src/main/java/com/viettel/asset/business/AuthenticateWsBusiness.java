package com.viettel.asset.business;

import org.springframework.stereotype.Service;

import com.viettel.asset.dto.AuthenticationInfo;
import com.viettel.asset.dto.BaseWsRequest;

@Service
public class AuthenticateWsBusiness {

	private String username = "ayfgrogsgjdsghrefpughsgrgwesfsgrs";
	private String password = "fdijfgfeughslvfbltgjhspno[hbgheg";
	private String version = "1.0";
	
	public void validateRequest(BaseWsRequest request) throws Exception {
		AuthenticationInfo authenticationInfo = request.getAuthenticationInfo();
		if (authenticationInfo == null
				|| !this.username.equals(authenticationInfo.getUsername())
				|| !this.password.equals(authenticationInfo.getPassword())
				|| !this.version.equals(authenticationInfo.getVersion())) {
			throw new Exception("Thông tin xác thực không hợp lệ");
		}
		
	}

	
}
