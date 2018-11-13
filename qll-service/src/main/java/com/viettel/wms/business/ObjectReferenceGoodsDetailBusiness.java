package com.viettel.wms.business;

import java.util.List;

import com.viettel.wms.dto.ObjectReferenceGoodsDetailDTO;

public interface ObjectReferenceGoodsDetailBusiness {

    long count();
    List<ObjectReferenceGoodsDetailDTO> getGoodsDetail(ObjectReferenceGoodsDetailDTO obj);
}
