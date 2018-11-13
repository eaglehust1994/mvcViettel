/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.rest;

import com.viettel.wms.business.OrderPatternGoodsBusinessImpl;
import com.viettel.wms.dto.OrderPatternDTO;
import com.viettel.wms.dto.OrderPatternGoodsDTO;
import com.viettel.service.base.dto.DataListDTO;

import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author HungLQ9
 */
public class OrderPatternGoodsRsServiceImpl implements OrderPatternGoodsRsService {

	// protected final Logger log = Logger.getLogger(UserRsService.class);
    @Autowired
    OrderPatternGoodsBusinessImpl orderPatternGoodsBusinessImpl;

    @Override
    public Response getOrderPatternGoods() {
        List<OrderPatternGoodsDTO> ls = orderPatternGoodsBusinessImpl.getAll();
        if (ls == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            DataListDTO data = new DataListDTO();
            data.setData(ls);
            data.setTotal(ls.size());
            data.setSize(ls.size());
            data.setStart(1);
            return Response.ok(data).build();
        }
    }

    @Override
    public Response getOrderPatternGoodsById(Long id) {
        OrderPatternGoodsDTO obj = (OrderPatternGoodsDTO) orderPatternGoodsBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateOrderPatternGoods(OrderPatternGoodsDTO obj) {
        Long id = orderPatternGoodsBusinessImpl.update(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addOrderPatternGoods(OrderPatternGoodsDTO obj) {
        Long id = orderPatternGoodsBusinessImpl.save(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(Response.Status.CREATED).build();
        }
    }

    @Override
    public Response deleteOrderPatternGoods(Long id) {
        OrderPatternGoodsDTO obj = (OrderPatternGoodsDTO) orderPatternGoodsBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            orderPatternGoodsBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }
    
    @Override
  	public Response doSearch(OrderPatternGoodsDTO obj) {
  		// TODO Auto-generated method stub
  		DataListDTO data= orderPatternGoodsBusinessImpl.doSearch(obj);
          if (data == null) {
              return Response.status(Response.Status.BAD_REQUEST).build();
          } else {
          	return Response.ok(data).build();
          }
  	}
    
    @Override
	public Response getPatternGoodsByOrderPatternId(OrderPatternGoodsDTO obj) {
		// TODO Auto-generated method stub
		DataListDTO data= orderPatternGoodsBusinessImpl.getPatternGoodsByOrderPatternId(obj);
        if (data == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
        	return Response.ok(data).build();
        }
	}
    
}
