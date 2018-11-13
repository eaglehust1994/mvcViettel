//package com.viettel.wms.business;
// 
//import com.viettel.wms.bo.OrderGoodsDetailBO;
//import com.viettel.wms.dao.OrderGoodsDetailDAO;
//import com.viettel.wms.dto.OrderGoodsDTO;
//import com.viettel.wms.dto.OrderGoodsDetailDTO;
//import com.viettel.service.base.business.BaseFWBusinessImpl;
//import com.viettel.service.base.dto.DataListDTO;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Scope;
//import org.springframework.context.annotation.ScopedProxyMode;
//import org.springframework.stereotype.Service;
//
//
//@Service("orderGoodsDetailBusinessImpl")
//@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
//public class OrderGoodsDetailBusinessImpl extends BaseFWBusinessImpl<OrderGoodsDetailDAO,OrderGoodsDetailDTO, OrderGoodsDetailBO> implements OrderGoodsDetailBusiness {
//
//    @Autowired
//    private OrderGoodsDetailDAO orderGoodsDetailDAO;
//    
//
//     
//    public OrderGoodsDetailBusinessImpl() {
//        tModel = new OrderGoodsDetailBO();
//        tDAO = orderGoodsDetailDAO;
//    }
//
//    @Override
//    public OrderGoodsDetailDAO gettDAO() {
//        return orderGoodsDetailDAO;
//    }
//
//    @Override
//    public long count() {
//        return orderGoodsDetailDAO.count("OrderGoodsDetailBO", null);
//    }
//
//	@Override
//	public DataListDTO doSearchGoodsDetailForImportReq(OrderGoodsDetailDTO obj) {
//		// TODO Auto-generated method stub
//		List<OrderGoodsDetailDTO> ls = orderGoodsDetailDAO.doSearchGoodsDetailForImportReq(obj);
//		   DataListDTO data = new DataListDTO();
//		   data.setData(ls);
//		   data.setTotal(obj.getTotalRecord());
//		   data.setSize(obj.getTotalRecord());
//		   data.setStart(1);
//		return data;
//	}
//
//	@Override
//	public DataListDTO doSearchGoodsDetailForExportReq() {
//		List<OrderGoodsDetailDTO> ls = orderGoodsDetailDAO.doSearchGoodsDetailForExportReq();
//		   DataListDTO data = new DataListDTO();
//		   data.setData(ls);
//		   data.setStart(1);
//		return data;
//	}
//
//	public DataListDTO doSearchGoodsDetailForExportReq(OrderGoodsDTO obj) {
//		List<OrderGoodsDetailDTO> ls = orderGoodsDetailDAO.doSearchGoodsDetailForExportReq(obj);
//		   DataListDTO data = new DataListDTO();
//		   data.setData(ls);
//		   data.setTotal(obj.getTotalRecord());
//		   data.setSize(obj.getTotalRecord());
//		   data.setStart(1);
//		return data;
//	}
//	
//	public List<OrderGoodsDetailDTO> getGoodsDetailByOrderGoodId(Long idordergood)
//	{
//		List<OrderGoodsDetailDTO> lst = new ArrayList<OrderGoodsDetailDTO>();
//		lst = orderGoodsDetailDAO.getGoodsDetailByOrderGoodId(idordergood);
//		return lst;
//	}
//	
//
//    
//
//    
//}
