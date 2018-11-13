/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.viettel.erp.business.CatCurrencyBusinessImpl;
import com.viettel.erp.dto.CatCurrencyDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 *
 * @author HungLQ9
 */
public class CatCurrencyRsServiceImpl implements CatCurrencyRsService {

    @Autowired
    CatCurrencyBusinessImpl catCurrencyBusinessImpl;

    @Override
    public Response getCatCurrency() {
        List<CatCurrencyDTO> ls = catCurrencyBusinessImpl.getAll();
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
    public Response getCatCurrencyById(Long id) {
        CatCurrencyDTO obj = (CatCurrencyDTO) catCurrencyBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateCatCurrency(CatCurrencyDTO obj) {
        Long id = catCurrencyBusinessImpl.update(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addCatCurrency(CatCurrencyDTO obj) {
        Long id = catCurrencyBusinessImpl.save(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(Response.Status.CREATED).build();
        }
    }

    @Override
    public Response deleteCatCurrency(Long id) {
        CatCurrencyDTO obj = (CatCurrencyDTO) catCurrencyBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            catCurrencyBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }
}
