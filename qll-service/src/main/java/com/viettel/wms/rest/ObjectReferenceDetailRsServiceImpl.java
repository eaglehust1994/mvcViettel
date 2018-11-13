/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.rest;

import com.viettel.wms.business.ObjectReferenceDetailBusinessImpl;
import com.viettel.wms.dto.ObjectReferenceDetailDTO;
import com.viettel.service.base.dto.DataListDTO;
import java.util.List;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author HungLQ9
 */
public class ObjectReferenceDetailRsServiceImpl implements ObjectReferenceDetailRsService {

    @Autowired
    ObjectReferenceDetailBusinessImpl objectReferenceDetailBusinessImpl;

    @Override
    public Response getObjectReferenceDetail() {
        List<ObjectReferenceDetailDTO> ls = objectReferenceDetailBusinessImpl.getAll();
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
    public Response getObjectReferenceDetailById(Long id) {
        ObjectReferenceDetailDTO obj = (ObjectReferenceDetailDTO) objectReferenceDetailBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateObjectReferenceDetail(ObjectReferenceDetailDTO obj) {
        Long id = objectReferenceDetailBusinessImpl.update(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addObjectReferenceDetail(ObjectReferenceDetailDTO obj) {
        Long id = objectReferenceDetailBusinessImpl.save(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(Response.Status.CREATED).build();
        }
    }

    @Override
    public Response deleteObjectReferenceDetail(Long id) {
        ObjectReferenceDetailDTO obj = (ObjectReferenceDetailDTO) objectReferenceDetailBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            objectReferenceDetailBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }

	
}
