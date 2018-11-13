package com.viettel.qll.rest;

import java.util.Collections;
import java.util.List;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.viettel.qll.business.SysUserBusinessImpl;
import com.viettel.qll.business.TaskBusinessImpl;

import com.viettel.erp.dto.DataListDTO;

import com.viettel.qll.dto.SysUserDTO;
import com.viettel.qll.dto.TaskDTO;

/**
 * @author hailh10
 */
 
public class TaskRsServiceImpl implements TaskRsService {

	protected final Logger log = Logger.getLogger(TaskRsService.class);
	@Autowired
	TaskBusinessImpl taskBusinessImpl;
	@Autowired
	SysUserBusinessImpl sysUserBusinessImpl;

	
	@Override
	public Response doSearch(TaskDTO obj) {
		if(StringUtils.isNotEmpty(obj.getEmployeeCode())){
			SysUserDTO user = new SysUserDTO();	
			user = sysUserBusinessImpl.infoSysUserByEmployee(obj.getEmployeeCode());
			obj.setDepartmentId(user.getDepartmentId());
		}
		
		List<TaskDTO> ls = taskBusinessImpl.doSearch(obj);
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
	
	public Response checkUser(TaskDTO obj) {
		try {
			boolean ls = true;
			SysUserDTO user = new SysUserDTO();	
			user = sysUserBusinessImpl.infoSysUserByEmployee(obj.getEmployeeCode());
			long objDept = obj.getDepartmentId();
			long userDept = user.getDepartmentId();
			if(objDept==userDept){
				ls = true;
			}else{
				ls = false;
			}
			return Response.ok(ls).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		
	}
	
	@Override
	public Response updateTaskStatus(TaskDTO obj) throws Exception {
		
		@SuppressWarnings("unused")
		Long ids;
		try {
			ids = taskBusinessImpl.updateTaskStatus(obj);
			return Response.ok(Response.Status.CREATED).build();
		} catch (IllegalArgumentException e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}

	}
	
}
