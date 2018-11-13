/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.rest;

import com.viettel.wms.dto.ObjectReferenceGoodsDTO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author HungLQ9
 */
public interface ObjectReferenceGoodsRsService {

    @GET
    @Path("/objectReferenceGoods")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getObjectReferenceGoods();

    @GET
    @Path("/objectReferenceGoods/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getObjectReferenceGoodsById(@PathParam("id") Long id);

    @PUT
    @Path("/objectReferenceGoods/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateObjectReferenceGoods(ObjectReferenceGoodsDTO obj);

    @POST
    @Path("/objectReferenceGoods/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addObjectReferenceGoods(ObjectReferenceGoodsDTO obj);

    @DELETE
    @Path("/objectReferenceGoods/{id}/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteObjectReferenceGoods(@PathParam("id") Long id);
    
    @POST
    @Path("/objectReferenceGoods/getGoodsInfoBeforeWarrantyByCode")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getGoodsInfoBeforeWarrantyByCode(String code);
    
    @POST
    @Path("/objectReferenceGoods/getGoodsInfoAfterWarrantyByCode")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getGoodsInfoAfterWarrantyByCode(String code);
    
    @POST
    @Path("/objectReferenceGoods/getGoodsInfoFromConstructionByCode")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getGoodsInfoFromConstructionByCode(String code);
    
    @POST
    @Path("/objectReferenceGoods/getGoodsInfoFromDepartmentByCode")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getGoodsInfoFromDepartmentByCode(String code);
    
    @POST
    @Path("/objectReferenceGoods/getGoodsInfoFromLoanByCode")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getGoodsInfoFromLoanByCode(String code);
}
