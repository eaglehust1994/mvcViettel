/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.erp.dao;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Joiner;
import com.viettel.erp.bo.EstimatesWorkItemsBO;
import com.viettel.erp.dto.AbDetailPriceNewDTO;
import com.viettel.erp.dto.EstimatesWorkItemsDTO;
import com.viettel.erp.dto.WorkItemsAcceptanceDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("estimatesWorkItemsDAO")
public class EstimatesWorkItemsDAO extends BaseFWDAOImpl<EstimatesWorkItemsBO, Long> {
	
	@Value("${completionPartner.attachType}")
    private Long partnerAttachType;

	public EstimatesWorkItemsDAO() {
		this.model = new EstimatesWorkItemsBO();
	}

	public EstimatesWorkItemsDAO(Session session) {
		this.session = session;
	}

	// ngoccx
	
	@SuppressWarnings("unchecked")
	public List<EstimatesWorkItemsDTO> getEstimatesWorkItemsSearchAccept(EstimatesWorkItemsDTO obj) {
		StringBuilder sqlbuilder = new StringBuilder(
				"SELECT V_CONSTRUCTION_HCQT.CONSTRT_CODE constrtCode, "
						+ "ESTIMATES_WORK_ITEMS.WORK_ITEM_CODE workItemCode, "
						+ "ESTIMATES_WORK_ITEMS.WORK_ITEM_NAME workItemName,"
						+ "ESTIMATES_WORK_ITEMS.UNIT unit, "
						+ "ESTIMATES_WORK_ITEMS.WORK_AMOUNT workAmount,"
						+ "ESTIMATES_WORK_ITEMS.UNIT_PRICE unitPrice,"
						+ "ESTIMATES_WORK_ITEMS.STATUS status " 
						+ "FROM ESTIMATES_WORK_ITEMS "
						+ "INNER JOIN CONSTR_ESTIMATE_INFO ON ESTIMATES_WORK_ITEMS.CONSTR_ESTIMATE_INFO_ID = CONSTR_ESTIMATE_INFO.CONSTR_ESTIMATE_INFO_ID "
						+ "INNER JOIN V_CONSTRUCTION_HCQT ON V_CONSTRUCTION_HCQT.CONSTRUCT_ID = CONSTR_ESTIMATE_INFO.CONSTRUCTION_ID "
						+ "WHERE ESTIMATES_WORK_ITEMS.PROGRESS_TYPE = 3 ");
		if(obj.getCheckcode() == null){
			sqlbuilder.append(" ");
		}else{
			if (obj.getCheckcode() == 2) {
			sqlbuilder.append(" ");
			} 
			if (obj.getCheckcode() == 1) {
				sqlbuilder.append("AND ESTIMATES_WORK_ITEMS.STATUS = 1");
			}
			if (obj.getCheckcode() == 0) {
				sqlbuilder.append("AND ESTIMATES_WORK_ITEMS.STATUS = 0");
			}
		}

		if (StringUtils.isNotEmpty(obj.getConstrtCode())) {
			sqlbuilder.append(" AND V_CONSTRUCTION_HCQT.CONSTRT_CODE = :constrtCode ");
		}
		if (obj.getConstrType() != null) {
			sqlbuilder.append(" AND V_CONSTRUCTION_HCQT.CONSTR_TYPE = :constrType ");
		}
		if (StringUtils.isNotEmpty(obj.getConstrtName())) {
			sqlbuilder.append(" AND V_CONSTRUCTION_HCQT.CONSTRT_NAME = :constrtName ");
		}
		if (obj.getProvinceId() != null) {
			sqlbuilder.append(" AND V_CONSTRUCTION_HCQT.PROVINCE_ID = :provinceId ");
		}
		
		SQLQuery query = getSession().createSQLQuery(sqlbuilder.toString());
		query.addScalar("constrtCode", StringType.INSTANCE);
		query.addScalar("workItemCode", StringType.INSTANCE);
		query.addScalar("workItemName", StringType.INSTANCE);
		query.addScalar("unit", StringType.INSTANCE);
		query.addScalar("workAmount", new DoubleType());
		query.addScalar("unitPrice", DoubleType.INSTANCE);
		query.addScalar("status", LongType.INSTANCE);

		// query.setParameter("roll", a);
		query.setResultTransformer(Transformers.aliasToBean(EstimatesWorkItemsDTO.class));

		if (StringUtils.isNotEmpty(obj.getConstrtCode())) {
			query.setParameter("constrtCode", obj.getConstrtCode());
		}
		if (obj.getConstrType() != null) {
			query.setParameter("constrType", obj.getConstrType());
		}
		if (StringUtils.isNotEmpty(obj.getConstrtName())) {
			query.setParameter("constrtName", obj.getConstrtName());
		}
		if (obj.getProvinceId() != null) {
			query.setParameter("provinceId", obj.getProvinceId());
		}
		return query.list();
	}
	//Nha
	public List<EstimatesWorkItemsDTO> getByConstructionId(Long id) {
		StringBuilder sqlbuilder = new StringBuilder(" SELECT CEI.CONSTRUCTION_ID constructionId,"
						+ " EWI.WORK_ITEM_CODE workItemCode,"				
						+ " EWI.WORK_ITEM_NAME workItemName,"
						+ " EWI.UNIT unit,"
						+ " EWI.TYPE type,"
						+ " EWI.WORK_AMOUNT workAmount,"
						+ " CAWL.EVALUATE_QUANTITY evaluateQuantity,"
						+ " CEI.CONSTR_ESTIMATE_INFO_ID constrEstimateInfo,"
						+ " EWI.ESTIMATES_WORK_ITEM_ID estimateWorkItemId,"
						+ " CASE WHEN EWI.WORK_AMOUNT >= CAWL.EVALUATE_QUANTITY Then EWI.WORK_AMOUNT - CAWL.EVALUATE_QUANTITY ELSE null end giam,"
						+ " CASE WHEN EWI.WORK_AMOUNT <= CAWL.EVALUATE_QUANTITY Then CAWL.EVALUATE_QUANTITY - EWI.WORK_AMOUNT ELSE null end tang"
						+ " FROM CONSTR_ESTIMATE_INFO CEI"
						+ " INNER JOIN ESTIMATES_WORK_ITEMS EWI ON CEI.CONSTR_ESTIMATE_INFO_ID = EWI.CONSTR_ESTIMATE_INFO_ID"
						+ " JOIN CONSTR_CONSTRUCTIONS CC ON CEI.CONSTRUCTION_ID = CC.CONSTRUCT_ID"
						+ " JOIN CONSTRUCTION_ACCEPTANCE CA ON CA.CONSTRUCT_ID= CC.CONSTRUCT_ID"
						+ " INNER JOIN CONSTR_ACCEPT_WORK_LIST CAWL ON"
						+ " (EWI.ESTIMATES_WORK_ITEM_ID = CAWL.ESTIMATES_WORK_ITEM_ID AND CAWL.CONSTRUCTION_ACCEPTANCE_ID = CA.CONSTRUCTION_ACCEPTANCE_ID)"						
						+ " WHERE EWI.PROGRESS_TYPE = 3 AND CA.STATUS_CA=2 AND (CA.IS_ACTIVE=1 OR CA.IS_ACTIVE IS NULL)"
						+ " AND EWI.WORK_AMOUNT IS NOT NULL AND CAWL.EVALUATE_QUANTITY IS NOT NULL AND CEI.CONSTRUCTION_ID = :id "  
						+ " ORDER BY EWI.TYPE ASC");
		

		SQLQuery query = getSession().createSQLQuery(sqlbuilder.toString());	
		//query.addScalar("constructionId", LongType.INSTANCE);
		query.setParameter("id", id);
		query.addScalar("tang", DoubleType.INSTANCE);
		query.addScalar("giam", DoubleType.INSTANCE);
		query.addScalar("workItemCode", StringType.INSTANCE);
		query.addScalar("workItemName", StringType.INSTANCE);
		query.addScalar("unit", StringType.INSTANCE);
		query.addScalar("workAmount", new DoubleType());
		query.addScalar("type", LongType.INSTANCE);
		query.addScalar("evaluateQuantity", new DoubleType());		
	
		query.setResultTransformer(Transformers.aliasToBean(EstimatesWorkItemsDTO.class));	
	
		return query.list();
	}
	
