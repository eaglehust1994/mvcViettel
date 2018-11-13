package com.viettel.erp.business;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.viettel.erp.bo.ConstructionAcceptanceBO;
import com.viettel.erp.bo.DetailSettlementEvaluateBO;
import com.viettel.erp.dao.ConstructionAcceptanceDAO;
import com.viettel.erp.dao.EstimatesWorkItemsDAO;
import com.viettel.erp.dto.ConstructionAcceptanceDTO;
import com.viettel.erp.dto.DetailSettlementEvaluateDTO;
import com.viettel.erp.dto.EstimatesWorkItemsDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;


@Service("constructionAcceptanceBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ConstructionAcceptanceBusinessImpl extends BaseFWBusinessImpl<ConstructionAcceptanceDAO,ConstructionAcceptanceDTO, ConstructionAcceptanceBO> implements ConstructionAcceptanceBusiness {

    @Autowired
    private ConstructionAcceptanceDAO constructionAcceptanceDAO;
    @Autowired
    private EstimatesWorkItemsDAO estimatesWorkItemsDAO;

     
    public ConstructionAcceptanceBusinessImpl() {
        tModel = new ConstructionAcceptanceBO();
        tDAO = constructionAcceptanceDAO;
    }

    @Override
    public ConstructionAcceptanceDAO gettDAO() {
        return constructionAcceptanceDAO;
    }

    @Override
    public long count() {
        return constructionAcceptanceDAO.count("ConstructionAcceptanceBO", null);
    }

	@Override
	public List<ConstructionAcceptanceDTO> findByConstructId(Long constructId) {
		return constructionAcceptanceDAO.findByConstructId(constructId);
	}

	   //dodt
	public List<ConstructionAcceptanceDTO> getAllC(ConstructionAcceptanceDTO obj) {
	 		//return Lists.newArrayList(Iterables.transform(constructionAcceptanceDAO.getAllC(obj), arg0 -> arg0.toDTO()));
		
		System.out.println(obj.getContractId() +"------------------");
		
		return constructionAcceptanceDAO.getAllC(obj);
	}
	

    public Long saveTable(ConstructionAcceptanceDTO ConstrAcc) throws Exception{
      Long constructionAcceptanceId =	constructionAcceptanceDAO.saveTable(ConstrAcc);  	
      return constructionAcceptanceId;
    }
    
    public Long deleteTable(ConstructionAcceptanceDTO ConstrAcc) throws Exception{
        Long constructionAcceptanceId =	constructionAcceptanceDAO.deleteTable(ConstrAcc);  	
        return constructionAcceptanceId;
      }
    
	
	public ConstructionAcceptanceDTO getByOneId(Long id) {
 		return constructionAcceptanceDAO.getByOneId(id);
 	}
	
	public String getDocumentPath(Long id) {
 		return constructionAcceptanceDAO.getDocumentPath(id);
 	}
	
	
	public String autoGenCode(){
		return constructionAcceptanceDAO.autoGenCode();
	}

	@Override
	public boolean updateIsActive(Long constrAcceptanceId) {
		// TODO Auto-generated method stub
		return constructionAcceptanceDAO.updateIsActive(constrAcceptanceId);
	}
	
	
}
