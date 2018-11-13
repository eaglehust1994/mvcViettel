package com.viettel.qll.dto;

import com.viettel.vps.webservice.AuthorizedData;
import com.viettel.vps.webservice.AuthorizedDataService;
import com.viettel.vps.webservice.AuthorizedDataService_Service;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ResourceBundle;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.log4j.Logger;

public class VPSServiceWrapper
{
  private static final Logger LOGGER = Logger.getLogger(VPSServiceWrapper.class);

  public static void updateDomainData(Long dataId, Long parentId, String dataCode, String dataName, String domainType, String path, String fullPath, Long status) throws Exception
  {
    SSLSocketFactory defaultSSLSocketFactory = HttpsURLConnection.getDefaultSSLSocketFactory();
    HostnameVerifier defaultHostnameVerifier = HttpsURLConnection.getDefaultHostnameVerifier();

    String vpsServiceUrl = ResourceBundle.getBundle("vpsclient").getString("vpsServiceUrl");
    if (vpsServiceUrl.startsWith("https")) {
      disableSslVerification();
    }
    URL url = new URL(vpsServiceUrl);
    AuthorizedDataService authorizedDataService = new AuthorizedDataService_Service(url).getAuthorizedDataServicePort();
    authorizedDataService.updateDomainData(dataId, parentId, dataCode, dataName, domainType, path, fullPath, status);

    HttpsURLConnection.setDefaultSSLSocketFactory(defaultSSLSocketFactory);
    HttpsURLConnection.setDefaultHostnameVerifier(defaultHostnameVerifier);
  }

  public static AuthorizedData getAuthorizedData(String vpsServiceUrl, String loginName, String appCode, String appPass) throws Exception {
    SSLSocketFactory defaultSSLSocketFactory = HttpsURLConnection.getDefaultSSLSocketFactory();
    HostnameVerifier defaultHostnameVerifier = HttpsURLConnection.getDefaultHostnameVerifier();
    if (vpsServiceUrl.startsWith("https")) {
      disableSslVerification();
    }
    URL url = new URL(vpsServiceUrl);
    AuthorizedDataService authorizedDataService = new AuthorizedDataService_Service(url).getAuthorizedDataServicePort();
    AuthorizedData authorizedData = authorizedDataService.getAuthorizedData(loginName, appCode, appPass);

    HttpsURLConnection.setDefaultSSLSocketFactory(defaultSSLSocketFactory);
    HttpsURLConnection.setDefaultHostnameVerifier(defaultHostnameVerifier);
    return authorizedData;
  }

  public static AuthorizedData getAuthorizedData(String loginName, String appCode, String appPass) throws Exception {
    SSLSocketFactory defaultSSLSocketFactory = HttpsURLConnection.getDefaultSSLSocketFactory();
    HostnameVerifier defaultHostnameVerifier = HttpsURLConnection.getDefaultHostnameVerifier();

    String vpsServiceUrl = ResourceBundle.getBundle("vpsclient").getString("vpsServiceUrl");
    if (vpsServiceUrl.startsWith("https")) {
      disableSslVerification();
    }
    URL url = new URL(vpsServiceUrl);
    AuthorizedDataService authorizedDataService = new AuthorizedDataService_Service(url).getAuthorizedDataServicePort();
    AuthorizedData authorizedData = authorizedDataService.getAuthorizedData(loginName, appCode, appPass);

    HttpsURLConnection.setDefaultSSLSocketFactory(defaultSSLSocketFactory);
    HttpsURLConnection.setDefaultHostnameVerifier(defaultHostnameVerifier);
    return authorizedData;
  }

  private static void disableSslVerification()
  {
    try {
      TrustManager[] trustAllCerts = { new X509TrustManager()
      {
        public X509Certificate[] getAcceptedIssuers()
        {
          return null;
        }

        public void checkClientTrusted(X509Certificate[] certs, String authType)
        {
        }

        public void checkServerTrusted(X509Certificate[] certs, String authType)
        {
        }
      }
       };
      SSLContext sc = SSLContext.getInstance("SSL");
      sc.init(null, trustAllCerts, new SecureRandom());
      HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

      HostnameVerifier allHostsValid = new HostnameVerifier()
      {
        public boolean verify(String hostname, SSLSession session) {
          return true;
        }
      };
      HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
    } catch (Exception ex) {
      LOGGER.error("Lỗi khi disable SSL", ex);
    }
  }
}