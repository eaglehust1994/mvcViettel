/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.wms.dao;

import com.viettel.wms.bo.ConfigSignVofficeBO;
import com.viettel.wms.dto.ConfigSignVofficeDTO;
import com.viettel.wms.dto.StockDTO;
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
@Repository("configSignVofficeDAO")
public class ConfigSignVofficeDAO extends BaseFWDAOImpl<ConfigSignVofficeBO, Long> {

    public ConfigSignVofficeDAO() {
        this.model = new ConfigSignVofficeBO();
    }

    public ConfigSignVofficeDAO(Session session) {
        this.session = session;
    }
    
    //Lấy danh sách Kho -- TUANNB
    @SuppressWarnings("unchecked")
    public List<StockDTO> doSearchStock(StockDTO obj){
    	StringBuilder sql = new StringBuilder("SELECT ST.STOCK_ID stockId," 
				+ "ST.CODE code," 
				+ "ST.NAME name,"
				+ "ST.STATUS status, "
				+ "dp.NAME departmentName "
				+ "FROM CAT_OWNER.STOCK ST "
				+ "LEFT JOIN CAT_OWNER.DEPARTMENT dp ON ST.DEPARTMENT_ID = dp.DEPARTMENT_ID "
				+ "WHERE ST.STATUS = 1 ");
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			sql.append(" AND (upper(ST.CODE) LIKE upper(:keySearch) OR upper(ST.NAME) LIKE upper(:keySearch))");
		}

		if(obj.getListStockId().size()>0){
			sql.append(" AND ST.STOCK_ID in (:listStockId) ");
		}
		
		sql.append("ORDER BY ST.CODE ");
		
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");
		
		SQLQuery query= getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount=getSession().createSQLQuery(sqlCount.toString());

		query.addScalar("stockId", new LongType());
		query.addScalar("name", new StringType());
		query.addScalar("code", new StringType());
		query.addScalar("departmentName", new StringType());
		
		query.setResultTransformer(Transformers.aliasToBean(StockDTO.class));
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
			queryCount.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
		}
		if(obj.getListStockId().size()>0){
			query.setParameterList("listStockId", obj.getListStockId());
			queryCount.setParameterList("listStockId", obj.getListStockId());
		}
		if(obj.getPage()!=null && obj.getPageSize()!=null){
			query.setFirstResult((obj.getPage().intValue()-1)*obj.getPageSize().intValue());
			query.setMaxResults(obj.getPageSize().intValue());
		}
		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());		
		return query.list();
	}
    
    
    //Lấy danh sách người ký theo từng kho  -- TUANNB
    @SuppressWarnings("unchecked")
    public List<ConfigSignVofficeDTO> getDataByID(ConfigSignVofficeDTO obj){
		StringBuilder sql = new StringBuilder("SELECT csv.CONFIG_SIGN_VOFFICE_ID configSignVofficeId,"
				+ "st.STOCK_ID stockId,"
				+ "btc.BUSSINESS_TYPE bussTypeId,"
				+ "btc.ODER oder, "
				+ "btc.NAME oderName,"
				+ "sys.FULL_NAME fullName,"
				+ "sys.SYS_USER_ID sysUserId,"
				+ "sys.EMAIL email,"
				+ "csv.ROLE_ID sysRoleId,"
				+ "csv.ROLE_NAME sysRoleName,"
				+ "csv.VOFFICE_USER_ID VofficeUserId, "
				+ "csv.VOFFICE_ADORGID VofficeAdOrgId, "
				+ "btc.BUSSINESS_TYPE_CONFIG_ID bussinessTypeConfigId "
				+ "FROM CAT_OWNER.STOCK st "
				+ "JOIN WMS_OWNER_KTTS.BUSSINESS_TYPE_CONFIG btc ON :bussTypeId = btc.BUSSINESS_TYPE "
				+ "LEFT JOIN WMS_OWNER_KTTS.CONFIG_SIGN_VOFFICE csv ON btc.BUSSINESS_TYPE_CONFIG_ID = csv.BUSSINESS_TYPE_CONFIG_ID "
				+ "AND csv.STOCK_ID = st.STOCK_ID "
				+ "LEFT JOIN VPS_OWNER.SYS_USER sys ON sys.SYS_USER_ID = csv.SYS_USER_ID "
				+ "WHERE st.STOCK_ID = :stockId ");
		sql.append(" ORDER BY btc.ODER");
		SQLQuery query= getSession().createSQLQuery(sql.toString());
		
		query.addScalar("configSignVofficeId", new LongType());
		query.addScalar("sysUserId", new LongType());
		query.addScalar("stockId", new LongType());
		query.addScalar("bussTypeId", new StringType());
		query.addScalar("oder", new LongType());
		query.addScalar("oderName", new StringType());
		query.addScalar("fullName", new StringType());
		query.addScalar("email", new StringType());
		query.addScalar("sysRoleId", new LongType());
		query.addScalar("sysRoleName", new StringType());
		query.addScalar("VofficeUserId", new LongType());
		query.addScalar("VofficeAdOrgId", new LongType());
		query.addScalar("bussinessTypeConfigId", new LongType());
		
		query.setResultTransformer(Transformers.aliasToBean(ConfigSignVofficeDTO.class));
		
		query.setParameter("stockId", obj.getStockId());
		query.setParameter("bussTypeId", obj.getBussTypeId());
		
		return query.list();
	}
	
}
