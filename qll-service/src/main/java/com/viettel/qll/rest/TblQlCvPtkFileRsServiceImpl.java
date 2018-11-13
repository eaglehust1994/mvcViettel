package com.viettel.qll.rest;



import java.util.Collections;

import javax.ws.rs.core.Response;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;


import com.viettel.qll.business.TblQlCvPtkFileBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;
import com.viettel.qll.dto.TblQlCvPtkDTO;
import com.viettel.qll.dto.TblQlCvPtkFileDTO;

/**
 * @author hailh10
 */
 
public class TblQlCvPtkFileRsServiceImpl implements TblQlCvPtkFileRsService {

	protected final Logger log = Logger.getLogger(TblQlCvPtkFileRsService.class);
	@Autowired
	TblQlCvPtkFileBusinessImpl tblQlCvPtkFileBusinessImpl;
	
	
	
	@Override
	public Response doSearch(TblQlCvPtkFileDTO obj) {
		DataListDTO data = tblQlCvPtkFileBusinessImpl.doSearch(obj);
		if (data == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(data).build();
		}
	}
	@Override
	public Response saveFile(TblQlCvPtkFileDTO obj) {
		// TODO Auto-generated method stub
		long ls = tblQlCvPtkFileBusinessImpl.saveFile(obj);
		if (ls == 0l) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}
	
	@Override
	public Response deleteObj(Long id) throws Exception {
		// TODO Auto-generated method stub
		
		Long ids;
		try {
			ids = tblQlCvPtkFileBusinessImpl.deleteObj(id);
			return Response.ok(Response.Status.CREATED).build();
		} catch (IllegalArgumentException e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}
}
