package com.viettel.qll.business;

import java.util.List;

import com.viettel.qll.dto.TblQuaTrinhTlDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 * @author hailh10
 */

public interface TblQuaTrinhTlBusiness {

	DataListDTO doSearchQTTL(TblQuaTrinhTlDTO obj);

}
