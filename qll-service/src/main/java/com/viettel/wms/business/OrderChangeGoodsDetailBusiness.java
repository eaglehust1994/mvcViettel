package com.viettel.wms.business;

import java.util.List;

import com.viettel.wms.dto.OrderChangeGoodsDetailDTO;

public interface OrderChangeGoodsDetailBusiness {

    long count();

    List<OrderChangeGoodsDetailDTO> importGoods(String fileInput) throws Exception;
}
