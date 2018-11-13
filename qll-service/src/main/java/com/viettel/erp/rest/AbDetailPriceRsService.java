/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import com.viettel.erp.dto.AbComplementWorkDTO;
import com.viettel.erp.dto.AbDetailPriceDTO;

import java.io.IOException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**
 *
 * @author HungLQ9
 */
public interface AbDetailPriceRsService {

    @GET
    @Path("/abDetailPrice")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAbDetailPrice();

    @GET
    @Path("/abDetailPrice/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAbDetailPriceById(@PathParam("id") Long id);

    @POST
    @Path("/abDetailPrice/put/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateAbDetailPrice(AbDetailPriceDTO obj);

    @POST
    @Path("/abDetailPrice/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addAbDetailPrice(AbDetailPriceDTO obj);

    @DELETE
    @Path("/abDetailPrice/{id}/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteAbDetailPrice(@PathParam("id") Long id);
    
    @POST
	@Path("/abDetailPrice/exportPDFBieu5")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response exportPDFBieu5(AbDetailPriceDTO data);
	
	@POST
	@Path("/abDetailPrice/exportDOCBieu5")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response exportDOCBieu5(AbDetailPriceDTO data);
	
	@POST
    @Path("/abDetailPrice/checkSave5/{constructId}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response checkSave5(@PathParam("constructId") Long constructId);
	
	@POST
    @Path("/abDetailPrice/constructIdsignCA/{constructId}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response signCA(@PathParam("constructId") Long constructId);
	
	@POST
    @Path("/abDetailPrice/getBieu5")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getBieu5(Long id);
	
	
    
}
