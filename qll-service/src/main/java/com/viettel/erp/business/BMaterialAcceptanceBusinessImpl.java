package com.viettel.erp.business;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.viettel.erp.bo.BMaterialAcceptanceBO;
import com.viettel.erp.dao.BMaterialAcceptanceDAO;
import com.viettel.erp.dto.BMaterialAcceptMerListDTO;
import com.viettel.erp.dto.BMaterialAcceptanceDTO;
import com.viettel.erp.dto.approDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;


@Service("bMaterialAcceptanceBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class BMaterialAcceptanceBusinessImpl extends BaseFWBusinessImpl<BMaterialAcceptanceDAO,BMaterialAcceptanceDTO, BMaterialAcceptanceBO> implements BMaterialAcceptanceBusiness {

    @Autowired
    private BMaterialAcceptanceDAO bMaterialAcceptanceDAO;
    

     
    public BMaterialAcceptanceBusinessImpl() {
        tModel = new BMaterialAcceptanceBO();
        tDAO = bMaterialAcceptanceDAO;
    }

    @Override
    public BMaterialAcceptanceDAO gettDAO() {
        return bMaterialAcceptanceDAO;
    }

    @Override
    public long count() {
        return bMaterialAcceptanceDAO.count("BMaterialAcceptanceBO", null);
    }
	@Override
	public List<BMaterialAcceptanceDTO> findByConstructId(BMaterialAcceptanceDTO dto) {
		return bMaterialAcceptanceDAO.findByConstructId(dto);
	}
	
	@Override
	public boolean deleteResult(List<String> listString) {
		return bMaterialAcceptanceDAO.deleteResult(listString);
	}
//	@Override
//	public List<CatEmployeeDTO> getListEmployeeByRole(SettlementRightDTO rightDTO) {
//		return bMaterialAcceptanceDAO.getListEmployeeByRole(rightDTO);
//	}
//    
	@Override
	public String autoGenCode() {
		return bMaterialAcceptanceDAO.autoGenCode();
	}

@Override
public BMaterialAcceptanceDTO exportBMA(BMaterialAcceptanceDTO dto) {
	
	return bMaterialAcceptanceDAO.exportListBmaterial(dto) ;
}

@Override
public List<BMaterialAcceptMerListDTO> getBMAMerList(Long bmaterialAcceptanceId) {
	
	return bMaterialAcceptanceDAO.getAccpetMerList(bmaterialAcceptanceId);
}



public Long appro(approDTO obj ) {
	return bMaterialAcceptanceDAO.appro(obj);
}

@Override
public Long getconstrCompReMapId(Long id) {
	// TODO Auto-generated method stub
	
	return bMaterialAcceptanceDAO.getconstrCompReMapId(id);
}

@Override
public boolean deleteListEntity(List<Long> listId) {
	// TODO Auto-generated method stub
	return bMaterialAcceptanceDAO.deleteListEntity(listId);
}
	
	
	
}
