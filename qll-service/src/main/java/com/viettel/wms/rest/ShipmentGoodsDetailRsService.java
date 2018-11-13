/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.rest;

import com.viettel.wms.dto.AttachmentDTO;
import com.viettel.wms.dto.ShipmentGoodsDetailDTO;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;

/**
 *
 * @author HungLQ9
 */
public interface ShipmentGoodsDetailRsService {

    @GET
    @Path("/shipmentGoodsDetail")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getShipmentGoodsDetail();

    @GET
    @Path("/shipmentGoodsDetail/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getShipmentGoodsDetailById(@PathParam("id") Long id);

    @PUT
    @Path("/shipmentGoodsDetail/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateShipmentGoodsDetail(ShipmentGoodsDetailDTO obj);

    @POST
    @Path("/shipmentGoodsDetail/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addShipmentGoodsDetail(ShipmentGoodsDetailDTO obj);

    @DELETE
    @Path("/shipmentGoodsDetail/{id}/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteShipmentGoodsDetail(@PathParam("id") Long id);
    
    @POST
    @Path("/shipmentGoodsDetail/getDoMapDetail")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getDoMapDetail(ShipmentGoodsDetailDTO obj);
    
    @POST
    @Path("/shipmentGoodsDetail/getGoodsInfoByCode")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getGoodsInfoByCode(String code);
    
    @POST
    @Path("/shipmentGoodsDetail/getGoodsDetail")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getGoodsDetail(ShipmentGoodsDetailDTO obj);
    
    @POST
    @Path("/shipmentGoodsDetail/importGoods")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response importGoods(Attachment attachments, @Context HttpServletRequest request) throws Exception;
    
    @POST
    @Path("/shipmentGoodsDetail/getGoodsFile")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getGoodsFile(AttachmentDTO obj);
    
}
