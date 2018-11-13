package com.viettel.erp.business;

import java.util.List;

import com.viettel.erp.dto.CatEmployeeDTO;
import com.viettel.erp.dto.ConstrOrganizationPlanDTO;
import com.viettel.erp.dto.ConstrWorkLogsDTO;

public interface ConstrOrganizationPlanBusiness {

    long count();
    
    List<ConstrOrganizationPlanDTO> getAllConstrOrganizationPlan(ConstrOrganizationPlanDTO dto);
    
    void deleteConstrOrganizationPlan(List<String> listConstrOrgPlanId);
    
    List<CatEmployeeDTO> getEmployee(String contructID);
    
    String autoGenCodeConstrOrganizationPlan();
    
    boolean checkStatusDatabase(String constrConstrOrganizationPlan);
    
    Long addConstrOrganizationPlan(ConstrOrganizationPlanDTO obj)throws Exception;
    
    List<ConstrOrganizationPlanDTO> getAllConstrOrganizationPlanChild(ConstrOrganizationPlanDTO dto);
    
    
}
