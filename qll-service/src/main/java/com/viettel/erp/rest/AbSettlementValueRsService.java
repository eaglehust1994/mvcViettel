/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import com.viettel.erp.dto.AbSettlementValueDTO;
import com.viettel.erp.dto.AssetManageReqDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author HungLQ9
 */
public interface AbSettlementValueRsService {

    @GET
    @Path("/abSettlementValue")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAbSettlementValue();

    @GET
    @Path("/abSettlementValue/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAbSettlementValueById(@PathParam("id") Long id);

    @POST
    @Path("/abSettlementValue/put/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateAbSettlementValue(AbSettlementValueDTO obj);

    @POST
    @Path("/abSettlementValue/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addAbSettlementValue(AbSettlementValueDTO obj);

    @DELETE
    @Path("/abSettlementValue/{id}/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteAbSettlementValue(@PathParam("id") Long id);
    
    @POST
    @Path("/abSettlementValue/checkDataForm1/{constructId}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response checkDataSaveForm1(@PathParam("constructId") Long constructId);
    
    @POST
    @Path("/abSettlementValue/getconstrCompReMapId/{abSettlementValueId}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getconstrCompReMapId(@PathParam("abSettlementValueId") Long abSettlementValueId);
    
    @GET
	@Path("/abSettlementValue/exportFilePdf/{idValue}/")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response exportFilePdfFromDB(@PathParam("idValue") Long id);
    
    @GET
	@Path("/abSettlementValue/exportFileWord/{idValue}/")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response exportFileWordFromDB(@PathParam("idValue") Long id);
    
    @GET
	@Path("/abSettlementValue/getStatusEvaluate/{constrId}/")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getStatusEvaluate(@PathParam("constrId") Long id);
}
