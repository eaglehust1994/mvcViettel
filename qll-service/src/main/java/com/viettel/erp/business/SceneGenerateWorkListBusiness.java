package com.viettel.erp.business;

import java.util.List;

import com.viettel.erp.dto.EstimatesWorkItemsDTO;
import com.viettel.erp.dto.SceneGenerateWorkDTO;
import com.viettel.erp.dto.SceneGenerateWorkListDTO;

public interface SceneGenerateWorkListBusiness {

    long count();
    
    //phongpv
    List<EstimatesWorkItemsDTO> getAllSceneGenerateWork(Long constructId);
    
    List<EstimatesWorkItemsDTO> doSearchSceneGenerateWork(EstimatesWorkItemsDTO criteria);
    
    //end phongpv
    //tungpv
    List<SceneGenerateWorkListDTO> doSearchSceneGenerateWorkList(SceneGenerateWorkDTO criteria);
    //end tungpv

}
