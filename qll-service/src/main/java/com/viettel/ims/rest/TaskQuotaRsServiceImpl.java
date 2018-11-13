package com.viettel.ims.rest;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.viettel.Common.CommonBussiness.UserRoleBusinessImpl;
import com.viettel.ims.bo.TaskQuotaBO;
import com.viettel.ims.business.TaskQuotaBusinessImpl;
import com.viettel.ims.dto.TaskQuotaDTO;
import com.viettel.ktts2.dto.KttsUserSession;
import com.viettel.service.base.dto.DataListDTO;
import com.viettel.util.ParamUtils;
//import com.viettel.erp.constant.ApplicationConstants;
//import com.viettel.service.base.dto.ActionListDTO;
//import com.viettel.erp.utils.ExportExcel;
//import com.viettel.erp.utils.FilterUtilities;

/**
 * @author hailh10
 */
 
public class TaskQuotaRsServiceImpl implements TaskQuotaRsService {

	protected final Logger log = Logger.getLogger(TaskQuotaRsService.class);
	@Autowired
	TaskQuotaBusinessImpl taskQuotaBusinessImpl;
	
	@Context HttpServletRequest request;
	@Autowired
	private UserRoleBusinessImpl userRoleBusinessImpl;
	
	@Override
	public Response doSearch(TaskQuotaDTO obj) {
		List<TaskQuotaDTO> ls = taskQuotaBusinessImpl.doSearch(obj);
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
		TaskQuotaDTO obj = (TaskQuotaDTO) taskQuotaBusinessImpl.getById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(obj).build();
		}
	}

	@Override
	public Response update(TaskQuotaDTO obj) {
		TaskQuotaDTO originObj = (TaskQuotaDTO) taskQuotaBusinessImpl.getOneById(obj.getTaskQuotaId());
		
		if (originObj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			
				if (!obj.getCode().equalsIgnoreCase(originObj.getCode())) {
					TaskQuotaDTO check = taskQuotaBusinessImpl.findByCode(obj.getCode());
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
	
	private Response doUpdate(TaskQuotaDTO obj) {
//		obj.setUpdated(new Timestamp(System.currentTimeMillis()));
//		obj.setUpdatedby(taskQuotaBusinessImpl.getSessionInfo().getUserId());
		
		Long id = taskQuotaBusinessImpl.update(obj);
		if (id == 0l) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(obj).build();
		}
	}

	@Override
	public Response add(TaskQuotaDTO obj) {
		KttsUserSession objUser=userRoleBusinessImpl.getUserSession(request);
		TaskQuotaDTO existing = (TaskQuotaDTO) taskQuotaBusinessImpl.findByCode(obj.getCode());
		if (existing != null) {
			return Response.status(Response.Status.CONFLICT).build();
		} else {
			obj.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			obj.setCreatedUserId(objUser.getSysUserId());
			obj.setCreatedGroupId(objUser.getGroupId());
			obj.setStatus(1l);
			Long id = taskQuotaBusinessImpl.save(obj);
			obj.setTaskQuotaId(id);
			if (id == 0l) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			} else {
				return Response.ok(obj).build();
			}
		}
	}

	@Override
	public Response delete(Long id) {
		TaskQuotaDTO obj = (TaskQuotaDTO) taskQuotaBusinessImpl.getOneById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			obj.setStatus(0L);
			obj.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
			taskQuotaBusinessImpl.update(obj);
			return Response.ok(Response.Status.NO_CONTENT).build();
		}
	}
	
	@Override
	public Response deleteList(List<Long> ids){
		String result = taskQuotaBusinessImpl.delete( ids, TaskQuotaBO.class.getName() ,"TASK_QUOTA_ID");
		
		if(result ==  ParamUtils.SUCCESS ){
			 return Response.ok().build();
		} else {
			 return Response.status(Response.Status.EXPECTATION_FAILED).build();
		}
	}


	@Override
	public Response getForAutoComplete(TaskQuotaDTO obj) {
		return Response.ok(taskQuotaBusinessImpl.getForAutoComplete(obj)).build();
	}

	@Override
	public Response addList(List<TaskQuotaDTO> obj) {
		KttsUserSession objUser=userRoleBusinessImpl.getUserSession(request);
		for (TaskQuotaDTO taskQuotaDTO : obj) {
			taskQuotaDTO.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			taskQuotaDTO.setCreatedUserId(objUser.getSysUserId());
			taskQuotaDTO.setCreatedGroupId(objUser.getGroupId());
			taskQuotaDTO.setStatus(1l);
			Long id = taskQuotaBusinessImpl.save(taskQuotaDTO);
			taskQuotaDTO.setTaskQuotaId(id);
			if (id == 0l) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			}
		}
		return Response.ok(obj).build();
	}

	@Override
	public Response updateList(List<TaskQuotaDTO> objLst) {
		if(objLst != null && !objLst.isEmpty()){
			Long workItemQuotaId = objLst.get(0).getWorkItemQuotaId();
			TaskQuotaDTO tq = new TaskQuotaDTO();
			tq.setWorkItemQuotaId(workItemQuotaId);
			List<TaskQuotaDTO> oldList = taskQuotaBusinessImpl.doSearch(tq);
			for (TaskQuotaDTO taskQuotaDTO : oldList) {
				if(taskQuotaDTO !=null)
					{
						taskQuotaDTO.setStatus(0L);
						taskQuotaBusinessImpl.update(taskQuotaDTO);
					}
			}			
			KttsUserSession objUser=userRoleBusinessImpl.getUserSession(request);
			for (TaskQuotaDTO taskQuotaDTO : objLst) {
				taskQuotaDTO.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
				taskQuotaDTO.setCreatedUserId(objUser.getSysUserId());
				taskQuotaDTO.setCreatedGroupId(objUser.getGroupId());
				
				taskQuotaDTO.setStatus(1l);
				Long id = taskQuotaBusinessImpl.save(taskQuotaDTO);
				taskQuotaDTO.setTaskQuotaId(id);
				if (id == 0l) {
					return Response.status(Response.Status.BAD_REQUEST).build();
				}
			}
			return Response.ok(objLst).build();
			
		}
		return Response.ok(Response.Status.NO_CONTENT).build();
	}
	
	
	
}
