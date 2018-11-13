package com.viettel.erp.business;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.viettel.erp.bo.AssetManageReqBO;
import com.viettel.erp.dao.AssetManageReqDAO;
import com.viettel.erp.dto.AssetManageReqDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;


@Service("assetManageReqBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AssetManageReqBusinessImpl extends BaseFWBusinessImpl<AssetManageReqDAO,AssetManageReqDTO, AssetManageReqBO> implements AssetManageReqBusiness {

    @Autowired
    private AssetManageReqDAO assetManageReqDAO;
    

     
    public AssetManageReqBusinessImpl() {
        tModel = new AssetManageReqBO();
        tDAO = assetManageReqDAO;
    }

    @Override
    public AssetManageReqDAO gettDAO() {
        return assetManageReqDAO;
    }

    @Override
    public long count() {
        return assetManageReqDAO.count("AssetManageReqBO", null);
    }

	@Override
	public List<AssetManageReqDTO> getValueSuppliesById(Long contractId) {
		return assetManageReqDAO.getValueSupplies(contractId);
	}

	@Override
	public List<AssetManageReqDTO> getTotalPriceById(Long contractId) {
		return assetManageReqDAO.getTotalPrice(contractId);
	}
    
}
