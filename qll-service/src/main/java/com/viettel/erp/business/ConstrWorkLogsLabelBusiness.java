package com.viettel.erp.business;

import java.util.List;

import com.viettel.erp.dto.ConstrWorkLogsLabelDTO;
import com.viettel.erp.dto.VConstructionHcqtDTO;
import com.viettel.erp.dto.approDTO;

public interface ConstrWorkLogsLabelBusiness {

    long count();
    
    Long creat(ConstrWorkLogsLabelDTO dto);
    
    String autoGenCode();
    
    boolean checkBia(Long constructId);
    
    List<ConstrWorkLogsLabelDTO> getAllBia(Long constructId);
    
    Long appro(approDTO obj);
    
    VConstructionHcqtDTO findById(Long constructId, String contractCode) ;
    
    Long updateLabel(ConstrWorkLogsLabelDTO dto) ;
    
    boolean checkStatusDatabaseLabel(String constrWoLogsLabId);
}
