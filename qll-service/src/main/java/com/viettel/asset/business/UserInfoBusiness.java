package com.viettel.asset.business;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.springframework.stereotype.Service;

import com.viettel.asset.filter.session.UserSession;
import com.viettel.ktts2.common.BusinessException;

@Service
public class UserInfoBusiness {
	@Context HttpServletRequest request;
	protected static final String USER_SESSION_KEY="USER_SESSION_KEY";
	public UserSession getUserSession(){
		UserSession s=(UserSession)request.getSession().getAttribute(USER_SESSION_KEY);
		if(s==null){
			throw new BusinessException("user is not authen");
		}
		return s;
		
	}
}
