package com.viettel.erp.business;
 
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.viettel.erp.bo.ProjInvestProjectBO;
import com.viettel.erp.dao.ProjInvestProjectDAO;
import com.viettel.erp.dto.ProjInvestProjectDTO;
import com.viettel.erp.dto.ProjInvestProjectSearchDTO;
import com.viettel.erp.dto.TotalNumDTO;
import com.viettel.erp.dto.VConstructionHcqtDTO;
import com.viettel.erp.dto.VConstructionsHcqtSearchDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("projInvestProjectBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ProjInvestProjectBusinessImpl extends BaseFWBusinessImpl<ProjInvestProjectDAO,ProjInvestProjectDTO, ProjInvestProjectBO> implements ProjInvestProjectBusiness {

    @Autowired
    private ProjInvestProjectDAO projInvestProjectDAO;
    

     
    public ProjInvestProjectBusinessImpl() {
        tModel = new ProjInvestProjectBO();
        tDAO = projInvestProjectDAO;
    }

    @Override
    public ProjInvestProjectDAO gettDAO() {
        return projInvestProjectDAO;
    }

    @Override
    public long count() {
        return projInvestProjectDAO.count("ProjInvestProjectBO", null);
    }
	public List<ProjInvestProjectDTO> doSearch(ProjInvestProjectSearchDTO dto, TotalNumDTO totalnum) {
		return Lists.newArrayList(Iterables.transform(projInvestProjectDAO.doSearch(dto,totalnum), arg0 -> arg0.toDTO()));
	}
    
	public List<ProjInvestProjectDTO> getAllList() {
		return Lists.newArrayList(Iterables.transform(projInvestProjectDAO.getAll(), arg0 -> arg0.toDTO()));
	}
    
    
}
