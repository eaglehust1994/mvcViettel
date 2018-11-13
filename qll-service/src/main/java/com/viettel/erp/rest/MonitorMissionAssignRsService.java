/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import com.viettel.erp.dto.DistanceUnloadConstrMinutesDTO;
import com.viettel.erp.dto.MonitorMissionAssignDTO;
import com.viettel.erp.dto.approDTO;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author HungLQ9
 */
public interface MonitorMissionAssignRsService {
	
	@POST
    @Path("/appro/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response appro(approDTO dto);

    @GET
    @Path("/monitorMissionAssign")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getMonitorMissionAssign();

    @GET
    @Path("/monitorMissionAssign/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getMonitorMissionAssignById(@PathParam("id") Long id);
    
    @GET
    @Path("/monitorMissionAssign/folder")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getFolder();

    @POST
    @Path("/monitorMissionAssign/put/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateMonitorMissionAssign(MonitorMissionAssignDTO obj) throws Exception;

    @POST
    @Path("/monitorMissionAssign/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addMonitorMissionAssign(MonitorMissionAssignDTO obj) throws Exception;
    
    @POST
	@Path("/monitorMissionAssign/updateIsActive/")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateIsActive(List<Long> monitorMissionAssignId);
    
    @POST
    @Path("/monitorMissionAssign/getMonitorMissionAssign")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getMonitorMissionAssign(MonitorMissionAssignDTO obj) throws Exception;

    @DELETE
    @Path("/monitorMissionAssign/{id}/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteMonitorMissionAssign(@PathParam("id") Long id);
    
    @POST
    @Path("/monitorMissionAssign/exportWord")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response exportMonitorMissionAssign(MonitorMissionAssignDTO obj) throws Exception;
    
    @POST
    @Path("/monitorMissionAssign/exportWordList")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response exportListMonitorMissionAssign(List<Long> data);
    
    @POST
    @Path("/monitorMissionAssign/exportDoc")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response exportDocMonitorMissionAssign(MonitorMissionAssignDTO obj) throws Exception;
    
    @POST
    @Path("/monitorMissionAssign/exportDocList")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response exportListDocMonitorMissionAssign(List<Long> data);
}
