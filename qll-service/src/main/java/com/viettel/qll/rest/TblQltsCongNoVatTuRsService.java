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

import com.viettel.qll.dto.TblQltsCongNoVatTuDTO;

/**
 * @author hailh10
 */

public interface TblQltsCongNoVatTuRsService {

	@POST
	@Path("/doSearch")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response doSearchCongNo(TblQltsCongNoVatTuDTO obj);

	@POST
	@Path("/importCongNo")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response importCongNo(Attachment attachments, @Context HttpServletRequest request) throws Exception;
	
	@POST
	@Path("/importCongNo1")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response importCongNo1(Attachment attachments, @Context HttpServletRequest request) throws Exception;

	@POST
	@Path("/exportExcelGrid")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response exportExcelGrid(TblQltsCongNoVatTuDTO obj,@Context HttpServletRequest request) throws Exception;
	
	@POST
	@Path("/exportExcelGrid1")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response exportExcelGrid1(TblQltsCongNoVatTuDTO obj,@Context HttpServletRequest request) throws Exception;
	
	@POST
	@Path("/exportExcelGrid2")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response exportExcelGrid2(TblQltsCongNoVatTuDTO obj,@Context HttpServletRequest request) throws Exception;

	@POST
	@Path("/exportExcelTongHop")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response exportExcelTongHop(TblQltsCongNoVatTuDTO obj,@Context HttpServletRequest request) throws Exception;
	
	@POST
	@Path("/updateCongNo")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateCongNo(TblQltsCongNoVatTuDTO obj,@Context HttpServletRequest request) throws Exception;

}
