package com.viettel.qll.rest;



import javax.ws.rs.Consumes;

import javax.ws.rs.POST;

import javax.ws.rs.Path;

import javax.ws.rs.Produces;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.viettel.qll.dto.SysGroupDTO;
import com.viettel.qll.dto.TblDanhMucDTO;


/**
 * @author hailh10
 */
 
public interface SysGroupRsService {


	
	@POST
	@Path("/getListDepartment")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON + ";charset=utf-8" , MediaType.APPLICATION_XML })
	public Response getListDepartment();
	
	@POST
	@Path("/getAutoDepartment")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON + ";charset=utf-8" , MediaType.APPLICATION_XML })
	public Response getAutoDepartment(SysGroupDTO obj);
	
	
}
