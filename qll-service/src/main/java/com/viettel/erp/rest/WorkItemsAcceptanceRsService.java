/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import com.viettel.erp.dto.WorkItemsAcceptanceDTO;
import com.viettel.erp.dto.approDTO;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author HungLQ9
 */
public interface WorkItemsAcceptanceRsService {

	//tungpv
	
	@POST
    @Path("/workItemsAcceptance/appro")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response appro(approDTO obj);
	
	@POST
    @Path("/workItemsAcceptance/search")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response doSearchWorkItemsAcceptance(WorkItemsAcceptanceDTO cretial);
	
	@POST
    @Path("/workItemsAcceptance/exportWord")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response exportFileWorkItemsAcceptance(WorkItemsAcceptanceDTO obj) throws Exception;
	
	@POST
    @Path("/workItemsAcceptance/exportWordList")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response exportListWorkItemsAcceptance(List<WorkItemsAcceptanceDTO> data);
	
	@POST
    @Path("/workItemsAcceptance/exportWordDoc")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response exportFileDocWorkItemsAcceptance(WorkItemsAcceptanceDTO obj);
	
	@POST
    @Path("/workItemsAcceptance/exportWordListDoc")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response exportListDocWorkItemsAcceptance(List<WorkItemsAcceptanceDTO> data);
	
	@POST
	@Path("/workItemsAcceptance/deleteWorkItemAcceptList/")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteWorkItemAcceptList(List<String> listEstimatesWorkItemId);
	
	@POST
	@Path("/workItemsAcceptance/addWorkItemAcceptList/")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response addWorkItemAcceptList(WorkItemsAcceptanceDTO criteria);
	
	@POST
	@Path("/workItemsAcceptance/updateIsActive")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateIsActive(List<Long> listId);
	//end tungpv
	
    @GET
    @Path("/workItemsAcceptance")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getWorkItemsAcceptance();

    @GET
    @Path("/workItemsAcceptance/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getWorkItemsAcceptanceById(@PathParam("id") Long id);

    @POST
    @Path("/workItemsAcceptance/put/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateWorkItemsAcceptance(WorkItemsAcceptanceDTO obj);

    @POST
    @Path("/workItemsAcceptance/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addWorkItemsAcceptance(WorkItemsAcceptanceDTO obj);

    @DELETE
    @Path("/workItemsAcceptance/{id}/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteWorkItemsAcceptance(@PathParam("id") Long id);
}
