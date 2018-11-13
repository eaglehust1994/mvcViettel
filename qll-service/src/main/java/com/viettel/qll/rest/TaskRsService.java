package com.viettel.qll.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.viettel.qll.dto.KqHdTkDTO;
import com.viettel.qll.dto.TaskDTO;

/**
 * @author hailh10
 */
 
public interface TaskRsService {


	
	@POST
	@Path("/doSearch")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response doSearch(TaskDTO obj);
	
	@POST
	@Path("/checkInfoUser")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response checkUser(TaskDTO obj);
	
	
	@POST
	@Path("/updateTaskStatus")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateTaskStatus(TaskDTO obj) throws Exception;
	
}
