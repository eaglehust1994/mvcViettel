package com.viettel.voffice.vo;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style=SOAPBinding.Style.RPC)
public abstract interface ReceiveFromVOffice
{
  @WebMethod
  public abstract String hello(String paramString);
  
  @WebMethod
  public abstract Long returnSignReult(ResultObj paramResultObj);
}