	// tungpv
	@SuppressWarnings("unchecked")
	public List<EstimatesWorkItemsDTO> doSearchEstimatesWorkItems(EstimatesWorkItemsDTO criteria) {
		String sql = "SELECT " 
				+ "EWI.ESTIMATES_WORK_ITEM_ID estimatesWorkItemId, " 
				+ "EWI.ESTIMATES_ITEM_CHILD_ID estimatesItemChildId, " 
				+ "EWI.CONSTR_ESTIMATE_INFO_ID constrEstimateInfoId, " 
				+ "EWI.PROGRESS_TYPE progressType, " 
				+ "EIC.ITEMS_CODE itemsCode, "
				+ "EIC.ITEM_NAME itemName, " 
				+ "EWI.WORK_ITEM_CODE workItemCode, " 
				+ "EWI.WORK_ITEM_NAME workItemName, "
				+ "EWI.TYPE type, "
				+ "EWI.STATUS status, "
				+ "cc.CONSTRUCT_ID constructionId " 
				+ "from ESTIMATES_WORK_ITEMS ewi JOIN CONSTR_ESTIMATE_INFO cei "
				+ "ON ewi.CONSTR_ESTIMATE_INFO_ID = cei.CONSTR_ESTIMATE_INFO_ID "
				+ "LEFT JOIN CONSTR_CONSTRUCTIONS cc "
				+ "ON cei.CONSTRUCTION_ID = cc.CONSTRUCT_ID "
				+ "LEFT JOIN ESTIMATES_ITEMS_CHILD eic ON eic.ESTIMATES_ITEM_CHILD_ID = ewi.ESTIMATES_ITEM_CHILD_ID "
				+ "WHERE (( ewi.PROGRESS_TYPE = 3 AND ewi.TYPE != 2) OR (ewi.type = 2 AND (ewi.WORK_TYPE IS NULL or ewi.WORK_TYPE !=2)))"
				+ "AND ewi.STATUS NOT IN (1,2) AND cc.CONSTRUCT_ID = :constructId " ;

		StringBuilder sqlBuilder = new StringBuilder(sql);
		if (criteria.getEstimatesWorkItemId() != null) {
			sqlBuilder.append(" AND EWI.ESTIMATES_WORK_ITEM_ID = :estimatesWorkItemId ");
		}
		if (StringUtils.isNotEmpty(criteria.getItemsCode())) {
    		sqlBuilder.append(" AND LOWER(EIC.ITEMS_CODE) LIKE :itemCode");
    	}
		if (StringUtils.isNotEmpty(criteria.getItemName())) {
    		sqlBuilder.append(" AND LOWER(EIC.ITEM_NAME) LIKE :itemName");
    	}
		SQLQuery query = getSession().createSQLQuery(sqlBuilder.toString());
		query.addScalar("estimatesWorkItemId", LongType.INSTANCE);
		query.addScalar("estimatesItemChildId", LongType.INSTANCE);
		query.addScalar("constrEstimateInfoId", LongType.INSTANCE);
		query.addScalar("progressType", LongType.INSTANCE);
		query.addScalar("itemsCode", StringType.INSTANCE);
		query.addScalar("itemName", StringType.INSTANCE);
		query.addScalar("workItemCode", StringType.INSTANCE);
		query.addScalar("workItemName", StringType.INSTANCE);
		query.addScalar("type", LongType.INSTANCE);
		query.addScalar("status", LongType.INSTANCE);
		query.addScalar("constructionId", LongType.INSTANCE);
		query.setParameter("constructId", criteria.getConstructionId());
		query.setResultTransformer(Transformers.aliasToBean(EstimatesWorkItemsDTO.class));
		if (criteria.getEstimatesWorkItemId() != null) {
			query.setParameter("estimatesWorkItemId", criteria.getEstimatesWorkItemId());
		}
		if (StringUtils.isNotEmpty(criteria.getItemsCode())) {
			  query.setParameter("itemCode", "%"+criteria.getItemsCode().toLowerCase() + "%");
		}
		if (StringUtils.isNotEmpty(criteria.getItemName())) {
			  query.setParameter("itemName", "%"+criteria.getItemName().toLowerCase() + "%");
		}
		// query.setMaxResults(100);
		return query.list();
	}
	//search cong viec xay dung -- work_type != 2
	@SuppressWarnings("unchecked")
	public List<EstimatesWorkItemsDTO> getAllEstimatesWorkItems(Long id) {
		String sql = "SELECT " 
				+ "EWI.ESTIMATES_WORK_ITEM_ID estimatesWorkItemId, " 
				+ "EWI.ESTIMATES_ITEM_CHILD_ID estimatesItemChildId, " 
				+ "EWI.CONSTR_ESTIMATE_INFO_ID constrEstimateInfoId, " 
				+ "EWI.PROGRESS_TYPE progressType, " 
				+ "EIC.ITEMS_CODE itemsCode, "
				+ "EIC.ITEM_NAME itemName, "
				+ "EWI.WORK_ITEM_CODE workItemCode, "
				+ "EWI.WORK_ITEM_NAME workItemName, "
				+ "EWI.TYPE type, "
				+ "EWI.STATUS status, "
				+ "cc.CONSTRUCT_ID constructionId " 
				+ "from ESTIMATES_WORK_ITEMS ewi "
				+ "LEFT JOIN CONSTR_ESTIMATE_INFO cei "
				+ "ON ewi.CONSTR_ESTIMATE_INFO_ID = cei.CONSTR_ESTIMATE_INFO_ID "
				+ "LEFT JOIN CONSTR_CONSTRUCTIONS cc "
				+ "ON cei.CONSTRUCTION_ID = cc.CONSTRUCT_ID "
				+ "LEFT JOIN ESTIMATES_ITEMS_CHILD eic ON eic.ESTIMATES_ITEM_CHILD_ID = ewi.ESTIMATES_ITEM_CHILD_ID "
				+ "WHERE ( (ewi.PROGRESS_TYPE = 3 AND ewi.TYPE != 2) OR (ewi.type = 2 AND(ewi.WORK_TYPE IS NULL or ewi.WORK_TYPE !=2))) AND ewi.STATUS NOT IN (1,2) AND cc.CONSTRUCT_ID = :id" ;
		StringBuilder sqlBuilder = new StringBuilder(sql);
		SQLQuery query = getSession().createSQLQuery(sqlBuilder.toString());
		query.addScalar("estimatesWorkItemId", LongType.INSTANCE);
		query.addScalar("estimatesItemChildId", LongType.INSTANCE);
		query.addScalar("constrEstimateInfoId", LongType.INSTANCE);
		query.addScalar("progressType", LongType.INSTANCE);
		query.addScalar("itemsCode", StringType.INSTANCE);
		query.addScalar("itemName", StringType.INSTANCE);
		query.addScalar("workItemCode", StringType.INSTANCE);
		query.addScalar("workItemName", StringType.INSTANCE);
		query.addScalar("type", LongType.INSTANCE);
		query.addScalar("status", LongType.INSTANCE);
		query.addScalar("constructionId", LongType.INSTANCE);
		query.setParameter("id", id);
		query.setResultTransformer(Transformers.aliasToBean(EstimatesWorkItemsDTO.class));
		// query.setMaxResults(100);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<EstimatesWorkItemsDTO> doSearchByWorkItemsAcceptanceId(WorkItemsAcceptanceDTO  workItemsAcceptance) {
		String sql = "SELECT " 
				+ "EWI.ESTIMATES_WORK_ITEM_ID estimatesWorkItemId, " 
				+ "EWI.ESTIMATES_ITEM_CHILD_ID estimatesItemChildId, " 
				+ "EWI.CONSTR_ESTIMATE_INFO_ID constrEstimateInfoId, " 
				+ "EWI.PROGRESS_TYPE progressType, " 
				+ "EIC.ITEMS_CODE itemsCode, "
				+ "EIC.ITEM_NAME itemName, " 
				+ "EWI.WORK_ITEM_CODE workItemCode, " 
				+ "EWI.WORK_ITEM_NAME workItemName, "
				+ "EWI.TYPE type, "
				+ "EWI.STATUS status, "
				+ "cc.CONSTRUCT_ID constructionId " 
				+ "from ESTIMATES_WORK_ITEMS ewi JOIN CONSTR_ESTIMATE_INFO cei "
				+ "ON ewi.CONSTR_ESTIMATE_INFO_ID = cei.CONSTR_ESTIMATE_INFO_ID "
				+ "LEFT JOIN CONSTR_CONSTRUCTIONS cc "
				+ "ON cei.CONSTRUCTION_ID = cc.CONSTRUCT_ID "
				+ "LEFT JOIN ESTIMATES_ITEMS_CHILD eic ON eic.ESTIMATES_ITEM_CHILD_ID = ewi.ESTIMATES_ITEM_CHILD_ID "
				//+ "WHERE ewi.PROGRESS_TYPE = 3 AND ewi.STATUS NOT IN (1,2) AND cc.CONSTRUCT_ID = " + workItemsAcceptance.getConstructId();
				+ "WHERE 1 = 1";

		StringBuilder sqlBuilder = new StringBuilder(sql);
		
		if(workItemsAcceptance.getConstructId() != null){
			sqlBuilder.append(" AND cc.CONSTRUCT_ID = :constructId");
		}
		
		if (workItemsAcceptance.getWorkItemsAcceptanceId() != null) {
			sqlBuilder.append(" and EWI.ESTIMATES_WORK_ITEM_ID "
					+ "in (select ESTIMATES_WORK_ITEM_ID from work_items_accept_list "
					+ "where WORK_ITEMS_ACCEPTANCE_ID = :workItemsAcceptanceId)");
		}
		SQLQuery query = getSession().createSQLQuery(sqlBuilder.toString());
		query.addScalar("estimatesWorkItemId", LongType.INSTANCE);
		query.addScalar("estimatesItemChildId", LongType.INSTANCE);
		query.addScalar("constrEstimateInfoId", LongType.INSTANCE);
		query.addScalar("progressType", LongType.INSTANCE);
		query.addScalar("itemsCode", StringType.INSTANCE);
		query.addScalar("itemName", StringType.INSTANCE);
		query.addScalar("workItemCode", StringType.INSTANCE);
		query.addScalar("workItemName", StringType.INSTANCE);
		query.addScalar("type", LongType.INSTANCE);
		query.addScalar("status", LongType.INSTANCE);
		query.addScalar("constructionId", LongType.INSTANCE);

		query.setResultTransformer(Transformers.aliasToBean(EstimatesWorkItemsDTO.class));
		if (workItemsAcceptance.getWorkItemsAcceptanceId() != null) {
			query.setParameter("workItemsAcceptanceId", workItemsAcceptance.getWorkItemsAcceptanceId());
		}
		if (workItemsAcceptance.getConstructId() != null) {
			query.setParameter("constructId", workItemsAcceptance.getConstructId());
		}
		// query.setMaxResults(100);
		return query.list();
	}
	//end tungpv
	
	//minhpvn - import cong trinh
	@SuppressWarnings("unchecked")
	public List<EstimatesWorkItemsDTO> doSearchSceneGenerateWorkListCongTrinh(EstimatesWorkItemsDTO dto,String mode) {
		String sql = "SELECT " 
				+ " EWI.ESTIMATES_WORK_ITEM_ID estimatesWorkItemId, " 
				+ " EWI.ESTIMATES_ITEM_CHILD_ID estimatesItemChildId, " 
				+ " EWI.CONSTR_ESTIMATE_INFO_ID constrEstimateInfoId, " 
				+ " EWI.WORK_ITEM_CODE workItemCode, "
				+ " EWI.WORK_ITEM_NAME workItemName, "
				+ " EWI.TYPE type, "
				+ " EWI.UNIT unit, "
				+ " EWI.WORK_AMOUNT workAmount, "
				+ " ESTIMATES_ITEMS_CHILD.ITEM_NAME itemName"
				+ " FROM ESTIMATES_WORK_ITEMS EWI INNER JOIN CONSTR_ESTIMATE_INFO CEI ON "
				+ " EWI.CONSTR_ESTIMATE_INFO_ID = CEI.CONSTR_ESTIMATE_INFO_ID "
				+ " INNER JOIN CONSTR_CONSTRUCTIONS CC ON CEI.CONSTRUCTION_ID = CC.CONSTRUCT_ID "
				+ " JOIN ESTIMATES_ITEMS_CHILD ON EWI.ESTIMATES_ITEM_CHILD_ID = ESTIMATES_ITEMS_CHILD.ESTIMATES_ITEM_CHILD_ID "
				+ " WHERE EWI.PROGRESS_TYPE = 3 AND CC.CONSTRUCT_ID = :constructId " ;
		StringBuilder sqlBuilder = new StringBuilder(sql);
		if("EXPORT".equals(mode)){
			if(dto.getTypeworkitemIncontract()==true){
				sqlBuilder.append("and (EWI.TYPE = 1 or EWI.TYPE IS NULL ) ");
			}
			if(dto.getType()!=null&&dto.getTypeworkitemIncontract()==false){
				sqlBuilder.append("and EWI.TYPE = :type ");
			}
		}
		
		sqlBuilder.append(" ORDER BY EWI.ESTIMATES_WORK_ITEM_ID  ASC");
		System.out.println("query====linhnn====="+sqlBuilder.toString());
		SQLQuery query = getSession().createSQLQuery(sqlBuilder.toString());
		query.addScalar("estimatesWorkItemId", LongType.INSTANCE);
		query.addScalar("estimatesItemChildId", LongType.INSTANCE);
		query.addScalar("constrEstimateInfoId", LongType.INSTANCE);
		query.addScalar("workItemCode", StringType.INSTANCE);
		query.addScalar("workItemName", StringType.INSTANCE);
		query.addScalar("type", LongType.INSTANCE);
		query.addScalar("unit", StringType.INSTANCE);
		query.addScalar("workAmount", DoubleType.INSTANCE);
		query.addScalar("itemName", StringType.INSTANCE);
		
		query.setParameter("constructId", dto.getConstructId());
		
		if(dto.getType() != null&&"EXPORT".equals(mode)&&dto.getTypeworkitemIncontract()==false){
		query.setParameter("type",dto.getType());
		}
		query.setResultTransformer(Transformers.aliasToBean(EstimatesWorkItemsDTO.class));
		// query.setMaxResults(100);
		return query.list();
	}
	
