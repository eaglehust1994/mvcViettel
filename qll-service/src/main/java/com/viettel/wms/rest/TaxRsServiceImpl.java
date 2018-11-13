/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.rest;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.viettel.ktts2.dto.KttsUserSession;
import com.viettel.service.base.dto.DataListDTO;
import com.viettel.wms.business.TaxBusinessImpl;
//import com.viettel.wms.business.UserRoleBusinessImpl;
import com.viettel.Common.CommonBussiness.*;
import com.viettel.wms.dto.TaxDTO;

/**
 *
 * @author HungLQ9
 */
public class TaxRsServiceImpl implements TaxRsService {

	//   protected final Logger log = Logger.getLogger(UserRsService.class);
	@Context
	HttpServletRequest request;
	@Autowired
	private UserRoleBusinessImpl userRoleBusinessImpl;
    @Autowired
    TaxBusinessImpl taxBusinessImpl;

    @Override
    public Response getTax() {
        List<TaxDTO> ls = taxBusinessImpl.getAll();
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
	public Response doSearch(TaxDTO obj) {
		DataListDTO data= taxBusinessImpl.doSearch(obj);
		return Response.ok(data).build();
	}
    
    @Override
   	public Response doSearchBytax(TaxDTO obj) {
   		DataListDTO data= taxBusinessImpl.doSearchBytax(obj);
   		return Response.ok(data).build();
   	}
    
    
    @Override
	public Response remove(TaxDTO obj) {
    	KttsUserSession objUser = userRoleBusinessImpl.getUserSession(request);
		try {
			obj.setStatus("0");
			obj.setUpdatedBy(objUser.getVpsUserInfo().getSysUserId());
			obj.setUpdatedDate(new Date());
			Long id= taxBusinessImpl.deleteStock(obj, objUser);
			if(id==0l){
				return Response.status(Response.Status.BAD_REQUEST).build();
			} else {
				return Response.ok().build();
			}
		} catch (Exception e) {
			return Response.ok().entity(Collections.singletonMap("error",e.getMessage())).build();
		}
    	
	}

	@Override
	public Response add(TaxDTO obj) throws Exception{
		KttsUserSession objUser = userRoleBusinessImpl.getUserSession(request);
		try {
			obj.setCreatedBy(objUser.getVpsUserInfo().getSysUserId());
			obj.setCreatedDate(new Date());
			Long id=taxBusinessImpl.createTax(obj);
			
			if(id==0l){
				return Response.status(Response.Status.BAD_REQUEST).build();
			} else {
				return Response.ok(id).build();
			}
		} catch (Exception e) {
			return Response.ok().entity(Collections.singletonMap("error",e.getMessage())).build();
		}
		
	}

	@Override
	public Response update(TaxDTO obj) throws Exception {
		KttsUserSession objUser = userRoleBusinessImpl.getUserSession(request);
		try {
			obj.setUpdatedBy(objUser.getVpsUserInfo().getSysUserId());
			obj.setUpdatedDate(new Date());
			Long id=taxBusinessImpl.updateTax(obj, objUser);
			if(id==0l){
				return Response.status(Response.Status.BAD_REQUEST).build();
			} else {
				return Response.ok(id).build();
			}
		} catch (Exception e) {
			return Response.ok().entity(Collections.singletonMap("error",e.getMessage())).build();
		}
		
	}


}
