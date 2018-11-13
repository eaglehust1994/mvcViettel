package com.viettel.qll.business;


import java.util.List;

import com.viettel.qll.dto.TblKhBckqcvDTO;

import com.viettel.service.base.dto.DataListDTO;

/**
 * @author hailh10
 */

public interface TblKhBckqcvBusiness {

	

	DataListDTO doSearch(TblKhBckqcvDTO obj) throws Exception;
	
	long deleteObj(TblKhBckqcvDTO obj);

	long deleteListObj(TblKhBckqcvDTO obj);
	List<TblKhBckqcvDTO> getForAutoCompleteMonth(TblKhBckqcvDTO obj);
	List<TblKhBckqcvDTO> getForAutoCompleteMaDv(TblKhBckqcvDTO obj);

	long updateKhKd(TblKhBckqcvDTO obj) throws Exception;
}
