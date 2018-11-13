package com.viettel.ims.business;

import java.util.List;
import com.viettel.ims.dto.TaskQuotaDTO;

/**
 * @author hailh10
 */

public interface TaskQuotaBusiness {

	TaskQuotaDTO findByCode(String code);

	List<TaskQuotaDTO> doSearch(TaskQuotaDTO obj);
	
	List<TaskQuotaDTO> getForAutoComplete(TaskQuotaDTO query);
}
