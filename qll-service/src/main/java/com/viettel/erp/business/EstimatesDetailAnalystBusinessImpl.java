package com.viettel.erp.business;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.viettel.erp.bo.EstimatesDetailAnalystBO;
import com.viettel.erp.dao.EstimatesDetailAnalystDAO;
import com.viettel.erp.dto.EstimatesDetailAnalystDTO;
import com.viettel.erp.dto.EstimatesWorkItemsDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;


@Service("estimatesDetailAnalystBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class EstimatesDetailAnalystBusinessImpl extends BaseFWBusinessImpl<EstimatesDetailAnalystDAO,EstimatesDetailAnalystDTO, EstimatesDetailAnalystBO> implements EstimatesDetailAnalystBusiness {

    @Autowired
    private EstimatesDetailAnalystDAO estimatesDetailAnalystDAO;
    

     
    public EstimatesDetailAnalystBusinessImpl() {
        tModel = new EstimatesDetailAnalystBO();
        tDAO = estimatesDetailAnalystDAO;
    }

    @Override
    public EstimatesDetailAnalystDAO gettDAO() {
        return estimatesDetailAnalystDAO;
    }

    @Override
    public long count() {
        return estimatesDetailAnalystDAO.count("EstimatesDetailAnalystBO", null);
    }

	@Override
	public List<EstimatesDetailAnalystDTO> getAcceptanceList() {
		// TODO Auto-generated method stub
		return estimatesDetailAnalystDAO.getAcceptanceList();
	}   
	@Override
	public List<EstimatesDetailAnalystDTO> getAcceptanceListById(Long constructId) {
		return estimatesDetailAnalystDAO.getAcceptanceListById(constructId);
	}
	
	@Override
	public List<EstimatesDetailAnalystDTO> doSearchEstimatesDetailAnalyst(EstimatesDetailAnalystDTO criteria) {
		return estimatesDetailAnalystDAO.doSearchEstimatesDetailAnalyst(criteria);
	}
	
	
    public List<EstimatesDetailAnalystDTO> getEstimatesDetailAnalystLst(EstimatesDetailAnalystDTO obj) {
 		return Lists.newArrayList(Iterables.transform(estimatesDetailAnalystDAO.getEstimatesDetailAnalystLst(obj), arg0 -> arg0.toDTO()));
 	}
}
