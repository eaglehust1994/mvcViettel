///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.viettel.wms.rest;
//
//import com.viettel.wms.business.GoodsTypeBusinessImpl;
//import com.viettel.wms.dto.GoodsTypeDTO;
//import com.viettel.service.base.dto.DataListDTO;
//import javax.ws.rs.core.Response;
//import org.springframework.beans.factory.annotation.Autowired;
//
///**
// *
// * @author TuanNB
// */
//public class GoodsTypeRsServiceImpl implements GoodsTypeRsService {
//
//	// protected final Logger log = Logger.getLogger(UserRsService.class);
//    @Autowired
//    GoodsTypeBusinessImpl goodsTypeBusinessImpl;
//
//    @Override
//	public Response getList(GoodsTypeDTO obj) {
//		DataListDTO data= goodsTypeBusinessImpl.getList(obj);
//		return Response.ok(data).build();
//	}
//
//	@Override
//	public Response getGoodTypeList(GoodsTypeDTO obj) {
//		return Response.ok(goodsTypeBusinessImpl.getGoodTypeList(obj)).build();
//	}
//}
