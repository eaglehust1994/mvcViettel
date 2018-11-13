package com.viettel.wms.business;

import java.util.List;

public interface IProjInvestProjectBusiness {

    long count();
    
    public List<String> getFromProjectCode(String code);
}
