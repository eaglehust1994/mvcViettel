package com.viettel.erp.business;

import java.util.List;

import com.viettel.erp.dto.ConstrAcceptLostNoteDTO;

public interface ConstrAcceptLostNoteBusiness {

    long count();
    List<ConstrAcceptLostNoteDTO> getValueLossById(Long contractId);
}
