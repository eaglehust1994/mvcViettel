/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.erp.dao;

import com.google.common.base.Joiner;
import com.viettel.erp.bo.CatEmployeeBO;
import com.viettel.erp.bo.ConstrWorkCompConfirmBO;
import com.viettel.erp.dto.ConstrCompleteRecordsMapDTO;
import com.viettel.erp.dto.ConstrWorkCompConfListLessDTO;
import com.viettel.erp.dto.ConstrWorkCompConfirmDTO;
import com.viettel.erp.dto.ConstrWorkCompConfirmExportDTO;
import com.viettel.erp.dto.CurrentStateHandoverDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("constrWorkCompConfirmDAO")
public class ConstrWorkCompConfirmDAO extends BaseFWDAOImpl<ConstrWorkCompConfirmBO, Long> {
	
	@Value("${completionPartner.attachType}")
    private Long partnerAttachType;

	public ConstrWorkCompConfirmDAO() {
		this.model = new ConstrWorkCompConfirmBO();
	}

	public ConstrWorkCompConfirmDAO(Session session) {
		this.session = session;
	}

	public List<ConstrWorkCompConfirmBO> getCWorkCompleteByContrId(long id) {
		Session session = getSession();
		Query query = session.createQuery(
				"SELECT u from cWorkComplete u WHERE  CONSTRUCT_ID= :constrId AND IS_ACTIVE= 1 order by u.constrWorkCompConfirmId desc");
		query.setParameter("constrId", id);
		return query.list();
	}

	@Transactional
	public boolean deleteListEntity(List<Long> listId) {
		Session session = getSession();
		Query query = session
				.createQuery("UPDATE cWorkComplete u SET u.isActive = 0 WHERE u.constrWorkCompConfirmId IN (:list) ");
		query.setParameterList("list", listId);
		query.executeUpdate();
		session.createSQLQuery(
				"DELETE FROM CONSTR_COMPLETE_RECORDS_MAP recod WHERE recod.DATA_TABLE_NAME = 'CONSTR_WORK_COMP_CONFIRM' AND recod.DATA_TABLE_ID_VALUE IN (:list)")
				.setParameterList("list", listId).executeUpdate();
		return true;
	}

	public String getCode(String tableName, String value) {
		SQLQuery query = getSession().createSQLQuery(
				Joiner.on("").join("select get_next_code('", tableName, "', 'CODE','", value, "',6) code from dual"));
		query.addScalar("code", StandardBasicTypes.STRING);

		return (String) query.uniqueResult();
	}

	public String getNameEmployee(Long id) {
		SQLQuery query = getSession().createSQLQuery("SELECT * FROM CAT_EMPLOYEE  cat WHERE cat.ID = :id");
		query.addEntity(CatEmployeeBO.class);

		query.setParameter("id", id);
		List<CatEmployeeBO> results = query.list();
		if (results.size() > 0) {
			return results.get(0).getFullName();
		} else {
			return null;
		}

	}

	public List<ConstrWorkCompConfListLessDTO> getListConstrWorkExistByConstrId(List<Long> Listid) {

		String sql = "SELECT ewi.WORK_ITEM_NAME workItemName,ewi.UNIT unit,ewi.WORK_AMOUNT workAmount,ewi.ESTIMATES_WORK_ITEM_ID estimatesWorkItemId,cwl.EXECUTE_QUANTITY executeQuantity,cwl.COMMENTS comments,cwl.CONSTR_WORK_COMP_LIST_ID constrWorkCompListId "
				+ " FROM ESTIMATES_WORK_ITEMS ewi " + " JOIN CONSTR_ESTIMATE_INFO cei "
				+ " ON ewi.CONSTR_ESTIMATE_INFO_ID = cei.CONSTR_ESTIMATE_INFO_ID " + " JOIN CONSTR_CONSTRUCTIONS cc "
				+ " ON cei.CONSTRUCTION_ID = cc.CONSTRUCT_ID " + " JOIN CONSTR_WORK_COMP_CONF_LIST cwl "
				+ " ON ewi.ESTIMATES_WORK_ITEM_ID = cwl.ESTIMATES_WORK_ITEM_ID "
				+ " WHERE ewi.PROGRESS_TYPE = 3 AND ewi.STATUS = 1 AND cc.CONSTRUCT_ID = :constrId AND cwl.CONSTR_WORK_COMP_CONFIRM_ID = :cWccid ";

		SQLQuery query = getSession().createSQLQuery(sql);
		query.addScalar("constrWorkCompListId", LongType.INSTANCE);
		query.addScalar("workItemName", StringType.INSTANCE);
		query.addScalar("unit", StringType.INSTANCE);
		query.addScalar("workAmount", LongType.INSTANCE);
		query.addScalar("estimatesWorkItemId", LongType.INSTANCE);
		query.addScalar("executeQuantity", DoubleType.INSTANCE);
		query.addScalar("comments", StringType.INSTANCE);
		query.setResultTransformer(Transformers.aliasToBean(ConstrWorkCompConfListLessDTO.class));

		query.setParameter("constrId", Listid.get(0));
		query.setParameter("cWccid", Listid.get(1));

		return query.list();
	}

