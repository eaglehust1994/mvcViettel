package com.viettel.erp.business;

import java.util.List;

import com.viettel.erp.dto.EstimatesWorkItemsDTO;
import com.viettel.erp.dto.WorkItemsAcceptanceDTO;

public interface EstimatesWorkItemsBusiness {

    long count();

    
    //minhpvn
    List<EstimatesWorkItemsDTO> doSearchSceneGenerateWorkListCongTrinh(EstimatesWorkItemsDTO obj);
    //ngoccx
    
    List<EstimatesWorkItemsDTO> getEstimatesWorkItemsSearchAccept(EstimatesWorkItemsDTO obj);


    //List<EstimatesWorkItemsDTO> getByConstructionId(EstimatesWorkItemsDTO obj);
    List<EstimatesWorkItemsDTO> getBieu5(Long id);

    
    //tungpv
    List<EstimatesWorkItemsDTO> doSearchEstimatesWorkItems(EstimatesWorkItemsDTO criteria);
    List<EstimatesWorkItemsDTO> getAllEstimatesWorkItems(Long id);
    List<EstimatesWorkItemsDTO> doSearchByWorkItemsAcceptanceId(WorkItemsAcceptanceDTO workItemsAcceptanceId);
    //end tungpv
    
    List<EstimatesWorkItemsDTO> getAllEstimatesWorkInsideContract(EstimatesWorkItemsDTO obj);
    List<EstimatesWorkItemsDTO> getAllEstimatesWorkOutsideContract(EstimatesWorkItemsDTO obj);

    // dong
    List<EstimatesWorkItemsDTO> getBieu4(EstimatesWorkItemsDTO obj);
    List<EstimatesWorkItemsDTO> getBieu2(Long id);
    public EstimatesWorkItemsDTO exportEstimateWorkItem(EstimatesWorkItemsDTO dto);
    // dong
    
    List<EstimatesWorkItemsDTO> getAllEstimatesWorkOutsideContractForEvaluate(EstimatesWorkItemsDTO obj);
    List<EstimatesWorkItemsDTO> getAllEstimatesWorkInsideContractForEvaluate(EstimatesWorkItemsDTO obj);
    List<EstimatesWorkItemsDTO> getWorkItemDetail(EstimatesWorkItemsDTO obj);

    List<EstimatesWorkItemsDTO> getAllEstimatesWorkContract(EstimatesWorkItemsDTO obj);

    //Nha
    List<EstimatesWorkItemsDTO> getByConstructionId(Long id);
    
    void updateStatus(Long estimatesWorkItemId);
}
