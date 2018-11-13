/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.rest;

import com.viettel.wms.dto.OrderGoodsDTO;
import com.viettel.wms.dto.OrderGoodsDetailDTO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author HungLQ9
 */
public interface OrderGoodsDetailRsService {

    @GET
    @Path("/orderGoodsDetail")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getOrderGoodsDetail();

    @GET
    @Path("/orderGoodsDetail/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getOrderGoodsDetailById(@PathParam("id") Long id);
    
    @GET
    @Path("/getorderGoodsDetail/{OrderGoodId}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getOrderGoodsDetailByOrderGoodId(@PathParam("OrderGoodId") Long OrderGoodId);

    @PUT
    @Path("/orderGoodsDetail/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateOrderGoodsDetail(OrderGoodsDetailDTO obj);

    @POST
    @Path("/orderGoodsDetail/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addOrderGoodsDetail(OrderGoodsDetailDTO obj);

    @DELETE
    @Path("/orderGoodsDetail/{id}/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteOrderGoodsDetail(@PathParam("id") Long id);
    
    @POST
    @Path("/orderGoodsDetail/doSearchGoodsDetailForImportReq")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response doSearchGoodsDetailForImportReq(OrderGoodsDetailDTO obj);
    
    @POST
    @Path("/orderGoodsDetail/doSearchGoodsDetailForExportReq")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response doSearchGoodsDetailForExportReq();
    
    @POST
    @Path("/orderGoodsDetail/doSearchGoodsDetailForExportReqById")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response doSearchGoodsDetailForExportReq(OrderGoodsDTO obj);
}
