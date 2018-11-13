package com.viettel.ims.business;

import java.util.List;
import com.viettel.ims.dto.${tbl.tableNameJV}DTO;
import com.viettel.ims.bo.${tbl.tableNameJV}BO;

/**
 * @author hailh10
 */

public interface ${tbl.tableNameJV}Business {

    long count();

	${tbl.tableNameJV}DTO findByValue(String value);

	List<${tbl.tableNameJV}DTO> getAll();

	List<${tbl.tableNameJV}DTO> doSearch(${tbl.tableNameJV}DTO obj);
	
	List<${tbl.tableNameJV}BO> getForAutoComplete(${tbl.tableNameJV}DTO query);
}
