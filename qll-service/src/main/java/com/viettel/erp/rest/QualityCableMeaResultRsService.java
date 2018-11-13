/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import com.viettel.erp.dto.QualityCableMeaResultDTO;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author HungLQ9
 */
public interface QualityCableMeaResultRsService {

    @GET
    @Path("/qualityCableMeaResult")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getQualityCableMeaResult();

    @GET
    @Path("/qualityCableMeaResult/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getQualityCableMeaResultById(@PathParam("id") Long id);

    @POST
    @Path("/qualityCableMeaResult/put/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateQualityCableMeaResult(QualityCableMeaResultDTO obj);

    @POST
    @Path("/qualityCableMeaResult/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addQualityCableMeaResult(QualityCableMeaResultDTO obj);
    
//    @POST
//    @Path("/qualityCableMeaResult/listQuality")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response insertListQualityResult(List<QualityCableMeaResultDTO> obj);

    @DELETE
    @Path("/qualityCableMeaResult/{id}/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteQualityCableMeaResult(@PathParam("id") Long id);
    
    @POST
    @Path("/qualityCableMeaResult/insertListQuality")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response insertListQuality(List<QualityCableMeaResultDTO> obj);
    
}
