package com.viettel.wms.business;

import java.util.List;

import com.viettel.wms.dto.ManufacturerDTO;

public interface ManufacturerBusiness {

    long count();
    List<ManufacturerDTO> getAllNameAndId();
    List<ManufacturerDTO> getForAutocomplete(ManufacturerDTO obj);
    ManufacturerDTO getAllNameById(Long id);
}
