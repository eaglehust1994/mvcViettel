/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.rest;

import com.viettel.wms.dto.StockTransDetailSerialDTO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author HungLQ9
 */
public interface StockTransDetailSerialRsService {

    @GET
    @Path("/stockTransDetailSerial")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getStockTransDetailSerial();

    @GET
    @Path("/stockTransDetailSerial/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getStockTransDetailSerialById(@PathParam("id") Long id);

    @PUT
    @Path("/stockTransDetailSerial/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateStockTransDetailSerial(StockTransDetailSerialDTO obj);

    @POST
    @Path("/stockTransDetailSerial/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addStockTransDetailSerial(StockTransDetailSerialDTO obj);

    @DELETE
    @Path("/stockTransDetailSerial/{id}/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteStockTransDetailSerial(@PathParam("id") Long id);
    
    @POST
    @Path("/stockTransDetailSerial/doSearchGoodsDetailForImportNote")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response doSearchGoodsDetailForImportNote(StockTransDetailSerialDTO obj);
    
    @POST
    @Path("/stockTransDetailSerial/doSearchGoodsDetailSerial")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response doSearchGoodsDetailSerial(StockTransDetailSerialDTO obj);
    
    @POST
    @Path("/stockTransDetailSerial/getGoodsInfoByCode")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getGoodsInfoByCode(String code);
    
    @POST
    @Path("/stockTransDetailSerial/doSearchGoodsDetailForExportNote")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response doSearchGoodsDetailForExportNote();
    
    @POST
    @Path("/stockTransDetailSerial/doSearchGoodsDetailForExportNoteByStockTransDetailId")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response doSearchGoodsDetailForExportNote(StockTransDetailSerialDTO obj);

    
    
    @POST
    @Path("/stockTransDetailSerial/getGoodsDetail")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getGoodsDetail(StockTransDetailSerialDTO obj);

    
    
}
