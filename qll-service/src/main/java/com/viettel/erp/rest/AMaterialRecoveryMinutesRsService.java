/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import com.viettel.erp.dto.AMaterialHandoverDTO;
import com.viettel.erp.dto.AMaterialRecoveryListModelDTO;
import com.viettel.erp.dto.AMaterialRecoveryMinutesDTO;
import com.viettel.erp.dto.AMaterialRecoveryMinutesModelDTO;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author HungLQ9
 */
public interface AMaterialRecoveryMinutesRsService {
	
	@POST
	@Path("/aMaterialRecoveryMinutes/exportFileM/")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response exportFileM(AMaterialRecoveryMinutesModelDTO obj);
	
	@POST
    @Path("aMaterialRecoveryMinutes/exportList/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response exportList(List<Long> data);
	
	@POST
	@Path("/aMaterialRecoveryMinutes/exportFileMDoc/")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response exportFileMDoc(AMaterialRecoveryMinutesModelDTO obj);
	
	@POST
    @Path("aMaterialRecoveryMinutes/exportListDoc/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response exportListDoc(List<Long> data);

	@POST
	@Path("/aMaterialRecoveryMinutes/deleteAMaterialMinutes/")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteAMaterialMinutes(List<String> listAMaterialRecoveryMinutesId);

	@GET
	@Path("/aMaterialRecoveryMinutes")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getAMaterialRecoveryMinutes();

	@POST
	@Path("/aMaterialRecoveryMinutes/findByConstructId")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response findByConstructId(AMaterialRecoveryMinutesModelDTO recoveryMinutesModelDTO);
	
	
	@GET
	@Path("/aMaterialRecoveryMinutes/updateRecoveryList/{id}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateRecoveryList(@PathParam("id") Long amaterialRecoveryMinutesId);

	@GET
	@Path("/aMaterialRecoveryMinutes/device/{id}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response device(@PathParam("id") Long constructId);
	
	@GET
	@Path("/aMaterialRecoveryMinutes/materials/{id}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response materials(@PathParam("id") Long constructId);
	
	@GET
	@Path("/aMaterialRecoveryMinutes/{id}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getAMaterialRecoveryMinutesById(@PathParam("id") Long id);

	@POST
	@Path("/aMaterialRecoveryMinutes/put/")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateAMaterialRecoveryMinutes(AMaterialRecoveryMinutesDTO obj);

	@POST
	@Path("/aMaterialRecoveryMinutes/")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response addAMaterialRecoveryMinutes(AMaterialRecoveryMinutesDTO obj);

	@DELETE
	@Path("/aMaterialRecoveryMinutes/{id}/")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteAMaterialRecoveryMinutes(@PathParam("id") Long id);
	
	@GET
	@Path("/aMaterialRecoveryMinutes/getTwoList/{id}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getTwoList(@PathParam("id") Long constructId);
	
	@GET
	@Path("/aMaterialRecoveryMinutes/getCheckTwoList/{id}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getCheckTwoList(@PathParam("id") Long constructId);
	
	@POST
	@Path("/aMaterialRecoveryMinutes/exportFile")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response exportFile(AMaterialRecoveryMinutesModelDTO minutesModelDTO);
	
//	@POST
//	@Path("/aMaterialRecoveryMinutes/checkInsert")
//	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	public Response checkInsert(AMaterialRecoveryMinutesModelDTO obj);
	
	@GET
	@Path("/aMaterialRecoveryMinutes/getExport/{id}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getExport(@PathParam("id") Long amaterialRecoveryMinutesId);
	
}
