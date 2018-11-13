package com.viettel.qll.business;


import java.util.List;

import com.viettel.qll.dto.TblGanNhiemVuDTO;
import com.viettel.qll.dto.TblQlCvPtkDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 * @author hailh10
 */

public interface TblGanNhiemVuBusiness {

	
	DataListDTO doSearch(TblGanNhiemVuDTO obj) throws Exception;
	
	

	long saveGanNv(TblGanNhiemVuDTO obj);



	DataListDTO getEmpByWorkId(TblGanNhiemVuDTO obj) throws Exception;



	long deleteListObj(TblGanNhiemVuDTO obj);



	long updateGNV(TblGanNhiemVuDTO obj) throws Exception;



	long saveAssignListTask(TblGanNhiemVuDTO obj);



	List<TblGanNhiemVuDTO> getForAutoCompleteTenCv(TblGanNhiemVuDTO obj);



	List<TblQlCvPtkDTO> getAutoMaViTriForShd(TblQlCvPtkDTO obj);
	
}
