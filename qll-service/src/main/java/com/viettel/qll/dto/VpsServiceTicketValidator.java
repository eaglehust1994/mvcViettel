package com.viettel.qll.dto;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import com.viettel.common.FunctionUtil;
import com.viettel.ktts.vps.VpsUtil;

import viettel.passport.client.UserToken;
import viettel.passport.util.SecureURL;

public class VpsServiceTicketValidator
{
  private static String wsVpsUrl;
  private static Logger log = Logger.getLogger(VpsServiceTicketValidator.class);
  private String casValidateUrl;
  private String proxyCallbackUrl;
  private String st;
  private String service;
  private String pgtIou;
  private String user;
  private String errorMessage;
  private String entireResponse;
  private boolean renew = false;
  private boolean successfulAuthentication;
  private String domainCode;
  private UserToken userToken;
  private String cookie;
  private String ipWan;
  private static long TIME_OUT;
  public static final long ACTION_TIMEOUT = 60000L;
  private int authenticateCode;
  private VpsUserToken vpsUserToken;

  public void setCasValidateUrl(String x)
  {
    this.casValidateUrl = x;
  }

  public String getCasValidateUrl() {
    return this.casValidateUrl;
  }

  public void setProxyCallbackUrl(String x) {
    this.proxyCallbackUrl = x;
  }

  public void setRenew(boolean b) {
    this.renew = b;
  }

  public String getProxyCallbackUrl() {
    return this.proxyCallbackUrl;
  }

  public void setServiceTicket(String x) {
    this.st = x;
  }

  public void setService(String x) {
    this.service = x;
  }

  public String getUser() {
    return this.user;
  }

  public String getPgtIou() {
    return this.pgtIou;
  }

  public boolean isAuthenticationSuccesful() {
    return this.successfulAuthentication;
  }

  public int getAuthenticateCode() {
    return this.authenticateCode;
  }

  public void setAuthenticateCode(int authenticateCode) {
    this.authenticateCode = authenticateCode;
  }

  public String getErrorMessage() {
    return this.errorMessage;
  }

  public String getResponse() {
    return this.entireResponse;
  }

  public String getDomainCode() {
    return this.domainCode;
  }

  public void setDomainCode(String domainCode) {
    this.domainCode = domainCode;
  }

  public UserToken getUserToken() {
    return this.userToken;
  }

  public void setUserToken(UserToken userToken) {
    this.userToken = userToken;
  }

  protected void clear() {
    this.user = (this.pgtIou = this.errorMessage = null);
    this.successfulAuthentication = false;
  }

  public void validate() throws IOException, ParserConfigurationException
  {
    if ((this.casValidateUrl == null) || (this.st == null)) {
      throw new IllegalStateException("must set validation URL and ticket");
    }
    clear();
    StringBuilder sb = new StringBuilder();
    sb.append(this.casValidateUrl);
    if (this.casValidateUrl.indexOf('?') == -1)
      sb.append('?');
    else {
      sb.append('&');
    }
    sb.append("service=").append(this.service).append("&ticket=").append(this.st);
    if (this.proxyCallbackUrl != null) {
      sb.append("&pgtUrl=").append(this.proxyCallbackUrl);
    }
    if (this.renew) {
      sb.append("&renew=true");
    }
    sb.append("&domainCode=").append(this.domainCode);
    sb.append("&ipWan=").append(this.ipWan);
    String url = sb.toString();
    log.info(new StringBuilder().append("Start validate for URL: ").append(url).toString());
    long start = System.currentTimeMillis();
    SecureURL secure = new SecureURL();
    if (((this.cookie != null ? 1 : 0) & ("".equals(this.cookie) ? 0 : 1)) != 0)
      this.entireResponse = secure.retrieve(url, new StringBuilder().append("JSESSIONID=").append(this.cookie).toString());
    else {
      this.entireResponse = secure.retrieve(url);
    }
    long elapse = System.currentTimeMillis() - start;
    log.info(new StringBuilder().append("Receive validate response for service ticket [").append(this.st).append("], response time = ").append(elapse).toString());
    if (elapse > 60000L)
      log.warn(new StringBuilder().append("Action timeout: Too long time to validate ticket [").append(this.st).append("], response time is ").append(elapse).toString());
    try
    {
      parseXMLResponse(this.entireResponse);

      elapse = System.currentTimeMillis() - start;
      log.info(new StringBuilder().append("Finish validate service ticket [").append(this.st).append("], elapse time = ").append(elapse).toString());
      if (elapse > 60000L) {
        log.error(new StringBuilder().append("Action timeout: Too long time to validate user, elapse time = ").append(elapse).toString());
      }

      elapse = System.currentTimeMillis() - start;
      log.info(new StringBuilder().append("Finish validate service ticket [").append(this.st).append("], elapse time = ").append(elapse).toString());
      if (elapse > 60000L)
        log.error(new StringBuilder().append("Action timeout: Too long time to validate user, elapse time = ").append(elapse).toString());
    }
    catch (SAXException ex)
    {
      log.error(new StringBuilder().append("Error when parse xml response, respone data:\n\"").append(this.entireResponse).append("\"").toString(), ex);

      elapse = System.currentTimeMillis() - start;
      log.info(new StringBuilder().append("Finish validate service ticket [").append(this.st).append("], elapse time = ").append(elapse).toString());
      if (elapse > 60000L) {
        log.error(new StringBuilder().append("Action timeout: Too long time to validate user, elapse time = ").append(elapse).toString());
      }

      elapse = System.currentTimeMillis() - start;
      log.info(new StringBuilder().append("Finish validate service ticket [").append(this.st).append("], elapse time = ").append(elapse).toString());
      if (elapse > 60000L)
        log.error(new StringBuilder().append("Action timeout: Too long time to validate user, elapse time = ").append(elapse).toString());
    }
    finally
    {
      elapse = System.currentTimeMillis() - start;
      log.info(new StringBuilder().append("Finish validate service ticket [").append(this.st).append("], elapse time = ").append(elapse).toString());
      if (elapse > 60000L)
        log.error(new StringBuilder().append("Action timeout: Too long time to validate user, elapse time = ").append(elapse).toString());
    }
  }

