
package com.viettel.passport;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.viettel.passport package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ChangePassResponse_QNAME = new QName("http://passport.viettel.com/", "changePassResponse");
    private final static QName _Validate_QNAME = new QName("http://passport.viettel.com/", "validate");
    private final static QName _ChangePassWithoutCheckOldPassResponse_QNAME = new QName("http://passport.viettel.com/", "changePassWithoutCheckOldPassResponse");
    private final static QName _GetAllowedApp_QNAME = new QName("http://passport.viettel.com/", "getAllowedApp");
    private final static QName _GetOTPCodeMultiFactorResponse_QNAME = new QName("http://passport.viettel.com/", "getOTPCodeMultiFactorResponse");
    private final static QName _CheckExistUser_QNAME = new QName("http://passport.viettel.com/", "checkExistUser");
    private final static QName _CreateUsersResponse_QNAME = new QName("http://passport.viettel.com/", "createUsersResponse");
    private final static QName _AuthenOTPMultiFactor_QNAME = new QName("http://passport.viettel.com/", "authenOTPMultiFactor");
    private final static QName _GetDepartmentTreeResponse_QNAME = new QName("http://passport.viettel.com/", "getDepartmentTreeResponse");
    private final static QName _ValidateIncludeIp_QNAME = new QName("http://passport.viettel.com/", "validateIncludeIp");
    private final static QName _ReportLoginResponse_QNAME = new QName("http://passport.viettel.com/", "reportLoginResponse");
    private final static QName _ChangePass_QNAME = new QName("http://passport.viettel.com/", "changePass");
    private final static QName _GetRolesOfAppResponse_QNAME = new QName("http://passport.viettel.com/", "getRolesOfAppResponse");
    private final static QName _Authen_QNAME = new QName("http://passport.viettel.com/", "authen");
    private final static QName _GetUserInfo_QNAME = new QName("http://passport.viettel.com/", "getUserInfo");
    private final static QName _ResetForgetPassword_QNAME = new QName("http://passport.viettel.com/", "resetForgetPassword");
    private final static QName _GetAppFunctionsResponse_QNAME = new QName("http://passport.viettel.com/", "getAppFunctionsResponse");
    private final static QName _GetRolesOfApp_QNAME = new QName("http://passport.viettel.com/", "getRolesOfApp");
    private final static QName _AuthenResponse_QNAME = new QName("http://passport.viettel.com/", "authenResponse");
    private final static QName _GetDepartmentTree_QNAME = new QName("http://passport.viettel.com/", "getDepartmentTree");
    private final static QName _AuthenOTPMultiFactorResponse_QNAME = new QName("http://passport.viettel.com/", "authenOTPMultiFactorResponse");
    private final static QName _GetAppFunctions_QNAME = new QName("http://passport.viettel.com/", "getAppFunctions");
    private final static QName _GetOTPCodeMultiFactor_QNAME = new QName("http://passport.viettel.com/", "getOTPCodeMultiFactor");
    private final static QName _CheckExistUserResponse_QNAME = new QName("http://passport.viettel.com/", "checkExistUserResponse");
    private final static QName _GetAllowedAppResponse_QNAME = new QName("http://passport.viettel.com/", "getAllowedAppResponse");
    private final static QName _ValidateIncludeIpResponse_QNAME = new QName("http://passport.viettel.com/", "validateIncludeIpResponse");
    private final static QName _CreateUsers_QNAME = new QName("http://passport.viettel.com/", "createUsers");
    private final static QName _GetUserInfoResponse_QNAME = new QName("http://passport.viettel.com/", "getUserInfoResponse");
    private final static QName _ReportLogin_QNAME = new QName("http://passport.viettel.com/", "reportLogin");
    private final static QName _ValidateResponse_QNAME = new QName("http://passport.viettel.com/", "validateResponse");
    private final static QName _ResetForgetPasswordResponse_QNAME = new QName("http://passport.viettel.com/", "resetForgetPasswordResponse");
    private final static QName _ChangePassWithoutCheckOldPass_QNAME = new QName("http://passport.viettel.com/", "changePassWithoutCheckOldPass");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.viettel.passport
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetRolesOfAppResponse }
     * 
     */
    public GetRolesOfAppResponse createGetRolesOfAppResponse() {
        return new GetRolesOfAppResponse();
    }

    /**
     * Create an instance of {@link ChangePass }
     * 
     */
    public ChangePass createChangePass() {
        return new ChangePass();
    }

    /**
     * Create an instance of {@link ReportLoginResponse }
     * 
     */
    public ReportLoginResponse createReportLoginResponse() {
        return new ReportLoginResponse();
    }

    /**
     * Create an instance of {@link CreateUsersResponse }
     * 
     */
    public CreateUsersResponse createCreateUsersResponse() {
        return new CreateUsersResponse();
    }

    /**
     * Create an instance of {@link GetDepartmentTreeResponse }
     * 
     */
    public GetDepartmentTreeResponse createGetDepartmentTreeResponse() {
        return new GetDepartmentTreeResponse();
    }

    /**
     * Create an instance of {@link ValidateIncludeIp }
     * 
     */
    public ValidateIncludeIp createValidateIncludeIp() {
        return new ValidateIncludeIp();
    }

    /**
     * Create an instance of {@link AuthenOTPMultiFactor }
     * 
     */
    public AuthenOTPMultiFactor createAuthenOTPMultiFactor() {
        return new AuthenOTPMultiFactor();
    }

    /**
     * Create an instance of {@link CheckExistUser }
     * 
     */
    public CheckExistUser createCheckExistUser() {
        return new CheckExistUser();
    }

    /**
     * Create an instance of {@link GetOTPCodeMultiFactorResponse }
     * 
     */
    public GetOTPCodeMultiFactorResponse createGetOTPCodeMultiFactorResponse() {
        return new GetOTPCodeMultiFactorResponse();
    }

    /**
     * Create an instance of {@link ChangePassWithoutCheckOldPassResponse }
     * 
     */
    public ChangePassWithoutCheckOldPassResponse createChangePassWithoutCheckOldPassResponse() {
        return new ChangePassWithoutCheckOldPassResponse();
    }

    /**
     * Create an instance of {@link GetAllowedApp }
     * 
     */
    public GetAllowedApp createGetAllowedApp() {
        return new GetAllowedApp();
    }

    /**
     * Create an instance of {@link ChangePassResponse }
     * 
     */
    public ChangePassResponse createChangePassResponse() {
        return new ChangePassResponse();
    }

    /**
     * Create an instance of {@link Validate }
     * 
     */
    public Validate createValidate() {
        return new Validate();
    }

    /**
     * Create an instance of {@link ChangePassWithoutCheckOldPass }
     * 
     */
    public ChangePassWithoutCheckOldPass createChangePassWithoutCheckOldPass() {
        return new ChangePassWithoutCheckOldPass();
    }

    /**
     * Create an instance of {@link ValidateResponse }
     * 
     */
    public ValidateResponse createValidateResponse() {
        return new ValidateResponse();
    }

    /**
     * Create an instance of {@link ResetForgetPasswordResponse }
     * 
     */
    public ResetForgetPasswordResponse createResetForgetPasswordResponse() {
        return new ResetForgetPasswordResponse();
    }

    /**
     * Create an instance of {@link ValidateIncludeIpResponse }
     * 
     */
    public ValidateIncludeIpResponse createValidateIncludeIpResponse() {
        return new ValidateIncludeIpResponse();
    }

    /**
     * Create an instance of {@link GetUserInfoResponse }
     * 
     */
    public GetUserInfoResponse createGetUserInfoResponse() {
        return new GetUserInfoResponse();
    }

    /**
     * Create an instance of {@link ReportLogin }
     * 
     */
    public ReportLogin createReportLogin() {
        return new ReportLogin();
    }

    /**
     * Create an instance of {@link CreateUsers }
     * 
     */
    public CreateUsers createCreateUsers() {
        return new CreateUsers();
    }

    /**
     * Create an instance of {@link CheckExistUserResponse }
     * 
     */
    public CheckExistUserResponse createCheckExistUserResponse() {
        return new CheckExistUserResponse();
    }

    /**
     * Create an instance of {@link GetAllowedAppResponse }
     * 
     */
    public GetAllowedAppResponse createGetAllowedAppResponse() {
        return new GetAllowedAppResponse();
    }

    /**
     * Create an instance of {@link GetDepartmentTree }
     * 
     */
    public GetDepartmentTree createGetDepartmentTree() {
        return new GetDepartmentTree();
    }

    /**
     * Create an instance of {@link GetOTPCodeMultiFactor }
     * 
     */
    public GetOTPCodeMultiFactor createGetOTPCodeMultiFactor() {
        return new GetOTPCodeMultiFactor();
    }

    /**
     * Create an instance of {@link AuthenOTPMultiFactorResponse }
     * 
     */
    public AuthenOTPMultiFactorResponse createAuthenOTPMultiFactorResponse() {
        return new AuthenOTPMultiFactorResponse();
    }

    /**
     * Create an instance of {@link GetAppFunctions }
     * 
     */
    public GetAppFunctions createGetAppFunctions() {
        return new GetAppFunctions();
    }

    /**
     * Create an instance of {@link AuthenResponse }
     * 
     */
    public AuthenResponse createAuthenResponse() {
        return new AuthenResponse();
    }

    /**
     * Create an instance of {@link GetRolesOfApp }
     * 
     */
    public GetRolesOfApp createGetRolesOfApp() {
        return new GetRolesOfApp();
    }

    /**
     * Create an instance of {@link GetAppFunctionsResponse }
     * 
     */
    public GetAppFunctionsResponse createGetAppFunctionsResponse() {
        return new GetAppFunctionsResponse();
    }

    /**
     * Create an instance of {@link Authen }
     * 
     */
    public Authen createAuthen() {
        return new Authen();
    }

    /**
     * Create an instance of {@link GetUserInfo }
     * 
     */
    public GetUserInfo createGetUserInfo() {
        return new GetUserInfo();
    }

    /**
     * Create an instance of {@link ResetForgetPassword }
     * 
     */
    public ResetForgetPassword createResetForgetPassword() {
        return new ResetForgetPassword();
    }

    /**
     * Create an instance of {@link UserInfo }
     * 
     */
    public UserInfo createUserInfo() {
        return new UserInfo();
    }

    /**
     * Create an instance of {@link ErrorCode }
     * 
     */
    public ErrorCode createErrorCode() {
        return new ErrorCode();
    }

    /**
     * Create an instance of {@link DepartmentTreNode }
     * 
     */
    public DepartmentTreNode createDepartmentTreNode() {
        return new DepartmentTreNode();
    }

    /**
     * Create an instance of {@link UserReportBO }
     * 
     */
    public UserReportBO createUserReportBO() {
        return new UserReportBO();
    }

    /**
     * Create an instance of {@link Actor }
     * 
     */
    public Actor createActor() {
        return new Actor();
    }

    /**
     * Create an instance of {@link Response }
     * 
     */
    public Response createResponse() {
        return new Response();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChangePassResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://passport.viettel.com/", name = "changePassResponse")
    public JAXBElement<ChangePassResponse> createChangePassResponse(ChangePassResponse value) {
        return new JAXBElement<ChangePassResponse>(_ChangePassResponse_QNAME, ChangePassResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Validate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://passport.viettel.com/", name = "validate")
    public JAXBElement<Validate> createValidate(Validate value) {
        return new JAXBElement<Validate>(_Validate_QNAME, Validate.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChangePassWithoutCheckOldPassResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://passport.viettel.com/", name = "changePassWithoutCheckOldPassResponse")
    public JAXBElement<ChangePassWithoutCheckOldPassResponse> createChangePassWithoutCheckOldPassResponse(ChangePassWithoutCheckOldPassResponse value) {
        return new JAXBElement<ChangePassWithoutCheckOldPassResponse>(_ChangePassWithoutCheckOldPassResponse_QNAME, ChangePassWithoutCheckOldPassResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllowedApp }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://passport.viettel.com/", name = "getAllowedApp")
    public JAXBElement<GetAllowedApp> createGetAllowedApp(GetAllowedApp value) {
        return new JAXBElement<GetAllowedApp>(_GetAllowedApp_QNAME, GetAllowedApp.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetOTPCodeMultiFactorResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://passport.viettel.com/", name = "getOTPCodeMultiFactorResponse")
    public JAXBElement<GetOTPCodeMultiFactorResponse> createGetOTPCodeMultiFactorResponse(GetOTPCodeMultiFactorResponse value) {
        return new JAXBElement<GetOTPCodeMultiFactorResponse>(_GetOTPCodeMultiFactorResponse_QNAME, GetOTPCodeMultiFactorResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckExistUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://passport.viettel.com/", name = "checkExistUser")
    public JAXBElement<CheckExistUser> createCheckExistUser(CheckExistUser value) {
        return new JAXBElement<CheckExistUser>(_CheckExistUser_QNAME, CheckExistUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateUsersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://passport.viettel.com/", name = "createUsersResponse")
    public JAXBElement<CreateUsersResponse> createCreateUsersResponse(CreateUsersResponse value) {
        return new JAXBElement<CreateUsersResponse>(_CreateUsersResponse_QNAME, CreateUsersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuthenOTPMultiFactor }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://passport.viettel.com/", name = "authenOTPMultiFactor")
    public JAXBElement<AuthenOTPMultiFactor> createAuthenOTPMultiFactor(AuthenOTPMultiFactor value) {
        return new JAXBElement<AuthenOTPMultiFactor>(_AuthenOTPMultiFactor_QNAME, AuthenOTPMultiFactor.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDepartmentTreeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://passport.viettel.com/", name = "getDepartmentTreeResponse")
    public JAXBElement<GetDepartmentTreeResponse> createGetDepartmentTreeResponse(GetDepartmentTreeResponse value) {
        return new JAXBElement<GetDepartmentTreeResponse>(_GetDepartmentTreeResponse_QNAME, GetDepartmentTreeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidateIncludeIp }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://passport.viettel.com/", name = "validateIncludeIp")
    public JAXBElement<ValidateIncludeIp> createValidateIncludeIp(ValidateIncludeIp value) {
        return new JAXBElement<ValidateIncludeIp>(_ValidateIncludeIp_QNAME, ValidateIncludeIp.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReportLoginResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://passport.viettel.com/", name = "reportLoginResponse")
    public JAXBElement<ReportLoginResponse> createReportLoginResponse(ReportLoginResponse value) {
        return new JAXBElement<ReportLoginResponse>(_ReportLoginResponse_QNAME, ReportLoginResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChangePass }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://passport.viettel.com/", name = "changePass")
    public JAXBElement<ChangePass> createChangePass(ChangePass value) {
        return new JAXBElement<ChangePass>(_ChangePass_QNAME, ChangePass.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRolesOfAppResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://passport.viettel.com/", name = "getRolesOfAppResponse")
    public JAXBElement<GetRolesOfAppResponse> createGetRolesOfAppResponse(GetRolesOfAppResponse value) {
        return new JAXBElement<GetRolesOfAppResponse>(_GetRolesOfAppResponse_QNAME, GetRolesOfAppResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Authen }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://passport.viettel.com/", name = "authen")
    public JAXBElement<Authen> createAuthen(Authen value) {
        return new JAXBElement<Authen>(_Authen_QNAME, Authen.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://passport.viettel.com/", name = "getUserInfo")
    public JAXBElement<GetUserInfo> createGetUserInfo(GetUserInfo value) {
        return new JAXBElement<GetUserInfo>(_GetUserInfo_QNAME, GetUserInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResetForgetPassword }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://passport.viettel.com/", name = "resetForgetPassword")
    public JAXBElement<ResetForgetPassword> createResetForgetPassword(ResetForgetPassword value) {
        return new JAXBElement<ResetForgetPassword>(_ResetForgetPassword_QNAME, ResetForgetPassword.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAppFunctionsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://passport.viettel.com/", name = "getAppFunctionsResponse")
    public JAXBElement<GetAppFunctionsResponse> createGetAppFunctionsResponse(GetAppFunctionsResponse value) {
        return new JAXBElement<GetAppFunctionsResponse>(_GetAppFunctionsResponse_QNAME, GetAppFunctionsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRolesOfApp }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://passport.viettel.com/", name = "getRolesOfApp")
    public JAXBElement<GetRolesOfApp> createGetRolesOfApp(GetRolesOfApp value) {
        return new JAXBElement<GetRolesOfApp>(_GetRolesOfApp_QNAME, GetRolesOfApp.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuthenResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://passport.viettel.com/", name = "authenResponse")
    public JAXBElement<AuthenResponse> createAuthenResponse(AuthenResponse value) {
        return new JAXBElement<AuthenResponse>(_AuthenResponse_QNAME, AuthenResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDepartmentTree }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://passport.viettel.com/", name = "getDepartmentTree")
    public JAXBElement<GetDepartmentTree> createGetDepartmentTree(GetDepartmentTree value) {
        return new JAXBElement<GetDepartmentTree>(_GetDepartmentTree_QNAME, GetDepartmentTree.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuthenOTPMultiFactorResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://passport.viettel.com/", name = "authenOTPMultiFactorResponse")
    public JAXBElement<AuthenOTPMultiFactorResponse> createAuthenOTPMultiFactorResponse(AuthenOTPMultiFactorResponse value) {
        return new JAXBElement<AuthenOTPMultiFactorResponse>(_AuthenOTPMultiFactorResponse_QNAME, AuthenOTPMultiFactorResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAppFunctions }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://passport.viettel.com/", name = "getAppFunctions")
    public JAXBElement<GetAppFunctions> createGetAppFunctions(GetAppFunctions value) {
        return new JAXBElement<GetAppFunctions>(_GetAppFunctions_QNAME, GetAppFunctions.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetOTPCodeMultiFactor }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://passport.viettel.com/", name = "getOTPCodeMultiFactor")
    public JAXBElement<GetOTPCodeMultiFactor> createGetOTPCodeMultiFactor(GetOTPCodeMultiFactor value) {
        return new JAXBElement<GetOTPCodeMultiFactor>(_GetOTPCodeMultiFactor_QNAME, GetOTPCodeMultiFactor.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckExistUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://passport.viettel.com/", name = "checkExistUserResponse")
    public JAXBElement<CheckExistUserResponse> createCheckExistUserResponse(CheckExistUserResponse value) {
        return new JAXBElement<CheckExistUserResponse>(_CheckExistUserResponse_QNAME, CheckExistUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllowedAppResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://passport.viettel.com/", name = "getAllowedAppResponse")
    public JAXBElement<GetAllowedAppResponse> createGetAllowedAppResponse(GetAllowedAppResponse value) {
        return new JAXBElement<GetAllowedAppResponse>(_GetAllowedAppResponse_QNAME, GetAllowedAppResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidateIncludeIpResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://passport.viettel.com/", name = "validateIncludeIpResponse")
    public JAXBElement<ValidateIncludeIpResponse> createValidateIncludeIpResponse(ValidateIncludeIpResponse value) {
        return new JAXBElement<ValidateIncludeIpResponse>(_ValidateIncludeIpResponse_QNAME, ValidateIncludeIpResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateUsers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://passport.viettel.com/", name = "createUsers")
    public JAXBElement<CreateUsers> createCreateUsers(CreateUsers value) {
        return new JAXBElement<CreateUsers>(_CreateUsers_QNAME, CreateUsers.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://passport.viettel.com/", name = "getUserInfoResponse")
    public JAXBElement<GetUserInfoResponse> createGetUserInfoResponse(GetUserInfoResponse value) {
        return new JAXBElement<GetUserInfoResponse>(_GetUserInfoResponse_QNAME, GetUserInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReportLogin }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://passport.viettel.com/", name = "reportLogin")
    public JAXBElement<ReportLogin> createReportLogin(ReportLogin value) {
        return new JAXBElement<ReportLogin>(_ReportLogin_QNAME, ReportLogin.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://passport.viettel.com/", name = "validateResponse")
    public JAXBElement<ValidateResponse> createValidateResponse(ValidateResponse value) {
        return new JAXBElement<ValidateResponse>(_ValidateResponse_QNAME, ValidateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResetForgetPasswordResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://passport.viettel.com/", name = "resetForgetPasswordResponse")
    public JAXBElement<ResetForgetPasswordResponse> createResetForgetPasswordResponse(ResetForgetPasswordResponse value) {
        return new JAXBElement<ResetForgetPasswordResponse>(_ResetForgetPasswordResponse_QNAME, ResetForgetPasswordResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChangePassWithoutCheckOldPass }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://passport.viettel.com/", name = "changePassWithoutCheckOldPass")
    public JAXBElement<ChangePassWithoutCheckOldPass> createChangePassWithoutCheckOldPass(ChangePassWithoutCheckOldPass value) {
        return new JAXBElement<ChangePassWithoutCheckOldPass>(_ChangePassWithoutCheckOldPass_QNAME, ChangePassWithoutCheckOldPass.class, null, value);
    }

}
