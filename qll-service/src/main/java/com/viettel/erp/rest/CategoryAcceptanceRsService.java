/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import com.viettel.erp.dto.AMaterialRecoveryMinutesModelDTO;
import com.viettel.erp.dto.AbComplementWorkDTO;
import com.viettel.erp.dto.CategoryAcceptanceDTO;
import com.viettel.erp.dto.ConstrGroundHandoverDTO;
import com.viettel.erp.dto.TitAziConstrAcceptDTO;
import com.viettel.erp.dto.approDTO;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author HungLQ9
 */
public interface CategoryAcceptanceRsService {

    @GET
    @Path("/categoryAcceptance/getAllCategoryAcceptance")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAllCategoryAcceptance();
    
    @GET
    @Path("/categoryAcceptance")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getCategoryAcceptance();

    @GET
    @Path("/categoryAcceptance/{constructId}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getCategoryAcceptanceById(@PathParam("constructId") Long constructId);
    
    @GET
    @Path("/categoryAcceptance/category/{constructId}/{contractId}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getCategoryById(@PathParam("constructId") Long constructId, @PathParam("contractId") Double contractId);
    
    @GET
    @Path("/categoryAcceptance/detail/{categoryAcceptanceId}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getCategoryAcceptanceByIdDetail(@PathParam("categoryAcceptanceId") Long categoryAcceptanceId);

    @POST
    @Path("/categoryAcceptance/put/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateCategoryAcceptance(CategoryAcceptanceDTO obj);
       
    @POST
    @Path("/categoryAcceptance/approval")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response approval(approDTO obj);

    @POST
    @Path("/categoryAcceptance/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addCategoryAcceptance(CategoryAcceptanceDTO obj);

    @DELETE
    @Path("/categoryAcceptance/{id}/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteCategoryAcceptance(@PathParam("id") Long id);
    
    @POST
    @Path("/categoryAcceptance/deleteListCategoryAcceptance/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteCategoryAcceptanceList(List<Long> lisItemCode);
    
    @DELETE
    @Path("/categoryAcceptance/{id}/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteCMarketPrice(@PathParam("id") Long id);
    
    @GET
	@Path("/categoryAcceptance/getTwoList/{id}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getTwoList(@PathParam("id") Long constructId);
	
	@POST
	@Path("/categoryAcceptance/exportFile")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response exportFile(CategoryAcceptanceDTO categoryDTO);
    
	@POST
    @Path("categoryAcceptance/exportList/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response exportList(List<Long> data);

	@POST
	@Path("/categoryAcceptance/exportPDF")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response exportPDF(CategoryAcceptanceDTO data);
	
	@POST
	@Path("/categoryAcceptance/exportDOC")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response exportDOC(CategoryAcceptanceDTO data);
    
	@POST
    @Path("/categoryAcceptance/exportWordListDoc")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response exportListDocCategoryAcceptence(List<Long> data);
	
	@POST
    @Path("/categoryAcceptance/exportWordListpdf")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response exportListPDFCategoryAcceptence(List<Long> data);
//    @POST
//    @Path("/categoryAcceptance/approveListCategoryAcceptance/")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response approvalCategoryAcceptanceList(List<Long> lisItemCode);
}
