package com.viettel.asset.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.viettel.asset.business.AssetReportBusiness;
import com.viettel.asset.dto.AssetReportS21SearchDto;
import com.viettel.asset.dto.AutocompleteSearchDto;
import com.viettel.ktts2.common.ResponseMessage;


@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class AssetReportRsService {
	@Autowired
	AssetReportBusiness assetReportBusiness;
	
	
	@POST
	@Path("/filterSysGroup/")
	public Response filterSysGroup(AutocompleteSearchDto autoSearchForm) {			
		return Response.ok(assetReportBusiness.filterSysGroup(autoSearchForm)).build();
	}
	@POST
	@Path("/filterLongTermAsset/")
	public Response filterLongTermAsset(AutocompleteSearchDto autoSearchForm) {			
		return Response.ok(assetReportBusiness.filterLongTermAsset(autoSearchForm)).build();
	}
	
	@POST
	@Path("/exportS21/")    
	public Response exportS21(AssetReportS21SearchDto reportForm) throws Exception{		
		return Response.ok(new ResponseMessage(assetReportBusiness.exportAssetS21(reportForm,"S21"))).build();
	}
	@POST
	@Path("/exportS22/")
	public Response exportS22(AssetReportS21SearchDto autoSearchForm) throws Exception{
		return Response.ok(new ResponseMessage(assetReportBusiness.exportAssetS21(autoSearchForm,"S22"))).build();				
	}
	
	@POST
	@Path("/exportS23/")
	public Response exportS23(AssetReportS21SearchDto autoSearchForm) throws Exception{
		return Response.ok(assetReportBusiness.exportAssetS23(autoSearchForm,"S22")).build();	
	}
	@POST
	@Path("/exportAssetBaseDonVi/")
	public Response exportAssetBaseDonVi(AssetReportS21SearchDto autoSearchForm) throws Exception{	
		return Response.ok(new ResponseMessage(assetReportBusiness.exportAssetBaseDonVi(autoSearchForm,"S22"))).build();	
	}
	
	@POST
	@Path("/exportAssetBaseAllDonVi/")
	public Response exportAssetBaseAllDonVi(AssetReportS21SearchDto autoSearchForm) throws Exception{	
		return Response.ok(new ResponseMessage(assetReportBusiness.exportAssetAllDonVi(autoSearchForm,"S22"))).build();	
	}
	
	@POST
	@Path("/notifyMerNotCreateAsset/")
	public Response notifyMerNotCreateAsset() throws Exception{	
		return Response.ok(new ResponseMessage(assetReportBusiness.notifyMerNotCreateAsset())).build();	
	}

}
