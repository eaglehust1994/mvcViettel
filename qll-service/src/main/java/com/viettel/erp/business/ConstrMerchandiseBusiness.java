package com.viettel.erp.business;

import java.util.List;

import com.viettel.erp.dto.ConstrMerchandiseDTO;

public interface ConstrMerchandiseBusiness {

    long count();
    List<ConstrMerchandiseDTO> gettValueConstrMerchandiseById(Long contractId);
    
}
