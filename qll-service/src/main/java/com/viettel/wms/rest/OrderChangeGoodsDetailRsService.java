/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.viettel.wms.dto.OrderChangeGoodsDetailDTO;

/**
 *
 * @author HungLQ9
 */
public interface OrderChangeGoodsDetailRsService {

    @GET
    @Path("/orderChangeGoodsDetail")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getOrderChangeGoodsDetail();

    @GET
    @Path("/orderChangeGoodsDetail/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getOrderChangeGoodsDetailById(@PathParam("id") Long id);

    @POST
    @Path("/orderChangeGoodsDetail/update")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateOrderChangeGoodsDetail(OrderChangeGoodsDetailDTO obj);

    @POST
    @Path("/orderChangeGoodsDetail/add")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addOrderChangeGoodsDetail(OrderChangeGoodsDetailDTO obj);

    @POST
    @Path("/orderChangeGoodsDetail/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteOrderChangeGoodsDetail(@PathParam("id") Long id);
    
    @POST
    @Path("/doSearch")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response doSearch(OrderChangeGoodsDetailDTO obj);
    
}
