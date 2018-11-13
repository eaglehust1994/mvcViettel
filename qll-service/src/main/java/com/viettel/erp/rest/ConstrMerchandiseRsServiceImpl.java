/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.viettel.erp.business.ConstrMerchandiseBusinessImpl;
import com.viettel.erp.dto.ConstrMerchandiseDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 *
 * @author HungLQ9
 */
public class ConstrMerchandiseRsServiceImpl implements ConstrMerchandiseRsService {

    @Autowired
    ConstrMerchandiseBusinessImpl constrMerchandiseBusinessImpl;

    @Override
    public Response getConstrMerchandise() {
        List<ConstrMerchandiseDTO> ls = constrMerchandiseBusinessImpl.getAll();
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
    public Response getConstrMerchandiseById(Long id) {
        ConstrMerchandiseDTO obj = (ConstrMerchandiseDTO) constrMerchandiseBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateConstrMerchandise(ConstrMerchandiseDTO obj) {
        Long id = constrMerchandiseBusinessImpl.update(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addConstrMerchandise(ConstrMerchandiseDTO obj) {
        Long id = constrMerchandiseBusinessImpl.save(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(Response.Status.CREATED).build();
        }
    }

    @Override
    public Response deleteConstrMerchandise(Long id) {
        ConstrMerchandiseDTO obj = (ConstrMerchandiseDTO) constrMerchandiseBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            constrMerchandiseBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }

	@Override
	public Response getValueConstrMerchandise(Long id) {
		List<ConstrMerchandiseDTO> obj = (List<ConstrMerchandiseDTO>) constrMerchandiseBusinessImpl.gettValueConstrMerchandiseById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
	}
}
