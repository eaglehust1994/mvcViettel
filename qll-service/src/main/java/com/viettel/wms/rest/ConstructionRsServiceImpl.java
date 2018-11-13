package com.viettel.wms.rest;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.viettel.wms.business.ConstructionBusinessImpl;
import com.viettel.wms.dto.ConstructionDTO;

public class ConstructionRsServiceImpl 

implements ConstructionRsService {
	@Autowired
	ConstructionBusinessImpl constructionBusinessImpl;
	
	@Override
	public Response getForAutoComplete(ConstructionDTO cn) {
		return Response.ok(constructionBusinessImpl.doSearchConstructionForAutoComplete(cn)).build();
	}

	@Override
	public Response getForAutoCompleteII(String code) {
		return Response.ok(constructionBusinessImpl.doSearchConstructionForAutoCompleteII(code)).build();
	}

	@Override
	public Response getConstructionByCode(String code) {
		return Response.ok(constructionBusinessImpl.getConstructionByCode(code)).build();
	}
 
}
