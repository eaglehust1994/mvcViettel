package com.viettel.ktts2.filter;

import java.io.IOException;
import java.util.Date;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.viettel.ktts2.common.UDate;
import com.viettel.qll.business.LichSuDangNhapBusinessImpl;
import com.viettel.qll.dto.LichSuDangNhapDTO;
import com.viettel.qll.dto.VpsPermissionChecker;
import com.viettel.qll.dto.VpsServiceTicketValidator;
//import com.viettel.erp.webservice.UserTokenDTO;
//import com.viettel.erp.webservice.UsersWS;
import com.viettel.qll.dto.VpsUserToken;

import viettel.passport.client.UserToken;
import viettel.passport.util.ModifyHeaderUtils;

public class VpsRestConnector {
	 private HttpServletRequest request;
	    private HttpServletResponse response;
	    private String ticket;
	    private static String passportLoginURL;
	    private static String serviceURL;
	    private static String domainCode;
	    private static String passportValidateURL;
	    private static String errorUrl;
	    private static String[] allowedUrls;
	    private static final String FILE_URL = "cas";
	    private static ResourceBundle rb;
	    private static boolean modifyHeader = false;
	    public String returnUrl;
	    // add service VSA url
	   // private static String serviceVsaURL;
	    //vps webservice url
	    private static String serviceVpsUrl;
	    private static Logger LOG = Logger.getLogger(VpsRestConnector.class);
	    private static Logger LOG_DANG_NHAP=Logger.getLogger("LogDangNhap");
	    
	    @Autowired
	    LichSuDangNhapBusinessImpl dangNhapBusinessImpl;
	    
	    LichSuDangNhapDTO dangNhapDTO=new LichSuDangNhapDTO();
	    
	    private int authenticateCode;

	    public int getAuthenticateCode() {
			return authenticateCode;
		}

		public void setAuthenticateCode(int authenticateCode) {
			this.authenticateCode = authenticateCode;
		}

		static {
	        try {
	            rb = ResourceBundle.getBundle("cas");
	            passportLoginURL = rb.getString("loginUrl");
	            serviceURL = rb.getString("service");
	            domainCode = rb.getString("domainCode");
	            passportValidateURL = rb.getString("validateUrl");
	            errorUrl = rb.getString("errorUrl");
	            modifyHeader = "true".equalsIgnoreCase(rb.getString("useModifyHeader"));
	            serviceVpsUrl = rb.getString("vpsServiceUrl");
	            String allowedUrlStr=rb.getString("AllowUrl");
	            if(allowedUrlStr!=null){
	            	allowedUrls = allowedUrlStr.split(",");
	            }
	           
	        } catch (MissingResourceException e) {
	            LOG.error(e.getMessage(), e);
	        }
	    }

	    public VpsRestConnector(HttpServletRequest req, HttpServletResponse res) {
	        this.request = req;
	        this.response = res;
	    }

	    public Boolean isAuthenticate() {	  
	        return Boolean.valueOf((this.request != null) && (this.request.getSession() != null) && (this.request.getSession().getAttribute("vpsUserToken") != null));
	    }

	    public Boolean hadTicket() {
	        String st = getTicketFromRequest();
	        return Boolean.valueOf((st != null) && (st.trim().length() > 0));
	    }
	    
	    private String getTicketFromRequest(){
	    	String referer=(String)this.request.getHeader("referer");//check refer
	    	if(referer!=null){
		    	int indexRefferr=referer.lastIndexOf("ticket");
		    	if(indexRefferr>=0){
		    		return referer.substring(indexRefferr+7);
		    	}
	    	}
	        return this.request.getHeader("ticket");
	    }

