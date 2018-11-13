/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.viettel.erp.business.CatFileInvoiceBusinessImpl;
import com.viettel.erp.dto.CatFileInvoiceDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 *
 * @author HungLQ9
 */
public class CatFileInvoiceRsServiceImpl implements CatFileInvoiceRsService {

    @Autowired
    CatFileInvoiceBusinessImpl catFileInvoiceBusinessImpl;

    @Override
    public Response getCatFileInvoice() {
        List<CatFileInvoiceDTO> ls = catFileInvoiceBusinessImpl.getAll();
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
    public Response getCatFileInvoiceById(Long id) {
        CatFileInvoiceDTO obj = (CatFileInvoiceDTO) catFileInvoiceBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateCatFileInvoice(CatFileInvoiceDTO obj) {
        Long id = catFileInvoiceBusinessImpl.update(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addCatFileInvoice(CatFileInvoiceDTO obj) {
        Long id = catFileInvoiceBusinessImpl.save(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(Response.Status.CREATED).build();
        }
    }

    @Override
    public Response deleteCatFileInvoice(Long id) {
        CatFileInvoiceDTO obj = (CatFileInvoiceDTO) catFileInvoiceBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            catFileInvoiceBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }

    @Override
    public Response findByExistProfile(Long id) {

        List<CatFileInvoiceDTO> ls = catFileInvoiceBusinessImpl.findByExistProfile(id);
        if (ls == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(ls).build();

    }

	@Override
	public Response getListInvoice() {
		List<CatFileInvoiceDTO> ls = catFileInvoiceBusinessImpl.getListInvoice();
		if (ls != null) {
		CatFileInvoiceDTO dto = new CatFileInvoiceDTO();
		dto.setCatFileInvoiceId(0l);
		dto.setFileInvoiceName("Tất cả");
		ls.add(0, dto);
        return Response.ok(ls).build();
        }
		return Response.status(Response.Status.BAD_REQUEST).build();
	}
}
