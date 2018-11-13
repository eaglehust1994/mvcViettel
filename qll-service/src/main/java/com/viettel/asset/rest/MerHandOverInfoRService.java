package com.viettel.asset.rest;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ctc.wstx.util.StringUtil;
import com.viettel.asset.business.MerHandOverInfoBusiness;
import com.viettel.asset.dto.AutocompleteSearchDto;
import com.viettel.asset.dto.LongTermAssetDto;
import com.viettel.asset.dto.LongTermAssetEntityDto;
import com.viettel.asset.dto.MerHandOverEntityDto;
import com.viettel.asset.dto.MerHandOverInfoDto;
import com.viettel.asset.dto.search.AssetHandOverDto;
import com.viettel.asset.filter.session.UserSession;
import com.viettel.ktts2.common.ResponseMessage;


@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class MerHandOverInfoRService extends AbstractRsService{
	@Autowired
	MerHandOverInfoBusiness merHandOverInfoBusiness;
	@Inject
	private UserSession userSession;
	
	
	
	@POST
	@Path("/getForAutoComplete/")
    public Response getMerHandOverInfoAutoComplete(MerHandOverInfoDto query) throws Exception{
		return Response.ok(merHandOverInfoBusiness.getMerHandOverInfoAutoComplete(query, false)).build();
	};
	
	//Lay bien ban ban giao khogn qua xay lap tu handOverId
	@POST
	@Path("/getbyId/")
	 public Response getMerHandOverInfobyId(Long id) throws Exception{
		return Response.ok(merHandOverInfoBusiness.getMerHandOverbyId(id)).build();
	};
	
	@POST
	@Path("/getMerHandOverEntity/")
	 public Response getMerHandOverEntitybyId(Long id) throws Exception{
		return Response.ok(merHandOverInfoBusiness.getMerHandOverEntitybyId(id)).build();
	};
	
	@POST
	@Path("/getCaacname/")
	 public Response getCaacName(AutocompleteSearchDto query) throws Exception{
		return Response.ok(merHandOverInfoBusiness.getCaacName(query)).build();
	}
	
	@POST
	@Path("/getGruopCaac/")
	 public Response getGruopCaac(AutocompleteSearchDto query) throws Exception{
		return Response.ok(merHandOverInfoBusiness.getGruopCaac(query)).build();
	}
	
	@POST
	@Path("/insertLTA/")
	 public Response addLongTermAsset(List<LongTermAssetDto> obj) throws Exception{
		return Response.ok(merHandOverInfoBusiness.addLongTermAssetNotByConstruction(obj)).build();
	};
	@POST
	@Path("/updateLTA/")
 public Response updateLongTermAssetInHandOver(List<LongTermAssetDto> obj) throws Exception{
		return Response.ok(merHandOverInfoBusiness.updateLongTermAssetNotByConstruction(obj)).build();
	};
	
	/**
	 * Su dung cho chuc nang cu
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Deprecated
	@POST
	@Path("/getupdateMer/")
	 public Response getDataMerHandOverId(Long id) throws Exception{
		MerHandOverInfoDto objReturn = (MerHandOverInfoDto) merHandOverInfoBusiness.getDataMerHandOverId(id);
		if(StringUtils.isNotEmpty(objReturn.getCheckUsing())){
			return Response.status(Response.Status.CONFLICT).build();
		} else {
			return Response.ok(objReturn).build();	
		}		
	};
	/*
	 * Sua hinh thanh khong qua xay lap -> chuyen sang trang hinh thanh
	 */
	@POST
	@Path("/getMerHandOverInfoByLtaId/")
	 public Response getMerHandOverInfoByLtaId(Long id) throws Exception{
		return 	Response.ok(merHandOverInfoBusiness.getMerHandOverInfoByLtaId(id)).build();
	};
	/*
	 *  lay cac tai san da hinh thanh tai san
	 */
	@POST
	@Path("/getMerHandOverInfoDetailByHandOverId/")
	 public Response getListMerInLtaDetailByHandOverId(Long handoverId) throws Exception{
		return 	Response.ok(merHandOverInfoBusiness.getListMerInLtaDetailByHandOverId(handoverId)).build();
	};
	
	/*
	 * Su dung o service cu
	 */
	@Deprecated
	@POST
	@Path("/update/")
	 public Response updateLongTermAsset(List<LongTermAssetDto> obj) throws Exception{
		return Response.ok(merHandOverInfoBusiness.updateLongTermAsset(obj)).build();
	};
	
	
	
	@Deprecated
	@POST
	@Path("/getMerUpdate/")
	 public Response getMerHandOverEntityUpdate(MerHandOverEntityDto obj) throws Exception{
		return Response.ok(merHandOverInfoBusiness.getMerHandOverEntityUpdate(obj)).build();
	};
	
	@POST
	@Path("/getcodefull/")
	 public Response getCaacCodeFull(Long id) throws Exception{
//		return Response.ok(merHandOverInfoBusiness.getCaacCodeFull(id)).build();
		return Response.ok(merHandOverInfoBusiness.getCaacFullCodeTemp(id)).build();
	};
	/**
	 * Lay thong tin bien ban ban giao cho chuc nang view biên bản bàn giao
	 * @param handOverCode
	 * @return
	 * @throws Exception
	 */
	  @GET
	    @Path("/getMerHandOverInfoDetail")
	    public Response getMerHandOverInfoDetail(@QueryParam("handOverCode") String handOverCode) throws Exception {
	        return Response.ok(merHandOverInfoBusiness.getMerHandOverInfoDetail(handOverCode)).build();
	    }
	  /**
	   * lấy danh sách tài sản trong biên bản bàn giao cho chức năng view
	   * @param id
	   * @return
	   * @throws Exception
	   */
	@GET
	@Path("/getMerHandOverEntityByHandOverCode/")
	public Response getMerHandOverEntityByHandOverCode(@QueryParam("handOverCode")String handOverCode) throws Exception {
		return Response.ok(merHandOverInfoBusiness.getMerHandOverEntityByHandOverCode(handOverCode)).build();
	};
	
	/**
	 * Export biên bản bàn giao ra doc(code lấy từ version1.0
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/exportMerHandOverDoc/")
	public Response exportMerHandOverDoc(AssetHandOverDto dto) throws Exception {
		return Response.ok(new ResponseMessage( merHandOverInfoBusiness.exportMerHandOverDoc(dto.getHandoverCode()))).build();
	};
	@POST
	@Path("/exportMerHandOverPdf/")
	public Response exportMerHandOverPdf(AssetHandOverDto dto) throws Exception {
		return Response.ok(new ResponseMessage( merHandOverInfoBusiness.exportMerHandOverPdf(dto.getHandoverCode()))).build();
	};

}
