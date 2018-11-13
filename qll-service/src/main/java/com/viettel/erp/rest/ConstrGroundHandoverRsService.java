/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.viettel.erp.dto.ConstrGroundHandoverDTO;
import com.viettel.erp.dto.approDTO;

/**
 *
 * @author HungLQ9
 */
public interface ConstrGroundHandoverRsService {

	// ChuongNV
	@POST
	@Path("/constrGroundHandover/getAllConstrGroundHandover")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getAllConstrGroundHandover(ConstrGroundHandoverDTO dto);
	
	//End ChuongNV
	
	@POST
	@Path("/constrGroundHandover/appro")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response appro(approDTO obj);
	
    @GET
    @Path("/constrGroundHandover")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getConstrGroundHandover();

    @GET
    @Path("/constrGroundHandover/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getConstrGroundHandoverById(@PathParam("id") Long id);

    @POST
    @Path("/constrGroundHandover/put/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateConstrGroundHandover(ConstrGroundHandoverDTO obj);

    @POST
    @Path("/constrGroundHandover/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addorupdateConstrGroundHandover(ConstrGroundHandoverDTO obj);

    @DELETE
    @Path("/constrGroundHandover/{id}/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteConstrGroundHandover(@PathParam("id") Long id);
    
    
    @POST
	@Path("/constrGroundHandover/deleteList/put/")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteList(List<Long> ids);
    
    
    @POST
    @Path("/constrGroundHandover/exportPDF")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response exportPDF(ConstrGroundHandoverDTO obj);
    

    @POST
    @Path("/constrGroundHandover/deleteOne")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteOne(Long id);
}
