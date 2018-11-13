package com.viettel.ktts2.filter;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashSet;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;


import viettel.passport.util.ModifyHeaderUtils;






public class VpsRestFilter implements Filter {
	 private FilterConfig filterConfig;
	 private static final String SERVICE_PATH="/service/";
	 private static final String[] LIST_SERVICE_PATH={"/service/","/share/","/voffice/"};
		 
	 
	    private static Logger logger = Logger.getLogger(VpsRestFilter.class);
	    private static HashSet<String> casAllowedURL = new HashSet();
	    private static HashSet<String> allMenuURL = new HashSet();
	    private boolean timeoutToErrorPage = false;
	    private boolean ignoreAjaxRequest = false;

	    @Override
	    public void init(FilterConfig config)
	            throws ServletException {
	        try {
	            logger.debug("Lay danh sach AllowUrl tu file config 'cas_en_US.properties'");
	            if (VpsRestConnector.getAllowedUrls() != null) {
	                getCasAllowedURL().addAll(Arrays.asList(VpsRestConnector.getAllowedUrls()));
	            }
	        } catch (Exception ex) {
	            logger.error("Loi lay danh sach AllowUrl tu file config:'cas_en_US.properties'", ex);
	            throw new ExceptionInInitializerError(ex);
	        }
	        try {
	            setFilterConfig(config);
	            logger.info("File Config :" + config);

	            String sTimeoutToErrorPage = this.filterConfig.getInitParameter("timeoutToErrorPage");
	            if ("true".equalsIgnoreCase(sTimeoutToErrorPage)) {
	                this.timeoutToErrorPage = true;
	            } else {
	                this.timeoutToErrorPage = false;
	            }
	            String sIgnoreAjaxRequest = this.filterConfig.getInitParameter("ignoreAjaxRequest");
	            if ("true".equalsIgnoreCase(sIgnoreAjaxRequest)) {
	                this.ignoreAjaxRequest = true;
	            } else {
	                this.ignoreAjaxRequest = false;
	            }
	            //VSAValidate vsa = new VSAValidate();
	            /*ArrayList<ObjectToken> objs = vsa.getAllMenu();
	            for (int i = 0; i < objs.size(); i++) {
	                String temp = ((ObjectToken) objs.get(i)).getObjectUrl().split("\\?")[0];
	                if (temp.startsWith("/")) {
	                    allMenuURL.add(temp);
	                } else {
	                    allMenuURL.add("/" + temp);
	                }
	            }*/
	            //logger.info("All menu URL: " + allMenuURL);
	           /* if (logger.isDebugEnabled()) {
	                logger.debug("All menu URL: " + allMenuURL);
	            }*/
	        } catch (Exception ex) {
	            logger.error("Loi khi lay danh sach tat ca module URL", ex);
	            allMenuURL.clear();
	        }
	    }
	    
