/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import com.viettel.erp.dto.AdvancePaymentProposalDTO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author HungLQ9
 */
public interface AdvancePaymentProposalRsService {

    @GET
    @Path("/advancePaymentProposal")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAdvancePaymentProposal();

    @GET
    @Path("/advancePaymentProposal/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAdvancePaymentProposalById(@PathParam("id") Long id);
    @GET
    @Path("/advancePaymentProposal/constructId/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAdvancePaymentProposalByConstructId(@PathParam("id") Long id);
    
    @POST
    @Path("/advancePaymentProposal/put")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateAdvancePaymentProposal(AdvancePaymentProposalDTO obj);

    @POST
    @Path("/advancePaymentProposal/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addAdvancePaymentProposal(AdvancePaymentProposalDTO obj);

    @DELETE
    @Path("/advancePaymentProposal/{id}/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteAdvancePaymentProposal(@PathParam("id") Long id);
}
