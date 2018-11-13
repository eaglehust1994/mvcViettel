package com.viettel.ims.business;

import java.util.List;

import com.viettel.ims.dto.CatPartnerDTO;

/**
 * @author hailh10
 */

public interface CatPartnerBusiness {

	CatPartnerDTO findByCode(String value);

	List<CatPartnerDTO> doSearch(CatPartnerDTO obj);
	
	List<CatPartnerDTO> getForAutoComplete(CatPartnerDTO query);
	
	List<CatPartnerDTO> getForComboBox(CatPartnerDTO query);
}
