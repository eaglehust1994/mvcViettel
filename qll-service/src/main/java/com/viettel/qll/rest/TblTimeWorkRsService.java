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

import com.viettel.qll.dto.KqHdTkDTO;
import com.viettel.qll.dto.TblTimeWorkDTO;

/**
 * @author hailh10
 */
 
public interface TblTimeWorkRsService {

	
	@POST
	@Path("/doSearch")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response doSearch(TblTimeWorkDTO obj) throws Exception;
	
	
//	@POST
//	@Path("/deleteObj")
//	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	public Response deleteObj(TblTimeWorkDTO obj) throws Exception;
//	
//	@POST
//	@Path("/deleteListObj")
//	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	public Response deleteListObj(TblTimeWorkDTO obj) throws Exception;
//	
	@POST
	@Path("/importFile")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response importFile(Attachment attachments,@Context HttpServletRequest request) throws Exception;
	
//	
	@POST
	@Path("/exportTimeLate")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response exportTimeLate(TblTimeWorkDTO obj,@Context HttpServletRequest request) throws Exception;
	


}
