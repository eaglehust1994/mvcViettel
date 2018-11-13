package com.viettel.qll.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.viettel.qll.dto.TaskGroupDTO;



/**
 * @author hailh10
 */
 
public interface TaskGroupRsService {


	
	@POST
	@Path("/getDeptId")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getDeptId(TaskGroupDTO obj);
	
	@POST
	@Path("/doSearch")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response doSearch(TaskGroupDTO obj);
	@POST
	@Path("/saveTaskGroup")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response saveTaskGroup(TaskGroupDTO obj) ;
	
	@POST
	@Path("/deleteObj")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteObj(TaskGroupDTO obj) throws Exception;
	
	@POST
	@Path("/updateTaskGroup")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateTaskGroup(TaskGroupDTO obj) throws Exception;
	
	@POST
	@Path("/deleteListObj")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteListObj(TaskGroupDTO obj) throws Exception;
	
	@POST
	@Path("/getAutoCompleteDept")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getAutoCompleteDept(TaskGroupDTO obj);
	
}
