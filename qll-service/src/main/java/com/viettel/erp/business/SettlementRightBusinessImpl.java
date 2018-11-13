package com.viettel.erp.business;
 
import com.viettel.erp.bo.SettlementRightBO;
import com.viettel.erp.dao.SettlementRightDAO;
import com.viettel.erp.dto.ConstrConstructionsDTO;
import com.viettel.erp.dto.RoleCaDTO;
import com.viettel.erp.dto.SettlementRightDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("settlementRightBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SettlementRightBusinessImpl extends BaseFWBusinessImpl<SettlementRightDAO,SettlementRightDTO, SettlementRightBO> implements SettlementRightBusiness {

    @Autowired
    private SettlementRightDAO settlementRightDAO;
    

     
    public SettlementRightBusinessImpl() {
        tModel = new SettlementRightBO();
        tDAO = settlementRightDAO;
    }

    @Override
    public SettlementRightDAO gettDAO() {
        return settlementRightDAO;
    }

    @Override
    public long count() {
        return settlementRightDAO.count("SettlementRightBO", null);
    }

	public List<SettlementRightDTO> getSettlementRightByConstrt(ConstrConstructionsDTO obj) {
		return settlementRightDAO.getSettlementRightByConstrt(obj);
	}

	public String deleteMultipleSettlement(List<String> listID) {
		return settlementRightDAO.deleteMultipleSettlement(listID);
	}

	@Override
	public List<SettlementRightDTO> getAllAMonitorOrBInChargeByConstructId(SettlementRightDTO dto) {
		// TODO Auto-generated method stub
		return settlementRightDAO.getAllAMonitorOrBInChargeByConstructId(dto);
	}	
	//minhpvn : lay nguoi ky quyet toan a-b form 6
	@Override
	public List<SettlementRightDTO> getAllAMonitorOrBInChargeByConstructIdForm6(SettlementRightDTO dto) {
		// TODO Auto-generated method stub
		return settlementRightDAO.getAllAMonitorOrBInChargeByConstructIdForm6(dto);
	}	
	//end minhpvn
	public boolean checkUnique(SettlementRightDTO obj) {
		return settlementRightDAO.checkUnique(obj);
	}

	public List<RoleCaDTO> getRoleCaByGroupSide(SettlementRightDTO settlementRightDTO) {
		return settlementRightDAO.getRoleCaByGroupSide(settlementRightDTO);
	}

	public void updateHiringMonitorConsult(ConstrConstructionsDTO obj) {
		settlementRightDAO.updateHiringMonitorConsult(obj);
		
	}
    
}
