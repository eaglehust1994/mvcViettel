package com.viettel.ims.business;

import java.util.List;

import com.viettel.ims.dto.BiddingPackageDTO;
import com.viettel.service.base.dto.DataListDTO;


public interface BiddingPackageBusiness {

    long getTotal();

    public List<BiddingPackageDTO> importBiddingPackage (String fileInput);
    
	List<BiddingPackageDTO> getFileDrop();
	

}
