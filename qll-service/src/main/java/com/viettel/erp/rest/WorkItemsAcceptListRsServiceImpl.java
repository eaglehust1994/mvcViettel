/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import com.viettel.erp.business.WorkItemsAcceptListBusinessImpl;
import com.viettel.erp.dto.WorkItemsAcceptListDTO;
import com.viettel.service.base.dto.DataListDTO;
import java.util.List;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author HungLQ9
 */
public class WorkItemsAcceptListRsServiceImpl implements WorkItemsAcceptListRsService {

    @Autowired
    WorkItemsAcceptListBusinessImpl workItemsAcceptListBusinessImpl;

    @Override
    public Response getWorkItemsAcceptList() {
        List<WorkItemsAcceptListDTO> ls = workItemsAcceptListBusinessImpl.getAll();
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
    public Response getWorkItemsAcceptListById(Long id) {
        WorkItemsAcceptListDTO obj = (WorkItemsAcceptListDTO) workItemsAcceptListBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateWorkItemsAcceptList(WorkItemsAcceptListDTO obj) {
        Long id = workItemsAcceptListBusinessImpl.update(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addWorkItemsAcceptList(WorkItemsAcceptListDTO obj) {
        Long id = workItemsAcceptListBusinessImpl.save(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(Response.Status.CREATED).build();
        }
    }

    @Override
    public Response deleteWorkItemsAcceptList(Long id) {
        WorkItemsAcceptListDTO obj = (WorkItemsAcceptListDTO) workItemsAcceptListBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            workItemsAcceptListBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }
}
