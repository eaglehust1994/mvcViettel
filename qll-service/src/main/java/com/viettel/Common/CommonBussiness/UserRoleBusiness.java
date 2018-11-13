package com.viettel.Common.CommonBussiness;

import java.util.List;

import com.viettel.Common.CommonDTO.UserRoleDTO;
import com.viettel.service.base.dto.DataListDTO;

public interface UserRoleBusiness {

    long count();
    DataListDTO doSearchUserRole(UserRoleDTO obj);
    List<UserRoleDTO> doSearchUserRoleData(UserRoleDTO obj);
    DataListDTO doSearchRole(UserRoleDTO obj);
    boolean deleteUserRoleData(UserRoleDTO obj);
    boolean insertUserRoleData(List<UserRoleDTO> obj);
    boolean insertRole(List<UserRoleDTO> obj);

}
