package com.viettel.erp.business;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.viettel.erp.bo.ConstrMerchandiseBO;
import com.viettel.erp.dao.ConstrMerchandiseDAO;
import com.viettel.erp.dto.ConstrMerchandiseDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;


@Service("constrMerchandiseBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ConstrMerchandiseBusinessImpl extends BaseFWBusinessImpl<ConstrMerchandiseDAO,ConstrMerchandiseDTO, ConstrMerchandiseBO> implements ConstrMerchandiseBusiness {

    @Autowired
    private ConstrMerchandiseDAO constrMerchandiseDAO;
    

     
    public ConstrMerchandiseBusinessImpl() {
        tModel = new ConstrMerchandiseBO();
        tDAO = constrMerchandiseDAO;
    }

    @Override
    public ConstrMerchandiseDAO gettDAO() {
        return constrMerchandiseDAO;
    }

    @Override
    public long count() {
        return constrMerchandiseDAO.count("ConstrMerchandiseBO", null);
    }

	@Override
	public List<ConstrMerchandiseDTO> gettValueConstrMerchandiseById(Long id) {
		return constrMerchandiseDAO.geValueConstrMerchandise(id);
	}
    
}
