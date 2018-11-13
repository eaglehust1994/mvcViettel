package com.viettel.qll.business;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.viettel.qll.dto.ErrExcelDTO;
import com.viettel.qll.dto.ImportErrDTO;
import com.viettel.qll.dto.TblLoiLapLaiDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 * @author hailh10
 */

public interface TblLoiLapLaiBusiness {

	DataListDTO doSearch(TblLoiLapLaiDTO obj);
	public boolean checkDataImport(int index, String value,String value2, ErrExcelDTO orderErrorFormat) ;
	public String exportExcelError(List<ImportErrDTO> obj) throws Exception ;
	public String exportExcelGrid(TblLoiLapLaiDTO lst) throws Exception;
	String importFile(String fileInput, HttpServletRequest request) throws Exception;
}
