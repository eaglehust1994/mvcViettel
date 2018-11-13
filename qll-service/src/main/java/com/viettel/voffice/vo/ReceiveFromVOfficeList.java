package com.viettel.voffice.vo;

import javax.jws.WebMethod;

public abstract interface ReceiveFromVOfficeList extends ReceiveFromVOffice
{
  @WebMethod
  public abstract Long returnMultiSignReult(ResultObjList paramResultObjList);
}
