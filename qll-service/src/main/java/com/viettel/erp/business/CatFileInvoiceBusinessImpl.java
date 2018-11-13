package com.viettel.erp.business;
 
import com.viettel.erp.bo.CatFileInvoiceBO;
import com.viettel.erp.dao.CatFileInvoiceDAO;
import com.viettel.erp.dto.CatFileInvoiceDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("catFileInvoiceBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CatFileInvoiceBusinessImpl extends BaseFWBusinessImpl<CatFileInvoiceDAO,CatFileInvoiceDTO, CatFileInvoiceBO> implements CatFileInvoiceBusiness {

    @Autowired
    private CatFileInvoiceDAO catFileInvoiceDAO;
    

     
    public CatFileInvoiceBusinessImpl() {
        tModel = new CatFileInvoiceBO();
        tDAO = catFileInvoiceDAO;
    }

    @Override
    public CatFileInvoiceDAO gettDAO() {
        return catFileInvoiceDAO;
    }

    @Override
    public long count() {
        return catFileInvoiceDAO.count("CatFileInvoiceBO", null);
    }

    @Override
    public List<CatFileInvoiceDTO> findByExistProfile(long constructionId) {
        return catFileInvoiceDAO.findByExistProfile(constructionId);
    }
    
    public CatFileInvoiceDTO findByTableName(String tableName) {
        return catFileInvoiceDAO.onlyFindByTableName(tableName);
    }

	@Override
	public CatFileInvoiceDTO onlyFindByTableName(String tableName) {
		// TODO Auto-generated method stub
		return  catFileInvoiceDAO.onlyFindByTableName( tableName);
	}

	public List<CatFileInvoiceDTO> getListInvoice() {
		return  catFileInvoiceDAO.getListInvoice();
	}


}
