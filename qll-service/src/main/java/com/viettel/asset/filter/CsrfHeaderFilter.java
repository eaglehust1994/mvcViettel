package com.viettel.asset.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.util.WebUtils;

public class CsrfHeaderFilter extends VtOnePerRequestFilter{

	@Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
	    CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class
	            .getName());
	    if (csrf != null) {
	    	Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
	    	 String token = csrf.getToken();
	         if (cookie==null || token!=null && !token.equals(cookie.getValue())) {
	           cookie = new Cookie("XSRF-TOKEN", token);
	           cookie.setPath("/");
	           response.addCookie(cookie);
	         }
	    }
	    chain.doFilter(request, response);
	}
	
}
