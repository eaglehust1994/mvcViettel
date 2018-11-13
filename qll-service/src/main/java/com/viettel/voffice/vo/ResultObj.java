package com.viettel.voffice.vo;


public class ResultObj
{
  private String transCode;
  private String actionDate;
  private String signComment;
  private String signStatus;
  private String documentCode;
  private String publishOganizationCode;
  private String lastSignEmail;
  private Long voTextId;
  private String publishDate;
  private String appCode;
  private String wsdl;
  
  public ResultObj() {}
  
  public ResultObj(String transCode, String signStatus)
  {
    this.transCode = transCode;
    this.signStatus = signStatus;
  }
  
  public ResultObj(String transCode, String signStatus, String actionDate)
  {
    this.transCode = transCode;
    this.actionDate = actionDate;
    this.signStatus = signStatus;
  }
  
  public ResultObj(String transCode, String signStatus, String actionDate, String signComment)
  {
    this.transCode = transCode;
    this.actionDate = actionDate;
    this.signComment = signComment;
    this.signStatus = signStatus;
  }
  
  public ResultObj(String transCode, String signStatus, String actionDate, String signComment, String registerNumber)
  {
    this.transCode = transCode;
    this.actionDate = actionDate;
    this.signComment = signComment;
    this.signStatus = signStatus;
    this.documentCode = registerNumber;
  }
  
  public ResultObj(String transCode, String signStatus, String actionDate, String signComment, String registerNumber, String publishUnit)
  {
    this.transCode = transCode;
    this.actionDate = actionDate;
    this.signComment = signComment;
    this.signStatus = signStatus;
    this.documentCode = registerNumber;
    this.publishOganizationCode = publishUnit;
  }
  
  public String toString()
  {
    String result = "transCode=" + this.transCode + "|" + "signStatus=" + this.signStatus + "|" + "actionDate=" + this.actionDate + "|" + "signComment=" + this.signComment + "|" + "documentCode=" + this.documentCode + "|" + "publishOganizationCode=" + this.publishOganizationCode + "\n";
    




    return result;
  }
  
  public String getActionDate()
  {
    return this.actionDate;
  }
  
  public void setActionDate(String actionDate)
  {
    this.actionDate = actionDate;
  }
  
  public String getDocumentCode()
  {
    return this.documentCode;
  }
  
  public void setDocumentCode(String documentCode)
  {
    this.documentCode = documentCode;
  }
  
  public String getLastSignEmail()
  {
    return this.lastSignEmail;
  }
  
  public void setLastSignEmail(String lastSignEmail)
  {
    this.lastSignEmail = lastSignEmail;
  }
  
  public String getPublishDate()
  {
    return this.publishDate;
  }
  
  public void setPublishDate(String publishDate)
  {
    this.publishDate = publishDate;
  }
  
  public String getPublishOganizationCode()
  {
    return this.publishOganizationCode;
  }
  
  public void setPublishOganizationCode(String publishOganizationCode)
  {
    this.publishOganizationCode = publishOganizationCode;
  }
  
  public String getSignComment()
  {
    return this.signComment;
  }
  
  public void setSignComment(String signComment)
  {
    this.signComment = signComment;
  }
  
  public String getSignStatus()
  {
    return this.signStatus;
  }
  
  public void setSignStatus(String signStatus)
  {
    this.signStatus = signStatus;
  }
  
  public String getTransCode()
  {
    return this.transCode;
  }
  
  public void setTransCode(String transCode)
  {
    this.transCode = transCode;
  }
  
  public Long getVoTextId()
  {
    return this.voTextId;
  }
  
  public void setVoTextId(Long voTextId)
  {
    this.voTextId = voTextId;
  }
  
  public String getAppCode()
  {
    return this.appCode;
  }
  
  public void setAppCode(String appCode)
  {
    this.appCode = appCode;
  }
  
  public String getWsdl()
  {
    return this.wsdl;
  }
  
  public void setWsdl(String wsdl)
  {
    this.wsdl = wsdl;
  }
}