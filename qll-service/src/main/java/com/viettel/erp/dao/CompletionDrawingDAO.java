/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.erp.dao;

import com.google.common.base.Joiner;
import com.viettel.erp.bo.CompletionDrawingBO;
import com.viettel.erp.bo.ConstructionAcceptanceBO;
import com.viettel.erp.business.CatFileInvoiceBusinessImpl;
import com.viettel.erp.business.CompletionDrawingBusinessImpl;
import com.viettel.erp.dto.CatFileInvoiceDTO;
import com.viettel.erp.dto.CompletionDrawingDTO;
import com.viettel.erp.dto.ConstrCompleteRecordsMapDTO;
import com.viettel.erp.dto.UtilAttachedDocumentsDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.LongType;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.StringType;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("completionDrawingDAO")
public class CompletionDrawingDAO extends BaseFWDAOImpl<CompletionDrawingBO, Long> {
	
	@Value("${completionDrawing.attachType}")
    private Long attachType;
	@Value("${completionPartner.attachType}")
    private Long partnerAttachType;
	@Autowired
    CatFileInvoiceBusinessImpl catFileInvoiceBusinessImpl;
	
    @Autowired
    CompletionDrawingBusinessImpl completionDrawingBusinessImpl;

    public CompletionDrawingDAO() {
        this.model = new CompletionDrawingBO();
    }

    public CompletionDrawingDAO(Session session) {
        this.session = session;
    }
    