	//dodt
    //Bảng diễn giải khối lượng xây lắp hoàn thành
	 public List<EstimatesWorkItemsBO> getWorkItem(EstimatesWorkItemsDTO obj) {

			Query q = getSession().createQuery(Joiner.on(" ").join("from estimatesworkitems e Join fetch e.constrestimateinfo ci Join fetch ci.constrconstructions cc  where 1=1 AND e.progressType = 3",
					obj.getProgressTypeAnalyst() != null ? " and e.estimatesWorkItemId IN ( select  eda.estimatesWorkItems.estimatesWorkItemId  from estimatesDetailAnalysts eda where eda.progressType = :progressTypeAnalyst )":"",
					obj.getCheckstatus() != null  ? " and e.status != 1" : "",
					obj.getStatus() !=null ? " and e.status = :status" : "",
					obj.getTypeworkitemIncontract() == true ? " and (e.type =1 OR e.type is null) " : "",
					obj.getConstructionId() !=null ? " and cc.constructId = :constructionId" : "",
					obj.getTypeworkitem() !=null ? " and e.type = :type" : "",
					"order by e.estimatesWorkItemId ASC"));

			
			if (obj.getConstructionId()!= null) {
				q.setParameter("constructionId",  obj.getConstructionId());
			}
			
			if (obj.getProgressTypeAnalyst()!= null) {
				q.setParameter("progressTypeAnalyst",  obj.getProgressTypeAnalyst());
			}
			if (obj.getStatus()!= null) {
				q.setParameter("status",  obj.getStatus());
			}
			if (obj.getTypeworkitem()!= null) {
				q.setParameter("type",  obj.getTypeworkitem());
			}
			
			return q.list();
		}
	 
	 public List<EstimatesWorkItemsBO> getForExportWorkSheet(EstimatesWorkItemsDTO obj) {

			Query q = getSession().createQuery(Joiner.on(" ").join("from estimatesworkitems e Join fetch e.constrestimateinfo ci Join fetch ci.constrconstructions cc  where 1=1 AND e.progressType = 3 and estimatesDetailAnalysts.progressType=2 ",
					obj.getCheckstatus() != null  ? " and e.status != 1" : "",
					obj.getStatus() !=null ? " and e.status = :status" : "",
					obj.getConstructionId() !=null ? " and cc.constructId = :constructionId" : "",
					obj.getTypeworkitem() !=null ? " and e.type = :type" : ""));

			
			if (obj.getConstructionId()!= null) {
				q.setParameter("constructionId",  obj.getConstructionId());
			}
			if (obj.getStatus()!= null) {
				q.setParameter("status",  obj.getStatus());
			}
			if (obj.getTypeworkitem()!= null) {
				q.setParameter("type",  obj.getTypeworkitem());
			}
			
			return q.setCacheable(true).list();
		}
    
    
    public List<EstimatesWorkItemsDTO> getWorkItemDetail(EstimatesWorkItemsDTO obj) {
    	String sql = "SELECT"
   			  + " ewi.ESTIMATES_WORK_ITEM_ID estimatesWorkItemId,"
   		      + " ewi.TYPE type, " 
   		      + " ewi.PROGRESS_TYPE progressType, "
   		      + " ewi.WORK_ITEM_NAME workItemName,"
   		      + " ewi.WORK_ITEM_CODE workItemCode,"
   		      + " ewi.UNIT_PRICE unitPrice, "
   		      + " ewi.UNIT unit, "
   		      + " ewi.WORK_AMOUNT workAmount,"
   		      + " caw.CONSTR_ACCEPT_WORK_LIST_ID constrAcceptWorkListId,"
   		      + " caw.EXPLAIN explain,"
   		      + " caw.EXECUTE_QUANTITY executeQuantity,"
   		      + " caw.EVALUATE_QUANTITY evaluateQuantity,"
   		      + " caw.EVALUATE_COMMENTS evaluateComments,"
   		      + " caw.INSTANCE_DRAW_CODE instanceDrawCode,"
   		      + " caw.COMMENTS comments,"
   		      + " caw.CONSTRUCTION_ACCEPTANCE_ID constructionAcceptanceId,"
   		      + " caw.SETTLE_UNIT_PRICE settleUnitPrice,"
   		      + " caw.EVALUATE_UNIT_PRICE evaluateUnitPrice"
   		      
   		      + " FROM ESTIMATES_WORK_ITEMS ewi"
   		      + " INNER JOIN CONSTR_ESTIMATE_INFO cei ON ewi.CONSTR_ESTIMATE_INFO_ID = cei.CONSTR_ESTIMATE_INFO_ID"
   		      + " INNER JOIN CONSTR_CONSTRUCTIONS cc ON cei.CONSTRUCTION_ID = cc.CONSTRUCT_ID"      
   		      + " INNER JOIN CONSTRUCTION_ACCEPTANCE ca On ca.CONSTRUCT_ID=cc.CONSTRUCT_ID"      
   		      + " INNER JOIN CONSTR_ACCEPT_WORK_LIST caw On (ewi.ESTIMATES_WORK_ITEM_ID=caw.ESTIMATES_WORK_ITEM_ID and caw.CONSTRUCTION_ACCEPTANCE_ID=ca.CONSTRUCTION_ACCEPTANCE_ID)"      
   		      + " WHERE ewi.PROGRESS_TYPE = 3 AND ewi.TYPE = 1 AND ca.STATUS_CA = 2 AND ewi.ESTIMATES_WORK_ITEM_ID = :estimatesWrkItemId ";
   	//AND ca.STATUS_CA = 2 
  		StringBuilder sqlBuilder = new StringBuilder(sql);
  		SQLQuery query = getSession().createSQLQuery(sqlBuilder.toString());	
  		 query.setParameter("estimatesWrkItemId", obj.getEstimatesWorkItemId());
  		
  		 query.addScalar("estimatesWorkItemId", LongType.INSTANCE);
  		 query.addScalar("type", LongType.INSTANCE);
  		 query.addScalar("progressType", LongType.INSTANCE);
  		 query.addScalar("workItemName", StringType.INSTANCE);
  		 query.addScalar("unitPrice", DoubleType.INSTANCE);
  		 query.addScalar("unit", StringType.INSTANCE);
  		 query.addScalar("workAmount", new DoubleType());
  		 query.addScalar("executeQuantity", new DoubleType());
  		 query.addScalar("workItemCode", StringType.INSTANCE);
  		 query.addScalar("explain", StringType.INSTANCE);
		 
		query.addScalar("constrAcceptWorkListId", LongType.INSTANCE);
		query.addScalar("evaluateQuantity", new DoubleType());
		query.addScalar("evaluateComments", StringType.INSTANCE);
		query.addScalar("instanceDrawCode", StringType.INSTANCE);
		query.addScalar("comments", StringType.INSTANCE);
		query.addScalar("constructionAcceptanceId", LongType.INSTANCE);
		query.addScalar("settleUnitPrice", new DoubleType());
		query.addScalar("evaluateUnitPrice", new DoubleType());

  		query.setResultTransformer(Transformers.aliasToBean(EstimatesWorkItemsDTO.class));
		
  		return query.list();
	}
    
    @SuppressWarnings("unchecked")
	public List<EstimatesWorkItemsDTO> getAllEstimatesWorkInsideContract(EstimatesWorkItemsDTO obj) {
       	String sql = "SELECT "
       			+"EWI.ESTIMATES_WORK_ITEM_ID estimatesWorkItemId, "
       			+"EWI.WORK_ITEM_NAME  workItemName," //hangmuccongviec
       			+"EWI.WORK_ITEM_CODE workItemCode,"
       			+"EWI.UNIT unit," //donvitinh
       			+"EWI.WORK_AMOUNT  workAmount, " //KHOILUONGGIAOKHOAN
       			+"EWI.UNIT_PRICE  unitPrice,  " //DONGIAGIAOKHOAN
       			+"CAW.EXECUTE_QUANTITY executeQuantity ,"//khoiluongdenghi defaul= khoiluongpheduyet
       			+"CAW.SETTLE_UNIT_PRICE settleUnitPrice," //dongiadenghi defaul= dongiapheduyet
       			+"CEI.CONSTRUCTION_ID  constructionId,"
       			+"CAW.EVALUATE_QUANTITY evaluateQuantity ,"//khoiluongpheduyet
       			+"CAW.EVALUATE_UNIT_PRICE evaluateUnitPrice," //dongiapheduyet
       			+"CAW.EVALUATE_COMMENTS evaluateComments,"
       			+"CAW.INSTANCE_DRAW_CODE instanceDrawCode,"
       			+"CAW.COMMENTS comments,"
       			+"CAW.EXPLAIN explain"
       			+" FROM ESTIMATES_WORK_ITEMS EWI "
       			+" JOIN CONSTR_ESTIMATE_INFO CEI ON EWI.CONSTR_ESTIMATE_INFO_ID = CEI.CONSTR_ESTIMATE_INFO_ID "
       			+" JOIN CONSTR_CONSTRUCTIONS CC ON CEI.CONSTRUCTION_ID = CC.CONSTRUCT_ID "
       			+" JOIN CONSTRUCTION_ACCEPTANCE CA ON CA.CONSTRUCT_ID=CC.CONSTRUCT_ID"
       			+" JOIN CONSTR_ACCEPT_WORK_LIST CAW ON "
       			+" (EWI.ESTIMATES_WORK_ITEM_ID=CAW.ESTIMATES_WORK_ITEM_ID AND CAW.CONSTRUCTION_ACCEPTANCE_ID=CA.CONSTRUCTION_ACCEPTANCE_ID)"
       			+" WHERE ewi.PROGRESS_TYPE = 3 AND ewi.TYPE = 1"
       			+" AND CA.STATUS_CA=2 AND (CA.IS_ACTIVE=1 OR CA.IS_ACTIVE IS NULL)  AND CEI.CONSTRUCTION_ID = :constructId" ;

      		StringBuilder sqlBuilder = new StringBuilder(sql);
      		SQLQuery query = getSession().createSQLQuery(sqlBuilder.toString());			
      		query.addScalar("constructionId", LongType.INSTANCE);
//      	 query.addScalar("constrEstimateInfoId", LongType.INSTANCE);
      		 query.addScalar("estimatesWorkItemId", LongType.INSTANCE);
//      	 query.addScalar("type", LongType.INSTANCE);
//      	 query.addScalar("progressType", LongType.INSTANCE);
      		query.addScalar("workItemCode", StringType.INSTANCE);
      		 query.addScalar("workItemName", StringType.INSTANCE);
      		 query.addScalar("unitPrice", DoubleType.INSTANCE);
      		 query.addScalar("unit", StringType.INSTANCE);
      		 query.addScalar("workAmount", new DoubleType());
      		 query.addScalar("executeQuantity", new DoubleType());
      		 query.addScalar("settleUnitPrice", new DoubleType());	
      		 query.addScalar("evaluateQuantity", new DoubleType());
      		 query.addScalar("evaluateUnitPrice", new DoubleType());	
      		 query.addScalar("evaluateComments", StringType.INSTANCE);
      		 query.addScalar("instanceDrawCode", StringType.INSTANCE);
      		 query.addScalar("comments", StringType.INSTANCE);
      		 query.addScalar("explain", StringType.INSTANCE);
      		 query.setParameter("constructId", obj.getConstructionId());

		query.setResultTransformer(Transformers.aliasToBean(EstimatesWorkItemsDTO.class));
		
		return query.list();
	}

