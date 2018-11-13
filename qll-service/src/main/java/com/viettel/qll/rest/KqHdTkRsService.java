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

/**
 * @author hailh10
 */
 
public interface KqHdTkRsService {

	@POST
	@Path("/doSearch")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response doSearch(KqHdTkDTO obj) throws Exception;
	
	@POST
	@Path("/insertHd")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response insertHd(KqHdTkDTO obj) throws Exception;
	
	@POST
	@Path("/updateHdTd")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateHdTd(KqHdTkDTO obj) throws Exception;
	
	@POST
	@Path("/updateHdPopSubmit")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateHdPopSubmit(KqHdTkDTO obj) throws Exception;
	
//	@POST
//	@Path("/exportTHTheoHD")
//	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	public Response exportTHTheoHD(KqHdTkDTO obj,@Context HttpServletRequest request) throws Exception;
	
	
	@POST
	@Path("/exportExcelGrid")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response exportExcelGrid(KqHdTkDTO obj,@Context HttpServletRequest request) throws Exception;

	
	@POST
	@Path("/getForAutoCompleteHD")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getForAutoCompleteHD(KqHdTkDTO obj) ;
	
	@POST
	@Path("/xuatBCLuong")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response xuatBCLuong(KqHdTkDTO obj,@Context HttpServletRequest request) throws Exception;

	@POST
	@Path("/xuatBCTH")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response xuatBCTH(KqHdTkDTO obj,@Context HttpServletRequest request) throws Exception;

	@POST
	@Path("/getForAutoCompleteKHTC")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getForAutoCompleteKHTC(KqHdTkDTO obj) ;
	
	@POST
	@Path("/updatePath")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updatePath(KqHdTkDTO obj) throws Exception;

	@POST
	@Path("/importFile")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response importFile(Attachment attachments,@Context HttpServletRequest request) throws Exception;
	
	@POST
	@Path("/importFileTD")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response importFileTD(Attachment attachments,@Context HttpServletRequest request) throws Exception;

	@POST
	@Path("/exportAllHD")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response exportAllHD(KqHdTkDTO obj,@Context HttpServletRequest request) throws Exception;
	
	
	@POST
	@Path("/deleteObj")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteObj(KqHdTkDTO obj) throws Exception;
	
	@POST
	@Path("/deleteListObj")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteListObj(KqHdTkDTO obj) throws Exception;

}
