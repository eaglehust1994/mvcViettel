/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import com.viettel.erp.dto.ConstrWorkLogsDTO;
import com.viettel.erp.dto.TitAziConstrAcceptDTO;
import com.viettel.erp.dto.TitAziConstrAcceptListDTO;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author HungLQ9
 */
public interface TitAziConstrAcceptListRsService {
	
	
	
	@GET
    @Path("/titAziConstrAcceptList/getListByTitAziConstrAcceptId/{titAziConstrAcceptId}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getListTitAziConstrAcceptById(@PathParam("titAziConstrAcceptId") Long titAziConstrAcceptId);
	
	@POST
	@Path("/titAziConstrAcceptList/deleteList/")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteList(List<String> listString);
	
    @GET
    @Path("/titAziConstrAcceptList")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getTitAziConstrAcceptList();

    @GET
    @Path("/titAziConstrAcceptList/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getTitAziConstrAcceptListById(@PathParam("id") Long id);

    @POST
    @Path("/titAziConstrAcceptList/put/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateTitAziConstrAcceptList(TitAziConstrAcceptListDTO obj);

    @POST
    @Path("/titAziConstrAcceptList/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addTitAziConstrAcceptList(TitAziConstrAcceptListDTO obj);
    
    @POST
    @Path("/titAziConstrAcceptList/saveList")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response saveListTitAziConstrAcceptList(List<TitAziConstrAcceptListDTO> obj);

    @DELETE
    @Path("/titAziConstrAcceptList/{id}/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteTitAziConstrAcceptList(@PathParam("id") Long id);
}
