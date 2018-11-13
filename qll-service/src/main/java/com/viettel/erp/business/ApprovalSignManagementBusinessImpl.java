package com.viettel.erp.business;
 
import com.viettel.erp.bo.ApprovalSignManagementBO;
import com.viettel.erp.dao.ApprovalSignManagementDAO;
import com.viettel.erp.dto.ApprovalSignManagementDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("approvalSignManagementBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ApprovalSignManagementBusinessImpl extends BaseFWBusinessImpl<ApprovalSignManagementDAO,ApprovalSignManagementDTO, ApprovalSignManagementBO> implements ApprovalSignManagementBusiness {

    @Autowired
    private ApprovalSignManagementDAO approvalSignManagementDAO;
     
    public ApprovalSignManagementBusinessImpl() {
        tModel = new ApprovalSignManagementBO();
        tDAO = approvalSignManagementDAO;
    }

    @Override
    public ApprovalSignManagementDAO gettDAO() {
        return approvalSignManagementDAO;
    }

    @Override
    public long count() {
        return approvalSignManagementDAO.count("ApprovalSignManagementBO", null);
    }

	@Override
	public boolean cancelAprroval(ApprovalSignManagementDTO dto) {
		// TODO Auto-generated method stub
		return approvalSignManagementDAO.cancelAprroval(dto);
	}

    

    
}
