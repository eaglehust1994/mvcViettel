/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.wms.dao;

import com.viettel.wms.bo.IProjInvestProjectBO;
//import com.viettel.wms.dto.AppParamDTO;
import com.viettel.wms.dto.ICntContractDTO;
import com.viettel.wms.dto.IProjInvestProjectDTO;
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
@Repository("iProjInvestProjectDAO")
public class IProjInvestProjectDAO extends BaseFWDAOImpl<IProjInvestProjectBO, Long> {

    public IProjInvestProjectDAO() {
        this.model = new IProjInvestProjectBO();
    }

    public IProjInvestProjectDAO(Session session) {
        this.session = session;
    }
    
    @SuppressWarnings("unchecked")
	public List<IProjInvestProjectDTO> getForAutoComplete(IProjInvestProjectDTO obj) {
		String sql = "SELECT SC.PROJ_INVEST_PROJECT_ID projInvestProjectId"	
			+ " ,SC.CODE projectCode"
			+ " ,SC.NAME name "
			+ " FROM IMS_OWNER.I_PROJ_INVEST_PROJECT SC"
			+ " WHERE 1=1 ";
		
		StringBuilder stringBuilder = new StringBuilder(sql);
		
		if(StringUtils.isNotEmpty(obj.getProjectCode())){
			stringBuilder.append(" AND upper(SC.CODE) LIKE upper(:projectCode) OR upper(SC.NAME) LIKE upper(:projectCode)");
		}
		
		stringBuilder.append(" ORDER BY SC.CODE");
		
		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		
		query.addScalar("projInvestProjectId", new LongType());
		query.addScalar("projectCode", new StringType());
		query.addScalar("name", new StringType());
	
		query.setResultTransformer(Transformers.aliasToBean(IProjInvestProjectDTO.class));

		if (StringUtils.isNotEmpty(obj.getProjectCode())) {
			query.setParameter("projectCode", "%" + ValidateUtils.validateKeySearch(obj.getProjectCode()) + "%");
		}
		return query.list();
	}
    
    @SuppressWarnings("unchecked")
	public List<IProjInvestProjectDTO> doSearch(IProjInvestProjectDTO obj){
		StringBuilder sql = new StringBuilder("SELECT SC.PROJ_INVEST_PROJECT_ID projInvestProjectId,"
				+ " SC.CODE code,"
				+ " SC.NAME name "
				+ " FROM IMS_OWNER.I_PROJ_INVEST_PROJECT SC WHERE 1=1");
		if(StringUtils.isNotEmpty(obj.getKeySearch())){
			sql.append(" AND (upper(SC.CODE) LIKE upper(:keySearch) OR upper(SC.NAME) LIKE upper(:keySearch))");
		}
		
		sql.append(" ORDER BY SC.CODE");
		
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");
		
		SQLQuery query= getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount=getSession().createSQLQuery(sqlCount.toString());
		
		query.addScalar("projInvestProjectId", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("name", new StringType());
		
		query.setResultTransformer(Transformers.aliasToBean(IProjInvestProjectDTO.class));
		
		if(StringUtils.isNotEmpty(obj.getKeySearch())){
			query.setParameter("keySearch", "%"+ ValidateUtils.validateKeySearch(obj.getKeySearch())+"%");
			queryCount.setParameter("keySearch", "%"+ ValidateUtils.validateKeySearch(obj.getKeySearch())+"%");
		}
		
		query.setFirstResult((obj.getPage().intValue()-1)*obj.getPageSize().intValue());
		query.setMaxResults(obj.getPageSize().intValue());
		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
		
		return query.list();
	}
    
    //tim ma de tai du an theo ma nhap vao tren autocomplete
    public List<IProjInvestProjectDTO> getFromProjectCode(String code){
		StringBuilder sql = new StringBuilder("SELECT ipip.CODE code"
				+ " FROM IMS_OWNER.I_PROJ_INVEST_PROJECT ipip "
				+ "WHERE 1=1 AND ipip.CODE IS NOT NULL ");
		if(StringUtils.isNotEmpty(code.trim()) && !code.trim().equals("[]") && !code.trim().equals("")){
			sql.append(" AND upper(CODE) LIKE upper(:code)");
		}
		
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("code", new StringType());

		query.setResultTransformer(Transformers.aliasToBean(IProjInvestProjectDTO.class));

		if (StringUtils.isNotEmpty(code.trim()) && !code.trim().equals("[]") && !code.trim().equals("")) {
			query.setParameter("code", "%" + code + "%");
		}
		return query.list();
	}
    public IProjInvestProjectDTO checkCode(IProjInvestProjectDTO obj){
		StringBuilder sql= new StringBuilder(
				 "SELECT ipip.PROJ_INVEST_PROJECT_ID projInvestProjectId,"
						   + "ipip.CODE projectCode "
						   + " FROM IMS_OWNER.I_PROJ_INVEST_PROJECT ipip"
						   + " WHERE 1=1"
				);
		if(obj.getProjInvestProjectId()!=null){
			sql.append(" AND ipip.PROJ_INVEST_PROJECT_ID=:projInvestProjectId");
		}
		if(obj.getProjInvestProjectId()==null||obj.getProjectCode()==null){
			sql.append(" AND 1=2");
		}
		if(obj.getProjectCode()!=null){
			sql.append(" AND ipip.CODE=:projectCode");
		}
		SQLQuery query=getSession().createSQLQuery(sql.toString());
		query.addScalar("projInvestProjectId", new  LongType());
		query.addScalar("projectCode", new  StringType());
		query.setResultTransformer(Transformers.aliasToBean(IProjInvestProjectDTO.class));
		if(obj.getProjInvestProjectId()!=null){
		query.setLong("projInvestProjectId",obj.getProjInvestProjectId());
		}
		if(obj.getProjectCode()!=null){
			query.setParameter("projectCode", obj.getProjectCode());
		}
		return (IProjInvestProjectDTO)query.uniqueResult();
	}
}
