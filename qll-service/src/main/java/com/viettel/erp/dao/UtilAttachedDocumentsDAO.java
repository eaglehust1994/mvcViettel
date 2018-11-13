/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.erp.dao;

import com.viettel.erp.bo.UtilAttachedDocumentsBO;
import com.viettel.erp.dto.UtilAttachedDocumentsDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("utilAttachedDocumentsDAO")
public class UtilAttachedDocumentsDAO extends BaseFWDAOImpl<UtilAttachedDocumentsBO, Long> {

    public UtilAttachedDocumentsDAO() {
        this.model = new UtilAttachedDocumentsBO();
    }

    public UtilAttachedDocumentsDAO(Session session) {
        this.session = session;
    }
    
    @Value("${constrOrganization.attachTypeKey}")
	private Long attachTypeKey;
	
	@Value("${constrOrganization.attachTypeValue}")
	private Long attachTypeValue;
	
	@Value("${completionDrawing.attachType}")
	private Long attachType;
	
	@Value("${constructionAcceptance.attachType}")
	private Long attachTypeCA;
    
    public void updateUtilByParentIdAndType(String documentName,Long parentId, String documentPath, Long type) {
    	StringBuffer sql = new StringBuffer("update UTIL_ATTACHED_DOCUMENTS set Document_name = :documentName,"
    			+ " document_path =:documentPath where type = :type and parent_id = :parentId ");
    	SQLQuery query = getSession().createSQLQuery(sql.toString());
    	query.setParameter("documentName",documentName);
    	query.setParameter("documentPath",documentPath);
    	query.setParameter("type",type);
    	query.setParameter("parentId",parentId);
		query.executeUpdate();
    }
    
    public List<UtilAttachedDocumentsDTO> getListByParentIdAndType(Long parentId){
    	StringBuffer sql = new StringBuffer("SELECT ATTACH_ID attachId"
    			+ ", created_Date createdDate "
    			+ ",DOCUMENT_NAME documentName"
    			+ ",DOCUMENT_Path documentPath "
    			+ ",Type type "
    			+ "FROM UTIL_ATTACHED_DOCUMENTS ");
    	if(parentId != null && attachTypeKey != null && attachTypeValue != null){
    		sql.append(" WHERE PARENT_ID = (SELECT DATA_TABLE_ID_VALUE FROM CONSTR_COMPLETE_RECORDS_MAP WHERE CONSTR_COMP_RE_MAP_ID =:parentId) and type in ( :attachTypeKey, :attachTypeValue ) order by ATTACH_ID ");
    	}
    	SQLQuery query = getSession().createSQLQuery(sql.toString());
    	
    	query.addScalar("attachId", LongType.INSTANCE);
    	query.addScalar("type", LongType.INSTANCE);
    	query.addScalar("documentName", StringType.INSTANCE);
    	query.addScalar("createdDate", DateType.INSTANCE);
    	query.addScalar("documentPath", StringType.INSTANCE);
    	
    	query.setResultTransformer(Transformers.aliasToBean(UtilAttachedDocumentsDTO.class));
    	if(parentId != null && attachTypeKey != null && attachTypeValue != null){
    		query.setParameter("parentId", parentId);
        	query.setParameter("attachTypeKey", attachTypeKey);
        	query.setParameter("attachTypeValue", attachTypeValue);
    	}
    	
    	return  query.list();
    }
    
    public List<UtilAttachedDocumentsDTO> getListByParentIdAndTypeOrgan(Long parentId){
    	StringBuffer sql = new StringBuffer("SELECT ATTACH_ID attachId"
    			+ ", created_Date createdDate "
    			+ ",DOCUMENT_NAME documentName"
    			+ ",DOCUMENT_Path documentPath "
    			+ ",Type type "
    			+ "FROM UTIL_ATTACHED_DOCUMENTS ");
    	if(parentId != null && attachTypeKey != null && attachTypeValue != null){
    		sql.append(" WHERE PARENT_ID = :parentId and type in ( :attachTypeKey, :attachTypeValue ) order by ATTACH_ID ");
    	}
    	SQLQuery query = getSession().createSQLQuery(sql.toString());
    	
    	query.addScalar("attachId", LongType.INSTANCE);
    	query.addScalar("type", LongType.INSTANCE);
    	query.addScalar("documentName", StringType.INSTANCE);
    	query.addScalar("createdDate", DateType.INSTANCE);
    	query.addScalar("documentPath", StringType.INSTANCE);
    	
    	query.setResultTransformer(Transformers.aliasToBean(UtilAttachedDocumentsDTO.class));
    	if(parentId != null && attachTypeKey != null && attachTypeValue != null){
    		query.setParameter("parentId", parentId);
        	query.setParameter("attachTypeKey", attachTypeKey);
        	query.setParameter("attachTypeValue", attachTypeValue);
    	}
    	
    	return  query.list();
    }
    
