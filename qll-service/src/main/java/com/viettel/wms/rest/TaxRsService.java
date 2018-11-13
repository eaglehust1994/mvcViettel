/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.rest;

import com.viettel.wms.dto.ReasonDTO;
import com.viettel.wms.dto.TaxDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author HungLQ9
 */
public interface TaxRsService {

    @GET
    @Path("/tax")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getTax();

    @POST
	@Path("/tax/doSearch")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response doSearch(TaxDTO obj);
    
    @POST
   	@Path("/tax/doSearchBytax")
   	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
   	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
   	public Response doSearchBytax(TaxDTO obj);
    

    @POST
    @Path("/remove")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response remove(TaxDTO obj);
    
    @POST
    @Path("/add")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response add(TaxDTO obj) throws Exception;
     
    @POST
    @Path("/update")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response update(TaxDTO obj) throws Exception;
}
