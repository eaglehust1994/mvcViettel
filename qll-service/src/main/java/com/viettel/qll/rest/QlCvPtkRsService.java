package com.viettel.qll.rest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.viettel.qll.business.KqHdTkBusinessImpl;
import com.viettel.qll.dto.KqHdTkDTO;
import com.viettel.qll.dto.QlCvPtkDTO;

/**
 * @author hailh10
 */
 
public interface QlCvPtkRsService {

	@POST
	@Path("/getForAutoCompleteSHD")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getForAutoCompleteSHD(QlCvPtkDTO obj);
	
	@POST
	@Path("/doSearch")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response doSearch(QlCvPtkDTO obj) throws Exception ;
	
	@POST
	@Path("/saveAddCv")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response saveAddCv(QlCvPtkDTO obj) ;
	
	@POST
	@Path("/xuatBCTHCV")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response xuatBCTHCV(@Context HttpServletRequest request) throws Exception;

	@POST
	@Path("/xuatBCTheoDauViec")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response xuatBCTheoDauViec(QlCvPtkDTO obj,@Context HttpServletRequest request) throws Exception;

	@POST
	@Path("/updatePathFileScan")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updatePathFileScan(QlCvPtkDTO obj) throws Exception;
	
	@POST
	@Path("/updatePathFileExcel")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updatePathFileExcell(QlCvPtkDTO obj) throws Exception;
	
	@POST
	@Path("/updatePathFileQtk")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updatePathFileQtk(QlCvPtkDTO obj) throws Exception;
	
	@POST
	@Path("/updatePathFileQtDt")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updatePathFileQtDt(QlCvPtkDTO obj) throws Exception;
	
	@POST
	@Path("/deleteObj")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteObj(QlCvPtkDTO obj) throws Exception;
	
	@POST
	@Path("/exportRowAll")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response exportRowAll(QlCvPtkDTO obj,@Context HttpServletRequest request) throws Exception;
	
	@POST
	@Path("/importFile")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response importFile(Attachment attachments,@Context HttpServletRequest request) throws Exception;

	
	@POST
	@Path("/deleteListObj")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteListObj(QlCvPtkDTO obj) throws Exception;

}
