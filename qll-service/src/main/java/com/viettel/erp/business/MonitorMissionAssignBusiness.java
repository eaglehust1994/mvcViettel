package com.viettel.erp.business;

import java.util.List;

import com.viettel.erp.dto.MonitorMissionAssignDTO;

public interface MonitorMissionAssignBusiness {

    long count();
    List<MonitorMissionAssignDTO> getMonitorMissionAssign(MonitorMissionAssignDTO obj);
    String autoGenCode();
    boolean updateIsActive(List<Long> monitorMissionAssignId);
}
