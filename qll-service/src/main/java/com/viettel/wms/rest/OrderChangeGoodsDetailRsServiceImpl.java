///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.viettel.wms.rest;
//
//import java.util.Collections;
//import java.util.List;
//
//import javax.ws.rs.core.Response;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.viettel.service.base.dto.DataListDTO;
//import com.viettel.wms.business.OrderChangeGoodsDetailBusinessImpl;
//import com.viettel.wms.dto.OrderChangeGoodsDTO;
//import com.viettel.wms.dto.OrderChangeGoodsDetailDTO;
//import com.viettel.wms.dto.StockTransDetailDTO;
//
///**
// *
// * @author HungLQ9
// */
//public class OrderChangeGoodsDetailRsServiceImpl implements
//		OrderChangeGoodsDetailRsService {
//
//	// protected final Logger log = Logger.getLogger(UserRsService.class);
//	@Autowired
//	OrderChangeGoodsDetailBusinessImpl orderChangeGoodsDetailBusinessImpl;
//
//	// @Autowired
//	// OrderChangeGoodsBusinessImpl orderChangeGoodsBusinessImpl;
//
//	/**
//	 * Danh sach  yeu cau thay doi
//	 */
//	@Override
//	public Response getOrderChangeGoodsDetail() {
//		List<OrderChangeGoodsDetailDTO> ls = orderChangeGoodsDetailBusinessImpl
//				.getAll();
//		if (ls == null) {
//			return Response.status(Response.Status.BAD_REQUEST).build();
//		} else {
//			DataListDTO data = new DataListDTO();
//			data.setData(ls);
//			data.setTotal(ls.size());
//			data.setSize(ls.size());
//			data.setStart(1);
//			return Response.ok(data).build();
//		}
//	}
//
//	/**
//	 * Lay yeu cau thay doi theo id 
//	 */
//	@Override
//	public Response getOrderChangeGoodsDetailById(Long id) {
//		OrderChangeGoodsDetailDTO obj = (OrderChangeGoodsDetailDTO) orderChangeGoodsDetailBusinessImpl
//				.getOneById(id);
//		if (obj == null) {
//			return Response.status(Response.Status.BAD_REQUEST).build();
//		} else {
//			return Response.ok(obj).build();
//		}
//	}
//
//	/**
//	 * update chi tiet yeu cau thay doi
//	 */
//	@Override
//	public Response updateOrderChangeGoodsDetail(OrderChangeGoodsDetailDTO obj) {
//		Long id = orderChangeGoodsDetailBusinessImpl.update(obj);
//		if (id == 0l) {
//			return Response.status(Response.Status.BAD_REQUEST).build();
//		} else {
//			return Response.ok().build();
//		}
//
//	}
//
//	@Override
//	public Response addOrderChangeGoodsDetail(OrderChangeGoodsDetailDTO obj) {
//		OrderChangeGoodsDTO dto = new OrderChangeGoodsDTO();
//		obj.setOrderChangeGoodsId(dto.getOrderChangeGoodsId());
//		obj.setGoodsCode(dto.getCode());
//		Long id = orderChangeGoodsDetailBusinessImpl.save(obj);
//		if (id == 0l) {
//			return Response.status(Response.Status.BAD_REQUEST).build();
//		} else {
//			return Response.ok(Response.Status.CREATED).build();
//		}
//	}
//
//	/**
//	 * Xoa yeu cau thay doi
//	 */
//	@Override
//	public Response deleteOrderChangeGoodsDetail(Long id) {
//		OrderChangeGoodsDetailDTO obj = (OrderChangeGoodsDetailDTO) orderChangeGoodsDetailBusinessImpl
//				.getOneById(id);
//
//		if (obj == null) {
//			return Response.status(Response.Status.BAD_REQUEST).build();
//		} else {
//			orderChangeGoodsDetailBusinessImpl.delete(obj);
//			return Response.ok(Response.Status.NO_CONTENT).build();
//		}
//	}
//
//	/**
//	 * Danh sach yeu cau thay doi
//	 */
//	@Override
//	public Response doSearch(OrderChangeGoodsDetailDTO obj) {
//		// TODO Auto-generated method stub
//		DataListDTO data = orderChangeGoodsDetailBusinessImpl.doSearch(obj);
//		return Response.ok(data).build();
//	}
//	
//}
