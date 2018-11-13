/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import com.viettel.erp.dto.ConstrWorkCompConfListDTO;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author HungLQ9
 */
public interface ConstrWorkCompConfListRsService {

    @GET
    @Path("/constrWorkCompConfList")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getConstrWorkCompConfList();

    @GET
    @Path("/constrWorkCompConfList/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getConstrWorkCompConfListById(@PathParam("id") Long id);
    
    
    @GET
    @Path("/constrWorkCompConfList/byConstrId/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getListConstrWorkByConstrId(@PathParam("id") Long id);
    
    @POST
    @Path("/constrWorkCompConfList/cWorkExistByConstrId/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getListConstrWorkExistByConstrId(List<Long> id);
    
    @POST
    @Path("/constrWorkCompConfList/put/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateConstrWorkCompConfList(ConstrWorkCompConfListDTO obj);

    @POST
    @Path("/constrWorkCompConfList/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addConstrWorkCompConfList(ConstrWorkCompConfListDTO obj);

    @DELETE
    @Path("/constrWorkCompConfList/{id}/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteConstrWorkCompConfList(@PathParam("id") Long id);
}
