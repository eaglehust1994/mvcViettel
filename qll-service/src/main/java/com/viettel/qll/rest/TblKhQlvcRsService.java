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


/**
 * @author hailh10
 */
 
public interface TblKhQlvcRsService {

	
	
	
	
	@POST
	@Path("/doSearch")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response doSearch(TblKhQlvcDTO obj) throws Exception;
	
	@POST
	@Path("/deleteObj")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteObj(TblKhQlvcDTO obj) throws Exception;
	
	@POST
	@Path("/deleteListObj")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteListObj(TblKhQlvcDTO obj) throws Exception;
	
	@POST
	@Path("/updateKhNv")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateKhNv(TblKhQlvcDTO obj) throws Exception;
	
	@POST
	@Path("/importFile")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response importFile(Attachment attachments,@Context HttpServletRequest request) throws Exception;
	
	@POST
	@Path("/getForAutoCompleteMonth")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getForAutoCompleteMonth(TblKhQlvcDTO obj) ;
	
	@POST
	@Path("/getForAutoCompleteYear")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getForAutoCompleteYear(TblKhQlvcDTO obj) ;
	
	@POST
	@Path("/getForAutoCompleteMaNv")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getForAutoCompleteMaNv(TblKhQlvcDTO obj) ;
	
	@POST
	@Path("/getForAutoCompleteMaDv")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getForAutoCompleteMaDv(TblKhQlvcDTO obj) ;

	@POST
    @Path("/getChartMonth")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getChartMonth(TblKhQlvcDTO obj) throws Exception;
	
//	@POST
//    @Path("/getChartDeptYear")
//    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	public Response getChartDeptYear(TblKhQlvcDTO obj) throws Exception;
	
	@POST
    @Path("/getChartDept")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getChartDept(TblKhQlvcDTO obj) throws Exception;
	
//	@POST
//    @Path("/getChartEmpYear")
//    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	public Response getChartEmpYear(TblKhQlvcDTO obj) throws Exception;
	
//	@POST
//    @Path("/getChartEmpMonth")
//    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	public Response getChartEmpMonth(TblKhQlvcDTO obj) throws Exception;
	

}
