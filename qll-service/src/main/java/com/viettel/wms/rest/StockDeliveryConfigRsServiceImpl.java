///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.viettel.wms.rest;
//
//import com.viettel.wms.business.StockBusinessImpl;
//import com.viettel.wms.business.StockDeliveryConfigBusinessImpl;
//import com.viettel.wms.dto.StockDeliveryConfigDTO;
//import com.viettel.service.base.dto.DataListDTO;
//import java.util.List;
//import javax.ws.rs.core.Response;
//
//import org.apache.jasper.tagplugins.jstl.core.ForEach;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//
///**
// *
// * @author HungLQ9
// */
//public class StockDeliveryConfigRsServiceImpl implements StockDeliveryConfigRsService {
//
//    //protected final Logger log = Logger.getLogger(UserRsService.class);StockDeliveryConfigBusinessImpl
//    @Autowired
//    StockDeliveryConfigBusinessImpl stockDeliveryConfigBusinessImpl;
//    
//    @Autowired
//    StockDeliveryConfigBusinessImpl stockdeliveryconfigBusinessImpl;
//    @Override
//    public Response getStockDeliveryConfig() {
//        List<StockDeliveryConfigDTO> ls = stockDeliveryConfigBusinessImpl.getAll();
//        if (ls == null) {
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        } else {
//            DataListDTO data = new DataListDTO();
//            data.setData(ls);
//            data.setTotal(ls.size());
//            data.setSize(ls.size());
//            data.setStart(1);
//            return Response.ok(data).build();
//        }
//    }
//
//    @Override
//    public Response getStockDeliveryConfigById(Long id) {
//        StockDeliveryConfigDTO obj = (StockDeliveryConfigDTO) stockDeliveryConfigBusinessImpl.getOneById(id);
//        if (obj == null) {
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        } else {
//            return Response.ok(obj).build();
//        }
//    }
//
//    @Override
//    public Response updateStockDeliveryConfig(StockDeliveryConfigDTO obj) {
//        Long id = stockDeliveryConfigBusinessImpl.update(obj);
//        if (id == 0l) {
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        } else {
//            return Response.ok().build();
//        }
//
//    }
//
//    @Override
//    public Response addStockDeliveryConfig(StockDeliveryConfigDTO obj) {
//        Long id = stockDeliveryConfigBusinessImpl.save(obj);
//        if (id == 0l) {
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        } else {
//            return Response.ok(Response.Status.CREATED).build();
//        }
//    }
//
//    @Override
//    public Response deleteStockDeliveryConfig(List<Long> ids) {
//    	if(ids.size()>0)
//    	{
//    		for(int i=0;i<ids.size();i++){
//        		StockDeliveryConfigDTO obj = (StockDeliveryConfigDTO) stockDeliveryConfigBusinessImpl.getOneById(ids.get(i));
//                if (obj == null) {
//                    return Response.status(Response.Status.BAD_REQUEST).build();
//                } else {
//                    stockDeliveryConfigBusinessImpl.delete(obj);
//                    return Response.ok(Response.Status.NO_CONTENT).build();
//                }
//        	}
//    	}
//		return null;
//        
//    }
//
//	@Override
//	public Response doSearch() {
//		DataListDTO data = stockdeliveryconfigBusinessImpl.doSearch();
//		return Response.ok(data).build();
//	}
//
//	@Override
//	public Response getStockDeliveryConfig(Long id) {
//		StockDeliveryConfigDTO obj = stockdeliveryconfigBusinessImpl.getStockDeliveryConfig(id);
//		return Response.ok(obj).build();
//	}
//}
