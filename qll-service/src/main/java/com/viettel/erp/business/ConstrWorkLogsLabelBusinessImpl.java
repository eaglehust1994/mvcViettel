package com.viettel.erp.business;
 
import com.viettel.erp.bo.ConstrWorkLogsLabelBO;
import com.viettel.erp.dao.ConstrWorkLogsLabelDAO;
import com.viettel.erp.dto.ConstrWorkLogsLabelDTO;
import com.viettel.erp.dto.VConstructionHcqtDTO;
import com.viettel.erp.dto.approDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("constrWorkLogsLabelBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ConstrWorkLogsLabelBusinessImpl extends BaseFWBusinessImpl<ConstrWorkLogsLabelDAO,ConstrWorkLogsLabelDTO, ConstrWorkLogsLabelBO> implements ConstrWorkLogsLabelBusiness {

	
	static Logger LOGGER = LoggerFactory.getLogger(ConstrWorkLogsLabelBusinessImpl.class);
    @Autowired
    private ConstrWorkLogsLabelDAO constrWorkLogsLabelDAO;
     
    @Autowired
   	ConstrCompleteRecordsMapBusinessImpl constrCompleteRecordsMapBusinessImpl;
    
    public ConstrWorkLogsLabelBusinessImpl() {
        tModel = new ConstrWorkLogsLabelBO();
        tDAO = constrWorkLogsLabelDAO;
    }

    @Override
    public ConstrWorkLogsLabelDAO gettDAO() {
        return constrWorkLogsLabelDAO;
    }

    @Override
    public long count() {
        return constrWorkLogsLabelDAO.count("ConstrWorkLogsLabelBO", null);
    }

	@Override
	@Transactional
	public Long creat(ConstrWorkLogsLabelDTO dto) {
		if(this.checkBia(dto.getConstructId())){
			return -1l;
		}
		Long constructionId = dto.getConstructId();
		Long id;
		Long idCom  = 0l;
		String code = this.autoGenCode();
		dto.setCode(code);
		dto.setCreatedDate(new Date());
		dto.setStatusCa(0l);
		dto.setIsActive(1l);
		id = this.save(dto);
		try {
			idCom = constrCompleteRecordsMapBusinessImpl.insert(constructionId, "CONSTR_WORK_LOGS_LABEL",
					"CONSTR_WO_LOGS_LAB_ID", id, dto.getCreatedUserId(), code);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return idCom;
	}
	
	@Override
	public Long updateLabel(ConstrWorkLogsLabelDTO dto) {
		if (this.checkStatusDatabaseLabel(dto.getConstructId()+"")) {
			return -1l;
		}
		return constrWorkLogsLabelDAO.updateLabel(dto);
	}

	
	@Override
	public String autoGenCode() {
		return constrWorkLogsLabelDAO.autoGenCode();
	}

	@Override
	public boolean checkBia(Long constructId) {
		return constrWorkLogsLabelDAO.checkBia(constructId);
	}

	@Override
	public List<ConstrWorkLogsLabelDTO> getAllBia(Long constructId) {
		return constrWorkLogsLabelDAO.getAllBia(constructId);
	}
    
	@Override
	public Long appro(approDTO obj) {
		return constrWorkLogsLabelDAO.appro(obj);
	}

	@Override
	public VConstructionHcqtDTO findById(Long constructId, String contractCode) {
		return constrWorkLogsLabelDAO.findById(constructId, contractCode);
	}

	@Override
	public boolean checkStatusDatabaseLabel(String constrWoLogsLabId) {
		return constrWorkLogsLabelDAO.checkStatusDatabaseLabel(constrWoLogsLabId);
	}
	
}
