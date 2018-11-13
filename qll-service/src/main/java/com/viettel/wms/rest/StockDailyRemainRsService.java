/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.rest;

import com.viettel.wms.dto.CommonDTO;
import com.viettel.wms.dto.StockDTO;
import com.viettel.wms.dto.StockDailyRemainDTO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author HungLQ9
 */
public interface StockDailyRemainRsService {

    @GET
    @Path("/stockDailyRemain")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getStockDailyRemain();

    @GET
    @Path("/stockDailyRemain/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getStockDailyRemainById(@PathParam("id") Long id);

    @PUT
    @Path("/stockDailyRemain/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateStockDailyRemain(StockDailyRemainDTO obj);

    @POST
    @Path("/stockDailyRemain/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addStockDailyRemain(StockDailyRemainDTO obj);

    @DELETE
    @Path("/stockDailyRemain/{id}/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteStockDailyRemain(@PathParam("id") Long id);

    @POST
	@Path("/doSearch")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response doSearch(StockDailyRemainDTO obj);

    @POST
   	@Path("/exportCard")
   	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
   	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	Response exportCard(CommonDTO obj) throws Exception;
}
