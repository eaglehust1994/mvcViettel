package com.viettel.erp.business;

import java.util.List;

import com.viettel.erp.dto.AbComplementWorkDTO;

public interface AbComplementWorkBusiness {

    long count();
    
    AbComplementWorkDTO getThongtinchung(Long id);
    
    AbComplementWorkDTO getAmonitorSingCa(Long id);
}
