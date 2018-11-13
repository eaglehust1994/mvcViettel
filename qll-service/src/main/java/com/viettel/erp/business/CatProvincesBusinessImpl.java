package com.viettel.erp.business;
 
import com.viettel.erp.bo.CatProvincesBO;
import com.viettel.erp.dao.CatProvincesDAO;
import com.viettel.erp.dto.CatProvincesDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("catProvincesBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CatProvincesBusinessImpl extends BaseFWBusinessImpl<CatProvincesDAO,CatProvincesDTO, CatProvincesBO> implements CatProvincesBusiness {

    @Autowired
    private CatProvincesDAO catProvincesDAO;
    

     
    public CatProvincesBusinessImpl() {
        tModel = new CatProvincesBO();
        tDAO = catProvincesDAO;
    }

    @Override
    public CatProvincesDAO gettDAO() {
        return catProvincesDAO;
    }

    @Override
    public long count() {
        return catProvincesDAO.count("CatProvincesBO", null);
    }

    

    
}