    @SuppressWarnings("unchecked")
	public List<CompletionDrawingDTO> getCompletionDrawingSearch(CompletionDrawingDTO obj) {
		StringBuilder sqlbuilder = new StringBuilder(
				"SELECT V_CONSTRUCTION_HCQT.CONSTRT_CODE constrtCode, COMPLETION_DRAWING.DRAW_NAME drawName, COMPLETION_DRAWING.DRAW_CODE drawCode, V_CONSTRUCTION_HCQT.CONTRACT_CODE contractCode,"
						+ " V_CONSTRUCTION_HCQT.CONTRACT_NAME contractName, COMPLETION_DRAWING.STATUS_CA statusCa, COMPLETION_DRAWING.COMPLETION_DRAWING_ID completionDrawingId, COMPLETION_DRAWING.CONSTRUCT_ID constructId, "
						+ " UTIL_ATTACHED_DOCUMENTS.DOCUMENT_PATH documentPath, UTIL_ATTACHED_DOCUMENTS.DOCUMENT_NAME documentName, "
						+ " COMPLETION_DRAWING.A_MONITOR_ID amonitorId, COMPLETION_DRAWING.B_DIRECTOR_ID bdirectorId, COMPLETION_DRAWING.CREATOR_ID creatorId, COMPLETION_DRAWING.CREATED_DATE createdDate, COMPLETION_DRAWING.CREATED_USER_ID createdUserId, "
						+ " UTIL_ATTACHED_DOCUMENTS.ATTACH_ID attachId, "
						+ " CONSTR_COMPLETE_RECORDS_MAP.CONSTR_COMP_RE_MAP_ID constrCompReMapId, "
						+ " DOCUMENT_CA.COMMENT_CA comments, "
						+ " (SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :type1 AND PARENT_ID = (SELECT COMPLETION_DRAWING.A_MONITOR_ID FROM COMPLETION_DRAWING WHERE COMPLETION_DRAWING.COMPLETION_DRAWING_ID = :completionDrawingId"+")) amonitorPath, "
						+ " (SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :type1 AND PARENT_ID = (SELECT COMPLETION_DRAWING.B_DIRECTOR_ID FROM COMPLETION_DRAWING WHERE COMPLETION_DRAWING.COMPLETION_DRAWING_ID = :completionDrawingId"+")) bdirectorPath, "
						+ " (SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :type1 AND PARENT_ID = (SELECT COMPLETION_DRAWING.CREATOR_ID FROM COMPLETION_DRAWING WHERE COMPLETION_DRAWING.COMPLETION_DRAWING_ID = :completionDrawingId"+")) creatorPath "
						+ " FROM COMPLETION_DRAWING "
						+ " LEFT JOIN DOCUMENT_CA ON COMPLETION_DRAWING.DOCUMENT_CA_ID = DOCUMENT_CA.DOCUMENT_CA_ID "
						+ " INNER JOIN CONSTR_COMPLETE_RECORDS_MAP ON COMPLETION_DRAWING.COMPLETION_DRAWING_ID = CONSTR_COMPLETE_RECORDS_MAP.DATA_TABLE_ID_VALUE "
						+ " INNER JOIN V_CONSTRUCTION_HCQT ON V_CONSTRUCTION_HCQT.CONSTRUCT_ID = COMPLETION_DRAWING.CONSTRUCT_ID "
						+ " INNER JOIN UTIL_ATTACHED_DOCUMENTS ON COMPLETION_DRAWING.COMPLETION_DRAWING_ID = UTIL_ATTACHED_DOCUMENTS.PARENT_ID "
						+ " WHERE UTIL_ATTACHED_DOCUMENTS.TYPE = :type "
						+ " AND COMPLETION_DRAWING.IS_ACTIVE = 1 "
						+ " AND CONSTR_COMPLETE_RECORDS_MAP.DATA_TABLE_NAME = 'COMPLETION_DRAWING' ");

		if (StringUtils.isNotEmpty(obj.getConstrtCode())) {
			sqlbuilder.append(" AND V_CONSTRUCTION_HCQT.CONSTRT_CODE = :constrtCode ");
		}
		if (obj.getCompletionDrawingId()!=null){
			sqlbuilder.append(" AND COMPLETION_DRAWING.COMPLETION_DRAWING_ID = :completionDrawingId ");
		}
		if (StringUtils.isNotEmpty(obj.getDrawName())) {
			sqlbuilder.append(" AND COMPLETION_DRAWING.DRAW_NAME = :drawName ");
		}
		if (StringUtils.isNotEmpty(obj.getDrawCode())) {
			sqlbuilder.append(" AND COMPLETION_DRAWING.DRAW_CODE = :drawCode ");
		}
		if (obj.getConstructId() != null) {
			sqlbuilder.append(" AND V_CONSTRUCTION_HCQT.CONSTRUCT_ID = :constructId ");
		}
		if (obj.getContractId() != null){
			sqlbuilder.append(" AND V_CONSTRUCTION_HCQT.CONTRACT_ID = :contractId ");
		}
		sqlbuilder.append(" ORDER BY COMPLETION_DRAWING.COMPLETION_DRAWING_ID DESC ");
		SQLQuery query = getSession().createSQLQuery(sqlbuilder.toString());
		query.addScalar("constrtCode", StringType.INSTANCE);
		query.addScalar("drawName", StringType.INSTANCE);
		query.addScalar("drawCode", StringType.INSTANCE);
		query.addScalar("contractCode", StringType.INSTANCE);
		query.addScalar("contractName", StringType.INSTANCE);
		query.addScalar("statusCa", LongType.INSTANCE);
		query.addScalar("completionDrawingId", LongType.INSTANCE);
		query.addScalar("constructId", LongType.INSTANCE);
		query.addScalar("documentPath", StringType.INSTANCE);
		query.addScalar("documentName", StringType.INSTANCE);
		query.addScalar("amonitorId", LongType.INSTANCE);
		query.addScalar("bdirectorId", LongType.INSTANCE);
		query.addScalar("creatorId", LongType.INSTANCE);
		query.addScalar("attachId", LongType.INSTANCE);
		query.addScalar("constrCompReMapId", LongType.INSTANCE);
		query.addScalar("createdUserId", new LongType());
		query.addScalar("createdDate", new DateType());
		query.addScalar("amonitorPath", StringType.INSTANCE);
		query.addScalar("bdirectorPath", StringType.INSTANCE);
		query.addScalar("creatorPath", StringType.INSTANCE);
		query.addScalar("comments", StringType.INSTANCE);
		
		// query.setParameter("roll", a);
		query.setResultTransformer(Transformers.aliasToBean(CompletionDrawingDTO.class));

		if (StringUtils.isNotEmpty(obj.getConstrtCode())) {
			query.setParameter("constrtCode", obj.getConstrtCode());
		}
		if (obj.getConstructId()!= null) {
			query.setParameter("constructId", obj.getConstructId());
		}
		
		if (obj.getCompletionDrawingId()!=null){
			query.setParameter("completionDrawingId", obj.getCompletionDrawingId());
		}else {
			query.setParameter("completionDrawingId", -1);
		}
		if (StringUtils.isNotEmpty(obj.getDrawName())) {
			query.setParameter("drawName", obj.getDrawName());
		}
		if (StringUtils.isNotEmpty(obj.getDrawCode())) {
			query.setParameter("drawCode", obj.getDrawCode());
		}
		if (obj.getContractId() != null){
			query.setParameter("contractId", obj.getContractId());
		}
			query.setParameter("type", attachType);
			query.setParameter("type1", partnerAttachType);
		return query.list();
	}
	public List<CompletionDrawingBO> getCompletionDrawing(Long constructId) {

		Query q = getSession().createQuery(Joiner.on(" ").join("from completiondrawing e join fetch  e.constrconstructions c  where 1=1 ",
				                       constructId!=null ? " and c.constructId = :constructId" : ""));
		

		
		if (constructId!= null) {
			q.setParameter("constructId",  constructId);
		}
	   
		return q.list();
	}
	
