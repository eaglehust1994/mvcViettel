package com.viettel.erp.business;
 
import com.viettel.erp.bo.AssetManageReqEntityBO;
import com.viettel.erp.dao.AssetManageReqEntityDAO;
import com.viettel.erp.dto.AssetManageReqEntityDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("assetManageReqEntityBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AssetManageReqEntityBusinessImpl extends BaseFWBusinessImpl<AssetManageReqEntityDAO,AssetManageReqEntityDTO, AssetManageReqEntityBO> implements AssetManageReqEntityBusiness {

    @Autowired
    private AssetManageReqEntityDAO assetManageReqEntityDAO;
    

     
    public AssetManageReqEntityBusinessImpl() {
        tModel = new AssetManageReqEntityBO();
        tDAO = assetManageReqEntityDAO;
    }

    @Override
    public AssetManageReqEntityDAO gettDAO() {
        return assetManageReqEntityDAO;
    }

    @Override
    public long count() {
        return assetManageReqEntityDAO.count("AssetManageReqEntityBO", null);
    }

    

    
}
