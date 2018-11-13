package com.viettel.erp.business;

import java.util.List;

import com.viettel.erp.bo.EstimatesDetailAnalystBO;
import com.viettel.erp.bo.EstimatesWorkItemsBO;
import com.viettel.erp.dto.DetailSettlementEvaluateDTO;
import com.viettel.erp.dto.EstimatesDetailAnalystDTO;
import com.viettel.erp.dto.EstimatesWorkItemsDTO;
import com.viettel.erp.dto.approDTO;

public interface DetailSettlementEvaluateBusiness {

    long count();
    
    List<DetailSettlementEvaluateDTO> getAllbyConstructId(DetailSettlementEvaluateDTO dto);
    
    Long addManyTable(DetailSettlementEvaluateDTO Evaluate,List<EstimatesWorkItemsDTO> listAcc, List<EstimatesDetailAnalystDTO> listAna) throws Exception;
    
    boolean checkStatusDatabase(Long detailSettlementEvaluateId);
//    String fail(DetailSettlementEvaluateDTO obj) throws Exception;
    Long appro(approDTO obj);
    List<EstimatesWorkItemsBO> importCT(String filename, Long constrId) throws Exception;
    void deleteDetailSettlementEvaluate(Long id) throws Exception;
    
    List<EstimatesWorkItemsDTO> exPortfull(Long id,String code);
    public boolean deleteFromRecomap(long id);
    
    boolean updateIsActive(Long detailSettlementEvaluateId);
}
