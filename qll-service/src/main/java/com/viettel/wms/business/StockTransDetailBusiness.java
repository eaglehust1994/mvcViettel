//package com.viettel.wms.business;
//
//import java.util.List;
//
//import com.viettel.wms.dto.GoodsDTO;
//import com.viettel.wms.dto.OrderGoodsDTO;
//import com.viettel.wms.dto.StockTransDTO;
//import com.viettel.wms.dto.StockTransDetailDTO;
//import com.viettel.wms.dto.StockTransDetailSerialDTO;
//import com.viettel.service.base.dto.DataListDTO;
//
//public interface StockTransDetailBusiness {
//
//    long count();
//    
//    DataListDTO doSearchGoodsForImportNote(StockTransDetailDTO obj);
//    
//    List<StockTransDetailDTO> getGoodsInfoFromAlternativeStockByCode(String code);
//
//    DataListDTO doSearchGoodsForExportNote(StockTransDetailDTO obj);
//
//    boolean createNote(List<StockTransDetailDTO> os);
//    
//    public String exportStockTransExcelError(GoodsDTO errorObj) throws Exception;
//
//	List<StockTransDetailDTO> importUpdateStockTrans(String fileInput,
//			Long orderId) throws Exception;
//	String exportExcelTemplate(StockTransDetailDTO obj) throws Exception;
//}
