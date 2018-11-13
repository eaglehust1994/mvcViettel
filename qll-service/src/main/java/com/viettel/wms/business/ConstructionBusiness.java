package com.viettel.wms.business;

import java.util.List;

import com.viettel.wms.dto.ConstructionDTO;

public interface ConstructionBusiness {
	List<String> doSearchConstructionForAutoComplete(ConstructionDTO o);
	
	List<ConstructionDTO> doSearchConstructionForAutoCompleteII(String code);

	ConstructionDTO getConstructionByCode(String code);

}
