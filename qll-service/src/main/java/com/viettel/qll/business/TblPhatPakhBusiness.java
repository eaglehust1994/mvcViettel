package com.viettel.qll.business;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.viettel.qll.dto.ErrExcelDTO;
import com.viettel.qll.dto.ImportErrDTO;
import com.viettel.qll.dto.TblPhatPakhDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 * @author hailh10
 */

public interface TblPhatPakhBusiness {

	DataListDTO doSearch(TblPhatPakhDTO obj);
	public boolean checkDataImport(int index, String value, ErrExcelDTO orderErrorFormat) ;
	public String exportExcelError(List<ImportErrDTO> obj) throws Exception ;
	public String exportExcelGrid(TblPhatPakhDTO lst) throws Exception;
	String importFile(String fileInput, HttpServletRequest request) throws Exception;
}
