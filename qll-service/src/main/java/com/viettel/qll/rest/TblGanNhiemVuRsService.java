package com.viettel.qll.rest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import com.viettel.qll.dto.TblGanNhiemVuDTO;
import com.viettel.qll.dto.TblQlCvPtkDTO;


/**
 * @author hailh10
 */
 
public interface TblGanNhiemVuRsService {


	@POST
	@Path("/doSearch")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response doSearch(TblGanNhiemVuDTO obj) throws Exception;
	
	@POST
	@Path("/DetailNV")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response DetailNV(TblGanNhiemVuDTO obj,@Context HttpServletRequest request) throws Exception;
	
	@POST
	@Path("/getDetailInfo")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public  Response getDetailInfo(TblGanNhiemVuDTO obj,@Context HttpServletRequest request) throws Exception;
	
	@POST
	@Path("/deleteListObj")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteListObj(TblGanNhiemVuDTO obj) throws Exception;
	
	@POST
	@Path("/deleteObj")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteObj(TblGanNhiemVuDTO obj) throws Exception;
	
	@POST
	@Path("/saveGanNv")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response saveGanNv(TblGanNhiemVuDTO obj) ;
	
	@POST
	@Path("/saveAssignListTask")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response saveAssignListTask(TblGanNhiemVuDTO obj) ;
	
	@POST
	@Path("/getEmpByWorkId")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getEmpByWorkId(TblGanNhiemVuDTO obj) throws Exception;
	
	@POST
	@Path("/updateGNV")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateGNV(TblGanNhiemVuDTO obj) throws Exception;

	@POST
	@Path("/getForAutoCompleteTenCv")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getForAutoCompleteTenCv(TblGanNhiemVuDTO obj) ;
	
	@POST
	@Path("/getAutoMaViTriForShd")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getAutoMaViTriForShd(TblQlCvPtkDTO obj) ;

	
	
	
}