  public void parseXMLResponse(String response)
    throws ParserConfigurationException, SAXException, IOException
  {
    this.userToken = UserToken.parseXMLResponse(response);
    if (this.userToken == null) {
      this.successfulAuthentication = false;
      this.authenticateCode = 0;
      log.info("khong ton tai phien dang nhap");
      log.info(new StringBuilder().append("Authenticate failure for service ticket [").append(this.st).append("], respone data:\n\"").append(response).append("\"").toString());
      return;
    }
    if (this.userToken.getStaffCode() == null) {
      log.info("khong ton tai ma nhien vien");
      this.authenticateCode = 2;
      return;
    }
    if ((this.userToken.getStaffCode() != null) && (!"".equals(this.userToken.getStaffCode().trim()))) {
      try {
        log.info("Lay thong tin nguoi dung tu vpsService tap trung");
        updateUser(this.userToken.getStaffCode(), FunctionUtil.getAppCode());
      } catch (Throwable e) {
        log.error("Loi khi update role cho tai khoan", e);
      }
    }
    else {
      log.info("SSO khong tra ve staff_code -> authen fail");
    }

    if ((this.vpsUserToken != null) ) {
      this.user = this.vpsUserToken.getUserName();
      this.pgtIou = this.vpsUserToken.getFullName();
      this.successfulAuthentication = true;
      this.authenticateCode = 1;
      log.info(new StringBuilder().append("Authenticate successful for username [").append(this.userToken.getUserName()).append("] for service ticket [").append(this.st).append("]").toString());
    } else {
      this.user = null;
      this.pgtIou = null;
      this.successfulAuthentication = false;
      this.authenticateCode = 2;
      log.info(new StringBuilder().append("size cua token:").append(this.userToken.getObjectTokens().size()).toString());
      log.info(new StringBuilder().append("nguoi dung chua duoc phan quyen voi").append(this.st).toString());
      log.info(new StringBuilder().append("Authenticate failure for service ticket [").append(this.st).append("], respone data:\n\"").append(response).append("\"").toString());
    }
  }

  public void setVpsUserToken(VpsUserToken token)
  {
    this.vpsUserToken = token;
  }
  public VpsUserToken getVpsUserToken() {
    return this.vpsUserToken;
  }

  public void updateUser(String staffCode, String appCode) throws Throwable
  {
    log.info(new StringBuilder().append("VPS:call du lieu sang vps, content: vpsUrl=").append(wsVpsUrl).append(",staffCode=").append(staffCode).append(",domain_code=").append(appCode).toString());
    appCode="CTCT";
    VpsUserToken data = new VpsUserToken(VPSServiceWrapper.getAuthorizedData(wsVpsUrl, staffCode, appCode, null));
    if ((data != null) && (data.getErrorCode() == null)) {
      log.info("Gọi dữ liệu sang VPS thành công");
//      if ((data.getParentMenu() == null) && (data.getParentMenu().isEmpty())) {
//        log.error("Loi khi goi sang vps");
//        log.info("nguoi dung chua duoc phan quyen voi");
//        this.authenticateCode = 2;
//      } else {
//        this.authenticateCode = 1;
//      }
    } else {
      log.error("Loi khi goi sang vps");
      this.authenticateCode = 3;
    }
    setVpsUserToken(data);
  }

  public String getCookie() {
    return this.cookie;
  }

  public void setCookie(String cookie) {
    this.cookie = cookie;
  }

  public String getIpWan() {
    return this.ipWan;
  }

  public void setIpWan(String ipWan) {
    this.ipWan = ipWan;
  }

  static
  {
    try
    {
      wsVpsUrl = VpsUtil.getVpsUrl();
    } catch (Exception ex) {
      log.error(ex);
      log.error("Khong lay duoc vpsUrl");
    }

    TIME_OUT = 18000L;
  }
}