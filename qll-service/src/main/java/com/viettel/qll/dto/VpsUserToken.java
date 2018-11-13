package com.viettel.qll.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.viettel.ktts.vps.IUserToken;
import com.viettel.ktts.vps.VpsMenu;
import com.viettel.vps.webservice.AuthorizedData;
import com.viettel.vps.webservice.MenuBO;

public final class VpsUserToken
  implements IUserToken
{
  private AuthorizedData authorizedData;
  public List<VpsMenu> vpsGrantedMenu = new ArrayList();
  private List<VpsMenu> parentMenu;

  public AuthorizedData getAuthorizedData()
  {
    return this.authorizedData;
  }

  public void setAuthorizedData(AuthorizedData authorizedData) {
    this.authorizedData = authorizedData;
  }

  public VpsUserToken(AuthorizedData data) {
    this.authorizedData = data;
    initMenu(this.authorizedData);
  }

  protected void initMenu(AuthorizedData data)
  {
    if ((data != null) && (data.getErrorCode() == null)) {
      for (MenuBO parentObj : data.getGrantedMenus()) {
        this.vpsGrantedMenu.add(new VpsMenu(parentObj));
      }
      if ((this.vpsGrantedMenu != null) && (this.vpsGrantedMenu.size() > 0)) {
        for (VpsMenu parentObj : this.vpsGrantedMenu) {
          List childList = new ArrayList();
          for (VpsMenu childObject : this.vpsGrantedMenu)
          {
            if ((childObject.getParentId() != null) && (childObject.getParentId().equals(parentObj.getMenuId())))
            {
              childList.add(childObject);
            }
          }
          Collections.sort(childList);
          parentObj.setChildMenu(childList);
        }

        ArrayList parentObject = VpsMenu.findFirstLevelMenus(this.vpsGrantedMenu);
        Collections.sort(parentObject);
        setParentMenu(parentObject);
      }
    }
  }

  public String getErrorCode()
  {
    return this.authorizedData.getErrorCode();
  }

  public List<VpsMenu> getParentMenu()
  {
    return this.parentMenu;
  }

  public void setParentMenu(List<VpsMenu> parentMenu) {
    this.parentMenu = parentMenu;
  }

  public String getUserName()
  {
    return this.authorizedData.getUser().getLoginName();
  }

  public String getFullName()
  {
    return this.authorizedData.getUser().getFullName();
  }

  public String getEmail()
  {
    return this.authorizedData.getUser().getEmail();
  }

  public String getPhoneNumber()
  {
    return this.authorizedData.getUser().getPhoneNumber();
  }

  public Long getSysUserId()
  {
    return this.authorizedData.getUser().getSysUserId();
  }

  public String getEmployeeCode()
  {
    return this.authorizedData.getUser().getEmployeeCode();
  }
}