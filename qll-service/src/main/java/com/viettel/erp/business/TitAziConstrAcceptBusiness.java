package com.viettel.erp.business;

import java.util.List;

import com.viettel.erp.dto.TitAziConstrAcceptDTO;
import com.viettel.erp.dto.TitAziConstrAcceptListDTO;

public interface TitAziConstrAcceptBusiness {

    long count();
    /*List<TitAziConstrAcceptDTO> listById(Long constructId);*/
    
    List<TitAziConstrAcceptDTO> findByConstructId(TitAziConstrAcceptDTO dto);
     String autoGenCode() ;
     
     TitAziConstrAcceptDTO getExportTitAzi(TitAziConstrAcceptDTO dto);
     
     List<TitAziConstrAcceptListDTO> listById(Long titAziConstrAcceptId);
     
     boolean pheduyet(TitAziConstrAcceptDTO dto);
     
     boolean updateIsActive(Long titAziConstrAcceptId);
}
