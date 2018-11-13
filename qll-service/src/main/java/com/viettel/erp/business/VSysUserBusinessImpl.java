package com.viettel.erp.business;
 
import com.viettel.erp.bo.VSysUserBO;
import com.viettel.erp.dao.VSysUserDAO;
import com.viettel.erp.dto.VSysUserDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("vSysUserBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class VSysUserBusinessImpl extends BaseFWBusinessImpl<VSysUserDAO,VSysUserDTO, VSysUserBO> implements VSysUserBusiness {

    @Autowired
    private VSysUserDAO vSysUserDAO;
     
    public VSysUserBusinessImpl() {
        tModel = new VSysUserBO();
        tDAO = vSysUserDAO;
    }

    @Override
    public VSysUserDAO gettDAO() {
        return vSysUserDAO;
    }

    @Override
    public long count() {
        return vSysUserDAO.count("VSysUserBO", null);
    }

	public VSysUserDTO getUserByLoginName(String loginName) {
		return vSysUserDAO.getUserByLoginName(loginName); 
	}
    
}
