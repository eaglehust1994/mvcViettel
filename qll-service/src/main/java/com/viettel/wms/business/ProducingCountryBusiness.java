package com.viettel.wms.business;

import java.util.List;

import com.viettel.wms.dto.ProducingCountryDTO;

public interface ProducingCountryBusiness {

    long count();
    List<ProducingCountryDTO> getAllNameAndId();
    List<ProducingCountryDTO> getForAutocomplete(ProducingCountryDTO obj);
    ProducingCountryDTO getAllNameById(Long id);
}