	    public Boolean getAuthenticate()
	            throws IOException {
	        try {
	            String tmpTicket =getTicketFromRequest();

	            String ip = this.request.getHeader("VTS-IP");
	            String ipwan = this.request.getRemoteAddr();
	            String mac = this.request.getHeader("VTS-MAC");
	            try {
	                if ((ip != null) && (ip.length() > 0)) {
	                    ip = ModifyHeaderUtils.parseIP(ip);
	                } else {
	                    ip = null;
	                }
	                if ((mac != null) && (mac.length() > 0)) {
	                    mac = ModifyHeaderUtils.parseMAC(mac);
	                } else {
	                    mac = null;
	                }
	            } catch (Exception e) {
	                ip = null;
	                mac = null;
	                LOG.error("Giai ma modify header that bai " + e.getMessage(), e);
	            }
	            if ((tmpTicket == null) || (tmpTicket.trim().length() == 0)) {
	                return Boolean.valueOf(false);
	            }
	            VpsServiceTicketValidator stValidator = new VpsServiceTicketValidator();
	            stValidator.setCasValidateUrl(passportValidateURL);
	            stValidator.setServiceTicket(tmpTicket);	            
	            stValidator.setIpWan(ipwan);
	            
	            String cookie_temp = this.request.getParameter("lb");
	            
	            LOG.info(">>>>> lb:" + cookie_temp + " + ticket_lb: " + tmpTicket);

	            
	            if ((this.returnUrl != null) && (this.returnUrl.trim().length() > 0)) {
	                stValidator.setService(serviceURL + "?return=" + this.returnUrl);
	            } else {
	                stValidator.setService(serviceURL);
	            }
	            stValidator.setDomainCode(domainCode);
	            stValidator.validate();

	            HttpSession session = this.request.getSession();
	            session.invalidate();
	            session = this.request.getSession(true);
	 
	 
	            authenticateCode=stValidator.getAuthenticateCode();
	            if (!stValidator.isAuthenticationSuccesful()) {
	                session.setAttribute("vsaUserToken", null);
	                session.setAttribute("netID", null);
	                
	                //Build log dang nhap fail
	                StringBuilder sb=new StringBuilder();
		            sb.append("Login|");
		            sb.append(UDate.toLogDateFormat(new Date())+"|");
		            sb.append("|");
		            sb.append(ip+"|");
		            sb.append(request.getPathInfo()+"|");
		            sb.append("FAIL"+"|");
		            sb.append(""+"|");
		            LOG_DANG_NHAP.warn(sb.toString());
		            //end log dang nhap fail
	                return false;
	            }
	            UserToken usr = stValidator.getUserToken();
	            VpsUserToken vpsUserToken=stValidator.getVpsUserToken();

	            session.setAttribute("vsaUserToken", usr);
	            session.setAttribute("vpsUserToken", vpsUserToken);
//	            vpsUserToken.getAuthorizedData().setUser(usr);
	            //Đẩy thông tin phiên người dùng vào
	            VpsPermissionChecker.putAuthorizedToSession(session, vpsUserToken);
	            //Ghi log dang nhap
	            StringBuilder sb=new StringBuilder();
	            sb.append("Login|");
	            sb.append(UDate.toLogDateFormat(new Date())+"|");
	            sb.append(usr.getUserName()+"|");
	            sb.append(request.getRemoteAddr()+"|");
	            sb.append(request.getPathInfo()+"|");
	            sb.append("SUCCESS"+"|");
	            sb.append(""+"|");
	            LOG_DANG_NHAP.warn(sb.toString());
	            
//	            dangNhapDTO.setGioDangNhap(new Date());
//	            dangNhapDTO.setIpDangNhap(ip);
//	            dangNhapDTO.setUserCode(usr.getUserName());
//	            dangNhapDTO.setTrangThai("Đăng nhập thành công");
//	            dangNhapBusinessImpl.insertLSDN(dangNhapDTO);
//	            LOG_DANG_NHAP.warn("Lưu lịch sử đăng nhập thành công!!!");
	            //endlog dang nhap
	            session.setAttribute("netID", stValidator.getUser());
	            session.setAttribute("VTS-IP", ip);
	            session.setAttribute("VTS-MAC", mac);
	          //  CacheManagement.getInstance().putCache(session.getId(),session);
	            if (ipwan == null) {
	                LOG.error("IP WAN get from request is NULL!!!");
	                System.out.println("IP WAN get from request is NULL!!!");
	            } else {
	                LOG.info("IP WAN is: " + ipwan);
	                System.out.println("IP WAN is: " + ipwan);
	            }
	            session.setAttribute("VTS-IPWAN", ipwan);
	            if ((this.returnUrl != null) && (this.returnUrl.trim().length() > 0)) {
	                session.setAttribute("return_url", this.returnUrl);
	            }
	            if ((modifyHeader) || (ip != null) || (mac != null)) {
	                LOG.info(String.format("User %s logined at ip %s and mac %s ipwan %s session %s - %s modifyHeader", new Object[]{stValidator.getUser(), ip, mac, ipwan, session.getId(), modifyHeader ? "with" : "without"}));
	            } else {
	                LOG.info(String.format("User %s logined at ipwan %s without modifyHeader", new Object[]{stValidator.getUser(), ipwan}));
	            }
	            return Boolean.valueOf(true);
	        } catch (ParserConfigurationException e) {
	            LOG.error(e.getMessage(), e);
	        }
	        return Boolean.valueOf(false);
	    }

	    public String getPassportLoginURL() {
	        return passportLoginURL;
	    }

	    public synchronized static void setPassportLoginURL(String passportLoginURLs) {
	        passportLoginURL = passportLoginURLs;
	    }

	    public String getServiceURL() {
	        return serviceURL;
	    }

	    public synchronized static void setServiceURL(String serviceURLs) {
	        serviceURL = serviceURLs;
	    }

