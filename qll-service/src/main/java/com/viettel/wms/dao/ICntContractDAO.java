/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.wms.dao;

import com.viettel.wms.bo.ICntContractBO;
import com.viettel.wms.dto.ICntContractDTO;
import com.viettel.wms.dto.IProjInvestProjectDTO;
import com.viettel.wms.dto.UserRoleDTO;
import com.viettel.wms.utils.ValidateUtils;
import com.viettel.service.base.dao.BaseFWDAOImpl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("iCntContractDAO")
public class ICntContractDAO extends BaseFWDAOImpl<ICntContractBO, Long> {

	public ICntContractDAO() {
		this.model = new ICntContractBO();
	}

	public ICntContractDAO(Session session) {
		this.session = session;
	}

	
	
	@SuppressWarnings("unchecked")
	public List<ICntContractDTO> doSearch(ICntContractDTO obj){
		StringBuilder sql = new StringBuilder("SELECT SC.CONTRACT_ID contractId,"
				+ " SC.CODE code "
				+ " FROM IMS_OWNER.I_CNT_CONTRACT SC WHERE 1=1");
		if(StringUtils.isNotEmpty(obj.getKeySearch())){
			sql.append(" AND upper(SC.CODE) LIKE upper(:keySearch)");
		}
		
		sql.append(" ORDER BY SC.CODE");
		
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");
		
		SQLQuery query= getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount=getSession().createSQLQuery(sqlCount.toString());
		
		query.addScalar("contractId", new LongType());
		query.addScalar("code", new StringType());
		
		query.setResultTransformer(Transformers.aliasToBean(ICntContractDTO.class));
		
		if(StringUtils.isNotEmpty(obj.getKeySearch())){
			query.setParameter("keySearch", "%"+ ValidateUtils.validateKeySearch(obj.getKeySearch())+"%");
			queryCount.setParameter("keySearch", "%"+ ValidateUtils.validateKeySearch(obj.getKeySearch())+"%");
		}
		
		query.setFirstResult((obj.getPage().intValue()-1)*obj.getPageSize().intValue());
		query.setMaxResults(obj.getPageSize().intValue());
		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
		
		return query.list();
	}

	public List<ICntContractDTO> getForAutoComplete(ICntContractDTO obj) {
		String sql = "SELECT SC.CODE contractCode,"
				   + "SC.CONTRACT_NAME contractName,"
				   +"SC.CNT_TYPE_ID cntTypeId,"
				   + "SC.CONTRACT_ID contractId "
				   + " FROM IMS_OWNER.I_CNT_CONTRACT SC"
				   + " WHERE 1=1";

		StringBuilder stringBuilder = new StringBuilder(sql);
		if(obj.getCntTypeId()!=null){
			stringBuilder.append(" AND SC.CNT_TYPE_ID=:cntTypeId");
		}
		if(StringUtils.isNotEmpty(obj.getContractCode())){
			stringBuilder.append(" AND (upper(SC.CODE) LIKE upper(:contractCode) OR upper(SC.CONTRACT_NAME) LIKE upper(:contractCode))");
		}
		stringBuilder.append(" ORDER BY SC.CODE");

		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());

		query.addScalar("contractCode", new StringType());
		query.addScalar("contractName", new StringType());
		query.addScalar("contractId", new LongType());
		query.addScalar("cntTypeId", new LongType());
		query.setResultTransformer(Transformers.aliasToBean(ICntContractDTO.class));

		if (StringUtils.isNotEmpty(obj.getContractCode())) {
			query.setParameter("contractCode", "%" + obj.getContractCode() + "%");
		}
		if(obj.getCntTypeId()!=null){
			query.setParameter("cntTypeId",(long)obj.getCntTypeId());
		}
		return query.list();
	}
}
