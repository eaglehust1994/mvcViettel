package com.viettel.ims.business;

import java.util.List;

import com.viettel.ims.dto.BiddingPackageDTO;
import com.viettel.ims.dto.WorkItemQuotaDTO;

/**
 * @author hailh10
 */

public interface WorkItemQuotaBusiness {

	List<WorkItemQuotaDTO> doSearch(WorkItemQuotaDTO obj);
	
	List<WorkItemQuotaDTO> getForAutoComplete(WorkItemQuotaDTO query);
	
    public List<WorkItemQuotaDTO> importWorkItemQuota (String fileInput, Long quotaType) throws Exception;
}
