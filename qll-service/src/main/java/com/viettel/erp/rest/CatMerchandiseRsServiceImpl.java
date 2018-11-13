/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.viettel.erp.business.CatMerchandiseBusinessImpl;
import com.viettel.erp.dto.CatMerchandiseDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 *
 * @author HungLQ9
 */
public class CatMerchandiseRsServiceImpl implements CatMerchandiseRsService {

    @Autowired
    CatMerchandiseBusinessImpl catMerchandiseBusinessImpl;

    @Override
    public Response getCatMerchandise() {
        List<CatMerchandiseDTO> ls = catMerchandiseBusinessImpl.getAll();
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
    public Response getCatMerchandiseById(Long id) {
        CatMerchandiseDTO obj = (CatMerchandiseDTO) catMerchandiseBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateCatMerchandise(CatMerchandiseDTO obj) {
        Long id = catMerchandiseBusinessImpl.update(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addCatMerchandise(CatMerchandiseDTO obj) {
        Long id = catMerchandiseBusinessImpl.save(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(Response.Status.CREATED).build();
        }
    }

    @Override
    public Response deleteCatMerchandise(Long id) {
        CatMerchandiseDTO obj = (CatMerchandiseDTO) catMerchandiseBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            catMerchandiseBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }
}
