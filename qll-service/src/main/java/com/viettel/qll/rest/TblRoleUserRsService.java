package com.viettel.qll.rest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.viettel.qll.dto.TblRoleUserDTO;

/**
 * @author hailh10
 */

public interface TblRoleUserRsService {

	@POST
	@Path("/insertRoles")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response insertRoles(TblRoleUserDTO lst,@Context HttpServletRequest request) throws Exception;
	
	@POST
	@Path("/insertRoles8")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response insertRoles8() throws Exception;


}
