/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.rest;

import com.viettel.wms.business.DepartmentBusinessImpl;
import com.viettel.wms.dto.DepartmentDTO;
import com.viettel.service.base.dto.DataListDTO;
import java.util.List;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author HungLQ9
 */
public class DepartmentRsServiceImpl implements DepartmentRsService {

  //  protected final Logger log = Logger.getLogger(UserRsService.class);
    @Autowired
    DepartmentBusinessImpl departmentBusinessImpl;

    @Override
    public Response getDepartment() {
        List<DepartmentDTO> ls = departmentBusinessImpl.getAll();
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
    public Response getDepartmentById(Long id) {
        DepartmentDTO obj = (DepartmentDTO) departmentBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateDepartment(DepartmentDTO obj) {
        Long id = departmentBusinessImpl.update(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addDepartment(DepartmentDTO obj) {
        Long id = departmentBusinessImpl.save(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(Response.Status.CREATED).build();
        }
    }

    @Override
    public Response deleteDepartment(Long id) {
        DepartmentDTO obj = (DepartmentDTO) departmentBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            departmentBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }

	@Override
	public Response getall(DepartmentDTO obj) {
		 return Response.ok(departmentBusinessImpl.getall(obj)).build();
	}
	
	@Override
	public Response getForAutocompleteDept(DepartmentDTO obj) {
		 return Response.ok(departmentBusinessImpl.getDeptForAutocomplete(obj)).build();
	}
}
