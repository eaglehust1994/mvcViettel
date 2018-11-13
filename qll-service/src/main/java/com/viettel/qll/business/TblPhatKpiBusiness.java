package com.viettel.qll.business;

import java.util.List;
import com.viettel.qll.dto.TblPhatKpiDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 * @author hailh10
 */

public interface TblPhatKpiBusiness {


	DataListDTO doSearchPhatKPI(TblPhatKpiDTO obj);


}
