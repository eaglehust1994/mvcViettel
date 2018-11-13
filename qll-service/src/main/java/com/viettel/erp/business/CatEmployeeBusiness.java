package com.viettel.erp.business;

import java.util.List;

import com.viettel.erp.dto.CatEmployeeDTO;
import com.viettel.erp.dto.SettlementRightDTO;
import com.viettel.erp.dto.TheSignCADTO;
import com.viettel.erp.dto.TotalNumDTO;

public interface CatEmployeeBusiness {

    long count();
    
    //Truong
    
    List<CatEmployeeDTO> getListEmployeeByRole(SettlementRightDTO rightDTO);
    
    //NgocCX
    List<CatEmployeeDTO> getEmployeeNameAndIdByRole(SettlementRightDTO rightDTO);

    List<CatEmployeeDTO> getListEmployeeByCurrent(SettlementRightDTO rightDTO);
    
  
    //ChuongNV
    List<CatEmployeeDTO> getEmployeeByListID(String stringEmployee);
   
    
    List<CatEmployeeDTO> doSearch(CatEmployeeDTO criteria);
    
    List<CatEmployeeDTO> doSearchEmployeeViettel(CatEmployeeDTO criteria);
    
    List<CatEmployeeDTO> getAllEmployee(CatEmployeeDTO dto, TotalNumDTO totalNum);
    
    List<CatEmployeeDTO> getListEmployeeByRoleConstructId(CatEmployeeDTO obj);
    
    List<CatEmployeeDTO> getListMonitorChangeConstruct(CatEmployeeDTO obj);
    
    //tungpv
    CatEmployeeDTO getEmployeeIdByUserId(Long userId);
    
    CatEmployeeDTO getRoleID(Long catEmployeeId);
    
    boolean checkUpdateCMT(String identifyNumber,String id);

}
