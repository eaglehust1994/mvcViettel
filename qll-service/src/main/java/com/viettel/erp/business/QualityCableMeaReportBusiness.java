package com.viettel.erp.business;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StringType;

import com.viettel.erp.dto.CatEmployeeDTO;
import com.viettel.erp.dto.QualityCableMeaReportDTO;
import com.viettel.erp.dto.QualityCableMeaReportModelDTO;
import com.viettel.erp.dto.QualityCableMeaResultDTO;
import com.viettel.erp.dto.SettlementRightDTO;
import com.viettel.erp.dto.approDTO;

public interface QualityCableMeaReportBusiness {

	long count();

	List<QualityCableMeaReportModelDTO> getQualityReportList();

	List<QualityCableMeaReportModelDTO> findByConstructId(QualityCableMeaReportModelDTO qualityCableMeaReportModelDTO);

	List<CatEmployeeDTO> getListEmployeeByRole(SettlementRightDTO rightDTO);

	List<QualityCableMeaResultDTO> getListQualityResualt(Long qualityCableMeaReportId);

	boolean saveOrUpdate(QualityCableMeaReportDTO targetObject);

	boolean qualitySaveOrUpdate(QualityCableMeaReportDTO targetObject);

	boolean deleteReport(List<String> listReportID);

	boolean deleteResult(List<String> listString);

	String autoGenCode();

	List<CatEmployeeDTO> getAllListEmployeeByRole(SettlementRightDTO rightDTO);

	QualityCableMeaReportModelDTO getQualityReport(QualityCableMeaReportModelDTO dto);

	Long appro(approDTO obj);

	Long saveTable(QualityCableMeaReportDTO completionDrawing);

	String getUpdateConstrCompleteRecod(Long qualityID,String nameTable);
}
