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

import com.viettel.qll.dto.TblDmKiDonViDTO;
import com.viettel.qll.dto.TblDonGiaTramDTO;

/**
 * @author hailh10
 */
 
public interface TblDonGiaTramRsService {

	@POST
	@Path("/doSearch")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response doSearchDLHaTang(TblDonGiaTramDTO obj);
	
	@POST
	@Path("/importDonGia0")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response importDonGia0(Attachment attachments,@Context HttpServletRequest request) throws Exception;
	
	@POST
	@Path("/exportExcelGrid")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response exportExcelGrid(TblDonGiaTramDTO obj) throws Exception;
	@POST
	@Path("/downloadImportTemplate")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response downloadImportTemplate() throws Exception;
	
}
