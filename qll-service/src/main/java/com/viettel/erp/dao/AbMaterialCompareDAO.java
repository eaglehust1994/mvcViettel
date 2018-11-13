/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.erp.dao;

import java.util.Date;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.viettel.erp.bo.AbMaterialCompareBO;
import com.viettel.erp.dto.AbMaterialCompareDTO;
import com.viettel.erp.dto.CatFileInvoiceDTO;
import com.viettel.erp.rest.FileServiceImpl;
import com.viettel.ktts2.common.UEncrypt;
import com.viettel.service.base.dao.BaseFWDAOImpl;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("abMaterialCompareDAO")
public class AbMaterialCompareDAO extends BaseFWDAOImpl<AbMaterialCompareBO, Long> {

	@Autowired
	CatFileInvoiceDAO catFileInvoiceDAO;
	
    @Value("${completionEstimateAB.attachType}")
	private Long attachType;
    
    @Value("${folder_upload}")
	private String folderUpload;
    
    public AbMaterialCompareDAO() {
        this.model = new AbMaterialCompareBO();
    }

    public AbMaterialCompareDAO(Session session) {
        this.session = session;
    }
    
    @Transactional
    public Long saveTable( AbMaterialCompareDTO  abMaterialCompare) throws Exception{
    	  Session session = getSession();
    	  abMaterialCompare.setCreatedDate(new Date());
    	  String code = autoGenCode();
    	  abMaterialCompare.setCode(code);
	      System.out.println("Code abMaterialCompare==========================" + code);
    	  abMaterialCompare.setStatusCa(0l);
    	  abMaterialCompare.setIsActive(1l);
    	  abMaterialCompare.setCreatedUserId(abMaterialCompare.getCreatedUserId());
    	  Long abMaterialCompareId = (Long) session.save(abMaterialCompare.toModel());
	      abMaterialCompare.getUtilAttachedDocuments().setParentId(abMaterialCompareId);
	      abMaterialCompare.getUtilAttachedDocuments().setCreatedDate(new Date());
	      abMaterialCompare.getUtilAttachedDocuments().setType(attachType);
	      abMaterialCompare.getUtilAttachedDocuments().setDocumentPath(UEncrypt.decryptFileUploadPath(abMaterialCompare.getUtilAttachedDocuments().getDocumentPath()));
//	      String docPath = abMaterialCompare.getUtilAttachedDocuments().getDocumentPath().substring(folder.length()+1);
//	      abMaterialCompare.getUtilAttachedDocuments().setDocumentPath(docPath);
	   //   String documentPath = FileServiceImpl.fileUploadPath.substring(folderUpload.length()+1);
	     // abMaterialCompare.getUtilAttachedDocuments().setDocumentPath(documentPath);
		//  System.out.println("create documentPath =  " + documentPath);
		//  System.out.println("create documentName =  " + FileServiceImpl.fileUploadName);
		    
	      /*Long utilAttachedDocuments= (Long) */session.save(abMaterialCompare.getUtilAttachedDocuments().toModel());
	      
	      /*CatFileInvoiceDTO dto = new CatFileInvoiceDTO();*/
	      CatFileInvoiceDTO dto = catFileInvoiceDAO.onlyFindByTableName("AB_MATERIAL_COMPARE");
	      abMaterialCompare.getConstrCompleteRecordsMap().setCatFileInvoiceId(dto.getCatFileInvoiceId());
	      abMaterialCompare.getConstrCompleteRecordsMap().setDataTableName("AB_MATERIAL_COMPARE");
	      abMaterialCompare.getConstrCompleteRecordsMap().setDataTableId("AB_MATERIAL_COMPARE_ID");
	      abMaterialCompare.getConstrCompleteRecordsMap().setDataTableIdValue(abMaterialCompareId);
	      abMaterialCompare.getConstrCompleteRecordsMap().setLevelOrder(1l);
	      abMaterialCompare.getConstrCompleteRecordsMap().setConstructionId(abMaterialCompare.getConstructId());
	      abMaterialCompare.getConstrCompleteRecordsMap().setCreatedDate(new Date());
	      abMaterialCompare.getConstrCompleteRecordsMap().setCreatedUserId(abMaterialCompare.getCreatedUserId());
	      abMaterialCompare.getConstrCompleteRecordsMap().setCode(abMaterialCompare.getCode());
	      abMaterialCompare.getConstrCompleteRecordsMap().setStatus(0L);
	      Long constrCompReMapId= (Long) session.save(abMaterialCompare.getConstrCompleteRecordsMap().toModel());
	      return constrCompReMapId;
     }
    
