//package com.viettel.wms.business;
// 
//import com.viettel.wms.bo.GoodsTypeBO;
//import com.viettel.wms.dao.GoodsTypeDAO;
//import com.viettel.wms.dto.GoodsTypeDTO;
//import com.viettel.wms.dto.StockDTO;
//import com.viettel.service.base.business.BaseFWBusinessImpl;
//import com.viettel.service.base.dto.DataListDTO;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Scope;
//import org.springframework.context.annotation.ScopedProxyMode;
//import org.springframework.stereotype.Service;
//
//
//@Service("goodsTypeBusinessImpl")
//@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
//public class GoodsTypeBusinessImpl extends BaseFWBusinessImpl<GoodsTypeDAO,GoodsTypeDTO, GoodsTypeBO> implements GoodsTypeBusiness {
//
//    @Autowired
//    private GoodsTypeDAO goodsTypeDAO;
//    
//
//     
//    public GoodsTypeBusinessImpl() {
//        tModel = new GoodsTypeBO();
//        tDAO = goodsTypeDAO;
//    }
//
//    @Override
//    public GoodsTypeDAO gettDAO() {
//        return goodsTypeDAO;
//    }
//
//    @Override
//    public long count() {
//        return goodsTypeDAO.count("GoodsTypeBO", null);
//    }
//
//    public DataListDTO getList(GoodsTypeDTO obj){
//  	   List<GoodsTypeDTO> ls = goodsTypeDAO.getList(obj);
//  	   DataListDTO data = new DataListDTO();
//  	   data.setData(ls);
//  	   data.setTotal(obj.getTotalRecord());
//  	   data.setSize(obj.getTotalRecord());
//  	   data.setStart(1);
//  	   return data;
//     }
//    
//    public List<GoodsTypeDTO> getGoodTypeList(GoodsTypeDTO obj) {
//		return goodsTypeDAO.getGoodTypeList(obj);
//	}
//}
