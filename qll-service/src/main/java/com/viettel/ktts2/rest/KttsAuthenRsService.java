package com.viettel.ktts2.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

public interface KttsAuthenRsService {
	@GET
	@Path("/login/")
	public Response login();
	
	
	@GET
	@Path("/getUserInfo/")
	public Response getUserInfo();
	
	@GET
	@Path("/logout/")
	public Response logout() throws Exception;
}
