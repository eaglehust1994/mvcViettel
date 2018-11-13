package com.viettel.erp.business;
 
import com.viettel.erp.bo.WorkItemsAcceptListBO;
import com.viettel.erp.dao.WorkItemsAcceptListDAO;
import com.viettel.erp.dto.WorkItemsAcceptListDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("workItemsAcceptListBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class WorkItemsAcceptListBusinessImpl extends BaseFWBusinessImpl<WorkItemsAcceptListDAO,WorkItemsAcceptListDTO, WorkItemsAcceptListBO> implements WorkItemsAcceptListBusiness {

    @Autowired
    private WorkItemsAcceptListDAO workItemsAcceptListDAO;
    

     
    public WorkItemsAcceptListBusinessImpl() {
        tModel = new WorkItemsAcceptListBO();
        tDAO = workItemsAcceptListDAO;
    }

    @Override
    public WorkItemsAcceptListDAO gettDAO() {
        return workItemsAcceptListDAO;
    }

    @Override
    public long count() {
        return workItemsAcceptListDAO.count("WorkItemsAcceptListBO", null);
    }

    

    
}
