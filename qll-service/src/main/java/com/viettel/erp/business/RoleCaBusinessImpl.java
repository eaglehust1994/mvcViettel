package com.viettel.erp.business;
 
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.viettel.erp.bo.RoleCaBO;
import com.viettel.erp.dao.RoleCaDAO;
import com.viettel.erp.dto.RoleCaDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("roleCaBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RoleCaBusinessImpl extends BaseFWBusinessImpl<RoleCaDAO,RoleCaDTO, RoleCaBO> implements RoleCaBusiness {

    @Autowired
    private RoleCaDAO roleCaDAO;
    

     
    public RoleCaBusinessImpl() {
        tModel = new RoleCaBO();
        tDAO = roleCaDAO;
    }

    @Override
    public RoleCaDAO gettDAO() {
        return roleCaDAO;
    }

    @Override
    public long count() {
        return roleCaDAO.count("RoleCaBO", null);
    }
    
    public List<RoleCaDTO> getRoleCaByType(Long type, Long groupSide) {
    	return Lists.newArrayList(Iterables
				.transform(roleCaDAO.getRoleCaByType(type, groupSide), f -> f.toDTO()));
    	
    }

	public List<RoleCaDTO> getListChucVu(RoleCaDTO obj) {
		return roleCaDAO.getListChucVu(obj);
	}

    

    
}
