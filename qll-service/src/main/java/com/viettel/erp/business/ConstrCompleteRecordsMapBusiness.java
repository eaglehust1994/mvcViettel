package com.viettel.erp.business;

import java.util.List;

import com.viettel.erp.dto.TheSignCADTO;

public interface ConstrCompleteRecordsMapBusiness {

    long count();
    void updateTotal(TheSignCADTO dto);
    Long insert(Long constructId, String tableName, String tableId, Long tableIdValue, Long userId, String code) throws Exception;
    
    Long check(String id);
    
    Long checkUserId(String id);
    
    int updateTotalApproval(TheSignCADTO dto);
    
    List<Long> getApproId(Long constrCompReMapId);
    
    void updateData(Long constrCompReMapId, String flag);
    
    void choiseCertification(TheSignCADTO theSignCADTO);
}
