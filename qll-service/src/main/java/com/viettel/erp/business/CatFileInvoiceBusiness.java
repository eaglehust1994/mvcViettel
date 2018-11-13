package com.viettel.erp.business;

import com.viettel.erp.dto.CatFileInvoiceDTO;

import java.util.List;

public interface CatFileInvoiceBusiness {

    long count();
    List<CatFileInvoiceDTO> findByExistProfile(long constructionId);
    
    CatFileInvoiceDTO onlyFindByTableName(String tableName);
}
