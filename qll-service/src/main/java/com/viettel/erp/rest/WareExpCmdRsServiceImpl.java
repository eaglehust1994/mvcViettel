/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.viettel.erp.business.WareExpCmdBusinessImpl;
import com.viettel.erp.dto.ConstrConstructionsDTO;
import com.viettel.erp.dto.WareExpCmdDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 *
 * @author HungLQ9
 */
public class WareExpCmdRsServiceImpl implements WareExpCmdRsService {

    @Autowired
    WareExpCmdBusinessImpl wareExpCmdBusinessImpl;
    
    @Override
	public Response deleteWareExpCmd(List<String> listID) {
    	String result = wareExpCmdBusinessImpl.deleteWareExpCmd(listID);
    	HashMap<Integer, String> hmap = new HashMap<Integer, String>();
    	hmap.put(1, result);
    	if (result == null || "".equals(result)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(hmap).build();
        }
	}
    
    @Override
	public Response getwareExpCmdByPxk(List<String> listPxk) {
    	List<WareExpCmdDTO> ls = wareExpCmdBusinessImpl.getwareExpCmdByPxk(listPxk);
    	if (ls == null || "".equals(ls)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(ls).build();
        }
	}

	@Override
	public Response getWareExpCmdByConstrt(ConstrConstructionsDTO obj) {
		List<WareExpCmdDTO> ls = wareExpCmdBusinessImpl.getWareExpCmdByConstrt(obj);
    	if (ls == null || "".equals(ls)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(ls).build();
        }
	}

    @Override
    public Response getWareExpCmd() {
        List<WareExpCmdDTO> ls = wareExpCmdBusinessImpl.getAll();
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
    public Response getWareExpCmdById(Long id) {
        WareExpCmdDTO obj = (WareExpCmdDTO) wareExpCmdBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateWareExpCmd(WareExpCmdDTO obj) {
        Long id = wareExpCmdBusinessImpl.update(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addWareExpCmd(WareExpCmdDTO obj) {
        Long id = wareExpCmdBusinessImpl.save(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(Response.Status.CREATED).build();
        }
    }

    @Override
    public Response deleteWareExpCmd(Long id) {
        WareExpCmdDTO obj = (WareExpCmdDTO) wareExpCmdBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            wareExpCmdBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }

	

	
}
