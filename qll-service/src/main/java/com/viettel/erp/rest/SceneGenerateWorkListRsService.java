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

import com.viettel.erp.dto.EstimatesWorkItemsDTO;
import com.viettel.erp.dto.SceneGenerateWorkDTO;
import com.viettel.erp.dto.SceneGenerateWorkListDTO;

/**
 *
 * @author HungLQ9
 */
public interface SceneGenerateWorkListRsService {

	//tungpv
	@POST
    @Path("/sceneGenerateWorkList/doCRUD")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response doCRUD(SceneGenerateWorkDTO dto);
	//end tungpv
	//minhpvn
	@POST
    @Path("/sceneGenerateWorkList/doCRUDCT")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response doCRUDCT(SceneGenerateWorkDTO dto);
    @GET
    @Path("/sceneGenerateWorkList")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getSceneGenerateWorkList();

    @POST
    @Path("/sceneGenerateWorkList/search")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response doSearchSceneGenerateWork(List<EstimatesWorkItemsDTO> criteria);
    
    @POST
    @Path("/sceneGenerateWorkList/searchList")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response doSearchSceneGenerateWorkList(SceneGenerateWorkDTO criteria);
    
    @GET
    @Path("/sceneGenerateWorkList/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getSceneGenerateWorkListById(@PathParam("id") Long id);
    
    @GET
    @Path("/sceneGenerateWorkList/getGrid/{constructId}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAllSceneGenerateWork(@PathParam("constructId") Long constructId);
    
    @POST
    @Path("/sceneGenerateWorkList/put/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateSceneGenerateWorkList(SceneGenerateWorkListDTO obj);

    @POST
    @Path("/sceneGenerateWorkList/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addSceneGenerateWorkList(SceneGenerateWorkListDTO obj);

    @DELETE
    @Path("/sceneGenerateWorkList/{id}/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteSceneGenerateWorkList(@PathParam("id") Long id);
    
}
