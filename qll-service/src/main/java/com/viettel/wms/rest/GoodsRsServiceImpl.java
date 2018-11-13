/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.rest;

import com.viettel.wms.business.GoodsBusinessImpl;
import com.viettel.wms.dto.GoodsDTO;
import com.viettel.service.base.dto.DataListDTO;
import java.util.List;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author HungLQ9
 */
public class GoodsRsServiceImpl implements GoodsRsService {

  //  protected final Logger log = Logger.getLogger(UserRsService.class);
    @Autowired
    GoodsBusinessImpl goodsBusinessImpl;

    @Override
    public Response getGoods() {
        List<GoodsDTO> ls = goodsBusinessImpl.getAll();
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
    public Response getGoodsById(Long id) {
        GoodsDTO obj = (GoodsDTO) goodsBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateGoods(GoodsDTO obj) {
        Long id = goodsBusinessImpl.update(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addGoods(GoodsDTO obj) {
        Long id = goodsBusinessImpl.save(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(Response.Status.CREATED).build();
        }
    }

    @Override
    public Response deleteGoods(Long id) {
        GoodsDTO obj = (GoodsDTO) goodsBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            goodsBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }

	@Override
	public Response doSearchActiveGoods(GoodsDTO obj) {
		// TODO Auto-generated method stub
		DataListDTO data = goodsBusinessImpl.doSearchActiveGoods(obj);
		return Response.ok(data).build();
	}

	@Override
	public Response getForAutoCompleteGoods(GoodsDTO obj) {
		// TODO Auto-generated method stub
		return Response.ok(goodsBusinessImpl.getForAutoCompleteGoods(obj)).build();
	}

	@Override
	public Response getGoodsForOrder(GoodsDTO obj) {
		// TODO Auto-generated method stub
		return Response.ok(goodsBusinessImpl.getGoodsForOrder(obj)).build();
	}
	
		@Override
	public Response getGoodsById(GoodsDTO obj) {
		// TODO Auto-generated method stub
	   
            return Response.ok(goodsBusinessImpl.getGoodById(obj)).build();

	}
}
