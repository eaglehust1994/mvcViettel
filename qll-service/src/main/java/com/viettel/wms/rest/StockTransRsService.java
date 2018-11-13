/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.rest;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

//import com.viettel.wms.dto.AppParamDTO;
import com.viettel.wms.dto.CommonDTO;
import com.viettel.wms.dto.KpiStorageAmountDTO;
import com.viettel.wms.dto.StockTransDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author HungLQ9
 */
public interface StockTransRsService {

    @GET
    @Path("/stockTrans")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getStockTrans();

    @GET
    @Path("/stockTrans/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getStockTransById(@PathParam("id") Long id);

    @PUT
    @Path("/stockTrans/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateStockTrans(StockTransDTO obj);
 
    @POST
    @Path("/stockTrans/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addStockTrans(StockTransDTO obj);

    @DELETE
    @Path("/stockTrans/{id}/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteStockTrans(@PathParam("id") Long id);
    
    @POST
    @Path("/stockTrans/doSearchImportNote")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response doSearchImportNote(StockTransDTO obj);
    
    @POST
    @Path("/stockTrans/getStockTransDetail")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getStockTransDetail(Long id);
    
    @POST
    @Path("/stockTrans/saveImportNote")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response saveImportNote(StockTransDTO obj) throws Exception;
    
    @POST
    @Path("/stockTrans/saveInforImport")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response saveInforImport(StockTransDTO obj) throws Exception;
    
    @POST
    @Path("/stockTrans/doSearchExport")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response doSearchExport(StockTransDTO obj) throws Exception;
   
    @POST
    @Path("/stockTrans/getStockTransForAutoComplete")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getStockTransForAutoComplete(StockTransDTO obj);

    @POST
    @Path("/stockTrans/saveAndRealImportNote")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response saveAndRealImportNote(StockTransDTO obj) throws Exception;
    
    @POST
    @Path("/stockTrans/realImportNote")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response realImportNote(StockTransDTO obj) throws Exception;
    
    @POST
    @Path("/stockTrans/updateImportNote")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateImportNote(StockTransDTO obj);
    
    @POST
    @Path("/stockTrans/doSearchExportStatement")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response doSearchExportStatement(StockTransDTO obj)throws Exception;
    
    @POST
    @Path("/stockTrans/getGoodsInfoFromAlternativeStockCode")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getGoodsInfoFromAlternativeStockCode(String code);
    
    @POST
    @Path("/stockTrans/getStockByOrder")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getStockByOrder(StockTransDTO obj);
    
    
    @POST
    @Path("/stockTrans/removeStockTrans")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response removeStockTrans(StockTransDTO obj);
    
    @POST
    @Path("/stockTrans/removeExportStockTrans")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response removeExportStockTrans(StockTransDTO obj);
    
    @POST
   	@Path("/stockTrans/realExportNote")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
   	public Response realExportNote(StockTransDTO obj) throws Exception;
   
    @POST
    @Path("/stockTrans/updateImportStockTran")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateImportStockTran(StockTransDTO obj) throws Exception;
    
    @POST
    @Path("/stockTrans/getAllImportNote")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAllImportNote(StockTransDTO obj);
    
    
    @POST
    @Path("/stockTrans/getAllExportManagement")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAllExportManagement(StockTransDTO obj);
    
    
    @POST
    @Path("/stockTrans/exportExcelBKVT")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response exportExcelBKVT(List<Long> ids) throws Exception;
    
}
