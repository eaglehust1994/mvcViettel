package com.viettel.wms.business;

import java.util.List;

import com.viettel.wms.dto.DepartmentDTO;
import com.viettel.wms.dto.StockDTO;

public interface DepartmentBusiness {

    long count();
    List<DepartmentDTO> getDeptForAutocomplete(DepartmentDTO obj);
}
