/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.viettel.erp.business.CurrentStateHandoverListBusinessImpl;
import com.viettel.erp.dto.CurrentStateHandoverListDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 *
 * @author HungLQ9
 */
public class CurrentStateHandoverListRsServiceImpl implements CurrentStateHandoverListRsService {

    @Autowired
    CurrentStateHandoverListBusinessImpl currentStateHandoverListBusinessImpl;

    @Override
    public Response getCurrentStateHandoverList() {
        List<CurrentStateHandoverListDTO> ls = currentStateHandoverListBusinessImpl.getAll();
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
    public Response getCurrentStateHandoverListById(Long id) {
        CurrentStateHandoverListDTO obj = (CurrentStateHandoverListDTO) currentStateHandoverListBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateCurrentStateHandoverList(CurrentStateHandoverListDTO obj) {
        Long id = currentStateHandoverListBusinessImpl.update(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addCurrentStateHandoverList(CurrentStateHandoverListDTO obj) {
        Long id = currentStateHandoverListBusinessImpl.save(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(Response.Status.CREATED).build();
        }
    }

    @Override
    public Response deleteCurrentStateHandoverList(Long id) {
        CurrentStateHandoverListDTO obj = (CurrentStateHandoverListDTO) currentStateHandoverListBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            currentStateHandoverListBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }

	@Override
	public Response getListCurrentStateHandoverByCrStHoId(Long id) {
		List<CurrentStateHandoverListDTO> ls  = currentStateHandoverListBusinessImpl.getCurrentStateHandoverByListId(id);
		 if (ls == null) {
	            return Response.status(Response.Status.BAD_REQUEST).build();
	        } else {
	            return Response.ok(ls).build();
	        }
	}

	@Override
	public Response deleteMutilRecord(List<String> listId) {
		boolean re = currentStateHandoverListBusinessImpl.deleteMutilRecord(listId);
		if(re){
			  return Response.ok().build();
		}else{
			 return Response.status(Response.Status.BAD_REQUEST).build();
		}
		
	}
}
