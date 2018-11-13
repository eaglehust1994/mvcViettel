package com.viettel.erp.business;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.viettel.erp.bo.ConstrWorkLogsBO;
import com.viettel.erp.dao.ConstrWorkLogsDAO;
import com.viettel.erp.dto.ConstrWorkLogsDTO;
import com.viettel.erp.dto.EstimatesWorkItemsDTO;
import com.viettel.erp.dto.approDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;

@Service("constrWorkLogsBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ConstrWorkLogsBusinessImpl extends
		BaseFWBusinessImpl<ConstrWorkLogsDAO, ConstrWorkLogsDTO, ConstrWorkLogsBO> implements ConstrWorkLogsBusiness {

	static Logger LOGGER = LoggerFactory.getLogger(ConstrWorkLogsBusinessImpl.class);
	@Autowired
	private ConstrWorkLogsDAO constrWorkLogsDAO;

	@Autowired
	ConstrCompleteRecordsMapBusinessImpl constrCompleteRecordsMapBusinessImpl;

	@Autowired
	ConstrWorkLogsLabelBusinessImpl constrWorkLogsLabelBusinessImpl;
	
	@Autowired
	QualityCableMeaReportBusinessImpl qualityCableMeaReportBusinessImpl;

	public ConstrWorkLogsBusinessImpl() {
		tModel = new ConstrWorkLogsBO();
		tDAO = constrWorkLogsDAO;
	}

	@Override
	public ConstrWorkLogsDAO gettDAO() {
		return constrWorkLogsDAO;
	}

	@Override
	public long count() {
		return constrWorkLogsDAO.count("ConstrWorkLogsBO", null);
	}

	@Override
	public List<ConstrWorkLogsDTO> getAllConstrWorkLogs(ConstrWorkLogsDTO dto) {
		return constrWorkLogsDAO.getAllConstrWorkLogs(dto);
	}

	@Override
	public List<EstimatesWorkItemsDTO> getEstimatesWork(String constructId) {
		return constrWorkLogsDAO.getEstimatesWork(constructId);
	}

	@Override
	public void deleteConstrWorkLogs(List<String> listConstrWorkLogsId) {
		constrWorkLogsDAO.deleteConstrWorkLogs(listConstrWorkLogsId);
	}

	@Override
	public String autoGenCode() {
		return constrWorkLogsDAO.autoGenCode();
	}

	@Override
	public boolean checkStatusDatabase(String constrWorkLogsId) {
		return constrWorkLogsDAO.checkStatusDatabase(constrWorkLogsId);
	}

	@Override
	public Long appro(approDTO obj) {
		return constrWorkLogsDAO.appro(obj);
	}

	@Override
	public ConstrWorkLogsDTO findById(Long constrWorkLogsId,String contractCode) {
		return constrWorkLogsDAO.findById(constrWorkLogsId,contractCode);
	}

	@Override
	@Transactional
	public Long addConstrWorkLogs(ConstrWorkLogsDTO obj) {
		Long id = 0l;
		obj.setIsActive(1l);

		if (constrWorkLogsLabelBusinessImpl.checkBia(obj.getConstructId())) {
			return new Long(-1);
		}
		String code = this.autoGenCode();
		Long constructionId = obj.getConstructId();
		if ("update".equals(obj.getCreatOrUpdate())) {
			if (this.checkStatusDatabase(obj.getConstrWorkLogsId() + "")) {
				return new Long(0);
			}
			id = constrWorkLogsDAO.updateDataTable(obj);
			if(obj.getStatusCa() == 0L){
				String nameTable = "CONSTR_WORK_LOGS";
				qualityCableMeaReportBusinessImpl.getUpdateConstrCompleteRecod(obj.getConstrWorkLogsId(), nameTable);
			}
			
		} else if ("creat".equals(obj.getCreatOrUpdate())) {
			obj.setCreatedDate(new Date());
			obj.setStatusCa(0l);
			obj.setCode(code);
			id = this.save(obj);
			try {
				constrCompleteRecordsMapBusinessImpl.insert(constructionId, "CONSTR_WORK_LOGS", "CONSTR_WORK_LOGS_ID",
						id, obj.getCreatedUserId(), code);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
			}
		}
		return id;
	}

}
