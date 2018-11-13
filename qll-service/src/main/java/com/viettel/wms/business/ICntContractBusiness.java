package com.viettel.wms.business;

import java.util.List;

import com.viettel.service.base.dto.DataListDTO;
import com.viettel.wms.dto.ICntContractDTO;

public interface ICntContractBusiness {

    long count();
    
    public List<ICntContractDTO> getForAutoComplete(ICntContractDTO obj);

    public DataListDTO doSearch(ICntContractDTO obj);
}
