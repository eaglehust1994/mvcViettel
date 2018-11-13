/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.viettel.erp.business.UserEmployeeBusinessImpl;
import com.viettel.erp.dto.UserEmployeeDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 *
 * @author HungLQ9
 */
public class UserEmployeeRsServiceImpl implements UserEmployeeRsService {

    @Autowired
    UserEmployeeBusinessImpl userEmployeeBusinessImpl;

    @Override
    public Response getUserEmployee() {
        List<UserEmployeeDTO> ls = userEmployeeBusinessImpl.getAll();
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
    public Response getUserEmployeeById(Long id) {
        UserEmployeeDTO obj = (UserEmployeeDTO) userEmployeeBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateUserEmployee(UserEmployeeDTO obj) {
        Long id = userEmployeeBusinessImpl.update(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addUserEmployee(UserEmployeeDTO obj) {
        Long id = userEmployeeBusinessImpl.save(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(Response.Status.CREATED).build();
        }
    }

    @Override
    public Response deleteUserEmployee(Long id) {
        UserEmployeeDTO obj = (UserEmployeeDTO) userEmployeeBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            userEmployeeBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }
}
