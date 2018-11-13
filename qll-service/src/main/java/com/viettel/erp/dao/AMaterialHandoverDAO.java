/*

 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.erp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.LongType;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Joiner;
import com.viettel.erp.bo.AMaterialHandoverBO;
import com.viettel.erp.dto.AMaterialHandoverDTO;
import com.viettel.erp.dto.BienBanBanGiaoAcapDTO;
import com.viettel.erp.dto.ConstrAcceptanceRequestDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("aMaterialHandoverDAO")
public class AMaterialHandoverDAO extends BaseFWDAOImpl<AMaterialHandoverBO, Long> {
	
	@Value("${completionPartner.attachType}")
    private Long partnerAttachType;

	public AMaterialHandoverDAO() {
		this.model = new AMaterialHandoverBO();
	}

	public AMaterialHandoverDAO(Session session) {
		this.session = session;
	}

	@Transactional
	public Long saveTable(AMaterialHandoverDTO aMaterialHandoverDTO) throws Exception {
		Session session = getSession();
		Long workItemsAcceptanceId = (Long) session.save(aMaterialHandoverDTO.toModel());
		aMaterialHandoverDTO.getConstrCompleteRecordsMap().setDataTableIdValue(workItemsAcceptanceId);
		session.save(aMaterialHandoverDTO.getConstrCompleteRecordsMap().toModel());
		return workItemsAcceptanceId;
	}

	public boolean checkStatusDatabase(String amaterialHandoverId) {
		StringBuffer sqlBuffer = new StringBuffer("SELECT CW.STATUS_CA statusCa" + " FROM A_MATERIAL_HANDOVER CW  "
				+ " WHERE CW.A_MATERIAL_HANDOVER_ID = :amaterialHandoverId");

		SQLQuery query = getSession().createSQLQuery(sqlBuffer.toString());
		query.addScalar("statusCa", LongType.INSTANCE);
		query.setParameter("amaterialHandoverId", amaterialHandoverId);

		Long statusCa = (Long) query.uniqueResult();
		if (statusCa == 0L || statusCa == 3L) {
			return false;
		} else {
			return true;
		}
	}
	
	public String autoGenCode() {
		StringBuffer sql = new StringBuffer(
				"select get_next_code('A_MATERIAL_HANDOVER', 'CODE','BGTBVT',6) code from dual");
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("code", StandardBasicTypes.STRING);
		return (String) query.uniqueResult();
	}

	public Long addListAMaterial(List<AMaterialHandoverDTO> listBTVT) {
			StringBuilder sql = new StringBuilder();
			sql.append("insert into A_MATERIAL_HANDOVER_MER_LIST ");
			sql.append("(A_MATE_HAND_MER_LIST_ID, EXP_NOTE_CODE, MER_ENTITY_ID, MER_NAME, "
					+ "SERIAL_NUMBER, UNIT_ID, HANDOVER_QUANTITY, " + "ACTUAL_RECEIVE_QUANTITY,QUALITY_STATUS, "
					+ "COMMENTS, A_MATERIAL_HANDOVER_ID) ");

			sql.append("value(");
			for (AMaterialHandoverDTO dto : listBTVT) {
				sql.append("A_MATERIAL_HAN_MER_LIST_SEQ.nextVal" + ",'");
				sql.append(dto.getExpNoteCode() + "',");
				sql.append(dto.getMerEntityId() + ",'");
				sql.append(dto.getMerName() + "','");
				sql.append(dto.getSerialNumber() + "',");
				sql.append(dto.getUnitID() + ",");
				sql.append(dto.getHandoverQuantity() + ",");
				sql.append(dto.getActualReceiveQuantity() + ",");
				sql.append(dto.getQuanlityStatus() + ",'");
				sql.append(dto.getComments() + "',");
				sql.append(dto.getAmaterialHandoverId() + "),");
			}

			String sqlInsert = sql.toString();
			sqlInsert = sqlInsert.substring(0, sqlInsert.length() - 1);

			SQLQuery query = getSession().createSQLQuery(sqlInsert);
			return (long) query.executeUpdate();
	}

	public List<AMaterialHandoverDTO> getAMaterialHandoverByCongTrinh(AMaterialHandoverDTO dto) {
			StringBuilder sql = new StringBuilder();
			sql.append("select A_MATERIAL_HANDOVER.CODE code," + "V_CONSTRUCTION_HCQT.CONSTRT_CODE constrtCode, "
					+ "V_CONSTRUCTION_HCQT.CONTRACT_CODE contractCode , "
					+ "A_MATERIAL_HANDOVER.HANDOVER_FROM_DATE handoverFromDate, "
					+ "A_MATERIAL_HANDOVER.HANDOVER_TO_DATE handoverToDate, "
					+ "A_MATERIAL_HANDOVER.A_MATERIAL_HANDOVER_ID amaterialHandoverId,"
					+ "A_MATERIAL_HANDOVER.CREATE_USER_ID createdUserId,"
					+ "A_MATERIAL_HANDOVER.Sign_date signDate,A_MATERIAL_HANDOVER.sign_Place signPlace,"
					+ "CONSTR_COMPLETE_RECORDS_MAP.CONSTR_COMP_RE_MAP_ID constrCompReMapId, "
					+ "DOCUMENT_CA.COMMENT_CA comments, "
					+ "A_MATERIAL_HANDOVER.A_DIRECTOR_ID adirectorId, "
					+ "A_MATERIAL_HANDOVER.A_HANDOVER_PERSON_ID ahandoverPersonId, "
					+ "A_MATERIAL_HANDOVER.B_RECEIVE_PERSON_ID breceivePersonId, "
					+ "A_MATERIAL_HANDOVER.B_DIRECTOR_ID bdirectorId, "
					+ "A_MATERIAL_HANDOVER.OTHER_COMMENTS otherComment, "
					+ "A_MATERIAL_HANDOVER.HANDOVER_PLACE handoverPlace, " + "A_MATERIAL_HANDOVER.STATUS_CA statusCa "
					+ "from A_MATERIAL_HANDOVER " + "inner join V_CONSTRUCTION_HCQT "
					+ "on A_MATERIAL_HANDOVER.CONSTRUCT_ID = V_CONSTRUCTION_HCQT.CONSTRUCT_ID "
					+ " LEFT JOIN DOCUMENT_CA ON A_MATERIAL_HANDOVER.DOCUMENT_CA_ID = DOCUMENT_CA.DOCUMENT_CA_ID "
					+ "inner join CONSTR_COMPLETE_RECORDS_MAP on CONSTR_COMPLETE_RECORDS_MAP.DATA_TABLE_ID_VALUE= A_MATERIAL_HANDOVER.A_MATERIAL_HANDOVER_ID "
					+ " and CONSTR_COMPLETE_RECORDS_MAP.DATA_TABLE_NAME = 'A_MATERIAL_HANDOVER' "
					
					);
			sql.append("where V_CONSTRUCTION_HCQT.CONSTRUCT_ID = :constructId");
			sql.append(" and A_MATERIAL_HANDOVER.IS_ACTIVE = 1 ");
			if(dto.getContractCode() != null && !"".equals(dto.getContractCode())) {
				sql.append(" and V_CONSTRUCTION_HCQT.CONTRACT_CODE=:contractCode ");
			}
			
			sql.append(" order By A_MATERIAL_HANDOVER.A_MATERIAL_HANDOVER_ID desc");

			SQLQuery query = getSession().createSQLQuery(sql.toString());
			query.addScalar("statusCa", LongType.INSTANCE);
			 query.addScalar("constrCompReMapId", LongType.INSTANCE);
			query.addScalar("contractCode", StringType.INSTANCE);
			query.addScalar("constrtCode", StringType.INSTANCE);
			query.addScalar("code", StringType.INSTANCE);
			query.addScalar("handoverFromDate", StandardBasicTypes.TIMESTAMP);
			query.addScalar("handoverToDate", StandardBasicTypes.TIMESTAMP);
			query.addScalar("amaterialHandoverId", LongType.INSTANCE);

			query.addScalar("comments", StringType.INSTANCE);
			query.addScalar("adirectorId", LongType.INSTANCE);
			query.addScalar("ahandoverPersonId", LongType.INSTANCE);
			query.addScalar("breceivePersonId", LongType.INSTANCE);
			query.addScalar("bdirectorId", LongType.INSTANCE);
			query.addScalar("otherComment", StringType.INSTANCE);
			query.addScalar("handoverPlace", StringType.INSTANCE);
			query.addScalar("signPlace", StringType.INSTANCE);
			query.addScalar("signDate", DateType.INSTANCE);
			query.addScalar("createdUserId", LongType.INSTANCE);
			
			query.setParameter("constructId", dto.getConstructId());
			
			if(dto.getContractCode() != null && !"".equals(dto.getContractCode())) {
				query.setParameter("contractCode", dto.getContractCode());
			}

			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			query.setResultTransformer(Transformers.aliasToBean(AMaterialHandoverDTO.class));
			return query.list();
	}
	
	
	@Transactional
	public String deleteAMaterialHandoverByCode(List<String> listCode) {
			Session session = getSession();
//			String condition = "";
//			for (String code : listCode) {
//				condition = condition + "" + code + ",";
//			}
//			condition = condition.substring(0, condition.length() - 1);
			StringBuilder sql = new StringBuilder();
			sql.append("update " + " A_MATERIAL_HANDOVER set IS_ACTIVE=0 ");
			sql.append(" where A_MATERIAL_HANDOVER.A_MATERIAL_HANDOVER_ID in (:condition)");

			SQLQuery query = session.createSQLQuery(sql.toString());
			query.setParameterList("condition", listCode);
			query.executeUpdate();
			
			session.createSQLQuery(
					"DELETE FROM CONSTR_COMPLETE_RECORDS_MAP recod "
					+ "WHERE recod.DATA_TABLE_NAME = 'A_MATERIAL_HANDOVER' AND recod.DATA_TABLE_ID_VALUE IN (:condition)").setParameterList("condition", listCode).executeUpdate();
			
			return "SUCCESS";

	}
	
	@Transactional
	public String deleteForUpdateByCode(List<String> listCode) {
			Session session = getSession();
//			String condition = "";
//			for (String code : listCode) {
//				condition = condition + "" + code + ",";
//			}
//			condition = condition.substring(0, condition.length() - 1);
			StringBuilder sql = new StringBuilder();
			sql.append("delete from " + " A_MATERIAL_HANDOVER ");
			sql.append(" where A_MATERIAL_HANDOVER.A_MATERIAL_HANDOVER_ID in (:condition)");

			SQLQuery query = session.createSQLQuery(sql.toString());
			query.setParameterList("condition", listCode);
			query.executeUpdate();
			
			session.createSQLQuery(
					"DELETE FROM A_MATERIAL_HANDOVER_MER_LIST  "
					+ " where A_MATERIAL_HANDOVER_MER_LIST.A_MATERIAL_HANDOVER_ID in (:condition)").setParameterList("condition", listCode).executeUpdate();
			
			session.createSQLQuery(
					"DELETE FROM CONSTR_COMPLETE_RECORDS_MAP recod "
					+ "WHERE recod.DATA_TABLE_NAME = 'A_MATERIAL_HANDOVER' AND recod.DATA_TABLE_ID_VALUE IN (:condition)").setParameterList("condition", listCode).executeUpdate();
			
			return "SUCCESS";

	}

	public List<AMaterialHandoverDTO> getThoiGianBanGiao(AMaterialHandoverDTO dto) {
			StringBuilder sql = new StringBuilder();
			sql.append("select A_MATERIAL_HANDOVER.HANDOVER_FROM_DATE handoverFromDate,"
					+ "A_MATERIAL_HANDOVER.HANDOVER_TO_DATE handoverToDate, "
					+ "A_MATERIAL_HANDOVER.OTHER_COMMENTS otherComments, "
					+ "A_MATERIAL_HANDOVER.HANDOVER_PLACE handoverPlace " + "from A_MATERIAL_HANDOVER ");

			sql.append("where A_MATERIAL_HANDOVER.A_MATERIAL_HANDOVER_ID = :amaterialHandOverId");

			SQLQuery query = getSession().createSQLQuery(sql.toString());

			query.addScalar("handoverPlace", StringType.INSTANCE);
			query.addScalar("handoverFromDate", StandardBasicTypes.TIMESTAMP);
			query.addScalar("handoverToDate", StandardBasicTypes.TIMESTAMP);
			query.addScalar("otherComments", StringType.INSTANCE);
			
			query.setParameter("amaterialHandOverId", dto.getAmaterialHandoverId());

			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			query.setResultTransformer(Transformers.aliasToBean(AMaterialHandoverDTO.class));

			return query.list();
	}

	public List<AMaterialHandoverBO> getAmaterialhandoverforcontruction(AMaterialHandoverDTO dto) {

		Query q = getSession().createQuery(Joiner.on(" ").join("from amaterialhandover e where 1=1 ", ""));
		// dto.getConstructionId()!=null ? " and
		// e.constracceptworklist.constructionId = :constructionId" : ""));

		/*
		 * if (dto.getConstructionId()!= null) {
		 * q.setParameter("constructionId", dto.getConstructionId()); }
		 */
		q.setMaxResults(200);
		return q.list();
	}

	public BienBanBanGiaoAcapDTO getDataExport(AMaterialHandoverDTO dto) {
			StringBuilder sql = new StringBuilder();
			sql.append("select V_CONSTRUCTION_HCQT.CONSTRT_NAME constrName, V_CONSTRUCTION_HCQT.STATION_CODE stationCode, A_MATERIAL_HANDOVER.OTHER_COMMENTS otherComment, "
					+ "A_MATERIAL_HANDOVER.CODE code,A_MATERIAL_HANDOVER.Sign_date signDate,A_MATERIAL_HANDOVER.sign_Place signPlace, "
					+ "A_MATERIAL_HANDOVER.HANDOVER_PLACE place, " + "V_CONSTRUCTION_HCQT.CONSTRT_ADDRESS address, "
					+ "V_CONSTRUCTION_HCQT.CONTRACT_CODE contractCode, "
					+ "(SELECT FULL_NAME FROM CAT_EMPLOYEE WHERE ID = (SELECT A_DIRECTOR_ID FROM A_MATERIAL_HANDOVER WHERE A_MATERIAL_HANDOVER_ID = :amaterialHandOverId"
					+  ")) aperson1, "
					+ "(SELECT FULL_NAME FROM CAT_EMPLOYEE WHERE ID = (SELECT A_HANDOVER_PERSON_ID FROM A_MATERIAL_HANDOVER WHERE A_MATERIAL_HANDOVER_ID = :amaterialHandOverId"
					+  ")) aperson2, "
					+ "(SELECT FULL_NAME FROM CAT_EMPLOYEE WHERE ID = (SELECT B_DIRECTOR_ID FROM A_MATERIAL_HANDOVER WHERE A_MATERIAL_HANDOVER_ID = :amaterialHandOverId"
					+  ")) bperson1, "
					+ "(SELECT FULL_NAME FROM CAT_EMPLOYEE WHERE ID = (SELECT B_RECEIVE_PERSON_ID FROM A_MATERIAL_HANDOVER WHERE A_MATERIAL_HANDOVER_ID = :amaterialHandOverId"
					+  ")) bperson2, "
					+ "TO_CHAR(A_MATERIAL_HANDOVER.HANDOVER_FROM_DATE, 'DD-MM-YYYY HH24:MI:SS') handoverFromDate, "
					+ "TO_CHAR(A_MATERIAL_HANDOVER.HANDOVER_TO_DATE, 'DD-MM-YYYY HH24:MI:SS') handoverToDate, "

					+ "(SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :type2 AND PARENT_ID = (SELECT A_DIRECTOR_ID FROM A_MATERIAL_HANDOVER WHERE A_MATERIAL_HANDOVER_ID = :amaterialHandOverId"
					+ ")) adirectorIdPath, "
					+ "(SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :type2 AND PARENT_ID = (SELECT A_HANDOVER_PERSON_ID FROM A_MATERIAL_HANDOVER WHERE A_MATERIAL_HANDOVER_ID = :amaterialHandOverId"
					+  ")) ahandoverPersonIdPath, "
					+ "(SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :type2 AND PARENT_ID = (SELECT B_DIRECTOR_ID FROM A_MATERIAL_HANDOVER WHERE A_MATERIAL_HANDOVER_ID = :amaterialHandOverId"
					+  ")) bdirectorIdPath, "
					+ "(SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :type2 AND PARENT_ID = (SELECT B_RECEIVE_PERSON_ID FROM A_MATERIAL_HANDOVER WHERE A_MATERIAL_HANDOVER_ID = :amaterialHandOverId"
					+  ")) breceivePersonIdPath, "
					+ " A_MATERIAL_HANDOVER.STATUS_CA statusCa "
					
					+ "from A_MATERIAL_HANDOVER "
					+ "inner join V_CONSTRUCTION_HCQT on V_CONSTRUCTION_HCQT.CONSTRUCT_ID = A_MATERIAL_HANDOVER.CONSTRUCT_ID "
					+ "");

			sql.append("where A_MATERIAL_HANDOVER.A_MATERIAL_HANDOVER_ID = :amaterialHandOverId AND ROWNUM = 1");

			SQLQuery query = getSession().createSQLQuery(sql.toString());

			query.addScalar("constrName", StringType.INSTANCE);
			query.addScalar("code", StringType.INSTANCE);
			query.addScalar("address", StringType.INSTANCE);
			query.addScalar("place", StringType.INSTANCE);
			query.addScalar("contractCode", StringType.INSTANCE);
			query.addScalar("aperson1", StringType.INSTANCE);
			query.addScalar("aperson2", StringType.INSTANCE);
			query.addScalar("bperson1", StringType.INSTANCE);
			query.addScalar("bperson2", StringType.INSTANCE);
			query.addScalar("handoverFromDate", StringType.INSTANCE);
			query.addScalar("handoverToDate", StringType.INSTANCE);
			query.addScalar("signPlace", StringType.INSTANCE);
			query.addScalar("signDate", DateType.INSTANCE);
			query.addScalar("stationCode", StringType.INSTANCE);
			query.addScalar("otherComment", StringType.INSTANCE);			

			query.addScalar("adirectorIdPath", StringType.INSTANCE);
			query.addScalar("ahandoverPersonIdPath", StringType.INSTANCE);
			query.addScalar("bdirectorIdPath", StringType.INSTANCE);
			query.addScalar("breceivePersonIdPath", StringType.INSTANCE);
			query.addScalar("statusCa", LongType.INSTANCE);
			
			query.setParameter("amaterialHandOverId", dto.getAmaterialHandoverId());
			query.setParameter("type2", partnerAttachType);
			
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			query.setResultTransformer(Transformers.aliasToBean(BienBanBanGiaoAcapDTO.class));

			return (BienBanBanGiaoAcapDTO) query.uniqueResult();

	}

}
