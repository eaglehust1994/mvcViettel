package com.viettel.erp.business;

import com.viettel.erp.dto.AbSettlementValueDTO;

public interface AbSettlementValueBusiness {

    long count();
    
    Long getConstrComReMapId(Long idChild);
    String autoGenCode();
    AbSettlementValueDTO getByABSettlementId(Long id);
}
