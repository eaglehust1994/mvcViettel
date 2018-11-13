/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.rest;

import com.viettel.wms.business.ObjectReferenceGoodsDetailBusinessImpl;
import com.viettel.wms.dto.ObjectReferenceGoodsDetailDTO;
import com.viettel.service.base.dto.DataListDTO;
import java.util.List;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author HungLQ9
 */
public class ObjectReferenceGoodsDetailRsServiceImpl implements ObjectReferenceGoodsDetailRsService {

    @Autowired
    ObjectReferenceGoodsDetailBusinessImpl objectReferenceGoodsDetailBusinessImpl;

    @Override
    public Response getObjectReferenceGoodsDetail() {
        List<ObjectReferenceGoodsDetailDTO> ls = objectReferenceGoodsDetailBusinessImpl.getAll();
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
    public Response getObjectReferenceGoodsDetailById(Long id) {
        ObjectReferenceGoodsDetailDTO obj = (ObjectReferenceGoodsDetailDTO) objectReferenceGoodsDetailBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateObjectReferenceGoodsDetail(ObjectReferenceGoodsDetailDTO obj) {
        Long id = objectReferenceGoodsDetailBusinessImpl.update(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addObjectReferenceGoodsDetail(ObjectReferenceGoodsDetailDTO obj) {
        Long id = objectReferenceGoodsDetailBusinessImpl.save(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(Response.Status.CREATED).build();
        }
    }

    @Override
    public Response deleteObjectReferenceGoodsDetail(Long id) {
        ObjectReferenceGoodsDetailDTO obj = (ObjectReferenceGoodsDetailDTO) objectReferenceGoodsDetailBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            objectReferenceGoodsDetailBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }

	@Override
	public Response getGoodsDetail(ObjectReferenceGoodsDetailDTO obj) {
		List<ObjectReferenceGoodsDetailDTO> lst = (List<ObjectReferenceGoodsDetailDTO>) objectReferenceGoodsDetailBusinessImpl.getGoodsDetail(obj);
        if (lst == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(lst).build();
        }
	}
}
