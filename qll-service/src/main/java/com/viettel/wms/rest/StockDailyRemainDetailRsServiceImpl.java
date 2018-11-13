/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.rest;

import java.util.List;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.viettel.service.base.dto.DataListDTO;
import com.viettel.wms.business.StockDailyRemainDetailBusinessImpl;
import com.viettel.wms.dto.StockDailyRemainDetailDTO;

/**
 *
 * @author HungLQ9
 */
public class StockDailyRemainDetailRsServiceImpl implements StockDailyRemainDetailRsService {

	//  protected final Logger log = Logger.getLogger(UserRsService.class);
	
    @Autowired
    StockDailyRemainDetailBusinessImpl stockDailyRemainDetailBusinessImpl;
    static Logger LOGGER = LoggerFactory.getLogger(StockDailyRemainDetailRsServiceImpl.class);
    @Override
    public Response getStockDailyRemainDetail() {
        List<StockDailyRemainDetailDTO> ls = stockDailyRemainDetailBusinessImpl.getAll();
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
    public Response getStockDailyRemainDetailById(Long id) {
        StockDailyRemainDetailDTO obj = (StockDailyRemainDetailDTO) stockDailyRemainDetailBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateStockDailyRemainDetail(StockDailyRemainDetailDTO obj) {
        Long id = stockDailyRemainDetailBusinessImpl.update(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addStockDailyRemainDetail(StockDailyRemainDetailDTO obj) {
        Long id = stockDailyRemainDetailBusinessImpl.save(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(Response.Status.CREATED).build();
        }
    }

    @Override
    public Response deleteStockDailyRemainDetail(Long id) {
        StockDailyRemainDetailDTO obj = (StockDailyRemainDetailDTO) stockDailyRemainDetailBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            stockDailyRemainDetailBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }
    
   
}
