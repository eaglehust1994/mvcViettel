/*
\ * To change this license header, choose License Headers in Project Properties.
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

import com.viettel.erp.dto.DistanceUnloadConstrMinutesDTO;
import com.viettel.erp.dto.approDTO;

/**
 *
 * @author HungLQ9
 */
public interface DistanceUnloadConstrMinutesRsService {
	

    @GET
    @Path("/distanceUnloadConstrMinutes")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getDistanceUnloadConstrMinutes();
    
   /* phongpv*/
    @GET 
    @Path("/distanceUnloadConstrMinutes/getListCR/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getListCR(@PathParam("id") Long constructId);
    
    
    @POST 
    @Path("/distanceUnloadConstrMinutes/getAllC")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getDistanceUnloadConstrMinutes(DistanceUnloadConstrMinutesDTO obj);
    
    @GET
    @Path("/distanceUnloadConstrMinutes/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getDistanceUnloadConstrMinutesById(@PathParam("id") Long id);

    @POST
    @Path("/distanceUnloadConstrMinutes/put/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateDistanceUnloadConstrMinutes(DistanceUnloadConstrMinutesDTO obj);

    @POST
    @Path("/distanceUnloadConstrMinutes/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addDistanceUnloadConstrMinutes(DistanceUnloadConstrMinutesDTO obj);

    @DELETE
    @Path("/distanceUnloadConstrMinutes/{id}/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteDistanceUnloadConstrMinutes(@PathParam("id") Long id) throws Exception ;
    
    @POST
    @Path("/distanceUnloadConstrMinutes/exportWord")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response exportDistanceUnloadConstrMinutes(DistanceUnloadConstrMinutesDTO obj);
    
    @POST
    @Path("/distanceUnloadConstrMinutes/exportWordList")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response exportListDistanceUnloadConstrMinutes(List<Long> data);
    
    @POST
    @Path("/distanceUnloadConstrMinutes/exportDoc")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response exportDocDistanceUnloadConstrMinutes(DistanceUnloadConstrMinutesDTO obj);
    
    @POST
    @Path("/distanceUnloadConstrMinutes/exportDocList")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response exportListDocDistanceUnloadConstrMinutes(List<Long> data);
    
    @POST
    @Path("/distanceUnloadConstrMinutes/appro")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response appro(approDTO obj);
    
    @POST
	@Path("/distanceUnloadConstrMinutes/updateIsActive")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateIsActive(List<Long> listId);
}
