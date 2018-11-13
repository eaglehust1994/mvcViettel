package com.viettel.erp.business;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.viettel.erp.bo.DistanceUnloadConstrMinutesBO;
import com.viettel.erp.dao.DistanceUnloadConstrMinutesDAO;
import com.viettel.erp.dto.DistanceUnloadConstrMinutesDTO;
import com.viettel.erp.dto.approDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;


@Service("distanceUnloadConstrMinutesBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class DistanceUnloadConstrMinutesBusinessImpl extends BaseFWBusinessImpl<DistanceUnloadConstrMinutesDAO,DistanceUnloadConstrMinutesDTO, DistanceUnloadConstrMinutesBO> implements DistanceUnloadConstrMinutesBusiness {

    @Autowired
    private DistanceUnloadConstrMinutesDAO distanceUnloadConstrMinutesDAO;
    

     
    public DistanceUnloadConstrMinutesBusinessImpl() {
        tModel = new DistanceUnloadConstrMinutesBO();
        tDAO = distanceUnloadConstrMinutesDAO;
    }

    @Override
    public DistanceUnloadConstrMinutesDAO gettDAO() {
        return distanceUnloadConstrMinutesDAO;
    }

    @Override
	public boolean updateIsActive(List<Long> listId) {
		return distanceUnloadConstrMinutesDAO.updateIsActive(listId);
	}
    
    @Override
    public long count() {
        return distanceUnloadConstrMinutesDAO.count("DistanceUnloadConstrMinutesBO", null);
    }

	@Override
	public List<DistanceUnloadConstrMinutesDTO> getListCR(Long constructId) {
		 return distanceUnloadConstrMinutesDAO.getListCR(constructId);
	}

	public List<DistanceUnloadConstrMinutesDTO> getAllC(DistanceUnloadConstrMinutesDTO obj) {
 		//return Lists.newArrayList(Iterables.transform(distanceUnloadConstrMinutesDAO.getAllC(constructId), arg0 -> arg0.toDTO()));
		return distanceUnloadConstrMinutesDAO.getAllC(obj);
	}
	
	public DistanceUnloadConstrMinutesDTO getDataExport(DistanceUnloadConstrMinutesDTO dto){
    	return distanceUnloadConstrMinutesDAO.getDataExport(dto);
    }

	public String autoGenCodeConstrOrganizationPlan() {
		return distanceUnloadConstrMinutesDAO.autoGenCodeConstrOrganizationPlan();
	}
	
	@Override
	public Long appro(approDTO obj) {
		return distanceUnloadConstrMinutesDAO.appro(obj);
	}
	
	public Long saveTable( DistanceUnloadConstrMinutesDTO  obj) throws Exception {
        Long id = distanceUnloadConstrMinutesDAO.saveTable(obj);
        return id;
	}
	
	public Long deleteTable(DistanceUnloadConstrMinutesDTO DistanCM) throws Exception{
        Long disUnloadConsMinId =	distanceUnloadConstrMinutesDAO.deleteTable(DistanCM);  	
        return disUnloadConsMinId;
      }
	
	public DistanceUnloadConstrMinutesDTO getByOneId(Long id) {
 		return distanceUnloadConstrMinutesDAO.getByOneId(id);
 	}
}
