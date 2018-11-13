package com.viettel.qll.business;

import java.util.List;
import com.viettel.qll.dto.TaskGroupDTO;

/**
 * @author hailh10
 */

public interface TaskGroupBusiness {

	

	List<TaskGroupDTO> doSearch(TaskGroupDTO obj);

	long saveTaskGroup(TaskGroupDTO obj);

	long updateTaskGroup(TaskGroupDTO obj) throws Exception;

	long deleteListObj(TaskGroupDTO obj);

	TaskGroupDTO getDeptId(TaskGroupDTO obj);
	

}
