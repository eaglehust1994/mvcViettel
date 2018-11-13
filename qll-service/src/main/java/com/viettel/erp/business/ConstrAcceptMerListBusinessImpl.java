package com.viettel.erp.business;
 
import com.viettel.erp.bo.ConstrAcceptMerListBO;
import com.viettel.erp.dao.ConstrAcceptMerListDAO;
import com.viettel.erp.dto.ConstrAcceptMerListDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("constrAcceptMerListBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ConstrAcceptMerListBusinessImpl extends BaseFWBusinessImpl<ConstrAcceptMerListDAO,ConstrAcceptMerListDTO, ConstrAcceptMerListBO> implements ConstrAcceptMerListBusiness {

    @Autowired
    private ConstrAcceptMerListDAO constrAcceptMerListDAO;
    

     
    public ConstrAcceptMerListBusinessImpl() {
        tModel = new ConstrAcceptMerListBO();
        tDAO = constrAcceptMerListDAO;
    }

    @Override
    public ConstrAcceptMerListDAO gettDAO() {
        return constrAcceptMerListDAO;
    }

    @Override
    public long count() {
        return constrAcceptMerListDAO.count("ConstrAcceptMerListBO", null);
    }

    

    
}
