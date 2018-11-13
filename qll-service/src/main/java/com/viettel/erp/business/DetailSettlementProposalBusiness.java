package com.viettel.erp.business;

import java.util.List;

import com.viettel.erp.dto.DetailSettlementProposalDTO;
import com.viettel.erp.dto.EstimatesWorkItemsDTO;
import com.viettel.erp.dto.approDTO;

public interface DetailSettlementProposalBusiness {

    long count();
    String getCode(String tableName,String value);
    
    List<DetailSettlementProposalDTO> getAllDetailSettlementProposal(DetailSettlementProposalDTO  detailSettlementProposalDTO);
    
    Long addManyTable(DetailSettlementProposalDTO Proposal,List<EstimatesWorkItemsDTO> listAcc) throws Exception;
  
    String updateManyTable(DetailSettlementProposalDTO Proposal,List<EstimatesWorkItemsDTO> listAcc) throws Exception;
    
    String deleteDetailSettlementProposal(Long id) throws Exception;
    
    boolean checkStatusDatabase(Long detailSettlementProposalId);
    
    Long appro(approDTO obj);
    public boolean deleteFromRecomap(DetailSettlementProposalDTO obj);
	
	boolean updateIsActive(Long detailSettlementProposalId);
	List<EstimatesWorkItemsDTO> exPortfull(Long id, Long flag);
    
}
