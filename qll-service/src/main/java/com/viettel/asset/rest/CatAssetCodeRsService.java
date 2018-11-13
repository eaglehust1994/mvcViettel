package com.viettel.asset.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.viettel.asset.business.CatAssetCodeBusiness;
import com.viettel.asset.dto.AutocompleteSearchDto;
import com.viettel.asset.dto.CatAssetCodeDto;
import com.viettel.asset.dto.CatAssetCodeSearchDto;
import com.viettel.ktts2.common.BusinessException;

@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class CatAssetCodeRsService {

	@Autowired
	private CatAssetCodeBusiness catAssetCodeBusiness;
	
	@POST
	@Path("/getDetail/")
	public Response getDetail(CatAssetCodeDto form) {
		return Response.ok(catAssetCodeBusiness.getDetail(form.getCatAssetCodeId())).build();
	}
	
	@POST
	@Path("/search/")
	public Response search(CatAssetCodeSearchDto searchForm) {
		return Response.ok(catAssetCodeBusiness.search(searchForm)).build();
	}
	
	@POST
	@Path("/searchPaginage/")
	public Response searchPaginage(CatAssetCodeSearchDto searchForm) {	
		return Response.ok(catAssetCodeBusiness.searchPaginage(searchForm)).build();
	}
	
	@POST
	@Path("/insert/")
	public Response insert(CatAssetCodeDto form) throws Exception {
		return Response.ok(catAssetCodeBusiness.insert(form)).build();
	}
	
	@POST
	@Path("/update/")
	public Response update(CatAssetCodeDto form) throws Exception {
		return Response.ok(catAssetCodeBusiness.update(form)).build();
	}
	
	@POST
	@Path("/delete/")
	public Response delete(CatAssetCodeDto form) throws Exception {
		catAssetCodeBusiness.delete(form.getCatAssetCodeId());
		return Response.ok().build();
	}
	
	@POST
	@Path("/active/")
	public Response active(CatAssetCodeDto form) throws Exception {
		return Response.ok(catAssetCodeBusiness.active(form.getCatAssetCodeId())).build();
	}
	
	@POST
	@Path("/inactive/")
	public Response inactive(CatAssetCodeDto form) throws Exception {
		return Response.ok(catAssetCodeBusiness.inactive(form.getCatAssetCodeId())).build();
	}
	
	@POST
	@Path("/filterCatAssetCodeType/")
	public Response filterCatAssetCodeType(AutocompleteSearchDto form) throws Exception {
		return Response.ok(catAssetCodeBusiness.filterCatAssetCodeType(form)).build();
	}
	
	@POST
	@Path("/filterCatAssetCodeAutocomplete/")
	public Response filterCatAssetCodeAutocomplete(CatAssetCodeSearchDto form) throws Exception {
		return Response.ok(catAssetCodeBusiness.filterCatAssetCodeAutocomplete(form)).build();
	}
	
}
