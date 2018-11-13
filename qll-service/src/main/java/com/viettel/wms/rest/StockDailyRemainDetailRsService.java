/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.rest;

import com.viettel.wms.dto.StockDailyRemainDetailDTO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author HungLQ9
 */
public interface StockDailyRemainDetailRsService {

    @GET
    @Path("/stockDailyRemainDetail")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getStockDailyRemainDetail();

    @GET
    @Path("/stockDailyRemainDetail/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getStockDailyRemainDetailById(@PathParam("id") Long id);

    @PUT
    @Path("/stockDailyRemainDetail/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateStockDailyRemainDetail(StockDailyRemainDetailDTO obj);

    @POST
    @Path("/stockDailyRemainDetail/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addStockDailyRemainDetail(StockDailyRemainDetailDTO obj);

    @DELETE
    @Path("/stockDailyRemainDetail/{id}/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteStockDailyRemainDetail(@PathParam("id") Long id);
}
