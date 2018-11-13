package com.viettel.ims.business;
 
import java.util.List;
import com.viettel.ims.bo.CatWorkItemTypeBO;
import com.viettel.ims.dao.CatWorkItemTypeDAO;
import com.viettel.ims.dto.CatWorkItemTypeDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("catWorkItemTypeBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CatWorkItemTypeBusinessImpl extends BaseFWBusinessImpl<CatWorkItemTypeDAO,CatWorkItemTypeDTO, CatWorkItemTypeBO> implements CatWorkItemTypeBusiness {

    @Autowired
    private CatWorkItemTypeDAO catWorkItemTypeDAO;
     
    public CatWorkItemTypeBusinessImpl() {
        tModel = new CatWorkItemTypeBO();
        tDAO = catWorkItemTypeDAO;
    }

    @Override
    public CatWorkItemTypeDAO gettDAO() {
        return catWorkItemTypeDAO;
    }
	
	@Override
	public CatWorkItemTypeDTO findByCode(String value) {
		return catWorkItemTypeDAO.findByCode(value);
	}

	@Override
	public List<CatWorkItemTypeDTO> doSearch(CatWorkItemTypeDTO obj) {
		return catWorkItemTypeDAO.doSearch(obj);
	}	
	//20180224_hoanm1_start
	@Override
	public List<CatWorkItemTypeDTO> doSearchExport(CatWorkItemTypeDTO obj) {
		return catWorkItemTypeDAO.doSearchExport(obj);
	}	
	//20180224_hoanm1_end
	
	@Override
	public List<CatWorkItemTypeDTO> getForAutoComplete(CatWorkItemTypeDTO query) {
		return catWorkItemTypeDAO.getForAutoComplete(query);
	}


	public CatWorkItemTypeDTO getById(Long id) {
		return catWorkItemTypeDAO.getById(id);
	}

	@Override
	public List<CatWorkItemTypeDTO> getForComboBox(CatWorkItemTypeDTO query) {
		return catWorkItemTypeDAO.getForComboBox(query);
	}
}
