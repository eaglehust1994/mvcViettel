package com.viettel.erp.business;
 
import com.viettel.erp.bo.ConstrAcceptanceRequestBO;
import com.viettel.erp.dao.ConstrAcceptanceRequestDAO;
import com.viettel.erp.dto.AMaterialHandoverDTO;
import com.viettel.erp.dto.ConstrAcceptanceRequestDTO;
import com.viettel.erp.dto.approDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("constrAcceptanceRequestBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ConstrAcceptanceRequestBusinessImpl extends BaseFWBusinessImpl<ConstrAcceptanceRequestDAO,ConstrAcceptanceRequestDTO, ConstrAcceptanceRequestBO> implements ConstrAcceptanceRequestBusiness {

    @Autowired
    private ConstrAcceptanceRequestDAO constrAcceptanceRequestDAO;
    

     
    public ConstrAcceptanceRequestBusinessImpl() {
        tModel = new ConstrAcceptanceRequestBO();
        tDAO = constrAcceptanceRequestDAO;
    }

    @Override
    public ConstrAcceptanceRequestDAO gettDAO() {
        return constrAcceptanceRequestDAO;
    }
    
    @Transactional
    @Override
    public Long save(ConstrAcceptanceRequestDTO costCenterBO) {
    	return super.save(costCenterBO);
    }
    
    public Long saveTable(ConstrAcceptanceRequestDTO  obj) throws Exception {
	       Long id = constrAcceptanceRequestDAO.saveTable(obj);
	       return id;
	}

    @Override
    public long count() {
        return constrAcceptanceRequestDAO.count("ConstrAcceptanceRequestBO", null);
    }

	public String autoGenCode() {
		return constrAcceptanceRequestDAO.autoGenCode();
	}

	public List<ConstrAcceptanceRequestDTO> listConstrAcceptanceReq(ConstrAcceptanceRequestDTO obj) {
		return constrAcceptanceRequestDAO.listConstrAcceptanceReq(obj);
	}

	public String deleteConstrAcceptanceReq(List<String> listCode) {
		return constrAcceptanceRequestDAO.deleteConstrAcceptanceReq(listCode);
	}

	public Long appro(approDTO dto) {
		return constrAcceptanceRequestDAO.appro(dto);
	}

	public Long deleteTable(ConstrAcceptanceRequestDTO obj) throws Exception {
		return constrAcceptanceRequestDAO.deleteTable(obj);
	}

    
}
