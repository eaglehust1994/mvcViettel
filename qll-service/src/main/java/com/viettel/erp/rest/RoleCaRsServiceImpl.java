/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.viettel.erp.business.RoleCaBusinessImpl;
import com.viettel.erp.dto.RoleCaDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 *
 * @author HungLQ9
 */
public class RoleCaRsServiceImpl implements RoleCaRsService {

    @Autowired
    RoleCaBusinessImpl roleCaBusinessImpl;
    
    @Override
	public Response getListChucVu(RoleCaDTO obj) {
    	List<RoleCaDTO> ls = roleCaBusinessImpl.getListChucVu(obj);
        if (ls == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(ls).build();
        }
	}

    @Override
    public Response getRoleCa() {
        List<RoleCaDTO> ls = roleCaBusinessImpl.getAll();
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
    public Response getRoleCaById(Long id) {
        RoleCaDTO obj = (RoleCaDTO) roleCaBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateRoleCa(RoleCaDTO obj) {
        Long id = roleCaBusinessImpl.update(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addRoleCa(RoleCaDTO obj) {
        Long id = roleCaBusinessImpl.save(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(Response.Status.CREATED).build();
        }
    }

    @Override
    public Response deleteRoleCa(Long id) {
        RoleCaDTO obj = (RoleCaDTO) roleCaBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            roleCaBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }

	@Override
	public Response getRoleCaByType(Long type, Long groupSide) {
		List<RoleCaDTO> ls = roleCaBusinessImpl.getRoleCaByType(type, groupSide);
		if (ls == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(ls).build();
        }
	}

	
}
