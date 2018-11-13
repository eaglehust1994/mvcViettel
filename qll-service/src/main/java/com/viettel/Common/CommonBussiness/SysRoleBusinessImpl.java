package com.viettel.Common.CommonBussiness;
 
import com.viettel.Common.CommonBO.SysRoleBO;
import com.viettel.Common.CommonDAO.SysRoleDAO;
import com.viettel.Common.CommonDTO.SysRoleDTO;
import com.viettel.Common.CommonDTO.UserRoleDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("sysRoleBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SysRoleBusinessImpl extends BaseFWBusinessImpl<SysRoleDAO,SysRoleDTO, SysRoleBO> implements SysRoleBusiness {

    @Autowired
    private SysRoleDAO sysRoleDAO;
    

     
    public SysRoleBusinessImpl() {
        tModel = new SysRoleBO();
        tDAO = sysRoleDAO;
    }

    @Override
    public SysRoleDAO gettDAO() {
        return sysRoleDAO;
    }

    @Override
    public long count() {
        return sysRoleDAO.count("SysRoleBO", null);
    }

	@Override
	public DataListDTO doSearchSysRole(SysRoleDTO obj) {
		List<SysRoleDTO> ls = sysRoleDAO.doSearchSysRole(obj);
		   DataListDTO data = new DataListDTO();
		   data.setData(ls);
		   data.setTotal(obj.getTotalRecord());
		   data.setSize(obj.getTotalRecord());
		   data.setStart(1);
		return data;
	}

    

    
}
