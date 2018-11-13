package com.viettel.qll.business;

import com.viettel.qll.dto.QlCvPtkDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 * @author hailh10
 */

public interface QlCvPtkBusiness {

	DataListDTO doSearch(QlCvPtkDTO obj) throws Exception;

	Long updatePathFileScan(QlCvPtkDTO obj) throws Exception;

	Long updatePathFileExcell(QlCvPtkDTO obj) throws Exception;
	
	Long updatePathFileQtk(QlCvPtkDTO obj) throws Exception;
	
	Long updatePathFileQtDt(QlCvPtkDTO obj) throws Exception;

	long deleteObj(QlCvPtkDTO obj);

	long deleteListObj(QlCvPtkDTO obj);

}
