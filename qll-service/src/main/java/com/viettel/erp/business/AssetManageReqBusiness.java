package com.viettel.erp.business;

import java.util.List;

import com.viettel.erp.dto.AssetManageReqDTO;

public interface AssetManageReqBusiness {

    long count();
    List<AssetManageReqDTO> getValueSuppliesById(Long contractId);
    List<AssetManageReqDTO> getTotalPriceById(Long contractId);
}
