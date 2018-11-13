package com.viettel.wms.business;
 
import com.viettel.wms.bo.DomainDataBO;
import com.viettel.wms.dao.DomainDataDAO;
import com.viettel.wms.dto.DomainDataDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("domainDataBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class DomainDataBusinessImpl extends BaseFWBusinessImpl<DomainDataDAO,DomainDataDTO, DomainDataBO> implements DomainDataBusiness {

    @Autowired
    private DomainDataDAO domainDataDAO;
    

     
    public DomainDataBusinessImpl() {
        tModel = new DomainDataBO();
        tDAO = domainDataDAO;
    }

    @Override
    public DomainDataDAO gettDAO() {
        return domainDataDAO;
    }

    @Override
    public long count() {
        return domainDataDAO.count("DomainDataBO", null);
    }

    

    
}
