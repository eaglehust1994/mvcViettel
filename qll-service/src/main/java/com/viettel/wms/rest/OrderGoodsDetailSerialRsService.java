/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.rest;

import com.viettel.wms.dto.OrderGoodsDetailSerialDTO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author HungLQ9
 */
public interface OrderGoodsDetailSerialRsService {

    @GET
    @Path("/orderGoodsDetailSerial")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getOrderGoodsDetailSerial();

    @GET
    @Path("/orderGoodsDetailSerial/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getOrderGoodsDetailSerialById(@PathParam("id") Long id);

    @PUT
    @Path("/orderGoodsDetailSerial/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateOrderGoodsDetailSerial(OrderGoodsDetailSerialDTO obj);

    @POST
    @Path("/orderGoodsDetailSerial/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addOrderGoodsDetailSerial(OrderGoodsDetailSerialDTO obj);

    @DELETE
    @Path("/orderGoodsDetailSerial/{id}/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteOrderGoodsDetailSerial(@PathParam("id") Long id);
}
