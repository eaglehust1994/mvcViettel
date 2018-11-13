/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.rest;

//import com.viettel.wms.dto.AppParamDTO;
import com.viettel.wms.dto.OddCableDTO;
import com.viettel.wms.dto.OrderPatternDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author HungLQ9
 */
public interface OddCableRsService {
  
//	Tim kiem
    @POST
    @Path("/doSearch")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response doSearch(OddCableDTO obj);
    
//  Xoa
    @POST
	@Path("/remove")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response remove(OddCableDTO obj);

//  Them moi
    @POST
    @Path("/add")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response add(OddCableDTO obj) throws Exception;
    
//  Cap nhat
    @POST
    @Path("/update")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response update(OddCableDTO obj) throws Exception;
}
