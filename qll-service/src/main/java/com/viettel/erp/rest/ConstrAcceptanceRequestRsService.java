/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import com.viettel.erp.dto.AMaterialHandoverDTO;
import com.viettel.erp.dto.ConstrAcceptanceRequestDTO;
import com.viettel.erp.dto.approDTO;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author HungLQ9
 */
public interface ConstrAcceptanceRequestRsService {
	
	@POST
    @Path("/appro/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response appro(approDTO dto);
	
	@POST
    @Path("/deleteConstrAcceptanceReq/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteConstrAcceptanceReq(List<String> listCode);
	
	@POST
    @Path("/listConstrAcceptanceReq/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response listConstrAcceptanceReq(ConstrAcceptanceRequestDTO obj);
	
    @GET
    @Path("/constrAcceptanceRequest")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getConstrAcceptanceRequest();

    @GET
    @Path("/constrAcceptanceRequest/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getConstrAcceptanceRequestById(@PathParam("id") Long id);

    @POST
    @Path("/constrAcceptanceRequest/put/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateConstrAcceptanceRequest(ConstrAcceptanceRequestDTO obj);

    @POST
    @Path("/constrAcceptanceRequest/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addConstrAcceptanceRequest(ConstrAcceptanceRequestDTO obj);

    @DELETE
    @Path("/constrAcceptanceRequest/{id}/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteConstrAcceptanceRequest(@PathParam("id") Long id);
    
    
    
    
    @POST
    @Path("/constrAcceptanceRequest/remove")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response removeConstrAcceptanceRequest(ConstrAcceptanceRequestDTO obj) throws Exception;
   
    
}
