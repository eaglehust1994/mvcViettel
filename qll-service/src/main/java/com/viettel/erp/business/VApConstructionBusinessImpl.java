package com.viettel.erp.business;
 
import com.viettel.erp.bo.VApConstructionBO;
import com.viettel.erp.dao.VApConstructionDAO;
import com.viettel.erp.dto.VApConstructionDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("vApConstructionBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class VApConstructionBusinessImpl extends BaseFWBusinessImpl<VApConstructionDAO,VApConstructionDTO, VApConstructionBO> implements VApConstructionBusiness {

    @Autowired
    private VApConstructionDAO vApConstructionDAO;
    

     
    public VApConstructionBusinessImpl() {
        tModel = new VApConstructionBO();
        tDAO = vApConstructionDAO;
    }

    @Override
    public VApConstructionDAO gettDAO() {
        return vApConstructionDAO;
    }

    @Override
    public long count() {
        return vApConstructionDAO.count("VApConstructionBO", null);
    }

    

    
}
