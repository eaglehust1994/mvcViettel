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
import com.viettel.wms.business.ReasonBusinessImpl;
//import com.viettel.wms.business.UserRoleBusinessImpl;
import com.viettel.Common.CommonBussiness.*;
import com.viettel.wms.dto.ReasonDTO;

/**
 *
 * @author HungLQ9
 */
public class ReasonRsServiceImpl implements ReasonRsService {

	// protected final Logger log = Logger.getLogger(UserRsService.class);
	@Context
	HttpServletRequest request;
	@Autowired
	private UserRoleBusinessImpl userRoleBusinessImpl;
    @Autowired
    ReasonBusinessImpl reasonBusinessImpl;

    @Override
    public Response getReason() {
        List<ReasonDTO> ls = reasonBusinessImpl.getAll();
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
    public Response getReasonById(Long id) {
        ReasonDTO obj = (ReasonDTO) reasonBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateReason(ReasonDTO obj) {
    	KttsUserSession objUser = userRoleBusinessImpl.getUserSession(request);
    	try {
    		obj.setUpdatedBy(objUser.getVpsUserInfo().getSysUserId());
    		obj.setUpdatedDate(new Date());
    		Long id=reasonBusinessImpl.updateReason(obj, objUser);
    		if(id==0l){
    			return Response.status(Response.Status.BAD_REQUEST).build();
    		} else {
    			return Response.ok(Response.Status.CREATED).build();
    		}
		} catch (Exception e) {
			return Response.ok().entity(Collections.singletonMap("error",e.getMessage())).build();
		}
		
	}

    @Override
    public Response addReason(ReasonDTO obj) throws Exception {
    	KttsUserSession objUser = userRoleBusinessImpl.getUserSession(request);
    	
    	try {
    		obj.setCreatedBy(objUser.getVpsUserInfo().getSysUserId());
        	obj.setCreatedDate(new Date());
    		Long id=reasonBusinessImpl.createReason(obj);
    		
    		if(id==0l){
    			return Response.status(Response.Status.BAD_REQUEST).build();
    		} else {
    			return Response.ok(Response.Status.CREATED).build();
    		}
		} catch (Exception e) {
			return Response.ok().entity(Collections.singletonMap("error",e.getMessage())).build();
		}
    	
	}

    /*@Override
    public Response deleteReason(Long id) {
    	KttsUserSession objUser = userRoleBusinessImpl.getUserSession(request);
        ReasonDTO obj = (ReasonDTO) reasonBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
        	try {
        		boolean check= reasonBusinessImpl.deleteReason(obj, objUser);
        		if(!check){
            		return Response.status(Response.Status.BAD_REQUEST).build();
            	}else{
            		 return Response.ok(Response.Status.NO_CONTENT).build();
            	}
			} catch (Exception e) {
				// TODO: handle exception
			}
        	
        }
    }*/

	@Override
	public Response doSearch(ReasonDTO obj) {
		DataListDTO data= reasonBusinessImpl.doSearch(obj);
		return Response.ok(data).build();
	}

	@Override
	public Response remove(ReasonDTO obj) {
		KttsUserSession objUser = userRoleBusinessImpl.getUserSession(request);
    	try {
    		obj.setUpdatedBy(objUser.getVpsUserInfo().getSysUserId());
        	obj.setUpdatedDate(new Date());
    		obj.setStatus("0");
    		Long id= reasonBusinessImpl.deleteReason(obj, objUser);
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
	public Response getForComboBox(ReasonDTO obj) {
		return Response.ok(reasonBusinessImpl.getForComboBox(obj)).build();
	}
}
