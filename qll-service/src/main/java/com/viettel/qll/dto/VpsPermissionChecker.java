package com.viettel.qll.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.viettel.ktts.vps.IUserToken;
import com.viettel.ktts.vps.VPSServiceWrapper;
import com.viettel.vps.webservice.AuthorizedData;
import com.viettel.vps.webservice.MenuBO;
import com.viettel.vps.webservice.SysUserBO;
import com.viettel.vps.webservice.UserPermissionBO;

public class VpsPermissionChecker {
	private static final Logger LOGGER = Logger.getLogger(VpsPermissionChecker.class);
	public static final String AUTHORIZED_DATA = "VPS_AUTHORIZED_DATA_20170811";
	private static String OPERATION_ADRESOURCE_SEPERATOR = " ";

	public static void init(String loginName, HttpSession session) throws Exception {
		String appCode = ResourceBundle.getBundle("vpsclient").getString("appCode");
		String appPass = ResourceBundle.getBundle("vpsclient").getString("appPass");

		session.setAttribute("VPS_AUTHORIZED_DATA_20170811",
				VPSServiceWrapper.getAuthorizedData(loginName, appCode, appPass));
	}

	public static void putAuthorizedToSession(HttpSession session, Object authorizedData) {
		session.setAttribute("VPS_AUTHORIZED_DATA_20170811", authorizedData);
	}

	public static AuthorizedData getAuthorizedData(HttpServletRequest req) {
		IUserToken authorizedData = (IUserToken) req.getSession().getAttribute("VPS_AUTHORIZED_DATA_20170811");
		if ((authorizedData == null) || (authorizedData.getAuthorizedData() == null)) {
			LOGGER.error("LOI KHONG LAY DUOC DU LIEU PHAN QUYEN TRONG REQUEST SESSION : " + req.getRemoteUser());
			return null;
		}
		return (AuthorizedData) authorizedData.getAuthorizedData();
	}

	public static SysUserBO getUser(HttpServletRequest req) {
		IUserToken authorizedData = (IUserToken) req.getSession().getAttribute("VPS_AUTHORIZED_DATA_20170811");
		if ((authorizedData == null) || (authorizedData.getAuthorizedData() == null)) {
			LOGGER.error("LOI KHONG LAY DUOC DU LIEU PHAN QUYEN TRONG REQUEST SESSION : " + req.getRemoteUser());
			return null;
		}

		return ((AuthorizedData) authorizedData.getAuthorizedData()).getUser();
	}

	public static List<MenuBO> getGrantedMenus(HttpServletRequest req) {
		IUserToken authorizedData = (IUserToken) req.getSession().getAttribute("VPS_AUTHORIZED_DATA_20170811");
		if ((authorizedData == null) || (authorizedData.getAuthorizedData() == null)) {
			LOGGER.error("LOI KHONG LAY DUOC DU LIEU PHAN QUYEN TRONG REQUEST SESSION : " + req.getRemoteUser());
			return null;
		}
		return ((AuthorizedData) authorizedData.getAuthorizedData()).getGrantedMenus();
	}

	public static boolean hasPermission(String operationKey, String adResourceKey, HttpServletRequest req) {
		IUserToken vpsUserToken = (IUserToken) req.getSession().getAttribute("VPS_AUTHORIZED_DATA_20170811");
		if ((vpsUserToken == null) || (vpsUserToken.getAuthorizedData() == null)) {
			LOGGER.error("LOI KHONG LAY DUOC DU LIEU PHAN QUYEN TRONG REQUEST SESSION : " + req.getRemoteUser());
			return false;
		}
		AuthorizedData authorizedData = (AuthorizedData) vpsUserToken.getAuthorizedData();
		String permissionCode = getPermissionCodeByKey(operationKey) + OPERATION_ADRESOURCE_SEPERATOR
				+ getPermissionCodeByKey(adResourceKey);
		List<UserPermissionBO> list = authorizedData.getBusinessUserPermissions();
		for (UserPermissionBO userPermissionBO : list) {
			if (permissionCode.equals(userPermissionBO.getPermissionCode())) {
				return true;
			}
		}
		return false;
	}

	public static boolean haveMenu(String url, HttpServletRequest req) {
		if ((url != null) && (!"".equals(url.trim()))) {
			IUserToken vpsUserToken = (IUserToken) req.getSession().getAttribute("VPS_AUTHORIZED_DATA_20170811");
			if ((vpsUserToken == null) || (vpsUserToken.getAuthorizedData() == null)) {
				LOGGER.error("LOI KHONG LAY DUOC DU LIEU PHAN QUYEN TRONG REQUEST SESSION : " + req.getRemoteUser());
				return false;
			}
			AuthorizedData authorizedData = (AuthorizedData) vpsUserToken.getAuthorizedData();
			url = getPermissionCodeByKey(url.trim());
			List<MenuBO> list = authorizedData.getGrantedMenus();
			for (MenuBO menuBo : list) {
				if (url.equals(menuBo.getUrl())) {
					return true;
				}
			}
			return false;
		}
		return false;
	}

