/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import com.viettel.erp.business.VSysUserBusinessImpl;
import com.viettel.erp.dto.VSysUserDTO;
import com.viettel.service.base.dto.DataListDTO;
import java.util.List;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author HungLQ9
 */
public class VSysUserRsServiceImpl implements VSysUserRsService {

    protected final Logger log = Logger.getLogger(VSysUserRsServiceImpl.class);
    @Autowired
    VSysUserBusinessImpl vSysUserBusinessImpl;

    @Override
    public Response getVSysUser() {
        List<VSysUserDTO> ls = vSysUserBusinessImpl.getAll();
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
    public Response getVSysUserById(Long id) {
        VSysUserDTO obj = (VSysUserDTO) vSysUserBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateVSysUser(VSysUserDTO obj) {
        Long id = vSysUserBusinessImpl.update(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addVSysUser(VSysUserDTO obj) {
        Long id = vSysUserBusinessImpl.save(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(Response.Status.CREATED).build();
        }
    }

    @Override
    public Response deleteVSysUser(Long id) {
        VSysUserDTO obj = (VSysUserDTO) vSysUserBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            vSysUserBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }

	@Override
	public Response getUserByLoginName(String loginName) {
		VSysUserDTO obj = (VSysUserDTO) vSysUserBusinessImpl.getUserByLoginName(loginName);
        if (obj == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok(obj).build();
        }
	}
}
