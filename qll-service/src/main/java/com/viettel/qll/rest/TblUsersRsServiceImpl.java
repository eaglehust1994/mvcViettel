package com.viettel.qll.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.viettel.qll.business.TblUsersBusinessImpl;
import com.viettel.qll.dto.checkRole;
import com.viettel.qll.dto.TblQltsThucXuatTheoKyDTO;
import com.viettel.qll.dto.TblUsersDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 * @author hailh10
 */

public class TblUsersRsServiceImpl implements TblUsersRsService {

	protected final Logger log = Logger.getLogger(TblUsersRsService.class);
	@Autowired
	TblUsersBusinessImpl tblUsersBusinessImpl;
	@Context 
	HttpServletRequest request;

//	@Override
//	public Response getUserRoles(String code) throws Exception {
//		List<TblUsersDTO> data = tblUsersBusinessImpl.getUserRoles(code);
//		if (data == null) {
//			return Response.status(Response.Status.BAD_REQUEST).build();
//		} else {
//			return Response.ok(data).build();
//		}
//	}
	
	
	@Override
	public Response checkRole(String code,HttpServletRequest request) throws Exception {
		TblUsersDTO data = tblUsersBusinessImpl.checkRole(code);
		if (data == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			String address=(String) request.getSession().getAttribute("VTS-IPWAN");
			data.setIpAdrr(address);
			return Response.ok(data).build();
		}
	}
	
	@Override
	public Response doSearch(TblUsersDTO obj) throws Exception {
		DataListDTO data = tblUsersBusinessImpl.doSearch(obj,request);
        if (data == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
        	return Response.ok(data).build();
        }
	}
	
	@Override
	public Response getForAutoCompleteNhanVien(TblUsersDTO obj) throws Exception {
		List<TblUsersDTO> data = tblUsersBusinessImpl.getForAutoCompleteNhanVien(obj);
		if (data == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(data).build();
		}
	}
	@Override
	public Response getForAutoCompleteNhanVienPtk(TblUsersDTO obj) throws Exception {
		List<TblUsersDTO> data = tblUsersBusinessImpl.getForAutoCompleteNhanVienPtk(obj);
		if (data == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(data).build();
		}
	}
	@Override
	public Response getRoles(TblUsersDTO obj) throws Exception {
		List<TblUsersDTO> data = tblUsersBusinessImpl.getRoles(obj);
		if (data == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(data).build();
		}
	}
	
	
}
