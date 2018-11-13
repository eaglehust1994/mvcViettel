package com.viettel.qll.rest;

import java.util.Collections;

import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.viettel.qll.business.LichSuDangNhapBusinessImpl;
import com.viettel.qll.dto.LichSuDangNhapDTO;
import com.viettel.qll.dto.LsThaoTacDTO;
import com.viettel.qll.dto.TblQltsCongNoVatTuDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 * @author hailh10
 */
 
public class LichSuDangNhapRsServiceImpl implements LichSuDangNhapRsService {

	protected final Logger log = Logger.getLogger(LichSuDangNhapRsService.class);
	@Autowired
	LichSuDangNhapBusinessImpl lichSuDangNhapBusinessImpl;
	
	@Override
	public Response insertLSDN(LichSuDangNhapDTO obj) throws Exception {
		Long ids;
		try {
			ids = lichSuDangNhapBusinessImpl.insertLSDN(obj);
			return Response.ok(Response.Status.CREATED).build();
		} catch (IllegalArgumentException e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}

	}

	@Override
	public Response doSearch(LichSuDangNhapDTO obj) throws Exception {
		DataListDTO data = lichSuDangNhapBusinessImpl.doSearch(obj);
        if (data == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
        	return Response.ok(data).build();
        }
	}
	
}