	public static String getDomainDataItemIds(String operationKey, String adResourceKey, HttpServletRequest req) {
		IUserToken vpsUserToken = (IUserToken) req.getSession().getAttribute("VPS_AUTHORIZED_DATA_20170811");
		if ((vpsUserToken == null) || (vpsUserToken.getAuthorizedData() == null)) {
			LOGGER.error("LOI KHONG LAY DUOC DU LIEU PHAN QUYEN TRONG REQUEST SESSION : " + req.getRemoteUser());
			return "";
		}
		AuthorizedData authorizedData = (AuthorizedData) vpsUserToken.getAuthorizedData();
		String permissionCode = getPermissionCodeByKey(operationKey) + OPERATION_ADRESOURCE_SEPERATOR
				+ getPermissionCodeByKey(adResourceKey);
		List<UserPermissionBO> list = authorizedData.getBusinessUserPermissions();
		String domainList = "";
		for (UserPermissionBO userPermissionBO : list) {
			if (userPermissionBO.getPermissionCode().equals(permissionCode)) {
				domainList = userPermissionBO.getDomainDataList();
			}
		}
		return domainList;
	}

	public static String getDomainDataItemIds(String operationKey, String adResourceKey, HttpServletRequest req,
			List<Long> lstOrganizationByMarket) {
		IUserToken vpsUserToken = (IUserToken) req.getSession().getAttribute("VPS_AUTHORIZED_DATA_20170811");
		if ((vpsUserToken == null) || (vpsUserToken.getAuthorizedData() == null)) {
			LOGGER.error("LOI KHONG LAY DUOC DU LIEU PHAN QUYEN TRONG REQUEST SESSION : " + req.getRemoteUser());
			return "";
		}
		AuthorizedData authorizedData = (AuthorizedData) vpsUserToken.getAuthorizedData();
		String permissionCode = getPermissionCodeByKey(operationKey) + OPERATION_ADRESOURCE_SEPERATOR
				+ getPermissionCodeByKey(adResourceKey);
		List<UserPermissionBO> list = authorizedData.getBusinessUserPermissions();
		String domainList = "";
		for (UserPermissionBO userPermissionBO : list) {
			if (userPermissionBO.getPermissionCode().equals(permissionCode)) {
				domainList = userPermissionBO.getDomainDataList();
			}
		}
		if ((lstOrganizationByMarket != null) && (domainList != null) && (lstOrganizationByMarket.size() > 0)) {
			String[] ids = domainList.split(",");
			List domainListTemp = new ArrayList();
			for (int i = 0; i < ids.length; i++) {
				domainListTemp.add(Long.valueOf(ids[i]));
			}
			List domainListFinal = new ArrayList(domainListTemp);
			domainListFinal.retainAll(lstOrganizationByMarket);
			String domainFinal = "";
			if (domainListFinal.size() > 0) {
//				for (Long a : domainListFinal) {
//					domainFinal = String.valueOf(a) + ",";
//				}
				String rephrase = "";
				if ((domainFinal != null) && (domainFinal.length() > 1)) {
					rephrase = domainFinal.substring(0, domainFinal.length() - 1);
				}
				domainFinal = rephrase;
			}
			return domainFinal;
		}

		return domainList;
	}

	public static boolean checkPermissionOnDomainData(String operationKey, String adResourceKey, Long domainDataId,
			HttpServletRequest req) {
		IUserToken vpsUserToken = (IUserToken) req.getSession().getAttribute("VPS_AUTHORIZED_DATA_20170811");
		if ((vpsUserToken == null) || (vpsUserToken.getAuthorizedData() == null)) {
			LOGGER.error("LOI KHONG LAY DUOC DU LIEU PHAN QUYEN TRONG REQUEST SESSION : " + req.getRemoteUser());
			return false;
		}
		AuthorizedData authorizedData = (AuthorizedData) vpsUserToken.getAuthorizedData();
		String permissionCode = getPermissionCodeByKey(operationKey) + OPERATION_ADRESOURCE_SEPERATOR
				+ getPermissionCodeByKey(adResourceKey);

		List<UserPermissionBO> list = authorizedData.getBusinessUserPermissions();
		for (UserPermissionBO userPermissionBO : list) {
			if (userPermissionBO.getPermissionCode().equals(permissionCode)) {
				String domainList = userPermissionBO.getDomainDataList();
				return (domainList != null) && (!"".equals(domainList)) && (("," + domainList + ",")
						.contains(String.valueOf("," + String.valueOf(domainDataId) + ",")));
			}
		}
		return false;
	}

	public static Long getDefaultId(String operationKey, String adResourceKey, HttpServletRequest req) {
		IUserToken vpsUserToken = (IUserToken) req.getSession().getAttribute("VPS_AUTHORIZED_DATA_20170811");
		if ((vpsUserToken == null) || (vpsUserToken.getAuthorizedData() == null)) {
			LOGGER.error("LOI KHONG LAY DUOC DU LIEU PHAN QUYEN TRONG REQUEST SESSION : " + req.getRemoteUser());
			return null;
		}
		AuthorizedData authorizedData = (AuthorizedData) vpsUserToken.getAuthorizedData();
		String permissionCode = getPermissionCodeByKey(operationKey) + OPERATION_ADRESOURCE_SEPERATOR
				+ getPermissionCodeByKey(adResourceKey);
		List<UserPermissionBO> list = authorizedData.getBusinessUserPermissions();
		for (UserPermissionBO userPermissionBO : list) {
			if (userPermissionBO.getPermissionCode().equals(permissionCode)) {
				return userPermissionBO.getDefaultId();
			}
		}
		return null;
	}

	public static String getPermissionCodeByKey(String key) {
		ResourceBundle rb = ResourceBundle.getBundle("vps_permissionCode");
		return rb.getString(key);
	}
}