/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.wms.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.hibernate.type.TimestampType;
import org.springframework.stereotype.Repository;

import com.viettel.service.base.dao.BaseFWDAOImpl;
import com.viettel.wms.bo.StockDailyRemainBO;
import com.viettel.wms.dto.StockDailyRemainDTO;
import com.viettel.wms.utils.ValidateUtils;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("stockDailyRemainDAO")
public class StockDailyRemainDAO extends BaseFWDAOImpl<StockDailyRemainBO, Long> {

    public StockDailyRemainDAO() {
        this.model = new StockDailyRemainBO();
    }

    public StockDailyRemainDAO(Session session) {
        this.session = session;
    }
    
    @SuppressWarnings("unchecked")
	public List<StockDailyRemainDTO> doSearch(StockDailyRemainDTO obj){
		StringBuilder sql = new StringBuilder("SELECT STOCK_NAME stockName,"
				+ "GOODS_CODE goodsCode,"
				+ "GOODS_NAME goodsName,"
				+ "GOODS_TYPE goodsType,"
				+ "AMOUNT amount, "
				+ "GOODS_STATE_NAME stateName"
				+ " FROM WMS_OWNER_KTTS.STOCK_DAILY_REMAIN WHERE 1=1");
		if(StringUtils.isNotEmpty(obj.getGoodsName())){
			sql.append(" AND (upper(STOCK_NAME) LIKE upper(:keySearch)");
		}
	
		sql.append(" ORDER BY STOCK_NAME");
		
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");
		
		SQLQuery query= getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount=getSession().createSQLQuery(sqlCount.toString());
		
		query.addScalar("stockName", new StringType());
		query.addScalar("goodsCode", new StringType());
		query.addScalar("goodsName", new StringType());
		query.addScalar("goodsType", new LongType());
		query.addScalar("amount", new DoubleType());
		
		
		query.setResultTransformer(Transformers.aliasToBean(StockDailyRemainDTO.class));
		if(StringUtils.isNotEmpty(obj.getKeySearch())){
		query.setParameter("keySearch", "%"+ ValidateUtils.validateKeySearch(obj.getKeySearch())+"%");
		queryCount.setParameter("keySearch", "%"+ ValidateUtils.validateKeySearch(obj.getKeySearch())+"%");
		}
		
		query.setFirstResult((obj.getPage().intValue()-1)*obj.getPageSize().intValue());
		query.setMaxResults(obj.getPageSize().intValue());
		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
		
		return query.list();
	}
    
    public StockDailyRemainDTO getAmount(Date remainDate,StockDailyRemainDTO obj){
    	StringBuilder sql = new StringBuilder("SELECT NVL(AMOUNT,0) amount,"
    			+ "GOODS_CODE goodsCode,"
				+ "GOODS_NAME goodsName,"
				+ "STOCK_NAME stockName,"
				+ "STOCK_CODE stockCode, "
				+ "GOODS_STATE_NAME goodsStateName"
    			+ " FROM WMS_OWNER_KTTS.STOCK_DAILY_REMAIN WHERE trunc(REMAIN_DATE)=trunc(:date) "
    			+ " AND GOODS_ID=:goodsId"
    			+ " AND STOCK_ID =:stockId"
    			+ " AND GOODS_STATE=:goodsState"
    			+ "");
    	SQLQuery query= getSession().createSQLQuery(sql.toString());
    	
    	query.addScalar("stockName", new StringType());
		query.addScalar("goodsCode", new StringType());
		query.addScalar("goodsName", new StringType());
		query.addScalar("goodsStateName", new StringType());
		query.addScalar("amount", new DoubleType());
		query.addScalar("stockCode", new StringType());
		query.setResultTransformer(Transformers.aliasToBean(StockDailyRemainDTO.class));
    	query.setTimestamp("date", remainDate);
    	query.setParameter("goodsId", obj.getGoodsId());
		query.setParameter("stockId", obj.getStockId());
		query.setParameter("goodsState", obj.getGoodsState());
    	
    	return (StockDailyRemainDTO) query.uniqueResult();
    	
    }
    
    public List<StockDailyRemainDTO> getListForExport(StockDailyRemainDTO obj,Date startDate,Date endDate){
    	StringBuilder sql = new StringBuilder("SELECT ST.CREATED_BY_NAME createdByName,"
    			+ " ST.REAL_IE_TRANS_DATE realIeTransDate, "
    			+ " ST.DESCRIPTION description,"
    			+ " (Case when ST.TYPE=1 THEN ST.CODE END) imCode,"
    			+ " (Case when ST.TYPE=2 THEN ST.CODE END) exCode,"
    			+ " NVL((Case when ST.TYPE=1 THEN STD.AMOUNT_REAL END),0) imAmount,"
    			+ " NVL((Case when ST.TYPE=2 THEN STD.AMOUNT_REAL END),0) exAmount,"
    			+ " STD.GOODS_NAME goodsName,"
    			+ " STD.GOODS_CODE goodsCode,"
    			+ " STD.GOODS_STATE_NAME goodsStateName,"
    			+ " STOCK.NAME stockName"
    			+ " FROM WMS_OWNER_KTTS.STOCK_TRANS ST"
    			+ " JOIN WMS_OWNER_KTTS.STOCK_TRANS_DETAIL STD ON ST.STOCK_TRANS_ID=STD.STOCK_TRANS_ID"
    			+ " JOIN CAT_OWNER.STOCK STOCK ON STOCK.STOCK_ID=ST.STOCK_ID"
    			+ " WHERE STD.GOODS_CODE=:goodsCode"
    			+ " AND ST.STOCK_ID=:stockId"
    			+ " AND STD.GOODS_STATE=:goodsState"
    			+ " AND ST.REAL_IE_TRANS_DATE>=:startDate"
    			+ " AND ST.REAL_IE_TRANS_DATE<=:endDate"
    			+ " ORDER BY ST.REAL_IE_TRANS_DATE ASC");
    	
    	SQLQuery query= getSession().createSQLQuery(sql.toString());
    	
    	query.addScalar("stockName", new StringType());
		query.addScalar("goodsCode", new StringType());
		query.addScalar("goodsName", new StringType());
		
		query.addScalar("imAmount", new LongType());
		query.addScalar("exAmount", new LongType());
		query.addScalar("realIeTransDate", new TimestampType());
		query.addScalar("createdByName", new StringType());
		query.addScalar("imCode", new StringType());
		query.addScalar("exCode", new StringType());
		query.addScalar("description", new StringType());
		query.setResultTransformer(Transformers.aliasToBean(StockDailyRemainDTO.class));
		query.setParameter("goodsCode", obj.getGoodsCode());
		query.setParameter("stockId", obj.getStockId());
		query.setParameter("goodsState", obj.getGoodsState());
		query.setTimestamp("startDate", startDate);
		query.setTimestamp("endDate", endDate);
    	return query.list();
    }
}
