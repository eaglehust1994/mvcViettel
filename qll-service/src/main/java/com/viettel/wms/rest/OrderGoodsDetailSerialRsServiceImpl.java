/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.rest;

import com.viettel.wms.business.OrderGoodsDetailSerialBusinessImpl;
import com.viettel.wms.dto.OrderGoodsDetailSerialDTO;
import com.viettel.service.base.dto.DataListDTO;
import java.util.List;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author HungLQ9
 */
public class OrderGoodsDetailSerialRsServiceImpl implements OrderGoodsDetailSerialRsService {

	//  protected final Logger log = Logger.getLogger(UserRsService.class);
    @Autowired
    OrderGoodsDetailSerialBusinessImpl orderGoodsDetailSerialBusinessImpl;

    @Override
    public Response getOrderGoodsDetailSerial() {
        List<OrderGoodsDetailSerialDTO> ls = orderGoodsDetailSerialBusinessImpl.getAll();
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
    public Response getOrderGoodsDetailSerialById(Long id) {
        OrderGoodsDetailSerialDTO obj = (OrderGoodsDetailSerialDTO) orderGoodsDetailSerialBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateOrderGoodsDetailSerial(OrderGoodsDetailSerialDTO obj) {
        Long id = orderGoodsDetailSerialBusinessImpl.update(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addOrderGoodsDetailSerial(OrderGoodsDetailSerialDTO obj) {
        Long id = orderGoodsDetailSerialBusinessImpl.save(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(Response.Status.CREATED).build();
        }
    }

    @Override
    public Response deleteOrderGoodsDetailSerial(Long id) {
        OrderGoodsDetailSerialDTO obj = (OrderGoodsDetailSerialDTO) orderGoodsDetailSerialBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            orderGoodsDetailSerialBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }
}
