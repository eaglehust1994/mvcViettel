/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import com.viettel.erp.dto.ConstrWorkLogsLabelDTO;
import com.viettel.erp.dto.approDTO;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author HungLQ9
 */
public interface ConstrWorkLogsLabelRsService {

	@POST
	@Path("/constrWorkLogsLabel/appro")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response appro(approDTO dto);

	@POST
	@Path("/constrWorkLogsLabel/updateLabel")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateLabel(ConstrWorkLogsLabelDTO dto);

	@POST
	@Path("/constrWorkLogsLabel/creat")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response creat(ConstrWorkLogsLabelDTO dto);

	@POST
	@Path("/constrWorkLogsLabel/exportFilePdf")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response exportFilePdf(ConstrWorkLogsLabelDTO dto) throws Exception;

	@POST
	@Path("/constrWorkLogsLabel/exportFileDoc")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response exportFileDoc(ConstrWorkLogsLabelDTO dto);

	@POST
	@Path("/constrWorkLogsLabel/checkBia")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response checkBia(Long constructId);

	@POST
	@Path("/constrWorkLogsLabel/getAllBia")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getAllBia(Long constructId);

	@GET
	@Path("/constrWorkLogsLabel")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getConstrWorkLogsLabel();

	@GET
	@Path("/constrWorkLogsLabel/{id}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getConstrWorkLogsLabelById(@PathParam("id") Long id);

	@POST
	@Path("/constrWorkLogsLabel/put/")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateConstrWorkLogsLabel(ConstrWorkLogsLabelDTO obj);

	@POST
	@Path("/constrWorkLogsLabel/")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response addConstrWorkLogsLabel(ConstrWorkLogsLabelDTO obj);

	@DELETE
	@Path("/constrWorkLogsLabel/{id}/")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteConstrWorkLogsLabel(@PathParam("id") Long id);
	
	@POST
	@Path("/constrWorkLogsLabel/exportListDoc")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response exportListDoc(List<Long> data);
}
