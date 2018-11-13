/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.viettel.erp.business.AdvancePaymentProposalBusinessImpl;
import com.viettel.erp.dto.AdvancePaymentProposalDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 *
 * @author HungLQ9
 */
public class AdvancePaymentProposalRsServiceImpl implements AdvancePaymentProposalRsService {

    @Autowired
    AdvancePaymentProposalBusinessImpl advancePaymentProposalBusinessImpl;

    @Override
    public Response getAdvancePaymentProposal() {
        List<AdvancePaymentProposalDTO> ls = advancePaymentProposalBusinessImpl.getAll();
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
    public Response getAdvancePaymentProposalById(Long id) {
        AdvancePaymentProposalDTO obj = (AdvancePaymentProposalDTO) advancePaymentProposalBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateAdvancePaymentProposal(AdvancePaymentProposalDTO obj) {
        Long id = advancePaymentProposalBusinessImpl.update(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addAdvancePaymentProposal(AdvancePaymentProposalDTO obj) {
    	System.out.println("run add function");
        Long id = advancePaymentProposalBusinessImpl.save(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(Response.Status.CREATED).build();
        }
    }

    @Override
    public Response deleteAdvancePaymentProposal(Long id) {
        AdvancePaymentProposalDTO obj = (AdvancePaymentProposalDTO) advancePaymentProposalBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            advancePaymentProposalBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }

	@Override
	public Response getAdvancePaymentProposalByConstructId(Long id) {
		AdvancePaymentProposalDTO obj = advancePaymentProposalBusinessImpl.getAdvancePaymentByConstructId(id);
		if(obj==null){
			return Response.status(Response.Status.BAD_REQUEST).build();
		}else{
			return Response.ok(obj).build();
		}
	}
}
