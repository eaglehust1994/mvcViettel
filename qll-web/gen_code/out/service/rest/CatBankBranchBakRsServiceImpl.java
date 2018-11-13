package com.viettel.ims.rest;

import java.util.List;
import java.util.Date;

import javax.ws.rs.core.Response;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Context;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.viettel.ims.bo.CatBankBranchBakBO;
import com.viettel.ims.business.CatBankBranchBakBusinessImpl;
import com.viettel.ims.dto.CatBankBranchBakDTO;
import com.viettel.util.ParamUtils;
import com.viettel.ktts2.common.Page;

/**
 * @author hailh10
 */
 
public class CatBankBranchBakRsServiceImpl implements CatBankBranchBakRsService {

	protected final Logger log = Logger.getLogger(CatBankBranchBakRsService.class);
	@Autowired
	CatBankBranchBakBusinessImpl catBankBranchBakBusinessImpl;

	public Response getAll(CatBankBranchBakDTO obj) {			
		List<CatBankBranchBakDTO> ls = catBankBranchBakBusinessImpl.doSearch(obj);							
		return Response.ok(ls).build();
	}
	
	@Override
	public Response getAllActive() {
		CatBankBranchBakDTO obj = new CatBankBranchBakDTO();
		obj.setIsActive(1l);		
		List<CatBankBranchBakDTO> ls = catBankBranchBakBusinessImpl.doSearch(obj);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}
	
	@Override
	public Response doSearch(CatBankBranchBakDTO obj) {
		List<CatBankBranchBakDTO> ls = catBankBranchBakBusinessImpl.doSearch(obj);
		Page data=new Page();
		data.setPage(obj.getPage());
		data.setRows(ls);
		data.setTotalRow(ls.isEmpty()?0:ls.get(0).getTotalRecord());			
		return Response.ok(data).build();
		
	}
	@Override
	public Response getById(Long id) {
		CatBankBranchBakDTO obj = (CatBankBranchBakDTO) catBankBranchBakBusinessImpl.getById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(obj).build();
		}
	}

	@Override
	public Response update(CatBankBranchBakDTO obj) {
		long id;
		CatBankBranchBakDTO originObj = (CatBankBranchBakDTO) catBankBranchBakBusinessImpl.getOneById(obj.getCatBankBranchId());
		
		if (originObj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			obj.setUpdatorDate(new Date());
			obj.setCreatorDate(originObj.getCreatorDate());			
								
			id = catBankBranchBakBusinessImpl.update(obj);
			
			if (id == 0l) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			} else {
				return Response.ok(id).build();
			}
		}

	}

	@Override
	public Response add(CatBankBranchBakDTO obj) {	
		obj.setCreatorDate(new Date());
		obj.setUpdatorDate(new Date());
		Long id = catBankBranchBakBusinessImpl.save(obj);
		if (id == 0l) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(id).build();
		}		
	}

	@Override
	public Response delete(Long id) {
		CatBankBranchBakDTO obj = (CatBankBranchBakDTO) catBankBranchBakBusinessImpl.getOneById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			obj.setIsActive(0l);
			obj.setUpdatorDate(new Date());
			catBankBranchBakBusinessImpl.update(obj);
			return Response.ok(Response.Status.NO_CONTENT).build();
		}
	}
	@Override
	public Response findByValue(String value) {
		CatBankBranchBakDTO obj = (CatBankBranchBakDTO) catBankBranchBakBusinessImpl.findByValue(value);
		if (obj == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			return Response.ok(obj).build();
		}
	}
	
	@Override
	public Response deleteList(List<Long> ids){
		String result = catBankBranchBakBusinessImpl.delete( ids, CatBankBranchBakBO.class.getName() ,"CAT_BANK_BRANCH_ID");
		if(result ==  ParamUtils.SUCCESS ){
			 return Response.ok().build();
		} else {
			 return Response.status(Response.Status.EXPECTATION_FAILED).build();
		}
	}

	@Override
	public Response findByAutoComplete(String term) {		
		String name = term == null ? "" : term;
		String value = term == null ? "" : term;
		CatBankBranchBakDTO objectSearch = new CatBankBranchBakDTO();
		//TODO: add key search vào dây
		
		List<CatBankBranchBakBO> results = catBankBranchBakBusinessImpl.getForAutoComplete(objectSearch);
						
		return Response.ok(results).build();
	}
}
