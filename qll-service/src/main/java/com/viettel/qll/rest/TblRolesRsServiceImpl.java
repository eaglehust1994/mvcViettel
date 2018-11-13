package com.viettel.qll.rest;

import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.viettel.qll.business.TblRolesBusinessImpl;
import com.viettel.qll.dto.TblRolesDTO;
import com.viettel.qll.dto.TblUsersDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 * @author hailh10
 */
 
public class TblRolesRsServiceImpl implements TblRolesRsService {

	protected final Logger log = Logger.getLogger(TblRolesRsService.class);
	@Autowired
	TblRolesBusinessImpl tblRolesBusinessImpl;
	
	@Override
	public Response selectRoles() throws Exception {
		DataListDTO data = tblRolesBusinessImpl.selectRoles();
		if (data == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(data).build();
		}
	}
	
}
