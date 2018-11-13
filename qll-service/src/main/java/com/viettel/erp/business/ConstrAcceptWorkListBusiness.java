package com.viettel.erp.business;

import java.util.List;

import com.viettel.erp.dto.ConstrAcceptWorkListDTO;

public interface ConstrAcceptWorkListBusiness {

    long count();
    List<ConstrAcceptWorkListDTO> getAllbyConstructId(Long constructId);
    List<ConstrAcceptWorkListDTO> getProposedSettlementById(Long contractId);
}
