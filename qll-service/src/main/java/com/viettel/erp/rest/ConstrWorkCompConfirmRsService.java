/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import com.viettel.erp.dto.ConstrWorkCompConfirmDTO;
import com.viettel.erp.dto.ConstrWorkCompConfirmExportDTO;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author HungLQ9
 */
public interface ConstrWorkCompConfirmRsService {

    @GET
    @Path("/constrWorkCompConfirm")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getConstrWorkCompConfirm();

    @GET
    @Path("/constrWorkCompConfirm/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getConstrWorkCompConfirmById(@PathParam("id") Long id);

    @GET
    @Path("/constrWorkCompConfirm/constructId/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getConstrWorkCompConfirmByConstrId(@PathParam("id") Long id);
    
    @POST
    @Path("/constrWorkCompConfirm/deleteList/put/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteListConstrWorkCompConfirm(List<Long> obj);

    @POST
    @Path("/constrWorkCompConfirm/put/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateConstrWorkCompConfirm(ConstrWorkCompConfirmDTO obj);
    
    @POST
    @Path("/constrWorkCompConfirm/exportList")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response exportZipConstrWorkCompConfirm(List<Long> listid);
    
    @POST
    @Path("/constrWorkCompConfirm/exportListDOC")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response exportZipConstrWorkCompConfirmDOC(List<Long> listid);
    
    @POST
    @Path("/constrWorkCompConfirm/exportOne")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response exportConstrWorkCompConfirm(Long id);
    
    @POST
    @Path("/constrWorkCompConfirm/exportDoc")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response exportConstrWorkCompConfirmDoc(Long id);
    
    @POST
    @Path("/constrWorkCompConfirm/signCA")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response signCA(Long id) throws Exception;
    
    @POST
    @Path("/constrWorkCompConfirm/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addConstrWorkCompConfirm(ConstrWorkCompConfirmDTO obj);

    @DELETE
    @Path("/constrWorkCompConfirm/{id}/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteConstrWorkCompConfirm(@PathParam("id") Long id);
}
