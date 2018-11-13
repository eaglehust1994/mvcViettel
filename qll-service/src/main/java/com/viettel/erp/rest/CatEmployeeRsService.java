/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.viettel.erp.dto.CatEmployeeDTO;
import com.viettel.erp.dto.SettlementRightDTO;

/**
 *
 * @author HungLQ9
 */
public interface CatEmployeeRsService {
	
	
	@POST
    @Path("/catEmployee/getRoleID")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getRoleID(Long catEmployeeId);
	
	//tungpv
	@POST
    @Path("/catEmployee/getEmployeeIdByUserId")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getEmployeeIdByUserId(Long userId);
	//ChuongNV
	@POST
    @Path("/getListEmployee/getEmployeeByListID")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getEmployeeByListID(String stringEmployee);
	//End ChuongNV

	@POST
    @Path("/getListEmployee/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getListEmployee(CatEmployeeDTO obj);
	
	@POST
    @Path("/DoSearch/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response DoSearch(CatEmployeeDTO obj);
	
	@POST
    @Path("/getAutoDataEmail/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAutoDataEmail(CatEmployeeDTO obj);
	
	@POST
    @Path("/getAutoDataUnit/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAutoDataUnit(CatEmployeeDTO obj);
	
	@POST
    @Path("/getAutoDataPartner/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAutoDataPartner(CatEmployeeDTO obj);
	
	@POST
    @Path("/getAutoData/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAutoData(CatEmployeeDTO obj);
	
	@POST
	@Path("/catEmployee/search")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response doSearch(CatEmployeeDTO obj) throws Exception;
	
	@POST
	@Path("/catEmployee/doSearchEmployeeViettel")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response doSearchEmployeeViettel(CatEmployeeDTO obj);
	
	@POST
    @Path("/getEmployeeNameRole/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getEmployeeNameRole(SettlementRightDTO obj);

	@POST
    @Path("/getEmployeeByRole/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getEmployeeByRole(CatEmployeeDTO obj);
	
	@POST
    @Path("/catEmployee/getEmployeeNameAndIdByRole/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getEmployeeNameAndIdByRole(SettlementRightDTO rightDTO);

	////////////////////////Add roleId////////////////////////////////////////
	@GET
	@Path("/catEmployee/getRoleId")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getRoleId();
	/////////////////////////END///////////////////////////////////////////////

    @GET
    @Path("/catEmployee")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getCatEmployee();

    @GET
    @Path("/catEmployee/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getCatEmployeeById(@PathParam("id") String id);

    @POST
    @Path("/catEmployee/put/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateCatEmployee(CatEmployeeDTO obj)throws Exception;

    @POST
    @Path("/catEmployee/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addCatEmployee(CatEmployeeDTO obj)throws Exception;

    @DELETE
    @Path("/catEmployee/{id}/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteCatEmployee(@PathParam("id") String id);
    
    @POST
    @Path("/getListEmployeeByRole/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getListEmployeeByRole(SettlementRightDTO rightDTO);
	
	@POST
    @Path("/catEmployee/getListEmployeeByConstruction")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getListEmployeeByConstruction(Long constructId);
	
	@POST
	@Path("/catEmployee/deleteList/put/")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteListPartnerHr(List<String> ids);
	
	@POST
    @Path("/catEmployee/getListEmployeeByCurrent")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getListEmployeeByCurrent(SettlementRightDTO rightDTO);
	
	@POST
    @Path("/catEmployee/getAllEmployee")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAllEmployee(CatEmployeeDTO dto);	
	
	
	@POST
    @Path("/getListMonitorChangeConstruct")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getListMonitorChangeConstruct(CatEmployeeDTO obj);
	
	@POST
    @Path("/catEmployee/getMonitorByRoleConstructId")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getListMonitorChangeConstructId(CatEmployeeDTO obj);

	@GET
    @Path("/catEmployee/folder")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getFolder();
	
	@GET
    @Path("/catEmployee/folder/uploadFileEstimate")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getFolderEstimate();
}
