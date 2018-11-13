package com.viettel.qll.business;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.viettel.qll.dto.ErrExcelDTO;
import com.viettel.qll.dto.ImportErrDTO;
import com.viettel.qll.dto.TblPhatRoiMangDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 * @author hailh10
 */

public interface TblPhatRoiMangBusiness {

	DataListDTO doSearch(TblPhatRoiMangDTO obj);
	public String exportExcelError(List<ImportErrDTO> obj) throws Exception ;
	public String exportExcelGrid(TblPhatRoiMangDTO lst) throws Exception;
	String importFile(String fileInput, HttpServletRequest request) throws Exception;
}
