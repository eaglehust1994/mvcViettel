package com.viettel.erp.business;
 
import com.viettel.erp.bo.AbComplementWorkBO;
import com.viettel.erp.dao.AbComplementWorkDAO;
import com.viettel.erp.dto.AbComplementWorkDTO;
import com.viettel.erp.dto.EstimatesWorkItemsDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("abComplementWorkBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AbComplementWorkBusinessImpl extends BaseFWBusinessImpl<AbComplementWorkDAO,AbComplementWorkDTO, AbComplementWorkBO> implements AbComplementWorkBusiness {

    @Autowired
    private AbComplementWorkDAO abComplementWorkDAO;
    

     
    public AbComplementWorkBusinessImpl() {
        tModel = new AbComplementWorkBO();
        tDAO = abComplementWorkDAO;
    }

    @Override
    public AbComplementWorkDAO gettDAO() {
        return abComplementWorkDAO;
    }

    @Override
    public long count() {
        return abComplementWorkDAO.count("AbComplementWorkBO", null);
    }

    public Long saveTable( AbComplementWorkDTO  completionDrawing) throws Exception{
        Long completionDrawingId = abComplementWorkDAO.saveTable(completionDrawing);
        return completionDrawingId;
	}
    @Override
	public AbComplementWorkDTO getThongtinchung(Long id) {
		return abComplementWorkDAO.getThongtinchung(id);
	}
    
    public AbComplementWorkDTO getThongtinchungBieu2(Long id) {
		return abComplementWorkDAO.getThongtinchungBieu2(id);
	}

	public AbComplementWorkDTO getByIdCC(Long constructId) {
		return abComplementWorkDAO.getByIdCC(constructId);
	}

	public AbComplementWorkDTO getAmonitorSingCa(Long id) {
		
		return abComplementWorkDAO.getAmonitorSingCa(id);
	}

	public AbComplementWorkDTO CheckEstimate3(Long constructId) {		
		return abComplementWorkDAO.CheckEstimate3(constructId);
	}

	public String autoGenCode() {		
		return abComplementWorkDAO.autoGenCode();
	}

    
}
