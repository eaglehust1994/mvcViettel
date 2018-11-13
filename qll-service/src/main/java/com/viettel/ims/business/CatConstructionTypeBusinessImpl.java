package com.viettel.ims.business;
 
import java.util.List;
import com.viettel.ims.bo.CatConstructionTypeBO;
import com.viettel.ims.dao.CatConstructionTypeDAO;
import com.viettel.ims.dto.CatConstructionTypeDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("catConstructionTypeBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CatConstructionTypeBusinessImpl extends BaseFWBusinessImpl<CatConstructionTypeDAO,CatConstructionTypeDTO, CatConstructionTypeBO> implements CatConstructionTypeBusiness {

    @Autowired
    private CatConstructionTypeDAO catConstructionTypeDAO;
     
    public CatConstructionTypeBusinessImpl() {
        tModel = new CatConstructionTypeBO();
        tDAO = catConstructionTypeDAO;
    }

    @Override
    public CatConstructionTypeDAO gettDAO() {
        return catConstructionTypeDAO;
    }
	
	@Override
	public CatConstructionTypeDTO findByCode(String value) {
		return catConstructionTypeDAO.findByCode(value);
	}

	@Override
	public List<CatConstructionTypeDTO> doSearch(CatConstructionTypeDTO obj) {
		return catConstructionTypeDAO.doSearch(obj);
	}	
	
	@Override
	public List<CatConstructionTypeDTO> getForAutoComplete(CatConstructionTypeDTO query) {
		return catConstructionTypeDAO.getForAutoComplete(query);
	}

	public String delete(List<Long> ids, String tableName, String tablePrimaryKey) {
		return catConstructionTypeDAO.delete(ids, tableName, tablePrimaryKey);	
	}
	

	public CatConstructionTypeDTO getById(Long id) {
		return catConstructionTypeDAO.getById(id);
	}

	@Override
	public List<CatConstructionTypeDTO> getForComboBox(
			CatConstructionTypeDTO query) {
		return catConstructionTypeDAO.getForComboBox(query);
	}
}
