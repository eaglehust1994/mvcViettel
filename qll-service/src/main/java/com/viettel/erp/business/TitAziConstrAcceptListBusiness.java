package com.viettel.erp.business;

import java.util.List;

import com.viettel.erp.dto.TitAziConstrAcceptListDTO;

public interface TitAziConstrAcceptListBusiness {

    long count();
   List<TitAziConstrAcceptListDTO> listById(Long titAziConstrAcceptId);
   boolean deleteList(List<String> listString);
}
