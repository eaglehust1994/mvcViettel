package com.viettel.qll.business;

import java.util.List;
import com.viettel.qll.dto.TblLuongDayMayDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 * @author hailh10
 */

public interface TblLuongDayMayBusiness {

	DataListDTO doSearch(TblLuongDayMayDTO obj);


}