	public boolean updateIsActive(List<Long> completionDrawingId){
    		Session session = getSession();
    		String listUpdate = new String(" IN (");
    		for (Long id : completionDrawingId) {
    			listUpdate = listUpdate + id + ",";
    		}
    		listUpdate = listUpdate.substring(0, listUpdate.length() - 1);
    		listUpdate = listUpdate + ") ";
			StringBuilder sql = new StringBuilder();
    		sql.append("UPDATE COMPLETION_DRAWING SET COMPLETION_DRAWING.IS_ACTIVE = '0' ");
    		sql.append("WHERE COMPLETION_DRAWING.COMPLETION_DRAWING_ID " + listUpdate);
    		session.createSQLQuery(sql.toString()).executeUpdate();
    		session.createSQLQuery("DELETE FROM CONSTR_COMPLETE_RECORDS_MAP WHERE DATA_TABLE_ID = 'COMPLETION_DRAWING_ID' "
    				+ "AND DATA_TABLE_ID_VALUE " + listUpdate).executeUpdate();
			return true;
    }
	
	public List<CompletionDrawingDTO> getDrawById(List<String> completionDrawingId){
		//List<CompletionDrawingDTO> list = new ArrayList<>();
		String list = new String(" IN (");
		for (String id : completionDrawingId) {
			list = list + id + ",";
		}
		list = list.substring(0, list.length() - 1);
		list = list + ") ";
		StringBuilder sqlbuilder = new StringBuilder(
				"SELECT UTIL_ATTACHED_DOCUMENTS.DOCUMENT_PATH documentPath, UTIL_ATTACHED_DOCUMENTS.DOCUMENT_NAME documentName, "
					+ " COMPLETION_DRAWING.STATUS_CA statusCa, COMPLETION_DRAWING.COMPLETION_DRAWING_ID completionDrawingId, UTIL_ATTACHED_DOCUMENTS.DOCUMENT_NAME documentName "
					+ " FROM COMPLETION_DRAWING "
					+ " INNER JOIN UTIL_ATTACHED_DOCUMENTS ON COMPLETION_DRAWING.COMPLETION_DRAWING_ID = UTIL_ATTACHED_DOCUMENTS.PARENT_ID "
					+ " WHERE COMPLETION_DRAWING.COMPLETION_DRAWING_ID "+list+" AND UTIL_ATTACHED_DOCUMENTS.TYPE = :type "
				);

		SQLQuery query = getSession().createSQLQuery(sqlbuilder.toString());
		query.addScalar("documentPath", StringType.INSTANCE);
		query.addScalar("documentName", StringType.INSTANCE);
		query.addScalar("statusCa", LongType.INSTANCE);
		query.addScalar("completionDrawingId", LongType.INSTANCE);
		
		query.setParameter("type", attachType);
		query.setParameter("type1", partnerAttachType);
		
		// query.setParameter("roll", a);
		query.setResultTransformer(Transformers.aliasToBean(CompletionDrawingDTO.class));		
		 
		return query.list();
		
	}
	
	@Transactional
	public void addListCompletionDrawing(List<CompletionDrawingDTO> obj) throws Exception{			
		for(CompletionDrawingDTO childobj : obj){
			completionDrawingBusinessImpl.saveTable(childobj);    	 	
		}
	}
	
	public CompletionDrawingDTO getPathById(Long completionDrawingId){
		StringBuilder sqlbuilder = new StringBuilder(
					"SELECT (SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :type1 AND PARENT_ID = (SELECT A_MONITOR_ID FROM COMPLETION_DRAWING WHERE COMPLETION_DRAWING_ID = :completionDrawingId"+")) amonitorPath, "
					+ " (SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :type1 AND PARENT_ID = (SELECT B_DIRECTOR_ID FROM COMPLETION_DRAWING WHERE COMPLETION_DRAWING_ID = :completionDrawingId"+")) bdirectorPath, "
					+ " (SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :type1 AND PARENT_ID = (SELECT CREATOR_ID FROM COMPLETION_DRAWING WHERE COMPLETION_DRAWING_ID = :completionDrawingId"+")) creatorPath "
					+ " FROM COMPLETION_DRAWING "
					+ " INNER JOIN UTIL_ATTACHED_DOCUMENTS ON COMPLETION_DRAWING.COMPLETION_DRAWING_ID = UTIL_ATTACHED_DOCUMENTS.PARENT_ID "
				);

		SQLQuery query = getSession().createSQLQuery(sqlbuilder.toString());
		query.addScalar("amonitorPath", StringType.INSTANCE);
		query.addScalar("bdirectorPath", StringType.INSTANCE);
		query.addScalar("creatorPath", StringType.INSTANCE);
		query.setParameter("completionDrawingId", completionDrawingId);
		
		// query.setParameter("roll", a);
		query.setResultTransformer(Transformers.aliasToBean(CompletionDrawingDTO.class));		
		 
		return (CompletionDrawingDTO) query.list().get(0);
	}
    @Transactional
    public Long saveTable( CompletionDrawingDTO  completionDrawing) throws Exception{
    	      Session session = getSession();
    	      Long completionDrawingId = (Long) session.save(completionDrawing.toModel());
    	      completionDrawing.getConstrCompleteRecordMap().setDataTableIdValue(completionDrawingId);     
    	      session.save(completionDrawing.getConstrCompleteRecordMap().toModel());
    	      return completionDrawingId;
     }  

}
