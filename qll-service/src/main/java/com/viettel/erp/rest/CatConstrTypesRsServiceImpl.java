/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.viettel.erp.business.CatConstrTypesBusinessImpl;
import com.viettel.erp.dto.CatConstrTypesDTO;

/**
 *
 * @author HungLQ9
 */
public class CatConstrTypesRsServiceImpl implements CatConstrTypesRsService {

    @Autowired
    CatConstrTypesBusinessImpl catConstrTypesBusinessImpl;

    @Override
    public Response getCatConstrTypes() {
        List<CatConstrTypesDTO> ls = catConstrTypesBusinessImpl.getAll();
        if (ls == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(ls).build();
    }

    @Override
    public Response getCatConstrTypesById(Long id) {
        CatConstrTypesDTO obj = (CatConstrTypesDTO) catConstrTypesBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateCatConstrTypes(CatConstrTypesDTO obj) {
        Long id = catConstrTypesBusinessImpl.update(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addCatConstrTypes(CatConstrTypesDTO obj) {
        Long id = catConstrTypesBusinessImpl.save(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(Response.Status.CREATED).build();
        }
    }

    @Override
    public Response deleteCatConstrTypes(Long id) {
        CatConstrTypesDTO obj = (CatConstrTypesDTO) catConstrTypesBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            catConstrTypesBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }
}
