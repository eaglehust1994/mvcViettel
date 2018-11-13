package com.viettel.erp.business;
 
import com.viettel.erp.bo.CatMerchandiseBO;
import com.viettel.erp.dao.CatMerchandiseDAO;
import com.viettel.erp.dto.CatMerchandiseDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("catMerchandiseBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CatMerchandiseBusinessImpl extends BaseFWBusinessImpl<CatMerchandiseDAO,CatMerchandiseDTO, CatMerchandiseBO> implements CatMerchandiseBusiness {

    @Autowired
    private CatMerchandiseDAO catMerchandiseDAO;
    

     
    public CatMerchandiseBusinessImpl() {
        tModel = new CatMerchandiseBO();
        tDAO = catMerchandiseDAO;
    }

    @Override
    public CatMerchandiseDAO gettDAO() {
        return catMerchandiseDAO;
    }

    @Override
    public long count() {
        return catMerchandiseDAO.count("CatMerchandiseBO", null);
    }

    

    
}
