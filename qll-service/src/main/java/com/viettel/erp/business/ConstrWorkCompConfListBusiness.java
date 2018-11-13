package com.viettel.erp.business;

import java.util.List;

import com.viettel.erp.dto.ConstrWorkCompConfListLessDTO;

public interface ConstrWorkCompConfListBusiness {

    long count();
    public List<ConstrWorkCompConfListLessDTO> getListConstrWorkByConstrId(Long id);
    public List<ConstrWorkCompConfListLessDTO> getListConstrWorkExistByConstrId(List<Long> id);
}
