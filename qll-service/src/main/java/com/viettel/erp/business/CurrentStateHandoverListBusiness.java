package com.viettel.erp.business;

import java.util.List;

import com.viettel.erp.bo.CurrentStateHandoverBO;
import com.viettel.erp.dto.CurrentStateHandoverDTO;
import com.viettel.erp.dto.CurrentStateHandoverListDTO;

public interface CurrentStateHandoverListBusiness {

    long count();
    public List<CurrentStateHandoverListDTO> getCurrentStateHandoverByListId(Long id);
    public boolean deleteMutilRecord(List<String> listReportID) ;
}
