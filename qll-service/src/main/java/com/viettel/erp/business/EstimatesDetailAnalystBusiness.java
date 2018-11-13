package com.viettel.erp.business;

import java.util.List;

import com.viettel.erp.dto.EstimatesDetailAnalystDTO;

public interface EstimatesDetailAnalystBusiness {

    long count();
    List<EstimatesDetailAnalystDTO> getAcceptanceList();
    List<EstimatesDetailAnalystDTO> getAcceptanceListById(Long constructId);
    List<EstimatesDetailAnalystDTO> doSearchEstimatesDetailAnalyst(EstimatesDetailAnalystDTO criteria);
}
