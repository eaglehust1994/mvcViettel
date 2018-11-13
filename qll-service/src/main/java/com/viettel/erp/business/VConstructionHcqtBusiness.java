package com.viettel.erp.business;

import java.util.List;

import com.viettel.erp.dto.VConstructionHcqtDTO;

public interface VConstructionHcqtBusiness {

    long count();
    List<VConstructionHcqtDTO> getContractTotalValueById(Long contractId);
    
}
