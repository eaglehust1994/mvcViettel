package com.viettel.Common.CommonBussiness;

import com.viettel.Common.CommonDTO.SysRoleDTO;
import com.viettel.service.base.dto.DataListDTO;

public interface SysRoleBusiness {

    long count();
    DataListDTO doSearchSysRole(SysRoleDTO obj);

}
