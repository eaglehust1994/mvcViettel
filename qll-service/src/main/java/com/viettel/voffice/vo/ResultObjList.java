package com.viettel.voffice.vo;


import java.util.List;

public class ResultObjList
{
  private List<ResultObj> lstResultObj;
  private String appCode;
  
  public String getListTextId()
  {
    String result = "-1";
    if (this.lstResultObj != null)
    {
      int size = this.lstResultObj.size();
      for (int i = 0; i < size; i++)
      {
        ResultObj obj = (ResultObj)this.lstResultObj.get(i);
        result = result + "," + obj.getVoTextId();
      }
    }
    return result;
  }
  
  public String getAppCode()
  {
    return this.appCode;
  }
  
  public void setAppCode(String appCode)
  {
    this.appCode = appCode;
  }
  
  public List<ResultObj> getLstResultObj()
  {
    return this.lstResultObj;
  }
  
  public void setLstResultObj(List<ResultObj> lstResultObj)
  {
    this.lstResultObj = lstResultObj;
  }
}
