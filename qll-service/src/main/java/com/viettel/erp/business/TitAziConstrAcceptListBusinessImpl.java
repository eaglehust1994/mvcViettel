package com.viettel.erp.business;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.viettel.erp.bo.TitAziConstrAcceptListBO;
import com.viettel.erp.dao.TitAziConstrAcceptListDAO;
import com.viettel.erp.dto.TitAziConstrAcceptListDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;


@Service("titAziConstrAcceptListBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TitAziConstrAcceptListBusinessImpl extends BaseFWBusinessImpl<TitAziConstrAcceptListDAO,TitAziConstrAcceptListDTO, TitAziConstrAcceptListBO> implements TitAziConstrAcceptListBusiness {

    @Autowired
    private TitAziConstrAcceptListDAO titAziConstrAcceptListDAO;
    
    @Override
    public List<TitAziConstrAcceptListDTO> listById(Long titAziConstrAcceptId){
    	return Lists.newArrayList(Iterables.transform(titAziConstrAcceptListDAO.getListById(titAziConstrAcceptId), arg0 -> arg0.toDTO()));
    }
    
  
     
    public TitAziConstrAcceptListBusinessImpl() {
        tModel = new TitAziConstrAcceptListBO();
        tDAO = titAziConstrAcceptListDAO;
    }

    @Override
    public TitAziConstrAcceptListDAO gettDAO() {
        return titAziConstrAcceptListDAO;
    }

    @Override
    public long count() {
        return titAziConstrAcceptListDAO.count("TitAziConstrAcceptListBO", null);
    }



	@Override
	public boolean deleteList(List<String> listString) {
		return titAziConstrAcceptListDAO.deleteList(listString);
	}



    

    
}
