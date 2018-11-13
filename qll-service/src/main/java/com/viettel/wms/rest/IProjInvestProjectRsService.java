/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.rest;

//import com.viettel.wms.dto.AppParamDTO;
import com.viettel.wms.dto.IProjInvestProjectDTO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author HungLQ9
 */
public interface IProjInvestProjectRsService {

    @GET
    @Path("/iProjInvestProject")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getIProjInvestProject();

    @GET
    @Path("/iProjInvestProject/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getIProjInvestProjectById(@PathParam("id") Long id);

    @PUT
    @Path("/iProjInvestProject/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateIProjInvestProject(IProjInvestProjectDTO obj);

    @POST
    @Path("/iProjInvestProject/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addIProjInvestProject(IProjInvestProjectDTO obj);

    @DELETE
    @Path("/iProjInvestProject/{id}/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteIProjInvestProject(@PathParam("id") Long id);
    
    @POST
    @Path("/iProjInvestProject/getForAutoComplete")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getForAutoComplete(IProjInvestProjectDTO obj);
    
    @POST
    @Path("/iProjInvestProject/doSearch")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response doSearch(IProjInvestProjectDTO obj);
    
    @POST
    @Path("/iProjInvestProject/getFromProjectCode")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getFromProjectCode(String code);
}
