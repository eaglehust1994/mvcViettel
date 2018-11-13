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
import com.viettel.qll.dto.TblKHuyenKhoDTO;

/**
 * @author hailh10
 */
 
public interface TblKHuyenKhoRsService {
	@POST
	@Path("/doSearchKHuyenKho")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response doSearchKHuyenKho(TblKHuyenKhoDTO obj) throws Exception;

	@POST
	@Path("/importkHuyenKho")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response importkHuyenKho(Attachment attachments, @Context HttpServletRequest request) throws Exception;

	@POST
	@Path("/exportExcelGrid")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response exportExcelGrid(TblKHuyenKhoDTO obj) throws Exception;
	
}
