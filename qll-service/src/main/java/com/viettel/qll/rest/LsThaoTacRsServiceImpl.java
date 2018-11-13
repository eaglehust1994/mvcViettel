package com.viettel.qll.rest;

import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.viettel.qll.business.LsThaoTacBusinessImpl;
import com.viettel.qll.dto.LsThaoTacDTO;
import com.viettel.qll.dto.TblDlDauVaoDayMayDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 * @author hailh10
 */
 
public class LsThaoTacRsServiceImpl implements LsThaoTacRsService {

	protected final Logger log = Logger.getLogger(LsThaoTacRsService.class);
	@Autowired
	LsThaoTacBusinessImpl lsThaoTacBusinessImpl;
	
	
	@Override
	public Response doSearch(LsThaoTacDTO obj) throws Exception {
		DataListDTO data = lsThaoTacBusinessImpl.doSearch(obj);
        if (data == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
        	return Response.ok(data).build();
        }
	}
}
