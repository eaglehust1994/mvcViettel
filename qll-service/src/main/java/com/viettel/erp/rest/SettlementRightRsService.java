/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import com.viettel.erp.dto.ConstrConstructionsDTO;
import com.viettel.erp.dto.SettlementRightDTO;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author HungLQ9
 */
public interface SettlementRightRsService {
	
	@POST
    @Path("/saveSettlementRight/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response saveSettlementRight(List<SettlementRightDTO> listID);
	
	@POST
    @Path("/deleteMultipleSettlement/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteMultipleSettlement(List<String> listID);
	
	@POST
    @Path("/getSettlementRightByConstrt/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getSettlementRightByConstrt(ConstrConstructionsDTO obj);

    @GET
    @Path("/settlementRight")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getSettlementRight();

    @GET
    @Path("/settlementRight/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getSettlementRightById(@PathParam("id") Long id);

    @POST
    @Path("/settlementRight/put/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateSettlementRight(SettlementRightDTO obj);

    @POST
    @Path("/settlementRight/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addSettlementRight(SettlementRightDTO obj);

    @DELETE
    @Path("/settlementRight/{id}/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteSettlementRight(@PathParam("id") Long id);
    
    @POST
    @Path("/getAllAMonitorOrBInChargeByConstructId/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAllAMonitorOrBInChargeByConstructId(SettlementRightDTO obj);
    
    //minhpvn : lay nguoi ky quyet toan A-B form 6
    @POST
    @Path("/getAllAMonitorOrBInChargeByConstructIdForm6/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAllAMonitorOrBInChargeByConstructIdForm6(SettlementRightDTO obj);
}
