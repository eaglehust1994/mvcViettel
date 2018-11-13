/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import com.viettel.erp.dto.ConstrCompleteRecordsMapCriteriaDTO;
import com.viettel.erp.dto.ConstrCompleteRecordsMapDTO;
import com.viettel.erp.dto.TheSignCADTO;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author HungLQ9
 */
public interface ConstrCompleteRecordsMapRsService {

	
	@POST
	@Path("/getNotify")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getNotify(Long userId);
	
	// ChuongNV
	//the SignCA
	@POST
	@Path("/constrCompleteRecordsMap/updateTotal")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateTotal(TheSignCADTO dto);
	
	//The Approval
	@POST
	@Path("/constrCompleteRecordsMap/updateTotalApproval")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateTotalApproval(TheSignCADTO dto);
	// End ChuongNV

	@GET
	@Path("/constrCompleteRecordsMap")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getConstrCompleteRecordsMap();

	@GET
	@Path("/constrCompleteRecordsMap/{id}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getConstrCompleteRecordsMapById(@PathParam("id") Long id);

	@POST
	@Path("/constrCompleteRecordsMap/put/")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateConstrCompleteRecordsMap(ConstrCompleteRecordsMapDTO obj);

	@POST
	@Path("/constrCompleteRecordsMap/")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response addConstrCompleteRecordsMap(ConstrCompleteRecordsMapDTO obj);

	@DELETE
	@Path("/constrCompleteRecordsMap/{id}/")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteConstrCompleteRecordsMap(@PathParam("id") Long id);

	@GET
	@Path("/constrCompleteRecordsMap/getByConstructionId/{id}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getByConstructionId(@PathParam("id") Long id);


    @POST
    @Path("/constrCompleteRecordsMap/filter")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response filter(ConstrCompleteRecordsMapCriteriaDTO criteria);
    
    @POST
	@Path("/exportFileExcell/")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response exportFileExcell(List<ConstrCompleteRecordsMapDTO> obj);

}
