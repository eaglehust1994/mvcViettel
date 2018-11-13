package com.viettel.erp.business;

import java.util.List;

import com.viettel.erp.dto.EstimatesItemsChildDTO;

public interface EstimatesItemsChildBusiness {

    long count();
  //haibt
    List<EstimatesItemsChildDTO> getAllItemsChildInContruct(Long constructionId);
    
    List<EstimatesItemsChildDTO> getListEstimateItemschild(EstimatesItemsChildDTO rightDTO);
}
