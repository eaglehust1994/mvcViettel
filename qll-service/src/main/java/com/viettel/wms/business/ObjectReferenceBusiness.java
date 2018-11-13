package com.viettel.wms.business;

import java.util.List;

public interface ObjectReferenceBusiness {

    long count();
    
    List<String> getGoodsInfoBeforeWarrantyCode(String code);
    List<String> getGoodsInfoAfterWarrantyCode(String code);
    List<String> getGoodsInfoFromConstructionCode(String code);
    List<String> getGoodsInfoFromDepartmentCode(String code);
    List<String> getGoodsInfoFromLoanCode(String code);
}
