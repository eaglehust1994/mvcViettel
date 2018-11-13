package com.viettel.qll.business;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.viettel.qll.dto.TblQltsCongNoVatTuDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 * @author hailh10
 */

public interface TblQltsCongNoVatTuBusiness {

//	public List<TblQltsCongNoVatTuDTO> getAllCongNo();

	DataListDTO getAllCongNo(TblQltsCongNoVatTuDTO obj);
//	String prepareDataTongHop(TblQltsCongNoVatTuDTO obj) throws Exception;
//	String exportExcelTongHop(List<TblQltsCongNoVatTuDTO> lst,TblQltsCongNoVatTuDTO obj) throws Exception ;
	Long updateSlCongNo(TblQltsCongNoVatTuDTO obj, HttpServletRequest request) throws Exception;
	String prepareDataTongHop(TblQltsCongNoVatTuDTO obj, HttpServletRequest request) throws Exception;
}
