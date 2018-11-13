/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import com.viettel.erp.dto.ConstrConstructionsSearchDTO;
import com.viettel.erp.dto.VConstrConstructionsDTO;
import com.viettel.erp.dto.VConstrConstructionsSearchDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author HungLQ9
 */
public interface VConstrConstructionsRsService {

    @GET
    @Path("/vConstrConstructions")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getVConstrConstructions();

    @GET
    @Path("/vConstrConstructions/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getVConstrConstructionsById(@PathParam("id") Long id);

    @POST
    @Path("/vConstrConstructions/put/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateVConstrConstructions(VConstrConstructionsDTO obj);

    @POST
    @Path("/vConstrConstructions/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addVConstrConstructions(VConstrConstructionsDTO obj);

    @DELETE
    @Path("/vConstrConstructions/{id}/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteVConstrConstructions(@PathParam("id") Long id);
    
    @POST
    @Path("/vConstrConstructions/getAllandSearch/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAllandSearch(VConstrConstructionsSearchDTO obj);
}
