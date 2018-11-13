package com.viettel.erp.business;
 
import com.viettel.erp.bo.CatCurrencyBO;
import com.viettel.erp.dao.CatCurrencyDAO;
import com.viettel.erp.dto.CatCurrencyDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("catCurrencyBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CatCurrencyBusinessImpl extends BaseFWBusinessImpl<CatCurrencyDAO,CatCurrencyDTO, CatCurrencyBO> implements CatCurrencyBusiness {

    @Autowired
    private CatCurrencyDAO catCurrencyDAO;
    

     
    public CatCurrencyBusinessImpl() {
        tModel = new CatCurrencyBO();
        tDAO = catCurrencyDAO;
    }

    @Override
    public CatCurrencyDAO gettDAO() {
        return catCurrencyDAO;
    }

    @Override
    public long count() {
        return catCurrencyDAO.count("CatCurrencyBO", null);
    }

    

    
}
