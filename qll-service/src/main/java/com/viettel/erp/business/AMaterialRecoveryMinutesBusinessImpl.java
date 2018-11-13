package com.viettel.erp.business;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.viettel.erp.bo.AMaterialRecoveryMinutesBO;
import com.viettel.erp.dao.AMaterialRecoveryMinutesDAO;
import com.viettel.erp.dto.AMaterialRecoveryListDTO;
import com.viettel.erp.dto.AMaterialRecoveryListModelDTO;
import com.viettel.erp.dto.AMaterialRecoveryMinutesDTO;
import com.viettel.erp.dto.AMaterialRecoveryMinutesModelDTO;
import com.viettel.erp.rest.FileServiceImpl;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service("aMaterialRecoveryMinutesBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AMaterialRecoveryMinutesBusinessImpl
		extends BaseFWBusinessImpl<AMaterialRecoveryMinutesDAO, AMaterialRecoveryMinutesDTO, AMaterialRecoveryMinutesBO>
		implements AMaterialRecoveryMinutesBusiness {
	
	static Logger LOGGER = LoggerFactory.getLogger(AMaterialRecoveryMinutesBusinessImpl.class);

	@Autowired
	private AMaterialRecoveryMinutesDAO aMaterialRecoveryMinutesDAO;

	public AMaterialRecoveryMinutesBusinessImpl() {
		tModel = new AMaterialRecoveryMinutesBO();
		tDAO = aMaterialRecoveryMinutesDAO;
	}

	@Override
	public AMaterialRecoveryMinutesDAO gettDAO() {
		return aMaterialRecoveryMinutesDAO;
	}

	@Override
	public long count() {
		return aMaterialRecoveryMinutesDAO.count("AMaterialRecoveryMinutesBO", null);
	}

	@Override
	public List<AMaterialRecoveryMinutesModelDTO> findByConstructId(AMaterialRecoveryMinutesModelDTO recoveryMinutesModelDTO) {
		return aMaterialRecoveryMinutesDAO.findByConstructId(recoveryMinutesModelDTO);
	}

	@Override
	public boolean deleteAMaterialMinutes(List<String> listAMaterialRecoveryMinutesId) {
		return aMaterialRecoveryMinutesDAO.deleteAMaterialMinutes(listAMaterialRecoveryMinutesId);
	}

	@Override
	public List<AMaterialRecoveryListModelDTO> device(Long constructId) {
		return aMaterialRecoveryMinutesDAO.device(constructId);
	}

	@Override
	public List<AMaterialRecoveryListModelDTO> materials(Long constructId) {
		return aMaterialRecoveryMinutesDAO.materials(constructId);
	}

	@Override
	public List<AMaterialRecoveryListDTO> checkDevice(Long constructId) {
		return aMaterialRecoveryMinutesDAO.checkDevice(constructId);
	}

	@Override
	public List<AMaterialRecoveryListDTO> checkMaterials(Long constructId) {
		return aMaterialRecoveryMinutesDAO.checkMaterials(constructId);
	}
	
	@Override
	public List<AMaterialRecoveryListDTO> checkSum(Long constructId) {
		return aMaterialRecoveryMinutesDAO.checkSum(constructId);
	}

	@Override
	public List<AMaterialRecoveryListDTO> getCheckTwoList(List<AMaterialRecoveryListDTO> device,
			List<AMaterialRecoveryListDTO> materials) {
		List<AMaterialRecoveryListDTO> list = Lists.newArrayList(Iterables.concat(device, materials));
		return list;
	}

	@Override
	public List<AMaterialRecoveryListModelDTO> getTwoList(List<AMaterialRecoveryListModelDTO> device,
			List<AMaterialRecoveryListModelDTO> materials) {
		List<AMaterialRecoveryListModelDTO> list = Lists.newArrayList(Iterables.concat(device, materials));
		return list;
	}

	@Override
	public String autoGenCode() {
		return aMaterialRecoveryMinutesDAO.autoGenCode();
	}

	@Override
	public List<AMaterialRecoveryListDTO> updateRecoveryList(Long amaterialRecoveryMinutesId) {
		return aMaterialRecoveryMinutesDAO.updateRecoveryList(amaterialRecoveryMinutesId);
	}

	@Override
	public AMaterialRecoveryListModelDTO getExport(Long amaterialRecoveryMinutesId) {
		// return
		// aMaterialRecoveryMinutesDAO.getExport(amaterialRecoveryMinutesId);
		return null;
	}

	public AMaterialRecoveryMinutesModelDTO recoveryMinutesModelDTO(AMaterialRecoveryMinutesModelDTO obj) {
		return aMaterialRecoveryMinutesDAO.recoveryMinutesModelDTO(obj);
	}

	@Override
	public Long saveTable(AMaterialRecoveryMinutesDTO completionDrawing) {
		try {
			return aMaterialRecoveryMinutesDAO.saveTable(completionDrawing);
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			LOGGER.error(ex.getMessage(), ex);
		}
		return null;
	}

	public Long CheckRecovryMinutes(Long amaterialRecoveryMinutesId) {
		return aMaterialRecoveryMinutesDAO.CheckRecovryMinutes(amaterialRecoveryMinutesId);
	}

	@Override
	public AMaterialRecoveryMinutesDTO checkInsert(AMaterialRecoveryMinutesDTO obj) {
		
		List<AMaterialRecoveryListDTO> device = aMaterialRecoveryMinutesDAO.checkDevice(obj.getConstructId());
		List<AMaterialRecoveryListDTO> materials = aMaterialRecoveryMinutesDAO.checkMaterials(obj.getConstructId());
		
		
		
		List<AMaterialRecoveryListDTO> listAma = getCheckTwoList(device, materials);
		obj.setAmaterialRecoveryList(listAma);
		return obj;
	}
}
