package com.viettel.erp.business;
 
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.viettel.erp.bo.VConstructionHcqtBO;
import com.viettel.erp.dao.VConstructionHcqtDAO;
import com.viettel.erp.dao.VSysUserDAO;
import com.viettel.erp.dto.TotalNumDTO;
import com.viettel.erp.dto.VConstrConstructionsDTO;
import com.viettel.erp.dto.VConstrConstructionsSearchDTO;
import com.viettel.erp.dto.VConstructionHcqtDTO;
import com.viettel.erp.dto.VConstructionsHcqtSearchDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("vConstructionHcqtBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class VConstructionHcqtBusinessImpl extends BaseFWBusinessImpl<VConstructionHcqtDAO,VConstructionHcqtDTO, VConstructionHcqtBO> implements VConstructionHcqtBusiness {

    @Autowired
    private VConstructionHcqtDAO vConstructionHcqtDAO;
    
    @Autowired
    private VSysUserDAO vSysUserDAO;
     
    public VConstructionHcqtBusinessImpl() {
        tModel = new VConstructionHcqtBO();
        tDAO = vConstructionHcqtDAO;
    }

    @Override
    public VConstructionHcqtDAO gettDAO() {
        return vConstructionHcqtDAO;
    }

    @Override
    public long count() {
        return vConstructionHcqtDAO.count("VConstructionHcqtBO", null);
    }

	public List<VConstructionHcqtDTO> getAllandSearch(VConstructionsHcqtSearchDTO dto, TotalNumDTO totalnum) {
		

		return Lists.newArrayList(Iterables.transform(vConstructionHcqtDAO.getAllandSearch(dto,totalnum), arg0 -> arg0.toDTO()));
	}
	
	public String queryCount() {
		return vConstructionHcqtDAO.queryCount();
	}

	@Override
	public List<VConstructionHcqtDTO> getContractTotalValueById(Long contractId) {
		return vConstructionHcqtDAO.getContractTotalValue(contractId);
	}

    
}
