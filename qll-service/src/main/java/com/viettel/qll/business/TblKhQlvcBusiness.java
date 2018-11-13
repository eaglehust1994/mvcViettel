package com.viettel.qll.business;


import java.util.List;

import com.viettel.qll.dto.TblKhQlvcDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 * @author hailh10
 */

public interface TblKhQlvcBusiness {

	

	DataListDTO doSearch(TblKhQlvcDTO obj) throws Exception;

	long deleteObj(TblKhQlvcDTO obj);

	long deleteListObj(TblKhQlvcDTO obj);

	List<TblKhQlvcDTO> getForAutoCompleteMonth(TblKhQlvcDTO obj);

	List<TblKhQlvcDTO> getForAutoCompleteYear(TblKhQlvcDTO obj);

	List<TblKhQlvcDTO> getForAutoCompleteMaNv(TblKhQlvcDTO obj);

	List<TblKhQlvcDTO> getForAutoCompleteMaDv(TblKhQlvcDTO obj);

	long updateKhNv(TblKhQlvcDTO obj) throws Exception;
	
	
}
