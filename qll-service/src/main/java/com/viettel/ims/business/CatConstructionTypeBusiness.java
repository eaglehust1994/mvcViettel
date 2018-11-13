package com.viettel.ims.business;

import java.util.List;
import com.viettel.ims.dto.CatConstructionTypeDTO;

/**
 * @author hailh10
 */

public interface CatConstructionTypeBusiness {

	CatConstructionTypeDTO findByCode(String value);

	List<CatConstructionTypeDTO> doSearch(CatConstructionTypeDTO obj);
	
	List<CatConstructionTypeDTO> getForAutoComplete(CatConstructionTypeDTO query);
	
	List<CatConstructionTypeDTO> getForComboBox(CatConstructionTypeDTO query);
}
