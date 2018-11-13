///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.viettel.wms.rest;
//
//import com.viettel.wms.business.OrderGoodsDetailBusinessImpl;
//import com.viettel.wms.dto.OrderGoodsDTO;
//import com.viettel.wms.dto.OrderGoodsDetailDTO;
//import com.viettel.service.base.dto.DataListDTO;
//import java.util.List;
//import javax.ws.rs.core.Response;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//
///**
// *
// * @author HungLQ9
// */
//public class OrderGoodsDetailRsServiceImpl implements OrderGoodsDetailRsService {
//
//	// protected final Logger log = Logger.getLogger(UserRsService.class);
//    @Autowired
//    OrderGoodsDetailBusinessImpl orderGoodsDetailBusinessImpl;
//
//    @Override
//    public Response getOrderGoodsDetail() {
//        List<OrderGoodsDetailDTO> ls = orderGoodsDetailBusinessImpl.getAll();
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
//    public Response getOrderGoodsDetailById(Long id) {
//        OrderGoodsDetailDTO obj = (OrderGoodsDetailDTO) orderGoodsDetailBusinessImpl.getOneById(id);
//        if (obj == null) {
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        } else {
//            return Response.ok(obj).build();
//        }
//    }
//
//    @Override
//    public Response updateOrderGoodsDetail(OrderGoodsDetailDTO obj) {
//        Long id = orderGoodsDetailBusinessImpl.update(obj);
//        if (id == 0l) {
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        } else {
//            return Response.ok().build();
//        }
//
//    }
//
//    @Override
//    public Response addOrderGoodsDetail(OrderGoodsDetailDTO obj) {
//        Long id = orderGoodsDetailBusinessImpl.save(obj);
//        if (id == 0l) {
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        } else {
//            return Response.ok(Response.Status.CREATED).build();
//        }
//    }
//
//    @Override
//    public Response deleteOrderGoodsDetail(Long id) {
//        OrderGoodsDetailDTO obj = (OrderGoodsDetailDTO) orderGoodsDetailBusinessImpl.getOneById(id);
//        if (obj == null) {
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        } else {
//            orderGoodsDetailBusinessImpl.delete(obj);
//            return Response.ok(Response.Status.NO_CONTENT).build();
//        }
//    }
//
//	@Override
//	public Response doSearchGoodsDetailForImportReq(OrderGoodsDetailDTO obj) {
//		// TODO Auto-generated method stub
//		DataListDTO data = orderGoodsDetailBusinessImpl.doSearchGoodsDetailForImportReq(obj);
//        if (data == null) {
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        } else {
//        	return Response.ok(data).build();
//        }
//	}
//
//	@Override
//	public Response doSearchGoodsDetailForExportReq() {
//		DataListDTO data = orderGoodsDetailBusinessImpl.doSearchGoodsDetailForExportReq();
//        if (data == null) {
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        } else {
//        	return Response.ok(data).build();
//        }
//	}
//
//	@Override
//	public Response doSearchGoodsDetailForExportReq(OrderGoodsDTO obj) {
//		DataListDTO data = orderGoodsDetailBusinessImpl.doSearchGoodsDetailForExportReq(obj);
//        if (data == null) {
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        } else {
//        	return Response.ok(data).build();
//        }
//	}
//
//	@Override
//	public Response getOrderGoodsDetailByOrderGoodId(Long OrderGoodId) {
//		List<OrderGoodsDetailDTO> lst = orderGoodsDetailBusinessImpl.getGoodsDetailByOrderGoodId(OrderGoodId);
//        if (lst == null) {
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        } else {
//            return Response.ok(lst).build();
//        }
//	}
//}
