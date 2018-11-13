/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.rest;

import com.viettel.wms.business.ObjectReferenceBusinessImpl;
import com.viettel.wms.dto.ObjectReferenceDTO;
import com.viettel.wms.dto.ShipmentDTO;
import com.viettel.service.base.dto.DataListDTO;
import java.util.List;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author HungLQ9
 */
public class ObjectReferenceRsServiceImpl implements ObjectReferenceRsService {

	// protected final Logger log = Logger.getLogger(UserRsService.class);
    @Autowired
    ObjectReferenceBusinessImpl objectReferenceBusinessImpl;

    @Override
    public Response getObjectReference() {
        List<ObjectReferenceDTO> ls = objectReferenceBusinessImpl.getAll();
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
    public Response getObjectReferenceById(Long id) {
        ObjectReferenceDTO obj = (ObjectReferenceDTO) objectReferenceBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateObjectReference(ObjectReferenceDTO obj) {
        Long id = objectReferenceBusinessImpl.update(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addObjectReference(ObjectReferenceDTO obj) {
        Long id = objectReferenceBusinessImpl.save(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(Response.Status.CREATED).build();
        }
    }

    @Override
    public Response deleteObjectReference(Long id) {
        ObjectReferenceDTO obj = (ObjectReferenceDTO) objectReferenceBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            objectReferenceBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }

	@Override
	public Response getDataKCS(ObjectReferenceDTO obj) {
		ObjectReferenceDTO data = objectReferenceBusinessImpl.getDataKCS(obj);
		return Response.ok(data).build();
	}

	@Override
	public Response getGoodsInfoBeforeWarrantyCode(String code) {
		// TODO Auto-generated method stub
		List<String> lst = objectReferenceBusinessImpl.getGoodsInfoBeforeWarrantyCode(code);
		if (lst == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(lst).build();
		}
	}

	@Override
	public Response getGoodsInfoAfterWarrantyCode(String code) {
		// TODO Auto-generated method stub
		List<String> lst = objectReferenceBusinessImpl.getGoodsInfoAfterWarrantyCode(code);
		if (lst == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(lst).build();
		}
	}

	@Override
	public Response getGoodsInfoFromConstructionCode(String code) {
		// TODO Auto-generated method stub
		List<String> lst = objectReferenceBusinessImpl.getGoodsInfoFromConstructionCode(code);
		if (lst == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(lst).build();
		}
	}

	@Override
	public Response getGoodsInfoFromDepartmentCode(String code) {
		// TODO Auto-generated method stub
		List<String> lst = objectReferenceBusinessImpl.getGoodsInfoFromDepartmentCode(code);
		if (lst == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(lst).build();
		}
	}

	@Override
	public Response getGoodsInfoFromLoanCode(String code) {
		// TODO Auto-generated method stub
		List<String> lst = objectReferenceBusinessImpl.getGoodsInfoFromLoanCode(code);
		if (lst == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(lst).build();
		}
	}
}
