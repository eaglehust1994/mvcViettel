package com.viettel.qll.business;

import com.viettel.qll.dto.TblQlCvPtkFileDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 * @author hailh10
 */

public interface TblQlCvPtkFileBusiness {



	DataListDTO doSearch(TblQlCvPtkFileDTO obj) throws Exception;

	long saveFile(TblQlCvPtkFileDTO obj);
	

}
