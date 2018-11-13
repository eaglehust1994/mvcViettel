/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.rest;

//import com.viettel.wms.dto.AppParamDTO;
import com.viettel.wms.dto.StockGoodsSerialDTO;
import com.viettel.wms.dto.StockGoodsTotalDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author HungLQ9
 */
public interface StockGoodsTotalRsService {

    @GET
    @Path("/stockGoodsTotal")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getStockGoodsTotal();

    @GET
    @Path("/stockGoodsTotal/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getStockGoodsTotalById(@PathParam("id") Long id);

    @PUT
    @Path("/stockGoodsTotal/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateStockGoodsTotal(StockGoodsTotalDTO obj);

    @POST
    @Path("/stockGoodsTotal/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addStockGoodsTotal(StockGoodsTotalDTO obj);

    @DELETE
    @Path("/stockGoodsTotal/{id}/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteStockGoodsTotal(@PathParam("id") Long id);
 
    // tim kiem bao cao ton kho tuannb
    @POST
    @Path("/doSearch")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response doSearch(StockGoodsTotalDTO obj) throws Exception;
    //end tuannb
    
    @POST
    @Path("/stockGoodsSerial/doSearchFindSerial")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response doSearchFindSerial(StockGoodsSerialDTO obj);
    
    @POST
    @Path("/doSearchTotal")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response doSearchTotal(StockGoodsTotalDTO obj) ;
    
    @POST
    @Path("/doSearchStockGood")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response doSearchStockGood(StockGoodsTotalDTO obj);
    
    @POST
    @Path("/getCheckStockGood")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getCheckStockGood(StockGoodsTotalDTO obj);
    
}
