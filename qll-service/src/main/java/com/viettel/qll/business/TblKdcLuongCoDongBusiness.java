package com.viettel.qll.business;

import javax.servlet.http.HttpServletRequest;

import com.viettel.qll.dto.TblKdcLuongCoDongDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 * @author hailh10
 */

public interface TblKdcLuongCoDongBusiness {

	DataListDTO doSearchKDCLuongCD(TblKdcLuongCoDongDTO obj, HttpServletRequest request) throws Exception;

}
