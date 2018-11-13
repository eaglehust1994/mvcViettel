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
import com.viettel.ims.bo.${tbl.tableNameJV}BO;
import com.viettel.ims.business.${tbl.tableNameJV}BusinessImpl;
import com.viettel.ims.dto.${tbl.tableNameJV}DTO;
import com.viettel.util.ParamUtils;
import com.viettel.ktts2.common.Page;

/**
 * @author hailh10
 */
 
public class ${tbl.tableNameJV}RsServiceImpl implements ${tbl.tableNameJV}RsService {

	protected final Logger log = Logger.getLogger(${tbl.tableNameJV}RsService.class);
	@Autowired
	${tbl.tableNameJV}BusinessImpl ${tbl.tableNameVar}BusinessImpl;

	public Response getAll(${tbl.tableNameJV}DTO obj) {			
		List<${tbl.tableNameJV}DTO> ls = ${tbl.tableNameVar}BusinessImpl.doSearch(obj);							
		return Response.ok(ls).build();
	}
	
	@Override
	public Response getAllActive() {
		${tbl.tableNameJV}DTO obj = new ${tbl.tableNameJV}DTO();
		obj.setIsActive(1l);		
		List<${tbl.tableNameJV}DTO> ls = ${tbl.tableNameVar}BusinessImpl.doSearch(obj);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}
	
	@Override
	public Response doSearch(${tbl.tableNameJV}DTO obj) {
		List<${tbl.tableNameJV}DTO> ls = ${tbl.tableNameVar}BusinessImpl.doSearch(obj);
		Page data=new Page();
		data.setPage(obj.getPage());
		data.setRows(ls);
		data.setTotalRow(ls.isEmpty()?0:ls.get(0).getTotalRecord());			
		return Response.ok(data).build();
		
	}
	@Override
	public Response getById(Long id) {
		${tbl.tableNameJV}DTO obj = (${tbl.tableNameJV}DTO) ${tbl.tableNameVar}BusinessImpl.getById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(obj).build();
		}
	}

	@Override
	public Response update(${tbl.tableNameJV}DTO obj) {
		long id;
		<#list  tbl.columnBOs as clm >
		<#if clm.isKey>
		${tbl.tableNameJV}DTO originObj = (${tbl.tableNameJV}DTO) ${tbl.tableNameVar}BusinessImpl.getOneById(obj.get${clm.columnNameJV}());
		</#if>
		</#list>
		
		if (originObj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			obj.setUpdatorDate(new Date());
			obj.setCreatorDate(originObj.getCreatorDate());			
								
			id = ${tbl.tableNameVar}BusinessImpl.update(obj);
			
			if (id == 0l) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			} else {
				return Response.ok(id).build();
			}
		}

	}

	@Override
	public Response add(${tbl.tableNameJV}DTO obj) {	
		obj.setCreatorDate(new Date());
		obj.setUpdatorDate(new Date());
		Long id = ${tbl.tableNameVar}BusinessImpl.save(obj);
		if (id == 0l) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(id).build();
		}		
	}

	@Override
	public Response delete(Long id) {
		${tbl.tableNameJV}DTO obj = (${tbl.tableNameJV}DTO) ${tbl.tableNameVar}BusinessImpl.getOneById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			obj.setIsActive(0l);
			obj.setUpdatorDate(new Date());
			${tbl.tableNameVar}BusinessImpl.update(obj);
			return Response.ok(Response.Status.NO_CONTENT).build();
		}
	}
	@Override
	public Response findByValue(String value) {
		${tbl.tableNameJV}DTO obj = (${tbl.tableNameJV}DTO) ${tbl.tableNameVar}BusinessImpl.findByValue(value);
		if (obj == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			return Response.ok(obj).build();
		}
	}
	
	@Override
	public Response deleteList(List<Long> ids){
		<#list  tbl.columnBOs as clm >
		<#if clm.isKey>
		String result = ${tbl.tableNameVar}BusinessImpl.delete( ids, ${tbl.tableNameJV}BO.class.getName() ,"${clm.columnName}");
		</#if>
		</#list>		
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
		${tbl.tableNameJV}DTO objectSearch = new ${tbl.tableNameJV}DTO();
		//TODO: add key search vào dây
		
		List<${tbl.tableNameJV}BO> results = ${tbl.tableNameVar}BusinessImpl.getForAutoComplete(objectSearch);
						
		return Response.ok(results).build();
	}
}
