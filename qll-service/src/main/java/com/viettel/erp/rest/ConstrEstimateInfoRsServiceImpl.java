/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.viettel.erp.business.ConstrEstimateInfoBusinessImpl;
import com.viettel.erp.dto.ConstrEstimateInfoDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 *
 * @author HungLQ9
 */
public class ConstrEstimateInfoRsServiceImpl implements ConstrEstimateInfoRsService {

    @Autowired
    ConstrEstimateInfoBusinessImpl constrEstimateInfoBusinessImpl;

    @Override
    public Response getConstrEstimateInfo() {
        List<ConstrEstimateInfoDTO> ls = constrEstimateInfoBusinessImpl.getAll();
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
    public Response getConstrEstimateInfoById(Long id) {
        ConstrEstimateInfoDTO obj = (ConstrEstimateInfoDTO) constrEstimateInfoBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateConstrEstimateInfo(ConstrEstimateInfoDTO obj) {
        Long id = constrEstimateInfoBusinessImpl.update(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addConstrEstimateInfo(ConstrEstimateInfoDTO obj) {
        Long id = constrEstimateInfoBusinessImpl.save(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(Response.Status.CREATED).build();
        }
    }

    @Override
    public Response deleteConstrEstimateInfo(Long id) {
        ConstrEstimateInfoDTO obj = (ConstrEstimateInfoDTO) constrEstimateInfoBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            constrEstimateInfoBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }
}
