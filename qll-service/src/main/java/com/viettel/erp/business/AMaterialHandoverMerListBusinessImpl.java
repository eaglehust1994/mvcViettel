package com.viettel.erp.business;
 
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.viettel.erp.bo.AMaterialHandoverMerListBO;
import com.viettel.erp.dao.AMaterialHandoverMerListDAO;
import com.viettel.erp.dto.AMaterialHandoverDTO;
import com.viettel.erp.dto.AMaterialHandoverMerListDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("aMaterialHandoverMerListBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AMaterialHandoverMerListBusinessImpl extends BaseFWBusinessImpl<AMaterialHandoverMerListDAO,AMaterialHandoverMerListDTO, AMaterialHandoverMerListBO> implements AMaterialHandoverMerListBusiness {

    @Autowired
    private AMaterialHandoverMerListDAO aMaterialHandoverMerListDAO;
    

     
    public AMaterialHandoverMerListBusinessImpl() {
        tModel = new AMaterialHandoverMerListBO();
        tDAO = aMaterialHandoverMerListDAO;
    }

    @Override
    public AMaterialHandoverMerListDAO gettDAO() {
        return aMaterialHandoverMerListDAO;
    }

    @Override
    public long count() {
        return aMaterialHandoverMerListDAO.count("AMaterialHandoverMerListBO", null);
    }

//	public Long addListAMaterial(List<AMaterialHandoverMerListBO> listBTVT) {
//		return aMaterialHandoverMerListDAO.addListAMaterial(listBTVT);
//	}

	public List<AMaterialHandoverMerListDTO> getListAMaterial(List<String> listPXK) {
		return aMaterialHandoverMerListDAO.getListAMaterial(listPXK);
	}

	public List<AMaterialHandoverMerListDTO> getListAMaterialHandOverMerList(AMaterialHandoverDTO dto) {
		return aMaterialHandoverMerListDAO.getListAMaterialHandOverMerList(dto);
	}
	
    public List<AMaterialHandoverMerListDTO> getAmaterialhandoverforcontruction(Long constructId) {
 		return Lists.newArrayList(Iterables.transform(aMaterialHandoverMerListDAO.getAmaterialhandoverforcontruction(constructId), arg0 -> arg0.toDTO()));
 	}
    
	public List<AMaterialHandoverMerListDTO> getAmaterialhandoverforcontructionX(Long constructId) {
		return aMaterialHandoverMerListDAO.getAmaterialhandoverforcontructionX(constructId);
	}

	public Double getSldabangiao(AMaterialHandoverMerListDTO dto) {
		return aMaterialHandoverMerListDAO.getSldabangiao(dto);
	}
    
}
