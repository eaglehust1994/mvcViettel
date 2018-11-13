/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.viettel.erp.dto.ConstrAcceptWorkListDTO;

/**
 *
 * @author HungLQ9
 */
public interface ConstrAcceptWorkListRsService {

    @GET
    @Path("/constrAcceptWorkList")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getConstrAcceptWorkList();

    @GET
    @Path("/constrAcceptWorkList/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getConstrAcceptWorkListById(@PathParam("id") Long id);

    @POST
    @Path("/constrAcceptWorkList/put/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateConstrAcceptWorkList(ConstrAcceptWorkListDTO obj);

    @POST
    @Path("/constrAcceptWorkList/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addConstrAcceptWorkList(ConstrAcceptWorkListDTO obj);
    
   
    @DELETE
    @Path("/constrAcceptWorkList/{id}/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteConstrAcceptWorkList(@PathParam("id") Long id);
    
    
    @GET
    @Path("/constrAcceptWorkList/getAllbyConstructId/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAllbyConstructId(@PathParam("id") Long id);
    
    
    @POST
    @Path("/constrAcceptWorkList/getProposedSettlement/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getProposedSettlement(@PathParam("id") Long id);
}
