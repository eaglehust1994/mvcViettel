package com.viettel.qll.business;

import java.util.List;

import com.viettel.qll.dto.LichSuDangNhapDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 * @author hailh10
 */

public interface LichSuDangNhapBusiness {

	DataListDTO doSearch(LichSuDangNhapDTO obj) throws Exception;

}