    @SuppressWarnings("unchecked")
	public List<EstimatesWorkItemsDTO> getAllEstimatesWorkOutsideContract(EstimatesWorkItemsDTO obj) {
    	String sql = "SELECT "
       			+"EWI.ESTIMATES_WORK_ITEM_ID estimatesWorkItemId, "
       			+"EWI.WORK_ITEM_NAME  workItemName," //hangmuccongviec
       			+"EWI.WORK_ITEM_CODE workItemCode,"
       			+"EWI.UNIT unit," //donvitinh
       			+"EWI.WORK_AMOUNT  workAmount, " //KHOILUONGGIAOKHOAN
       			+"EWI.UNIT_PRICE  unitPrice,  " //DONGIAGIAOKHOAN
       			+"CAW.EXECUTE_QUANTITY executeQuantity ,"//khoiluongdenghi defaul= khoiluongpheduyet
       			+"CAW.SETTLE_UNIT_PRICE settleUnitPrice," //dongiadenghi defaul= dongiapheduyet
       			+"CEI.CONSTRUCTION_ID  constructionId,"
       			+"CAW.EVALUATE_QUANTITY evaluateQuantity ,"//khoiluongpheduyet
       			+"CAW.EVALUATE_UNIT_PRICE evaluateUnitPrice," //dongiapheduyet
       			+"CAW.EVALUATE_COMMENTS evaluateComments,"
       			+"CAW.INSTANCE_DRAW_CODE instanceDrawCode,"
       			+"CAW.COMMENTS comments,"
       			+"CAW.EXPLAIN explain"
       			+" FROM ESTIMATES_WORK_ITEMS EWI "
       			+" JOIN CONSTR_ESTIMATE_INFO CEI ON EWI.CONSTR_ESTIMATE_INFO_ID = CEI.CONSTR_ESTIMATE_INFO_ID "
       			+" JOIN CONSTR_CONSTRUCTIONS CC ON CEI.CONSTRUCTION_ID = CC.CONSTRUCT_ID "
       			+" JOIN CONSTRUCTION_ACCEPTANCE CA ON CA.CONSTRUCT_ID=CC.CONSTRUCT_ID"
       			+" JOIN CONSTR_ACCEPT_WORK_LIST CAW ON "
       			+" (EWI.ESTIMATES_WORK_ITEM_ID=CAW.ESTIMATES_WORK_ITEM_ID AND CAW.CONSTRUCTION_ACCEPTANCE_ID=CA.CONSTRUCTION_ACCEPTANCE_ID)"
       			+" WHERE ewi.PROGRESS_TYPE = 3 AND ewi.TYPE = 2"
       			+" AND CA.STATUS_CA=2 AND (CA.IS_ACTIVE=1 OR CA.IS_ACTIVE IS NULL)  AND CEI.CONSTRUCTION_ID = :cnstructionId";

      		StringBuilder sqlBuilder = new StringBuilder(sql);
      		SQLQuery query = getSession().createSQLQuery(sqlBuilder.toString());			
      		query.addScalar("constructionId", LongType.INSTANCE);
//      	 query.addScalar("constrEstimateInfoId", LongType.INSTANCE);
      		 query.addScalar("estimatesWorkItemId", LongType.INSTANCE);
//      	 query.addScalar("type", LongType.INSTANCE);
//      	 query.addScalar("progressType", LongType.INSTANCE);
      		query.addScalar("workItemCode", StringType.INSTANCE);
      		 query.addScalar("workItemName", StringType.INSTANCE);
      		 query.addScalar("unitPrice", DoubleType.INSTANCE);
      		 query.addScalar("unit", StringType.INSTANCE);
      		 query.addScalar("workAmount", new DoubleType());
      		 query.addScalar("executeQuantity", new DoubleType());
      		 query.addScalar("settleUnitPrice", new DoubleType());	
      		 query.addScalar("evaluateQuantity", new DoubleType());
      		 query.addScalar("evaluateUnitPrice", new DoubleType());	
      		 query.addScalar("evaluateComments", StringType.INSTANCE);
      		 query.addScalar("instanceDrawCode", StringType.INSTANCE);
      		 query.addScalar("comments", StringType.INSTANCE);
      		 query.addScalar("explain", StringType.INSTANCE);
      		 query.setParameter("cnstructionId", obj.getConstructionId());

  		query.setResultTransformer(Transformers.aliasToBean(EstimatesWorkItemsDTO.class));
		
		return query.list();
	}
    
 // minhpvn
 	public List<EstimatesWorkItemsDTO> getBieu4(EstimatesWorkItemsDTO obj) {
 		StringBuilder sqlbuilder = new StringBuilder(" SELECT CEI.CONSTRUCTION_ID constructionId,"
 						+ " EWI.WORK_ITEM_CODE workItemCode, "				
 						+ " EWI.WORK_ITEM_NAME workItemName,"
 						+ " EWI.UNIT unit, "
 						+ " EWI.WORK_AMOUNT workAmount," 						// khối lượng theo hợp đồng
 						+ "	EWI.UNIT_PRICE unitPrice,"   						// đơn giá theo hợp đồng
 						+ " CAWL.EVALUATE_QUANTITY evaluateQuantity," 			// khối lượng theo quyết toán
 						+ " CAWL.EVALUATE_UNIT_PRICE evaluateUnitPrice,"		// đơn giá theo quyết toán
 						+ " CAWL.EVALUATE_COMMENTS evaluateComments, "          // dien giai
 						+ " CAWL.INSTANCE_DRAW_CODE instanceDrawCode, "			// ban ve the hien
 						+ " CEI.CONSTR_ESTIMATE_INFO_ID constrEstimateInfo,"
 						+ " EWI.ESTIMATES_WORK_ITEM_ID estimateWorkItemId "
 						+ " FROM CONSTR_ESTIMATE_INFO CEI "
 						+ " INNER JOIN ESTIMATES_WORK_ITEMS EWI ON CEI.CONSTR_ESTIMATE_INFO_ID = EWI.CONSTR_ESTIMATE_INFO_ID "
 						+ " INNER JOIN CONSTR_ACCEPT_WORK_LIST CAWL ON EWI.ESTIMATES_WORK_ITEM_ID = CAWL.ESTIMATES_WORK_ITEM_ID "
 						+ " INNER JOIN CONSTRUCTION_ACCEPTANCE CA ON CA.CONSTRUCTION_ACCEPTANCE_ID = CAWL.CONSTRUCTION_ACCEPTANCE_ID "
 						+ " WHERE CA.STATUS_CA = 2 AND (CA.IS_ACTIVE  is null OR CA.IS_ACTIVE =1 ) AND CEI.CONSTRUCTION_ID = :cnstructionId");
 		SQLQuery query = getSession().createSQLQuery(sqlbuilder.toString());	
 		//query.addScalar("constructionId", LongType.INSTANCE);
 		query.addScalar("workItemCode", StringType.INSTANCE);
 		query.addScalar("workItemName", StringType.INSTANCE);
 		query.addScalar("unit", StringType.INSTANCE);
 		query.addScalar("workAmount", new DoubleType());
 		query.addScalar("unitPrice", DoubleType.INSTANCE);
 		query.addScalar("evaluateQuantity", new DoubleType());
 		query.addScalar("evaluateUnitPrice", new DoubleType());
 		query.addScalar("evaluateComments", StringType.INSTANCE);
 		query.addScalar("instanceDrawCode", StringType.INSTANCE);
 		query.setParameter("cnstructionId", obj.getConstructionId());
 		query.setResultTransformer(Transformers.aliasToBean(EstimatesWorkItemsDTO.class));

 		return query.list();
 	}
 	
 	public List<EstimatesWorkItemsDTO> getbieu2(Long id) {
 		StringBuilder sqlbuilder = new StringBuilder("SELECT "
 				+ "CEI.CONSTRUCTION_ID constructionId,"
 				+ "EWI.ESTIMATES_WORK_ITEM_ID estimatesWorkItemId,"
 				+ "EWI.WORK_ITEM_CODE workItemCode,"
 				+ "EWI.WORK_ITEM_NAME workItemName,"
 				+ "EWI.UNIT unit,"
 				+ "EWI.WORK_AMOUNT workAmount,"
 				+ "EWI.UNIT_PRICE unitPrice,"
 				+ "EWI.WORK_AMOUNT*EWI.UNIT_PRICE thanhtien1,"
 				+ "CAWL.EVALUATE_QUANTITY evaluateQuantity,"
 				+ "CAWL.EVALUATE_UNIT_PRICE evaluateUnitPrice,"
 				+ "CAWL.EVALUATE_QUANTITY * CAWL.EVALUATE_UNIT_PRICE thanhtien2,"
 				+ "EWI.TYPE type "
 				+ " FROM CONSTR_ESTIMATE_INFO CEI "
 				+ " INNER JOIN ESTIMATES_WORK_ITEMS EWI ON CEI.CONSTR_ESTIMATE_INFO_ID = EWI.CONSTR_ESTIMATE_INFO_ID "
 				+ " JOIN CONSTR_CONSTRUCTIONS CC ON CEI.CONSTRUCTION_ID = CC.CONSTRUCT_ID "
 				+ " JOIN CONSTRUCTION_ACCEPTANCE CA ON CA.CONSTRUCT_ID= CC.CONSTRUCT_ID "
 				+ " INNER JOIN CONSTR_ACCEPT_WORK_LIST CAWL ON "
 				+ " (EWI.ESTIMATES_WORK_ITEM_ID = CAWL.ESTIMATES_WORK_ITEM_ID AND CAWL.CONSTRUCTION_ACCEPTANCE_ID = CA.CONSTRUCTION_ACCEPTANCE_ID) "
 				+ " WHERE EWI.PROGRESS_TYPE = 3 AND CA.STATUS_CA=2 AND (CA.IS_ACTIVE=1 OR CA.IS_ACTIVE IS NULL) "
 				+ " AND EWI.WORK_AMOUNT IS NOT NULL AND CAWL.EVALUATE_QUANTITY IS NOT NULL AND CEI.CONSTRUCTION_ID = :id"  + " ORDER BY EWI.TYPE, EWI.WORK_ITEM_CODE ASC");
 		SQLQuery query = getSession().createSQLQuery(sqlbuilder.toString());	
 		query.addScalar("constructionId", LongType.INSTANCE);
 		query.addScalar("estimatesWorkItemId", LongType.INSTANCE);
 		query.addScalar("workItemCode", StringType.INSTANCE);
 		query.addScalar("workItemName", StringType.INSTANCE);
 		query.addScalar("unit", StringType.INSTANCE);
 		query.addScalar("workAmount", new DoubleType());
 		query.addScalar("unitPrice", DoubleType.INSTANCE);
 		query.addScalar("evaluateQuantity", new DoubleType());
 		query.addScalar("evaluateUnitPrice", new DoubleType());
 		query.addScalar("thanhtien1", new DoubleType());
 		query.addScalar("thanhtien2", new DoubleType());
 		query.addScalar("type", LongType.INSTANCE);
 		query.setParameter("id", id);
 		query.setResultTransformer(Transformers.aliasToBean(EstimatesWorkItemsDTO.class));

 		return query.list();
 	}
 	// dong end

