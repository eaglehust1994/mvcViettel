package com.viettel.ims.business;
 
import java.util.List;
import com.viettel.ims.bo.TaskQuotaBO;
import com.viettel.ims.dao.TaskQuotaDAO;
import com.viettel.ims.dto.TaskQuotaDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("taskQuotaBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TaskQuotaBusinessImpl extends BaseFWBusinessImpl<TaskQuotaDAO,TaskQuotaDTO, TaskQuotaBO> implements TaskQuotaBusiness {

    @Autowired
    private TaskQuotaDAO taskQuotaDAO;
     
    public TaskQuotaBusinessImpl() {
        tModel = new TaskQuotaBO();
        tDAO = taskQuotaDAO;
    }

    @Override
    public TaskQuotaDAO gettDAO() {
        return taskQuotaDAO;
    }
	
	@Override
	public TaskQuotaDTO findByCode(String value) {
		return taskQuotaDAO.findByCode(value);
	}

	@Override
	public List<TaskQuotaDTO> doSearch(TaskQuotaDTO obj) {
		return taskQuotaDAO.doSearch(obj);
	}	
	
	@Override
	public List<TaskQuotaDTO> getForAutoComplete(TaskQuotaDTO query) {
		return taskQuotaDAO.getForAutoComplete(query);
	}
	
	public List<TaskQuotaDTO> getForComboBox(TaskQuotaDTO query) {
		return taskQuotaDAO.getForComboBox(query);
	}

	public String delete(List<Long> ids, String tableName, String tablePrimaryKey) {
		return taskQuotaDAO.delete(ids, tableName, tablePrimaryKey);	
	}
	
	
	public TaskQuotaDTO getById(Long id) {
		return taskQuotaDAO.getById(id);
	}
}
