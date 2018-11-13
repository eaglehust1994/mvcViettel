package com.viettel.qll.business;

import javax.servlet.http.HttpServletRequest;

import com.viettel.qll.dto.LsThaoTacDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 * @author hailh10
 */

public interface LsThaoTacBusiness {

	DataListDTO doSearch(LsThaoTacDTO obj) throws Exception;
}
