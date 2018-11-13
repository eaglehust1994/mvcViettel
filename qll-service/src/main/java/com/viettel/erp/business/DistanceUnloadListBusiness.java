package com.viettel.erp.business;

import java.util.List;

import com.viettel.erp.dto.DistanceUnloadListDTO;

public interface DistanceUnloadListBusiness {

    long count();
    
    List<DistanceUnloadListDTO> doSearchByDisUnloadConsMinId(Long disUnloadConsMinId);
}
