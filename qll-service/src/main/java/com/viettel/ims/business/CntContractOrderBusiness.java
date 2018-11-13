package com.viettel.ims.business;

import java.util.List;
import com.viettel.ims.dto.CntContractOrderDTO;

/**
 * @author hailh10
 */

public interface CntContractOrderBusiness {

	CntContractOrderDTO findByValue(String value);

	List<CntContractOrderDTO> doSearch(CntContractOrderDTO obj);
	
	List<CntContractOrderDTO> getForAutoComplete(CntContractOrderDTO query);
}
