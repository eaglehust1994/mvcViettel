package com.viettel.erp.business;

import java.util.List;

import com.viettel.erp.dto.ConstrWorkCompConfListLessDTO;
import com.viettel.erp.dto.ConstrWorkCompConfirmDTO;
import com.viettel.erp.dto.ConstrWorkCompConfirmExportDTO;

public interface ConstrWorkCompConfirmBusiness {

    long count();
    
	List<ConstrWorkCompConfirmDTO> getCWorkCompleteByContrId(long id);
	
	public boolean deleteListEntity(List<Long> listId);
	public  String getCode( String tableName, String value);
	public String getNameEmployee(Long id);
	List<ConstrWorkCompConfListLessDTO> getListConstrWorkExistByConstrId(List<Long> Listid);
	public ConstrWorkCompConfirmExportDTO getConstructInfoById(long id);
	public Long getconstrCompReMapId(Long id);
	public Long saveTable(ConstrWorkCompConfirmDTO completionDrawing);
}
