package com.viettel.qll.business;

import java.util.List;

import com.viettel.qll.dto.KpiDepartmentDTO;
import com.viettel.qll.dto.SysGroupDTO;

/**
 * @author hailh10
 */

public interface SysGroupBusiness {



	List<SysGroupDTO> getListDepartment();

	List<SysGroupDTO> getAutoDepartment(SysGroupDTO obj);
}
