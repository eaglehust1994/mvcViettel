package com.viettel.qll.business;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.viettel.qll.dto.TblDmKiDonViDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 * @author hailh10
 */

public interface TblDmKiDonViBusiness {

	DataListDTO doSearchKiDonVi(TblDmKiDonViDTO obj,HttpServletRequest request)throws Exception;
}
