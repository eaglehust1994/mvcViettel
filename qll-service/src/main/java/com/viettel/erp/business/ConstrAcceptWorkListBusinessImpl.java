package com.viettel.erp.business;
 
import com.viettel.erp.bo.ConstrAcceptWorkListBO;
import com.viettel.erp.dao.ConstrAcceptWorkListDAO;
import com.viettel.erp.dto.ConstrAcceptWorkListDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("constrAcceptWorkListBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ConstrAcceptWorkListBusinessImpl extends BaseFWBusinessImpl<ConstrAcceptWorkListDAO,ConstrAcceptWorkListDTO, ConstrAcceptWorkListBO> implements ConstrAcceptWorkListBusiness {

    @Autowired
    private ConstrAcceptWorkListDAO constrAcceptWorkListDAO;
    

     
    public ConstrAcceptWorkListBusinessImpl() {
        tModel = new ConstrAcceptWorkListBO();
        tDAO = constrAcceptWorkListDAO;
    }

    @Override
    public ConstrAcceptWorkListDAO gettDAO() {
        return constrAcceptWorkListDAO;
    }

    @Override
    public long count() {
        return constrAcceptWorkListDAO.count("ConstrAcceptWorkListBO", null);
    }

    @Override
    public  List<ConstrAcceptWorkListDTO> getAllbyConstructId(Long constructId){
    	  return constrAcceptWorkListDAO.getAllbyConstructId(constructId);
    }
    
    
	@Override
	public List<ConstrAcceptWorkListDTO> getProposedSettlementById(Long id) {
		return constrAcceptWorkListDAO.getProposedSettlement(id);
	}

}
