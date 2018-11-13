package com.viettel.qll.business;

import java.util.List;
import com.viettel.qll.dto.TaskDTO;

/**
 * @author hailh10
 */

public interface TaskBusiness {


	List<TaskDTO> doSearch(TaskDTO obj);
	

}
