/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import com.viettel.erp.dto.AMaterialRecoveryListDTO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author HungLQ9
 */
public interface AMaterialRecoveryListRsService {

	@GET
	@Path("/aMaterialRecoveryList")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getAMaterialRecoveryList();

	@GET
	@Path("/aMaterialRecoveryList/findByConstructId/{id}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response findByConstructId(@PathParam("id") Long constructId);

	@GET
	@Path("/aMaterialRecoveryList/{id}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getAMaterialRecoveryListById(@PathParam("id") Long id);

	@POST
	@Path("/aMaterialRecoveryList/put/")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateAMaterialRecoveryList(AMaterialRecoveryListDTO obj);

	@POST
	@Path("/aMaterialRecoveryList/")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response addAMaterialRecoveryList(AMaterialRecoveryListDTO obj);

	@DELETE
	@Path("/aMaterialRecoveryList/{id}/")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteAMaterialRecoveryList(@PathParam("id") Long id);
}
