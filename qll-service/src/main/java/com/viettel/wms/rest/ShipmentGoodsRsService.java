///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.viettel.wms.rest;
//
//import java.util.List;
//
//import com.viettel.wms.dto.AppParamDTO;
//import com.viettel.wms.dto.GoodsDTO;
//import com.viettel.wms.dto.ShipmentGoodsDTO;
//import com.viettel.wms.dto.TaxDTO;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.ws.rs.*;
//import javax.ws.rs.core.Context;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//
//import org.apache.cxf.jaxrs.ext.multipart.Attachment;
//
///**
// *
// * @author HungLQ9
// */
//public interface ShipmentGoodsRsService {
//
//    @GET
//    @Path("/shipmentGoods")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response getShipmentGoods();
//
//    @GET
//    @Path("/shipmentGoods/{id}")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response getShipmentGoodsById(@PathParam("id") Long id);
//
//    @PUT
//    @Path("/shipmentGoods/")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response updateShipmentGoods(ShipmentGoodsDTO obj);
//
//    @POST
//    @Path("/shipmentGoods/")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response addShipmentGoods(ShipmentGoodsDTO obj);
//
//    @POST
//    @Path("/shipmentGoods/add")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response addShipmentGoodsList (List<ShipmentGoodsDTO> obj);
//    
//    @DELETE
//    @Path("/shipmentGoods/{id}/")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response deleteShipmentGoods(@PathParam("id") Long id);
//    
//    @POST
//    @Path("/shipmentGoods/doSearchMap")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response doSearchMap(ShipmentGoodsDTO obj);
//    
//    @POST
//	@Path("/getForAutoComplete")
//	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	public Response getForAutoComplete(ShipmentGoodsDTO obj);
//    
//    @POST
//    @Path("/shipmentGoods/getGoodsInfoByCode")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response getGoodsInfoByCode(String code);
//    
//    @POST
//    @Path("/shipmentGoods/exportExcelTemplate")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//	public Response exportExcelTemplate(ShipmentGoodsDTO obj) throws Exception;
//    
//    @POST
//    @Path("/shipmentGoods/importQuantitative/{id}")
//    @Consumes(MediaType.MULTIPART_FORM_DATA)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response importQuantitative(Attachment attachments, @Context HttpServletRequest request,@PathParam("id") Long id) throws Exception;
//    
//    @POST
//    @Path("/shipmentGoods/exportExcelError")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//	public Response exportExcelError(GoodsDTO errorObj) throws Exception;
//    
//    @POST
//    @Path("/shipmentGoods/remove")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//	public Response delete(ShipmentGoodsDTO obj) throws Exception;
//    
//    
//    @POST
//   	@Path("/shipmentGoods/getShipmentGoodsPrice")
//   	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//   	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//   	public Response getShipmentGoodsPrice(ShipmentGoodsDTO obj);
//
//}
