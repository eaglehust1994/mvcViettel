/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import com.viettel.erp.dto.BMaterialAcceptMerListDTO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author HungLQ9
 */
public interface BMaterialAcceptMerListRsService {

    @GET
    @Path("/bMaterialAcceptMerList")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getBMaterialAcceptMerList();

    @GET
    @Path("/bMaterialAcceptMerList/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getBMaterialAcceptMerListById(@PathParam("id") Long id);

    @GET
    @Path("/bMaterialAcceptMerList/getAccpetMerList/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAccpetMerList(@PathParam("id") Long bMaterialAcceptanceId);
//   
    @POST
    @Path("/bMaterialAcceptMerList/put/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateBMaterialAcceptMerList(BMaterialAcceptMerListDTO obj);
//    public Response updateBMerList(BMaterialAcceptMerListDTO obj);
   

    @POST
    @Path("/bMaterialAcceptMerList/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addBMaterialAcceptMerList(BMaterialAcceptMerListDTO obj);

    @DELETE
    @Path("/bMaterialAcceptMerList/{id}/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteBMaterialAcceptMerList(@PathParam("id") Long id);
}
