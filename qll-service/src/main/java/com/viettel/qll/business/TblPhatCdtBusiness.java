package com.viettel.qll.business;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.viettel.qll.dto.ErrExcelDTO;
import com.viettel.qll.dto.ImportErrDTO;
import com.viettel.qll.dto.TblPhatCdtDTO;
import com.viettel.qll.dto.TblPhatFcDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 * @author hailh10
 */

public interface TblPhatCdtBusiness {

	DataListDTO doSearchPhatCDT(TblPhatCdtDTO obj);

	public boolean checkPhatCdt(int index, String value, ErrExcelDTO orderErrorFormat) ;
	public String exportExcelError(List<ImportErrDTO> obj) throws Exception ;
	public String exportExcelGrid(TblPhatCdtDTO lst) throws Exception;

	String importFile(String fileInput, HttpServletRequest request) throws Exception;
}
