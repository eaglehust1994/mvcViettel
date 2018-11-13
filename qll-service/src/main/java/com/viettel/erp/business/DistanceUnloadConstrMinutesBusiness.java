package com.viettel.erp.business;

import java.util.List;

import com.viettel.erp.dto.DistanceUnloadConstrMinutesDTO;
import com.viettel.erp.dto.approDTO;

public interface DistanceUnloadConstrMinutesBusiness {

    long count();
    
    
    List<DistanceUnloadConstrMinutesDTO> getListCR(Long constructId);
    
    Long appro(approDTO obj);
    
    public boolean updateIsActive(List<Long> listId);
}
