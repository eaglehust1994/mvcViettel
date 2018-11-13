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

import com.viettel.erp.dto.DetailSettlementEvaluateDTO;
import com.viettel.erp.dto.DetailSettlementProposalDTO;
import com.viettel.erp.dto.EstimatesWorkItemsDTO;
import com.viettel.erp.dto.approDTO;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author HungLQ9
 */
public interface DetailSettlementEvaluateRsService {

    @GET
    @Path("/detailSettlementEvaluate")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getDetailSettlementEvaluate();

    @GET
    @Path("/detailSettlementEvaluate/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getDetailSettlementEvaluateById(@PathParam("id") Long id);

    @POST
    @Path("/detailSettlementEvaluate/put/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateDetailSettlementEvaluate(DetailSettlementEvaluateDTO obj);

    @POST
    @Path("/detailSettlementEvaluate/appro")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response appro(approDTO obj);
    
    @POST
    @Path("/detailSettlementEvaluate/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addOrUpdateDetailSettlementEvaluate(DetailSettlementEvaluateDTO obj);

    @DELETE
    @Path("/detailSettlementEvaluate/{id}/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteDetailSettlementEvaluate(@PathParam("id") Long id);
    
    
    @POST
    @Path("/detailSettlementEvaluate/fail/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response fail(DetailSettlementEvaluateDTO obj) throws Exception;
    
    
    @POST
    @Path("/detailSettlementEvaluate/getAllbyConstructId")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAllbyConstructId(DetailSettlementEvaluateDTO obj);
    
    
    @POST
    @Path("/detailSettlementEvaluate/getAllEstimatesWorkInsideContract")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAllEstimatesWorkInsideContractForEvaluate(EstimatesWorkItemsDTO obj);
    
    
    @POST
    @Path("/detailSettlementEvaluate/getAllEstimatesWorkOutsideContract")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAllEstimatesWorkOutsideContractForEvaluate(EstimatesWorkItemsDTO obj);
    
    
    @POST
    @Path("/detailSettlementEvaluate/addAll")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addAll(DetailSettlementEvaluateDTO Evaluate) throws Exception;
    
    @POST
    @Path("/detailSettlementEvaluate/importCT")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response importCT(EstimatesWorkItemsDTO obj) throws Exception;
    
    @POST
    @Path("/detailSettlementEvaluate/importKL")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response importKL(EstimatesWorkItemsDTO obj) throws Exception;
    
    @POST
    @Path("/detailSettlementEvaluate/exPortKLTD")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response exPortKLTD(EstimatesWorkItemsDTO obj) throws Exception;
    
    @POST
    @Path("/detailSettlementEvaluate/exPortCT")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response exPortCT(EstimatesWorkItemsDTO obj) throws Exception;
    
    @POST
    @Path("/detailSettlementEvaluate/updateIsActive")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateIsActive(Long detailSettlementEvaluateId) throws Exception;
    
    @POST
    @Path("/detailSettlementEvaluate/checkPoFound")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response checkPoFound(DetailSettlementProposalDTO obj) throws Exception;

    
    @POST
    @Path("/detailSettlementEvaluate/exPortfull")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response exPortfull(EstimatesWorkItemsDTO obj) throws Exception;
    
    @POST
    @Path("/detailSettlementEvaluate/importCTHD")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response importCTHD(EstimatesWorkItemsDTO obj) throws Exception;
    
    @POST
    @Path("/detailSettlementEvaluate/exPortCTHD")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response exPortCTHD(EstimatesWorkItemsDTO obj) throws Exception;
    
    @POST
    @Path("/detailSettlementEvaluate/exPortCVHD")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response exPortCVHD(EstimatesWorkItemsDTO obj) throws Exception;
    
    
    @POST
    @Path("/detailSettlementEvaluate/imPortCVHD")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response imPortCVHD(EstimatesWorkItemsDTO obj) throws Exception;
    
    @GET
    @Path("/detailSettlementEvaluate/getsendPersonName/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getsendPersonName(@PathParam("id") Long id);
}
