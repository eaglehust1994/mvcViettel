//package com.viettel.wms.business;
// 
//import com.viettel.wms.bo.StockDailyImportExportBO;
//import com.viettel.wms.constant.Constants;
//import com.viettel.wms.dao.ConfigSignVofficeDAO;
//import com.viettel.wms.dao.StockDailyImportExportDAO;
//import com.viettel.wms.dto.StockDTO;
//import com.viettel.wms.dto.StockDailyImportExportDTO;
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
//@Service("stockDailyImportExportBusinessImpl")
//@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
//public class StockDailyImportExportBusinessImpl extends BaseFWBusinessImpl<StockDailyImportExportDAO,StockDailyImportExportDTO, StockDailyImportExportBO> implements StockDailyImportExportBusiness {
//
//    @Autowired
//    private StockDailyImportExportDAO stockDailyImportExportDAO;
//    
//    @Autowired
//    private ConfigSignVofficeDAO configSignVofficeDAO;
//    
//    @Autowired
//    private CommonBusiness commonBusiness;
//    
//    @Autowired
//    private StockBusinessImpl stockBusinessImpl;
//     
//    public StockDailyImportExportBusinessImpl() {
//        tModel = new StockDailyImportExportBO();
//        tDAO = stockDailyImportExportDAO;
//    }
//
//    @Override
//    public StockDailyImportExportDAO gettDAO() {
//        return stockDailyImportExportDAO;
//    }
//
//    @Override
//    public long count() {
//        return stockDailyImportExportDAO.count("StockDailyImportExportBO", null);
//    }
//
//    public DataListDTO doSearch(StockDailyImportExportDTO obj,HttpServletRequest request) throws Exception{
//    	String err="";
//    	for(Long id:obj.getListStockId()){
//    		StockDTO stockDTO=(StockDTO) stockBusinessImpl.getOneById(id);
//    		if(!VpsPermissionChecker.checkPermissionOnDomainData(Constants.OperationKey.REPORT, Constants.AdResourceKey.STOCK,id, request)){
//    			err=StringUtils.isNotEmpty(err)? (err+";"+id):("Bạn không có quyền xem báo cáo tại kho "+id);
//    		}
//    	}
//    	
//    	if(StringUtils.isNotEmpty(err)){
//    		throw new IllegalArgumentException(err);
//    	}
//  	   List<StockDailyImportExportDTO> ls = stockDailyImportExportDAO.doSearch(obj);
//  	   DataListDTO data = new DataListDTO();
//  	   data.setData(ls);
//  	   data.setTotal(obj.getTotalRecord());
//  	   data.setSize(obj.getTotalRecord());
//  	   data.setStart(1);
//  	   return data;
//     }
//    
//    public DataListDTO doSearchStock(StockDTO obj, HttpServletRequest request) {
//    	List<Long> listStockId=commonBusiness.getListDomainData(Constants.OperationKey.CONFIG,Constants.AdResourceKey.STOCK, request);
//		obj.setListStockId(listStockId);
//    	
//		List<StockDTO> ls = configSignVofficeDAO.doSearchStock(obj);
//		DataListDTO data = new DataListDTO();
//		data.setData(ls);
//		data.setTotal(obj.getTotalRecord());
//		data.setSize(obj.getTotalRecord());
//		data.setStart(1);
//		return data;
//	}
//}
