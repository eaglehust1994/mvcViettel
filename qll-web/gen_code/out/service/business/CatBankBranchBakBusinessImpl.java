package com.viettel.ims.business;
 
import java.util.List;
import com.viettel.ims.bo.CatBankBranchBakBO;
import com.viettel.ims.dao.CatBankBranchBakDAO;
import com.viettel.ims.dto.CatBankBranchBakDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("catBankBranchBakBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CatBankBranchBakBusinessImpl extends BaseFWBusinessImpl<CatBankBranchBakDAO,CatBankBranchBakDTO, CatBankBranchBakBO> implements CatBankBranchBakBusiness {

    @Autowired
    private CatBankBranchBakDAO catBankBranchBakDAO;
     
    public CatBankBranchBakBusinessImpl() {
        tModel = new CatBankBranchBakBO();
        tDAO = catBankBranchBakDAO;
    }

    @Override
    public CatBankBranchBakDAO gettDAO() {
        return catBankBranchBakDAO;
    }

    @Override
    public long count() {
        return catBankBranchBakDAO.count("CatBankBranchBakBO", null);
    }
	
	@Override
	public CatBankBranchBakDTO findByValue(String value) {
		return catBankBranchBakDAO.findByValue(value);
	}

	@Override
	public List<CatBankBranchBakDTO> getAll() {
		return catBankBranchBakDAO.getAll();
	}

	@Override
	public List<CatBankBranchBakDTO> doSearch(CatBankBranchBakDTO obj) {
		return catBankBranchBakDAO.doSearch(obj);
	}

	public String delete(List<Long> ids, String tableName, String tablePrimaryKey) {
		return catBankBranchBakDAO.delete(ids, tableName, tablePrimaryKey);	
	}		

	@Override
	public List<CatBankBranchBakBO> getForAutoComplete(CatBankBranchBakDTO query) {
		return catBankBranchBakDAO.getForAutoComplete(query);
	}
	
	public CatBankBranchBakDTO getById(Long id) {
		return catBankBranchBakDAO.getById(id);
	}
}
