package com.viettel.wms.business;
 
import com.viettel.wms.bo.ManufacturerBO;
import com.viettel.wms.dao.ManufacturerDAO;
import com.viettel.wms.dto.ManufacturerDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("manufacturerBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ManufacturerBusinessImpl extends BaseFWBusinessImpl<ManufacturerDAO,ManufacturerDTO, ManufacturerBO> implements ManufacturerBusiness {

    @Autowired
    private ManufacturerDAO manufacturerDAO;
    

     
    public ManufacturerBusinessImpl() {
        tModel = new ManufacturerBO();
        tDAO = manufacturerDAO;
    }

    @Override
    public ManufacturerDAO gettDAO() {
        return manufacturerDAO;
    }

    @Override
    public long count() {
        return manufacturerDAO.count("ManufacturerBO", null);
    }

    //Tim kiem tat ca ten va id - NgocCX
	@Override
	public List<ManufacturerDTO> getAllNameAndId() {
		// TODO Auto-generated method stub
		return manufacturerDAO.getAllNameAndId();
	}

	@Override
	public List<ManufacturerDTO> getForAutocomplete(ManufacturerDTO obj) {
		return manufacturerDAO.getForAutoComplete(obj);
	}
	
	//Tim kiem ten bang id - NgocCX
	@Override
	public ManufacturerDTO getAllNameById(Long id) {
		// TODO Auto-generated method stub
		return manufacturerDAO.getAllNameById(id);
	}

    

    
}
