package com.viettel.erp.business;
 
import com.viettel.erp.bo.AdvancePaymentProposalBO;
import com.viettel.erp.dao.AdvancePaymentProposalDAO;
import com.viettel.erp.dto.AdvancePaymentProposalDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("advancePaymentProposalBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AdvancePaymentProposalBusinessImpl extends BaseFWBusinessImpl<AdvancePaymentProposalDAO,AdvancePaymentProposalDTO, AdvancePaymentProposalBO> implements AdvancePaymentProposalBusiness {

    @Autowired
    private AdvancePaymentProposalDAO advancePaymentProposalDAO;
    
    
     
    public AdvancePaymentProposalBusinessImpl() {
        tModel = new AdvancePaymentProposalBO();
        tDAO = advancePaymentProposalDAO;
    }

    @Override
    public AdvancePaymentProposalDAO gettDAO() {
        return advancePaymentProposalDAO;
    }

    @Override
    public long count() {
        return advancePaymentProposalDAO.count("AdvancePaymentProposalBO", null);
    }

    public AdvancePaymentProposalDTO getAdvancePaymentByConstructId(long id){
    	   return  advancePaymentProposalDAO.getAdvancePaymentByContructId(id).toDTO();
    	
    }

    
    
}
