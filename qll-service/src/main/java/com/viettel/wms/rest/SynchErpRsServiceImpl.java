///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.viettel.wms.rest;
//
//import java.util.List;
//
//import javax.ws.rs.core.Response;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.viettel.wms.business.StockTransBusinessImpl;
//import com.viettel.wms.dto.SynchERPDTO;
//
//
//public class SynchErpRsServiceImpl implements SynchErpRsService {
//
////    protected final Logger log = Logger.getLogger(UserRsService.class);
//	 @Autowired
//	    StockTransBusinessImpl stockTransBusinessImpl;
//	    Logger logger = Logger.getLogger(SynchErpRsServiceImpl.class);
//		@Override
//		public Response getListStockTrans(SynchERPDTO obj) throws Exception {
//			try {
//				List<SynchERPDTO> ls= stockTransBusinessImpl.getListStockTrans(obj);
//				 return Response.ok(ls).build();
//			} catch (Exception e) {
//				  return Response.status(Response.Status.BAD_REQUEST).build();
//			}
//		}
// 
//
//}
