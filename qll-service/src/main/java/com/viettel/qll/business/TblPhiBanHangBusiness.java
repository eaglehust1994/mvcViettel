package com.viettel.qll.business;

import javax.servlet.http.HttpServletRequest;

import com.viettel.qll.dto.TblPhiBanHangDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 * @author hailh10
 */

public interface TblPhiBanHangBusiness {

	DataListDTO doSearchPhiBanHang(TblPhiBanHangDTO obj, HttpServletRequest request) throws Exception;


}
