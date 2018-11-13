///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.viettel.wms.rest;
//
//import java.util.Collections;
//
//import com.viettel.wms.business.ConfigSignVofficeBusinessImpl;
//import com.viettel.wms.business.StockDailyImportExportBusinessImpl;
//import com.viettel.wms.dto.StockDTO;
//import com.viettel.wms.dto.StockDailyImportExportDTO;
//import com.viettel.service.base.dto.DataListDTO;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.ws.rs.core.Context;
//import javax.ws.rs.core.Response;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
///**
// *
// * @author HungLQ9
// */
//public class StockDailyImportExportRsServiceImpl implements StockDailyImportExportRsService {
//
//	// protected final Logger log = Logger.getLogger(UserRsService.class);
//    @Autowired
//    StockDailyImportExportBusinessImpl stockDailyImportExportBusinessImpl;
//    
//    @Autowired
//    ConfigSignVofficeBusinessImpl configSignVofficeBusinessImpl;
//    
//    @Context
//	HttpServletRequest request;
//    
//    @Override
//	public Response doSearch(StockDailyImportExportDTO obj) throws Exception{
//    	DataListDTO data;
//    	try {
//			data= stockDailyImportExportBusinessImpl.doSearch(obj,request);
//			return Response.ok(data).build();
//    	}catch (IllegalArgumentException e) {
//			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
//		}
//	}
//    
//    @Override
//   	public Response doSearchStock(StockDTO obj) {
//   		// TODO Auto-generated method stub
//   		DataListDTO data= stockDailyImportExportBusinessImpl.doSearchStock(obj,request);
//           if (data == null) {
//               return Response.status(Response.Status.BAD_REQUEST).build();
//           } else {
//           	return Response.ok(data).build();
//           }
//   	}
//}
