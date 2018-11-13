/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import com.viettel.erp.dto.BMaterialAcceptanceDTO;
import com.viettel.erp.dto.QualityCableMeaReportModelDTO;
import com.viettel.erp.dto.SettlementRightDTO;
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
public interface BMaterialAcceptanceRsService {

    @GET
    @Path("/bMaterialAcceptance")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getBMaterialAcceptance();

    @POST
    @Path("/bMaterialAcceptance/findByConstructId")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findByConstructId( BMaterialAcceptanceDTO data);
    
    @GET
    @Path("/bMaterialAcceptance/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getBMaterialAcceptanceById(@PathParam("id") Long id);

    @POST
    @Path("/bMaterialAcceptance/put/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateBMaterialAcceptance(BMaterialAcceptanceDTO obj);


    
	@POST
	@Path("/bMaterialAcceptance/deleteResult/")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteResult(List<String> listString);
    
	//remove bma list
	@POST
	@Path("/bMaterialAcceptance/deleteListEntity/")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteListEntity(List<Long> listLong);
	
    @POST
    @Path("/bMaterialAcceptance/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addBMaterialAcceptance(BMaterialAcceptanceDTO obj);
    
    @POST
    @Path("/bMaterialAcceptance/exportList/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response exportList(List<Long> data);
    
    
    @POST
    @Path("/bMaterialAcceptance/exportFilePDFDetail")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response exportFilePDFDetail(BMaterialAcceptanceDTO rightDTO);
    
    @POST
    @Path("/bMaterialAcceptance/exportFileDOCDetail")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response exportFileDOCDetail(BMaterialAcceptanceDTO rightDTO);
    
    // export doc
    @POST
    @Path("/bMaterialAcceptance/exportListDoc/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response exportListDoc(List<Long> data);
    
    @POST
    @Path("/bMaterialAcceptance/appro")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response appro(approDTO obj);
    
    
    @DELETE
    @Path("/bMaterialAcceptance/{id}/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteBMaterialAcceptance(@PathParam("id") Long id);
    
    @POST
    @Path("/bMaterialAcceptance/downloadTempleBmaterial")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	Response downloadTempleBmaterial(BMaterialAcceptanceDTO obj) throws Exception;
}
