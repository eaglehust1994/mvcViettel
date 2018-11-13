/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.erp.dao;

import com.viettel.erp.bo.AMaterialRecoveryMinutesBO;
import com.viettel.erp.dto.AMaterialRecoveryListDTO;
import com.viettel.erp.dto.AMaterialRecoveryListModelDTO;
import com.viettel.erp.dto.AMaterialRecoveryMinutesDTO;
import com.viettel.erp.dto.AMaterialRecoveryMinutesModelDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
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
@Repository("aMaterialRecoveryMinutesDAO")
public class AMaterialRecoveryMinutesDAO extends BaseFWDAOImpl<AMaterialRecoveryMinutesBO, Long> {
	
	@Value("${completionPartner.attachType}")
    private Long partnerAttachType;


	public AMaterialRecoveryMinutesDAO() {
		this.model = new AMaterialRecoveryMinutesBO();
	}

	public AMaterialRecoveryMinutesDAO(Session session) {
		this.session = session;
	}

	@SuppressWarnings("unchecked")
	public List<AMaterialRecoveryMinutesModelDTO> findByConstructId(
			AMaterialRecoveryMinutesModelDTO recoveryMinutesModelDTO) {
		StringBuffer sql = new StringBuffer("SELECT "
				+ "AM.A_MATERIAL_RECOVERY_MINUTES_ID amaterialRecoveryMinutesId,VC.CONSTRUCT_ID constructId,VC.CONSTRT_CODE constrtCode,VC.CONTRACT_CODE contractCode,AM.STATUS_CA statusCa,AM.CODE code,VC.CONTRACT_NAME contractName,"
				+ "AM.A_DIRECTOR_ID adirectorId,AM.A_HANDOVER_PERSON_ID ahandoverPersonId,AM.CREATED_USER_ID createdUserId,"
				+ "AM.CREATED_DATE createdDate,"
				+ "AM.B_DIRECTOR_ID bdirectorId,B_RECEIVE_PERSON_ID breceivePersonId,AM.IS_ACTIVE isActive,"
				+ "AM.SIGN_PLACE signPlace,cvc.constr_comp_re_map_id constrCompReMapId, "
				+ " DOCUMENT_CA.COMMENT_CA comments "
				+ " FROM  A_MATERIAL_RECOVERY_MINUTES AM " + "INNER JOIN CONSTR_COMPLETE_RECORDS_MAP cvc "
				+ "on AM.A_MATERIAL_RECOVERY_MINUTES_ID = cvc.data_table_id_value AND cvc.DATA_TABLE_NAME = 'A_MATERIAL_RECOVERY_MINUTES' "
				+ "INNER JOIN V_CONSTRUCTION_HCQT VC ON VC.CONSTRUCT_ID = AM.CONSTRUCT_ID "
				+ " LEFT JOIN DOCUMENT_CA ON AM.DOCUMENT_CA_ID = DOCUMENT_CA.DOCUMENT_CA_ID "
				+  "WHERE AM.IS_ACTIVE = 1 AND VC.CONSTRUCT_ID = :constructId AND VC.CONTRACT_ID = :contractId "
				+ " order by AM.A_MATERIAL_RECOVERY_MINUTES_ID DESC");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("amaterialRecoveryMinutesId", LongType.INSTANCE);
		query.addScalar("constructId", LongType.INSTANCE);
		query.addScalar("constrtCode", StringType.INSTANCE);
		query.addScalar("contractCode", StringType.INSTANCE);
		query.addScalar("statusCa", LongType.INSTANCE);
		query.addScalar("code", StringType.INSTANCE);
		query.addScalar("contractName", StringType.INSTANCE);
		query.addScalar("adirectorId", LongType.INSTANCE);
		query.addScalar("ahandoverPersonId", LongType.INSTANCE);
		query.addScalar("bdirectorId", LongType.INSTANCE);
		query.addScalar("breceivePersonId", LongType.INSTANCE);
		query.addScalar("createdUserId", LongType.INSTANCE);
		query.addScalar("isActive", LongType.INSTANCE);
		query.addScalar("signPlace", StringType.INSTANCE);
		query.addScalar("comments", StringType.INSTANCE);
		query.addScalar("constrCompReMapId", LongType.INSTANCE);
		query.addScalar("createdDate", new DateType());
		query.setParameter("constructId", recoveryMinutesModelDTO.getConstructId());
		query.setParameter("contractId", recoveryMinutesModelDTO.getContractId());
		query.setResultTransformer(Transformers.aliasToBean(AMaterialRecoveryMinutesModelDTO.class));

		List<AMaterialRecoveryMinutesModelDTO> list = query.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public boolean deleteAMaterialMinutes(List<String> listAMaterialRecoveryMinutesId) {
			String list = new String(" IN (");
			for (String constrWorkLogsId : listAMaterialRecoveryMinutesId) {
				list = list + constrWorkLogsId + ",";
			}
			list = list.substring(0, list.length() - 1);
			list = list + ") ";

			StringBuffer sql = new StringBuffer("update A_MATERIAL_RECOVERY_MINUTES " + "set is_active = 0 "
					+ "where A_MATERIAL_RECOVERY_MINUTES_ID");
			sql.append(list);
			SQLQuery query = getSession().createSQLQuery(sql.toString());
			query.executeUpdate();
			StringBuffer sqlConstr = new StringBuffer(
					"DELETE FROM CONSTR_COMPLETE_RECORDS_MAP recod WHERE recod.DATA_TABLE_NAME = 'A_MATERIAL_RECOVERY_MINUTES' AND recod.DATA_TABLE_ID_VALUE ");
			sqlConstr.append(list);
			SQLQuery queryConstr = getSession().createSQLQuery(sqlConstr.toString());
			queryConstr.executeUpdate();
			return true;
	}

	// load vat tu thiet bi
	@SuppressWarnings("unchecked")
	public List<AMaterialRecoveryListModelDTO> device(Long constructId) {
		// Code cu 07/03/2017
		// StringBuffer sql = new StringBuffer("SELECT me.mer_entity_id
		// merEntityId, c.name as merName, "
		// + "me.SERIAL_NUMBER serialNumber, d.name as name,"
		// + "am.ACTUAL_RECEIVE_QUANTITY as handoverQuantity,c.UNIT_ID unitId "
		// + " FROM A_MATERIAL_HANDOVER_MER_LIST am join A_MATERIAL_HANDOVER b
		// on "
		// + "(am.A_MATERIAL_HANDOVER_ID = b.A_MATERIAL_HANDOVER_ID) "
		// + " join mer_entity me on (am.mer_entity_Id = me.mer_entity_id) "
		// + "join cat_merchandise c on (me.mer_id = c.merchandise_id) "
		// + "join cat_unit d on (c.UNIT_ID = d.unit_id) "
		// + "WHERE me.is_temp = 0 and c.IS_DEVICE = 1 and b.STATUS_CA = 2 and
		// b.IS_ACTIVE = 1 "
		// + "and b.CONSTRUCT_ID = :constructId " + " and not exists" + "
		// (select 1 from constr_merchandise cm "
		// + "where cm.MER_ENTITY_ID = am.MER_ENTITY_ID and cm.CONSTRUCT_ID =
		// b.CONSTRUCT_ID)");

		// Chinh sua: Lay vat tu A cap nghiem thu theo chuc nang Bien Ban nghiem
		StringBuffer sql = new StringBuffer("SELECT me.mer_entity_id merEntityId, c.name as merName, "
				+ "me.SERIAL_NUMBER serialNumber, d.name as name,"
				+ "am.ACTUAL_RECEIVE_QUANTITY as handoverQuantity,c.UNIT_ID unitId "
				+ " FROM A_MATERIAL_HANDOVER_MER_LIST am join A_MATERIAL_HANDOVER b on "
				+ "(am.A_MATERIAL_HANDOVER_ID = b.A_MATERIAL_HANDOVER_ID) "
				+ " join mer_entity me on (am.mer_entity_Id = me.mer_entity_id) "
				+ "join cat_merchandise c on (me.mer_id = c.merchandise_id) "
				+ "join cat_unit d on (c.UNIT_ID = d.unit_id) "
				+ "WHERE me.is_temp = 0 and c.IS_DEVICE = 1 and b.STATUS_CA = 2 and b.IS_ACTIVE = 1 "
				+ "and b.CONSTRUCT_ID = :constructId " + " and not exists"
				+ " (select 1 from CONSTR_ACCEPT_MER_LIST cal "
				+ " join construction_acceptance ca on (ca.construction_acceptance_id  = cal.construction_acceptance_id) "
				+ " where ca.status_ca = 2 and cal.mer_entity_id = am.MER_ENTITY_ID and ca.CONSTRUCT_ID = b.CONSTRUCT_ID) ");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("merEntityId", LongType.INSTANCE);
		query.addScalar("merName", StringType.INSTANCE);
		query.addScalar("serialNumber", StringType.INSTANCE);
		query.addScalar("name", StringType.INSTANCE);
		query.addScalar("handoverQuantity", DoubleType.INSTANCE);
		query.addScalar("unitId", LongType.INSTANCE);

		query.setResultTransformer(Transformers.aliasToBean(AMaterialRecoveryListModelDTO.class));
		query.setParameter("constructId", constructId);
		List<AMaterialRecoveryListModelDTO> list = query.list();
		return list;

	}

	@SuppressWarnings("unchecked")
	public List<AMaterialRecoveryListDTO> checkDevice(Long constructId) {
		StringBuffer sql = new StringBuffer("SELECT me.mer_entity_id merEntityId, c.name as merName, "
				+ "me.SERIAL_NUMBER serialNumber, d.name as name,"
				+ "am.ACTUAL_RECEIVE_QUANTITY as handoverQuantity,c.UNIT_ID unitId "
				+ " FROM A_MATERIAL_HANDOVER_MER_LIST am join A_MATERIAL_HANDOVER b on "
				+ "(am.A_MATERIAL_HANDOVER_ID = b.A_MATERIAL_HANDOVER_ID) "
				+ " join mer_entity me on (am.mer_entity_Id = me.mer_entity_id) "
				+ "join cat_merchandise c on (me.mer_id = c.merchandise_id) "
				+ "join cat_unit d on (c.UNIT_ID = d.unit_id) "
				+ "WHERE me.is_temp = 0 and c.IS_DEVICE = 1 and b.STATUS_CA = 2 and b.IS_ACTIVE = 1 "
				+ "and b.CONSTRUCT_ID = :constructId " + " and not exists" + " (select 1 from constr_merchandise cm "
				+ "where cm.MER_ENTITY_ID = am.MER_ENTITY_ID and cm.CONSTRUCT_ID = b.CONSTRUCT_ID)");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("merEntityId", LongType.INSTANCE);
		query.addScalar("merName", StringType.INSTANCE);
		query.addScalar("serialNumber", StringType.INSTANCE);
		query.addScalar("name", StringType.INSTANCE);
		query.addScalar("handoverQuantity", DoubleType.INSTANCE);
		query.addScalar("unitId", LongType.INSTANCE);

		query.setResultTransformer(Transformers.aliasToBean(AMaterialRecoveryListDTO.class));
		query.setParameter("constructId", constructId);
		List<AMaterialRecoveryListDTO> list = query.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<AMaterialRecoveryListDTO> checkMaterials(Long constructId) {
		StringBuffer sql = new StringBuffer(
				"SELECT cd.name merName, cu.name name,AM.MER_ID merID,cd.UNIT_ID unitId,AM.handoverQuantity handoverQuantity,"
						+ "CM.acceptQuantity acceptQuantity FROM "
						+ "(select a.*,b.STATUS_CA, b.IS_ACTIVE, b.CONSTRUCT_ID ,a.ACTUAL_RECEIVE_QUANTITY handoverQuantity from "
						+ "A_MATERIAL_HANDOVER_MER_LIST a INNER JOIN A_MATERIAL_HANDOVER b on a.A_MATERIAL_HANDOVER_ID = b.A_MATERIAL_HANDOVER_ID "
						+ "WHERE b.STATUS_CA = 2  and b.IS_ACTIVE = 1 and a.IS_DEVICE=0 and b.CONSTRUCT_ID = :constructId) AM "
						+ "Left join (SELECT a.mer_id merID ,c.name as merName,d.name as name, a.CONSTRUCT_ID ,sum (a.quantity) as acceptQuantity,c.UNIT_ID unitID "
						+ "FROM constr_merchandise a join cat_merchandise c on ( a.mer_id = c.merchandise_id ) join cat_unit d on ( c.unit_id = d.unit_id ) "
						+ "WHERE c.IS_DEVICE = 0 group by a.mer_id, c.name ,d.name ,c.UNIT_ID ,a.CONSTRUCT_ID) CM "
						+ "on (AM.MER_ID = CM.merID and CM.CONSTRUCT_ID=AM.CONSTRUCT_ID ) join  cat_merchandise cd on ( AM.mer_id = cd.merchandise_id ) "
						+ "join cat_unit cu on (cd.unit_id = cu.unit_id) WHERE (AM.handoverQuantity > CM.acceptQuantity OR CM.merID IS NULL) ");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("merID", LongType.INSTANCE);
		query.addScalar("merName", StringType.INSTANCE);
		query.addScalar("name", StringType.INSTANCE);
		query.addScalar("handoverQuantity", LongType.INSTANCE);
		query.addScalar("acceptQuantity", LongType.INSTANCE);
		query.addScalar("unitId", LongType.INSTANCE);

		query.setParameter("constructId", constructId);
		query.setResultTransformer(Transformers.aliasToBean(AMaterialRecoveryListDTO.class));

		List<AMaterialRecoveryListDTO> list = query.list();
		return list;

	}

	@SuppressWarnings("unchecked")
	public List<AMaterialRecoveryListModelDTO> materials(Long constructId) {
		// Code cu 07/03/2017
		// StringBuffer sql = new StringBuffer(
		// "SELECT cd.name merName, cu.name name,AM.MER_ID merID,cd.UNIT_ID
		// unitId,AM.handoverQuantity handoverQuantity,"
		// + "CM.acceptQuantity acceptQuantity FROM "
		// + "(select a.*,b.STATUS_CA, b.IS_ACTIVE, b.CONSTRUCT_ID
		// ,a.ACTUAL_RECEIVE_QUANTITY handoverQuantity from "
		// + "A_MATERIAL_HANDOVER_MER_LIST a INNER JOIN A_MATERIAL_HANDOVER b on
		// a.A_MATERIAL_HANDOVER_ID = b.A_MATERIAL_HANDOVER_ID "
		// + "WHERE b.STATUS_CA = 2 and b.IS_ACTIVE = 1 and a.IS_DEVICE=0 and
		// b.CONSTRUCT_ID = :constructId) AM "
		// + "Left join (SELECT a.mer_id merID ,c.name as merName,d.name as
		// name, a.CONSTRUCT_ID ,sum (a.quantity) as acceptQuantity,c.UNIT_ID
		// unitID "
		// + "FROM constr_merchandise a join cat_merchandise c on ( a.mer_id =
		// c.merchandise_id ) join cat_unit d on ( c.unit_id = d.unit_id ) "
		// + "WHERE c.IS_DEVICE = 0 group by a.mer_id, c.name ,d.name ,c.UNIT_ID
		// ,a.CONSTRUCT_ID) CM "
		// + "on (AM.MER_ID = CM.merID and CM.CONSTRUCT_ID=AM.CONSTRUCT_ID )
		// join cat_merchandise cd on ( AM.mer_id = cd.merchandise_id ) "
		// + "join cat_unit cu on (cd.unit_id = cu.unit_id) WHERE
		// (AM.handoverQuantity > CM.acceptQuantity OR CM.merID IS NULL) ");

		// Chinh sua: Lay vat tu A cap nghiem thu theo chuc nang Bien Ban nghiem
		// thu BGDVSD - Hoan cong
		StringBuffer sql = new StringBuffer(
				" SELECT cd.name merName, cu.name name,AM.MER_ID merID,cd.UNIT_ID unitId,AM.handoverQuantity handoverQuantity, "
						+ " CM.acceptQuantity acceptQuantity FROM "
						+ " (select a.MER_ID,b.STATUS_CA, b.IS_ACTIVE, b.CONSTRUCT_ID ,a.ACTUAL_RECEIVE_QUANTITY handoverQuantity from "
						+ " A_MATERIAL_HANDOVER_MER_LIST a INNER JOIN A_MATERIAL_HANDOVER b on a.A_MATERIAL_HANDOVER_ID = b.A_MATERIAL_HANDOVER_ID "
						+ " WHERE b.STATUS_CA = 2  and b.IS_ACTIVE = 1 and a.IS_DEVICE=0 and b.CONSTRUCT_ID = :constructId) AM "
						+ " Left join (select a.merchandise_id merID, b.CONSTRUCT_ID, a.execute_quantity acceptQuantity "
						+ " from CONSTR_ACCEPT_MER_LIST a join construction_acceptance b on (a.construction_acceptance_id  = b.construction_acceptance_id) "
						+ " where b.is_active = 1 and b.status_ca = 2 and a.mer_entity_id is null) CM "
						+ " on (AM.MER_ID = CM.merID and CM.CONSTRUCT_ID=AM.CONSTRUCT_ID ) join  cat_merchandise cd on ( AM.mer_id = cd.merchandise_id ) "
						+ " join cat_unit cu on (cd.unit_id = cu.unit_id) WHERE (AM.handoverQuantity > CM.acceptQuantity OR CM.merID IS NULL)");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("merID", LongType.INSTANCE);
		query.addScalar("merName", StringType.INSTANCE);
		query.addScalar("name", StringType.INSTANCE);
		query.addScalar("handoverQuantity", DoubleType.INSTANCE);
		query.addScalar("acceptQuantity", DoubleType.INSTANCE);
		query.addScalar("unitId", LongType.INSTANCE);

		query.setParameter("constructId", constructId);
		query.setResultTransformer(Transformers.aliasToBean(AMaterialRecoveryListModelDTO.class));

		List<AMaterialRecoveryListModelDTO> list = query.list();
		return list;

	}

	@SuppressWarnings("unchecked")
	public List<AMaterialRecoveryListDTO> updateRecoveryList(Long amaterialRecoveryMinutesId) {

		StringBuilder sql = new StringBuilder("SELECT "
				+ "AL.A_MATERIAL_RECOVERY_LIST_ID amaterialRecoveryListId,AL.MER_ENTITY_ID merEntityId,AL.MER_NAME merName,"
				+ "AL.SERIAL_NUMBER serialNumber,AL.UNIT_ID unitId,AL.RECOVERY_QUANTITY recoveryQuantity,AL.QUALITY_STATUS qualityStatus,"
				+ "AL.COMMENTS comments, AL.UNIT_NAME unitName, AL.A_MATERIAL_RECOVERY_MINUTES_ID amaterialRecoveryMinutesId,"
				+ "AL.HANDOVER_QUANTITY handoverQuantity, AL.ACCEPT_QUANTITY acceptQuantity,AM.CONSTRUCT_ID constructId "
				+ " FROM  A_MATERIAL_RECOVERY_LIST AL"
				+ " INNER JOIN A_MATERIAL_RECOVERY_MINUTES AM ON AM.A_MATERIAL_RECOVERY_MINUTES_ID = AL.A_MATERIAL_RECOVERY_MINUTES_ID"
				+ " Where AM.A_MATERIAL_RECOVERY_MINUTES_ID = :amaterialRecoveryMinutesId");
		// + " order by AL.A_MATERIAL_RECOVERY_LIST_ID DESC");

		SQLQuery query = getSession().createSQLQuery(sql.toString());

		query.addScalar("amaterialRecoveryListId", LongType.INSTANCE);
		query.addScalar("merEntityId", LongType.INSTANCE);
		query.addScalar("constructId", LongType.INSTANCE);
		query.addScalar("merName", StringType.INSTANCE);
		query.addScalar("serialNumber", StringType.INSTANCE);
		query.addScalar("unitId", LongType.INSTANCE);
		query.addScalar("recoveryQuantity", DoubleType.INSTANCE);
		query.addScalar("qualityStatus", LongType.INSTANCE);
		query.addScalar("handoverQuantity", DoubleType.INSTANCE);
		query.addScalar("acceptQuantity", DoubleType.INSTANCE);
		query.addScalar("comments", StringType.INSTANCE);
		query.addScalar("unitName", StringType.INSTANCE);
		query.addScalar("amaterialRecoveryMinutesId", LongType.INSTANCE);

		query.setParameter("amaterialRecoveryMinutesId", amaterialRecoveryMinutesId);

		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		query.setResultTransformer(Transformers.aliasToBean(AMaterialRecoveryListDTO.class));

		List<AMaterialRecoveryListDTO> list = query.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public String autoGenCode() {
		StringBuffer sql = new StringBuffer(
				"select get_next_code('A_MATERIAL_RECOVERY_MINUTES', 'CODE','QLHC_VTTBACT',6) code from dual");
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("code", StandardBasicTypes.STRING);
		return (String) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public AMaterialRecoveryMinutesModelDTO recoveryMinutesModelDTO(AMaterialRecoveryMinutesModelDTO obj) {
			StringBuffer sql = new StringBuffer("SELECT "
					+ "AM.A_MATERIAL_RECOVERY_MINUTES_ID amaterialRecoveryMinutesId,AM.STATUS_CA statusCa,"
					+ "VC.CONSTRUCT_ID constructId,VC.CONSTRT_CODE constrtCode,VC.CONTRACT_CODE contractCode,"
					+ "AM.CODE code,VC.CONTRACT_NAME contractName,AM.SIGN_PLACE signPlace,VC.CONSTRT_NAME constrtName,"
					+ "VC.CONSTRUCTOR_NAME constructorName, VC.PROVINCE_NAME provinceName,VC.STATION_CODE stationCode,"
					+ "(SELECT FULL_NAME FROM CAT_EMPLOYEE WHERE ID ="
					+ "(SELECT A_DIRECTOR_ID FROM A_MATERIAL_RECOVERY_MINUTES WHERE A_MATERIAL_RECOVERY_MINUTES_ID = :amaterialRecoveryMinutesId"
					+ ")) adirectorName," + "(SELECT FULL_NAME FROM CAT_EMPLOYEE WHERE ID ="
					+ "(SELECT A_HANDOVER_PERSON_ID FROM A_MATERIAL_RECOVERY_MINUTES WHERE A_MATERIAL_RECOVERY_MINUTES_ID = :amaterialRecoveryMinutesId"
					+ ")) ahandoverPersonName," + "(SELECT FULL_NAME FROM CAT_EMPLOYEE WHERE ID ="
					+ "(SELECT B_DIRECTOR_ID FROM A_MATERIAL_RECOVERY_MINUTES WHERE A_MATERIAL_RECOVERY_MINUTES_ID = :amaterialRecoveryMinutesId"
					+ ")) bdirectorName," + "(SELECT FULL_NAME FROM CAT_EMPLOYEE WHERE ID ="
					+ "(SELECT B_RECEIVE_PERSON_ID FROM A_MATERIAL_RECOVERY_MINUTES WHERE A_MATERIAL_RECOVERY_MINUTES_ID = :amaterialRecoveryMinutesId"
					+ ")) breceivePersonName, "
					+ "(SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :type2 AND PARENT_ID = "
					+ "(SELECT A_DIRECTOR_ID FROM A_MATERIAL_RECOVERY_MINUTES WHERE A_MATERIAL_RECOVERY_MINUTES_ID = :amaterialRecoveryMinutesId"
					+ ")) aDirectorPath, "
					+ "(SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :type2 AND PARENT_ID = "
					+ "(SELECT A_HANDOVER_PERSON_ID FROM A_MATERIAL_RECOVERY_MINUTES WHERE A_MATERIAL_RECOVERY_MINUTES_ID = :amaterialRecoveryMinutesId"
					+ ")) aHandoverPersonPath, "
					+ "(SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :type2 AND PARENT_ID = "
					+ "(SELECT B_DIRECTOR_ID FROM A_MATERIAL_RECOVERY_MINUTES WHERE A_MATERIAL_RECOVERY_MINUTES_ID = :amaterialRecoveryMinutesId"
					+ ")) bDirectorPath, "
					+ "(SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :type2 AND PARENT_ID = "
					+ "(SELECT B_RECEIVE_PERSON_ID FROM A_MATERIAL_RECOVERY_MINUTES WHERE A_MATERIAL_RECOVERY_MINUTES_ID = :amaterialRecoveryMinutesId"
					+ ")) bReceivePersonPath " + "FROM  A_MATERIAL_RECOVERY_MINUTES AM "
					+ "INNER JOIN V_CONSTRUCTION_HCQT VC ON VC.CONSTRUCT_ID = AM.CONSTRUCT_ID "
					+ "WHERE AM.IS_ACTIVE = 1 AND AM.A_MATERIAL_RECOVERY_MINUTES_ID = :amaterialRecoveryMinutesId AND rownum = 1");

			if (obj.getContractId() != null) {
				sql.append(" AND VC.CONTRACT_ID = :contractId");
			}

			SQLQuery query = getSession().createSQLQuery(sql.toString());
			query.addScalar("amaterialRecoveryMinutesId", LongType.INSTANCE);
			query.addScalar("constructId", LongType.INSTANCE);
			query.addScalar("constrtCode", StringType.INSTANCE);
			query.addScalar("contractCode", StringType.INSTANCE);
			query.addScalar("code", StringType.INSTANCE);
			query.addScalar("contractName", StringType.INSTANCE);
			query.addScalar("adirectorName", StringType.INSTANCE);
			query.addScalar("ahandoverPersonName", StringType.INSTANCE);
			query.addScalar("bdirectorName", StringType.INSTANCE);
			query.addScalar("breceivePersonName", StringType.INSTANCE);
			query.addScalar("signPlace", StringType.INSTANCE);
			query.addScalar("constrtName", StringType.INSTANCE);
			query.addScalar("constructorName", StringType.INSTANCE);
			query.addScalar("provinceName", StringType.INSTANCE);
			query.addScalar("stationCode", StringType.INSTANCE);
			query.addScalar("aDirectorPath", StringType.INSTANCE);
			query.addScalar("aHandoverPersonPath", StringType.INSTANCE);
			query.addScalar("bDirectorPath", StringType.INSTANCE);
			query.addScalar("bReceivePersonPath", StringType.INSTANCE);
			query.addScalar("statusCa", LongType.INSTANCE);

			query.setParameter("amaterialRecoveryMinutesId", obj.getAmaterialRecoveryMinutesId());
			if (obj.getContractId() != null) {
				query.setParameter("contractId", obj.getContractId());
			}
			query.setParameter("type2", partnerAttachType);
			// query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			query.setResultTransformer(Transformers.aliasToBean(AMaterialRecoveryMinutesModelDTO.class));

			return (AMaterialRecoveryMinutesModelDTO) query.uniqueResult();

	}

	@Transactional
	public Long saveTable(AMaterialRecoveryMinutesDTO completionDrawing) throws Exception {
		Session session = getSession();
		Long completionDrawingId = (Long) session.save(completionDrawing.toModel());
		completionDrawing.getConstrCompleteRecordMap().setDataTableIdValue(completionDrawingId);
		session.save(completionDrawing.getConstrCompleteRecordMap().toModel());
		return completionDrawingId;
	}

	public Long CheckRecovryMinutes(Long id) {
		StringBuffer sqlBuffer = new StringBuffer(
				"select AL.MER_ENTITY_ID merEntityId from A_MATERIAL_RECOVERY_LIST AL where = :id");
		SQLQuery query = getSession().createSQLQuery(sqlBuffer.toString());
		query.addScalar("merEntityId", LongType.INSTANCE);
		query.setParameter("id", id);

		/*SQLQuery sql = */getSession().createSQLQuery(query.toString());
		return new Long(query.executeUpdate());
	}

	@SuppressWarnings("unchecked")
	public List<AMaterialRecoveryListDTO> checkSum(Long constructId) {
		StringBuffer sql = new StringBuffer(
				"SELECT MER_ENTITY_ID merEntityId, SUM(RECOVERY_QUANTITY) sumRecoveryQuantity "
						+ "FROM A_MATERIAL_RECOVERY_LIST " + "INNER JOIN A_MATERIAL_RECOVERY_MINUTES AMRM "
						+ "ON AMRM.A_MATERIAL_RECOVERY_MINUTES_ID = A_MATERIAL_RECOVERY_LIST.A_MATERIAL_RECOVERY_MINUTES_ID "
						+ "JOIN CONSTR_CONSTRUCTIONS VC ON VC.CONSTRUCT_ID = AMRM.CONSTRUCT_ID "
						+ "WHERE AMRM.IS_ACTIVE = 1 AND AMRM.CONSTRUCT_ID = :constructId " + "GROUP BY MER_ENTITY_ID ");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("merEntityId", LongType.INSTANCE);
		query.addScalar("sumRecoveryQuantity", DoubleType.INSTANCE);
		query.setParameter("constructId", constructId);

		query.setResultTransformer(Transformers.aliasToBean(AMaterialRecoveryListDTO.class));

		List<AMaterialRecoveryListDTO> list = query.list();
		return list;
	}

}
