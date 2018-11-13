package com.viettel.qll.rest;

import java.util.Collections;
import java.util.List;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.viettel.qll.business.TaskGroupBusinessImpl;
import com.viettel.erp.dto.DataListDTO;
import com.viettel.qll.dto.TaskGroupDTO;


/**
 * @author hailh10
 */
 
public class TaskGroupRsServiceImpl implements TaskGroupRsService {

	protected final Logger log = Logger.getLogger(TaskGroupRsService.class);
	@Autowired
	TaskGroupBusinessImpl taskGroupBusinessImpl;
	

	
	@Override
	public Response doSearch(TaskGroupDTO obj) {
		List<TaskGroupDTO> ls = taskGroupBusinessImpl.doSearch(obj);
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
	public Response getDeptId(TaskGroupDTO obj) {
		TaskGroupDTO ls = taskGroupBusinessImpl.getDeptId(obj);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
		
			return Response.ok(ls).build();
		}
	}
	@Override
	public Response saveTaskGroup(TaskGroupDTO obj) {
		// TODO Auto-generated method stub
		long ls = taskGroupBusinessImpl.saveTaskGroup(obj);
		if (ls == 0l) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}
	
	@Override
	public Response updateTaskGroup(TaskGroupDTO obj) throws Exception {
		long data = taskGroupBusinessImpl.updateTaskGroup(obj);
        if (data == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
        	return Response.ok(data).build();
        }
	}
	
	@Override
	public Response deleteObj(TaskGroupDTO obj) throws Exception {
		// TODO Auto-generated method stub
		@SuppressWarnings("unused")
		Long ids;
		try {
			ids = taskGroupBusinessImpl.deleteObj(obj);
			return Response.ok(Response.Status.CREATED).build();
		} catch (IllegalArgumentException e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}
	
	@Override
	public Response deleteListObj(TaskGroupDTO obj) throws Exception {
		@SuppressWarnings("unused")
		Long ids;
		try {
			ids = taskGroupBusinessImpl.deleteListObj(obj);
			return Response.ok(Response.Status.CREATED).build();
		} catch (IllegalArgumentException e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}
	
	@Override
	public Response getAutoCompleteDept(TaskGroupDTO obj) {
		List<TaskGroupDTO> ls = taskGroupBusinessImpl.getAutoCompleteDept(obj);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}
}
