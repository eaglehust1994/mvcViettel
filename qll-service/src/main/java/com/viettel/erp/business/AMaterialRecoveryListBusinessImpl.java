package com.viettel.erp.business;
 
import com.viettel.erp.bo.AMaterialRecoveryListBO;
import com.viettel.erp.bo.AMaterialRecoveryMinutesBO;
import com.viettel.erp.dao.AMaterialRecoveryListDAO;
import com.viettel.erp.dto.AMaterialRecoveryListDTO;
import com.viettel.erp.dto.AMaterialRecoveryListModelDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("aMaterialRecoveryListBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AMaterialRecoveryListBusinessImpl extends BaseFWBusinessImpl<AMaterialRecoveryListDAO,AMaterialRecoveryListDTO, AMaterialRecoveryListBO> implements AMaterialRecoveryListBusiness {

    @Autowired
    private AMaterialRecoveryListDAO aMaterialRecoveryListDAO;
    

     
    public AMaterialRecoveryListBusinessImpl() {
        tModel = new AMaterialRecoveryListBO();
        tDAO = aMaterialRecoveryListDAO;
    }

    @Override
    public AMaterialRecoveryListDAO gettDAO() {
        return aMaterialRecoveryListDAO;
    }

    @Override
    public long count() {
        return aMaterialRecoveryListDAO.count("AMaterialRecoveryListBO", null);
    }

	@Override
	public List<AMaterialRecoveryListModelDTO> findByConstructId(Long constructId) {
		return aMaterialRecoveryListDAO.findByConstructId(constructId);
	}



    

    
}
