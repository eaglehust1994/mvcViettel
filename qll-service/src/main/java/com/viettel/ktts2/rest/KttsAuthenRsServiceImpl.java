package com.viettel.ktts2.rest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.viettel.ktts.vps.IUserToken;
import com.viettel.ktts2.business.AuthenticateBusiness;
import com.viettel.ktts2.business.CommonSysGroupBusiness;
import com.viettel.ktts2.common.ResponseMessage;
import com.viettel.ktts2.common.UDate;
import com.viettel.ktts2.dto.KttsSysUserDto;
import com.viettel.ktts2.dto.KttsUserSession;
import com.viettel.wms.business.SysUserQLKBusinessImpl;
import com.viettel.wms.dto.SysUserQLKDTO;

import viettel.passport.client.UserToken;

public class KttsAuthenRsServiceImpl implements KttsAuthenRsService {
	private static Logger LOGGER = Logger.getLogger(KttsAuthenRsServiceImpl.class);
	private static Logger LOG_DANG_NHAP = Logger.getLogger("LogDangNhap");
	@Context
	HttpServletRequest request;
	private static final String VPS_USER_TOKEN_KEY = "vpsUserToken";
	@Autowired
	CommonSysGroupBusiness commonSysGroupBusiness;
	@Autowired
	AuthenticateBusiness authenticateBusiness;
	@Autowired
	SysUserQLKBusinessImpl sysUserQLKBusinessImpl;

	private static final String USER_SESSION_KEY = "kttsUserSession";

	private static String logout;
	private static String service;
	private static String domainCode;

	static {
		try {
			ResourceBundle rb = ResourceBundle.getBundle("cas");
			logout = rb.getString("logoutUrl");
			service = rb.getString("service");
			domainCode = rb.getString("domainCode");
		} catch (Exception ex) {
			LOGGER.error("Loi khoi tao ung dung", ex);
		}
	}

	@Override
	public Response login() {
		Map<String, Object> map = new HashMap<>();
		IUserToken vpsUserToken = (IUserToken) request.getSession().getAttribute(VPS_USER_TOKEN_KEY);
		// map.put("casUser", vsaUserToken);
		map.put("userToken", vpsUserToken);
		return Response.ok(map).build();
	}

	@Override
	public Response getUserInfo() {/*
		Map<String, Object> map = new HashMap<>();

		UserToken vsaUserToken = (UserToken) request.getSession().getAttribute("vsaUserToken");
		IUserToken vpsUserToken = (IUserToken) request.getSession().getAttribute(VPS_USER_TOKEN_KEY);
		map.put("casUser", vsaUserToken);
		map.put("vpsUserToken", vpsUserToken);
		KttsUserSession userSession2 = (KttsUserSession) request.getSession().getAttribute(USER_SESSION_KEY);

		if (userSession2 == null) {
			userSession2 = new KttsUserSession();
			if (vsaUserToken != null) {
				userSession2.setUserToken(vpsUserToken);

				// Lay thong tin tu db
				try {
//					KttsSysUserDto sysUserDto = authenticateBusiness.getKttsSysUserById(vpsUserToken.getSysUserId());
//					userSession2.setKttsSysUserDto(sysUserDto);

					SysUserQLKDTO dto= sysUserQLKBusinessImpl.getUserInfoByLoginName(vpsUserToken.getUserName());
					userSession2.setVpsUserInfo(dto);
					
				} catch (Exception ex) {
					LOGGER.error("Loi khi truy van database", ex);
				}
				request.getSession().setAttribute(USER_SESSION_KEY, userSession2);
			}

		}
		if (userSession2.getUserToken() != null) {
			map.put("kttsUsertoken", userSession2.getKttsSysUserDto());
		
		}
		if(userSession2.getVpsUserInfo() != null){
		map.put("VpsUserInfo", userSession2.getVpsUserInfo());
		}
		return Response.ok(map).build();*/
		return Response.ok().build();
	}

	@Override
	public Response logout() throws Exception {

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
}
