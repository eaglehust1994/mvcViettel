//package com.viettel.wms.business;
//
//import java.io.File;
//import java.io.InputStream;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.multipart.MultipartHttpServletRequest;
//
//import com.viettel.wms.dto.GoodsDTO;
//import com.viettel.wms.dto.OrderGoodsDTO;
//import com.viettel.wms.dto.OrderGoodsDetailDTO;
//import com.viettel.wms.dto.OrderGoodsExelDTO;
//import com.viettel.wms.dto.StockTransDTO;
//import com.viettel.service.base.dto.DataListDTO;
//
//public interface OrderGoodsBusiness {
//
//	long count();
//
//	DataListDTO doSearchGoodsForImportReq(OrderGoodsDTO obj);
//
//	public List<OrderGoodsDetailDTO> importCells(String fileInput, Long id) throws Exception;
//	
//	public List<OrderGoodsDTO> importGoods(String fileInput) throws Exception;
//	
////	public List<OrderGoodsDTO> importOrderGood(String fileInput);
//	
//	DataListDTO doSearchGoodsForExportOrder(OrderGoodsDTO obj);
//	
//	String exportExcelError(OrderGoodsDetailDTO errorObj) throws Exception;
//	
//	boolean saveGoodsList(List<OrderGoodsDTO> orderGoods);
//	
//	String exportExcelTemplate(OrderGoodsDTO obj) throws Exception;
//	
//	List<OrderGoodsDTO> getGoodDetail(Long orderId);
//
//}
