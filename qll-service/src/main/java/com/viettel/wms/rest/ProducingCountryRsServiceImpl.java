/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.rest;

import com.viettel.wms.business.ProducingCountryBusinessImpl;
import com.viettel.wms.dto.ProducingCountryDTO;
import com.viettel.service.base.dto.DataListDTO;
import java.util.List;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author HungLQ9
 */
public class ProducingCountryRsServiceImpl implements ProducingCountryRsService {

    @Autowired
    ProducingCountryBusinessImpl producingCountryBusinessImpl;

    @Override
    public Response getProducingCountry() {
        List<ProducingCountryDTO> ls = producingCountryBusinessImpl.getAll();
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
    public Response getProducingCountryById(Long id) {
        ProducingCountryDTO obj = (ProducingCountryDTO) producingCountryBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateProducingCountry(ProducingCountryDTO obj) {
        Long id = producingCountryBusinessImpl.update(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addProducingCountry(ProducingCountryDTO obj) {
        Long id = producingCountryBusinessImpl.save(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(Response.Status.CREATED).build();
        }
    }

    @Override
    public Response deleteProducingCountry(Long id) {
        ProducingCountryDTO obj = (ProducingCountryDTO) producingCountryBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            producingCountryBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }

	@Override
	public Response getForAutocomplete(ProducingCountryDTO obj) {
		// TODO Auto-generated method stub
		return Response.ok(producingCountryBusinessImpl.getForAutocomplete(obj)).build();
	}
}
