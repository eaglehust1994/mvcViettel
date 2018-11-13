/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.rest;

import java.util.List;

import com.viettel.wms.dto.CommonDTO;
import com.viettel.wms.dto.SignVofficeDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author HungLQ9
 */
public interface SignVofficeRsService {

    @GET
    @Path("/signVoffice")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getSignVoffice();

    @GET
    @Path("/signVoffice/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getSignVofficeById(@PathParam("id") Long id);

    @PUT
    @Path("/signVoffice/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateSignVoffice(SignVofficeDTO obj);

    @POST
    @Path("/signVoffice/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addSignVoffice(SignVofficeDTO obj);

    @DELETE
    @Path("/signVoffice/{id}/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteSignVoffice(@PathParam("id") Long id);
    
    
    @POST
    @Path("/signVoffice/getRoleByEmail")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getRoleByEmail(CommonDTO obj);
    
    
    
    @POST
    @Path("/signVoffice/getDataSign")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getDataSign(CommonDTO obj);
	
	 @POST
    @Path("/signVoffice/getDetail")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getDetail(CommonDTO obj);
	 
	 
	 	@POST
	    @Path("/signVoffice/test")
	    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	    public Response test(Long id) throws Exception;
}
