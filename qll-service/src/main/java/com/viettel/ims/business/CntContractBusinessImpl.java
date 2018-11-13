package com.viettel.ims.business;
 
import java.util.List;

import com.viettel.ims.bo.CntContractBO;
import com.viettel.ims.dao.CntContractDAO;
import com.viettel.ims.dto.CntContractDTO;
import com.viettel.ims.dto.PurchaseOrderDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("cntContractBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CntContractBusinessImpl extends BaseFWBusinessImpl<CntContractDAO,CntContractDTO, CntContractBO> implements CntContractBusiness {

    @Autowired
    private CntContractDAO cntContractDAO;
    
     
    public CntContractBusinessImpl() {
        tModel = new CntContractBO();
        tDAO = cntContractDAO;
    }

    @Override
    public CntContractDAO gettDAO() {
        return cntContractDAO;
    }
	
	@Override
	public CntContractDTO findByCode(String value) {
		return cntContractDAO.findByCode(value);
	}

	@Override
	public List<CntContractDTO> doSearch(CntContractDTO obj) {
		List<CntContractDTO> result = cntContractDAO.doSearch(obj);
		for(CntContractDTO contract : result){
			if(contract != null){
				List<PurchaseOrderDTO> orderLst = cntContractDAO.getOrder(contract.getCntContractId());
				contract.setPurchaseOrderLst(orderLst);
			}
		}
		return result;
	}	
	
	@Override
	public List<CntContractDTO> getForAutoComplete(CntContractDTO query) {
		return cntContractDAO.getForAutoComplete(query);
	}

	public String delete(List<Long> ids, String tableName, String tablePrimaryKey) {
		return cntContractDAO.delete(ids, tableName, tablePrimaryKey);	
	}
	

	public CntContractDTO getById(Long id) {
		return cntContractDAO.getById(id);
	}
}
