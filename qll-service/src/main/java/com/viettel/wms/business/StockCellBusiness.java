package com.viettel.wms.business;

import java.util.List;

import com.viettel.wms.dto.StockCellDTO;


public interface StockCellBusiness {

    long count();
    public List<StockCellDTO> importStockCell(String fileInput);
    
    String exportStockCellExcel() throws Exception;
}
