///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.viettel.wms.rest;
//
//import java.io.File;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.ws.rs.core.Context;
//import javax.ws.rs.core.Response;
//import javax.ws.rs.core.Response.ResponseBuilder;
//
//import org.hibernate.Session;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.viettel.wms.business.StockGoodsTotalReponseBusinessImpl;
//import com.viettel.wms.constant.Constants;
//import com.viettel.wms.dto.StockGoodsTotalReponseDTO;
//import com.viettel.service.base.dto.DataListDTO;
//
//public class StockGoodsTotalReponseRsServiceImpl implements
//		StockGoodsTotalReponseRsService {
//
//	// protected final Logger log = Logger.getLogger(UserRsService.class);
//	@Autowired
//	StockGoodsTotalReponseBusinessImpl stockGoodsTotalReponseBusinessImpl;
//
//	@Context
//	HttpServletRequest request;
//
//	// tim kiem bao cao kha nang dap ung tuannb
//	@Override
//	public Response doSearch(StockGoodsTotalReponseDTO obj) throws Exception {
//		DataListDTO data;
//		try {
//			data = stockGoodsTotalReponseBusinessImpl.doSearch(obj, request);
//			return Response.ok(data).build();
//		} catch (IllegalArgumentException e) {
//			return Response.ok()
//					.entity(Collections.singletonMap("error", e.getMessage()))
//					.build();
//		}
//
//	}
//	// end tuannb
//}
