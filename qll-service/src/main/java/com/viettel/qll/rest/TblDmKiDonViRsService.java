package com.viettel.qll.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;

import javax.ws.rs.core.Context;

import com.viettel.qll.dto.TblDmKiDonViDTO;
import com.viettel.qll.dto.TblDutCapLoiCqDTO;

/**
 * @author hailh10
 */

public interface TblDmKiDonViRsService {

	@GET
	@Path("/getAll")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getAllKiDonVi() throws Exception;

	@POST
	@Path("/doSearchKiDonVi")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response doSearchKiDonVi(TblDmKiDonViDTO obj) throws Exception;

	@POST
	@Path("/importKiDonVi")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response importKiDonVi(Attachment attachments, @Context HttpServletRequest request) throws Exception;

	@POST
	@Path("/exportExcelGrid")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response exportExcelGrid(TblDmKiDonViDTO obj) throws Exception;
}
