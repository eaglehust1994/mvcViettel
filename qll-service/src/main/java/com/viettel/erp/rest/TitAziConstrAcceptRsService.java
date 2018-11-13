/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import com.viettel.erp.dto.TitAziConstrAcceptDTO;
import com.viettel.erp.dto.approDTO;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author HungLQ9
 */
public interface TitAziConstrAcceptRsService {
	
	@POST
	@Path("/titAziConstrAccept/removeMultiple/")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteList(List<String> listId);
	
	@POST
    @Path("/titAziConstrAccept/appro")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response appro(approDTO obj);
	
	@POST
    @Path("/titAziConstrAccept/updateIsActive")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateIsActive(Long titAziConstrAcceptId);
	
	@POST
    @Path("/titAziConstrAccept/exportList/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response exportList(List<Long> data);
	
	@POST
	@Path("/titAziConstrAccept/exportOnePDF")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response exportOnePDF(TitAziConstrAcceptDTO data);
	
	@POST
    @Path("/titAziConstrAccept/exportListDoc/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response exportListDoc(List<Long> data);
	
	@POST
	@Path("/titAziConstrAccept/exportOneDoc")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response exportOneDoc(TitAziConstrAcceptDTO data);
	
	
	@POST
    @Path("/titAziConstrAccept/findByConstructId")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findByConstructId(TitAziConstrAcceptDTO data);
	
		
    @GET
    @Path("/titAziConstrAccept")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getTitAziConstrAccept();

    @GET
    @Path("/titAziConstrAccept/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getTitAziConstrAcceptById(@PathParam("id") Long id);
    

    @POST
    @Path("/titAziConstrAccept/put/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateTitAziConstrAccept(TitAziConstrAcceptDTO obj);
    
    @POST
    @Path("/titAziConstrAccept/pheduyet/put/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response pheduyet(TitAziConstrAcceptDTO dto);

    @POST
    @Path("/titAziConstrAccept/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addTitAziConstrAccept(TitAziConstrAcceptDTO obj);

    @DELETE
    @Path("/titAziConstrAccept/{id}/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteTitAziConstrAccept(@PathParam("id") Long id);
    
    @POST
    @Path("/titAziConstrAccept/downloadTempleKLXLHT")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	Response downloadTempleKLXLHT(TitAziConstrAcceptDTO obj) throws Exception;
}
