package com.viettel.erp.business;
 
import com.viettel.erp.bo.DistanceUnloadListBO;
import com.viettel.erp.dao.DistanceUnloadListDAO;
import com.viettel.erp.dto.DistanceUnloadListDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("distanceUnloadListBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class DistanceUnloadListBusinessImpl extends BaseFWBusinessImpl<DistanceUnloadListDAO,DistanceUnloadListDTO, DistanceUnloadListBO> implements DistanceUnloadListBusiness {

    @Autowired
    private DistanceUnloadListDAO distanceUnloadListDAO;
    

     
    public DistanceUnloadListBusinessImpl() {
        tModel = new DistanceUnloadListBO();
        tDAO = distanceUnloadListDAO;
    }

    @Override
    public DistanceUnloadListDAO gettDAO() {
        return distanceUnloadListDAO;
    }

    @Override
    public long count() {
        return distanceUnloadListDAO.count("DistanceUnloadListBO", null);
    }

	@Override
	public List<DistanceUnloadListDTO> doSearchByDisUnloadConsMinId(Long disUnloadConsMinId) {
		return distanceUnloadListDAO.doSearchByDisUnloadConsMinId(disUnloadConsMinId);
	}

    

    
}
