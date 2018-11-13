package com.viettel.ktts2.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public interface CommonRsService {
	/**
	 * Tìm kiếm
	 * @param id
	 * @return
	 */
	@GET
    @Path("/getSysGroupAjax/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getSysGroupAjax(@PathParam("id") Long id);
}
