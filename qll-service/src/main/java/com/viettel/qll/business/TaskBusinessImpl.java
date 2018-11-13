package com.viettel.qll.business;
 
import java.util.List;
import com.viettel.qll.bo.TaskBO;
import com.viettel.qll.dao.TaskDAO;
import com.viettel.qll.dto.KqHdTkDTO;
import com.viettel.qll.dto.TaskDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("taskBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TaskBusinessImpl extends BaseFWBusinessImpl<TaskDAO,TaskDTO, TaskBO> implements TaskBusiness {

    @Autowired
    private TaskDAO taskDAO;
     
    public TaskBusinessImpl() {
        tModel = new TaskBO();
        tDAO = taskDAO;
    }

    @Override
    public TaskDAO gettDAO() {
        return taskDAO;
    }
	
    public Long updateTaskStatus(TaskDTO obj) throws Exception {
		try {
			long ids = taskDAO.updateTaskStatus(obj);
			return ids;
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Có lỗi khi cập nhật");
		}

	}

	@Override
	public List<TaskDTO> doSearch(TaskDTO obj) {
		return taskDAO.doSearch(obj);
	}	
	
	

}
