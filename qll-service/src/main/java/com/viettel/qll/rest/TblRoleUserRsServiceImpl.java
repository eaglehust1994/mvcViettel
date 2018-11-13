package com.viettel.qll.rest;

import java.sql.Date;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.viettel.ktts2.dto.KttsUserSession;
import com.viettel.qll.business.TblRoleUserBusinessImpl;
import com.viettel.qll.dto.TblRoleUserDTO;
import com.viettel.wms.dto.OrderChangeGoodsDTO;

/**
 * @author hailh10
 */
 
public class TblRoleUserRsServiceImpl implements TblRoleUserRsService {

	protected final Logger log = Logger.getLogger(TblRoleUserRsService.class);
	@Autowired
	TblRoleUserBusinessImpl tblRoleUserBusinessImpl;
	
	@Override
	public Response insertRoles(TblRoleUserDTO lst, HttpServletRequest request) throws Exception {
		try {
			tblRoleUserBusinessImpl.insertRoles(lst,request);
			return Response.ok(Response.Status.CREATED).build();
		} catch (IllegalArgumentException e) {
			return Response.ok()
					.entity(Collections.singletonMap("error", e.getMessage()))
					.build();
		}

	}
	
	@Override
	public Response insertRoles8() throws Exception {
		try {
			tblRoleUserBusinessImpl.insertRoles8();
			return Response.ok(Response.Status.CREATED).build();
		} catch (IllegalArgumentException e) {
			return Response.ok()
					.entity(Collections.singletonMap("error", e.getMessage()))
					.build();
		}

	}
	
}
