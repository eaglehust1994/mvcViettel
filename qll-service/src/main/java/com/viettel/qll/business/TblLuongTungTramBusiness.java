package com.viettel.qll.business;

import java.util.List;
import com.viettel.qll.dto.TblLuongTungTramDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 * @author hailh10
 */

public interface TblLuongTungTramBusiness {

	public DataListDTO doSearch(TblLuongTungTramDTO obj);
}
