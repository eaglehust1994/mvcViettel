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

import com.viettel.qll.dto.TblDlHaTangTramDTO;
import com.viettel.qll.dto.TblTypeAPxkDTO;

/**
 * @author hailh10
 */

public interface TblTypeAPxkRsService {
	@POST
	@Path("/doSearchPXK")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response doSearchPXK(TblTypeAPxkDTO obj);

	@POST
	@Path("/importTypePXK")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response importTypePXK(Attachment attachments,@Context HttpServletRequest request) throws Exception;

	
	@POST
	@Path("/exportExcelGrid")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response exportExcelGrid(TblTypeAPxkDTO obj,@Context HttpServletRequest request) throws Exception;
	
	@POST
	@Path("/exportTHDoiSoatVatTu")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response exportTHDoiSoatVatTu(TblTypeAPxkDTO obj,@Context HttpServletRequest request) throws Exception;

	@POST
	@Path("/checkMaHdxl")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response checkMaHdxl(TblTypeAPxkDTO obj) throws Exception;
	
	@POST
	@Path("/updatePathImg")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updatePathImg(TblTypeAPxkDTO obj) throws Exception;

	@POST
	@Path("/getDeptForAutocomplete")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getDeptForAutocomplete(TblTypeAPxkDTO obj) throws Exception;
	
	@POST
	@Path("/getForAutoCompleteHDXL")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getForAutoCompleteHDXL(TblTypeAPxkDTO obj) throws Exception;



}
