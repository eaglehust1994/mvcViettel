/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import com.viettel.erp.dto.EstimatesWorkItemsDTO;
import com.viettel.erp.dto.SettlementRightDTO;
import com.viettel.erp.dto.WorkItemsAcceptanceDTO;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**
 *
 * @author HungLQ9
 */
public interface EstimatesWorkItemsRsService {
	
	@POST
    @Path("/getWorkItemDone/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getWorkItemDone(EstimatesWorkItemsDTO obj);
	
	@POST
    @Path("/getWorkItemNotDone/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getWorkItemNotDone(EstimatesWorkItemsDTO obj);
	
	@POST
    @Path("/pauseWorkItem/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response pauseWorkItem(List<Long> listWorkItem);
	
	@POST
    @Path("/estimatesWorkItems/SearchAccept/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getEstimatesWorkItemsSearchAccept(EstimatesWorkItemsDTO obj);

	//tungpv
	@POST
    @Path("/estimatesWorkItems/search")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response doSearchEstimatesWorkItems(List<EstimatesWorkItemsDTO> criteria);
	
	@GET
    @Path("/estimatesWorkItems/getGrid/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAllEstimatesWorkItems(@PathParam("id") Long id);	
	
	@POST
    @Path("/estimatesWorkItems/searchWorkItemsAcceptanceId")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response doSearchByWorkItemsAcceptanceId(WorkItemsAcceptanceDTO dto);
	//end tungpv
	
    @GET
    @Path("/estimatesWorkItems")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getEstimatesWorkItems();

    @GET
    @Path("/estimatesWorkItems/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getEstimatesWorkItemsById(@PathParam("id") Long id);
   
    @POST
    @Path("/estimatesWorkItems/getAllEstimatesWorkInsideContract")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAllEstimatesWorkInsideContract(EstimatesWorkItemsDTO obj);
    
    @POST
    @Path("/estimatesWorkItems/getAllEstimatesWorkOutsideContract")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAllEstimatesWorkOutsideContract(EstimatesWorkItemsDTO obj);
    
    @POST
    @Path("/estimatesWorkItems/getAllEstimatesWorkContract")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAllEstimatesWorkContract(EstimatesWorkItemsDTO obj);
    
    @POST
    @Path("/estimatesWorkItems/put/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateEstimatesWorkItems(EstimatesWorkItemsDTO obj);

    @POST
    @Path("/estimatesWorkItems/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addEstimatesWorkItems(EstimatesWorkItemsDTO obj);

    
    @DELETE
    @Path("/estimatesWorkItems/{id}/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteEstimatesWorkItems(@PathParam("id") Long id);
	
	
	@POST
    @Path("/estimatesWorkItems/getWorkItem")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getWorkItem(EstimatesWorkItemsDTO obj) ;
	
	//minhpvn
	@POST
    @Path("/estimatesWorkItems/getWorkItemCongTrinh")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getWorkItemCongTrinh(EstimatesWorkItemsDTO obj) ;
	
	@POST
    @Path("/estimatesWorkItems/exportDataImport")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response exportDataImport(EstimatesWorkItemsDTO obj)  throws Exception;
	
	@POST
    @Path("/estimatesWorkItems/exportWorkItem")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response exportWorkItem(EstimatesWorkItemsDTO obj) throws Exception, InvalidFormatException, IOException;
	
	@POST
    @Path("/estimatesWorkItems/exportEstimateTable")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response exportEstimateTable(EstimatesWorkItemsDTO obj) throws Exception;
	//Nha
	@POST
    @Path("/estimatesWorkItems/getByConstruction")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getByConstructionId(Long id);
		
	@POST
    @Path("/estimatesWorkItems/getBieu4")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getBieu4(EstimatesWorkItemsDTO obj);
	
	@POST
	@Path("/estimatesWorkItems/getWorkItemDetail")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getWorkItemDetail(EstimatesWorkItemsDTO obj);
	
	@POST
    @Path("/estimatesWorkItems/getBieu2")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getBieu2(Long id);
	
	@POST
    @Path("/estimatesWorkItems/getBieu5")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getBieu5(Long id);
	
	@POST
    @Path("/estimatesWorkItems/exportFileDOCDetailForm4")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response exportFileDOCDetailForm4(EstimatesWorkItemsDTO dto);
	
	@POST
    @Path("/estimatesWorkItems/exportFilePDFDetailForm4")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response exportFilePDFDetailForm4(EstimatesWorkItemsDTO dto);
	
	@POST
    @Path("/estimatesWorkItems/updateStatus")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateStatus(Long estimatesWorkItemId);
	
}
