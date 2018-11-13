/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import java.io.IOException;
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

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.viettel.erp.dto.AMaterialHandoverDTO;
import com.viettel.erp.dto.AMaterialHandoverMerListDTO;
import com.viettel.erp.dto.EstimatesWorkItemsDTO;

/**
 *
 * @author HungLQ9
 */
public interface AMaterialHandoverMerListRsService {
	
	@POST
    @Path("/getListAMaterialHandOverMerList/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getListAMaterialHandOverMerList(AMaterialHandoverDTO dto);
	
	@POST
    @Path("/getListAMaterial/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getListAMaterial(List<String> listPXK);
	
//	@POST
//    @Path("/addListAMaterial/")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response addListAMaterial(List<AMaterialHandoverMerListBO> listBTVT);

    @GET
    @Path("/aMaterialHandoverMerList")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAMaterialHandoverMerList();

    @GET
    @Path("/aMaterialHandoverMerList/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAMaterialHandoverMerListById(@PathParam("id") Long id);

    @POST
    @Path("/aMaterialHandoverMerList/put/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateAMaterialHandoverMerList(AMaterialHandoverMerListDTO obj);

    @POST
    @Path("/aMaterialHandoverMerList/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addAMaterialHandoverMerList(AMaterialHandoverMerListDTO obj);

    @DELETE
    @Path("/aMaterialHandoverMerList/{id}/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteAMaterialHandoverMerList(@PathParam("id") Long id);
	
	@POST
    @Path("/aMaterialHandoverMerList/getAmaterialhandoverforcontruction")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAmaterialhandoverforcontruction(Long constructId);
	
	@POST
    @Path("/aMaterialHandoverMerList/getAmaterialhandoverforcontructionX")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAmaterialhandoverforcontructionX(Long constructId);
	
	
	@POST
    @Path("/aMaterialHandoverMerList/exportMerList")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response exportMerList(AMaterialHandoverMerListDTO obj) throws Exception;
}
