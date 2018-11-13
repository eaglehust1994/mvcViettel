package com.viettel.ims.business;

import java.util.List;
import com.viettel.ims.dto.CntContractDTO;

/**
 * @author hailh10
 */

public interface CntContractBusiness {

	CntContractDTO findByCode(String value);

	List<CntContractDTO> doSearch(CntContractDTO obj);
	
	List<CntContractDTO> getForAutoComplete(CntContractDTO query);
}
