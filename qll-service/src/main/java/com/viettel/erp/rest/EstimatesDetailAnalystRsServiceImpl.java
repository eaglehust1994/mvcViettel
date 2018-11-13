/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.viettel.erp.business.EstimatesDetailAnalystBusinessImpl;
import com.viettel.erp.dto.EstimatesDetailAnalystDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 *
 * @author HungLQ9
 */
public class EstimatesDetailAnalystRsServiceImpl implements EstimatesDetailAnalystRsService {

    @Autowired
    EstimatesDetailAnalystBusinessImpl estimatesDetailAnalystBusinessImpl;

    @Override
    public Response doSearchEstimatesDetailAnalyst(EstimatesDetailAnalystDTO obj) {
    	List<EstimatesDetailAnalystDTO> ls = estimatesDetailAnalystBusinessImpl.doSearchEstimatesDetailAnalyst(obj);
        if (ls == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(ls).build();
        }
    }
    
    @Override
    public Response getEstimatesDetailAnalyst() {
        List<EstimatesDetailAnalystDTO> ls = estimatesDetailAnalystBusinessImpl.getAll();
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
    public Response getEstimatesDetailAnalystById(Long id) {
        EstimatesDetailAnalystDTO obj = (EstimatesDetailAnalystDTO) estimatesDetailAnalystBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateEstimatesDetailAnalyst(EstimatesDetailAnalystDTO obj) {
        Long id = estimatesDetailAnalystBusinessImpl.update(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addEstimatesDetailAnalyst(EstimatesDetailAnalystDTO obj) {
        Long id = estimatesDetailAnalystBusinessImpl.save(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(Response.Status.CREATED).build();
        }
    }

    @Override
    public Response deleteEstimatesDetailAnalyst(Long id) {
        EstimatesDetailAnalystDTO obj = (EstimatesDetailAnalystDTO) estimatesDetailAnalystBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            estimatesDetailAnalystBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }
	@Override
	public Response getAcceptanceList() {
		List<EstimatesDetailAnalystDTO> ls = estimatesDetailAnalystBusinessImpl.getAcceptanceList();
        if (ls == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(ls).build();
        }
	} 
	
	@Override
	public Response getAcceptanceListById(Long constructId) {
        List<EstimatesDetailAnalystDTO> ls = estimatesDetailAnalystBusinessImpl.getAcceptanceListById(constructId);
        if (ls == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(ls).build();
        }
	}
}
