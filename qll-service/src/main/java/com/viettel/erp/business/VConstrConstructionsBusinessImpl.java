package com.viettel.erp.business;
 
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.viettel.erp.bo.VConstrConstructionsBO;
import com.viettel.erp.dao.VConstrConstructionsDAO;
import com.viettel.erp.dto.ConstrConstructionsDTO;
import com.viettel.erp.dto.ConstrConstructionsSearchDTO;
import com.viettel.erp.dto.VConstrConstructionsDTO;
import com.viettel.erp.dto.VConstrConstructionsSearchDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("vConstrConstructionsBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class VConstrConstructionsBusinessImpl extends BaseFWBusinessImpl<VConstrConstructionsDAO,VConstrConstructionsDTO, VConstrConstructionsBO> implements VConstrConstructionsBusiness {

    @Autowired
    private VConstrConstructionsDAO vConstrConstructionsDAO;
    

     
    public VConstrConstructionsBusinessImpl() {
        tModel = new VConstrConstructionsBO();
        tDAO = vConstrConstructionsDAO;
    }

    @Override
    public VConstrConstructionsDAO gettDAO() {
        return vConstrConstructionsDAO;
    }

    @Override
    public long count() {
        return vConstrConstructionsDAO.count("VConstrConstructionsBO", null);
    }

	public List<VConstrConstructionsDTO> getAllandSearch(VConstrConstructionsSearchDTO dto) {
		return Lists.newArrayList(Iterables.transform(vConstrConstructionsDAO.getAllandSearch(dto), arg0 -> arg0.toDTO()));
	}

    
}
