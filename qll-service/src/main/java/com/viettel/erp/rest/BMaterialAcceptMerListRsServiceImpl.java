/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.viettel.erp.business.BMaterialAcceptMerListBusinessImpl;
import com.viettel.erp.dto.BMaterialAcceptMerListDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 *
 * @author HungLQ9
 */
public class BMaterialAcceptMerListRsServiceImpl implements BMaterialAcceptMerListRsService {

    @Autowired
    BMaterialAcceptMerListBusinessImpl bMaterialAcceptMerListBusinessImpl;

    @Override
    public Response getBMaterialAcceptMerList() {
    	System.out.println("linh");
        List<BMaterialAcceptMerListDTO> ls = bMaterialAcceptMerListBusinessImpl.getAll();
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
    public Response getBMaterialAcceptMerListById(Long id) {
        BMaterialAcceptMerListDTO obj = (BMaterialAcceptMerListDTO) bMaterialAcceptMerListBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateBMaterialAcceptMerList(BMaterialAcceptMerListDTO obj) {
        Long id = bMaterialAcceptMerListBusinessImpl.update(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addBMaterialAcceptMerList(BMaterialAcceptMerListDTO obj) {
        Long id = bMaterialAcceptMerListBusinessImpl.save(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(Response.Status.CREATED).build();
        }
    }

    @Override
    public Response deleteBMaterialAcceptMerList(Long id) {
        BMaterialAcceptMerListDTO obj = (BMaterialAcceptMerListDTO) bMaterialAcceptMerListBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            bMaterialAcceptMerListBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }
    
	@Override
	public Response getAccpetMerList(Long bMaterialAcceptanceId) {
//        System.out.println("=========");
        List<BMaterialAcceptMerListDTO> ls = bMaterialAcceptMerListBusinessImpl.getAccpetMerList(bMaterialAcceptanceId);
//         System.out.println("========="+ls.get(0).getContractQuantity());
        if (ls == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(ls).build();
        }
	}
}
