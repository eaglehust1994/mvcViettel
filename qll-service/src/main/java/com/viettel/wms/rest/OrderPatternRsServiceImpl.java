/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.rest;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.viettel.wms.business.OrderPatternBusinessImpl;
//import com.viettel.wms.business.UserRoleBusinessImpl;
import com.viettel.Common.CommonBussiness.*;
import com.viettel.wms.dto.OrderPatternDTO;
import com.viettel.ktts2.dto.KttsUserSession;
import com.viettel.service.base.dto.DataListDTO;
import com.viettel.asset.filter.session.UserSession;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author TuanNB
 */
public class OrderPatternRsServiceImpl implements OrderPatternRsService {

	// protected final Logger log = Logger.getLogger(UserRsService.class);
    @Autowired
    OrderPatternBusinessImpl orderPatternBusinessImpl;

	@Context HttpServletRequest request;
	
	@Autowired
	private UserRoleBusinessImpl userRoleBusinessImpl;
	
    @Override
    public Response getOrderPattern() {
        List<OrderPatternDTO> ls = orderPatternBusinessImpl.getAll();
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
    
//	Tìm kiếm dữ liệu
    @Override
	public Response doSearch(OrderPatternDTO obj) {
		// TODO Auto-generated method stub
		DataListDTO data= orderPatternBusinessImpl.doSearch(obj);
        if (data == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
        	return Response.ok(data).build();
        }
	}
    
//	Xem chi tiết
    @Override
    public Response viewDetail(OrderPatternDTO obj){
    	
		DataListDTO data= orderPatternBusinessImpl.viewDetail(obj);
		return Response.ok(data).build();
    }

//  Xoá dữ liệu
    @Override
	public Response remove(OrderPatternDTO obj) throws Exception {
    	KttsUserSession objUser=userRoleBusinessImpl.getUserSession(request);
    	try{
    	Long id= orderPatternBusinessImpl.remove(obj,objUser);
    	/*
			obj.setCreatedUserId(objUser.getVpsUserInfo().getSysUserId());
			obj.setCreatedUserName(objUser.getVpsUserInfo().getFullName());
			obj.setCreatedDate(new Date());*/
			if(id==0l){
				return Response.status(Response.Status.BAD_REQUEST).build();
			} else {
				return Response.ok(id).build();
			}
    	}catch (IllegalArgumentException e) {
			return Response.ok().entity(Collections.singletonMap("error",e.getMessage())).build();
		}	
	}

//  Thêm mới dữ liệu
    @Override
	public Response add(OrderPatternDTO obj) throws Exception{
    	KttsUserSession objUser=userRoleBusinessImpl.getUserSession(request);
    	try {
			obj.setCreatedUserId(objUser.getVpsUserInfo().getSysUserId());
			obj.setCreatedUserName(objUser.getVpsUserInfo().getFullName());
			obj.setCreatedDate(new Date());
			Long id = orderPatternBusinessImpl.savePattern(obj);
	
			if (id == 0l) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			} else {
				return Response.ok(id).build();
			}
    	}catch (IllegalArgumentException e) {
			return Response.ok().entity(Collections.singletonMap("error",e.getMessage())).build();
		}	
	}
    
//  Sửa dữ liệu
	@Override
	public Response update(OrderPatternDTO obj) throws Exception {
		KttsUserSession objUser=userRoleBusinessImpl.getUserSession(request);
		try {
			obj.setCreatedDate(new Date());
			
			Long id=orderPatternBusinessImpl.updatePattern(obj,objUser);
			
			obj.setCreatedUserId(objUser.getVpsUserInfo().getSysUserId());
			obj.setCreatedUserName(objUser.getVpsUserInfo().getFullName());					
			
			if (id == 0l) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			} else {
				return Response.ok(id).build();
			}
		} catch (IllegalArgumentException e) {
			return Response.ok().entity(Collections.singletonMap("error",e.getMessage())).build();
		}	
	}
}
