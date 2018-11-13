/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.rest;

import com.viettel.wms.business.StockGoodsSerialBusinessImpl;
import com.viettel.wms.dto.OrderDTO;
import com.viettel.wms.dto.StockGoodsSerialDTO;
import com.viettel.wms.dto.StockTransDTO;
import com.viettel.service.base.dto.DataListDTO;
import java.util.List;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author HungLQ9
 */
public class StockGoodsSerialRsServiceImpl implements StockGoodsSerialRsService {

	//  protected final Logger log = Logger.getLogger(UserRsService.class);
    @Autowired
    StockGoodsSerialBusinessImpl stockGoodsSerialBusinessImpl;

    @Override
    public Response getStockGoodsSerial() {
        List<StockGoodsSerialDTO> ls = stockGoodsSerialBusinessImpl.getAll();
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
    public Response getStockGoodsSerialById(Long id) {
        StockGoodsSerialDTO obj = (StockGoodsSerialDTO) stockGoodsSerialBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateStockGoodsSerial(StockGoodsSerialDTO obj) {
        Long id = stockGoodsSerialBusinessImpl.update(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addStockGoodsSerial(StockGoodsSerialDTO obj) {
        Long id = stockGoodsSerialBusinessImpl.save(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(Response.Status.CREATED).build();
        }
    }

    @Override
    public Response deleteStockGoodsSerial(Long id) {
        StockGoodsSerialDTO obj = (StockGoodsSerialDTO) stockGoodsSerialBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            stockGoodsSerialBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }

	@Override
	public Response doSearchFindSerial(StockGoodsSerialDTO obj) {
		// TODO Auto-generated method stub
		DataListDTO data= stockGoodsSerialBusinessImpl.doSearchFindSerial(obj);
        if (data == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
        	return Response.ok(data).build();
        }
	}

	@Override
	public Response doSearchHistory(StockTransDTO obj) {
		DataListDTO data= stockGoodsSerialBusinessImpl.doSearchHistory(obj);
        if (data == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
        	return Response.ok(data).build();
        }
	}
}