	    private boolean isInWhiteListPath(String path){
	    	for(String str:LIST_SERVICE_PATH){
	    		if(path.startsWith(str)){
	    			return true;
	    		}
	    	}
	    	return false;
	    }
	    @Override
	    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	            throws IOException, ServletException {
	        logger.info("VSAFilter process request");
	        HttpServletRequest req = null;
	        HttpServletResponse res = null;
	        if ((request instanceof HttpServletRequest)) {
	            req = (HttpServletRequest) request;
	        }
	        String returnURL = req.getParameter("return");
	        if ((response instanceof HttpServletResponse)) {
	            res = (HttpServletResponse) response;
	        }
	        
	        VpsRestConnector cnn = new VpsRestConnector(req, res);
	        if ((returnURL != null) && (returnURL.trim().length() > 0)) {
	            cnn.returnUrl = returnURL;
	        }
	        req.setAttribute("VSA-IsPassedVSAFilter", "True");
	        /*
	         * Hanhls1 
	         * Nếu pathInfo nằm trong service Path thì thực hiện bỏ filter
	         */
	        String pathInfo = req.getPathInfo();	        	        

	        if (pathInfo==null||"/".equals(pathInfo)||isInWhiteListPath(pathInfo.toLowerCase())) {
	        	chain.doFilter(request, response);
	        	
	        	return;
	        } 
	        logger.info("Path Info:"+pathInfo);
	        
	        String linkFull = req.getRequestURI().contains(req.getServletPath()) ? req.getRequestURI() : req.getRequestURI() + req.getServletPath().substring(1);
	        if ((alowURL(pathInfo, VpsRestConnector.getAllowedUrls()).booleanValue()) || (alowURL(linkFull, VpsRestConnector.getAllowedUrls()).booleanValue())) {
//	        if (true) {
	            logger.info("request in allow urls: " + linkFull);
	            chain.doFilter(req, res);
	        } else {
	            logger.info("request not in allow urls. Check if session authen?");
	            //Chưa authenticate
	            if (!cnn.isAuthenticate().booleanValue()) {
	                logger.info("session is not authen. Check if have ticket in request?");
//	                //Hanhls1 - trường hợp có ticket  kiểm tra xem ticket có validate không -> bằng cách gọi sang VSA
	                if (cnn.hadTicket().booleanValue()) 
	                
	                {
	                	
	                    logger.info("have ticket from passport. Validate this ticket");
	                    //Trường hợp ticket không hợp lệ -> redirect                    
	                    if (!cnn.getAuthenticate().booleanValue()) {
	                    	logger.warn("cnn.getAuthenticateCode():"+cnn.getAuthenticateCode());
	                    	if(2==cnn.getAuthenticateCode()){	                    		
	                    		logger.warn("Redirect to error page by authenticate failure,use had not grant role");
	                    		String serviceUrl = cnn.getServiceURL();
	 	                        String redirectUrl = cnn.getPassportLoginURL() + "?appCode=" + cnn.getDomainCode() + "&service=" + URLEncoder.encode(serviceUrl, "UTF-8");
	 	                        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
	                    		httpServletResponse.setStatus(492);
	 	                        httpServletResponse.setContentType("application/json");
	 	                        httpServletResponse.getWriter().write("{\"data\":\"" + redirectUrl + "\"}");
	 	                        return;
	                    	}else{
	                    		 logger.warn("Redirect to error page by authenticate failure.");
	                    		String serviceUrl = cnn.getServiceURL();
	 	                        String redirectUrl = cnn.getPassportLoginURL() + "?appCode=" + cnn.getDomainCode() + "&service=" + URLEncoder.encode(serviceUrl, "UTF-8");
	 	                        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
	 	                        logger.info("redirect browser to error page: " + redirectUrl);
	 	                        httpServletResponse.setStatus(489);
	 	                        httpServletResponse.setContentType("application/json");
	 	                        httpServletResponse.getWriter().write("{\"data\":\"" + redirectUrl + "\"}");
	 	                        return;
	                    	}
	                       
	                    } else {	                    	//
	                        logger.info("validat successfull. Fw request to next filter");
	                        chain.doFilter(request, response);
	                    }
	                } 
//	                //Hanhls1 - trường hợp không có tịcket -> redirect đến passport
	                else {
	                   logger.info("Request have no ticket. Redirect to passport");
	                    String serviceUrl = cnn.getServiceURL();
//	                    returnURL="http://localhost:8084/erp/client/app/index.html";
	                    if ((returnURL != null) && (returnURL.trim().length() > 0)) {
	                        serviceUrl = serviceUrl + "?return=" + returnURL;
	                    }
	                    String redirectUrl = cnn.getPassportLoginURL() + "?appCode=" + cnn.getDomainCode() + "&service=" + URLEncoder.encode(serviceUrl, "UTF-8");
	                    HttpServletResponse httpServletResponse=(HttpServletResponse)response;
                        httpServletResponse.setStatus(489);
                        httpServletResponse.setContentType("application/json");
                        httpServletResponse.getWriter().write("{\"data\":\""+redirectUrl+"\"}");
                        return ;
	                }
	            } else {
	            
	                logger.info("session already authen. Check session hijacking");
	                HttpSession session = req.getSession();

	                logger.info("get ip,mac stored in session");
	                String macSavedInSession = (String) session.getAttribute("VTS-MAC");
	                logger.info("mac store: " + macSavedInSession);
	                String ipSavedInSession = (String) session.getAttribute("VTS-IP");
	                logger.info("ip lan sotre: " + ipSavedInSession);
	                String ipWanSavedInSession = (String) session.getAttribute("VTS-IPWAN");
	                logger.info("ipwan stored: " + ipWanSavedInSession);

	                logger.info("get ip,mac in request");
	                String mac = req.getHeader("VTS-MAC");
	                String ip = req.getHeader("VTS-IP");
	               // String ipWan = req.getRemoteAddr();
	                try {
	                    logger.info("decode ip");
	                    if ((ip != null) && (ip.length() > 0)) {
	                        ip = ModifyHeaderUtils.parseIP(ip);
	                    } else {
	                        ip = null;
	                    }
	                    logger.info("decode mac");
	                    if ((mac != null) && (mac.length() > 0)) {
	                        mac = ModifyHeaderUtils.parseMAC(mac);
	                    } else {
	                        mac = null;
	                    }
	                } catch (Exception e) {
	                    ip = null;
	                    mac = null;
	                    logger.error("Giai ma modify header that bai " + e.getMessage(), e);
	                }
	                logger.info("ip in request: " + ip);
	                logger.info("mac in request: " + mac);


	                boolean fakeSession = false;
	                /*logger.info("compare ip wan");
	                logger.info(" bo qua compare ip wan vi lb 2 con");	*/
	                if (!fakeSession) {
	                	//Chưa bắt thực hiện allMenu
	                	 logger.info("fw request to next filter");
	                	chain.doFilter(request, response);
	                	
	                	//Thực hiện bắt thêm filter compoent
	                  
	                }
	            }
	        }
	    }

