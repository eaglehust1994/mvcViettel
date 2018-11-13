/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.wms.dao;

import com.viettel.wms.bo.KpiStorageTimeBO;
import com.viettel.wms.dto.KpiStorageTimeDTO;
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
@Repository("kpiStorageTimeDAO")
public class KpiStorageTimeDAO extends BaseFWDAOImpl<KpiStorageTimeBO, Long> {

    public KpiStorageTimeDAO() {
        this.model = new KpiStorageTimeBO();
    }

    public KpiStorageTimeDAO(Session session) {
        this.session = session;
    }
    //Hàm tìm kiếm của báo cáo KPI tồn kho theo thời gian
    @SuppressWarnings("unchecked")
    public List<KpiStorageTimeDTO> doSearchKpiTime(KpiStorageTimeDTO obj){
		StringBuilder sql = new StringBuilder("SELECT s.NAME stockName,"
				+ "(s.NAME ||' (' || s.CODE || ')') text,"
				+ "g.NAME goodsTypeName,"
				+ "kst.GOODS_CODE goodsCode,"
				+ "kst.GOODS_NAME goodsName,"
				+ "kst.GOODS_STATE_NAME goodsStateName,"
				+ "kst.GOODS_UNIT_NAME goodsUnitName,"
				+ "kst.CONTRACT_CODE contractCode,"
				+ "kst.PROJECT_CODE projectCode,"
				+ "kst.SERIAL serial,"
				+ "kst.AMOUNT amount,"
				+ "kst.TIME_QUATO timeQuato,"
				+ "kst.TIME_STORAGE timeStorage,"
				+ "kst.TIME_KPI timeKpi "
				+ " FROM WMS_OWNER_KTTS.KPI_STORAGE_TIME kst "
				+ "INNER JOIN CAT_OWNER.STOCK s ON kst.STOCK_ID=s.STOCK_ID "
				+ "INNER JOIN CAT_OWNER.GOODS_TYPE g ON kst.GOODS_TYPE=g.GOODS_TYPE_ID WHERE s.STATUS=1");
		if(StringUtils.isNotEmpty(obj.getGoodsState()) && !"2".equals(obj.getGoodsState())  ){
			sql.append(" AND kst.GOODS_STATE = :goodsState");
		}
		if((obj.getListGoodsType()!=null) && (!obj.getListGoodsType().isEmpty())){
			sql.append(" AND g.GOODS_TYPE_ID IN (:listGoodsType)");
		}
		if(StringUtils.isNotEmpty(obj.getKeySearch())  ){
			sql.append(" AND (upper(kst.CONTRACT_CODE) LIKE upper(:keySearch) escape '&' OR upper(kst.PROJECT_CODE) LIKE upper(:keySearch) escape '&' OR upper(kst.SHIPMENT_CODE) LIKE upper(:keySearch) escape '&')");
		}
		if((obj.getListStockId()!=null && !obj.getListStockId().isEmpty())){
		   sql.append(" AND kst.STOCK_ID IN :listStockId");
		}
		if(StringUtils.isNotEmpty(obj.getName())  ){
			sql.append(" AND (upper(kst.GOODS_CODE) LIKE upper(:name) escape '&' OR upper(kst.GOODS_NAME) LIKE upper(:name) escape '&')");
		}
		
		sql.append(" ORDER BY s.STOCK_ID ASC");
		
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");
		
		SQLQuery query= getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount=getSession().createSQLQuery(sqlCount.toString());
		
		query.addScalar("goodsTypeName", new StringType());
		query.addScalar("goodsName", new StringType());
		query.addScalar("goodsUnitName", new StringType());
		query.addScalar("contractCode", new StringType());
		query.addScalar("amount", new DoubleType());
		query.addScalar("goodsStateName", new StringType());
		query.addScalar("serial", new StringType());
		query.addScalar("stockName", new StringType());
		query.addScalar("projectCode", new StringType());
		query.addScalar("goodsCode", new StringType());
		query.addScalar("timeQuato", new LongType());
		query.addScalar("timeKpi", new LongType());
		query.addScalar("timeStorage", new LongType());
		query.addScalar("text", new StringType());
		
		query.setResultTransformer(Transformers.aliasToBean(KpiStorageTimeDTO.class));
		if(StringUtils.isNotEmpty(obj.getKeySearch())){
		query.setParameter("keySearch", "%"+ ValidateUtils.validateKeySearch(obj.getKeySearch())+"%");
		queryCount.setParameter("keySearch", "%"+ ValidateUtils.validateKeySearch(obj.getKeySearch())+"%");
		}
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
		if((obj.getListGoodsType()!=null) && !obj.getListGoodsType().isEmpty()){
			query.setParameterList("listGoodsType", obj.getListGoodsType());
			queryCount.setParameterList("listGoodsType", obj.getListGoodsType());
		}
		query.setFirstResult((obj.getPage().intValue()-1)*obj.getPageSize().intValue());
		query.setMaxResults(obj.getPageSize().intValue());
		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
		
		return query.list();
	}
    //End
   
}

