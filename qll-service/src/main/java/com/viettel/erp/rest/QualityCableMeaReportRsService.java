/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import com.viettel.erp.dto.QualityCableMeaReportDTO;
import com.viettel.erp.dto.QualityCableMeaReportModelDTO;
import com.viettel.erp.dto.SettlementRightDTO;
import com.viettel.erp.dto.approDTO;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author HungLQ9
 */
public interface QualityCableMeaReportRsService {

	@POST
	@Path("/getListEmployeeByRole/")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getListEmployeeByRole(SettlementRightDTO rightDTO);

	@POST
	@Path("/qualityCableMeaReport/getAllListEmployeeByRole/")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getAllListEmployeeByRole(SettlementRightDTO rightDTO);

	@POST
	@Path("/qualityCableMeaReport/exportFileDoc")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response exportFileDoc(QualityCableMeaReportModelDTO rightDTO);

	@POST
	@Path("qualityCableMeaReport/exportListDoc/")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response exportListDoc(List<Long> data);

	@POST
	@Path("/qualityCableMeaReport/exportFile")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response exportFile(QualityCableMeaReportModelDTO rightDTO);

	@POST
	@Path("qualityCableMeaReport/exportList/")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response exportList(List<Long> data);

	@POST
	@Path("/qualityCableMeaReport/deleteReport/")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteReport(List<String> listReportID);

	@POST
	@Path("/qualityCableMeaReport/deleteResult/")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteResult(List<String> listString);

	@GET
	@Path("/qualityCableMeaReport")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getQualityCableMeaReport();

	@POST
	@Path("/qualityCableMeaReport/findByConstructId")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response findByConstructId(QualityCableMeaReportModelDTO qualityCableMeaReportModelDTO);

	@GET
	@Path("/qualityCableMeaReport/getListQualityResualt/{id}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getListQualityResualt(@PathParam("id") Long qualityCableMeaReportId);

	@GET
	@Path("/qualityCableMeaReport/{id}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getQualityCableMeaReportById(@PathParam("id") Long id);

	@POST
	@Path("/qualityCableMeaReport/put/")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateQualityCableMeaReport(QualityCableMeaReportDTO obj);

	@POST
	@Path("/qualityCableMeaReport/updateReportResult/put/")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response saveOrUpdate(QualityCableMeaReportDTO obj);

	@POST
	@Path("/qualityCableMeaReport/qualitySaveOrUpdate")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response qualitySaveOrUpdate(QualityCableMeaReportDTO obj);

	@POST
	@Path("/qualityCableMeaReport/")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response addQualityCableMeaReport(QualityCableMeaReportDTO obj);

	@POST
	@Path("/qualityCableMeaReport/appro")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response appro(approDTO obj);

	@DELETE
	@Path("/qualityCableMeaReport/{id}/")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteQualityCableMeaReport(@PathParam("id") Long id);
	
	
	@POST
	@Path("/qualityCableMeaReport/exportExcel")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response exportExcel() throws Exception;
	
}
