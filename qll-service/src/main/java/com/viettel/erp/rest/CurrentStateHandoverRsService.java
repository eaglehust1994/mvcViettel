/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import com.viettel.erp.dto.CurrentStateHandoverDTO;
import com.viettel.erp.dto.CurrentStateHandoverExtraDTO;
import com.viettel.erp.dto.approDTO;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author HungLQ9
 */
public interface CurrentStateHandoverRsService {

    @GET
    @Path("/currentStateHandover")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getCurrentStateHandover();
    
    @GET
    @Path("/currentStateHandover/listCurrentStateHandover/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getListCurrentStateHandoverByContructId(@PathParam("id") Long id);
    
    @GET
    @Path("/currentStateHandover/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getCurrentStateHandoverById(@PathParam("id") Long id);

    @POST
    @Path("/currentStateHandover/put/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateCurrentStateHandover(CurrentStateHandoverDTO obj);
    
    @POST
    @Path("/currentStateHandover/deleteList/post")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteCurrentStateHandoverByListId(List<Long> obj);
    
    @POST
    @Path("/currentStateHandover/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addCurrentStateHandover(CurrentStateHandoverDTO obj);
    
    @POST
    @Path("/currentStateHandover/export")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response exportCurrentStateHandover(Long id);
    
    @POST
    @Path("/currentStateHandover/exportDoc")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response exportCurrentStateHandoverDoc(Long id);
    
    @POST
    @Path("/currentStateHandover/approval")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response theApproval(Long id) throws Exception;
    
    @POST
    @Path("/currentStateHandover/appro")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response appro(approDTO obj);
    
    @POST
    @Path("/currentStateHandover/exportList")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response exportCurrentStateHandoverList(List<Long> listId);
    
    @POST
    @Path("/currentStateHandover/exportListDOC")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response exportCurrentStateHandoverDOCList(List<Long> listId);
    
    @DELETE
    @Path("/currentStateHandover/{id}/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteCurrentStateHandover(@PathParam("id") Long id);
}
