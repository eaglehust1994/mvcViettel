package com.viettel.qll.rest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.viettel.qll.dto.TblUsersDTO;

/**
 * @author hailh10
 */

public interface TblUsersRsService {
//	@POST
//	@Path("/getUserRoles")
//	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	public Response getUserRoles(String code) throws Exception;

	@POST
	@Path("/checkRole")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response checkRole(String code,@Context HttpServletRequest request) throws Exception;
	
	@POST
	@Path("/getForAutoCompleteNhanVien")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getForAutoCompleteNhanVien(TblUsersDTO obj) throws Exception;
	
	@POST
	@Path("/getForAutoCompleteNhanVienPtk")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getForAutoCompleteNhanVienPtk(TblUsersDTO obj) throws Exception;
	
	@POST
	@Path("/doSearch")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response doSearch(TblUsersDTO obj) throws Exception;
	

	@POST
	@Path("/getRoles")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getRoles(TblUsersDTO obj) throws Exception;

}