    public List<UtilAttachedDocumentsDTO> getListOrganizationByParentId(Long parentId){
    	StringBuffer sql = new StringBuffer("SELECT ATTACH_ID attachId"
    			+ ", created_Date createdDate "
    			+ ",DOCUMENT_NAME documentName"
    			+ ",DOCUMENT_Path documentPath "
    			+ ",Type type "
    			+ "FROM UTIL_ATTACHED_DOCUMENTS ");
    	if(parentId != null && attachTypeKey != null && attachTypeValue != null){
    		sql.append(" WHERE PARENT_ID = :parentId and type in ( :attachTypeKey, :attachTypeValue ) order by ATTACH_ID ");
    	}
    	SQLQuery query = getSession().createSQLQuery(sql.toString());
    	
    	query.addScalar("attachId", LongType.INSTANCE);
    	query.addScalar("type", LongType.INSTANCE);
    	query.addScalar("documentName", StringType.INSTANCE);
    	query.addScalar("createdDate", DateType.INSTANCE);
    	query.addScalar("documentPath", StringType.INSTANCE);
    	
    	query.setResultTransformer(Transformers.aliasToBean(UtilAttachedDocumentsDTO.class));
    	if(parentId != null && attachTypeKey != null && attachTypeValue != null){
    		query.setParameter("parentId", parentId);
        	query.setParameter("attachTypeKey", attachTypeKey);
        	query.setParameter("attachTypeValue", attachTypeValue);
    	}
    	
    	return  query.list();
    }
    
    
    
    public List<UtilAttachedDocumentsDTO> getListByParentIdAndTypeDrawing(Long parentId){
    	StringBuffer sql = new StringBuffer("SELECT ATTACH_ID attachId"
    			+ ", created_Date createdDate "
    			+ ",DOCUMENT_NAME documentName"
    			+ ",DOCUMENT_Path documentPath "
    			+ ",Type type "
    			+ "FROM UTIL_ATTACHED_DOCUMENTS ");
    	if(parentId != null && attachType != null){
    		sql.append(" WHERE PARENT_ID = (SELECT DATA_TABLE_ID_VALUE FROM CONSTR_COMPLETE_RECORDS_MAP WHERE CONSTR_COMP_RE_MAP_ID =:parentId) and type = :attachType  order by ATTACH_ID ");
    	}
    	SQLQuery query = getSession().createSQLQuery(sql.toString());
    	
    	query.addScalar("attachId", LongType.INSTANCE);
    	query.addScalar("type", LongType.INSTANCE);
    	query.addScalar("documentName", StringType.INSTANCE);
    	query.addScalar("createdDate", DateType.INSTANCE);
    	query.addScalar("documentPath", StringType.INSTANCE);
    	
    	query.setResultTransformer(Transformers.aliasToBean(UtilAttachedDocumentsDTO.class));
    	if(parentId != null && attachType != null){
    		query.setParameter("parentId", parentId);
        	query.setParameter("attachType", attachType);
    	}
    	
    	return  query.list();
    }
    
    public boolean checkType(String attachId) {
		StringBuffer sqlBuffer = new StringBuffer("SELECT Type type"
				+ " FROM UTIL_ATTACHED_DOCUMENTS CO  "
				+ " WHERE CO.attach_Id = :attachId");
				
		SQLQuery query = getSession().createSQLQuery(sqlBuffer.toString());
		query.addScalar("type", LongType.INSTANCE);
		query.setParameter("attachId", attachId);
		
		Long type = (Long) query.uniqueResult();
		if(type==9453l) {
			return false;
		} else {
			return true;
		}
	}
    
