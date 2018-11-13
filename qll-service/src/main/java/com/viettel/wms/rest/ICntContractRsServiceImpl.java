/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.rest;

import com.viettel.wms.business.ICntContractBusinessImpl;
import com.viettel.wms.dto.ICntContractDTO;
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
public class ICntContractRsServiceImpl implements ICntContractRsService {

//    protected final Logger log = Logger.getLogger(UserRsService.class);
    @Autowired
    ICntContractBusinessImpl iCntContractBusinessImpl;

    @Override
    public Response getICntContract() {
        List<ICntContractDTO> ls = iCntContractBusinessImpl.getAll();
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
    public Response getICntContractById(Long id) {
        ICntContractDTO obj = (ICntContractDTO) iCntContractBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateICntContract(ICntContractDTO obj) {
        Long id = iCntContractBusinessImpl.update(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addICntContract(ICntContractDTO obj) {
        Long id = iCntContractBusinessImpl.save(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(Response.Status.CREATED).build();
        }
    }

    @Override
    public Response deleteICntContract(Long id) {
        ICntContractDTO obj = (ICntContractDTO) iCntContractBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            iCntContractBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }

    @Override
	public Response getForAutoComplete(ICntContractDTO obj) {
		return Response.ok(iCntContractBusinessImpl.getForAutoComplete(obj)).build();
	}
	
	@Override
	public Response doSearch(ICntContractDTO obj) {
		DataListDTO data= iCntContractBusinessImpl.doSearch(obj);
		return Response.ok(data).build();
	}
}
