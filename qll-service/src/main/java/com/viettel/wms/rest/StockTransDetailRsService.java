///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.viettel.wms.rest;
//
//import com.viettel.wms.dto.GoodsDTO;
//import com.viettel.wms.dto.OrderGoodsDTO;
//import com.viettel.wms.dto.StockTransDTO;
//import com.viettel.wms.dto.StockTransDetailDTO;
//
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
//public interface StockTransDetailRsService {
//
//    @GET
//    @Path("/stockTransDetail")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response getStockTransDetail();
//
//    @GET
//    @Path("/stockTransDetail/{id}")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response getStockTransDetailById(@PathParam("id") Long id);
//    
//
//    @PUT
//    @Path("/stockTransDetail/")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response updateStockTransDetail(StockTransDetailDTO obj);
//
//    @POST
//    @Path("/stockTransDetail/")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response addStockTransDetail(StockTransDetailDTO obj);
//
//    @DELETE
//    @Path("/stockTransDetail/{id}/")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response deleteStockTransDetail(@PathParam("id") Long id);
//    
//    @POST
//    @Path("/stockTransDetail/doSearchGoodsForImportNote")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response doSearchGoodsForImportNote(StockTransDetailDTO obj);
//    
//  
//    @POST
//    @Path("/stockTransDetail/getGoodsInfoFromAlternativeStockByCode")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response getGoodsInfoFromAlternativeStockByCode(String code);
//    
//    @POST
//    @Path("/stockTransDetail/doSearchGoodsForExportNote")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response doSearchGoodsForExportNote(StockTransDetailDTO obj);
//
//    @POST
//    @Path("/stockTransDetail/getStockTransDetail")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response getStockTransDetail(Long id);
//    
//    @POST
//    @Path("/stockTransDetail/addMoreExportNote")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response addExportNote(List<StockTransDetailDTO> os);
//    
//    @POST
//    @Path("/stockTransDetail/exportStockTrans")
//    @Consumes(MediaType.MULTIPART_FORM_DATA)
//    @Produces({MediaType.APPLICATION_JSON})
//    public Response exportStockTrans(Attachment attachments,@Context HttpServletRequest request)throws Exception;
//    
//    
//    @POST
//    @Path("/stockTransDetail/importUpdateStockTrans")
//    @Consumes(MediaType.MULTIPART_FORM_DATA)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response importUpdateStockTrans(Attachment attachments, @Context HttpServletRequest request) throws Exception;
//   
//    
//    @POST
//    @Path("/stockTransDetail/exportStockTransExcelError")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//	public Response exportStockTransExcelError(GoodsDTO errorObj) throws Exception;
//
//    
//    @POST
//    @Path("/stockTransDetail/getListStockTransDetail")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//	Response getListStockTransDetail(Long id);
//
//    @POST
//    @Path("/stockTransDetail/exportExcelTemplate")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//	public Response exportExcelTemplate(StockTransDetailDTO obj) throws Exception;
//}
