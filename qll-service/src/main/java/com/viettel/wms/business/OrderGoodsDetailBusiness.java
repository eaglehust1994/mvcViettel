package com.viettel.wms.business;

import com.viettel.wms.dto.OrderDTO;
import com.viettel.wms.dto.OrderGoodsDetailDTO;
import com.viettel.service.base.dto.DataListDTO;

public interface OrderGoodsDetailBusiness {

    long count();
    
    DataListDTO doSearchGoodsDetailForImportReq(OrderGoodsDetailDTO obj);

    DataListDTO doSearchGoodsDetailForExportReq();

}
