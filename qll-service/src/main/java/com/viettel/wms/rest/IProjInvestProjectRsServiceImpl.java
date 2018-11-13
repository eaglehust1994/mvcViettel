/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.rest;

import com.viettel.wms.business.IProjInvestProjectBusinessImpl;
//import com.viettel.wms.dto.AppParamDTO;
import com.viettel.wms.dto.IProjInvestProjectDTO;
import com.viettel.service.base.dto.DataListDTO;
import java.util.List;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author HungLQ9
 */
public class IProjInvestProjectRsServiceImpl implements IProjInvestProjectRsService {

//    protected final Logger log = Logger.getLogger(UserRsService.class);
    @Autowired
    IProjInvestProjectBusinessImpl iProjInvestProjectBusinessImpl;

    @Override
    public Response getIProjInvestProject() {
        List<IProjInvestProjectDTO> ls = iProjInvestProjectBusinessImpl.getAll();
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
    public Response getIProjInvestProjectById(Long id) {
        IProjInvestProjectDTO obj = (IProjInvestProjectDTO) iProjInvestProjectBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateIProjInvestProject(IProjInvestProjectDTO obj) {
        Long id = iProjInvestProjectBusinessImpl.update(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addIProjInvestProject(IProjInvestProjectDTO obj) {
        Long id = iProjInvestProjectBusinessImpl.save(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(Response.Status.CREATED).build();
        }
    }

    @Override
    public Response deleteIProjInvestProject(Long id) {
        IProjInvestProjectDTO obj = (IProjInvestProjectDTO) iProjInvestProjectBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            iProjInvestProjectBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }

	@Override
	public Response getForAutoComplete(IProjInvestProjectDTO obj) {
		return Response.ok(iProjInvestProjectBusinessImpl.getForAutoComplete(obj)).build();
	}
	
	@Override
	public Response doSearch(IProjInvestProjectDTO obj) {
		DataListDTO data= iProjInvestProjectBusinessImpl.doSearch(obj);
		return Response.ok(data).build();
	}

	@Override
	public Response getFromProjectCode(String code) {
		// TODO Auto-generated method stub
		List<String> lst = iProjInvestProjectBusinessImpl.getFromProjectCode(code);
		if (lst == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(lst).build();
        }
	}
}