    public void deleteDocument(List<String> listAttachId) {
		String list = new String(" IN (");
		for (String atachId : listAttachId) {
			list = list + atachId + ",";
		}
		list = list.substring(0, list.length() - 1);
		list = list + ") ";

		StringBuffer sql = new StringBuffer("delete UTIL_ATTACHED_DOCUMENTS where " + "attach_Id ");
		sql.append(list);
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.executeUpdate();
	}
    public void updateParentIdByAttachId( Long parentId,Long attachId){
    	String sql="Update UTIL_ATTACHED_DOCUMENTS set PARENT_ID=:parentId where ATTACH_ID=:attachId";
    	SQLQuery query=getSession().createSQLQuery(sql);
    	query.setParameter("parentId", parentId);
    	query.setParameter("attachId", attachId);
    	query.executeUpdate();
    }
    
    public UtilAttachedDocumentsDTO findByParentIdAndType(Long parentId, Long type){
    	StringBuffer sql = new StringBuffer("SELECT ATTACH_ID attachId"
    			+ ",DOCUMENT_NAME documentName"
    			+ ",DOCUMENT_PATH documentPath"
    			+ ",TYPE type"
    			+ ",PARENT_ID parentId"
    			+ ",CREATED_DATE createdDate"
    			+ ",LEVEL_ID levelId"
    			+ ",SIGNER signer"
    			+ ",CODE code"
    			+ ",SIGNED_DATE signedDate"
    			+ ",DESCRIPTION description"
    			+ ",USER_ID userId"
    			+ ",FILE_TYPE fileType"
    			+ ",DOCUMEN_TYPE_ID documenTypeId"
    			+ ",DOCUMENT_TYPE_NAME documentTypeName "
    			+ "FROM UTIL_ATTACHED_DOCUMENTS "
    			+ "WHERE TYPE = :type AND PARENT_ID = :parentId AND ROWNUM = 1");
    	SQLQuery query = getSession().createSQLQuery(sql.toString());
    	
    	query.addScalar("attachId", LongType.INSTANCE);
    	query.addScalar("documentName", StringType.INSTANCE);
    	query.addScalar("documentPath", StringType.INSTANCE);
    	query.addScalar("type", LongType.INSTANCE);
    	query.addScalar("parentId", LongType.INSTANCE);
    	query.addScalar("createdDate", new DateType());
    	query.addScalar("levelId", LongType.INSTANCE);
    	query.addScalar("signer", StringType.INSTANCE);
    	query.addScalar("code", StringType.INSTANCE);
    	query.addScalar("signedDate", new DateType());
    	query.addScalar("description", StringType.INSTANCE);
    	query.addScalar("userId", LongType.INSTANCE);
    	query.addScalar("fileType", LongType.INSTANCE);
    	query.addScalar("documenTypeId", LongType.INSTANCE);
    	query.addScalar("documentTypeName", StringType.INSTANCE);
    	
    	query.setResultTransformer(Transformers.aliasToBean(UtilAttachedDocumentsDTO.class));
    	
    	query.setParameter("type", type);
    	query.setParameter("parentId", parentId);
    	
    	return (UtilAttachedDocumentsDTO) query.uniqueResult();
    }
    
    public List<UtilAttachedDocumentsDTO> getListByParentIdCA(Long parentId){
    	StringBuffer sql = new StringBuffer("SELECT ATTACH_ID attachId"
    			+ ", created_Date createdDate "
    			+ ",DOCUMENT_NAME documentName"
    			+ ",DOCUMENT_Path documentPath "
    			+ ",Type type "
    			+ "FROM UTIL_ATTACHED_DOCUMENTS ");
    	if(parentId != null && attachType != null){
    		sql.append(" WHERE PARENT_ID = :parentId and type = :attachType  order by ATTACH_ID ");
    	}
    	SQLQuery query = getSession().createSQLQuery(sql.toString());
    	
    	query.addScalar("attachId", LongType.INSTANCE);
    	query.addScalar("type", LongType.INSTANCE);
    	query.addScalar("documentName", StringType.INSTANCE);
    	query.addScalar("createdDate", DateType.INSTANCE);
    	query.addScalar("documentPath", StringType.INSTANCE);
    	
    	query.setResultTransformer(Transformers.aliasToBean(UtilAttachedDocumentsDTO.class));
    	if(parentId != null && attachType != null){
    		query.setParameter("parentId", parentId);
        	query.setParameter("attachType", attachTypeCA);
    	}
    	
    	return  query.list();
    }
}
