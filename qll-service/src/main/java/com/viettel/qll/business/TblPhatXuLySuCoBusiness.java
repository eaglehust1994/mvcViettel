package com.viettel.qll.business;

import java.util.List;

import com.viettel.qll.dto.ErrExcelDTO;
import com.viettel.qll.dto.ImportErrDTO;
import com.viettel.qll.dto.TblPhatPakhDTO;
import com.viettel.qll.dto.TblPhatXuLySuCoDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 * @author hailh10
 */

public interface TblPhatXuLySuCoBusiness {

	DataListDTO doSearchPhatKPIXLSC(TblPhatXuLySuCoDTO obj);
	public boolean checkDataImport(int index, String value, ErrExcelDTO orderErrorFormat) ;
	public String exportExcelError(List<ImportErrDTO> obj) throws Exception ;
	public String exportExcelGrid(TblPhatXuLySuCoDTO lst) throws Exception;

}
