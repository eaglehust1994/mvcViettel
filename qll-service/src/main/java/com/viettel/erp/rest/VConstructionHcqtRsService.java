/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import com.viettel.erp.dto.VConstrConstructionsSearchDTO;
import com.viettel.erp.dto.VConstructionHcqtDTO;
import com.viettel.erp.dto.VConstructionsHcqtSearchDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author HungLQ9
 */
public interface VConstructionHcqtRsService {

    @GET
    @Path("/vConstructionHcqt")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getVConstructionHcqt();

    @GET
    @Path("/vConstructionHcqt/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getVConstructionHcqtById(@PathParam("id") Long id);

    @POST
    @Path("/vConstructionHcqt/put/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateVConstructionHcqt(VConstructionHcqtDTO obj);

    @POST
    @Path("/vConstructionHcqt/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addVConstructionHcqt(VConstructionHcqtDTO obj);

    @DELETE
    @Path("/vConstructionHcqt/{id}/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteVConstructionHcqt(@PathParam("id") Long id);
    
    @POST
    @Path("/vConstructionHcqt/getAllandSearch/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAllandSearch(VConstructionsHcqtSearchDTO obj);
    
    @POST
    @Path("/vConstructionHcqt/getContractTotalValue/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getContractTotalValue(@PathParam("id") Long id);
}
