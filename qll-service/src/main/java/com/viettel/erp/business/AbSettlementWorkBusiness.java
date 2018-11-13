package com.viettel.erp.business;

import com.viettel.erp.dto.AbSettlementWorkDTO;

public interface AbSettlementWorkBusiness {

    long count();
    String autoGenCode() ;
    AbSettlementWorkDTO getAbSettIdByConstrId(Long id);
}
