package com.viettel.ims.business;

import java.util.List;
import com.viettel.ims.dto.CatWorkItemTypeDTO;

/**
 * @author hailh10
 */

public interface CatWorkItemTypeBusiness {

	CatWorkItemTypeDTO findByCode(String value);

	List<CatWorkItemTypeDTO> doSearch(CatWorkItemTypeDTO obj);
	//20180224_hoanm1_start
	List<CatWorkItemTypeDTO> doSearchExport(CatWorkItemTypeDTO obj);
	//20180224_hoanm1_end
	
	List<CatWorkItemTypeDTO> getForAutoComplete(CatWorkItemTypeDTO query);
	
	List<CatWorkItemTypeDTO> getForComboBox(CatWorkItemTypeDTO query);
}
