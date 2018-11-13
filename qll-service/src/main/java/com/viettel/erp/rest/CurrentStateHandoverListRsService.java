/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import com.viettel.erp.dto.CurrentStateHandoverListDTO;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author HungLQ9
 */
public interface CurrentStateHandoverListRsService {

    @GET
    @Path("/currentStateHandoverList")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getCurrentStateHandoverList();

    @GET
    @Path("/currentStateHandoverList/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getCurrentStateHandoverListById(@PathParam("id") Long id);

    @GET
    @Path("/currentStateHandoverList/listCurrentHandoverByCrStHID/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getListCurrentStateHandoverByCrStHoId(@PathParam("id") Long id);
    
    @POST
    @Path("/currentStateHandoverList/put/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateCurrentStateHandoverList(CurrentStateHandoverListDTO obj);

    @POST
    @Path("/currentStateHandoverList/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addCurrentStateHandoverList(CurrentStateHandoverListDTO obj);

    @DELETE
    @Path("/currentStateHandoverList/{id}/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteCurrentStateHandoverList(@PathParam("id") Long id);
    
    @POST
    @Path("/currentStateHandoverList/removeMutiRecord/put/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteMutilRecord(List<String> listId);
}
