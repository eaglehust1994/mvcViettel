/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import com.viettel.erp.dto.UtilAttachedDocumentsDTO;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author HungLQ9
 */
public interface UtilAttachedDocumentsRsService {

	@POST
	@Path("/utilAttachedDocuments/getListByParentIdAndType")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getListByParentIdAndType(Long parentId)throws Exception;
	
	@POST
	@Path("/utilAttachedDocuments/getListOrganizationByParentId")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getListOrganizationByParentId(Long parentId)throws Exception;
	
	@POST
	@Path("/utilAttachedDocuments/getListByParentIdCA")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getListByParentIdCA(Long parentId)throws Exception;
	
	@POST
	@Path("/utilAttachedDocuments/exportFileZip")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response  exportFileZip(List<Long> listParentId)throws Exception;
	
	@POST
	@Path("/utilAttachedDocuments/deleteDocument")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteDocument(List<String> listAttachId);

	@GET
	@Path("/utilAttachedDocuments")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getUtilAttachedDocuments();

	@GET
	@Path("/utilAttachedDocuments/{id}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getUtilAttachedDocumentsById(@PathParam("id") Long id);

	@POST
	@Path("/utilAttachedDocuments/put/")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateUtilAttachedDocuments(UtilAttachedDocumentsDTO obj);

	@POST
	@Path("/utilAttachedDocuments/")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response addUtilAttachedDocuments(UtilAttachedDocumentsDTO obj);

	@DELETE
	@Path("/utilAttachedDocuments/{id}/")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteUtilAttachedDocuments(@PathParam("id") Long id);
}
