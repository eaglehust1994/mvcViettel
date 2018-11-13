package com.viettel.erp.business;
 
import com.viettel.erp.bo.SceneGenerateWorkBO;
import com.viettel.erp.dao.SceneGenerateWorkDAO;
import com.viettel.erp.dto.SceneGenerateWorkDTO;
import com.viettel.erp.dto.WorkItemsAcceptanceDTO;
import com.viettel.erp.dto.approDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("sceneGenerateWorkBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SceneGenerateWorkBusinessImpl extends BaseFWBusinessImpl<SceneGenerateWorkDAO,SceneGenerateWorkDTO, SceneGenerateWorkBO> implements SceneGenerateWorkBusiness {

    @Autowired
    private SceneGenerateWorkDAO sceneGenerateWorkDAO;
    

     
    public SceneGenerateWorkBusinessImpl() {
        tModel = new SceneGenerateWorkBO();
        tDAO = sceneGenerateWorkDAO;
    }

    @Override
    public SceneGenerateWorkDAO gettDAO() {
        return sceneGenerateWorkDAO;
    }

    @Override
    public long count() {
        return sceneGenerateWorkDAO.count("SceneGenerateWorkBO", null);
    }

    //tungpv
    public List<SceneGenerateWorkDTO> doSearchSceneGenerateWork(SceneGenerateWorkDTO criteria){
    	return sceneGenerateWorkDAO.doSearchSceneGenerateWork(criteria);
    }
    public List<SceneGenerateWorkDTO> doSearchSceneGenerateWorkConstruction(SceneGenerateWorkDTO criteria){
    	return sceneGenerateWorkDAO.doSearchSceneGenerateWorkConstruction(criteria);
    }
    public List<SceneGenerateWorkDTO> getItemNameByConstrId(SceneGenerateWorkDTO criteria){
    	return sceneGenerateWorkDAO.getItemNameByConstrId(criteria);
    }
    public SceneGenerateWorkDTO getDataExport(SceneGenerateWorkDTO dto){
    	return sceneGenerateWorkDAO.getDataExport(dto);
    }
    
    @Override
	public boolean updateIsActive(List<Long> id) {
		
		return sceneGenerateWorkDAO.updateIsActive(id);
	}

	@Override
	public Long appro(approDTO obj) {
		// TODO Auto-generated method stub
		return sceneGenerateWorkDAO.appro(obj);
	}
    //end tungpv
//minhpvn approval cong trinh
	@Override
	public Long approCT(approDTO obj) {
		// TODO Auto-generated method stub
		return sceneGenerateWorkDAO.approCT(obj);
	}
    
}
