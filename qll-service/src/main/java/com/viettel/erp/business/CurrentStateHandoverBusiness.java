package com.viettel.erp.business;

import java.util.List;

import com.viettel.erp.bo.CurrentStateHandoverListBO;
import com.viettel.erp.dto.CompletionDrawingDTO;
import com.viettel.erp.dto.CurrentStateHandoverDTO;
import com.viettel.erp.dto.CurrentStateHandoverExtraDTO;
import com.viettel.erp.dto.approDTO;

public interface CurrentStateHandoverBusiness {

    long count();
    
    public List<CurrentStateHandoverDTO> getCurrentStateHandoverByContructId(long constructId);
    public boolean deleteListEntity(List<Long> listId);
    public CurrentStateHandoverExtraDTO getCurrentStateHandoverExtraById(Long id);
    public List<CurrentStateHandoverListBO> getCurrentStateHandoverByListId(Long id);
    public String getNameEmployee(Long id);
    public Long getconstrCompReMapId(Long id);
//    public boolean lastSignPeople(approDTO obj);
    public Long appro(approDTO obj);
    public Long saveTable( CurrentStateHandoverDTO  completionDrawing);
}
