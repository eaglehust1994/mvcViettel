/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.viettel.erp.business.ConstrAcceptLostEntityBusinessImpl;
import com.viettel.erp.dto.ConstrAcceptLostEntityDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 *
 * @author HungLQ9
 */
public class ConstrAcceptLostEntityRsServiceImpl implements ConstrAcceptLostEntityRsService {

    @Autowired
    ConstrAcceptLostEntityBusinessImpl constrAcceptLostEntityBusinessImpl;

    @Override
    public Response getConstrAcceptLostEntity() {
        List<ConstrAcceptLostEntityDTO> ls = constrAcceptLostEntityBusinessImpl.getAll();
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
    public Response getConstrAcceptLostEntityById(Long id) {
        ConstrAcceptLostEntityDTO obj = (ConstrAcceptLostEntityDTO) constrAcceptLostEntityBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateConstrAcceptLostEntity(ConstrAcceptLostEntityDTO obj) {
        Long id = constrAcceptLostEntityBusinessImpl.update(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addConstrAcceptLostEntity(ConstrAcceptLostEntityDTO obj) {
        Long id = constrAcceptLostEntityBusinessImpl.save(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(Response.Status.CREATED).build();
        }
    }

    @Override
    public Response deleteConstrAcceptLostEntity(Long id) {
        ConstrAcceptLostEntityDTO obj = (ConstrAcceptLostEntityDTO) constrAcceptLostEntityBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            constrAcceptLostEntityBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }
}
