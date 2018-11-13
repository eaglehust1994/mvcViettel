package com.viettel.wms.business;
 
import com.viettel.wms.bo.DomainTypeBO;
import com.viettel.wms.dao.DomainTypeDAO;
import com.viettel.wms.dto.DomainTypeDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("domainTypeBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class DomainTypeBusinessImpl extends BaseFWBusinessImpl<DomainTypeDAO,DomainTypeDTO, DomainTypeBO> implements DomainTypeBusiness {

    @Autowired
    private DomainTypeDAO domainTypeDAO;
    

     
    public DomainTypeBusinessImpl() {
        tModel = new DomainTypeBO();
        tDAO = domainTypeDAO;
    }

    @Override
    public DomainTypeDAO gettDAO() {
        return domainTypeDAO;
    }

    @Override
    public long count() {
        return domainTypeDAO.count("DomainTypeBO", null);
    }

    

    
}
