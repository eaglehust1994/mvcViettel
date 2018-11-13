package com.viettel.qll.business;

import java.util.List;

import com.viettel.qll.dto.KqHdTkDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 * @author hailh10
 */

public interface KqHdTkBusiness {

	DataListDTO doSearch(KqHdTkDTO obj) throws Exception;

	long insertHD(KqHdTkDTO obj) throws Exception;

	long updateHDTd(KqHdTkDTO obj) throws Exception;
	long updateHdPopSubmit(KqHdTkDTO obj) throws Exception;

	List<KqHdTkDTO> getForAutoCompleteHD(KqHdTkDTO obj);

	List<KqHdTkDTO> getForAutoCompleteKHTC(KqHdTkDTO obj);

	Long updatePath(KqHdTkDTO obj) throws Exception;

	long deleteObj(KqHdTkDTO obj);

	long deleteListObj(KqHdTkDTO obj);


}
