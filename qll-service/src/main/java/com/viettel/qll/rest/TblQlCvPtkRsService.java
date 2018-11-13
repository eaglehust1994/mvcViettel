package com.viettel.qll.rest;



import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;

import com.viettel.qll.dto.KqHdTkDTO;
import com.viettel.qll.dto.TblKhQlvcDTO;
import com.viettel.qll.dto.TblQlCvPtkDTO;

/**
 * @author hailh10
 */
 
public interface TblQlCvPtkRsService {

	


	
	@POST
	@Path("/doSearch")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response doSearch(TblQlCvPtkDTO obj) throws Exception;
	
	@POST
	@Path("/listTasks")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response listTasks(TblQlCvPtkDTO obj) throws Exception;
	
	@POST
	@Path("/deleteObj")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteObj(TblQlCvPtkDTO obj) throws Exception;
	
	@POST
	@Path("/deleteListObj")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteListObj(TblQlCvPtkDTO obj) throws Exception;
	
	@POST
	@Path("/saveAddCv")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response saveAddCv(TblQlCvPtkDTO obj) ;
	
	@POST
	@Path("/updateStatus")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateStatus(TblQlCvPtkDTO obj) throws Exception;
	
	@POST
	@Path("/updatePath")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updatePath(TblQlCvPtkDTO obj) throws Exception;
	
	@POST
	@Path("/updateCV1")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateCV1(TblQlCvPtkDTO obj) throws Exception;
	
	@POST
	@Path("/updateCV2")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateCV2(TblQlCvPtkDTO obj) throws Exception;
	
	@POST
	@Path("/updateCV3")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateCV3(TblQlCvPtkDTO obj) throws Exception;
	
	@POST
	@Path("/updateCV4")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateCV4(TblQlCvPtkDTO obj) throws Exception;
	
	@POST
	@Path("/updateCV5")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateCV5(TblQlCvPtkDTO obj) throws Exception;
	
	@POST
	@Path("/updateCvPtk")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateCvPtk(TblQlCvPtkDTO obj) throws Exception;
	@POST
	@Path("/getForAutoCompleteSHD")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getForAutoCompleteSHD(TblQlCvPtkDTO obj) ;
	
	@POST
	@Path("/importFile")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response importFile(Attachment attachments,@Context HttpServletRequest request) throws Exception;
	
	@POST
	@Path("/importFileCV")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response importFileCV(Attachment attachments,@Context HttpServletRequest request) throws Exception;
	
	@POST
	@Path("/exportAllCV")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response exportAllCV(TblQlCvPtkDTO obj,@Context HttpServletRequest request) throws Exception;
	
	
	@POST
	@Path("/reportForQTK")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response reportForQTK(TblQlCvPtkDTO obj,@Context HttpServletRequest request) throws Exception;
	
	@POST
	@Path("/reportTotal")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response reportTotal(TblQlCvPtkDTO obj,@Context HttpServletRequest request) throws Exception;
	
	@POST
    @Path("/getChart")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getChart(TblQlCvPtkDTO obj) throws Exception;
	
}
