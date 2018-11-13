package com.viettel.erp.business;
 
import com.viettel.erp.bo.ConstrWorkCompConfListBO;
import com.viettel.erp.dao.ConstrWorkCompConfListDAO;
import com.viettel.erp.dto.ConstrWorkCompConfListDTO;
import com.viettel.erp.dto.ConstrWorkCompConfListLessDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("constrWorkCompConfListBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ConstrWorkCompConfListBusinessImpl extends BaseFWBusinessImpl<ConstrWorkCompConfListDAO,ConstrWorkCompConfListDTO, ConstrWorkCompConfListBO> implements ConstrWorkCompConfListBusiness {

    @Autowired
    private ConstrWorkCompConfListDAO constrWorkCompConfListDAO;
    

     
    public ConstrWorkCompConfListBusinessImpl() {
        tModel = new ConstrWorkCompConfListBO();
        tDAO = constrWorkCompConfListDAO;
    }

    @Override
    public ConstrWorkCompConfListDAO gettDAO() {
        return constrWorkCompConfListDAO;
    }

    @Override
    public long count() {
        return constrWorkCompConfListDAO.count("ConstrWorkCompConfListBO", null);
    }

	@Override
	public List<ConstrWorkCompConfListLessDTO> getListConstrWorkByConstrId(Long id) {
		return constrWorkCompConfListDAO.getListConstrWorkByConstrId(id);
	}

	@Override
	public List<ConstrWorkCompConfListLessDTO> getListConstrWorkExistByConstrId(List<Long> id) {
		// TODO Auto-generated method stub
		return constrWorkCompConfListDAO.getListConstrWorkExistByConstrId(id);
	}

    

    
}
