package com.viettel.erp.business;

import java.util.List;

import com.viettel.erp.dto.SettlementRightDTO;

public interface SettlementRightBusiness {

    long count();
 
	public List<SettlementRightDTO> getAllAMonitorOrBInChargeByConstructId(SettlementRightDTO dto);
	//minhpvn : lay nguoi ky quyet toan a-b form 6
	public List<SettlementRightDTO> getAllAMonitorOrBInChargeByConstructIdForm6(SettlementRightDTO dto);
}