	public List<EstimatesWorkItemsDTO> getWorkItemDone(EstimatesWorkItemsDTO obj) {
			String sql = "select ewi.ESTIMATES_WORK_ITEM_ID estimatesWorkItemId from ESTIMATES_WORK_ITEMS ewi "
					+ "JOIN CONSTR_ESTIMATE_INFO cei ON ewi. CONSTR_ESTIMATE_INFO_ID = cei. CONSTR_ESTIMATE_INFO_ID "
					+ "JOIN CONSTR_CONSTRUCTIONS cc ON cei.CONSTRUCTION_ID = cc.CONSTRUCT_ID "
					+ "WHERE ewi.PROGRESS_TYPE = 3 AND ewi.STATUS =2 AND cei.CONSTRUCTION_ID = :constructionId";
			
			StringBuilder sqlBuilder = new StringBuilder(sql);
			SQLQuery query = getSession().createSQLQuery(sqlBuilder.toString());
			query.addScalar("estimatesWorkItemId", LongType.INSTANCE);
			query.setParameter("constructionId",  obj.getConstructionId());
			query.setResultTransformer(Transformers.aliasToBean(EstimatesWorkItemsDTO.class));
			return query.list();
	}

	public List<EstimatesWorkItemsDTO> getWorkItemNotDone(EstimatesWorkItemsDTO obj) {
			String sql = "select "
					+ "ewi.ESTIMATES_WORK_ITEM_ID estimatesWorkItemId, "
					+ "ewi.WORK_ITEM_NAME workItemName, "
					+ "ewi.WORK_ITEM_CODE workItemCode, "
					+ "ewi.UNIT unit, "
					+ "ewi.WORK_AMOUNT workAmount "
					+ "from ESTIMATES_WORK_ITEMS ewi "
					+ "JOIN CONSTR_ESTIMATE_INFO cei ON ewi. CONSTR_ESTIMATE_INFO_ID = cei.CONSTR_ESTIMATE_INFO_ID "
					+ "JOIN CONSTR_CONSTRUCTIONS cc ON cei.CONSTRUCTION_ID = cc.CONSTRUCT_ID "
					+ "WHERE ewi.PROGRESS_TYPE = 3 AND ewi.STATUS NOT IN (1,2) AND cei.CONSTRUCTION_ID = :constructionId";
			
			StringBuilder sqlBuilder = new StringBuilder(sql);
			SQLQuery query = getSession().createSQLQuery(sqlBuilder.toString());
			query.addScalar("estimatesWorkItemId", LongType.INSTANCE);
			query.addScalar("workItemName", StringType.INSTANCE);
			query.addScalar("workItemCode", StringType.INSTANCE);
			query.addScalar("unit", StringType.INSTANCE);
			query.addScalar("workAmount", new DoubleType());
			query.setParameter("constructionId",  obj.getConstructionId());
			query.setResultTransformer(Transformers.aliasToBean(EstimatesWorkItemsDTO.class));
			return query.list();
	}

	public Long pauseWorkItem(List<Long> listWorkItem) {
//			String condition = "";
//			for (Long code : listWorkItem) {
//				condition = condition + "" + code + ",";
//			}
//			condition = condition.substring(0, condition.length() - 1);
			StringBuilder sql = new StringBuilder();
			sql.append("update " + " ESTIMATES_WORK_ITEMS set STATUS=2 ");
			sql.append(" where ESTIMATES_WORK_ITEM_ID in (:condition)" );

			SQLQuery query = getSession().createSQLQuery(sql.toString());
			query.setParameterList("condition", listWorkItem);
			return new Long(query.executeUpdate()) ;

	}

	 @SuppressWarnings("unchecked")
	 	public List<EstimatesWorkItemsDTO> getAllEstimatesWorkContract(EstimatesWorkItemsDTO obj) {
	  	String sql = "SELECT "
	  			+"NVL(sum(EWI.WORK_AMOUNT*EWI.UNIT_PRICE),0)   allotmentAmount, "
	  			+"NVL(sum(CAW.EXECUTE_QUANTITY*EWI.UNIT_PRICE),0) advanceAmount ,"
	  			+"NVL(sum(CAW.EVALUATE_QUANTITY*CAW.EVALUATE_UNIT_PRICE),0) approvalAmount,"
	  			+"NVL(sum(CAW.EXECUTE_QUANTITY*EWI.UNIT_PRICE),0) -  NVL(sum(EWI.WORK_AMOUNT*EWI.UNIT_PRICE),0) deviantAdvance,"
	  			+"NVL(sum(CAW.EVALUATE_QUANTITY*CAW.EVALUATE_UNIT_PRICE),0) - NVL(sum(CAW.EXECUTE_QUANTITY*EWI.UNIT_PRICE),0) deviantApproval,"
	  			+"EWI.TYPE type"
	  			+" FROM ESTIMATES_WORK_ITEMS EWI "
	  			+" JOIN CONSTR_ESTIMATE_INFO CEI ON EWI.CONSTR_ESTIMATE_INFO_ID = CEI.CONSTR_ESTIMATE_INFO_ID"
	  			+" JOIN CONSTR_CONSTRUCTIONS CC ON CEI.CONSTRUCTION_ID = CC.CONSTRUCT_ID "
	  			+" JOIN CONSTRUCTION_ACCEPTANCE CA ON CA.CONSTRUCT_ID=CC.CONSTRUCT_ID"
	  			+" JOIN CONSTR_ACCEPT_WORK_LIST CAW ON "
	  			+" (EWI.ESTIMATES_WORK_ITEM_ID=CAW.ESTIMATES_WORK_ITEM_ID AND CAW.CONSTRUCTION_ACCEPTANCE_ID=CA.CONSTRUCTION_ACCEPTANCE_ID)"
	  			+" WHERE ewi.PROGRESS_TYPE = 3"
	  			+" AND CA.STATUS_CA=2 AND (CA.IS_ACTIVE=1 OR CA.IS_ACTIVE IS NULL)  and ewi.type is not null  AND CEI.CONSTRUCTION_ID = :constructionId"
	  			+" GROUP BY EWI.TYPE";

	 		StringBuilder sqlBuilder = new StringBuilder(sql);
	 		SQLQuery query = getSession().createSQLQuery(sqlBuilder.toString());			
	 		query.addScalar("allotmentAmount", new DoubleType());
	 		query.addScalar("type", new LongType());
	 		query.addScalar("advanceAmount", new DoubleType());
	 		query.addScalar("approvalAmount", new DoubleType());
	 		query.addScalar("deviantAdvance", new DoubleType());
	 		query.addScalar("deviantApproval", new DoubleType());
	 		if (obj.getConstructionId()!= null) {
	 			query.setParameter("constructionId",  obj.getConstructionId());
	 		}
	 		query.setResultTransformer(Transformers.aliasToBean(EstimatesWorkItemsDTO.class));
	 		
	 		return query.list();
	 	}
 	
