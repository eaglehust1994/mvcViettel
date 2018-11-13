package com.viettel.erp.business;
import java.util.List;

import com.viettel.erp.dto.CatPartnersDTO;

public interface CatPartnersBusiness {

    long count();
    
    List<CatPartnersDTO> getPartnersName();
    
    List<CatPartnersDTO> getForAutoComplete(CatPartnersDTO query);
}
