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

import com.viettel.ims.dto.CntContractDTO;
import com.viettel.qll.dto.TblDanhMucDTO;


/**
 * @author hailh10
 */
 
public interface TblDanhMucRsService {
	@GET
	@Path("/doSearch")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response doSearch(CntContractDTO obj);
	
	
	@POST
	@Path("/doSearchDepartment")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response doSearchDepartment(TblDanhMucDTO obj);
	
	@POST
	@Path("/getDeptForAutocomplete")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getDeptForAutocomplete(TblDanhMucDTO obj);
	
	@POST
	@Path("/getDeptForAutocomplete1")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getDeptForAutocomplete1(TblDanhMucDTO obj);
	
	@POST
	@Path("/getDeptForAutocomplete2")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getDeptForAutocomplete2(TblDanhMucDTO obj);
	
	@POST
	@Path("/getDeptForAutocomplete3")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getDeptForAutocomplete3(TblDanhMucDTO obj);
	
	@POST
	@Path("/getForAutoCompleteDeptFor62DV")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getForAutoCompleteDeptFor62DV(TblDanhMucDTO obj);

	@POST
	@Path("/getDeptForAutocomplete1Popup")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getDeptForAutocomplete1Popup(TblDanhMucDTO obj);
	
	
	
}
