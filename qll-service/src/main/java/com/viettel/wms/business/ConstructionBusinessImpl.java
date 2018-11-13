package com.viettel.wms.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.wms.bo.ConstructionBO;
import com.viettel.wms.dao.ConstructionDAO;
import com.viettel.wms.dto.ConstructionDTO;

@Service("constructionBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ConstructionBusinessImpl extends BaseFWBusinessImpl<ConstructionDAO, ConstructionDTO, ConstructionBO>
implements ConstructionBusiness{

	@Autowired 
	ConstructionDAO constructionDAO;
	
	@Override
	public List<String> doSearchConstructionForAutoComplete(ConstructionDTO o) {
		return constructionDAO.doSearchConstructionForAutoComplete(o);
	}

	@Override
	public List<ConstructionDTO> doSearchConstructionForAutoCompleteII(String code) {
		return constructionDAO.doSearchConstructionForAutoCompleteII(code);
	}

	@Override
	public ConstructionDTO getConstructionByCode(String code) {
		return constructionDAO.getConstructionByCode(code);
	}
	
}
