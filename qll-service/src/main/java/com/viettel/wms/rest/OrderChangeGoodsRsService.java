/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.rest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;

import com.viettel.wms.dto.OrderChangeGoodsDTO;
import com.viettel.wms.dto.OrderChangeGoodsDetailDTO;

/**
 *
 * @author HungLQ9
 */
public interface OrderChangeGoodsRsService {

    @GET
    @Path("/orderChangeGoods")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getOrderChangeGoods();

    @GET
    @Path("/orderChangeGoods/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getOrderChangeGoodsById(@PathParam("id") Long id);

    @POST
    @Path("/orderChangeGoods/update")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateOrderChangeGoods(OrderChangeGoodsDTO obj);

    @POST
    @Path("/orderChangeGoods/add")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addOrderChangeGoods(OrderChangeGoodsDTO obj) throws Exception;

    @POST
    @Path("/orderChangeGoods/importGoods")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response importGoods(Attachment attachments, @Context HttpServletRequest request) throws Exception;
    
    @POST
    @Path("/orderChangeGoods/remove")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteOrderChangeGoods(OrderChangeGoodsDTO obj);
    
    @POST
    @Path("/doSearch")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response doSearch(OrderChangeGoodsDTO obj);
    
    @POST
    @Path("/doSearchForAutoImport")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response doSearchForAutoImport(OrderChangeGoodsDetailDTO obj);
    
    @POST
    @Path("/orderChangeGoods/doSearchGoodsForImportReq")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response doSearchGoodsForImportReq(OrderChangeGoodsDTO obj);
    
    @POST
    @Path("/orderChangeGoods/getOrderChangeById")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getOrderChangeById(Long id);
    
    @POST
    @Path("/exportOrderChangeExcelError")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response exportOrderChangeExcelError(OrderChangeGoodsDetailDTO errorObj)throws Exception;
    
    @POST
    @Path("/doSearchForCheckAll")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response doSearchForCheckAll(OrderChangeGoodsDTO obj) throws Exception;
}
