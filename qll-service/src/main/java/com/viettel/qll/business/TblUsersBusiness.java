package com.viettel.qll.business;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.viettel.qll.dto.TblUsersDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 * @author hailh10
 */

public interface TblUsersBusiness {

	List<TblUsersDTO> getForAutoCompleteNhanVien(TblUsersDTO obj);

	DataListDTO doSearch(TblUsersDTO obj, HttpServletRequest request) throws Exception;

	List<TblUsersDTO> getRoles(TblUsersDTO obj);

	List<TblUsersDTO> getForAutoCompleteNhanVienPtk(TblUsersDTO obj);
	
	
}
