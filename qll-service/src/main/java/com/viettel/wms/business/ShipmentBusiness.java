//package com.viettel.wms.business;
//
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//
//import com.viettel.wms.dto.GoodsDTO;
//import com.viettel.wms.dto.OrderGoodsDTO;
//import com.viettel.wms.dto.ShipmentDTO;
//import com.viettel.wms.dto.ShipmentGoodsDTO;
//
//public interface ShipmentBusiness {
//
//    long count();
//    
//    List<String> searchListShipmentCode(String code);
//    
//    public List<GoodsDTO> importGoods(String fileInput) throws Exception;
//    
//    String exportExcelError(GoodsDTO errorObj) throws Exception;
//    
//    String exportExcelTemplate() throws Exception;
//    /*Long updateShipmentGoodsTax(ShipmentDTO obj);*/
//    public void updateStatus(ShipmentDTO obj);
//    
//
//	Long saveDetailShipment(ShipmentDTO obj, HttpServletRequest request) throws Exception;
//	
//	
//}
