/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import com.viettel.erp.dto.EstimatesDetailAnalystDTO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author HungLQ9
 */
public interface EstimatesDetailAnalystRsService {

	//minhpvn
	@POST
    @Path("/estimatesDetailAnalyst/doSearchEstimatesDetailAnalyst")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response doSearchEstimatesDetailAnalyst(EstimatesDetailAnalystDTO obj);
	//end
    @GET
    @Path("/estimatesDetailAnalyst")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getEstimatesDetailAnalyst();

    @GET
    @Path("/estimatesDetailAnalyst/estimatesDetailAnalystList")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAcceptanceList();
    
    @GET
    @Path("/estimatesDetailAnalyst/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getEstimatesDetailAnalystById(@PathParam("id") Long id);
    
    @GET
    @Path("/estimatesDetailAnalyst/getAcceptanceListById/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAcceptanceListById(@PathParam("id") Long constructId);

    @POST
    @Path("/estimatesDetailAnalyst/put")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateEstimatesDetailAnalyst(EstimatesDetailAnalystDTO obj);

    @POST
    @Path("/estimatesDetailAnalyst/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addEstimatesDetailAnalyst(EstimatesDetailAnalystDTO obj);

    @DELETE
    @Path("/estimatesDetailAnalyst/{id}/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteEstimatesDetailAnalyst(@PathParam("id") Long id);
}
