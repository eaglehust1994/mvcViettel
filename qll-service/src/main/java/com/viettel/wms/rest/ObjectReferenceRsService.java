/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.rest;

import com.viettel.wms.dto.ObjectReferenceDTO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author HungLQ9
 */
public interface ObjectReferenceRsService {

    @GET
    @Path("/objectReference")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getObjectReference();

    @GET
    @Path("/objectReference/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getObjectReferenceById(@PathParam("id") Long id);

    @PUT
    @Path("/objectReference/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateObjectReference(ObjectReferenceDTO obj);

    @POST
    @Path("/objectReference/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addObjectReference(ObjectReferenceDTO obj);

    @DELETE
    @Path("/objectReference/{id}/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteObjectReference(@PathParam("id") Long id);
    
    @POST
    @Path("/objectReference/getDataKCS")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getDataKCS(ObjectReferenceDTO obj);
    
    @POST
    @Path("/objectReference/getGoodsInfoBeforeWarrantyCode")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getGoodsInfoBeforeWarrantyCode(String code);
    
    @POST
    @Path("/objectReference/getGoodsInfoAfterWarrantyCode")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getGoodsInfoAfterWarrantyCode(String code);
    
    @POST
    @Path("/objectReference/getGoodsInfoFromConstructionCode")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getGoodsInfoFromConstructionCode(String code);
    
    @POST
    @Path("/objectReference/getGoodsInfoFromDepartmentCode")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getGoodsInfoFromDepartmentCode(String code);
    
    @POST
    @Path("/objectReference/getGoodsInfoFromLoanCode")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getGoodsInfoFromLoanCode(String code);
    
}
