package com.viettel.asset.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class VtOnePerRequestFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	public static final String ALREADY_FILTERED_SUFFIX = ".VT.FILTERED";
	
	protected String  getAlreadyFilteredAttributeName() {			
		return getClass().getName() + ALREADY_FILTERED_SUFFIX;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		String alreadyFilteredAttributeName = getAlreadyFilteredAttributeName();
		
		
		boolean hasAlreadyFilteredAttribute = request.getAttribute(alreadyFilteredAttributeName) != null;
		if (hasAlreadyFilteredAttribute){
			chain.doFilter(request, response);
			
		}else{
			request.setAttribute(alreadyFilteredAttributeName, Boolean.TRUE);
			try{
				doFilterInternal((HttpServletRequest)request,(HttpServletResponse)response,chain);
			}finally{
				request.removeAttribute(alreadyFilteredAttributeName);
			}
		}
		
	}
	public abstract void doFilterInternal(HttpServletRequest request, HttpServletResponse response,FilterChain chain) throws IOException, ServletException;
		

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
