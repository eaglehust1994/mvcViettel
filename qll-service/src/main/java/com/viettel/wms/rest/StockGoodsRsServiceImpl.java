/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.rest;

import com.viettel.wms.business.StockGoodsBusinessImpl;
import com.viettel.wms.dto.ShipmentTaxDTO;
import com.viettel.wms.dto.StockGoodsDTO;
import com.viettel.service.base.dto.DataListDTO;

import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author HungLQ9
 */
public class StockGoodsRsServiceImpl implements StockGoodsRsService {

	//  protected final Logger log = Logger.getLogger(UserRsService.class);
    @Autowired
    StockGoodsBusinessImpl stockGoodsBusinessImpl;

    @Override
    public Response getStockGoods() {
        List<StockGoodsDTO> ls = stockGoodsBusinessImpl.getAll();
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
    public Response getStockGoodsById(Long id) {
        StockGoodsDTO obj = (StockGoodsDTO) stockGoodsBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateStockGoods(StockGoodsDTO obj) {
        Long id = stockGoodsBusinessImpl.update(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addStockGoods(StockGoodsDTO obj) {
        Long id = stockGoodsBusinessImpl.save(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(Response.Status.CREATED).build();
        }
    }

    @Override
    public Response deleteStockGoods(Long id) {
        StockGoodsDTO obj = (StockGoodsDTO) stockGoodsBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            stockGoodsBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }

	@Override
	public Response detailStockGoods(StockGoodsDTO obj) {
		List<StockGoodsDTO> ls = stockGoodsBusinessImpl.detailStockGoods(obj);
        if (ls == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(ls).build();
        }
	}

	@Override
	public Response detailStockGoodsSerial(StockGoodsDTO obj) {
		List<StockGoodsDTO> ls = stockGoodsBusinessImpl.detailStockGoodsSerial(obj);
        if (ls == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(ls).build();
        }
	}
}
