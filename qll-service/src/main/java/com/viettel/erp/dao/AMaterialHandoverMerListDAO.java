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
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import com.google.common.base.Joiner;
import com.viettel.erp.bo.AMaterialHandoverMerListBO;
import com.viettel.erp.dto.AMaterialHandoverDTO;
import com.viettel.erp.dto.AMaterialHandoverMerListDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("aMaterialHandoverMerListDAO")
public class AMaterialHandoverMerListDAO extends BaseFWDAOImpl<AMaterialHandoverMerListBO, Long> {

	public AMaterialHandoverMerListDAO() {
		this.model = new AMaterialHandoverMerListBO();
	}

	public AMaterialHandoverMerListDAO(Session session) {
		this.session = session;
	}

	public List<AMaterialHandoverMerListDTO> getListAMaterial(List<String> listPXK) {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT " + "A.exp_note_code expNoteCode, " + "D.mer_entity_id merEntityId, "
					+ "E.NAME merName , " + "D.serial_number serialNumber, " + "E.UNIT_ID unitId , "
					+ "U.NAME unitName , " + "C.QUANTITY handoverQuantity, " + "NULL actualReceiveQuantity, "
					+ "1 qualityStatus, " + "NULL comments, " + "E.IS_DEVICE isDevice , " + "E.MERCHANDISE_ID merId "

					+ "FROM WARE_EXP_CMD A " + "JOIN WARE_EXP_NOTE B ON A.EXP_CMD_ID=B.EXP_CMD_ID "
					+ "JOIN WARE_EXP_NOTE_MER_ENTITY C ON B.DELIVERY_NOTE_ID=C.DELIVERY_NOTE_ID "
					+ "JOIN MER_ENTITY D ON D.MER_ENTITY_ID=C.MER_ENTITY_ID AND D.IS_TEMP=0 "
					+ "JOIN CAT_MERCHANDISE E ON E.MERCHANDISE_ID=D.MER_ID " + "JOIN CAT_UNIT U ON U.UNIT_ID=E.UNIT_ID "
					+ "WHERE A.DEST_TYPE=3  AND A.STATUS=5  and b.status in(2) AND E.IS_DEVICE=1 "
					+ "and A.exp_note_code in ( :condition )"

					+ "UNION ALL "

					+ "SELECT " + "A.EXP_NOTE_CODE expNoteCode," + "NULL merEntityId, " + "d.NAME merName , "
					+ "NULL serialNumber, " + "d.UNIT_ID unitId , " + "U.NAME unitName , "
					+ "c.total_delivery handoverQuantity," + "NULL actualReceiveQuantity, " + " 1 qualityStatus , "
					+ "A.COMMEMT_CA comments, " + "d.IS_DEVICE isDevice , " + "d.MERCHANDISE_ID merId "

					+ "FROM WARE_EXP_CMD A " + "JOIN WARE_EXP_NOTE B ON A.EXP_CMD_ID=B.EXP_CMD_ID "
					+ "JOIN WARE_EXP_SUM_MER C ON C.EXP_REQ_ID=A.EXP_REQ_ID "
					+ "JOIN CAT_MERCHANDISE D ON D.MERCHANDISE_ID=C.MER_ID " + "JOIN CAT_UNIT U ON U.UNIT_ID=D.UNIT_ID "
					+ "WHERE A.DEST_TYPE=3  AND A.STATUS=5  and b.status in(2) AND D.IS_DEVICE=0 "
					+ " AND  A.exp_note_code in ( :condition )");

			SQLQuery query = getSession().createSQLQuery(sql.toString());
			query.addScalar("expNoteCode", StringType.INSTANCE);
			query.addScalar("handoverQuantity", DoubleType.INSTANCE);
			query.addScalar("actualReceiveQuantity", DoubleType.INSTANCE);
			query.addScalar("merName", StringType.INSTANCE);
			query.addScalar("unitId", LongType.INSTANCE);
			query.addScalar("qualityStatus", LongType.INSTANCE);
			query.addScalar("serialNumber", StringType.INSTANCE);
			query.addScalar("comments", StringType.INSTANCE);
			query.addScalar("merEntityId", LongType.INSTANCE);
			query.addScalar("isDevice", LongType.INSTANCE);
			query.addScalar("merId", LongType.INSTANCE);
			query.addScalar("unitName", StringType.INSTANCE);
			

