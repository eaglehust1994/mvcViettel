/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.viettel.erp.business.EstimatesItemsChildBusinessImpl;
import com.viettel.erp.dto.EstimatesItemsChildDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 *
 * @author HungLQ9
 */
public class EstimatesItemsChildRsServiceImpl implements EstimatesItemsChildRsService {

    @Autowired
    EstimatesItemsChildBusinessImpl estimatesItemsChildBusinessImpl;

    @Override
    public Response getEstimatesItemsChild() {
        List<EstimatesItemsChildDTO> ls = estimatesItemsChildBusinessImpl.getAll();
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
    public Response getEstimatesItemsChildById(Long id) {
        EstimatesItemsChildDTO obj = (EstimatesItemsChildDTO) estimatesItemsChildBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateEstimatesItemsChild(EstimatesItemsChildDTO obj) {
        Long id = estimatesItemsChildBusinessImpl.update(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addEstimatesItemsChild(EstimatesItemsChildDTO obj) {
        Long id = estimatesItemsChildBusinessImpl.save(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(Response.Status.CREATED).build();
        }
    }

    @Override
    public Response deleteEstimatesItemsChild(Long id) {
        EstimatesItemsChildDTO obj = (EstimatesItemsChildDTO) estimatesItemsChildBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            estimatesItemsChildBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }
    
    @Override
	public Response getAllItemsChildInContruct(Long constructionId) {
	
		List<EstimatesItemsChildDTO> ls = estimatesItemsChildBusinessImpl.getAllItemsChildInContruct(constructionId);
		if (ls == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(ls).build();
        }
	}

	@Override
	public Response getListEstimateItemschild(EstimatesItemsChildDTO rightDTO) {
		List<EstimatesItemsChildDTO> ls = estimatesItemsChildBusinessImpl.getListEstimateItemschild(rightDTO);
		if (ls == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(ls).build();
        }
	}	
}
