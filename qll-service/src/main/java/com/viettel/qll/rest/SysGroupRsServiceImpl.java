package com.viettel.qll.rest;

import java.util.List;


import javax.ws.rs.core.Response;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.viettel.qll.business.KpiDepartmentBusinessImpl;
import com.viettel.qll.business.SysGroupBusinessImpl;
import com.viettel.qll.dto.KpiDepartmentDTO;
import com.viettel.qll.dto.KpiDepartmentDTOResponse;
import com.viettel.qll.dto.SysGroupDTO;
import com.viettel.qll.dto.SysGroupDTOResponse;
import com.viettel.qll.dto.TblDanhMucDTO;
import com.viettel.asset.dto.ResultInfo;


/**
 * @author hailh10
 */
 
public class SysGroupRsServiceImpl implements SysGroupRsService {

	protected final Logger log = Logger.getLogger(SysGroupRsService.class);
	@Autowired
	SysGroupBusinessImpl sysGroupBusinessImpl;
	@Autowired
	KpiDepartmentBusinessImpl kpiDepartmentBusinessImpl;

	// tạm thời để kpi_derpartment 
	@Override
	public Response getListDepartment() {
		
		
		try {
			KpiDepartmentDTOResponse newObj = new KpiDepartmentDTOResponse();
		
			List<KpiDepartmentDTO> listData = kpiDepartmentBusinessImpl.getListDepartment();
			newObj.setListData(listData);
			ResultInfo resultInfo = new ResultInfo();
			resultInfo.setStatus(ResultInfo.RESULT_OK);
			newObj.setResultInfo(resultInfo);
			return Response.ok(newObj).build();

		} catch (Exception e) {
			KpiDepartmentDTOResponse errObj = new KpiDepartmentDTOResponse();
			ResultInfo resultInfo = new ResultInfo();
			resultInfo.setStatus(ResultInfo.RESULT_NOK);
			resultInfo.setMessage(e.getMessage());
			errObj.setResultInfo(resultInfo);
			return Response.ok(errObj).status(Response.Status.BAD_REQUEST).build();
		}
		
	}
	
	@Override
	public Response getAutoDepartment(SysGroupDTO obj) {
		List<SysGroupDTO> ls = sysGroupBusinessImpl.getAutoDepartment(obj);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}
	



	

}
