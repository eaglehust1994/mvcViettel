package com.viettel.erp.business;
 
import com.viettel.erp.bo.IntergratedContractBO;
import com.viettel.erp.dao.IntergratedContractDAO;
import com.viettel.erp.dto.CatEmployeeDTO;
import com.viettel.erp.dto.CatPartnersDTO;
import com.viettel.erp.dto.IntergratedContractDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("intergratedContractBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class IntergratedContractBusinessImpl extends BaseFWBusinessImpl<IntergratedContractDAO,IntergratedContractDTO, IntergratedContractBO> implements IntergratedContractBusiness {

    @Autowired
    private IntergratedContractDAO intergratedContractDAO;
    

     
    public IntergratedContractBusinessImpl() {
        tModel = new IntergratedContractBO();
        tDAO = intergratedContractDAO;
    }

    @Override
    public IntergratedContractDAO gettDAO() {
        return intergratedContractDAO;
    }

    @Override
    public long count() {
        return intergratedContractDAO.count("IntergratedContractBO", null);
    }
    
    @Override
    public List<IntergratedContractDTO> getIntergratedContractConstrt(IntergratedContractDTO dto) {
        return intergratedContractDAO.getIntergratedContractConstrt(dto);
    }

	public List<CatPartnersDTO> getPartner(CatEmployeeDTO dto) {
		return intergratedContractDAO.getPartner(dto);
	}

    

    
}
