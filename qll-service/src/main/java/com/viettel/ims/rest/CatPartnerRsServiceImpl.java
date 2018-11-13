package com.viettel.ims.rest;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.viettel.ims.bo.CatPartnerBO;
import com.viettel.ims.business.CatPartnerBusinessImpl;
import com.viettel.ims.dto.CatPartnerDTO;
import com.viettel.service.base.dto.DataListDTO;
//import com.viettel.erp.constant.ApplicationConstants;
//import com.viettel.service.base.dto.ActionListDTO;
//import com.viettel.erp.utils.ExportExcel;
//import com.viettel.erp.utils.FilterUtilities;
import com.viettel.service.base.utils.DateUtil;
import com.viettel.util.ParamUtils;

/**
 * @author hailh10
 */
 
public class CatPartnerRsServiceImpl implements CatPartnerRsService {

	protected final Logger log = Logger.getLogger(CatPartnerRsService.class);
	@Autowired
	CatPartnerBusinessImpl catPartnerBusinessImpl;
	
	
	@Override
	public Response doSearch(CatPartnerDTO obj) {
		List<CatPartnerDTO> ls = catPartnerBusinessImpl.doSearch(obj);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			DataListDTO data = new DataListDTO();
			data.setData(ls);
			data.setTotal(obj.getTotalRecord());
			data.setSize(obj.getPageSize());
			data.setStart(1);
			return Response.ok(data).build();
		}
	}
	
	@Override
	public Response getById(Long id) {
		CatPartnerDTO obj = (CatPartnerDTO) catPartnerBusinessImpl.getById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(obj).build();
		}
	}

	@Override
	public Response update(CatPartnerDTO obj) {
		CatPartnerDTO originObj = (CatPartnerDTO) catPartnerBusinessImpl.getOneById(obj.getCatPartnerId());
		
		if (originObj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			
				if (!obj.getCode().equalsIgnoreCase(originObj.getCode())) {
					CatPartnerDTO check = catPartnerBusinessImpl.findByCode(obj.getCode());
					if (check != null) {
						return Response.status(Response.Status.CONFLICT).build();
					} else {
						return doUpdate(obj);
					}
				} else {
					return doUpdate(obj);
				}
			
		}

	}
	
	private Response doUpdate(CatPartnerDTO obj) {
		obj.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
//		obj.setUpdatedby(catPartnerBusinessImpl.getSessionInfo().getUserId());
		
		Long id = catPartnerBusinessImpl.update(obj);
		if (id == 0l) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(obj).build();
		}
	}

	@Override
	public Response add(CatPartnerDTO obj) {
		CatPartnerDTO existing = (CatPartnerDTO) catPartnerBusinessImpl.findByCode(obj.getCode());
		if (existing != null) {
			return Response.status(Response.Status.CONFLICT).build();
		} else {
			obj.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			Long id = catPartnerBusinessImpl.save(obj);
			obj.setCatPartnerId(id);
			if (id == 0l) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			} else {
				return Response.ok(obj).build();
			}
		}
	}

	@Override
	public Response delete(Long id) {
		CatPartnerDTO obj = (CatPartnerDTO) catPartnerBusinessImpl.getOneById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			obj.setStatus(0l);
			catPartnerBusinessImpl.update(obj);
			return Response.ok(Response.Status.NO_CONTENT).build();
		}
	}
	
	@Override
	public Response deleteList(List<Long> ids){
		String result = catPartnerBusinessImpl.delete( ids, CatPartnerBO.class.getName() ,"CAT_PARTNER_ID");
		
		if(result ==  ParamUtils.SUCCESS ){
			 return Response.ok().build();
		} else {
			 return Response.status(Response.Status.EXPECTATION_FAILED).build();
		}
	}


	@Override
	public Response getForAutoComplete(CatPartnerDTO obj) {
		return Response.ok(catPartnerBusinessImpl.getForAutoComplete(obj)).build();
	}

	@Override
	public Response getForComboBox(CatPartnerDTO obj) {
		return Response.ok(catPartnerBusinessImpl.getForComboBox(obj)).build();
	}
}
