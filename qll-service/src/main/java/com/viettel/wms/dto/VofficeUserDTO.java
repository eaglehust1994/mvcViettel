package com.viettel.wms.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.viettel.voffice.ws_autosign.service.StaffBO;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="vof2EntityUser", propOrder={"adOrgId", "adOrgName", "aliasName", "fullName", "homePhone", "isDefault", "isPublicText", "isSecrectaryVo2", "jobTile", "jobTitle", "languageCode", "loginName", "mobileNumber", "pathDepart2", "signImageIndex", "specializedOrgId", "staff", "strAddress", "strCANumber", "strCardNumber", "strDateOfBirth", "strEmail", "strIdentification", "sysOrgId", "sysOrgName", "sysRoleId", "userId", "userLanguage"})
public class VofficeUserDTO
{
  protected Long adOrgId;
  protected String adOrgName;
  protected String aliasName;
  protected String fullName;
  protected String homePhone;
  protected Long isDefault;
  protected Long isPublicText;
  protected Boolean isSecrectaryVo2;
  protected String jobTile;
  protected String jobTitle;
  protected String languageCode;
  protected String loginName;
  protected String mobileNumber;
  protected String pathDepart2;
  protected Long signImageIndex;
  protected Long specializedOrgId;
  protected StaffBO staff;
  protected String strAddress;
  protected String strCANumber;
  protected String strCardNumber;
  protected String strDateOfBirth;
  protected String strEmail;
  protected String strIdentification;
  protected Long sysOrgId;
  protected String sysOrgName;
  protected Long sysRoleId;
  protected Long userId;
  protected String userLanguage;
  private String sysRoleName;
  
  public String getSysRoleName() {
	return sysRoleName;
}

public void setSysRoleName(String sysRoleName) {
	this.sysRoleName = sysRoleName;
}

public Long getAdOrgId()
  {
    return this.adOrgId;
  }
  
  public void setAdOrgId(Long value)
  {
    this.adOrgId = value;
  }
  
  public String getAdOrgName()
  {
    return this.adOrgName;
  }
  
  public void setAdOrgName(String value)
  {
    this.adOrgName = value;
  }
  
  public String getAliasName()
  {
    return this.aliasName;
  }
  
  public void setAliasName(String value)
  {
    this.aliasName = value;
  }
  
  public String getFullName()
  {
    return this.fullName;
  }
  
  public void setFullName(String value)
  {
    this.fullName = value;
  }
  
  public String getHomePhone()
  {
    return this.homePhone;
  }
  
  public void setHomePhone(String value)
  {
    this.homePhone = value;
  }
  
  public Long getIsDefault()
  {
    return this.isDefault;
  }
  
  public void setIsDefault(Long value)
  {
    this.isDefault = value;
  }
  
  public Long getIsPublicText()
  {
    return this.isPublicText;
  }
  
  public void setIsPublicText(Long value)
  {
    this.isPublicText = value;
  }
  
  public Boolean isIsSecrectaryVo2()
  {
    return this.isSecrectaryVo2;
  }
  
  public void setIsSecrectaryVo2(Boolean value)
  {
    this.isSecrectaryVo2 = value;
  }
  
  public String getJobTile()
  {
    return this.jobTile;
  }
  
  public void setJobTile(String value)
  {
    this.jobTile = value;
  }
  
  public String getJobTitle()
  {
    return this.jobTitle;
  }
  
  public void setJobTitle(String value)
  {
    this.jobTitle = value;
  }
  
  public String getLanguageCode()
  {
    return this.languageCode;
  }
  
  public void setLanguageCode(String value)
  {
    this.languageCode = value;
  }
  
  public String getLoginName()
  {
    return this.loginName;
  }
  
  public void setLoginName(String value)
  {
    this.loginName = value;
  }
  
  public String getMobileNumber()
  {
    return this.mobileNumber;
  }
  
  public void setMobileNumber(String value)
  {
    this.mobileNumber = value;
  }
  
  public String getPathDepart2()
  {
    return this.pathDepart2;
  }
  
  public void setPathDepart2(String value)
  {
    this.pathDepart2 = value;
  }
  
  public Long getSignImageIndex()
  {
    return this.signImageIndex;
  }
  
  public void setSignImageIndex(Long value)
  {
    this.signImageIndex = value;
  }
  
  public Long getSpecializedOrgId()
  {
    return this.specializedOrgId;
  }
  
  public void setSpecializedOrgId(Long value)
  {
    this.specializedOrgId = value;
  }
  
  public StaffBO getStaff()
  {
    return this.staff;
  }
  
  public void setStaff(StaffBO value)
  {
    this.staff = value;
  }
  
  public String getStrAddress()
  {
    return this.strAddress;
  }
  
  public void setStrAddress(String value)
  {
    this.strAddress = value;
  }
  
  public String getStrCANumber()
  {
    return this.strCANumber;
  }
  
  public void setStrCANumber(String value)
  {
    this.strCANumber = value;
  }
  
  public String getStrCardNumber()
  {
    return this.strCardNumber;
  }
  
  public void setStrCardNumber(String value)
  {
    this.strCardNumber = value;
  }
  
  public String getStrDateOfBirth()
  {
    return this.strDateOfBirth;
  }
  
  public void setStrDateOfBirth(String value)
  {
    this.strDateOfBirth = value;
  }
  
  public String getStrEmail()
  {
    return this.strEmail;
  }
  
  public void setStrEmail(String value)
  {
    this.strEmail = value;
  }
  
  public String getStrIdentification()
  {
    return this.strIdentification;
  }
  
  public void setStrIdentification(String value)
  {
    this.strIdentification = value;
  }
  
  public Long getSysOrgId()
  {
    return this.sysOrgId;
  }
  
  public void setSysOrgId(Long value)
  {
    this.sysOrgId = value;
  }
  
  public String getSysOrgName()
  {
    return this.sysOrgName;
  }
  
  public void setSysOrgName(String value)
  {
    this.sysOrgName = value;
  }
  
  public Long getSysRoleId()
  {
    return this.sysRoleId;
  }
  
  public void setSysRoleId(Long value)
  {
    this.sysRoleId = value;
  }
  
  public Long getUserId()
  {
    return this.userId;
  }
  
  public void setUserId(Long value)
  {
    this.userId = value;
  }
  
  public String getUserLanguage()
  {
    return this.userLanguage;
  }
  
  public void setUserLanguage(String value)
  {
    this.userLanguage = value;
  }
}