    public AbMaterialCompareDTO getById(Long constructId) {
		StringBuilder sqlbuilder = new StringBuilder("SELECT "
 				+ "a.CONSTRUCT_ID constructId,"
 				+ "a.AB_MATERIAL_COMPARE_ID abMaterialCompareId " 				
 				+ "FROM AB_MATERIAL_COMPARE a " 				
 				+ "WHERE a.CONSTRUCT_ID = :constructId AND ROWNUM = 1");
 		SQLQuery query = getSession().createSQLQuery(sqlbuilder.toString());	 		
 		query.addScalar("constructId", LongType.INSTANCE);
 		query.addScalar("abMaterialCompareId", LongType.INSTANCE);
 		
 		query.setParameter("constructId", constructId);
 		
 		query.setResultTransformer(Transformers.aliasToBean(AbMaterialCompareDTO.class));

 		return (AbMaterialCompareDTO) query.uniqueResult();
	}
    
    public AbMaterialCompareDTO checkEstimateAB6(Long constructId){
    	StringBuilder sqlbuilder = new StringBuilder("SELECT "
 				+ "a.CONSTRUCT_ID constructId, "
 				+ "uad.document_name documentName, "
 				+ "uad.document_path documentPath, "
 				+ "a.A_DIRECTOR_ID aDirectorId, "
 				+ "a.B_DIRECTOR_ID bDirectorId, "
 				+ "a.A_HEAD_CONSTRUCT_ID aHeadConstructId,"
 				+ "a.A_HEAD_TECHNICAL_ID aHeadTechnicalId,"
 				+ "a.A_HEAD_FINANCE_ID aHeadFinanceId,"
 				+ "a.B_HEAD_CONSTRUCT_ID bHeadConstructId,"
 				+ "a.B_HEAD_ACCOUNT_ID bHeadAccountId,"
 				+ "a.Code code, "
 				+ "a.STATUS_CA statusCa, "
 				+ "a.DOCUMENT_CA_ID documentCaId, "
 				+ "a.AB_MATERIAL_COMPARE_ID abMaterialCompareId, "
 				+ "a.CREATED_USER_ID createdUserId "
 				+ "FROM AB_MATERIAL_COMPARE a "
 				+ "left join UTIL_ATTACHED_DOCUMENTS uad ON uad.TYPE = :type1 and uad.parent_id = a.AB_MATERIAL_COMPARE_ID "
 				+ "WHERE "
 				+ "a.CONSTRUCT_ID = :constructId AND ROWNUM = 1");
 		SQLQuery query = getSession().createSQLQuery(sqlbuilder.toString());	 		
 		query.addScalar("constructId", LongType.INSTANCE);
 		query.addScalar("documentName", StringType.INSTANCE);
 		query.addScalar("documentPath", StringType.INSTANCE);
 		query.addScalar("aDirectorId", LongType.INSTANCE);
 		query.addScalar("bDirectorId", LongType.INSTANCE);
 		query.addScalar("aHeadConstructId", LongType.INSTANCE);
 		query.addScalar("aHeadTechnicalId", LongType.INSTANCE);
 		query.addScalar("aHeadFinanceId", LongType.INSTANCE);
 		query.addScalar("bHeadConstructId", LongType.INSTANCE);
 		query.addScalar("bHeadAccountId", LongType.INSTANCE);
 		query.addScalar("code", StringType.INSTANCE);
 		query.addScalar("statusCa", LongType.INSTANCE);
 		query.addScalar("documentCaId", LongType.INSTANCE);
 		query.addScalar("abMaterialCompareId", LongType.INSTANCE);
 		query.addScalar("createdUserId", LongType.INSTANCE);
 		query.setParameter("constructId", constructId);
 		query.setParameter("type1", attachType);
 		query.setResultTransformer(Transformers.aliasToBean(AbMaterialCompareDTO.class));

 		return (AbMaterialCompareDTO) query.uniqueResult();
    }
    
    public Long getConstrComReMapId(Long idChild){
    	//Long id= 0l;
    	StringBuilder sqlbuilder = new StringBuilder("select CONSTR_COMP_RE_MAP_ID id "
    			+ " from CONSTR_COMPLETE_RECORDS_MAP "
    			+ " where DATA_TABLE_NAME = 'AB_MATERIAL_COMPARE' "
    			+ " and DATA_TABLE_ID_VALUE = :idChild");
    	SQLQuery query = getSession().createSQLQuery(sqlbuilder.toString());	
    	query.addScalar("id", LongType.INSTANCE);
    	query.setParameter("idChild", idChild);
    	Long id = (Long) query.uniqueResult();
    	return id;
    }
    
    @SuppressWarnings("unchecked")
	public String autoGenCode() {
		StringBuffer sql = new StringBuffer(
				"select get_next_code('AB_MATERIAL_COMPARE', 'CODE','QT-AB6',6) code from dual");
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("code", StandardBasicTypes.STRING);
		return (String) query.uniqueResult();
	}
}
