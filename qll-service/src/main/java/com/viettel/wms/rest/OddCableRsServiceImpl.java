/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.rest;

import com.viettel.wms.business.OddCableBusinessImpl;
//import com.viettel.wms.dto.AppParamDTO;
import com.viettel.wms.dto.OddCableDTO;
import com.viettel.wms.dto.OrderPatternDTO;
import com.viettel.service.base.dto.DataListDTO;
import java.util.List;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author HungLQ9
 */
public class OddCableRsServiceImpl implements OddCableRsService {

 //   protected final Logger log = Logger.getLogger(UserRsService.class);
    @Autowired
    OddCableBusinessImpl oddCableBusinessImpl;

//	Tim kiem
	@Override
	public Response doSearch(OddCableDTO obj) {
		// TODO Auto-generated method stub
		DataListDTO data= oddCableBusinessImpl.doSearch(obj);
        if (data == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
        	return Response.ok(data).build();
        }
	}
	
//	Xoa
	@Override
	public Response remove(OddCableDTO obj) {
		obj.setStatus("0");
		Long id = oddCableBusinessImpl.update(obj);
		if (id == 0l) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok().build();
		}
	}
	
//	Them moi
	@Override
	public Response add(OddCableDTO obj) {
		boolean check = oddCableBusinessImpl.checkCode(obj.getGoodsCode(), obj.getGoodsName(),null);
		if (!check) {
			return Response.status(Response.Status.CONFLICT).build();
		}

		Long id = oddCableBusinessImpl.save(obj);

		if (id == 0l) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(id).build();
		}
	}
	
//	Cap nhat
	@Override
	public Response update(OddCableDTO obj) {
		boolean check = oddCableBusinessImpl.checkCode(obj.getGoodsCode(), obj.getGoodsName(), obj.getOddCableId());
		if (!check ) {
			return Response.status(Response.Status.CONFLICT).build();
		}
		Long id=oddCableBusinessImpl.update(obj);
		
		if(id==0l){
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(id).build();
		}
	}
}
