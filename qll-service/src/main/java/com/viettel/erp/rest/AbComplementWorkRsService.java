/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import com.viettel.erp.dto.AbComplementWorkDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author HungLQ9
 */
public interface AbComplementWorkRsService {
	
	
	//Nha
	
	@POST
	@Path("/abComplementWork/exportPDFBieu2")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response exportPDFBieu2(Long id);
	
	@POST
	@Path("/abComplementWork/exportDOCBieu2")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response exportDOCBieu2(Long id);
	
	@POST
	@Path("/abComplementWork/exportPDFBieu3")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response exportPDFBieu3(Long id);
	
	@POST
	@Path("/abComplementWork/exportDOCBieu3")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response exportDOCBieu3(Long id);
	
	@POST
    @Path("/abComplementWork/thongtinchung")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getThongtinchung(Long id);

    @GET
    @Path("/abComplementWork")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAbComplementWork();

    @GET
    @Path("/abComplementWork/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAbComplementWorkById(@PathParam("id") Long id);

    @POST
    @Path("/abComplementWork/put/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateAbComplementWork(AbComplementWorkDTO obj);

    @POST
    @Path("/abComplementWork/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addAbComplementWork(AbComplementWorkDTO obj);

    @DELETE
    @Path("/abComplementWork/{id}/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteAbComplementWork(@PathParam("id") Long id);
    
    
    @POST
    @Path("/abComplementWork/constructIdsignCA/{constructId}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response signCA(@PathParam("constructId") Long constructId);
    
    @POST
    @Path("/abComplementWork/checkSave3/{constructId}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response checkSave3(@PathParam("constructId") Long constructId);
}
