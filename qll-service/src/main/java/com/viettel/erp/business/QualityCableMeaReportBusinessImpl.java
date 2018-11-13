package com.viettel.erp.business;
 
import com.google.common.collect.Lists;
import com.viettel.erp.bo.QualityCableMeaReportBO;
import com.viettel.erp.dao.QualityCableMeaReportDAO;
import com.viettel.erp.dto.CatEmployeeDTO;
import com.viettel.erp.dto.ConstructionAcceptanceDTO;
import com.viettel.erp.dto.QualityCableMeaReportDTO;
import com.viettel.erp.dto.QualityCableMeaReportModelDTO;
import com.viettel.erp.dto.QualityCableMeaResultDTO;
import com.viettel.erp.dto.SettlementRightDTO;
import com.viettel.erp.dto.approDTO;
import com.viettel.erp.rest.FileServiceImpl;
import com.viettel.service.base.business.BaseFWBusinessImpl;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("qualityCableMeaReportBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class QualityCableMeaReportBusinessImpl extends BaseFWBusinessImpl<QualityCableMeaReportDAO,QualityCableMeaReportDTO, QualityCableMeaReportBO> implements QualityCableMeaReportBusiness {

	static Logger LOGGER = LoggerFactory.getLogger(QualityCableMeaReportBusinessImpl.class);
    @Autowired
    private QualityCableMeaReportDAO qualityCableMeaReportDAO;
    

     
    public QualityCableMeaReportBusinessImpl() {
        tModel = new QualityCableMeaReportBO();
        tDAO = qualityCableMeaReportDAO;
    }

    @Override
    public QualityCableMeaReportDAO gettDAO() {
        return qualityCableMeaReportDAO;
    }

    @Override
    public long count() {
        return qualityCableMeaReportDAO.count("QualityCableMeaReportBO", null);
    }

	@Override
	public List<QualityCableMeaReportModelDTO> getQualityReportList() {
		return qualityCableMeaReportDAO.getQualityReportList();
	}

	@Override
	public List<QualityCableMeaReportModelDTO> findByConstructId(QualityCableMeaReportModelDTO qualityCableMeaReportModelDTO) {
		return qualityCableMeaReportDAO.findByConstructId(qualityCableMeaReportModelDTO);
	}

	@Override
	public List<CatEmployeeDTO> getListEmployeeByRole(SettlementRightDTO rightDTO) {
		List<CatEmployeeDTO> listEm = qualityCableMeaReportDAO.getListEmployeeByRole(rightDTO);
		List<CatEmployeeDTO> listRsEm = Lists.newArrayList();
//		System.out.println("=========================================================");
		HashMap listEmMap = new HashMap<>();
		for(CatEmployeeDTO em : listEm){
			if(!listEmMap.containsKey(em.getId())){
				listRsEm.add(em);
				listEmMap.put(em.getId(), em);
//				System.out.println(em.getId()+"============="+em.getFullName());
			}
		}
//		System.out.println("=========================================================");
		return listRsEm;
	}
    
	@Override
	public List<QualityCableMeaResultDTO> getListQualityResualt(Long qualityCableMeaReportId) {
		return qualityCableMeaReportDAO.getListQualityResualt(qualityCableMeaReportId);
	}
	
	@Override
	public boolean saveOrUpdate(QualityCableMeaReportDTO targetObject){
		return qualityCableMeaReportDAO.saveOrUpdate(targetObject);
	}

	@Override
	public boolean qualitySaveOrUpdate(QualityCableMeaReportDTO targetObject) {
		return qualityCableMeaReportDAO.qualitySaveOrUpdate(targetObject);
	}

	@Override
	public boolean deleteReport(List<String> listReportID) {
	return qualityCableMeaReportDAO.deleteReport(listReportID);
	}

	@Override
	public boolean deleteResult(List<String> listString) {
		return qualityCableMeaReportDAO.deleteResult(listString);
	}

	@Override
	public String autoGenCode() {
		return qualityCableMeaReportDAO.autoGenCode();
	}

	@Override
	public List<CatEmployeeDTO> getAllListEmployeeByRole(SettlementRightDTO rightDTO) {
		return qualityCableMeaReportDAO.getAllListEmployeeByRole(rightDTO);
	}

	@Override
	public QualityCableMeaReportModelDTO getQualityReport(QualityCableMeaReportModelDTO dto) {
		return qualityCableMeaReportDAO.getQualityReport(dto);
	}

	@Override
	public Long appro(approDTO obj) {
		// TODO Auto-generated method stub
		return qualityCableMeaReportDAO.appro(obj);
	}

	@Override
	public Long saveTable(QualityCableMeaReportDTO completionDrawing) {
		// TODO Auto-generated method stub
		try {
			return qualityCableMeaReportDAO.saveTable(completionDrawing);
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
		return null;
	}

	@Override
	public String getUpdateConstrCompleteRecod(Long qualityID, String nameTable) {
		// TODO Auto-generated method stub
		return qualityCableMeaReportDAO.getUpdateConstrCompleteRecod(qualityID , nameTable);
	}
	
}
