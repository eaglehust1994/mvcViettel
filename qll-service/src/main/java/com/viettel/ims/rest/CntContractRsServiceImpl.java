package com.viettel.ims.rest;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.viettel.Common.CommonBussiness.UserRoleBusinessImpl;
import com.viettel.ims.bo.CntContractBO;
import com.viettel.ims.business.BiddingPackageBusinessImpl;
import com.viettel.ims.business.CntContractBusinessImpl;
import com.viettel.ims.business.CntContractOrderBusiness;
import com.viettel.ims.business.CntContractOrderBusinessImpl;
import com.viettel.ims.dto.CntContractDTO;
import com.viettel.ims.dto.CntContractOrderDTO;
import com.viettel.ims.dto.PurchaseOrderDTO;
import com.viettel.ims.dto.SysGroupDTO;
import com.viettel.ktts2.dto.KttsUserSession;
import com.viettel.service.base.dto.DataListDTO;
import com.viettel.service.base.utils.DateUtil;
import com.viettel.util.ParamUtils;

/**
 * @author hailh10
 */

public class CntContractRsServiceImpl implements CntContractRsService {

	@Context
	HttpServletRequest request;
	protected final Logger log = Logger.getLogger(CntContractRsService.class);
	@Autowired
	CntContractBusinessImpl cntContractBusinessImpl;
	
	@Autowired
	CntContractOrderBusinessImpl CntContractOrderBusinessImpl;

	@Autowired
	private UserRoleBusinessImpl userRoleBusinessImpl;
	@Autowired
	BiddingPackageBusinessImpl biddingPackageBusinessImpl;

	@Override
	public Response doSearch(CntContractDTO obj) {
		List<CntContractDTO> ls = cntContractBusinessImpl.doSearch(obj);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			DataListDTO data = new DataListDTO();
			data.setData(ls);
			data.setTotal(ls.size());
			data.setSize(ls.size());
			data.setStart(1);
			return Response.ok(data).build();
		}
	}

	@Override
	public Response getById(Long id) {
		CntContractDTO obj = (CntContractDTO) cntContractBusinessImpl
				.getById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(obj).build();
		}
	}

	@Override
	public Response update(CntContractDTO obj) {
		CntContractDTO originObj = (CntContractDTO) cntContractBusinessImpl
				.getOneById(obj.getCntContractId());

		if (originObj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {

			if (!obj.getCode().equalsIgnoreCase(originObj.getCode())) {
				CntContractDTO check = cntContractBusinessImpl.findByCode(obj
						.getCode());
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

	private Response doUpdate(CntContractDTO obj) {
		obj.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
		CntContractOrderDTO criteria = new CntContractOrderDTO();
		criteria.setCntContractId(obj.getCntContractId());
		List<CntContractOrderDTO> oldLst = CntContractOrderBusinessImpl.doSearch(criteria);
		for(CntContractOrderDTO po : oldLst){
			if(po!=null){
				CntContractOrderBusinessImpl.delete(po);
			}
		}
		
		List<PurchaseOrderDTO> order = obj.getPurchaseOrderLst();
		for(PurchaseOrderDTO po : order){
			if(po!=null){
				CntContractOrderDTO cntOrd = new CntContractOrderDTO();
				cntOrd.setCntContractId(obj.getCntContractId());
				cntOrd.setPurchaseOrderId(po.getPurchaseOrderId());
				CntContractOrderBusinessImpl.save(cntOrd);
			}
		}
		Long id = cntContractBusinessImpl.update(obj);
		if (id == 0l) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(obj).build();
		}
	}

	@Override
	public Response add(CntContractDTO obj) {
		CntContractDTO existing = (CntContractDTO) cntContractBusinessImpl
				.findByCode(obj.getCode());
		Long id =0l;
		if (existing != null) {
			return Response.status(Response.Status.CONFLICT).build();
		} else {
			try{
				HttpServletRequest test = request;
			boolean check = test == null;
			System.out.println(check);
			KttsUserSession objUser = userRoleBusinessImpl.getUserSession(request);
			obj.setCreatedUserId(objUser.getSysUserId());
			obj.setCreatedGroupId(objUser.getGroupId());
			
			obj.setStatus(1L);
//			hoanm1_20170227_start
			obj.setContractType(0L);
//			hoanm1_20170227_end
			obj.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			id = cntContractBusinessImpl.save(obj);
			obj.setCntContractId(id);
			List<PurchaseOrderDTO> order = obj.getPurchaseOrderLst();
			for(PurchaseOrderDTO po : order){
				if(po!=null){
					CntContractOrderDTO cntOrd = new CntContractOrderDTO();
					cntOrd.setCntContractId(obj.getCntContractId());
					cntOrd.setPurchaseOrderId(po.getPurchaseOrderId());
					CntContractOrderBusinessImpl.save(cntOrd);
				}
			}
			obj.setCntContractId(id);
			}catch(Exception e){
				e.printStackTrace();
			}
			if (id == 0l) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			} else {
				return Response.ok(obj).build();
			}
			
		}
	}

	@Override
	public Response delete(CntContractDTO cnt) {
		CntContractDTO obj = (CntContractDTO) cntContractBusinessImpl
				.getOneById(cnt.getCntContractId());
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			obj.setStatus(0L);
			obj.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
			cntContractBusinessImpl.update(obj);
			return Response.ok(Response.Status.NO_CONTENT).build();
		}
	}

	@Override
	public Response deleteList(List<Long> ids) {
		String result = cntContractBusinessImpl.delete(ids,
				CntContractBO.class.getName(), "CNT_CONTRACT_ID");

		if (result == ParamUtils.SUCCESS) {
			return Response.ok().build();
		} else {
			return Response.status(Response.Status.EXPECTATION_FAILED).build();
		}
	}

	@Override
	public Response findByAutoComplete(CntContractDTO obj) {
		List<CntContractDTO> results = cntContractBusinessImpl
				.getForAutoComplete(obj);
		if (obj.getIsSize()) {
			CntContractDTO moreObject = new CntContractDTO();
			moreObject.setCntContractId(0l);
			;
			results.add(moreObject);
		}
		return Response.ok(results).build();
	}
//	hoanm1_20180303_start
	@Override
	public Response getForAutoComplete(CntContractDTO obj) {
		List<CntContractDTO> results = cntContractBusinessImpl.getForAutoComplete(obj);
		return Response.ok(results).build();
	}
//	hoanm1_20180303_end

}
