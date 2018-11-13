/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.viettel.erp.business.ConstrAcceptLostNoteBusinessImpl;
import com.viettel.erp.dto.ConstrAcceptLostNoteDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 *
 * @author HungLQ9
 */
public class ConstrAcceptLostNoteRsServiceImpl implements ConstrAcceptLostNoteRsService {

    @Autowired
    ConstrAcceptLostNoteBusinessImpl constrAcceptLostNoteBusinessImpl;

    @Override
    public Response getConstrAcceptLostNote() {
        List<ConstrAcceptLostNoteDTO> ls = constrAcceptLostNoteBusinessImpl.getAll();
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
    public Response getConstrAcceptLostNoteById(Long id) {
        ConstrAcceptLostNoteDTO obj = (ConstrAcceptLostNoteDTO) constrAcceptLostNoteBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateConstrAcceptLostNote(ConstrAcceptLostNoteDTO obj) {
        Long id = constrAcceptLostNoteBusinessImpl.update(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addConstrAcceptLostNote(ConstrAcceptLostNoteDTO obj) {
        Long id = constrAcceptLostNoteBusinessImpl.save(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(Response.Status.CREATED).build();
        }
    }

    @Override
    public Response deleteConstrAcceptLostNote(Long id) {
        ConstrAcceptLostNoteDTO obj = (ConstrAcceptLostNoteDTO) constrAcceptLostNoteBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            constrAcceptLostNoteBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }

	@Override
	public Response getValueLoss(Long id) {
		List<ConstrAcceptLostNoteDTO> obj = (List<ConstrAcceptLostNoteDTO>) constrAcceptLostNoteBusinessImpl.getValueLossById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
	}
}
