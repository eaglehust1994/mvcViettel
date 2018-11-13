package com.viettel.ims.business;
 
import java.util.List;
import com.viettel.ims.bo.CntContractOrderBO;
import com.viettel.ims.dao.CntContractOrderDAO;
import com.viettel.ims.dto.CntContractOrderDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("cntContractOrderBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CntContractOrderBusinessImpl extends BaseFWBusinessImpl<CntContractOrderDAO,CntContractOrderDTO, CntContractOrderBO> implements CntContractOrderBusiness {

    @Autowired
    private CntContractOrderDAO cntContractOrderDAO;
     
    public CntContractOrderBusinessImpl() {
        tModel = new CntContractOrderBO();
        tDAO = cntContractOrderDAO;
    }

    @Override
    public CntContractOrderDAO gettDAO() {
        return cntContractOrderDAO;
    }
	
	@Override
	public CntContractOrderDTO findByValue(String value) {
		return cntContractOrderDAO.findByValue(value);
	}

	@Override
	public List<CntContractOrderDTO> doSearch(CntContractOrderDTO obj) {
		return cntContractOrderDAO.doSearch(obj);
	}	
	
	@Override
	public List<CntContractOrderDTO> getForAutoComplete(CntContractOrderDTO query) {
		return cntContractOrderDAO.getForAutoComplete(query);
	}

	public String delete(List<Long> ids, String tableName, String tablePrimaryKey) {
		return cntContractOrderDAO.delete(ids, tableName, tablePrimaryKey);	
	}
	
	public CntContractOrderDTO getById(Long id) {
		return cntContractOrderDAO.getById(id);
	}
}
