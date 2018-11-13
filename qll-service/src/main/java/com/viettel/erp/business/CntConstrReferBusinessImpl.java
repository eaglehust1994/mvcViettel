package com.viettel.erp.business;
 
import com.viettel.erp.bo.CntConstrReferBO;
import com.viettel.erp.dao.CntConstrReferDAO;
import com.viettel.erp.dto.CntConstrReferDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("cntConstrReferBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CntConstrReferBusinessImpl extends BaseFWBusinessImpl<CntConstrReferDAO,CntConstrReferDTO, CntConstrReferBO> implements CntConstrReferBusiness {

    @Autowired
    private CntConstrReferDAO cntConstrReferDAO;
    

     
    public CntConstrReferBusinessImpl() {
        tModel = new CntConstrReferBO();
        tDAO = cntConstrReferDAO;
    }

    @Override
    public CntConstrReferDAO gettDAO() {
        return cntConstrReferDAO;
    }

    @Override
    public long count() {
        return cntConstrReferDAO.count("CntConstrReferBO", null);
    }

    

    
}