	    public String getDomainCode() {
	        return domainCode;
	    }

	    public synchronized static void setDomainCode(String domainCodes) {
	        domainCode = domainCodes;
	    }

	    public String getPassportValidateURL() {
	        return passportValidateURL;
	    }

	    public synchronized static void setPassportValidateURL(String passportValidateURLs) {
	        passportValidateURL = passportValidateURLs;
	    }

	    public String getTicket() {
	        return this.ticket;
	    }

	    public void setTicket(String tickets) {
	        this.ticket = tickets;
	    }

	    public static String getErrorUrl() {
	        return errorUrl;
	    }

	    public synchronized static void setErrorUrl(String errorUrls) {
	        errorUrl = errorUrls;
	    }

	    public static boolean isModifyHeader() {
	        return modifyHeader;
	    }

	    public synchronized static void setModifyHeader(boolean usemodifyHeaders) {
	        modifyHeader = usemodifyHeaders;
	    }

	    public synchronized static void setAllowedUrls(String[] strs) {
	        allowedUrls = new String[strs.length];
	        System.arraycopy(strs, 0, allowedUrls, 0, strs.length);
	    }

	    public static String[] getAllowedUrls() {
	    	if(allowedUrls!=null){
		        String[] tmps = new String[allowedUrls.length];
		        System.arraycopy(allowedUrls, 0, tmps, 0, allowedUrls.length);
		        return tmps;
	    	}
	        return new String[0];
	    }

//	    private UserTokenDTO getUserDTO(String userName) {
//	        UserTokenDTO usrDto = null;
//	        try {
//	            UsersWS usrWS = CxfWsClientFactory.createWsClient(UsersWS.class);
//	            usrDto = usrWS.getUserToken(userName, domainCode);
//	        } catch (Exception ex) {
//	            LOG.error(ex);
//	        }
//	        return usrDto;
//	    }

	  /*  private ArrayList<RoleToken> getAllRoles(List<RolesDTO> lsObj) {
	        ArrayList<RoleToken> ls = new ArrayList<RoleToken>();
	        try {
	            if (lsObj != null && !lsObj.isEmpty()) {
	                for (RolesDTO obj : lsObj) {
	                    ls.add(convertToRoleToken(obj));
	                }
	                //
	            }
	        } catch (Exception ex) {
	            LOG.error(ex);
	        }
	        return ls;
	    }*/
/*
	    private ArrayList<ObjectToken> getAllMenu(List<ObjectsDTO> lsObj) {
	        ArrayList<ObjectToken> ls = new ArrayList<ObjectToken>();
	        try {
	            if (lsObj != null && !lsObj.isEmpty()) {
	                for (ObjectsDTO obj : lsObj) {
	                    if (obj.getParentId() == null) {
	                        ObjectToken objT = convertToObjectToken(obj);
	                        objT.setChildObjects(fillRecursive(lsObj, objT.getObjectId()));
	                        ls.add(objT);
	                    }
	                }
	                //
	            }
	        } catch (Exception ex) {
	            LOG.error(ex);
	        }
	        return ls;
	    }*/

	   /* private static ArrayList<ObjectToken> fillRecursive(List<ObjectsDTO> flatObjects, Long parentId) {
	        ArrayList<ObjectToken> recursiveObjects = new ArrayList<ObjectToken>();
	        for (ObjectsDTO dto : flatObjects) {
	            if (dto.getParentId() != null && parentId.equals(dto.getParentId())) {
	                ObjectToken obj = convertToObjectToken(dto);
	                obj.setChildObjects(fillRecursive(flatObjects, obj.getObjectId()));
	                recursiveObjects.add(convertToObjectToken(dto));
	            }
	        }
	        return recursiveObjects;
	    }*/

	   /* private static ObjectToken convertToObjectToken(ObjectsDTO dto) {
	        ObjectToken obj = new ObjectToken();
	        obj.setObjectCode(dto.getObjectCode());
	        obj.setDescription(dto.getDescription());
	        obj.setObjectId(dto.getObjectId());
	        obj.setObjectName(dto.getObjectName());
	        obj.setObjectType(dto.getObjectTypeId() != null ? dto.getObjectTypeId() : -1l);
	        obj.setObjectUrl(dto.getObjectUrl());
	        obj.setParentId(dto.getParentId() != null ? dto.getParentId() : -1l);
	        obj.setOrd(dto.getOrd());

	        return obj;
	    }

	    private static RoleToken convertToRoleToken(RolesDTO dto) {
	        RoleToken obj = new RoleToken();
	        obj.setDescription(dto.getDescription());
	        obj.setRoleCode(dto.getRoleCode());
	        obj.setRoleName(dto.getRoleName());

	        return obj;
	    }*/
}
