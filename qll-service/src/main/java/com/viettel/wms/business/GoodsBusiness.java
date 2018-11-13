package com.viettel.wms.business;

import java.util.List;

import com.viettel.wms.dto.GoodsDTO;

public interface GoodsBusiness {

    long count();
    
    List<GoodsDTO> getGoodsForOrder(GoodsDTO obj);
    GoodsDTO getGoodByCode(String goodcode);
   // GoodsDTO getGoodById(Long id);
    GoodsDTO getGoodById(GoodsDTO obj);

}
