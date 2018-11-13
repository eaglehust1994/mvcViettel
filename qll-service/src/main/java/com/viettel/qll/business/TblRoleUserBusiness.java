package com.viettel.qll.business;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.viettel.qll.dto.TblRoleUserDTO;

/**
 * @author hailh10
 */

public interface TblRoleUserBusiness {

//	String insertRoles(TblRoleUserDTO lst) throws Exception;

	String insertRoles(TblRoleUserDTO lst, HttpServletRequest request) throws Exception;
	long insertRoles8() throws Exception;


}
