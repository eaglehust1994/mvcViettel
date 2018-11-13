/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import com.viettel.erp.business.ConstrWorkCompConfListBusinessImpl;
import com.viettel.erp.dto.ConstrWorkCompConfListDTO;
import com.viettel.erp.dto.ConstrWorkCompConfListLessDTO;
import com.viettel.service.base.dto.DataListDTO;
import java.util.List;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author HungLQ9
 */
public class ConstrWorkCompConfListRsServiceImpl implements ConstrWorkCompConfListRsService {

	protected final Logger log = Logger.getLogger(ConstrWorkCompConfListRsServiceImpl.class);
    @Autowired
    ConstrWorkCompConfListBusinessImpl constrWorkCompConfListBusinessImpl;

    @Override
    public Response getConstrWorkCompConfList() {
        List<ConstrWorkCompConfListDTO> ls = constrWorkCompConfListBusinessImpl.getAll();
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
    public Response getConstrWorkCompConfListById(Long id) {
        ConstrWorkCompConfListDTO obj = (ConstrWorkCompConfListDTO) constrWorkCompConfListBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateConstrWorkCompConfList(ConstrWorkCompConfListDTO obj) {
        Long id = constrWorkCompConfListBusinessImpl.update(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addConstrWorkCompConfList(ConstrWorkCompConfListDTO obj) {
        Long id = constrWorkCompConfListBusinessImpl.save(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(Response.Status.CREATED).build();
        }
    }

    @Override
    public Response deleteConstrWorkCompConfList(Long id) {
        ConstrWorkCompConfListDTO obj = (ConstrWorkCompConfListDTO) constrWorkCompConfListBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            constrWorkCompConfListBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }

	@Override
	public Response getListConstrWorkByConstrId(Long id) {
        List<ConstrWorkCompConfListLessDTO> ls = constrWorkCompConfListBusinessImpl.getListConstrWorkByConstrId(id);
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
	public Response getListConstrWorkExistByConstrId(List<Long> id) {
		 List<ConstrWorkCompConfListLessDTO> ls = constrWorkCompConfListBusinessImpl.getListConstrWorkExistByConstrId(id);
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

}
