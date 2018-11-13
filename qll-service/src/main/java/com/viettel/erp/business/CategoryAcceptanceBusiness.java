package com.viettel.erp.business;

import java.util.List;


import com.viettel.erp.dto.CatEmployeeDTO;
import com.viettel.erp.dto.CategoryAcceptanceDTO;
import com.viettel.erp.dto.CategoryAcceptanceExtDTO;
import com.viettel.erp.dto.approDTO;

public interface CategoryAcceptanceBusiness {

    long count();
    
    //haibt
    List<CategoryAcceptanceExtDTO> getAllCategoryAcceptance();
    
    public boolean deleteCategoryAcceptanceList(List<Long> lisItemCode);
    
    List<CategoryAcceptanceDTO> getCategoryAcceptanceById(Long constructId, Double contractId);
    
    CategoryAcceptanceDTO getCategoryAcceptanceByIdDetail(Long categoryAcceptanceId);
    
    //List<CatEmployeeDTO> getListMonitorChangeConstruct(CatEmployeeDTO obj);
    
    String autoGenCode() ;
    
    Long appro(approDTO obj);
    
//    public String approvalCategoryAcceptanceList(List<Long> lisItemCode);
}
