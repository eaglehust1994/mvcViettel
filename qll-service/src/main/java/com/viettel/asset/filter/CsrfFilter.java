package com.viettel.asset.filter;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.util.WebUtils;



public class CsrfFilter  extends  VtOnePerRequestFilter {
	private DefaultRequiresCsrfMatcher requireCsrfProtectionMatcher = new DefaultRequiresCsrfMatcher();
	private static final String SERVICE_PATH="/service/";
	@Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		String pathInfo = request.getPathInfo();	        	        

        if (pathInfo==null||"/".equals(pathInfo)||pathInfo.toLowerCase().startsWith(SERVICE_PATH)) {
        	chain.doFilter(request, response);
        	
        	return;
        } 
		CsrfToken csrfToken=CsrfTokenRepository.getInstance().loadToken(request);
		//
		  final boolean missingToken = csrfToken == null;
		  if(missingToken){			  
			  csrfToken=CsrfTokenRepository.getInstance().generateToken(request);;
			  CsrfTokenRepository.getInstance().saveToken(request, csrfToken);
		  }
		  request.setAttribute(CsrfToken.class.getName(), csrfToken);
		  request.setAttribute(csrfToken.getParameterName(), csrfToken);
		  
		  
		  
		  /*
		   * Nếu request không nằm trong danh sách bắt check thì pass qua filter
		   */
		  if(!requireCsrfProtectionMatcher.matches(request)) {
			  chain.doFilter(request, response);
			  return;
		  }
		  
		  String actualToken = request.getHeader(csrfToken.getHeaderName());
		  if(actualToken==null){
			  actualToken=request.getParameter(csrfToken.getParameterName());			  
		  }		  
		  if(!csrfToken.getToken().equals(actualToken)){
			  //TODO: handle csrf handle
			  //Kiem tra trong cookie
			/*  Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
			  if(cookie==null||!csrfToken.getToken().equals(cookie.getValue())){
				  if(missingToken){
					  throw new ServletException("missing CSRF Token");
				  }else{
					  throw new ServletException("Invalid Csrf Token, check header: "+csrfToken.getHeaderName()+",paramter:"+csrfToken.getParameterName());
				  }		
			  }*/
			  if(missingToken){
				  throw new ServletException("missing CSRF Token");
			  }else{
				  throw new ServletException("Invalid Csrf Token, check header: "+csrfToken.getHeaderName()+",paramter:"+csrfToken.getParameterName());
			  }	
		  }		  	
		chain.doFilter(request, response);
	}


	
	 private static final class  DefaultRequiresCsrfMatcher {
		 private Pattern allowedMethods = Pattern.compile("^(GET|HEAD|TRACE|OPTIONS)$");
		 
		 
		 public boolean  matches(HttpServletRequest request) {
		    return !allowedMethods.matcher(request.getMethod()).matches();

        }
	 }
}
