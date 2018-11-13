package com.viettel.asset.rest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.inject.Inject;
//import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.viettel.asset.business.SysGroupBusiness;
import com.viettel.asset.business.SysUserBusiness;
import com.viettel.asset.dto.VSysUserDto;
import com.viettel.asset.filter.session.UserSession;
import com.viettel.ktts2.common.ResponseMessage;
import com.viettel.ktts2.common.UDate;
import com.viettel.wms.business.SysUserQLKBusinessImpl;
import com.viettel.wms.dto.SysUserQLKDTO;

import viettel.passport.client.UserToken;

@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class AssetAuthenRsService extends AbstractRsService {

	@Inject
	private UserSession userSession;
	private static Logger LOGGER = Logger.getLogger(AssetAuthenRsService.class);
	private static Logger LOG_DANG_NHAP = Logger.getLogger("LogDangNhap");

	// @SessionScoped
	// private UserSession userSession2;
	@Autowired
	SysGroupBusiness SysGroupBusiness;

	@Autowired
	SysUserBusiness sysUserBusiness;
	
	@Autowired
	SysUserQLKBusinessImpl sysUserQLKBusinessImpl;

	@Context
	HttpServletRequest request;

	private static ResourceBundle rb;
	private static String logout;
	private static String service;
	private static String domainCode;

	static {
		try {
			rb = ResourceBundle.getBundle("cas");
			logout = rb.getString("logoutUrl");
			service = rb.getString("service");
			domainCode = rb.getString("domainCode");
		} catch (Exception ex) {
			LOGGER.error("Loi khoi tao ung dung", ex);
		}
	}

	@GET
	@Path("/checkLogin/")
	public Response checkLogin() {
		// UserToken vsaUserToken = (UserToken)
		// request.getSession().getAttribute("vsaUserToken");
		// if(userSession.getUserId()==null){
		// userSession.setLoginName(vsaUserToken.getUserName().toUpperCase());
		// userSession.setFullName(vsaUserToken.getFullName());
		// userSession.setVsaUserToken(vsaUserToken);
		//
		// //Lấy thông tin từ db
		// VSysUser userBO =
		// sysUserBusiness.getVSysUserByLoginName(userSession.getLoginName());
		//
		// userSession.setGroupId(userBO.getGroupId());
		// userSession.setGroupName(userBO.getGroupName());
		// userSession.setGroupCode(userBO.getGroupCode());
		// userSession.setUserId(userBO.getUserId());
		// if (userBO.getVofficeName() != null) {
		// userSession.setvOfficeName(userBO.getVofficeName().trim().toUpperCase());
		// }
		// }
		
		return Response.ok(Calendar.getInstance().getTime()).build();
	}

	@GET
	@Path("/loadMenu/")
	public Response loadMenu() {
		UserToken vsaUserToken = (UserToken) request.getSession().getAttribute("vsaUserToken");

		/*
		 * if(userSession.getUserId()==null){
		 * userSession.setLoginName(vsaUserToken.getUserName().toUpperCase());
		 * userSession.setFullName(vsaUserToken.getFullName());
		 * userSession.setVsaUserToken(vsaUserToken);
		 * 
		 * //Lấy thông tin từ db VSysUser userBO =
		 * sysUserBusiness.getVSysUserByLoginName(userSession.getLoginName());
		 * // VSysUserDto
		 * userBO=sysUserBusiness.getVSysUserByLogiNameHcqt(userSession.
		 * getLoginName());
		 * 
		 * 
		 * userSession.setGroupId(userBO.getGroupId());
		 * userSession.setGroupName(userBO.getGroupName());
		 * userSession.setGroupCode(userBO.getGroupCode());
		 * userSession.setUserId(userBO.getUserId()); if
		 * (userBO.getVofficeName() != null) {
		 * userSession.setvOfficeName(userBO.getVofficeName().trim().toUpperCase
		 * ()); }
		 * 
		 * }
		 */
		return Response.ok(vsaUserToken).build();
	}
	
	@GET
	@Path("/login/")
	public Response login(){
		Map<String, Object> map = new HashMap<>();
		UserToken vsaUserToken = (UserToken) request.getSession().getAttribute("vsaUserToken");
		map.put("casUser", vsaUserToken);
		return Response.ok(map).build();
	}

	@GET
	@Path("/getUserInfo/")
	public Response getUserInfo() {
		Map<String, Object> map = new HashMap<>();

		UserToken vsaUserToken = (UserToken) request.getSession().getAttribute("vsaUserToken");
		map.put("casUser", vsaUserToken);
		UserSession userSession2 = (UserSession) request.getSession().getAttribute(USER_SESSION_KEY);

		if (userSession2 == null) {
			userSession2 = new UserSession();
			if (vsaUserToken != null) {
				userSession2.setLoginName(vsaUserToken.getUserName().toUpperCase());
				userSession2.setFullName(vsaUserToken.getFullName());
				userSession2.setVsaUserToken(vsaUserToken);
				userSession2.setDepId(vsaUserToken.getDeptId());
				// Lấy thông tin từ db
				try {
					
					/*ToanBD comment get userinfo by VPS*/
					
					/*VSysUserDto dto = sysUserBusiness.getVSysUserByLogiNameHcqt(userSession2.getLoginName());

					userSession2.setGroupId(dto.getGroupId());
					userSession2.setGroupName(dto.getGroupName());
					userSession2.setGroupCode(dto.getGroupCode());
					userSession2.setUserId(dto.getUserId());
					if (dto.getVofficeName() != null) {
						userSession2.setvOfficeName(dto.getVofficeName().trim().toUpperCase());
					}
					userSession2.setSrvUserInfo(dto);*/

					SysUserQLKDTO dto= sysUserQLKBusinessImpl.getUserInfoByLoginName(vsaUserToken.getUserName());
					userSession2.setUserId(dto.getSysUserId());
					userSession2.setVpsUserInfo(dto);
					
					
				} catch (Exception ex) {
					LOGGER.error("Loi khi truy van database", ex);
				}
				request.getSession().setAttribute(USER_SESSION_KEY, userSession2);
			}

		}
		if (userSession2.getVpsUserInfo() != null) {
			map.put("srvUser", userSession2.getVpsUserInfo());
		}

		userSession=userSession2;
		return Response.ok(map).build();
	}

	@GET
	@Path("/logout/")
	public Response logout() throws UnsupportedEncodingException {

		ResponseMessage rs = new ResponseMessage();
		UserToken vsaUserToken = (UserToken) request.getSession().getAttribute("vsaUserToken");
		String username = vsaUserToken == null ? "" : vsaUserToken.getUserName();
		// build log dang xuat
		StringBuilder sb = new StringBuilder();
		sb.append("Logout|");
		sb.append(UDate.toLogDateFormat(new Date()) + "|");
		sb.append(username + "|");
		sb.append(request.getRemoteAddr() + "|");
		sb.append(request.getPathInfo() + "|");
		sb.append("SUCCESS" + "|");
		sb.append("" + "|");
		LOG_DANG_NHAP.warn(sb.toString());
		request.getSession().invalidate();

		rs.setStatus("499");// logout status

		StringBuilder logoutUrl = new StringBuilder();
		logoutUrl.append(logout).append("?service=" + URLEncoder.encode(service, "UTF-8")).append("&userName=")
				.append(username).append("&appCode=").append(domainCode);
		rs.setData(logoutUrl.toString());
		return Response.status(499).entity(rs).build();
	}
	
	@GET
	@Path("/error/")
	public Response error(){
		ResponseMessage rs = new ResponseMessage();
		rs.setStatus("500");
		return Response.status(500).entity(rs).build();
	}
}
