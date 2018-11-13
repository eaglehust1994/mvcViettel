///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.viettel.wms.rest;
//
//import com.viettel.wms.dto.GoodsDTO;
//import com.viettel.wms.dto.ShipmentDTO;
//import com.viettel.wms.dto.ShipmentGoodsDTO;
//import java.util.List;
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
//public interface ShipmentRsService {
//
//    @GET
//    @Path("/shipment")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response getShipment();
//
//    @GET
//    @Path("/shipment/{id}")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response getShipmentById(@PathParam("id") Long id);
//
//    @POST
//    @Path("/shipment/update")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response updateShipment(ShipmentDTO obj) throws Exception;
//
//    @POST
//    @Path("/shipment/add")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response addShipment(ShipmentDTO obj) throws Exception;
//    
//    @POST
//    @Path("/shipment/addList")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response addShipmentGoods(List<ShipmentGoodsDTO> obj) throws Exception;
//    
//    @POST
//    @Path("/shipment/remove")
//    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//    public Response remove(ShipmentDTO obj) throws Exception;
//    
//    @POST
//	@Path("/shipment/doSearch")
//	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	public Response doSearch(ShipmentDTO obj);
//    
//    @POST
//   	@Path("/shipment/doSearchQuantity")
//   	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//   	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//   	public Response doSearchQuantity(ShipmentDTO obj) throws Exception;
//    
//    @POST
//   	@Path("/shipment/doSearchPrice")
//   	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//   	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//   	public Response doSearchPrice(ShipmentDTO obj) throws Exception;
//    
//    @POST
//	@Path("/shipment/searchListShipmentCode")
//	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	public Response searchListShipmentCode(String code);
//    
//    @POST
//    @Path("/shipment/importGoods")
//    @Consumes(MediaType.MULTIPART_FORM_DATA)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response importGoods(Attachment attachments, @Context HttpServletRequest request) throws Exception;
//    
//    @POST
//    @Path("/shipment/exportExcelError")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//	public Response exportExcelError(GoodsDTO errorObj) throws Exception;
//    
//    @POST
//    @Path("/shipment/exportExcelTemplate")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//	public Response exportExcelTemplate() throws Exception;
//    
//    @POST
//    @Path("/shipment/updateShipmentGoods")
//    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//    public Response updateShipmentGoods(ShipmentDTO obj) throws Exception;
//    
//    @POST
//    @Path("/shipment/saveDetailShipment")
//    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//    public Response saveDetailShipment(ShipmentDTO obj) throws Exception;
//    
//
//    
//    
//}
