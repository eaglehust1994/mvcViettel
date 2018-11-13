/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.viettel.erp.business.VConstrConstructionsBusinessImpl;
import com.viettel.erp.dto.VConstrConstructionsDTO;
import com.viettel.erp.dto.VConstrConstructionsSearchDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 *
 * @author HungLQ9
 */
public class VConstrConstructionsRsServiceImpl implements VConstrConstructionsRsService {

    @Autowired
    VConstrConstructionsBusinessImpl vConstrConstructionsBusinessImpl;

    @Override
    public Response getVConstrConstructions() {
        List<VConstrConstructionsDTO> ls = vConstrConstructionsBusinessImpl.getAll();
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
    public Response getVConstrConstructionsById(Long id) {
        VConstrConstructionsDTO obj = (VConstrConstructionsDTO) vConstrConstructionsBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateVConstrConstructions(VConstrConstructionsDTO obj) {
        Long id = vConstrConstructionsBusinessImpl.update(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addVConstrConstructions(VConstrConstructionsDTO obj) {
        Long id = vConstrConstructionsBusinessImpl.save(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(Response.Status.CREATED).build();
        }
    }

    @Override
    public Response deleteVConstrConstructions(Long id) {
        VConstrConstructionsDTO obj = (VConstrConstructionsDTO) vConstrConstructionsBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            vConstrConstructionsBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }

	@Override
	public Response getAllandSearch(VConstrConstructionsSearchDTO obj) {
		 List<VConstrConstructionsDTO> ls = vConstrConstructionsBusinessImpl.getAllandSearch(obj);
	        if (ls == null) {
	            return Response.status(Response.Status.BAD_REQUEST).build();
	        } else {
	           
	            return Response.ok(ls).build();
	        }
	}
}
