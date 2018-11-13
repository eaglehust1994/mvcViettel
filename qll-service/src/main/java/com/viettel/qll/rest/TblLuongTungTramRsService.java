package com.viettel.qll.rest;

import java.util.List;

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
import javax.ws.rs.core.Context;

import com.viettel.qll.dto.TblDlDauVaoDayMayDTO;
import com.viettel.qll.dto.TblLuongNvTramDTO;
import com.viettel.qll.dto.TblLuongTungTramDTO;

/**
 * @author hailh10
 */
 
public interface TblLuongTungTramRsService {

	@POST
	@Path("/doSearch")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response doSearch(TblLuongTungTramDTO obj);
	@POST
	@Path("/exportExcelGrid")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response exportExcelGrid(TblLuongTungTramDTO obj) throws Exception;
	
	@Path("/tinhluong")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response tinhLuong(TblLuongTungTramDTO obj) throws Exception;
	
}
