package com.viettel.wms.business;

import com.viettel.wms.dto.OddCableDTO;
import com.viettel.service.base.dto.DataListDTO;

public interface OddCableBusiness {

    long count();
    DataListDTO doSearch(OddCableDTO obj);
}
