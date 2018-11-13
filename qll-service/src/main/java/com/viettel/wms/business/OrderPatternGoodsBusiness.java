package com.viettel.wms.business;

import com.viettel.wms.dto.OrderPatternGoodsDTO;
import com.viettel.service.base.dto.DataListDTO;

public interface OrderPatternGoodsBusiness {
    DataListDTO getPatternGoodsByOrderPatternId(OrderPatternGoodsDTO obj);

	 DataListDTO doSearch(OrderPatternGoodsDTO obj);
    long count();
}
