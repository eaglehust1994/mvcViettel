/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import com.itextpdf.text.DocumentException;
import com.viettel.erp.dto.CompletionDrawingDTO;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author HungLQ9
 */
public interface CompletionDrawingRsService {
	
	@POST
    @Path("/completionDrawing/Search/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getCompletionDrawingSearch(CompletionDrawingDTO obj) throws Exception;
	
	@POST
    @Path("/completionDrawing/SearchById/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getDrawById(List<String> completionDrawingId);
	
	@POST
	@Path("/completionDrawing/updateIsActive/")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateIsActive(List<Long> completionDrawingId);
	
	@POST
	@Path("/completionDrawing/addList/")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response addListCompletionDrawing(List<CompletionDrawingDTO> obj) throws Exception;
	
	/*@POST
	@Path("/completionDrawing/ganChanKy/")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response ganChanky(Long completionDrawingId) throws DocumentException, IOException;*/
	
	@POST
    @Path("/completionDrawing/downloadFile/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response downloadFileZip(List<String> path, @Context HttpServletRequest request);

    @GET
    @Path("/completionDrawing")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getCompletionDrawing() throws Exception;
    
    @GET
    @Path("/completionDrawing/folder")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getFolder();

    @GET
    @Path("/completionDrawing/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getCompletionDrawingById(@PathParam("id") Long id);

    @POST
    @Path("/completionDrawing/put/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateCompletionDrawing(CompletionDrawingDTO obj) throws Exception;

    @POST
    @Path("/completionDrawing/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addCompletionDrawing(CompletionDrawingDTO obj)throws Exception;

    @DELETE
    @Path("/completionDrawing/{id}/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteCompletionDrawing(@PathParam("id") Long id);
	
	@POST
    @Path("/completionDrawing/getCompletionDrawing")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getCompletionDrawing(Long constructId);
	
	@POST
    @Path("/completionDrawing/deleteDraw")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteDraw(List<CompletionDrawingDTO> listdto);
}
