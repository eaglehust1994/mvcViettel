package com.viettel.ims.business;

import java.util.List;
import com.viettel.ims.dto.CatBankBranchBakDTO;
import com.viettel.ims.bo.CatBankBranchBakBO;

/**
 * @author hailh10
 */

public interface CatBankBranchBakBusiness {

    long count();

	CatBankBranchBakDTO findByValue(String value);

	List<CatBankBranchBakDTO> getAll();

	List<CatBankBranchBakDTO> doSearch(CatBankBranchBakDTO obj);
	
	List<CatBankBranchBakBO> getForAutoComplete(CatBankBranchBakDTO query);
}
