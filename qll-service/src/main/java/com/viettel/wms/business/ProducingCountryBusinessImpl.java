package com.viettel.wms.business;
 
import com.viettel.wms.bo.ProducingCountryBO;
import com.viettel.wms.dao.ProducingCountryDAO;
import com.viettel.wms.dto.ProducingCountryDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("producingCountryBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ProducingCountryBusinessImpl extends BaseFWBusinessImpl<ProducingCountryDAO,ProducingCountryDTO, ProducingCountryBO> implements ProducingCountryBusiness {

    @Autowired
    private ProducingCountryDAO producingCountryDAO;
    

     
    public ProducingCountryBusinessImpl() {
        tModel = new ProducingCountryBO();
        tDAO = producingCountryDAO;
    }

    @Override
    public ProducingCountryDAO gettDAO() {
        return producingCountryDAO;
    }

    @Override
    public long count() {
        return producingCountryDAO.count("ProducingCountryBO", null);
    }

    //Tim kiem tat ca ten va id - NgocCX
	@Override
	public List<ProducingCountryDTO> getAllNameAndId() {
		// TODO Auto-generated method stub
		return producingCountryDAO.getAllNameAndId();
	}

	@Override
	public List<ProducingCountryDTO> getForAutocomplete(ProducingCountryDTO obj) {
		// TODO Auto-generated method stub
		return producingCountryDAO.getForAutoComplete(obj);
	}
	
	//Tim kiem ten bang id - NgocCX
	@Override
	public ProducingCountryDTO getAllNameById(Long id) {
		// TODO Auto-generated method stub
		return producingCountryDAO.getAllNameById(id);
	}

    

    
}
