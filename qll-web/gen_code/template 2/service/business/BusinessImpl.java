package com.viettel.${projectCode}.business;
 
import java.util.List;
import com.viettel.${projectCode}.bo.${tbl.tableNameJV}BO;
import com.viettel.${projectCode}.dao.${tbl.tableNameJV}DAO;
import com.viettel.${projectCode}.dto.${tbl.tableNameJV}DTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("${tbl.tableNameVar}BusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ${tbl.tableNameJV}BusinessImpl extends BaseFWBusinessImpl<${tbl.tableNameJV}DAO,${tbl.tableNameJV}DTO, ${tbl.tableNameJV}BO> implements ${tbl.tableNameJV}Business {

    @Autowired
    private ${tbl.tableNameJV}DAO ${tbl.tableNameVar}DAO;
     
    public ${tbl.tableNameJV}BusinessImpl() {
        tModel = new ${tbl.tableNameJV}BO();
        tDAO = ${tbl.tableNameVar}DAO;
    }

    @Override
    public ${tbl.tableNameJV}DAO gettDAO() {
        return ${tbl.tableNameVar}DAO;
    }

    @Override
    public long count() {
        return ${tbl.tableNameVar}DAO.count("${tbl.tableNameJV}BO", null);
    }
	
	@Override
	public ${tbl.tableNameJV}DTO findByValue(String value) {
		return ${tbl.tableNameVar}DAO.findByValue(value);
	}

	@Override
	public List<${tbl.tableNameJV}DTO> getAll() {
		return ${tbl.tableNameVar}DAO.getAll();
	}

	@Override
	public List<${tbl.tableNameJV}DTO> doSearch(${tbl.tableNameJV}DTO obj) {
		return ${tbl.tableNameVar}DAO.doSearch(obj);
	}

	public String delete(List<Long> ids, String tableName, String tablePrimaryKey) {
		return ${tbl.tableNameVar}DAO.delete(ids, tableName, tablePrimaryKey);	
	}		

	@Override
	public List<${tbl.tableNameJV}BO> getForAutoComplete(${tbl.tableNameJV}DTO query) {
		return ${tbl.tableNameVar}DAO.getForAutoComplete(query);
	}
	
	public ${tbl.tableNameJV}DTO getById(Long id) {
		return ${tbl.tableNameVar}DAO.getById(id);
	}
}
