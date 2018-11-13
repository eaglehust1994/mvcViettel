package com.viettel.erp.business;

import java.util.List;

import com.viettel.erp.dto.AbComplementWorkDescribeDTO;
import com.viettel.erp.dto.EstimatesWorkItemsDTO;

public interface AbComplementWorkDescribeBusiness {

    long count();
    
    //minhpvn
    public String autoGenCode(String tableName, String value);
    List<AbComplementWorkDescribeDTO> signCAForm4(AbComplementWorkDescribeDTO obj);
    public Long saveTable( AbComplementWorkDescribeDTO  completionDrawing);
    public AbComplementWorkDescribeDTO getAbComplementWorkById(Long id);
    public Long getconstrCompReMapId(Long id);
    
    List<EstimatesWorkItemsDTO> getBieu4(EstimatesWorkItemsDTO obj);
    public EstimatesWorkItemsDTO exportEstimateWorkItem(EstimatesWorkItemsDTO dto);
}
