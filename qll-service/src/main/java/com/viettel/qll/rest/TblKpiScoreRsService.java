package com.viettel.qll.rest;



import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;

import javax.ws.rs.POST;

import javax.ws.rs.Path;

import javax.ws.rs.Produces;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import org.apache.cxf.jaxrs.ext.multipart.Attachment;

import javax.ws.rs.core.Context;


import com.viettel.qll.dto.TblKpiScoreDTO;


/**
 * @author hailh10
 */
 
public interface TblKpiScoreRsService {

	@POST
	@Path("/delete")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON + ";charset=utf-8" , MediaType.APPLICATION_XML })
	public Response deleteObj(TblKpiScoreDTO obj) throws Exception;

	@POST
	@Path("/update")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON + ";charset=utf-8" , MediaType.APPLICATION_XML })
	public Response updateObj(TblKpiScoreDTO obj) throws Exception;
	
	@POST
	@Path("/insert")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON + ";charset=utf-8" , MediaType.APPLICATION_XML })
	public Response insertObj(TblKpiScoreDTO obj) throws Exception;
	
	@POST
	@Path("/ListAllDepartment")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON + ";charset=utf-8" , MediaType.APPLICATION_XML })
	public Response getListAllDepartment(TblKpiScoreDTO obj) throws Exception;
	
	@POST
	@Path("/InfoDetailByDepartment")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON + ";charset=utf-8" , MediaType.APPLICATION_XML })
	public Response getInfoDetailByDepartment(TblKpiScoreDTO obj) throws Exception;
	
	@POST
	@Path("/InfoDetailByUseridcreated")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON + ";charset=utf-8" , MediaType.APPLICATION_XML })
	public Response getInfoDetailByUseridcreated(TblKpiScoreDTO obj) throws Exception;

	
	@POST
	@Path("/NotifyByDepartment")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON + ";charset=utf-8" , MediaType.APPLICATION_XML })
	public Response getNotifyByDepartment(TblKpiScoreDTO obj) throws Exception;
	
	@POST
	@Path("/InfoDetailByDoSearch")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON + ";charset=utf-8" , MediaType.APPLICATION_XML })
	public Response getInfoDetailByDoSearch(TblKpiScoreDTO obj) throws Exception;

	@POST
	@Path("/getAutoDepartmentKPI")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getAutoDepartmentKPI(TblKpiScoreDTO obj) ;
	
	@POST
	@Path("/getAutoDepartmentCreatedKPI")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getAutoDepartmentCreatedKPI(TblKpiScoreDTO obj) ;
	
	@POST
	@Path("/decidedImportFile")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response decidedImportFile(Attachment attachments,@Context HttpServletRequest request) throws Exception;
	
//	
	@POST
	@Path("/decidedExportFile")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response decidedExportFile(TblKpiScoreDTO obj,@Context HttpServletRequest request) throws Exception;
	
	@POST
	@Path("/generalExportFile")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response generalExportFile(TblKpiScoreDTO obj,@Context HttpServletRequest request) throws Exception;
	
}
