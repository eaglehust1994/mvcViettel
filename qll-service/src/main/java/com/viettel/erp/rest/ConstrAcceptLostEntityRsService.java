/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import com.viettel.erp.dto.ConstrAcceptLostEntityDTO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author HungLQ9
 */
public interface ConstrAcceptLostEntityRsService {

    @GET
    @Path("/constrAcceptLostEntity")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getConstrAcceptLostEntity();

    @GET
    @Path("/constrAcceptLostEntity/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getConstrAcceptLostEntityById(@PathParam("id") Long id);

    @POST
    @Path("/constrAcceptLostEntity/put/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateConstrAcceptLostEntity(ConstrAcceptLostEntityDTO obj);

    @POST
    @Path("/constrAcceptLostEntity/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addConstrAcceptLostEntity(ConstrAcceptLostEntityDTO obj);

    @DELETE
    @Path("/constrAcceptLostEntity/{id}/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteConstrAcceptLostEntity(@PathParam("id") Long id);
}
