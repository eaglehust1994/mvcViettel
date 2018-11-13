/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import com.viettel.erp.dto.AbComplementWorkDescribeDTO;
import com.viettel.erp.dto.EstimatesWorkItemsDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author HungLQ9
 */
public interface AbComplementWorkDescribeRsService {

    @GET
    @Path("/abComplementWorkDescribe")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAbComplementWorkDescribe();

    @GET
    @Path("/abComplementWorkDescribe/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAbComplementWorkDescribeById(@PathParam("id") Long id);

    @GET
    @Path("/abComplementWorkDescribe/constructId/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAbComplementWorkByConstId(@PathParam("id") Long id);
    
    @POST
    @Path("/abComplementWorkDescribe/put/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateAbComplementWorkDescribe(AbComplementWorkDescribeDTO obj);

    @POST
    @Path("/abComplementWorkDescribe/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addAbComplementWorkDescribe(AbComplementWorkDescribeDTO obj);

    @DELETE
    @Path("/abComplementWorkDescribe/{id}/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteAbComplementWorkDescribe(@PathParam("id") Long id);
    
    @POST
    @Path("/abComplementWorkDescribe/addForm4")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addForm4(AbComplementWorkDescribeDTO obj);
    
    @POST
    @Path("/abComplementWorkDescribe/signCA")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response signCA(EstimatesWorkItemsDTO dto) throws Exception;
    
}
