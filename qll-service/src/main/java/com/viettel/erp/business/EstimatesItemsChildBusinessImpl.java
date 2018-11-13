package com.viettel.erp.business;
 
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.viettel.erp.bo.EstimatesItemsChildBO;
import com.viettel.erp.dao.EstimatesItemsChildDAO;
import com.viettel.erp.dto.ConstrEstimateInfoDTO;
import com.viettel.erp.dto.EstimatesItemsChildDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("estimatesItemsChildBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class EstimatesItemsChildBusinessImpl extends BaseFWBusinessImpl<EstimatesItemsChildDAO,EstimatesItemsChildDTO, EstimatesItemsChildBO> implements EstimatesItemsChildBusiness {

    @Autowired
    private EstimatesItemsChildDAO estimatesItemsChildDAO;
    

     
    public EstimatesItemsChildBusinessImpl() {
        tModel = new EstimatesItemsChildBO();
        tDAO = estimatesItemsChildDAO;
    }

    @Override
    public EstimatesItemsChildDAO gettDAO() {
        return estimatesItemsChildDAO;
    }

    @Override
    public long count() {
        return estimatesItemsChildDAO.count("EstimatesItemsChildBO", null);
    }

    
  //haibt  	
  	@Override
  	public List<EstimatesItemsChildDTO> getAllItemsChildInContruct(Long constructionId) {
  		// TODO Auto-generated method stub
  		return estimatesItemsChildDAO.getAllItemsInContruct(constructionId);
  	}

	public List<EstimatesItemsChildDTO> getListEstimateItemschild(EstimatesItemsChildDTO rightDTO) {		
		return estimatesItemsChildDAO.getListEstimateItemschild(rightDTO);
	}
    public List<EstimatesItemsChildDTO> getAllandSearch(EstimatesItemsChildDTO obj) {
 		return Lists.newArrayList(Iterables.transform(estimatesItemsChildDAO.getAllandSearch(obj), arg0 -> arg0.toDTO()));
 	}
}
