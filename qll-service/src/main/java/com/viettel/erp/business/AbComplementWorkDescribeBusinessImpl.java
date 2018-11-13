package com.viettel.erp.business;
 
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.viettel.erp.bo.AbComplementWorkDescribeBO;
import com.viettel.erp.dao.AbComplementWorkDescribeDAO;
import com.viettel.erp.dto.AbComplementWorkDescribeDTO;
import com.viettel.erp.dto.EstimatesWorkItemsDTO;
import com.viettel.erp.rest.FileServiceImpl;
import com.viettel.service.base.business.BaseFWBusinessImpl;


@Service("abComplementWorkDescribeBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AbComplementWorkDescribeBusinessImpl extends BaseFWBusinessImpl<AbComplementWorkDescribeDAO,AbComplementWorkDescribeDTO, AbComplementWorkDescribeBO> implements AbComplementWorkDescribeBusiness {

	static Logger LOGGER = LoggerFactory.getLogger(AbComplementWorkDescribeBusinessImpl.class);
    @Autowired
    private AbComplementWorkDescribeDAO abComplementWorkDescribeDAO;
    
    @Autowired
    private EstimatesWorkItemsBusinessImpl estimatesWorkItemsBusinessImpl;
     
    public AbComplementWorkDescribeBusinessImpl() {
        tModel = new AbComplementWorkDescribeBO();
        tDAO = abComplementWorkDescribeDAO;
    }

    @Override
    public AbComplementWorkDescribeDAO gettDAO() {
        return abComplementWorkDescribeDAO;
    }

    @Override
    public long count() {
        return abComplementWorkDescribeDAO.count("AbComplementWorkDescribeBO", null);
    }
	@Override
	public List<AbComplementWorkDescribeDTO> signCAForm4(AbComplementWorkDescribeDTO obj) {
		return null;
	}

	@Override
	public String autoGenCode(String tableName, String value) {
		return abComplementWorkDescribeDAO.autoGenCode(tableName, value);
	}

	@Override
	public Long saveTable(AbComplementWorkDescribeDTO completionDrawing) {
		try {
			return abComplementWorkDescribeDAO.saveTable(completionDrawing);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage(), e);
			return new Long(0);
		}
	}

	@Override
	public AbComplementWorkDescribeDTO getAbComplementWorkById(Long id) {
		// TODO Auto-generated method stub
		return abComplementWorkDescribeDAO.getAbComplementWorkById(id);
	}

	@Override
	public Long getconstrCompReMapId(Long id) {
		return abComplementWorkDescribeDAO.getconstrCompReMapId(id);
	}
    
	@Override
	public List<EstimatesWorkItemsDTO> getBieu4(EstimatesWorkItemsDTO obj) {
		return estimatesWorkItemsBusinessImpl.getBieu4(obj);
	}
	
	@Override
	public EstimatesWorkItemsDTO exportEstimateWorkItem(EstimatesWorkItemsDTO dto) {
		// TODO Auto-generated method stub
		return estimatesWorkItemsBusinessImpl.exportEstimateWorkItem(dto);
	}

    
}
