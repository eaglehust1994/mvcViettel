package com.viettel.erp.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.viettel.erp.bo.SceneGenerateWorkListBO;
import com.viettel.erp.dao.SceneGenerateWorkListDAO;
import com.viettel.erp.dto.EstimatesWorkItemsDTO;
import com.viettel.erp.dto.SceneGenerateWorkDTO;
import com.viettel.erp.dto.SceneGenerateWorkListDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;

@Service("sceneGenerateWorkListBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SceneGenerateWorkListBusinessImpl
		extends BaseFWBusinessImpl<SceneGenerateWorkListDAO, SceneGenerateWorkListDTO, SceneGenerateWorkListBO>
		implements SceneGenerateWorkListBusiness {

	@Autowired
	private SceneGenerateWorkListDAO sceneGenerateWorkListDAO;

	public SceneGenerateWorkListBusinessImpl() {
		tModel = new SceneGenerateWorkListBO();
		tDAO = sceneGenerateWorkListDAO;
	}

	@Override
	public SceneGenerateWorkListDAO gettDAO() {
		return sceneGenerateWorkListDAO;
	}

	@Override
	public long count() {
		return sceneGenerateWorkListDAO.count("SceneGenerateWorkListBO", null);
	}

	@Override
	public List<EstimatesWorkItemsDTO> getAllSceneGenerateWork(Long constructId) {
		return sceneGenerateWorkListDAO.getAllSceneGenerateWork(constructId);
	}

	@Override
	public List<EstimatesWorkItemsDTO> doSearchSceneGenerateWork(EstimatesWorkItemsDTO criteria) {
		return sceneGenerateWorkListDAO.doSearchSceneGenerateWork(criteria);
	}

	@Override
	public List<SceneGenerateWorkListDTO> doSearchSceneGenerateWorkList(SceneGenerateWorkDTO criteria) {
		return sceneGenerateWorkListDAO.doSearchSceneGenerateWorkList(criteria);
	}
	
	@Transactional
	public SceneGenerateWorkDTO doCRUD(SceneGenerateWorkDTO dto) {
		return sceneGenerateWorkListDAO.doCRUD(dto);
	}

}
