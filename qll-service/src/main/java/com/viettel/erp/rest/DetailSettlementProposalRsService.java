/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.viettel.erp.dto.DetailSettlementProposalDTO;
import com.viettel.erp.dto.EstimatesWorkItemsDTO;
import com.viettel.erp.dto.approDTO;

/**
 *
 * @author HungLQ9
 */
public interface DetailSettlementProposalRsService {

	 @POST
	    @Path("/detailSettlementProposal/appro")
	    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	    public Response appro(approDTO obj);
	
	
    @GET
    @Path("/detailSettlementProposal")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getDetailSettlementProposal();

    @GET
    @Path("/detailSettlementProposal/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getDetailSettlementProposalById(@PathParam("id") Long id);
   
    @POST
    @Path("/detailSettlementProposal/getAllDetailSettlementProposal")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAllDetailSettlementProposal(DetailSettlementProposalDTO  detailSettlementProposalDTO);

    @POST
    @Path("/detailSettlementProposal/put/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateDetailSettlementProposal(DetailSettlementProposalDTO obj);

    @POST
    @Path("/detailSettlementProposal/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addDetailSettlementProposal(DetailSettlementProposalDTO obj);

    @DELETE
    @Path("/detailSettlementProposal/{id}/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteDetailSettlementProposal(@PathParam("id") Long id);
    
    @POST
    @Path("/detailSettlementProposal/updateManyTable")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateManyTable(DetailSettlementProposalDTO Evaluate) throws Exception;
    
    @POST
    @Path("/detailSettlementProposal/addAll")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addAll(DetailSettlementProposalDTO Evaluate) throws Exception;
    
    @POST
    @Path("/detailSettlementProposal/saveDetailSettlementProposal")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response saveDetailSettlementProposal(DetailSettlementProposalDTO Evaluate) throws Exception;

    @POST
    @Path("/detailSettlementProposal/exPortfull")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	Response exPortfull(EstimatesWorkItemsDTO obj) throws Exception;
    
    @POST
    @Path("/detailSettlementProposal/updateIsActive")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	Response updateIsActive(Long detailSettlementProposalId) throws Exception;
}
