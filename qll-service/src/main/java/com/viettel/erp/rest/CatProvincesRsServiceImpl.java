/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.viettel.erp.business.CatProvincesBusinessImpl;
import com.viettel.erp.dto.CatProvincesDTO;

/**
 *
 * @author HungLQ9
 */
public class CatProvincesRsServiceImpl implements CatProvincesRsService {

    @Autowired
    CatProvincesBusinessImpl catProvincesBusinessImpl;

    @Override
    public Response getCatProvinces() {
        List<CatProvincesDTO> ls = catProvincesBusinessImpl.getAll();
        if (ls == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(ls).build();
    }

    @Override
    public Response getCatProvincesById(Long id) {
        CatProvincesDTO obj = (CatProvincesDTO) catProvincesBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateCatProvinces(CatProvincesDTO obj) {
        Long id = catProvincesBusinessImpl.update(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addCatProvinces(CatProvincesDTO obj) {
        Long id = catProvincesBusinessImpl.save(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(Response.Status.CREATED).build();
        }
    }

    @Override
    public Response deleteCatProvinces(Long id) {
        CatProvincesDTO obj = (CatProvincesDTO) catProvincesBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            catProvincesBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }
}
