package com.viettel.erp.business;

import com.viettel.erp.dto.ApprovalSignManagementDTO;

public interface ApprovalSignManagementBusiness {

    long count();
    boolean cancelAprroval(ApprovalSignManagementDTO dto);
}