	public ConstrWorkCompConfirmExportDTO getConstructInfoById(long id) {
		String sql = "select v.CONTRACT_CODE contractCode, v.CONSTRT_NAME constrtName , v.CONSTRT_ADDRESS constrtAddress, v.STATION_CODE station_code , "
				+ " com.CODE code,com.CONSTRUCT_ID constructId, com.A_DIRECTOR_ID aDirectorId, com.STATUS_CA statusCa , com.A_IN_CHARGE_MONITOR_ID aInChargeMonitorId,com.B_DIRECTOR_ID bDirectorId, com.B_IN_CHARGE_CONSTRUCT_ID bInChargeConstructId , "

				+ "(SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :type2 AND PARENT_ID = "
				+ "(SELECT A_DIRECTOR_ID FROM CONSTR_WORK_COMP_CONFIRM WHERE CONSTR_WORK_COMP_CONFIRM_ID = :id" 
				+ ")) aDirectorPath, "
				+ "(SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :type2 AND PARENT_ID = "
				+ "(SELECT A_IN_CHARGE_MONITOR_ID FROM CONSTR_WORK_COMP_CONFIRM WHERE CONSTR_WORK_COMP_CONFIRM_ID = :id"
				+ ")) aInChargeMonitorPath, "
				+ "(SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :type2 AND PARENT_ID = "
				+ "(SELECT B_DIRECTOR_ID FROM CONSTR_WORK_COMP_CONFIRM WHERE CONSTR_WORK_COMP_CONFIRM_ID = :id" 
				+ ")) bDirectorPath, "
				+ "(SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :type2 AND PARENT_ID = "
				+ "(SELECT B_IN_CHARGE_CONSTRUCT_ID FROM CONSTR_WORK_COMP_CONFIRM WHERE CONSTR_WORK_COMP_CONFIRM_ID = :id"
				+ ")) bInChargeConstructPath "

				+ " From CONSTR_WORK_COMP_CONFIRM com " + " Join  V_CONSTRUCTION_HCQT v "
				+ " on v.CONSTRUCT_ID = com.CONSTRUCT_ID " + " where com.CONSTR_WORK_COMP_CONFIRM_ID = :id ";

		SQLQuery query = getSession().createSQLQuery(sql);
		query.addScalar("contractCode", StringType.INSTANCE);
		query.addScalar("constrtName", StringType.INSTANCE);
		query.addScalar("code", StringType.INSTANCE);
		query.addScalar("station_code", StringType.INSTANCE);
		query.addScalar("constrtAddress", StringType.INSTANCE);
		query.addScalar("aDirectorId", LongType.INSTANCE);
		query.addScalar("aInChargeMonitorId", LongType.INSTANCE);
		query.addScalar("bDirectorId", LongType.INSTANCE);
		query.addScalar("bInChargeConstructId", LongType.INSTANCE);
		query.addScalar("constructId", LongType.INSTANCE);
		query.addScalar("aDirectorPath", StringType.INSTANCE);
		query.addScalar("aInChargeMonitorPath", StringType.INSTANCE);
		query.addScalar("bDirectorPath", StringType.INSTANCE);
		query.addScalar("bInChargeConstructPath", StringType.INSTANCE);
		query.addScalar("statusCa", LongType.INSTANCE);
		query.setResultTransformer(Transformers.aliasToBean(ConstrWorkCompConfirmExportDTO.class));
		System.out.println(id);
		query.setParameter("id", id);
		query.setParameter("type2", partnerAttachType);
		List<ConstrWorkCompConfirmExportDTO> list = query.list();
		System.out.println(list.size());
		return list.size() > 0 ? list.get(0) : null;
	}

	public Long getconstrCompReMapId(Long id) {
		String sql = "select CONSTR_COMP_RE_MAP_ID constrCompReMapId from CONSTR_COMPLETE_RECORDS_MAP  where DATA_TABLE_ID_VALUE = :id AND DATA_TABLE_NAME = 'CONSTR_WORK_COMP_CONFIRM'";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.addScalar("constrCompReMapId", LongType.INSTANCE);
		query.setResultTransformer(Transformers.aliasToBean(ConstrCompleteRecordsMapDTO.class));
		System.out.println(id);
		query.setParameter("id", id);
		List<ConstrCompleteRecordsMapDTO> list = query.list();
		System.out.println(list.size());
		return list.size() > 0 ? list.get(0).getConstrCompReMapId() : null;
	}

	@Transactional
	public Long saveTable(ConstrWorkCompConfirmDTO completionDrawing) throws Exception {
		Session session = getSession();
		Long completionDrawingId = (Long) session.save(completionDrawing.toModel());
		completionDrawing.getConstrCompleteRecordMap().setDataTableIdValue(completionDrawingId);
		session.save(completionDrawing.getConstrCompleteRecordMap().toModel());
		return completionDrawingId;
	}
}
