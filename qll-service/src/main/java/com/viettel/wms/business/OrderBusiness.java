package com.viettel.wms.business;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

//import com.viettel.wms.dto.GoodsDTO;
//import com.viettel.cat.dto.GoodsDTO;
import com.viettel.wms.dto.OrderDTO;
import com.viettel.wms.dto.OrderGoodsDTO;
import com.viettel.wms.dto.OrderGoodsDetailDTO;
import com.viettel.ktts2.dto.KttsUserSession;
import com.viettel.service.base.dto.DataListDTO;

public interface OrderBusiness {

    long count();
    
    DataListDTO doSearchImportRequirement(OrderDTO obj,HttpServletRequest request);
    
    
    DataListDTO doSearchForCreateImportNote(OrderDTO obj,HttpServletRequest request);
    
    
    OrderDTO getOrderDetail(Long id);
    
    DataListDTO doSearchExportStatementManagement(OrderDTO obj);
    
    
    DataListDTO doSearchExportRequirement(OrderDTO obj,HttpServletRequest request)throws Exception;
    
    DataListDTO doSearchDeliveryOrder(OrderDTO obj);
    
    Long saveImportReq(OrderDTO obj,HttpServletRequest request)throws Exception;
    
    Long updateImportReq(OrderDTO obj,KttsUserSession objUser,HttpServletRequest request)throws Exception;
    
    boolean removeOrder(OrderDTO obj,KttsUserSession objUser);
    
    boolean rejectOrder(OrderDTO obj,KttsUserSession objUser);
    
    String exportDocSerial(OrderDTO obj) throws Exception;
    
    Long replicateExportOrder(OrderDTO originalOrder);
    
    boolean saveExportOrder(OrderDTO obj);
    
//    public List<GoodsDTO> importGoods(String fileInput) throws Exception;
    
    boolean updateStatusOrder(OrderDTO obj);
    
    String exportExcelTemplate() throws Exception;
    
//    String exportExcelError(GoodsDTO errorObj) throws Exception;
    
//    String exportExcelErrorXK(GoodsDTO errorObj) throws Exception;

    
    Long updateExportRequest(OrderDTO obj) ;

	List<OrderDTO> getGoodsDetailByOrderId(OrderDTO obj);
	
	List<OrderDTO> getAllImportRequirement(OrderDTO obj,HttpServletRequest request);
	List<OrderDTO> getAllExportStatement(OrderDTO obj);
    
}
