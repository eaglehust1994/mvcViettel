package com.viettel.erp.business;

import java.util.List;

import com.viettel.erp.dto.BMaterialAcceptMerListDTO;

public interface BMaterialAcceptMerListBusiness {

    long count();
    List<BMaterialAcceptMerListDTO> getAccpetMerList(Long bMaterialAcceptanceId);
}
