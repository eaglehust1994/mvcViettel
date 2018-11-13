package com.viettel.qll.business;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.viettel.qll.dto.ErrExcelDTO;
import com.viettel.qll.dto.ImportErrDTO;
import com.viettel.qll.dto.TblLoiDongAoDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 * @author hailh10
 */

public interface TblLoiDongAoBusiness {

	DataListDTO doSearch(TblLoiDongAoDTO obj);
	public boolean checkDataImport(int index, String value, ErrExcelDTO orderErrorFormat) ;
	public String exportExcelError(List<ImportErrDTO> obj) throws Exception ;
	public String exportExcelGrid(TblLoiDongAoDTO lst) throws Exception;
	String importFile(String fileInput, HttpServletRequest request) throws Exception;
}
