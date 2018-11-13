/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.ims.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;



//import com.viettel.utils.ValidateUtils;
import com.viettel.ims.bo.BiddingPackageBO;
import com.viettel.ims.dto.BiddingPackageDTO;
import com.viettel.ims.dto.BiddingPackageDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("biddingPackageDAO")
public class BiddingPackageDAO extends BaseFWDAOImpl<BiddingPackageBO, Long> {

    public BiddingPackageDAO() {
        this.model = new BiddingPackageBO();
    }

    public BiddingPackageDAO(Session session) {
        this.session = session;
    }
    
    public List<BiddingPackageDTO> doSearch(BiddingPackageDTO obj){
		StringBuilder sql = new StringBuilder("Select BIDDING_PACKAGE_ID biddingPackageId,"
				+ "CODE code,"
				+ "NAME name,"
				+ "PRICE price,"
				+ "PROCUREMENT_FORMS_ID procurementFormsId,"
				+ "INVESTMENT_OWNER_TYPE investmentOwnerType,"
				+ "CONTENT content,"
				+ "SIGN_DATE signDate,"
				+ "STATUS status,"
				+ "CREATED_DATE createdDate,"
				+ "CREATED_USER_ID createdUserId,"
				+ "CREATED_GROUP_ID createdGroupId,"
				+ "UPDATED_DATE updatedDate,"
				+ "UPDATED_USER_ID updatedUserId,"
				+ "UPDATED_GROUP_ID updatedGroupId"
				+ " FROM BIDDING_PACKAGE"
				+ " WHERE 1=1");
		if(obj.getStatus() != null ){
			sql.append(" AND STATUS = :status ");
		}
		if(obj.getProcurementFormsId() != null){
			sql.append(" AND PROCUREMENT_FORMS_ID = :procurementFormsId");
		}
		
		if(obj.getInvestmentOwnerType() != null){
			sql.append(" AND INVESTMENT_OWNER_TYPE  = :investmentOwnerType");
		}
		if (null != obj.getSignDateFrom()) {
			sql.append("AND SIGN_DATE >= :signDateFrom ");
		}
		if (null != obj.getSignDateTo()) {
			sql.append("AND SIGN_DATE <= :signDateTo ");
		}
		if(StringUtils.isNotEmpty(obj.getKeySearch())){
			sql.append(" AND (UPPER(NAME) like UPPER(:key) OR UPPER(CODE) like UPPER(:key) escape '&')");
		}
		sql.append("ORDER BY BIDDING_PACKAGE_ID DESC");

		
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");
		
		SQLQuery query= getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount=getSession().createSQLQuery(sqlCount.toString());
		
		query.addScalar("biddingPackageId", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("name", new StringType());
		query.addScalar("price", new LongType());
		query.addScalar("procurementFormsId", new LongType());
		query.addScalar("investmentOwnerType", new LongType());
		query.addScalar("content", new StringType());
		query.addScalar("signDate", new DateType());
		query.addScalar("status", new LongType());
		query.addScalar("createdDate", new DateType());
		query.addScalar("createdUserId", new LongType());
		query.addScalar("createdGroupId", new LongType());
		query.addScalar("updatedDate", new DateType());
		query.addScalar("updatedUserId", new LongType());
		query.addScalar("updatedGroupId", new LongType());
		
		query.setResultTransformer(Transformers.aliasToBean(BiddingPackageDTO.class));
		
		if(obj.getStatus() != null){
			query.setParameter("status", obj.getStatus());
			queryCount.setParameter("status", obj.getStatus());
		}
		if(obj.getProcurementFormsId() != null){
			query.setParameter("procurementFormsId", obj.getProcurementFormsId());
			queryCount.setParameter("procurementFormsId", obj.getProcurementFormsId());
		}
		if(obj.getInvestmentOwnerType() != null){
			query.setParameter("investmentOwnerType", obj.getInvestmentOwnerType());
			queryCount.setParameter("investmentOwnerType", obj.getInvestmentOwnerType());
		}
		if(StringUtils.isNotEmpty(obj.getKeySearch())){
			query.setParameter("key","%"+ obj.getKeySearch()+"%");
			queryCount.setParameter("key", "%"+ obj.getKeySearch()+"%");
		}
		if (null != obj.getSignDateFrom()) {
			query.setTimestamp("signDateFrom", obj.getSignDateFrom());
			queryCount.setTimestamp("signDateFrom", obj.getSignDateFrom());
		}
		if (null != obj.getSignDateTo()) {
			query.setTimestamp("signDateTo", obj.getSignDateTo());
			queryCount.setTimestamp("signDateTo", obj.getSignDateTo());
		}
		if (obj.getPage() != null && obj.getPageSize() != null) {
			query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
			query.setMaxResults(obj.getPageSize().intValue());
		}
		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
		
		return query.list();
	}
    
    
    public BiddingPackageDTO getByCode(String code){
		StringBuilder sql = new StringBuilder("Select BIDDING_PACKAGE_ID biddingPackageId,"
				+ "CODE code,"
				+ "NAME name,"
				+ "PRICE price,"
				+ "PROCUREMENT_FORMS_ID procurementFormsId,"
				+ "INVESTMENT_OWNER_TYPE investmentOwnerType,"
				+ "CONTENT content,"
				+ "SIGN_DATE signDate,"
				+ "STATUS status,"
				+ "CREATED_DATE createdDate,"
				+ "CREATED_USER_ID createdUserId,"
				+ "CREATED_GROUP_ID createdGroupId,"
				+ "UPDATED_DATE updatedDate,"
				+ "UPDATED_USER_ID updatedUserId,"
				+ "UPDATED_GROUP_ID updatedGroupId"
				+ " FROM BIDDING_PACKAGE"
				+ " WHERE UPPER(CODE) = UPPER(:code)");
		
		SQLQuery query= getSession().createSQLQuery(sql.toString());
		
		query.addScalar("biddingPackageId", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("name", new StringType());
		query.addScalar("price", new LongType());
		query.addScalar("procurementFormsId", new LongType());
		query.addScalar("investmentOwnerType", new LongType());
		query.addScalar("content", new StringType());
		query.addScalar("signDate", new DateType());
		query.addScalar("status", new LongType());
		query.addScalar("createdDate", new DateType());
		query.addScalar("createdUserId", new LongType());
		query.addScalar("createdGroupId", new LongType());
		query.addScalar("updatedDate", new DateType());
		query.addScalar("updatedUserId", new LongType());
		query.addScalar("updatedGroupId", new LongType());
		
		query.setResultTransformer(Transformers.aliasToBean(BiddingPackageDTO.class));
		
		query.setParameter("code", code);
		
		return (BiddingPackageDTO) query.uniqueResult();
	}
	
    public List<BiddingPackageDTO> getForAutoComplete(
			BiddingPackageDTO obj) {
    	StringBuilder stringBuilder = new StringBuilder("Select BIDDING_PACKAGE_ID biddingPackageId,"
				+ "CODE code,"
				+ "NAME name,"
				+ "PRICE price,"
				+ "PROCUREMENT_FORMS_ID procurementFormsId,"
				+ "INVESTMENT_OWNER_TYPE investmentOwnerType,"
				+ "CONTENT content,"
				+ "SIGN_DATE signDate,"
				+ "STATUS status,"
				+ "CREATED_DATE createdDate,"
				+ "CREATED_USER_ID createdUserId,"
				+ "CREATED_GROUP_ID createdGroupId,"
				+ "UPDATED_DATE updatedDate,"
				+ "UPDATED_USER_ID updatedUserId,"
				+ "UPDATED_GROUP_ID updatedGroupId"
				+ " FROM BIDDING_PACKAGE"
				+ " WHERE STATUS=1");
		stringBuilder.append(obj.getIsSize() ? " AND ROWNUM <=10" : "");
		if(StringUtils.isNotEmpty(obj.getKeySearch())){
			stringBuilder.append(" AND (UPPER(NAME) like UPPER(:key) OR UPPER(CODE) like UPPER(:key) escape '&')");
		}
		stringBuilder.append(" ORDER BY NAME");

		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());

		query.addScalar("biddingPackageId", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("name", new StringType());
		query.addScalar("price", new LongType());
		query.addScalar("procurementFormsId", new LongType());
		query.addScalar("investmentOwnerType", new LongType());
		query.addScalar("content", new StringType());
		query.addScalar("signDate", new DateType());
		query.addScalar("status", new LongType());
		query.addScalar("createdDate", new DateType());
		query.addScalar("createdUserId", new LongType());
		query.addScalar("createdGroupId", new LongType());
		query.addScalar("updatedDate", new DateType());
		query.addScalar("updatedUserId", new LongType());
		query.addScalar("updatedGroupId", new LongType());
		query.setResultTransformer(Transformers
				.aliasToBean(BiddingPackageDTO.class));

		if(StringUtils.isNotEmpty(obj.getKeySearch())){
			query.setParameter("key","%"+ obj.getKeySearch()+"%");
		}
		return query.list();
	}
    
    public List<BiddingPackageDTO> getAll(){
		StringBuilder sql = new StringBuilder("Select BIDDING_PACKAGE_ID biddingPackageId,"
				+ "CODE code,"
				+ "NAME name,"
				+ "PRICE price,"
				+ "PROCUREMENT_FORMS_ID procurementFormsId,"
				+ "INVESTMENT_OWNER_TYPE investmentOwnerType,"
				+ "CONTENT content,"
				+ "SIGN_DATE signDate,"
				+ "STATUS status,"
				+ "CREATED_DATE createdDate,"
				+ "CREATED_USER_ID createdUserId,"
				+ "CREATED_GROUP_ID createdGroupId,"
				+ "UPDATED_DATE updatedDate,"
				+ "UPDATED_USER_ID updatedUserId,"
				+ "UPDATED_GROUP_ID updatedGroupId"
				+ " FROM BIDDING_PACKAGE"
				+ " where STATUS=1");
	
		
//		sql.append(" ORDER BY CT.TAX_ID DESC ");
		
	
		SQLQuery query= getSession().createSQLQuery(sql.toString());
		
		
		query.addScalar("biddingPackageId", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("name", new StringType());
		query.addScalar("price", new LongType());
		query.addScalar("procurementFormsId", new LongType());
		query.addScalar("investmentOwnerType", new LongType());
		query.addScalar("content", new StringType());
		query.addScalar("signDate", new DateType());
		query.addScalar("status", new LongType());
		query.addScalar("createdDate", new DateType());
		query.addScalar("createdUserId", new LongType());
		query.addScalar("createdGroupId", new LongType());
		query.addScalar("updatedDate", new DateType());
		query.addScalar("updatedUserId", new LongType());
		query.addScalar("updatedGroupId", new LongType());
		
		query.setResultTransformer(Transformers.aliasToBean(BiddingPackageDTO.class));
		
		return query.list();
	}
	
}
