package com.viettel.wms.business;

import java.util.List;

import com.viettel.wms.dto.StockDTO;

public interface StockBusiness {

	 long getTotal();
	 
	 List<StockDTO> getStocksForAutocomplete(StockDTO obj);
	 List<StockDTO> getStocksForAutocompleteDropDown(StockDTO obj);
}
