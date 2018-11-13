package com.viettel.erp.business;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.viettel.erp.bo.AbSettlementValueBO;
import com.viettel.erp.dao.AbSettlementValueDAO;
import com.viettel.erp.dto.AbSettlementValueDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;


@Service("abSettlementValueBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AbSettlementValueBusinessImpl extends BaseFWBusinessImpl<AbSettlementValueDAO,AbSettlementValueDTO, AbSettlementValueBO> implements AbSettlementValueBusiness {

    @Autowired
    private AbSettlementValueDAO abSettlementValueDAO;
    

     
    public AbSettlementValueBusinessImpl() {
        tModel = new AbSettlementValueBO();
        tDAO = abSettlementValueDAO;
    }

    @Override
    public AbSettlementValueDAO gettDAO() {
        return abSettlementValueDAO;
    }

    @Override
    public long count() {
        return abSettlementValueDAO.count("AbSettlementValueBO", null);
    }

    public Long saveTable( AbSettlementValueDTO  abSettlementValue) throws Exception{
	      Long abSettlementValueId = abSettlementValueDAO.saveTable(abSettlementValue);
	      return abSettlementValueId;
    }
    
    public AbSettlementValueDTO getById(Long constructId) {
		return abSettlementValueDAO.getById(constructId);
	}
    
    public AbSettlementValueDTO checkEstimateAB1(Long constructId) {
		return abSettlementValueDAO.checkEstimateAB1(constructId);
	}

	@Override
	public Long getConstrComReMapId(Long idChild) {
		return abSettlementValueDAO.getConstrComReMapId(idChild);
	}

	@Override
	public  String autoGenCode(){
		return abSettlementValueDAO.autoGenCode();
	}

	@Override
	public AbSettlementValueDTO getByABSettlementId(Long id) {
		return abSettlementValueDAO.getByABSettlementId(id);
	}
    
}
