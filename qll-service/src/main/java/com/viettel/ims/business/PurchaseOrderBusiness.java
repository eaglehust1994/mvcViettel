package com.viettel.ims.business;

import java.util.List;

import com.viettel.ims.dto.PurchaseOrderDTO;
import com.viettel.ims.dto.WorkItemQuotaDTO;

/**
 * @author hailh10
 */

public interface PurchaseOrderBusiness {

	PurchaseOrderDTO findByCode(String value);

	List<PurchaseOrderDTO> doSearch(PurchaseOrderDTO obj);
	
	List<PurchaseOrderDTO> getForAutoComplete(PurchaseOrderDTO query);
	
	public List<PurchaseOrderDTO> importPurchaseOrder (String fileInput) throws Exception;
}