 	// ToanBD
 	 @SuppressWarnings("unchecked")
 	public List<EstimatesWorkItemsDTO> getAllEstimatesWorkOutsideContractForEvaluate(EstimatesWorkItemsDTO obj) {
  	String sql = "SELECT "
  			+"EWI.ESTIMATES_WORK_ITEM_ID estimatesWorkItemId, "
  			+"EWI.WORK_ITEM_NAME  workItemName," //hangmuccongviec
  			+"EWI.WORK_ITEM_CODE workItemCode,"
  			+"EWI.UNIT unit," //donvitinh
  			+"EWI.WORK_AMOUNT  workAmount, " //KHOILUONGGIAOKHOAN
  			+"EWI.UNIT_PRICE  unitPrice,  " //DONGIAGIAOKHOAN
  			+"CAW.EXECUTE_QUANTITY executeQuantity ,"//khoiluongdenghi defaul= khoiluongpheduyet
  			+"CAW.SETTLE_UNIT_PRICE settleUnitPrice," //dongiadenghi defaul= dongiapheduyet
  			+"CEI.CONSTRUCTION_ID  constructionId,"
  			+"CAW.EVALUATE_QUANTITY evaluateQuantity,"//khoiluongpheduyet
   			+"CAW.EVALUATE_UNIT_PRICE evaluateUnitPrice," //dongiapheduyet
   			+"CAW.EVALUATE_COMMENTS evaluateComments,"
   			+"CAW.INSTANCE_DRAW_CODE instanceDrawCode,"
   			+"CAW.COMMENTS comments,"
   			+"CAW.EXPLAIN explain"
  			+" FROM ESTIMATES_WORK_ITEMS EWI "
  			+" JOIN CONSTR_ESTIMATE_INFO CEI ON EWI.CONSTR_ESTIMATE_INFO_ID = CEI.CONSTR_ESTIMATE_INFO_ID"
  			+" JOIN CONSTR_CONSTRUCTIONS CC ON CEI.CONSTRUCTION_ID = CC.CONSTRUCT_ID "
  			+" JOIN CONSTRUCTION_ACCEPTANCE CA ON CA.CONSTRUCT_ID=CC.CONSTRUCT_ID"
  			+" JOIN CONSTR_ACCEPT_WORK_LIST CAW ON "
  			+" (EWI.ESTIMATES_WORK_ITEM_ID=CAW.ESTIMATES_WORK_ITEM_ID AND CAW.CONSTRUCTION_ACCEPTANCE_ID=CA.CONSTRUCTION_ACCEPTANCE_ID)"
  			+" WHERE ewi.PROGRESS_TYPE = 3 AND EWI.TYPE = 2"
  			+" AND CA.STATUS_CA=2 AND (CA.IS_ACTIVE=1 OR CA.IS_ACTIVE IS NULL)  AND CEI.CONSTRUCTION_ID = :constructionId" ;

 		StringBuilder sqlBuilder = new StringBuilder(sql);
 		SQLQuery query = getSession().createSQLQuery(sqlBuilder.toString());			
 		query.addScalar("constructionId", LongType.INSTANCE);
// 		 query.addScalar("constrEstimateInfoId", LongType.INSTANCE);
 		 query.addScalar("estimatesWorkItemId", LongType.INSTANCE);
// 		 query.addScalar("type", LongType.INSTANCE);
// 		 query.addScalar("progressType", LongType.INSTANCE);
 		query.addScalar("workItemCode", StringType.INSTANCE);
 		 query.addScalar("workItemName", StringType.INSTANCE);
 		 query.addScalar("unitPrice", DoubleType.INSTANCE);
 		 query.addScalar("unit", StringType.INSTANCE);
 		 query.addScalar("workAmount", new DoubleType());
 		 query.addScalar("executeQuantity", new DoubleType());
 		 query.addScalar("settleUnitPrice", new DoubleType());			
 		 query.addScalar("evaluateQuantity", new DoubleType());
  		 query.addScalar("evaluateUnitPrice", new DoubleType());
  		 query.addScalar("evaluateComments", StringType.INSTANCE);
  		 query.addScalar("instanceDrawCode", StringType.INSTANCE);
  		 query.addScalar("comments", StringType.INSTANCE);
  		 query.addScalar("explain", StringType.INSTANCE);
 		if (obj.getConstructionId()!= null) {
 			query.setParameter("constructionId",  obj.getConstructionId());
 		}
 		query.setResultTransformer(Transformers.aliasToBean(EstimatesWorkItemsDTO.class));
 		
 		return query.list();
 	}
 	 
 	 
 	 @SuppressWarnings("unchecked")
  	public List<EstimatesWorkItemsDTO> getAllEstimatesWorkInsideContractForEvaluate(EstimatesWorkItemsDTO obj) {
   	String sql = "SELECT "
   			+"EWI.ESTIMATES_WORK_ITEM_ID estimatesWorkItemId, "
   			+"EWI.WORK_ITEM_NAME  workItemName," //hangmuccongviec
   			+"EWI.WORK_ITEM_CODE workItemCode,"
   			+"EWI.UNIT unit," //donvitinh
   			+"EWI.WORK_AMOUNT  workAmount, " //KHOILUONGGIAOKHOAN
   			+"EWI.UNIT_PRICE  unitPrice,  " //DONGIAGIAOKHOAN
   			+"CAW.EXECUTE_QUANTITY executeQuantity ,"//khoiluongdenghi defaul= khoiluongpheduyet
   			+"CAW.SETTLE_UNIT_PRICE settleUnitPrice," //dongiadenghi defaul= dongiapheduyet
   			+"CEI.CONSTRUCTION_ID  constructionId,"
   			+"CAW.EVALUATE_QUANTITY evaluateQuantity ,"//khoiluongpheduyet
   			+"CAW.EVALUATE_UNIT_PRICE evaluateUnitPrice," //dongiapheduyet
   			+"CAW.EVALUATE_COMMENTS evaluateComments,"
   			+"CAW.INSTANCE_DRAW_CODE instanceDrawCode,"
   			+"CAW.COMMENTS comments,"
   			+"CAW.EXPLAIN explain"
   			+" FROM ESTIMATES_WORK_ITEMS EWI "
   			+" JOIN CONSTR_ESTIMATE_INFO CEI ON EWI.CONSTR_ESTIMATE_INFO_ID = CEI.CONSTR_ESTIMATE_INFO_ID "
   			+" JOIN CONSTR_CONSTRUCTIONS CC ON CEI.CONSTRUCTION_ID = CC.CONSTRUCT_ID "
   			+" JOIN CONSTRUCTION_ACCEPTANCE CA ON CA.CONSTRUCT_ID=CC.CONSTRUCT_ID"
   			+" JOIN CONSTR_ACCEPT_WORK_LIST CAW ON "
   			+" (EWI.ESTIMATES_WORK_ITEM_ID=CAW.ESTIMATES_WORK_ITEM_ID AND CAW.CONSTRUCTION_ACCEPTANCE_ID=CA.CONSTRUCTION_ACCEPTANCE_ID)"
   			+" WHERE ewi.PROGRESS_TYPE = 3 AND ewi.TYPE = 1"
   			+" AND CA.STATUS_CA=2 AND (CA.IS_ACTIVE=1 OR CA.IS_ACTIVE IS NULL)  AND CEI.CONSTRUCTION_ID = :constructionId" ;

  		StringBuilder sqlBuilder = new StringBuilder(sql);
  		SQLQuery query = getSession().createSQLQuery(sqlBuilder.toString());			
  		query.addScalar("constructionId", LongType.INSTANCE);
//  	 query.addScalar("constrEstimateInfoId", LongType.INSTANCE);
  		 query.addScalar("estimatesWorkItemId", LongType.INSTANCE);
//  	 query.addScalar("type", LongType.INSTANCE);
//  	 query.addScalar("progressType", LongType.INSTANCE);
  		query.addScalar("workItemCode", StringType.INSTANCE);
  		 query.addScalar("workItemName", StringType.INSTANCE);
  		 query.addScalar("unitPrice", DoubleType.INSTANCE);
  		 query.addScalar("unit", StringType.INSTANCE);
  		 query.addScalar("workAmount", new DoubleType());
  		 query.addScalar("executeQuantity", new DoubleType());
  		 query.addScalar("settleUnitPrice", new DoubleType());	
  		 query.addScalar("evaluateQuantity", new DoubleType());
  		 query.addScalar("evaluateUnitPrice", new DoubleType());	
  		 query.addScalar("evaluateComments", StringType.INSTANCE);
  		 query.addScalar("instanceDrawCode", StringType.INSTANCE);
  		 query.addScalar("comments", StringType.INSTANCE);
  		 query.addScalar("explain", StringType.INSTANCE);

  		if (obj.getConstructionId()!= null) {
  			query.setParameter("constructionId",  obj.getConstructionId());
  		}
  		query.setResultTransformer(Transformers.aliasToBean(EstimatesWorkItemsDTO.class));
  		
  		return query.list();
  	}
 	
 	 
     @SuppressWarnings("unchecked")
     public EstimatesWorkItemsDTO exportEstimateWorkItem(EstimatesWorkItemsDTO dto) {
//    	  System.out.println("===11==============");
      StringBuffer sql = new StringBuffer("SELECT "
    		  + "vc.STATION_CODE 					station_code, "
        + "vc.CONSTRT_CODE 					constrtCode,"
        + "vc.CONTRACT_CODE 				contractCode,"
        + "vc.CONTRACT_NAME 				contractName,"
    	+ "vc.CONSTRUCTOR_NAME  			constructorName,"
        + "vc.CONSTRT_NAME 					constrtName, "
        + "vc.CONSTRT_ADDRESS 				constrtAddress, "
        + "bma.STATUS_CA 					statusCa, "
                        
        + "(SELECT FULL_NAME FROM CAT_EMPLOYEE WHERE ID =" 
        + "(SELECT A_DIRECTOR_ID FROM AB_COMPLEMENT_WORK_DESCRIBE WHERE CONSTRUCT_ID = :constructId )) aDirectorFullName, "
    	
        + "(SELECT FULL_NAME FROM CAT_EMPLOYEE WHERE ID =" 
        + "(SELECT A_IN_CHARGE_MONITOR_ID FROM AB_COMPLEMENT_WORK_DESCRIBE WHERE CONSTRUCT_ID = :constructId)) aInchargeMonitorFullName, "
        
        + "(SELECT FULL_NAME FROM CAT_EMPLOYEE WHERE ID =" 
        + "(SELECT B_DIRECTOR_ID FROM AB_COMPLEMENT_WORK_DESCRIBE WHERE CONSTRUCT_ID = :constructId)) bDirectorFullName, "
        
        + "(SELECT FULL_NAME FROM CAT_EMPLOYEE WHERE ID =" 
        + "(SELECT B_IN_CHARGE_CONSTRUCT_ID FROM AB_COMPLEMENT_WORK_DESCRIBE WHERE CONSTRUCT_ID = :constructId)) bInchargeConstructFullName, "
       

		+ "(SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :typesign AND PARENT_ID = "
		+ "(SELECT A_DIRECTOR_ID FROM AB_COMPLEMENT_WORK_DESCRIBE WHERE AB_COMPLEMENT_WORK_DESCRIBE_ID = :abComplementWorkDescribeId)) aDirectoridPath, "

		+ "(SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :typesign AND PARENT_ID = "
		+ "(SELECT B_DIRECTOR_ID FROM AB_COMPLEMENT_WORK_DESCRIBE WHERE AB_COMPLEMENT_WORK_DESCRIBE_ID = :abComplementWorkDescribeId)) bDirectoridPath, "

		+ "(SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :typesign AND PARENT_ID = "
		+ "(SELECT A_IN_CHARGE_MONITOR_ID FROM AB_COMPLEMENT_WORK_DESCRIBE WHERE AB_COMPLEMENT_WORK_DESCRIBE_ID = :abComplementWorkDescribeId)) aInchargeMonitoridPath, "

		+ "(SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :typesign AND PARENT_ID = "
		+ "(SELECT B_IN_CHARGE_CONSTRUCT_ID FROM AB_COMPLEMENT_WORK_DESCRIBE WHERE AB_COMPLEMENT_WORK_DESCRIBE_ID = :abComplementWorkDescribeId)) bInchargeConstructidPath "


        + "FROM  AB_COMPLEMENT_WORK_DESCRIBE bma "
        + "INNER JOIN V_CONSTRUCTION_HCQT vc "
        + "ON vc.CONSTRUCT_ID = bma.CONSTRUCT_ID "
        + "WHERE bma.IS_ACTIVE = 1 AND bma.CONSTRUCT_ID = :constructId");

      SQLQuery query = getSession().createSQLQuery(sql.toString());
      		//chan ky
      
      		query.setParameter("constructId", dto.getConstructId());
      		query.setParameter("abComplementWorkDescribeId", dto.getAbComplementWorkDescribeId());
      		query.setParameter("typesign", partnerAttachType);
      		query.addScalar("aDirectoridPath", StringType.INSTANCE);
      		query.addScalar("bDirectoridPath", StringType.INSTANCE);
      		query.addScalar("aInchargeMonitoridPath", StringType.INSTANCE);
      		query.addScalar("bInchargeConstructidPath", StringType.INSTANCE);
      		query.addScalar("statusCa", LongType.INSTANCE);
      
      
        	query.addScalar("station_code", StringType.INSTANCE);
        	query.addScalar("constrtCode", StringType.INSTANCE);
        	query.addScalar("contractCode", StringType.INSTANCE);
        	query.addScalar("contractName", StringType.INSTANCE);
        	query.addScalar("constructorName", StringType.INSTANCE);
        	query.addScalar("constrtName", StringType.INSTANCE);
        	query.addScalar("constrtAddress", StringType.INSTANCE);
      
    		query.addScalar("aDirectorFullName", StringType.INSTANCE);
    		query.addScalar("aInchargeMonitorFullName", StringType.INSTANCE);
    		query.addScalar("bDirectorFullName", StringType.INSTANCE);
    		query.addScalar("bInchargeConstructFullName", StringType.INSTANCE);
           
      query.setResultTransformer(Transformers.aliasToBean(EstimatesWorkItemsDTO.class));
      
      List<EstimatesWorkItemsDTO> list = query.list();
      System.out.println("=======11==========="+list.size());
      
       return (EstimatesWorkItemsDTO) (list.size()>0?list.get(0):null);
       
 }
     
     

     
   //ngoccx
     public List<EstimatesWorkItemsDTO> getbieu5(Long id) {
  		StringBuilder sqlbuilder = new StringBuilder("select x.*, y.* from (select a.ESTIMATES_WORK_ITEM_ID estimatesWorkItemId, "
  				+ "b.WORK_ITEM_NAME workItemName, "
  				+ "b.WORK_ITEM_CODE workItemCode, " 
  				+ "a.COST_INGREDIENT_CODE costIngredientCode, " 
  				+ "a.COST_INGREDIENT_NAME costIngredientName, " 
  				+ "a.UNIT unit, "
  				+ "a.TYPE type2, "
  				+ "a.NORM_INDEX normIndex, "
  				+ "a.UNIT_PRICE unitPrice , "
  				+ "a.TOTAL_MONEY totalMoney, "
  				+ "a.PROGRESS_TYPE progressType, "
  				+ "a.COST_INGREDIENT_CODE  "
  				+ "from ESTIMATES_DETAIL_ANALYST a JOIN ESTIMATES_WORK_ITEMS b ON a.ESTIMATES_WORK_ITEM_ID = b.ESTIMATES_WORK_ITEM_ID "
  				+ "JOIN CONSTR_ESTIMATE_INFO CEI  ON b.CONSTR_ESTIMATE_INFO_ID = CEI.CONSTR_ESTIMATE_INFO_ID AND CEI.CONSTRUCTION_ID = :id"
  				
  				+ " where a.PROGRESS_TYPE = 2 AND a.COST_INGREDIENT_CODE NOT  IN  ('NC' ,'M','CONG','HSR','TT','T','C','TL','G','GTGT','Gxdcpt','Gxd','VL','Gxdnt','CONGM','HSRM','CONGVL','HSRNC')) x "
  				+ "left join (select a.ESTIMATES_WORK_ITEM_ID estimatesWorkItemId, "
  				+ "b.WORK_ITEM_NAME workItemName4, "
  				+ "b.WORK_ITEM_CODE workItemCode4, " 
  				+ "a.COST_INGREDIENT_NAME costIngredientName4, " 
  				+ "a.UNIT unit4, "
  				+ "a.TYPE type4, "
  				+ "a.NORM_INDEX normIndex4, "
  				+ "a.UNIT_PRICE unitPrice4 , "
  				+ "a.TOTAL_MONEY totalMoney4, " 
  				+ "a.COST_INGREDIENT_CODE  "
  				+ "from ESTIMATES_DETAIL_ANALYST a JOIN ESTIMATES_WORK_ITEMS b ON a.ESTIMATES_WORK_ITEM_ID = b.ESTIMATES_WORK_ITEM_ID "
  				+ "JOIN CONSTR_ESTIMATE_INFO CEI  ON b.CONSTR_ESTIMATE_INFO_ID = CEI.CONSTR_ESTIMATE_INFO_ID AND CEI.CONSTRUCTION_ID = :id"
  				+ " where a.PROGRESS_TYPE = 4 AND a.COST_INGREDIENT_CODE NOT  IN  ('NC' ,'M','CONG','HSR','TT','T','C','TL','G','GTGT','Gxdcpt','Gxd','VL','Gxdnt','CONGM','HSRM','CONGVL','HSRNC')) "
  				+ "y on (x.estimatesWorkItemId = y.estimatesWorkItemId AND x.TYPE2 = y.TYPE4 AND x.COST_INGREDIENT_CODE = y.COST_INGREDIENT_CODE) ORDER BY x.estimatesWorkItemId ASC");
  		SQLQuery query = getSession().createSQLQuery(sqlbuilder.toString());	
  		//query.addScalar("constructionId", LongType.INSTANCE);
  		query.setParameter("id", id);
  		query.addScalar("estimatesWorkItemId", LongType.INSTANCE);
  		query.addScalar("workItemCode", StringType.INSTANCE);
  		query.addScalar("workItemName", StringType.INSTANCE);
  		query.addScalar("unit", StringType.INSTANCE);
  		query.addScalar("type2", LongType.INSTANCE); 
  		query.addScalar("unitPrice", DoubleType.INSTANCE);
  		query.addScalar("normIndex", DoubleType.INSTANCE);
  		query.addScalar("totalMoney", DoubleType.INSTANCE); 
  		query.addScalar("costIngredientCode", StringType.INSTANCE);
  		query.addScalar("costIngredientName", StringType.INSTANCE);
  		//Quyết quoán
  		query.addScalar("workItemCode4", StringType.INSTANCE);
  		query.addScalar("workItemName4", StringType.INSTANCE);
  		query.addScalar("unit4", StringType.INSTANCE);  
  		query.addScalar("type4", LongType.INSTANCE);
  		query.addScalar("unitPrice4", DoubleType.INSTANCE);
  		query.addScalar("normIndex4", DoubleType.INSTANCE);
  		query.addScalar("totalMoney4", DoubleType.INSTANCE);  		
  		query.addScalar("costIngredientName4", StringType.INSTANCE);
  		query.setResultTransformer(Transformers.aliasToBean(EstimatesWorkItemsDTO.class));

  		return query.list();
  	}
  	// ngoccx

