/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import com.viettel.erp.dto.ProjInvestProjectDTO;
import com.viettel.erp.dto.ProjInvestProjectSearchDTO;
import com.viettel.erp.dto.VConstructionsHcqtSearchDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author HungLQ9
 */
public interface ProjInvestProjectRsService {

    @GET
    @Path("/projInvestProject")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getProjInvestProject();

    @GET
    @Path("/projInvestProject/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getProjInvestProjectById(@PathParam("id") Long id);

    @POST
    @Path("/projInvestProject/put/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateProjInvestProject(ProjInvestProjectDTO obj);

    @POST
    @Path("/projInvestProject/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addProjInvestProject(ProjInvestProjectDTO obj);

    @DELETE
    @Path("/projInvestProject/{id}/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteProjInvestProject(@PathParam("id") Long id);
    
    @POST
    @Path("/projInvestProject/doSearch/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response doSearch(ProjInvestProjectSearchDTO obj);
}
