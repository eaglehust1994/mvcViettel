package com.viettel.wms.business;
 
import com.viettel.wms.bo.ICntContractBO;
import com.viettel.wms.dao.ICntContractDAO;
import com.viettel.wms.dto.ICntContractDTO;
import com.viettel.wms.dto.IProjInvestProjectDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("iCntContractBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ICntContractBusinessImpl extends BaseFWBusinessImpl<ICntContractDAO,ICntContractDTO, ICntContractBO> implements ICntContractBusiness {

    @Autowired
    private ICntContractDAO iCntContractDAO;
    

     
    public ICntContractBusinessImpl() {
        tModel = new ICntContractBO();
        tDAO = iCntContractDAO;
    }

    @Override
    public ICntContractDAO gettDAO() {
        return iCntContractDAO;
    }

    @Override
    public long count() {
        return iCntContractDAO.count("ICntContractBO", null);
    }

    @Override
    public List<ICntContractDTO> getForAutoComplete(ICntContractDTO obj) {
		return iCntContractDAO.getForAutoComplete(obj);
	}

    @Override
    public DataListDTO doSearch(ICntContractDTO obj) {
    	List<ICntContractDTO> ls = iCntContractDAO.doSearch(obj);
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		data.setTotal(obj.getTotalRecord());
		data.setSize(obj.getTotalRecord());
		data.setStart(1);
		return data;
	}
}
