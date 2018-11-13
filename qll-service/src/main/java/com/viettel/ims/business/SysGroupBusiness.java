package com.viettel.ims.business;

import java.util.List;
import com.viettel.ims.dto.SysGroupDTO;

/**
 * @author hailh10
 */

public interface SysGroupBusiness {

	SysGroupDTO findByCode(String value);

	List<SysGroupDTO> doSearch(SysGroupDTO obj);
	
	List<SysGroupDTO> getForAutoComplete(SysGroupDTO query);
	
	List<SysGroupDTO> getForComboBox(SysGroupDTO query);
}
