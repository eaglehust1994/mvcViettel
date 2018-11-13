package com.viettel.erp.business;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.viettel.erp.bo.AbMaterialCompareBO;
import com.viettel.erp.dao.AbMaterialCompareDAO;
import com.viettel.erp.dto.AbMaterialCompareDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;


@Service("abMaterialCompareBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AbMaterialCompareBusinessImpl extends BaseFWBusinessImpl<AbMaterialCompareDAO,AbMaterialCompareDTO, AbMaterialCompareBO> implements AbMaterialCompareBusiness {

    @Autowired
    private AbMaterialCompareDAO abMaterialCompareDAO;
    
    public AbMaterialCompareBusinessImpl() {
        tModel = new AbMaterialCompareBO();
        tDAO = abMaterialCompareDAO;
    }

    @Override
    public AbMaterialCompareDAO gettDAO() {
        return abMaterialCompareDAO;
    }

    @Override
    public long count() {
        return abMaterialCompareDAO.count("AbMaterialCompareBO", null);
    }

    public Long saveTable( AbMaterialCompareDTO  abMaterialCompare) throws Exception{
	    Long abMaterialCompareId = abMaterialCompareDAO.saveTable(abMaterialCompare);
	    return abMaterialCompareId;
    }
    
    public AbMaterialCompareDTO getById(Long constructId) {
		return abMaterialCompareDAO.getById(constructId);
	}
    
    public AbMaterialCompareDTO checkEstimateAB6(Long constructId) {
		
		return abMaterialCompareDAO.checkEstimateAB6(constructId);
	}

	@Override
	public Long getConstrComReMapId(Long idChild) {
		return abMaterialCompareDAO.getConstrComReMapId(idChild);
	}
}