	    public void destroy() {
	    }

	    private Boolean alowURL(String url, String[] listAlowUrl) {
	        for (String str : listAlowUrl) {
	            if (url.equalsIgnoreCase(str)) {
	                return Boolean.valueOf(true);
	            }
	        }
	        return Boolean.valueOf(false);
	    }

	    public static HashSet<String> getAllMenuURL() {
	        return allMenuURL;
	    }

	    public synchronized static void setAllMenuURL(HashSet<String> allMenuURLs) {
	        allMenuURL = allMenuURLs;
	    }

	    public static HashSet<String> getCasAllowedURL() {
	        return casAllowedURL;
	    }

	    public synchronized static void setCasAllowedURL(HashSet<String> casAllowedURLs) {
	        casAllowedURL = casAllowedURLs;
	    }

	   /* private HashSet<String> getVsaAllowedServletPath(HttpServletRequest request) {
	        UserToken vsaUserToken = (UserToken) request.getSession().getAttribute("vsaUserToken");
	        HashSet<String> vsaAllowedURL = new HashSet();
	        for (ObjectToken ot : vsaUserToken.getObjectTokens()) {
	            String servletPath = ot.getObjectUrl();
	            if (!"#".equals(servletPath)) {
	                if (servletPath.startsWith("/")) {
	                    vsaAllowedURL.add(servletPath.split("\\?")[0]);
	                } else {
	                    vsaAllowedURL.add("/" + servletPath.split("\\?")[0]);
	                }
	            }
	        }
	        logger.info("getVsaAllowedServletPath: " + vsaAllowedURL);
	        return vsaAllowedURL;
	    }*/

	    public FilterConfig getFilterConfig() {
	        return this.filterConfig;
	    }

	    public void setFilterConfig(FilterConfig filterConfig) {
	        this.filterConfig = filterConfig;
	    }
	    
	    private boolean hadTicket(HttpServletRequest request ){
	         String st = request.getHeader("ticket");
	        return Boolean.valueOf((st != null) && (st.trim().length() > 0));
	    }
}
