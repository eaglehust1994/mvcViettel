package com.viettel.qll.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
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

import org.apache.cxf.jaxrs.ext.multipart.Attachment;

import javax.ws.rs.core.Context;

import com.viettel.qll.dto.TblDmKiDonViDTO;
import com.viettel.qll.dto.TblQltsThucXuatTheoKyDTO;
import com.viettel.qll.dto.TblTypeAPxkDTO;

/**
 * @author hailh10
 */

public interface TblQltsThucXuatTheoKyRsService {
	@GET
	@Path("/getAll")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getAllBctxtk() throws Exception;

	@POST
	@Path("/doSearchBctxtk")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response doSearchBctxtk(TblQltsThucXuatTheoKyDTO obj) throws Exception;

	@POST
	@Path("/importFile")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response importFile(Attachment attachments, @Context HttpServletRequest request) throws Exception;
	
	@POST
	@Path("/updateChiNhanh")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	Response updateChiNhanh(TblTypeAPxkDTO obj, @Context HttpServletRequest request) throws Exception;

	@POST
	@Path("/exportExcelTongHopTheoNV")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response exportExcelTongHopTheoNV(TblQltsThucXuatTheoKyDTO obj, @Context HttpServletRequest request) throws Exception;
	
	@POST
	@Path("/doSearchByPXK")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response doSearchByPXK(TblQltsThucXuatTheoKyDTO obj) throws Exception;
	

	@POST
	@Path("/updateSLByNV")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	Response updateSLByNV(TblQltsThucXuatTheoKyDTO obj, @Context HttpServletRequest request) throws Exception;
	
	@POST
	@Path("/exportExcelTongHopTheoDV")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response exportExcelTongHopTheoDV(TblQltsThucXuatTheoKyDTO obj, @Context HttpServletRequest request) throws Exception;

	@POST
	@Path("/updateNhanVien")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	Response updateNhanVien(TblTypeAPxkDTO obj, @Context HttpServletRequest request) throws Exception;

	

}
