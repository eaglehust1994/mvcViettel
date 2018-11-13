package com.viettel.erp.business;
 
import com.viettel.erp.bo.MonitorMissionAssignBO;
import com.viettel.erp.dao.MonitorMissionAssignDAO;
import com.viettel.erp.dto.CompletionDrawingDTO;
import com.viettel.erp.dto.EstimatesWorkItemsDTO;
import com.viettel.erp.dto.MonitorMissionAssignDTO;
import com.viettel.erp.dto.WorkItemsAcceptanceDTO;
import com.viettel.erp.dto.approDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("monitorMissionAssignBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MonitorMissionAssignBusinessImpl extends BaseFWBusinessImpl<MonitorMissionAssignDAO,MonitorMissionAssignDTO, MonitorMissionAssignBO> implements MonitorMissionAssignBusiness {

    @Autowired
    private MonitorMissionAssignDAO monitorMissionAssignDAO;
    

     
    public MonitorMissionAssignBusinessImpl() {
        tModel = new MonitorMissionAssignBO();
        tDAO = monitorMissionAssignDAO;
    }

    @Override
    public MonitorMissionAssignDAO gettDAO() {
        return monitorMissionAssignDAO;
    }

    @Override
    public long count() {
        return monitorMissionAssignDAO.count("MonitorMissionAssignBO", null);
    }

    public MonitorMissionAssignDTO getDataExport(MonitorMissionAssignDTO dto){
    	return monitorMissionAssignDAO.getDataExport(dto);
    }

	@Override
	public List<MonitorMissionAssignDTO> getMonitorMissionAssign(MonitorMissionAssignDTO obj) {
		// TODO Auto-generated method stub
		return monitorMissionAssignDAO.getMonitorMissionAssign(obj);
	}

	@Override
	public String autoGenCode() {
		// TODO Auto-generated method stub
		return monitorMissionAssignDAO.autoGenCode();
	}

	public Long saveTable( MonitorMissionAssignDTO  monitorMissionAssign) throws Exception{
	      Long monitorMissionAssignId = monitorMissionAssignDAO.saveTable(monitorMissionAssign);
	      return monitorMissionAssignId;
}

	@Override
	public boolean updateIsActive(List<Long> monitorMissionAssignId) {
		// TODO Auto-generated method stub
		return monitorMissionAssignDAO.updateIsActive(monitorMissionAssignId);
	}

	 
	public Long appro(approDTO dto) {
		return monitorMissionAssignDAO.appro(dto);
	}
}
