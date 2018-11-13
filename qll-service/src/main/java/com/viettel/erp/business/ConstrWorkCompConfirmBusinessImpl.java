package com.viettel.erp.business;
 
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.viettel.erp.bo.ConstrWorkCompConfirmBO;
import com.viettel.erp.dao.ConstrWorkCompConfirmDAO;
import com.viettel.erp.dto.ConstrWorkCompConfListLessDTO;
import com.viettel.erp.dto.ConstrWorkCompConfirmDTO;
import com.viettel.erp.dto.ConstrWorkCompConfirmExportDTO;
import com.viettel.erp.rest.FileServiceImpl;
import com.viettel.service.base.business.BaseFWBusinessImpl;


@Service("constrWorkCompConfirmBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ConstrWorkCompConfirmBusinessImpl extends BaseFWBusinessImpl<ConstrWorkCompConfirmDAO,ConstrWorkCompConfirmDTO, ConstrWorkCompConfirmBO> implements ConstrWorkCompConfirmBusiness {

	static Logger LOGGER = LoggerFactory.getLogger(ConstrWorkCompConfirmBusinessImpl.class);
    @Autowired
    private ConstrWorkCompConfirmDAO constrWorkCompConfirmDAO;

    
     
    public ConstrWorkCompConfirmBusinessImpl() {
        tModel = new ConstrWorkCompConfirmBO();
        tDAO = constrWorkCompConfirmDAO;
    }

    @Override
    public ConstrWorkCompConfirmDAO gettDAO() {
        return constrWorkCompConfirmDAO;
    }

    @Override
    public long count() {
        return constrWorkCompConfirmDAO.count("ConstrWorkCompConfirmBO", null);
    }

    public List<ConstrWorkCompConfirmDTO> getCWorkCompleteByContrId(long id){
    	List<ConstrWorkCompConfirmBO> listBO = constrWorkCompConfirmDAO.getCWorkCompleteByContrId(id);
    	List<ConstrWorkCompConfirmDTO> listDTO = Lists.newArrayList();
    	for(ConstrWorkCompConfirmBO obj : listBO){
    		listDTO.add(obj.toDTO());
    	}
    	return listDTO;
    }

	@Override
	public boolean deleteListEntity(List<Long> listId) {
		
		return constrWorkCompConfirmDAO.deleteListEntity(listId);
	}

	@Override
	public String getCode(String tableName, String value) {
		return constrWorkCompConfirmDAO.getCode(tableName, value);
	}

	@Override
	public String getNameEmployee(Long id) {
		// TODO Auto-generated method stub
		return constrWorkCompConfirmDAO.getNameEmployee(id);
	}

	@Override
	public List<ConstrWorkCompConfListLessDTO> getListConstrWorkExistByConstrId(List<Long> Listid) {
		// TODO Auto-generated method stub
		return constrWorkCompConfirmDAO.getListConstrWorkExistByConstrId(Listid);
	}

	@Override
	public ConstrWorkCompConfirmExportDTO getConstructInfoById(long id) {
		// TODO Auto-generated method stub
		return constrWorkCompConfirmDAO.getConstructInfoById(id);
	}

	@Override
	public Long getconstrCompReMapId(Long id) {
		// TODO Auto-generated method stub
		return constrWorkCompConfirmDAO.getconstrCompReMapId(id);
	}

	@Override
	public Long saveTable(ConstrWorkCompConfirmDTO completionDrawing) {
		try {
			return constrWorkCompConfirmDAO.saveTable(completionDrawing);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage(), e);
			return null;
		}
	};

    
}
