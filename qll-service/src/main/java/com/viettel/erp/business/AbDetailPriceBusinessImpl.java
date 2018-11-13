package com.viettel.erp.business;
 
import com.viettel.erp.bo.AbDetailPriceBO;
import com.viettel.erp.dao.AbDetailPriceDAO;
import com.viettel.erp.dto.AbComplementWorkDTO;
import com.viettel.erp.dto.AbDetailPriceDTO;
import com.viettel.erp.dto.AbDetailPriceNewDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("abDetailPriceBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AbDetailPriceBusinessImpl extends BaseFWBusinessImpl<AbDetailPriceDAO,AbDetailPriceDTO, AbDetailPriceBO> implements AbDetailPriceBusiness {

    @Autowired
    private AbDetailPriceDAO abDetailPriceDAO;
    

     
    public AbDetailPriceBusinessImpl() {
        tModel = new AbDetailPriceBO();
        tDAO = abDetailPriceDAO;
    }

    @Override
    public AbDetailPriceDAO gettDAO() {
        return abDetailPriceDAO;
    }

    @Override
    public long count() {
        return abDetailPriceDAO.count("AbDetailPriceBO", null);
    }

	public AbDetailPriceDTO getById(Long constructId) {
		// TODO Auto-generated method stub
		return abDetailPriceDAO.getById(constructId);
	}
	
	public String autoGenCode() {
		
		return abDetailPriceDAO.autoGenCode();
	}
	
	public Long saveTable( AbDetailPriceDTO  completionDrawing) throws Exception{
        Long completionDrawingId = abDetailPriceDAO.saveTable(completionDrawing);
        return completionDrawingId;
    }

	public AbDetailPriceDTO getThongtinchung(AbDetailPriceDTO dto) {
		// TODO Auto-generated method stub
		return abDetailPriceDAO.getThongtinchung(dto);
	}

	public AbDetailPriceDTO CheckEstimate5(Long constructId) {
		// TODO Auto-generated method stub
		return abDetailPriceDAO.CheckEstimate5(constructId);
	}

	public AbDetailPriceDTO getAmonitorSingCa(Long constructId) {
		// TODO Auto-generated method stub
		return abDetailPriceDAO.getAmonitorSingCa(constructId);
	}

	public List<AbDetailPriceNewDTO> getExportBieu5(Long constructId) {
		// TODO Auto-generated method stub
		return abDetailPriceDAO.getExportBieu5(constructId);
	}

	

    

    
}
