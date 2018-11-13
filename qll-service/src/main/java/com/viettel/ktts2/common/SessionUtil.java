package com.viettel.ktts2.common;

import java.util.ResourceBundle;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.viettel.asset.filter.session.UserSession;

public class SessionUtil {
	protected static final String USER_SESSION_KEY="USER_SESSION_KEY";

	public static Long getUserId(HttpServletRequest request) throws BusinessException {
		UserSession s = (UserSession) request.getSession().getAttribute(USER_SESSION_KEY);
		if (s == null) {
			throw new BusinessException("user is not authen");
		}
		return s.getUserId();

	}
	
	public static Long getGroupId(HttpServletRequest request) throws BusinessException {
		UserSession s = (UserSession) request.getSession().getAttribute(USER_SESSION_KEY);
		if (s == null) {
			throw new BusinessException("user is not authen");
		}
		return s.getGroupId();

	}
	
	
	/**
	 * Lấy encryptKey từ session được lưu trong session key "UID_QUERY_CRYPT"
	 * @param request
	 * @return
	 */
	private static ResourceBundle rb = ResourceBundle.getBundle("config");
	//private static String UPLOAD_ENCRYPT_KEY = rb.getString("default_upload_encode_key");
	private static String UID_QUERY_CRYPT = "UID_QUERY_CRYPT";
	public static String getEncryptKey(HttpServletRequest request){
		String encryptKey;
		if (request.getSession().getAttribute(UID_QUERY_CRYPT) == null) {
            encryptKey = UUID.randomUUID().toString();
            request.getSession().setAttribute(UID_QUERY_CRYPT, encryptKey);
        } else {
            encryptKey = (String) request.getSession().getAttribute(UID_QUERY_CRYPT);
        }
		return encryptKey;
	}
	
	
	
}