	public List<AbDetailPriceNewDTO> getExportBieu5(Long constructId) {
		StringBuilder sqlbuilder = new StringBuilder("select x.*, y.* from (select a.ESTIMATES_WORK_ITEM_ID estimatesWorkItemId, "
  				+ "b.WORK_ITEM_NAME workItemName, "
  				+ "b.WORK_ITEM_CODE workItemCode, " 
  				+ "a.COST_INGREDIENT_CODE costIngredientCode, " 
  				+ "a.COST_INGREDIENT_NAME costIngredientName, " 
  				+ "a.UNIT unit, "
  				+ "a.TYPE type2, "
  				+ "a.NORM_INDEX normIndex, "
  				+ "a.UNIT_PRICE unitPrice , "
  				+ "a.TOTAL_MONEY totalMoney, "
  				+ "a.PROGRESS_TYPE progressType, "
  				+ "a.COST_INGREDIENT_CODE  "
  				+ "from ESTIMATES_DETAIL_ANALYST a JOIN ESTIMATES_WORK_ITEMS b ON a.ESTIMATES_WORK_ITEM_ID = b.ESTIMATES_WORK_ITEM_ID "
  				+ "JOIN CONSTR_ESTIMATE_INFO CEI  ON b.CONSTR_ESTIMATE_INFO_ID = CEI.CONSTR_ESTIMATE_INFO_ID AND CEI.CONSTRUCTION_ID = :constructId"
  				
  				+ " where a.PROGRESS_TYPE = 2 order by a.EST_DETAIL_ANALYST_ID) x "
  				+ "left join (select a.ESTIMATES_WORK_ITEM_ID estimatesWorkItemId, "
  				+ "b.WORK_ITEM_NAME workItemName4, "
  				+ "b.WORK_ITEM_CODE workItemCode4, " 
  				+ "a.COST_INGREDIENT_NAME costIngredientName4, " 
  				+ "a.UNIT unit4, "
  				+ "a.TYPE type4, "
  				+ "a.NORM_INDEX normIndex4, "
  				+ "a.UNIT_PRICE unitPrice4 , "
  				+ "a.TOTAL_MONEY totalMoney4, " 
  				+ "a.COST_INGREDIENT_CODE  "
  				+ "from ESTIMATES_DETAIL_ANALYST a JOIN ESTIMATES_WORK_ITEMS b ON a.ESTIMATES_WORK_ITEM_ID = b.ESTIMATES_WORK_ITEM_ID "
  				+ "JOIN CONSTR_ESTIMATE_INFO CEI  ON b.CONSTR_ESTIMATE_INFO_ID = CEI.CONSTR_ESTIMATE_INFO_ID AND CEI.CONSTRUCTION_ID = :constructId"
  				+ " where a.PROGRESS_TYPE = 4 order by a.EST_DETAIL_ANALYST_ID) "
  				+ "y on (x.estimatesWorkItemId = y.estimatesWorkItemId AND x.TYPE2 = y.TYPE4 AND x.COST_INGREDIENT_CODE = y.COST_INGREDIENT_CODE) ORDER BY x.estimatesWorkItemId ASC");
		
  		SQLQuery query = getSession().createSQLQuery(sqlbuilder.toString());	
  		query.setParameter("constructId", constructId);
  		//query.addScalar("constructionId", LongType.INSTANCE);
  		query.addScalar("estimatesWorkItemId", LongType.INSTANCE);
  		query.addScalar("workItemCode", StringType.INSTANCE);
  		query.addScalar("workItemName", StringType.INSTANCE);
  		query.addScalar("unit", StringType.INSTANCE);
  		query.addScalar("type2", LongType.INSTANCE); 
  		query.addScalar("unitPrice", DoubleType.INSTANCE);
  		query.addScalar("normIndex", DoubleType.INSTANCE);
  		query.addScalar("totalMoney", DoubleType.INSTANCE); 
  		query.addScalar("costIngredientCode", StringType.INSTANCE);
  		query.addScalar("costIngredientName", StringType.INSTANCE);
  		//Quyết toán
  		query.addScalar("workItemCode4", StringType.INSTANCE);
  		query.addScalar("workItemName4", StringType.INSTANCE);
  		query.addScalar("unit4", StringType.INSTANCE);  
  		query.addScalar("type4", LongType.INSTANCE);
  		query.addScalar("unitPrice4", DoubleType.INSTANCE);
  		query.addScalar("normIndex4", DoubleType.INSTANCE);
  		query.addScalar("totalMoney4", DoubleType.INSTANCE);  		
  		query.addScalar("costIngredientName4", StringType.INSTANCE);
  		query.setResultTransformer(Transformers.aliasToBean(EstimatesWorkItemsDTO.class));
  		
  		return query.list();
	}
	
