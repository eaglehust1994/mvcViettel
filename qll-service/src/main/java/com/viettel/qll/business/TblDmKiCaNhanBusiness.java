package com.viettel.qll.business;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.viettel.qll.dto.TblDmKiCaNhanDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 * @author hailh10
 */

public interface TblDmKiCaNhanBusiness {

	DataListDTO doSearchKiCaNhan(TblDmKiCaNhanDTO obj, HttpServletRequest request) throws Exception;


}
