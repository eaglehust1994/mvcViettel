package com.viettel.ims.business;

import java.util.List;
import com.viettel.ims.dto.CatTaskDTO;

/**
 * @author hailh10
 */

public interface CatTaskBusiness {

	CatTaskDTO findByCode(String value);

	List<CatTaskDTO> doSearch(CatTaskDTO obj);
	
	List<CatTaskDTO> getForAutoComplete(CatTaskDTO query);
}
