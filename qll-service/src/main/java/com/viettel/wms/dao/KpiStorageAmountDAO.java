/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.wms.dao;

import com.viettel.wms.bo.KpiStorageAmountBO;
import com.viettel.wms.bo.KpiStorageTimeBO;
import com.viettel.wms.dto.CommonDTO;
import com.viettel.wms.dto.KpiStorageAmountDTO;
import com.viettel.wms.dto.KpiStorageTimeDTO;
import com.viettel.wms.dto.StockGoodsTotalDTO;
import com.viettel.wms.utils.ValidateUtils;
import com.viettel.service.base.dao.BaseFWDAOImpl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("kpiStorageAmountDAO")
public class KpiStorageAmountDAO extends BaseFWDAOImpl<KpiStorageAmountBO, Long> {

    public KpiStorageAmountDAO() {
        this.model = new KpiStorageAmountBO();
    }

    public KpiStorageAmountDAO(Session session) {
        this.session = session;
    }
    // Hàm tìm kiếm của báo cáo tồn kho KPI theo số lượng
    @SuppressWarnings("unchecked")
    public List<KpiStorageAmountDTO> doSearchKpiAmount(KpiStorageAmountDTO obj){
		StringBuilder sql = new StringBuilder("SELECT s.STOCK_ID stockId,"
				+ "s.CODE stockCode,"
				+ "s.NAME stockName,"
				+ "(s.NAME ||' (' || s.CODE || ')') text,"
				+ "g.NAME goodsTypeName,"
				+ "ksa.GOODS_CODE goodsCode,"
				+ "ksa.GOODS_NAME goodsName,"
				+ "ksa.GOODS_STATE_NAME goodsStateName,"
				+ "ksa.GOODS_UNIT_NAME goodsUnitName,"
				+ "ksa.AMOUNT_QUATO amountQuato,"
				+ "ksa.AMOUNT_REMAIN amountRemain,"
				+ "ksa.AMOUNT_KPI amountKpi "
				+ " FROM WMS_OWNER_KTTS.KPI_STORAGE_AMOUNT ksa "
				+ "INNER JOIN CAT_OWNER.STOCK s ON ksa.STOCK_ID=s.STOCK_ID "
				+ "INNER JOIN CAT_OWNER.GOODS_TYPE g ON ksa.GOODS_TYPE=g.GOODS_TYPE_ID WHERE s.STATUS=1");
		
		if((obj.getListGoodsType()!=null && !obj.getListGoodsType().isEmpty())  ){
			sql.append(" AND g.GOODS_TYPE_ID IN (:listGoodsType)");
		}
		if((obj.getListStockId()!=null && !obj.getListStockId().isEmpty())){
			   sql.append(" AND ksa.STOCK_ID IN :listStockId");
			}
		if(StringUtils.isNotEmpty(obj.getName())  ){
			sql.append(" AND (upper(ksa.GOODS_CODE) LIKE upper(:name) escape '&' OR upper(ksa.GOODS_NAME) LIKE upper(:name) escape '&')");
		}
		if(StringUtils.isNotEmpty(obj.getGoodsState()) && !"2".equals(obj.getGoodsState())  ){
			sql.append(" AND ksa.GOODS_STATE = :goodsState");
		}
		sql.append(" ORDER BY s.STOCK_ID ASC ");
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");
		
		SQLQuery query= getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount=getSession().createSQLQuery(sqlCount.toString());
		
		query.addScalar("stockId", new LongType());
		query.addScalar("goodsTypeName", new StringType());
		query.addScalar("goodsName", new StringType());
		query.addScalar("amountQuato", new DoubleType());
		query.addScalar("amountRemain", new DoubleType());
		query.addScalar("amountKpi", new DoubleType());
		query.addScalar("goodsStateName", new StringType());
		query.addScalar("stockName", new StringType());
		query.addScalar("stockCode", new StringType());
		query.addScalar("text", new StringType());
		query.addScalar("goodsCode", new StringType());
		query.addScalar("goodsUnitName", new StringType());
		
		
		query.setResultTransformer(Transformers.aliasToBean(KpiStorageAmountDTO.class));
		
		if((obj.getListStockId()!=null && !obj.getListStockId().isEmpty())){
			query.setParameterList("listStockId", obj.getListStockId());
			queryCount.setParameterList("listStockId", obj.getListStockId());
		}
		if(StringUtils.isNotEmpty(obj.getName()) ){
			query.setParameter("name", "%"+ ValidateUtils.validateKeySearch(obj.getName())+"%");
			queryCount.setParameter("name", "%"+ ValidateUtils.validateKeySearch(obj.getName())+"%");
			}
		if(StringUtils.isNotEmpty(obj.getGoodsState()) && !"2".equals(obj.getGoodsState()) ){
			query.setParameter("goodsState", obj.getGoodsState());
			queryCount.setParameter("goodsState", obj.getGoodsState());
		}
		if((obj.getListGoodsType()!=null) && (!obj.getListGoodsType().isEmpty())){
			query.setParameterList("listGoodsType", obj.getListGoodsType());
			queryCount.setParameterList("listGoodsType", obj.getListGoodsType());
		}
		query.setFirstResult((obj.getPage().intValue()-1)*obj.getPageSize().intValue());
		query.setMaxResults(obj.getPageSize().intValue());
		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
		
		return query.list();
	}
    
   //End
    
    public List<CommonDTO> getCharOneAmount(CommonDTO obj){
    	StringBuilder sql = new StringBuilder("SELECT Count(STD.GOODS_CODE) kipAmount,STOCK.CODE stockCode"
    			+ " FROM WMS_OWNER_KTTS.KPI_STORAGE_AMOUNT STD"
    			+ " LEFT JOIN CAT_OWNER.STOCK STOCK ON STOCK.STOCK_ID=STD.STOCK_ID"
    			+ " WHERE 1=1");
    	
    	if(obj.getListStockId().size()>0){
    		sql.append(" AND STD.STOCK_ID in (:listStockId)");
    	}
		
    	sql.append(" group by STOCK.CODE");
		SQLQuery query=getSession().createSQLQuery(sql.toString());
		
		query.addScalar("kipAmount",new LongType());
    	query.addScalar("stockCode",new StringType());
    	
		query.setResultTransformer(Transformers.aliasToBean(CommonDTO.class));
    	
		if(obj.getListStockId().size()>0){
    		query.setParameterList("listStockId", obj.getListStockId());
    	}
		
    	
    	return query.list();
    }
    
    
    public List<CommonDTO> getCharOneTimes(CommonDTO obj){
    	StringBuilder sql = new StringBuilder("SELECT Count(STD.GOODS_CODE) kipAmount,STOCK.CODE stockCode"
    			+ " FROM WMS_OWNER_KTTS.KPI_STORAGE_TIME STD"
    			+ " LEFT JOIN CAT_OWNER.STOCK STOCK ON STOCK.STOCK_ID=STD.STOCK_ID"
    			+ " WHERE 1=1");
    	
    	if(obj.getListStockId().size()>0){
    		sql.append(" AND STD.STOCK_ID in (:listStockId)");
    	}
		
    	sql.append(" group by STOCK.CODE");
		SQLQuery query=getSession().createSQLQuery(sql.toString());
		
		query.addScalar("kipAmount",new LongType());
    	query.addScalar("stockCode",new StringType());
    	
		query.setResultTransformer(Transformers.aliasToBean(CommonDTO.class));
    	
		if(obj.getListStockId().size()>0){
    		query.setParameterList("listStockId", obj.getListStockId());
    	}
		
    	
    	return query.list();
    }
}

