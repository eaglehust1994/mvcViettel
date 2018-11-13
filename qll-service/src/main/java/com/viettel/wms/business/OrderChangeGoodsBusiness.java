package com.viettel.wms.business;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.viettel.service.base.dto.DataListDTO;
import com.viettel.wms.dto.OrderChangeGoodsDTO;
import com.viettel.wms.dto.OrderChangeGoodsDetailDTO;

public interface OrderChangeGoodsBusiness {

    long count();
    public List<OrderChangeGoodsDetailDTO> doSearchForAutoImport(OrderChangeGoodsDetailDTO obj);
    public DataListDTO doSearchGoodsForImportReq(OrderChangeGoodsDTO obj);
	Long saveImportChange(OrderChangeGoodsDTO obj, HttpServletRequest request)
			throws Exception;
	public List<OrderChangeGoodsDTO> doSearchForCheckAll(OrderChangeGoodsDTO obj);
   
}
