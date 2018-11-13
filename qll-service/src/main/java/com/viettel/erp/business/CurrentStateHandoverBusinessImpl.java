package com.viettel.erp.business;

import com.google.common.collect.Lists;
import com.viettel.erp.bo.CurrentStateHandoverBO;
import com.viettel.erp.bo.CurrentStateHandoverListBO;
import com.viettel.erp.dao.CurrentStateHandoverDAO;
import com.viettel.erp.dto.CompletionDrawingDTO;
import com.viettel.erp.dto.CurrentStateHandoverDTO;
import com.viettel.erp.dto.CurrentStateHandoverExtraDTO;
import com.viettel.erp.dto.approDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service("currentStateHandoverBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CurrentStateHandoverBusinessImpl
		extends BaseFWBusinessImpl<CurrentStateHandoverDAO, CurrentStateHandoverDTO, CurrentStateHandoverBO>
		implements CurrentStateHandoverBusiness {

	static Logger LOGGER = LoggerFactory.getLogger(CurrentStateHandoverBusinessImpl.class);
	@Autowired
	private CurrentStateHandoverDAO currentStateHandoverDAO;

	public CurrentStateHandoverBusinessImpl() {
		tModel = new CurrentStateHandoverBO();
		tDAO = currentStateHandoverDAO;
	}

	@Override
	public CurrentStateHandoverDAO gettDAO() {
		return currentStateHandoverDAO;
	}

	@Override
	public long count() {
		return currentStateHandoverDAO.count("CurrentStateHandoverBO", null);
	}

	@Override
	public List<CurrentStateHandoverDTO> getCurrentStateHandoverByContructId(long constructId) {
	/*	List<CurrentStateHandoverBO> listCurrentStateHandoverBO 
							= currentStateHandoverDAO.getListCurrentStateHandoverByContructId(constructId);
		
		ArrayList<CurrentStateHandoverDTO> listDTO = Lists.newArrayList();
		if (listCurrentStateHandoverBO.size() > 0) {
			for (CurrentStateHandoverBO obj : listCurrentStateHandoverBO) {
				listDTO.add(obj.toDTO());
			}
		}*/
		return currentStateHandoverDAO.getListCurrentStateHandoverByContructId(constructId);
		
		//return listDTO;
	}

	@Override
	public boolean deleteListEntity(List<Long> listId) {
		// TODO Auto-generated method stub
		return currentStateHandoverDAO.deleteListEntity(listId);
	}
	 public String autoGenCode(String tableName, String value){
		return currentStateHandoverDAO.autoGenCode( tableName, value);
	 }

	@Override
	public CurrentStateHandoverExtraDTO getCurrentStateHandoverExtraById(Long id) {
		return currentStateHandoverDAO.getCurrentStateHandoverExtraById(id);
	}

	@Override
	public List<CurrentStateHandoverListBO> getCurrentStateHandoverByListId(Long id) {
		// TODO Auto-generated method stub
		return currentStateHandoverDAO.getCurrentStateHandoverByListId(id);
	}

	@Override
	public String getNameEmployee(Long id) {
		// TODO Auto-generated method stub
		return currentStateHandoverDAO.getNameEmployee(id);
	}

	@Override
	public Long getconstrCompReMapId(Long id) {
		// TODO Auto-generated method stub
		return currentStateHandoverDAO.getconstrCompReMapId(id);
	}


	@Override
	public Long appro(approDTO obj) {
		// TODO Auto-generated method stub
		return currentStateHandoverDAO.appro(obj);
	}

	@Override
	public Long saveTable(CurrentStateHandoverDTO completionDrawing) {
		// TODO Auto-generated method stub
		   Long completionDrawingId;
		try {
			completionDrawingId = currentStateHandoverDAO.saveTable(completionDrawing);
			return completionDrawingId;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage(), e);
		}
		      return new Long(0);
	}

}