			// query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			query.setResultTransformer(Transformers.aliasToBean(AMaterialHandoverMerListDTO.class));
			query.setParameterList("condition", listPXK);
			List<AMaterialHandoverMerListDTO> ls = query.list();
			return ls;

	}

	public List<AMaterialHandoverMerListDTO> getListAMaterialHandOverMerList(AMaterialHandoverDTO dto) {

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT A.EXP_NOTE_CODE expNoteCode, " + " A.A_MATE_HAND_MER_LIST_ID aMateHandMerListId, "
					+ " A.MER_ENTITY_ID merEntityId, " + " A.MER_NAME merName, " + " A.SERIAL_NUMBER serialNumber, "
					+ " A.UNIT_ID unitId, " + " U.NAME unitName , " + " A.HANDOVER_QUANTITY handoverQuantity, "
					+ " A.ACTUAL_RECEIVE_QUANTITY actualReceiveQuantity, " + " A.QUALITY_STATUS qualityStatus, "
					+ " A.COMMENTS comments, " + " A.A_MATERIAL_HANDOVER_ID amaterialHandoverId, "
					+ " A.IS_DEVICE isDevice, " + "A. MER_ID merId " + "FROM A_MATERIAL_HANDOVER_MER_LIST A "
					+ "JOIN CAT_UNIT U ON U.UNIT_ID=A.UNIT_ID "
					+ "WHERE A.A_MATERIAL_HANDOVER_ID = :amaterialHandOverId");

			SQLQuery query = getSession().createSQLQuery(sql.toString());
			query.addScalar("expNoteCode", StringType.INSTANCE);
			query.addScalar("merEntityId", LongType.INSTANCE);
			query.addScalar("merName", StringType.INSTANCE);
			query.addScalar("serialNumber", StringType.INSTANCE);
			query.addScalar("unitId", LongType.INSTANCE);
			query.addScalar("handoverQuantity", DoubleType.INSTANCE);
			query.addScalar("actualReceiveQuantity", DoubleType.INSTANCE);
			query.addScalar("qualityStatus", LongType.INSTANCE);
			query.addScalar("comments", StringType.INSTANCE);
			query.addScalar("amaterialHandoverId", LongType.INSTANCE);
			query.addScalar("isDevice", LongType.INSTANCE);
			query.addScalar("merId", LongType.INSTANCE);
			query.addScalar("aMateHandMerListId", LongType.INSTANCE);
			query.addScalar("unitName", StringType.INSTANCE);

			query.setParameter("amaterialHandOverId", dto.getAmaterialHandoverId());

			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			query.setResultTransformer(Transformers.aliasToBean(AMaterialHandoverMerListDTO.class));

			return query.list();

	}

	// dodt
	public List<AMaterialHandoverMerListBO> getAmaterialhandoverforcontruction(Long constructId) {

		Query q = getSession().createQuery(Joiner.on(" ").join(
				"from amaterialhandovermerlist a join fetch a.amaterialhandover b join fetch b.constrconstructions c join fetch a.merentity d join fetch d.catmerchandise e join fetch e.catunit left join fetch a.constrmerchandise g left join fetch g.constrconstructions  where 1=1 and d.isTemp = 0  ",
				constructId != null ? " and c.constructId = :constructId" : ""));

		if (constructId != null) {
			q.setParameter("constructId", constructId);
		}
		q.setMaxResults(200);
		return q.list();
	}

	public List<AMaterialHandoverMerListDTO> getAmaterialhandoverforcontructionX(Long constructId) {

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM (SELECT " + "D.mer_entity_id merEntityId, " + "E.CODE merCode , "
					+ "E.NAME merName , " + "D.serial_number serialNumber, " + "E.UNIT_ID unitId , "
					+ "U.NAME unitName , " + "C.QUANTITY handoverQuantity, " + "NULL executeQuantity, "
					+ "null qualityStatus, " + "NULL comments, " + "E.IS_DEVICE isDevice , "
					+ "E.MERCHANDISE_ID merChandiseId "

					+ "FROM WARE_EXP_CMD A " + "JOIN WARE_EXP_NOTE B ON A.EXP_CMD_ID=B.EXP_CMD_ID "
					+ "JOIN WARE_EXP_NOTE_MER_ENTITY C ON B.DELIVERY_NOTE_ID=C.DELIVERY_NOTE_ID "
					+ "JOIN MER_ENTITY D ON D.MER_ENTITY_ID=C.MER_ENTITY_ID AND D.IS_TEMP=0 "
					+ "JOIN CAT_MERCHANDISE E ON E.MERCHANDISE_ID=D.MER_ID " + "JOIN CAT_UNIT U ON U.UNIT_ID=E.UNIT_ID "
					+ "WHERE A.DEST_TYPE=3  AND A.STATUS=5  and b.status in(2) AND E.IS_DEVICE=1 "
					+ "and B.CONSTRUCTION_ID =:constructId " 
					+ "GROUP BY D.mer_entity_id,E.CODE  ,E.NAME  ,D.serial_number ,E.UNIT_ID  ,U.NAME  ,C.QUANTITY ,E.IS_DEVICE  ,E.MERCHANDISE_ID "
					+ " UNION ALL "

					+ "SELECT " + "null merEntityId, " + "d.CODE merCode , " + "d.NAME merName , "
					+ "NULL serialNumber, " + "d.UNIT_ID unitId , " + "U.NAME unitName , "
					+ "SUM(c.total_delivery) handoverQuantity," + "NULL executeQuantity, " + " NULL qualityStatus , "
					+ "NULL comments, " + "d.IS_DEVICE isDevice , " + "d.MERCHANDISE_ID merChandiseId "

					+ "FROM WARE_EXP_CMD A " + "JOIN WARE_EXP_NOTE B ON A.EXP_CMD_ID=B.EXP_CMD_ID "
					+ "JOIN WARE_EXP_SUM_MER C ON C.EXP_REQ_ID=A.EXP_REQ_ID "
					+ "JOIN CAT_MERCHANDISE D ON D.MERCHANDISE_ID=C.MER_ID " + "JOIN CAT_UNIT U ON U.UNIT_ID=D.UNIT_ID "
					+ "WHERE A.DEST_TYPE=3  AND A.STATUS=5  and b.status in(2) AND D.IS_DEVICE=0 "
					+ " and B.CONSTRUCTION_ID =:constructId "
					+ " GROUP BY d.CODE  ,d.NAME  ,d.UNIT_ID ,U.NAME   ,d.IS_DEVICE ,d.MERCHANDISE_ID )"
					+ " ORDER BY merChandiseId");

			SQLQuery query = getSession().createSQLQuery(sql.toString());
			query.addScalar("handoverQuantity", DoubleType.INSTANCE);
			query.addScalar("executeQuantity", DoubleType.INSTANCE);
			query.addScalar("merCode", StringType.INSTANCE);
			query.addScalar("merName", StringType.INSTANCE);
			query.addScalar("unitId", LongType.INSTANCE);
			query.addScalar("qualityStatus", LongType.INSTANCE);
			query.addScalar("serialNumber", StringType.INSTANCE);
			query.addScalar("comments", StringType.INSTANCE);
			query.addScalar("merEntityId", LongType.INSTANCE);
			query.addScalar("isDevice", LongType.INSTANCE);
			query.addScalar("merChandiseId", LongType.INSTANCE);
			query.addScalar("unitName", StringType.INSTANCE);

			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			query.setResultTransformer(Transformers.aliasToBean(AMaterialHandoverMerListDTO.class));
			if (constructId != null) {
				query.setParameter("constructId", constructId);
			}
			return query.list();

	}

	public Double getSldabangiao(AMaterialHandoverMerListDTO dto) {

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT SUM(ACTUAL_RECEIVE_QUANTITY) sldabangiao " + "FROM A_MATERIAL_HANDOVER_MER_LIST "
					+ " INNER JOIN A_MATERIAL_HANDOVER ON A_MATERIAL_HANDOVER.A_MATERIAL_HANDOVER_ID = A_MATERIAL_HANDOVER_MER_LIST.A_MATERIAL_HANDOVER_ID "
					+ " WHERE EXP_NOTE_CODE = :code " + " AND MER_NAME = :merName "
					+ " AND A_MATERIAL_HANDOVER.IS_ACTIVE = 1 AND A_MATERIAL_HANDOVER.STATUS_CA != 0 " + " GROUP BY MER_NAME");

			SQLQuery query = getSession().createSQLQuery(sql.toString());

			query.addScalar("sldabangiao", DoubleType.INSTANCE);

			query.setParameter("code", dto.getExpNoteCode());
			query.setParameter("merName", dto.getMerName());

			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			query.setResultTransformer(Transformers.aliasToBean(AMaterialHandoverMerListDTO.class));

			AMaterialHandoverMerListDTO rs = (AMaterialHandoverMerListDTO) query.uniqueResult();
			if (rs != null) {
				return rs.getSldabangiao();
			} else {
				return 0D;
			}

	}

}