	 //dinhcongmanh
    public List<EstimatesWorkItemsDTO> exPortfull(Long id,String code) {
  		StringBuilder sqlbuilder = new StringBuilder("select b.ESTIMATES_WORK_ITEM_ID estimatesWorkItemId, "
  				+ "b.ESTIMATES_ITEM_CHILD_ID estimatesItemChildId,"
  				+ "b.WORK_ITEM_NAME workItemName, "
  				+ "b.WORK_ITEM_CODE workItemCode, "
  				+ "caw.EVALUATE_UNIT_PRICE evaluateUnitPrice,"
  				+ " caw.EVALUATE_QUANTITY evaluateQuantity," 
  				+ "a.COST_INGREDIENT_NAME costIngredientName, " 
  				+ "a.UNIT unit4,"
  				+ "b.UNIT  unit,"
  				+ "b.TYPE type, "
  				+ "a.NORM_INDEX normIndexCT, "
  				+ "a.UNIT_PRICE unitPrice4 , "
  				+ "a.TOTAL_MONEY totalMoney, " 
  				+ "a.COST_INGREDIENT_CODE  costIngredientCode,"
  				+ "a.TOTAL_MONEY_FORMULA totalMoneyFormula,"
  				+ "a.COEFFICIENT coefficient,"
  				+ " b.WORK_AMOUNT workAmount,"
  				+" b.UNIT_PRICE unitPrice,"
  				+" caw.EXECUTE_QUANTITY executeQuantity,"
  				+" caw.SETTLE_UNIT_PRICE settleUnitPrice, "
  				+ "  EIC.ITEM_NAME itemName,"
  				+ "  EIC.ITEMS_CODE itemsCode "
  				+ " from ESTIMATES_DETAIL_ANALYST a RIGHT JOIN ESTIMATES_WORK_ITEMS b on (a.ESTIMATES_WORK_ITEM_ID = b.ESTIMATES_WORK_ITEM_ID and a.PROGRESS_TYPE = 4  ) "
  				+ "JOIN CONSTR_ESTIMATE_INFO CEI  ON b.CONSTR_ESTIMATE_INFO_ID = CEI.CONSTR_ESTIMATE_INFO_ID "
  				+ " JOIN  CONSTRUCTION_ACCEPTANCE ca   On ca.CONSTRUCT_ID=CEI.CONSTRUCTION_ID "
  				+ "  JOIN ESTIMATES_ITEMS_CHILD EIC "
  				+ " ON EIC.ESTIMATES_ITEM_CHILD_ID=b.ESTIMATES_ITEM_CHILD_ID"
  				+ " JOIN CONSTR_ACCEPT_WORK_LIST caw  On (  b.ESTIMATES_WORK_ITEM_ID=caw.ESTIMATES_WORK_ITEM_ID   and "
  				+ "	caw.CONSTRUCTION_ACCEPTANCE_ID=ca.CONSTRUCTION_ACCEPTANCE_ID)"
  				+ "  JOIN V_CONSTRUCTION_HCQT VCH ON VCH.CONSTRUCT_ID=ca.CONSTRUCT_ID "
  				+ " where b.PROGRESS_TYPE = 3  "
  				+ " AND VCH.CONTRACT_CODE=:code "
  				+ " AND ca.STATUS_CA=2  AND (ca.IS_ACTIVE=1 or ca.IS_ACTIVE is null)"
  				+ " AND CEI.CONSTRUCTION_ID = :id"
  				+ " order by a.EST_DETAIL_ANALYST_ID ASC,a.ESTIMATES_WORK_ITEM_ID,b.ESTIMATES_ITEM_CHILD_ID ");
  		SQLQuery query = getSession().createSQLQuery(sqlbuilder.toString());	
  		query.addScalar("estimatesItemChildId", LongType.INSTANCE);
  		query.addScalar("estimatesWorkItemId", LongType.INSTANCE);
  		query.addScalar("workItemCode", StringType.INSTANCE);
  		query.addScalar("workItemName", StringType.INSTANCE);
  		query.addScalar("unit4", StringType.INSTANCE);
  		query.addScalar("type", LongType.INSTANCE); 
  		query.addScalar("unitPrice4", DoubleType.INSTANCE);
  		query.addScalar("normIndexCT", DoubleType.INSTANCE);
  		query.addScalar("totalMoney", DoubleType.INSTANCE); 
  		query.addScalar("costIngredientCode", StringType.INSTANCE);
  		query.addScalar("costIngredientName", StringType.INSTANCE);
  		query.addScalar("totalMoneyFormula", StringType.INSTANCE);
  		query.addScalar("unit", StringType.INSTANCE);
  		query.addScalar("coefficient", DoubleType.INSTANCE);
  		query.addScalar("evaluateUnitPrice", DoubleType.INSTANCE);
  		query.addScalar("evaluateQuantity", DoubleType.INSTANCE);
  		
  		query.addScalar("unitPrice", DoubleType.INSTANCE);
  		query.addScalar("workAmount", new DoubleType());
  		query.addScalar("executeQuantity", DoubleType.INSTANCE);
  		query.addScalar("settleUnitPrice", DoubleType.INSTANCE);
  		
  		query.addScalar("itemName", StringType.INSTANCE);
  		query.addScalar("itemsCode", StringType.INSTANCE);
 		query.setParameter("id", id);
 		query.setParameter("code", code);
  		query.setResultTransformer(Transformers.aliasToBean(EstimatesWorkItemsDTO.class));

  		return query.list();
  	}
    
    public List<EstimatesWorkItemsDTO> exPortfullPro(Long id,Long flag) {
  		StringBuilder sqlbuilder = new StringBuilder("select b.ESTIMATES_WORK_ITEM_ID estimatesWorkItemId, "
  				+ "b.ESTIMATES_ITEM_CHILD_ID estimatesItemChildId,"
  				+ "b.WORK_ITEM_NAME workItemName, "
  				+ "b.WORK_ITEM_CODE workItemCode, "
  				+ "caw.EVALUATE_UNIT_PRICE evaluateUnitPrice,"
  				+ " caw.EVALUATE_QUANTITY evaluateQuantity," 
  				+ "a.COST_INGREDIENT_NAME costIngredientName, " 
  				+ "a.UNIT unit, "
  				+ "b.TYPE type, "
  				+ "a.NORM_INDEX normIndexCT, "
  				+ "a.UNIT_PRICE unitPrice4 , "
  				+ "a.TOTAL_MONEY totalMoney, " 
  				+ "a.COST_INGREDIENT_CODE  costIngredientCode,"
  				+ "a.TOTAL_MONEY_FORMULA totalMoneyFormula,"
  				+ "a.COEFFICIENT coefficient,"
  				+ " b.WORK_AMOUNT workAmount,"
  				+" b.UNIT_PRICE unitPrice,"
  				+" caw.EXECUTE_QUANTITY executeQuantity,"
  				+" caw.SETTLE_UNIT_PRICE settleUnitPrice, "
  				+ "  EIC.ITEM_NAME itemName,"
  				+ "  EIC.ITEMS_CODE itemsCode "
  				+ " from ESTIMATES_DETAIL_ANALYST a ");
  				if(flag!=null && flag==0l){
  					sqlbuilder.append("RIGHT ");
  				}
  				sqlbuilder.append("JOIN ESTIMATES_WORK_ITEMS b ON (a.ESTIMATES_WORK_ITEM_ID = b.ESTIMATES_WORK_ITEM_ID AND a.PROGRESS_TYPE = 2 ) "
  				+ "JOIN CONSTR_ESTIMATE_INFO CEI  ON b.CONSTR_ESTIMATE_INFO_ID = CEI.CONSTR_ESTIMATE_INFO_ID "
  				+ " JOIN  CONSTRUCTION_ACCEPTANCE ca   On ca.CONSTRUCT_ID=CEI.CONSTRUCTION_ID "
  				+ "  JOIN ESTIMATES_ITEMS_CHILD EIC "
  				+ " ON EIC.ESTIMATES_ITEM_CHILD_ID=b.ESTIMATES_ITEM_CHILD_ID"
  				+ " JOIN CONSTR_ACCEPT_WORK_LIST caw  On (  b.ESTIMATES_WORK_ITEM_ID=caw.ESTIMATES_WORK_ITEM_ID   and "
  				+ "	caw.CONSTRUCTION_ACCEPTANCE_ID=ca.CONSTRUCTION_ACCEPTANCE_ID)"
  				+ " where b.PROGRESS_TYPE = 3 "
  				+ " AND ca.STATUS_CA=2  AND (ca.IS_ACTIVE=1 or ca.IS_ACTIVE is null)"
  				+ " AND CEI.CONSTRUCTION_ID = :id"
  				+ " order by a.EST_DETAIL_ANALYST_ID ASC,a.ESTIMATES_WORK_ITEM_ID,b.ESTIMATES_ITEM_CHILD_ID ");
  		SQLQuery query = getSession().createSQLQuery(sqlbuilder.toString());	
  		query.addScalar("estimatesItemChildId", LongType.INSTANCE);
  		query.addScalar("estimatesWorkItemId", LongType.INSTANCE);
  		query.addScalar("workItemCode", StringType.INSTANCE);
  		query.addScalar("workItemName", StringType.INSTANCE);
  		query.addScalar("unit", StringType.INSTANCE);
  		query.addScalar("type", LongType.INSTANCE); 
  		query.addScalar("unitPrice4", DoubleType.INSTANCE);
  		query.addScalar("normIndexCT", DoubleType.INSTANCE);
  		query.addScalar("totalMoney", DoubleType.INSTANCE); 
  		query.addScalar("costIngredientCode", StringType.INSTANCE);
  		query.addScalar("costIngredientName", StringType.INSTANCE);
  		query.addScalar("totalMoneyFormula", StringType.INSTANCE);
  		
  		query.addScalar("coefficient", DoubleType.INSTANCE);
  		query.addScalar("evaluateUnitPrice", DoubleType.INSTANCE);
  		query.addScalar("evaluateQuantity", DoubleType.INSTANCE);
  		
  		query.addScalar("unitPrice", DoubleType.INSTANCE);
  		query.addScalar("workAmount", new DoubleType());
  		query.addScalar("executeQuantity", DoubleType.INSTANCE);
  		query.addScalar("settleUnitPrice", DoubleType.INSTANCE);
  		
  		query.addScalar("itemName", StringType.INSTANCE);
  		query.addScalar("itemsCode", StringType.INSTANCE);
  		
  		
 		query.setParameter("id", id);
  		query.setResultTransformer(Transformers.aliasToBean(EstimatesWorkItemsDTO.class));

  		return query.list();
  	}
    
    
    public List<EstimatesWorkItemsDTO> getfullByConstrId(Long id) {
  		StringBuilder sqlbuilder = new StringBuilder("select EWI.ESTIMATES_WORK_ITEM_ID estimatesWorkItemId, "
  				+ "EWI.WORK_ITEM_NAME workItemName, "
  				+ "EWI.WORK_ITEM_CODE workItemCode, "
  				+ "EWI.UNIT unit, "
  				+ "EWI.TYPE type, "
  				+ " EWI.WORK_AMOUNT workAmount"
  				+ " FROM  ESTIMATES_WORK_ITEMS EWI "
  				+ " JOIN CONSTR_ESTIMATE_INFO CEI ON CEI.CONSTR_ESTIMATE_INFO_ID = EWI.CONSTR_ESTIMATE_INFO_ID "
  				+ "WHERE CEI.CONSTRUCTION_ID= :id" );
  		SQLQuery query = getSession().createSQLQuery(sqlbuilder.toString());	
  		query.addScalar("estimatesWorkItemId", LongType.INSTANCE);
  		query.addScalar("workItemCode", StringType.INSTANCE);
  		query.addScalar("workItemName", StringType.INSTANCE);
  		query.addScalar("unit", StringType.INSTANCE);
  		query.addScalar("type", LongType.INSTANCE); 
  		query.addScalar("workAmount", new DoubleType());
  		
  		query.setParameter("id", id);
  		query.setResultTransformer(Transformers.aliasToBean(EstimatesWorkItemsDTO.class));

  		return query.list();
  	}
    
    
    
	 public List<EstimatesWorkItemsBO> getAllandSearch(EstimatesWorkItemsDTO obj) {

			Query q = getSession().createQuery(Joiner.on(" ").join("from estimatesworkitems a Join fetch a.constrestimateinfo b Join fetch b.constrconstructions c  where 1=1 ",


			obj.getConstructId() !=null ? " and c.constructId = :constructId" : ""));		
			if (obj.getConstructId()!= null) {
				q.setParameter("constructId",  obj.getConstructId());
			}	
			return q.list();
		}
    
    
	    @Transactional
	    public Integer DeleteEstimatesWorkItems(Long idwi) throws Exception{
	    	      Session session = getSession();
	    	            	StringBuilder sql = new StringBuilder();
	    	                sql.append("DELETE FROM ESTIMATES_WORK_ITEMS");
	    	                sql.append(" WHERE ");
	    	                sql.append("ESTIMATES_WORK_ITEM_ID =:idwi");
	    	         Integer id = session.createSQLQuery(sql.toString()).setParameter("idwi", idwi).executeUpdate();
	    	      return id;
	    }
    
    
    
	    @SuppressWarnings("unchecked")
		public void updateStatus(Long estimatesWorkItemId) {

			StringBuffer sql = new StringBuffer("update ESTIMATES_WORK_ITEMS " + "set STATUS = 1 "
					+ " where ESTIMATES_WORK_ITEM_ID = :estimatesWorkItemId");
			SQLQuery query = getSession().createSQLQuery(sql.toString());
			query.setParameter("estimatesWorkItemId", estimatesWorkItemId);
			query.executeUpdate();
		}
    
    
    
    
    
}
