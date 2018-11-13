package com.viettel.qll.business;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.viettel.qll.dto.ImportErrDTO;
import com.viettel.qll.dto.TblNgayCongDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 * @author hailh10
 */

public interface TblNgayCongBusiness {

	DataListDTO doSearch(TblNgayCongDTO obj, HttpServletRequest request) throws Exception;
	String exportExcelError(List<ImportErrDTO> obj) throws Exception ;
	long deletePhatFcByMaNvAndThang(String maNv, String thang);
	String downloadImportTemplate() throws Exception ;
	String importFile(String fileInput, HttpServletRequest request);

}
