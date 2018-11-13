/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.viettel.erp.business.ConstrAcceptWorkListBusinessImpl;
import com.viettel.erp.dto.ConstrAcceptWorkListDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 *
 * @author HungLQ9
 */
public class ConstrAcceptWorkListRsServiceImpl implements ConstrAcceptWorkListRsService {

    @Autowired
    ConstrAcceptWorkListBusinessImpl constrAcceptWorkListBusinessImpl;

    @Override
    public Response getConstrAcceptWorkList() {
        List<ConstrAcceptWorkListDTO> ls = constrAcceptWorkListBusinessImpl.getAll();
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
    public Response getAllbyConstructId(Long constructId) {
        List<ConstrAcceptWorkListDTO> ls = constrAcceptWorkListBusinessImpl.getAllbyConstructId(constructId);
        if (ls == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(ls).build();
        }
    }

    @Override
    public Response getConstrAcceptWorkListById(Long id) {
        ConstrAcceptWorkListDTO obj = (ConstrAcceptWorkListDTO) constrAcceptWorkListBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateConstrAcceptWorkList(ConstrAcceptWorkListDTO obj) {
        Long id = constrAcceptWorkListBusinessImpl.update(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addConstrAcceptWorkList(ConstrAcceptWorkListDTO obj) {
        Long id = constrAcceptWorkListBusinessImpl.save(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(Response.Status.CREATED).build();
        }
    }

    @Override
    public Response deleteConstrAcceptWorkList(Long id) {
        ConstrAcceptWorkListDTO obj = (ConstrAcceptWorkListDTO) constrAcceptWorkListBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            constrAcceptWorkListBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }

	@Override
	public Response getProposedSettlement(Long id) {
		List<ConstrAcceptWorkListDTO> obj = (List<ConstrAcceptWorkListDTO>) constrAcceptWorkListBusinessImpl.getProposedSettlementById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
	}

	
}
