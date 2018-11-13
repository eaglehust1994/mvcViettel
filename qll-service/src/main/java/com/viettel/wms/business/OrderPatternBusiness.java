package com.viettel.wms.business;

import com.viettel.wms.dto.OrderPatternDTO;
import com.viettel.service.base.dto.DataListDTO;

public interface OrderPatternBusiness {

	long count();
    DataListDTO doSearch(OrderPatternDTO obj);
	DataListDTO viewDetail(OrderPatternDTO obj);
}
