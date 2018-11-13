package com.viettel.erp.business;
 
import com.viettel.erp.bo.CatStatusBO;
import com.viettel.erp.dao.CatStatusDAO;
import com.viettel.erp.dto.CatStatusDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("catStatusBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CatStatusBusinessImpl extends BaseFWBusinessImpl<CatStatusDAO,CatStatusDTO, CatStatusBO> implements CatStatusBusiness {

    @Autowired
    private CatStatusDAO catStatusDAO;
    

     
    public CatStatusBusinessImpl() {
        tModel = new CatStatusBO();
        tDAO = catStatusDAO;
    }

    @Override
    public CatStatusDAO gettDAO() {
        return catStatusDAO;
    }

    @Override
    public long count() {
        return catStatusDAO.count("CatStatusBO", null);
    }

    

    
}
