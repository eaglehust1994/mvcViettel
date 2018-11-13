/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.rest;

import com.viettel.wms.dto.OrderPatternDTO;
import com.viettel.wms.dto.OrderPatternGoodsDTO;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author HungLQ9
 */
public interface OrderPatternGoodsRsService {

    @GET
    @Path("/orderPatternGoods")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getOrderPatternGoods();

    @GET
    @Path("/orderPatternGoods/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getOrderPatternGoodsById(@PathParam("id") Long id);

    @PUT
    @Path("/orderPatternGoods/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateOrderPatternGoods(OrderPatternGoodsDTO obj);

    @POST
    @Path("/orderPatternGoods/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addOrderPatternGoods(OrderPatternGoodsDTO obj);

    @DELETE
    @Path("/orderPatternGoods/{id}/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteOrderPatternGoods(@PathParam("id") Long id);
    
    @POST
    @Path("/doSearch")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response doSearch(OrderPatternGoodsDTO obj);
    
    //tim hang hoa theo id cua mau yeu cau
    @POST
    @Path("/getPatternGoodsByOrderPatternId")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getPatternGoodsByOrderPatternId(OrderPatternGoodsDTO obj);
    
}
