package com.viettel.erp.business;
 
import com.viettel.erp.bo.AbSettlementWorkBO;
import com.viettel.erp.dao.AbSettlementWorkDAO;
import com.viettel.erp.dto.AbSettlementWorkDTO;
import com.viettel.erp.dto.WorkItemsAcceptanceDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("abSettlementWorkBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AbSettlementWorkBusinessImpl extends BaseFWBusinessImpl<AbSettlementWorkDAO,AbSettlementWorkDTO, AbSettlementWorkBO> implements AbSettlementWorkBusiness {

    @Autowired
    private AbSettlementWorkDAO abSettlementWorkDAO;
    
    @Override
	public String autoGenCode() {
		return abSettlementWorkDAO.autoGenCode();
	}
     
    public AbSettlementWorkBusinessImpl() {
        tModel = new AbSettlementWorkBO();
        tDAO = abSettlementWorkDAO;
    }

    @Override
    public AbSettlementWorkDAO gettDAO() {
        return abSettlementWorkDAO;
    }

    @Override
    public long count() {
        return abSettlementWorkDAO.count("AbSettlementWorkBO", null);
    }

    @Override
    public AbSettlementWorkDTO getAbSettIdByConstrId(Long constructId) {
		return abSettlementWorkDAO.getAbSettIdByConstrId(constructId);
	}
    
    public Long saveTable( AbSettlementWorkDTO  obj) throws Exception {
	       Long id = abSettlementWorkDAO.saveTable(obj);
	       return id;
	}
}
