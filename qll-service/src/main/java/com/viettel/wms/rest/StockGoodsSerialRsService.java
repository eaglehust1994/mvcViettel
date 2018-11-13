/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.rest;

import com.viettel.wms.dto.OrderDTO;
import com.viettel.wms.dto.StockGoodsSerialDTO;
import com.viettel.wms.dto.StockTransDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author HungLQ9
 */
public interface StockGoodsSerialRsService {

    @GET
    @Path("/stockGoodsSerial")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getStockGoodsSerial();

    @GET
    @Path("/stockGoodsSerial/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getStockGoodsSerialById(@PathParam("id") Long id);

    @PUT
    @Path("/stockGoodsSerial/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateStockGoodsSerial(StockGoodsSerialDTO obj);

    @POST
    @Path("/stockGoodsSerial/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addStockGoodsSerial(StockGoodsSerialDTO obj);
    

    @DELETE
    @Path("/stockGoodsSerial/{id}/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteStockGoodsSerial(@PathParam("id") Long id);
    
    @POST
    @Path("/stockGoodsSerial/doSearchFindSerial")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response doSearchFindSerial(StockGoodsSerialDTO obj);
    
    @POST
    @Path("/stockGoodsSerial/doSearchHistory")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response doSearchHistory(StockTransDTO obj);
}
