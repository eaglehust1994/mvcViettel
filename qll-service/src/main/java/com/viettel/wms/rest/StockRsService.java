///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.viettel.wms.rest;
//
//import com.viettel.wms.dto.AppParamDTO;
//import com.viettel.wms.dto.GoodsDTO;
//import com.viettel.wms.dto.StockDTO;
//
//import javax.ws.rs.Consumes;
//import javax.ws.rs.GET;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//
///**
// *
// * @author HungLQ9
// */
//public interface StockRsService {
//
//	
//	
//	@GET
//	@Path("/getByUserId/{userId}")
//	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	public Response getByUserId(@PathParam("userId") Long userId);
//
//	@POST
//	@Path("/doSearch")
//	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	public Response doSearch(StockDTO obj);
//	
//
//	@POST
//	@Path("/getForAutoComplete")
//	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	public Response getForAutoComplete(AppParamDTO obj);
//	 
//	@POST
//	@Path("/getForAutoCompleteStock")
//	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	public Response getForAutoCompleteStock(StockDTO obj);
//	
//	@POST
//	@Path("/getForAutoCompleteStockDomain")
//	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	public Response getForAutoCompleteStockDomain(StockDTO obj);
//	
//	@POST
//	@Path("/remove")
//	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	public Response remove(StockDTO obj);
//
//	@POST
//	@Path("/add")
//	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	public Response add(StockDTO obj);
//
//	@POST
//	@Path("/update")
//	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	public Response update(StockDTO obj);
//
//    @GET
//    @Path("/stock")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response getStock();
//    
//    @POST
//	@Path("/getStocksForAutocomplete")
//	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	public Response getStocksForAutocomplete(StockDTO obj);
//    
//    @POST
//	@Path("/getStocksForAutocompleteDropDown")
//	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	public Response getStocksForAutocompleteDropDown(StockDTO obj);
//    
//    @POST
//	@Path("/doSearchStockInPopUp")
//	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	public Response doSearchStockInPopUp(StockDTO obj);
//    
//    @POST
//   	@Path("/doSearchStockInPopUpDomain")
//   	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//   	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//   	public Response doSearchStockInPopUpDomain(StockDTO obj);
//    
//    @POST
//	@Path("/doSearchGoods")
//	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	public Response doSearchGoods(GoodsDTO obj);
//    
//    @POST
//    @Path("/doSearchGoodsInPopup")
//    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//    public Response doSearchGoodsInPopup(GoodsDTO obj);
//    
//    
//    @POST
//	@Path("/getListByNameOrCode")
//	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	public Response getListByNameOrCode(StockDTO obj);
//    
//    @POST
//   	@Path("/getChangeAreaByStock")
//   	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//   	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//   	public Response getChangeAreaByStock(StockDTO obj);
//
//    @POST
//   	@Path("/saveListStockCell")
//   	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//   	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//   	public Response saveListStockCell(StockDTO obj);
//    
//	
//}
