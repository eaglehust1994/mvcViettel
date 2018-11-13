/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.rest;

import com.viettel.wms.business.ObjectReferenceGoodsBusinessImpl;
import com.viettel.wms.dto.ObjectReferenceGoodsDTO;
import com.viettel.service.base.dto.DataListDTO;
import java.util.List;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author HungLQ9
 */
public class ObjectReferenceGoodsRsServiceImpl implements ObjectReferenceGoodsRsService {

    @Autowired
    ObjectReferenceGoodsBusinessImpl objectReferenceGoodsBusinessImpl;

    @Override
    public Response getObjectReferenceGoods() {
        List<ObjectReferenceGoodsDTO> ls = objectReferenceGoodsBusinessImpl.getAll();
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
    public Response getObjectReferenceGoodsById(Long id) {
        ObjectReferenceGoodsDTO obj = (ObjectReferenceGoodsDTO) objectReferenceGoodsBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateObjectReferenceGoods(ObjectReferenceGoodsDTO obj) {
        Long id = objectReferenceGoodsBusinessImpl.update(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addObjectReferenceGoods(ObjectReferenceGoodsDTO obj) {
        Long id = objectReferenceGoodsBusinessImpl.save(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(Response.Status.CREATED).build();
        }
    }

    @Override
    public Response deleteObjectReferenceGoods(Long id) {
        ObjectReferenceGoodsDTO obj = (ObjectReferenceGoodsDTO) objectReferenceGoodsBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            objectReferenceGoodsBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }

	@Override
	public Response getGoodsInfoBeforeWarrantyByCode(String code) {
		// TODO Auto-generated method stub
		List<ObjectReferenceGoodsDTO> lst = (List<ObjectReferenceGoodsDTO>) objectReferenceGoodsBusinessImpl.getGoodsInfoBeforeWarrantyByCode(code);
        if (lst == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(lst).build();
        }
	}

	@Override
	public Response getGoodsInfoAfterWarrantyByCode(String code) {
		// TODO Auto-generated method stub
		List<ObjectReferenceGoodsDTO> lst = (List<ObjectReferenceGoodsDTO>) objectReferenceGoodsBusinessImpl.getGoodsInfoAfterWarrantyByCode(code);
        if (lst == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(lst).build();
        }
	}

	@Override
	public Response getGoodsInfoFromConstructionByCode(String code) {
		// TODO Auto-generated method stub
		List<ObjectReferenceGoodsDTO> lst = (List<ObjectReferenceGoodsDTO>) objectReferenceGoodsBusinessImpl.getGoodsInfoBeforeWarrantyByCode(code);
        if (lst == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(lst).build();
        }
	}

	@Override
	public Response getGoodsInfoFromDepartmentByCode(String code) {
		// TODO Auto-generated method stub
		List<ObjectReferenceGoodsDTO> lst = (List<ObjectReferenceGoodsDTO>) objectReferenceGoodsBusinessImpl.getGoodsInfoBeforeWarrantyByCode(code);
        if (lst == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(lst).build();
        }
	}

	@Override
	public Response getGoodsInfoFromLoanByCode(String code) {
		// TODO Auto-generated method stub
		List<ObjectReferenceGoodsDTO> lst = (List<ObjectReferenceGoodsDTO>) objectReferenceGoodsBusinessImpl.getGoodsInfoBeforeWarrantyByCode(code);
        if (lst == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(lst).build();
        }
	}
}
