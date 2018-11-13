/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.rest;

import com.viettel.wms.dto.ConfigSignVofficeDTO;
import com.viettel.wms.dto.StockDTO;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author TuanNB
 */
public interface ConfigSignVofficeRsService {

	@POST
    @Path("/configSignVoffice/add")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addConfigSignVoffice(List<ConfigSignVofficeDTO> obj);
	
    @POST
    @Path("/configSignVoffice/update")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateConfigSignVoffice(List<ConfigSignVofficeDTO> obj);

    @POST
	@Path("/configSignVoffice/doSearchStock")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response doSearchStock(StockDTO obj);
    
    @POST
    @Path("/configSignVoffice/getDataByID")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getDataByID(ConfigSignVofficeDTO obj);
    
}
