package com.viettel.erp.business;

import java.util.List;

import com.viettel.erp.bo.AMaterialRecoveryMinutesBO;
import com.viettel.erp.dto.AMaterialRecoveryListModelDTO;

public interface AMaterialRecoveryListBusiness {

    long count();
    List<AMaterialRecoveryListModelDTO> findByConstructId(Long constructId);
}
