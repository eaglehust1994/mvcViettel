package com.viettel.erp.business;

import java.util.List;

import com.viettel.erp.dto.IntergratedContractDTO;

public interface IntergratedContractBusiness {

    long count();

    List<IntergratedContractDTO> getIntergratedContractConstrt(IntergratedContractDTO dto);
}
