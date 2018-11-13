/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.viettel.erp.business.ConstrAcceptMerListBusinessImpl;
import com.viettel.erp.dto.ConstrAcceptMerListDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 *
 * @author HungLQ9
 */
public class ConstrAcceptMerListRsServiceImpl implements ConstrAcceptMerListRsService {

    @Autowired
    ConstrAcceptMerListBusinessImpl constrAcceptMerListBusinessImpl;

    @Override
    public Response getConstrAcceptMerList() {
        List<ConstrAcceptMerListDTO> ls = constrAcceptMerListBusinessImpl.getAll();
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
    public Response getConstrAcceptMerListById(Long id) {
        ConstrAcceptMerListDTO obj = (ConstrAcceptMerListDTO) constrAcceptMerListBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateConstrAcceptMerList(ConstrAcceptMerListDTO obj) {
        Long id = constrAcceptMerListBusinessImpl.update(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addConstrAcceptMerList(ConstrAcceptMerListDTO obj) {
        Long id = constrAcceptMerListBusinessImpl.save(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(Response.Status.CREATED).build();
        }
    }

    @Override
    public Response deleteConstrAcceptMerList(Long id) {
        ConstrAcceptMerListDTO obj = (ConstrAcceptMerListDTO) constrAcceptMerListBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            constrAcceptMerListBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }
}
