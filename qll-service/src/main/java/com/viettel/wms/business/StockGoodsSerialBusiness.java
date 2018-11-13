package com.viettel.wms.business;

import com.viettel.wms.dto.StockGoodsSerialDTO;
import com.viettel.wms.dto.StockTransDTO;
import com.viettel.service.base.dto.DataListDTO;

public interface StockGoodsSerialBusiness {

    long count();
    DataListDTO doSearchFindSerial(StockGoodsSerialDTO obj);
    DataListDTO doSearchHistory(StockTransDTO obj);
    
    void updateBySerial(StockGoodsSerialDTO stockGoodsSerialDTO);
    

}
