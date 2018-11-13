/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.viettel.erp.business.IntergratedContractBusinessImpl;
import com.viettel.erp.dto.CatEmployeeDTO;
import com.viettel.erp.dto.CatPartnersDTO;
import com.viettel.erp.dto.IntergratedContractDTO;

/**
 *
 * @author HungLQ9
 */
public class IntergratedContractRsServiceImpl implements IntergratedContractRsService {

    @Autowired
    IntergratedContractBusinessImpl intergratedContractBusinessImpl;
    
    @Override
	public Response getPartner(CatEmployeeDTO dto) {
    	List<CatPartnersDTO> ls = intergratedContractBusinessImpl.getPartner(dto);
    	if (ls == null || "".equals(ls)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(ls).build();
        }
	}

    @Override
	public Response getIntergratedContractConstrt(IntergratedContractDTO dto) {  	
    	List<IntergratedContractDTO> ls = intergratedContractBusinessImpl.getIntergratedContractConstrt(dto);
    	
        if (ls == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(ls.get(0)).build();
        }
	}
    
    @Override
    public Response getIntergratedContract() {
        List<IntergratedContractDTO> ls = intergratedContractBusinessImpl.getAll();
        if (ls == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(ls).build();
        }
    }

    @Override
    public Response getIntergratedContractById(Long id) {
        IntergratedContractDTO obj = (IntergratedContractDTO) intergratedContractBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateIntergratedContract(IntergratedContractDTO obj) {
        Long id = intergratedContractBusinessImpl.update(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addIntergratedContract(IntergratedContractDTO obj) {
        Long id = intergratedContractBusinessImpl.save(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(Response.Status.CREATED).build();
        }
    }

    @Override
    public Response deleteIntergratedContract(Long id) {
        IntergratedContractDTO obj = (IntergratedContractDTO) intergratedContractBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            intergratedContractBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }

	

	
}
