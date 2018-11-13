package com.viettel.erp.business;
 
import com.viettel.erp.bo.UserEmployeeBO;
import com.viettel.erp.dao.UserEmployeeDAO;
import com.viettel.erp.dto.UserEmployeeDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("userEmployeeBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserEmployeeBusinessImpl extends BaseFWBusinessImpl<UserEmployeeDAO,UserEmployeeDTO, UserEmployeeBO> implements UserEmployeeBusiness {

    @Autowired
    private UserEmployeeDAO userEmployeeDAO;
    

     
    public UserEmployeeBusinessImpl() {
        tModel = new UserEmployeeBO();
        tDAO = userEmployeeDAO;
    }

    @Override
    public UserEmployeeDAO gettDAO() {
        return userEmployeeDAO;
    }

    @Override
    public long count() {
        return userEmployeeDAO.count("UserEmployeeBO", null);
    }

    

    
}
