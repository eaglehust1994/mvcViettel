/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.rest;

import com.viettel.wms.business.ManufacturerBusinessImpl;
import com.viettel.wms.dto.ManufacturerDTO;
import com.viettel.service.base.dto.DataListDTO;
import java.util.List;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author HungLQ9
 */
public class ManufacturerRsServiceImpl implements ManufacturerRsService {

    @Autowired
    ManufacturerBusinessImpl manufacturerBusinessImpl;

    @Override
    public Response getManufacturer() {
        List<ManufacturerDTO> ls = manufacturerBusinessImpl.getAll();
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
    public Response getManufacturerById(Long id) {
        ManufacturerDTO obj = (ManufacturerDTO) manufacturerBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateManufacturer(ManufacturerDTO obj) {
        Long id = manufacturerBusinessImpl.update(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addManufacturer(ManufacturerDTO obj) {
        Long id = manufacturerBusinessImpl.save(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(Response.Status.CREATED).build();
        }
    }

    @Override
    public Response deleteManufacturer(Long id) {
        ManufacturerDTO obj = (ManufacturerDTO) manufacturerBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            manufacturerBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }

	@Override
	public Response autocomplete(ManufacturerDTO obj) {
		// TODO Auto-generated method stub
		return Response.ok(manufacturerBusinessImpl.getForAutocomplete(obj)).build();
	}
}
