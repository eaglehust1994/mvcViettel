package com.viettel.erp.business;

import java.util.List;

import com.viettel.erp.bo.ConstrWorkCompConfirmBO;
import com.viettel.erp.dao.ConstrWorkLogsDAO;
import com.viettel.erp.dto.ConstrWorkCompConfirmDTO;
import com.viettel.erp.dto.ConstrWorkLogsDTO;
import com.viettel.erp.dto.EstimatesWorkItemsDTO;
import com.viettel.erp.dto.WorkItemsAcceptanceDTO;
import com.viettel.erp.dto.approDTO;

public interface ConstrWorkLogsBusiness {

	long count();

	// ChuongNV
	List<ConstrWorkLogsDTO> getAllConstrWorkLogs(ConstrWorkLogsDTO dto);

	List<EstimatesWorkItemsDTO> getEstimatesWork(String constructId);

	void deleteConstrWorkLogs(List<String> listConstrWorkLogsId);

	String autoGenCode();
	
	boolean checkStatusDatabase(String constrWorkLogsId);
	
	Long appro(approDTO obj);
	
	ConstrWorkLogsDTO findById(Long constrWorkLogsId,String contractCode);
	
	Long addConstrWorkLogs(ConstrWorkLogsDTO obj);
}
