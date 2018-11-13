/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.viettel.erp.business.CntConstrReferBusinessImpl;
import com.viettel.erp.dto.CntConstrReferDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 *
 * @author HungLQ9
 */
public class CntConstrReferRsServiceImpl implements CntConstrReferRsService {

    @Autowired
    CntConstrReferBusinessImpl cntConstrReferBusinessImpl;

    @Override
    public Response getCntConstrRefer() {
        List<CntConstrReferDTO> ls = cntConstrReferBusinessImpl.getAll();
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
    public Response getCntConstrReferById(Long id) {
        CntConstrReferDTO obj = (CntConstrReferDTO) cntConstrReferBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateCntConstrRefer(CntConstrReferDTO obj) {
        Long id = cntConstrReferBusinessImpl.update(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addCntConstrRefer(CntConstrReferDTO obj) {
        Long id = cntConstrReferBusinessImpl.save(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(Response.Status.CREATED).build();
        }
    }

    @Override
    public Response deleteCntConstrRefer(Long id) {
        CntConstrReferDTO obj = (CntConstrReferDTO) cntConstrReferBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            cntConstrReferBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }
}
