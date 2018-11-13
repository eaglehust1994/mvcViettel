package com.viettel.qll.business;

import javax.servlet.http.HttpServletRequest;

import com.viettel.qll.dto.TblKdcQuyLuongDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 * @author hailh10
 */

public interface TblKdcQuyLuongBusiness {


	DataListDTO doSearchQuyLuong(TblKdcQuyLuongDTO obj, HttpServletRequest request) throws Exception;

	}
