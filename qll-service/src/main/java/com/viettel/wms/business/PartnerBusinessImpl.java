package com.viettel.wms.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.wms.bo.PartnerBO;
import com.viettel.wms.dao.PartnerDAO;
import com.viettel.wms.dto.PartnerDTO;

@Service("partnerBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PartnerBusinessImpl 
extends BaseFWBusinessImpl<PartnerDAO, PartnerDTO, PartnerBO> implements PartnerBusiness {

	@Override
	public List<String> getForAutoComplete(PartnerDTO p) {
		return partnerDAO.doSearchPartnerForAutoComplete(p);
	}
	
	@Autowired 
	PartnerDAO partnerDAO;

	@Override
	public List<PartnerDTO> getForAutoCompleteII(String obj) {
		// TODO Auto-generated method stub
		return partnerDAO.getForAutoCompleteII(obj);
	}

	public PartnerDTO getPartnerByCode(String code) {
		return partnerDAO.getPartnerByCode(code);
	}

	@Override
	public PartnerDTO getPartnerById(Long id) {
		return partnerDAO.getPartnerById(id);
	}


}
