//package com.viettel.wms.business;
// 
//import com.viettel.wms.bo.StockGoodsTotalReponseBO;
//import com.viettel.wms.constant.Constants;
//import com.viettel.wms.dao.StockGoodsTotalReponseDAO;
//import com.viettel.wms.dto.StockGoodsTotalReponseDTO;
//import com.viettel.ktts.vps.VpsPermissionChecker;
//import com.viettel.service.base.business.BaseFWBusinessImpl;
//import com.viettel.service.base.dto.DataListDTO;
//
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Scope;
//import org.springframework.context.annotation.ScopedProxyMode;
//import org.springframework.stereotype.Service;
//
//
//@Service("stockGoodsTotalReponseBusinessImpl")
//@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
//public class StockGoodsTotalReponseBusinessImpl extends BaseFWBusinessImpl<StockGoodsTotalReponseDAO,StockGoodsTotalReponseDTO, StockGoodsTotalReponseBO> implements StockGoodsTotalReponseBusiness {
//
//    @Autowired
//    private StockGoodsTotalReponseDAO stockGoodsTotalReponseDAO;
//    
//    @Autowired
//    private StockBusinessImpl stockBusinessImpl;
//     
//    public StockGoodsTotalReponseBusinessImpl() {
//        tModel = new StockGoodsTotalReponseBO();
//        tDAO = stockGoodsTotalReponseDAO;
//    }
//
//    @Override
//    public StockGoodsTotalReponseDAO gettDAO() {
//        return stockGoodsTotalReponseDAO;
//    }
//
//    @Override
//    public long count() {
//        return stockGoodsTotalReponseDAO.count("StockGoodsTotalReponseBO", null);
//    }
//
////    Hàm tìm kiếm dữ liệu trong bảng StockGoodsTotalResponse TUANNB
//    public DataListDTO doSearch(StockGoodsTotalReponseDTO obj,HttpServletRequest request) throws Exception{
//    	String err="";
//    		if(!VpsPermissionChecker.checkPermissionOnDomainData(Constants.OperationKey.REPORT, Constants.AdResourceKey.STOCK,obj.getStockId(), request)){
//    			err=StringUtils.isNotEmpty(err)? (err+";"+obj.getStockId()):("Bạn không có quyền xem báo cáo tại kho "+obj.getStockId());
//    		}
//    	
//    	if(StringUtils.isNotEmpty(err)){
//    		throw new IllegalArgumentException(err);
//    	}
//   	   	List<StockGoodsTotalReponseDTO> ls = stockGoodsTotalReponseDAO.doSearch(obj);
//   	   	DataListDTO data = new DataListDTO();
//   	   	data.setData(ls);
//   	   	data.setTotal(obj.getTotalRecord());
//   	   	data.setSize(obj.getTotalRecord());
//   	   	data.setStart(1);
//   	   	return data;
//    }
////    end TUANNB
//    
//}
