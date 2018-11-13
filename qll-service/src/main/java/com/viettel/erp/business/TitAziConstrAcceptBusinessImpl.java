package com.viettel.erp.business;
 
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.viettel.erp.bo.TitAziConstrAcceptBO;
import com.viettel.erp.dao.TitAziConstrAcceptDAO;
import com.viettel.erp.dao.TitAziConstrAcceptListDAO;
import com.viettel.erp.dto.ConstrConstructionsDTO;
import com.viettel.erp.dto.TitAziConstrAcceptDTO;
import com.viettel.erp.dto.TitAziConstrAcceptListDTO;
import com.viettel.erp.dto.approDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("titAziConstrAcceptBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TitAziConstrAcceptBusinessImpl extends BaseFWBusinessImpl<TitAziConstrAcceptDAO,TitAziConstrAcceptDTO, TitAziConstrAcceptBO> implements TitAziConstrAcceptBusiness {

    @Autowired
    private TitAziConstrAcceptDAO titAziConstrAcceptDAO;
    
    private TitAziConstrAcceptListDAO titAziConstrAcceptListDAO;
    /*public List<TitAziConstrAcceptDTO> listById(Long constructId){
    	return Lists.newArrayList(Iterables.transform(titAziConstrAcceptDAO.getListById(constructId), arg0 -> arg0.toDTO()));
    }*/
     
    public TitAziConstrAcceptBusinessImpl() {
        tModel = new TitAziConstrAcceptBO();
        tDAO = titAziConstrAcceptDAO;
    }

    @Override
    public TitAziConstrAcceptDAO gettDAO() {
        return titAziConstrAcceptDAO;
    }

    @Override
    public long count() {
        return titAziConstrAcceptDAO.count("TitAziConstrAcceptBO", null);
    }

	
	@Override
	public List<TitAziConstrAcceptDTO> findByConstructId(TitAziConstrAcceptDTO dto) {
		return titAziConstrAcceptDAO.findByConstructId(dto);
	}
	
	@Override
	public String autoGenCode() {
		return titAziConstrAcceptDAO.autoGenCode();
	}

	public boolean deleteList(List<String> listId) {
		return titAziConstrAcceptDAO.deleteList(listId);
	}

	@Override
	public TitAziConstrAcceptDTO getExportTitAzi(TitAziConstrAcceptDTO dto) {
		return titAziConstrAcceptDAO.getExportTitAzi(dto);
	}
	
	@Override
    public List<TitAziConstrAcceptListDTO> listById(Long titAziConstrAcceptId){
    	return Lists.newArrayList(Iterables.transform(titAziConstrAcceptDAO.getListbangcon(titAziConstrAcceptId), arg0 -> arg0.toDTO()));
    }

	@Override
	public boolean pheduyet(TitAziConstrAcceptDTO dto) {
		return titAziConstrAcceptDAO.pheduyet(dto);
		
	}
	 
	public boolean lastSignPeople(approDTO obj) {
		
		return titAziConstrAcceptDAO.lastSignPeople(obj);
	}

	public Long appro(approDTO obj) {
		return titAziConstrAcceptDAO.appro(obj);
	}

	@Override
	public boolean updateIsActive(Long titAziConstrAcceptId) {
		// TODO Auto-generated method stub
		return titAziConstrAcceptDAO.updateIsActive(titAziConstrAcceptId);
	}

	
	

	
	

	
    
    

    
}
