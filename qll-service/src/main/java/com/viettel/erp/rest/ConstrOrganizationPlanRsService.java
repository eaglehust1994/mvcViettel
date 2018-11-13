/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import com.viettel.erp.dto.ConstrOrganizationPlanDTO;
import com.viettel.erp.dto.ConstrWorkLogsDTO;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author HungLQ9
 */
public interface ConstrOrganizationPlanRsService {

	// ChuongNV
	
	   
    @GET
    @Path("/constrOrganizationPlan/folder")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getFolder();
	
	@POST
	@Path("/constrOrganizationPlan/getAllConstrOrganizationPlan")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getAllConstrOrganizationPlan(ConstrOrganizationPlanDTO dto) throws Exception;
	
	@POST
	@Path("/constrOrganizationPlan/getAllConstrOrganizationPlanChild")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getAllConstrOrganizationPlanChild(ConstrOrganizationPlanDTO dto) throws Exception;

	@POST
	@Path("/constrOrganizationPlan/deleteConstrOrganizationPlan/")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteConstrOrganizationPlan(List<String> listConstrOrgPlanId);
	
	@POST
	@Path("/constrOrganizationPlan/getEmployee/")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getEmployee(String contructID);
	
	@POST
	@Path("/constrOrganizationPlan/addConstrOrganizationPlan")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response addConstrOrganizationPlan(ConstrOrganizationPlanDTO obj)throws Exception;

	@POST
	@Path("/constrOrganizationPlan/updateConstrOrganizationPlan")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateConstrOrganizationPlan(ConstrOrganizationPlanDTO obj);
	
	@POST
	@Path("/constrOrganizationPlan/exportFileListWorkOrganizationPlan")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response exportFileListWorkOrganizationPlan(ConstrOrganizationPlanDTO obj);
	
	@POST
    @Path("/constrOrganizationPlan/downloadFile/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response downloadFileZip(List<String> path, @Context HttpServletRequest request);
	
	@POST
    @Path("/constrOrganizationPlan/downloadFileParentChild/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response downloadFileZipParentChild(Long path, @Context HttpServletRequest request);
	
	// End ChuongNV
	
	@GET
	@Path("/constrOrganizationPlan")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getConstrOrganizationPlan();

	@GET
	@Path("/constrOrganizationPlan/{id}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getConstrOrganizationPlanById(@PathParam("id") Long id);


	@DELETE
	@Path("/constrOrganizationPlan/{id}/")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteConstrOrganizationPlan(@PathParam("id") Long id);
	
	@GET
    @Path("/constrOrganizationPlan/attachTypeKey")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAttachTypeKey();
	
	@GET
    @Path("/constrOrganizationPlan/attachTypeValue")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAttachTypeValue();
}
