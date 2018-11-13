///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.viettel.wms.rest;
//
//import java.util.List;
//
//import com.viettel.wms.dto.CommonDTO;
//import com.viettel.wms.dto.GoodsDTO;
//import com.viettel.wms.dto.OrderDTO;
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
//public interface OrderRsService {
//
//    @GET
//    @Path("/order")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response getOrder();
//
//    @GET
//    @Path("/order/{id}")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response getOrderById(@PathParam("id") Long id);
//
//    @PUT
//    @Path("/order/")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response updateOrder(OrderDTO obj);
//
//    @POST
//    @Path("/order/")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response addOrder(OrderDTO obj);
//
//    @DELETE
//    @Path("/order/{id}/")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response deleteOrder(@PathParam("id") Long id);
//    
//    @POST
//    @Path("/order/doSearchImportRequirement")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response doSearchImportRequirement(OrderDTO obj);
//    
//    @POST
//    @Path("/order/doSearchForCreateImportNote")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response doSearchForCreateImportNote(OrderDTO obj);
//    
//    
//    @POST
//    @Path("/order/doSearchExportRequirement")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response doSearchExportRequirement (OrderDTO obj) throws Exception ;
//
//    @POST
//    @Path("/order/getOrderDetail")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response getOrderDetail(Long id);
//        
//  
//    
//    @POST
//    @Path("/order/getOderByShipment")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response getOderByShipment(OrderDTO obj);
//    
//    @POST
//    @Path("/order/saveImportReq")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response saveImportReq(OrderDTO obj) throws Exception;
//    
//    @POST
//    @Path("/order/removerOrder")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response removeOrder(OrderDTO obj);
//    
//    @POST
//    @Path("/order/rejectOrder")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response rejectOrder(OrderDTO obj);
//    
//    @POST
//    @Path("/order/exportSerial")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response exportSerial(OrderDTO obj) throws Exception;
//    
//    @POST
//    @Path("/order/updateImportReq")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response updateImportReq(OrderDTO obj)throws Exception;
//    
//    @POST
//    @Path("/order/updateExportReq")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response updateExportReq(OrderDTO obj);
//    
//    @POST
//    @Path("/order/repExportReq")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response repExportReq(OrderDTO obj);
//    
//    @POST
//    @Path("/order/getOrder")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response getOrder(Long id);
//    
//    @POST
//    @Path("/order/removeExportOrder")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response removeExportOrder(OrderDTO obj);
//
//    @POST
//    @Path("/order/importGoods")
//    @Consumes(MediaType.MULTIPART_FORM_DATA)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response importGoods(Attachment attachments, @Context HttpServletRequest request) throws Exception;
//    
//    @POST
//    @Path("/order/exportExcelTemplate")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//	public Response exportExcelTemplate() throws Exception;
//    
//    @POST
//    @Path("/order/exportExcelError")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//	public Response exportExcelError(GoodsDTO errorObj) throws Exception;
//    
//    @POST
//    @Path("/order/exportExcelErrorXK")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//	public Response exportExcelErrorXK(GoodsDTO errorObj) throws Exception;
//    
//    @POST
//    @Path("/order/getGoodsDetailByOrderId")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//	public Response getGoodsDetailByOrderId(OrderDTO obj) throws Exception;
//    
//    @POST
//    @Path("/order/getAllImportRequirement")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//	public Response getAllImportRequirement(OrderDTO obj) throws Exception;
//    @POST
//    @Path("/order/getAllExportStatement")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//	public Response getAllExportStatement(OrderDTO obj) throws Exception;
//    
//
//    
//    @POST
//    @Path("/order/getOderByShipmentId")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//	public Response getOderByShipmentId(OrderDTO obj) throws Exception;
//}
