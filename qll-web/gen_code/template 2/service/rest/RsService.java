package com.viettel.${projectCode}.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Context;

import com.viettel.${projectCode}.dto.${tbl.tableNameJV}DTO;

/**
 * @author hailh10
 */
 
public interface ${tbl.tableNameJV}RsService {

	@GET
	@Path("/${tbl.tableNameVar}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getAll(${tbl.tableNameJV}DTO obj);

	@GET
	@Path("/${tbl.tableNameVar}/findAllActive")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getAllActive();
	
	@GET
	@Path("/${tbl.tableNameVar}/findById")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getById(@QueryParam("id") Long id);

	@POST
	@Path("/${tbl.tableNameVar}/update/")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response update(${tbl.tableNameJV}DTO obj);

	@POST
	@Path("/${tbl.tableNameVar}/")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response add(${tbl.tableNameJV}DTO obj);

	@POST
	@Path("/${tbl.tableNameVar}/delete/{id}/")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response delete(@PathParam("id") Long id);
	
	@POST
	@Path("/${tbl.tableNameVar}/search")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response doSearch(${tbl.tableNameJV}DTO obj);
	
	@GET
	@Path("/${tbl.tableNameVar}/findByValue")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	Response findByValue(@QueryParam("value") String value);
	
	@POST
	@Path("/${tbl.tableNameVar}/deleteList/")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteList( List<Long> ids);
	
	@GET
	@Path("/${tbl.tableNameVar}/findByAutoComplete")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response findByAutoComplete(@QueryParam("term") String term);
}
