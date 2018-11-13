package com.viettel.wms.business;

import java.util.List;

import com.viettel.wms.dto.StockTransDetailSerialDTO;
import com.viettel.service.base.dto.DataListDTO;

public interface StockTransDetailSerialBusiness {

    long count();
    
    DataListDTO doSearchGoodsDetailForImportNote(StockTransDetailSerialDTO obj);
    
    List<StockTransDetailSerialDTO> getGoodsInfoByCode(String code);
    
    DataListDTO doSearchGoodsDetailForExportNote();
    
    List<StockTransDetailSerialDTO> getGoodsDetail(StockTransDetailSerialDTO obj);

    DataListDTO doSearchGoodsDetailForExportNote(StockTransDetailSerialDTO obj);
    
    DataListDTO doSearchGoodsDetailSerial(StockTransDetailSerialDTO obj);
    
 
}
