/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import com.viettel.erp.dto.ConstrWorkLogsDTO;
import com.viettel.erp.dto.approDTO;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author HungLQ9
 */
public interface ConstrWorkLogsRsService {

//	ChuongNV
	@POST
	@Path("/constrWorkLogs/appro/")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response appro(approDTO dto);
	
	@POST
	@Path("/constrWorkLogs/getAllConstrWorkLogs/")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getAllConstrWorkLogs(ConstrWorkLogsDTO dto);
	
	@POST
	@Path("/constrWorkLogs/getEstimatesWork/")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getEstimatesWork(String constructId);
	

	@POST
	@Path("/constrWorkLogs/addConstrWorkLogs")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response addConstrWorkLogs(ConstrWorkLogsDTO obj);
	
	@POST
	@Path("/constrWorkLogs/deleteConstrWorkLogs")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteConstrWorkLogs(List<String> listConstrWorkLogsId);
	

	@POST
	@Path("/constrWorkLogs/updateConstrWorkLogs")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateConstrWorkLogs(ConstrWorkLogsDTO obj);
	
	@POST
	@Path("/constrWorkLogs/exportFileConstrWorkLogs")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response exportFileConstrWorkLogs(ConstrWorkLogsDTO obj) throws Exception;
	
	@POST
	@Path("/constrWorkLogs/exportFileDoc")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response exportFileDoc(ConstrWorkLogsDTO obj);
	
//End ChuongNV
	
	@GET
	@Path("/constrWorkLogs")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getConstrWorkLogs();

	@GET
	@Path("/constrWorkLogs/{id}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getConstrWorkLogsById(@PathParam("id") Long id);


	@DELETE
	@Path("/constrWorkLogs/{id}/")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteConstrWorkLogs(@PathParam("id") Long id);
	
	@POST
	@Path("/constrWorkLogs/exportList")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response exportList(List<String> list);
	
	@POST
	@Path("/constrWorkLogs/exportListDoc")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response exportListDoc(List<String> list);
	
	@GET
    @Path("/constrWorkLogs/downloadInstruction")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response downloadInstruction() throws Exception;
	
	
}
