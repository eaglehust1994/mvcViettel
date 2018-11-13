package com.viettel.qll.business;

import java.util.List;
import com.viettel.qll.dto.TblDlHaTangTramDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 * @author hailh10
 */

public interface TblDlHaTangTramBusiness {

	public List<TblDlHaTangTramDTO> getAllDLHaTang();
	public DataListDTO doSearchDLHaTang(TblDlHaTangTramDTO obj);
	String downloadImportTemplate() throws Exception ;
}
