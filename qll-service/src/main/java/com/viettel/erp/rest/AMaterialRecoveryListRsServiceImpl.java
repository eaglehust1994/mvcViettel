/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.viettel.erp.business.AMaterialRecoveryListBusinessImpl;
import com.viettel.erp.dto.AMaterialRecoveryListDTO;
import com.viettel.erp.dto.AMaterialRecoveryListModelDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 *
 * @author HungLQ9
 */
public class AMaterialRecoveryListRsServiceImpl implements AMaterialRecoveryListRsService {

    @Autowired
    AMaterialRecoveryListBusinessImpl aMaterialRecoveryListBusinessImpl;

    @Override
    public Response getAMaterialRecoveryList() {
        List<AMaterialRecoveryListDTO> ls = aMaterialRecoveryListBusinessImpl.getAll();
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
    public Response getAMaterialRecoveryListById(Long id) {
        AMaterialRecoveryListDTO obj = (AMaterialRecoveryListDTO) aMaterialRecoveryListBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateAMaterialRecoveryList(AMaterialRecoveryListDTO obj) {
        Long id = aMaterialRecoveryListBusinessImpl.update(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addAMaterialRecoveryList(AMaterialRecoveryListDTO obj) {
        Long id = aMaterialRecoveryListBusinessImpl.save(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(Response.Status.CREATED).build();
        }
    }

    @Override
    public Response deleteAMaterialRecoveryList(Long id) {
        AMaterialRecoveryListDTO obj = (AMaterialRecoveryListDTO) aMaterialRecoveryListBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            aMaterialRecoveryListBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }

	@Override
	public Response findByConstructId(Long constructId) {
		List<AMaterialRecoveryListModelDTO> ls = aMaterialRecoveryListBusinessImpl.findByConstructId(constructId);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}
}
