package com.viettel.erp.business;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.viettel.erp.bo.CatPartnersBO;
import com.viettel.erp.dao.CatPartnersDAO;
import com.viettel.erp.dto.CatPartnersDTO;
import com.viettel.erp.dto.CatPartnersSearchDTO;
import com.viettel.erp.dto.TotalNumDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;


@Service("catPartnersBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CatPartnersBusinessImpl extends BaseFWBusinessImpl<CatPartnersDAO,CatPartnersDTO, CatPartnersBO> implements CatPartnersBusiness {

    @Autowired
    private CatPartnersDAO catPartnersDAO;
    

     
    public CatPartnersBusinessImpl() {
        tModel = new CatPartnersBO();
        tDAO = catPartnersDAO;
    }

    @Override
    public CatPartnersDAO gettDAO() {
        return catPartnersDAO;
    }

    @Override
    public long count() {
        return catPartnersDAO.count("CatPartnersBO", null);
    }
    
    @Override
    public List<CatPartnersDTO> getPartnersName() {
    	return catPartnersDAO.getPartnersName();
    };
	public List<CatPartnersDTO> doSearch(CatPartnersSearchDTO dto , TotalNumDTO totalnum) {
		return Lists.newArrayList(Iterables.transform(catPartnersDAO.doSearch(dto,totalnum), arg0 -> arg0.toDTO()));
	}

	@Override
	public List<CatPartnersDTO> getForAutoComplete(CatPartnersDTO query) {
		return catPartnersDAO.getForAutoComplete(query);
	}
    
}
