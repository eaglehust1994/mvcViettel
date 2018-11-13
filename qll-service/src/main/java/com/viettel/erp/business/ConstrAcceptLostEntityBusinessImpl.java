package com.viettel.erp.business;
 
import com.viettel.erp.bo.ConstrAcceptLostEntityBO;
import com.viettel.erp.dao.ConstrAcceptLostEntityDAO;
import com.viettel.erp.dto.ConstrAcceptLostEntityDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("constrAcceptLostEntityBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ConstrAcceptLostEntityBusinessImpl extends BaseFWBusinessImpl<ConstrAcceptLostEntityDAO,ConstrAcceptLostEntityDTO, ConstrAcceptLostEntityBO> implements ConstrAcceptLostEntityBusiness {

    @Autowired
    private ConstrAcceptLostEntityDAO constrAcceptLostEntityDAO;
    

     
    public ConstrAcceptLostEntityBusinessImpl() {
        tModel = new ConstrAcceptLostEntityBO();
        tDAO = constrAcceptLostEntityDAO;
    }

    @Override
    public ConstrAcceptLostEntityDAO gettDAO() {
        return constrAcceptLostEntityDAO;
    }

    @Override
    public long count() {
        return constrAcceptLostEntityDAO.count("ConstrAcceptLostEntityBO", null);
    }

    

    
}
