package com.viettel.qll.business;
 
import java.util.ArrayList;
import java.util.List;
import com.viettel.qll.bo.TaskGroupBO;
import com.viettel.qll.dao.TaskGroupDAO;
import com.viettel.qll.dto.TaskGroupDTO;

import com.viettel.service.base.business.BaseFWBusinessImpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("taskGroupBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TaskGroupBusinessImpl extends BaseFWBusinessImpl<TaskGroupDAO,TaskGroupDTO, TaskGroupBO> implements TaskGroupBusiness {

    @Autowired
    private TaskGroupDAO taskGroupDAO;
    protected final Logger log = Logger.getLogger(TaskGroupBusinessImpl.class);
    public TaskGroupBusinessImpl() {
        tModel = new TaskGroupBO();
        tDAO = taskGroupDAO;
    }

    @Override
    public TaskGroupDAO gettDAO() {
        return taskGroupDAO;
    }
	
	

	@Override
	public List<TaskGroupDTO> doSearch(TaskGroupDTO obj) {
		return taskGroupDAO.doSearch(obj);
	}	
	@Override
	public   TaskGroupDTO getDeptId(TaskGroupDTO obj) {
		return taskGroupDAO.getDeptId(obj);
	}
	@Override
	public long saveTaskGroup(TaskGroupDTO obj) {
		try {
			long ids = taskGroupDAO.saveObject(obj.toModel());
			return ids;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0l;
	}
	// Xoa 1 ban ghi

		public long deleteObj(TaskGroupDTO obj) {
			try {
				taskGroupDAO.delete(obj.toModel());
				return 1l;
			} catch (Exception e) {
				// TODO: handle exception
			}
			return 0l;
		}
		
		@Override
		public long updateTaskGroup(TaskGroupDTO obj) throws Exception {
			try {
			
				Long ids = taskGroupDAO.updateObject(obj.toModel());
				return ids;
			} catch (Exception e) {
				// TODO: handle exception
			}
			return 1l;
		}
		@Override
		public long deleteListObj(TaskGroupDTO obj) {
			List<TaskGroupDTO> lst = taskGroupDAO.doSearch(obj);
			List<Long> lstId = new ArrayList<>();
			int size = lst.size();
			int count = 0;
			int groupBatch = 0;
			try {
				for (TaskGroupDTO obj1 : lst) {
					count++;
					groupBatch++;
					lstId.add(obj1.getTaskGroupId());
					if (groupBatch == 999) {
						groupBatch = 0;
						taskGroupDAO.deleteList(lstId);
						lstId.clear();
					}
					if (groupBatch < 999 && count == size) {

						taskGroupDAO.deleteList(lstId);

					}
				}
				return 1l;
			} catch (Exception e) {
				log.error(e.getMessage());
			}

			return 0l;
		}
		
		public List<TaskGroupDTO> getAutoCompleteDept(TaskGroupDTO obj) {
			// TODO Auto-generated method stub
			return taskGroupDAO.getAutoCompleteDept(obj);
		}
		
		
}
