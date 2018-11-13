package com.viettel.erp.business;

import java.util.List;

import com.viettel.erp.bo.AMaterialRecoveryMinutesBO;
import com.viettel.erp.dto.AMaterialRecoveryListDTO;
import com.viettel.erp.dto.AMaterialRecoveryListModelDTO;
import com.viettel.erp.dto.AMaterialRecoveryMinutesDTO;
import com.viettel.erp.dto.AMaterialRecoveryMinutesModelDTO;

public interface AMaterialRecoveryMinutesBusiness {

	long count();

	List<AMaterialRecoveryMinutesModelDTO> findByConstructId(AMaterialRecoveryMinutesModelDTO recoveryMinutesModelDTO);

	boolean deleteAMaterialMinutes(List<String> listAMaterialRecoveryMinutesId);

	List<AMaterialRecoveryListModelDTO> device(Long constructId);

	List<AMaterialRecoveryListModelDTO> materials(Long constructId);
	
	List<AMaterialRecoveryListDTO> checkDevice(Long constructId);

	List<AMaterialRecoveryListDTO> checkMaterials(Long constructId);
	
	List<AMaterialRecoveryListDTO> checkSum(Long constructId);
	
	List<AMaterialRecoveryListDTO> getCheckTwoList(List<AMaterialRecoveryListDTO> device ,List<AMaterialRecoveryListDTO> materials );

	String autoGenCode();

	List<AMaterialRecoveryListDTO> updateRecoveryList(Long amaterialRecoveryMinutesId);

	AMaterialRecoveryListModelDTO getExport(Long amaterialRecoveryMinutesId);

	Long saveTable(AMaterialRecoveryMinutesDTO completionDrawing);

	AMaterialRecoveryMinutesDTO checkInsert(AMaterialRecoveryMinutesDTO obj);
	
	List<AMaterialRecoveryListModelDTO> getTwoList(List<AMaterialRecoveryListModelDTO> device,List<AMaterialRecoveryListModelDTO> materials);
}
