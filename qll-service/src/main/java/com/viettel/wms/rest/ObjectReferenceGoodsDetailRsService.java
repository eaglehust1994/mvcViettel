/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.rest;

import com.viettel.wms.dto.ObjectReferenceGoodsDetailDTO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author HungLQ9
 */
public interface ObjectReferenceGoodsDetailRsService {

    @GET
    @Path("/objectReferenceGoodsDetail")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getObjectReferenceGoodsDetail();

    @GET
    @Path("/objectReferenceGoodsDetail/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getObjectReferenceGoodsDetailById(@PathParam("id") Long id);

    @PUT
    @Path("/objectReferenceGoodsDetail/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateObjectReferenceGoodsDetail(ObjectReferenceGoodsDetailDTO obj);

    @POST
    @Path("/objectReferenceGoodsDetail/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addObjectReferenceGoodsDetail(ObjectReferenceGoodsDetailDTO obj);

    @DELETE
    @Path("/objectReferenceGoodsDetail/{id}/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteObjectReferenceGoodsDetail(@PathParam("id") Long id);
    
    @POST
    @Path("/objectReferenceGoodsDetail/getGoodsDetail")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getGoodsDetail(ObjectReferenceGoodsDetailDTO obj);
}
