package com.viettel.qll.business;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.viettel.qll.dto.TblDonGiaThueBaoDTO;
import com.viettel.qll.dto.TblPhatFcDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 * @author hailh10
 */

public interface TblDonGiaThueBaoBusiness {
	DataListDTO doSearch(TblDonGiaThueBaoDTO obj, HttpServletRequest request) throws Exception;
	String downloadImportTemplate() throws Exception ;
}
