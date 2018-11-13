/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import com.viettel.erp.dto.EstimatesItemsChildDTO;
import com.viettel.erp.dto.SettlementRightDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author HungLQ9
 */
public interface EstimatesItemsChildRsService {

    @GET
    @Path("/estimatesItemsChild")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getEstimatesItemsChild();

    @GET
    @Path("/estimatesItemsChild/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getEstimatesItemsChildById(@PathParam("id") Long id);

    @POST
    @Path("/estimatesItemsChild/put/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateEstimatesItemsChild(EstimatesItemsChildDTO obj);

    @POST
    @Path("/estimatesItemsChild/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addEstimatesItemsChild(EstimatesItemsChildDTO obj);

    @DELETE
    @Path("/estimatesItemsChild/{id}/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteEstimatesItemsChild(@PathParam("id") Long id);
    
    @GET
    @Path("/estimatesItemsChild/allItemChildInConstruct/{constructionId}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAllItemsChildInContruct(@PathParam("constructionId") Long constructionId);
    
    @POST
	@Path("/getListEstimateItemschild/")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getListEstimateItemschild(EstimatesItemsChildDTO rightDTO);
}
