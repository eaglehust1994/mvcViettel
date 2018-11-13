package com.viettel.erp.business;
 
import com.viettel.erp.bo.CatConstrTypesBO;
import com.viettel.erp.dao.CatConstrTypesDAO;
import com.viettel.erp.dto.CatConstrTypesDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("catConstrTypesBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CatConstrTypesBusinessImpl extends BaseFWBusinessImpl<CatConstrTypesDAO,CatConstrTypesDTO, CatConstrTypesBO> implements CatConstrTypesBusiness {

    @Autowired
    private CatConstrTypesDAO catConstrTypesDAO;
    

     
    public CatConstrTypesBusinessImpl() {
        tModel = new CatConstrTypesBO();
        tDAO = catConstrTypesDAO;
    }

    @Override
    public CatConstrTypesDAO gettDAO() {
        return catConstrTypesDAO;
    }

    @Override
    public long count() {
        return catConstrTypesDAO.count("CatConstrTypesBO", null);
    }

    

    
}
