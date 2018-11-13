package com.viettel.erp.business;
 
import com.viettel.erp.bo.WorkItemsAcceptanceBO;
import com.viettel.erp.dao.WorkItemsAcceptanceDAO;
import com.viettel.erp.dto.WorkItemsAcceptanceDTO;
import com.viettel.erp.dto.approDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("workItemsAcceptanceBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class WorkItemsAcceptanceBusinessImpl extends BaseFWBusinessImpl<WorkItemsAcceptanceDAO,WorkItemsAcceptanceDTO, WorkItemsAcceptanceBO> implements WorkItemsAcceptanceBusiness {

    @Autowired
    private WorkItemsAcceptanceDAO workItemsAcceptanceDAO;
    

     
    public WorkItemsAcceptanceBusinessImpl() {
        tModel = new WorkItemsAcceptanceBO();
        tDAO = workItemsAcceptanceDAO;
    }

    @Override
    public WorkItemsAcceptanceDAO gettDAO() {
        return workItemsAcceptanceDAO;
    }

    @Override
    public long count() {
        return workItemsAcceptanceDAO.count("WorkItemsAcceptanceBO", null);
    }

    //tungpv
    
    @Override
    public List<WorkItemsAcceptanceDTO> doSearchWorkItemsAcceptance(WorkItemsAcceptanceDTO criteria){
    	return workItemsAcceptanceDAO.doSearchWorkItemsAcceptance(criteria);
    }
    
    public WorkItemsAcceptanceDTO getDataExport(WorkItemsAcceptanceDTO dto){
    	return workItemsAcceptanceDAO.getDataExport(dto);
    }
    
    @Override
	public boolean deleteWorkItemAcceptList(List<String> listString) {
		return workItemsAcceptanceDAO.deleteWorkItemAcceptList(listString);
	}
    
    @Override
	public boolean addWorkItemAcceptList(WorkItemsAcceptanceDTO criteria) {
		return workItemsAcceptanceDAO.addWorkItemAcceptList(criteria);
	}
    
    @Override
	public boolean updateIsActive(List<Long> listId) {
		return workItemsAcceptanceDAO.updateIsActive(listId);
	}
	
	@Override
	public Long appro(approDTO obj) {
		return workItemsAcceptanceDAO.appro(obj);
	}
	
	public Long saveTable( WorkItemsAcceptanceDTO  obj) throws Exception {
	       Long id = workItemsAcceptanceDAO.saveTable(obj);
	       return id;
	}
    //end tungpv
}
