package com.viettel.ims.business;

import java.util.List;

import com.viettel.ims.dto.SysUserDTO;

/**
 * @author hailh10
 */

public interface SysUserBusiness {

	public SysUserDTO findByLoginName(String name);

	List<SysUserDTO> doSearch(SysUserDTO obj);
	
	List<SysUserDTO> getForAutoComplete(SysUserDTO query);
}
