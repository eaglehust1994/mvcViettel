package com.viettel.wms.business;

import java.util.List;

import com.viettel.wms.dto.PartnerDTO;


public interface PartnerBusiness {
	List<String> getForAutoComplete(PartnerDTO p);
	List<PartnerDTO> getForAutoCompleteII(String obj);
	PartnerDTO getPartnerById(Long id);
}
