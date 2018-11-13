package com.viettel.asset.business;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import com.viettel.asset.filter.session.UserSession;
import com.viettel.ktts2.common.BusinessException;

public class BaseBussiness {
	@Context
	HttpServletRequest request;
	public UserSession getUserSession(){
		UserSession useSession=(UserSession)request.getSession().getAttribute("USER_SESSION_KEY");
		if(useSession==null){
			throw new BusinessException("user is not authen");
		}
		return useSession;
	}
}
