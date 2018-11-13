/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.viettel.erp.business.VConstructionHcqtBusinessImpl;
import com.viettel.erp.dto.TotalNumDTO;
import com.viettel.erp.dto.VConstructionHcqtDTO;
import com.viettel.erp.dto.VConstructionsHcqtSearchDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 *
 * @author HungLQ9
 */
public class VConstructionHcqtRsServiceImpl implements VConstructionHcqtRsService {

    @Autowired
    VConstructionHcqtBusinessImpl vConstructionHcqtBusinessImpl;

    @Override
    public Response getVConstructionHcqt() {
        List<VConstructionHcqtDTO> ls = vConstructionHcqtBusinessImpl.getAll();
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
    public Response getVConstructionHcqtById(Long id) {
        VConstructionHcqtDTO obj = (VConstructionHcqtDTO) vConstructionHcqtBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateVConstructionHcqt(VConstructionHcqtDTO obj) {
        Long id = vConstructionHcqtBusinessImpl.update(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addVConstructionHcqt(VConstructionHcqtDTO obj) {
        Long id = vConstructionHcqtBusinessImpl.save(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(Response.Status.CREATED).build();
        }
    }

    @Override
    public Response deleteVConstructionHcqt(Long id) {
        VConstructionHcqtDTO obj = (VConstructionHcqtDTO) vConstructionHcqtBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            vConstructionHcqtBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }

	@Override
	public Response getAllandSearch(VConstructionsHcqtSearchDTO obj) {
		TotalNumDTO totalnum = new TotalNumDTO(0);
		
		List<VConstructionHcqtDTO> ls = vConstructionHcqtBusinessImpl.getAllandSearch(obj,totalnum);

		//String queryCount_ = vConstructionHcqtBusinessImpl.queryCount();
        if (ls == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
           
            DataListDTO data = new DataListDTO();
            data.setData(ls);
            data.setTotal(totalnum.getNum());
            data.setSize(totalnum.getNum());
            data.setStart(1);
            return Response.ok(data).build();
        }
	}

	@Override
	public Response getContractTotalValue(Long id) {
		List<VConstructionHcqtDTO> obj = (List<VConstructionHcqtDTO>) vConstructionHcqtBusinessImpl.getContractTotalValueById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
	}
}
