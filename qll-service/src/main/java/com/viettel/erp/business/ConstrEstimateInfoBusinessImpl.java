package com.viettel.erp.business;
 
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.viettel.erp.bo.ConstrEstimateInfoBO;
import com.viettel.erp.dao.ConstrEstimateInfoDAO;
import com.viettel.erp.dto.ConstrEstimateInfoDTO;
import com.viettel.erp.dto.EstimatesWorkItemsDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("constrEstimateInfoBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ConstrEstimateInfoBusinessImpl extends BaseFWBusinessImpl<ConstrEstimateInfoDAO,ConstrEstimateInfoDTO, ConstrEstimateInfoBO> implements ConstrEstimateInfoBusiness {

    @Autowired
    private ConstrEstimateInfoDAO constrEstimateInfoDAO;
    

     
    public ConstrEstimateInfoBusinessImpl() {
        tModel = new ConstrEstimateInfoBO();
        tDAO = constrEstimateInfoDAO;
    }

    @Override
    public ConstrEstimateInfoDAO gettDAO() {
        return constrEstimateInfoDAO;
    }

    @Override
    public long count() {
        return constrEstimateInfoDAO.count("ConstrEstimateInfoBO", null);
    }

    
    public List<ConstrEstimateInfoDTO> getAllandSearch(ConstrEstimateInfoDTO obj) {
 		return Lists.newArrayList(Iterables.transform(constrEstimateInfoDAO.getAllandSearch(obj), arg0 -> arg0.toDTO()));
 	}
    
}
