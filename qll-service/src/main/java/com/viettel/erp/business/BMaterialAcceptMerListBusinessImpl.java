package com.viettel.erp.business;
 
import com.viettel.erp.bo.BMaterialAcceptMerListBO;
import com.viettel.erp.dao.BMaterialAcceptMerListDAO;
import com.viettel.erp.dto.BMaterialAcceptMerListDTO;
import com.viettel.erp.dto.BMaterialAcceptanceDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("bMaterialAcceptMerListBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class BMaterialAcceptMerListBusinessImpl extends BaseFWBusinessImpl<BMaterialAcceptMerListDAO,BMaterialAcceptMerListDTO, BMaterialAcceptMerListBO> implements BMaterialAcceptMerListBusiness {

    @Autowired
    private BMaterialAcceptMerListDAO bMaterialAcceptMerListDAO;
    

     
    public BMaterialAcceptMerListBusinessImpl() {
        tModel = new BMaterialAcceptMerListBO();
        tDAO = bMaterialAcceptMerListDAO;
    }

    @Override
    public BMaterialAcceptMerListDAO gettDAO() {
        return bMaterialAcceptMerListDAO;
    }

    @Override
    public long count() {
        return bMaterialAcceptMerListDAO.count("BMaterialAcceptMerListBO", null);
    }
	@Override
	public List<BMaterialAcceptMerListDTO> getAccpetMerList(Long bMaterialAcceptanceId) {
		return bMaterialAcceptMerListDAO.getAccpetMerList(bMaterialAcceptanceId);
	}
    

    
}
