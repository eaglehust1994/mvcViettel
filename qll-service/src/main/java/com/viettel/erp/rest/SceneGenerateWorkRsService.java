/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import com.viettel.erp.dto.BMaterialAcceptanceDTO;
import com.viettel.erp.dto.SceneGenerateWorkDTO;
import com.viettel.erp.dto.approDTO;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author HungLQ9
 */
public interface SceneGenerateWorkRsService {

	//tungpv
	@POST
    @Path("/sceneGenerateWork/updateIsActive")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateIsActive(List<Long> id);
	
	@POST
    @Path("/sceneGenerateWork/appro")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response appro(approDTO obj);
	//minhpvn appro cong trinh
	@POST
    @Path("/sceneGenerateWork/approCT")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response approCT(approDTO obj);
	
	@POST
    @Path("/sceneGenerateWork/doSearchSceneGenerateWork")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response doSearchSceneGenerateWork(SceneGenerateWorkDTO obj);
	
	@POST
    @Path("/sceneGenerateWork/doSearchSceneGenerateWorkConstruction")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response doSearchSceneGenerateWorkConstruction(SceneGenerateWorkDTO obj);
	
	@POST
    @Path("/sceneGenerateWork/getItemNameByConstrId")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getItemNameByConstrId(SceneGenerateWorkDTO obj);
	
	@POST
    @Path("/sceneGenerateWork/exportScene")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response exportFileSceneGenerateWork(SceneGenerateWorkDTO obj);
	
	@POST
    @Path("/sceneGenerateWork/exportSceneList")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response exportListSceneGenerateWork(List<Long> data);
	
	@POST
    @Path("/sceneGenerateWork/exportSceneDoc")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response exportFileDocSceneGenerateWork(SceneGenerateWorkDTO obj);
	
	@POST
    @Path("/sceneGenerateWork/exportSceneListDoc")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response exportListDocSceneGenerateWork(List<Long> data);
	//end tungpv
	
    @GET
    @Path("/sceneGenerateWork")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getSceneGenerateWork();

    @GET
    @Path("/sceneGenerateWork/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getSceneGenerateWorkById(@PathParam("id") Long id);

    @POST
    @Path("/sceneGenerateWork/put/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateSceneGenerateWork(SceneGenerateWorkDTO obj);

    @POST
    @Path("/sceneGenerateWork/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addSceneGenerateWork(SceneGenerateWorkDTO obj);

    @DELETE
    @Path("/sceneGenerateWork/{id}/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteSceneGenerateWork(@PathParam("id") Long id);
    
    //minhpvn
    @POST
    @Path("/sceneGenerateWork/downloadTempleReportSceneGenerate")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	Response downloadTempleReportSceneGenerate(SceneGenerateWorkDTO obj) throws Exception;
    
    @POST
    @Path("/sceneGenerateWork/downloadBieuMau")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	Response downloadBieuMau(SceneGenerateWorkDTO obj) throws Exception;
    //minhpvn export bieu mau cong trinh
    @POST
    @Path("/sceneGenerateWork/exportSceneCT")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response exportFileSceneGenerateWorkCT(SceneGenerateWorkDTO obj);
	
	@POST
    @Path("/sceneGenerateWork/exportSceneListCT")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response exportListSceneGenerateWorkCT(List<Long> data);
	
	@POST
    @Path("/sceneGenerateWork/exportSceneDocCT")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response exportFileDocSceneGenerateWorkCT(SceneGenerateWorkDTO obj);
	
	@POST
    @Path("/sceneGenerateWork/exportSceneListDocCT")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response exportListDocSceneGenerateWorkCT(List<Long> data);
    
}
