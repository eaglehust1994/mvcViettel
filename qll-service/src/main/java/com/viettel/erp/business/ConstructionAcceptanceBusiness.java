package com.viettel.erp.business;

import java.util.List;

import com.viettel.erp.dto.ConstructionAcceptanceDTO;

public interface ConstructionAcceptanceBusiness {

    long count();
    
    List<ConstructionAcceptanceDTO> findByConstructId(Long constructId);
    boolean updateIsActive(Long constrAcceptanceId);
}
