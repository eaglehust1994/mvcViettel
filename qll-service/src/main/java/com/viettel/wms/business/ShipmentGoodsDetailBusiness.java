package com.viettel.wms.business;

import java.util.List;

import com.viettel.wms.dto.AttachmentDTO;
import com.viettel.wms.dto.ShipmentGoodsDTO;
import com.viettel.wms.dto.ShipmentGoodsDetailDTO;

public interface ShipmentGoodsDetailBusiness {

    long count();
    
    List<ShipmentGoodsDetailDTO> getGoodsInfoByCode(String code);
    
    List<ShipmentGoodsDetailDTO> getGoodsDetail(ShipmentGoodsDetailDTO obj);
    
    List<AttachmentDTO> getGoodsFile(AttachmentDTO obj);
}
