package com.viettel.asset.filter;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CsrfTokenRepository {

    private static final String DEFAULT_CSRF_PARAMETER_NAME = "_csrf";
    private static final String DEFAULT_CSRF_HEADER_NAME = "X-CSRF-TOKEN";
    private static final String DEFAUL_SESSION_NAME = "X-CSRF-TOKEN_SESSION_NAME";

    private static String headerName = DEFAULT_CSRF_HEADER_NAME;
    private static String parameterName = DEFAULT_CSRF_PARAMETER_NAME;
    private static final String DEFAULT_CSRF_TOKEN_ATTR_NAME = CsrfTokenRepository.class.getName()
            .concat(".CSRF_TOKEN");
    private static String sessionAttributeName = DEFAULT_CSRF_TOKEN_ATTR_NAME;

    /*
	 * Singleton insstance
     */
    private static CsrfTokenRepository instance;

    public static synchronized CsrfTokenRepository getInstance() {
        if (instance == null) {
            instance = new CsrfTokenRepository();
        }
        return instance;
    }

    public CsrfToken loadToken(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return null;
        }
        return (CsrfToken) session.getAttribute(sessionAttributeName);
    }

    public void saveToken(HttpServletRequest request, CsrfToken token) {
        if (token == null) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.removeAttribute(sessionAttributeName);
            }
        } else {
            HttpSession session = request.getSession(false);
            if (session != null) {
            	session.setAttribute(sessionAttributeName, token);
            }

        }
    }

    public CsrfToken generateToken(HttpServletRequest request) {
        return new CsrfToken(headerName, parameterName, createNewToken());
    }

    private String createNewToken() {
        return UUID.randomUUID().toString();
    }
}
