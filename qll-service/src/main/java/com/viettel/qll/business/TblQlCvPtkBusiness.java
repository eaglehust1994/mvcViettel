package com.viettel.qll.business;


import java.util.List;

import com.viettel.qll.dto.TblQlCvPtkDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 * @author hailh10
 */

public interface TblQlCvPtkBusiness {

	

	DataListDTO doSearch(TblQlCvPtkDTO obj) throws Exception;

	long saveAddCv(TblQlCvPtkDTO obj);

	long updateStatus(TblQlCvPtkDTO obj) throws Exception;

	long updatePath(TblQlCvPtkDTO obj) throws Exception;
	
	long updateCV1(TblQlCvPtkDTO obj) throws Exception;
	
	long updateCV2(TblQlCvPtkDTO obj) throws Exception;
	
	long updateCV3(TblQlCvPtkDTO obj) throws Exception;
	
	long updateCV4(TblQlCvPtkDTO obj) throws Exception;
	
	long updateCV5(TblQlCvPtkDTO obj) throws Exception;
	
	long deleteListObj(TblQlCvPtkDTO obj);

	List<TblQlCvPtkDTO> getForAutoCompleteSHD(TblQlCvPtkDTO obj);

	long updateCvPtk(TblQlCvPtkDTO obj) throws Exception;

	DataListDTO listTasks(TblQlCvPtkDTO obj) throws Exception;
	

}
